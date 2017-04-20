#修改库存座位图表，添加产品id
alter table stock_seat_rel add product_id bigint(20) DEFAULT NULL COMMENT '产品id';

#删除库存关联演艺中间表id
alter table stock drop column area_screeings_id;

#删除库存座位关联备注字段
alter table stock_seat_rel drop column remarks;