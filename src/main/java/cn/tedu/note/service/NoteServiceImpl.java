package cn.tedu.note.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.note.dao.NoteDAO;
import cn.tedu.note.dao.NotebookDAO;
import cn.tedu.note.dao.UserDAO;
import cn.tedu.note.entity.Note;
import cn.tedu.note.entity.Notebook;
import cn.tedu.note.entity.User;

@Service("noteService")
public class NoteServiceImpl implements NoteService {
	
	@Resource
	private UserDAO userdao;
	@Resource
	private NoteDAO notedao;
	@Resource
	private NotebookDAO notebookdao;
	
	@Transactional
	public List<Map<String, Object>> listNotes(String notebookId) throws NotebookNotFoundException {

		if (notebookId == null || notebookId.trim().isEmpty()) {
			throw new NotebookNotFoundException("id空");
		}

		Notebook notebook = notebookdao.findNotebookById(notebookId);
		if (notebook == null) {
			throw new NotebookNotFoundException("无此笔记");
		}

		return notedao.findNoteByNotesbookId(notebookId);
	}
	
	@Transactional
	public Note loadNote(String id) throws NoteNotFoundException {
		if (id == null || id.trim().isEmpty()) {
			throw new NoteNotFoundException("id空");
		}
		Note note = notedao.findNoteById(id);
		if (note == null) {
			throw new NoteNotFoundException("ID错误");
		}
		return note;
	}
	
	@Transactional
	public boolean saveNote(String id, String title, String body) throws NoteNotFoundException {

		if (id == null || id.trim().isEmpty())
			throw new NoteNotFoundException("ID为空");

		int num = notedao.countNotesById(id);

		if (num != 1)
			throw new NoteNotFoundException("没有笔记");

		// 不改变原有title
		Map<String, Object> note = new HashMap<String, Object>();

		if (title != null && !title.trim().isEmpty())
			note.put("title", title);

		if (body == null)
			body = "";

		note.put("body", body);
		note.put("id", id);
		note.put("lastModifyTime", System.currentTimeMillis());
		int n = notedao.updateNote(note);
		return n == 1;
	}
	
	@Transactional
	public Note addNote(String userId, String notebookId, String title)
			throws UserNotFoundException, NotebookNotFoundException {
		if (userId == null || userId.trim().isEmpty())throw new NoteNotFoundException("ID为空");		
		
		User user = userdao.findUserById(userId);
		if(user==null)throw new UserNotFoundException("无此用户");	
		
		if (notebookId == null || notebookId.trim().isEmpty())throw new NotebookNotFoundException("id空");
		
		Notebook notebook = notebookdao.findNotebookById(notebookId);
		if (notebook == null)throw new NotebookNotFoundException("无此笔记");
		
		if (title == null || title.trim().isEmpty()){
			title = "九阴真经";
		}
		
		String id = UUID.randomUUID().toString();
		String statusId = "0";
		String typeId = "0";
		String body = "";
		Long now = System.currentTimeMillis();
		Note note = new Note(id, notebookId, userId, statusId, typeId, title, body, now, now);
		int n = notedao.addNote(note);
		if(n!=1){
			throw new NoteNotFoundException("存取失败");
		}
		return note;
	}

	@Transactional
	public int deleteAll(String... noteId) {
		int n = 0;
		for(String id : noteId){
			int i =notedao.countNotesById(id);
//			System.out.println(i);
			if(i==0){
				throw new NoteNotFoundException(id);
			}
			notedao.deleteNote(id);
			n++;
		}
		return n;
	}


}
