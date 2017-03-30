
select * from cn_note;

select
 cn_note_id as id, 
 cn_notebook_id as notebookId,
 cn_user_id as userId,
 cn_note_status_id as statusId,
 cn_note_type_id as typeId,
 cn_note_title as title,
 cn_note_body as body,
 cn_note_create_time as createTime,
 cn_note_last_modify_time as lastModifyTime
from
 cn_note
where
 cn_note_id= '12121' 
 
 select * from cn_note where cn_note_id='051538a6-0f8e-472c-8765-251a795bc88f';
 
 desc cn_note;
 
 desc cn_notebook;
 
 SELECT * FROM cn_notebook;
insert into cn_notebook
		( cn_user_id,
		  cn_notebook_id,
		  cn_notebook_type_id,
		  cn_notebook_name,
		  cn_notebook_desc,
		  cn_notebook_createtime
		)	
		values
		(	)

		
		