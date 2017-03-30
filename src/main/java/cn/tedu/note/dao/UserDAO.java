package cn.tedu.note.dao;

import java.util.Map;

import cn.tedu.note.entity.User;

public interface UserDAO {
	void saveUser(User user);
	
	User findUserById(String id);
	
	User findUserByName(String name);

	int updataUser(Map<String,Object> user);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
