/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50528
 Source Host           : localhost:3306
 Source Schema         : db_filemanagement

 Target Server Type    : MySQL
 Target Server Version : 50528
 File Encoding         : 65001

 Date: 17/05/2019 16:13:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_table_field
-- ----------------------------
DROP TABLE IF EXISTS `tb_table_field`;
CREATE TABLE `tb_table_field`  (
  `table_id` int(11) NOT NULL,
  `field_id` int(11) NOT NULL,
  INDEX `FKsjmiiia3rk98myrggactx8xfj`(`field_id`) USING BTREE,
  INDEX `FK75xdx2rkj4mv13rq1r5ghwg3b`(`table_id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

SET FOREIGN_KEY_CHECKS = 1;
