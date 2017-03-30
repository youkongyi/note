package cn.tedu.note.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class DemoController {
	
	@RequestMapping("/test.do")
	@ResponseBody
	public Object test() throws Exception{
		Thread t = Thread.currentThread();
		//线程名
		String name = t.getName();
		Thread.sleep(10000);
		//线程号
		long id = t.getId();
		System.out.println("线程 : " +t);
		return new String[]{name+":"+id," OK!"};
	}
	
	
}
