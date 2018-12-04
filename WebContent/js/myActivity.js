window.joinIndex=0;
window.trueJoinIndex=0;
window.joinMax;
window.trueJoinMax;
window.bigId;
$(function(){
	window.joinMax = $("#joinCount").val();
	window.trueJoinMax = $("#trueJoinCount").val();
	window.bigId = $("#bigId").val();
	//接受报名
	$("#joinDIV").on("click",".accept",function(){
		var a = $(this).parent().find('.username').find('span');
		
		$.ajax({
			url:"joinerHandler",
			data:{
				userId:$(this).attr("id"),
				activityId:$(this).attr("name")
			},
			success:function(result){
				$("#trueJoin").empty();
				var array = $.parseJSON(result);
				a.text("  "+array[0].info);
				if(array[0].info.equals("接受成功")){
					a.css("color","green");
					//location.reload();
				}
				else
					a.css("color","red");
				array.splice(0,1);
		
				$.each(array,function(index,value){
					$("#trueJoin").append("<div class='applyitem'>"+"<img src='"+value.imgUrl+"' height='30px' width='30px' />"
							+"<div class='username'><a>"+value.name+"</a></div>"
							+"<br/></div>");
				})
			}
		})
	})
	//拒绝报名
	$("#joinDIV").on("click",".refuse",function(){
		var a = $(this).parent().find('.username').find('span');
		$.ajax({
			url:"refuseJoiner",
			data:{
				userId:$(this).attr("id"),
				activityId:$(this).attr("name")
			},
			success:function(result){
				a.text("  "+result);
				if(array[0].info.equals("拒绝成功"))
					a.css("color","green");
				else
					a.css("color","red");
			}
		})
	})
	//结束活动
	$("#stopActivity").click(function(){
		$.ajax({
			url:"stopActivity?activityId="+window.bigId,
			success:function(){
				$("#info03status").empty();
				$("#info03status").append("已结束");
			}
		})
	})
	//报名人员翻页

	$("#joinBeginPage").click(function(){
		if(window.joinIndex>0){
			window.joinIndex=0;
			console.log(window.joinIndex+" "+window.joinMax);
			getJoiner(window.joinIndex);
		}
	})
	$("#joinLastPage").click(function(){
		if(window.joinIndex>0){
			window.joinIndex-=3;
			console.log(window.joinIndex+" "+window.joinMax);
			getJoiner(window.joinIndex);
		}
	})
	$("#joinNextPage").click(function(){
		if(window.joinIndex+3<window.joinMax){
			window.joinIndex+=3;
			console.log(window.joinIndex+" "+window.joinMax);
			getJoiner(window.joinIndex);
		}
	})
	$("#joinEndPage").click(function(){
		if(window.joinIndex+3!=window.joinMax){
			window.joinIndex=Math.floor(window.joinMax/3)*3;
			if(window.joinIndex==window.joinMax){
				window.joinIndex-=3;
			}
			console.log(window.joinIndex+" "+window.joinMax);
			getJoiner(window.joinIndex);
		}
	})
	//实际参加人员翻页
	
	$("#trueJoinBeginPage").click(function(){
		if(window.trueJoinIndex>0){
			window.trueJoinIndex=0;
			console.log(window.trueJoinIndex+" "+window.trueJoinMax);
			getTrueJoiner(window.trueJoinIndex);
		}
	})
	$("#trueJoinLastPage").click(function(){
		if(window.trueJoinIndex>0){
			window.trueJoinIndex-=3;
			console.log(window.trueJoinIndex+" "+window.trueJoinMax);
			getTrueJoiner(window.trueJoinIndex);
		}
	})
	$("#trueJoinNextPage").click(function(){
		if(window.trueJoinIndex+3<window.trueJoinMax){
			window.trueJoinIndex+=3;
			console.log(window.trueJoinIndex+" "+window.trueJoinMax);
			getTrueJoiner(window.trueJoinIndex);
		}
	})
	$("#trueJoinEndPage").click(function(){
		if(window.trueJoinIndex+3!=window.trueJoinMax){
			window.trueJoinIndex=Math.floor(window.trueJoinMax/3)*3;
			if(window.trueJoinIndex==window.trueJoinMax){
				window.trueJoinIndex-=3;
			}
			console.log(window.trueJoinIndex+" "+window.trueJoinMax);
			getTrueJoiner(window.trueJoinIndex);
		}
	})
})

function getJoiner(index){
	$.ajax({
		url:"getJoinerByPage",
		data:{
			begin:index,
			activityId:$("#bigId").val()
		},
		success:function(result){
			var array = $.parseJSON(result);
			window.trueJoinMax = array[0];
			array.splice(0,1);
			$("#join").empty();
			$.each(array,function(index,value){
				$("#join").append("<div class='applyitem'>"+"<img src='"+value.imgUrl+"' height='30px' width='30px' />"
						+"<div class='username'><a href='lookOtherInfo?email="+value.email+"' target='_blank'>"+value.name+"</a><span style='color:red' id='refuAcptInfo'></span></div>"
						+"<a class='accept' id='"+value.userId+"' name='"+window.bigId+"'>接受</a>"
                        +"<a class='refuse' id='"+value.userId+"' name='"+window.bigId+"'>拒绝</a>"
						+"</div><br/>");
			})
		}
	})
}
function getTrueJoiner(index){
	$.ajax({
		url:"getTrueJoinerByPage",
		data:{
			begin:index,
			activityId:$("#bigId").val()
		},
		success:function(result){
			var array = $.parseJSON(result);
			window.trueJoinMax = array[0];
			array.splice(0,1);
			$("#trueJoin").empty();
			$.each(array,function(index,value){
				$("#trueJoin").append("<div class='applyitem'>"+"<img src='"+value.imgUrl+"' height='30px' width='30px' />"
						+"<div class='username'><a href='lookOtherInfo?email="+value.email+"' target='_blank' >"+value.name+"</a></div>"
						+"</div><br/>");
			})
		}
	})
}
