<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>找回密码页面</title>
    <link rel="stylesheet" href="css/revise3.css"/>
    <script src="js/jquery-1.11.1.js"></script>
    <script src="js/revise3.js" charset="gb2312"></script>
</head>
<body>
<div class="top wp">
    <img src="${pageContext.request.contextPath}/images/indexlogo.png"" alt="logo"/>
    <div class="title">找回密码</div>
    <button id="login">登录</button>
    <button id="register">注册</button>
</div>
<s:form action="reviseThree" namespace="/" method="post" theme="simple">
<div class="center wp">
    <div class="center1">
        <div class="div1">1.确认账号</div>
        <div class="div2">2.安全验证</div>
        <div class="div3">3.重置密码</div>
    </div>
    <div class="psw">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;新密码
        <input name="user.password" type="password"/>  <span>&nbsp;&nbsp;&nbsp;&nbsp;${reviseThreeMes}</span>
        <span class="span1"></span>
        <span class="span2"></span>
        <span class="span3"></span>
    </div>
    <div class="psw2">
        确认新密码
        <input name="confirmPassword" type="password"/>
        <span></span>
    </div>
    <div class="next"><button>确定</button></div>
</div>
</s:form>
</body>
</html>