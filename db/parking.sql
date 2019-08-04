/*
Navicat MySQL Data Transfer

Source Server         : manager
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : parking

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-08-04 23:46:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `send_user_id` varchar(255) DEFAULT NULL,
  `send_user_type` varchar(255) DEFAULT NULL,
  `send_user_name` varchar(255) DEFAULT NULL,
  `send_user_contract` varchar(255) DEFAULT NULL,
  `receive_user_id` varchar(255) DEFAULT NULL,
  `receive_user_type` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('3', '8', 'teacher', '何生', '13413608888', '5', 'student', '请完善你的班级信息', '1553568918', null, 'normal');
INSERT INTO `message` VALUES ('4', '5', 'student', '周宁', '123456', '8', 'teacher', '好的老师', '1553570950', null, 'delete');

-- ----------------------------
-- Table structure for user_base
-- ----------------------------
DROP TABLE IF EXISTS `user_base`;
CREATE TABLE `user_base` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `pass` varchar(255) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `slogan` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `age` varchar(255) DEFAULT NULL,
  `idnum` varchar(255) DEFAULT NULL,
  `nation` varchar(255) DEFAULT NULL COMMENT '民族',
  `registered_residence` varchar(255) DEFAULT NULL COMMENT '户籍',
  `email` varchar(255) DEFAULT NULL,
  `useridentify` varchar(255) DEFAULT NULL COMMENT '用户编号（学号，教师编号，管理员编号）',
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `health` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL COMMENT '用户类型',
  `class_id` varchar(255) DEFAULT NULL,
  `college_id` varchar(255) DEFAULT NULL,
  `school_id` varchar(255) DEFAULT NULL,
  `class_name` varchar(255) DEFAULT NULL,
  `college_name` varchar(255) DEFAULT NULL,
  `school_name` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` varchar(255) DEFAULT NULL COMMENT '删除标志位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_base
-- ----------------------------
INSERT INTO `user_base` VALUES ('2', 'admin', '123456', '系统管理员', 'upload/1553408286963.jpg', '不个性何来个性签名', '女', '13', '123', '汉', '广东', '13413607283@163.com', '123456', '12345678910', '常住地址', '1996', 'student', null, null, null, null, null, null, null, '无', 'normal');
INSERT INTO `user_base` VALUES ('5', '6558455', '123', '周宁', 'upload/1553335338579.jpg', null, null, null, null, null, null, null, '123', null, null, null, 'student', 'null', 'null', 'null', 'null', 'null', 'null', '1553332979', null, 'normal');
INSERT INTO `user_base` VALUES ('8', null, '1234', '何生', 'upload/1553563210115.jpg', null, null, null, null, null, null, null, '1234', null, null, null, 'teacher', null, null, null, null, null, null, '1553497718', null, 'delete');
INSERT INTO `user_base` VALUES ('10', '系统管理员', 'admin', '系统管理员', null, null, null, null, null, null, null, null, 'admin', null, null, null, 'system', null, null, null, null, null, null, null, null, 'normal');
INSERT INTO `user_base` VALUES ('19', '123452019-08-04', '12345', null, 'upload/1553335338579.jpg', null, null, null, null, null, null, null, '12345', null, null, null, '1', null, null, null, null, null, null, '1564905412', null, 'normal');
