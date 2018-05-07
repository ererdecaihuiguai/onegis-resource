CREATE DATABASE IF NOT EXISTS db_resource DEFAULT CHARACTER SET UTF8;
USE db_resource;

DROP TABLE IF EXISTS t_user;
DROP TABLE IF EXISTS t_plugin;

CREATE TABLE t_user(
   id                   BIGINT NOT NULL PRIMARY KEY COMMENT '主键Id',
   user_name            VARCHAR(100) NOT NULL COMMENT '账号',
   nick_name            VARCHAR(100) COMMENT '昵称',
   avatar               VARCHAR(200) COMMENT '头像',
   email                VARCHAR(100) COMMENT '邮箱',
   signature            VARCHAR(200) COMMENT '签名',
   create_time          DATETIME COMMENT '记录创建时间',
   update_time          TIMESTAMP COMMENT '记录修改时间',
   del_flag             SMALLINT DEFAULT 0 COMMENT '逻辑删除标识'
);

CREATE TABLE t_plugin (
   id                   BIGINT NOT NULL PRIMARY KEY COMMENT '主键Id',
   user_id              BIGINT COMMENT '上传者Id',
   name                 VARCHAR(200) COMMENT '插件名称',
   icon                 VARCHAR(200) COMMENT '图标',
   version             VARCHAR(100) COMMENT '版本',
   tags                 VARCHAR(200) COMMENT '标签',
   description          VARCHAR(1024) COMMENT '功能描述',
   create_time          DATETIME COMMENT '记录创建时间',
   update_time          TIMESTAMP COMMENT '记录更新时间',
   del_flag             SMALLINT DEFAULT 0 COMMENT '逻辑删除标识'
);





