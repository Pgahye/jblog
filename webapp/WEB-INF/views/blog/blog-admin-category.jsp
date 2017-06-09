<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/blogheader.jsp"></c:import>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.servletContext.contextPath }/blog/basic/${UserVo.id }">기본설정</li>
					<li><a href="${pageContext.servletContext.contextPath }/blog/category/${UserVo.id }">카테고리</a></li>
					<li><a href="${pageContext.servletContext.contextPath }/blog/write/${UserVo.id }">글작성</a></li>
				</ul>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		<c:set var="count" value="${fn:length(Categorylist) }"/>
		      		<c:forEach items="${Categorylist }" var="vo" varStatus="status">		
		      			<tr>
						<td>[${count - status.index }]</td>
						<td>${vo.name }</td>
						<td>10</td>
						<td>${vo.description }</td>
						<td>
						<a href="${pageContext.servletContext.contextPath }/blog/category/delete/${UserVo.id }/${vo.no }">
						<img src="${pageContext.request.contextPath}/assets/images/delete.jpg" ></a>
						
						</td>
						</tr>  
					</c:forEach> 			  
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
      			<form action="${pageContext.servletContext.contextPath }/blog/category/${UserVo.id }" method="post">
		      	<table id="admin-cat-add" >
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="description"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input type="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table> 
		      	</form>
			</div>
		</div>
	<c:import url="/WEB-INF/views/include/blogfoot.jsp"></c:import>
	</div>
</body>
</html>