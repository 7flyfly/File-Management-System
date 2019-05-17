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

 Date: 17/05/2019 16:13:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_template
-- ----------------------------
DROP TABLE IF EXISTS `tb_template`;
CREATE TABLE `tb_template`  (
  `template_id` int(11) NOT NULL AUTO_INCREMENT,
  `template_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `template_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `template_uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `field_id` int(11) NOT NULL,
  PRIMARY KEY (`template_id`) USING BTREE,
  INDEX `FK7a3jiv1x5bteaea7vy7xy61le`(`field_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_template
-- ----------------------------
INSERT INTO `tb_template` VALUES (1, '文书档案-文书-文件目录所使用的模板', '文书模板', 'T_p656ted5', 2);
INSERT INTO `tb_template` VALUES (2, '文书档案-已故人事-文件目录所使用的模板', '已故人事模板', 'T_2afe1x59', 2);
INSERT INTO `tb_template` VALUES (3, '文书档案-暂存文件-文件目录所使用的模板', '暂存文件模板', 'T_5zdy1n7u', 2);
INSERT INTO `tb_template` VALUES (4, '文书档案-河海资料/文件资料汇编-文件目录所使用的模板', '河海资料/文件资料汇编模板', 'T_decy39ux', 2);
INSERT INTO `tb_template` VALUES (5, '文书案卷-案卷目录/卷内目录', '文书案卷', 'T_uxefpvjk', 2);

SET FOREIGN_KEY_CHECKS = 1;
