<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<script src="/news/js/jquery-3.3.1.js"></script>
    <base href="<%=basePath%>">
    
    <title>changeUserInformation</title>
   
  </head>
  
  <body>
    <form action="/news/servlet/UserServlet?type=changeUserInformation2" method="post"  enctype="multipart/form-data">
	<table width="500" border="1" align="center" cellspacing="0" cellpadding="0">
		<tbody>
			<tr><td colspan="2">修改个人信息：</td></tr>		
			<tr><td>头像：</td>	
				<td><img src="${ sessionScope.user.headIconUrl}" height="100"/><input id="myFile" name="myFile" type="file" onchange="preview()"><br>
					<br>预览：<img id="myImage" height="100"/></td></tr>
			<c:if test="${sessionScope.user.userType=='user'}" >
				<tr><td >性别：</td>
					<td>
					<input id="sex" name='sex' type="radio" value="男" >男</input>
					<input id="sex" name='sex' type="radio" value="女" >女</input>
					</td>
					</tr>
				<tr><td>爱好：</td>
					<td><input type="text" name="hobby" value="${requestScope.userInformation.hobby}"/></td>
				</tr> 
			</c:if>	
			<tr><td colspan="2"><input type="submit" value="提交"/></td></tr>					
		</tbody>
	</table> 
  </form>
  <script type="text/javascript">
  	window.onload=function(){
							$("input[name='sex'][value='女']").attr("checked","checked")
						}
  	
	        
		function preview() {
		 	var preview = document.getElementById("myImage");
		 	var file  = document.getElementById("myFile").files[0];
		 	var reader = new FileReader();
		 	reader.onloadend = function () {
		  		preview.src = reader.result;
		 	}
		 	
			if (file) 
			  	reader.readAsDataURL(file);
			else 
			  	preview.src = "";			
		}
  </script>	
			
  </body>
</html>
