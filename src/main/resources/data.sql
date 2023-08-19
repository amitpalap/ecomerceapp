
delete from category;
delete from eproduct;
insert into category(id,name) values (1,'casual shoes');
insert into category(id,name) values (2,'sports shoes');
insert into category(id,name) values (3,'formal shoes');
insert into category(id,name) values (4,'sneakers shoes');
insert into category(id,name) values (5,'sandals and floaters');

insert into eproduct(id,name,price,date_added,category_id) values (11,'Us Polo Casual White', 1349,null,1);


insert into eproduct(id,name,price,date_added,category_id) values (2,'Puma Casual Black', 1799,null,1);

insert into eproduct(id,name,price,date_added,category_id) values (3,'Puma Men Running Shoes', 1799,null,2);

insert into eproduct(id,name,price,date_added,category_id) values (4,'Redtape walking shoes', 1699,null,2);

insert into eproduct(id,name,price,date_added,category_id) values (5,'Louis Philippe leather shoes', 1749,null,3);
insert into eproduct(id,name,price,date_added,category_id) values (6,'Van Heusen Leather formal ', 1504,null,3);
insert into eproduct(id,name,price,date_added,category_id) values (7,'Puma Casual Sneakers', 1799,null,4);
insert into eproduct(id,name,price,date_added,category_id) values (8,'Men Colourblocked sneakers', 1399,null,4);

insert into eproduct(id,name,price,date_added,category_id) values (9,'Crocs clogs sandal', 2921,null,5);


insert into eproduct(id,name,price,date_added,category_id) values (10,'Bata Men Leather comfort sandal', 1364,null,5);
