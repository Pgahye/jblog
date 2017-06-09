<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="navigation">

			<h2>카테고리</h2>
			
			
					<c:forEach items="${Categorylist }" var="vo" varStatus="status">
					<ul>
					<li><a href="${pageContext.servletContext.contextPath }/blog/category/view/${UserVo.id}/${vo.no }">${vo.name }</a></li>
					</ul>	
				</c:forEach>  
				
			
</div>		