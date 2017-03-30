package cn.tedu.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.note.dao.NoteDAO;
import cn.tedu.note.dao.NotebookDAO;
import cn.tedu.note.dao.PostDAO;
import cn.tedu.note.entity.Note;
import cn.tedu.note.entity.Notebook;
import cn.tedu.note.entity.Post;
import cn.tedu.note.service.NoteService;

public class NoteTest {
	ClassPathXmlApplicationContext ctx;
	@Before
	public void init(){
		ctx = new ClassPathXmlApplicationContext("spring-web.xml","spring-service.xml","spring-mybatis.xml");
	}
	
	@Test
	public void test1(){
		NoteDAO dao = ctx.getBean("noteDAO",NoteDAO.class);
		String id = "d0b0727f-a233-4a1f-8600-f49fc1f25bc9";
		List<Map<String,Object>> list = dao.findNoteByNotesbookId(id);
		for(Map<String,Object> n :list){
			System.out.println(n);
		}
	}
	
	@Test
	public void test2(){
		NoteDAO dao = ctx.getBean("noteDAO",NoteDAO.class);
		String id ="051538a6-0f8e-472c-8765-251a795bc88f";
		Note note = dao.findNoteById(id);
		System.out.println(note);
	}
	
	@Test
	public void test3(){
		NoteService dao = ctx.getBean("noteService",NoteService.class);
		String id ="051538a6-0f8e-472c-8765-251a795bc88f";
		Note note = dao.loadNote(id);
		System.out.println(note);
	}
	
	@Test
	public void test4(){
		NoteDAO dao = ctx.getBean("noteDAO",NoteDAO.class);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", "051538a6-0f8e-472c-8765-251a795bc88f");
		map.put("lastModifyTime", System.currentTimeMillis());
		map.put("body", "HelloWord!");
		dao.updateNote(map);
	}
	
	@Test
	public void test5(){
		String id ="051538a6-0f8e-472c-8765-251a795bc88f";
		NoteDAO dao = ctx.getBean("noteDAO",NoteDAO.class);
		int n = dao.countNotesById(id);
		System.out.println(n);
	}
	
	@Test
	public void test6(){
		String id ="051538a6-0f8e-472c-8765-251a795bc88f";
		NoteService serc = ctx.getBean("noteService",NoteService.class);
		boolean b = serc.saveNote(id, "天行键", "君子以自强不息");
		System.out.println(b);
		
		Note n = serc.loadNote(id);
		System.out.println(n);
	}
	
	@Test
	public void test7(){
		NoteDAO dao = ctx.getBean("noteDAO",NoteDAO.class);
		String id = "12121";
		String notebookId="4b86d1f9-6345-4532-bc50-ee86442f004b";
		String userId="39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
		String statusId="0";
		String typeId="0";
		String title="java";
		String body="hello";
		Long now = System.currentTimeMillis();
		Note note = new Note(id,notebookId, userId, statusId, typeId, title, body, now, now);
//		System.out.println(note);
		dao.addNote(note);
	}
	
	@Test
	public void test8(){
		NoteService serc = ctx.getBean("noteService",NoteService.class);
		String userId="39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
		String notebookId="6d763ac9-dca3-42d7-a2a7-a08053095c08";
		String title="汽车好吗?";
		Note note = serc.addNote(userId, notebookId, title);
		System.out.println(note);
	}
	
	
	
	@Test
	public void test9(){
		NotebookDAO notebookdao =ctx.getBean("notebookDAO",NotebookDAO.class);
		String notebookId="6d763ac9-dca3-42d7-a2a7-a08053095c08";
		Notebook note = notebookdao.findNotebookById(notebookId);
		
		System.out.println(note);
	}
	
	@Test
	public void test10(){
		NoteDAO dao = ctx.getBean("noteDAO",NoteDAO.class);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", "");
		map.put("notebookId", "");
		map.put("key", "%1%");
		map.put("start", 0);
		map.put("rows", 10);
		List<Map<String,Object>> list = dao.findNoteByParams(map);
		for(Map<String,Object> m : list){
			System.out.println(m);
		}
	}
	
	@Test
	public void test11(){
		NoteDAO dao = ctx.getBean("noteDAO",NoteDAO.class);
		Map<String,Object> params = new HashMap<String, Object>();
		String userId = "39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
		List<String> idList = new ArrayList<String>();
		idList.add("12121");
		idList.add("125");
		params.put("userId", userId);
		params.put("idList", idList);
		dao.deleteNotes(params);
		
	}
	
	@Test
	public void test12(){
		PostDAO dao = ctx.getBean("postDAO",PostDAO.class);
		Post p = dao.findPostById(1);
		System.out.println(p);
	}
	
	
	
	
	
	
	
	
	
	
	
}
