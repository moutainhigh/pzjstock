#将座位图关联到景区上
ALTER table stock_seat_chart change column supplier_id  scenic_id bigint(20) NOT NULL COMMENT '景区主键id';

#座位图关联区域
ALTER table stock_seat_chart add prod_area_id bigint(20) NOT NULL  COMMENT '区域主键id';

#一个区域关联多座位图排序
ALTER table stock_seat_chart add sort int(5) DEFAULT 1 COMMENT '排序';

#将区域和座位图的关系从区域表去掉
ALTER table prod_area drop column seat_chart_id;

