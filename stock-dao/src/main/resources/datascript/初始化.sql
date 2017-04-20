
--获取表中最大的id
SELECT MAX(id) FROM prod_acting;
SELECT MAX(id) FROM prod_area;
SELECT MAX(id) FROM prod_screeings;
SELECT MAX(id) FROM prod_area_screeings;
SELECT MAX(id) FROM stock_seat_chart;


--插入演绎数据
INSERT INTO prod_acting
(id,scenic_id,supplier_id,state,name,whether_need_seat,create_time,update_time,remarks)
VALUES 
(1, 1234567, 1234567, 1, 'name', null, sysdate(), sysdate(), 'remarks');


--插入演绎区域数据
INSERT INTO prod_area 
(id,supplier_id,seat_chart_id,code,name,create_time,update_time)
VALUES 
(1, 1234567, null, 'code1', 'name1', sysdate(), sysdate());
INSERT INTO prod_area 
(id,supplier_id,seat_chart_id,code,name,create_time,update_time)
VALUES 
(2, 1234567, null, 'code2', 'name2', sysdate(), sysdate());


--插入演绎场次数据
INSERT INTO prod_screeings 
(id,code,name,supplier_id,start_time,end_time,end_sale_time,create_time,update_time)
VALUES 
(1, 'code1', 'name1', 1234567, 2030, 2230, null, sysdate(), sysdate());
INSERT INTO prod_screeings 
(id,code,name,supplier_id,start_time,end_time,end_sale_time,create_time,update_time)
VALUES 
(2, 'code2', 'name2', 1234567, 2030, 2230, null, sysdate(), sysdate());


--维护演绎 区域 场次的关系表 
INSERT INTO prod_area_screeings 
(id,acting_id,area_id,screeings_id,state,create_time,update_time)
VALUES 
(1, 1, 1, 1, 1, sysdate(), sysdate());
INSERT INTO prod_area_screeings 
(id,acting_id,area_id,screeings_id,state,create_time,update_time)
VALUES 
(2, 1, 1, 2, 1, sysdate(), sysdate());
INSERT INTO prod_area_screeings 
(id,acting_id,area_id,screeings_id,state,create_time,update_time)
VALUES 
(3, 1, 2, 1, 1, sysdate(), sysdate());
INSERT INTO prod_area_screeings 
(id,acting_id,area_id,screeings_id,state,create_time,update_time)
VALUES 
(4, 1, 2, 2, 1, sysdate(), sysdate());


--插入演绎座位图数据
INSERT INTO stock_seat_chart
(id,supplier_id,area_code,total_seats,seat_maps,create_time,update_time,remarks)
VALUES 
(1, 1234567, 'area_code', 10000, 'seat_maps', sysdate(), sysdate(), 'remarks');


--数据验证
SELECT * FROM prod_acting where id = 1;
SELECT * FROM prod_area where id in(1,2);
SELECT * FROM prod_screeings where id in(1,2);
SELECT * FROM prod_area_screeings where id in(1,2,3,4);
SELECT * FROM stock_seat_chart where id = 1;

--回滚数据
DELETE from prod_acting where id = 1;
DELETE from prod_area where id in(1,2) ;
DELETE from prod_screeings where id in(1,2);
DELETE from prod_area_screeings where id in (1,2,3,4);
DELETE from stock_seat_chart where id = 1;



