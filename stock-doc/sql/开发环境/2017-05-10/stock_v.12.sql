# 只需在预发布环境执行，原sql已经改好，上线时执行原sql就行了。

ALTER TABLE `stock`.`prod_area`
CHANGE COLUMN `state` `state` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '状态值（1：正常；0：删除）' ;


ALTER TABLE `stock`.`prod_screeings`
CHANGE COLUMN `state` `state` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '状态值（1：正常；0：删除）' ;
