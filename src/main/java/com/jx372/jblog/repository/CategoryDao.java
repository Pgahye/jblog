package com.jx372.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.jx372.jblog.vo.CategoryVo;
import com.jx372.jblog.vo.UserVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;

	public boolean insert(UserVo vo) {

		int count = sqlSession.insert("category.insert", vo); //디폴트값 

		return count == 1;
	}

	
	public int delete(Long no){
		
		int count = sqlSession.delete("category.delete",no);
		
		return count;
		
	}
	
	public void insert(CategoryVo vo) {
		
 
		 sqlSession.insert("category.insertUser", vo);

		
	}
	
	public List<CategoryVo> get(Long user_no) { // 수정폼

	
		
		List<CategoryVo> categoryvo = sqlSession.selectList("category.getByNo", user_no);
		
		

		return categoryvo;
	}

}
