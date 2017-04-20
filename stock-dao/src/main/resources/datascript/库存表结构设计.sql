
# 1、演艺表 prod_acting
create table prod_acting(
	id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '演艺主键id',
	scenic_id bigint(20) NOT NULL COMMENT '景区id',
	supplier_id bigint(20) NOT NULL COMMENT '供应商id',
	status tinyint(2) DEFAULT 1 COMMENT '1 正常 2 停用',
	name varchar(64) DEFAULT NULL COMMENT '演艺名称',
	whether_need_seat tinyint(2) DEFAULT 2 COMMENT '1 需要选座 2 不需要选座',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time timestamp COMMENT '修改时间',
	remarks varchar(2000) DEFAULT NULL COMMENT '演艺备注',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 2、区域表 prod_area
create table prod_area(
	id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '区域主键id',
	supplier_id bigint(20) DEFAULT NULL COMMENT '供应商主键id',
	stock_seat_chart_id bigint(20) NOT NULL COMMENT '座位图id',
	code varchar(32) DEFAULT NULL COMMENT '区域标识',
	name varchar(200) NOT NULL COMMENT '区域名称',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time timestamp COMMENT '修改时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 3、场次表 prod_screeings
create table prod_screeings(
	id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '场次主键id',
	code varchar(32) NOT NULL COMMENT '场次标识',
	name varchar(200) DEFAULT NULL COMMENT '场次名称',
	supplier_id bigint(20) NOT NULL COMMENT '供应商主键id',
	start_time int(10) NOT NULL COMMENT '演出开始时间',
	end_time int(10) NOT NULL COMMENT '演出结束时间',
	end_sale_time int(10) NOT NULL COMMENT '演出停售时间',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time timestamp COMMENT '修改时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 4、区域场次关联表 prod_area_screeings
create table prod_area_screeings(
	id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '区域场次关联主键id',
	prod_acting_id bigint(20) NOT NULL COMMENT '演艺主键id',
	prod_area_id bigint(20) NOT NULL COMMENT '区域主键id',
	prod_screeings_id bigint(20) NOT NULL COMMENT '场次主键id',
	status tinyint(2) DEFAULT 1 COMMENT '1 正常 2 停用',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time timestamp COMMENT '修改时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#----------------------以下是库存系统需要的表，上面四张表未来挪到产品里面维护--------------------------------

# 5、座位图表 stock_seat_chart
create table stock_seat_chart(
	id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '座位图主键id',
	supplier_id bigint(20) NOT NULL COMMENT '供应商主键id',
	area_code varchar(32) DEFAULT NULL COMMENT '区域标识',
	total_seats int(10) NOT NULL COMMENT '总座位数',
	seat_maps text NOT NULL COMMENT '座位图',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time timestamp COMMENT '修改时间',
	remarks varchar(2000) DEFAULT NULL COMMENT '座位图备注',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 6、座位表 stock_seat
create table stock_seat(
	id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '座位表主键id',
	stock_seat_chart_id bigint(20) NOT NULL COMMENT '座位图主键id',
	seat_num varchar(32) DEFAULT NULL COMMENT '座位号',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


# 7、库存规则表 stock_rule
create table stock_rule(
	id varchar(20) NOT NULL COMMENT '库存规则主键id',
	status tinyint(2) DEFAULT 1 COMMENT '1 正常 2 停用',
	name varchar(200) NOT NULL COMMENT '库存规则名称',	
	category int(10) NOT NULL COMMENT '库存类别',
	type tinyint(2) NOT NULL COMMENT '库存类型 1、单一库存 2、每日库存',
	upper_limit int(10) NOT NULL COMMENT '库存上限',
	supplier_id bigint(20) NOT NULL COMMENT '供应商主键id',
	whether_return tinyint(2) DEFAULT NULL COMMENT '是否归还库存 1、是 2、否',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time timestamp COMMENT '修改时间',
	remarks varchar(2000) DEFAULT NULL COMMENT '库存规则备注',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 8、库存表 stock
create table stock(
	id varchar(20) NOT NULL COMMENT '库存主键id',
	stock_rule_id bigint(20) NOT NULL COMMENT '库存规则主键id',
	stock_area_screeings_id bigint(20) DEFAULT NULL COMMENT '区域场次关联主键id',
	status tinyint(2) DEFAULT 1 COMMENT '1 正常 2 停用',
	stock_time int(10) NOT NULL COMMENT '库存时间',
	total_num int(10) NOT NULL COMMENT '库存总数',
	used_num int(10) DEFAULT NULL COMMENT '使用的库存数',
	left_num int(10) DEFAULT NULL COMMENT '剩余库存数',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time timestamp COMMENT '修改时间',
	remarks varchar(2000) DEFAULT NULL COMMENT '日库存备注',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 9、锁库存记录表 stock_lock_record
create table stock_lock_record(
	id varchar(20) NOT NULL COMMENT '锁库存记录主键id',
	stock_id bigint(20) NOT NULL COMMENT '库存主键id',
	transaction_id bigint(20) DEFAULT NULL COMMENT '交易唯一id',
	product_id bigint(20) DEFAULT NULL COMMENT '产品id',
	bussiness_type int(10) DEFAULT NULL COMMENT '操作库存业务场景',
	lock_num int(10) NOT NULL COMMENT '锁定库存数',
	operate_user_id bigint(20) DEFAULT NULL COMMENT '操作库存用户id',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time timestamp COMMENT '修改时间',
	remarks varchar(2000) DEFAULT NULL COMMENT '锁库存记录备注',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 10、库存座位关联表 stock_seat_rel
create table stock_seat_rel(
	id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '库存座位主键id',
	stock_id bigint(20) NOT NULL COMMENT '库存主键id',
	status tinyint(2) DEFAULT 1 COMMENT '1 正常 2 取消',
	seat_num varchar(32) DEFAULT NULL COMMENT '座位号',
	transaction_id varchar(20) DEFAULT NULL COMMENT '交易唯一id或预约座位主键id',
	operate_user_id bigint(20) DEFAULT NULL COMMENT '占座用户id',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time timestamp COMMENT '修改时间',
	remarks varchar(2000) DEFAULT NULL COMMENT '库存座位备注',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 11、库存历史存储表 stock_history
create table stock_history(
	id varchar(20) NOT NULL COMMENT '库存历史存储主键id',
	stock_rule_id bigint(20) NOT NULL COMMENT '库存规则主键id',
	stock_area_screeings_id bigint(20) DEFAULT NULL COMMENT '区域场次关联主键id',
	status tinyint(2) DEFAULT 1 COMMENT '1 正常 2 停用',
	stock_time int(10) NOT NULL COMMENT '库存时间',
	total_num int(10) NOT NULL COMMENT '库存总数',
	used_num int(10) DEFAULT NULL COMMENT '使用的库存数',
	left_num int(10) DEFAULT NULL COMMENT '剩余库存数',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time timestamp COMMENT '修改时间',
	remarks varchar(2000) DEFAULT NULL COMMENT '日库存备注',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 12、锁库存记录历史存储表 stock_lock_record_history
create table stock_lock_record_history(
	id varchar(20) NOT NULL COMMENT '锁库存历史存储主键id',
	stock_id bigint(20) NOT NULL COMMENT '库存主键id',
	transaction_id bigint(20) DEFAULT NULL COMMENT '交易唯一id',
	product_id bigint(20) DEFAULT NULL COMMENT '产品id',
	bussiness_type int(10) DEFAULT NULL COMMENT '操作库存业务场景',
	lock_num int(10) NOT NULL COMMENT '锁定库存数',
	operate_user_id bigint(20) DEFAULT NULL COMMENT '操作库存用户id',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time timestamp COMMENT '修改时间',
	remarks varchar(2000) DEFAULT NULL COMMENT '锁库存记录备注',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 13、预约座位表 stock_ordered_seat
create table stock_ordered_seat(
	id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '预约座位主键id',
	stock_id bigint(20) NOT NULL COMMENT '库存主键id',
	status tinyint(2) DEFAULT 1 COMMENT '1 正常 2 停用',
	reseller_id bigint(20) DEFAULT NULL COMMENT '地接社ID',
	sec_reseller_id bigint(20) DEFAULT NULL COMMENT '地接社部门ID',
	guide_id bigint(20) DEFAULT NULL COMMENT '导游id',
	operator_id bigint(20) DEFAULT NULL COMMENT '操作者id',
	guide_mobile varchar(16) DEFAULT NULL COMMENT '导游手机号',
	seat_number varchar(1000) DEFAULT NULL COMMENT '预约座位信息',
	total_seat int(10) DEFAULT NULL COMMENT '总预约座位数',
	is_team int(1) DEFAULT 1 COMMENT '是否团客占座：0，散客；1，团客',
	appoint_name varchar(255) DEFAULT NULL COMMENT '散客占座人姓名',
	appoint_mobile varchar(16) DEFAULT NULL COMMENT '散客占座人联系方式',
	appoint_only_id bigint(20) NOT NULL COMMENT '预约唯一id',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	appoint_time timestamp NOT NULL COMMENT '预约时间',
	unvalid_time timestamp NOT NULL COMMENT '失效时间',   
	update_time timestamp COMMENT '修改时间',
	remarks varchar(2000) DEFAULT NULL COMMENT '预约座位备注',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;