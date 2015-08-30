/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50515
Source Host           : localhost:3306
Source Database       : dsxfw

Target Server Type    : MYSQL
Target Server Version : 50515
File Encoding         : 65001

Date: 2015-08-29 14:15:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `useid` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '1-学生，2-商家，3-管理员',
  `name` varchar(100) DEFAULT NULL COMMENT '用户名',
  `password` varchar(300) DEFAULT NULL COMMENT '密码',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(50) DEFAULT NULL COMMENT '手机',
  `nickname` varchar(200) DEFAULT NULL COMMENT '昵称',
  `organization` varchar(300) DEFAULT NULL COMMENT '学校/公司商家名称',
  `introduction` varchar(2000) DEFAULT NULL COMMENT '个人/公司介绍',
  `card` int(11) DEFAULT NULL COMMENT '身份证',
  `sex` varchar(4) DEFAULT NULL COMMENT '性别',
  `weixin` varchar(200) DEFAULT NULL COMMENT '微信号',
  `zhifubao` varchar(200) DEFAULT NULL COMMENT '支付宝账号',
  `picture` blob COMMENT '头像',
  PRIMARY KEY (`useid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
