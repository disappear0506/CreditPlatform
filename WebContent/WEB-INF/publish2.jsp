<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发布任务页面</title>
<link rel="stylesheet" href="css/publish2.css" />
<script src="js/jquery-1.11.1.js"></script>
<script src="js/publish2.js"></script>

</head>
<body>
	<div class="top wp">
		<img src="images/indexlogo.png" alt="logo" />
		<div class="title">发布任务</div>
	</div>
	<div class="center wp">
		<div class="center1">
			<div class="div1">1.协议</div>
			<div class="div2">2.填写活动信息</div>
			<div class="div3">3.审核</div>
		</div>
		<s:form action="publish3" method="post" namespace="/" theme="simple" enctype="multipart/form-data">
			<div class="name">
				活动名称
				<s:textfield type="text" name="activity.name" id="name"/>
				<span></span>
			</div>
			<div class="classes">
				类别&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<s:textfield type="text" name="name" id="classes" value="%{name}" readonly="true"/>
				<span></span>
			</div>
			<div id="preview">
			    <span>活动封面</span>
				<div class="img">
					<img id="imghead" width="260" height="190" /><span></span>
				</div>
			</div>
			<div class="file-box">
				<s:textfield type='text' id='textField' class='txt'
					name="activity.imgUrl" style="height:22px; border:1px solid #cdcdcd; width:180px;padding-left:15px;" />
				<s:submit type='button' class='btn1' value='浏览...' onclick="return false;" style="background-color:#FFF; border:1px solid #CDCDCD;height:24px; width:70px;"/>
				<s:file type="file" class="file" id="fileField" name="file" size="28" style="position:absolute;top:0; right:80px; height:24px; filter:alpha(opacity:0);opacity: 0;width:260px;"/>
				<span></span>
			</div>
			<div class="place">
				活动地点
				<s:textfield type="text" name="activity.place" id="place"/>
				<span></span>
			</div>
			<div class="time">
				活动时间
				<s:textfield type="text" name="activity.time" id="time"/>
				<span></span>
			</div>
			<div class="describe">
				活动描述
				<s:textarea name="activity.introduce" id="describe" cols="30"
					rows="10" ></s:textarea>
				<span></span>
			</div>
		</s:form>
		<div class="next">
			<button id="button">提交等待审核</button>
		</div>
	</div>
	<div id="foot">
	
	</div>
</body>
</html>