alter table stock_seat drop index u_seat_chart_seat;

alter table stock_seat add column xpos int(6) not null default 0 comment '座位横坐标';
alter table stock_seat add column ypos int(6) not null default 0 comment '座位纵坐标';
alter table stock_seat add column area_id bigint(20) not null default 0 comment '座位对应区域id';
alter table stock_seat add column defined_xpos varchar(10) not null default '0' comment '自定义座位横坐标';
alter table stock_seat add column defined_ypos varchar(10) not null default '0' comment '自定义座位纵坐标';
alter table stock_seat add column xpos_name varchar(10) not null default '0' comment '自定义横坐标名称';
alter table stock_seat add column ypos_name varchar(10) not null default '0' comment '自定义纵坐标名称';