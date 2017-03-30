package cn.tedu.note.util;

import java.io.Serializable;

public class JsonResult<T> implements Serializable{
	//版本序列号
	private static final long serialVersionUID = 1L;
	//成功
	public static final int SUCCESS=0;
	//失败
	public static final int ERROR=1;
	//状态
	private int state;
	//数据
	private T data;
	//信息
	private String message;
	
	public JsonResult() {
	}
	
	public JsonResult(T t){
		state = SUCCESS;
		data = t;
		message = "";
	}
	
	public JsonResult(int state , Throwable e){
		this.state = state;
		this.message = e.getMessage();
		this.data = null;
	}
	public JsonResult(Throwable e){
		state = ERROR;
		data = null;
		message = e.getMessage();
	}
    
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
