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
<link href="${pageContext.servletContext.contextPath }/assets/css/index-ajax.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/ejs/ejs.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">

var listItemTemplate = new EJS({url:"${pageContext.request.contextPath }/assets/js/ejs-template/category-list-item.ejs"}); 
var listTemplate = new EJS({url:"${pageContext.request.contextPath }/assets/js/ejs-template/category-list.ejs"});


var render = function(vo, mode){
	// 상용 app에서는 template 라이브러리를 사용해서 함  ex) ejs, leaf
	
	
	//var html = 
		
	//"<tr> <td>" + vo.no +"</td>"+
		//"<td>" +  vo.name  + "</td>"+
		//"<td>" + 10 + "</td>" + 
		//"<td>" +  vo.description  + "</td><td>" + 
		//"<a href='' data-no='" + vo.no + "'>삭제</a>" + 
		//"<img src='${pageContext.request.contextPath}/assets/images/delete.jpg' ></a></td></tr> ";
		
	var html = listItemTemplate.render(vo);
		
		
	if(mode === true){
		
		$(".admin-cat").prepend(html);
		
	}else{
		
		$(".admin-cat").append(html);		
		
	}	
		
	
		
		
		
		
		
};
var fetchList = function(){
	
	var userid= $("#userid").val();
	

	
	$.ajax( {
		//url : "http://www.jx372.com:8088${pageContext.servletContext.contextPath }/blog/api/category/"+userid,
		/url : "${pageContext.servletContext.contextPath }/blog/api/category/"+userid,
		type: "get",
		dataType: "json", // 받아야되는 데이터 타입 
		data: "",
		//contentType: 'application/json', //json 타입으로 데이터를 보낼때 사용함 
		success: function(response){

				if(response.result ==="fail"){
					
					console.error(response.message);
					return;
				}
				
		
				//$.each(response.data, function(index, vo){
				//	render(vo, false);
				//});
				
				response.data.contextpath = "${pageContext.request.contextPath}";
				
				var htmls = listTemplate.render(response); // response 객체를 넘김  listTemplate에서 data로 읽음 
				
				$(".admin-cat").append(htmls);
				//$("#list-guestbook").hello();
			
		},
		error: function( jqXHR, status, e ){
			console.error( status + " : " + e );
		}
		} );

	
	
}

var messageBox = function(title, message, callback){
	
	$( "#dialog" ).attr("title", title);
	$( "#dialog p" ).text(message);
	
	
	 $( "#dialog" ).dialog({
	      modal: true,
	  
	      
	      buttons: {
	        Ok: function() {
	          $( this ).dialog( "close" );
	        }
	 		
	 		//	,
	       // Cancel : function(){ 버튼  추가할수도 있음 
	       // 	console.log();
	       // }
	      },
	      close: callback || function(){ // undefine일 경우 에러발생할수 있으므로 빈 function을 넣어준다. 
	    	  
	    	  
	      }
	    });
	
	
}


$(function(){
	
	fetchList();
	

	
	var userid= $("#userid").val();
	
	
	
	$("#add-category").submit(function(event){
		
		event.preventDefault();
		
		var vo = {};
		
		vo.name = $("#name").val();
		
		console.log(vo.name);
		
		if(vo.name === ""){
			
			messageBox("블로그 카테고리등록", "카테고리 이름은 필수 입력 항목 입니다.  ", function(){$("#name").focus();});
			//$("#dialog").dialog();
			//alert("이름은 필수 입력 항목 입니다. ");
			return false; 
			
		}
		
		vo.description = $("#description").val();
		
		console.log(vo.description);
		console.log(vo.name + ":" + vo.description + ":" + userid);
		
		if(vo.description === ""){
			
			messageBox("블로그 카테고리등록", "카테고리 설명은 필수 입력 항목 입니다.  ", function(){$("#description").focus();});
			//$("#dialog").dialog();
			//alert("이름은 필수 입력 항목 입니다. ");
			return false; 
			
		}
		
	

		
		// ajax 통신 
		console.log(JSON.stringify(vo));
		
		$.ajax( {
			url : "${pageContext.request.contextPath }/blog/api/category/"+userid,
			type: "post",
			dataType: "json", // 받아야되는 데이터 타입 
			data: JSON.stringify(vo),
	
	//		data: "name= "+vo.name + "&" + 
		//	"description=" + vo.description,
			contentType: 'application/json; charset=utf-8', //json 타입으로 데이터를 보낼때 사용함 
			
			
			
			success: function(response){

				
					if(response.result === "fail"){
						
						console.error(response.message);
						return;
					}
					
					
					response.data.contextpath = "${pageContext.request.contextPath}";
					render(response.data, false);
					
					// reset form 
					
					$("#add-category").get(0).reset(); //html 엘리먼트 reset을 사용함 
				
			},
			error: function( jqXHR, status, e ){
				console.error( status + " : " + e );
			}
			} );
		
		
		
	});
	
	
	$(document).on("click", ".admin-cat a", function(){
		
		event.preventDefault();
		
	 	window.no = $(this).data("no");
		
		
		var userid= $("#userid").val();
	
		
		$.ajax( {
			url : "${pageContext.servletContext.contextPath }/blog/api/category/delete/"+userid+"/"+no,
			type: "get",
			dataType: "json", // 받아야되는 데이터 타입 
			data: "",
			//contentType: 'application/json', //json 타입으로 데이터를 보낼때 사용함 
			
			success: function(response){

				
					if(response.result === "fail"){
						
						console.error(response.message);
						return;
					}
					
					if(response.data === -1){
						
						
						return ; 
						
						
					}
				
					// 삭제 성공
					$(".admin-cat a[data-no='"+ response.data +"']").parents( "tr" ).remove();
				
		
							
					},
					
			error: function( jqXHR, status, e ){
					console.error( status + " : " + e );
			}
			} );			  		
				
	});




});
</script>
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
		      	<table class="admin-cat" >
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		<c:set var="count" value="${fn:length(Categorylist) }"/>
		      		<c:forEach items="${Categorylist }" var="vo" varStatus="status">		
		      		<!--  
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
		      			-->	
					</c:forEach> 			  
				</table>
      			<h4 class="n-c">새로운 카테고리 추가</h4>
      			<form id="add-category"  method="post">
		      	<table id="admin-cat-add" >
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input id="name" type="text" name="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input id="description" type="text" name="description"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input type="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table> 
		      	  <input type="hidden" name="userid" id ="userid" value="${UserVo.id }" />
		      	  
		      	</form>
			</div>
		</div>
		
		<div id="dialog" title="" style="display:none">
  			<p> </p>
		</div>	
		
	<c:import url="/WEB-INF/views/include/blogfoot.jsp"></c:import>
	</div>
</body>
</html>