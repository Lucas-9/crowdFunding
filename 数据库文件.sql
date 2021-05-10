drop database if exists crowdfunding;
create database crowdfunding DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
use crowdfunding;
create table `admin`
(
    `id`                   int not null auto_increment,
    `login_account`        varchar(255) not null unique,
    `user_password`        char(32) not null,
    `user_name`            varchar(255) not null,
    `email`                varchar(255) not null,
    `create_time`          char(19),
    primary key (`id`)
);
insert into admin(`id`, `login_account`, `user_password`,`user_name`, `email`, `create_time`) values (1, 'lucas', '123456', 'lucas', 'lucas@qq.com', null);

create table `role` (
                        `id`        int (11) not null auto_increment,
                        `name`      varchar (255) not null unique,
                        primary key (`id`)
);
insert into role(name) values ('董事长'), ('员工');

create table `menu` (
                        `id`    int (11) not null auto_increment,
                        `pid`   int (11),
                        `name`  varchar (200),
                        `url`   varchar (200),
                        `icon`  varchar (200),
                        primary key (`id`)
);


insert into `menu` (`id`, `pid`, `name`, `icon`, `url`) values('1',NULL,'系统权限菜单','glyphicon glyphicon-th-list',NULL);
insert into `menu` (`id`, `pid`, `name`, `icon`, `url`) values('2','1','控制面板','glyphicon glyphicon-dashboard','main.htm');
insert into `menu` (`id`, `pid`, `name`, `icon`, `url`) values('3','1','权限管理','glyphicon glyphicon glyphicon-tasks',NULL);
insert into `menu` (`id`, `pid`, `name`, `icon`, `url`) values('4','3','用户维护','glyphicon glyphicon-user','user/index.htm');
insert into `menu` (`id`, `pid`, `name`, `icon`, `url`) values('5','3','角色维护','glyphicon glyphicon-king','role/index.htm');
insert into `menu` (`id`, `pid`, `name`, `icon`, `url`) values('6','3','许可维护','glyphicon glyphicon-lock','permission/index.htm');
insert into `menu` (`id`, `pid`, `name`, `icon`, `url`) values('7','1','业务审核','glyphicon glyphicon-ok',NULL);
insert into `menu` (`id`, `pid`, `name`, `icon`, `url`) values('8','7','实名认证审核','glyphicon glyphicon-check','auth_cert/index.htm');
insert into `menu` (`id`, `pid`, `name`, `icon`, `url`) values('9','7','广告审核','glyphicon glyphicon-check','auth_adv/index.htm');
insert into `menu` (`id`, `pid`, `name`, `icon`, `url`) values('10','7','项目审核','glyphicon glyphicon-check','auth_project/index.htm');
insert into `menu` (`id`, `pid`, `name`, `icon`, `url`) values('11','1','业务管理','glyphicon glyphicon-th-large',NULL);
insert into `menu` (`id`, `pid`, `name`, `icon`, `url`) values('12','11','资质维护','glyphicon glyphicon-picture','cert/index.htm');
insert into `menu` (`id`, `pid`, `name`, `icon`, `url`) values('13','11','分类管理','glyphicon glyphicon-equalizer','certtype/index.htm');
insert into `menu` (`id`, `pid`, `name`, `icon`, `url`) values('14','11','流程管理','glyphicon glyphicon-random','process/index.htm');
insert into `menu` (`id`, `pid`, `name`, `icon`, `url`) values('15','11','广告管理','glyphicon glyphicon-hdd','advert/index.htm');
insert into `menu` (`id`, `pid`, `name`, `icon`, `url`) values('16','11','消息模板','glyphicon glyphicon-comment','message/index.htm');
insert into `menu` (`id`, `pid`, `name`, `icon`, `url`) values('17','11','项目分类','glyphicon glyphicon-list','projectType/index.htm');
insert into `menu` (`id`, `pid`, `name`, `icon`, `url`) values('18','11','项目标签','glyphicon glyphicon-tags','tag/index.htm');
insert into `menu` (`id`, `pid`, `name`, `icon`, `url`) values('19','1','参数管理','glyphicon glyphicon-list-alt','param/index.htm');

create table `inner_admin_role` (
                                    `id` int (11) not null auto_increment,
                                    `admin_id` int (11),
                                    `role_id` int (11),
                                    primary key (`id`)
);

create table `auth` (
                        `id` int(11) not null auto_increment,
                        `name` varchar(200) default null,
                        `title` varchar(200) default null,
                        `category_id` int(11) default null,
                        primary key (`id`)
);
insert into auth(id,`name`,title,category_id) values(1,'','用户模块',null);
insert into auth(id,`name`,title,category_id) values(2,'user:delete','删除',1);
insert into auth(id,`name`,title,category_id) values(3,'user:get','查询',1);
insert into auth(id,`name`,title,category_id) values(4,'','角色模块',null);
insert into auth(id,`name`,title,category_id) values(5,'role:delete','删除',4);
insert into auth(id,`name`,title,category_id) values(6,'role:get','查询',4);
insert into auth(id,`name`,title,category_id) values(7,'role:add','新增',4);


create table `inner_role_auth` (
                                   `id` int (11) not null auto_increment,
                                   `role_id` int(11) default null,
                                   `auth_id` int(11) default null,
                                   primary key (`id`)
);