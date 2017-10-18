DROP DATABASE IF EXISTS `db_multimedia_server`;
CREATE DATABASE `db_multimedia_server`;
USE `db_multimedia_server`;
DROP TABLE IF EXISTS `m_user`;
CREATE TABLE `m_user` (
    `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    `account` varchar(225) COMMENT '账号',
    `pwd` varchar(225) COMMENT '密码',
    `name` varchar(225) COMMENT '姓名',
    `phone` varchar(225) COMMENT '手机',
    `role` int(1) COMMENT '角色',
    `status` int(1) COMMENT '状态',
    `del_flg` int(1) DEFAULT '0' COMMENT '删除标示',
    `create_datetime` timestamp DEFAULT '0000-00-00 00:00:00' COMMENT '创建日时',
    `create_user_id` int(11) COMMENT '创建者ID',
    `update_datetime` timestamp DEFAULT '0000-00-00 00:00:00' COMMENT '更新日时',
    `update_user_id` int(11) COMMENT '更新者ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
ALTER TABLE `m_user` COMMENT '用户管理';
DROP TABLE IF EXISTS `m_machine`;
CREATE TABLE `m_machine` (
    `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    `mac` varchar(225) COMMENT 'MAC地址',
    `address` varchar(500) COMMENT '所在位置',
    `heart_hit` timestamp DEFAULT '0000-00-00 00:00:00' COMMENT '最近心跳日时',
    `ip` varchar(500) COMMENT '当前内网IP',
    `del_flg` int(1) DEFAULT '0' COMMENT '删除标示',
    `create_datetime` timestamp DEFAULT '0000-00-00 00:00:00' COMMENT '创建日时',
    `create_user_id` int(11) COMMENT '创建者ID',
    `update_datetime` timestamp DEFAULT '0000-00-00 00:00:00' COMMENT '更新日时',
    `update_user_id` int(11) COMMENT '更新者ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
ALTER TABLE `m_machine` COMMENT '终端管理';
DROP TABLE IF EXISTS `m_version`;
CREATE TABLE `m_version` (
    `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    `name` varchar(225) COMMENT '版本名称',
    `des` varchar(500) COMMENT '版本描述',
    `push_datetime` timestamp NULL COMMENT '推送日时',
    `del_flg` int(1) DEFAULT '0' COMMENT '删除标示',
    `create_datetime` timestamp DEFAULT '0000-00-00 00:00:00' COMMENT '创建日时',
    `create_user_id` int(11) COMMENT '创建者ID',
    `update_datetime` timestamp DEFAULT '0000-00-00 00:00:00' COMMENT '更新日时',
    `update_user_id` int(11) COMMENT '更新者ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
ALTER TABLE `m_version` COMMENT '版本管理';
DROP TABLE IF EXISTS `m_apk`;
CREATE TABLE `m_apk` (
    `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    `name` varchar(225) COMMENT '名称',
    `size` int(11) COMMENT '大小',
    `version` varchar(225) COMMENT '版本',
    `des` varchar(500) COMMENT '描述',
    `pkg` varchar(255) COMMENT '包名',
    `del_flg` int(1) DEFAULT '0' COMMENT '删除标示',
    `create_datetime` timestamp DEFAULT '0000-00-00 00:00:00' COMMENT '创建日时',
    `create_user_id` int(11) COMMENT '创建者ID',
    `update_datetime` timestamp DEFAULT '0000-00-00 00:00:00' COMMENT '更新日时',
    `update_user_id` int(11) COMMENT '更新者ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
ALTER TABLE `m_apk` COMMENT '应用管理';
DROP TABLE IF EXISTS `m_history`;
CREATE TABLE `m_history` (
    `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    `version_id` int(11) COMMENT '版本ID',
    `des` varchar(500) COMMENT '描述',
    `del_flg` int(1) DEFAULT '0' COMMENT '删除标示',
    `create_datetime` timestamp DEFAULT '0000-00-00 00:00:00' COMMENT '创建日时',
    `create_user_id` int(11) COMMENT '创建者ID',
    `update_datetime` timestamp DEFAULT '0000-00-00 00:00:00' COMMENT '更新日时',
    `update_user_id` int(11) COMMENT '更新者ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
ALTER TABLE `m_history` COMMENT '推送历史';
DROP TABLE IF EXISTS `m_file_cate`;
CREATE TABLE `m_file_cate` (
    `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    `name` varchar(255) COMMENT '名称',
    `sort` int(4) COMMENT '排序',
    `del_flg` int(1) DEFAULT '0' COMMENT '删除标示',
    `create_datetime` timestamp DEFAULT '0000-00-00 00:00:00' COMMENT '创建日时',
    `create_user_id` int(11) COMMENT '创建者ID',
    `update_datetime` timestamp DEFAULT '0000-00-00 00:00:00' COMMENT '更新日时',
    `update_user_id` int(11) COMMENT '更新者ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
ALTER TABLE `m_file_cate` COMMENT '课件分类管理';
DROP TABLE IF EXISTS `m_file`;
CREATE TABLE `m_file` (
    `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    `file_cate_id` int(11) COMMENT '课件大分类',
    `file_sub_cate_id` int(11) COMMENT '课件子分类',
    `name` varchar(255) COMMENT '名称',
    `des` text COMMENT '简介',
    `actor` varchar(255) COMMENT '作者',
    `company` varchar(500) COMMENT '单位',
    `download_cnt` int(11) COMMENT '下载量',
    `love_cnt` int(11) COMMENT '点赞量',
    `cnt` int(11) COMMENT '浏览量',
    `status` int(1) DEFAULT '0' COMMENT '状态 0:待审核 1:已通过 2:已拒绝',
    `open_flg` int(1) DEFAULT '0' COMMENT '公开度',
    `type` int(1) COMMENT '类型',
    `suffix` varchar(50) COMMENT '后缀',
    `del_flg` int(1) DEFAULT '0' COMMENT '删除标示',
    `create_datetime` timestamp DEFAULT '0000-00-00 00:00:00' COMMENT '创建日时',
    `create_user_id` int(11) COMMENT '创建者ID',
    `update_datetime` timestamp DEFAULT '0000-00-00 00:00:00' COMMENT '更新日时',
    `update_user_id` int(11) COMMENT '更新者ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
ALTER TABLE `m_file` COMMENT '课件管理';
DROP TABLE IF EXISTS `m_file_comment`;
CREATE TABLE `m_file_comment` (
    `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    `file_id` int(11) COMMENT '课件ID',
    `msg` varchar(500) COMMENT '评论',
    `del_flg` int(1) DEFAULT '0' COMMENT '删除标示',
    `create_datetime` timestamp DEFAULT '0000-00-00 00:00:00' COMMENT '创建日时',
    `create_user_id` int(11) COMMENT '创建者ID',
    `update_datetime` timestamp DEFAULT '0000-00-00 00:00:00' COMMENT '更新日时',
    `update_user_id` int(11) COMMENT '更新者ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
ALTER TABLE `m_file_comment` COMMENT '课件评论管理';
DROP TABLE IF EXISTS `m_file_sub_cate`;
CREATE TABLE `m_file_sub_cate` (
    `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    `file_cate_id` int(11) COMMENT '大分类',
    `name` varchar(255) COMMENT '名称',
    `sort` int(4) COMMENT '排序',
    `del_flg` int(1) DEFAULT '0' COMMENT '删除标示',
    `create_datetime` timestamp DEFAULT '0000-00-00 00:00:00' COMMENT '创建日时',
    `create_user_id` int(11) COMMENT '创建者ID',
    `update_datetime` timestamp DEFAULT '0000-00-00 00:00:00' COMMENT '更新日时',
    `update_user_id` int(11) COMMENT '更新者ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
ALTER TABLE `m_file_sub_cate` COMMENT '课件子分类管理';
DROP TABLE IF EXISTS `m_machine_user_relation`;
CREATE TABLE `m_machine_user_relation` (
    `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    `machine_id` int(11) COMMENT '机器ID',
    `user_id` int(11) COMMENT '用户ID',
    `del_flg` int(1) DEFAULT '0' COMMENT '删除标示',
    `create_datetime` timestamp DEFAULT '2017-01-01 00:00:00' COMMENT '创建日时',
    `create_user_id` int(11) COMMENT '创建者ID',
    `update_datetime` timestamp DEFAULT '2017-01-01 00:00:00' COMMENT '更新日时',
    `update_user_id` int(11) COMMENT '更新者ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
ALTER TABLE `m_machine_user_relation` COMMENT '终端用户关联表';