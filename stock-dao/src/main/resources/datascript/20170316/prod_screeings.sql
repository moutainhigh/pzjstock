alter table prod_screeings add state tinyint(2) unsigned DEFAULT '1' COMMENT '1 正常 0 禁用';
alter table prod_screeings add supplier_id bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '供应商id';