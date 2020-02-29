/*
 Navicat Premium Data Transfer

 Source Server         : robot
 Source Server Type    : MySQL
 Source Server Version : 50644
 Source Host           : 47.101.187.208:3306
 Source Schema         : Robot

 Target Server Type    : MySQL
 Target Server Version : 50644
 File Encoding         : 65001

 Date: 29/02/2020 23:47:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for word
-- ----------------------------
DROP TABLE IF EXISTS `word`;
CREATE TABLE `word`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of word
-- ----------------------------
INSERT INTO `word` VALUES (1, '三坊七巷');

SET FOREIGN_KEY_CHECKS = 1;
