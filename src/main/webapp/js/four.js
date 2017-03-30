$(function(){
	$("#passport").blur(demo1);	
	$("[name=login]").click(demo2);
});

function demo1(){
	var regular = /^[a-zA-Z0-9_]{6,16}$/;
	var passport = $("#passport").val();
	$("#passport").css("border","1px solid #dedede");
	if(passport){
		if(!regular.exec(passport)){
			$("#passport").css("border","1px solid #ea6a6a");
			return false;
		}
	}
	return true;
}

function demo2(){
	var passport = $("#passport").val();
	var password = $("#password").val();
	if(demo1()){
		if((passport+password)!="123456123456"){
			$("#message").text("账号密码错误");
		}
	}
}












