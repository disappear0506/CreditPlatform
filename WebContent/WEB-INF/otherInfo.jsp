<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看好友资料</title>
<script src="js/jquery-1.11.1.js"></script>
<script src="js/otherInfo.js"></script>
<link href="css/personal.css" rel="stylesheet" type="text/css" />
</head>

<body>
<input type='hidden' value='${other.email}' id='bigEmail'/>
<div id="top">
    <img src="images/indexlogo.png" alt="logo"/>
    <div class="title">查看好友资料</div>
</div>

<div id="main">
	<div id="main_left">
    	<img src="${other.imgUrl }" height="100px" width="100px"  id="head"/>
        <div id="gra"><span id="gradetitle">信用积分：</span><span id="grade">${other.score}</span></div>
    </div>
    <div id="main_right">
    	<div id="main_title">
        	<div id="titleinfo">
            	<div id="username">用户昵称</div>
            </div>
            <div id="titleitem">
            	<div id="infopersonalitem" class="item">ta的个人资料</div>
                <div id="publishacitem" class="item">ta发布的活动</div>
                <div id="joinacitem" class="itemlast">ta参与的活动</div>
            </div>
        </div>
        <div id="main_content">
        	<div id="infopersonal">
           		<div id="infotop">
            		<div id="infopersonaltilte">ta的基本资料</div>
                </div>
                <div id="info">
                	   <div class="infoitem">
							<div class="infotitle">昵称：</div>
							<div class="infomessage">${other.name}</div>
						</div>
						<div class="infoitem">
							<div class="infotitle">邮箱：</div>
							<div class="infomessage">${other.email}</div>
						</div>
						<div class="infoitem">
							<div class="infotitle">手机号：</div>
							<div class="infomessage">${other.phone}</div>
						</div>
						<div class="infoitem">
							<div class="infotitle">学校：</div>
							<div class="infomessage">${other.school }</div>
						</div>
						<div class="infoitem">
							<div class="infotitle">院系：</div>
							<div class="infomessage">${other.department }</div>
						</div>
						<div class="infoitem">
							<div class="infotitle">年级：</div>
							<div class="infomessage">${other.grade }</div>
						</div>
						<div class="infoitem">
							<div class="infotitle">性别：</div>
							<div class="infomessage">${other.sex}</div>
						</div>
						<div class="infoitem">
							<div class="infotitle">年龄：</div>
							<div class="infomessage">
								<span id="age">${2016-other.burn} </span>
							</div>
						</div>
                
                </div>
            
            </div>
            <div id="publishac">
					<div id="publishtop">
						<div id="publishtitle">ta的发布</div>
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
        
        
        </div><!-- mainContentDIV结束 -->
    
    
    </div><!-- mainrightDIV结束 -->


</div><!-- mainContentDIV结束 -->
<div id="foot">

</div>

</body>
</html>