<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <link href="/news/css/1.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
	  <div class="center" style="width:800px;margin-top:30px;">
	  	<form action="/news/servlet/CommentServlet?type=addCommnet" method="post">
	  		<div class="center" style="width:500px">
				<textarea name="content" cols="72" rows="8" id="textarea"  required></textarea>
			</div>
			<p>
			<div class="center" style="width:50px;height:80px;">
				<br>
				<input type="submit" name="submit" id="submit" value="  提 交 评 论  ">
			</div>		
			<input type="hidden" name="newsId" id="newsId" value="${param.newsId}">
		 	<input type="hidden" name="page" id="page" value="${param.page}">
			<input type="hidden" name="pageSize" id="pageSize" value="${param.pageSize}">
		</form> 
	 </div>      
   
  </body>
</html>
