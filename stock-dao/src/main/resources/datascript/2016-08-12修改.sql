#将座位图关联到景区上
alter table stock_lock_record change column transaction_id transaction_id varchar(20) DEFAULT NULL COMMENT '交易id';

