/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : db_filemanagement

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2019-05-09 15:38:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_expiredfile`
-- ----------------------------
DROP TABLE IF EXISTS `tb_expiredfile`;
CREATE TABLE `tb_expiredfile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `add` varchar(255) DEFAULT NULL,
  `time` date DEFAULT NULL,
  `charge` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20190505 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_expiredfile
-- ----------------------------
INSERT INTO `tb_expiredfile` VALUES ('20190501', '大数据技术', '科技档案', '一区201901', '2019-05-17', '张三');
INSERT INTO `tb_expiredfile` VALUES ('20190502', '文本挖掘技术', '科技档案', '二区201902', '2019-04-04', '李四');
INSERT INTO `tb_expiredfile` VALUES ('20190503', '2019界校友活动', '人文档案', '三区201903', '2019-04-11', '王五');
INSERT INTO `tb_expiredfile` VALUES ('20190504', '2019年教学活动', '人文档案', '四区201904', '2019-03-13', '张三');
