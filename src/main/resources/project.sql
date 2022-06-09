CREATE DATABASE `test` DEFAULT CHARACTER SET utf8;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id`    bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name`  varchar(30)          DEFAULT NULL COMMENT '姓名',
    `age`   int                  DEFAULT NULL COMMENT '年龄',
    `email` varchar(50) NOT NULL DEFAULT '' COMMENT '邮箱',
    `created_at` datetime,
    `updated_at` datetime,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;