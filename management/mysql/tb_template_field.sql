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

 Date: 17/05/2019 16:13:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_template_field
-- ----------------------------
DROP TABLE IF EXISTS `tb_template_field`;
CREATE TABLE `tb_template_field`  (
  `template_id` int(11) NOT NULL,
  `field_id` int(11) NOT NULL,
  INDEX `FK7np8edahyokm248evwjmqlxb2`(`field_id`) USING BTREE,
  INDEX `FKdpt6ls46bxbp7dfulhxpxn1fb`(`template_id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of tb_template_field
-- ----------------------------
INSERT INTO `tb_template_field` VALUES (1, 1);
INSERT INTO `tb_template_field` VALUES (1, 6);
INSERT INTO `tb_template_field` VALUES (1, 7);
INSERT INTO `tb_template_field` VALUES (1, 8);
INSERT INTO `tb_template_field` VALUES (1, 3);
INSERT INTO `tb_template_field` VALUES (1, 17);
INSERT INTO `tb_template_field` VALUES (1, 2);
INSERT INTO `tb_template_field` VALUES (1, 4);
INSERT INTO `tb_template_field` VALUES (1, 9);
INSERT INTO `tb_template_field` VALUES (1, 5);
INSERT INTO `tb_template_field` VALUES (1, 10);
INSERT INTO `tb_template_field` VALUES (1, 11);
INSERT INTO `tb_template_field` VALUES (1, 18);
INSERT INTO `tb_template_field` VALUES (1, 12);
INSERT INTO `tb_template_field` VALUES (1, 13);
INSERT INTO `tb_template_field` VALUES (1, 19);
INSERT INTO `tb_template_field` VALUES (2, 1);
INSERT INTO `tb_template_field` VALUES (2, 6);
INSERT INTO `tb_template_field` VALUES (2, 2);
INSERT INTO `tb_template_field` VALUES (2, 3);
INSERT INTO `tb_template_field` VALUES (2, 4);
INSERT INTO `tb_template_field` VALUES (2, 5);
INSERT INTO `tb_template_field` VALUES (2, 20);
INSERT INTO `tb_template_field` VALUES (3, 1);
INSERT INTO `tb_template_field` VALUES (3, 2);
INSERT INTO `tb_template_field` VALUES (3, 21);
INSERT INTO `tb_template_field` VALUES (3, 22);
INSERT INTO `tb_template_field` VALUES (4, 1);
INSERT INTO `tb_template_field` VALUES (4, 2);
INSERT INTO `tb_template_field` VALUES (4, 8);
INSERT INTO `tb_template_field` VALUES (4, 23);
INSERT INTO `tb_template_field` VALUES (4, 24);
INSERT INTO `tb_template_field` VALUES (4, 25);
INSERT INTO `tb_template_field` VALUES (4, 26);
INSERT INTO `tb_template_field` VALUES (5, 1);
INSERT INTO `tb_template_field` VALUES (5, 2);
INSERT INTO `tb_template_field` VALUES (5, 27);
INSERT INTO `tb_template_field` VALUES (5, 4);
INSERT INTO `tb_template_field` VALUES (5, 5);
INSERT INTO `tb_template_field` VALUES (5, 28);
INSERT INTO `tb_template_field` VALUES (5, 26);
INSERT INTO `tb_template_field` VALUES (5, 25);
INSERT INTO `tb_template_field` VALUES (5, 18);
INSERT INTO `tb_template_field` VALUES (5, 7);
INSERT INTO `tb_template_field` VALUES (5, 8);
INSERT INTO `tb_template_field` VALUES (5, 12);
INSERT INTO `tb_template_field` VALUES (5, 29);
INSERT INTO `tb_template_field` VALUES (5, 30);

SET FOREIGN_KEY_CHECKS = 1;
