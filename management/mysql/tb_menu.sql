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

 Date: 10/05/2019 09:26:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE = MyISAM AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES (1, '预立卷库', '', 0, '预立卷', 1, 'M_un8lpa1c', NULL, NULL);
INSERT INTO `tb_menu` VALUES (2, '预立卷库', '文书档案', 1, '文书档案', 1, 'M_lw6g0pbl', 1, NULL);
INSERT INTO `tb_menu` VALUES (3, '预立卷库', '', 2, '文书', 1, 'M_w5xxydyx', 2, NULL);
INSERT INTO `tb_menu` VALUES (4, '预立卷库', '', 3, '文件目录', 1, 'M_e8p1xo47', 3, 1);
INSERT INTO `tb_menu` VALUES (5, '预立卷库', '', 2, '已故人事', 2, 'M_5z5ix03r', 2, NULL);
INSERT INTO `tb_menu` VALUES (6, '预立卷库', '', 3, '文件目录', 1, 'M_vbimypoo', 5, 2);
INSERT INTO `tb_menu` VALUES (7, '预立卷库', '', 2, '暂存文件', 3, 'M_cqay8dzx', 2, NULL);
INSERT INTO `tb_menu` VALUES (8, '预立卷库', '', 3, '文件目录', 1, 'M_xzr91kb1', 7, NULL);
INSERT INTO `tb_menu` VALUES (9, '预立卷库', '', 2, '河海资料', 4, 'M_39ifso05', 2, NULL);
INSERT INTO `tb_menu` VALUES (10, '预立卷库', '', 3, '文件目录', 1, 'M_0dthrif3', 9, NULL);
INSERT INTO `tb_menu` VALUES (11, '预立卷库', '', 2, '文件资料汇编', 5, 'M_cc4s94qw', 2, NULL);
INSERT INTO `tb_menu` VALUES (12, '预立卷库', '', 3, '文件目录', 1, 'M_rsxnwrjq', 11, NULL);
INSERT INTO `tb_menu` VALUES (13, '预立卷库', '', 2, '文书案卷', 6, 'M_4nq56uen', 2, NULL);
INSERT INTO `tb_menu` VALUES (14, '预立卷库', '', 3, '案卷目录', 1, 'M_vmsxkv03', 13, NULL);
INSERT INTO `tb_menu` VALUES (15, '预立卷库', '', 3, '卷内目录', 2, 'M_eig2ibx8', 13, NULL);
INSERT INTO `tb_menu` VALUES (16, '预立卷库', '教学档案', 1, '教学档案', 2, 'M_aitlugzc', 1, NULL);
INSERT INTO `tb_menu` VALUES (17, '预立卷库', '', 2, '学籍卡', 1, 'M_ixkl6cgn', 16, NULL);
INSERT INTO `tb_menu` VALUES (18, '预立卷库', '', 3, '案卷目录', 1, 'M_pm631hd9', 17, NULL);
INSERT INTO `tb_menu` VALUES (19, '预立卷库', '', 3, '卷内目录', 2, 'M_ow98l91h', 17, NULL);
INSERT INTO `tb_menu` VALUES (20, '整理库', '', 0, '整理库', 2, 'M_160ml1o8', NULL, NULL);
INSERT INTO `tb_menu` VALUES (21, '整理库', '文书档案', 1, '文书档案', 1, 'M_02smr715', 20, NULL);
INSERT INTO `tb_menu` VALUES (22, '整理库', '', 2, '文书', 1, 'M_ba05vost', 21, NULL);
INSERT INTO `tb_menu` VALUES (23, '整理库', '', 3, '文件目录', 1, 'M_gudj58xy', 22, NULL);
INSERT INTO `tb_menu` VALUES (24, '整理库', '', 2, '已故人事', 2, 'M_4a2myndj', 21, NULL);
INSERT INTO `tb_menu` VALUES (25, '整理库', '', 3, '文件目录', 1, 'M_mjuasvpz', 24, NULL);
INSERT INTO `tb_menu` VALUES (26, '整理库', '', 2, '暂存文件', 3, 'M_2c44bvoc', 21, NULL);
INSERT INTO `tb_menu` VALUES (27, '整理库', '', 3, '文件目录', 1, 'M_hppx8sy8', 26, NULL);
INSERT INTO `tb_menu` VALUES (28, '整理库', '', 2, '河海资料', 4, 'M_2wzxdgib', 21, NULL);
INSERT INTO `tb_menu` VALUES (29, '整理库', '', 3, '文件目录', 1, 'M_fj6fr1az', 28, NULL);
INSERT INTO `tb_menu` VALUES (30, '整理库', '', 2, '文件资料汇编', 5, 'M_ry6zdnz6', 21, NULL);
INSERT INTO `tb_menu` VALUES (31, '整理库', '', 3, '文件目录', 1, 'M_ucom4ieb', 30, NULL);
INSERT INTO `tb_menu` VALUES (32, '整理库', '', 2, '文书案卷', 6, 'M_knh4tksr', 21, NULL);
INSERT INTO `tb_menu` VALUES (33, '整理库', '', 3, '案卷目录', 1, 'M_z0yc0t3c', 32, NULL);
INSERT INTO `tb_menu` VALUES (34, '整理库', '', 3, '卷内目录', 2, 'M_lbdff190', 32, NULL);
INSERT INTO `tb_menu` VALUES (35, '整理库', '教学档案', 1, '教学档案', 2, 'M_la9g1mif', 20, NULL);
INSERT INTO `tb_menu` VALUES (36, '整理库', '', 2, '学籍卡', 1, 'M_mbcj7yyn', 35, NULL);
INSERT INTO `tb_menu` VALUES (37, '整理库', '', 3, '案卷目录', 1, 'M_a7hgtpqr', 36, NULL);
INSERT INTO `tb_menu` VALUES (38, '整理库', '', 3, '卷内目录', 2, 'M_vbw0t09c', 36, NULL);

SET FOREIGN_KEY_CHECKS = 1;
