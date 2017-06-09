package com.jx372.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.jx372.jblog.repository.PostDao;
import com.jx372.jblog.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostDao postDao;
	
	
	
	public void insert(PostVo vo){
		
		
		postDao.insert(vo);
	}
	
	public List<PostVo> getList(Long no){
		
		return postDao.getList(no);
		
	}
	
	public PostVo get(Long user_no, Long no){
		
		
		return postDao.get(user_no, no);
	}
	
public PostVo get(Long user_no){
		
		
		return postDao.get(user_no);
	}

public List<PostVo> CagetList(Long category_no, Long user_no){
	
	
	return postDao.CagetList(category_no, user_no);
}

public PostVo CagetOne(Long category_no, Long user_no){
	
	
	return postDao.CagetOne(category_no, user_no);
}
	

}
