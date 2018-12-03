$(document).ready(function(){
							
	$("#select1 dd").click(function () {
		//为选中的dd增加样式selected
		$(this).addClass("selected").siblings().removeClass("selected");
		//异步发送ajax请求
		var request=new XMLHttpRequest();
		request.open("POST","appointFindCondition.action",true);
		request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		request.send("referKey=status&referValue="+$(this).text());
		request.onreadystatechange=callback;//绑定回调函数
		
	});
	
	$("#select2 dd").click(function () {
		$(this).addClass("selected").siblings().removeClass("selected");
		
	});
	
	$("#select3 dd").click(function () {
		$(this).addClass("selected").siblings().removeClass("selected");
		
	});
	
});
//回调函数
function callback()
{
	//window.location.reload();
}

