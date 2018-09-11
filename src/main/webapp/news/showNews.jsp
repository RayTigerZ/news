<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="tool.WebProperties" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<table width="800" border="0">
			<tbody>
				<tr><td class="newsCaption">${requestScope.news.caption}</td>
				<tr><td align="center" height="50">作者：${requestScope.news.author}&nbsp;	&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
						</td>
				</tr><tr><td height="30"><hr></td></tr>
				<tr>
					<td>${requestScope.news.content}</td>
				</tr>
				<tr><td height="30"><hr></td></tr>
			</tbody>
		</table>
	</div>
	<jsp:include page='/comment/addComment.jsp' flush="true" >
		<jsp:param name="newsId" value="${requestScope.news.newsId}" />
		<jsp:param name="page" value="1" />
		<jsp:param name="pageSize" value="${param.pageSize}" />
		<jsp:param name="totalPageCount" value="${param.totalPageCount}" />
	</jsp:include>	
	<jsp:include page='<%="/servlet/CommentServlet?type=showCommnet"%>' flush="true" >
		<jsp:param name="newsId" value="${requestScope.news.newsId}" />
		<jsp:param name="page" value="${param.page}" />
		<jsp:param name="pageSize" value="${param.pageSize}" />
		<jsp:param name="totalPageCount" value="${param.totalPageCount}" />
	</jsp:include>	
  </body>
  
<script language="JavaScript">
	if('${param.addCommnet}'=='addCommnet')  
     	location.hash="#commentsStart";  
</script>
</html>
