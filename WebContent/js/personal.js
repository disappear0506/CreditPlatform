// JavaScript Document
window.myIndex=0;
window.joinIndex=0;
window.myMax;
window.joinMax;

window.searchPubIndex=0;
window.searchJoIndex=0;
window.searcPubMax;
window.searchJoMax;

window.search = false;
window.original = true;
window.content;
$(function(){
	//轮询是否有人报名
	//longAskEnroll();
	
	//报名消息被查看
	/*
	$("#enrollMes").click(function(){
		window.location="tojsp?mesCount="+$("#badge").text();
	})*/
	//查看未读消息
	$("#noReadMes").click(function(){
		window.location="noReadMes";
	})
	//查看已读消息
	$("#ReadMes").click(function(){
		window.location="ReadMes";
	})
	
	
	
	//点击我发布的每一个活动
	$('#publish').on('click','.publishname',function () {
//	    window.open("myActivity?activityId="+$(this).attr("id"));
		window.location="myActivity?background=publish&activityId="+$(this).attr("id");
	});
	//点击我参与的每一个活动
	$("#join").on('click','.joinname',function(){
//		window.open("myActivity?activityId="+$(this).attr("id"));
		window.location="myActivity?background=join&activityId="+$(this).attr("id");
	})
	//点击“我发布的活动”
	$("#publishacitem").click(function(){
		getMyPublishedAc(0);
		window.original=true;
		window.search=false;
	})
	//点击“我参与的”
	$("#joinacitem").click(function(){
		getMyJoinedAc(0);
		window.original=true;
		window.search=false;
	})
	//点击我发布的 完成的
	$("#publishcomplete").click(function(){
		window.content="已结束";
		window.original=false;
		window.search=true;
		searchPublishedAc(0,window.content);
	})
	//点击我发布的 未完成的
	$("#publishuncomplete").click(function(){
		window.content="已通过";
		window.original=false;
		window.search=true;
		searchPublishedAc(0,window.content);
	})
	//点击我参与的 完成的
	$("#joincomplete").click(function(){
		window.content="已结束";
		window.original=false;
		window.search=true;
		searchJoinedAc(0,"已结束");
	})
	//点击我参与的 未完成的
	$("#joinuncomplete").click(function(){
		window.content="已通过";
		window.original=false;
		window.search=true;
		searchJoinedAc(0,"已通过");
	})
	//修改资料页面转跳
	var public=$("#changeinfoto").click(function(){
        window.location=("edit.action");
    })
	var public=$("#changeinfo").click(function(){
//		window.location=("edit.action?age="+$("#age").html());
		window.location=("edit.action");
    })
	/* 划过消息提示和设置时出现div*/
	$("#main>#main_right>#main_title>#titleinfo>#messagewarm").mouseover(function()
	{
		$("#main>#main_right>#main_title>#titleinfo>#messagewarm>#messageitem").css("display","block");
	})
	
	$("#main>#main_right>#main_title>#titleinfo>#messagewarm").mouseout(function()
	{
		$("#main>#main_right>#main_title>#titleinfo>#messagewarm>#messageitem").css("display","none");
	})
	$("#main>#main_right>#main_title>#titleinfo>#set").mouseover(function()
	{
		$("#main>#main_right>#main_title>#titleinfo>#set>#setitem").css("display","block");
	})
	
	$("#main>#main_right>#main_title>#titleinfo>#set").mouseout(function()
	{
		$("#main>#main_right>#main_title>#titleinfo>#set>#setitem").css("display","none");
	})
	
	/*划过消息设置里的选项时*/
	$("#main>#main_right>#main_title>#titleinfo>#messagewarm>#messageitem>.messitem").mouseover(function(){
		$(this).css("color","#03f");
	})
	$("#main>#main_right>#main_title>#titleinfo>#messagewarm>#messageitem>.messitem").mouseout(function(){
		$(this).css("color","#517290");
	})
	$("#main>#main_right>#main_title>#titleinfo>#set>#setitem>.messitem").mouseover(function(){
		$(this).css("color","#03f");
	})
	$("#main>#main_right>#main_title>#titleinfo>#set>#setitem>.messitem").mouseout(function(){
		$(this).css("color","#517290");
	})
	
	/*点击分类 切换界面*/
	$("#main>#main_right>#main_title>#titleitem>#infopersonalitem").click(function()
	{
		$(this).css("color","#517290");
		$("#main>#main_right>#main_title>#titleitem>#publishacitem").css("color","#000000");
		$("#main>#main_right>#main_title>#titleitem>#joinacitem").css("color","#000000");
		$("#main>#main_right>#main_content>#infopersonal").css("display","block");
		$("#main>#main_right>#main_content>#publishac").css("display","none");
		$("#main>#main_right>#main_content>#joinac").css("display","none");
	})
	
	$("#main>#main_right>#main_title>#titleitem>#publishacitem").click(function()
	{
		$(this).css("color","#517290");
		$("#main>#main_right>#main_title>#titleitem>#infopersonalitem").css("color","#000000");
		$("#main>#main_right>#main_title>#titleitem>#joinacitem").css("color","#000000");
		$("#main>#main_right>#main_content>#infopersonal").css("display","none");
		$("#main>#main_right>#main_content>#publishac").css("display","block");
		$("#main>#main_right>#main_content>#joinac").css("display","none");
	})
	
	$("#main>#main_right>#main_title>#titleitem>#joinacitem").click(function()
	{
		$(this).css("color","#517290");
		$("#main>#main_right>#main_title>#titleitem>#infopersonalitem").css("color","#000000");
		$("#main>#main_right>#main_title>#titleitem>#publishacitem").css("color","#000000");
		$("#main>#main_right>#main_content>#infopersonal").css("display","none");
		$("#main>#main_right>#main_content>#publishac").css("display","none");
		$("#main>#main_right>#main_content>#joinac").css("display","block");
	})
	
	
	/*鼠标划过活动框时 框边变化*/
	$(".page>.pageitem").mouseover(function()
	{
		$(this).css("border-color","#999");
	})
	$(".page>.pageitem").mouseout(function()
	{
		$(this).css("border-color","#ccc");
	})
	
	/*点击翻页*/
	$("#publishac>.page>.pageitem").click(function()
	{
		$("#publishac>.page>.pageitem").css("background","#fff");
		$(this).css("background","#ccc");
	})
	/*我发布的活动*/
	$("#myBeginPage").click(function(){
		if(window.myIndex>0 && window.original==true){
			window.myIndex=0;
			console.log(window.myIndex+" "+window.myMax);
			getMyPublishedAc(window.myIndex);
		}else if(window.searchPubIndex>0 && window.search==true){
			window.searchPubIndex=0;
			console.log(window.searchPubIndex+" "+window.searchPubMax);
			searchPublishedAc(window.searchPubIndex,window.content);
		}
	})
	$("#myLastPage").click(function(){
		if(window.myIndex>0 && window.original==true){
			window.myIndex-=3;
			console.log(window.myIndex+" "+window.myMax);
			getMyPublishedAc(window.myIndex);
		}else if(window.searchPubIndex>0 && window.search==true){
			window.searchPubIndex-=3;
			console.log(window.searchPubIndex+" "+window.searchPubMax);
			searchPublishedAc(window.searchPubIndex,window.content);
		}
	})
	$("#myNextPage").click(function(){
		if(window.myIndex+3<window.myMax && window.original==true){
			window.myIndex+=3;
			console.log(window.myIndex+" "+window.myMax);
			getMyPublishedAc(window.myIndex);
		}else if(window.searchPubIndex+3<window.searchPubMax && search==true){
			window.searchPubIndex+=3;
			console.log(window.searchPubIndex+" "+window.searchPubMax);
			searchPublishedAc(window.searchPubIndex,window.content);
		}
	})
	$("#myEndPage").click(function(){
		if(window.myIndex+3<window.myMax && window.original==true){
			window.myIndex=Math.floor(window.myMax/3)*3;
			if(window.myIndex==window.myMax)
				window.myMax-=3;
			console.log(window.myIndex+" "+window.myMax);
			getMyPublishedAc(window.myIndex);
		}else if(window.searchPubIndex+3<window.searchPubMax && window.search==true){
			window.searchPubIndex=Math.floor(window.searchPubMax/3)*3;
			if(window.searchPubIndex==window.searchPubMax)
				window.searchPubIndex-=3;
			console.log(window.searchPubIndex+" "+window.searchPubMax);
			searchPublishedAc(window.searchPubIndex,window.content);
		}
	})
	/*我参与的活动*/
	$("#joinBeginPage").click(function(){
		if(window.joinIndex>0 && window.original==true){
			window.joinIndex=0;
			console.log(window.joinIndex+" "+window.joinMax);
			getMyJoinedAc(window.joinIndex);
		}else if(window.searchJoIndex>0 && window.search==true){
			window.searchJoIndex=0;
			console.log(window.searchJoIndex+" "+window.searchJoMax);
			searchJoinedAc(window.searchJoIndex,window.content);
		}
	})
	$("#joinLastPage").click(function(){
		if(window.joinIndex>0 && window.original==true){
			window.joinIndex-=3;
			console.log(window.joinIndex+" "+window.joinMax);
			getMyJoinedAc(window.joinIndex);
		}else if(window.searchJoIndex>0 && window.search==true){
			window.searchJoIndex-=3;
			console.log(window.searchJoIndex+" "+window.searchJoMax);
			searchJoinedAc(window.searchJoIndex,window.content);
		}
	})
	$("#joinNextPage").click(function(){
		if(window.joinIndex+3<window.joinMax && window.original==true){
			window.joinIndex+=3;
			console.log(window.joinIndex+" "+window.joinMax);
			getMyJoinedAc(window.joinIndex);
		}else if(window.searchJoIndex+3<window.searchJoMax && window.search==true){
			window.searchJoIndex+=3;
			console.log(window.searchJoIndex+" "+window.searchJoMax);
			searchJoinedAc(window.searchJoIndex,window.content);
		}
	})
	$("#joinEndPage").click(function(){
		if(window.joinIndex+3<window.joinMax && window.original==true){
			window.joinIndex=Math.floor(window.joinMax/3)*3;
			if(window.joinIndex==window.joinMax){
				window.joinIndex-=3;
			}
			console.log(window.joinIndex+" "+window.joinMax);
			getMyJoinedAc(window.joinIndex);
		}else if(window.searchJoIndex+3<window.searchJoMax && window.search==true){
			window.searchJoIndex=Math.floor(window.searchJoMax/3)*3;
			if(window.searchJoIndex==window.searchJoMax){
				window.searchJoIndex-=3;
			}
			console.log(window.searchJoIndex+" "+window.searchJoMax);
			searchJoinedAc(window.searchJoIndex,window.content);
		}
	})
	
	//点击开始活动按钮
	/*
	$('#publish').on('click','#startactivity',function () {
		var msg = "您确定开始活动？";    
		
		if (confirm(msg)==true){
			window.location="startactivity?activityId="+$(".publishname").attr("id");
		}   
		
	})*/
	//点击结束活动按钮
	/*
	$('#publish').on('click','#endactivity',function () {
		var msg = "您确定要结束活动？";
		if (confirm(msg)==true){
			window.location="endactivity?activityId="+$(".publishname").attr("id");
		}   
		
	})*/
	//点击评价活动按钮
	/*
	$('#publish').on('click','#evaluateactivity',function () {
		
	   window.location="evaluate?activityId="+$(".publishname").attr("id")+"&&currPage=1";
	})*/
	//点击自己参加活动的评价按钮
	$('#join').on('click','#joinerevaluateactivity',function(){
		 window.location="joinerevaluate?activityId="+$(".joinname").attr("id");
	})
	
	
})
//获取我发布的活动
function getMyPublishedAc(index){
	$.ajax({
		url:"getpublishedActivity",
		data:{
			begin:index,
			pageSize:3
		},
		success:function(result){
			$("#publish").empty();
			var array = $.parseJSON(result);
			window.myMax = array[0];
			array.splice(0,1);
			$.each(array,function(index,value){
				var msg="<div class='publishitem'>"+
				"<div class='publishtime'>"+value.time+"</div>"+
				"<img src='"+value.imgUrl+"' height='100px' width='140px' class='publishpicture' />"+
				"<div class='publishname' id='"+value.activityId+"'><a>"+value.name+"</a></div>"+
				"<div class='publishnumber'>"+
					"活动人数：<br />"+value.perNum+
				"</div>"+
				"<div class='publishstate'>"+
					"活动状态：<br />"+value.status+
				"</div>";
				
				if(value.status=="待审核"){
					msg+="<span class='publishspan'>审核中</span>"+"</div>";
				}else if(value.status=="已通过"){
					
					//msg+="" +"<form action='startactivity?activityId="+value.activityId+"' method='post'/>"+
							//"<input type='submit' value='开始活动' class='publishinput' id='startactivity' />"
							//+"</form></div>";
					msg+=" <a href='javascript:startactivity("+value.activityId+")' class='publishinput' style='text-align:center;'>开始活动</a>";
				}else if(value.status=="进行中"){
					//msg+="<input type='button' value='确认结束' class='publishinput' id='endactivity'/>"+"</div>";
					msg+=" <a href='javascript:endactivity("+value.activityId+")' class='publishinput' style='text-align:center;'>确认结束</a>";
				}else if(value.status=="待评价"){
					msg+="<input type='button' value='评价' class='publishinput' id='evaluateactivity' />"+"</div>";
				}else if(value.status=="已结束"){
					if(value.ispingjia==0)
					{
						//msg+="<input type='button' value='评价' class='publishinput' id='evaluateactivity' />"+"</div>";
						msg+=" <a href='javascript:evaluateactivity("+value.activityId+")' class='publishinput' style='text-align:center;'>评价</a>";
						
					}else{
						msg+="<span class='publishspan'></span>"+"</div>";
					}
				}
				$("#publish").append(msg);
			})
		}
	})
}
//按条件查找我发布的活动
function searchPublishedAc(index,content){
	$.ajax({
		url:"searchPublishedAc",
		data:{
			begin:index,
			content:content
		},
		success:function(result){
			$("#publish").empty();
			var array = $.parseJSON(result);
			window.searchPubMax = array[0];
			array.splice(0,1);
			$.each(array,function(index,value){
				var msg="<div class='publishitem'>"+
				"<div class='publishtime'>"+value.time+"</div>"+
				"<img src='"+value.imgUrl+"' height='100px' width='140px' class='publishpicture' />"+
				"<div class='publishname' id='"+value.activityId+"'><a>"+value.name+"</a></div>"+
				"<div class='publishnumber'>"+
					"活动人数：<br />"+value.perNum+
				"</div>"+
				"<div class='publishstate'>"+
					"活动状态：<br />"+value.status+
				"</div>";
				
				if(value.status=="待审核"){
					msg+="<span class='publishspan'>审核中</span>"+"</div>";
				}else if(value.status=="已通过"){
					//msg+="<input type='button' value='开始活动' class='publishinput' id='startactivity' />"+"</div>";
					msg+=" <a href='javascript:startactivity("+value.activityId+")' class='publishinput' style='text-align:center;'>开始活动</a>";
				}else if(value.status=="进行中"){
					//msg+="<input type='button' value='确认结束' class='publishinput' id='endactivity'/>"+"</div>";
					msg+=" <a href='javascript:endactivity("+value.activityId+")' class='publishinput' style='text-align:center;'>确认结束</a>";
				}else if(value.status=="待评价"){
					msg+="<input type='button' value='评价' class='publishinput' id='evaluateactivity' />"+"</div>";
				}else if(value.status=="已结束"){
					if(value.ispingjia==0)
					{
						//msg+="<input type='button' value='评价' class='publishinput' id='evaluateactivity' />"+"</div>";
						msg+=" <a href='javascript:evaluateactivity("+value.activityId+")' class='publishinput' style='text-align:center;'>评价</a>";
						
					}else{
						msg+="<span class='publishspan'></span>"+"</div>";
					}
				}
				$("#publish").append(msg);
			})
		}
	})
}

