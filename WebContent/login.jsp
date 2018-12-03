<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>登录页面</title>
<link rel="stylesheet" href="css/login.css" />
<script src="js/jquery-1.11.1.js"></script>
<script src="js/login.js" charset="UTF-8"></script>
</head>
<body>
	<div class="top wp">
		<img src="${pageContext.request.contextPath}/images/indexlogo.png" alt="logo" />
		<div class="title">登录</div>
		<div class="register">
			还未注册，现在就
			<button>注册</button>
		</div>
	</div>
	<div class="center wp">
		<div class="picture">
			<img src="${pageContext.request.contextPath}/images/index.jpg" alt="picture" />
		</div>
		<div class="login">
			<div class="information">
				<span style="font-size:15px">${loginMes}${reviseThreeMes} ${registerMes} </span>
			</div>
			<s:form action="login" method="post" namespace="/" theme="simple">
				<div class="phone">
					<s:textfield name="user.email" id="phone" placeholder="帐号（输入邮箱）" />
				</div>
				<div class="psw">
					<s:password id="psw" name="user.password" type="text" placeholder="密码" />
				</div>
			</s:form>
			<div class="checkBox">
				<span>&nbsp;&nbsp;记住帐号</span><a href="reviseOne.jsp">忘记密码？</a><input
					id="checkBox" type="checkbox" />
			</div>
			<div class="button">
				<button>登录</button>
			</div>
		</div>
	</div>
	<div id="foot">
		
	</div>
</body>
</html>