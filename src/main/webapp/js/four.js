/* 登录界面脚本程序 */
$(function() {
	//登录点击事件
	$('.btn').click(loginAction);
});

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

