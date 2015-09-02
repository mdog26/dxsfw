/*
Navicat MySQL Data Transfer

Source Server         : 金点子生产环境
Source Server Version : 50616
Source Host           : 10.0.0.184:3306
Source Database       : dxsfw

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2015-09-02 16:03:37
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
-- Table structure for `t_jianzhi`
-- ----------------------------
DROP TABLE IF EXISTS `t_jianzhi`;
CREATE TABLE `t_jianzhi` (
  `jianzhiid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL COMMENT '发布人id（外键）',
  `createtime` time DEFAULT NULL COMMENT '发布时间,创建时间',
  `expiretime` time DEFAULT NULL COMMENT '过期时间/失效时间,如果当前系统时间超过失效时间,兼职信息失效',
  `updatetime` time DEFAULT NULL COMMENT '更新时间(发布着可以更新、申请人申请动作可以更新、排序用)',
  `expire` varchar(2) DEFAULT NULL COMMENT '是否过期，或者被锁定',
  `title` varchar(200) DEFAULT NULL COMMENT '职位标题、职位名称',
  `tag` varchar(100) DEFAULT NULL COMMENT '标签(可用于检索)eg:java 工程师 j2ee(中间用空格隔开)',
  `company` varchar(400) DEFAULT NULL COMMENT '公司名称',
  `companyintroduction` varchar(8000) DEFAULT NULL COMMENT '公司介绍(简介)',
  `workplace` varchar(500) DEFAULT NULL COMMENT '工作地点',
  `pay` varchar(50) DEFAULT NULL COMMENT '[枚举类型]薪资(20-100/小时 100/天 1000/月 面谈)',
  `worktime` varchar(50) DEFAULT NULL COMMENT '[枚举类型]工作时间(工作日 周末 其他)',
  `people` varchar(50) DEFAULT NULL COMMENT '[枚举类型]招聘人数',
  `description` varchar(8000) DEFAULT NULL COMMENT '职位描述、介绍',
  `status` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`jianzhiid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_jianzhi
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
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
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '3', '', 'pwd', 'email', '15207109571', 'riven', null, null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('4', null, '中午', '2', null, '1', null, null, null, null, null, null, null, null);
