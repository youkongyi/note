package cn.tedu.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.note.service.NoteService;

public class TxTestCase {
	ClassPathXmlApplicationContext ctx;
	@Before
	public void init(){
		ctx = new ClassPathXmlApplicationContext("spring-mybatis.xml","spring-aop.xml","spring-service.xml","spring-web.xml");
	}
	
	@Test
	public void testDeleteAll(){
		String id1="019cd9e1-b629-4d8d-afd7-2aa9e2d6afe0";
		String id2="01da5d69-89d5-4140-9585-b559a97f9cb0";
		String id3="054449b4-93d4-4f97-91cb-e0043fc4497f";
		String id4="07305c91-d9fa-420d-af09-c3ff209608ff";
		NoteService serc = ctx.getBean("noteService",NoteService.class);
		int n = serc.deleteAll(id1,id2,id3,id4);
		System.out.println(n);
	}
}
