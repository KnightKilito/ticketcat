/*
 Navicat Premium Data Transfer

 Source Server         : MySQL-zlc
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : 127.0.0.1:3306
 Source Schema         : tpm

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 13/09/2022 09:37:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cinema
-- ----------------------------
DROP TABLE IF EXISTS `cinema`;
CREATE TABLE `cinema`  (
  `cid` int NOT NULL AUTO_INCREMENT,
  `cname` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `brand` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `label` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2147438592 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cinema
-- ----------------------------
INSERT INTO `cinema` VALUES (2, '梦露歌剧院影城', '厦门市海沧区阿罗海城市广场4层', 0, '梦露影城', '福建省');
INSERT INTO `cinema` VALUES (3, '万达影城海沧体育中心店', '厦门市海沧区自由贸易试验区兴港六里171号2F/3F', 0, '万达影城', '福建省');
INSERT INTO `cinema` VALUES (4, '首星巨幕影城（华美空间店）', '厦门市湖里区华昌路132号华美空间文创园A2一层', 1, '首星影城', '福建省');
INSERT INTO `cinema` VALUES (5, '万达影城（SM广场杜比影院店）', '厦门市湖里区嘉禾路399号SM新生活广场（二期）三层', 1, '万达影城', '福建省');
INSERT INTO `cinema` VALUES (6, '金逸影城（光美五缘湾MX4D店）', '厦门市湖里区金湖路101号五缘湾乐都汇购物中心L4F007', 0, '金逸影城', '福建省');
INSERT INTO `cinema` VALUES (7, '中影国际影城(五缘湾海峡新岸店)', '厦门市湖里区环岛东路2486号旅游博览中心（砂之船奥莱B馆3层）', 0, '中影集团', '福建省');
INSERT INTO `cinema` VALUES (2130780162, '中影梦工坊（蔡塘杜比巨幕店）', '厦门市湖里区吕岭路蔡塘广场5楼中影梦工坊', 1, '中影集团', '福建省');
INSERT INTO `cinema` VALUES (2130780163, '金逸影城（旺角大学城店）', '厦门市集美区天马路旺角学生商业街', 1, '金逸影城', '福建省');
INSERT INTO `cinema` VALUES (2130780164, '厦门华谊兄弟电影中心', '厦门市集美区杏林湾路368号厦门嘉庚华谊兄弟电影中心', 1, '华谊兄弟', '福建省');
INSERT INTO `cinema` VALUES (2130780165, '万达影城（集美万达广场IMAX店）', '厦门市集美区银江路137号万达广场5号门娱乐楼4层', 1, '万达影城', '福建省');
INSERT INTO `cinema` VALUES (2130780166, '大地影院（厦门银泰百货CINITY店）', '厦门市集美区同集南路68号银泰百货L4层30号(5号门入口)', 1, '大地影院', '福建省');
INSERT INTO `cinema` VALUES (2130780167, '中影星美杏林湾影城', '厦门市集美区杏林湾商务运营中心6号楼3层', 1, '中影集团', '福建省');
INSERT INTO `cinema` VALUES (2130780168, '寰映影城(集美IOI广场激光IMAX店)', '厦门市集美区滨水中五里ioi MALL5层', 1, '寰映影城', '福建省');
INSERT INTO `cinema` VALUES (2130780169, '厦门中影国际影城（万科里店）', '厦门市集美区杏林街道宁海一里64号三层L301铺位', 1, '中影集团', '福建省');
INSERT INTO `cinema` VALUES (2147438591, '金逸影城（厦门西雅图店）', '厦门市海沧区钟林里332号西雅图城市广场1层', 1, '金逸影城', '福建省');

-- ----------------------------
-- Table structure for cinema_movie
-- ----------------------------
DROP TABLE IF EXISTS `cinema_movie`;
CREATE TABLE `cinema_movie`  (
  `mid` int NOT NULL,
  `cid` int NOT NULL,
  `cmid` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`cmid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cinema_movie
-- ----------------------------
INSERT INTO `cinema_movie` VALUES (1, 2147438591, 1);
INSERT INTO `cinema_movie` VALUES (2, 2147438591, 2);
INSERT INTO `cinema_movie` VALUES (3, 2147438591, 3);
INSERT INTO `cinema_movie` VALUES (4, 2147438591, 4);
INSERT INTO `cinema_movie` VALUES (5, 2147438591, 5);
INSERT INTO `cinema_movie` VALUES (6, 2147438591, 6);
INSERT INTO `cinema_movie` VALUES (7, 2147438591, 7);
INSERT INTO `cinema_movie` VALUES (1, 2, 8);
INSERT INTO `cinema_movie` VALUES (2, 2, 9);
INSERT INTO `cinema_movie` VALUES (3, 2, 10);
INSERT INTO `cinema_movie` VALUES (4, 2, 11);
INSERT INTO `cinema_movie` VALUES (5, 2, 12);
INSERT INTO `cinema_movie` VALUES (6, 2, 13);
INSERT INTO `cinema_movie` VALUES (7, 2, 14);
INSERT INTO `cinema_movie` VALUES (1, 3, 15);
INSERT INTO `cinema_movie` VALUES (2, 3, 16);
INSERT INTO `cinema_movie` VALUES (1, 4, 17);
INSERT INTO `cinema_movie` VALUES (5, 4, 18);
INSERT INTO `cinema_movie` VALUES (5, 5, 19);
INSERT INTO `cinema_movie` VALUES (7, 5, 20);
INSERT INTO `cinema_movie` VALUES (3, 6, 21);
INSERT INTO `cinema_movie` VALUES (5, 6, 22);
INSERT INTO `cinema_movie` VALUES (6, 7, 23);
INSERT INTO `cinema_movie` VALUES (1, 2130780162, 24);
INSERT INTO `cinema_movie` VALUES (3, 2130780162, 25);

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection`  (
  `collectionid` int NOT NULL AUTO_INCREMENT,
  `uid` int NULL DEFAULT NULL,
  `mid` int NULL DEFAULT NULL,
  PRIMARY KEY (`collectionid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 82604 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of collection
-- ----------------------------
INSERT INTO `collection` VALUES (18298, 583041026, 2);
INSERT INTO `collection` VALUES (57493, 838889473, 1);
INSERT INTO `collection` VALUES (72134, 838889473, 2);
INSERT INTO `collection` VALUES (82603, 838889473, 4);

-- ----------------------------
-- Table structure for hall
-- ----------------------------
DROP TABLE IF EXISTS `hall`;
CREATE TABLE `hall`  (
  `hid` int NOT NULL AUTO_INCREMENT,
  `hname` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `cid` int NULL DEFAULT NULL,
  `rowsize` int NULL DEFAULT NULL,
  `columnsize` int NULL DEFAULT NULL,
  PRIMARY KEY (`hid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of hall
-- ----------------------------
INSERT INTO `hall` VALUES (1, '1号厅', 2, 5, 10);
INSERT INTO `hall` VALUES (2, '2号厅', 2, 5, 10);
INSERT INTO `hall` VALUES (3, '3号厅', 2, 5, 10);
INSERT INTO `hall` VALUES (4, '4号厅', 2, 5, 10);

-- ----------------------------
-- Table structure for movie
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie`  (
  `mid` int NOT NULL AUTO_INCREMENT,
  `mname` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `context` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `score` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `director` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `actor` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `label` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `rtime` date NULL DEFAULT NULL,
  `duration` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `money` double NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `style` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`mid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 981594117 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of movie
-- ----------------------------
INSERT INTO `movie` VALUES (1, '哆啦A梦：大雄的宇宙小战争', '大雄意外结识拇指外星人帕比，在哆啦A梦的帮助下，与大家一起穿越星际共同去守护匹里卡星，并开展了一段奇妙的宇宙冒险。', '9.9', '山口晋', '水田山葵', '动画', '2022-05-28', '123', 50, 'https://img.alicdn.com/bao/uploaded/i4/O1CN01Rcc8zd1YAI5QECmKy_!!6000000003018-0-alipicbeacon.jpg_300x300.jpg', 1, 'IMAX厅');
INSERT INTO `movie` VALUES (2, '云霄之上', '这是一部在生存与毁灭间行走的战争电影。在战争废墟的慷慨悲歌中，年轻的红军战士洪启辰接到军令，必须在48小时内炸毁敌人的弹药库。这是一条赴死的军令，他开始在危机四伏的群山里...', '9.5', '刘智海', '陈伟鑫,吴嘉辉', '战争', '2022-06-17', '124', 42, 'https://img.alicdn.com/bao/uploaded/i4/O1CN01vpMJGM1U8DpvwSwGp_!!6000000002472-0-alipicbeacon.jpg_300x300.jpg', 1, 'CINITY厅');
INSERT INTO `movie` VALUES (3, '你是我的春天', '双影帝影后集结，献给每一个努力生活的“你”和“我”。影片由中国电影家协会指导拍摄，陈道明任总监制，黄渤任监制，实力派演员强力加盟演绎人间温情故事。', '8.2', '周楠, 张弛', '周冬雨,尹昉', '剧情', '2022-06-02', '200', 50, 'https://img.alicdn.com/bao/uploaded/i1/O1CN01ehZNWb1OoSfUmbeWi_!!6000000001752-0-alipicbeacon.jpg_300x300.jpg', 1, '杜比影院');
INSERT INTO `movie` VALUES (4, '侏罗纪世界3', '影片为《侏罗纪世界》系列的完结篇，讲述了自从恐龙进入了人类世界，侏罗纪公园已经不复存在的故事', '8.5', '科林·特莱沃若', '克里斯·帕拉特', '科幻', '2022-06-10', '100', 50, 'https://img.alicdn.com/bao/uploaded/i3/O1CN018va4OM1vpGgYpdMAX_!!6000000006221-0-alipicbeacon.jpg_300x300.jpg', 1, 'MX4D');
INSERT INTO `movie` VALUES (5, '一周的朋友', '胜华高中的复读班上，新来了一个转学生林湘之（赵今麦饰），她文静聪明却总是孤独一人，不跟其他的同学做朋友。但这样的她却吸引了班上的学渣徐又树（林一饰）的注意。', '7.5', '林孝谦', '赵今麦,林一', '爱情', '2022-06-13', '200', 34, 'https://img.alicdn.com/bao/uploaded/i3/O1CN01FVhg4k1oyhX7eZVfq_!!6000000005294-0-alipicbeacon.jpg_300x300.jpg', 1, 'VIP厅');
INSERT INTO `movie` VALUES (6, '精灵旅社4', '火爆全球的系列动画电影《精灵旅社》迎来大结局，十年经典欢乐完结！在精灵旅社125周年庆典派对上，一场意外使德古拉带领的精灵家族首次变身成人，而约翰尼则成了怪兽。', '8.5', '德里克·德莱蒙', '凯瑟琳·哈恩', '喜剧', '2022-04-03', '140', 43, 'https://img.alicdn.com/bao/uploaded/i1/O1CN01CRS3fm1EiNERhajHf_!!6000000000385-0-alipicbeacon.jpg_300x300.jpg', 1, '3D IMAX');
INSERT INTO `movie` VALUES (7, '长津湖之水门桥', '电影《长津湖之水门桥》以抗美援朝战争第二次战役中的长津湖战役为背景，讲述了在结束了新兴里和下碣隅里的战斗之后，七连战士们又接到了更艰巨的任务……', '9.2', '徐克', '吴京,易烊千玺', '历史', '2022-07-01', '135', 50, 'https://img.alicdn.com/bao/uploaded/i1/O1CN01sSmj2b1daSm6IAUcs_!!6000000003752-0-alipicbeacon.jpg_300x300.jpg', -1, '3D IMAX');
INSERT INTO `movie` VALUES (981594114, '神奇动物：邓布利多之谜', '阿不思·邓布利多教授（裘德·洛 饰）意识到强大的黑巫师盖勒特·格林德沃（麦斯·米科尔森 饰）正试图夺取魔法世界的控制权。邓布利多了解仅凭他一人之力，将无法阻止格林德沃，于...', '7.7', '大卫·叶茨', '埃迪·雷德梅恩', '奇幻', '2022-07-14', '120', 47, 'https://img.alicdn.com/bao/uploaded/i3/O1CN014RLbt71jzYAo1cXQM_!!6000000004619-0-alipicbeacon.jpg_300x300.jpg', -1, '杜比影院');
INSERT INTO `movie` VALUES (981594115, '唐顿庄园2', '这是2019年电影《唐顿庄园》的续集，在这部电影中，英国国王和王后访问了Crowley家族和唐顿庄园的工作人员。', '8.8', '西蒙·柯蒂斯', '米歇尔·道克瑞', '剧情', '2022-07-19', '119', 50, 'https://img.alicdn.com/bao/uploaded/i3/O1CN01PzJrVd1Xqb3TPj8A5_!!6000000002975-0-alipicbeacon.jpg_300x300.jpg', -1, 'VIP厅');
INSERT INTO `movie` VALUES (981594116, '邓小平小道', '文革期间邓小平被革职下放江西，面临多重高压危机四伏，但始终保持坚毅、乐观，初心不改。为了他的安全，工厂师傅为他专门开辟了一条近道。三年零四个月，他每天沿着这条小道去工厂劳动', '7.3', '雷献禾', '卢奇', '传记', '2022-07-22', '105', 32, 'https://img.alicdn.com/bao/uploaded/i1/O1CN01wD9S1P1rKnro6bPZh_!!6000000005613-0-alipicbeacon.jpg_300x300.jpg', -1, '杜比影院');

-- ----------------------------
-- Table structure for moviecomment
-- ----------------------------
DROP TABLE IF EXISTS `moviecomment`;
CREATE TABLE `moviecomment`  (
  `mcid` int NOT NULL,
  `tid` int NULL DEFAULT NULL,
  `comment` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `uscore` double(255, 0) NULL DEFAULT NULL,
  PRIMARY KEY (`mcid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of moviecomment
-- ----------------------------

-- ----------------------------
-- Table structure for session
-- ----------------------------
DROP TABLE IF EXISTS `session`;
CREATE TABLE `session`  (
  `sid` int NOT NULL AUTO_INCREMENT,
  `cid` int NOT NULL,
  `mid` int NOT NULL,
  `tprice` double(10, 2) NULL DEFAULT NULL,
  `maxsize` int NULL DEFAULT NULL,
  `orderednum` int NULL DEFAULT NULL,
  `starttime` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `endtime` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `hid` int NULL DEFAULT NULL,
  `date` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1916866576 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of session
-- ----------------------------
INSERT INTO `session` VALUES (1, 2, 3, 42.90, 2200, 20, '16:00', '18:00', 2, '6月23号');
INSERT INTO `session` VALUES (1916866566, 2, 4, 31.90, 50, 13, '12:00', '13:00', 3, '7月1号');
INSERT INTO `session` VALUES (1916866569, 2, 4, 55.90, 89, 0, '13:00', '14:40', 4, '7月1号');
INSERT INTO `session` VALUES (1916866573, 2, 4, 55.90, 89, 0, '13:00', '14:40', 4, '7月2号');
INSERT INTO `session` VALUES (1916866574, 2, 4, 35.90, 99, 0, '19:00', '20:40', 3, '7月3号');
INSERT INTO `session` VALUES (1916866575, 2, 3, 25.60, 99, 0, '9:00', '12:20', 3, '7月2号');

-- ----------------------------
-- Table structure for sessionseat
-- ----------------------------
DROP TABLE IF EXISTS `sessionseat`;
CREATE TABLE `sessionseat`  (
  `sessionseatid` int NOT NULL AUTO_INCREMENT,
  `sid` int NULL DEFAULT NULL,
  `hid` int NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL COMMENT '当前座位状态',
  `uid` int NULL DEFAULT NULL,
  `rowname` int NULL DEFAULT NULL COMMENT '这个座位是第几排',
  `columnname` int NULL DEFAULT NULL COMMENT '这个座位是第几列',
  `version` int NULL DEFAULT NULL,
  PRIMARY KEY (`sessionseatid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 258 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sessionseat
-- ----------------------------
INSERT INTO `sessionseat` VALUES (1, 1916866566, 3, 0, NULL, 1, 1, 1);
INSERT INTO `sessionseat` VALUES (2, 1916866566, 3, 0, NULL, 1, 2, 1);
INSERT INTO `sessionseat` VALUES (3, 1916866566, 3, 0, NULL, 1, 3, 1);
INSERT INTO `sessionseat` VALUES (4, 1916866566, 3, 0, NULL, 1, 4, 1);
INSERT INTO `sessionseat` VALUES (5, 1916866566, 3, 0, NULL, 1, 5, 1);
INSERT INTO `sessionseat` VALUES (6, 1916866566, 3, 0, NULL, 1, 6, 1);
INSERT INTO `sessionseat` VALUES (7, 1916866566, 3, 0, NULL, 1, 7, 1);
INSERT INTO `sessionseat` VALUES (8, 1916866566, 3, 0, NULL, 1, 8, 1);
INSERT INTO `sessionseat` VALUES (9, 1916866566, 3, 0, NULL, 1, 9, 1);
INSERT INTO `sessionseat` VALUES (10, 1916866566, 3, 0, NULL, 1, 10, 1);
INSERT INTO `sessionseat` VALUES (11, 1916866566, 3, 0, NULL, 2, 1, 1);
INSERT INTO `sessionseat` VALUES (12, 1916866566, 3, 0, NULL, 2, 2, 1);
INSERT INTO `sessionseat` VALUES (13, 1916866566, 3, 0, NULL, 2, 3, 1);
INSERT INTO `sessionseat` VALUES (14, 1916866566, 3, 0, NULL, 2, 4, 1);
INSERT INTO `sessionseat` VALUES (15, 1916866566, 3, 0, NULL, 2, 5, 1);
INSERT INTO `sessionseat` VALUES (16, 1916866566, 3, 0, NULL, 2, 6, 1);
INSERT INTO `sessionseat` VALUES (17, 1916866566, 3, 0, NULL, 2, 7, 1);
INSERT INTO `sessionseat` VALUES (18, 1916866566, 3, 0, NULL, 2, 8, 1);
INSERT INTO `sessionseat` VALUES (19, 1916866566, 3, 0, NULL, 2, 9, 1);
INSERT INTO `sessionseat` VALUES (20, 1916866566, 3, 0, NULL, 2, 10, 1);
INSERT INTO `sessionseat` VALUES (21, 1916866566, 3, 0, NULL, 3, 1, 1);
INSERT INTO `sessionseat` VALUES (22, 1916866566, 3, 0, NULL, 3, 2, 1);
INSERT INTO `sessionseat` VALUES (23, 1916866566, 3, 0, NULL, 3, 3, 1);
INSERT INTO `sessionseat` VALUES (24, 1916866566, 3, 0, NULL, 3, 4, 1);
INSERT INTO `sessionseat` VALUES (25, 1916866566, 3, 0, NULL, 3, 5, 1);
INSERT INTO `sessionseat` VALUES (26, 1916866566, 3, 0, NULL, 3, 6, 1);
INSERT INTO `sessionseat` VALUES (27, 1916866566, 3, 0, NULL, 3, 7, 1);
INSERT INTO `sessionseat` VALUES (28, 1916866566, 3, 0, NULL, 3, 8, 1);
INSERT INTO `sessionseat` VALUES (29, 1916866566, 3, 0, NULL, 3, 9, 1);
INSERT INTO `sessionseat` VALUES (30, 1916866566, 3, 0, NULL, 3, 10, 1);
INSERT INTO `sessionseat` VALUES (31, 1916866566, 3, 0, NULL, 4, 1, 1);
INSERT INTO `sessionseat` VALUES (32, 1916866566, 3, 0, NULL, 4, 2, 1);
INSERT INTO `sessionseat` VALUES (33, 1916866566, 3, 0, NULL, 4, 3, 1);
INSERT INTO `sessionseat` VALUES (34, 1916866566, 3, 0, NULL, 4, 4, 1);
INSERT INTO `sessionseat` VALUES (35, 1916866566, 3, 0, NULL, 4, 5, 1);
INSERT INTO `sessionseat` VALUES (36, 1916866566, 3, 0, NULL, 4, 6, 1);
INSERT INTO `sessionseat` VALUES (37, 1916866566, 3, 0, NULL, 4, 7, 1);
INSERT INTO `sessionseat` VALUES (38, 1916866566, 3, 0, NULL, 4, 8, 1);
INSERT INTO `sessionseat` VALUES (39, 1916866566, 3, 0, NULL, 4, 9, 1);
INSERT INTO `sessionseat` VALUES (40, 1916866566, 3, 0, NULL, 4, 10, 1);
INSERT INTO `sessionseat` VALUES (41, 1916866566, 3, 0, NULL, 5, 1, 1);
INSERT INTO `sessionseat` VALUES (42, 1916866566, 3, 0, NULL, 5, 2, 1);
INSERT INTO `sessionseat` VALUES (43, 1916866566, 3, 0, NULL, 5, 3, 1);
INSERT INTO `sessionseat` VALUES (44, 1916866566, 3, 0, NULL, 5, 4, 1);
INSERT INTO `sessionseat` VALUES (45, 1916866566, 3, 0, NULL, 5, 5, 1);
INSERT INTO `sessionseat` VALUES (46, 1916866566, 3, 0, NULL, 5, 6, 1);
INSERT INTO `sessionseat` VALUES (47, 1916866566, 3, 0, NULL, 5, 7, 1);
INSERT INTO `sessionseat` VALUES (48, 1916866566, 3, 0, NULL, 5, 8, 1);
INSERT INTO `sessionseat` VALUES (49, 1916866566, 3, 0, NULL, 5, 9, 1);
INSERT INTO `sessionseat` VALUES (50, 1916866566, 3, 0, NULL, 5, 10, 1);
INSERT INTO `sessionseat` VALUES (51, 1916866569, 4, 0, NULL, 1, 1, 1);
INSERT INTO `sessionseat` VALUES (52, 1916866569, 4, 0, NULL, 1, 2, 1);
INSERT INTO `sessionseat` VALUES (53, 1916866569, 4, 0, NULL, 1, 3, 1);
INSERT INTO `sessionseat` VALUES (54, 1916866569, 4, 0, NULL, 1, 4, 1);
INSERT INTO `sessionseat` VALUES (55, 1916866569, 4, 0, NULL, 1, 5, 1);
INSERT INTO `sessionseat` VALUES (56, 1916866569, 4, 0, NULL, 2, 1, 1);
INSERT INTO `sessionseat` VALUES (57, 1916866569, 4, 0, NULL, 2, 2, 1);
INSERT INTO `sessionseat` VALUES (58, 1916866569, 4, 0, NULL, 2, 3, 1);
INSERT INTO `sessionseat` VALUES (59, 1916866569, 4, 0, NULL, 2, 4, 1);
INSERT INTO `sessionseat` VALUES (60, 1916866569, 4, 0, NULL, 2, 5, 1);
INSERT INTO `sessionseat` VALUES (61, 1916866569, 4, 0, NULL, 3, 1, 1);
INSERT INTO `sessionseat` VALUES (62, 1916866569, 4, 0, NULL, 3, 2, 1);
INSERT INTO `sessionseat` VALUES (63, 1916866569, 4, 0, NULL, 3, 3, 1);
INSERT INTO `sessionseat` VALUES (64, 1916866569, 4, 0, NULL, 3, 4, 1);
INSERT INTO `sessionseat` VALUES (65, 1916866569, 4, 0, NULL, 3, 5, 1);
INSERT INTO `sessionseat` VALUES (66, 1916866569, 4, 0, NULL, 4, 1, 1);
INSERT INTO `sessionseat` VALUES (67, 1916866569, 4, 0, NULL, 4, 2, 1);
INSERT INTO `sessionseat` VALUES (68, 1916866569, 4, 0, NULL, 4, 3, 1);
INSERT INTO `sessionseat` VALUES (69, 1916866569, 4, 0, NULL, 4, 4, 1);
INSERT INTO `sessionseat` VALUES (70, 1916866569, 4, 0, NULL, 4, 5, 1);
INSERT INTO `sessionseat` VALUES (71, 1916866569, 4, 0, NULL, 5, 1, 1);
INSERT INTO `sessionseat` VALUES (72, 1916866569, 4, 0, NULL, 5, 2, 1);
INSERT INTO `sessionseat` VALUES (73, 1916866569, 4, 0, NULL, 5, 3, 1);
INSERT INTO `sessionseat` VALUES (74, 1916866569, 4, 0, NULL, 5, 4, 1);
INSERT INTO `sessionseat` VALUES (75, 1916866569, 4, 0, NULL, 5, 5, 1);
INSERT INTO `sessionseat` VALUES (76, 1916866569, 4, 0, NULL, 6, 1, 1);
INSERT INTO `sessionseat` VALUES (77, 1916866569, 4, 0, NULL, 6, 2, 1);
INSERT INTO `sessionseat` VALUES (78, 1916866569, 4, 0, NULL, 6, 3, 1);
INSERT INTO `sessionseat` VALUES (79, 1916866569, 4, 0, NULL, 6, 4, 1);
INSERT INTO `sessionseat` VALUES (80, 1916866569, 4, 0, NULL, 6, 5, 1);
INSERT INTO `sessionseat` VALUES (81, 1916866569, 4, 0, NULL, 7, 1, 1);
INSERT INTO `sessionseat` VALUES (82, 1916866569, 4, 0, NULL, 7, 2, 1);
INSERT INTO `sessionseat` VALUES (83, 1916866569, 4, 0, NULL, 7, 3, 1);
INSERT INTO `sessionseat` VALUES (84, 1916866569, 4, 0, NULL, 7, 4, 1);
INSERT INTO `sessionseat` VALUES (85, 1916866569, 4, 0, NULL, 7, 5, 1);
INSERT INTO `sessionseat` VALUES (86, 1916866569, 4, 0, NULL, 8, 1, 1);
INSERT INTO `sessionseat` VALUES (87, 1916866569, 4, 0, NULL, 8, 2, 1);
INSERT INTO `sessionseat` VALUES (88, 1916866569, 4, 0, NULL, 8, 3, 1);
INSERT INTO `sessionseat` VALUES (89, 1916866569, 4, 0, NULL, 8, 4, 1);
INSERT INTO `sessionseat` VALUES (90, 1916866569, 4, 0, NULL, 8, 5, 1);
INSERT INTO `sessionseat` VALUES (91, 1916866569, 4, 0, NULL, 9, 1, 1);
INSERT INTO `sessionseat` VALUES (92, 1916866569, 4, 0, NULL, 9, 2, 1);
INSERT INTO `sessionseat` VALUES (93, 1916866569, 4, 0, NULL, 9, 3, 1);
INSERT INTO `sessionseat` VALUES (94, 1916866569, 4, 0, NULL, 9, 4, 1);
INSERT INTO `sessionseat` VALUES (95, 1916866569, 4, 0, NULL, 9, 5, 1);
INSERT INTO `sessionseat` VALUES (96, 1916866569, 4, 0, NULL, 10, 1, 1);
INSERT INTO `sessionseat` VALUES (97, 1916866569, 4, 0, NULL, 10, 2, 1);
INSERT INTO `sessionseat` VALUES (98, 1916866569, 4, 0, NULL, 10, 3, 1);
INSERT INTO `sessionseat` VALUES (99, 1916866569, 4, 0, NULL, 10, 4, 1);
INSERT INTO `sessionseat` VALUES (100, 1916866569, 4, 0, NULL, 10, 5, 1);
INSERT INTO `sessionseat` VALUES (108, 1916866573, 4, 0, NULL, 1, 1, 1);
INSERT INTO `sessionseat` VALUES (109, 1916866573, 4, 0, NULL, 1, 2, 1);
INSERT INTO `sessionseat` VALUES (110, 1916866573, 4, 0, NULL, 1, 3, 1);
INSERT INTO `sessionseat` VALUES (111, 1916866573, 4, 0, NULL, 1, 4, 1);
INSERT INTO `sessionseat` VALUES (112, 1916866573, 4, 0, NULL, 1, 5, 1);
INSERT INTO `sessionseat` VALUES (113, 1916866573, 4, 0, NULL, 2, 1, 1);
INSERT INTO `sessionseat` VALUES (114, 1916866573, 4, 0, NULL, 2, 2, 1);
INSERT INTO `sessionseat` VALUES (115, 1916866573, 4, 0, NULL, 2, 3, 1);
INSERT INTO `sessionseat` VALUES (116, 1916866573, 4, 0, NULL, 2, 4, 1);
INSERT INTO `sessionseat` VALUES (117, 1916866573, 4, 0, NULL, 2, 5, 1);
INSERT INTO `sessionseat` VALUES (118, 1916866573, 4, 0, NULL, 3, 1, 1);
INSERT INTO `sessionseat` VALUES (119, 1916866573, 4, 0, NULL, 3, 2, 1);
INSERT INTO `sessionseat` VALUES (120, 1916866573, 4, 0, NULL, 3, 3, 1);
INSERT INTO `sessionseat` VALUES (121, 1916866573, 4, 0, NULL, 3, 4, 1);
INSERT INTO `sessionseat` VALUES (122, 1916866573, 4, 0, NULL, 3, 5, 1);
INSERT INTO `sessionseat` VALUES (123, 1916866573, 4, 0, NULL, 4, 1, 1);
INSERT INTO `sessionseat` VALUES (124, 1916866573, 4, 0, NULL, 4, 2, 1);
INSERT INTO `sessionseat` VALUES (125, 1916866573, 4, 0, NULL, 4, 3, 1);
INSERT INTO `sessionseat` VALUES (126, 1916866573, 4, 0, NULL, 4, 4, 1);
INSERT INTO `sessionseat` VALUES (127, 1916866573, 4, 0, NULL, 4, 5, 1);
INSERT INTO `sessionseat` VALUES (128, 1916866573, 4, 0, NULL, 5, 1, 1);
INSERT INTO `sessionseat` VALUES (129, 1916866573, 4, 0, NULL, 5, 2, 1);
INSERT INTO `sessionseat` VALUES (130, 1916866573, 4, 0, NULL, 5, 3, 1);
INSERT INTO `sessionseat` VALUES (131, 1916866573, 4, 0, NULL, 5, 4, 1);
INSERT INTO `sessionseat` VALUES (132, 1916866573, 4, 0, NULL, 5, 5, 1);
INSERT INTO `sessionseat` VALUES (133, 1916866573, 4, 0, NULL, 6, 1, 1);
INSERT INTO `sessionseat` VALUES (134, 1916866573, 4, 0, NULL, 6, 2, 1);
INSERT INTO `sessionseat` VALUES (135, 1916866573, 4, 0, NULL, 6, 3, 1);
INSERT INTO `sessionseat` VALUES (136, 1916866573, 4, 0, NULL, 6, 4, 1);
INSERT INTO `sessionseat` VALUES (137, 1916866573, 4, 0, NULL, 6, 5, 1);
INSERT INTO `sessionseat` VALUES (138, 1916866573, 4, 0, NULL, 7, 1, 1);
INSERT INTO `sessionseat` VALUES (139, 1916866573, 4, 0, NULL, 7, 2, 1);
INSERT INTO `sessionseat` VALUES (140, 1916866573, 4, 0, NULL, 7, 3, 1);
INSERT INTO `sessionseat` VALUES (141, 1916866573, 4, 0, NULL, 7, 4, 1);
INSERT INTO `sessionseat` VALUES (142, 1916866573, 4, 0, NULL, 7, 5, 1);
INSERT INTO `sessionseat` VALUES (143, 1916866573, 4, 0, NULL, 8, 1, 1);
INSERT INTO `sessionseat` VALUES (144, 1916866573, 4, 0, NULL, 8, 2, 1);
INSERT INTO `sessionseat` VALUES (145, 1916866573, 4, 0, NULL, 8, 3, 1);
INSERT INTO `sessionseat` VALUES (146, 1916866573, 4, 0, NULL, 8, 4, 1);
INSERT INTO `sessionseat` VALUES (147, 1916866573, 4, 0, NULL, 8, 5, 1);
INSERT INTO `sessionseat` VALUES (148, 1916866573, 4, 0, NULL, 9, 1, 1);
INSERT INTO `sessionseat` VALUES (149, 1916866573, 4, 0, NULL, 9, 2, 1);
INSERT INTO `sessionseat` VALUES (150, 1916866573, 4, 0, NULL, 9, 3, 1);
INSERT INTO `sessionseat` VALUES (151, 1916866573, 4, 0, NULL, 9, 4, 1);
INSERT INTO `sessionseat` VALUES (152, 1916866573, 4, 0, NULL, 9, 5, 1);
INSERT INTO `sessionseat` VALUES (153, 1916866573, 4, 0, NULL, 10, 1, 1);
INSERT INTO `sessionseat` VALUES (154, 1916866573, 4, 0, NULL, 10, 2, 1);
INSERT INTO `sessionseat` VALUES (155, 1916866573, 4, 0, NULL, 10, 3, 1);
INSERT INTO `sessionseat` VALUES (156, 1916866573, 4, 0, NULL, 10, 4, 1);
INSERT INTO `sessionseat` VALUES (157, 1916866573, 4, 0, NULL, 10, 5, 1);
INSERT INTO `sessionseat` VALUES (158, 1916866574, 3, 0, NULL, 1, 1, 1);
INSERT INTO `sessionseat` VALUES (159, 1916866574, 3, 0, NULL, 1, 2, 1);
INSERT INTO `sessionseat` VALUES (160, 1916866574, 3, 0, NULL, 1, 3, 1);
INSERT INTO `sessionseat` VALUES (161, 1916866574, 3, 0, NULL, 1, 4, 1);
INSERT INTO `sessionseat` VALUES (162, 1916866574, 3, 0, NULL, 1, 5, 1);
INSERT INTO `sessionseat` VALUES (163, 1916866574, 3, 0, NULL, 1, 6, 1);
INSERT INTO `sessionseat` VALUES (164, 1916866574, 3, 0, NULL, 1, 7, 1);
INSERT INTO `sessionseat` VALUES (165, 1916866574, 3, 0, NULL, 1, 8, 1);
INSERT INTO `sessionseat` VALUES (166, 1916866574, 3, 0, NULL, 1, 9, 1);
INSERT INTO `sessionseat` VALUES (167, 1916866574, 3, 0, NULL, 1, 10, 1);
INSERT INTO `sessionseat` VALUES (168, 1916866574, 3, 0, NULL, 2, 1, 1);
INSERT INTO `sessionseat` VALUES (169, 1916866574, 3, 0, NULL, 2, 2, 1);
INSERT INTO `sessionseat` VALUES (170, 1916866574, 3, 1, NULL, 2, 3, 1);
INSERT INTO `sessionseat` VALUES (171, 1916866574, 3, 1, NULL, 2, 4, 1);
INSERT INTO `sessionseat` VALUES (172, 1916866574, 3, 1, NULL, 2, 5, 1);
INSERT INTO `sessionseat` VALUES (173, 1916866574, 3, 1, NULL, 2, 6, 1);
INSERT INTO `sessionseat` VALUES (174, 1916866574, 3, 1, NULL, 2, 7, 1);
INSERT INTO `sessionseat` VALUES (175, 1916866574, 3, 0, NULL, 2, 8, 1);
INSERT INTO `sessionseat` VALUES (176, 1916866574, 3, 0, NULL, 2, 9, 1);
INSERT INTO `sessionseat` VALUES (177, 1916866574, 3, 0, NULL, 2, 10, 1);
INSERT INTO `sessionseat` VALUES (178, 1916866574, 3, 0, NULL, 3, 1, 1);
INSERT INTO `sessionseat` VALUES (179, 1916866574, 3, 0, NULL, 3, 2, 1);
INSERT INTO `sessionseat` VALUES (180, 1916866574, 3, 1, NULL, 3, 3, 1);
INSERT INTO `sessionseat` VALUES (181, 1916866574, 3, 1, NULL, 3, 4, 1);
INSERT INTO `sessionseat` VALUES (182, 1916866574, 3, 1, NULL, 3, 5, 1);
INSERT INTO `sessionseat` VALUES (183, 1916866574, 3, 1, NULL, 3, 6, 1);
INSERT INTO `sessionseat` VALUES (184, 1916866574, 3, 1, NULL, 3, 7, 1);
INSERT INTO `sessionseat` VALUES (185, 1916866574, 3, 0, NULL, 3, 8, 1);
INSERT INTO `sessionseat` VALUES (186, 1916866574, 3, 0, NULL, 3, 9, 1);
INSERT INTO `sessionseat` VALUES (187, 1916866574, 3, 0, NULL, 3, 10, 1);
INSERT INTO `sessionseat` VALUES (188, 1916866574, 3, 0, NULL, 4, 1, 1);
INSERT INTO `sessionseat` VALUES (189, 1916866574, 3, 0, NULL, 4, 2, 1);
INSERT INTO `sessionseat` VALUES (190, 1916866574, 3, 0, NULL, 4, 3, 1);
INSERT INTO `sessionseat` VALUES (191, 1916866574, 3, 0, NULL, 4, 4, 1);
INSERT INTO `sessionseat` VALUES (192, 1916866574, 3, 0, NULL, 4, 5, 1);
INSERT INTO `sessionseat` VALUES (193, 1916866574, 3, 0, NULL, 4, 6, 1);
INSERT INTO `sessionseat` VALUES (194, 1916866574, 3, 0, NULL, 4, 7, 1);
INSERT INTO `sessionseat` VALUES (195, 1916866574, 3, 0, NULL, 4, 8, 1);
INSERT INTO `sessionseat` VALUES (196, 1916866574, 3, 0, NULL, 4, 9, 1);
INSERT INTO `sessionseat` VALUES (197, 1916866574, 3, 0, NULL, 4, 10, 1);
INSERT INTO `sessionseat` VALUES (198, 1916866574, 3, 0, NULL, 5, 1, 1);
INSERT INTO `sessionseat` VALUES (199, 1916866574, 3, 0, NULL, 5, 2, 1);
INSERT INTO `sessionseat` VALUES (200, 1916866574, 3, 0, NULL, 5, 3, 1);
INSERT INTO `sessionseat` VALUES (201, 1916866574, 3, 0, NULL, 5, 4, 1);
INSERT INTO `sessionseat` VALUES (202, 1916866574, 3, 0, NULL, 5, 5, 1);
INSERT INTO `sessionseat` VALUES (203, 1916866574, 3, 0, NULL, 5, 6, 1);
INSERT INTO `sessionseat` VALUES (204, 1916866574, 3, 0, NULL, 5, 7, 1);
INSERT INTO `sessionseat` VALUES (205, 1916866574, 3, 0, NULL, 5, 8, 1);
INSERT INTO `sessionseat` VALUES (206, 1916866574, 3, 0, NULL, 5, 9, 1);
INSERT INTO `sessionseat` VALUES (207, 1916866574, 3, 0, NULL, 5, 10, 1);
INSERT INTO `sessionseat` VALUES (208, 1916866575, 3, 0, NULL, 1, 1, 1);
INSERT INTO `sessionseat` VALUES (209, 1916866575, 3, 0, NULL, 1, 2, 1);
INSERT INTO `sessionseat` VALUES (210, 1916866575, 3, 0, NULL, 1, 3, 1);
INSERT INTO `sessionseat` VALUES (211, 1916866575, 3, 0, NULL, 1, 4, 1);
INSERT INTO `sessionseat` VALUES (212, 1916866575, 3, 0, NULL, 1, 5, 1);
INSERT INTO `sessionseat` VALUES (213, 1916866575, 3, 0, NULL, 1, 6, 1);
INSERT INTO `sessionseat` VALUES (214, 1916866575, 3, 0, NULL, 1, 7, 1);
INSERT INTO `sessionseat` VALUES (215, 1916866575, 3, 0, NULL, 1, 8, 1);
INSERT INTO `sessionseat` VALUES (216, 1916866575, 3, 0, NULL, 1, 9, 1);
INSERT INTO `sessionseat` VALUES (217, 1916866575, 3, 0, NULL, 1, 10, 1);
INSERT INTO `sessionseat` VALUES (218, 1916866575, 3, 0, NULL, 2, 1, 1);
INSERT INTO `sessionseat` VALUES (219, 1916866575, 3, 0, NULL, 2, 2, 1);
INSERT INTO `sessionseat` VALUES (220, 1916866575, 3, 1, NULL, 2, 3, 1);
INSERT INTO `sessionseat` VALUES (221, 1916866575, 3, 1, NULL, 2, 4, 1);
INSERT INTO `sessionseat` VALUES (222, 1916866575, 3, 1, NULL, 2, 5, 1);
INSERT INTO `sessionseat` VALUES (223, 1916866575, 3, 1, NULL, 2, 6, 1);
INSERT INTO `sessionseat` VALUES (224, 1916866575, 3, 1, NULL, 2, 7, 1);
INSERT INTO `sessionseat` VALUES (225, 1916866575, 3, 0, NULL, 2, 8, 1);
INSERT INTO `sessionseat` VALUES (226, 1916866575, 3, 0, NULL, 2, 9, 1);
INSERT INTO `sessionseat` VALUES (227, 1916866575, 3, 0, NULL, 2, 10, 1);
INSERT INTO `sessionseat` VALUES (228, 1916866575, 3, 0, NULL, 3, 1, 1);
INSERT INTO `sessionseat` VALUES (229, 1916866575, 3, 0, NULL, 3, 2, 1);
INSERT INTO `sessionseat` VALUES (230, 1916866575, 3, 1, NULL, 3, 3, 1);
INSERT INTO `sessionseat` VALUES (231, 1916866575, 3, 1, NULL, 3, 4, 1);
INSERT INTO `sessionseat` VALUES (232, 1916866575, 3, 1, NULL, 3, 5, 1);
INSERT INTO `sessionseat` VALUES (233, 1916866575, 3, 1, NULL, 3, 6, 1);
INSERT INTO `sessionseat` VALUES (234, 1916866575, 3, 1, NULL, 3, 7, 1);
INSERT INTO `sessionseat` VALUES (235, 1916866575, 3, 0, NULL, 3, 8, 1);
INSERT INTO `sessionseat` VALUES (236, 1916866575, 3, 0, NULL, 3, 9, 1);
INSERT INTO `sessionseat` VALUES (237, 1916866575, 3, 0, NULL, 3, 10, 1);
INSERT INTO `sessionseat` VALUES (238, 1916866575, 3, 0, NULL, 4, 1, 1);
INSERT INTO `sessionseat` VALUES (239, 1916866575, 3, 0, NULL, 4, 2, 1);
INSERT INTO `sessionseat` VALUES (240, 1916866575, 3, 0, NULL, 4, 3, 1);
INSERT INTO `sessionseat` VALUES (241, 1916866575, 3, 0, NULL, 4, 4, 1);
INSERT INTO `sessionseat` VALUES (242, 1916866575, 3, 0, NULL, 4, 5, 1);
INSERT INTO `sessionseat` VALUES (243, 1916866575, 3, 0, NULL, 4, 6, 1);
INSERT INTO `sessionseat` VALUES (244, 1916866575, 3, 0, NULL, 4, 7, 1);
INSERT INTO `sessionseat` VALUES (245, 1916866575, 3, 0, NULL, 4, 8, 1);
INSERT INTO `sessionseat` VALUES (246, 1916866575, 3, 0, NULL, 4, 9, 1);
INSERT INTO `sessionseat` VALUES (247, 1916866575, 3, 0, NULL, 4, 10, 1);
INSERT INTO `sessionseat` VALUES (248, 1916866575, 3, 0, NULL, 5, 1, 1);
INSERT INTO `sessionseat` VALUES (249, 1916866575, 3, 0, NULL, 5, 2, 1);
INSERT INTO `sessionseat` VALUES (250, 1916866575, 3, 0, NULL, 5, 3, 1);
INSERT INTO `sessionseat` VALUES (251, 1916866575, 3, 0, NULL, 5, 4, 1);
INSERT INTO `sessionseat` VALUES (252, 1916866575, 3, 0, NULL, 5, 5, 1);
INSERT INTO `sessionseat` VALUES (253, 1916866575, 3, 0, NULL, 5, 6, 1);
INSERT INTO `sessionseat` VALUES (254, 1916866575, 3, 0, NULL, 5, 7, 1);
INSERT INTO `sessionseat` VALUES (255, 1916866575, 3, 0, NULL, 5, 8, 1);
INSERT INTO `sessionseat` VALUES (256, 1916866575, 3, 0, NULL, 5, 9, 1);
INSERT INTO `sessionseat` VALUES (257, 1916866575, 3, 0, NULL, 5, 10, 1);

-- ----------------------------
-- Table structure for ticket
-- ----------------------------
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket`  (
  `tid` int NOT NULL,
  `mid` int NULL DEFAULT NULL,
  `uid` int NULL DEFAULT NULL,
  `cid` int NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `ordertime` datetime NULL DEFAULT NULL,
  `outtime` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `sid` int NULL DEFAULT NULL,
  `sessionseats` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `sessionseatids` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `tmoney` double NULL DEFAULT NULL,
  `tcontext` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ticket
-- ----------------------------
INSERT INTO `ticket` VALUES (1, 2, 2, 2, 1, '2022-06-21 11:25:49', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `ticket` VALUES (2, 3, 2, 3, 2, '2022-06-21 09:23:13', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `ticket` VALUES (775962626, 4, 838889473, 2, 1, '2022-06-25 19:30:50', '13:00', 1916866566, '1排1座,1排2座', '1,2', 63.8, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `tec` int NULL DEFAULT NULL,
  `love` int NULL DEFAULT NULL,
  `cartoon` int NULL DEFAULT NULL,
  `identity` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '加密盐值',
  `wxid` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `wx_username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `old_password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2061365251 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (-1941950462, 'zxd3', 'b7678e8b05c551fbbd054b4a4ac1010e', '125546', '用户zxd31656157403615', 'http://rdwojd7jd.hn-bkt.clouddn.com/74ab719f-3150-42b1-95ab-14633e36ae26.jpg', NULL, NULL, NULL, NULL, 'guest', 'a261bc83e5bd46359ae05e0d4a88249e', NULL, NULL, NULL);
INSERT INTO `user` VALUES (-490733567, 'admin', '43c7ee339f398abcffc84dfc3e4ae153', '18959729360', '', 'http://rdwojd7jd.hn-bkt.clouddn.com/74ab719f-3150-42b1-95ab-14633e36ae26.jpg', NULL, NULL, NULL, NULL, 'guest', '31ac66f03ed74f7bbe32412325f802ce', NULL, NULL, NULL);
INSERT INTO `user` VALUES (-253960191, 'zhangsan3', '935e77a4cd053ec19bd690b13488b385', '13558557575', '张三', 'https://img-blog.csdnimg.cn/9d748965c35f403f92d639b1a8830755.png', NULL, NULL, NULL, NULL, 'guest', 'abc3366b511e44478f73ee5bdbcf1588', NULL, NULL, NULL);
INSERT INTO `user` VALUES (583041026, 'or7Rl5Wef1S23FuE4TUzYXGJpi2E', 'c5b3cfc0e7e64d62c9ccb9aa3c420767', NULL, '微信小程序用户1234', 'http://rdwojd7jd.hn-bkt.clouddn.com/74ab719f-3150-42b1-95ab-14633e36ae26.jpg', NULL, NULL, NULL, NULL, NULL, NULL, 'or7Rl5Wef1S23FuE4TUzYXGJpi2E', NULL, NULL);
INSERT INTO `user` VALUES (838889473, 'zxd', '67e95bf84dcb1d92122ffb4bfcb4c196', '13888888888', 'zxd', 'https://img-blog.csdnimg.cn/9d748965c35f403f92d639b1a8830755.png', NULL, NULL, NULL, NULL, 'guest', 'c6cc6f0d9c25455281b5afda0a8b9ead', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
