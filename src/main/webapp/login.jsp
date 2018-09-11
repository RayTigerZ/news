<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户登陆</title>
    <link rel="stylesheet" href="/news/css/1.css" type="text/css">
    <link rel="stylesheet" href="/news/plugin/bootstrap-4.1.3-dist/css/bootstrap.css" type="text/css">
    <script src="/news/plugin/jquery-3.3.1.js"></script>
    <script src="/news/plugin/bootstrap-4.1.3-dist/js/bootstrap.min.js"></script>
  
  <script type="text/javascript">
 	 function loginSubmit()
  	{
  		var userName=document.getElementById("userName").value;
  		var password=document.getElementById("password").value;
  		if(userName==null||userName==""||password==null||password=="")
  		{
  			alert("用户名和密码均不能为空！");
  			return false;
  		}
  		else return true;
  
 	 }
  </script>  
	
  </head>
  
  <body>
   <center>
     	<form action="/news/servlet/UserServlet?type=login" method="post" onsubmit="return loginSubmit()">
     		<div class="center" style="width:400px;margin-top:40px">
		<table  height="121" border="0" align="center">
			<tbody>
				<tr height="30">
					<td></td><td>登录</td>
				</tr>			
				<tr height="30">
					<td align="right">用户名：</td>
					<td align="left"><input type="text" name="userName" id="userName" ><span id="namespan"></span></td>
				</tr>
				<tr height="30">
					<td align="right">密码：</td>
					<td align="left"><input type="password" name="password" id="password" ><span id="passwordspan"></span></td>
				</tr>
				<tr height="30">
					<td></td><td><input type="submit" value="    登  录    "/></td>
				</tr> 
			</tbody>
		</table>
	</div>
		</form><br>
			<div>还没有账户，请<a href="registe.jsp">
				<input type="button" id="registe" value="注册">
			</a></div>
     	
     </center>
     
  </body>
</html>
