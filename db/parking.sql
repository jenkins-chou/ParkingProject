/*
 Navicat Premium Data Transfer

 Source Server         : administrator
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : parking

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 28/08/2019 19:34:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
BEGIN;
INSERT INTO `account` VALUES (1, '8', 15197, '1566744032', '1566921059', NULL, 'normal');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_recharge
-- ----------------------------
BEGIN;
INSERT INTO `account_recharge` VALUES (1, 8, NULL, 7885, 'success', NULL, '1566744032', NULL, 'normal');
INSERT INTO `account_recharge` VALUES (2, 8, NULL, 10000, 'success', NULL, '1566921059', NULL, 'normal');
COMMIT;

-- ----------------------------
-- Table structure for bankcard
-- ----------------------------
DROP TABLE IF EXISTS `bankcard`;
CREATE TABLE `bankcard` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  `bankcard_number` varchar(255) DEFAULT NULL,
  `bankcard_username` varchar(255) DEFAULT NULL,
  `bankcard_phone` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of bankcard
-- ----------------------------
BEGIN;
INSERT INTO `bankcard` VALUES (1, '8', 'dgg', 'ghgf', 'ghhf', 'uhggh', '1566744010', NULL, 'normal');
COMMIT;

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
-- Table structure for consume_record
-- ----------------------------
DROP TABLE IF EXISTS `consume_record`;
CREATE TABLE `consume_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of consume_record
-- ----------------------------
BEGIN;
INSERT INTO `consume_record` VALUES (1, 8, '100.0', 'online', 'success', 'none', '�Ż�ȯ�̵���������', '1566745302', 'none', 'normal');
INSERT INTO `consume_record` VALUES (2, 8, '288.0', 'online', 'success', 'none', '��λԤԼ��������', '1566746091', 'none', 'normal');
INSERT INTO `consume_record` VALUES (3, 8, '96.0', 'online', 'success', 'none', '��λԤԼ��������', '1566748079', 'none', 'normal');
INSERT INTO `consume_record` VALUES (4, 8, '48.0', 'online', 'success', 'none', '��λԤԼ��������', '1566748175', 'none', 'normal');
INSERT INTO `consume_record` VALUES (5, 8, '144.0', 'online', 'success', 'none', '��λԤԼ��������', '1566916919', 'none', 'normal');
INSERT INTO `consume_record` VALUES (6, 8, '1488.0', 'online', 'success', 'none', '停车场预约线上消费', '1566917885', 'none', 'normal');
INSERT INTO `consume_record` VALUES (7, 8, '100.0', 'online', 'success', 'none', '优惠券购买线上消费', '1566917898', 'none', 'normal');
INSERT INTO `consume_record` VALUES (8, 8, '48.0', 'online', 'success', 'none', '停车场预约线上消费', '1566918248', 'none', 'normal');
INSERT INTO `consume_record` VALUES (9, 8, '376.0', 'online', 'success', 'none', '停车场预约线上消费', '1566920262', 'none', 'normal');
INSERT INTO `consume_record` VALUES (10, 8, '0.0', 'online', 'success', 'none', '停车场预约线上消费', '1566920988', 'none', 'normal');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of coupon_base
-- ----------------------------
BEGIN;
INSERT INTO `coupon_base` VALUES (1, '代金券', '管理者', '200', 2, 100, '2019年8月', '类型', '3', '1566745217', '备注', 'normal');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of coupon_user
-- ----------------------------
BEGIN;
INSERT INTO `coupon_user` VALUES (1, 8, 1, '1566745302', NULL, 'delete');
INSERT INTO `coupon_user` VALUES (2, 8, 1, '1566917898', NULL, 'delete');
COMMIT;

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
BEGIN;
INSERT INTO `message` VALUES (3, '8', 'teacher', '何生', '13413608888', '5', 'student', '请完善你的班级信息', '1553568918', NULL, 'normal');
INSERT INTO `message` VALUES (4, '5', 'student', '周宁', '123456', '8', 'teacher', '好的老师', '1553570950', NULL, 'delete');
COMMIT;

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
  `latitude` varchar(255) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of parking_lot
-- ----------------------------
BEGIN;
INSERT INTO `parking_lot` VALUES (1, '停车场1', '广东省深圳市福田区华富街道梅岗南街深圳市第二人民医院', '30', 2, '114.086765', '22.557211', '详细', 'upload/default_parking_img.jpg', '1566745007', NULL, 'normal');
COMMIT;

-- ----------------------------
-- Table structure for parking_lot_appointment
-- ----------------------------
DROP TABLE IF EXISTS `parking_lot_appointment`;
CREATE TABLE `parking_lot_appointment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `parking_id` varchar(255) DEFAULT NULL,
  `money` varchar(255) DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of parking_lot_appointment
-- ----------------------------
BEGIN;
INSERT INTO `parking_lot_appointment` VALUES (1, '8', '1.0', '288.0', '1566746079', '1567005280', '2', 'success', 'BB+1cJ(=Ed/O', '1566746091', NULL, 'normal');
INSERT INTO `parking_lot_appointment` VALUES (2, '8', '1.0', '96.0', '1566748061', '1566834473', '2', 'success', 'cB+1cJ(=Ed/O', '1566748079', NULL, 'normal');
INSERT INTO `parking_lot_appointment` VALUES (3, '8', '1.0', '48.0', '1566834566', '1566920968', '1', 'success', 'u3hk>}O5m2$`', '1566748175', NULL, 'normal');
INSERT INTO `parking_lot_appointment` VALUES (4, '8', '1.0', '144.0', '1567003307', '1567262510', '1', 'success', '2]22GXu@x{r4', '1566916919', NULL, 'normal');
INSERT INTO `parking_lot_appointment` VALUES (5, '8', '1.0', '1488.0', '1566917867', '1569596278', '1', 'success', '13$$qqd67DKR', '1566917885', NULL, 'normal');
INSERT INTO `parking_lot_appointment` VALUES (6, '8', '1.0', '48.0', '1567004637', '1567091042', '1', 'success', '?$VnA{Lo`NtE', '1566918248', NULL, 'normal');
INSERT INTO `parking_lot_appointment` VALUES (7, '8', '1.0', '376.0', '1566920250', '1567179452', '4', 'success', 'm:V{7ZqL8?[U', '1566920262', NULL, 'normal');
INSERT INTO `parking_lot_appointment` VALUES (8, '8', '1.0', '0.0', '1566920740', '1567093301', '1', 'success', '7Ny);Esu[_09', '1566920988', NULL, 'normal');
COMMIT;

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
BEGIN;
INSERT INTO `user_base` VALUES (2, 'admin', '123456', '系统管理员', 'upload/1553408286963.jpg', '不个性何来个性签名', '女', '13', '123', '汉', '广东', '13413607283@163.com', '123456', '12345678910', '常住地址', '1996', 'student', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '无', 'normal');
INSERT INTO `user_base` VALUES (5, '6558455', '123', '周宁', 'upload/1553335338579.jpg', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '123', NULL, NULL, NULL, 'student', 'null', 'null', 'null', 'null', 'null', 'null', '1553332979', NULL, 'normal');
INSERT INTO `user_base` VALUES (8, NULL, '1234', '何生', 'upload/1553563210115.jpg', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1234', NULL, NULL, NULL, 'teacher', NULL, NULL, NULL, NULL, NULL, NULL, '1553497718', NULL, 'normal');
INSERT INTO `user_base` VALUES (10, '系统管理员', 'admin', '系统管理员', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', NULL, NULL, NULL, 'system', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'normal');
INSERT INTO `user_base` VALUES (19, '123452019-08-04', '12345', NULL, 'upload/1553335338579.jpg', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12345', NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, '1564905412', NULL, 'normal');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
