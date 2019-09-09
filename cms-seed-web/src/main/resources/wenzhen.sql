/*
 Navicat Premium Data Transfer

 Source Server         : SUBO
 Source Server Type    : MySQL
 Source Server Version : 50642
 Source Host           : 192.168.2.172:3306
 Source Schema         : wenzhen

 Target Server Type    : MySQL
 Target Server Version : 50642
 File Encoding         : 65001

 Date: 17/06/2019 18:22:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_entry
-- ----------------------------
DROP TABLE IF EXISTS `sys_entry`;
CREATE TABLE `sys_entry`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `resource_id` int(11) NULL DEFAULT NULL COMMENT '资源ID',
  `resource_object_id` bigint(20) NULL DEFAULT NULL COMMENT '资源实例ID',
  `mask` tinyint(4) NULL DEFAULT NULL COMMENT '权限码',
  `level` tinyint(4) NULL DEFAULT NULL COMMENT '权限级别',
  `target_type` tinyint(4) NULL DEFAULT NULL COMMENT '目标类型',
  `target_id` bigint(20) NULL DEFAULT NULL COMMENT '目标ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_group`;
CREATE TABLE `sys_group`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '组名',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '组说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_r_role_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_r_role_group`;
CREATE TABLE `sys_r_role_group`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_id` bigint(20) NULL DEFAULT NULL COMMENT '用户组ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_r_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_r_role_user`;
CREATE TABLE `sys_r_role_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_r_user_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_r_user_group`;
CREATE TABLE `sys_r_user_group`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_id` bigint(20) NULL DEFAULT NULL COMMENT '用户组ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '资源编码',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '资源名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_resource_object
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource_object`;
CREATE TABLE `sys_resource_object`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `resource_id` int(11) NULL DEFAULT NULL COMMENT '资源ID',
  `resource_object_id` bigint(20) NULL DEFAULT NULL COMMENT '资源实例ID',
  `owner_id` bigint(20) NULL DEFAULT NULL COMMENT '所有者ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '角色码',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
