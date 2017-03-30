package cn.tedu.note.service;

import java.util.List;
import java.util.Map;

import cn.tedu.note.entity.Notebook;

public interface NotebookService {
	List<Map<String,Object>> listNotebooks(String userId)throws UserNotFoundException;
	
	List<Map<String,Object>> listNotebooks(String userId,int pageNum,int pageSize)throws UserNotFoundException;
	
	Notebook addNotebook(String userId,String notebookName)throws UserNameException,NotebookNotFoundException;
	
	void deleteNoteBook(String id);
}
