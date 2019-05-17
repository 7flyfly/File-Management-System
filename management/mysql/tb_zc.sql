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

 Date: 17/05/2019 16:14:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_zc
-- ----------------------------
DROP TABLE IF EXISTS `tb_zc`;
CREATE TABLE `tb_zc`  (
  `No` int(11) NOT NULL AUTO_INCREMENT,
  `DocumentNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Prize` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PrizeTime` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_MODIFIED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `IS_DEL` int(11) NULL DEFAULT 0,
  `TABLE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
