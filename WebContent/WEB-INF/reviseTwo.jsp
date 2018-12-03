<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>找回密码页面</title>
    <link rel="stylesheet" href="css/revise2.css"/>
    <script src="js/jquery-1.11.1.js"></script>
    <script src="js/revise2.js" charset="UTF-8"></script>
</head>
<body>
<script type="text/javascript">

		function validate(){
			document.getElementById("code").innerHTML="验证码已发送";
			
			//使用ajax向服务器发送发送验证码请求
			var email=document.getElementById("email").innerHTML;
			var request=new XMLHttpRequest();
			request.open("POST","sendCode.action",true);
			request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			request.send("email="+email);
			
		}
		
</script>

<div class="top wp">
    <img src="${pageContext.request.contextPath}/images/indexlogo.png"" alt="logo"/>
    <div class="title">找回密码</div>
    <button id="login">登录</button>
    <button id="register">注册</button>
</div>
<s:form action="reviseTwo" namespace="/" method="post" theme="simple">
<div class="center wp">
	
    <div class="center1">
        <div class="div1">1.确认账号</div>
        <div class="div2">2.安全验证</div>
        <div class="div3">3.重置密码</div>
    </div>
 
    <div class="div1"><p>为了您的帐号安全，请完成身份验证</p></div>
    <div class="div2"><p>邮箱验证</p></div>
    <div class="div3"><span>邮箱号：</span><span id="email">${findemail}</span> &nbsp;&nbsp;${reviseTwoMes}</div>
    <div class="div4"><p>验证码</p></div>
    <div class="checkCode">
        <input type="text" placeholder="请输入验证码" name="checkCode"/>
        <button> <a href="javascript:validate()" id="code">获取验证码</a> </button>
     	 
    </div>
    <div class="next"><button>下一步</button></div>
    
</div>
</s:form>
</body>
</html>