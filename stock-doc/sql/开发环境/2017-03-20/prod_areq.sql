ALTER TABLE `stock`.`prod_area`
ADD COLUMN `supplier_id` BIGINT(20) NULL COMMENT '供应商ID' AFTER `scenic_id`,
CHANGE COLUMN `name` `name` VARCHAR(200) NOT NULL COMMENT '区域名称' AFTER `supplier_id`,
ADD COLUMN `state` TINYINT(1) NULL DEFAULT 1 COMMENT '状态值（1：正常；0：删除）' AFTER `code`,
ADD COLUMN `seat_type` TINYINT(1) NULL DEFAULT 1 COMMENT '座位类型（1：无座位；2：有座位，只支持矩形）' AFTER `state`,
ADD COLUMN `seat_mode` TINYINT(1) NULL DEFAULT 1 COMMENT '座位模式（1：用户自选；2：后台配座；3：系统分配）' AFTER `seat_type`,
ADD COLUMN `thumb` VARCHAR(50) NULL COMMENT '缩略图' AFTER `seat_mode`;