<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>提示</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">  
    var time = 6;  
  
    function returnUrlByTime() {  
  
        window.setTimeout('returnUrlByTime()', 1000);  
  
        time = time - 1;  
  
        document.getElementById("layer").innerHTML = time;
        if(time==0)
        {
        	window.location.href="evaluate?activityId="+${activityId}+"&& currPage=1"; 
        }  
    }  
</script>  
  </head>
  
  <body  onload="returnUrlByTime()">
  <center>
  <div id="main_block">

   	<center>
           <div style="margin-top:50px; font-size:20px;">${infomsg}！系统将过<span id="layer">5</span>秒后自动跳转到评价界面..</div>
    </center>
   <br/>
   <br/>
  </div>
  </center>
  </body>
</html>
