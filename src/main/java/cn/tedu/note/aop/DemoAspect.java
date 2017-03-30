package cn.tedu.note.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

//@Aspect 声明 切面 组件
//@Aspect
//@Component
public class DemoAspect {
	
//	@Before("bean(userService)")
	public void hello(){
		System.out.println("Hello World!");
	}
	

//	@Before("bean(userService)")
	public void before(){
		System.out.println("before() ");
	}
//	@AfterReturning("bean(userService) ")
	public void afterReturning(){
		System.out.println("afterReturning() ");
	}
//	@AfterThrowing(pointcut="bean(userService)",throwing="ex")
	public void afterThrowing(Exception ex) throws Exception{
		System.out.println("afterThrowing() ");
		throw ex;
	}
//	@After("bean(userService)")
	public void after(){
		System.out.println("after() ");
	}
	
	//环绕
//	@Around("bean(*Service)")
//	@Around("within(cn.tedu.note.service.*)")
//	@Around("execution(* cn.tedu.note.service.*Service.*(..))")
	public Object around(ProceedingJoinPoint pjp)throws Throwable{
		System.out.println("开始");
		Long start = System.currentTimeMillis();
		//调用业务方法 
		Signature name = pjp.getSignature();//方法签名
		System.out.println(name);
		Object obj = pjp.proceed();
		Long end = System.currentTimeMillis();
		System.out.println(end-start+"毫秒");
		System.out.println("结束");
		//业务方法的返回值
		return obj;
	}  
	
}
