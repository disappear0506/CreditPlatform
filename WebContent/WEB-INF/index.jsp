<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大学生信用培养平台</title>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/nivo-slider.css" type="text/css" media="screen" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
<script src="js/jquery-1.11.1.js"></script>
<script src="js/index.js"></script>
</head>

<body>
<div id="top">
	<div id="logo"></div>
    <a class="naviga_onclick" href="#" >首页</a>
    <a class="naviga" href="appoint.action" >约起来</a>
    <a class="naviga" href="lendBook.action" >互借书</a>
    <a class="naviga" href="growUp.action" >助成长</a>
    <a class="naviga" href="leaveMarket.action" >闲置市场</a>
    <a class="naviga" href="load.action" >小额借款</a>
    <a class="naviga" href="personal.action" >个人中心</a>
  <div id="submit">
  	
    </div>
</div>
<div id="main">
	<div id="main_left">
    	<div id="flash">
    		<div id="wrapper">
        <div id="slider-wrapper">
            <div id="slider" class="nivoSlider">
               <img src="images/flash/flash02.jpg" alt="" />
               <img src="images/flash/flash01.gif" alt=""  />
               <img src="images/flash/flash04.jpg" alt="" />
               <img src="images/flash/flash05.jpg" alt=""  />
            </div>
        </div>
        <script type="text/javascript" src="js/jquery-1.4.3.min.js"></script>
<script type="text/javascript" src="js/jquery.nivo.slider.pack.js"></script>
<script type="text/javascript">
	$(window).load(function() {
		$('#slider').nivoSlider();
	});
</script>
    </div>
    	</div>
    	<!-- 开始 -->
        <div id="hostac">
        	<div id="title">热门活动</div>
        	<c:forEach var="activity" items="${list}" step="6" varStatus="act">
            <div class="host_left">
            	<div class="achost">
            		<div class="acimage"><img src="${activity.imgUrl}" height="100" width="140"/></div>
                	<div class="acdescribe">
                		<div class="acschooltitle">
                			
                    		<a class="actitle" href="showAcdetails.action?activityId=${activity.activityId }" >
                    		<c:out value="${fn:substring(activity.name, 0, 20)}"></c:out>
                    		</a>
                     	</div>
                    	<div class="acbiref"><p><c:out value="${fn:substring(activity.introduce, 0, 35)}"></c:out>...</p></div>
                	</div>
                </div>
                
                <div class="actable">
                	<c:forEach var="activitys" items="${list}" begin="${act.index+1}" end="${act.index+5}">
                	<div class="actableitem">
                    	<div class="actableitemst">
                        
                            <a class="actableitemtitle" href="showAcdetails.action?activityId=${activitys.activityId}" >&nbsp;&nbsp;<c:out value="${fn:substring(activitys.name, 0, 13)}"></c:out>..</a>                      
                        </div>
                        <div class="actableitemtime">${activitys.time}</div>
                    </div>
                   </c:forEach>
                </div>
            </div>
            </c:forEach>     
        </div>
        
    </div>
    <!-- 结束 -->
    <div id="main_right">
   <strong> 网站活动类型介绍</strong><br/><br/>
 <strong>1.一起约</strong><br/><br/>
     &nbsp;&nbsp;&nbsp;该活动包括学生平时所需一系列“约”行为，比如约球，约饭等等。约的双方可通过对方赴约情况，赴约满意度进行互评，获取对应的信用积分。
 <br/><br/><strong>2.远书荒”</strong><br/><br/>
 &nbsp;&nbsp;&nbsp;该活动主要为学生提供借书功能，借方可在网站上发布自己的需求，学生也可以将自己的闲置书发布在网站上供人借阅，在双方完成一次交易时，通过对书籍的归还时间和保护程度等进行互评，获取信用积分。
 <br/><br/><strong>3.助成长</strong><br/><br/>
&nbsp;&nbsp;&nbsp; 该活动为老师与学生之间信用活动，老师可在网站中发布自己的需求，学生可通过自己的能力选取对应的需求，在完成一次项目后双方可通过项目完成的情况等进行互评，获取信用积分。
 <br/><br/><strong>4.闲渔</strong><br/><br/>
 &nbsp;&nbsp;&nbsp;该活动为学生提供闲置物品的买卖或者交换功能，学生可在平台上发布关于闲置物品的相关信息进行交易，在交易完成后对交易物品的满意度等进行互评，获取信用积分。
<br/><br/><strong> 5.小额借款</strong><br/><br/>
&nbsp;&nbsp;&nbsp; 该模块在学生积累了一定的信用积分后才可开启，为学生之间提供小额借款服务。学生可通过网站发布借款需求，被借方可通过查阅借方的信用积分选择借与不借，交易完成后可通过借款是否及时归还等行为进行互评，获取信用积分。
    
    
    </div>
</div>
<div id="foot">

</div>

</body>
</html>
