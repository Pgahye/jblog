<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>

<script>
$(function(){
	
	$("#btn-checkemail").click(function(){
			var id = $("#id").val();
		
			if(id == ""){
				return;
			}
			//ajax 통신 시작( 객체로 넣음 )
			$.ajax( {
			    url : "/jblog/user/api/checkemail?id="+id,
			    type: "get",
			    dataType: "json",
			    data: "",
			//  contentType: "application/json",
			    success: function( response ){
			    	
			    	if(response.data== true){
			    		alert("이미 존재하는 이메일입니다. ");
			    	}else{
			    		
			    		alert("사용가능한 이메일입니다. ");
			    	}
			       console.log( response.data );
			    },
			    error: function( jqXHR, status, error ){
			       console.error( status + " : " + error );
			    }

			   });

			
	});
	
});




</script>
</head>
<body>
	<div class="center-content">
		
		
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		
		<form:form 	modelAttribute="userVo"  class="join-form" id="join-form" method="post" action="${pageContext.servletContext.contextPath }/user/join">
			<label class="block-label" for="name">이름</label>
			
			<form:input path="name"/>
					<p style="margin:0; padding:0; color:red; text-align:left">
					 <form:errors path="name" />
					 </p>
			
			
			<label class="block-label" for="blog-id">아이디</label>
			
			<form:input path="id"/>
					<p style="margin:0; padding:0; color:red; text-align:left">
					 <form:errors path="id" />
					 </p>
			
		
			<input id="btn-checkemail" type="button" value="id 중복체크">
			<img id="img-checkemail" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">

			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password" />

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
</body>
</html>
