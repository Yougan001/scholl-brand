/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : db_school_trade

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 21/05/2024 13:26:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sh_address
-- ----------------------------
DROP TABLE IF EXISTS `sh_address`;
CREATE TABLE `sh_address`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `consignee_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人姓名',
  `consignee_phone` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人手机号',
  `province_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '省',
  `city_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '市',
  `region_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '区',
  `detail_address` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '详细地址',
  `default_flag` tinyint NOT NULL COMMENT '是否默认地址',
  `user_id` bigint NOT NULL COMMENT '用户主键id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id_index`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sh_address
-- ----------------------------
INSERT INTO `sh_address` VALUES (30, '陈xx', '17777777777', '天津市', '市辖区', '和平区', '浙江省杭州市余杭区xx街道xx小区', 1, 11);
INSERT INTO `sh_address` VALUES (31, '陈xx', '18888888888', '北京市', '市辖区', '西城区', '北京xx路xx小区', 1, 12);
INSERT INTO `sh_address` VALUES (32, '油柑', '13111111111', '广东省', '广州市', '白云区', '太和镇兴太三路广州理工学院', 1, 1);
INSERT INTO `sh_address` VALUES (33, '捡破烂的小咪', '13101111111', '广东省', '广州市', '白云区', '太和镇兴太三路广州理工学院', 1, 17);
INSERT INTO `sh_address` VALUES (34, '翻找垃圾的小咪', '12011111111', '广东省', '广州市', '天河区', '太和镇兴太三路广州理工学院', 0, 17);

-- ----------------------------
-- Table structure for sh_admin
-- ----------------------------
DROP TABLE IF EXISTS `sh_admin`;
CREATE TABLE `sh_admin`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `account_number` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员账号',
  `admin_password` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `admin_name` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员名字',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `account_number`(`account_number` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sh_admin
-- ----------------------------
INSERT INTO `sh_admin` VALUES (1, '1000', '123456', '油柑');

-- ----------------------------
-- Table structure for sh_favorite
-- ----------------------------
DROP TABLE IF EXISTS `sh_favorite`;
CREATE TABLE `sh_favorite`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `create_time` datetime NOT NULL COMMENT '加入收藏的时间',
  `user_id` bigint NOT NULL COMMENT '用户主键id',
  `idle_id` bigint NOT NULL COMMENT '闲置物主键id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id` ASC, `idle_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sh_favorite
-- ----------------------------
INSERT INTO `sh_favorite` VALUES (48, '2024-01-05 14:29:40', 12, 112);
INSERT INTO `sh_favorite` VALUES (49, '2024-05-14 13:18:08', 1, 109);

-- ----------------------------
-- Table structure for sh_idle_item
-- ----------------------------
DROP TABLE IF EXISTS `sh_idle_item`;
CREATE TABLE `sh_idle_item`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `idle_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '闲置物名称',
  `idle_details` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '详情',
  `picture_list` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图集',
  `idle_price` decimal(10, 2) NOT NULL COMMENT '价格',
  `idle_place` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发货地区',
  `idle_label` int NOT NULL COMMENT '分类标签',
  `release_time` datetime NOT NULL COMMENT '发布时间',
  `idle_status` tinyint NOT NULL COMMENT '状态（发布1、下架2、删除0）',
  `user_id` bigint NOT NULL COMMENT '用户主键id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id_index`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 117 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sh_idle_item
