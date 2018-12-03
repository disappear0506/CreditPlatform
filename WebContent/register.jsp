<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册页面</title>
<link rel="stylesheet" href="css/register.css" />
<script src="js/jquery-1.11.1.js"></script>
<script src="js/register.js" charset="UTF-8"></script>
<script type="text/javascript">

		function validate(){
			document.getElementById("code").innerHTML="验证码已发送";
			
			//使用ajax向服务器发送发送验证码请求
			var email=document.getElementById("phone").value;
			var request=new XMLHttpRequest();
			request.open("POST","sendCode.action",true);
			request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			request.send("email="+email);
			operator();
		}
		
</script>


</head>
<body>
	<div class="top wp">
		<img src="${pageContext.request.contextPath}/images/indexlogo.png"" alt="logo" />
		<div class="title">注册帐号</div>
		<div class="login">
			我已注册，现在就
			<button>登录</button>
		</div>
	</div>
	<div class="center wp">
		<s:form action="register" namespace="/" method="post" theme="simple">
			<div class="phone">
				&nbsp;&nbsp;&nbsp;邮箱&nbsp;&nbsp;
				<s:textfield name="user.email" id="phone" placeholder="请输入邮箱" />
				<span style="font-size: 15px">${registerMes}</span>
			</div>
			<div class="psw">
				&nbsp;&nbsp;&nbsp;密码&nbsp;&nbsp;
				<s:password name="user.password" id="psw" placeholder="请输入密码" />
				<span class="span"></span>
			</div>
			<div class="checkCode">
				验证码&nbsp;&nbsp;
				<s:textfield name="checkCode" id="checkCode" placeholder="请输入验证码" />
				
				<div id="sentMessage" ><a href="javascript:validate()" id="code">获取验证码</a> </div>
				<span></span>
			</div>
			
			<div class="checkBox">
				<input id="checkBox" type="checkbox" checked /> &nbsp;&nbsp;阅读并接受 <a
					href="registerPro.html">《大学生信用培养平台注册协议》</a> <span></span>
			</div>
		
		<div class="button">
			<button id="submit">注册</button>
		</div>
		</s:form>
	</div>
	<div id="foot">
		
	</div>
</body>
</html>