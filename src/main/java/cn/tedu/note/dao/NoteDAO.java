package cn.tedu.note.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.note.entity.Note;

public interface NoteDAO {
	/**
	 * 根据笔记本ID查询全部的笔记信息
	 * @param notebookId
	 * @return
	 */
	
	List<Map<String,Object>> findNoteByNotesbookId(String notebookId);
	
	Note findNoteById(String id);
	
	/**
	 * 更新note信息
	 * 参数是Map封装的note信息数据
	 * 其中必须有:
	 * 			id
	 * 			lastModifyTime
	 * 可选参数:
	 * 			notebookId
	 * 			userId
	 * 			statusId
	 * 			typeId
	 * 			title
	 * 			body
	 * 使用:
	 * 		Map<String,Object> note = new HashMap<String,Object>();
	 * 		note.put('id','笔记ID');
	 * 		note.put('lastModifyTime','system.currentTimeMilies');
	 * 		note.put('title','String name');
	 * 
	 * 		dao.updateNote(note);
	 * 
	 * @param note
	 * @return 
	 */
	int updateNote(Map<String,Object> note);

	int countNotesById(String id);
	
	int addNote(Note note);

	void deleteNote(String noteId);
	
	List<Map<String,Object>> findNoteByParams(Map<String,Object> params);

	void deleteNotes(Map<String,Object> params);
}
