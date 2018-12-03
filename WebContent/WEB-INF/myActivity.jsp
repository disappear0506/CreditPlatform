<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Expires" content="0">
<meta http-equiv="kiben" content="no-cache">
<title>我发布的活动的活动详情</title>
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/myActivity.js"></script>
<link href="css/myActivity.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/acdetails.js"></script>
</head>

<body>
<div id="top">
	<div id="logo"></div>
    <a class="naviga_onclick" href="myIndex.action" >首页</a>
    <a class="naviga" href="appoint.jsp" >约起来</a>
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
    	<img src="${activity.imgUrl}" height="400px" width="780px" />
    	<div id="acdetails01">
        	<div class="title">
            	<div class="titlename">活动详情</div>
            	<a id="more">更多∨</a>
            </div>
            <div id="ac01main">
         
            	<p>一：活动描述<br/>
            	<c:out value="${fn:substring(activity.introduce, 0, 80)}"></c:out></p>
            </div>
           	
        </div>
        <div id="acdetails02">
			<div class="title">
            	<div class="titlename">活动详情</div>
            	<a id="return">收起∨</a>
            </div>
            <div id="ac02main">
            		<p>一：活动描述<br/>${activity.introduce}</p>
                	<p>二：活动时间<br/>
                    	${activity.time}
                    </p>
                
            </div>
        
        </div>
        <div id="acforum">
        	<div class="title">
            	<div class="titlename">活动论坛</div>
            </div>
            <div id="acform_main">
        		<div id="acform_left">
                </div>
            	<div id="acform_right">
                	<c:forEach var="comment" items="${activity.commentSet }">
                    <div class="othercom" id="othercom01">
                    	<div class="info">
                        	<img src="${comment.commenter.imgUrl}" height="70px" width="70px" />
                            <div class="cominfo">
                            	<div class="compersonname"><a href="lookOtherInfo?email=${comment.commenter.email}" >${comment.commenter.name}</a></div>
                                <div class="comtime">${comment.time}</div>
                            </div>
                        </div>
                        <div class="comcontent">${comment.content}</div>
                    </div>
                    </c:forEach>
                    
        		</div>
            			
            </div>
        </div>
        
    </div>
    <div id="main_right">
    	<div id="rightinfo">
        	<div id="info01">
        		<input type='hidden' value='${activity.activityId}' id="bigId"></input>
            	<div class="infotitle">${activity.name}</div>
                <div id="info01main">
                	<div class="infoitem">活动类别:&nbsp;&nbsp;&nbsp;${activity.activityType.name }</div>
                	<div class="infoitem">地点:&nbsp;&nbsp;&nbsp;${activity.place }</div>
                    <div class="infoitem">时间:&nbsp;&nbsp;&nbsp;${activity.time}</div>
                    <div class="infoitem">发布人:&nbsp;&nbsp;&nbsp;${activity.publisher.name}</div>
                    <div class="infoitem">分享到</div>
                </div>
            </div>
            <div id="info02">
            	<input type="submit" value="结束活动" id="stopActivity"/>
            </div>
            <div id="info03">
            	<div id="info03title">已报名</div>
                <div id="info03status">${activity.status}</div>
                <div id="info03num"> ${ acPerNum } </div>
            
            </div>
        </div>
        
        <div class="rightapply" id="joinDIV">
        	<div class="infotitle">报名人员</div>
        	<input type="hidden" value="${joinCount}" id="joinCount"/>
        	<div id="join">
        	<c:if test="${background=='publish'}">
        	<c:forEach var="joiner" items="${joinerlist}">
                	<div class="applyitem">
                		<img src="${joiner.imgUrl}" height="30px" width="30px" />
                		<div class="username"><a href="lookOtherInfo?email=${joiner.email}" >${joiner.name}</a><span style='color:red' id='refuAcptInfo'><span></div>
                		<a class='accept' id='${joiner.userId}' name='${activity.activityId}'>接受</a>
                		<a class='refuse' id='${joiner.userId}' name='${activity.activityId}'>拒绝</a>
                	</div>
                	<br/>
            </c:forEach>
            </c:if>
            <c:if test="${background=='join'}">
            	<c:forEach var="joiner" items="${joinerlist}">
                	<div class="applyitem">
                		<img src="${joiner.imgUrl}" height="30px" width="30px" />
                		<div class="username"><a href="lookOtherInfo?email=${joiner.email}" >${joiner.name}</a></div>
                	</div>
                	<br/>
            	</c:forEach>
            </c:if>
            </div>
            <br/>
            <br/>
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
        </div>
        <div class="rightapply" id="trueJoinDIV">
         	<div class="infotitle">实际参与人员</div>
         	<input type="hidden" value="${trueJoinCount}" id="trueJoinCount"/>
        	<div id="trueJoin">
        		<c:forEach var="trueJoiner" items="${trueJoinerlist}">
                	<div class="applyitem">
                		<img src="${trueJoiner.imgUrl}" height="30px" width="30px" />
                		<div class="username"><a href="lookOtherInfo?email=${trueJoiner.email}" >${trueJoiner.name}</a></div>
                	</div>
                	<br/>
           	 	</c:forEach>
            </div>
            <br/>
        	<div class="page">
				<div class="pageitem">
					<a id="trueJoinBeginPage">第一页</a>
				</div>
				<div class="pageitem">
					<a id="trueJoinLastPage">上一页</a>
				</div>
				<div class="pageitem">
					<a id="trueJoinNextPage">下一页</a>
				</div>
				<div class="pageitem">
					<a id="trueJoinEndPage">最后一页</a>
				</div>
			 </div>
        </div>
    	
    
    </div>
</div>
<div id="foot">

</div>

</body>
</html>