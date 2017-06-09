package com.jx372.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jx372.jblog.repository.CategoryDao;
import com.jx372.jblog.vo.CategoryVo;


@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	public List<CategoryVo> get (Long user_no){
		
	
		
		
		
		return categoryDao.get(user_no);
		
	}
	
	public void insert(CategoryVo vo){
		
		categoryDao.insert(vo);
		
	}
	
	public void delete(Long no){
		
		
		categoryDao.delete(no);
	}
	

}