function startactivity(startactivityId){
	//alert(startactivityId);
	window.location="startactivity?activityId="+startactivityId;
}

function endactivity(id){
	//alert(startactivityId);
	window.location="endactivity?activityId="+id;
}
//评价活动
function evaluateactivity(id){
	//alert(startactivityId);
	window.location="evaluate?activityId="+id;
}


//获取我参与的活动
function getMyJoinedAc(index){
	$.ajax({
		url:"getJoAcByPage",
		data:{
			begin:index,
			pageSize:3
		},
		success:function(result){
			var array = $.parseJSON(result);
			$("#join").empty();
			window.joinMax = array[0];
			array.splice(0,1);
			
			$.each(array,function(index,value){
				var msg="<div class='publishitem'>"+
				"<div class='publishtime'>"+value.time+"</div>"+
				"<img src='"+value.imgUrl+"' height='100px' width='140px' class='publishpicture' />"+
				"<div class='joinname' id='"+value.activityId+"'><a>"+value.name+"</a></div>"+
				"<div class='publishnumber'>"+
					"活动人数：<br />"+value.perNum+
				"</div>"+
				"<div class='publishstate'>"+
					"活动状态：<br />"+value.status+
				"</div>";
				
				var agreeflag=false;
				var refuseflag=false;
				var i;
				 //对是否同意进行处理
				var trueJoiner= new Array(); //定义一数组 
				trueJoiner=value.trueJoinerId.split(","); //字符分割 
				for (i=0;i<trueJoiner.length ;i++ ) 
				{ 
					//已经同意的人
					if(trueJoiner[i]==userId)
					{
						if(value.status=="进行中"){
							msg+="<span class='publishspan'>等待发布者结束</span>"+"</div>";
						}else if(value.status=="待评价"){
							msg+="<input type='button' value='评价' class='publishinput' id='joinerevaluateactivity' />"+"</div>";
						}else if(value.status=="已结束"){
							//msg+="<span class='publishspan'></span>"+"</div>";
							//alert(value.ispingjia);
							if(value.ispingjia==0)
							{
								msg+=" <a href='javascript:evaluateactivity("+value.activityId+")' class='publishinput' style='text-align:center;'>评价</a>";
								//msg+="<input type='button' value='评价' class='publishinput' id='evaluateactivity' />"+"</div>";
							}else{
								msg+="<span class='publishspan'></span>"+"</div>";
							}
							
							
						}else{
							msg+="<span class='publishspan'>发布者已同意</span>"+"</div>";
						}
						agreeflag=true;
					}
				}
				//还未同意
				if(!agreeflag)
				{
					var unwillingOut= new Array(); //定义一数组 
					unwillingOut=value.unwillingOutID.split(","); //字符分割 
					for (i=0;i<unwillingOut.length ;i++ )
					{
						//被拒绝的人
						if(unwillingOut[i]==userId)
						{
							msg+="<span class='publishspan'>已被拒绝</span>"+"</div>";
							refuseflag=true;
						}
					}
					//还未审核的 
					if(!refuseflag)
					{
						msg+="<span class='publishspan'>待发布者审核</span>"+"</div>";
					}	
				}
				 //msg+="<input type='button' value='评价' class='publishinput' id='joinerevaluateactivity' />"+"</div>";
				$("#join").append(msg);
			})
		}
	})
}
//按条件查找我报名的活动
function searchJoinedAc(index,content){
	console.log(index);
	$.ajax({
		url:"searchJoinedAc",
		data:{
			begin:index,
			content:content
		},
		success:function(result){
			var array = $.parseJSON(result);
			$("#join").empty();
			window.searchJoMax = array[0];
			array.splice(0,1);
			$.each(array,function(index,value){
				var msg="<div class='publishitem'>"+
				"<div class='publishtime'>"+value.time+"</div>"+
				"<img src='"+value.imgUrl+"' height='100px' width='140px' class='publishpicture' />"+
				"<div class='joinname' id='"+value.activityId+"'><a>"+value.name+"</a></div>"+
				"<div class='publishnumber'>"+
					"活动人数：<br />"+value.perNum+
				"</div>"+
				"<div class='publishstate'>"+
					"活动状态：<br />"+value.status+
				"</div>";
				
				var agreeflag=false;
				var refuseflag=false;
				var i;
				 //对是否同意进行处理
				var trueJoiner= new Array(); //定义一数组 
				trueJoiner=value.trueJoinerId.split(","); //字符分割 
				for (i=0;i<trueJoiner.length ;i++ ) 
				{ 
					//已经同意的人
					if(trueJoiner[i]==userId)
					{
						if(value.status=="进行中"){
							msg+="<span class='publishspan'>等待发布者结束</span>"+"</div>";
						}else if(value.status=="待评价"){
							msg+="<input type='button' value='评价' class='publishinput' id='joinerevaluateactivity' />"+"</div>";
						}else if(value.status=="已结束"){
							//msg+="<span class='publishspan'></span>"+"</div>";
							//alert(value.ispingjia);
							if(value.ispingjia==0)
							{
								msg+=" <a href='javascript:evaluateactivity("+value.activityId+")' class='publishinput' style='text-align:center;'>评价</a>";
								//msg+="<input type='button' value='评价' class='publishinput' id='evaluateactivity' />"+"</div>";
							}else{
								msg+="<span class='publishspan'></span>"+"</div>";
							}
							
							
						}else{
							msg+="<span class='publishspan'>发布者已同意</span>"+"</div>";
						}
						agreeflag=true;
					}
				}
				//还未同意
				if(!agreeflag)
				{
					var unwillingOut= new Array(); //定义一数组 
					unwillingOut=value.unwillingOutID.split(","); //字符分割 
					for (i=0;i<unwillingOut.length ;i++ )
					{
						//被拒绝的人
						if(unwillingOut[i]==userId)
						{
							msg+="<span class='publishspan'>已被拒绝</span>"+"</div>";
							refuseflag=true;
						}
					}
					//还未审核的 
					if(!refuseflag)
					{
						msg+="<span class='publishspan'>待发布者审核</span>"+"</div>";
					}	
				}
				 //msg+="<input type='button' value='评价' class='publishinput' id='joinerevaluateactivity' />"+"</div>";
				$("#join").append(msg);
			})
		}
	})
}
//轮询是否有人报名
function longAskEnroll(){
	$.ajax({
		url:"askEnroll",
		success:function(result){
//			$("#MesSpan").text("有人报名");
			$(".badge").text(result);
			longAskEnroll();
		}
	})
}
