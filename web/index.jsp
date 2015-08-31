<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>大学生服务</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="resources/js/jquery-1.9.1.js"></script>
  </head>
  
  <body>
    大学生服务 <br>
    <button>按钮 </button>
    <table >
    <tr>
		<td width="200"></td>
		<td></td>
    </tr>
    <tr>
		<td>3</td>
		<td></td>
		<td></td>
		<td><a href="#">测试链接</a></td>
    </tr>
    </table>
  </body>
</html>
<script type="text/javascript">
//请求数据,登录账号 +密码  
var data = {  
     email: 'anme',  
     useid: 1
}  
  
$.ajax({  
    url : "http://127.0.0.1:8080/dxsfw/pub/updateUser",  
    type : "GET",  
    data : JSON.stringify(data), //转JSON字符串  
    dataType: 'json',  
    contentType:'application/json;charset=UTF-8', //contentType很重要     
    success : function(result) {  
        console.log(result);  
    }  
}); 

$(document).ready(function(){
  $("button").click(function(){
  $(this).hide();
  });
});
</script>