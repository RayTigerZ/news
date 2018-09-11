<%@page import="bean.User"%>
<%@ page contentType="text/html" language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String type=((User)session.getAttribute("user")).getUserType();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="content-type"content="text/html; charset=utf-8" />
    <base href="<%=basePath%>">
    
    <title>页面1</title>
	
  </head>
  
  <body>
      all<br>
      <a href="/news/user/register.jsp" target='_blank'>注册</a><br><br>
      <a href="/news/user/login.jsp" target='_blank'>登录</a><br><br>
      manager<br>
      <a href="/news/servlet/UserServlet?type=show&page=1&pageSize=2" target='_blank'>浏览用户</a><br><br>
	  <a href="/news/servlet/UserServlet?type=check&page=1&pageSize=2" target='_blank'>审查用户</a><br><br>
	  <a href="/news/manager/searchUser.jsp" target='_blank'>查询用户</a><br><br>  
	  <a href="/news/servlet/UserServlet?type=registeCheck&page=1&pageSize=2" target='_blank'>注册审查</a><br><br>  
	  <a href="/news/servlet/UserServlet?type=delete&page=1&pageSize=2" target='_blank'>删除用户</a><br><br>
	     用户<br>
  	  <a href="/news/all/changePassword.jsp" target='_blank'>修改密码</a><br><br>
      <a href="/news/servlet/UserServlet?type=showUserInformation" target='_blank'>显示个人信息</a><br><br>  
      <a href="/news/servlet/UserServlet?type=changeUserInformation1" target='_blank'>修改个人信息</a><br><br>
	      新闻<br> 
	  <a href="/news/news/addNews.jsp" target='_blank'>添加新闻</a><br><br>
	  <a href="/news/servlet/NewsServlet?type=manageNews&page=1&pageSize=2" target='_blank'>管理新闻</a><br><br>
	  <a href="/news/servlet/NewsServlet?type=showNews&page=1&pageSize=2" target='_blank'>显示新闻</a><br><br>  
	     评论<br>   
	  <a href="/news/servlet/CommentServlet?type=showCommnet&page=1&pageSize=2&newsId=8" target='_blank'>显示评论</a><br><br>
     
     
  </body>
</html>
