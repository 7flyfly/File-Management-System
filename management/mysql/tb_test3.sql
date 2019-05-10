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

 Date: 10/05/2019 13:34:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_test3
-- ----------------------------
DROP TABLE IF EXISTS `tb_test3`;
CREATE TABLE `tb_test3`  (
  `No` int(11) NULL DEFAULT NULL,
  `DocumentNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `PartNo` int(11) NULL DEFAULT NULL,
  `Title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PersonLiable` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Annex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_MODIFIED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `IS_DEL` int(11) NULL DEFAULT 0,
  `TABLE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`DocumentNo`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_test3
-- ----------------------------
INSERT INTO `tb_test3` VALUES (1, '2018-1WS0908.3-171', NULL, '河海大学西康路校区位于南京市鼓楼区清凉山北麓，占地800余亩。123', '洪毅', NULL, '2019-05-06 11:18:58', 0, 1);
INSERT INTO `tb_test3` VALUES (1, '2018-1WS0908.3-172', 8, '河海大学西康路校区位于南京市鼓楼区清凉山北麓，占地800余亩。', '洪毅', ';http://192.168.0.104/documents/solr-word.pdf;;http://192.168.0.104/documents/科学技术哲学在我国的发展状况及趋势.pdf', '2019-05-08 16:17:16', 0, 1);
INSERT INTO `tb_test3` VALUES (1, '2018-1WS0908.3-173', 8, '河海大学常州校区的前身为1986年成立的河海大学机械学院，1996年5月更名为河海大学常州分校，2000年6月更名为河海大学常州校区。', '洪毅', ';http://192.168.0.104/documents/科学技术哲学在我国的发展状况及趋势.pdf;http://192.168.0.104/documents/picture1.jpeg', '2019-05-10 09:21:13', 1, 1);
INSERT INTO `tb_test3` VALUES (1, '2018-1WS0908.3-175', 8, '根据2019年4月信息显示，河海大学江宁校区教学区位于南京市江宁开发区，占地面积863亩，学生生活区占地面积134亩，教职工住宅占地80亩，总面积1077亩。', '洪毅', ';http://192.168.0.104/documents/论文答辩及学位申请书.pdf', '2019-05-08 16:17:01', 0, 1);
INSERT INTO `tb_test3` VALUES (1, '2018-1WS0908.3-176', 8, '根据2019年4月信息显示，河海大学江宁校区教学区位于南京市江宁开发区，占地面积863亩，学生生活区占地面积134亩，教职工住宅占地80亩，总面积1077亩。', '洪毅', 'http://192.168.0.104/documents/picture2.jpeg;http://192.168.0.104/documents/picture3.jpeg;http://192.168.0.104/documents/picture4.jpeg;http://192.168.0.104/documents/picture4.jpeg;http://192.168.0.104/documents/picture5.jpeg;', '2019-05-08 16:19:21', 0, 1);
INSERT INTO `tb_test3` VALUES (1, '2018-1WS0908.3-177', 8, '根据2019年4月信息显示，河海大学江宁校区教学区位于南京市江宁开发区，占地面积863亩，学生生活区占地面积134亩，教职工住宅占地80亩，总面积1077亩。', '洪毅', 'http://192.168.0.104/documents/picture4.jpeg;http://192.168.0.104/documents/picture5.jpeg', '2019-05-08 16:17:55', 0, 1);
INSERT INTO `tb_test3` VALUES (1, '2018-1WS0908.3-178', 8, '根据2019年4月信息显示，河海大学江宁校区教学区位于南京市江宁开发区，占地面积863亩，学生生活区占地面积134亩，教职工住宅占地80亩，总面积1077亩。', '洪毅', 'http://192.168.0.104/documents/hh1.jpg;', '2019-05-08 21:06:23', 0, 1);
INSERT INTO `tb_test3` VALUES (1, '2018-1WS0908.3-179', 8, '根据2019年4月信息显示，河海大学江宁校区教学区位于南京市江宁开发区，占地面积863亩，学生生活区占地面积134亩，教职工住宅占地80亩，总面积1077亩。', '洪毅', 'http://192.168.0.104/documents/hh2.jpeg;', '2019-05-08 16:17:55', 0, 1);
INSERT INTO `tb_test3` VALUES (1, '2018-1WS0908.3-180', 8, '根据2019年4月信息显示，河海大学江宁校区教学区位于南京市江宁开发区，占地面积863亩，学生生活区占地面积134亩，教职工住宅占地80亩，总面积1077亩。', '洪毅', 'http://192.168.0.104/documents/hh3.jpeg;', '2019-05-08 16:17:55', 0, 1);
INSERT INTO `tb_test3` VALUES (1, '2018-1WS0908.3-181', 8, '根据2019年4月信息显示，河海大学江宁校区教学区位于南京市江宁开发区，占地面积863亩，学生生活区占地面积134亩，教职工住宅占地80亩，总面积1077亩。', '洪毅', 'http://192.168.0.104/documents/hh4.jpeg;', '2019-05-08 16:17:55', 0, 1);
INSERT INTO `tb_test3` VALUES (1, '2018-1WS0908.3-182', 8, '根据2019年4月信息显示，河海大学江宁校区教学区位于南京市江宁开发区，占地面积863亩，学生生活区占地面积134亩，教职工住宅占地80亩，总面积1077亩。', '洪毅', 'http://192.168.0.104/documents/hh5.jpg;', '2019-05-08 21:06:46', 0, 1);
INSERT INTO `tb_test3` VALUES (1, '2018-1WS0908.3-183', 8, '根据2019年4月信息显示，河海大学江宁校区教学区位于南京市江宁开发区，占地面积863亩，学生生活区占地面积134亩，教职工住宅占地80亩，总面积1077亩。', '洪毅', 'http://192.168.0.104/documents/hh6.jpg;', '2019-05-08 21:06:49', 0, 1);
INSERT INTO `tb_test3` VALUES (45, '6', 4, '8489', NULL, NULL, '2019-05-10 10:23:32', 1, NULL);

SET FOREIGN_KEY_CHECKS = 1;
