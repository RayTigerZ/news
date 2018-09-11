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
    
   
  	 <link href="/news/css/1.css" rel="stylesheet" type="text/css">
  	 <script type="text/javascript"> 
  	 	function search(){
  	 		document.getElementById('searchForm').submit();
  	 	}	
  	 </script>

  </head>
  
  <body>
    <form id="searchForm" action="/news/servlet/NewsServlet?type=search">
		 <div class="div-out">
		 	<div class="logleft">
		 		<img src="/news/image/log.png">
		 	</div>
		 	<div class="logMiddle">
				<div class="logMiddleInner">			
					<input type="text" id="search">
					<a href='javascript:void(0);' onclick="search();">
					<img  src="/news/image/search_b.jpg" /></a>	
				</div> 	
		 	</div>
			<div class="logRight">
				<div class="logRightInner">	
					<c:if test="${!(empty sessionScope.user) }">
						<a href="/news/manageMain.jsp">管理</a>&nbsp;
					</c:if>
				
					<a href="/news/index.jsp">首页</a>&nbsp;
					<c:choose>
						<c:when test="${empty sessionScope.user}">
							<a href="/news/login.jsp">登录</a>
							&nbsp;<a href="/news/registe.jsp">注册</a>
					    </c:when>
					    <c:otherwise>
					    	${sessionScope.user.userName}&nbsp;
					    	<a href="/news/servlet/UserServlet?type=exit">注销</a>
					    </c:otherwise>
					</c:choose>		
				</div> 	
		 	</div>
		</div>
		<div class="clear"></div>
	</form>	   
  </body>
</html>
