<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <div class="newsShowByType_frame center" >
  	  	<div class="newsShowByType_left">
  	  		<ul style="list-style-type: none;">
		    	<li><a href="/news/servlet/UserServlet?type=showPrivate" target='frame'>显示个人信息</a></li>
  				<li><a href="/news/servlet/UserServlet?type=changePrivate1" target='frame'>修改个人信息</a></li>
  				<li><a href="/news/user/manage/changePassword.jsp" target='frame'>修改密码</a></li>
  	  		</ul>
		</div>	
		<div class="backMainUser_right">
			<iframe name="frame" id="frame" scrolling="no" seamless frameborder="0" width="100%" height="100%"></iframe>
		</div>
  	  </div>	

	 <div class="clear center" style="width:400;">
	 	<hr style="margin-top:25px;">
		Copyright © 2006-2015 renming.com All Rights Reserved. 人民网 版权所有 <br>
			京ICP证120085号 京ICP备16004154号 京网文[2012]0620-206号<br>
					京公网安备 11011202000608号<br>
	 </div>   
  </body>
</html>
