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

 Date: 17/05/2019 16:14:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_wsaj
-- ----------------------------
DROP TABLE IF EXISTS `tb_wsaj`;
CREATE TABLE `tb_wsaj`  (
  `No` int(11) NOT NULL AUTO_INCREMENT,
  `DocumentNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TransactionNo` int(11) NULL DEFAULT NULL,
  `Title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PersonLiable` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Collaborator` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Designation` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CopieNo` int(11) NULL DEFAULT NULL,
  `RetentionPeriod` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ArchivingDep` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ClassificationNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SecurityClassification` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Specifications` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Subject` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_MODIFIED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `IS_DEL` int(11) NULL DEFAULT 0,
  `TABLE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
