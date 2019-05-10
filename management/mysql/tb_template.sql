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

 Date: 10/05/2019 09:26:42
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
) ENGINE = MyISAM AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_template
-- ----------------------------
INSERT INTO `tb_template` VALUES (1, '第一个模板demo', 'demo1', 'T_g8n0no6w', 2);
INSERT INTO `tb_template` VALUES (2, '第二个模板demo', 'demo2', 'T_9visuj78', 2);
INSERT INTO `tb_template` VALUES (3, '第三个模板demo', 'demo3', 'T_cvfsjzch', 2);
INSERT INTO `tb_template` VALUES (4, '第四个模板demo', 'demo4', 'T_on4aqgx3', 2);
INSERT INTO `tb_template` VALUES (5, '第五个模板demo', 'demo5', 'T_1kdai22i', 2);
INSERT INTO `tb_template` VALUES (6, '第六个模板demo', 'demo6', 'T_9ihljltf', 2);
INSERT INTO `tb_template` VALUES (7, '第七个模板demo', 'demo7', 'T_msbqs482', 2);
INSERT INTO `tb_template` VALUES (8, '第八个模板demo', 'demo8', 'T_29kmpmb3', 2);
INSERT INTO `tb_template` VALUES (9, '第九个模板demo', 'demo9', 'T_opu20d4g', 2);
INSERT INTO `tb_template` VALUES (10, '第十个模板demo', 'demo10', 'T_0rkgl218', 2);
INSERT INTO `tb_template` VALUES (11, '第十一个模板demo', 'demo11', 'T_mykv4u87', 2);

SET FOREIGN_KEY_CHECKS = 1;
