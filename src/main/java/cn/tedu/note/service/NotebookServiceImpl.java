package cn.tedu.note.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.note.dao.NoteDAO;
import cn.tedu.note.dao.NotebookDAO;
import cn.tedu.note.dao.UserDAO;
import cn.tedu.note.entity.Notebook;
import cn.tedu.note.entity.User;

@Service("notebookService")
public class NotebookServiceImpl implements NotebookService{
  	
	@Resource
	private NotebookDAO notebookDAO;
	@Resource
	private UserDAO userDAO;
	@Resource
	private NoteDAO noteDAO;
	
	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	public List<Map<String, Object>> listNotebooks(String userId) throws UserNotFoundException {
		if(userId==null||userId.trim().isEmpty()){
			throw new UserNameException("请填写ID");
		}
		
		User user = userDAO.findUserById(userId);
		if(user==null){
			throw new UserNotFoundException("没有此用户");
		}
		return notebookDAO.findNotebooksByUserId(userId);
	}

	@Transactional
	public Notebook addNotebook(String userId, String notebookName) throws UserNameException {
//		System.out.println(userId);
		if(userId == null || userId.trim().isEmpty()){
			throw new UserNameException("请填写ID");
		}
		User user = userDAO.findUserById(userId);
		if(user==null){
			throw new UserNotFoundException("没有此用户");
		}
		if(notebookName ==null ||notebookName.trim().isEmpty()){
			throw new NotebookNotFoundException("请填写笔记本名称");
		}
		String desc= "这家伙太懒没有添加备注!";
		Notebook notebook = new Notebook();
		notebook.setUserId(userId);
		notebook.setName(notebookName);
		notebook.setTypeId("1");
		notebook.setDesc(desc);
		notebook.setId(UUID.randomUUID().toString());
		notebook.setCreatetime(new Timestamp(System.currentTimeMillis()));
//		System.out.println(notebook);
		int i =notebookDAO.addNotebook(notebook);
		if(i!=1){
			throw new NotebookNotFoundException("创建失败");
		}
		return notebook;
	}
	@Resource
	private NoteService noteService;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteNoteBook(String id) {
		if(id==null||id.trim().isEmpty()){
			throw new NotebookNotFoundException("id为空");
		}
		List<Map<String,Object>> list = noteService.listNotes(id);
		for(Map<String,Object> map : list){
			String noteId = (String) map.get("id");
			noteService.deleteAll(noteId);
		}
		notebookDAO.deleteNotebook(id);
	}

	public List<Map<String, Object>> listNotebooks(String userId, int pageNum, int pageSize)
			throws UserNotFoundException {
		if(userId==null||userId.trim().isEmpty()){
			throw new UserNotFoundException("Id空");
		}
		User user = userDAO.findUserById(userId);
		if(user==null){
			throw new UserNotFoundException("无此用户");
		}
		//计算起始行号
		int start = pageNum*pageSize;
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("start", start);
		map.put("rows", pageSize);
		return notebookDAO.findNotebooksByPage(map);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
