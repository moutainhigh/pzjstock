alter table stock add index u_stock_rule_time(rule_id , stock_time); 
alter table stock_lock_record add index u_lock_record_stock_tran_pro(stock_id , transaction_id, product_id); 
alter table stock_lock_record add index u_lock_record_stock_user(stock_id , operator_id); 
alter table stock_seat_rel add index u_stock_seat(stock_id , seat_num); 
alter table stock_seat_chart add index u_seat_chart_area_sort(prod_area_id , sort); 
alter table stock_seat add index u_seat_chart_seat(seat_chart_id , seat_num); 

drop table stock_history;
drop table stock_lock_record_history;

CREATE TABLE `stock_history` (
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

CREATE TABLE `stock_lock_record_history` (
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