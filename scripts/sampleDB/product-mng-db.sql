/*
 Navicat Premium Data Transfer

 Source Server         : product-mng
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : 127.0.0.1:3306
 Source Schema         : product-mng-db

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 18/05/2022 15:16:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `creation_time` datetime NOT NULL,
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modification_status_time` datetime NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKm1rmnfcvq5mk26li4lit88pc5`(`product_id` ASC) USING BTREE,
  INDEX `comment_product_status_modification`(`modification_status_time` ASC, `status` ASC, `product_id` ASC) USING BTREE,
  CONSTRAINT `FKm1rmnfcvq5mk26li4lit88pc5` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, '2022-05-18 15:13:35', 'msg', '2022-05-18 15:13:41', 1, 'user-id', 1);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `commentable` int NULL DEFAULT NULL,
  `creation_time` datetime NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `public_visible_comment` bit(1) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `visible` bit(1) NOT NULL,
  `voteable` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_h3w5r1mx6d0e5c6um32dgyjej`(`code` ASC) USING BTREE,
  INDEX `product_code_visible_active`(`active` ASC, `code` ASC, `visible` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, b'1', 'code', 1, '2022-05-18 15:12:29', 'desc', 'image', b'1', 'title', b'1', 1);

-- ----------------------------
-- Table structure for product_providers
-- ----------------------------
DROP TABLE IF EXISTS `product_providers`;
CREATE TABLE `product_providers`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint NOT NULL,
  `provider_id` bigint NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKcpqd9g5ts1pc8v5eoic3ivpb8`(`provider_id` ASC) USING BTREE,
  INDEX `FKtjc117dhg4rs86gkkvy1hrbbw`(`product_id` ASC) USING BTREE,
  CONSTRAINT `FKcpqd9g5ts1pc8v5eoic3ivpb8` FOREIGN KEY (`provider_id`) REFERENCES `provider` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKtjc117dhg4rs86gkkvy1hrbbw` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_providers
-- ----------------------------
INSERT INTO `product_providers` VALUES (1, 1, 1);

-- ----------------------------
-- Table structure for provider
-- ----------------------------
DROP TABLE IF EXISTS `provider`;
CREATE TABLE `provider`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_fql8wc9usg5icu8ngn40oqf5j`(`code` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of provider
-- ----------------------------
INSERT INTO `provider` VALUES (1, 'code', 'name');

-- ----------------------------
-- Table structure for vote
-- ----------------------------
DROP TABLE IF EXISTS `vote`;
CREATE TABLE `vote`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `creation_time` datetime NOT NULL,
  `score` int NOT NULL,
  `status` int NULL DEFAULT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKh5477qurpc725lhy6ky00onkc`(`product_id` ASC) USING BTREE,
  INDEX `vote_status_product`(`status` ASC, `product_id` ASC) USING BTREE,
  CONSTRAINT `FKh5477qurpc725lhy6ky00onkc` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vote
-- ----------------------------
INSERT INTO `vote` VALUES (1, '2022-05-18 15:13:18', 5, 1, 'user-id', 1);

SET FOREIGN_KEY_CHECKS = 1;
