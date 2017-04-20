alter table stock_history change column status state tinyint(2) DEFAULT 1 COMMENT '1 正常 2 停用';

alter table stock_ordered_seat change column status state tinyint(2) DEFAULT 1 COMMENT '1 正常 2 停用';

alter table stock_rule change column status state tinyint(2) DEFAULT 1 COMMENT '1 正常 2 停用';

alter table stock_seat_rel change column status state tinyint(2) DEFAULT 1 COMMENT '1 正常 2 停用';