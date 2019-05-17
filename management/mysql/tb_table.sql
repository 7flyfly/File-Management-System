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

 Date: 17/05/2019 16:13:02
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
) ENGINE = MyISAM AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_table
-- ----------------------------
INSERT INTO `tb_table` VALUES (1, 'tb_ws', 'T_vhiwmibb', 1, 1);
INSERT INTO `tb_table` VALUES (2, 'tb_ygrs', 'T_5cc8d5ce', 1, 2);
INSERT INTO `tb_table` VALUES (3, 'tb_zc', 'T_nvedmng9', 1, 3);
INSERT INTO `tb_table` VALUES (4, 'tb_hhzl', 'T_zq7pxu5o', 1, 1);
INSERT INTO `tb_table` VALUES (5, 'tb_wjzl', 'T_nirxqzl2', 1, 4);
INSERT INTO `tb_table` VALUES (6, 'tb_wsaj', 'T_28481zvm', 1, 5);
INSERT INTO `tb_table` VALUES (7, 'tb_wsajn', 'T_8nnyvkk4', 1, 1);
INSERT INTO `tb_table` VALUES (8, 'tb_ws2', 'T_lm7fqd2e', 1, 1);
INSERT INTO `tb_table` VALUES (9, 'tb_ws3', 'T_p6yu6e45', 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
