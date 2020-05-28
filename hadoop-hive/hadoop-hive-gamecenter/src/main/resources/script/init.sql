CREATE DATABASE if not exists `game_center`;

USE `game_center`;

DROP TABLE IF EXISTS `res_active_users`;

CREATE TABLE `res_active_users`
(
    `id`         int(11) NOT NULL AUTO_INCREMENT,
    `plat_id`    varchar(16) DEFAULT NULL,
    `channel_id` varchar(16) DEFAULT NULL,
    `event_date` varchar(16) DEFAULT NULL,
    `new_users`  varchar(16) DEFAULT NULL,
    `old_users`  varchar(16) DEFAULT NULL,
    `all_users`  varchar(16) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 64
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `res_channel_num`;

CREATE TABLE `res_channel_num`
(
    `login_date`      varchar(64) DEFAULT NULL,
    `channel_id`      varchar(64) DEFAULT NULL,
    `device_num`      varchar(64) DEFAULT NULL,
    `reg_user`        varchar(64) DEFAULT NULL,
    `active_users`    varchar(64) DEFAULT NULL,
    `play_once_users` varchar(64) DEFAULT NULL,
    `pay_users`       varchar(64) DEFAULT NULL,
    `pay_user`        varchar(64) DEFAULT NULL,
    `pay_money`       varchar(64) DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `res_channel_num_temp`;

CREATE TABLE `res_channel_num_temp`
(
    `login_date`      varchar(64) DEFAULT NULL,
    `channel_id`      varchar(64) DEFAULT NULL,
    `device_num`      varchar(64) DEFAULT NULL,
    `reg_user`        varchar(64) DEFAULT NULL,
    `active_users`    varchar(64) DEFAULT NULL,
    `play_once_users` varchar(64) DEFAULT NULL,
    `pay_users`       varchar(64) DEFAULT NULL,
    `pay_user`        varchar(64) DEFAULT NULL,
    `pay_money`       varchar(64) DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;