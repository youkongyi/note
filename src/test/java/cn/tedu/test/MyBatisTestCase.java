package cn.tedu.test;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.note.dao.UserDAO;
import cn.tedu.note.entity.User;

public class MyBatisTestCase {
	ClassPathXmlApplicationContext ctx;
	@Before
	public void init(){
		ctx = new ClassPathXmlApplicationContext("spring-web.xml","spring-mybatis.xml");
	}
	
	@Test
	public void test1(){
		DataSource ds = ctx.getBean("dataSource",DataSource.class);
		System.out.println(ds);
	}
	
	@Test
	public void test2(){
		SqlSessionFactory ssf = ctx.getBean("sqlSessionFactory",SqlSessionFactory.class);
		System.out.println(ssf);
	}
	
	@Test
	public void test3(){
		MapperScannerConfigurer msc = ctx.getBean("mapperScanner",MapperScannerConfigurer.class);
		System.out.println(msc);
	}
	
	@Test
	public void test4(){
		UserDAO dao = ctx.getBean("userDAO",UserDAO.class);
		dao.saveUser(new User("123","Tom","123","","Cat"));
		//select * from cn_user 
		//where cn_user_id= '123'
	}
	
	@Test
	public void test5(){
		UserDAO dao = ctx.getBean("userDAO",UserDAO.class);
		User user =dao.findUserById("123");
		System.out.println(user);
	}
	
	@Test
	public void test6(){
		UserDAO dao = ctx.getBean("userDAO",UserDAO.class);
		String name = "Tom";
		User user = dao.findUserByName(name);
		System.out.println(user);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
