// edit_init.js 初始化
var SUCCESS = 0;

//当前页面中的数据模型
var model = {};

$(function() {
	//页面加载以后.立即加载笔记本列表
	loadNotebooksAction();
	//点击显示笔记事件
	$('#notebooks').on('click', 'li.notebook', showNotesAction);
	//点击显示下一页笔记本事件
	$('#notebooks').on('click', 'li.more', loadNotebooksAction);
	/*
	 * 绑定点击笔记本列表的事件
	 * showNotesAction函数定义在edit_note.js中
	 */

	//绑定笔记列表点击事件
	$('#notes').on('click', 'li', loadNoteAction);
	//保存按钮
	$('#save_note').click(saveNoteAction);
	//添加笔记按钮事件
	$('#add_note').click(showAddNoteDialog);
	//添加笔记本按钮事件
	$('#add_notebook').click(showAddNotebookDialog);
	//关闭所有菜单
	$('#can').on('click', '.close,.cancle', closeDialog);
	//弹出笔记可选菜单
	$('#notes').on('click', '.btn_slide_down', showNoteMenu);
	//弹出收藏选项
	$('#like_button').click(showLikeNotebook);
	//弹出移动笔记选项
	$('#notes').on('click', '.btn_move', showMove);
	//弹出笔记回收选项
	$('#notes').on('click', '.btn_delete', showDelete);
});

function showNoteMenu() {
	//	$('#notes .note_menu').hide();
	$(this).parent().next().toggle();
}

//弹出收藏选项
function showLikeNotebook() {
	$('#can').load('alert/alert_like.jsp');
}
//弹出笔记回收选项
function showDelete() {
	$('#can').load('alert/alert_delete_note.jsp');
}

//弹出移动笔记选项
function showMove() {
	$('#can').load('alert/alert_move.jsp');
}

function closeDialog() {
	$('#can').empty();
	$('.opacity_bg').hide();
}
