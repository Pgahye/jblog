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
    	
    	
<div class="center-content">

			
			<h1 class="logo">JBlog</h1>
			
			<ul class="menu">
			<li><a href="${pageContext.servletContext.contextPath }/main">메인으로</a></li>
			<c:choose>
				<c:when test="${empty authUser }">
					<li><a href="${pageContext.servletContext.contextPath }/user/login">로그인</a><li>
					<li><a href="${pageContext.servletContext.contextPath }/user/join">회원가입</a><li>			
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.servletContext.contextPath }/user/modify">회원정보수정</a><li>
					<li><a href="${pageContext.servletContext.contextPath }/user/logout">로그아웃</a><li>
					<li><a href="${pageContext.servletContext.contextPath }/blog/main/${authUser.id}">내블로그</a></li>
					<a>
					<li>${authUser.name }님 안녕하세요 ^^;</li>		
					</a>	
				</c:otherwise>		
			</c:choose>
	
			</ul>

</div>