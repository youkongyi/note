package cn.tedu.note.entity;

import java.io.Serializable;

public class Note implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;              
	private String notebookId;          
	private String userId;              
	private String statusId;       
	private String typeId;         
	private String title;           
	private String body;            
	private Long createTime;     
	private Long lastModifyTime;
	
	public String getNotebookId() {
		return notebookId;
	}
	public void setNotebookId(String notebookId) {
		this.notebookId = notebookId;
	}
	public Long getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNootbookId() {
		return notebookId;
	}

	public Note(String id, String notebookId, String userId, String statusId, String typeId, String title, String body,
			Long createTime, Long lastModifyTime) {
		super();
		this.id = id;
		this.notebookId = notebookId;
		this.userId = userId;
		this.statusId = statusId;
		this.typeId = typeId;
		this.title = title;
		this.body = body;
		this.createTime = createTime;
		this.lastModifyTime = lastModifyTime;
	}
	public void setNootbookId(String nootbookId) {
		this.notebookId = nootbookId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Note() {
	}
	
	
}                   