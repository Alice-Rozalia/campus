/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50645
 Source Host           : localhost:3306
 Source Schema         : campus

 Target Server Type    : MySQL
 Target Server Version : 50645
 File Encoding         : 65001

 Date: 19/03/2021 11:32:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category`  (
  `id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '分类名称',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '分类图片',
  `parent_id` int(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '分类父ID',
  `enabled` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否可用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 107 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` VALUES (38, '数码产品', '', 0, 1);
INSERT INTO `tb_category` VALUES (39, '手机', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/1.jpg', 38, 1);
INSERT INTO `tb_category` VALUES (40, '平板', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/2.jpg', 38, 1);
INSERT INTO `tb_category` VALUES (41, '笔记本', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/3.jpg', 38, 1);
INSERT INTO `tb_category` VALUES (42, '台式机', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/4.jpg', 38, 1);
INSERT INTO `tb_category` VALUES (43, '手表手环', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/5.png', 38, 1);
INSERT INTO `tb_category` VALUES (44, '单反相机', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/6.jpg', 38, 1);
INSERT INTO `tb_category` VALUES (45, '充电宝', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/7.jpg', 38, 1);
INSERT INTO `tb_category` VALUES (46, '耳机', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/8.jpg', 38, 1);
INSERT INTO `tb_category` VALUES (47, '手机配件', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/9.jpg', 38, 1);
INSERT INTO `tb_category` VALUES (48, '电脑配件', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/10.jpg', 38, 1);
INSERT INTO `tb_category` VALUES (49, '路由器', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/11.jpg', 38, 1);
INSERT INTO `tb_category` VALUES (50, '书籍教材', '', 0, 1);
INSERT INTO `tb_category` VALUES (51, '教科书', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/12.jpg', 50, 1);
INSERT INTO `tb_category` VALUES (52, '文学小说', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/13.jpg', 50, 1);
INSERT INTO `tb_category` VALUES (53, '轻小说', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/14.jpg', 50, 1);
INSERT INTO `tb_category` VALUES (54, '电子书', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/15.jpg', 50, 1);
INSERT INTO `tb_category` VALUES (55, '考试教育', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/16.jpg', 50, 1);
INSERT INTO `tb_category` VALUES (56, '杂志', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/17.png', 50, 1);
INSERT INTO `tb_category` VALUES (57, '漫画', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/18.png', 50, 1);
INSERT INTO `tb_category` VALUES (58, '衣鞋帽伞', '', 0, 1);
INSERT INTO `tb_category` VALUES (59, '裙子', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/19.png', 58, 1);
INSERT INTO `tb_category` VALUES (60, '裤子', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/20.jpg', 58, 1);
INSERT INTO `tb_category` VALUES (61, '衣服', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/21.jpg', 58, 1);
INSERT INTO `tb_category` VALUES (62, '帽子', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/22.jpg', 58, 1);
INSERT INTO `tb_category` VALUES (63, '鞋子', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/23.jpg', 58, 1);
INSERT INTO `tb_category` VALUES (64, '箱子', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/24.jpg', 58, 1);
INSERT INTO `tb_category` VALUES (65, '包包', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/25.jpg', 58, 1);
INSERT INTO `tb_category` VALUES (66, '雨伞', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/26.jpg', 58, 1);
INSERT INTO `tb_category` VALUES (67, '代步工具', '', 0, 1);
INSERT INTO `tb_category` VALUES (68, '自行车', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/27.jpg', 67, 1);
INSERT INTO `tb_category` VALUES (69, '旱冰鞋', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/28.jpg', 67, 1);
INSERT INTO `tb_category` VALUES (71, '滑板', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/29.jpg', 67, 1);
INSERT INTO `tb_category` VALUES (72, '电车', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/30.jpg', 67, 1);
INSERT INTO `tb_category` VALUES (73, '体育健身', '', 0, 1);
INSERT INTO `tb_category` VALUES (74, '球类', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/31.jpg', 73, 1);
INSERT INTO `tb_category` VALUES (75, '运动器材', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/32.jpg', 73, 1);
INSERT INTO `tb_category` VALUES (76, '运动鞋', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/33.jpg', 73, 1);
INSERT INTO `tb_category` VALUES (77, '家用电器', '', 0, 1);
INSERT INTO `tb_category` VALUES (78, '吹风机', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/35.jpg', 77, 1);
INSERT INTO `tb_category` VALUES (79, '音响', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/36.jpg', 77, 1);
INSERT INTO `tb_category` VALUES (80, '锅', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/34.jpg', 77, 1);
INSERT INTO `tb_category` VALUES (81, '电磁炉', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/38.jpg', 77, 1);
INSERT INTO `tb_category` VALUES (82, '风扇', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/39.jpg', 77, 1);
INSERT INTO `tb_category` VALUES (83, '电热扇', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/40.jpg', 77, 1);
INSERT INTO `tb_category` VALUES (84, '日常用品', '', 0, 1);
INSERT INTO `tb_category` VALUES (85, '化妆护肤', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/37.jpg', 84, 1);
INSERT INTO `tb_category` VALUES (86, '小工具', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/41.jpg', 84, 1);
INSERT INTO `tb_category` VALUES (87, '学习用品', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/42.jpg', 84, 1);
INSERT INTO `tb_category` VALUES (88, '虚拟产品', '', 0, 1);
INSERT INTO `tb_category` VALUES (89, '游戏周边', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/43.jpg', 88, 1);
INSERT INTO `tb_category` VALUES (90, '各类账号', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/44.jpg', 88, 1);
INSERT INTO `tb_category` VALUES (91, '手工艺品', '', 0, 1);
INSERT INTO `tb_category` VALUES (92, '书法', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/45.jpg', 91, 1);
INSERT INTO `tb_category` VALUES (93, '绘画', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/46.jpg', 91, 1);
INSERT INTO `tb_category` VALUES (94, '工艺品', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/47.jpg', 91, 1);
INSERT INTO `tb_category` VALUES (95, '服装设计', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/48.jpg', 91, 1);
INSERT INTO `tb_category` VALUES (96, '乐器', '', 0, 1);
INSERT INTO `tb_category` VALUES (97, '吉他', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/49.jpg', 96, 1);
INSERT INTO `tb_category` VALUES (98, '钢琴', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/50.jpg', 96, 1);
INSERT INTO `tb_category` VALUES (99, '口琴/竖笛', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/51.jpg', 96, 1);
INSERT INTO `tb_category` VALUES (100, '陶笛', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/52.jpg', 96, 1);
INSERT INTO `tb_category` VALUES (101, '电子琴', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/53.jpg', 96, 1);
INSERT INTO `tb_category` VALUES (102, '电吉他', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/54.jpg', 96, 1);
INSERT INTO `tb_category` VALUES (103, '架子鼓', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/55.jpg', 96, 1);
INSERT INTO `tb_category` VALUES (104, '贝斯', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/56.jpg', 96, 1);
INSERT INTO `tb_category` VALUES (105, '小提琴', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/57.jpg', 96, 1);
INSERT INTO `tb_category` VALUES (106, '曲谱', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/58.png', 96, 1);

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(10) UNSIGNED NOT NULL,
  `entity_type` int(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '评论类型，0表示评论，1表示回复',
  `entity_id` int(10) UNSIGNED NOT NULL COMMENT '评论对象id',
  `target_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '目标id(回复)',
  `content` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '评论内容',
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '评论状态',
  `create_time` datetime(0) NOT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_comment
-- ----------------------------

-- ----------------------------
-- Table structure for tb_goods
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods`;
CREATE TABLE `tb_goods`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '价格',
  `introduce` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '描述',
  `create_date` datetime(0) NOT NULL COMMENT '发布时间',
  `look` int(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '浏览',
  `original` decimal(10, 2) NULL DEFAULT NULL COMMENT '原价',
  `enabled` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否下架',
  `category_id` int(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '分类id',
  `user_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_goods
-- ----------------------------
INSERT INTO `tb_goods` VALUES (1, '捷安特XTC 800山地自行车', 2000.00, '捷安特XTC 800山地自行车，购入一年多，平时很少骑', '2021-02-09 19:51:39', 0, 3689.12, 1, 68, 3);
INSERT INTO `tb_goods` VALUES (3, '微软Surface Go 2', 3000.00, '二合一平板电脑/笔记本电脑 10.5英寸 奔腾金牌4425Y 8G 128G SSD,单机+充电器', '2021-02-15 13:08:28', 0, 3988.00, 1, 40, 3);
INSERT INTO `tb_goods` VALUES (9, 'ROG幻14', 9999.00, '轻薄商务办公设计师14英寸2K屏笔记本电脑(锐龙R9-4900HS 8核 7nm 16G 512GSSD RTX2060MaxQ)经典白,买了不到半年99新', '2021-02-15 18:37:42', 0, 9000.00, 1, 41, 3);
INSERT INTO `tb_goods` VALUES (10, '华为 HUAWEI Mate 40 Pro麒麟9000', 6999.00, '8GB+256GB釉白色5G全网通，包装配件齐全，使用不到半年', '2021-02-15 18:41:30', 0, 5000.00, 1, 39, 3);
INSERT INTO `tb_goods` VALUES (11, '天堂伞', 70.00, '双层全遮光黑胶55cm*8骨三折太阳伞晴雨伞30774E梦幻星空', '2021-02-15 18:43:29', 0, 119.00, 1, 66, 3);
INSERT INTO `tb_goods` VALUES (12, 'Re:从零开始的异世界生活.15', 20.00, 'Re:从零开始的异世界生活.15', '2021-02-15 18:45:50', 0, 31.00, 1, 53, 3);
INSERT INTO `tb_goods` VALUES (13, '联想(Lenovo)拯救者R7000', 5000.00, '15.6英寸游戏笔记本电脑(R7-4800H 16G 512G SSD GTX1650 100%sRGB)幻影黑，购入半年，平时很少使用，99新', '2021-02-15 18:48:29', 0, 5899.00, 1, 41, 3);

-- ----------------------------
-- Table structure for tb_image
-- ----------------------------
DROP TABLE IF EXISTS `tb_image`;
CREATE TABLE `tb_image`  (
  `id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '图片地址',
  `entity_id` int(8) UNSIGNED NOT NULL COMMENT '实体id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_image
-- ----------------------------
INSERT INTO `tb_image` VALUES (3, 'http://kuro-campus.oss-cn-shenzhen.aliyuncs.com/2021/02/15/da3c367b85114f56a1f087183d5dea17.jpg', 9);
INSERT INTO `tb_image` VALUES (4, 'http://kuro-campus.oss-cn-shenzhen.aliyuncs.com/2021/02/15/3a9f838cbcd2434d84cf26146de7c794.jpg', 9);
INSERT INTO `tb_image` VALUES (5, 'http://kuro-campus.oss-cn-shenzhen.aliyuncs.com/2021/02/15/599449957e204c248fdd2413043e5432.jpg', 9);
INSERT INTO `tb_image` VALUES (6, 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/2.jpg', 3);
INSERT INTO `tb_image` VALUES (7, 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/27.jpg', 1);
INSERT INTO `tb_image` VALUES (8, 'http://kuro-campus.oss-cn-shenzhen.aliyuncs.com/2021/02/15/e0e3d6734cc844329adf0b1f90a74d9d.jpg', 10);
INSERT INTO `tb_image` VALUES (9, 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/1.jpg', 10);
INSERT INTO `tb_image` VALUES (10, 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/26.jpg', 11);
INSERT INTO `tb_image` VALUES (11, 'http://kuro-campus.oss-cn-shenzhen.aliyuncs.com/2021/02/15/0a918a4577f7400a91ce0adcf88c4af8.jpg', 12);
INSERT INTO `tb_image` VALUES (12, 'http://kuro-campus.oss-cn-shenzhen.aliyuncs.com/2021/02/15/327a2306ed5246bcb7cfcf0340e1dabe.jpg', 12);
INSERT INTO `tb_image` VALUES (13, 'http://kuro-campus.oss-cn-shenzhen.aliyuncs.com/2021/02/15/900d8e1929ce434b9aa3fbe3037e7e19.jpg', 13);
INSERT INTO `tb_image` VALUES (14, 'http://kuro-campus.oss-cn-shenzhen.aliyuncs.com/2021/02/15/e19a35f1ab244a6283961459ca53530c.jpg', 13);

-- ----------------------------
-- Table structure for tb_login_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_login_log`;
CREATE TABLE `tb_login_log`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `login_time` datetime(0) NOT NULL,
  `location` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '登录地点',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'ip',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for tb_message
-- ----------------------------
DROP TABLE IF EXISTS `tb_message`;
CREATE TABLE `tb_message`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `from_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '消息的发送者',
  `to_id` int(10) UNSIGNED NOT NULL COMMENT '消息的接收者',
  `conversation_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '查询字段',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '消息内容',
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态，0未读，1已读',
  `create_time` datetime(0) NOT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_message
-- ----------------------------

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `perms` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '权限标识',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '权限名称',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_permission
-- ----------------------------
INSERT INTO `tb_permission` VALUES (2, 'goods:publish', '发布商品', 1);
INSERT INTO `tb_permission` VALUES (3, 'upload:upload', '图片上传', 1);
INSERT INTO `tb_permission` VALUES (4, 'upload:delete', '删除图片', 1);
INSERT INTO `tb_permission` VALUES (5, 'image:keep', '保存图片', 1);
INSERT INTO `tb_permission` VALUES (6, 'comment:add', '添加留言', 1);
INSERT INTO `tb_permission` VALUES (7, 'message:list', '私信列表', 1);
INSERT INTO `tb_permission` VALUES (8, 'message:detail', '私信详情', 1);
INSERT INTO `tb_permission` VALUES (9, 'message:send', '发送私信', 1);
INSERT INTO `tb_permission` VALUES (10, 'give:like', '点赞', 1);
INSERT INTO `tb_permission` VALUES (11, 'follow:interest', '关注', 1);
INSERT INTO `tb_permission` VALUES (12, 'unfollow:interest', '取消关注', 1);
INSERT INTO `tb_permission` VALUES (13, 'follow:list', '关注列表', 1);
INSERT INTO `tb_permission` VALUES (14, 'follow:has', '是否关注', 1);
INSERT INTO `tb_permission` VALUES (15, 'goods:my', '我的商品', 1);
INSERT INTO `tb_permission` VALUES (16, 'goods:delete', '删除商品（逻辑删除）', 1);
INSERT INTO `tb_permission` VALUES (17, 'goods:list', '全部商品', 1);
INSERT INTO `tb_permission` VALUES (18, 'log:login', '登录日志', 1);
INSERT INTO `tb_permission` VALUES (19, 'message:unreadLetter', '未读私信', 1);
INSERT INTO `tb_permission` VALUES (20, 'user:code', '发送验证码', 1);
INSERT INTO `tb_permission` VALUES (21, 'user:setting', '修改信息', 1);
INSERT INTO `tb_permission` VALUES (22, 'user:list', '用户列表', 1);

-- ----------------------------
-- Table structure for tb_permission_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission_role`;
CREATE TABLE `tb_permission_role`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `permission_id` int(10) UNSIGNED NOT NULL,
  `role_id` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_permission_role
-- ----------------------------
INSERT INTO `tb_permission_role` VALUES (3, 2, 1);
INSERT INTO `tb_permission_role` VALUES (4, 2, 2);
INSERT INTO `tb_permission_role` VALUES (5, 3, 1);
INSERT INTO `tb_permission_role` VALUES (6, 3, 2);
INSERT INTO `tb_permission_role` VALUES (7, 4, 1);
INSERT INTO `tb_permission_role` VALUES (8, 4, 2);
INSERT INTO `tb_permission_role` VALUES (9, 5, 1);
INSERT INTO `tb_permission_role` VALUES (10, 5, 2);
INSERT INTO `tb_permission_role` VALUES (11, 6, 1);
INSERT INTO `tb_permission_role` VALUES (12, 6, 2);
INSERT INTO `tb_permission_role` VALUES (13, 7, 1);
INSERT INTO `tb_permission_role` VALUES (14, 7, 2);
INSERT INTO `tb_permission_role` VALUES (15, 8, 1);
INSERT INTO `tb_permission_role` VALUES (16, 8, 2);
INSERT INTO `tb_permission_role` VALUES (17, 9, 1);
INSERT INTO `tb_permission_role` VALUES (18, 9, 2);
INSERT INTO `tb_permission_role` VALUES (19, 10, 1);
INSERT INTO `tb_permission_role` VALUES (20, 10, 2);
INSERT INTO `tb_permission_role` VALUES (21, 11, 1);
INSERT INTO `tb_permission_role` VALUES (22, 11, 2);
INSERT INTO `tb_permission_role` VALUES (23, 12, 1);
INSERT INTO `tb_permission_role` VALUES (24, 12, 2);
INSERT INTO `tb_permission_role` VALUES (25, 13, 1);
INSERT INTO `tb_permission_role` VALUES (26, 13, 2);
INSERT INTO `tb_permission_role` VALUES (27, 14, 1);
INSERT INTO `tb_permission_role` VALUES (28, 14, 2);
INSERT INTO `tb_permission_role` VALUES (29, 15, 1);
INSERT INTO `tb_permission_role` VALUES (30, 15, 2);
INSERT INTO `tb_permission_role` VALUES (31, 16, 1);
INSERT INTO `tb_permission_role` VALUES (32, 16, 2);
INSERT INTO `tb_permission_role` VALUES (33, 17, 1);
INSERT INTO `tb_permission_role` VALUES (34, 18, 1);
INSERT INTO `tb_permission_role` VALUES (35, 19, 1);
INSERT INTO `tb_permission_role` VALUES (36, 19, 2);
INSERT INTO `tb_permission_role` VALUES (37, 20, 1);
INSERT INTO `tb_permission_role` VALUES (38, 20, 2);
INSERT INTO `tb_permission_role` VALUES (39, 21, 1);
INSERT INTO `tb_permission_role` VALUES (40, 21, 2);
INSERT INTO `tb_permission_role` VALUES (41, 22, 1);

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色名',
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否可用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES (1, '系统管理员', 1);
INSERT INTO `tb_role` VALUES (2, '普通用户', 1);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `address` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '联系地址',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号',
  `enabled` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用',
  `password` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '头像',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `admin` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否是管理员',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (3, 'Alice', '西区', '13555555553', 1, '$2a$10$/aKxLXki3tDScZn9MbmkZOXyFOxWZA8l9ygyhZce7oZDryRtQaQhG', 'https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/random-avatar/11.jpg', '2021-02-08 21:28:55', 0);

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(10) UNSIGNED NOT NULL,
  `role_id` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES (2, 3, 2);

SET FOREIGN_KEY_CHECKS = 1;
