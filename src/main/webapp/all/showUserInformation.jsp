<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>showUserInformation</title>

  </head>
  
  <body>
    <table width="500" border="1" align="center" cellspacing="0" cellpadding="0">
		<tbody>
			<tr><td colspan="2">个人信息：</td></tr>
			<tr><td>用户类型：</td>
				<td>${ sessionScope.user.userType}</td>
			</tr>			
			<tr><td>用户名：</td>
				<td>${ sessionScope.user.userName}</td>
			</tr>			
			<tr><td>头像：</td>	
				<td><img src="${ sessionScope.user.headIconUrl}" height="100"/></td></tr>
			<tr><td>注册日期：</td>
				<td>${ sessionScope.user.registerDate}</td>
			</tr> 
			<c:if test="${sessionScope.user.userType=='user'}" >
				<tr><td>性别：</td>	
					<td>${ requestScope.userInformation.sex}</td></tr>
				<tr><td>爱好：</td>
					<td>${ requestScope.userInformation.hobby}</td>
				</tr> 
			</c:if>					
		</tbody>
	</table> 		
  </body>
</html>
