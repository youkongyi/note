package cn.tedu.note.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.note.entity.Notebook;
import cn.tedu.note.service.NotebookService;
import cn.tedu.note.util.JsonResult;

@Controller
@RequestMapping("/notebook")
public class NotebookController extends AbstractController{
	
	@Resource
	private NotebookService notebookService;
	 
	@RequestMapping("/list.do")
	@ResponseBody
	public JsonResult<List<Map<String,Object>>> list(String userId){
//		System.out.println(userId);
		List<Map<String,Object>> list = notebookService.listNotebooks(userId);
//		System.out.println(list);
		System.gc();
		return new JsonResult<List<Map<String,Object>>>(list);
	}
	
	@RequestMapping("/add.do")
	@ResponseBody
	public JsonResult<Notebook> add(String userId,String notebookName){
//		System.out.println(userId);
		Notebook notebook = notebookService.addNotebook(userId, notebookName);
//		System.out.println(notebook);
		return new JsonResult<Notebook>(notebook);
	}
	
	@RequestMapping("/notebooks.do")
	@ResponseBody
	public JsonResult<List<Map<String,Object>>> notebooks(String userId,int page){
		int size = 6;
		List<Map<String,Object>> list = notebookService.listNotebooks(userId, page, size);
		return new JsonResult<List<Map<String,Object>>>(list);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
