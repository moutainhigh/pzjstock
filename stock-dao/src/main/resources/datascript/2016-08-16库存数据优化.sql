CREATE TABLE `stock` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '库存主键id',
  `rule_id` bigint(20) NOT NULL COMMENT '库存规则主键id',
  `state` tinyint(2) unsigned DEFAULT 1 COMMENT '1 正常 2 停用',
  `stock_time` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '库存时间',
  `total_num` int(10) NOT NULL COMMENT '库存总数',
  `used_num` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '已用库存数量',
  `remain_num` int(10) NOT NULL COMMENT '剩余库存数',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `stock_lock_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '锁库存记录主键id',
  `stock_id` bigint(20) NOT NULL COMMENT '库存主键id',
  `transaction_id` varchar(20) DEFAULT '0' COMMENT '交易id',
  `product_id` bigint(20) DEFAULT 0 COMMENT '产品id',
  `biz_type` tinyint(2) unsigned DEFAULT 0 COMMENT '操作库存业务场景',
  `lock_num` int(10) NOT NULL COMMENT '锁定库存数',
  `operator_id` bigint(20) DEFAULT 0 COMMENT '操作库存用户id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `stock_rule` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '库存规则主键id',
  `state` tinyint(2) unsigned DEFAULT 1 COMMENT '1 正常 2 停用',
  `name` varchar(200) NOT NULL COMMENT '库存规则名称',
  `category` int(10) NOT NULL COMMENT '库存类别',
  `type` tinyint(2) unsigned NOT NULL DEFAULT 1 COMMENT '库存类型 1、单一库存 2、每日库存',
  `upper_limit` int(10) NOT NULL COMMENT '库存上限',
  `supplier_id` bigint(20) NOT NULL COMMENT '供应商主键id',
  `whether_return` tinyint(2) unsigned DEFAULT 2 COMMENT '是否归还库存 1、是 2、否',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `stock_seat_rel` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '库存座位主键id',
  `stock_id` bigint(20) NOT NULL COMMENT '库存主键id',
  `state` tinyint(2) unsigned DEFAULT 1 COMMENT '1 正常 2 停用',
  `seat_num` varchar(20) NOT NULL COMMENT '座位号',
  `transaction_id` varchar(20) NOT NULL COMMENT '交易id',
  `product_id` bigint(20) DEFAULT 0 COMMENT '产品id',
  `operate_user_id` bigint(20) DEFAULT 0 COMMENT '占座用户id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `stock_seat_chart` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '座位图主键id',
  `scenic_id` bigint(20) NOT NULL COMMENT '景区主键id',
  `prod_area_id` bigint(20) NOT NULL COMMENT '区域主键id',
  `total_seats` int(10) NOT NULL COMMENT '总座位数',
  `seat_maps` text NOT NULL COMMENT '座位图',
  `sort` tinyint(4) unsigned DEFAULT 1 COMMENT '排序',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `stock_seat` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '座位表主键id',
  `seat_chart_id` bigint(20) NOT NULL COMMENT '座位图主键id',
  `seat_num` varchar(20) NOT NULL COMMENT '座位号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `stock_ordered_seat` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '预约座位主键id',
  `stock_id` bigint(20) NOT NULL COMMENT '库存主键id',
  `state` tinyint(2) unsigned DEFAULT 1 COMMENT '1 正常 2 停用',
  `reseller_id` bigint(20) DEFAULT 0 COMMENT '分销商',
  `travel_id` bigint(20) DEFAULT 0 COMMENT '旅行社',
  `travel_depart_id` bigint(20) DEFAULT 0 COMMENT '旅行社部门',
  `guide_id` bigint(20) DEFAULT 0 COMMENT '导游id',
  `operator_id` bigint(20) DEFAULT 0 COMMENT '操作者id',
  `guide_mobile` char(20) DEFAULT '0' COMMENT '导游手机号',
  `seat_number` varchar(1000) NOT NULL COMMENT '预约座位信息',
  `total_seat` int(10) NOT NULL COMMENT '总预约座位数',
  `is_team` tinyint(1) unsigned DEFAULT 1 COMMENT '是否团客占座：0，散客；1，团客',
  `appoint_name` varchar(250) DEFAULT '0' COMMENT '散客占座人姓名',
  `appoint_mobile` varchar(20) DEFAULT '0' COMMENT '散客占座人联系方式',
  `appoint_only_id` bigint(20) NOT NULL COMMENT '预约唯一id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `appoint_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '预约时间',
  `unvalid_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '失效时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


