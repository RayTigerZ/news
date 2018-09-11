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
	
	<link rel="stylesheet" type="text/css" href="/news/css/1.css">

  </head>
  
  	<div class="newsShowByType_frame center"  id="frameDiv">
  	  	<div class="newsShowByType_left" id="leftDiv">
  	  		<ul style="list-style-type: none;">
		    	<li><a href="/news/servlet/UserServlet?type=showUserInformation" target='frame'>显示个人信息</a></li>
  				<li><a href="/news/servlet/UserServlet?type=changeUserInformation1" target='frame'>修改个人信息</a></li>
  				<li><a href="/news/all/changePassword.jsp" target='frame'>修改密码</a></li>
  				<c:if test="${sessionScope.user.userType=='manager'}" >
  					<br>
	 		    	<li><a href="/news/servlet/UserServlet?type=show&page=1&pageSize=2" target='frame'>浏览用户</a></li>
	  				<li><a href="/news/servlet/UserServlet?type=check&page=1&pageSize=2" target='frame'>审查信息</a></li>
	  				<li><a href="/news/manager/searchUser.jsp" target='frame'>查询用户</a></li>
	  				<li><a href="/news/servlet/UserServlet?type=delete&page=1&pageSize=2" target='frame'>删除用户</a></li>
	  				<br>
	  				<li><a href="/news/servlet/NewsServlet?type=manageNews&page=1&pageSize=2" target='frame'>管理新闻</a></li>
	 			</c:if>	
  				<c:if test="${sessionScope.user.userType=='newsAuthor'}" >
  					<br>
	 		    	<li><a href="/news/news/addNews.jsp" target='_blank'>添加新闻</a></li>
 					<li><a href="/news/servlet/NewsServlet?type=manageNews&page=1&pageSize=2" target='frame'>管理新闻</a></li>
	 			</c:if>		 			
  	  		</ul>
		</div>	
		<div class="manageMain_right">
			<iframe name="frame" id="frame" scrolling="no" seamless frameborder="0" width="100%" height="100%">
			</iframe>
		</div>
		<div class="clear"></div>
  	  </div>	
  <body>
    <script type="text/javascript">
  	//让父div与子div高度一致（这里因为子div是绝对定位，脱离了文档流，导致父div高度为0了，所以要手动设定父div高度。
  	//否则下面父div后面的兄弟div就会往上移，因为父div的高度为0
  	var father = document.getElementById('frameDiv');
    var son = document.getElementById('leftDiv');
    frameDiv.style.height=leftDiv.offsetHeight+'px';
  </script>
  </body>
</html>
