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
			<div id="content">
				<div class="blog-content">
				
				<h4>${MainPost.title }</h4>
				<p>
				${MainPost.content }
				<p>
				
				
				<c:set var="count" value="${fn:length(Postlist) }"/>
				<c:forEach items="${Postlist }" var="vo" varStatus="status">
				<ul class="blog-list">
					<li><a href="${pageContext.servletContext.contextPath }/blog/main/${UserVo.id}/${vo.no}">${vo.title }</a> <span>${vo.reg_date }</span></li>
				</ul>
				
				</c:forEach> 
				</div>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${BlogVo.logo}">
			</div>
		</div>

		<c:import url="/WEB-INF/views/include/blognavigation.jsp"></c:import>
		
		<c:import url="/WEB-INF/views/include/blogfoot.jsp"></c:import>
		
	</div>
</body>
</html>