/**
 * Created by Administrator on 2016/5/20.
 */
$(function() {
	$("#fileField").text("");
	var public = $("#button").click(function() {
		if($("#place").val().length!=0 &&
		   $("#time").val().length!=0 &&
		   $("#textField").val().length!=0 &&
		   $("#name").val().length!=0 &&
		   $("#describe").val().length!=0){
			   $("form").submit();
		}
	    if($(".place input").val().length==0){
	    	$(".place span").text("   请填写活动地点");
	    	$(".place span").css("color","red");
	    	$(".place input").css("border-color","red");
	    }
	    if($(".time input").val().length==0){
	    	$(".time span").text("   请填写活动时间");
	    	$(".time span").css("color","red");
	    	$(".time input").css("border-color","red");
	    }
	    if($(".name input").val().length==0){
	    	$(".name span").text("   请填写活动名称");
	    	$(".name span").css("color","red");
	    	$(".name input").css("border-color","red");
	    }
	    if($("#describe").val().length==0){
	    	$(".describe span").text("   请填写活动描述");
	    	$(".describe span").css("color","red");
	    	$("#describe").css("border-color","red");
	    }
	    if($("#textField").val().length==0){
	    	$(".file-box span").text("请选图片");
	    	$(".file-box span").css("color","red");
	    	$("#textfield").css("border-color","red");
	    }
	})
	function previewImage(file) {
		var MAXWIDTH = 260;
		var MAXHEIGHT = 180;
		var div = document.getElementById('preview');
		if (file.files && file.files[0]) {
			var img = document.getElementById('imghead');
			img.onload = function() {
				var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT,
						img.offsetWidth, img.offsetHeight);
				img.width = rect.width;
				img.height = rect.height;
				img.style.marginTop = rect.top + 'px';
			}
			var reader = new FileReader();
			reader.onload = function(evt) {
				img.src = evt.target.result;
			}
			reader.readAsDataURL(file.files[0]);
		} else // 兼容IE
		{
			var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
			file.select();
			var src = document.selection.createRange().text;
			div.innerHTML = '<img id=imghead>';
			var img = document.getElementById('imghead');
			img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
			var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth,
					img.offsetHeight);
			status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width
					+ ',' + rect.height);
			div.innerHTML = "<div id=divhead style='width:" + rect.width
					+ "px;height:" + rect.height + "px;margin-top:" + rect.top
					+ "px;" + sFilter + src + "\"'></div>";
		}
	}
	function clacImgZoomParam(maxWidth, maxHeight, width, height) {
		var param = {
			top : 0,
			left : 0,
			width : width,
			height : height
		};
		if (width > maxWidth || height > maxHeight) {
			rateWidth = width / maxWidth;
			rateHeight = height / maxHeight;

			if (rateWidth > rateHeight) {
				param.width = maxWidth;
				param.height = Math.round(height / rateWidth);
			} else {
				param.width = Math.round(width / rateHeight);
				param.height = maxHeight;
			}
		}
		param.left = Math.round((maxWidth - param.width) / 2);
		param.top = Math.round((maxHeight - param.height) / 2);
		return param;
	}
	$("#fileField").change(function() {
		$("#textField").val($("#fileField").val());
		previewImage(this);
	})
    
	// 获得与失去焦点，边框变色
	$(".place input").focus(function(){
		$(".place input").css("border-color","blue");
		$(".place span").text("");
	})
	$(".place input").focusout(function(){
		$(".place input").css("border-color","#EBEBEB");
	})
	
	$(".time>input").focus(function(){
		$(".time>input").css("border-color","blue");
		$(".time>span").text("");
	})
	$(".time input").focusout(function(){
		$(".time input").css("border-color","#EBEBEB");
	})
	
	$(".file-box input").focus(function(){
		$("#textField").css("border-color","blue");
		$(".file-box span").text("");
	})
	$(".file-box input").focusout(function(){
		$("#textField").css("border-color","#EBEBEB");
	})
	
	$(".name input").focus(function(){
		$(".name input").css("border-color","blue");
		$(".name span").text("");
	})
	$(".name input").focusout(function(){
		$(".name input").css("border-color","#EBEBEB");
	})
	
	$("#describe").focus(function(){
		$("#describe").css("border-color","blue");
		$(".describe span").text("");
	})
	$("#describe").focusout(function(){
		$("#describe").css("border-color","#EBEBEB");
	})
})
