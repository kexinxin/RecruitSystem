/*
Navicat MySQL Data Transfer

Source Server         : database
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : searchsystem

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2017-04-21 15:56:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `answer_id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NOT NULL COMMENT '试题ID',
  `answer_content` varchar(255) DEFAULT NULL COMMENT '答案内容',
  PRIMARY KEY (`answer_id`),
  KEY `fk_answer_ques` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cate_id` int(2) NOT NULL AUTO_INCREMENT COMMENT '题型编号',
  `cate_name` varchar(20) NOT NULL COMMENT '题型名称',
  PRIMARY KEY (`cate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `question_id` int(11) NOT NULL COMMENT '试题ID',
  `content` varchar(255) DEFAULT NULL COMMENT '评论内容',
  `user_id` int(11) NOT NULL COMMENT '所属用户',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam` (
  `exam_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '试卷ID',
  `exam_name` varchar(255) NOT NULL COMMENT '试卷名称',
  `exam_difficulty` varchar(255) DEFAULT NULL,
  `exam_question_type` varchar(255) DEFAULT NULL,
  `exam_skill` varchar(255) DEFAULT NULL,
  `position_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`exam_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for exam_question
-- ----------------------------
DROP TABLE IF EXISTS `exam_question`;
CREATE TABLE `exam_question` (
  `exam_id` int(11) NOT NULL COMMENT '试卷ID',
  `question_id` int(11) NOT NULL COMMENT '试题ID',
  PRIMARY KEY (`exam_id`),
  KEY `fk_ques` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history` (
  `history_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `last_time` datetime NOT NULL,
  `question_id` int(11) NOT NULL,
  PRIMARY KEY (`history_id`),
  KEY `fk_his_ques` (`question_id`),
  KEY `fk_his_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `position_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '职位ID',
  `position_name` varchar(50) NOT NULL COMMENT '职位名称',
  `position_desc` varchar(255) NOT NULL COMMENT '职位描述',
  `position_request` varchar(255) NOT NULL COMMENT '职位要求',
  PRIMARY KEY (`position_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `question_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '试题ID',
  `cate_id` int(11) DEFAULT NULL COMMENT '题型',
  `cate_name` varchar(20) DEFAULT NULL,
  `skill_id` int(11) DEFAULT NULL COMMENT '技能（知识点）',
  `skill_name` varchar(20) DEFAULT NULL,
  `question_title` varchar(255) DEFAULT '' COMMENT '题干',
  `question_difficulty` int(2) DEFAULT '5' COMMENT '难度',
  `question_collect` int(11) DEFAULT '0' COMMENT '点击量',
  `question_answer` varchar(255) DEFAULT NULL,
  `A` varchar(255) DEFAULT NULL,
  `B` varchar(255) DEFAULT NULL,
  `C` varchar(255) DEFAULT NULL,
  `D` varchar(255) DEFAULT NULL,
  `question_content` varchar(255) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`question_id`),
  KEY `fk_ques_cate` (`cate_id`),
  KEY `fk_ques_skill` (`skill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=813965 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for question_net
-- ----------------------------
DROP TABLE IF EXISTS `question_net`;
CREATE TABLE `question_net` (
  `question_net_id` int(11) NOT NULL AUTO_INCREMENT,
  `question_title` varchar(1000) DEFAULT NULL,
  `skill_name` varchar(255) DEFAULT NULL,
  `question_difficulty` varchar(255) DEFAULT NULL,
  `cate_name` varchar(255) DEFAULT NULL,
  `A` varchar(255) DEFAULT NULL,
  `B` varchar(255) DEFAULT NULL,
  `C` varchar(255) DEFAULT NULL,
  `D` varchar(255) DEFAULT NULL,
  `question_content` varchar(3000) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`question_net_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28925 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for search_path
-- ----------------------------
DROP TABLE IF EXISTS `search_path`;
CREATE TABLE `search_path` (
  `bn_id` int(11) NOT NULL AUTO_INCREMENT,
  `start_question_id` int(11) DEFAULT NULL,
  `end_question_id` int(11) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  PRIMARY KEY (`bn_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for skill
-- ----------------------------
DROP TABLE IF EXISTS `skill`;
CREATE TABLE `skill` (
  `skill_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '技能ID',
  `father_id` int(11) DEFAULT '0' COMMENT '父技能ID',
  `skill_name` varchar(20) NOT NULL COMMENT '技能名称',
  `collect` int(11) NOT NULL DEFAULT '0' COMMENT '点击量',
  PRIMARY KEY (`skill_id`),
  KEY `self` (`father_id`)
) ENGINE=InnoDB AUTO_INCREMENT=225 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for stop_word
-- ----------------------------
DROP TABLE IF EXISTS `stop_word`;
CREATE TABLE `stop_word` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1248 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for total_collect
-- ----------------------------
DROP TABLE IF EXISTS `total_collect`;
CREATE TABLE `total_collect` (
  `id` int(11) NOT NULL,
  `total_collect` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `nick_name` varchar(20) NOT NULL COMMENT '昵称',
  `user_name` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `password` varchar(20) NOT NULL COMMENT '用户密码',
  `create_user_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` int(1) NOT NULL COMMENT '当前状态（1 可用 0 不可用...）',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `nick_name_unique` (`nick_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
