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
    
    <title>My JSP 'manageNews.jsp' starting page</title>
    
	<script type="text/javascript">
	  function deleteANews(id){
		ids1=document.getElementById("ids"); 
	  	ids1.value=id;
	  	//提交
	  	document.getElementById('myform').action="/news/servlet/NewsServlet?type=deleteNews";
		document.getElementById('myform').submit();
	  }
	  	
	  function editsANews(id){
		ids1=document.getElementById("ids"); 
	  	ids1.value=id;
	  	//提交
	  	document.getElementById('myform').action="/news/servlet/NewsServlet?type=editNews";
		document.getElementById('myform').submit();
	  }	
	  
      function getOnePage(type){
    	  	var url1;
    	  	var page=document.getElementById("page");
    	  	var pageSize=document.getElementById("pageSize");
    	  	var totalPageCount=document.getElementById("totalPageCount");
    	  	
  			var order=document.getElementById("order");
  			var orderField=document.getElementById("orderField");
			
    	  	pageValue=parseInt(page.value);
    	  	if(type=="first")
    	  		page.value="1";
    	  	else if(type=="pre"){
    	  		pageValue-=1;
    	  		page.value=pageValue.toString();
    	  	}else if(type=="next"){
    	  		pageValue+=1;
    	  		page.value=pageValue.toString();
    	  	}else if(type=="last"){
    	  		page.value=totalPageCount.value;
    	  	}
    	  	//提交
    	  	document.getElementById('myform').submit();
      	}
	</script> 

  </head>
  
  <body>
    <form action="/news/servlet/NewsServlet?type=manageNews" target="_blank" id="myform" method="post">
  	 <table align="center" border='1' >
	    <tr bgcolor='#FFACAC'>
	      <td>Id</td>
	      <td>标题</td><td>作者</td><td>日期</td>
	      <td>删除</td><td>编辑</td>
	    </tr>	    
	    <c:forEach items="${requestScope.newses}"  var="news">      
	   		<tr>
		   		<td>${news.newsId}</td>     
		   		<td>${news.caption}</td>	
		   		<td>${news.author}</td>     
		   		<td>${news.newsTime}</td>
		   		<td><a href="javascript:void(0);" onclick="deleteANews(${news.newsId});">删除</a></td>
		   		<td><a href="javascript:void(0);" onclick="editsANews(${news.newsId});">编辑</a></td>	
		   	</tr>
		</c:forEach> 	    
	</table>
	 <table align="center" border='1'>     
	   	<tr>			
			<c:if test="${requestScope.pageInformation.page > 1}">
				<td><a href="javascript:void(0);" onclick="getOnePage('first');">首页</a></td>  
			</c:if>
			
			<c:if test="${requestScope.pageInformation.page > 1}">
				<td><a href="javascript:void(0);" onclick="getOnePage('pre');">上一页</a></td>  
			</c:if>			 
			
			<c:if test="${requestScope.pageInformation.page < requestScope.pageInformation.totalPageCount}">
				<td><a href="javascript:void(0);" onclick="getOnePage('next');">下一页</a></td>
			</c:if>	  			
			<c:if test="${requestScope.pageInformation.page < requestScope.pageInformation.totalPageCount}">
				<td><a href="javascript:void(0);" onclick="getOnePage('last');">尾页</a></td>
			</c:if>	
			<td>共${requestScope.pageInformation.totalPageCount}页</td>   		
		</tr>    
	 </table>
	 	<input type="hidden" name="page" id="page" value="${requestScope.pageInformation.page}">
		<input type="hidden" name="pageSize" id="pageSize" value="${requestScope.pageInformation.pageSize}">
	 	<input type="hidden" name="totalPageCount" id="totalPageCount" value="${requestScope.pageInformation.totalPageCount}">
		<input type="hidden" name="allRecordCount" id="allRecordCount" value="${requestScope.pageInformation.allRecordCount}">
	 	<input type="hidden" name="orderField" id="orderField" value="${requestScope.pageInformation.orderField}">
		<input type="hidden" name="order" id="order" value="${requestScope.pageInformation.order}">
	 	<input type="hidden" name="ids" id="ids" value="${requestScope.pageInformation.ids}">
		<input type="hidden" name="searchSql" id="searchSql" value="${requestScope.pageInformation.searchSql}">
	 	<input type="hidden" name="result" id="result" value="${requestScope.pageInformation.result}">
	 </form>
  </body>
</html>
