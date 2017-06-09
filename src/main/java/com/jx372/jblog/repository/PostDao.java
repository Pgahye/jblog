package com.jx372.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jx372.jblog.vo.CategoryVo;
import com.jx372.jblog.vo.PostVo;

@Repository
public class PostDao {

	
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(PostVo vo){
		
		
		sqlSession.insert("post.insert", vo);
		
	}
	
public List<PostVo> getList(Long user_no) { // 수정폼

		

		return sqlSession.selectList("post.getlist", user_no);
	}

public PostVo get(Long user_no, Long no){
	
	Map<String, Object> map= new HashMap<String, Object>(); //vo가 없을경우 map을 사용
	map.put("user_no", user_no);
	map.put("no", no);
	
	return sqlSession.selectOne("post.get", map);
}

public PostVo get(Long user_no){
	
	
	return sqlSession.selectOne("post.getOne", user_no);
}
public List<PostVo> CagetList(Long category_no, Long user_no){
	
	Map<String, Object> map= new HashMap<String, Object>(); //vo가 없을경우 map을 사용
	map.put("user_no", user_no);
	map.put("category_no", category_no);
	
	return sqlSession.selectList("post.CagetList", map);
}

public PostVo CagetOne(Long category_no, Long user_no){
	
	Map<String, Object> map= new HashMap<String, Object>(); //vo가 없을경우 map을 사용
	map.put("user_no", user_no);
	map.put("category_no", category_no);
	
	
	
	return sqlSession.selectOne("post.CagetOne", map);
}
}
