package com.jx372.jblog.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jx372.jblog.vo.BlogVo;
import com.jx372.jblog.vo.UserVo;


@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;

	public boolean insertblog(UserVo vo) {

		int count = sqlSession.insert("blog.insertblog", vo);

		return count == 1;
	}

	public BlogVo get(Long user_no) { // 수정폼

		BlogVo blogvo = sqlSession.selectOne("blog.getByNo", user_no);

		return blogvo;
	}

	public boolean update(BlogVo vo) {

		int count = sqlSession.update("blog.update", vo);

		return count == 1;

	}

}
