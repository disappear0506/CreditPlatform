/**
 * Created by Administrator on 2016/5/12.
 */
$(function(){
    $("div.top>button#login").click(function(){
        window.location=("../login/login.html");
    })
    $("div.top>button#register").click(function(){
        window.location=("../register/register.html");
    })
    var setText=function(){
//        $("div.psw>.span1").text("长度为6-14个字符");
//        $("div.center>div.psw>.span2").text("支持数字、大小写字母和标点符号");
//        $("div.center>div.psw>.span3").text("不允许有空格");
    }
    $("div.center>div.psw>input").focus(function(){
        $("div.center>div.psw>input").css("border-color","blue");
        setText();
        $("div.center>div.psw>span").css({"font-size":"15px","color":"#6D6D6D"});
    })
    $("div.center>div.psw>input").focusout(function(){
        $("div.center>div.psw>input").css("border-color","#EBEBEB");
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
        if(!$("div.psw>input").val().match(/^[a-zA-Z0-9,.'"]*$/)){
            $("div.psw>.span2").css("color","red");
            setText();
            $("div.psw>input").css("border-color", "red");
        }
    })

    $("div.center>div.psw2>input").focus(function(){
        $("div.center>div.psw2>input").css("border-color","blue");
        $("div.center>div.psw2>span").text(" ");
    })
    $("div.center>div.psw2>input").focusout(function(){
        $("div.center>div.psw2>input").css("border-color","#EBEBEB");
    })
    $("div.center>div.next>button").click(function(){

        if($("div.center>div.psw>input").val().length==0){
//            $("div.center>div.psw>span").text("请输入密码");
            $("div.center>div.psw>span").css("color","red");
            $("div.center>div.psw>input").css("border-color","red");
        }
        if($("div.center>div.psw2>input").val().length==0){
//            $("div.center>div.psw2>span").text("请确认密码");
            $("div.center>div.psw2>span").css("color","red");
            $("div.center>div.psw2>input").css("border-color","red");
        }
    })

})