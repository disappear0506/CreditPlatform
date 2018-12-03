// JavaScript Document

$(function(){
	//ask();
	var public=$("#post").click(function(){
        window.open("leaveMarketPublish1.action?tempname="+encodeURI("闲置市场"));
    })
	var classflycontentclick1=$("#classify>#classifymain>.classifyitem01>.content").click(function(){
		//$("#classify>#classifymain>.classifyitem01>.content").css("background","#e2e2e2");
		$(this).css("background","#50ABf2");
		$(this).css("border-radius","5px");	
	})
	var classflycontentclick2=$("#classify>#classifymain>.classifyitem02>.content").click(function(){
		$(this).css("background","#50ABf2");
		$(this).css("border-radius","5px");	
	})

	var classflycontentclick3=$("#classify>#classifymain>.classifyitem03>.content").click(function(){
		$(this).css("background","#50ABf2");
		$(this).css("border-radius","5px");	
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
	$(".page>.pageitem").click(function()
	{
		$(".page>.pageitem").css("background","#fff");
		$(this).css("background","#ccc");
	})
})
function ask(){
	$.ajax({
		url:"askLeaveMarket",
		success:function(result){
		    window.location=("leaveMarket");
		}
	})
}
