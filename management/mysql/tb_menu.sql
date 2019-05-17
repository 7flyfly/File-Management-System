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

 Date: 17/05/2019 16:12:53
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

SET FOREIGN_KEY_CHECKS = 1;
