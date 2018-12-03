<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="code.domain.Activity" %>
<%@ page import="code.domain.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>活动评价</title>
<script src="js/jquery-1.11.1.js"></script>
<script src="js/joinerevaluate.js"></script>
<link href="css/changeInfo.css" rel="stylesheet" type="text/css" />
<link href="css/joinerevaluate.css" rel="stylesheet" type="text/css" />
<script >
	var userId = ${activity.publisher.userId}; 
	var activityId=${activity.activityId};
</script>
</head>

<body onload="InitEvent()">
	<div id="top">
		<img src="images/indexlogo.png" alt="logo" />
		<div class="title">活动评价</div>
	</div>
	<div id="main">
		<div id="left">
			<div class="acimage"><img src="${activity.imgUrl}" height="160" width="200"/></div>
			<a class="actitle" href="showAcdetails.action?activityId=${activity.activityId }"  >
			${activity.name}
			</a>
		</div>
		<div id="right">
			<div>评价：</div>
			<s:textarea id="describe" cols="100" rows="10" > </s:textarea>
			<div id="core">
				<table id="rating">
					<tr style="color:Yellow"><td>☆</td><td>☆</td><td>☆</td><td>☆</td><td>☆</td></tr>
				</table>
			</div>
			<a class="save" id="save" href="">保存</a>
			
		</div>
	
	
	</div>

	<div id="foot">
	
	</div>

</body>
</html>