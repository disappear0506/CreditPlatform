<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>找回密码页面</title>
<link rel="stylesheet" href="css/revise1.css" />
<script src="js/jquery-1.11.1.js"></script>
<script src="js/revise1.js" charset="UTF-8"></script>
<script type="text/javascript">
        function reloadCode()
        {
            var time=new Date().getTime();
            document.getElementById("CreateCheckCode").src="getCode.action?d="+time;
        }
</script>
 
</head>
<body>
	<div class="top wp">
		<img src="${pageContext.request.contextPath}/images/indexlogo.png"" alt="logo" />
		<div class="title">找回密码</div>
		<button id="login">登录</button>
		<button id="register">注册</button>
	</div>
	<div class="center wp">
		<div class="center1">
			<div class="div1">1.确认账号</div>
			<div class="div2">2.安全验证</div>
			<div class="div3">3.重置密码</div>
		</div>
		<div class="information">
			<span>${reviseOneMes}</span>
		</div>
		<s:form action="reviseOne" namespace="/" method="post" theme="simple">
			<div class="phone">
				<s:textfield type="text" name="user.email" id="phone" placeholder="帐号（输入邮箱）" />
			</div>
			<div class="checkCode">
				<input name="findCode" type="text" id="checkCode" title="验证码区分大小写"  
                size="8" maxlength="4" /> 
				<div class="image">
					<img src="getCode.action" id="CreateCheckCode" align="middle" > 
				</div>
				<div class="change">
					 <a href="javascript:reloadCode()"> 看不清,换一个</a>  
				</div>
			</div>
		</s:form>
		<div class="next">
			<button>下一步</button>
		</div>
	</div>
</body>
</html>