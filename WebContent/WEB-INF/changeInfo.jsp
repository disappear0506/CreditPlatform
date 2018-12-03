<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<title>修改个人资料</title>
<script src="js/jquery-1.11.1.js"></script>
<script src="js/changeInfo.js"></script>
<link href="css/changeInfo.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div id="top">
		<img src="images/indexlogo.png" alt="logo" />
		<div class="title">修改个人资料</div>
	</div>
	<div id="main">
		<s:form action="saveEdit" method="post" namespace="/" theme="simple"
			id="changeinfo" enctype="multipart/form-data">
			<div id="main_left">
				<div id="preview">
					<div class="img">
						<img id="imghead" width="100" height="100" src="${sessionScope.user.imgUrl}" />
					</div>
				</div>
				<div class="file-box">
					<s:textfield type='text' id='textField' />
					<s:submit type='button' id="btn1" value='浏览...'
						onclick="return false;" />
					<s:file type="file" id="fileField" name="file" size="28" />
					<span></span>
				</div>
			</div>
			<div id="main_right">
				<div id="changeinfotitle">
					<div id="basetitle">基本资料</div>
				</div>
				<div id="changeinfomain">
					<s:hidden name="user.email" value="%{#session.user.email}"/>
					<div class="changeitem">
						<div class="itemtitle">昵称：</div>
						<s:textfield type="text" name="user.name"
							value="%{#session.user.name}" />
					</div>
					<div class="changeitem">
						<div class="itemtitle">手机号：</div>
						<s:textfield type="text" name="user.phone"
							value="%{#session.user.phone}"></s:textfield>
					</div>
					<div class="changeitem">
						<div class="itemtitle">学校：</div>
						<s:textfield type="text" name="user.school"
							value="%{#session.user.school}" />
					</div>
					<div class="changeitem">
						<div class="itemtitle">院系：</div>
						<s:textfield type="text" name="user.department"
							value="%{#session.user.department}" />
					</div>
					<div class="changeitem">
						<div class="itemtitle">年级：</div>
						<s:textfield type="text" name="user.grade"
							value="%{#session.user.grade}" />
					</div>
					<div class="changeitem">
						<div class="itemtitle">性别：</div>
						<s:textfield type="text" name="user.sex"
							value="%{#session.user.sex}" />
					</div>
					<div class="changeitem">
						<div class="itemtitle">出生年份：</div>
						<s:textfield type="text" name="user.burn"
							value="%{#session.user.burn}" />
					</div>
					<div class="changesave">
						<s:textfield type="submit" value="保存" id="save" />
					</div>
				</div>
			</div>
		</s:form>
	</div>

	<div id="foot">
	
	</div>

</body>
</html>