/**
 * Created by Administrator on 2016/5/11.
 */
$(function(){
    $("div.top>div.login>button").click(function() {
    	 window.location=("login.jsp");
    })
    var phoneFocus=$("input#phone").focus(function(){
    	    $("div.phone>span").text("请输入中国大陆手机号，其他用户不可见");
            $("div.phone>span").css("font-size","15px");
            $("div.phone>input").css("border-color","blue");
            $("div.phone>span").css("color","#6D6D6D");
        }
    )
    var phoneRight=$("input#phone").focusout(function() {
        $("div.phone>span").text("");
        $("div.phone>input").css("border-color", "#EBEBEB");
        if (!$("div.phone>input").val().match(/^(?:13\d|15\d|18\d)\d{5}(\d{3}|\*{3})/) && $("div.phone>input").val() != "") {
            $("div.phone>span").text("请输入正确的手机号码");
            $("div.phone>span").css("font-size", "15px");
            $("div.phone>input").css("border-color", "red");
            $("div.phone>span").css("color", "red");
        }
    })
    var setText=function(){
        $("div.psw>.span1").text("长度为6-14个字符");
        $("div.psw>.span2").text("支持数字、大小写字母和标点符号");
        $("div.psw>.span3").text("不允许有空格");
    }
    var pswFocus=$("input#psw").focus(function(){
        setText();
        $("div.psw>span").css("font-size","15px");
        $("div.psw>input").css("border-color","blue");
        $("div.psw>span").css("color","#6D6D6D");
    })
    $("input#psw").focusout(function(){
        $("input#psw").css("border-color","#EBEBEB");
        $("div.psw>span").text(" ");
        if($("div.psw>input").val().indexOf(" ")>0 ){
            setText();
            $("div.center>div.psw>.span3").css("color","red");
            $("div.psw>input").css("border-color", "red");
        }
        if (  ($("div.psw>input").val().length>0 && $("div.psw>input").val().length<6) || $("div.phone>input").val().length>12 ) {
            $("div.psw>.span1").css("color","red");
            setText();
            $("div.psw>input").css("border-color", "red");
        }
    })
    var checkCodeFocus=$("div.center div.checkCode>input").focus(function(){
        $("div.center div.checkCode>input").css("border-color","blue");
        $("div.center div.checkCode>span").text(" ");
    })
    var checkCodeFocusOut=$("div.center div.checkCode>input").focusout(function(){
        $("div.center div.checkCode>input").css("border-color","#EBEBEB");
        $("div.center div.checkCode>span").text(" ");
    })
    if(!$("div.checkBox>input").attr('checked')){
        $("div.checkBox>span").text("同意协议才能注册");
        $("div.checkBox>span").css("color", "red");
        $("div.checkBox>input").css("border-color","red");
    }
    var register=$("#register").click(function() {
        if ($("div.center div.phone>input").val().length == 0) {
            $("div.center div.phone>input").css("border-color", "red");
            $("div.center div.phone>span").text("请输入中国大陆手机号，其他用户不可见");
            $("div.center div.phone>span").css({"font-size":"15px",color:"red"});
        }
        if ($("div.center div.psw>input").val().length == 0) {
            $("div.center div.psw>input").css("border-color", "red");
            $("div.center div.psw>.span1").text("请输入密码");
            $("div.center div.psw>.span1").css({"font-size":"15px",color:"red"});
        }
        if($("div.center div.checkCode>input").val().length==0){
            $("div.center div.checkCode>input").css("border-color", "red");
            $("div.center div.checkCode>span").text("请输入验证码");
            $("div.center div.checkCode>span").css({"font-size":"15px",color:"red"});
        }
        if($("#phone").val().length!=0 && $("#psw").val().length!=0){
        	$("form").submit();
        }
    })
})

