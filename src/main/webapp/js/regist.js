$(function() {
	//注册单击事件
	$('#submit').click(registAction);
	//焦点离开检验注册账户名有效性
	$('#passport').blur(checkRegistUsername);
	//焦点离开检验注册密码有效性
	$('#password').blur(checkRegistPassword);
	//焦点离开再次检验密码有效性
	$('#passwordAgain').blur(checkFinalPassword);
});

function registAction() {
	//检验用户名,密码,确认密码
	var pass = checkRegistUsername() + checkRegistPassword()
			+ checkFinalPassword();
	var checkbox = $("input[type='checkbox']").is(':checked');
	if(!checkbox){
		$('#prompt').html("您还未接受协议");
		return;
	}
	if (pass != 3) {
		return;
	}
	//请求地址
	var url = "user/regist.do";
	//用户名
	var name = $('#passport').val();
	//昵称
	var nick = $('#nickName').val();
	//密码
	var password = $('#password').val();
	//确认密码
	var confirm = $('#passwordAgain').val();
	//QQ
	var qq = $('#qq').val();
	//获取请求参数
	var data = {
		'name' : name,
		'nick' : nick,
		'password' : password,
		'confirm' : confirm,
		'qq' : qq
	};
   //发出Ajax请求
	$.post(url, data, function(result) {
		if (result.state == 0) {
			var user = result.data;
			$('#login')[0].click();
			return;
		} else if (result.state == 2) {
			$('#prompt').html(result.message).parent().show();
			return;
		} else if (result.state == 3) {
			$('#prompt').html(result.message).parent().show();
			return;
		} else {
			alert(result.message);
		}
	});

}

function checkRegistUsername() {
	var name = $('#passport').val();
	var reg = /^\w{3,10}$/;
	if(name){
		if (!reg.test(name)) {
			$('#prompt').html("账号格式不对");
			return false; 
		}
	}
	$('#prompt').html("");
	return true;
}

function checkRegistPassword() {
	var password = $('#password').val();
	var reg = /^\w{3,10}$/;
	if(password){
		if (!reg.test(password)) {
			$('#prompt').html("密码格式不对");
			return false;
		}
	}
	$('#prompt').html("");	
	return true;
}

function checkFinalPassword() {
	var confirm = $('#passwordAgain').val();
	var password = $('#password').val();
	if (confirm != password) {
		$('#prompt').html("密码不一致");
		return false;
	}
	$('#prompt').html("");
	return true;
}


