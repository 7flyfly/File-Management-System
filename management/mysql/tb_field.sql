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

 Date: 17/05/2019 16:12:30
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
) ENGINE = MyISAM AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_field
-- ----------------------------
INSERT INTO `tb_field` VALUES (1, 'No', b'0', b'1', 11, '序号', 'int', 'F_6fjt03vu');
INSERT INTO `tb_field` VALUES (2, 'DocumentNo', b'0', b'1', 50, '档案号', 'varchar', 'F_5sb4m3ts');
INSERT INTO `tb_field` VALUES (3, 'PartNo', b'0', b'0', 11, '案卷号', 'int', 'F_anc2rym3');
INSERT INTO `tb_field` VALUES (4, 'Title', b'1', b'1', 255, '题名', 'varchar', 'F_2xx3bgfh');
INSERT INTO `tb_field` VALUES (5, 'PersonLiable', b'0', b'1', 50, '责任者', 'varchar', 'F_unt1z5qb');
INSERT INTO `tb_field` VALUES (6, 'CampusCode', b'0', b'1', 50, '校区代号', 'varchar', 'F_zh48yhon');
INSERT INTO `tb_field` VALUES (7, 'ArchivingDep', b'0', b'1', 50, '归档单位', 'varchar', 'F_zf19yk42');
INSERT INTO `tb_field` VALUES (8, 'ClassificationNo', b'0', b'0', 50, '实体分类号', 'varchar', 'F_hsk56eso');
INSERT INTO `tb_field` VALUES (9, 'PageNo', b'0', b'0', 11, '页数', 'int', 'F_1868cr7p');
INSERT INTO `tb_field` VALUES (10, 'Annex', b'0', b'0', 255, '附件', 'varchar', 'F_rvevrbcg');
INSERT INTO `tb_field` VALUES (11, 'Date', b'0', b'1', 0, '日期', 'date', 'F_yk56uu38');
INSERT INTO `tb_field` VALUES (12, 'SecurityClassification', b'0', b'0', 50, '密级', 'varchar', 'F_n3z9adw9');
INSERT INTO `tb_field` VALUES (13, 'Remarks', b'0', b'0', 100, '备注', 'varchar', 'F_m8yb29ed');
INSERT INTO `tb_field` VALUES (14, 'LAST_MODIFIED', b'0', b'0', 100, '最近修改时间', 'TIMESTAMP', 'F_6nyx0w7v');
INSERT INTO `tb_field` VALUES (15, 'IS_DEL', b'0', b'0', 11, '是否被删除', 'int', 'F_gh41pkku');
INSERT INTO `tb_field` VALUES (16, 'TABLE_ID', b'0', b'0', 11, '表格号', 'int', 'F_lg4outo1');
INSERT INTO `tb_field` VALUES (17, 'ReferenceNo', b'0', b'1', 123, '文号', 'varchar', 'F_scrmuoqn');
INSERT INTO `tb_field` VALUES (18, 'RetentionPeriod', b'0', b'1', 123, '保管期限', 'varchar', 'F_51pvgj0t');
INSERT INTO `tb_field` VALUES (19, 'FilingTime', b'0', b'0', 123, '归档时间', 'varchar', 'F_kj1dtqd1');
INSERT INTO `tb_field` VALUES (20, 'Name', b'0', b'1', 123, '姓名', 'varchar', 'F_qxea97gx');
INSERT INTO `tb_field` VALUES (21, 'Prize', b'0', b'1', 123, '获奖名称', 'varchar', 'F_bp7ej05a');
INSERT INTO `tb_field` VALUES (22, 'PrizeTime', b'0', b'0', 123, '获奖时间', 'varchar', 'F_s8tvv7uu');
INSERT INTO `tb_field` VALUES (23, 'ReceiveTime', b'0', b'0', 123, '收到时间', 'varchar', 'F_ych1szw6');
INSERT INTO `tb_field` VALUES (24, 'Source', b'0', b'0', 123, '来源', 'varchar', 'F_bq652448');
INSERT INTO `tb_field` VALUES (25, 'CopieNo', b'0', b'0', 11, '份数', 'int', 'F_izia1924');
INSERT INTO `tb_field` VALUES (26, 'Designation', b'0', b'0', 123, '名称', 'varchar', 'F_aa5sbgse');
INSERT INTO `tb_field` VALUES (27, 'TransactionNo', b'0', b'0', 11, '处理号', 'int', 'F_4jttcddj');
INSERT INTO `tb_field` VALUES (28, 'Collaborator', b'0', b'1', 123, '合作者', 'varchar', 'F_ea9h67q0');
INSERT INTO `tb_field` VALUES (29, 'Specifications', b'0', b'1', 123, '规格', 'varchar', 'F_2t8mc1bm');
INSERT INTO `tb_field` VALUES (30, 'Subject', b'0', b'1', 123, '主题词', 'varchar', 'F_2sh39gwk');

