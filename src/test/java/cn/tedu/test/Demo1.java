package cn.tedu.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.Test;

public class Demo1 {
	public Demo1(){
		
	} 
	
	private int num = 53;
	public void who(){
		System.out.println("Hi");
	}
	@SuppressWarnings("unused")
	private void asdas(int i){
		System.out.println("jao");
	}
	
	@Test
	public void test1() throws Exception{
		Scanner scan = new Scanner(System.in);
		System.out.println("输入类名");
		String className = scan.nextLine();
		//动态加载类:
			//在类名错误时候出现: Class没有找到
		Class cls = Class.forName(className);
		//如果没有无参构造其器,将抛出异常
		Object obj = cls.newInstance();
		System.out.println(cls);
		System.out.println(obj);
	}
	
	@Test
	public void test2() throws Exception{
		Scanner scan = new Scanner(System.in);
		System.out.println("输入类名: ");
		String className = scan.nextLine();
		Class cls = Class.forName(className);
		//找类中全部的方法信息
//		Method[] methods = cls.getDeclaredMethods();
		//找到类中所有的共有方法信息包含继承的方法
//		Method[] methods = cls.getMethods();
		//利用方法签名找到一个特定的方法信息
		String name = "asdas";
		Class[] types= {int.class};
		Method methods = cls.getDeclaredMethod(name, types);
//		for(Method m : methods){
			System.out.println(methods);
//		}
	}
	
	
	/*
	 * 动态找到一个类,并执行它的方法
	 */
	@Test
	public void test3() throws Exception{
		Scanner scan = new Scanner(System.in);
		System.out.println("输入类名: ");
		String className = scan.nextLine();
		//加载类
		Class cls = Class.forName(className);
		System.out.println("输入方法名: ");
		String name = scan.nextLine();
		//查找方法
		Class[] types = {};
		Method method =cls.getDeclaredMethod(name, types);
		//执行方法
//		String obj = "ABC";
		Object obj = cls.newInstance();
		Object val = method.invoke(obj);
		System.out.println(val);
	}
	
	/*
	 * 执行某个类中全部以test为开头
	 * 的方法都是非静态方法
	 * 
	 * 方法都是无参数无返回值的方法
	 * 都有无参构造器
	 */
	@Test
	public void test4() throws Exception{
		Scanner scan = new Scanner(System.in);
		System.out.println("输入类名: ");
		String className = scan.nextLine();
		//加载类
		Class cls = Class.forName(className);
		//动态查找方法
		Method[] methods = cls.getDeclaredMethods();
		//遍历每个方法,查找方法名是以test为开头的
		Object obj = cls.newInstance();
		for(Method m : methods){
			String name =  m.getName();
			//获取方法的参数类型列表
			Class[] types = m.getParameterTypes();
			System.out.println(name + " : " + Arrays.toString(types));
			//检查参数类型列表长度 
			if(types.length!=0){
				continue;
			}
			if(name.startsWith("test")){
				System.out.println(name+" . ");
				//找到test开头方法并执行
				m.invoke(obj);
			}
		}
	}
	
	@Test
	public void test5() throws Exception{
		Scanner scan = new Scanner(System.in);
		System.out.println("输入类名: ");
		String className = scan.nextLine();
		//加载类
		Class cls = Class.forName(className);
		//查看类的所有属性
		Field[] fields = cls.getDeclaredFields();
		for(Field field : fields){
			System.out.println(field);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
