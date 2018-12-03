// JavaScript Document

$(function(){
	function appendheight()
	{	
		$heightacform=$("#main>#main_left>#acforum>#acform_main").height()+190;
		$("#main>#main_left>#acforum>#acform_main").css("height",$heightacform+"px");
		$heightacformleft=$("#main>#main_left>#acforum>#acform_main>#acform_left").height()+190;
		$("#main>#main_left>#acforum>#acform_main>#acform_left").css("height",$heightacformleft+"px");
		$height=$("#main").height()+190;
		$("#main").css("height",$height+"px");
		
	}
	function deheight()
	{	
		$heightacform=$("#main>#main_left>#acforum>#acform_main").height()-190;
		$("#main>#main_left>#acforum>#acform_main").css("height",$heightacform+"px");
		$heightacformleft=$("#main>#main_left>#acforum>#acform_main>#acform_left").height()-190;
		$("#main>#main_left>#acforum>#acform_main>#acform_left").css("height",$heightacformleft+"px");
		$height=$("#main").height()-190;
		$("#main").css("height",$height+"px");
		
	}
	//点击收起
	$(".title>a#more").click(function(){
		$("#main>#main_left>#acdetails02").css("display","block");
		$("#main>#main_left>#acdetails01").css("display","none");
		$height=$("#main").height()+150;
		$("#main").css("height",$height+"px");
	})
	//点击收起
	$(".title>a#return").click(function(){
		$("#main>#main_left>#acdetails02").css("display","none");
		$("#main>#main_left>#acdetails01").css("display","block");
		$height=$("#main").height()-150;
		$("#main").css("height",$height+"px");
	})
	//点击评论框
	var $i0=0;
	$("#main>#main_left>#acforum>#acform_main>#acform_right>#othercom01>.pinglun").click(function(){
		if($i0%2==0)
		{
			$("#main>#main_left>#acforum>#acform_main>#acform_right>#othercom01>.publishcom02").css("display","block");
			
			$heightother=$("#main>#main_left>#acforum>#acform_main>#acform_right>#othercom01").height()+190;
			$("#main>#main_left>#acforum>#acform_main>#acform_right>#othercom01").css("height",$heightother+"px");
			appendheight();
			
		}
		else
		{
			$("#main>#main_left>#acforum>#acform_main>#acform_right>#othercom01>.publishcom02").css("display","none");
			
			$heightother=$("#main>#main_left>#acforum>#acform_main>#acform_right>#othercom01").height()-190;
			$("#main>#main_left>#acforum>#acform_main>#acform_right>#othercom01").css("height",$heightother+"px");
			deheight();
		}
		$i0=$i0+1;
		
	})
	
	var $i1=0;
	$("#main>#main_left>#acforum>#acform_main>#acform_right>#othercom02>.pinglun").click(function(){
		if($i1%2==0)
		{
			$("#main>#main_left>#acforum>#acform_main>#acform_right>#othercom02>.publishcom02").css("display","block");
			$heightother=$("#main>#main_left>#acforum>#acform_main>#acform_right>#othercom02").height()+190;
			$("#main>#main_left>#acforum>#acform_main>#acform_right>#othercom02").css("height",$heightother+"px");
			appendheight();
		}
		else
		{
			$("#main>#main_left>#acforum>#acform_main>#acform_right>#othercom02>.publishcom02").css("display","none");
			
			$heightother=$("#main>#main_left>#acforum>#acform_main>#acform_right>#othercom02").height()-190;
			$("#main>#main_left>#acforum>#acform_main>#acform_right>#othercom02").css("height",$heightother+"px");
			deheight();
		}
		$i1=$i1+1;
		
	})
	
	var $i2=0;
	$("#main>#main_left>#acforum>#acform_main>#acform_right>#othercom03>.pinglun").click(function(){
		if($i2%2==0)
		{
			$("#main>#main_left>#acforum>#acform_main>#acform_right>#othercom03>.publishcom02").css("display","block");
			$heightother=$("#main>#main_left>#acforum>#acform_main>#acform_right>#othercom03").height()+190;
			$("#main>#main_left>#acforum>#acform_main>#acform_right>#othercom03").css("height",$heightother+"px");
			appendheight();
		}
		else
		{
			$("#main>#main_left>#acforum>#acform_main>#acform_right>#othercom03>.publishcom02").css("display","none");
			$heightother=$("#main>#main_left>#acforum>#acform_main>#acform_right>#othercom03").height()-190;
			$("#main>#main_left>#acforum>#acform_main>#acform_right>#othercom03").css("height",$heightother+"px");
			deheight();
		}
		$i2=$i2+1;
		
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