/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : db_filemanagement

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2019-05-09 15:38:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_state`
-- ----------------------------
DROP TABLE IF EXISTS `tb_state`;
CREATE TABLE `tb_state` (
  `name` varchar(20) NOT NULL DEFAULT '',
  `source` varchar(20) DEFAULT '',
  `exp` varchar(50) DEFAULT '',
  `min` varchar(10) DEFAULT '',
  `max` varchar(10) DEFAULT NULL,
  `num` varchar(10) DEFAULT '',
  `less` varchar(10) DEFAULT '',
  `fit` varchar(10) DEFAULT '',
  `more` varchar(10) DEFAULT '',
  `bool` varchar(4) DEFAULT '',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_state
-- ----------------------------
INSERT INTO `tb_state` VALUES ('归档进度', '人工检查，自动检测', '进度百分比', '0', '100', '90', '', '', '查看归档情况', '0');
INSERT INTO `tb_state` VALUES ('查档', '人工检查，自动检测', '查档请求条数', '0', '10', '6', '', '处理查档', '', '1');
INSERT INTO `tb_state` VALUES ('温度', '人工检查，自动检测', '适宜温度在14~24度', '14', '24', '10', '升温', '关闭空调', '降温', '1');
INSERT INTO `tb_state` VALUES ('湿度', '人工检查，自动检测', '适宜湿度在45~60度', '45', '60', '50', '加湿', '关闭加湿器', '除湿', '0');
INSERT INTO `tb_state` VALUES ('火警', '人工检查，自动检测', '0表示无1表示有', '0', '1', '1', '', '', '打开消防', '1');
INSERT INTO `tb_state` VALUES ('电子档案到期', '人工检查，自动检测', '电子档案到期数目', '0', '10', '6', '', '处理到期电子档案', null, '1');
INSERT INTO `tb_state` VALUES ('盗窃', '门禁，以及触发器警报', '发生盗窃情况，报警并发出提示！', '0', '1', '0', '', '', '报警', '0');
INSERT INTO `tb_state` VALUES ('纸质档案到期', '人工检查，自动检测', '到期档案数目', '0', '10', '8', '', '处理到期档案', null, '1');
INSERT INTO `tb_state` VALUES ('鉴定进度', '人工检查，自动检测', '进度百分比', '0', '100', '90', null, null, '查看鉴定', '0');
