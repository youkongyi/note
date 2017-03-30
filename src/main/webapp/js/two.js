$(function(){
	$("input[name='search']").click(demo1);
	$("a").click(demo2);
	$("input[name='search']").blur(demo3);
});

function demo1(){
	
	$("input[name='search']").val("");
}

function demo2(){
	//弹出选择框
	var falg = confirm("是否搜索?");
	var search = $("input[name='search']").val();
	var url = "https://www.baidu.com/s?wd=" + search + "&cl=3";
	if(falg){
		return $("a").attr("href", url);
	} else {
		return falg;
	}
}
function demo3(){
	var text =$("input[name='search']").val();
	if(text.length == 0){
		$("input[name='search']").val("请输入要搜索的文字");
	}
}