/**
 * Created by Administrator on 2016/5/12.
 */
$(function(){
    var register=$("div.top>div.register>button").click(function(){
        window.location=("register.jsp");
    })
    var phoneFocus=$("#phone").focus(function(){
        $("#phone") .css("border-color","blue");
        $("div.information>span").text(" ");
    })
    var phoneFocusOut=$("#phone").focusout(function(){
        $("#phone") .css("border-color","#EBEBEB");
    })
    var pswFocus=$("#psw").focus(function(){
        $("#psw") .css("border-color","blue");
        $("div.information>span").text(" ");
    })
    var pswFocusOut=$("#psw").focusout(function(){
        $("#psw") .css("border-color","#EBEBEB");
    })
    var login=$("div.center>div.login>div.button>button").click(function(){
    	if($("#phone").val().length==0 && $("#psw").val().length==0){
            $("div.information>span").text("请填写账号和密码");
            $("div.information>span").css("color","red");
            $("div.information>span").css("font-size","15px");
            $("#phone").css("border-color","red");
            $("#psw").css("border-color","red");
        }
    	else if($("#phone").val().length==0){
            $("div.information>span").text("请填写账号");
            $("div.information>span").css("color","red");
            $("div.information>span").css("font-size","15px");
            $("#phone").css("border-color","red");
        }
        else if($("#psw").val().length==0){
            $("div.information>span").text("请填写密码");
            $("div.information>span").css("color","red");
            $("div.information>span").css("font-size","15px");
            $("#psw").css("border-color","red");
        }
        else{
        	$("form").submit();
        }
    })

})