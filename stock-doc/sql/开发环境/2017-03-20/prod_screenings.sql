ALTER TABLE `stock`.`prod_screeings`
ADD COLUMN `name_abbr` VARCHAR(45) NULL DEFAULT '' COMMENT '场次简称' AFTER `name`,
CHANGE COLUMN `state` `state` TINYINT(2) UNSIGNED NULL DEFAULT '1' COMMENT '状态值（1：正常；0：删除）' AFTER `name_abbr`,
CHANGE COLUMN `supplier_id` `supplier_id` BIGINT(20) UNSIGNED NOT NULL DEFAULT '0' COMMENT '供应商id' AFTER `scenic_id`,
CHANGE COLUMN `scenic_id` `scenic_id` BIGINT(20) NOT NULL COMMENT '景区id（剧场id）';

ALTER TABLE `stock`.`prod_screeings`
CHANGE COLUMN `code` `code` VARCHAR(32) NULL default '' COMMENT '场次标识' ;