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

 Date: 17/05/2019 16:14:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_ws3
-- ----------------------------
DROP TABLE IF EXISTS `tb_ws3`;
CREATE TABLE `tb_ws3`  (
  `No` int(11) NOT NULL AUTO_INCREMENT,
  `CampusCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ArchivingDep` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ClassificationNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PartNo` int(11) NULL DEFAULT NULL,
  `ReferenceNo` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DocumentNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PageNo` int(11) NULL DEFAULT NULL,
  `PersonLiable` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Annex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Date` date NULL DEFAULT NULL,
  `RetentionPeriod` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SecurityClassification` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Remarks` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `FilingTime` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_MODIFIED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `IS_DEL` int(11) NULL DEFAULT 0,
  `TABLE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_ws3
-- ----------------------------
INSERT INTO `tb_ws3` VALUES (1, '1.南京校区', '科技处', '2017-1WS12.1', 534, '20175017812', '2017-1WS12.1-534', '江苏省水利科技项目合同书：超高韧性水泥基复合材料修复水工混凝土结构裂缝的应用技术研究', 25, '河海大学', NULL, '2017-08-21', '永久', NULL, NULL, '201805', '2019-05-17 16:09:12', 0, NULL);
INSERT INTO `tb_ws3` VALUES (2, '1.南京校区', '科技处', '2017-1WS12.1', 532, '20175017512', '2017-1WS12.1-532', '中国水利水电科学研究院流域水循环模拟与调控国家重点实验室开放研究基金项目合同书：大体积比骨料周围界面微观结构调控下氯离子扩散行为研究', 13, '河海大学', NULL, '2017-05-21', '永久', NULL, NULL, '201805', '2019-05-17 16:10:17', 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
