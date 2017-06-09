package com.jx372.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jx372.jblog.repository.BlogDao;
import com.jx372.jblog.repository.CategoryDao;
import com.jx372.jblog.repository.UserDao;
import com.jx372.jblog.vo.UserVo;



@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	public boolean existEmail(String id){
		
		UserVo userVo = userDao.get(id);
		
		
		return userVo != null;
		
	}
	
	public UserVo get(String id){
		
		
		return userDao.get(id);
	}
	
	
	public void join(UserVo userVo){
		
		// 1.db에 사용정보 저장
		
		userDao.insert(userVo);
		UserVo usevo = userDao.get(userVo.getId());
		
		
		blogDao.insertblog(usevo);
		categoryDao.insert(usevo);
		
	
		
		// 2. 인증메일보내기 
		
		
	}
	
	public UserVo getUser(String id, String password) throws Exception{
		
		return userDao.get(id, password);
		
	}
	public UserVo getUser(Long no){
		
		
		return userDao.get(no);
	}
	
	public boolean outUser(UserVo vo){
		
		
		return userDao.update(vo);
	}
	
	public boolean smallupdate(UserVo vo){
		
		
		return userDao.smallupdate(vo);
	}

}
