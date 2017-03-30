package cn.tedu.test;


import java.util.Date;

import cn.tedu.demo.ApplicationContext;

public class Demo3 {
	public static void main(String[] args) throws Exception {
		
		ApplicationContext ctx = new ApplicationContext("spring.xml");
		Date d = (Date)ctx.getBean("date");
		System.out.println(d);
		TestCase2 t = (TestCase2) ctx.getBean("tc2");
		System.out.println(t);
	}
}
