<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>审核任务页面</title>
    <link rel="stylesheet" href="css/publish3.css"/>
    
    <script src="js/jquery-1.11.1.js"></script>

<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="bootstrap/css/bootstrap-theme.min.css">
<script type="text/javascript"
	src="bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="bootstrap/css/bootstrap-select.min.css">
<script type="text/javascript" src="bootstrap/js/bootstrap-select.min.js"></script>
  <script  type="text/javascript">   
	function del() {
    	var msg = "您确定要退出？";     
		if (confirm(msg)==true){
	          window.location.href="AdminLogin.jsp"; 
		}   
    }   
</script>   
</head>
<body>
<div class="top wp">
    <img src="images/indexlogo.png" alt="logo"/>
    <div class="title">审核任务</div>
    
</div>
<div class="row">
	<div class="col-md-3"></div>
	<div class="col-md-6">
	<a onclick="javascript:del()">退出登陆</a>
	<table class="table table-bordered">
  
  <thead>
    <tr>
      <th>图片</th>
      <th>标题</th>
      <th>介绍</th>
      <th>地点</th>
      <th>时间</th>
      <th>发布者</th>
      <th></th><th></th>
    </tr>
  </thead>
  <tbody>
  
  <c:forEach var="act" items="${list}">
   <tr>
   	<td><img src="${act.imgUrl}" height="100" width="140"/></td>
      <td>${act.name}</td>
      <td>${act.introduce}</td>
      <td>${act.place}</td>
      <td>${act.time}</td>
      <td>
      <a href="lookOtherInfo?email=${act.publisher.email}" >${act.publisher.name}</a>
      </td>
      <td>
      <a href="checkPass?activityId=${act.activityId}" >通过</a>
    <br/>
    </td>
    <td>
       <a href="checkOut?activityId=${act.activityId}" >不通过</a>
      </td>
    </tr>
  </c:forEach>
    
  </tbody>
</table></div>
	<div class="col-md-3"></div>
</div>
<div id="foot">

	</div>
</body>
</html>