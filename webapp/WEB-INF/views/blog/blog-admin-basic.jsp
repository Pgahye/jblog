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
				<form action="${pageContext.servletContext.contextPath }/blog/upload/${UserVo.id }" method="post" enctype="multipart/form-data">
	 		      	<table class="admin-config">
			      		<tr>
			      			<td class="t">블로그 제목</td>
			      			<td><input type="text" size="40" name="title" value="${BlogVo.title}"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">로고이미지</td>
			      			<td><img src="${pageContext.request.contextPath}${BlogVo.logo}"></td>      			
			      		</tr>      		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td><input type="file" name="file1"></td>      			
			      		</tr>           		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td class="s"><input type="submit" value="기본설정 변경"></td>      			
			      		</tr>           		
			      	</table>
				</form>
			</div>
		</div>
		
		
		<c:import url="/WEB-INF/views/include/blogfoot.jsp"></c:import>
	</div>
</body>
</html>