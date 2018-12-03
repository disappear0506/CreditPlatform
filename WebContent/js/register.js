/**
 * Created by Administrator on 2016/5/11.
 */
$(function(){
    $("div.top>div.login>button").click(function() {
    	 window.location=("login.jsp");
    })
    $("#phone").focus(function(){
            $("#phone").css("border-color","blue");
            $("div.phone>span").text("");
        }
    )
    $("input#phone").focusout(function() {
        $("#phone").css("border-color", "#EBEBEB");
        if (!$("#phone").val().match(/^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/) && $("#phone").val() != "") {
            $("div.phone>span").text("请输入正确的邮箱");
            $("div.phone>span").css("font-size", "15px");
            $("#phone").css("border-color", "red");
            $("div.phone>span").css("color", "red");
        }
    })
    $("#psw").focus(function(){
        $("#psw").css("border-color","blue");
        $("div.psw>span").text(" ");
    })
    $("#psw").focusout(function(){
        $("#psw").css("border-color","#EBEBEB");
    })
    $("#submit").click(function(){
    	if ($("#phone").val().length == 0) {
            $("#phone").css("border-color", "red");
        }
    	if ($("#psw").val().length == 0) {
            $("#psw").css("border-color", "red");
        }
    	if(!$("div.checkBox>input").is(':checked')){
            $("div.checkBox>span").text("同意协议才能注册");
            $("div.checkBox>span").css("color", "red");
            $("div.checkBox>input").css("border-color","red");
            $("div.checkBox>span").css("font-size", "15px");
        }
    	if($("#phone").val().length != 0 && $("#psw").val().length != 0 && $("div.checkBox>input").is(':checked')){
    		$("form").submit();
    	}
    })
})

