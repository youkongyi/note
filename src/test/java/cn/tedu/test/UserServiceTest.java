package cn.tedu.test;


import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.note.dao.NotebookDAO;
import cn.tedu.note.entity.User;
import cn.tedu.note.service.UserService;

public class UserServiceTest {
	ClassPathXmlApplicationContext ctx;
	@Before
	public void init(){
		ctx = new ClassPathXmlApplicationContext("spring-web.xml","spring-service.xml","spring-mybatis.xml");
	}
	
	@Test
	public void test1(){
		UserService service = ctx.getBean("userService",UserService.class);
		String name = "Tom";
		String password = "123";
		User user = service.login(name, password);
		System.out.println(user);
		
	}
	
	@Test
	public void testMd5(){
		String str = "12345678";
		String md5 = DigestUtils.md5Hex(str);
		System.out.println(md5);
	}
	
	@Test
	public void testSaltPwd(){
		String pwd = "123";
		String salt = "好的";
		String s = DigestUtils.md5Hex(pwd+salt);
		System.out.println(s);
	}
	
	@Test
	public void testRegistUser(){
		UserService ser = ctx.getBean("userService",UserService.class);
		User user = ser.regist("Jerry", "Mouse", "123", "123");
		System.out.println(user);
	}
	
	@Test
	public void testFindNotebookByUserId(){
		String id = "03590914-a934-4da9-ba4d-b41799f917d1";
		NotebookDAO dao = ctx.getBean("notebookDAO",NotebookDAO.class);
		List<Map<String,Object>> list = 
		dao.findNotebooksByUserId(id);
		for(Map<String,Object> n :list){
			System.out.println(n);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
