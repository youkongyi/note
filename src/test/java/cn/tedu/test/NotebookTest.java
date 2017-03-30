package cn.tedu.test;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.note.dao.NotebookDAO;
import cn.tedu.note.entity.Notebook;
import cn.tedu.note.service.NoteService;
import cn.tedu.note.service.NotebookService;

public class NotebookTest {
	ClassPathXmlApplicationContext ctx;
	@Before
	public void init(){
		ctx = new ClassPathXmlApplicationContext("spring-web.xml","spring-service.xml","spring-mybatis.xml","spring-aop.xml");
	}
	
	@Test
	public void test1(){
		String userId = "03590914-a934-4da9-ba4d-b41799f917d1";
		NotebookService ns = ctx.getBean("notebookService",NotebookService.class);
		List<Map<String,Object>> list = ns.listNotebooks(userId);
		for(Map<String,Object> n : list){
			System.out.println(n);
		}
	}
	
	@Test
	public void test2(){
		String id = "d0e7ce0d-4893-4705-a51a-9a73d259bc70";
		NoteService dao = ctx.getBean("noteService",NoteService.class);
		List<Map<String,Object>> note = dao.listNotes(id);
		for(Map<String,Object> m :note){
			System.out.println(m);
		}
	}
	  
	  
	@Test
	public void test3(){
		NotebookDAO notebook = ctx.getBean("notebookDAO",NotebookDAO.class);
		Notebook note = new Notebook();
		note.setId(UUID.randomUUID().toString());
		note.setUserId("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
		note.setTypeId("5");
		note.setName("是的");
		String desc = "好的";
		note.setDesc(desc);
		long t = System.currentTimeMillis();
		Timestamp s = new Timestamp(t);
		note.setCreatetime(s);
		int a = notebook.addNotebook(note);
		System.out.println(a);
	}

	@Test
	public void test4(){
		String id = "4acb305b-5def-47ae-8832-c2a188662085";
		NotebookService dao = ctx.getBean("notebookService",NotebookService.class);
		dao.deleteNoteBook(id);
	}
	
	@Test
	public void test5(){
		NotebookDAO notebook = ctx.getBean("notebookDAO",NotebookDAO.class);
		String userId="03590914-a934-4da9-ba4d-b41799f917d1";
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("start", 6);
		params.put("rows", 6);
		List<Map<String,Object>>list = notebook.findNotebooksByPage(params);
		for(Map<String,Object> m :list){
			System.out.println(m);
		}
		
	}
	
}
