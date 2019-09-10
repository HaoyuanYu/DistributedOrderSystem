create table `product_category` (
    `category_id` int not null auto_increment,
    `category_name` varchar(64) not null comment '类目名字',
    `category_type` int not null comment '类目编号',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`category_id`),
    unique key `uqe_category_type` (`category_type`)
);
INSERT INTO `product_category` (`category_id`, `category_name`, `category_type`, `create_time`, `update_time`)
VALUES
	(1,'food',1,'2019-08-13 16:40:22','2019-08-26 23:39:36'),
	(2,'clothing',2,'2019-08-14 17:38:46','2019-08-26 23:39:40');product_category