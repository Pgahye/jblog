<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link href="${pageContext.servletContext.contextPath }/assets/css/jblog.css" rel="stylesheet" type="text/css">
</head>
    	
    	

		<div id="header">
			<h1>${BlogVo.title }</h1>
			<ul>
			<c:choose>
				<c:when test="${empty authUser }">
					<li><a href="${pageContext.servletContext.contextPath }/user/login">로그인</a><li>		
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.servletContext.contextPath }/user/logout">로그아웃</a><li>
					<li><a href="${pageContext.servletContext.contextPath }/blog/basic/${UserVo.id }">블로그 관리</a></li>
					<li><a href="${pageContext.servletContext.contextPath }/blog/main/${UserVo.id}">블로그 메인으로 가기</a></li>
				</c:otherwise>		
			</c:choose>
			</ul>

		</div>