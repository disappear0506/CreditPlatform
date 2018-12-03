/**
 * Created by Administrator on 2016/5/12.
 */
$(function(){
    $("div.top>button#login").click(function(){
        window.location=("login.jsp");
    })
    $("div.top>button#register").click(function(){
        window.location=("register.jsp");
    })
    $("#phone").focus(function(){
        $("#phone").css("border-color","blue");
        $("div.center>div.information>span").text(" ");
    })
    $("#phone").focusout(function(){
        $("#phone").css("border-color","#EBEBEB");
    })
    $("#checkCode").focus(function(){
        $("#checkCode").css("border-color","blue");
        $("div.center>div.information>span").text(" ");
    })
    $("#checkCode").focusout(function(){
        $("#checkCode").css("border-color","#EBEBEB");
    })
    $("div.center>div.next>button").click(function(){
        if($("#phone").val().length==0 || $("#checkCode").val().length==0){
            $("div.center>div.information>span").text("请输入邮箱和验证码");
            $("div.center>div.information>span").css({"color":"red","font-size":"15px"});
            $("#phone").css("border-color","red");
            $("#checkCode").css("border-color","red");
        }
        else{
        	$("form").submit();
        }
    })
})