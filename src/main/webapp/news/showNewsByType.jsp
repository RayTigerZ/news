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
	
	<script type="text/javascript"> 
 		function getOnePage(type){
    	  	var url1;
    	  	var page=document.getElementById("page");
    	  	var pageSize=document.getElementById("pageSize");
    	  	var totalPageCount=document.getElementById("totalPageCount");
			
    	  	pageValue=parseInt(page.value);
    	  	if(type=="pre"){
    	  		pageValue-=1;
    	  		page.value=pageValue.toString();
    	  	}else if(type=="next"){
    	  		pageValue+=1;
    	  		page.value=pageValue.toString();
    	  	}
    	  	//提交
    	  	document.getElementById('myform').submit();
      	}
	</script>  
  </head>
  
  <body>
    <div class="center" style="width:810px"> 
  	<form action="/news/servlet/NewsServlet?type=showNewsByNewsType&newsType=${requestScope.newsType}" id="myform" method="post">
  	 
  	  <div class="newsShowByType_frame center" id="frameDiv">
  	  	<div class="newsShowByType_left" id="leftDiv">
  	  		<ul style="list-style-type: none;">
				<c:choose>
				    <c:when test="${requestScope.newsType=='all'}">
						<li>全部</li>
					</c:when>
				    <c:otherwise>
						<li><a href="/news/servlet/NewsServlet?type=showNewsByNewsType&newsType=all&page=1&pageSize=5">
  	  					全部</a></li>	
				    </c:otherwise>				        
				</c:choose>	  	  			
  	  					
	 			<c:forEach items="${requestScope.newsTypes}"  var="newsType1">	  	 			
					<c:choose>
					    <c:when test="${newsType1.newsTypeName == requestScope.newsType}">
							<li>${newsType}</li>
						</c:when>
					    <c:otherwise>
					    	<li><a href="/news/servlet/NewsServlet?type=showNewsByNewsType&newsType=${newsType1.newsTypeName}&page=1&pageSize=5">
					    		${newsType1.newsTypeName}</a></li>
					    </c:otherwise>				        
					</c:choose>				
				</c:forEach> 	  		
  	  		</ul>
		</div>	
		<div class="newsShowByType_rightTop">
			<div><ul>
					<c:forEach items="${requestScope.newses}"  var="news"> 
						<li><a href="/news/servlet/NewsServlet?type=showNews&newsId=${news.newsId}&page=1&pageSize=2" target="_blank">${news.caption}</a></li>
					</c:forEach>
				</ul>
			</div>	
		</div>
		<div class="newsShowByType_rightBottom" >
			<div class="center" style="width:150px;">
				<c:if test="${requestScope.pageInformation.page > 1}">
					<td><a href="javascript:void(0);" onclick="getOnePage('pre');">上一页</a></td>  
				</c:if>					
				<c:if test="${requestScope.pageInformation.page < requestScope.pageInformation.totalPageCount}">
					&nbsp; &nbsp;<a href="javascript:void(0);" onclick="getOnePage('next');">下一页</a>
				</c:if>	
			</div>			
		</div>
  	  </div>	
	
 		<input type="hidden" name="page" id="page" value="${requestScope.pageInformation.page}">
		<input type="hidden" name="pageSize" id="pageSize" value="${requestScope.pageInformation.pageSize}">
 		<input type="hidden" name="totalPageCount" id="totalPageCount" value="${requestScope.pageInformation.totalPageCount}">
		<input type="hidden" name="allRecordCount" id="allRecordCount" value="${requestScope.pageInformation.allRecordCount}">
 	</form>
 	</div>
  </body>
  
  <script type="text/javascript">
  	//让父div与子div高度一致（这里因为子div是绝对定位，脱离了文档流，导致父div高度为0了，所以要手动设定父div高度。
  	//否则下面父div后面的兄弟div就会往上移，因为父div的高度为0
  	var father = document.getElementById('frameDiv');
    var son = document.getElementById('leftDiv');
    frameDiv.style.height=leftDiv.offsetHeight+'px';
  </script>
</html>
