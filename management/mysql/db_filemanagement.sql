/*
 Navicat Premium Data Transfer

 Source Server         : jdbc
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : db_filemanagement

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 31/07/2019 15:24:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (17);

-- ----------------------------
-- Table structure for jsondata
-- ----------------------------
DROP TABLE IF EXISTS `jsondata`;
CREATE TABLE `jsondata`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `data` json NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jsondata
-- ----------------------------
INSERT INTO `jsondata` VALUES (1, '{\"age\": 18, \"name\": \"lison\", \"address\": \"enjoy\"}');
INSERT INTO `jsondata` VALUES (2, '{\"age\": 18, \"name\": \"lison\", \"address\": \"enjoy\"}');
INSERT INTO `jsondata` VALUES (3, '{\"age\": 28, \"mail\": \"james@163.com\", \"name\": \"james\"}');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(11) NOT NULL,
  `available` bit(1) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` bigint(20) NULL DEFAULT NULL,
  `parent_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resource_type` enum('menu','button') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_awgpnmh0eukj0uq98pcp26gdv`(`name`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, b'0', '利用登记', NULL, '', 'LibraryUse:Check', 'menu', 'LibraryUse/Check');
INSERT INTO `sys_permission` VALUES (2, b'0', '利用审批', NULL, '', 'LibraryUse:Examine', 'menu', 'LibraryUse/Examine');
INSERT INTO `sys_permission` VALUES (3, b'0', '利用查询', NULL, '', 'LibraryUse:Query', 'menu', 'LibraryUse/Query');
INSERT INTO `sys_permission` VALUES (17, b'0', '预立库', NULL, NULL, 'mywork:ylk', 'menu', 'mywork/homepage');
INSERT INTO `sys_permission` VALUES (4, b'0', '智能检索', NULL, NULL, 'IntegratedQuery:IntelligentRetrieval', 'menu', 'IntegratedQuery/IntelligentRetrieval');
INSERT INTO `sys_permission` VALUES (5, b'0', '高级搜索', NULL, NULL, 'IntegratedQuery:AdvancedSearch', 'menu', 'IntegratedQuery/AdvancedSearch');
INSERT INTO `sys_permission` VALUES (6, b'0', '知识图谱', NULL, NULL, 'IntegratedQuery:KnowledgeGraph', 'menu', 'IntegratedQuery/KnowledgeGraph');
INSERT INTO `sys_permission` VALUES (7, b'0', '专题', NULL, NULL, 'SpecialTopic:Special', 'menu', 'SpecialTopic/Special');
INSERT INTO `sys_permission` VALUES (8, b'0', '汇总统计', NULL, NULL, 'FormStatistics:TabulateStatistics', 'menu', 'FormStatistics/TabulateStatistics');
INSERT INTO `sys_permission` VALUES (9, b'0', '分项统计', NULL, NULL, 'FormStatistics:Breakdown', 'menu', 'FormStatistics/Breakdown');
INSERT INTO `sys_permission` VALUES (10, b'0', '元数据模板管理', NULL, NULL, 'metadata:MetadataTemplate', 'menu', 'metadata/MetadataTemplate');
INSERT INTO `sys_permission` VALUES (11, b'0', '元数据管理', NULL, NULL, 'metadata:MetadataManagement', 'menu', 'metadata/MetadataManagement');
INSERT INTO `sys_permission` VALUES (12, b'0', '数字词典', NULL, NULL, 'SystemManagement:DataDictionary', 'menu', 'SystemManagement/DataDictionary');
INSERT INTO `sys_permission` VALUES (13, b'0', '部门管理', NULL, NULL, 'SystemManagement:DepartmentManage', 'menu', 'SystemManagement/DepartmentManage');
INSERT INTO `sys_permission` VALUES (14, b'0', '用户管理', NULL, NULL, 'SystemManagement:UserManagement', 'menu', 'SystemManagement/UserManagement');
INSERT INTO `sys_permission` VALUES (15, b'0', '状态管理', NULL, NULL, 'SystemManagement:StateManagement', 'menu', 'SystemManagement/StateManagement');
INSERT INTO `sys_permission` VALUES (16, b'0', '动作管理', NULL, NULL, 'SystemManagement:ActionManagement', 'menu', 'SystemManagement/ActionManagement');
INSERT INTO `sys_permission` VALUES (18, b'0', '整理库', NULL, NULL, 'mywork:zlk', 'menu', 'mywork/homepage');
INSERT INTO `sys_permission` VALUES (19, b'0', '档案库', NULL, NULL, 'mywork:dak', 'menu', 'mywork/homepage');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `available` bit(1) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_mwbqlu5c82jfd2w9oa9d6e87d`(`role`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, b'0', '管理员', 'GLY');
INSERT INTO `sys_role` VALUES (2, b'0', '计算机学院档案管理者', 'JXYGLY');
INSERT INTO `sys_role` VALUES (3, b'0', '商学院管理员', 'SXYGLY');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  INDEX `FKomxrs8a388bknvhjokh440waq`(`permission_id`) USING BTREE,
  INDEX `FK9q28ewrhntqeipl1t04kh1be7`(`role_id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 16);
INSERT INTO `sys_role_permission` VALUES (1, 15);
INSERT INTO `sys_role_permission` VALUES (1, 14);
INSERT INTO `sys_role_permission` VALUES (1, 13);
INSERT INTO `sys_role_permission` VALUES (1, 12);
INSERT INTO `sys_role_permission` VALUES (1, 11);
INSERT INTO `sys_role_permission` VALUES (1, 10);
INSERT INTO `sys_role_permission` VALUES (1, 9);
INSERT INTO `sys_role_permission` VALUES (1, 8);
INSERT INTO `sys_role_permission` VALUES (1, 7);
INSERT INTO `sys_role_permission` VALUES (1, 6);
INSERT INTO `sys_role_permission` VALUES (1, 5);
INSERT INTO `sys_role_permission` VALUES (1, 4);
INSERT INTO `sys_role_permission` VALUES (1, 17);
INSERT INTO `sys_role_permission` VALUES (1, 1);
INSERT INTO `sys_role_permission` VALUES (1, 19);
INSERT INTO `sys_role_permission` VALUES (1, 2);
INSERT INTO `sys_role_permission` VALUES (2, 1);
INSERT INTO `sys_role_permission` VALUES (1, 3);
INSERT INTO `sys_role_permission` VALUES (3, 1);
INSERT INTO `sys_role_permission` VALUES (2, 3);
INSERT INTO `sys_role_permission` VALUES (2, 2);
INSERT INTO `sys_role_permission` VALUES (3, 3);
INSERT INTO `sys_role_permission` VALUES (2, 4);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `role_id` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  INDEX `FKgkmyslkrfeyn9ukmolvek8b8f`(`uid`) USING BTREE,
  INDEX `FKhh52n8vd4ny9ff4x9fb8v65qx`(`role_id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 3);
INSERT INTO `sys_user_role` VALUES (2, 1);
INSERT INTO `sys_user_role` VALUES (3, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (2, 4);
INSERT INTO `sys_user_role` VALUES (3, 3);

-- ----------------------------
-- Table structure for tb_action
-- ----------------------------
DROP TABLE IF EXISTS `tb_action`;
CREATE TABLE `tb_action`  (
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `exp` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `plug` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_action
-- ----------------------------
INSERT INTO `tb_action` VALUES ('关闭加湿器', 'Remind', '对各部门的提交资料进行收集', '湿度适宜！自动关闭加湿器。', NULL);
INSERT INTO `tb_action` VALUES ('关闭空调', 'Remind', '关闭空调', '温度适宜！自动关闭空调。', NULL);
INSERT INTO `tb_action` VALUES ('加湿', 'Remind', '对应的请求返回对应的信息', '湿度过低！自动打开加湿器。', NULL);
INSERT INTO `tb_action` VALUES ('升温', 'Remind', '开启空调，降温', '温度过低！自动打开空调。', NULL);
INSERT INTO `tb_action` VALUES ('处理到期档案', 'Action', '处理到期档案', '存在纸质档案到期，请及时处理！', NULL);
INSERT INTO `tb_action` VALUES ('处理到期电子档案', 'Action', '电子档案到期', '存在电子档案到期，请及时处理！', NULL);
INSERT INTO `tb_action` VALUES ('处理档案请求', 'Action', '前往查看请求', '新的档案申请，请及时处理！', NULL);
INSERT INTO `tb_action` VALUES ('打开消防', 'Alarm', '打开消防设施', '发生火情，请及时撤离！', NULL);
INSERT INTO `tb_action` VALUES ('报警', NULL, NULL, NULL, NULL);
INSERT INTO `tb_action` VALUES ('查看归档情况', 'Message', '归档工作完成', '归档工作已完成，完成度100%！', NULL);
INSERT INTO `tb_action` VALUES ('查看鉴定', 'Message', '鉴定工作完成', '鉴定工作已完成，完成度100%！', NULL);
INSERT INTO `tb_action` VALUES ('降温', 'Remind', '开启空调，提升温度！', '温度过低！自动打开空调。', NULL);
INSERT INTO `tb_action` VALUES ('除湿', 'Remind', '对收集的档案进行整理', '湿度过高！自动开启加湿器。', NULL);

-- ----------------------------
-- Table structure for tb_bksyxbysj
-- ----------------------------
DROP TABLE IF EXISTS `tb_bksyxbysj`;
CREATE TABLE `tb_bksyxbysj`  (
  `No` int(11) NOT NULL AUTO_INCREMENT,
  `TotalPackages` int(11) NULL DEFAULT NULL,
  `Number` int(11) NULL DEFAULT NULL,
  `Title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PersonLiable` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ArchivingDep` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SecurityClassification` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Remarks` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `RetentionPeriod` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `FilingTime` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TransactionNo` int(11) NULL DEFAULT NULL,
  `Collaborator` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `BeginTime` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `EndTime` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TypingPerson` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DocumentNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_MODIFIED` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `IS_DEL` int(11) NULL DEFAULT 0,
  `TABLE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_bksyxbysjn
-- ----------------------------
DROP TABLE IF EXISTS `tb_bksyxbysjn`;
CREATE TABLE `tb_bksyxbysjn`  (
  `No` int(11) NOT NULL AUTO_INCREMENT,
  `PageNumber` int(11) NULL DEFAULT NULL,
  `PartNo` int(11) NULL DEFAULT NULL,
  `Title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PersonLiable` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Annex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Remarks` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ReferenceNo` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `RetentionPeriod` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TypingPerson` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DocumentDate` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DocumentNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_MODIFIED` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `IS_DEL` int(11) NULL DEFAULT 0,
  `TABLE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_databases
-- ----------------------------
DROP TABLE IF EXISTS `tb_databases`;
CREATE TABLE `tb_databases`  (
  `documentno` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `begindate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `boxno` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `caseno` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `classification` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deadline` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `docunmentnum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `eaddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enddate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rdate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ryear` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `security` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `subject` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `filestore` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`documentno`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_databases
-- ----------------------------
INSERT INTO `tb_databases` VALUES ('2019SW0001', '2019-05-10', 'B001', 'C', '实物', '2019-05-10', 'admin', '2020-05-10', '声像', '4', '声像库', '2019-05-10', '声像库', '2019-05-10', 'D001', '2019', '公开', '已入库', '声像', '声像', '河海大学', '声像档案');
INSERT INTO `tb_databases` VALUES ('123', '12', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '声像档案');

-- ----------------------------
-- Table structure for tb_department
-- ----------------------------
DROP TABLE IF EXISTS `tb_department`;
CREATE TABLE `tb_department`  (
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `charger` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fax` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_department
-- ----------------------------
INSERT INTO `tb_department` VALUES ('校办', '李大大', 'XB', '', '', '', '');
INSERT INTO `tb_department` VALUES ('计算机信息学院', '张某某', 'JXY', '', '', '', '13514701123');

-- ----------------------------
-- Table structure for tb_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `tb_dictionary`;
CREATE TABLE `tb_dictionary`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dictionarycode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dictionaryname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sequence` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dictionary` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_dictionary
-- ----------------------------
INSERT INTO `tb_dictionary` VALUES (13, 'W', '', 'FLH', '分类号', '文书档案', '', '1', NULL);
INSERT INTO `tb_dictionary` VALUES (5, NULL, NULL, 'FLH', '分类号', NULL, NULL, NULL, NULL);
INSERT INTO `tb_dictionary` VALUES (12, 'HHU', '', 'QZH', '全宗号', '河海大学', '', '1', NULL);
INSERT INTO `tb_dictionary` VALUES (11, NULL, NULL, 'QZH', '全宗号', NULL, NULL, NULL, NULL);
INSERT INTO `tb_dictionary` VALUES (14, 'K', '', 'FLH', '分类号', '科技档案', '', '2', NULL);
INSERT INTO `tb_dictionary` VALUES (15, 'X', '', 'FLH', '分类号', '声像档案', '', '3', NULL);
INSERT INTO `tb_dictionary` VALUES (16, 'J', '', 'FLH', '分类号', '学籍档案', '', '4', NULL);
INSERT INTO `tb_dictionary` VALUES (17, NULL, NULL, 'GDDW', '归档单位', NULL, NULL, NULL, NULL);
INSERT INTO `tb_dictionary` VALUES (18, 'XB', '', 'GDDW', '归档单位', '校办', '', '1', NULL);
INSERT INTO `tb_dictionary` VALUES (19, 'JXY', '', 'GDDW', '归档单位', '计算机信息学院', '', '2', NULL);
INSERT INTO `tb_dictionary` VALUES (20, NULL, NULL, 'ZJLX', '证件类型', NULL, NULL, NULL, NULL);
INSERT INTO `tb_dictionary` VALUES (21, 'SFZ', '', 'ZJLX', '证件类型', '身份证', '', '1', NULL);
INSERT INTO `tb_dictionary` VALUES (22, 'XSZ', '', 'ZJLX', '证件类型', '学生证', '', '2', NULL);
INSERT INTO `tb_dictionary` VALUES (23, 'JSZ', '', 'ZJLX', '证件类型', '教师证', '', '3', NULL);
INSERT INTO `tb_dictionary` VALUES (24, NULL, NULL, 'ZT', '状态', NULL, NULL, NULL, NULL);
INSERT INTO `tb_dictionary` VALUES (25, 'DSP', '', 'ZT', '状态', '待审批', '', '1', NULL);
INSERT INTO `tb_dictionary` VALUES (26, 'TG', '', 'ZT', '状态', '通过', '', '2', NULL);
INSERT INTO `tb_dictionary` VALUES (27, 'WTG', '', 'ZT', '状态', '未通过', '', '3', NULL);
INSERT INTO `tb_dictionary` VALUES (28, 'GH', '', 'ZT', '状态', '归还', '', '4', NULL);
INSERT INTO `tb_dictionary` VALUES (29, NULL, NULL, 'XQDH', '校区代号', NULL, NULL, NULL, NULL);
INSERT INTO `tb_dictionary` VALUES (30, 'NJXQ', '', 'XQDH', '校区代号', '1.南京校区', '', '1', NULL);
INSERT INTO `tb_dictionary` VALUES (31, 'CZXQ', '', 'XQDH', '校区代号', '2.常州校区', '', '2', NULL);
INSERT INTO `tb_dictionary` VALUES (1, NULL, NULL, 'BGQX', '保管期限', NULL, NULL, NULL, NULL);
INSERT INTO `tb_dictionary` VALUES (2, 'YJ', '', 'BGQX', '保管期限', '永久', '', '1', NULL);
INSERT INTO `tb_dictionary` VALUES (4, 'DQS', '', 'BGQX', '保管期限', '定期10', '', '2', NULL);
INSERT INTO `tb_dictionary` VALUES (6, 'DQSS', '', 'BGQX', '保管期限', '定期30', '', '3', NULL);
INSERT INTO `tb_dictionary` VALUES (7, NULL, NULL, 'MJ', '密级', NULL, NULL, NULL, NULL);
INSERT INTO `tb_dictionary` VALUES (8, 'GK', '', 'MJ', '密级', '公开', '', '1', NULL);
INSERT INTO `tb_dictionary` VALUES (9, 'NB', '', 'MJ', '密级', '内部', '', '2', NULL);
INSERT INTO `tb_dictionary` VALUES (10, 'MM', '', 'MJ', '密级', '秘密', '', '3', NULL);
INSERT INTO `tb_dictionary` VALUES (37, 'JM', '', 'MJ', '密级', '绝密', '', '5', NULL);
INSERT INTO `tb_dictionary` VALUES (36, 'JM', '', 'MJ', '密级', '机密', '', '4', NULL);

-- ----------------------------
-- Table structure for tb_expiredfile
-- ----------------------------
DROP TABLE IF EXISTS `tb_expiredfile`;
CREATE TABLE `tb_expiredfile`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `add` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` date NULL DEFAULT NULL,
  `charge` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20190505 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_expiredfile
-- ----------------------------
INSERT INTO `tb_expiredfile` VALUES (20190501, '大数据技术', '科技档案', '一区201901', '2019-05-17', '张三');
INSERT INTO `tb_expiredfile` VALUES (20190502, '文本挖掘技术', '科技档案', '二区201902', '2019-04-04', '李四');
INSERT INTO `tb_expiredfile` VALUES (20190503, '2019界校友活动', '人文档案', '三区201903', '2019-04-11', '王五');
INSERT INTO `tb_expiredfile` VALUES (20190504, '2019年教学活动', '人文档案', '四区201904', '2019-03-13', '张三');

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
) ENGINE = MyISAM AUTO_INCREMENT = 105 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `tb_field` VALUES (31, 'BeginTime', b'0', b'0', 123, '开始时间', 'varchar', 'F_8ujnf7ga');
INSERT INTO `tb_field` VALUES (32, 'EndTime', b'0', b'0', 123, '结束时间', 'varchar', 'F_jkrir5ph');
INSERT INTO `tb_field` VALUES (33, 'TypingPerson', b'1', b'1', 123, '输入员', 'varchar', 'F_wnjmyg4s');
INSERT INTO `tb_field` VALUES (34, 'Sex', b'1', b'1', 123, '性别', 'varchar', 'F_3hvp3ozp');
INSERT INTO `tb_field` VALUES (35, 'EducationalSystem', b'1', b'1', 11, '学制', 'varchar', 'F_mjcqchjn');
INSERT INTO `tb_field` VALUES (36, 'DocumentDate', b'1', b'1', 123, '文件日期', 'varchar', 'F_3uylf1iu');
INSERT INTO `tb_field` VALUES (37, 'Major', b'1', b'1', 123, '专业名', 'varchar', 'F_dgix2cck');
INSERT INTO `tb_field` VALUES (38, 'IdNo', b'1', b'1', 123, '身份证', 'varchar', 'F_20arf7zg');
INSERT INTO `tb_field` VALUES (39, 'EnrollmentPage', b'0', b'0', 255, '录取页', 'varchar', 'F_ioczjlfx');
INSERT INTO `tb_field` VALUES (40, 'CertificateSignature ', b'0', b'0', 255, '证书签收页', 'varchar', 'F_0vbdbluu');
INSERT INTO `tb_field` VALUES (41, 'TotalPackages', b'0', b'0', 11, '总件数', 'int', 'F_77e3hodf');
INSERT INTO `tb_field` VALUES (42, 'Number', b'0', b'0', 11, '数量', 'int', 'F_ga8urlgs');
INSERT INTO `tb_field` VALUES (43, 'PageNumber', b'0', b'0', 11, '页次', 'int', 'F_0po5aa1j');
INSERT INTO `tb_field` VALUES (44, 'SeriesArea', b'0', b'0', 123, '丛编项', 'varchar', 'F_570k1jsc');

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
  `LAST_MODIFIED` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `IS_DEL` int(11) NULL DEFAULT 0,
  `TABLE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = MyISAM AUTO_INCREMENT = 267 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `tb_menu` VALUES (18, '预立库', '', 3, '案卷目录', 1, 'M_y30diqwl', 17, 11);
INSERT INTO `tb_menu` VALUES (19, '预立库', '', 3, '卷内目录', 2, 'M_ch9cqs1x', 17, 12);
INSERT INTO `tb_menu` VALUES (20, '整理库', '', 0, '整理库', 2, 'M_ia19ds3i', NULL, NULL);
INSERT INTO `tb_menu` VALUES (21, '整理库', '文书档案', 1, '文书档案', 1, 'M_oibxc0sk', 20, NULL);
INSERT INTO `tb_menu` VALUES (22, '整理库', '', 2, '文书', 1, 'M_zmx3k57n', 21, NULL);
INSERT INTO `tb_menu` VALUES (23, '整理库', '', 3, '文件目录', 1, 'M_w1mql04p', 22, 8);
INSERT INTO `tb_menu` VALUES (24, '整理库', '', 2, '已故人事', 2, 'M_h0135do6', 21, NULL);
INSERT INTO `tb_menu` VALUES (25, '整理库', '', 3, '文件目录', 1, 'M_3pyoevz9', 24, 10);
INSERT INTO `tb_menu` VALUES (26, '整理库', '', 2, '暂存文件', 3, 'M_c1yjf19z', 21, NULL);
INSERT INTO `tb_menu` VALUES (27, '整理库', '', 3, '文件目录', 1, 'M_tgvx9t40', 26, 20);
INSERT INTO `tb_menu` VALUES (28, '整理库', '', 2, '河海资料', 4, 'M_ay4ns6or', 21, NULL);
INSERT INTO `tb_menu` VALUES (29, '整理库', '', 3, '文件目录', 1, 'M_z5y64h0s', 28, 21);
INSERT INTO `tb_menu` VALUES (30, '整理库', '', 2, '文件资料汇编', 5, 'M_yh1txuj9', 21, NULL);
INSERT INTO `tb_menu` VALUES (31, '整理库', '', 3, '文件目录', 1, 'M_guyuwjd8', 30, 22);
INSERT INTO `tb_menu` VALUES (32, '整理库', '', 2, '文书案卷', 6, 'M_hptepaft', 21, NULL);
INSERT INTO `tb_menu` VALUES (33, '整理库', '', 3, '案卷目录', 1, 'M_fidi8xzt', 32, 23);
INSERT INTO `tb_menu` VALUES (34, '整理库', '', 3, '卷内目录', 2, 'M_1o8ecqn8', 32, 33);
INSERT INTO `tb_menu` VALUES (35, '整理库', '教学档案', 1, '教学档案', 2, 'M_n7jg26co', 20, NULL);
INSERT INTO `tb_menu` VALUES (36, '整理库', '', 2, '学籍卡', 1, 'M_inbrhqsm', 35, NULL);
INSERT INTO `tb_menu` VALUES (37, '整理库', '', 3, '案卷目录', 1, 'M_j567iadi', 36, 24);
INSERT INTO `tb_menu` VALUES (38, '整理库', '', 3, '卷内目录', 2, 'M_y7kgu14k', 36, 25);
INSERT INTO `tb_menu` VALUES (39, '档案库', '', 0, '档案库', 3, 'M_cu7ei2dz', NULL, NULL);
INSERT INTO `tb_menu` VALUES (40, '档案库', '文书档案', 1, '文书档案', 1, 'M_3jrbafso', 39, NULL);
INSERT INTO `tb_menu` VALUES (41, '档案库', '', 2, '文书', 1, 'M_yd9xjji6', 40, NULL);
INSERT INTO `tb_menu` VALUES (42, '档案库', '', 3, '文件目录', 1, 'M_w2dij5n8', 41, 9);
INSERT INTO `tb_menu` VALUES (43, '档案库', '', 2, '已故人事', 2, 'M_7dj4w646', 40, NULL);
INSERT INTO `tb_menu` VALUES (44, '档案库', '', 3, '文件目录', 1, 'M_9m39pz7c', 43, 34);
INSERT INTO `tb_menu` VALUES (45, '档案库', '', 2, '暂存文件', 3, 'M_02cnr0qf', 40, NULL);
INSERT INTO `tb_menu` VALUES (46, '档案库', '', 3, '文件目录', 1, 'M_z5bc6ikv', 45, 35);
INSERT INTO `tb_menu` VALUES (47, '档案库', '', 2, '河海资料', 4, 'M_mou9szrm', 40, NULL);
INSERT INTO `tb_menu` VALUES (48, '档案库', '', 3, '文件目录', 1, 'M_t4wgiffp', 47, 36);
INSERT INTO `tb_menu` VALUES (49, '档案库', '', 2, '文件资料汇编', 5, 'M_i65k7l74', 40, NULL);
INSERT INTO `tb_menu` VALUES (50, '档案库', '', 3, '文件目录', 1, 'M_qwqi3dex', 49, 37);
INSERT INTO `tb_menu` VALUES (51, '档案库', '', 2, '文书案卷', 6, 'M_ztomvs96', 40, NULL);
INSERT INTO `tb_menu` VALUES (52, '档案库', '', 3, '案卷目录', 1, 'M_n7tih830', 51, 38);
INSERT INTO `tb_menu` VALUES (53, '档案库', '', 3, '卷内目录', 2, 'M_zgjogdaz', 51, 39);
INSERT INTO `tb_menu` VALUES (54, '档案库', '教学档案', 1, '教学档案', 2, 'M_2kmsb811', 39, NULL);
INSERT INTO `tb_menu` VALUES (55, '档案库', '', 2, '学籍卡', 1, 'M_hgbio5zr', 54, NULL);
INSERT INTO `tb_menu` VALUES (56, '档案库', '', 3, '案卷目录', 1, 'M_wwu2hnop', 55, 40);
INSERT INTO `tb_menu` VALUES (57, '档案库', '', 3, '卷内目录', 2, 'M_zync9eho', 55, 41);
INSERT INTO `tb_menu` VALUES (59, '预立库', '', 2, '学生文书档案', 2, 'M_1tbp4hka', 16, NULL);
INSERT INTO `tb_menu` VALUES (60, '预立库', '', 3, '文件目录', 1, 'M_0plo5ujw', 59, 13);
INSERT INTO `tb_menu` VALUES (61, '预立库', '', 2, '本科生优秀毕业设计', 3, 'M_s5nvca9o', 16, NULL);
INSERT INTO `tb_menu` VALUES (62, '预立库', '', 3, '案卷目录', 1, 'M_4c60c4wv', 61, 14);
INSERT INTO `tb_menu` VALUES (63, '预立库', '', 3, '卷内目录', 2, 'M_99bao632', 61, 15);
INSERT INTO `tb_menu` VALUES (64, '预立库', '', 2, '学生成绩', 4, 'M_w71hqd0k', 16, NULL);
INSERT INTO `tb_menu` VALUES (65, '预立库', '', 2, '研究生业务', 5, 'M_rrravbkh', 16, NULL);
INSERT INTO `tb_menu` VALUES (66, '预立库', '', 3, '案卷目录', 1, 'M_uh7ot68g', 64, 16);
INSERT INTO `tb_menu` VALUES (67, '预立库', '', 3, '卷内目录', 2, 'M_a8oclfs2', 64, 17);
INSERT INTO `tb_menu` VALUES (70, '预立库', '', 3, '案卷目录', 1, 'M_dmkfk36l', 65, 18);
INSERT INTO `tb_menu` VALUES (71, '预立库', '', 3, '卷内目录', 2, 'M_0bgriq33', 65, 19);
INSERT INTO `tb_menu` VALUES (72, '预立库', '', 2, '学生人头库', 6, 'M_8hxwh9rc', 16, NULL);
INSERT INTO `tb_menu` VALUES (73, '预立库', '', 2, '教学案卷', 7, 'M_ezsp103o', 16, NULL);
INSERT INTO `tb_menu` VALUES (74, '预立库', '', 1, '科研档案', 3, 'M_cbwb1zbz', 1, NULL);
INSERT INTO `tb_menu` VALUES (75, '预立库', '', 1, '基建档案', 4, 'M_6bjckd6g', 1, NULL);
INSERT INTO `tb_menu` VALUES (76, '预立库', '', 1, '设备档案', 5, 'M_cpogpmc0', 1, NULL);
INSERT INTO `tb_menu` VALUES (77, '预立库', '', 1, '出版档案', 6, 'M_qbvudira', 1, NULL);
INSERT INTO `tb_menu` VALUES (78, '预立库', '', 1, '财会档案', 7, 'M_818gm405', 1, NULL);
INSERT INTO `tb_menu` VALUES (79, '预立库', '', 2, '案卷目录', 1, 'M_vooau9pb', 74, NULL);
INSERT INTO `tb_menu` VALUES (80, '预立库', '', 2, '案卷目录', 1, 'M_38jalpnk', 75, NULL);
INSERT INTO `tb_menu` VALUES (81, '预立库', '', 2, '案卷目录', 1, 'M_uu98567b', 76, NULL);
INSERT INTO `tb_menu` VALUES (82, '预立库', '', 2, '案卷目录', 1, 'M_z5bw7ilt', 77, NULL);
INSERT INTO `tb_menu` VALUES (83, '预立库', '', 2, '卷内目录', 2, 'M_di03ywgb', 74, NULL);
INSERT INTO `tb_menu` VALUES (84, '预立库', '', 2, '卷内目录', 2, 'M_jjd86mzt', 75, NULL);
INSERT INTO `tb_menu` VALUES (85, '预立库', '', 2, '卷内目录', 2, 'M_k1mznm49', 76, NULL);
INSERT INTO `tb_menu` VALUES (86, '预立库', '', 2, '卷内目录', 2, 'M_i9pmj5eb', 77, NULL);
INSERT INTO `tb_menu` VALUES (87, '预立库', '', 2, '财务处', 1, 'M_2t2j2s59', 78, NULL);
INSERT INTO `tb_menu` VALUES (88, '预立库', '', 2, '二级单位', 2, 'M_i0zitrwg', 78, NULL);
INSERT INTO `tb_menu` VALUES (89, '预立库', '', 3, '案卷目录', 1, 'M_m2wumm4j', 87, NULL);
INSERT INTO `tb_menu` VALUES (90, '预立库', '', 3, '卷内目录', 2, 'M_i5ksyed5', 87, NULL);
INSERT INTO `tb_menu` VALUES (91, '整理库', '', 1, '科研档案', 3, 'M_ycsxkicy', 20, NULL);
INSERT INTO `tb_menu` VALUES (92, '整理库', '', 1, '基建档案', 4, 'M_0iw4o1cs', 20, NULL);
INSERT INTO `tb_menu` VALUES (93, '整理库', '', 1, '设备档案', 5, 'M_nca5zipw', 20, NULL);
INSERT INTO `tb_menu` VALUES (94, '整理库', '', 1, '出版档案', 6, 'M_iyyk1dc1', 20, NULL);
INSERT INTO `tb_menu` VALUES (95, '整理库', '', 1, '财会档案', 7, 'M_7jvabi9j', 20, NULL);
INSERT INTO `tb_menu` VALUES (96, '整理库', '', 2, '学生文书档案', 2, 'M_sk87wehb', 35, NULL);
INSERT INTO `tb_menu` VALUES (97, '整理库', '', 2, '本科生优秀毕业设计', 3, 'M_0t68uutr', 35, NULL);
INSERT INTO `tb_menu` VALUES (98, '整理库', '', 2, '学生成绩', 4, 'M_i3i78ncy', 35, NULL);
INSERT INTO `tb_menu` VALUES (99, '整理库', '', 2, '研究生业务', 5, 'M_bvu0oixt', 35, NULL);
INSERT INTO `tb_menu` VALUES (100, '整理库', '', 2, '学生人头库', 6, 'M_pft9nxos', 35, NULL);
INSERT INTO `tb_menu` VALUES (101, '整理库', '', 2, '教学案卷', 7, 'M_e9cybsf2', 35, NULL);
INSERT INTO `tb_menu` VALUES (102, '整理库', '', 3, '文件目录', 1, 'M_et9pr54r', 96, 26);
INSERT INTO `tb_menu` VALUES (103, '整理库', '', 3, '案卷目录', 1, 'M_6vf2g8mo', 97, 27);
INSERT INTO `tb_menu` VALUES (104, '整理库', '', 3, '案卷目录', 1, 'M_pofs7h55', 98, 29);
INSERT INTO `tb_menu` VALUES (105, '整理库', '', 3, '案卷目录', 1, 'M_mj16rs5b', 99, 31);
INSERT INTO `tb_menu` VALUES (106, '整理库', '', 3, '卷内目录', 2, 'M_q2jkxo2d', 98, 30);
INSERT INTO `tb_menu` VALUES (107, '整理库', '', 3, '卷内目录', 2, 'M_os84frj0', 99, 32);
INSERT INTO `tb_menu` VALUES (108, '整理库', '', 3, '卷内目录', 2, 'M_anijozah', 97, 28);
INSERT INTO `tb_menu` VALUES (109, '整理库', '', 2, '案卷目录', 1, 'M_lkyzl3r8', 91, NULL);
INSERT INTO `tb_menu` VALUES (110, '整理库', '', 2, '案卷目录', 1, 'M_e5xf2cff', 92, NULL);
INSERT INTO `tb_menu` VALUES (111, '整理库', '', 2, '案卷目录', 1, 'M_9imewsde', 93, NULL);
INSERT INTO `tb_menu` VALUES (112, '整理库', '', 2, '案卷目录', 1, 'M_759dfxg1', 94, NULL);
INSERT INTO `tb_menu` VALUES (113, '整理库', '', 2, '卷内目录', 2, 'M_kvclvz9y', 91, NULL);
INSERT INTO `tb_menu` VALUES (114, '整理库', '', 2, '卷内目录', 2, 'M_mns18z51', 92, NULL);
INSERT INTO `tb_menu` VALUES (115, '整理库', '', 2, '卷内目录', 2, 'M_qll8wens', 93, NULL);
INSERT INTO `tb_menu` VALUES (116, '整理库', '', 2, '卷内目录', 2, 'M_cqehg0ld', 94, NULL);
INSERT INTO `tb_menu` VALUES (117, '整理库', '', 2, '财务处', 1, 'M_2eba7m1w', 95, NULL);
INSERT INTO `tb_menu` VALUES (118, '整理库', '', 2, '二级单位', 2, 'M_zuq1m0la', 95, NULL);
INSERT INTO `tb_menu` VALUES (119, '整理库', '', 3, '案卷目录', 1, 'M_1k7anva8', 117, NULL);
INSERT INTO `tb_menu` VALUES (120, '整理库', '', 3, '卷内目录', 2, 'M_9fznavjo', 117, NULL);
INSERT INTO `tb_menu` VALUES (121, '档案库', '', 2, '学生文书档案', 2, 'M_6wr4rxen', 54, NULL);
INSERT INTO `tb_menu` VALUES (122, '档案库', '', 2, '本科生优秀毕业设计', 3, 'M_2gdp0cg9', 54, NULL);
INSERT INTO `tb_menu` VALUES (123, '档案库', '', 2, '学生成绩', 4, 'M_8ovbmblg', 54, NULL);
INSERT INTO `tb_menu` VALUES (124, '档案库', '', 2, '研究生业务', 5, 'M_180lj8ez', 54, NULL);
INSERT INTO `tb_menu` VALUES (125, '档案库', '', 2, '学生人头库', 6, 'M_bhu2dcnf', 54, NULL);
INSERT INTO `tb_menu` VALUES (126, '档案库', '', 2, '教学案卷', 7, 'M_wvm3ouzl', 54, NULL);
INSERT INTO `tb_menu` VALUES (127, '档案库', '', 3, '案卷目录', 1, 'M_zrkdt621', 122, 43);
INSERT INTO `tb_menu` VALUES (128, '档案库', '', 3, '案卷目录', 1, 'M_0pnz728n', 123, 45);
INSERT INTO `tb_menu` VALUES (129, '档案库', '', 3, '案卷目录', 1, 'M_riqojuyj', 124, 47);
INSERT INTO `tb_menu` VALUES (130, '档案库', '', 3, '卷内目录', 2, 'M_b171k0eb', 122, 44);
INSERT INTO `tb_menu` VALUES (131, '档案库', '', 3, '卷内目录', 2, 'M_w5cqn51m', 123, 46);
INSERT INTO `tb_menu` VALUES (132, '档案库', '', 3, '卷内目录', 2, 'M_4yh1kaov', 124, 48);
INSERT INTO `tb_menu` VALUES (133, '档案库', '', 3, '文件目录', 1, 'M_7sn5tw85', 121, 42);
INSERT INTO `tb_menu` VALUES (134, '档案库', '', 1, '科研档案', 3, 'M_ayyp2cbm', 39, NULL);
INSERT INTO `tb_menu` VALUES (135, '档案库', '', 1, '基建档案', 4, 'M_7b3iziz7', 39, NULL);
INSERT INTO `tb_menu` VALUES (136, '档案库', '', 1, '设备档案', 5, 'M_f3dl6t3j', 39, NULL);
INSERT INTO `tb_menu` VALUES (137, '档案库', '', 1, '出版档案', 6, 'M_vhkkbmhh', 39, NULL);
INSERT INTO `tb_menu` VALUES (138, '档案库', '', 1, '财会档案', 7, 'M_ou1kk2kz', 39, NULL);
INSERT INTO `tb_menu` VALUES (139, '档案库', '', 2, '案卷目录', 1, 'M_1ca2lprd', 134, NULL);
INSERT INTO `tb_menu` VALUES (140, '档案库', '', 2, '案卷目录', 1, 'M_gmmemse3', 135, NULL);
INSERT INTO `tb_menu` VALUES (141, '档案库', '', 2, '案卷目录', 1, 'M_j8pye1q4', 136, NULL);
INSERT INTO `tb_menu` VALUES (142, '档案库', '', 2, '案卷目录', 1, 'M_u7gl4efm', 137, NULL);
INSERT INTO `tb_menu` VALUES (143, '档案库', '', 2, '卷内目录', 2, 'M_jchepaur', 134, NULL);
INSERT INTO `tb_menu` VALUES (144, '档案库', '', 2, '卷内目录', 2, 'M_5kdzqlah', 135, NULL);
INSERT INTO `tb_menu` VALUES (145, '档案库', '', 2, '卷内目录', 2, 'M_7h3xcdcl', 136, NULL);
INSERT INTO `tb_menu` VALUES (146, '档案库', '', 2, '卷内目录', 2, 'M_cc2891xa', 137, NULL);
INSERT INTO `tb_menu` VALUES (147, '档案库', '', 2, '财务处', 1, 'M_fh8rjx0r', 138, NULL);
INSERT INTO `tb_menu` VALUES (148, '档案库', '', 2, '二级单位', 2, 'M_tvphx2fs', 138, NULL);
INSERT INTO `tb_menu` VALUES (149, '档案库', '', 3, '案卷目录', 1, 'M_1g02bbt1', 147, NULL);
INSERT INTO `tb_menu` VALUES (150, '档案库', '', 3, '卷内目录', 2, 'M_l9z9yzqn', 147, NULL);
INSERT INTO `tb_menu` VALUES (266, '预立库', '456', 1, '456', 8, 'M_ayr6wwln', 1, 49);

-- ----------------------------
-- Table structure for tb_message
-- ----------------------------
DROP TABLE IF EXISTS `tb_message`;
CREATE TABLE `tb_message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_message
-- ----------------------------
INSERT INTO `tb_message` VALUES (1, '报警', '警报:发生火情，自动打开消防设备！');
INSERT INTO `tb_message` VALUES (2, '提示', '提示：归档工作已完成！');
INSERT INTO `tb_message` VALUES (3, '提示', '信息：温度适宜，自动关闭空调！');
INSERT INTO `tb_message` VALUES (4, 'Action', '提示:新的档案申请，请及时处理！');
INSERT INTO `tb_message` VALUES (5, 'Remind', '提示:温度过低！自动打开空调。');

-- ----------------------------
-- Table structure for tb_newfile
-- ----------------------------
DROP TABLE IF EXISTS `tb_newfile`;
CREATE TABLE `tb_newfile`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `add` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` date NULL DEFAULT NULL,
  `charge` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `finish` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20190507 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_newfile
-- ----------------------------
INSERT INTO `tb_newfile` VALUES (20190501, '大数据技术', '科技档案', '一区201901', '2019-05-17', '张三', 'true');
INSERT INTO `tb_newfile` VALUES (20190502, '文本挖掘技术', '科技档案', '二区201902', '2019-04-04', '李四', 'false');
INSERT INTO `tb_newfile` VALUES (20190503, '2019界校友活动', '人文档案', '三区201903', '2019-04-11', '王五', 'true');
INSERT INTO `tb_newfile` VALUES (20190504, '2019年教学活动', '人文档案', '四区201904', '2019-03-13', '张三', 'true');
INSERT INTO `tb_newfile` VALUES (20190505, '2019界学生档案', '人文档案', '五区201906', '2019-05-24', '李想', 'false');
INSERT INTO `tb_newfile` VALUES (20190506, '科研成果', '科技档案', '六区201908', '2019-05-15', '王韵', 'true');

-- ----------------------------
-- Table structure for tb_regist_examine
-- ----------------------------
DROP TABLE IF EXISTS `tb_regist_examine`;
CREATE TABLE `tb_regist_examine`  (
  `id` int(11) NOT NULL,
  `approvenumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `certificatenumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `certificatetype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contant` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `examine` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `loanubmissiondate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `oddnumbers` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `purpose` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `recorddate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `registrant` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stloanagentate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telphone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `way` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_registration
-- ----------------------------
DROP TABLE IF EXISTS `tb_registration`;
CREATE TABLE `tb_registration`  (
  `oddnumbers` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `turn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `approvenumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `certificatenumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `certificatetype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contant` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `day` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `loandate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `loanubmissiondate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `opinion` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `purpose` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `recorddate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `registrant` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `result` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `returndata` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stloanagentate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telphone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `way` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`oddnumbers`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_registration
-- ----------------------------
INSERT INTO `tb_registration` VALUES ('417542', NULL, '19638975', '18130701142', '学生证', NULL, '2019-05-05', '6', NULL, NULL, '张三', NULL, NULL, '调职查档', '2019-04-29', 'admin', NULL, NULL, '归还', 'admin', '13614250120', '微信登记', '计算机信息学院', '查阅');
INSERT INTO `tb_registration` VALUES ('168165', NULL, '59195941', '171307050012', '学生证', NULL, '2019-05-30', '14', NULL, NULL, '弘毅', NULL, '合理', '出国查档', '2019-05-09', 'admin', '通过', NULL, '通过', 'admin', '15301234477', '网上登记', '河海大学', '查阅');
INSERT INTO `tb_registration` VALUES ('839267', NULL, '59935155', '181307050011', '学生证', NULL, '2019-05-05', '8', NULL, NULL, '朱旭', NULL, NULL, '出国查档', '2019-05-06', 'admin', NULL, NULL, '待审批', NULL, '13805172256', '馆内登记', '河海大学', '查阅');
INSERT INTO `tb_registration` VALUES ('509789', NULL, '97903402', '171307050014', '学生证', '声像档案/2019SW0001', '2019-05-15', '6', NULL, NULL, '徐新', NULL, NULL, '出国查档', '2019-05-13', 'admin', NULL, NULL, '待审批', 'admin', '13050172613', '网上登记', '计算机信息学院', '查阅');
INSERT INTO `tb_registration` VALUES ('427313', '', '55400822', '341124198901012561', '身份证', '声像档案/2019SW0001', '2019-05-28', '4', NULL, NULL, '王磊', NULL, NULL, '出国查档', '2019-05-17', '', NULL, '2019-05-24', '归还', '', '18305142036', '馆内登记', '校办', '借阅');

-- ----------------------------
-- Table structure for tb_registration_form
-- ----------------------------
DROP TABLE IF EXISTS `tb_registration_form`;
CREATE TABLE `tb_registration_form`  (
  `id` int(11) NOT NULL,
  `turn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `certificatenumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `certificatetype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contant` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `day` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `loandate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `loanubmissiondate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `oddnumbers` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `purpose` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `recorddate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `registrant` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `returndata` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stloanagentate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telphone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `way` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_special
-- ----------------------------
DROP TABLE IF EXISTS `tb_special`;
CREATE TABLE `tb_special`  (
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `contant` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creater` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `publisher` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `publishtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_special
-- ----------------------------
INSERT INTO `tb_special` VALUES ('历史回顾', '重大事件', 'Admin', '2019-05-07', 'Admin', '2019-05-04');

-- ----------------------------
-- Table structure for tb_state
-- ----------------------------
DROP TABLE IF EXISTS `tb_state`;
CREATE TABLE `tb_state`  (
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `source` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `exp` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `min` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `max` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `num` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `less` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `fit` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `more` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `bool` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `plug` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_state
-- ----------------------------
INSERT INTO `tb_state` VALUES ('归档进度', '人工检查，自动检测', '进度百分比', '0', '100', '90', '', '', '查看归档情况', '0', NULL);
INSERT INTO `tb_state` VALUES ('查档', '人工检查，自动检测', '查档请求条数', '0', '10', '6', '', '处理查档', '', '1', '-');
INSERT INTO `tb_state` VALUES ('温度', '人工检查，自动检测', '适宜温度在14~24度', '14', '24', '25', '升温', '关闭空调', '降温', '1', '-');
INSERT INTO `tb_state` VALUES ('湿度', '人工检查，自动检测', '适宜湿度在45~60度', '45', '60', '50', '加湿', '关闭加湿器', '除湿', '0', NULL);
INSERT INTO `tb_state` VALUES ('火警', '人工检查，自动检测', '0表示无1表示有', '0', '1', '1', '', '', '打开消防', '1', NULL);
INSERT INTO `tb_state` VALUES ('电子档案到期', '人工检查，自动检测', '电子档案到期数目', '0', '10', '6', '', '处理到期电子档案', NULL, '1', NULL);
INSERT INTO `tb_state` VALUES ('盗窃', '门禁，以及触发器警报', '发生盗窃情况，报警并发出提示！', '0', '1', '0', '', '', '报警', '0', NULL);
INSERT INTO `tb_state` VALUES ('纸质档案到期', '人工检查，自动检测', '到期档案数目', '0', '10', '8', '', '处理到期档案', NULL, '1', NULL);
INSERT INTO `tb_state` VALUES ('鉴定进度', '人工检查，自动检测', '进度百分比', '0', '100', '90', NULL, NULL, '查看鉴定', '0', NULL);

-- ----------------------------
-- Table structure for tb_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_user_role`;
CREATE TABLE `tb_sys_user_role`  (
  `uid` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL,
  INDEX `FKk263092gq3li3gnad65w2r20k`(`role_id`) USING BTREE,
  INDEX `FKj36q86xmig4ba0d6fvnmqu0cy`(`uid`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

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
) ENGINE = MyISAM AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `tb_table` VALUES (10, 'tb_ygrs2', 'T_tqomy9q6', 1, 2);
INSERT INTO `tb_table` VALUES (11, 'tb_xjk', 'T_1pw4sdsh', 1, 6);
INSERT INTO `tb_table` VALUES (12, 'tb_xjkn', 'T_c1e9iboo', 1, 7);
INSERT INTO `tb_table` VALUES (13, 'tb_xsws', 'T_snm0nt2g', 1, 8);
INSERT INTO `tb_table` VALUES (14, 'tb_bksyxbysj', 'T_zkxm4ooz', 1, 9);
INSERT INTO `tb_table` VALUES (15, 'tb_bksyxbysjn', 'T_wme47hxq', 1, 10);
INSERT INTO `tb_table` VALUES (16, 'tb_xscj', 'T_8q80ao30', 1, 11);
INSERT INTO `tb_table` VALUES (17, 'tb_xscjn', 'T_m3gf4z2l', 1, 12);
INSERT INTO `tb_table` VALUES (18, 'tb_yjsyw', 'T_jcgwg7rm', 1, 13);
INSERT INTO `tb_table` VALUES (19, 'tb_yjsywn', 'T_ki4wgu4k', 1, 14);
INSERT INTO `tb_table` VALUES (20, 'tb_zc2', 'T_ut3uew8j', 1, 3);
INSERT INTO `tb_table` VALUES (21, 'tb_hhzl2', 'T_awypcppw', 1, 4);
INSERT INTO `tb_table` VALUES (22, 'tb_wjzl2', 'T_3zh2cp3o', 1, 4);
INSERT INTO `tb_table` VALUES (23, 'tb_wsaj2', 'T_oi45leme', 1, 5);
INSERT INTO `tb_table` VALUES (24, 'tb_xjk2', 'T_6elm8gey', 1, 6);
INSERT INTO `tb_table` VALUES (25, 'tb_xjkn2', 'T_4cd8y4ku', 1, 7);
INSERT INTO `tb_table` VALUES (26, 'tb_xsws2', 'T_zqj33mqs', 1, 8);
INSERT INTO `tb_table` VALUES (27, 'tb_bksyxbysj2', 'T_s1luacb8', 1, 9);
INSERT INTO `tb_table` VALUES (28, 'tb_bksyxbysjn2', 'T_6cju4e5a', 1, 10);
INSERT INTO `tb_table` VALUES (29, 'tb_xscj2', 'T_qv4i03kh', 1, 11);
INSERT INTO `tb_table` VALUES (30, 'tb_xscjn2', 'T_jxjl3rro', 1, 12);
INSERT INTO `tb_table` VALUES (31, 'tb_yjsyw2', 'T_vdheh5u6', 1, 13);
INSERT INTO `tb_table` VALUES (32, 'tb_yjsywn2', 'T_usdhc1di', 1, 14);
INSERT INTO `tb_table` VALUES (33, 'tb_wsajn2', 'T_qpqhhkvm', 1, 1);
INSERT INTO `tb_table` VALUES (34, 'tb_ygrs3', 'T_2s80976f', 1, 2);
INSERT INTO `tb_table` VALUES (35, 'tb_zc3', 'T_dc96jqea', 1, 3);
INSERT INTO `tb_table` VALUES (36, 'tb_hhzl3', 'T_4uic9cw1', 1, 4);
INSERT INTO `tb_table` VALUES (37, 'tb_wjzl3', 'T_wteoeni6', 1, 4);
INSERT INTO `tb_table` VALUES (38, 'tb_wsaj3', 'T_yy68oe4c', 1, 5);
INSERT INTO `tb_table` VALUES (39, 'tb_wsajn3', 'T_jbo7o7tf', 1, 1);
INSERT INTO `tb_table` VALUES (40, 'tb_xjk3', 'T_suuudg4a', 1, 6);
INSERT INTO `tb_table` VALUES (41, 'tb_xjkn3', 'T_bcx9ses6', 1, 12);
INSERT INTO `tb_table` VALUES (42, 'tb_xsws3', 'T_7wgaejrk', 1, 8);
INSERT INTO `tb_table` VALUES (43, 'tb_bksyxbysj3', 'T_08kaw06l', 1, 9);
INSERT INTO `tb_table` VALUES (44, 'tb_bksyxbysjn3', 'T_yfhf2scx', 1, 10);
INSERT INTO `tb_table` VALUES (45, 'tb_xscj3', 'T_se221xz9', 1, 11);
INSERT INTO `tb_table` VALUES (46, 'tb_xscjn3', 'T_vp0red4c', 1, 12);
INSERT INTO `tb_table` VALUES (47, 'tb_yjsyw3', 'T_31hovp87', 1, 13);
INSERT INTO `tb_table` VALUES (48, 'tb_yjsywn3', 'T_x3g1uukq', 1, 14);
INSERT INTO `tb_table` VALUES (49, 'tb_test2', 'T_jo5jde7h', 1, 1);

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
) ENGINE = MyISAM AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_template
-- ----------------------------
INSERT INTO `tb_template` VALUES (1, '文书档案-文书-文件目录所使用的模板', '文书模板', 'T_p656ted5', 2);
INSERT INTO `tb_template` VALUES (2, '文书档案-已故人事-文件目录所使用的模板', '已故人事模板', 'T_2afe1x59', 2);
INSERT INTO `tb_template` VALUES (3, '文书档案-暂存文件-文件目录所使用的模板', '暂存文件模板', 'T_5zdy1n7u', 2);
INSERT INTO `tb_template` VALUES (4, '文书档案-河海资料/文件资料汇编-文件目录所使用的模板', '河海资料/文件资料汇编模板', 'T_decy39ux', 2);
INSERT INTO `tb_template` VALUES (5, '文书案卷-案卷目录/卷内目录所使用的模板', '文书案卷模板', 'T_uxefpvjk', 2);
INSERT INTO `tb_template` VALUES (6, '教学档案-学籍卡-案卷目录所使用的模板', '学籍卡案卷模板', 'T_718b0naz', 2);
INSERT INTO `tb_template` VALUES (7, '教学档案-学籍卡-卷内目录所使用的模板', '学籍卡卷内模板', 'T_txw6o3kw', 2);
INSERT INTO `tb_template` VALUES (8, '教学档案-学生文书档案-文件目录所使用的模板', '学生文书档案模板', 'T_zhf425bn', 2);
INSERT INTO `tb_template` VALUES (9, '教学档案-本科生优秀毕业设计-案卷目录所使用的模板', '本科生优秀毕业设计案卷模板', 'T_tybrtil6', 2);
INSERT INTO `tb_template` VALUES (10, '教学档案-本科生优秀毕业设计-卷内目录所使用的模板', '本科生优秀毕业设计卷内模板', 'T_f4y0pb0i', 2);
INSERT INTO `tb_template` VALUES (11, '教学档案-学生成绩-案卷目录所使用的模板', '学生成绩案卷模板', 'T_qkqg9swe', 2);
INSERT INTO `tb_template` VALUES (12, '教学档案-学生成绩-卷内目录所使用的模板', '学生成绩卷内模板', 'T_8kh72p2c', 2);
INSERT INTO `tb_template` VALUES (13, '教学档案-研究生业务-案卷目录所使用的模板', '研究生业务案卷模板', 'T_i1f1jz34', 2);
INSERT INTO `tb_template` VALUES (14, '教学档案-研究生业务-卷内目录所使用的模板', '研究生业务卷内模板', 'T_f3yk4hmw', 2);

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
INSERT INTO `tb_template_field` VALUES (6, 31);
INSERT INTO `tb_template_field` VALUES (6, 32);
INSERT INTO `tb_template_field` VALUES (6, 33);
INSERT INTO `tb_template_field` VALUES (6, 4);
INSERT INTO `tb_template_field` VALUES (6, 5);
INSERT INTO `tb_template_field` VALUES (6, 7);
INSERT INTO `tb_template_field` VALUES (6, 9);
INSERT INTO `tb_template_field` VALUES (6, 12);
INSERT INTO `tb_template_field` VALUES (6, 13);
INSERT INTO `tb_template_field` VALUES (6, 18);
INSERT INTO `tb_template_field` VALUES (6, 19);
INSERT INTO `tb_template_field` VALUES (6, 25);
INSERT INTO `tb_template_field` VALUES (6, 2);
INSERT INTO `tb_template_field` VALUES (6, 1);
INSERT INTO `tb_template_field` VALUES (7, 34);
INSERT INTO `tb_template_field` VALUES (7, 35);
INSERT INTO `tb_template_field` VALUES (7, 36);
INSERT INTO `tb_template_field` VALUES (7, 37);
INSERT INTO `tb_template_field` VALUES (7, 38);
INSERT INTO `tb_template_field` VALUES (7, 39);
INSERT INTO `tb_template_field` VALUES (7, 40);
INSERT INTO `tb_template_field` VALUES (7, 3);
INSERT INTO `tb_template_field` VALUES (7, 4);
INSERT INTO `tb_template_field` VALUES (7, 5);
INSERT INTO `tb_template_field` VALUES (7, 9);
INSERT INTO `tb_template_field` VALUES (7, 12);
INSERT INTO `tb_template_field` VALUES (7, 13);
INSERT INTO `tb_template_field` VALUES (7, 17);
INSERT INTO `tb_template_field` VALUES (7, 18);
INSERT INTO `tb_template_field` VALUES (7, 31);
INSERT INTO `tb_template_field` VALUES (7, 32);
INSERT INTO `tb_template_field` VALUES (7, 2);
INSERT INTO `tb_template_field` VALUES (7, 1);
INSERT INTO `tb_template_field` VALUES (8, 3);
INSERT INTO `tb_template_field` VALUES (8, 4);
INSERT INTO `tb_template_field` VALUES (8, 5);
INSERT INTO `tb_template_field` VALUES (8, 7);
INSERT INTO `tb_template_field` VALUES (8, 9);
INSERT INTO `tb_template_field` VALUES (8, 10);
INSERT INTO `tb_template_field` VALUES (8, 11);
INSERT INTO `tb_template_field` VALUES (8, 12);
INSERT INTO `tb_template_field` VALUES (8, 17);
INSERT INTO `tb_template_field` VALUES (8, 18);
INSERT INTO `tb_template_field` VALUES (8, 19);
INSERT INTO `tb_template_field` VALUES (8, 28);
INSERT INTO `tb_template_field` VALUES (8, 33);
INSERT INTO `tb_template_field` VALUES (8, 2);
INSERT INTO `tb_template_field` VALUES (8, 1);
INSERT INTO `tb_template_field` VALUES (9, 41);
INSERT INTO `tb_template_field` VALUES (9, 42);
INSERT INTO `tb_template_field` VALUES (9, 4);
INSERT INTO `tb_template_field` VALUES (9, 5);
INSERT INTO `tb_template_field` VALUES (9, 7);
INSERT INTO `tb_template_field` VALUES (9, 12);
INSERT INTO `tb_template_field` VALUES (9, 13);
INSERT INTO `tb_template_field` VALUES (9, 18);
INSERT INTO `tb_template_field` VALUES (9, 19);
INSERT INTO `tb_template_field` VALUES (9, 27);
INSERT INTO `tb_template_field` VALUES (9, 28);
INSERT INTO `tb_template_field` VALUES (9, 31);
INSERT INTO `tb_template_field` VALUES (9, 32);
INSERT INTO `tb_template_field` VALUES (9, 33);
INSERT INTO `tb_template_field` VALUES (9, 2);
INSERT INTO `tb_template_field` VALUES (9, 1);
INSERT INTO `tb_template_field` VALUES (10, 43);
INSERT INTO `tb_template_field` VALUES (10, 3);
INSERT INTO `tb_template_field` VALUES (10, 4);
INSERT INTO `tb_template_field` VALUES (10, 5);
INSERT INTO `tb_template_field` VALUES (10, 10);
INSERT INTO `tb_template_field` VALUES (10, 13);
INSERT INTO `tb_template_field` VALUES (10, 17);
INSERT INTO `tb_template_field` VALUES (10, 18);
INSERT INTO `tb_template_field` VALUES (10, 33);
INSERT INTO `tb_template_field` VALUES (10, 36);
INSERT INTO `tb_template_field` VALUES (10, 2);
INSERT INTO `tb_template_field` VALUES (10, 1);
INSERT INTO `tb_template_field` VALUES (11, 4);
INSERT INTO `tb_template_field` VALUES (11, 5);
INSERT INTO `tb_template_field` VALUES (11, 7);
INSERT INTO `tb_template_field` VALUES (11, 12);
INSERT INTO `tb_template_field` VALUES (11, 13);
INSERT INTO `tb_template_field` VALUES (11, 18);
INSERT INTO `tb_template_field` VALUES (11, 19);
INSERT INTO `tb_template_field` VALUES (11, 27);
INSERT INTO `tb_template_field` VALUES (11, 31);
INSERT INTO `tb_template_field` VALUES (11, 32);
INSERT INTO `tb_template_field` VALUES (11, 33);
INSERT INTO `tb_template_field` VALUES (11, 41);
INSERT INTO `tb_template_field` VALUES (11, 42);
INSERT INTO `tb_template_field` VALUES (11, 2);
INSERT INTO `tb_template_field` VALUES (11, 1);
INSERT INTO `tb_template_field` VALUES (12, 3);
INSERT INTO `tb_template_field` VALUES (12, 4);
INSERT INTO `tb_template_field` VALUES (12, 5);
INSERT INTO `tb_template_field` VALUES (12, 10);
INSERT INTO `tb_template_field` VALUES (12, 13);
INSERT INTO `tb_template_field` VALUES (12, 17);
INSERT INTO `tb_template_field` VALUES (12, 18);
INSERT INTO `tb_template_field` VALUES (12, 28);
INSERT INTO `tb_template_field` VALUES (12, 33);
INSERT INTO `tb_template_field` VALUES (12, 36);
INSERT INTO `tb_template_field` VALUES (12, 43);
INSERT INTO `tb_template_field` VALUES (12, 2);
INSERT INTO `tb_template_field` VALUES (12, 1);
INSERT INTO `tb_template_field` VALUES (13, 44);
INSERT INTO `tb_template_field` VALUES (13, 4);
INSERT INTO `tb_template_field` VALUES (13, 5);
INSERT INTO `tb_template_field` VALUES (13, 7);
INSERT INTO `tb_template_field` VALUES (13, 12);
INSERT INTO `tb_template_field` VALUES (13, 13);
INSERT INTO `tb_template_field` VALUES (13, 18);
INSERT INTO `tb_template_field` VALUES (13, 19);
INSERT INTO `tb_template_field` VALUES (13, 27);
INSERT INTO `tb_template_field` VALUES (13, 28);
INSERT INTO `tb_template_field` VALUES (13, 31);
INSERT INTO `tb_template_field` VALUES (13, 32);
INSERT INTO `tb_template_field` VALUES (13, 33);
INSERT INTO `tb_template_field` VALUES (13, 38);
INSERT INTO `tb_template_field` VALUES (13, 39);
INSERT INTO `tb_template_field` VALUES (13, 41);
INSERT INTO `tb_template_field` VALUES (13, 42);
INSERT INTO `tb_template_field` VALUES (13, 2);
INSERT INTO `tb_template_field` VALUES (13, 1);
INSERT INTO `tb_template_field` VALUES (14, 3);
INSERT INTO `tb_template_field` VALUES (14, 4);
INSERT INTO `tb_template_field` VALUES (14, 5);
INSERT INTO `tb_template_field` VALUES (14, 10);
INSERT INTO `tb_template_field` VALUES (14, 13);
INSERT INTO `tb_template_field` VALUES (14, 17);
INSERT INTO `tb_template_field` VALUES (14, 18);
INSERT INTO `tb_template_field` VALUES (14, 28);
INSERT INTO `tb_template_field` VALUES (14, 33);
INSERT INTO `tb_template_field` VALUES (14, 36);
INSERT INTO `tb_template_field` VALUES (14, 43);
INSERT INTO `tb_template_field` VALUES (14, 2);
INSERT INTO `tb_template_field` VALUES (14, 1);

-- ----------------------------
-- Table structure for tb_test2
-- ----------------------------
DROP TABLE IF EXISTS `tb_test2`;
CREATE TABLE `tb_test2`  (
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
  `LAST_MODIFIED` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `IS_DEL` int(11) NULL DEFAULT 0,
  `TABLE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_users
-- ----------------------------
DROP TABLE IF EXISTS `tb_users`;
CREATE TABLE `tb_users`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `uid` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_users
-- ----------------------------
INSERT INTO `tb_users` VALUES ('admin', '123', 0, 0, NULL);

-- ----------------------------
-- Table structure for tb_usersmanage
-- ----------------------------
DROP TABLE IF EXISTS `tb_usersmanage`;
CREATE TABLE `tb_usersmanage`  (
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`account`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_usersmanage
-- ----------------------------
INSERT INTO `tb_usersmanage` VALUES ('admin', '超级管理员', '', '管理员', '123', '', '管理组', '正常', 0, 'admin');
INSERT INTO `tb_usersmanage` VALUES ('jxy', '计算机信息学院', NULL, '计算机信息学院管理员', '123', NULL, '计算机信息学院管理员', '正常', 1, 'jxy');

-- ----------------------------
-- Table structure for tb_video
-- ----------------------------
DROP TABLE IF EXISTS `tb_video`;
CREATE TABLE `tb_video`  (
  `id` int(11) NOT NULL,
  `localpath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_video
-- ----------------------------
INSERT INTO `tb_video` VALUES (0, 'D:/Video/01RDD复习.mp4', '01RDD复习.mp4', 'http://localhost:8080/images/01RDD复习.mp4');

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
  `LAST_MODIFIED` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `IS_DEL` int(11) NULL DEFAULT 0,
  `TABLE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
  `LAST_MODIFIED` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `IS_DEL` int(11) NULL DEFAULT 0,
  `TABLE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_ws
-- ----------------------------
INSERT INTO `tb_ws` VALUES (1, '1.南京校区', '科技处', '2017-1WS12.1', 534, '20175017812', '2017-1WS12.1-534', '江苏省水利科技项目合同书：超高韧性水泥基复合材料修复水工混凝土结构裂缝的应用技术研究', 25, '河海大学', 'http://192.168.0.105/hh1.jpg;http://192.168.0.105/hh11.jpg;http://192.168.0.105/hh12.jpg;http://192.168.0.105/hh8.jpg;', '2017-08-21', '永久', NULL, '456', '201805', '2019-07-02 14:35:22', 0, 1);
INSERT INTO `tb_ws` VALUES (2, '1.南京校区', '科技处', '2017-1WS12.1', 533, '20175017612', '2017-1WS12.1-533', '中国水利水电科学研究院流域水循环模拟与调控国家重点实验室开放研究基金项目合同书：大体积比骨料周围界面微观结构调控下氯离子扩散行为研究', 13, '河海大学', 'http://192.168.0.105/hh12.jpg;http://192.168.0.105/科学技术哲学在我国的发展状况及趋势.pdf;', '2017-07-28', '永久', NULL, NULL, '201805', '2019-05-23 18:02:02', 0, 1);
INSERT INTO `tb_ws` VALUES (3, '1.南京校区', '科技处', '2017-1WS12.1', 526, '20175016812', '2017-1WS12.1-526', '湖南省水利科技项目合作协议书：农村水电河流生态修复技术研究与应用', 8, '河海大学', 'http://192.168.0.105/hh5.jpg;http://192.168.0.105/hh6.jpg;http://192.168.0.105/hh7.jpg;', '2017-07-04', '永久', NULL, NULL, '201805', '2019-05-24 09:04:08', 0, 1);

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
  `LAST_MODIFIED` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `IS_DEL` int(11) NULL DEFAULT 0,
  `TABLE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
  `LAST_MODIFIED` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `IS_DEL` int(11) NULL DEFAULT 0,
  `TABLE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_xjk
-- ----------------------------
DROP TABLE IF EXISTS `tb_xjk`;
CREATE TABLE `tb_xjk`  (
  `No` int(11) NOT NULL AUTO_INCREMENT,
  `BeginTime` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `EndTime` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TypingPerson` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PersonLiable` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ArchivingDep` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PageNo` int(11) NULL DEFAULT NULL,
  `SecurityClassification` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Remarks` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `RetentionPeriod` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `FilingTime` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CopieNo` int(11) NULL DEFAULT NULL,
  `DocumentNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_MODIFIED` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `IS_DEL` int(11) NULL DEFAULT 0,
  `TABLE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_xjkn
-- ----------------------------
DROP TABLE IF EXISTS `tb_xjkn`;
CREATE TABLE `tb_xjkn`  (
  `No` int(11) NOT NULL AUTO_INCREMENT,
  `Sex` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `EducationalSystem` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DocumentDate` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Major` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IdNo` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `EnrollmentPage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CertificateSignature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PartNo` int(11) NULL DEFAULT NULL,
  `Title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PersonLiable` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PageNo` int(11) NULL DEFAULT NULL,
  `SecurityClassification` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Remarks` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ReferenceNo` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `RetentionPeriod` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `BeginTime` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `EndTime` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DocumentNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_MODIFIED` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `IS_DEL` int(11) NULL DEFAULT 0,
  `TABLE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_xscj
-- ----------------------------
DROP TABLE IF EXISTS `tb_xscj`;
CREATE TABLE `tb_xscj`  (
  `No` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PersonLiable` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ArchivingDep` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SecurityClassification` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Remarks` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `RetentionPeriod` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `FilingTime` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TransactionNo` int(11) NULL DEFAULT NULL,
  `BeginTime` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `EndTime` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TypingPerson` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TotalPackages` int(11) NULL DEFAULT NULL,
  `Number` int(11) NULL DEFAULT NULL,
  `DocumentNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_MODIFIED` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `IS_DEL` int(11) NULL DEFAULT 0,
  `TABLE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_xscjn
-- ----------------------------
DROP TABLE IF EXISTS `tb_xscjn`;
CREATE TABLE `tb_xscjn`  (
  `No` int(11) NOT NULL AUTO_INCREMENT,
  `PartNo` int(11) NULL DEFAULT NULL,
  `Title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PersonLiable` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Annex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Remarks` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ReferenceNo` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `RetentionPeriod` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Collaborator` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TypingPerson` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DocumentDate` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PageNumber` int(11) NULL DEFAULT NULL,
  `DocumentNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_MODIFIED` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `IS_DEL` int(11) NULL DEFAULT 0,
  `TABLE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_xsws
-- ----------------------------
DROP TABLE IF EXISTS `tb_xsws`;
CREATE TABLE `tb_xsws`  (
  `No` int(11) NOT NULL AUTO_INCREMENT,
  `PartNo` int(11) NULL DEFAULT NULL,
  `Title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PersonLiable` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ArchivingDep` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PageNo` int(11) NULL DEFAULT NULL,
  `Annex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Date` date NULL DEFAULT NULL,
  `SecurityClassification` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ReferenceNo` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `RetentionPeriod` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `FilingTime` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Collaborator` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TypingPerson` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DocumentNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_MODIFIED` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `IS_DEL` int(11) NULL DEFAULT 0,
  `TABLE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
  `LAST_MODIFIED` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `IS_DEL` int(11) NULL DEFAULT 0,
  `TABLE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_yjsyw
-- ----------------------------
DROP TABLE IF EXISTS `tb_yjsyw`;
CREATE TABLE `tb_yjsyw`  (
  `No` int(11) NOT NULL AUTO_INCREMENT,
  `SeriesArea` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PersonLiable` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ArchivingDep` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SecurityClassification` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Remarks` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `RetentionPeriod` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `FilingTime` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TransactionNo` int(11) NULL DEFAULT NULL,
  `Collaborator` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `BeginTime` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `EndTime` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TypingPerson` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IdNo` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `EnrollmentPage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TotalPackages` int(11) NULL DEFAULT NULL,
  `Number` int(11) NULL DEFAULT NULL,
  `DocumentNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_MODIFIED` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `IS_DEL` int(11) NULL DEFAULT 0,
  `TABLE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_yjsywn
-- ----------------------------
DROP TABLE IF EXISTS `tb_yjsywn`;
CREATE TABLE `tb_yjsywn`  (
  `No` int(11) NOT NULL AUTO_INCREMENT,
  `PartNo` int(11) NULL DEFAULT NULL,
  `Title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PersonLiable` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Annex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Remarks` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ReferenceNo` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `RetentionPeriod` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Collaborator` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TypingPerson` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DocumentDate` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PageNumber` int(11) NULL DEFAULT NULL,
  `DocumentNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_MODIFIED` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `IS_DEL` int(11) NULL DEFAULT 0,
  `TABLE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_yjsywn2
-- ----------------------------
DROP TABLE IF EXISTS `tb_yjsywn2`;
CREATE TABLE `tb_yjsywn2`  (
  `No` int(11) NOT NULL AUTO_INCREMENT,
  `PartNo` int(11) NULL DEFAULT NULL,
  `Title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PersonLiable` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Annex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Remarks` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ReferenceNo` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `RetentionPeriod` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Collaborator` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TypingPerson` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DocumentDate` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PageNumber` int(11) NULL DEFAULT NULL,
  `DocumentNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_MODIFIED` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `IS_DEL` int(11) NULL DEFAULT 0,
  `TABLE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_yjsywn3
-- ----------------------------
DROP TABLE IF EXISTS `tb_yjsywn3`;
CREATE TABLE `tb_yjsywn3`  (
  `No` int(11) NOT NULL AUTO_INCREMENT,
  `PartNo` int(11) NULL DEFAULT NULL,
  `Title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PersonLiable` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Annex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Remarks` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ReferenceNo` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `RetentionPeriod` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Collaborator` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TypingPerson` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DocumentDate` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PageNumber` int(11) NULL DEFAULT NULL,
  `DocumentNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_MODIFIED` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `IS_DEL` int(11) NULL DEFAULT 0,
  `TABLE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_zc
-- ----------------------------
DROP TABLE IF EXISTS `tb_zc`;
CREATE TABLE `tb_zc`  (
  `No` int(11) NOT NULL AUTO_INCREMENT,
  `DocumentNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Prize` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PrizeTime` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_MODIFIED` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `IS_DEL` int(11) NULL DEFAULT 0,
  `TABLE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_zc2
-- ----------------------------
DROP TABLE IF EXISTS `tb_zc2`;
CREATE TABLE `tb_zc2`  (
  `No` int(11) NOT NULL AUTO_INCREMENT,
  `DocumentNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Prize` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PrizeTime` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_MODIFIED` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `IS_DEL` int(11) NULL DEFAULT 0,
  `TABLE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_zc3
-- ----------------------------
DROP TABLE IF EXISTS `tb_zc3`;
CREATE TABLE `tb_zc3`  (
  `No` int(11) NOT NULL AUTO_INCREMENT,
  `DocumentNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Prize` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PrizeTime` varchar(123) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_MODIFIED` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `IS_DEL` int(11) NULL DEFAULT 0,
  `TABLE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `uid` int(11) NOT NULL,
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, '管理员', NULL, '管理员', '123', NULL, '正常', 'admin');
INSERT INTO `user_info` VALUES (2, '计算机信息学院', '', '计算机信息学院管理员', '123', NULL, '正常', 'jxy');
INSERT INTO `user_info` VALUES (3, '商学院', '', '商学院管理员', '123', NULL, '正常', 'sxy');
INSERT INTO `user_info` VALUES (4, '计算机信息学院', '', '计算机信息学院管理员', '123', '', '正常', 'jxy2');

SET FOREIGN_KEY_CHECKS = 1;
