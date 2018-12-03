<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人中心</title>
<link href="css/personal.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap.min.css">
<script src="js/jquery-1.11.1.js"></script>
<script src="js/personal.js"></script>
<script  type="text/javascript">   
	function del() {
    	var msg = "您确定要退出？";     
		if (confirm(msg)==true){
	          window.location.href="login.jsp"; 
		}   
    }   
</script> 
 <script>  
       <%--JS gloable varilible--%>  
       var userId = ${user.userId};  
 </script>  
</head>

<body>
	<div id="top">
		<div id="logo"></div>
		<a class="naviga" href="myIndex.action" >首页</a> <a
			class="naviga" href="appoint.action" >约起来</a> <a
			class="naviga" href="lendBook.action" >互借书</a> <a
			class="naviga" href="growUp.action" >助成长</a> <a
			class="naviga" href="leaveMarket.action" >闲置市场</a> <a
			class="naviga" href="load.action" >小额借款</a> <a
			class="naviga_onclick" href="#" target="_self">个人中心</a>
		<div id="submit">
		</div>
	</div>

	<div id="main">
		<div id="main_left">
			<!-- upload/e98dfea1-2147-4452-8a82-6d7e12f13876.jpg -->
			<img src="${user.imgUrl}" height="100px" width="100px" id="head" />
			<div id="gra">
				<span id="gradetitle">信用积分：</span><span id="grade">${user.score}</span>
			</div>
		</div>
		<div id="main_right">
			<div id="main_title">
				<div id="titleinfo">
					<div id="username">用户昵称</div>
					<div id="messagewarm">
						消息提醒  <span class="badge"></span>
						<div id="messageitem">
							<div id="first">消息提醒  </div>
							<div class="messitem" id="enrollMes">报名消息  <span class="badge" id="badge"></span></div>
						</div>

					</div>
					<span style="color:red;font-size:10px;" id="MesSpan"></span>
					<div id="set">
						设置
						<div id="setitem">
							<div id="first">设置</div>
							<div class="messitem" id="changeinfoto">修改资料</div>
							<div class="messitem" onclick="javascript:del()">退出登录</div>
						</div>

					</div>

				</div>
				<div id="titleitem">
					<div id="infopersonalitem" class="item">个人资料</div>
					<div id="publishacitem" class="item">我发布的活动</div>
					<div id="joinacitem" class="itemlast">我参与的活动</div>
				</div>
			</div>
			<div id="main_content">
				<div id="infopersonal">
					<div id="infotop">
						<div id="infopersonaltilte">基本资料</div>
						<div id="changeinfo">修改</div>
					</div>
					<div id="info">
						<div class="infoitem">
							<div class="infotitle">昵称：</div>
							<div class="infomessage">${user.name}</div>
						</div>
						<div class="infoitem">
							<div class="infotitle">邮箱：</div>
							<div class="infomessage">${user.email}</div>
						</div>
						<div class="infoitem">
							<div class="infotitle">手机号：</div>
							<div class="infomessage">${user.phone}</div>
						</div>
						<div class="infoitem">
							<div class="infotitle">学校：</div>
							<div class="infomessage">${user.school }</div>
						</div>
						<div class="infoitem">
							<div class="infotitle">院系：</div>
							<div class="infomessage">${user.department }</div>
						</div>
						<div class="infoitem">
							<div class="infotitle">年级：</div>
							<div class="infomessage">${user.grade }</div>
						</div>
						<div class="infoitem">
							<div class="infotitle">性别：</div>
							<div class="infomessage">${user.sex}</div>
						</div>
						<div class="infoitem">
							<div class="infotitle">年龄：</div>
							<div class="infomessage">
								<span id="age">${2018-user.burn} </span>
							</div>
						</div>
						<div class="infoitem">
							<div class="infotitle"></div>
							<div class="infomessage"></div>
						</div>
						<div class="infoitem">
							<div class="infotitle"></div>
							<div class="infomessage"></div>
						</div>
						<div class="infoitem">
							<div class="infotitle"></div>
							<div class="infomessage"></div>
						</div>

					</div><!-- info结束 -->

				</div><!-- infopersonal结束 -->
				<div id="publishac">
					<div id="publishtop">
						<div id="publishtitle">我的发布</div>
						<div id="publishcomplete">已完成</div>
						<div id="publishuncomplete">未完成</div>
					</div>
					<div id="publish" class="publish">
						
					</div>
				
					<div class="page">
						<div class="pageitem">
							<a id="myBeginPage">第一页</a>
						</div>
						<div class="pageitem">
							<a id="myLastPage">上一页</a>
						</div>
						<div class="pageitem">
							<a id="myNextPage">下一页</a>
						</div>
						<div class="pageitem">
							<a id="myEndPage">最后一页</a>
						</div>
					</div>
				
				</div><!-- publishac结束 -->

				<div id="joinac">
					<div id="jointop">
						<div id="jointitle">我的参与</div>
						<div id="joincomplete">已完成</div>
						<div id="joinuncomplete">未完成</div>
					</div>
					<div id="join" class="publish">
					
					</div><!-- joinacDIV 里的publishDIV结束 -->
					
					<div class="page">
						<div class="pageitem">
							<a id="joinBeginPage">第一页</a>
						</div>
						<div class="pageitem">
							<a id="joinLastPage">上一页</a>
						</div>
						<div class="pageitem">
							<a id="joinNextPage">下一页</a>
						</div>
						<div class="pageitem">
							<a id="joinEndPage">最后一页</a>
						</div>
					</div>
				
				</div><!-- joinacDIV 结束 -->

			</div> <!-- mainContentDIV结束 -->

		</div><!-- mainrightDIV结束 -->

	</div><!-- mainContentDIV结束 -->
	
	<div id="foot">
		
	</div>
	<script src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>
</body>
</html>