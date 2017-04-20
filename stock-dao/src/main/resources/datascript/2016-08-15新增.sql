-- ----------------------------
-- Table structure for `sku_rule_rel`
-- 同步产品表中 产品与库存规则绑定关系
-- ----------------------------
DROP TABLE IF EXISTS `sku_rule_rel`;
CREATE TABLE `sku_rule_rel` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '关系表ID',
  `pro_id` bigint(20) unsigned NOT NULL COMMENT '产品ID',
  `rule_id` bigint(20) unsigned NOT NULL COMMENT '规则ID',
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=1 ENGINE=InnoDB DEFAULT CHARSET=utf8;