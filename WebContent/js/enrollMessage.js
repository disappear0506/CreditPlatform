window.logMax;
window.index=0;
$(function(){
	getEnrollMes(0);
	
	$("#Return").click(function(){
		window.location='toPerson';
	})
	
	$("#myBeginPage").click(function(){
		console.log(window.index);
		if(window.index>0 ){
			window.index=0;
			console.log(window.index+" "+window.logMax);
			getEnrollMes(window.index);
		}
	})
	$("#myLastPage").click(function(){
		if(window.index>0){
			window.index-=7;
			console.log(window.index+" "+window.logMax);
			getEnrollMes(window.index);
		}
	})
	$("#myNextPage").click(function(){
		if(window.index+7<window.logMax){
			window.index+=7;
			console.log(window.index+" "+window.logMax);
			getEnrollMes(window.index);
		}
	})
	$("#myEndPage").click(function(){
		if(window.index+7<window.logMax){
			index=Math.floor(window.logMax/7)*7;
			if(window.index==window.logMax)
				window.index-=7;
			console.log(window.index+" "+window.logMax);
			getEnrollMes(window.index);
		}
	})
	function getEnrollMes(index){
		$.ajax({
			url:"readMes",
			data:{
				begin:window.index
			},
			success:function(result){
				$("#mes").empty();
				$("#mes").append("<ul>");
				var array = $.parseJSON(result);
				window.logMax = array[0];
				array.splice(0,1);
				$.each(array,function(index,value){
					$("#mes").append("<li>"+value+"</li><br>");
				})
				$("#mes").append("</ul>");
			}
		})
	}
})
