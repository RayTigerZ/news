<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>changePassword</title>
    
    <script type="text/javascript">
    	function changePasswordSubmit(){
    		
    	
    	}
    </script>
    
  </head>
  
  <body>
    <form action="/news/servlet/UserServlet?type=changePassword" method="post" onsubmit="return changePasswordSubmit()">
		<table width="400" border="0" align="center">
			<tbody>
				<tr>
					<td></td>
					<td>修改密码</td>
				</tr>		
				<tr>
					<td align="right">旧密码：</td>
					<td align="left"><input type="password" name="oldPassword" id="oldPassword" ></td>
				</tr>
				<tr>
					<td align="right">新密码：</td>
					<td align="left"><input type="password" name="newPassword" id="newPassword" ></td>
				</tr>
				<tr>
					<td align="right">再次输入新密码：</td>
					<td align="left"><input type="password" name="newPassword1" id="newPassword1" ></td>
				</tr>
				<tr>
					<td></td><td><input type="submit" value="提交"/></td>
				</tr>								
			</tbody>
		</table>
	</form>    
  </body>
</html>
