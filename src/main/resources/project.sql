CREATE DATABASE `test` DEFAULT CHARACTER SET utf8;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`         bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name`       varchar(30)  DEFAULT NULL COMMENT '姓名',
    `age`        int DEFAULT NULL COMMENT '年龄',
    `email`      varchar(50) NOT NULL DEFAULT '' COMMENT '邮箱',
    `created_at` datetime,
    `updated_at` datetime,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `credential`;
CREATE TABLE `credential`
(
    `id`         BIGINT (20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `app_id`     VARCHAR(50) NOT NULL COMMENT 'appId',
    `app_secret` VARCHAR(50) NOT NULL COMMENT 'appSecret',
    `signer`     VARCHAR(50) NOT NULL COMMENT 'token 盐值',
    `argorithm`  VARCHAR(10) NOT NULL COMMENT 'token 生成算法',
    `expire_at`  BIGINT (20) NOT NULL COMMENT 'token 过期时间（秒）',
    `created_at` datetime,
    `updated_at` datetime,
    PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_unicode_ci;