-- ----------------------------
INSERT INTO `sh_idle_item` VALUES (105, '手机', '书籍', '[\"http://localhost:8080/image?imageName=file170446441929610041.jpg\",\"http://localhost:8080/image?imageName=file170446442439410052.jpg\"]', 1900.00, '市辖区', 1, '2024-01-05 14:20:26', 0, 11);
INSERT INTO `sh_idle_item` VALUES (106, '电脑', '电脑', '[\"http://localhost:8080/image?imageName=file17044644744561006个人博客.jpg\"]', 3000.00, '阳泉市', 1, '2024-01-05 14:21:16', 0, 11);
INSERT INTO `sh_idle_item` VALUES (107, '电视机', '电视机', '[\"http://localhost:8080/image?imageName=file17044645972761007电影.jpg\"]', 5999.00, '长治市', 2, '2024-01-05 14:23:19', 0, 11);
INSERT INTO `sh_idle_item` VALUES (108, '空调', '空调', '[\"http://localhost:8080/image?imageName=file17044646299941008停车场.jpg\"]', 1000.00, '景德镇市', 2, '2024-01-05 14:23:51', 0, 11);
INSERT INTO `sh_idle_item` VALUES (109, '历史书', '历史书', '[\"http://localhost:8080/image?imageName=file17044646674121009音乐.jpg\"]', 20.00, '乌海市', 4, '2024-01-05 14:24:33', 0, 11);
INSERT INTO `sh_idle_item` VALUES (110, '帐篷', '帐篷', '[\"http://localhost:8080/image?imageName=file17044647092251010社团.jpg\"]', 100.00, '秦皇岛市', 3, '2024-01-05 14:25:22', 0, 11);
INSERT INTO `sh_idle_item` VALUES (111, '自行车', '自行车', '[\"http://localhost:8080/image?imageName=file17044647567061011考试.jpg\"]', 599.00, '市辖区', 5, '2024-01-05 14:26:00', 0, 11);
INSERT INTO `sh_idle_item` VALUES (112, '衣服', '衣服', '[\"http://localhost:8080/image?imageName=file17044647909181012图书馆.png\"]', 199.00, '长治市', 5, '2024-01-05 14:26:38', 0, 11);
INSERT INTO `sh_idle_item` VALUES (113, '一个狗头', '小狗小狗', '[\"http://localhost:8080/image?imageName=file17156920091531003heade.jpg\"]', 40.00, '广州市', 5, '2024-05-14 13:06:51', 0, 1);
INSERT INTO `sh_idle_item` VALUES (114, '测试', '没啥', '[]', 30.00, '广州市', 5, '2024-05-18 14:41:59', 0, 14);
INSERT INTO `sh_idle_item` VALUES (115, '111', '123', '[]', 30.00, '市辖区', 1, '2024-05-18 15:19:01', 0, 15);
INSERT INTO `sh_idle_item` VALUES (116, '纯牛奶', '好喝爱喝多喝', '[\"http://localhost:8080/image?imageName=file17162627394601002item03.jpeg\"]', 30.00, '广州市', 5, '2024-05-21 03:39:30', 0, 14);
INSERT INTO `sh_idle_item` VALUES (117, '破烂手机', '三手手机值得拥有', '[\"http://localhost:8080/image?imageName=file17162640828881004category02.jpg\"]', 999.00, '广州市', 1, '2024-05-21 04:01:29', 1, 16);
INSERT INTO `sh_idle_item` VALUES (118, '特仑苏纯牛奶', '不是所有牛奶都叫特仑苏', '[\"http://localhost:8080/image?imageName=file17162641363771005item03.jpeg\"]', 30.00, '广州市', 5, '2024-05-21 04:02:17', 1, 16);
INSERT INTO `sh_idle_item` VALUES (119, 'Java入门基础', '学Java 入门到炒粉', '[\"http://localhost:8080/image?imageName=file17162641851411006item08.jpeg\"]', 30.00, '广州市', 4, '2024-05-21 04:03:06', 1, 16);
INSERT INTO `sh_idle_item` VALUES (120, '正版充电线', '十分钟就满', '[\"http://localhost:8080/image?imageName=file17162642340561007item02.jpeg\"]', 10.00, '广州市', 2, '2024-05-21 04:03:55', 1, 16);
INSERT INTO `sh_idle_item` VALUES (121, '机械键盘', '宿舍友好键', '[\"http://localhost:8080/image?imageName=file1716267990148100825a0e76759254afe.jpeg\"]', 50.00, '广州市', 1, '2024-05-21 05:06:34', 1, 17);
INSERT INTO `sh_idle_item` VALUES (122, '价值书籍', '看完你就能认清自己的价值', '[\"http://localhost:8080/image?imageName=file17162680434411009category01.jpg\"]', 30.00, '广州市', 4, '2024-05-21 05:07:25', 1, 17);
INSERT INTO `sh_idle_item` VALUES (123, '破旧保温杯', '保温持续 23 小时', '[\"http://localhost:8080/image?imageName=file17162681048371010category03.jpg\"]', 40.00, '广州市', 5, '2024-05-21 05:08:26', 1, 17);
INSERT INTO `sh_idle_item` VALUES (124, '宠物狗粮', '嘎嘎好吃 吃完不上火', '[\"http://localhost:8080/image?imageName=file1716268214053101190dac72663d62ac0.jpeg\"]', 60.00, '广州市', 5, '2024-05-21 05:10:15', 1, 17);
INSERT INTO `sh_idle_item` VALUES (125, '户外必备乘凉椅', '坐上它 嘎嘎睡', '[\"http://localhost:8080/image?imageName=file17162682458791012item04.jpeg\"]', 20.00, '广州市', 3, '2024-05-21 05:11:18', 1, 17);

