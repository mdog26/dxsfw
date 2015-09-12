/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50515
Source Host           : localhost:3306
Source Database       : dxsfw

Target Server Type    : MYSQL
Target Server Version : 50515
File Encoding         : 65001

Date: 2015-09-12 12:18:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_bbs`
-- ----------------------------
DROP TABLE IF EXISTS `t_bbs`;
CREATE TABLE `t_bbs` (
  `bbsid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL COMMENT '发布人id(外键)',
  `feeid` int(11) DEFAULT NULL COMMENT '收费id(外键)',
  `createtime` timestamp NULL DEFAULT NULL COMMENT '发布时间',
  `expiretime` timestamp NULL DEFAULT NULL COMMENT '过期时间',
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `type` varchar(200) DEFAULT NULL COMMENT '发布类型{1-交流、2-讲师授课}',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `address` varchar(500) DEFAULT NULL COMMENT '授课地址',
  `bankuai` varchar(300) DEFAULT NULL COMMENT '板块',
  `zhuanye` varchar(300) DEFAULT NULL COMMENT '专业分类/板块｛用于论坛交流的专业分类｝',
  `teachtype` varchar(500) DEFAULT NULL COMMENT '授课种类｛1对1，1对多当面授课等｝',
  `content` varchar(8000) DEFAULT NULL COMMENT '交流/授课内容介绍',
  `teachtime` varchar(500) DEFAULT NULL COMMENT '授课开始时间',
  `people` int(11) DEFAULT NULL COMMENT '授课可参与人数',
  `replynumber` int(11) DEFAULT NULL COMMENT '回贴数量',
  `clicknumber` int(11) DEFAULT NULL COMMENT '点击量',
  `tag` varchar(200) DEFAULT NULL COMMENT '标签(可用于检索)',
  `status` varchar(2) DEFAULT NULL COMMENT '状态(预留字段)',
  `pictures` varchar(1000) DEFAULT NULL COMMENT '图片集{1,23,50 "公共图片表id组合"}',
  `info` varchar(1000) DEFAULT NULL COMMENT '预留字段',
  PRIMARY KEY (`bbsid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bbs
-- ----------------------------
INSERT INTO `t_bbs` VALUES ('2', '5', null, '2015-09-09 20:30:10', null, '2015-09-09 22:36:24', '1', '标题1', null, null, null, null, '内容', null, null, null, '2', '?', null, null, null);
INSERT INTO `t_bbs` VALUES ('3', '5', null, '2015-09-09 20:31:08', null, '2015-09-09 22:36:27', '1', '标题1', null, null, null, null, '内容', null, null, null, null, '?', null, null, null);
INSERT INTO `t_bbs` VALUES ('4', '5', null, '2015-09-09 20:37:43', null, '2015-09-09 22:50:53', '1', '交流1update', null, null, null, null, '交流内容，学生学术交流', null, null, '2', null, '学术 交流', null, '7,8', null);
INSERT INTO `t_bbs` VALUES ('5', '4', null, '2015-09-09 20:44:04', null, '2015-09-09 21:31:37', '2', '授课1', null, null, null, '1对1面授', '高数补课', '周六下午4点-6点', '1', null, null, '数学', null, null, null);

-- ----------------------------
-- Table structure for `t_bbsshengqing`
-- ----------------------------
DROP TABLE IF EXISTS `t_bbsshengqing`;
CREATE TABLE `t_bbsshengqing` (
  `shenqingid` int(11) NOT NULL AUTO_INCREMENT,
  `bbsid` int(11) DEFAULT NULL COMMENT '学术论坛表id（外键）',
  `publishUserid` int(11) DEFAULT NULL COMMENT '发布人id（外键）',
  `shengqingUserid` int(11) DEFAULT NULL COMMENT '报名人id（外键）',
  `payid` int(11) DEFAULT NULL COMMENT '支付明细表id（外键）',
  `time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '报名时间',
  `message` varchar(2000) DEFAULT NULL COMMENT '留言',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`shenqingid`),
  KEY `FK_bbsid` (`bbsid`),
  KEY `FK_userid_5` (`publishUserid`),
  KEY `FK_userid_6` (`shengqingUserid`),
  CONSTRAINT `FK_bbsid` FOREIGN KEY (`bbsid`) REFERENCES `t_bbs` (`bbsid`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_userid_5` FOREIGN KEY (`publishUserid`) REFERENCES `t_user` (`userid`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_userid_6` FOREIGN KEY (`shengqingUserid`) REFERENCES `t_user` (`userid`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bbsshengqing
-- ----------------------------
INSERT INTO `t_bbsshengqing` VALUES ('1', '5', '1', '7', null, '2015-09-09 21:31:36', null, null);

-- ----------------------------
-- Table structure for `t_chuangye`
-- ----------------------------
DROP TABLE IF EXISTS `t_chuangye`;
CREATE TABLE `t_chuangye` (
  `chuangyeid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL COMMENT '发布人id(外键)',
  `payuserid` int(11) DEFAULT NULL COMMENT '支付人id',
  `feeid` int(11) DEFAULT NULL COMMENT '收费id(外键)',
  `createtime` timestamp NULL DEFAULT NULL COMMENT '发布时间',
  `expiretime` timestamp NULL DEFAULT NULL COMMENT '过期时间',
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `type` varchar(200) DEFAULT NULL COMMENT '创业分类',
  `level` varchar(200) DEFAULT NULL COMMENT '创业等级',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `jieshao` varchar(4000) DEFAULT NULL COMMENT '引言介绍',
  `content` varchar(8000) DEFAULT NULL COMMENT '具体内容(加密的,只对支付人和自己显示)',
  `jiami` varchar(2) DEFAULT NULL COMMENT '加密开关{ Y, N}',
  `price` double(11,2) DEFAULT NULL COMMENT '价格',
  `sellstatus` varchar(2) DEFAULT NULL COMMENT '售卖状态{ Y, N}',
  `pingjia` varchar(200) DEFAULT NULL COMMENT '售卖评价',
  `pingjiafenshu` int(11) DEFAULT NULL COMMENT '评价分数',
  `tag` varchar(200) DEFAULT NULL COMMENT '标签(可用于检索)',
  `status` varchar(2) DEFAULT NULL COMMENT '状态(预留字段)',
  `pictures` varchar(1000) DEFAULT NULL COMMENT '图片集{1,23,50 "公共图片表id组合"}',
  `fujian` varchar(1000) DEFAULT NULL COMMENT '附件集{1,23,50 "公共附件表id组合"}',
  PRIMARY KEY (`chuangyeid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_chuangye
-- ----------------------------
INSERT INTO `t_chuangye` VALUES ('2', '5', null, null, '2015-09-11 22:11:32', null, '2015-09-11 22:11:32', '餐饮业', null, '黄焖鸡加盟', '介绍内容，所有人可显示', '具体内容(加密的,只对支付人和自己显示)', null, '12.00', null, null, null, '黄焖鸡 饮食', null, null, null);
INSERT INTO `t_chuangye` VALUES ('3', '5', '1', null, '2015-09-11 22:12:08', null, '2015-09-11 23:28:37', '汽车', null, '二手车买卖创业', '车介绍内容，所有人可显示', '车具体内容(加密的,只对支付人和自己显示)', null, '1000.12', 'Y', '好评，给五星', '5', '汽车', null, '9', '1,2');
INSERT INTO `t_chuangye` VALUES ('4', '4', null, null, '2015-09-11 22:20:47', null, '2015-09-11 22:20:47', '4餐饮业', null, '黄焖鸡4加盟', '介绍内容，所有人可显示', '具体内容(加密的,只对支付人和自己显示)', null, '12.50', null, null, null, '黄焖鸡 饮食4', null, null, null);

-- ----------------------------
-- Table structure for `t_fujian`
-- ----------------------------
DROP TABLE IF EXISTS `t_fujian`;
CREATE TABLE `t_fujian` (
  `fujianid` int(11) NOT NULL AUTO_INCREMENT COMMENT '附件id',
  `tablename` varchar(100) DEFAULT NULL COMMENT '其他业务模块表名',
  `pk` int(11) DEFAULT NULL COMMENT '其他业务表的主键',
  `path` varchar(200) DEFAULT NULL COMMENT '附件路径',
  PRIMARY KEY (`fujianid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_fujian
-- ----------------------------
INSERT INTO `t_fujian` VALUES ('1', 't_chuangye', '3', '3_1.docx');
INSERT INTO `t_fujian` VALUES ('2', 't_chuangye', '3', '3_2.pptx');

-- ----------------------------
-- Table structure for `t_idea`
-- ----------------------------
DROP TABLE IF EXISTS `t_idea`;
CREATE TABLE `t_idea` (
  `ideaid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL COMMENT '发布人id(外键)',
  `payuserid` int(11) DEFAULT NULL,
  `feeid` int(11) DEFAULT NULL COMMENT '收费id(外键)',
  `zhengjiid` int(11) DEFAULT NULL COMMENT '征集id(外键)',
  `createtime` timestamp NULL DEFAULT NULL COMMENT '发布时间',
  `expiretime` timestamp NULL DEFAULT NULL COMMENT '过期时间',
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `type` varchar(200) DEFAULT NULL COMMENT '创意分类',
  `level` varchar(200) DEFAULT NULL COMMENT '创意等级',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `jieshao` varchar(4000) DEFAULT NULL COMMENT '引言介绍',
  `content` varchar(8000) DEFAULT NULL COMMENT '具体内容(加密的,只对支付人和自己显示)',
  `jiami` varchar(2) DEFAULT NULL COMMENT '加密开关{ Y, N}',
  `price` double(11,2) DEFAULT NULL COMMENT '价格',
  `sellstatus` varchar(2) DEFAULT NULL COMMENT '售卖状态{ Y, N}',
  `pingjia` varchar(200) DEFAULT NULL COMMENT '售卖评价',
  `pingjiafenshu` int(11) DEFAULT NULL COMMENT '评价分数',
  `tag` varchar(200) DEFAULT NULL COMMENT '标签(可用于检索)',
  `status` varchar(2) DEFAULT NULL COMMENT '状态(预留字段)',
  `pictures` varchar(1000) DEFAULT NULL COMMENT '图片集{1,23,50 "公共图片表id组合"}',
  PRIMARY KEY (`ideaid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_idea
-- ----------------------------
INSERT INTO `t_idea` VALUES ('1', '5', null, null, null, '2015-09-12 10:41:07', null, '2015-09-12 10:45:31', '汽车', null, '二手车买卖创意', '车介绍内容，所有人可显示', '车具体内容(加密的,只对支付人和自己显示)', null, '1000.00', null, null, null, '汽车', null, null);
INSERT INTO `t_idea` VALUES ('3', '4', '1', null, null, '2015-09-12 10:46:47', null, '2015-09-12 10:55:18', '4餐饮业', null, '黄焖鸡4加盟', '介绍内容，所有人可显示', '具体内容(加密的,只对支付人和自己显示)', null, '12.00', 'Y', '好评，给五星', '5', '黄焖鸡 饮食4', null, '10');
INSERT INTO `t_idea` VALUES ('4', '1', '6', null, '3', '2015-09-12 11:59:57', null, '2015-09-12 11:59:57', '4餐饮业', null, '黄焖鸡4加盟', '介绍内容，所有人可显示', '具体内容(加密的,只对支付人和自己显示)', null, '12.50', 'Y', null, null, '黄焖鸡 饮食4', null, null);
INSERT INTO `t_idea` VALUES ('5', '7', null, null, '3', '2015-09-12 12:11:16', null, '2015-09-12 12:11:16', '4餐饮业', null, '投标啦4加盟', '介绍内容，所有人可显示投标啦', '投标啦具体内容(加密的,只对支付人和自己显示)', null, '12.50', null, null, null, '投标啦', null, null);

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
  `address` varchar(200) DEFAULT NULL COMMENT '住址',
  `height` varchar(100) DEFAULT NULL COMMENT '身高',
  `education` varchar(2000) DEFAULT NULL COMMENT '教育经历',
  `train` varchar(4000) DEFAULT NULL COMMENT '培训经历',
  `language` varchar(1000) DEFAULT NULL COMMENT '语言能力',
  `zhengshu` varchar(2000) DEFAULT NULL COMMENT '证书/奖励',
  `experience` varchar(4000) DEFAULT NULL COMMENT '工作经验',
  `evaluation` varchar(1000) DEFAULT NULL COMMENT '自我评价',
  `picture` varchar(300) DEFAULT NULL COMMENT '简历头像',
  `fujian` varchar(300) DEFAULT NULL COMMENT '简历附件',
  `createtime` timestamp NULL DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`jianliid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_jianli
-- ----------------------------
INSERT INTO `t_jianli` VALUES ('1', '1', 'title', 'name', null, '2015-08-04', null, null, null, null, null, null, null, null, null, null, null, '1\\1.ico', null, null, '2015-09-09 22:50:54', null);
INSERT INTO `t_jianli` VALUES ('4', '5', 'uuijbbn', '%E9%B1%BC', '0', '1993-09-06', '17727610912', null, null, '%E9%B1%BC', null, null, null, null, null, null, null, null, null, '2015-09-06 00:00:00', '2015-09-09 22:50:53', null);
INSERT INTO `t_jianli` VALUES ('5', '4', '1我的简历标题2', '姓名', '女', '2015-09-01', '13607447461', 'sfa@163.com', '420202199012120000', '长沙市望城坡1-1301', '身高', '[{\"time\":\"2003/9-2006/6\",\"school\":\"湖南大学\",\"zhuanye\":\"会计学\",\"xueli\":\"本科\",\"miaoshu\":\"预留字段\"},{\"time\":\"2007/9-2010/6\",\"school\":\"北京大学\",\"zhuanye\":\"会计学\",\"xueli\":\"研究生\"},{\"time\":\"2010/9-2013/6\",\"school\":\"哈弗大学\",\"zhuanye\":\"财经管理\",\"xueli\":\"博士生\"}]', '[{\"time\":\"2013/9-2013/12\",\"company\":\"新东方厨师学院\",\"kecheng\":\"厨师高级班\",\"address\":\"长沙\",\"zhengshu\":\"国家级厨师专业三级\",\"miaoshu\":\"预留字段\"},{\"time\":\"2013/9-2013/12\",\"company\":\"某某飞行学校\",\"kecheng\":\"飞行驾驶员课程\",\"address\":\"上海\",\"zhengshu\":\"飞行员资格证书\"}]', '[{\"zhonglei\":\"英语\",\"dengji\":\"专业八级\",\"chengdu\":\"精通\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"},{\"zhonglei\":\"日语\",\"dengji\":\"国家一级\",\"chengdu\":\"熟练\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"},{\"zhonglei\":\"法语\",\"dengji\":\"一级\",\"chengdu\":\"熟练\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"}]', '[{\"time\":\"2013/9\",\"name\":\"校级辩论赛一等奖\",\"dengji\":\"高级0\"},{\"time\":\"2003/9\",\"name\":\"1一等奖\",\"dengji\":\"高级1\"},{\"time\":\"2004/9\",\"name\":\"2一等奖\",\"dengji\":\"高级2\"},{\"time\":\"2014/9\",\"name\":\"3一等奖\",\"dengji\":\"高级3\"},{\"time\":\"2015/9\",\"name\":\"4一等奖\",\"dengji\":\"高级4\"},{\"time\":\"2013/10\",\"name\":\"5一等奖\",\"dengji\":\"高级5\"}]', '[{\"time\":\"2013/9-2015/12\",\"company\":\"新东方英语学校\",\"zhiwei\":\"英语口语高级讲师\",\"address\":\"北京\",\"zhengshu\":\"预留字段\",\"miaoshu\":\"预留字段1\"}]', '自我评价啊,随便填', '5/1.ico', '2/2.xls', '2015-09-05 18:18:47', '2015-09-05 20:10:06', 'N');
INSERT INTO `t_jianli` VALUES ('6', '1', '简历标题', '测试中文', null, '2015-08-04', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_jianli` VALUES ('7', '4', '我的简历标题2', '姓名', '女', '2015-09-01', '13607447461', 'sfa@163.com', '420202199012120000', '长沙市望城坡1-1301', '身高', '[{\"time\":\"2003/9-2006/6\",\"school\":\"湖南大学\",\"zhuanye\":\"会计学\",\"xueli\":\"本科\",\"miaoshu\":\"预留字段\"},{\"time\":\"2007/9-2010/6\",\"school\":\"北京大学\",\"zhuanye\":\"会计学\",\"xueli\":\"研究生\"},{\"time\":\"2010/9-2013/6\",\"school\":\"哈弗大学\",\"zhuanye\":\"财经管理\",\"xueli\":\"博士生\"}]', '[{\"time\":\"2013/9-2013/12\",\"company\":\"新东方厨师学院\",\"kecheng\":\"厨师高级班\",\"address\":\"长沙\",\"zhengshu\":\"国家级厨师专业三级\",\"miaoshu\":\"预留字段\"},{\"time\":\"2013/9-2013/12\",\"company\":\"某某飞行学校\",\"kecheng\":\"飞行驾驶员课程\",\"address\":\"上海\",\"zhengshu\":\"飞行员资格证书\"}]', '[{\"zhonglei\":\"英语\",\"dengji\":\"专业八级\",\"chengdu\":\"精通\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"},{\"zhonglei\":\"日语\",\"dengji\":\"国家一级\",\"chengdu\":\"熟练\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"},{\"zhonglei\":\"法语\",\"dengji\":\"一级\",\"chengdu\":\"熟练\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"}]', '[{\"time\":\"2013/9\",\"name\":\"校级辩论赛一等奖\",\"dengji\":\"高级0\"},{\"time\":\"2003/9\",\"name\":\"1一等奖\",\"dengji\":\"高级1\"},{\"time\":\"2004/9\",\"name\":\"2一等奖\",\"dengji\":\"高级2\"},{\"time\":\"2014/9\",\"name\":\"3一等奖\",\"dengji\":\"高级3\"},{\"time\":\"2015/9\",\"name\":\"4一等奖\",\"dengji\":\"高级4\"},{\"time\":\"2013/10\",\"name\":\"5一等奖\",\"dengji\":\"高级5\"}]', '[{\"time\":\"2013/9-2015/12\",\"company\":\"新东方英语学校\",\"zhiwei\":\"英语口语高级讲师\",\"address\":\"北京\",\"zhengshu\":\"预留字段\",\"miaoshu\":\"预留字段\"}]', '自我评价啊,随便填', '5/1.ico', '2/2.xls', '2015-09-05 18:18:47', '2015-09-05 18:23:15', 'N');
INSERT INTO `t_jianli` VALUES ('8', '4', '1我的简历标题2', '姓名', '女', '2015-09-05', '13607447461', 'sfa@163.com', '420202199012120000', '长沙市望城坡1-1301', '身高', '[{\"time\":\"2003/9-2006/6\",\"school\":\"湖南大学\",\"zhuanye\":\"会计学\",\"xueli\":\"本科\",\"miaoshu\":\"预留字段\"},{\"time\":\"2007/9-2010/6\",\"school\":\"北京大学\",\"zhuanye\":\"会计学\",\"xueli\":\"研究生\"},{\"time\":\"2010/9-2013/6\",\"school\":\"哈弗大学\",\"zhuanye\":\"财经管理\",\"xueli\":\"博士生\"}]', '[{\"time\":\"2013/9-2013/12\",\"company\":\"新东方厨师学院\",\"kecheng\":\"厨师高级班\",\"address\":\"长沙\",\"zhengshu\":\"国家级厨师专业三级\",\"miaoshu\":\"预留字段\"},{\"time\":\"2013/9-2013/12\",\"company\":\"某某飞行学校\",\"kecheng\":\"飞行驾驶员课程\",\"address\":\"上海\",\"zhengshu\":\"飞行员资格证书\"}]', '[{\"zhonglei\":\"英语\",\"dengji\":\"专业八级\",\"chengdu\":\"精通\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"},{\"zhonglei\":\"日语\",\"dengji\":\"国家一级\",\"chengdu\":\"熟练\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"},{\"zhonglei\":\"法语\",\"dengji\":\"一级\",\"chengdu\":\"熟练\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"}]', '[{\"time\":\"2013/9\",\"name\":\"校级辩论赛一等奖\",\"dengji\":\"高级0\"},{\"time\":\"2003/9\",\"name\":\"1一等奖\",\"dengji\":\"高级1\"},{\"time\":\"2004/9\",\"name\":\"2一等奖\",\"dengji\":\"高级2\"},{\"time\":\"2014/9\",\"name\":\"3一等奖\",\"dengji\":\"高级3\"},{\"time\":\"2015/9\",\"name\":\"4一等奖\",\"dengji\":\"高级4\"},{\"time\":\"2013/10\",\"name\":\"5一等奖\",\"dengji\":\"高级5\"}]', '[{\"time\":\"2013/9-2015/12\",\"company\":\"新东方英语学校\",\"zhiwei\":\"英语口语高级讲师\",\"address\":\"北京\",\"zhengshu\":\"预留字段\",\"miaoshu\":\"预留字段1\"}]', '自我评价啊,随便填', '5/1.ico', '2/2.xls', '2015-09-05 18:18:47', '2015-09-05 18:23:15', 'N');
INSERT INTO `t_jianli` VALUES ('9', '4', '??????3', '??', '?', '2015-09-01', '13607447461', 'sfa@163.com', '420202199012120000', '??????1-1301', '??', '[{\"time\":\"2003/9-2006/6\",\"school\":\"????\",\"zhuanye\":\"???\",\"xueli\":\"??\",\"miaoshu\":\"????\"},{\"time\":\"2007/9-2010/6\",\"school\":\"????\",\"zhuanye\":\"???\",\"xueli\":\"???\"},{\"time\":\"2010/9-2013/6\",\"school\":\"????\",\"zhuanye\":\"????\",\"xueli\":\"???\"}]', '[{\"time\":\"2013/9-2013/12\",\"company\":\"???????\",\"kecheng\":\"?????\",\"address\":\"??\",\"zhengshu\":\"?????????\",\"miaoshu\":\"????\"},{\"time\":\"2013/9-2013/12\",\"company\":\"??????\",\"kecheng\":\"???????\",\"address\":\"??\",\"zhengshu\":\"???????\"}]', '[{\"zhonglei\":\"??\",\"dengji\":\"????\",\"chengdu\":\"??\",\"duxie\":\"??\",\"tingshuo\":\"??\"},{\"zhonglei\":\"??\",\"dengji\":\"????\",\"chengdu\":\"??\",\"duxie\":\"??\",\"tingshuo\":\"??\"},{\"zhonglei\":\"??\",\"dengji\":\"??\",\"chengdu\":\"??\",\"duxie\":\"??\",\"tingshuo\":\"??\"}]', '[{\"time\":\"2013/9\",\"name\":\"????????\",\"dengji\":\"??0\"},{\"time\":\"2003/9\",\"name\":\"1???\",\"dengji\":\"??1\"},{\"time\":\"2004/9\",\"name\":\"2???\",\"dengji\":\"??2\"},{\"time\":\"2014/9\",\"name\":\"3???\",\"dengji\":\"??3\"},{\"time\":\"2015/9\",\"name\":\"4???\",\"dengji\":\"??4\"},{\"time\":\"2013/10\",\"name\":\"5???\",\"dengji\":\"??5\"}]', '[{\"time\":\"2013/9-2015/12\",\"company\":\"???????\",\"zhiwei\":\"????????\",\"address\":\"??\",\"zhengshu\":\"????\",\"miaoshu\":\"????\"}]', '?????,???', '5/1.ico', '2/2.xls', '2015-09-09 20:32:56', '2015-09-09 20:32:56', 'N');
INSERT INTO `t_jianli` VALUES ('10', '4', '我的简历标题3', '姓名', '女', '2015-09-01', '13607447461', 'sfa@163.com', '420202199012120000', '长沙市望城坡1-1301', '身高', '[{\"time\":\"2003/9-2006/6\",\"school\":\"湖南大学\",\"zhuanye\":\"会计学\",\"xueli\":\"本科\",\"miaoshu\":\"预留字段\"},{\"time\":\"2007/9-2010/6\",\"school\":\"北京大学\",\"zhuanye\":\"会计学\",\"xueli\":\"研究生\"},{\"time\":\"2010/9-2013/6\",\"school\":\"哈弗大学\",\"zhuanye\":\"财经管理\",\"xueli\":\"博士生\"}]', '[{\"time\":\"2013/9-2013/12\",\"company\":\"新东方厨师学院\",\"kecheng\":\"厨师高级班\",\"address\":\"长沙\",\"zhengshu\":\"国家级厨师专业三级\",\"miaoshu\":\"预留字段\"},{\"time\":\"2013/9-2013/12\",\"company\":\"某某飞行学校\",\"kecheng\":\"飞行驾驶员课程\",\"address\":\"上海\",\"zhengshu\":\"飞行员资格证书\"}]', '[{\"zhonglei\":\"英语\",\"dengji\":\"专业八级\",\"chengdu\":\"精通\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"},{\"zhonglei\":\"日语\",\"dengji\":\"国家一级\",\"chengdu\":\"熟练\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"},{\"zhonglei\":\"法语\",\"dengji\":\"一级\",\"chengdu\":\"熟练\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"}]', '[{\"time\":\"2013/9\",\"name\":\"校级辩论赛一等奖\",\"dengji\":\"高级0\"},{\"time\":\"2003/9\",\"name\":\"1一等奖\",\"dengji\":\"高级1\"},{\"time\":\"2004/9\",\"name\":\"2一等奖\",\"dengji\":\"高级2\"},{\"time\":\"2014/9\",\"name\":\"3一等奖\",\"dengji\":\"高级3\"},{\"time\":\"2015/9\",\"name\":\"4一等奖\",\"dengji\":\"高级4\"},{\"time\":\"2013/10\",\"name\":\"5一等奖\",\"dengji\":\"高级5\"}]', '[{\"time\":\"2013/9-2015/12\",\"company\":\"新东方英语学校\",\"zhiwei\":\"英语口语高级讲师\",\"address\":\"北京\",\"zhengshu\":\"预留字段\",\"miaoshu\":\"预留字段\"}]', '自我评价啊,随便填', '5/1.ico', '2/2.xls', '2015-09-09 20:37:06', '2015-09-09 20:37:06', 'N');
INSERT INTO `t_jianli` VALUES ('11', '4', '我的简历标题3', '姓名', '女', '2015-09-01', '13607447461', 'sfa@163.com', '420202199012120000', '长沙市望城坡1-1301', '身高', '[{\"time\":\"2003/9-2006/6\",\"school\":\"湖南大学\",\"zhuanye\":\"会计学\",\"xueli\":\"本科\",\"miaoshu\":\"预留字段\"},{\"time\":\"2007/9-2010/6\",\"school\":\"北京大学\",\"zhuanye\":\"会计学\",\"xueli\":\"研究生\"},{\"time\":\"2010/9-2013/6\",\"school\":\"哈弗大学\",\"zhuanye\":\"财经管理\",\"xueli\":\"博士生\"}]', '[{\"time\":\"2013/9-2013/12\",\"company\":\"新东方厨师学院\",\"kecheng\":\"厨师高级班\",\"address\":\"长沙\",\"zhengshu\":\"国家级厨师专业三级\",\"miaoshu\":\"预留字段\"},{\"time\":\"2013/9-2013/12\",\"company\":\"某某飞行学校\",\"kecheng\":\"飞行驾驶员课程\",\"address\":\"上海\",\"zhengshu\":\"飞行员资格证书\"}]', '[{\"zhonglei\":\"英语\",\"dengji\":\"专业八级\",\"chengdu\":\"精通\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"},{\"zhonglei\":\"日语\",\"dengji\":\"国家一级\",\"chengdu\":\"熟练\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"},{\"zhonglei\":\"法语\",\"dengji\":\"一级\",\"chengdu\":\"熟练\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"}]', '[{\"time\":\"2013/9\",\"name\":\"校级辩论赛一等奖\",\"dengji\":\"高级0\"},{\"time\":\"2003/9\",\"name\":\"1一等奖\",\"dengji\":\"高级1\"},{\"time\":\"2004/9\",\"name\":\"2一等奖\",\"dengji\":\"高级2\"},{\"time\":\"2014/9\",\"name\":\"3一等奖\",\"dengji\":\"高级3\"},{\"time\":\"2015/9\",\"name\":\"4一等奖\",\"dengji\":\"高级4\"},{\"time\":\"2013/10\",\"name\":\"5一等奖\",\"dengji\":\"高级5\"}]', '[{\"time\":\"2013/9-2015/12\",\"company\":\"新东方英语学校\",\"zhiwei\":\"英语口语高级讲师\",\"address\":\"北京\",\"zhengshu\":\"预留字段\",\"miaoshu\":\"预留字段\"}]', '自我评价啊,随便填', '5/1.ico', '2/2.xls', '2015-09-09 22:50:52', '2015-09-09 22:50:52', 'N');

-- ----------------------------
-- Table structure for `t_jianzhi`
-- ----------------------------
DROP TABLE IF EXISTS `t_jianzhi`;
CREATE TABLE `t_jianzhi` (
  `jianzhiid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL COMMENT '发布人id（外键）',
  `createtime` timestamp NULL DEFAULT NULL COMMENT '发布时间,创建时间',
  `expiretime` timestamp NULL DEFAULT NULL COMMENT '过期时间/失效时间,如果当前系统时间超过失效时间,兼职信息失效',
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间(发布着可以更新、申请人申请动作可以更新、排序用)',
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
  `status` varchar(4000) DEFAULT NULL COMMENT '任职要求',
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
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_picture
-- ----------------------------
INSERT INTO `t_picture` VALUES ('1', 't_jianzhi', '1', '1.ico');
INSERT INTO `t_picture` VALUES ('2', 't_party', '1', '1.png');
INSERT INTO `t_picture` VALUES ('3', 't_jianzhi', '5', '5.png');
INSERT INTO `t_picture` VALUES ('4', 't_jianzhi', '4', '4.jpg');
INSERT INTO `t_picture` VALUES ('5', 't_party', '5', '5_5.bpm');
INSERT INTO `t_picture` VALUES ('6', 't_jianzhi', '4', '4_6.bpm');
INSERT INTO `t_picture` VALUES ('7', 't_bbs', '4', '4_7.jpg');
INSERT INTO `t_picture` VALUES ('8', 't_bbs', '4', '4_8.jpg');
INSERT INTO `t_picture` VALUES ('9', 't_chuangye', '3', '3_9.bpm');
INSERT INTO `t_picture` VALUES ('10', 't_idea', '3', '3_10.bpm');
INSERT INTO `t_picture` VALUES ('11', 't_zhengji', '3', '3_11.bpm');

-- ----------------------------
-- Table structure for `t_reply`
-- ----------------------------
DROP TABLE IF EXISTS `t_reply`;
CREATE TABLE `t_reply` (
  `replyid` int(11) NOT NULL AUTO_INCREMENT,
  `tablename` varchar(100) DEFAULT NULL COMMENT '模块表名',
  `pk` int(11) DEFAULT NULL COMMENT '模块主键id',
  `publishUserid` int(11) DEFAULT NULL COMMENT '发布人id（外键）',
  `replyUserid` int(11) DEFAULT NULL COMMENT '回复人id（外键）',
  `message` varchar(4000) DEFAULT NULL COMMENT '留言',
  `time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '回复时间',
  `picture` varchar(1000) DEFAULT NULL COMMENT '回复图片',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`replyid`),
  KEY `FK_userid_7` (`publishUserid`),
  KEY `FK_userid_8` (`replyUserid`),
  CONSTRAINT `FK_userid_7` FOREIGN KEY (`publishUserid`) REFERENCES `t_user` (`userid`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_userid_8` FOREIGN KEY (`replyUserid`) REFERENCES `t_user` (`userid`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_reply
-- ----------------------------
INSERT INTO `t_reply` VALUES ('1', 't_bbs', '4', '5', '1', '用户1回复用户5，帖子是4', '2015-09-09 22:54:29', null, null);
INSERT INTO `t_reply` VALUES ('2', 't_bbs', '4', '5', '1', '用户1回复用户5，帖子是4', '2015-09-12 11:41:35', null, null);
INSERT INTO `t_reply` VALUES ('3', 't_bbs', '4', '5', '1', '用户1回复用户5，帖子是4', '2015-09-12 11:42:01', null, null);

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
INSERT INTO `t_user` VALUES ('1', '3', 'test1', 'pwd', 'xiazl1987@163.com', '15207109571', 'riven', null, null, null, null, null, null, '1\\1.ico');
INSERT INTO `t_user` VALUES ('4', null, '中午', '2', null, '1', null, null, null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('5', null, '', 'pwd', '', '18000000', '', '', '', '', '', '', '', '');
INSERT INTO `t_user` VALUES ('6', '1', null, 'pwd', null, '13607447461', null, null, null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('7', '2', null, 'pwd', null, '15010842975', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `t_zhengji`
-- ----------------------------
DROP TABLE IF EXISTS `t_zhengji`;
CREATE TABLE `t_zhengji` (
  `zhengjiid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL COMMENT '发布人id（外键）',
  `selluserid` int(11) DEFAULT NULL COMMENT '收款人id（外键）',
  `feeid` int(11) DEFAULT NULL COMMENT '收费id（外键）',
  `createtime` timestamp NULL DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `title` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '主题',
  `content` varchar(8000) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述内容',
  `sellstatus` varchar(2) CHARACTER SET utf8 DEFAULT NULL COMMENT '售卖状态',
  `tag` varchar(1000) CHARACTER SET utf8 DEFAULT NULL COMMENT '标签',
  `pictures` varchar(1000) CHARACTER SET utf8 DEFAULT NULL COMMENT '图片集{1,23,50 "公共图片表id组合"}',
  `status` varchar(2) CHARACTER SET utf8 DEFAULT NULL COMMENT '状态(预留字段)',
  PRIMARY KEY (`zhengjiid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_zhengji
-- ----------------------------
INSERT INTO `t_zhengji` VALUES ('2', '7', null, null, '2015-09-12 11:26:22', '2015-09-12 11:29:00', '征集标题1', '1征集创意的具体内容', null, '1黄焖鸡 饮食', null, null);
INSERT INTO `t_zhengji` VALUES ('3', '6', '1', null, '2015-09-12 11:28:13', '2015-09-12 11:46:31', '征集标题4', '征集创意的具体内容', 'Y', '黄焖鸡 饮食', '11', null);
