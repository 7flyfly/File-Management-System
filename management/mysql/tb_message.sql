/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : db_filemanagement

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2019-05-09 15:38:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_message`
-- ----------------------------
DROP TABLE IF EXISTS `tb_message`;
CREATE TABLE `tb_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_message
-- ----------------------------
INSERT INTO `tb_message` VALUES ('1', '报警', '警报:发生火情，自动打开消防设备！');
INSERT INTO `tb_message` VALUES ('2', '提示', '提示：归档工作已完成！');
INSERT INTO `tb_message` VALUES ('3', '提示', '信息：温度适宜，自动关闭空调！');
INSERT INTO `tb_message` VALUES ('4', 'Action', '提示:新的档案申请，请及时处理！');
INSERT INTO `tb_message` VALUES ('5', 'Remind', '提示:温度过低！自动打开空调。');
INSERT INTO `tb_message` VALUES ('6', 'Remind', '提示:温度适宜！自动关闭空调。');
INSERT INTO `tb_message` VALUES ('7', 'Remind', '提示:温度过低！自动打开空调。');
