<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册页面</title>
    <script type="text/javascript">
 	 function registeSubmit()
  	{
  		var userName=document.getElementById("userName").value;
  		var password=document.getElementById("password").value;
  		var password2=document.getElementById("password2").value;
  		if(userName==null||userName==""||password==null||password==""||password!=password2)
  		{
  			alert("请检查用户名和密码！");
  			return false;
  		}
  		else return true;
  
 	 }
  </script> 
	

  </head>
  
  <body>
   <center>
     	<form action="/news/servlet/UserServlet?type=registe" method="post" onsubmit="return registeSubmit()" >	
     <div class="center" style="width:600px;margin-top:40px">
	<table  border="0" align="center">
		<tbody>
			<tr height="30">
				<td></td><td>注册</td>
			</tr>
			<tr height="30">
				<td align="right">用户类型：</td>
				<td><select name="userType">
						<option value="user">user</option>
						<option value="newsAuthor">newsAuthor</option>
						<option value="manager">manager</option>
				</select></td>
			</tr>			
			<tr height="30">
				<td  align="right">用户名：</td>
				<td align="left"><input type="text" name="userName" id="userName" >
					<br><span id="namespan"  style="color: #E7060A;"></span></td>
			</tr>
			<tr height="30">
				<td align="right">密码：</td>
				<td align="left"><input type="password" name="password" id="password" >
					<br><span id="passwordspan"  style="color: #E7060A;"></span></td>
			</tr>
			<tr height="30">
				<td align="right">重新输入密码：</td>
				<td align="left"><input type="password" name="password2" id="password2" >
					<br><span id="passwordspan2"  style="color: #E7060A;"></span></td>
			</tr>
			<tr height="30">
				<td></td><td><input type="submit" value="      注     册     "/></td>
			</tr>
		</tbody>
	</table>
  </div>
  </form>
  </center>
  </body>
</html>
