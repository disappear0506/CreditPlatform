<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap.min.css">
<link rel="stylesheet" href="css/enrollMessage.css" type="text/css" media="screen" />
<script src="js/jquery-1.11.1.js"></script>
<script src="js/enrollMessage.js"></script>
</head>
<body>
<div id="top">
	<div id="logo"></div>
    <a class="naviga_onclick" href="#" >首页</a>
    <a class="naviga" href="appoint.action" >约起来</a>
    <a class="naviga" href="lendBook.action" >互借书</a>
    <a class="naviga" href="growUp.action" >助成长</a>
    <a class="naviga" href="leaveMarket.action" >闲置市场</a>
    <a class="naviga" href="load.action" >小额借款</a>
    <a class="naviga" href="personal.action" >个人中心</a>
</div>
<div id="content">
		<h3 style='text-align:center'>未读消息</h3>
		<button class='btn btn-primary' id='Return'>返回</button>
		
		<ul>
			<c:forEach var="message" items="${noreadMessage }">
			<li style="line-height:40px;">${message.content}</li>
			</c:forEach>
		</ul>
	
		
</div>
</body>
</html>