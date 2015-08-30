/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50515
Source Host           : localhost:3306
Source Database       : dxsfw

Target Server Type    : MYSQL
Target Server Version : 50515
File Encoding         : 65001

Date: 2015-08-30 22:31:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_jianli`
-- ----------------------------
DROP TABLE IF EXISTS `t_jianli`;
CREATE TABLE `t_jianli` (
  `jianliid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL COMMENT '用户id（外键）',
  `title` varchar(100) DEFAULT NULL COMMENT '简历名称、简历标题',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(4) DEFAULT NULL COMMENT '性别',
  `birthdate` date DEFAULT NULL COMMENT '生日',
  `mobile` varchar(50) DEFAULT NULL COMMENT '手机号',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `card` varchar(50) DEFAULT NULL COMMENT '身份证',
  `school` varchar(200) DEFAULT NULL COMMENT '学校',
  `education` varchar(4000) DEFAULT NULL COMMENT '教育经历',
  `experience` varchar(8000) DEFAULT NULL COMMENT '个人优势、经历',
  `evaluation` varchar(2000) DEFAULT NULL COMMENT '自我评价',
  `picture` varchar(300) DEFAULT NULL COMMENT '简历头像',
  `fujian` varchar(300) DEFAULT NULL COMMENT '简历附件',
  PRIMARY KEY (`jianliid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_jianli
-- ----------------------------
INSERT INTO `t_jianli` VALUES ('1', '1', 'title', 'name', null, '2015-08-04', null, null, null, null, null, null, null, null, null);
INSERT INTO `t_jianli` VALUES ('2', '1', '简历标题', 'name', null, '2015-08-04', null, null, null, null, null, null, null, null, null);

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
  `card` varchar(50) DEFAULT NULL COMMENT '身份证',
  `sex` varchar(4) DEFAULT NULL COMMENT '性别',
  `weixin` varchar(200) DEFAULT NULL COMMENT '微信号',
  `zhifubao` varchar(200) DEFAULT NULL COMMENT '支付宝账号',
  `picture` varchar(300) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`useid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '3', '', 'pwd', 'email', '15207109571', 'riven', null, null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('4', null, '中午', '2', null, '1', null, null, null, null, null, null, null, null);
