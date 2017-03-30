package cn.tedu.note.dao;

import cn.tedu.note.entity.Post;

public interface PostDAO {
	Post findPostById(Integer id);
	
}
