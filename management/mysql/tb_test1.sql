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

 Date: 10/05/2019 13:34:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_test1
-- ----------------------------
DROP TABLE IF EXISTS `tb_test1`;
CREATE TABLE `tb_test1`  (
  `No` int(11) NULL DEFAULT NULL,
  `PersonLiable` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DocumentNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `PartNo` int(11) NULL DEFAULT NULL,
  `Title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_MODIFIED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `IS_DEL` int(11) NULL DEFAULT 0,
  `TABLE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`DocumentNo`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_test1
-- ----------------------------
INSERT INTO `tb_test1` VALUES (2, '洪毅', '2017-1WS0908.2-151', 170, '1924年，河海工程专门学校与并入的国立东南大学工科成立河海工科大学，仍隶属于全国水利局，茅以升任首届校长。', '2019-04-26 16:17:30', 0, 2);
INSERT INTO `tb_test1` VALUES (5, '徐新', '2017-1WS0908.2-166', 171, '1939年，中央大学成立工学研究所，其中土木工程部下设水利工程组。', '2019-04-26 16:17:30', 0, 2);
INSERT INTO `tb_test1` VALUES (5, 'xx', '2017-1WS0908.2-171', 171, '1939年，中央大学成立工学研究所，其中土木工程部下设水利工程组。', '2019-05-10 10:24:30', 0, 2);
INSERT INTO `tb_test1` VALUES (5, '123', '456', 171, '1949年5月，成为南京大学工学院水利系。', '2019-05-10 09:44:31', 0, 2);

SET FOREIGN_KEY_CHECKS = 1;
