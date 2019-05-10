/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : db_filemanagement

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2019-05-09 15:38:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_action`
-- ----------------------------
DROP TABLE IF EXISTS `tb_action`;
CREATE TABLE `tb_action` (
  `name` varchar(255) NOT NULL DEFAULT '',
  `type` varchar(255) DEFAULT '',
  `exp` varchar(255) DEFAULT '',
  `message` varchar(255) DEFAULT '',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_action
-- ----------------------------
INSERT INTO `tb_action` VALUES ('关闭加湿器', 'Remind', '对各部门的提交资料进行收集', '湿度适宜！自动关闭加湿器。');
INSERT INTO `tb_action` VALUES ('关闭空调', 'Remind', '关闭空调', '温度适宜！自动关闭空调。');
INSERT INTO `tb_action` VALUES ('加湿', 'Remind', '对应的请求返回对应的信息', '湿度过低！自动打开加湿器。');
INSERT INTO `tb_action` VALUES ('升温', 'Remind', '开启空调，降温', '温度过低！自动打开空调。');
INSERT INTO `tb_action` VALUES ('处理到期档案', 'Action', '处理到期档案', '存在纸质档案到期，请及时处理！');
INSERT INTO `tb_action` VALUES ('处理到期电子档案', 'Action', '电子档案到期', '存在电子档案到期，请及时处理！');
INSERT INTO `tb_action` VALUES ('处理档案请求', 'Action', '前往查看请求', '新的档案申请，请及时处理！');
INSERT INTO `tb_action` VALUES ('打开消防', 'Alarm', '打开消防设施', '发生火情，请及时撤离！');
INSERT INTO `tb_action` VALUES ('报警', null, null, null);
INSERT INTO `tb_action` VALUES ('查看归档情况', 'Message', '归档工作完成', '归档工作已完成，完成度100%！');
INSERT INTO `tb_action` VALUES ('查看鉴定', 'Message', '鉴定工作完成', '鉴定工作已完成，完成度100%！');
INSERT INTO `tb_action` VALUES ('降温', 'Remind', '开启空调，提升温度！', '温度过低！自动打开空调。');
INSERT INTO `tb_action` VALUES ('除湿', 'Remind', '对收集的档案进行整理', '湿度过高！自动开启加湿器。');
