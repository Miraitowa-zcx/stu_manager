/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : localhost:3306
 Source Schema         : stu_manger

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 24/06/2024 19:07:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_stu_class
-- ----------------------------
DROP TABLE IF EXISTS `t_stu_class`;
CREATE TABLE `t_stu_class`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `stuClassName` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `stuClassDesc` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '学生班级表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_stu_class
-- ----------------------------
INSERT INTO `t_stu_class` VALUES (5, '2024', '新生');
INSERT INTO `t_stu_class` VALUES (7, '2023', '大二');

-- ----------------------------
-- Table structure for t_stu_grade
-- ----------------------------
DROP TABLE IF EXISTS `t_stu_grade`;
CREATE TABLE `t_stu_grade`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `stu_class_id` int NULL DEFAULT NULL COMMENT '班级编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `chinese_score` float NOT NULL DEFAULT -1 COMMENT '语文成绩',
  `math_score` float NOT NULL DEFAULT -1 COMMENT '数学成绩',
  `english_score` float NOT NULL DEFAULT -1 COMMENT '英语成绩',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `stu_class_id_ibfk`(`stu_class_id` ASC) USING BTREE,
  CONSTRAINT `stu_class_id_ibfk` FOREIGN KEY (`stu_class_id`) REFERENCES `t_stu_class` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '学生成绩表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_stu_grade
-- ----------------------------
INSERT INTO `t_stu_grade` VALUES (14, 7, 'zcx', 80, 90, 100);
INSERT INTO `t_stu_grade` VALUES (15, 7, 'xxx', 80, 90, 70);
INSERT INTO `t_stu_grade` VALUES (18, 5, 'ccc', 100, 60, 80);

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `home` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `sex` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `score` float NULL DEFAULT NULL,
  `stu_class_id` int NULL DEFAULT NULL,
  `stuDesc` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `stuClassId`(`stu_class_id` ASC) USING BTREE,
  INDEX `name`(`name` ASC) USING BTREE,
  CONSTRAINT `t_student_ibfk_1` FOREIGN KEY (`stu_class_id`) REFERENCES `t_stu_class` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '学生表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES (15, 'zcx', 'xxx', '男', 150, 7, 'xxx');
INSERT INTO `t_student` VALUES (16, 'xxx', 'xxx', '男', 200, 7, 'xxx');
INSERT INTO `t_student` VALUES (17, 'ccc', 'ccc', '女', 200, 5, 'ccc');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (5, 'admin', 'admin');

-- ----------------------------
-- Triggers structure for table t_student
-- ----------------------------
DROP TRIGGER IF EXISTS `update_stu_class_id`;
delimiter ;;
CREATE TRIGGER `update_stu_class_id` AFTER UPDATE ON `t_student` FOR EACH ROW BEGIN
         UPDATE t_stu_grade
         SET t_stu_grade.stu_class_id = NEW.stu_class_id
         WHERE t_stu_grade.stu_class_id = OLD.stu_class_id AND t_stu_grade.`name` = OLD.`name`;
     END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
