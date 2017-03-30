/* 登录界面脚本程序 */
$(function() {
	//登录点击事件
	$('.btn').click(loginAction);
	//注册单击事件
	$('#regist_button').click(registAction);
	//焦点离开检验注册账户名有效性
	$('#regist_username').blur(checkRegistUsername);
	//焦点离开检验注册密码有效性
	$('#regist_password').blur(checkRegistPassword);
	//焦点离开再次检验密码有效性
	$('#final_password').blur(checkFinalPassword);
});

function registAction() {
	
	//检验用户名,密码,确认密码
	var pass = checkRegistUsername() + checkRegistPassword()
			+ checkFinalPassword();
	if (pass != 3) {
		return;
	}
	//请求地址
	var url = "user/regist.do";
	//用户名
	var name = $('#regist_username').val();
	//昵称
	var nick = $('#nickname').val();
	//密码
	var password = $('#regist_password').val();
	//确认密码
	var confirm = $('#final_password').val();
		//获取请求参数
	var data = {
		'name' : name,
		'nick' : nick,
		'password' : password,
		'confirm' : confirm
	};
   //发出Ajax请求
	$.post(url, data, function(result) {
		if (result.state == 0) {
			var user = result.data;
			$('#back').click();
			$('#count').val(user.name);
			$('#password').focus();
			return;
		} else if (result.state == 2) {
			$('#warning_1 span').html(result.message).parent().show();
			return;
		} else if (result.state == 3) {
			$('#warning_2 span').html(result.message).parent().show();
			return;
		} else {
			alert(result.message);
		}
	});

}

function checkRegistUsername() {
	var name = $('#regist_username').val();
	var reg = /^\w{3,10}$/;
	if (!reg.test(name)) {
		$('#warning_1 span').html("用户名不符合").parent().show();
		return false;
	}
	$('#warning_1').hide();
	return true;
}

function checkRegistPassword() {
	var password = $('#regist_password').val();
	var reg = /^\w{3,10}$/;
	if (!reg.test(password)) {
		$('#warning_2 span').html("密码不符合").parent().show();
		return false;
	}
	$('#warning_2').hide();
	return true;
}

function checkFinalPassword() {
	var confirm = $('#final_password').val();
	var password = $('#regist_password').val();
	if (confirm != password) {
		$('#warning_3 span').html("密码不一致").parent().show();
	}
	$('#warning_3').hide();
	return true;
}

function loginAction() {
	// 收集用户名和密码数据
	var name = $('.name').val();
	var password = $('.password').val();
	var paramter = {
		'name' : name,
		'password' : password
	};
	// 发送Ajax请求
	$.ajax({
		//请求页面
		url : 'user/login.do',
		//请求参数
		data : paramter,
		//返回的数据类型
		dataType : 'json',
		//请求类型
		type : 'POST',
		//指定函数
		success : function(result) {
			//返回值状态为0 则是正确返回,设置Cookie(保存UserId)并跳转页面 
			if (result.state == 0) {
				var user = result.data;
				setCookie('userId', user.id);
				location.href = 'edit.html';
				return;
				//返回值状态若为2 则返回帐号错误信息  直接return
			} else if (result.state == 2) {
				$('#prompt').html(result.message);
				return;
				//返回值状态若为3 则返回密码错误信息  直接return
			} else if (result.state == 3) {
				$('#prompt').html(result.message);
				return;
			}
			//其他状态则直接弹出报错信息
			alert(result.message);
		},
		 //严重错误时,页面错误信息
		error : function() {
			alert('Ajax请求失败');
		}
	});

};
//页面检验并提示帐号信息
function checkName() {
	//读取网页账户名
	var name = $('#passport').val();
	//检验帐号的有效性
	if (name == null || name == "") {
		// 提示错误
		$('#prompt').html('帐号不能为空');
		return false;
	}
	var reg = /^\w{3,10}$/;
	if (!reg.test(name)) {
		$('#prompt').html('帐号错误');
		return false;
	}
	$('#prompt').empty();
	return true;
}

