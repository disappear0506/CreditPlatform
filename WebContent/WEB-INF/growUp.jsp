<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>助成长</title>
<link href="css/growUp.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-1.11.1.js"></script>
<script src="js/growUp.js"></script>
</head>

<body>
<div id="top">
	<div id="logo"></div>
    <a class="naviga" href="myIndex.action" >首页</a>
    <a class="naviga" href="appoint.action" >约起来</a>
    <a class="naviga" href="lendBook.action" >互借书</a>
    <a class="naviga_onclick" href="#" target="_self">助成长</a>
    <a class="naviga" href="leaveMarket.action" >闲置市场</a>
    <a class="naviga" href="load.action" >小额借款</a>
    <a class="naviga" href="personal.action" >个人中心</a>
<div id="submit">
  </div>
</div>

<div id="classify">
<div id="classifyku">
	<div id="classifymain">
		<div class="classifyitem01">
        	<span class="type">活动进程：</span>
            <span class="content">全部</span>
            <span class="content">进行中</span>
            <span class="content">已过期</span>    
        </div>
        <!--  
    	<div class="classifyitem02">
        	<span class="type">地区：</span>
            <span class="content">全部</span>
           	<c:forEach var="pro" items="${province}" begin="1" end="3">
            <span class="content">${pro}</span>
            </c:forEach>
            
            <select class="select">
            	<c:forEach var="proi" items="${province}" begin="4">
      			<option value="${proi}">${proi}</option>
      			</c:forEach>
    		</select> 
    		
        </div>
    	<div class="classifyitem03">
        	<span class="type">学校：</span>
            <span class="content">全部</span>
            <c:forEach var="name" items="${schoolname}" begin="0" end="1">
            <span class="content">${name}</span>
            </c:forEach>
            
            <select class="select">
            	<c:forEach var="namei" items="${schoolname}" begin="2" end="15">
      			<option value="${namei}">${namei}</option>
      			</c:forEach>
    		</select> 
        </div>
        -->
    </div>
    <div id="postmessage">
    	<input type="button" id="post" value="我也要发布" />
    </div>

</div>
</div>


<div id="main">
	<div class="line">
		<s:iterator value="returnlist" var="activity">
    	<div class="action">
        	<img src="<s:property value="#activity.imgUrl"/>" height="250px" width="300px" />
            <div class="actitle"><a href="showAcdetails.action?activityId=${activity.activityId }" ><s:property value="#activity.name"/></a></div>
            <div class="acnumber"><span class="numbertitle">报名人数:</span><span class="number">${activity.joinerCount}</span></div>
            <div class="acstate"><s:property value="#activity.status"/></div>
        </div>
   		</s:iterator>
    
	</div>
    <div class="page">
    	
        	<span class="pageitem">第<s:property value="currPage"/>/<s:property value="totalPage"/>页</span>
        <s:if test="currPage!=1">
        	<span class="pageitem"><a href="${pageContext.request.contextPath}/growUpFindPageBean.action?currPage=1">[首页]</a></span>
        	<span class="pageitem"><a href="${pageContext.request.contextPath}/growUpFindPageBean.action?currPage=<s:property value="currPage-1"/>">[上一页]</a></span>
        </s:if>
        <s:if test="currPage!=totalPage">
        	<span class="pageitem"><a href="${pageContext.request.contextPath}/growUpFindPageBean.action?currPage=<s:property value="currPage+1"/>">[下一页]</a></span>
        	<span class="pageitem"><a href="${pageContext.request.contextPath}/growUpFindPageBean.action?currPage=<s:property value="totalPage"/>">[尾页]</a></span>
    	</s:if>
    </div>

</div>
<div id="foot">

</div>

</body>
</html>