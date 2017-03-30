package cn.tedu.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

import org.junit.Test;

/**
 * 动态读写属性
 */
public class Demo2 {
	public Demo2(){
		
	}
	private int num=5;
	
	@Test
	public void test1() throws Exception{
		Scanner scan = new Scanner(System.in);
		System.out.println("输入类名: ");
		String className = scan.nextLine();
		//加载类
		Class cls = Class.forName(className);
		//获取属性信息
		System.out.println("属性名称: ");
		String name = scan.nextLine();
		//查找属性: 在方法区中的类信息李查找属性信息
		Field field = cls.getDeclaredField(name);
		//读取属性:
		// Obj 参数是包含属性值的对象
		Object obj = cls.newInstance();
		field.setAccessible(true);
		field.set(obj, 1506);
		Object val = field.get(obj);
		System.out.println(val);
	}
	
	
	@Test
	public void test2() throws Exception{
		Scanner scan = new Scanner(System.in);
		System.out.println("输入类名: ");
		String className = scan.nextLine();
		//加载类
		Class cls = Class.forName(className);
		//查找方法
		Method[] methods = cls.getDeclaredMethods();
		Object obj = cls.newInstance();
		for(Method m : methods){
			//查找注解
			//查找 方法中是否包含Test注解
			//如包含注解就返回一个对象,
			//否则返回null
			System.out.println(m);
			Start ann = m.getAnnotation(Start.class);
			System.out.println(ann);
			if(ann!=null){
				m.invoke(obj);
			}
		}
	}
	
	
}
