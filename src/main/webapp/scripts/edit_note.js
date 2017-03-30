/*
 *	edit_note.js
 * 封装全部与笔记有关的全本 
 */
function showNotesAction(){
	//this 是 li 元素
	//获取选中元素的序号,此序号时在显示
	//笔记本列时候绑定的.
	var li = $(this);
//	console.log(li);
	var index = li.data('index');
//	console.log(index);
	//处理视觉效果
	li.parent().find('a').removeClass('checked');
	li.find('a').addClass('checked');
	//让模型显示当前笔记本的全部笔记
	model.showNotes(index);
}

//显示选定笔记本的全部笔记
model.showNotes=function(notebookIndex){
//	console.log(notebookIndex)
	//保存选定的笔记本序号到model
	this.notebookIndex = notebookIndex;
	
	//找到当前笔记本信息
	var notebook = this.notebooks[notebookIndex];
	var url="note/list.do";
	var data = {'notebookId':notebook.id };
//	console.log(data);
	//向服务器泛起Ajax请求获取笔记列表
	$.getJSON(url,data,function(result){
//		console.log(result.state);
		if(result.state==SUCCESS){
			//更新笔记列表
//			console.log(result.data);
			model.updataNotes(result.data);
		}
	});
}

model.updataNotes = function(notes){
	var template = '<li class="online">'+
					'<a><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>[note.title]<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>'+
					'</a><div class="note_menu" tabindex="-1">'+
					'<dl><dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>'+
					'<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>'+
					'<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>'+
					'</dl></div></li>';
	if(notes){
		this.notes = notes;
	}
//	console.log(123);
	var ul = $('#notes').empty();
	for(var i=0;i<this.notes.length;i++){
		var note = this.notes[i];
//		console.log(notebook);
		var li = template.replace("[note.title]",note.title);
		//在DOM对象上绑定数据index
		li = $(li).data('index',i);
		if(i == this.noteIndex){
			li.children('a').addClass('checked');
		}
		ul.append(li);
	}
}


function loadNoteAction(){
	var li = $(this);
//	console.log(li);
	li.parent().find('a').removeClass('checked');
	li.find('a').addClass('checked');
	var index = li.data('index');
	model.loadNote(index);
}

model.loadNote = function(index){
	//model中记录当前选定笔记的位置
	//在更新title时候,利用这个序号找到笔记的位置
	this.noteIndex = index;
	var url = 'note/load.do';
	var data = {id:model.notes[index].id };
	$.getJSON(url,data,function(result){
//		console.log(result.state);
		if(result.state==SUCCESS){
			//更新笔记列表
			model.updataNote(result.data);
		}else{
			alert(result.message);
		}
	});
	
}

//将note对象显示到编辑区域
model.updataNote = function(note,newNote){
	this.note = note;
	$('#input_note_title').val(this.note.title);
	um.setContent(note.body);
	if(newNote){
		 this.noteIndex=0;
		 this.notes.unshift({id:note.id,title:note.title});
		 this.updataNotes();
	}
}

//处理保存按钮事件
function saveNoteAction(){
	var title = $('#input_note_title').val();
	var body = um.getContent();
	var id = model.note.id;
	//title不允许为空
	if(title.replace(" ","")==""){
		title = model.note.title;
		$('#input_note_title').val(title);
	}
	if(title==model.note.title && body==model.note.body){
		return ;
	}
	
	var url = "note/save.do";
	var data = {'id':id,'title':title,'body':body};
	$.post(url,data,function(result){
		if(result.state==SUCCESS){
			if(result.data==false){
				alert("更新失败");
				return ; 
			}
			console.log("OK");
//			var index = $('#notebooks .checked').parent().data('index');
//			model.showNotes(index);
			model.updateTitle(title);
		}else{
			alert(result.message);
		}
	});
}

model.updateTitle = function(title){
	var index = this.noteIndex;
//	console.log(index);
//	console.log(title);
	this.notes[index].title = title;
	this.updataNotes();
}


/*
 * 响应添加笔记按钮事件,
 * 打开添加笔记窗口.
 */
function showAddNoteDialog(){
	$('#can').load('alert/alert_note.jsp',function(){
		//在 页面组建加载后执行
		$('#can .sure').click(addNoteAction);
	});
	$('.opacity_bg').show();
}

//处理点击创建笔记按钮
function addNoteAction(){
	var url = "note/add.do";
	var notebookId=model.notebooks[model.notebookIndex].id;
	var title = $('#can #input_note').val();
	if(title.replace(' ','')==''){
		return ;
	}
	var data = {userId:getCookie('userId'),notebookId:notebookId,title:title};
	$.post(url,data,function(result){
		if(result.state==SUCCESS){
			var note = result.data;
//			console.log(note);
			model.updataNote(note,true);
			closeDialog();
		}else{
			alert(result.message);
		}
	});
}















