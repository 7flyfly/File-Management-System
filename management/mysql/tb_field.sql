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

 Date: 10/05/2019 09:26:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_field
-- ----------------------------
DROP TABLE IF EXISTS `tb_field`;
CREATE TABLE `tb_field`  (
  `field_id` int(11) NOT NULL AUTO_INCREMENT,
  `field_english_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `field_ik` bit(1) NULL DEFAULT b'0',
  `field_index` bit(1) NOT NULL,
  `field_length` int(11) NULL DEFAULT 0,
  `field_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `field_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `field_uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`field_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_field
-- ----------------------------
INSERT INTO `tb_field` VALUES (1, 'No', b'0', b'1', 11, '序号', 'int', 'F_po95xdtp');
INSERT INTO `tb_field` VALUES (2, 'DocumentNo', b'0', b'1', 50, '档案号', 'varchar', 'F_wt5uzdqy');
INSERT INTO `tb_field` VALUES (3, 'PartNo', b'0', b'0', 11, '案卷号', 'int', 'F_2agyabn3');
INSERT INTO `tb_field` VALUES (4, 'Title', b'1', b'1', 255, '题名', 'varchar', 'F_tx00eua4');
INSERT INTO `tb_field` VALUES (5, 'PersonLiable', b'0', b'1', 50, '责任者', 'varchar', 'F_oozeuwva');
INSERT INTO `tb_field` VALUES (6, 'CampusCode', b'0', b'1', 50, '校区代号', 'varchar', 'F_rkfjx4oh');
INSERT INTO `tb_field` VALUES (7, 'ArchivingDep', b'0', b'1', 50, '归档单位', 'varchar', 'F_aiovdc25');
INSERT INTO `tb_field` VALUES (8, 'ClassificationNo', b'0', b'0', 50, '实体分类号', 'varchar', 'F_7te03ynt');
INSERT INTO `tb_field` VALUES (9, 'PageNo', b'0', b'0', 11, '页数', 'int', 'F_gp7hbt0y');
INSERT INTO `tb_field` VALUES (10, 'Annex', b'0', b'0', 255, '附件', 'varchar', 'F_5orvldp8');
INSERT INTO `tb_field` VALUES (11, 'Date', b'0', b'1', 0, '日期', 'date', 'F_z1qxahnv');
INSERT INTO `tb_field` VALUES (12, 'SecurityClassification', b'0', b'0', 50, '密级', 'varchar', 'F_5l4ubu8e');
INSERT INTO `tb_field` VALUES (13, 'Remarks', b'0', b'0', 100, '备注', 'varchar', 'F_a35exg6l');
INSERT INTO `tb_field` VALUES (14, 'LAST_MODIFIED', b'0', b'0', 100, '最近修改时间', 'TIMESTAMP', 'F_zhgm8pwn');
INSERT INTO `tb_field` VALUES (15, 'IS_DEL', b'0', b'0', 11, '是否被删除', 'int', 'F_uguvpeb8');
INSERT INTO `tb_field` VALUES (16, 'TABLE_ID', b'0', b'0', 11, '表格号', 'int', 'F_i96vmjlt');

SET FOREIGN_KEY_CHECKS = 1;
