CREATE DATABASE `activity`;

USE `activity`;

CREATE TABLE `t_lottery_config` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '活动名称',
  `start_date` date DEFAULT NULL COMMENT '开始时间',
  `end_date` date DEFAULT NULL COMMENT '结束时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='抽奖活动配置表';

INSERT INTO `activity`.`t_lottery_config` (`id`, `name`, `start_date`, `end_date`, `gmt_modified`, `gmt_create`) VALUES ('1', '新年抽奖活动', '2018-02-23', '2018-02-24', '2018-02-23 10:48:14', '2018-02-23 10:48:20');


CREATE TABLE `t_lottery_number` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL COMMENT '用户id',
  `count` int(10) unsigned NOT NULL COMMENT '可抽次数',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='抽奖次数';

INSERT INTO `activity`.`t_lottery_number` (`id`, `user_id`, `count`, `gmt_modified`, `gmt_create`) VALUES ('1', '1', '20', '2018-02-23 10:48:59', '2018-02-23 10:49:02');


CREATE TABLE `t_prize` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `activity_id` int(10) unsigned DEFAULT NULL COMMENT '所属活动id',
  `name` varchar(20) DEFAULT NULL COMMENT '奖品名称',
  `grade` tinyint(4) unsigned DEFAULT NULL COMMENT '奖品等级',
  `probability` decimal(10,2) DEFAULT NULL COMMENT '概率',
  `upper_limit` smallint(6) unsigned DEFAULT NULL COMMENT '中奖次数上限',
  `type` tinyint(4) unsigned DEFAULT NULL COMMENT '奖品类型：0-实物，1-红包，2-现金，3-理财金, 4-其它',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='奖品表';


INSERT INTO `activity`.`t_prize` (`id`, `activity_id`, `name`, `grade`, `probability`, `upper_limit`, `type`, `amount`, `gmt_modified`, `gmt_create`) VALUES ('1', '1', '谢谢参与', '7', '78.70', '1200', '4', '0.00', '2018-02-23 10:56:51', '2018-02-23 10:56:51');
INSERT INTO `activity`.`t_prize` (`id`, `activity_id`, `name`, `grade`, `probability`, `upper_limit`, `type`, `amount`, `gmt_modified`, `gmt_create`) VALUES ('2', '1', '3元现金', '6', '15.70', '500', '2', '3.00', '2018-02-23 10:56:51', '2018-02-23 10:56:51');
INSERT INTO `activity`.`t_prize` (`id`, `activity_id`, `name`, `grade`, `probability`, `upper_limit`, `type`, `amount`, `gmt_modified`, `gmt_create`) VALUES ('3', '1', '5元现金', '5', '0.80', '200', '2', '5.00', '2018-02-23 10:56:51', '2018-02-23 10:56:51');
INSERT INTO `activity`.`t_prize` (`id`, `activity_id`, `name`, `grade`, `probability`, `upper_limit`, `type`, `amount`, `gmt_modified`, `gmt_create`) VALUES ('4', '1', '10元现金', '4', '0.80', '100', '2', '10.00', '2018-02-23 10:56:51', '2018-02-23 10:56:51');
INSERT INTO `activity`.`t_prize` (`id`, `activity_id`, `name`, `grade`, `probability`, `upper_limit`, `type`, `amount`, `gmt_modified`, `gmt_create`) VALUES ('5', '1', '香肠腊肉礼盒', '3', '1.60', '50', '0', '86.00', '2018-02-23 10:56:51', '2018-02-23 10:56:51');
INSERT INTO `activity`.`t_prize` (`id`, `activity_id`, `name`, `grade`, `probability`, `upper_limit`, `type`, `amount`, `gmt_modified`, `gmt_create`) VALUES ('6', '1', '三只松鼠套装', '2', '1.60', '20', '0', '99.00', '2018-02-23 10:56:51', '2018-02-23 10:56:51');
INSERT INTO `activity`.`t_prize` (`id`, `activity_id`, `name`, `grade`, `probability`, `upper_limit`, `type`, `amount`, `gmt_modified`, `gmt_create`) VALUES ('7', '1', '中粮大礼包', '1', '0.80', '10', '0', '158.00', '2018-02-23 10:56:51', '2018-02-23 10:56:51');


CREATE TABLE `t_winning_record` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned DEFAULT NULL COMMENT '用户id',
  `prize_name` varchar(20) DEFAULT NULL COMMENT '中奖奖品名称',
  `prize_grade` tinyint(4) unsigned DEFAULT NULL COMMENT '奖品等级',
  `winning_time` datetime DEFAULT NULL COMMENT '中奖时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='中奖记录表';

