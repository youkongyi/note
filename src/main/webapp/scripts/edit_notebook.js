// edit_notebook.js 与笔记有关的代码

/*
 *  封装与笔记本有关的操作方法
 */

function loadNotebooksAction() {
	// 获取page数据
	var page = $(this).data('page');
	if (!page) {
		page = 0;
	}
	//请求地址
	var url='notebook/notebooks.do';
	//请求数据
	var data={userId:getCookie('userId'),
			page:page};
	//获取JSON对象
	$.getJSON(url, data, function(result){
		if(result.state==SUCCESS){
			var list=result.data;
			console.log(list); 
			model.updateNotebooks(list, page);
		}
	});

}

/*
 * <li class="online"> <a class='checked'> <i class="fa fa-book" title="online"
 * rel="tooltip-bottom"> </i> 默认笔记本</a></li>
 */

model.updateNotebooks=function(list, page){
	var template='<li class="online notebook">'+
		'<a>'+
		'<i class="fa fa-book " title="online" rel="tooltip-bottom"></i>'+
		'[notebook.name]</a></li>';
	if(! this.notebooks ){
		this.notebooks=list;
	}else{
		this.notebooks=
			this.notebooks.concat(list); 
	}
	//console.log(this);
	var ul=$('#notebooks').empty();
	for(var i=0;i<this.notebooks.length;i++){
		var notebook=this.notebooks[i];
		// id name
		var li=template.replace(
			'[notebook.name]', notebook.name);
		//在DOM对象上绑定数据 index
		li = $(li).data('index',i);
		ul.append(li);
	}
	var li = $('<li class="online more">'+
			'<a>More...</a></li>');
	li.data('page', page+1);
	ul.append(li);
};

function showAddNotebookDialog() {
	$('#can').load('alert/alert_notebook.jsp', function() {
		// 在 页面组建加载后执行
		$('#can .sure').click(addNotebookAction);
	});
	$('.opacity_bg').show();
}

function addNotebookAction() {
	var url = "notebook/add.do";
	var notebookName = $('#can #input_notebook').val();
	if (notebookName.replace(' ', '') == '') {
		return;
	}
	var data = {
		userId : getCookie('userId'),
		notebookName : notebookName
	};
	$.post(url, data, function(result) {
		if (result.state == SUCCESS) {
			var notebook = result.data;
			model.updataNotebook(notebook, true);
			closeDialog();
		} else {
			alert(result.message);
		}
	});
}

model.updataNotebook = function(notebook, newNotebook) {
	this.notebook = notebook;
	if (newNotebook) {
		this.notebookIndex = 0;
		this.notebooks.unshift({
			id : notebook.id,
			name : notebook.name
		});
		this.updateNotebooks();
	}
}