-- ----------------------------
-- Table structure for sh_message
-- ----------------------------
DROP TABLE IF EXISTS `sh_message`;
CREATE TABLE `sh_message`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` bigint NOT NULL COMMENT '用户主键id',
  `idle_id` bigint NOT NULL COMMENT '闲置主键id',
  `content` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '留言内容',
  `create_time` datetime NOT NULL COMMENT '留言时间',
  `to_user` bigint NOT NULL COMMENT '所回复的用户',
  `to_message` bigint NULL DEFAULT NULL COMMENT '所回复的留言',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id_index`(`user_id` ASC) USING BTREE,
  INDEX `idle_id_index`(`idle_id` ASC) USING BTREE,
  INDEX `to_user_index`(`to_user` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sh_message
-- ----------------------------
INSERT INTO `sh_message` VALUES (48, 11, 112, '多少钱', '2024-01-05 14:26:48', 11, NULL);
INSERT INTO `sh_message` VALUES (49, 11, 111, '这个可以便宜点么？', '2024-01-05 14:27:03', 11, NULL);
INSERT INTO `sh_message` VALUES (50, 11, 111, '可以', '2024-01-05 14:27:19', 11, 49);
INSERT INTO `sh_message` VALUES (51, 11, 107, '东西怎么买啊？人在哪？', '2024-01-06 00:32:51', 11, NULL);
INSERT INTO `sh_message` VALUES (52, 11, 105, '东西怎么买？', '2024-01-06 00:33:54', 11, NULL);
INSERT INTO `sh_message` VALUES (53, 11, 106, '可以 很帅', '2024-05-14 12:53:56', 11, NULL);
INSERT INTO `sh_message` VALUES (54, 11, 105, '少管我', '2024-05-14 12:54:54', 11, 52);
INSERT INTO `sh_message` VALUES (55, 1, 113, '真的帅', '2024-05-14 13:07:05', 1, NULL);
INSERT INTO `sh_message` VALUES (56, 1, 113, '那是 也不看看是谁', '2024-05-14 13:17:45', 1, 55);
INSERT INTO `sh_message` VALUES (57, 14, 113, '这么帅', '2024-05-21 03:49:54', 1, NULL);
INSERT INTO `sh_message` VALUES (58, 14, 113, '可以可以', '2024-05-21 03:50:02', 1, 56);
INSERT INTO `sh_message` VALUES (59, 14, 116, '123123123', '2024-05-21 03:55:43', 14, NULL);
INSERT INTO `sh_message` VALUES (60, 17, 117, '用你这手机能变身吗', '2024-05-21 04:06:36', 16, NULL);
INSERT INTO `sh_message` VALUES (61, 16, 117, '那肯定啦 变身奥特曼', '2024-05-21 04:07:30', 17, 60);

-- ----------------------------
-- Table structure for sh_order
-- ----------------------------
DROP TABLE IF EXISTS `sh_order`;
CREATE TABLE `sh_order`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `order_number` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `user_id` bigint NOT NULL COMMENT '用户主键id',
  `idle_id` bigint NOT NULL COMMENT '闲置物品主键id',
  `order_price` decimal(10, 2) NOT NULL COMMENT '订单总价',
  `payment_status` tinyint NOT NULL COMMENT '支付状态',
  `payment_way` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `payment_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `order_status` tinyint NOT NULL COMMENT '订单状态',
  `is_deleted` tinyint NULL DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 90 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sh_order
-- ----------------------------
INSERT INTO `sh_order` VALUES (90, '171626447762110002', 17, 117, 999.00, 1, '支付宝', '2024-05-21 04:07:58', '2024-05-21 04:09:03', 3, NULL);

-- ----------------------------
-- Table structure for sh_order_address
-- ----------------------------
DROP TABLE IF EXISTS `sh_order_address`;
CREATE TABLE `sh_order_address`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `order_id` bigint NOT NULL COMMENT '订单id',
  `consignee_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人',
  `consignee_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `detail_address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货地址',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `orderId`(`order_id` ASC) USING BTREE,
  INDEX `order_id_index`(`order_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sh_order_address
-- ----------------------------
INSERT INTO `sh_order_address` VALUES (21, 86, '陈xx', '18888888888', '北京市市辖区西城区北京xx路xx小区');
INSERT INTO `sh_order_address` VALUES (22, 85, '陈xx', '18888888888', '北京市市辖区西城区北京xx路xx小区');
INSERT INTO `sh_order_address` VALUES (23, 88, '油柑', '13111111111', '广东省广州市白云区太和镇兴太三路广州理工学院');
INSERT INTO `sh_order_address` VALUES (24, 89, '油柑', '13111111111', '广东省广州市白云区太和镇兴太三路广州理工学院');
INSERT INTO `sh_order_address` VALUES (25, 90, '捡破烂的小咪', '13101111111', '广东省广州市白云区太和镇兴太三路广州理工学院');

-- ----------------------------
-- Table structure for sh_user
-- ----------------------------
DROP TABLE IF EXISTS `sh_user`;
CREATE TABLE `sh_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `account_number` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号（手机号）',
  `user_password` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `nickname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `avatar` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '头像',
  `sign_in_time` datetime NOT NULL COMMENT '注册时间',
  `user_status` tinyint NULL DEFAULT NULL COMMENT '状态（1代表封禁）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `account_number`(`account_number` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sh_user
-- ----------------------------
INSERT INTO `sh_user` VALUES (16, '001', '123456', '捡破烂', 'http://localhost:8080/image?imageName=file17162640410341003YouGan-logo.png', '2024-05-21 04:00:10', NULL);
INSERT INTO `sh_user` VALUES (17, '002', '123456', '捡破烂的小咪', 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', '2024-05-21 04:05:12', NULL);

SET FOREIGN_KEY_CHECKS = 1;
