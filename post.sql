create table p_person(
	id int,
	name varchar(100),
	primary key(id)
)

insert  into p_person (
	id,name
)
values (1,'花花')

insert  into p_person (
	id,name
)
values (2,'Jerry')

create table p_comment(
	id int,
	content varchar(100),
	post_id int,
	primary key(id)
);

create table p_post(
	id int,
	content varchar(100),
	person_id int,
	primary key(id)
);


insert into p_post(id,content,person_id)
values (1,'今天下雪了',1)
insert into p_post(id,content,person_id)
values (2,'李鸿河老时:-)呵呵',1)
insert into p_comment(id,content,post_id)
values(1,'东城够',1)
insert into p_comment(id,content,post_id)
value(2,'比无码好',1)
insert into p_comment(id,content,post_id)
value(3,'喜欢下雪',1)
















