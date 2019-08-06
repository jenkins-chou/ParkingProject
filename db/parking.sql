/*
Navicat MySQL Data Transfer

Source Server         : manager
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : parking

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-08-06 23:16:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `update_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------

-- ----------------------------
-- Table structure for account_recharge
-- ----------------------------
DROP TABLE IF EXISTS `account_recharge`;
CREATE TABLE `account_recharge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `money` double(255,0) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_recharge
-- ----------------------------

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `car_number` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `owner` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `property` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `car_code` varchar(255) DEFAULT NULL,
  `engine_number` varchar(255) DEFAULT NULL,
  `register_time` varchar(255) DEFAULT NULL,
  `grant_date` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car
-- ----------------------------

-- ----------------------------
-- Table structure for consume_record
-- ----------------------------
DROP TABLE IF EXISTS `consume_record`;
CREATE TABLE `consume_record` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `money` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `duration` varchar(255) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of consume_record
-- ----------------------------

-- ----------------------------
-- Table structure for coupon_base
-- ----------------------------
DROP TABLE IF EXISTS `coupon_base`;
CREATE TABLE `coupon_base` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `coupon_name` varchar(255) DEFAULT NULL,
  `coupon_publisher` varchar(255) DEFAULT NULL,
  `deduction` varchar(255) DEFAULT NULL,
  `discount` double(255,0) DEFAULT NULL,
  `money` double(255,0) DEFAULT NULL,
  `deadline` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `quota` varchar(255) DEFAULT NULL COMMENT '数量上限',
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of coupon_base
-- ----------------------------

-- ----------------------------
-- Table structure for coupon_user
-- ----------------------------
DROP TABLE IF EXISTS `coupon_user`;
CREATE TABLE `coupon_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `coupon_id` int(11) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of coupon_user
-- ----------------------------

-- ----------------------------
-- Table structure for driver
-- ----------------------------
DROP TABLE IF EXISTS `driver`;
CREATE TABLE `driver` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `nation` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `idcard` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `award_time` varchar(255) DEFAULT NULL,
  `validity` varchar(255) DEFAULT NULL,
  `awrad_unit` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of driver
-- ----------------------------

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
-- Table structure for parking_lot
-- ----------------------------
DROP TABLE IF EXISTS `parking_lot`;
CREATE TABLE `parking_lot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parking_name` varchar(255) DEFAULT NULL,
  `parking_address` varchar(255) DEFAULT NULL,
  `parking_num` varchar(255) DEFAULT NULL,
  `parking_price` double DEFAULT NULL,
  `longitude` varchar(255) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of parking_lot
-- ----------------------------

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
