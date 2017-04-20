#产品库存规则关联表
ALTER table tmp_sku_rule_rel add state tinyint(2) unsigned DEFAULT 1 COMMENT '1 正常 2 停用 ';