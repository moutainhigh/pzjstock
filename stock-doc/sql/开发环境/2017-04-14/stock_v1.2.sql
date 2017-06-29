CREATE TABLE `assigned_order` (
  `assigned_id` bigint(20) NOT NULL COMMENT '主键id',
  `screening_id` bigint(20) NOT NULL COMMENT '场次id',
  `area_id` bigint(20) NOT NULL COMMENT '区域id',
  `transaction_id` varchar(20) NOT NULL COMMENT '交易id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `state` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态 1.	待分配 2.分配完成',
  `travel_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '游玩日期',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `un_occupied_num` int(3) NOT NULL DEFAULT '0' COMMENT '待分配数量(总数量)',
  `occupied_num` int(3) NOT NULL DEFAULT '0' COMMENT '已分配数量',
  `spu_id` bigint(20) DEFAULT NULL COMMENT '产品id',
  PRIMARY KEY (`assigned_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='待分配列表';


CREATE TABLE `seat_char` (
  `seat_id` bigint(20) NOT NULL COMMENT '座位id',
  `scenic_id` bigint(20) NOT NULL COMMENT '剧场id',
  `seat_unique` bigint(20) NOT NULL COMMENT '座位唯一索引',
  `area_id` bigint(20) NOT NULL COMMENT '区域id',
  `abscissa` int(11) NOT NULL COMMENT '横坐标',
  `ordinate` int(11) NOT NULL COMMENT '纵坐标',
  `name_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '名称类型（0：默认名称；1：自定义名称）',
  `line_name` varchar(45) NOT NULL COMMENT '自定义 的行名称',
  `column_name` varchar(45) NOT NULL COMMENT '自定义的列名称',
  `type` tinyint(1) NOT NULL COMMENT '类型（1:是座位、2:不是座位、3:删除）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`seat_id`),
  UNIQUE KEY `UX_seat_char_unique` (`seat_unique`) COMMENT '座位唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='座位图';



CREATE TABLE `seat_record` (
  `record_id` bigint(20) NOT NULL COMMENT '主键id',
  `record_unique` bigint(20) NOT NULL COMMENT '占座记录唯一值,拥有唯一索引,保证不插入重复的占座记录',
  `transaction_id` varchar(20) NOT NULL DEFAULT '0' COMMENT '交易id',
  `screening_id` bigint(20) NOT NULL COMMENT '场次id',
  `area_id` bigint(20) NOT NULL COMMENT '区域id',
  `seat_id` bigint(20) NOT NULL COMMENT '座位id',
  `seat_name` varchar(20) NOT NULL COMMENT '座位名称',
  `operator_id` bigint(20) NOT NULL COMMENT '占座用户id',
  `category` tinyint(2) NOT NULL COMMENT '类别（10：待选；20：已占；30：锁定；40：预选）	',
  `state` tinyint(1) NOT NULL DEFAULT '1' COMMENT '记录是否有效（0：无效；1：有效）',
  `travel_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '游玩日期',
  `expiration_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  PRIMARY KEY (`record_id`),
  UNIQUE KEY `record_unique_UNIQUE` (`record_unique`) COMMENT '占座记录唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='座位记录';



CREATE TABLE `seat_scale` (
  `scale_id` bigint(20) NOT NULL,
  `type` tinyint(1) NOT NULL COMMENT '类型（0：x；1：y）',
  `position` int(11) NOT NULL COMMENT '位置',
  `scenic_id` bigint(20) NOT NULL COMMENT '剧场id',
  `supplier_id` bigint(20) NOT NULL COMMENT '供应商id',
  `name` varchar(5) NOT NULL COMMENT '名称',
  PRIMARY KEY (`scale_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='座位图标尺';



ALTER TABLE `stock`.`prod_area`

ADD COLUMN `supplier_id` BIGINT(20) NULL COMMENT '供应商ID' AFTER `scenic_id`,
CHANGE COLUMN `name` `name` VARCHAR(200) NOT NULL COMMENT '区域名称' AFTER `supplier_id`,
ADD COLUMN `state` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '状态值（1：正常；0：删除）' AFTER `code`,
ADD COLUMN `seat_type` TINYINT(1) NULL DEFAULT 1 COMMENT '座位类型（1：无座位；2：有座位，只支持矩形）' AFTER `state`,
ADD COLUMN `seat_mode` TINYINT(1) NULL DEFAULT 1 COMMENT '座位模式（1：用户自选；2：后台配座；3：系统分配）' AFTER `seat_type`,
ADD COLUMN `thumb` VARCHAR(50) NULL COMMENT '缩略图' AFTER `seat_mode`;




ALTER TABLE `stock`.`prod_screeings`
add COLUMN `state` TINYINT(2) UNSIGNED NOT NULL DEFAULT '1' COMMENT '状态值（1：正常；0：删除）',
add COLUMN  `supplier_id` BIGINT(20) UNSIGNED NOT NULL DEFAULT '0' COMMENT '供应商id' AFTER `scenic_id`,
CHANGE COLUMN  `scenic_id`  `scenic_id` BIGINT(20) NOT NULL COMMENT '景区id（剧场id）';

ALTER TABLE `stock`.`prod_screeings`
CHANGE COLUMN `code` `code` VARCHAR(32) NULL default '' COMMENT '场次标识' ;

CREATE TABLE `theater_info` (
  `theater_id` bigint(20) NOT NULL COMMENT '剧场id',
  `line_num` int(5) NOT NULL DEFAULT '0' COMMENT '行数量',
  `column_num` int(5) NOT NULL DEFAULT '0' COMMENT '列数量',
  `sort_type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '座位排序类型 1.正序 2.奇数左 3.奇数右',
  `seat_state` tinyint(1) NOT NULL DEFAULT '1' COMMENT '座位状态1.可售 3.锁定',
  PRIMARY KEY (`theater_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='剧场信息';
