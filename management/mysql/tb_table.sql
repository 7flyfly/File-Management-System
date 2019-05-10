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

 Date: 10/05/2019 09:26:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_table
-- ----------------------------
DROP TABLE IF EXISTS `tb_table`;
CREATE TABLE `tb_table`  (
  `table_id` int(11) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `table_uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `field_id` int(11) NOT NULL,
  `template_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`table_id`) USING BTREE,
  INDEX `FK9k8f8q0nthn89cnh4jojuhqr7`(`field_id`) USING BTREE,
  INDEX `FKkhtm9owdif522nel3sqrxivm1`(`template_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_table
-- ----------------------------
INSERT INTO `tb_table` VALUES (1, 'tb_test3', 'T_v5fqrpkd', 2, NULL);
INSERT INTO `tb_table` VALUES (2, 'tb_test1', 'T_xmoe2upl', 2, 1);
INSERT INTO `tb_table` VALUES (3, 'tb_test2', 'T_ks1r9v4g', 2, 1);

SET FOREIGN_KEY_CHECKS = 1;
