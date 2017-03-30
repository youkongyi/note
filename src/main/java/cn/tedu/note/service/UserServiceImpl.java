package cn.tedu.note.service;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.note.dao.UserDAO;
import cn.tedu.note.entity.User;
import cn.tedu.note.util.Utils;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Transactional
	public User login(String name, String password) throws UserNameException, PasswordException {
		//检验输入参数的合理性
			String reg = "^\\w{3,10}$";
		if(name==null||name.trim().isEmpty()){
			throw new UserNameException("帐号不能为空");
		}
	    
		if(! name.matches(reg)){
			throw new UserNameException("帐号不合格");
		}
		
		if(password==null||password.trim().isEmpty()){
			throw new PasswordException("密码不能为空");
		}
		if(! password.matches(reg)){
			throw new PasswordException("密码不合格");
		}
		
		//查询用户数据 
		User user = userDAO.findUserByName(name);
		if(user == null){
			throw new UserNameException("用户名错误");
		}
		
		//对照密文
		String md5 = Utils.crypt(password);
		if(user.getPassword().equals(md5)){
			//登录成功,返回用户信息
			String token = UUID.randomUUID().toString();
			user.setToken(token);
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("id", user.getId());
			data.put("token", token);
			userDAO.updataUser(data);
			return user;
		}
		
		
		throw new PasswordException("密码错误");
		
	}

	@Transactional
	public User regist(String name, String nick, String password, String confirm)
			throws UserNameException, PasswordException {
		
		if(name==null||name.trim().isEmpty()){
			throw new UserNameException("用户名不能为空");
		}
		String reg = "^\\w{3,10}$";
		
		if(! name.matches(reg)){
			throw new UserNameException("用户名不合格");
		}
		
		User newUser = userDAO.findUserByName(name);
		if(newUser!=null){
			throw new UserNameException("用户名已存在");
		}
			
		if(nick==null||nick.trim().isEmpty()){
			nick = name;
		}
		
		if(password==null||password.trim().isEmpty()){
			throw new PasswordException("密码不能为空");
		}
		
		password = password.trim();
	
		if(! password.equals(confirm)){
			throw new PasswordException("2次输入密码不一样");
		}
		String pwd = Utils.crypt(password);
		name = name.trim();
		String token = "";
		//UUID 用于生产永远不重复的ID
		String id = UUID.randomUUID().toString();
		User user = new User(id,name,pwd,token,nick);
		userDAO.saveUser(user);
		return user;
	}
	
	@Transactional
	public boolean checkToken(String userId, String token) {
		if(userId==null||userId.trim().isEmpty()){
			return false;
		}
		if(token==null||token.trim().isEmpty()){
			return false;
		}
		User user = userDAO.findUserById(userId);
		if(user==null){
			return false;
		}
		return token.equals(user.getToken());
	}
}
