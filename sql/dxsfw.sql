/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50515
Source Host           : localhost:3306
Source Database       : dxsfw

Target Server Type    : MYSQL
Target Server Version : 50515
File Encoding         : 65001

Date: 2015-09-04 20:53:35
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_jianli
-- ----------------------------
INSERT INTO `t_jianli` VALUES ('1', '1', 'title', 'name', null, '2015-08-04', null, null, null, null, null, null, null, '1\\1.ico', null);
INSERT INTO `t_jianli` VALUES ('2', '1', '简历标题1', 'name', null, '2015-09-01', 'name', null, null, 'xx大学', null, null, null, null, '2\\2.xls');
INSERT INTO `t_jianli` VALUES ('4', '1', '简历标题', 'name', null, '2015-08-04', null, null, null, null, null, null, null, null, null);
INSERT INTO `t_jianli` VALUES ('5', '1', '简历标题', '测试中文', null, '2015-08-04', null, null, null, null, null, null, null, null, null);
INSERT INTO `t_jianli` VALUES ('6', '1', '简历标题', '测试中文', null, '2015-08-04', null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `t_jianzhi`
-- ----------------------------
DROP TABLE IF EXISTS `t_jianzhi`;
CREATE TABLE `t_jianzhi` (
  `jianzhiid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL COMMENT '发布人id（外键）',
  `createtime` timestamp NULL DEFAULT NULL COMMENT '发布时间,创建时间',
  `expiretime` timestamp NULL DEFAULT NULL COMMENT '过期时间/失效时间,如果当前系统时间超过失效时间,兼职信息失效',
  `updatetime` timestamp NULL DEFAULT NULL COMMENT '更新时间(发布着可以更新、申请人申请动作可以更新、排序用)',
  `expire` varchar(2) DEFAULT NULL COMMENT '是否过期，或者被锁定',
  `title` varchar(200) DEFAULT NULL COMMENT '职位标题、职位名称',
  `tag` varchar(200) DEFAULT NULL COMMENT '标签(可用于检索)eg:java 工程师 j2ee(中间用空格隔开)',
  `company` varchar(400) DEFAULT NULL COMMENT '公司名称',
  `companyintroduction` varchar(8000) DEFAULT NULL COMMENT '公司介绍(简介)',
  `industry` varchar(200) DEFAULT NULL COMMENT '行业',
  `workplace` varchar(500) DEFAULT NULL COMMENT '工作地点',
  `pay` varchar(50) DEFAULT NULL COMMENT '薪资(20-100/小时 100/天 1000/月 面谈)',
  `worktime` varchar(50) DEFAULT NULL COMMENT '工作时间(工作日 周末 其他)',
  `people` varchar(50) DEFAULT NULL COMMENT '招聘人数',
  `description` varchar(8000) DEFAULT NULL COMMENT '职位描述、介绍',
  `status` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`jianzhiid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_jianzhi
-- ----------------------------
INSERT INTO `t_jianzhi` VALUES ('1', '1', '2015-09-03 15:31:03', null, '2015-09-03 22:46:37', null, '兼职标题', '传单 java 服务员', null, null, null, '武汉', null, null, null, null, null);
INSERT INTO `t_jianzhi` VALUES ('4', '4', '2015-09-03 21:26:37', null, '2015-09-04 20:17:36', null, '兼职标题', '传单 java', null, null, null, '广州', null, null, null, null, null);
INSERT INTO `t_jianzhi` VALUES ('5', '5', '2015-09-03 21:28:03', null, '2015-09-03 22:02:22', null, '兼职标题5', 'java', null, null, null, '上海', null, null, null, null, null);
INSERT INTO `t_jianzhi` VALUES ('6', '1', '2015-09-03 21:28:47', null, '2015-09-03 21:28:47', null, '兼职标题2', '快递', null, null, null, '深圳', null, null, null, null, null);
INSERT INTO `t_jianzhi` VALUES ('7', '1', '2015-09-03 21:29:38', null, '2015-09-03 21:29:38', null, '兼职标题3', '空姐', null, null, null, '成都', null, null, null, null, null);
INSERT INTO `t_jianzhi` VALUES ('8', '5', '2015-09-03 21:30:20', null, '2015-09-03 21:30:20', null, '兼职标题4', 'cto ceo', null, null, null, 'anywhere', null, null, null, null, null);

-- ----------------------------
-- Table structure for `t_jianzhishengqing`
-- ----------------------------
DROP TABLE IF EXISTS `t_jianzhishengqing`;
CREATE TABLE `t_jianzhishengqing` (
  `shenqingid` int(11) NOT NULL AUTO_INCREMENT,
  `jianzhiid` int(11) DEFAULT NULL,
  `publishUserid` int(11) DEFAULT NULL,
  `shengqingUserid` int(11) DEFAULT NULL,
  `jianliid` int(11) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`shenqingid`),
  KEY `FK_jianzhi` (`jianzhiid`),
  KEY `FK_userid_1` (`publishUserid`),
  KEY `FK_userid_2` (`shengqingUserid`),
  KEY `FK_jianli` (`jianliid`),
  CONSTRAINT `FK_jianli` FOREIGN KEY (`jianliid`) REFERENCES `t_jianli` (`jianliid`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_jianzhi` FOREIGN KEY (`jianzhiid`) REFERENCES `t_jianzhi` (`jianzhiid`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_userid_1` FOREIGN KEY (`publishUserid`) REFERENCES `t_user` (`userid`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_userid_2` FOREIGN KEY (`shengqingUserid`) REFERENCES `t_user` (`userid`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_jianzhishengqing
-- ----------------------------
INSERT INTO `t_jianzhishengqing` VALUES ('4', '1', '5', '1', '2', '2015-09-03 20:27:16', null);
INSERT INTO `t_jianzhishengqing` VALUES ('5', '4', '4', '1', '6', '2015-09-03 21:44:44', null);
INSERT INTO `t_jianzhishengqing` VALUES ('6', '1', '1', '1', '5', '2015-09-03 22:46:37', null);

-- ----------------------------
-- Table structure for `t_party`
-- ----------------------------
DROP TABLE IF EXISTS `t_party`;
CREATE TABLE `t_party` (
  `partyid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL COMMENT '发布人id（外键）',
  `feeid` int(11) DEFAULT NULL COMMENT '收费id（外键）',
  `createtime` timestamp NULL DEFAULT NULL COMMENT '发布时间',
  `expiretime` timestamp NULL DEFAULT NULL COMMENT '过期时间',
  `updatetime` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `title` varchar(200) DEFAULT NULL COMMENT '活动主题，标题',
  `address` varchar(500) DEFAULT NULL COMMENT '活动地址',
  `cost` varchar(200) DEFAULT NULL COMMENT '活动花费｛AA、大概费用50到100等｝',
  `content` varchar(8000) DEFAULT NULL COMMENT '活动具体内容介绍',
  `partytime` varchar(500) DEFAULT NULL COMMENT '活动具体的开始时间',
  `people` int(11) DEFAULT NULL COMMENT '活动参与人数',
  `tag` varchar(200) DEFAULT NULL COMMENT '标签(可用于检索)',
  `status` varchar(2) DEFAULT NULL COMMENT '状态(预留字段)',
  `pictures` varchar(1000) DEFAULT NULL COMMENT '图片集｛1,23,50 "公共图片表id组合"｝',
  `info` varchar(1000) DEFAULT NULL COMMENT '预留字段',
  PRIMARY KEY (`partyid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_party
-- ----------------------------
INSERT INTO `t_party` VALUES ('2', '1', null, '2015-09-04 15:39:03', null, '2015-09-04 16:14:53', '十一欧洲7日游', '欧洲', '人均1万', null, '十一7天', '2', '旅游 十一 欧洲', null, null, null);
INSERT INTO `t_party` VALUES ('3', '4', null, '2015-09-04 16:17:41', null, '2015-09-04 16:33:00', '周末假面舞会', 'XX广场', '无', null, '本周六晚7点-9点', '0', '舞会', null, null, null);
INSERT INTO `t_party` VALUES ('4', '5', null, '2015-09-04 16:18:51', null, '2015-09-04 16:18:51', '宝宝联谊会', '婴儿乐园', '无', null, '周日上午', '0', '宝宝 会', null, null, null);
INSERT INTO `t_party` VALUES ('5', '1', null, '2015-09-04 16:19:44', null, '2015-09-04 20:08:58', '宝宝联谊会1', '婴儿乐园', '无', null, '周日上午', null, '联谊', null, '5,6', null);
INSERT INTO `t_party` VALUES ('6', '5', null, '2015-09-04 16:21:52', null, '2015-09-04 16:34:19', '海鲜自助聚餐', '自助餐厅', '120元', null, '一起定日子', null, '吃', null, null, null);

-- ----------------------------
-- Table structure for `t_partyshengqing`
-- ----------------------------
DROP TABLE IF EXISTS `t_partyshengqing`;
CREATE TABLE `t_partyshengqing` (
  `shenqingid` int(11) NOT NULL AUTO_INCREMENT,
  `partyid` int(11) DEFAULT NULL COMMENT '活动表id（外键）',
  `publishUserid` int(11) DEFAULT NULL COMMENT '发布人id（外键）',
  `shengqingUserid` int(11) DEFAULT NULL COMMENT '参与人id（外键）',
  `payid` int(11) DEFAULT NULL COMMENT '支付明细表id（外键）',
  `time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`shenqingid`),
  KEY `FK_partyid` (`partyid`),
  KEY `FK_userid_3` (`publishUserid`),
  KEY `FK_userid_4` (`shengqingUserid`),
  CONSTRAINT `FK_partyid` FOREIGN KEY (`partyid`) REFERENCES `t_party` (`partyid`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_userid_3` FOREIGN KEY (`publishUserid`) REFERENCES `t_user` (`userid`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_userid_4` FOREIGN KEY (`shengqingUserid`) REFERENCES `t_user` (`userid`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_partyshengqing
-- ----------------------------
INSERT INTO `t_partyshengqing` VALUES ('1', '2', '1', '4', null, '2015-09-04 15:46:54', null);
INSERT INTO `t_partyshengqing` VALUES ('7', '2', '1', '5', null, '2015-09-04 16:14:52', null);
INSERT INTO `t_partyshengqing` VALUES ('8', '3', '4', '5', null, '2015-09-04 16:32:43', null);
INSERT INTO `t_partyshengqing` VALUES ('9', '3', '4', '1', null, '2015-09-04 16:33:00', null);
INSERT INTO `t_partyshengqing` VALUES ('10', '5', '1', '4', null, '2015-09-04 16:33:24', null);
INSERT INTO `t_partyshengqing` VALUES ('11', '5', '1', '5', null, '2015-09-04 16:33:41', null);
INSERT INTO `t_partyshengqing` VALUES ('12', '6', '5', '1', null, '2015-09-04 16:34:03', null);
INSERT INTO `t_partyshengqing` VALUES ('13', '6', '5', '4', null, '2015-09-04 16:34:19', null);
INSERT INTO `t_partyshengqing` VALUES ('14', '5', '1', '6', null, '2015-09-04 16:45:14', null);
INSERT INTO `t_partyshengqing` VALUES ('15', '5', '1', '7', null, '2015-09-04 16:45:34', null);

-- ----------------------------
-- Table structure for `t_picture`
-- ----------------------------
DROP TABLE IF EXISTS `t_picture`;
CREATE TABLE `t_picture` (
  `pictureid` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片id',
  `tablename` varchar(100) DEFAULT NULL COMMENT '其他业务模块表名',
  `pk` int(11) DEFAULT NULL COMMENT '其他业务表的主键',
  `path` varchar(200) DEFAULT NULL COMMENT '图片路径',
  PRIMARY KEY (`pictureid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_picture
-- ----------------------------
INSERT INTO `t_picture` VALUES ('1', 't_jianzhi', '1', '1.ico');
INSERT INTO `t_picture` VALUES ('2', 't_party', '1', '1.png');
INSERT INTO `t_picture` VALUES ('3', 't_jianzhi', '5', '5.png');
INSERT INTO `t_picture` VALUES ('4', 't_jianzhi', '4', '4.jpg');
INSERT INTO `t_picture` VALUES ('5', 't_party', '5', '5_5.bpm');
INSERT INTO `t_picture` VALUES ('6', 't_jianzhi', '4', '4_6.bpm');

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '3', '开发人员', 'pwd', 'email2', '15207109571', 'riven', null, null, null, null, null, null, '1\\1.ico');
INSERT INTO `t_user` VALUES ('4', null, '中午', '2', null, '1', null, null, null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('5', null, '', 'pwd', '', '18000000', '', '', '', '', '', '', '', '');
INSERT INTO `t_user` VALUES ('6', '1', null, 'pwd', null, '13607447461', null, null, null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('7', '2', null, 'pwd', null, '15010842975', null, null, null, null, null, null, null, null);