-- ----------------------------
-- Table structure for tb_hhzl
-- ----------------------------
DROP TABLE IF EXISTS `tb_hhzl`;
CREATE TABLE `tb_hhzl`  (
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu`  (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_classification` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `menu_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menu_level` int(11) NOT NULL,
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `menu_order` int(11) NULL DEFAULT NULL,
  `menu_uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` int(11) NULL DEFAULT NULL,
  `table_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`menu_id`) USING BTREE,
  INDEX `FKf6cync8rfoqw0tl3s3q8s1les`(`parent_id`) USING BTREE,
  INDEX `FKr0l8l6x0e6vlodqobysvw8o2e`(`table_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES (1, '预立库', '', 0, '预立库', 1, 'M_n93d3qys', NULL, NULL);
INSERT INTO `tb_menu` VALUES (2, '预立库', '文书档案', 1, '文书档案', 1, 'M_smjisktg', 1, NULL);
INSERT INTO `tb_menu` VALUES (3, '预立库', '', 2, '文书', 1, 'M_7e5eh7pg', 2, NULL);
INSERT INTO `tb_menu` VALUES (4, '预立库', '', 3, '文件目录', 1, 'M_ew34uwh2', 3, 1);
INSERT INTO `tb_menu` VALUES (5, '预立库', '', 2, '已故人事', 2, 'M_xv3s7nfk', 2, NULL);
INSERT INTO `tb_menu` VALUES (6, '预立库', '', 3, '文件目录', 1, 'M_1xvzwvls', 5, 2);
INSERT INTO `tb_menu` VALUES (7, '预立库', '', 2, '暂存文件', 3, 'M_x5nsw8t7', 2, NULL);
INSERT INTO `tb_menu` VALUES (8, '预立库', '', 3, '文件目录', 1, 'M_jhulhxc2', 7, 3);
INSERT INTO `tb_menu` VALUES (9, '预立库', '', 2, '河海资料', 4, 'M_3s222hkt', 2, NULL);
INSERT INTO `tb_menu` VALUES (10, '预立库', '', 3, '文件目录', 1, 'M_g0544fi0', 9, 4);
INSERT INTO `tb_menu` VALUES (11, '预立库', '', 2, '文件资料汇编', 5, 'M_tzkrk4vv', 2, NULL);
INSERT INTO `tb_menu` VALUES (12, '预立库', '', 3, '文件目录', 1, 'M_r1hpia39', 11, 5);
INSERT INTO `tb_menu` VALUES (13, '预立库', '', 2, '文书案卷', 6, 'M_ey1ufowf', 2, NULL);
INSERT INTO `tb_menu` VALUES (14, '预立库', '', 3, '案卷目录', 1, 'M_66m1vmyg', 13, 6);
INSERT INTO `tb_menu` VALUES (15, '预立库', '', 3, '卷内目录', 2, 'M_3jff2qnz', 13, 7);
INSERT INTO `tb_menu` VALUES (16, '预立库', '教学档案', 1, '教学档案', 2, 'M_tqv86fj4', 1, NULL);
INSERT INTO `tb_menu` VALUES (17, '预立库', '', 2, '学籍卡', 1, 'M_6is3s55h', 16, NULL);
INSERT INTO `tb_menu` VALUES (18, '预立库', '', 3, '案卷目录', 1, 'M_y30diqwl', 17, NULL);
INSERT INTO `tb_menu` VALUES (19, '预立库', '', 3, '卷内目录', 2, 'M_ch9cqs1x', 17, NULL);
INSERT INTO `tb_menu` VALUES (20, '整理库', '', 0, '整理库', 2, 'M_ia19ds3i', NULL, NULL);
INSERT INTO `tb_menu` VALUES (21, '整理库', '文书档案', 1, '文书档案', 1, 'M_oibxc0sk', 20, NULL);
INSERT INTO `tb_menu` VALUES (22, '整理库', '', 2, '文书', 1, 'M_zmx3k57n', 21, NULL);
INSERT INTO `tb_menu` VALUES (23, '整理库', '', 3, '文件目录', 1, 'M_w1mql04p', 22, 8);
INSERT INTO `tb_menu` VALUES (24, '整理库', '', 2, '已故人事', 2, 'M_h0135do6', 21, NULL);
INSERT INTO `tb_menu` VALUES (25, '整理库', '', 3, '文件目录', 1, 'M_3pyoevz9', 24, NULL);
INSERT INTO `tb_menu` VALUES (26, '整理库', '', 2, '暂存文件', 3, 'M_c1yjf19z', 21, NULL);
INSERT INTO `tb_menu` VALUES (27, '整理库', '', 3, '文件目录', 1, 'M_tgvx9t40', 26, NULL);
INSERT INTO `tb_menu` VALUES (28, '整理库', '', 2, '河海资料', 4, 'M_ay4ns6or', 21, NULL);
INSERT INTO `tb_menu` VALUES (29, '整理库', '', 3, '文件目录', 1, 'M_z5y64h0s', 28, NULL);
INSERT INTO `tb_menu` VALUES (30, '整理库', '', 2, '文件资料汇编', 5, 'M_yh1txuj9', 21, NULL);
INSERT INTO `tb_menu` VALUES (31, '整理库', '', 3, '文件目录', 1, 'M_guyuwjd8', 30, NULL);
INSERT INTO `tb_menu` VALUES (32, '整理库', '', 2, '文书案卷', 6, 'M_hptepaft', 21, NULL);
INSERT INTO `tb_menu` VALUES (33, '整理库', '', 3, '案卷目录', 1, 'M_fidi8xzt', 32, NULL);
INSERT INTO `tb_menu` VALUES (34, '整理库', '', 3, '卷内目录', 2, 'M_1o8ecqn8', 32, NULL);
INSERT INTO `tb_menu` VALUES (35, '整理库', '教学档案', 1, '教学档案', 2, 'M_n7jg26co', 20, NULL);
INSERT INTO `tb_menu` VALUES (36, '整理库', '', 2, '学籍卡', 1, 'M_inbrhqsm', 35, NULL);
INSERT INTO `tb_menu` VALUES (37, '整理库', '', 3, '案卷目录', 1, 'M_j567iadi', 36, NULL);
INSERT INTO `tb_menu` VALUES (38, '整理库', '', 3, '卷内目录', 2, 'M_y7kgu14k', 36, NULL);
INSERT INTO `tb_menu` VALUES (39, '档案库', '', 0, '档案库', 3, 'M_cu7ei2dz', NULL, NULL);
INSERT INTO `tb_menu` VALUES (40, '档案库', '文书档案', 1, '文书档案', 1, 'M_3jrbafso', 39, NULL);
INSERT INTO `tb_menu` VALUES (41, '档案库', '', 2, '文书', 1, 'M_yd9xjji6', 40, NULL);
INSERT INTO `tb_menu` VALUES (42, '档案库', '', 3, '文件目录', 1, 'M_w2dij5n8', 41, 9);
INSERT INTO `tb_menu` VALUES (43, '档案库', '', 2, '已故人事', 2, 'M_7dj4w646', 40, NULL);
INSERT INTO `tb_menu` VALUES (44, '档案库', '', 3, '文件目录', 1, 'M_9m39pz7c', 43, NULL);
INSERT INTO `tb_menu` VALUES (45, '档案库', '', 2, '暂存文件', 3, 'M_02cnr0qf', 40, NULL);
INSERT INTO `tb_menu` VALUES (46, '档案库', '', 3, '文件目录', 1, 'M_z5bc6ikv', 45, NULL);
INSERT INTO `tb_menu` VALUES (47, '档案库', '', 2, '河海资料', 4, 'M_mou9szrm', 40, NULL);
INSERT INTO `tb_menu` VALUES (48, '档案库', '', 3, '文件目录', 1, 'M_t4wgiffp', 47, NULL);
INSERT INTO `tb_menu` VALUES (49, '档案库', '', 2, '文件资料汇编', 5, 'M_i65k7l74', 40, NULL);
INSERT INTO `tb_menu` VALUES (50, '档案库', '', 3, '文件目录', 1, 'M_qwqi3dex', 49, NULL);
INSERT INTO `tb_menu` VALUES (51, '档案库', '', 2, '文书案卷', 6, 'M_ztomvs96', 40, NULL);
INSERT INTO `tb_menu` VALUES (52, '档案库', '', 3, '案卷目录', 1, 'M_n7tih830', 51, NULL);
INSERT INTO `tb_menu` VALUES (53, '档案库', '', 3, '卷内目录', 2, 'M_zgjogdaz', 51, NULL);
INSERT INTO `tb_menu` VALUES (54, '档案库', '教学档案', 1, '教学档案', 2, 'M_2kmsb811', 39, NULL);
INSERT INTO `tb_menu` VALUES (55, '档案库', '', 2, '学籍卡', 1, 'M_hgbio5zr', 54, NULL);
INSERT INTO `tb_menu` VALUES (56, '档案库', '', 3, '案卷目录', 1, 'M_wwu2hnop', 55, NULL);
INSERT INTO `tb_menu` VALUES (57, '档案库', '', 3, '卷内目录', 2, 'M_zync9eho', 55, NULL);

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

-- ----------------------------
-- Table structure for tb_table_field
-- ----------------------------
DROP TABLE IF EXISTS `tb_table_field`;
CREATE TABLE `tb_table_field`  (
  `table_id` int(11) NOT NULL,
  `field_id` int(11) NOT NULL,
  INDEX `FKsjmiiia3rk98myrggactx8xfj`(`field_id`) USING BTREE,
  INDEX `FK75xdx2rkj4mv13rq1r5ghwg3b`(`table_id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

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

-- ----------------------------
-- Table structure for tb_wjzl
-- ----------------------------
DROP TABLE IF EXISTS `tb_wjzl`;
CREATE TABLE `tb_wjzl`  (
  `No` int(11) NOT NULL AUTO_INCREMENT,
  `DocumentNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ClassificationNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ReceiveTime` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Source` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CopieNo` int(11) NULL DEFAULT NULL,
  `Designation` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_MODIFIED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `IS_DEL` int(11) NULL DEFAULT 0,
  `TABLE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_ws
-- ----------------------------
DROP TABLE IF EXISTS `tb_ws`;
CREATE TABLE `tb_ws`  (
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_ws2
-- ----------------------------
DROP TABLE IF EXISTS `tb_ws2`;
CREATE TABLE `tb_ws2`  (
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

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

-- ----------------------------
-- Table structure for tb_wsajn
-- ----------------------------
DROP TABLE IF EXISTS `tb_wsajn`;
CREATE TABLE `tb_wsajn`  (
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_ygrs
-- ----------------------------
DROP TABLE IF EXISTS `tb_ygrs`;
CREATE TABLE `tb_ygrs`  (
  `No` int(11) NOT NULL AUTO_INCREMENT,
  `CampusCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DocumentNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PartNo` int(11) NULL DEFAULT NULL,
  `Title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PersonLiable` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Name` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_MODIFIED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `IS_DEL` int(11) NULL DEFAULT 0,
  `TABLE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

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
