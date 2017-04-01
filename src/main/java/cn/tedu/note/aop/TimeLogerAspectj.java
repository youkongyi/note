package cn.tedu.note.aop;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *  记录每个业务方法的执行时间
 *  保存到文件中
 */
@Component
@Aspect
public class TimeLogerAspectj {
	private BlockingQueue<String> queue = new LinkedBlockingQueue<String>(500);
	
	private File file ;
	
	private Thread writer;

	@SuppressWarnings("unused")
	private String filename;
	
	@Value("#{config.filename}")
	public void setFilename(String filename) {
		this.filename = filename;
        String classPath = TimeLogerAspectj.class.getClassLoader().getResource("/").getPath();
        String rootPath = classPath.substring(1,classPath.indexOf("/WEB-INF/classes")).replace("/", "\\");
        String str = rootPath.substring(2, rootPath.indexOf("\\note"));
        rootPath = rootPath.replace(str, "");
        file = new File(rootPath+filename);
	}
	
	public TimeLogerAspectj() {
		writer = new Thread(){
			@Override
			public void run(){
				while(true){
					try{
					if(queue.isEmpty()){
						Thread.sleep(500);
						continue;
					}
					//发现队列中有数据
					PrintWriter out = new PrintWriter(new FileOutputStream(file,true));
					while(!queue.isEmpty()){
						String str = queue.poll();
						out.println(str);
					}
					out.close();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		};
		writer.start();
	}



	@Around("execution(* cn.tedu.note.service.*Service.*(..))")
	public Object proc(ProceedingJoinPoint pjp)throws Throwable{
		
		long t1 =System.nanoTime();
		
		Object obj = pjp.proceed();
		
		long t2 = System.nanoTime();
		
		Signature s = pjp.getSignature();
		
		String str = System.currentTimeMillis()+":"+ s + ":" + (t2-t1);
		queue.offer(str);
		return obj;
	}

}
