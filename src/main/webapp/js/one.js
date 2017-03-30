//选择器
$(function(){
	//ID选择器
	$("#hello").click(demo1);
	//元素选择器
	$("div").click(demo2);
	//类选择器
	$(".first").click(demo3);
	//父元素下匹配所有的子元素
	$("div > p").click(demo4);
	// 匹配 父 元素之后的所有 同级 元素
	$("div ~ input").click(demo5);
	//匹配一个给定索引值的元素(下标从0开始)
	$("tr:eq(4) td img").hover(function(){
			$(this).css("width","180px");
			$(this).css("height","180px");
			
	},	//模拟鼠标悬停事件
	function(){
			$(this).css("width","160px");
			$(this).css("height","160px");
	});
	//匹配所有大于给定索引值的元素(下标从0开始)
	$("tr:gt(0)").click(demo6);
});

function demo1(){
	
	//dom对象转换成jquery对象
	// $(document.getElementById("hello")).html("nihao");
	
	//jquery对象转换成dom对象 
	// $("#hello").get(0).innerHTML="gehanbiao";
	
	//同一函数的set、get方法 
	// $("#hello").text("戈汉彪");
	// alert($("#hello").text());
	
	//增加事件
	 // $.extend({ 
	 // min: function(a, b){return a < b?a:b; },
	 // max: function(a, b){return a > b?a:b; }
	 // });
	 // alert($.min(300,200));
}

function demo2(){
	// $("div").click(function (){alert("你好")}); 
	// $("div").each(function(i){this.style.backgroundColor=['#ccc','#fff'][i%2]});
}

function demo3(){
	// $(".first").html("home");
}

function demo4(){
	//操作元素的样式  
	// $("div > p").css("background-color","blue");
}

function demo5(){
	//Input中取值val()
	var a = $("#note2").val();
	//Input赋值val(val)
	$("#note").val(a);
	// alert(a);
}

function demo6(){
	//用于绑定两个或多个事件处理器函数，以响应被选元素的轮流的 click 事件。
	//如果元素是可见的，切换为隐藏的；如果元素是隐藏的，切换为可见的
	$(".img").toggle("slow");
}






























































