$(function(){
	$(".img").mouseover(function(){
	$(this).css({"width":"420px","height":"420px","z-index":"4","display":"block"});
	});
	$(".img").mouseout(function(){
    $(this).css({"width":"400px","height":"400px","z-index":"0"});
  });
	
});