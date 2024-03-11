/*
 Navicat Premium Data Transfer

 Source Server         : 本机MySql-EoaBuild
 Source Server Type    : MySQL
 Source Server Version : 50743
 Source Host           : localhost:3306
 Source Schema         : eoa_build

 Target Server Type    : MySQL
 Target Server Version : 50743
 File Encoding         : 65001

 Date: 11/03/2024 17:07:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority`  (
  `dataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '数据编号,权限唯一id',
  `authorityName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `authorityDescription` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限描述',
  `authorityRemark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限备注,用来描述那些角色或人员拥有该权限',
  PRIMARY KEY (`dataId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后端操作权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES (1, '全部权限', '全部后端权限拥有', '默认系统管理员享有该权限');
INSERT INTO `authority` VALUES (2, '表单权限', '表单列表的配置操作', '默认表单管理员享有该权限');
INSERT INTO `authority` VALUES (3, '组织权限', '组织人员的配置操作', '默认表单管理员享有该权限');
INSERT INTO `authority` VALUES (4, '目录权限', '目录文件的配置操作', '默认表单管理员享有该权限');
INSERT INTO `authority` VALUES (5, '角色权限', '角色人员的配置操作', '默认表单管理员享有该权限');
INSERT INTO `authority` VALUES (6, '授权权限', '权限授权的配置操作', '默认表单管理员享有该权限');
INSERT INTO `authority` VALUES (7, '流程权限', '流程审批的配置操作', '默认表单管理员享有该权限');
INSERT INTO `authority` VALUES (8, '监控权限', '流程数据的监控操作', '默认监控管理员享有该权限');
INSERT INTO `authority` VALUES (9, '图表权限', '图表展示的配置操作', '默认图表管理员享有该权限');
INSERT INTO `authority` VALUES (10, '页面权限', '页面菜单的配置操作', '默认页面管理员享有该权限');

-- ----------------------------
-- Table structure for authority_character
-- ----------------------------
DROP TABLE IF EXISTS `authority_character`;
CREATE TABLE `authority_character`  (
  `characterId` bigint(64) UNSIGNED NOT NULL COMMENT '数据编号,角色唯一id',
  `authorityId` bigint(64) UNSIGNED NOT NULL COMMENT '数据编号,权限唯一id',
  PRIMARY KEY (`characterId`, `authorityId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限索引表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authority_character
-- ----------------------------
INSERT INTO `authority_character` VALUES (1, 1);
INSERT INTO `authority_character` VALUES (2, 2);
INSERT INTO `authority_character` VALUES (3, 3);
INSERT INTO `authority_character` VALUES (4, 4);
INSERT INTO `authority_character` VALUES (5, 5);
INSERT INTO `authority_character` VALUES (6, 6);
INSERT INTO `authority_character` VALUES (7, 7);
INSERT INTO `authority_character` VALUES (8, 8);
INSERT INTO `authority_character` VALUES (9, 9);
INSERT INTO `authority_character` VALUES (10, 10);

-- ----------------------------
-- Table structure for authority_human
-- ----------------------------
DROP TABLE IF EXISTS `authority_human`;
CREATE TABLE `authority_human`  (
  `humanId` bigint(64) UNSIGNED NOT NULL COMMENT '数据编号,人员唯一id',
  `authorityId` bigint(64) UNSIGNED NOT NULL COMMENT '数据编号,权限唯一id',
  PRIMARY KEY (`humanId`, `authorityId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '人员权限索引表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authority_human
-- ----------------------------

-- ----------------------------
-- Table structure for character
-- ----------------------------
DROP TABLE IF EXISTS `character`;
CREATE TABLE `character`  (
  `dataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '数据编号,角色唯一id',
  `characterName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `characterDescription` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `createTime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` bigint(64) UNSIGNED NOT NULL COMMENT '创建者id',
  PRIMARY KEY (`dataId`) USING BTREE,
  UNIQUE INDEX `characterName_Unique`(`characterName`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of character
-- ----------------------------
INSERT INTO `character` VALUES (1, '系统管理员', '拥有所有后端的操作权限', '2023-10-27 00:00:00', 1);
INSERT INTO `character` VALUES (2, '表单管理员', '拥有表单字段的操作权限', '2023-10-27 00:00:00', 1);
INSERT INTO `character` VALUES (3, '组织管理员', '拥有组织结构的操作权限', '2023-10-27 00:00:00', 1);
INSERT INTO `character` VALUES (4, '目录管理员', '拥有目录配置的操作权限', '2023-10-27 00:00:00', 1);
INSERT INTO `character` VALUES (5, '角色管理员', '拥有角色配置的操作权限', '2023-10-27 00:00:00', 1);
INSERT INTO `character` VALUES (6, '授权管理员', '拥有权限授予的操作权限', '2023-10-27 00:00:00', 1);
INSERT INTO `character` VALUES (7, '流程管理员', '拥有流程配置的操作权限', '2023-10-27 00:00:00', 1);
INSERT INTO `character` VALUES (8, '监控管理员', '拥有流程监控的操作权限', '2023-10-27 00:00:00', 1);
INSERT INTO `character` VALUES (9, '图表管理员', '拥有图表配置的操作权限', '2023-10-27 00:00:00', 1);
INSERT INTO `character` VALUES (10, '页面管理员', '拥有页面配置的操作权限', '2023-10-27 00:00:00', 1);
INSERT INTO `character` VALUES (11, '采购', '项目预算管理角色', '2024-01-26 13:39:36', 1);
INSERT INTO `character` VALUES (12, '销售', '项目预算管理角色', '2024-01-26 13:40:11', 1);
INSERT INTO `character` VALUES (13, '总经理', '项目预算管理角色', '2024-01-26 13:40:38', 1);
INSERT INTO `character` VALUES (14, '项目经理', '项目预算管理角色', '2024-01-26 13:40:49', 1);
INSERT INTO `character` VALUES (15, '预决算工程师', '项目预算管理角色', '2024-01-26 13:41:03', 1);
INSERT INTO `character` VALUES (16, '分部领导', '项目预算管理角色', '2024-02-05 11:24:45', 1);

-- ----------------------------
-- Table structure for character_human
-- ----------------------------
DROP TABLE IF EXISTS `character_human`;
CREATE TABLE `character_human`  (
  `humanId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '数据编号,人员唯一id',
  `characterId` bigint(64) UNSIGNED NOT NULL COMMENT '数据编号,角色唯一id',
  `grade` int(11) NOT NULL DEFAULT 0 COMMENT '角色作用级别',
  PRIMARY KEY (`humanId`, `characterId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色人员索引表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of character_human
-- ----------------------------
INSERT INTO `character_human` VALUES (1, 1, 0);
INSERT INTO `character_human` VALUES (3, 13, 0);
INSERT INTO `character_human` VALUES (3, 16, 0);
INSERT INTO `character_human` VALUES (5, 15, 0);
INSERT INTO `character_human` VALUES (6, 14, 0);
INSERT INTO `character_human` VALUES (7, 14, 0);
INSERT INTO `character_human` VALUES (8, 11, 0);
INSERT INTO `character_human` VALUES (9, 14, 0);
INSERT INTO `character_human` VALUES (11, 12, 0);
INSERT INTO `character_human` VALUES (12, 11, 0);

-- ----------------------------
-- Table structure for charts_base
-- ----------------------------
DROP TABLE IF EXISTS `charts_base`;
CREATE TABLE `charts_base`  (
  `dataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '路径编号',
  `moduleTypeId` bigint(64) UNSIGNED NOT NULL COMMENT '所属模块',
  `chartName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '列表名称',
  `defaultCondition` json NULL COMMENT '默认查询条件',
  `tableId` bigint(64) UNSIGNED NOT NULL COMMENT '表单编号',
  `shareAuthority` json NULL COMMENT '查看权限',
  `isVirtual` tinyint(4) NULL DEFAULT 0 COMMENT '0否1是虚拟',
  `rows` json NULL COMMENT '数据对应',
  `orders` json NULL COMMENT '排序字段',
  `config` json NULL COMMENT '图表配置',
  `creator` bigint(64) UNSIGNED NOT NULL COMMENT '创建人唯一id',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`dataId`) USING BTREE,
  INDEX `charts_base_moduleTypeId_index`(`moduleTypeId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '展示图表基础' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of charts_base
-- ----------------------------

-- ----------------------------
-- Table structure for content_list
-- ----------------------------
DROP TABLE IF EXISTS `content_list`;
CREATE TABLE `content_list`  (
  `dataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '数据编号,目录唯一id',
  `isDeprecated` tinyint(4) NULL DEFAULT 0 COMMENT '0否1是废弃',
  `contentName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目录名',
  `contentRemark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目录描述',
  `creator` bigint(64) UNSIGNED NOT NULL COMMENT '创建人唯一id',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `defaultEdit` json NULL COMMENT '编辑权限描述',
  `defaultCreate` json NULL COMMENT '创建权限描述',
  `defaultDelete` json NULL COMMENT '删除权限描述',
  `defaultShare` json NULL COMMENT '共享权限描述',
  `leadContent` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '数据编号,所在目录唯一id',
  `defaultView` json NULL,
  PRIMARY KEY (`dataId`) USING BTREE,
  INDEX `leadContent_FOREIGN`(`leadContent`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '目录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of content_list
-- ----------------------------
INSERT INTO `content_list` VALUES (1, 0, '组织架构展示资料', '系统默认目录,勿动', 1, '2024-01-24 13:33:10', NULL, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": null, \"bodyType\": \"allConstraint\", \"tableType\": null}', NULL, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": null, \"bodyType\": \"allConstraint\", \"tableType\": null}', 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": null, \"bodyType\": \"allConstraint\", \"tableType\": null}');
INSERT INTO `content_list` VALUES (2, 0, '人员头像', '系统默认目录,勿动', 1, '2024-01-24 13:33:10', NULL, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": null, \"bodyType\": \"allConstraint\", \"tableType\": null}', NULL, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": null, \"bodyType\": \"allConstraint\", \"tableType\": null}', 1, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": null}');
INSERT INTO `content_list` VALUES (3, 0, '部门头像', '系统默认目录,勿动', 1, '2024-01-24 13:33:10', NULL, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": null, \"bodyType\": \"allConstraint\", \"tableType\": null}', NULL, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": null, \"bodyType\": \"allConstraint\", \"tableType\": null}', 1, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": null, \"bodyType\": \"allConstraint\", \"tableType\": null}');
INSERT INTO `content_list` VALUES (4, 0, '分部头像', '系统默认目录,勿动', 1, '2024-01-24 13:33:10', NULL, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": null, \"bodyType\": \"allConstraint\", \"tableType\": null}', NULL, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": null, \"bodyType\": \"allConstraint\", \"tableType\": null}', 1, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": null, \"bodyType\": \"allConstraint\", \"tableType\": null}');
INSERT INTO `content_list` VALUES (5, 0, '合同管理', '项目组织预算目录,勿动', 1, '2024-01-26 16:11:23', NULL, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":100,\\\"end\\\":0}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', NULL, '{\"body\": {\"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":true,\\\"leaderRecursion\\\":true,\\\"depart\\\":false,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\", \"characterConstraint\": \"[{\\\"characterId\\\":13,\\\"grade\\\":2}]\"}, \"table\": {}, \"bodyType\": \"createConstraint,characterConstraint,\", \"tableType\": \"\"}', 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}');
INSERT INTO `content_list` VALUES (6, 0, '发票管理', '项目组织预算目录,勿动', 1, '2024-01-26 16:11:37', NULL, NULL, NULL, '{\"body\": {\"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":true,\\\"leaderRecursion\\\":true,\\\"depart\\\":false,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\"}, \"table\": {}, \"bodyType\": \"createConstraint,\", \"tableType\": \"\"}', 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}');
INSERT INTO `content_list` VALUES (7, 0, '销售合同', '项目组织预算目录,勿动', 1, '2024-01-26 16:50:53', NULL, NULL, NULL, '{\"body\": {\"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":true,\\\"leaderRecursion\\\":true,\\\"depart\\\":false,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\", \"characterConstraint\": \"[{\\\"characterId\\\":13,\\\"grade\\\":0},{\\\"characterId\\\":12,\\\"grade\\\":0}]\"}, \"table\": {}, \"bodyType\": \"createConstraint,characterConstraint,\", \"tableType\": \"\"}', 5, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\", \"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":true,\\\"leaderRecursion\\\":true,\\\"depart\\\":false,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\", \"characterConstraint\": \"[]\"}, \"table\": {}, \"bodyType\": \"allConstraint,createConstraint,characterConstraint,\", \"tableType\": \"\"}');
INSERT INTO `content_list` VALUES (8, 0, '采购合同', '项目组织预算目录,勿动', 1, '2024-01-26 16:51:04', NULL, NULL, NULL, '{\"body\": {\"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":true,\\\"leaderRecursion\\\":true,\\\"depart\\\":false,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\", \"characterConstraint\": \"[{\\\"characterId\\\":11,\\\"grade\\\":0},{\\\"characterId\\\":13,\\\"grade\\\":0}]\"}, \"table\": {}, \"bodyType\": \"createConstraint,characterConstraint,\", \"tableType\": \"\"}', 5, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}');
INSERT INTO `content_list` VALUES (9, 0, '项目组织名录', '项目组织预算目录,勿动', 1, '2024-02-04 14:20:45', NULL, NULL, NULL, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}');

-- ----------------------------
-- Table structure for depart_resource
-- ----------------------------
DROP TABLE IF EXISTS `depart_resource`;
CREATE TABLE `depart_resource`  (
  `dataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '数据编号,部门唯一id',
  `departName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名称',
  `departCode` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门编号',
  `fullName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门全称',
  `belongDepart` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '上级部门',
  `belongSection` bigint(64) UNSIGNED NOT NULL COMMENT '上级分部',
  `departManager` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '部门负责人',
  `departIntroduction` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门介绍',
  `createTime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `photo` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '照片文件编号',
  `isDeprecated` tinyint(4) NULL DEFAULT 0 COMMENT '0否1是废弃',
  PRIMARY KEY (`dataId`) USING BTREE,
  UNIQUE INDEX `departCode_Unique`(`departCode`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of depart_resource
-- ----------------------------
INSERT INTO `depart_resource` VALUES (1, '总经理室', 'SERVE-MANAGER', '总经理室', NULL, 1, NULL, NULL, '2024-01-26 10:54:12', NULL, 0);
INSERT INTO `depart_resource` VALUES (2, '法务部', 'SERVE-LAW', '法务部', NULL, 1, NULL, NULL, '2024-01-26 10:55:03', NULL, 0);
INSERT INTO `depart_resource` VALUES (3, '预决算部', 'SERVE-BUDGET', '预决算部', NULL, 1, NULL, NULL, '2024-01-26 11:09:22', NULL, 0);
INSERT INTO `depart_resource` VALUES (4, '项目部', 'ENERGY-PROGECT', '项目部', NULL, 2, NULL, NULL, '2024-01-26 11:18:36', NULL, 0);
INSERT INTO `depart_resource` VALUES (5, '质量部', 'ENERGY-QUALITY', '质量部', NULL, 2, NULL, NULL, '2024-01-26 11:20:52', NULL, 0);
INSERT INTO `depart_resource` VALUES (6, '项目部', 'EQUIPMENT-PROJECT', '项目部', NULL, 3, NULL, NULL, '2024-01-26 11:26:39', NULL, 0);
INSERT INTO `depart_resource` VALUES (7, '质量部', 'EQUIPMENT-QUALITY', '质量部', NULL, 3, NULL, NULL, '2024-01-26 11:27:47', NULL, 0);
INSERT INTO `depart_resource` VALUES (8, '研发部', 'EQUIPMENT/RESEARCH-DEVELOP', '研发部', NULL, 4, NULL, NULL, '2024-01-26 13:16:05', NULL, 0);
INSERT INTO `depart_resource` VALUES (9, '销售部', 'FINICAL-SALE', '销售部', NULL, 5, NULL, NULL, '2024-01-26 13:27:43', NULL, 0);
INSERT INTO `depart_resource` VALUES (10, '采购部', 'FINICAL-PURCHASE', '采购部', NULL, 5, NULL, NULL, '2024-01-26 13:28:07', NULL, 0);
INSERT INTO `depart_resource` VALUES (11, '商务部', 'OlD-BUDGET', '外贸商务部', NULL, 6, NULL, NULL, '2024-02-19 10:09:38', NULL, 1);

-- ----------------------------
-- Table structure for file_storage
-- ----------------------------
DROP TABLE IF EXISTS `file_storage`;
CREATE TABLE `file_storage`  (
  `dataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '数据编号,文件唯一id',
  `isDeprecated` tinyint(4) NULL DEFAULT 0 COMMENT '0否1是废弃',
  `fileName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `fileRoute` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件路径',
  `creator` bigint(64) UNSIGNED NOT NULL COMMENT '创建人唯一id',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `editAuthority` json NULL COMMENT '编辑权限描述',
  `viewAuthority` json NULL COMMENT '查看权限描述',
  `deleteAuthority` json NULL COMMENT '删除权限描述',
  `leadContent` bigint(64) UNSIGNED NOT NULL COMMENT '数据编号,所在目录唯一id,null',
  PRIMARY KEY (`dataId`) USING BTREE,
  INDEX `leadContent_FOREIGN`(`leadContent`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件存储' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file_storage
-- ----------------------------

-- ----------------------------
-- Table structure for form_table_1
-- ----------------------------
DROP TABLE IF EXISTS `form_table_1`;
CREATE TABLE `form_table_1`  (
  `dataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `requestId` bigint(64) NULL DEFAULT NULL COMMENT '流程编号',
  `requestStatus` int(11) NULL DEFAULT NULL COMMENT '流程状态',
  `creator` bigint(64) NULL DEFAULT NULL COMMENT '创建者',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `lastEditTime` datetime NULL DEFAULT NULL COMMENT '最后编辑时间',
  `editAuthority` json NULL COMMENT '编辑权限',
  `viewAuthority` json NULL COMMENT '查看权限',
  `deleteAuthority` json NULL COMMENT '删除权限',
  `xsry` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '销售人员',
  `xssj` datetime NULL DEFAULT NULL COMMENT '销售时间',
  `xsbm` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '销售部门',
  `xsfb` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '销售分部',
  `htbh` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同编号',
  `htmc` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同名称',
  `yxqdsj` datetime NULL DEFAULT NULL COMMENT '意向确定时间',
  `yjqssj` datetime NULL DEFAULT NULL COMMENT '预计签署时间',
  `xdfgsmc` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '相对方公司名称',
  `xdfgsyhzh` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '相对方公司银行账号',
  `xdfgssh` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '相对方公司税号',
  `xdfgsdz` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '相对方公司地址',
  `xdfgslxr` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '相对方公司联系人',
  `xdfgslxfs` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '相对方公司联系方式',
  `xsje` decimal(38, 2) NULL DEFAULT NULL COMMENT '销售金额',
  `xsbz` int(11) NULL DEFAULT NULL COMMENT '销售币种',
  `hl` decimal(38, 2) NULL DEFAULT NULL COMMENT '汇率',
  `sl` decimal(38, 2) NULL DEFAULT NULL COMMENT '税率',
  `hjrmbje` decimal(38, 2) NULL DEFAULT NULL COMMENT '合计人民币金额',
  `htfj` varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同附件',
  PRIMARY KEY (`dataId`) USING BTREE,
  UNIQUE INDEX `dataId_UNIQUE`(`dataId`) USING BTREE,
  UNIQUE INDEX `requestId_UNIQUE`(`requestId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '销售合同审批表单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_table_1
-- ----------------------------
INSERT INTO `form_table_1` VALUES (1, 2, NULL, 11, '2024-02-18 10:15:23', '2024-02-18 17:09:33', '{\"body\": {\"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":16,\\\"grade\\\":1}]}\"}, \"table\": {}, \"bodyType\": \"characterConstraint,\", \"tableType\": \"\"}', '{\"body\": {\"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":true,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\", \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":1,\\\"grade\\\":0},{\\\"characterId\\\":13,\\\"grade\\\":0},{\\\"characterId\\\":12,\\\"grade\\\":2},{\\\"characterId\\\":15,\\\"grade\\\":2}]}\"}, \"table\": {}, \"bodyType\": \"createConstraint,characterConstraint,\", \"tableType\": \"\"}', NULL, 11, '2024-02-17 18:11:34', 9, 5, 'TEST001', '销售合同1', '2024-02-06 18:12:13', '2024-02-25 18:12:21', '相对方公司一', '相对方公司一银行账号', '相对方公司一税号', '相对方公司一地址', '相对方公司一联系人', '相对方公司一联系方式', 1000000.00, 0, 1.00, 0.25, 1250000.00, NULL);
INSERT INTO `form_table_1` VALUES (2, 11, NULL, 11, '2024-03-08 11:52:36', '2024-03-08 16:06:42', '{\"body\": {\"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":16,\\\"grade\\\":1}]}\"}, \"table\": {}, \"bodyType\": \"characterConstraint,\", \"tableType\": \"\"}', '{\"body\": {\"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":true,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\", \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":1,\\\"grade\\\":0},{\\\"characterId\\\":13,\\\"grade\\\":0},{\\\"characterId\\\":12,\\\"grade\\\":2},{\\\"characterId\\\":15,\\\"grade\\\":2}]}\"}, \"table\": {}, \"bodyType\": \"createConstraint,characterConstraint,\", \"tableType\": \"\"}', NULL, 11, '2024-03-07 19:40:07', 9, 1, 'TEST002', '测试销售合同002', '2024-03-05 19:42:40', '2024-03-12 19:42:44', '相对方公司二', '相对方公司二银行账户', '相对方公司二税号', '相对方公司二地址', '相对方公司二联系人', '相对方公司二联系方式', 100000.00, 2, 7.18, 15.00, 825700.00, NULL);

-- ----------------------------
-- Table structure for form_table_1_dt_1
-- ----------------------------
DROP TABLE IF EXISTS `form_table_1_dt_1`;
CREATE TABLE `form_table_1_dt_1`  (
  `detailDataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '明细主键',
  `detailMainId` bigint(64) NOT NULL COMMENT '主表编号',
  `fkxmc` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款项名称',
  `fktj` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款条件',
  `yjfksj` datetime NULL DEFAULT NULL COMMENT '预计付款时间',
  `fkje` decimal(38, 2) NULL DEFAULT NULL COMMENT '付款金额',
  PRIMARY KEY (`detailDataId`) USING BTREE,
  UNIQUE INDEX `detailDataId_UNIQUE`(`detailDataId`) USING BTREE,
  INDEX `detailMainId_UNIQUE`(`detailMainId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '销售合同付款条件明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_table_1_dt_1
-- ----------------------------
INSERT INTO `form_table_1_dt_1` VALUES (10, 1, '预付款', '合同签订30日内', '2024-02-26 23:35:06', 250000.00);
INSERT INTO `form_table_1_dt_1` VALUES (11, 1, '验收款', '项目交付30日内', '2024-03-28 23:36:00', 750000.00);
INSERT INTO `form_table_1_dt_1` VALUES (12, 1, '维护款', '项目维护满一年30日内', '2025-03-30 10:15:03', 250000.00);
INSERT INTO `form_table_1_dt_1` VALUES (13, 2, '预付款', '合同签订30日内', '2024-03-29 19:51:02', 400000.00);
INSERT INTO `form_table_1_dt_1` VALUES (14, 2, '验收款', '项目完成30日内', '2025-03-28 19:51:46', 400000.00);
INSERT INTO `form_table_1_dt_1` VALUES (15, 2, '维护款', '项目维护一年30日内', '2026-03-29 19:52:15', 225700.00);

-- ----------------------------
-- Table structure for form_table_2
-- ----------------------------
DROP TABLE IF EXISTS `form_table_2`;
CREATE TABLE `form_table_2`  (
  `dataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `requestId` bigint(64) NULL DEFAULT NULL COMMENT '流程号',
  `requestStatus` int(11) NULL DEFAULT NULL COMMENT '流程状态',
  `creator` bigint(64) NULL DEFAULT NULL COMMENT '创建者',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `lastEditTime` datetime NULL DEFAULT NULL COMMENT '最后编辑时间',
  `editAuthority` json NULL COMMENT '编辑权限',
  `viewAuthority` json NULL COMMENT '查看',
  `deleteAuthority` json NULL COMMENT '删除',
  `xmbh` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目编号',
  `xmmc` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `xmlxsj` datetime NULL DEFAULT NULL COMMENT '项目立项时间',
  `xmyjjssj` datetime NULL DEFAULT NULL COMMENT '项目预计结束日期',
  `xmsjjssj` datetime NULL DEFAULT NULL COMMENT '项目实际结束日期',
  `xmhst` decimal(38, 2) NULL DEFAULT NULL COMMENT '项目耗时',
  `htbh` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '合同编号',
  `xmjl` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '项目经理',
  `zxfb` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '执行分部',
  `xsje` decimal(38, 2) NULL DEFAULT NULL COMMENT '销售金额',
  `ysje` decimal(38, 2) NULL DEFAULT NULL COMMENT '预算金额',
  `sjzc` decimal(38, 2) NULL DEFAULT NULL COMMENT '实际支出',
  `bz` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `sfyl` int(11) NULL DEFAULT NULL COMMENT '是否盈利',
  `sfcz` int(11) NULL DEFAULT NULL COMMENT '是否超支',
  `yglr` decimal(38, 2) NULL DEFAULT NULL COMMENT '预估利润\r\n',
  `sjlr` decimal(38, 2) NULL DEFAULT NULL COMMENT '实际利润',
  `zzmlfj` varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织名录附件',
  PRIMARY KEY (`dataId`) USING BTREE,
  UNIQUE INDEX `dataId_UNIQUE`(`dataId`) USING BTREE,
  UNIQUE INDEX `requestId_UNIQUE`(`requestId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目预算审批表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_table_2
-- ----------------------------
INSERT INTO `form_table_2` VALUES (1, 3, NULL, 5, '2024-02-18 17:37:03', '2024-02-21 16:30:07', '{\"body\": {\"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":true,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\"}, \"table\": {}, \"bodyType\": \"createConstraint,\", \"tableType\": \"\"}', '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\", \"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":false,\\\"section\\\":true,\\\"sectionRecursive\\\":false}\", \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":1,\\\"grade\\\":0}]}\"}, \"table\": {\"proposedConstraint\": \"{\\\"humans\\\":[],\\\"departs\\\":[33],\\\"sections\\\":[]}\"}, \"bodyType\": \"createConstraint,characterConstraint,allConstraint,\", \"tableType\": \"proposedConstraint,\"}', NULL, 'ENERGY001', '能源项目1', '2024-02-17 17:27:04', '2024-03-26 17:27:09', NULL, NULL, 1, 6, 3, 1250000.00, 100000.00, NULL, NULL, 1, 1, 340000.00, NULL, NULL);
INSERT INTO `form_table_2` VALUES (2, 12, NULL, 5, '2024-03-08 16:54:38', '2024-03-08 17:04:05', '{\"body\": {\"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":true,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\"}, \"table\": {}, \"bodyType\": \"createConstraint,\", \"tableType\": \"\"}', '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\", \"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":false,\\\"section\\\":true,\\\"sectionRecursive\\\":false}\", \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":1,\\\"grade\\\":0}]}\"}, \"table\": {\"proposedConstraint\": \"{\\\"humans\\\":[],\\\"departs\\\":[33],\\\"sections\\\":[]}\"}, \"bodyType\": \"createConstraint,characterConstraint,allConstraint,\", \"tableType\": \"proposedConstraint,\"}', NULL, 'EQUIPMENT001', '设备项目001', '2024-03-08 08:49:12', '2024-03-31 08:49:14', NULL, NULL, 2, 9, 3, 100000.00, 21000.00, 100.00, NULL, 1, 1, 79000.00, 99900.00, NULL);

-- ----------------------------
-- Table structure for form_table_2_dt_1
-- ----------------------------
DROP TABLE IF EXISTS `form_table_2_dt_1`;
CREATE TABLE `form_table_2_dt_1`  (
  `detailDataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT,
  `detailMainId` bigint(64) NOT NULL,
  `ysmc` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预算名称',
  `jdje` decimal(38, 2) NULL DEFAULT NULL COMMENT '既定金额',
  `yyje` decimal(38, 2) NULL DEFAULT NULL COMMENT '使用金额',
  `sfcz` int(11) NULL DEFAULT NULL COMMENT '是否超支',
  PRIMARY KEY (`detailDataId`) USING BTREE,
  UNIQUE INDEX `detailDataId_UNIQUE`(`detailDataId`) USING BTREE,
  INDEX `detailMainId_UNIQUE`(`detailMainId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '预算细项明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_table_2_dt_1
-- ----------------------------
INSERT INTO `form_table_2_dt_1` VALUES (1, 1, '管理费', 12000.00, 101.00, 1);
INSERT INTO `form_table_2_dt_1` VALUES (2, 1, '运输费', 80000.00, 0.00, 1);
INSERT INTO `form_table_2_dt_1` VALUES (3, 1, '设备费', 10000.00, 93.00, 1);
INSERT INTO `form_table_2_dt_1` VALUES (4, 2, '管理费', 1000.00, 100.00, 1);
INSERT INTO `form_table_2_dt_1` VALUES (5, 2, '运输费', 10000.00, 0.00, 1);
INSERT INTO `form_table_2_dt_1` VALUES (6, 2, '制造费', 10000.00, 0.00, 1);

-- ----------------------------
-- Table structure for form_table_3
-- ----------------------------
DROP TABLE IF EXISTS `form_table_3`;
CREATE TABLE `form_table_3`  (
  `dataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT,
  `requestId` bigint(64) NULL DEFAULT NULL,
  `requestStatus` int(11) NULL DEFAULT NULL,
  `creator` bigint(64) NULL DEFAULT NULL,
  `createTime` datetime NULL DEFAULT NULL,
  `lastEditTime` datetime NULL DEFAULT NULL,
  `editAuthority` json NULL,
  `viewAuthority` json NULL,
  `deleteAuthority` json NULL,
  `cgr` bigint(64) UNSIGNED NOT NULL COMMENT '采购人',
  `cgsj` datetime NULL DEFAULT NULL COMMENT '采购时间',
  `cgbh` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号',
  `cgmc` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `htfj` varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件',
  `bz` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `xmmc` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '项目名称',
  `xmjl` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '项目经理',
  `cgje` decimal(38, 2) NULL DEFAULT NULL COMMENT '采购金额',
  `cgbz` int(11) NULL DEFAULT NULL COMMENT '采购币种',
  `sl` decimal(38, 2) NULL DEFAULT NULL COMMENT '税率',
  `hl` decimal(38, 2) NULL DEFAULT NULL COMMENT '汇率',
  `fkje` decimal(38, 2) NULL DEFAULT NULL COMMENT '付款金额',
  `fpqk` int(11) NULL DEFAULT NULL COMMENT '分配情况',
  PRIMARY KEY (`dataId`) USING BTREE,
  UNIQUE INDEX `dataId_UNIQUE`(`dataId`) USING BTREE,
  UNIQUE INDEX `requestId_UNIQUE`(`requestId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '采购审批单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_table_3
-- ----------------------------
INSERT INTO `form_table_3` VALUES (1, 4, NULL, 12, '2024-02-21 14:40:36', '2024-02-22 11:41:20', NULL, '{\"body\": {\"createConstraint\": \"{\\\"self\\\":false,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":false,\\\"section\\\":true,\\\"sectionRecursive\\\":false}\", \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":1,\\\"grade\\\":0},{\\\"characterId\\\":11,\\\"grade\\\":2}]}\"}, \"table\": {}, \"bodyType\": \"createConstraint,characterConstraint,\", \"tableType\": \"\"}', NULL, 12, '2024-02-20 06:33:06', 'by', '购买测试', NULL, NULL, 1, 6, 100.00, 0, 0.12, 1.00, 112.00, 1);
INSERT INTO `form_table_3` VALUES (2, 13, NULL, 8, '2024-03-08 17:12:35', '2024-03-08 17:29:45', NULL, '{\"body\": {\"createConstraint\": \"{\\\"self\\\":false,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":false,\\\"section\\\":true,\\\"sectionRecursive\\\":false}\", \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":1,\\\"grade\\\":0},{\\\"characterId\\\":11,\\\"grade\\\":2}]}\"}, \"table\": {}, \"bodyType\": \"createConstraint,characterConstraint,\", \"tableType\": \"\"}', NULL, 8, '2024-03-06 09:06:42', 'EQUIP00101', '设备采购1', NULL, NULL, 2, 9, 100.00, NULL, 1.00, 1.00, 100.00, 1);

-- ----------------------------
-- Table structure for form_table_3_dt_1
-- ----------------------------
DROP TABLE IF EXISTS `form_table_3_dt_1`;
CREATE TABLE `form_table_3_dt_1`  (
  `detailDataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT,
  `detailMainId` bigint(64) NOT NULL,
  `jfx` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交付项',
  `jftj` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交付条件',
  `jfsj` datetime NULL DEFAULT NULL COMMENT '交付时间',
  `jfnr` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交付内容',
  `dyfk` decimal(38, 2) NULL DEFAULT NULL COMMENT '对应付款',
  PRIMARY KEY (`detailDataId`) USING BTREE,
  UNIQUE INDEX `detailDataId_UNIQUE`(`detailDataId`) USING BTREE,
  INDEX `detailMainId_UNIQUE`(`detailMainId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '交付明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_table_3_dt_1
-- ----------------------------
INSERT INTO `form_table_3_dt_1` VALUES (3, 1, '全部', '合同签订30日内', '2024-02-28 14:36:57', '购买测试', 112.00);
INSERT INTO `form_table_3_dt_1` VALUES (4, 2, '全额交付', '付款30日内', '2024-03-29 09:12:25', '1', 100.00);

-- ----------------------------
-- Table structure for form_table_3_dt_2
-- ----------------------------
DROP TABLE IF EXISTS `form_table_3_dt_2`;
CREATE TABLE `form_table_3_dt_2`  (
  `detailDataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT,
  `detailMainId` bigint(64) NOT NULL,
  `ysx` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '预算项',
  `jdje` decimal(38, 2) NULL DEFAULT NULL COMMENT '既定金额',
  `yyje` decimal(38, 2) NULL DEFAULT NULL COMMENT '已用金额',
  `bcje` decimal(38, 2) NULL DEFAULT NULL COMMENT '本次金额',
  PRIMARY KEY (`detailDataId`) USING BTREE,
  UNIQUE INDEX `detailDataId_UNIQUE`(`detailDataId`) USING BTREE,
  INDEX `detailMainId_UNIQUE`(`detailMainId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '采购预算分配明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_table_3_dt_2
-- ----------------------------
INSERT INTO `form_table_3_dt_2` VALUES (1, 1, 3, 10000.00, 0.00, 112.00);
INSERT INTO `form_table_3_dt_2` VALUES (2, 2, 4, 1000.00, 100.00, 100.00);

-- ----------------------------
-- Table structure for form_table_4
-- ----------------------------
DROP TABLE IF EXISTS `form_table_4`;
CREATE TABLE `form_table_4`  (
  `dataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT,
  `requestId` bigint(64) NULL DEFAULT NULL,
  `requestStatus` int(11) NULL DEFAULT NULL,
  `creator` bigint(64) NULL DEFAULT NULL,
  `createTime` datetime NULL DEFAULT NULL,
  `lastEditTime` datetime NULL DEFAULT NULL,
  `editAuthority` json NULL,
  `viewAuthority` json NULL,
  `deleteAuthority` json NULL,
  `bxr` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '报销人',
  `bxsj` datetime NULL DEFAULT NULL COMMENT '报销时间',
  `xmmc` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '项目名称',
  `xmjl` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '项目经理',
  `bxje` decimal(38, 2) NULL DEFAULT NULL COMMENT '报销金额',
  `bxsx` varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报销事项',
  `fpqk` int(11) NULL DEFAULT NULL COMMENT '分配情况',
  PRIMARY KEY (`dataId`) USING BTREE,
  UNIQUE INDEX `dataId_UNIQUE`(`dataId`) USING BTREE,
  UNIQUE INDEX `requestId_UNIQUE`(`requestId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '报销审批单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_table_4
-- ----------------------------
INSERT INTO `form_table_4` VALUES (1, 5, NULL, 6, '2024-02-22 10:23:33', '2024-02-22 11:41:17', NULL, '{\"body\": {\"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":true,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\", \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":1,\\\"grade\\\":0}]}\"}, \"table\": {}, \"bodyType\": \"createConstraint,characterConstraint,\", \"tableType\": \"\"}', NULL, 6, '2024-02-21 18:22:19', 1, 6, 100.00, '报销测试', 1);
INSERT INTO `form_table_4` VALUES (2, 10, NULL, 6, '2024-02-22 13:39:56', '2024-02-22 14:30:10', NULL, '{\"body\": {\"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":true,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\", \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":1,\\\"grade\\\":0}]}\"}, \"table\": {}, \"bodyType\": \"createConstraint,characterConstraint,\", \"tableType\": \"\"}', NULL, 6, '2024-02-21 21:39:21', 1, 6, 1.00, '测试报销2', 1);

-- ----------------------------
-- Table structure for form_table_4_dt_1
-- ----------------------------
DROP TABLE IF EXISTS `form_table_4_dt_1`;
CREATE TABLE `form_table_4_dt_1`  (
  `detailDataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT,
  `detailMainId` bigint(64) NOT NULL,
  `bxnr` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报销内容',
  `mxje` decimal(38, 2) NULL DEFAULT NULL COMMENT '明细金额',
  `mxfp` varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '明细发票',
  PRIMARY KEY (`detailDataId`) USING BTREE,
  UNIQUE INDEX `detailDataId_UNIQUE`(`detailDataId`) USING BTREE,
  INDEX `detailMainId_UNIQUE`(`detailMainId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '报销明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_table_4_dt_1
-- ----------------------------
INSERT INTO `form_table_4_dt_1` VALUES (1, 1, '报销事项1', 100.00, NULL);
INSERT INTO `form_table_4_dt_1` VALUES (2, 2, '测试2', 1.00, NULL);

-- ----------------------------
-- Table structure for form_table_4_dt_2
-- ----------------------------
DROP TABLE IF EXISTS `form_table_4_dt_2`;
CREATE TABLE `form_table_4_dt_2`  (
  `detailDataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT,
  `detailMainId` bigint(64) NOT NULL,
  `ysx` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '预算项',
  `jdje` decimal(38, 2) NULL DEFAULT NULL COMMENT '既定金额',
  `yyje` decimal(38, 2) NULL DEFAULT NULL COMMENT '已用金额',
  `bcje` decimal(38, 2) NULL DEFAULT NULL COMMENT '本次金额',
  PRIMARY KEY (`detailDataId`) USING BTREE,
  UNIQUE INDEX `detailDataId_UNIQUE`(`detailDataId`) USING BTREE,
  INDEX `detailMainId_UNIQUE`(`detailMainId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '报销分配预算明细' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_table_4_dt_2
-- ----------------------------
INSERT INTO `form_table_4_dt_2` VALUES (1, 1, 1, 10000.00, 0.00, 100.00);
INSERT INTO `form_table_4_dt_2` VALUES (3, 2, 1, 12000.00, 100.00, 1.00);

-- ----------------------------
-- Table structure for form_table_5
-- ----------------------------
DROP TABLE IF EXISTS `form_table_5`;
CREATE TABLE `form_table_5`  (
  `dataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT,
  `requestId` bigint(64) NULL DEFAULT NULL,
  `requestStatus` int(11) NULL DEFAULT NULL,
  `creator` bigint(64) NULL DEFAULT NULL,
  `createTime` datetime NULL DEFAULT NULL,
  `lastEditTime` datetime NULL DEFAULT NULL,
  `editAuthority` json NULL,
  `viewAuthority` json NULL,
  `deleteAuthority` json NULL,
  `xmmc` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '项目名称',
  `xmjl` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '项目经理',
  `tzsj` datetime NULL DEFAULT NULL COMMENT '调整时间',
  `tzyy` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '调整原因',
  `tzqk` int(11) NULL DEFAULT NULL COMMENT '调整情况',
  PRIMARY KEY (`dataId`) USING BTREE,
  UNIQUE INDEX `dataId_UNIQUE`(`dataId`) USING BTREE,
  UNIQUE INDEX `requestId_UNIQUE`(`requestId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '既定预算调整审批' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_table_5
-- ----------------------------
INSERT INTO `form_table_5` VALUES (1, 7, NULL, 6, '2024-02-22 10:34:31', '2024-02-22 11:41:14', NULL, '{\"body\": {\"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":false,\\\"section\\\":true,\\\"sectionRecursive\\\":false}\", \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":1,\\\"grade\\\":0},{\\\"characterId\\\":15,\\\"grade\\\":2}]}\"}, \"table\": {}, \"bodyType\": \"createConstraint,characterConstraint,\", \"tableType\": \"\"}', NULL, 1, 6, '2024-02-22 02:27:57', '既定预算调整测试', 1);

-- ----------------------------
-- Table structure for form_table_5_dt_1
-- ----------------------------
DROP TABLE IF EXISTS `form_table_5_dt_1`;
CREATE TABLE `form_table_5_dt_1`  (
  `detailDataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT,
  `detailMainId` bigint(64) NOT NULL,
  `ysx` bigint(64) UNSIGNED NOT NULL COMMENT '预算项',
  `yjdje` decimal(38, 2) NULL DEFAULT NULL COMMENT '原既定金额',
  `xjdje` decimal(38, 2) NULL DEFAULT NULL COMMENT '新既定金额',
  PRIMARY KEY (`detailDataId`) USING BTREE,
  UNIQUE INDEX `detailDataId_UNIQUE`(`detailDataId`) USING BTREE,
  INDEX `detailMainId_UNIQUE`(`detailMainId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '既定预算调整明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_table_5_dt_1
-- ----------------------------
INSERT INTO `form_table_5_dt_1` VALUES (1, 1, 1, 10000.00, 12000.00);

-- ----------------------------
-- Table structure for form_table_6
-- ----------------------------
DROP TABLE IF EXISTS `form_table_6`;
CREATE TABLE `form_table_6`  (
  `dataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT,
  `requestId` bigint(64) NULL DEFAULT NULL,
  `requestStatus` int(11) NULL DEFAULT NULL,
  `creator` bigint(64) NULL DEFAULT NULL,
  `createTime` datetime NULL DEFAULT NULL,
  `lastEditTime` datetime NULL DEFAULT NULL,
  `editAuthority` json NULL,
  `viewAuthority` json NULL,
  `deleteAuthority` json NULL,
  `xmmc` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '项目名称',
  `xmjl` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '项目经理',
  `tzsj` datetime NULL DEFAULT NULL COMMENT '调整时间',
  `tzyy` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '调整原因',
  `tzqk` int(11) NULL DEFAULT NULL COMMENT '调整情况',
  PRIMARY KEY (`dataId`) USING BTREE,
  UNIQUE INDEX `dataId_UNIQUE`(`dataId`) USING BTREE,
  UNIQUE INDEX `requestId_UNIQUE`(`requestId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '使用预算调整审批' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_table_6
-- ----------------------------
INSERT INTO `form_table_6` VALUES (2, 9, NULL, 6, '2024-02-22 10:59:56', '2024-02-22 11:41:10', NULL, '{\"body\": {\"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":false,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\", \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":1,\\\"grade\\\":0},{\\\"characterId\\\":15,\\\"grade\\\":2}]}\"}, \"table\": {}, \"bodyType\": \"createConstraint,characterConstraint,\", \"tableType\": \"\"}', NULL, 1, 6, '2024-02-22 02:58:24', '返回采购1降价', 1);

-- ----------------------------
-- Table structure for form_table_6_dt_1
-- ----------------------------
DROP TABLE IF EXISTS `form_table_6_dt_1`;
CREATE TABLE `form_table_6_dt_1`  (
  `detailDataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT,
  `detailMainId` bigint(64) NOT NULL,
  `ysx` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '预算项',
  `jdje` decimal(38, 2) NULL DEFAULT NULL COMMENT '既定金额',
  `yyje` decimal(38, 2) NULL DEFAULT NULL COMMENT '已用金额',
  `tzje` decimal(38, 2) NULL DEFAULT NULL COMMENT '调整金额',
  PRIMARY KEY (`detailDataId`) USING BTREE,
  UNIQUE INDEX `detailDataId_UNIQUE`(`detailDataId`) USING BTREE,
  INDEX `detailMainId_UNIQUE`(`detailMainId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '使用预算调整明细' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_table_6_dt_1
-- ----------------------------
INSERT INTO `form_table_6_dt_1` VALUES (1, 2, 3, 10000.00, 112.00, -19.00);

-- ----------------------------
-- Table structure for human_resource
-- ----------------------------
DROP TABLE IF EXISTS `human_resource`;
CREATE TABLE `human_resource`  (
  `dataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '数据编号,人员唯一id',
  `loginName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `sex` int(11) NULL DEFAULT NULL COMMENT '性别,0男1女',
  `birth` datetime NULL DEFAULT NULL COMMENT '出生年月',
  `telephone` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `mail` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '固话',
  `fax` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '传真',
  `workCode` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工号',
  `section` bigint(64) UNSIGNED NOT NULL COMMENT '分部编号',
  `depart` bigint(64) UNSIGNED NOT NULL COMMENT '部门编号',
  `job` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位',
  `directorLeader` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '直属领导',
  `supporter` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '助理',
  `photo` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '头像文件编号',
  `signature` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个性签名',
  `lastLogin` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `safety` int(11) NULL DEFAULT 0 COMMENT '安全等级',
  `isDeprecated` tinyint(4) NULL DEFAULT 0 COMMENT '0否1是废弃',
  PRIMARY KEY (`dataId`) USING BTREE,
  UNIQUE INDEX `loginName_Unique`(`loginName`) USING BTREE,
  INDEX `section_FOREIGN`(`section`) USING BTREE,
  INDEX `depart_FOREIGN`(`depart`) USING BTREE,
  INDEX `directorLeader_FOREIGN`(`directorLeader`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '人员索引表,构建对应虚拟视图' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of human_resource
-- ----------------------------
INSERT INTO `human_resource` VALUES (1, 'sysadmin', 'eoa', '系统管理员', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, '系统管理员', 0, 0, 0, NULL, '2024-03-08 17:59:39', 0, 0);
INSERT INTO `human_resource` VALUES (2, 'tourist', 'eoa', '游客用户', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, '游客', 1, 0, 0, NULL, NULL, 0, 0);
INSERT INTO `human_resource` VALUES (3, 'ywy', 'eoa', '杨文元', 0, NULL, NULL, NULL, NULL, NULL, 'W001', 1, 1, '总经理', 0, 0, 0, NULL, '2024-03-08 16:58:00', 0, 0);
INSERT INTO `human_resource` VALUES (4, 'wzc', 'eoa', '王晨志', 0, NULL, NULL, NULL, NULL, NULL, 'W002', 2, 1, '法务', 3, 0, 0, NULL, NULL, 0, 0);
INSERT INTO `human_resource` VALUES (5, 'ly', 'eoa', '李妍', 1, NULL, NULL, NULL, NULL, NULL, 'W003', 3, 1, '预决算工程师', 3, 0, 0, NULL, '2024-03-08 16:54:37', 0, 0);
INSERT INTO `human_resource` VALUES (6, 'xp', 'eoa', '徐平', 0, NULL, NULL, NULL, NULL, NULL, 'W004', 2, 4, '项目人员', 3, 0, 0, NULL, '2024-02-22 13:39:05', 0, 0);
INSERT INTO `human_resource` VALUES (7, 'xj', 'eoa', '赵静', 1, NULL, NULL, NULL, NULL, NULL, 'W005', 2, 5, '项目经理', 3, 0, 0, NULL, NULL, 0, 0);
INSERT INTO `human_resource` VALUES (8, 'lcg', 'eoa', '刘长庚', 0, NULL, NULL, NULL, NULL, NULL, 'W006', 3, 6, '项目人员', 3, 0, 0, NULL, '2024-03-08 17:12:34', 0, 0);
INSERT INTO `human_resource` VALUES (9, 'fl', 'eoa', '付蕾', 1, NULL, NULL, NULL, NULL, NULL, 'W007', 3, 6, '项目经理', 3, 0, 0, NULL, '2024-03-08 17:29:24', 0, 0);
INSERT INTO `human_resource` VALUES (10, 'ml', 'eoa', '孟玲', 1, NULL, NULL, NULL, NULL, NULL, 'W008', 4, 7, '研发人员', 3, 0, 0, NULL, NULL, 0, 0);
INSERT INTO `human_resource` VALUES (11, 'lch', 'eoa', '李长昊', 0, NULL, NULL, NULL, NULL, NULL, 'W009', 5, 9, '销售', 3, 0, 0, NULL, '2024-03-08 15:41:18', 0, 0);
INSERT INTO `human_resource` VALUES (12, 'fzc', 'eoa', '范成志', 0, NULL, NULL, NULL, NULL, NULL, 'W010', 5, 10, '采购', 3, 0, 0, NULL, '2024-02-21 14:40:36', 0, 0);

-- ----------------------------
-- Table structure for login_config
-- ----------------------------
DROP TABLE IF EXISTS `login_config`;
CREATE TABLE `login_config`  (
  `dataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '登录设置编号',
  `backgroundUrl` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '背景文件路径',
  `logoUrl` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图表文件路径',
  `backgroundVideoUrl` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '背景视频路径',
  `loginTitle` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录主标题',
  `loginSubTitle` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录副标题',
  `activeMainTitle` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动信息主标题',
  `activeIntroduction` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动信息副标题',
  `contactManager` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员联系方式',
  `linkUrl` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动信息跳转链接',
  `linkStr` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动信息跳转文字',
  `creator` bigint(64) UNSIGNED NOT NULL COMMENT '创建人唯一id',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `onUse` tinyint(4) NULL DEFAULT 0 COMMENT '0否1是启用',
  PRIMARY KEY (`dataId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录页面配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login_config
-- ----------------------------
INSERT INTO `login_config` VALUES (1, 'https://mdn.alipayobjects.com/huamei_gcee1x/afts/img/A*y0ZTS6WLwvgAAAAAAAAAAAAADml6AQ/fmt.webp', 'https://github.githubassets.com/images/modules/logos_page/Octocat.png', 'https://gw.alipayobjects.com/v/huamei_gcee1x/afts/video/jXRBRK_VAwoAAAAAAAAAAAAAK4eUAQBr', 'EOA', '可配置的低代码办公平台', '项目预算特化开发版本', '作者相关', '张骏山:13671985248', 'http://127.0.0.1:8080/doc.html', '查看', 1, '2024-01-24 13:33:10', 1);

-- ----------------------------
-- Table structure for menu_base
-- ----------------------------
DROP TABLE IF EXISTS `menu_base`;
CREATE TABLE `menu_base`  (
  `dataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '菜单编号',
  `contentName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `belongContent` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '菜单编号',
  `contentUrl` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单链接',
  `viewNo` int(11) NULL DEFAULT NULL COMMENT '显示顺序',
  `isDeprecated` tinyint(4) NULL DEFAULT 0 COMMENT '0否1是废弃',
  `shareAuthority` json NULL COMMENT '共享权限',
  `creator` bigint(64) UNSIGNED NOT NULL COMMENT '创建人唯一id',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`dataId`) USING BTREE,
  INDEX `menu_base_belong_content_index`(`belongContent`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '页面菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu_base
-- ----------------------------
INSERT INTO `menu_base` VALUES (1, '主页', NULL, NULL, 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-01-24 13:33:10');
INSERT INTO `menu_base` VALUES (2, '组织结构', NULL, NULL, 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-01-24 13:33:10');
INSERT INTO `menu_base` VALUES (3, '知识目录', NULL, NULL, 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-01-24 13:33:10');
INSERT INTO `menu_base` VALUES (4, '工作流程', NULL, NULL, 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-01-24 13:33:10');
INSERT INTO `menu_base` VALUES (5, '公司业务', 1, '/main', 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-01-24 13:33:10');
INSERT INTO `menu_base` VALUES (6, '组织结构树', 2, '/organization', 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-01-24 13:33:10');
INSERT INTO `menu_base` VALUES (7, '分部列表', 2, '/section', 2, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-01-24 13:33:10');
INSERT INTO `menu_base` VALUES (8, '部门列表', 2, '/depart', 3, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-01-24 13:33:10');
INSERT INTO `menu_base` VALUES (9, '人员列表', 2, '/human_resource', 4, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-01-24 13:33:10');
INSERT INTO `menu_base` VALUES (10, '知识地图', 3, '/content/0', 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-01-24 13:33:10');
INSERT INTO `menu_base` VALUES (11, '待办请求', 4, '/request/backlog', 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-01-24 13:33:10');
INSERT INTO `menu_base` VALUES (12, '已办请求', 4, '/request/done', 2, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-01-24 13:33:10');
INSERT INTO `menu_base` VALUES (13, '我的请求', 4, '/request/self', 3, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-01-24 13:33:10');
INSERT INTO `menu_base` VALUES (14, '发起请求', 4, '/workflow', 4, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-01-24 13:33:10');
INSERT INTO `menu_base` VALUES (15, '项目预算', NULL, NULL, 2, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-02-06 14:21:25');
INSERT INTO `menu_base` VALUES (16, '项目信息', 15, '', 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-02-06 14:30:44');
INSERT INTO `menu_base` VALUES (17, '分配信息', 15, NULL, 3, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-02-06 14:37:40');
INSERT INTO `menu_base` VALUES (18, '调整信息', 15, NULL, 4, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-02-06 14:55:08');
INSERT INTO `menu_base` VALUES (19, '采购金额分配记录', 17, '/search/2', 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-02-06 15:00:43');
INSERT INTO `menu_base` VALUES (20, '报销金额分配记录', 17, '/search/3', 2, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-02-06 15:01:38');
INSERT INTO `menu_base` VALUES (21, '既定预算调整记录', 18, '/search/5', 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-02-06 15:31:09');
INSERT INTO `menu_base` VALUES (22, '预算使用统计', 28, '/search/7', 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-02-06 15:51:07');
INSERT INTO `menu_base` VALUES (23, '销售合同台账', 16, '/search/4', 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-02-06 15:54:08');
INSERT INTO `menu_base` VALUES (24, '使用预算调整记录', 18, '/search/6', 2, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-02-06 15:56:09');
INSERT INTO `menu_base` VALUES (25, '业务流程', 1, '/flow', 2, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-02-06 15:57:02');
INSERT INTO `menu_base` VALUES (26, '组织预算', 16, '/search/1', 2, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-02-06 16:35:02');
INSERT INTO `menu_base` VALUES (27, '预算使用明细', 28, '/search/8', 2, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-02-07 17:37:29');
INSERT INTO `menu_base` VALUES (28, '预算信息', 15, '', 2, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-02-07 17:39:46');
INSERT INTO `menu_base` VALUES (29, '预算角色', 1, '/character', 3, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-03-07 13:32:26');

-- ----------------------------
-- Table structure for module_type
-- ----------------------------
DROP TABLE IF EXISTS `module_type`;
CREATE TABLE `module_type`  (
  `moduleTypeId` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '模块类型编号',
  `moduleTypeName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块类型名称',
  `workflowRemark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块类型备注',
  `creator` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '创建人',
  `createTime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`moduleTypeId`) USING BTREE,
  UNIQUE INDEX `moduleTypeName_UNIQUE`(`moduleTypeName`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '模块表单 __module_type__' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of module_type
-- ----------------------------
INSERT INTO `module_type` VALUES (1, '组织架构', '系统默认应用-组织架构', 1, '2024-01-24 13:33:10');
INSERT INTO `module_type` VALUES (2, '目录文档', '系统默认应用-目录文档', 1, '2024-01-24 13:33:10');
INSERT INTO `module_type` VALUES (3, '页面菜单', '系统默认应用-页面菜单', 1, '2024-01-24 13:33:10');
INSERT INTO `module_type` VALUES (4, '流程批阅', '系统默认应用-流程批阅', 1, '2024-01-24 13:33:10');
INSERT INTO `module_type` VALUES (5, '汇总展示', '系统默认应用-汇总展示', 1, '2024-01-24 13:33:10');
INSERT INTO `module_type` VALUES (6, '项目预算', '项目预算管理应用', 1, '2024-01-26 15:55:28');

-- ----------------------------
-- Table structure for page_config
-- ----------------------------
DROP TABLE IF EXISTS `page_config`;
CREATE TABLE `page_config`  (
  `dataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '页面设置编号',
  `companyName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司名称',
  `headerColor` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '头部导航栏颜色',
  `sideColor` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '侧导航栏颜色',
  `onUse` tinyint(4) NULL DEFAULT 0 COMMENT '0否1是启用',
  `creator` bigint(64) UNSIGNED NOT NULL COMMENT '创建人唯一id',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`dataId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登陆后页面设置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of page_config
-- ----------------------------
INSERT INTO `page_config` VALUES (1, '业务流办公平台-项目预算', '#140066', '#8A8DAD', 1, 1, '2024-01-24 13:33:10');
INSERT INTO `page_config` VALUES (2, '标题', '#ff0000', '#ff0000', 0, 1, '2024-01-29 17:47:36');

-- ----------------------------
-- Table structure for request
-- ----------------------------
DROP TABLE IF EXISTS `request`;
CREATE TABLE `request`  (
  `requestId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '流程数据编号',
  `dataId` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '表单数据编号',
  `workflowId` bigint(64) UNSIGNED NOT NULL COMMENT '所属流程编号',
  `currentNode` bigint(64) UNSIGNED NOT NULL COMMENT '当前节点编号',
  `doneHistory` json NULL COMMENT '操作历史',
  `requestTitle` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
  `requestStatus` int(11) NULL DEFAULT NULL COMMENT '流程状态',
  `flowHistory` json NULL COMMENT '流转历史',
  `submitTime` datetime NULL DEFAULT NULL COMMENT '发起事件',
  `finishTime` datetime NULL DEFAULT NULL COMMENT '归档事件',
  `creator` bigint(64) UNSIGNED NOT NULL COMMENT '创建者编号',
  PRIMARY KEY (`requestId`) USING BTREE,
  INDEX `request_workflowId_index`(`workflowId`) USING BTREE,
  INDEX `requestStatus`(`requestStatus`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '审批数据' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of request
-- ----------------------------
INSERT INTO `request` VALUES (2, 1, 1, 6, '[{\"time\": 1708222522525, \"nodeId\": 1, \"comment\": \"创建\", \"humanId\": 11, \"operation\": 0, \"workflowNodeName\": \"创建\"}, {\"time\": 1708241283555, \"nodeId\": 2, \"comment\": \"批准\", \"humanId\": 3, \"operation\": 2, \"workflowNodeName\": \"上级审批\"}, {\"time\": 1708244543566, \"nodeId\": 3, \"comment\": \"批准\", \"humanId\": 3, \"operation\": 2, \"workflowNodeName\": \"分部领导审批\"}, {\"time\": 1708247373673, \"nodeId\": 5, \"comment\": \"提交\", \"humanId\": 5, \"operation\": 1, \"workflowNodeName\": \"通知预决算部\"}]', '销售合同审批-TEST001', 3, '[{\"id\": 1, \"date\": 1708222522531, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 1, \"date\": 1708222522543, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 2, \"date\": 1708222522551, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}, {\"id\": 2, \"date\": 1708241283557, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 2, \"date\": 1708241283560, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 3, \"date\": 1708241283565, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}, {\"id\": 3, \"date\": 1708244543571, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 4, \"date\": 1708244543605, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 5, \"date\": 1708244543614, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}, {\"id\": 5, \"date\": 1708247373673, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 6, \"date\": 1708247373675, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 6, \"date\": 1708247373676, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}]', '2024-02-18 10:15:23', '2024-02-18 17:09:34', 11);
INSERT INTO `request` VALUES (3, 1, 2, 10, '[{\"time\": 1708249023066, \"nodeId\": 7, \"comment\": \"创建\", \"humanId\": 5, \"operation\": 0, \"workflowNodeName\": \"建立组织\"}, {\"time\": 1708326787322, \"nodeId\": 8, \"comment\": \"批准\", \"humanId\": 3, \"operation\": 2, \"workflowNodeName\": \"领导审批\"}, {\"time\": 1708327849294, \"nodeId\": 9, \"comment\": \"提交\", \"humanId\": 6, \"operation\": 1, \"workflowNodeName\": \"通知项目经理\"}, {\"time\": 1708329015457, \"nodeId\": 10, \"comment\": \"test\", \"humanId\": 1, \"operation\": 4, \"workflowNodeName\": \"完成归档\"}]', '项目预算编制审批-ENERGY001', 3, '[{\"id\": 7, \"date\": 1708249023067, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 7, \"date\": 1708249023068, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 8, \"date\": 1708249023070, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}, {\"id\": 8, \"date\": 1708326787328, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 8, \"date\": 1708326787333, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 9, \"date\": 1708326787345, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}, {\"id\": 9, \"date\": 1708327849295, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 9, \"date\": 1708327849299, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 10, \"date\": 1708327849302, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}, {\"id\": 10, \"date\": 1708329015463, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 10, \"date\": 1708329015477, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}]', '2024-02-18 17:37:03', '2024-02-19 15:50:15', 5);
INSERT INTO `request` VALUES (4, 1, 3, 14, '[{\"time\": 1708497636190, \"nodeId\": 11, \"comment\": \"创建\", \"humanId\": 12, \"operation\": 0, \"workflowNodeName\": \"采购发起\"}, {\"time\": 1708507100629, \"nodeId\": 12, \"comment\": \"批准\", \"humanId\": 6, \"operation\": 2, \"workflowNodeName\": \"项目经理分配预算\"}, {\"time\": 1708573280311, \"nodeId\": 13, \"comment\": \"批准\", \"humanId\": 5, \"operation\": 2, \"workflowNodeName\": \"预决算部超支审批\"}]', '采购审批', 3, '[{\"id\": 11, \"date\": 1708497636193, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 18, \"date\": 1708497636205, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 12, \"date\": 1708497636209, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}, {\"id\": 12, \"date\": 1708507100641, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 19, \"date\": 1708507100645, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 13, \"date\": 1708507100649, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}, {\"id\": 13, \"date\": 1708573280311, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 20, \"date\": 1708573280316, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 14, \"date\": 1708573280317, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}]', '2024-02-21 14:40:36', '2024-02-22 11:41:20', 12);
INSERT INTO `request` VALUES (5, 1, 4, 18, '[{\"time\": 1708568613271, \"nodeId\": 15, \"comment\": \"创建\", \"humanId\": 6, \"operation\": 0, \"workflowNodeName\": \"业务人员发起\"}, {\"time\": 1708568645812, \"nodeId\": 16, \"comment\": \"批准\", \"humanId\": 6, \"operation\": 2, \"workflowNodeName\": \"项目经理分配预算\"}, {\"time\": 1708573277476, \"nodeId\": 17, \"comment\": \"批准\", \"humanId\": 5, \"operation\": 2, \"workflowNodeName\": \"预算部超支审查\"}]', '报销审批报销测试', 3, '[{\"id\": 15, \"date\": 1708568613273, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 10, \"date\": 1708568613285, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 16, \"date\": 1708568613289, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}, {\"id\": 16, \"date\": 1708568645819, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 11, \"date\": 1708568645853, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 17, \"date\": 1708568645856, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}, {\"id\": 17, \"date\": 1708573277476, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 13, \"date\": 1708573277480, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 18, \"date\": 1708573277482, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}]', '2024-02-22 10:23:33', '2024-02-22 11:41:17', 6);
INSERT INTO `request` VALUES (6, NULL, 5, 19, NULL, NULL, 0, NULL, NULL, NULL, 6);
INSERT INTO `request` VALUES (7, 1, 5, 21, '[{\"time\": 1708569270710, \"nodeId\": 19, \"comment\": \"创建\", \"humanId\": 6, \"operation\": 0, \"workflowNodeName\": \"项目经理发起\"}, {\"time\": 1708573274660, \"nodeId\": 20, \"comment\": \"批准\", \"humanId\": 5, \"operation\": 2, \"workflowNodeName\": \"预决算部审批\"}]', '既定预算调整申请既定预算调整测试', 3, '[{\"id\": 19, \"date\": 1708569270711, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 14, \"date\": 1708569270712, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 20, \"date\": 1708569270713, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}, {\"id\": 20, \"date\": 1708573274665, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 15, \"date\": 1708573274667, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 21, \"date\": 1708573274669, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}]', '2024-02-22 10:34:31', '2024-02-22 11:41:15', 6);
INSERT INTO `request` VALUES (8, NULL, 6, 22, NULL, NULL, 0, NULL, NULL, NULL, 6);
INSERT INTO `request` VALUES (9, 2, 6, 24, '[{\"time\": 1708570796481, \"nodeId\": 22, \"comment\": \"采购降价,望批准\", \"humanId\": 6, \"operation\": 0, \"workflowNodeName\": \"项目经理发起\"}, {\"time\": 1708573270120, \"nodeId\": 23, \"comment\": \"批准\", \"humanId\": 5, \"operation\": 2, \"workflowNodeName\": \"预决算部审批\"}]', '使用预算调整申请返回采购1降价', 3, '[{\"id\": 22, \"date\": 1708570796482, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 16, \"date\": 1708570796486, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 23, \"date\": 1708570796488, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}, {\"id\": 23, \"date\": 1708573270129, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 17, \"date\": 1708573270132, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 24, \"date\": 1708573270133, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}]', '2024-02-22 10:59:56', '2024-02-22 11:41:10', 6);
INSERT INTO `request` VALUES (10, 2, 4, 18, '[{\"time\": 1708580395911, \"nodeId\": 15, \"comment\": \"创建\", \"humanId\": 6, \"operation\": 0, \"workflowNodeName\": \"业务人员发起\"}, {\"time\": 1708580438331, \"nodeId\": 16, \"comment\": \"批准\", \"humanId\": 6, \"operation\": 2, \"workflowNodeName\": \"项目经理分配预算\"}, {\"time\": 1708583410101, \"nodeId\": 17, \"comment\": \"批准\", \"humanId\": 5, \"operation\": 2, \"workflowNodeName\": \"预算部超支审查\"}]', '报销审批测试报销2', 3, '[{\"id\": 15, \"date\": 1708580395913, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 10, \"date\": 1708580395917, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 16, \"date\": 1708580395920, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}, {\"id\": 16, \"date\": 1708580438336, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 11, \"date\": 1708580438338, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 17, \"date\": 1708580438340, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}, {\"id\": 17, \"date\": 1708583410102, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 13, \"date\": 1708583410107, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 18, \"date\": 1708583410111, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}]', '2024-02-22 13:39:56', '2024-02-22 14:30:10', 6);
INSERT INTO `request` VALUES (11, 2, 1, 6, '[{\"time\": 1709883697864, \"nodeId\": 1, \"comment\": \"请求审批\", \"humanId\": 11, \"operation\": 0, \"workflowNodeName\": \"创建\"}, {\"time\": 1709884721494, \"nodeId\": 2, \"comment\": \"ok\", \"humanId\": 3, \"operation\": 2, \"workflowNodeName\": \"上级审批\"}, {\"time\": 1709884730576, \"nodeId\": 3, \"comment\": \"批准\", \"humanId\": 3, \"operation\": 2, \"workflowNodeName\": \"分部领导审批\"}, {\"time\": 1709884738580, \"nodeId\": 4, \"comment\": \"批准\", \"humanId\": 3, \"operation\": 2, \"workflowNodeName\": \"公司领导审批\"}, {\"time\": 1709885202600, \"nodeId\": 5, \"comment\": \"提交\", \"humanId\": 5, \"operation\": 1, \"workflowNodeName\": \"通知预决算部\"}]', '销售合同审批-TEST002', 3, '[{\"id\": 1, \"date\": 1709883697865, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 1, \"date\": 1709883697874, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 2, \"date\": 1709883697881, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}, {\"id\": 2, \"date\": 1709884721495, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 2, \"date\": 1709884721499, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 3, \"date\": 1709884721506, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}, {\"id\": 3, \"date\": 1709884730576, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 3, \"date\": 1709884730609, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 4, \"date\": 1709884730613, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}, {\"id\": 4, \"date\": 1709884738581, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 5, \"date\": 1709884738585, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 5, \"date\": 1709884738590, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}, {\"id\": 5, \"date\": 1709885202601, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 6, \"date\": 1709885202605, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 6, \"date\": 1709885202608, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}]', '2024-03-08 15:41:38', '2024-03-08 16:06:43', 11);
INSERT INTO `request` VALUES (12, 2, 2, 10, '[{\"time\": 1709888077953, \"nodeId\": 7, \"comment\": \"创建\", \"humanId\": 5, \"operation\": 0, \"workflowNodeName\": \"建立组织\"}, {\"time\": 1709888295390, \"nodeId\": 8, \"comment\": \"批准\", \"humanId\": 3, \"operation\": 2, \"workflowNodeName\": \"领导审批\"}, {\"time\": 1709888645719, \"nodeId\": 9, \"comment\": \"已知晓\", \"humanId\": 9, \"operation\": 1, \"workflowNodeName\": \"通知项目经理\"}]', '项目预算编制审批-EQUIPMENT001', 3, '[{\"id\": 7, \"date\": 1709888077960, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 7, \"date\": 1709888077968, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 8, \"date\": 1709888077972, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}, {\"id\": 8, \"date\": 1709888295390, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 8, \"date\": 1709888295394, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 9, \"date\": 1709888295400, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}, {\"id\": 9, \"date\": 1709888645719, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 9, \"date\": 1709888645724, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 10, \"date\": 1709888645727, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}]', '2024-03-08 16:54:38', '2024-03-08 17:04:06', 5);
INSERT INTO `request` VALUES (13, 2, 3, 14, '[{\"time\": 1709889154546, \"nodeId\": 11, \"comment\": \"创建\", \"humanId\": 8, \"operation\": 0, \"workflowNodeName\": \"采购发起\"}, {\"time\": 1709889258307, \"nodeId\": 12, \"comment\": \"批准\", \"humanId\": 9, \"operation\": 2, \"workflowNodeName\": \"项目经理分配预算\"}, {\"time\": 1709889843536, \"nodeId\": 13, \"comment\": \"强制流传\", \"humanId\": 1, \"operation\": 4, \"workflowNodeName\": \"预决算部超支审批\"}, {\"time\": 1709889865440, \"nodeId\": 12, \"comment\": \"批准\", \"humanId\": 1, \"operation\": 2, \"workflowNodeName\": \"项目经理分配预算\"}, {\"time\": 1709889895170, \"nodeId\": 13, \"comment\": \"强制流传\", \"humanId\": 1, \"operation\": 4, \"workflowNodeName\": \"预决算部超支审批\"}, {\"time\": 1709890185220, \"nodeId\": 12, \"comment\": \"批准\", \"humanId\": 1, \"operation\": 2, \"workflowNodeName\": \"项目经理分配预算\"}]', '采购审批EQUIP00101', 3, '[{\"id\": 11, \"date\": 1709889154552, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 18, \"date\": 1709889154559, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 12, \"date\": 1709889154566, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}, {\"id\": 12, \"date\": 1709889258316, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 19, \"date\": 1709889258324, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 13, \"date\": 1709889258334, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}, {\"id\": 13, \"date\": 1709889843537, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 12, \"date\": 1709889849495, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}, {\"id\": 12, \"date\": 1709889865446, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 19, \"date\": 1709889865452, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 13, \"date\": 1709889878222, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}, {\"id\": 13, \"date\": 1709889895171, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 12, \"date\": 1709889895176, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}, {\"id\": 12, \"date\": 1709890185222, \"action\": \"离开节点\", \"object\": \"WorkflowNode\"}, {\"id\": 21, \"date\": 1709890314029, \"action\": \"经过路径\", \"object\": \"WorkflowRoute\"}, {\"id\": 14, \"date\": 1709890319202, \"action\": \"到达节点\", \"object\": \"WorkflowNode\"}]', '2024-03-08 17:12:35', '2024-03-08 17:31:59', 8);

-- ----------------------------
-- Table structure for request_backlog
-- ----------------------------
DROP TABLE IF EXISTS `request_backlog`;
CREATE TABLE `request_backlog`  (
  `humanId` bigint(64) UNSIGNED NOT NULL COMMENT '人员编号',
  `requestId` bigint(64) UNSIGNED NOT NULL COMMENT '请求编号',
  `nodeId` bigint(64) UNSIGNED NOT NULL COMMENT '到达请求节点',
  `arriveTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '到达时间',
  `workflowId` bigint(64) UNSIGNED NOT NULL COMMENT '所属流程编号',
  PRIMARY KEY (`humanId`, `requestId`) USING BTREE,
  INDEX `request_backlog_workflowId_index`(`workflowId`) USING BTREE,
  INDEX `request_backlog_arriveTime_index`(`arriveTime`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '待办列表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of request_backlog
-- ----------------------------
INSERT INTO `request_backlog` VALUES (5, 12, 10, '2024-03-08 17:04:05', 2);
INSERT INTO `request_backlog` VALUES (6, 6, 19, '2024-02-22 10:32:09', 5);
INSERT INTO `request_backlog` VALUES (6, 8, 22, '2024-02-22 10:58:50', 6);
INSERT INTO `request_backlog` VALUES (8, 13, 14, '2024-03-08 17:31:59', 3);
INSERT INTO `request_backlog` VALUES (11, 11, 6, '2024-03-08 16:06:42', 1);

-- ----------------------------
-- Table structure for request_done
-- ----------------------------
DROP TABLE IF EXISTS `request_done`;
CREATE TABLE `request_done`  (
  `humanId` bigint(64) UNSIGNED NOT NULL COMMENT '人员编号',
  `requestId` bigint(64) UNSIGNED NOT NULL COMMENT '请求编号',
  `nodeId` bigint(64) UNSIGNED NOT NULL COMMENT '完成请求节点',
  `doneTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '完成时间',
  `workflowId` bigint(64) UNSIGNED NOT NULL COMMENT '所属流程编号',
  PRIMARY KEY (`humanId`, `requestId`) USING BTREE,
  INDEX `request_done_workflowId_index`(`workflowId`) USING BTREE,
  INDEX `request_done_doneTime_index`(`doneTime`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '已办列表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of request_done
-- ----------------------------
INSERT INTO `request_done` VALUES (1, 13, 12, '2024-03-08 17:29:45', 3);
INSERT INTO `request_done` VALUES (3, 2, 3, '2024-02-18 16:22:23', 1);
INSERT INTO `request_done` VALUES (3, 3, 8, '2024-02-19 15:13:07', 2);
INSERT INTO `request_done` VALUES (3, 11, 4, '2024-03-08 15:58:58', 1);
INSERT INTO `request_done` VALUES (3, 12, 8, '2024-03-08 16:58:15', 2);
INSERT INTO `request_done` VALUES (5, 2, 6, '2024-02-18 17:39:03', 1);
INSERT INTO `request_done` VALUES (5, 3, 10, '2024-02-22 11:41:53', 2);
INSERT INTO `request_done` VALUES (5, 4, 14, '2024-02-22 11:41:32', 3);
INSERT INTO `request_done` VALUES (5, 5, 18, '2024-02-22 11:41:43', 4);
INSERT INTO `request_done` VALUES (5, 7, 21, '2024-02-22 11:41:47', 5);
INSERT INTO `request_done` VALUES (5, 9, 24, '2024-02-22 11:41:50', 6);
INSERT INTO `request_done` VALUES (5, 10, 18, '2024-02-22 14:30:39', 4);
INSERT INTO `request_done` VALUES (5, 11, 5, '2024-03-08 16:06:42', 1);
INSERT INTO `request_done` VALUES (5, 12, 7, '2024-03-08 16:54:37', 2);
INSERT INTO `request_done` VALUES (6, 3, 10, '2024-02-19 15:31:49', 2);
INSERT INTO `request_done` VALUES (6, 4, 12, '2024-02-21 17:18:20', 3);
INSERT INTO `request_done` VALUES (6, 5, 16, '2024-02-22 10:24:05', 4);
INSERT INTO `request_done` VALUES (6, 7, 19, '2024-02-22 10:34:30', 5);
INSERT INTO `request_done` VALUES (6, 9, 22, '2024-02-22 10:59:56', 6);
INSERT INTO `request_done` VALUES (6, 10, 16, '2024-02-22 13:40:38', 4);
INSERT INTO `request_done` VALUES (8, 13, 11, '2024-03-08 17:12:34', 3);
INSERT INTO `request_done` VALUES (9, 12, 9, '2024-03-08 17:04:05', 2);
INSERT INTO `request_done` VALUES (9, 13, 12, '2024-03-08 17:14:18', 3);
INSERT INTO `request_done` VALUES (11, 2, 1, '2024-02-18 10:15:22', 1);
INSERT INTO `request_done` VALUES (11, 11, 1, '2024-03-08 15:41:37', 1);
INSERT INTO `request_done` VALUES (12, 4, 11, '2024-02-21 14:40:36', 3);

-- ----------------------------
-- Table structure for search_list_base
-- ----------------------------
DROP TABLE IF EXISTS `search_list_base`;
CREATE TABLE `search_list_base`  (
  `dataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '列表编号',
  `moduleTypeId` bigint(64) UNSIGNED NOT NULL COMMENT '所属模块',
  `searchListName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '列表名称',
  `defaultCondition` json NULL COMMENT '默认查询条件',
  `tableId` bigint(64) UNSIGNED NOT NULL COMMENT '表单编号',
  `shareAuthority` json NULL COMMENT '查看权限',
  `orders` json NULL COMMENT '排序字段',
  `isVirtual` tinyint(4) NULL DEFAULT 0 COMMENT '0否1是虚拟',
  `creator` bigint(64) UNSIGNED NOT NULL COMMENT '创建人唯一id',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`dataId`) USING BTREE,
  INDEX `search_list_base_moduleTypeId_index`(`moduleTypeId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '展示列表基础' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of search_list_base
-- ----------------------------
INSERT INTO `search_list_base` VALUES (1, 6, '项目组织台账', NULL, 2, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', NULL, 0, 1, '2024-02-06 09:17:57');
INSERT INTO `search_list_base` VALUES (2, 6, '采购合同分配台账', '{\"fpqk\": \"1\"}', 3, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', '{\"type\": \"desc\", \"column\": \"dataId\"}', 0, 1, '2024-02-06 09:18:40');
INSERT INTO `search_list_base` VALUES (3, 6, '报销账单分配台账', NULL, 4, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', NULL, 0, 1, '2024-02-06 09:19:01');
INSERT INTO `search_list_base` VALUES (4, 6, '销售合同台账', '{}', 1, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', NULL, 0, 1, '2024-02-06 09:19:31');
INSERT INTO `search_list_base` VALUES (5, 6, '既定预算调整台账', NULL, 5, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', NULL, 0, 1, '2024-02-06 09:22:20');
INSERT INTO `search_list_base` VALUES (6, 6, '使用预算调账台账', NULL, 6, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', NULL, 0, 1, '2024-02-06 09:22:33');
INSERT INTO `search_list_base` VALUES (7, 6, '预算统计台账', '{}', 6, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', NULL, 1, 1, '2024-02-06 10:01:34');
INSERT INTO `search_list_base` VALUES (8, 6, '预算使用记录台账', '{}', 7, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', NULL, 1, 1, '2024-02-07 17:22:59');

-- ----------------------------
-- Table structure for search_list_column
-- ----------------------------
DROP TABLE IF EXISTS `search_list_column`;
CREATE TABLE `search_list_column`  (
  `dataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '列表字段编号',
  `searchListId` bigint(64) UNSIGNED NOT NULL COMMENT '列表编号',
  `columnId` bigint(64) UNSIGNED NOT NULL COMMENT '字段编号',
  `isVirtual` tinyint(4) NULL DEFAULT 0 COMMENT '0否1是虚拟',
  `viewNo` int(11) NULL DEFAULT 0 COMMENT '显示顺序',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字段标题',
  PRIMARY KEY (`dataId`) USING BTREE,
  INDEX `search_list_base_viewNo_index`(`viewNo`) USING BTREE,
  INDEX `search_list_base_searchListId_index`(`searchListId`) USING BTREE,
  INDEX `search_list_base_columnId_index`(`columnId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '列表字段配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of search_list_column
-- ----------------------------
INSERT INTO `search_list_column` VALUES (1, 1, 25, 0, 1, '项目编号');
INSERT INTO `search_list_column` VALUES (2, 1, 26, 0, 2, '项目名称');
INSERT INTO `search_list_column` VALUES (3, 1, 31, 0, 3, '关联合同');
INSERT INTO `search_list_column` VALUES (4, 1, 32, 0, 4, '项目经理');
INSERT INTO `search_list_column` VALUES (5, 1, 33, 0, 5, '所属分部');
INSERT INTO `search_list_column` VALUES (6, 2, 49, 0, 1, '采购编号');
INSERT INTO `search_list_column` VALUES (7, 2, 50, 0, 2, '采购名称');
INSERT INTO `search_list_column` VALUES (8, 2, 59, 0, 3, '总金额');
INSERT INTO `search_list_column` VALUES (9, 2, 53, 0, 4, '相关项目');
INSERT INTO `search_list_column` VALUES (10, 2, 54, 0, 5, '项目经理');
INSERT INTO `search_list_column` VALUES (11, 2, 52, 0, 6, '备注');
INSERT INTO `search_list_column` VALUES (12, 3, 69, 0, 1, '报销人');
INSERT INTO `search_list_column` VALUES (13, 3, 73, 0, 2, '报销金额');
INSERT INTO `search_list_column` VALUES (14, 3, 71, 0, 3, '相关项目');
INSERT INTO `search_list_column` VALUES (15, 3, 72, 0, 4, '项目经理');
INSERT INTO `search_list_column` VALUES (16, 3, 74, 0, 5, '备注');
INSERT INTO `search_list_column` VALUES (17, 4, 5, 0, 1, '合同编号');
INSERT INTO `search_list_column` VALUES (18, 4, 6, 0, 2, '合同名称');
INSERT INTO `search_list_column` VALUES (19, 4, 9, 0, 3, '相对方公司');
INSERT INTO `search_list_column` VALUES (20, 4, 19, 0, 4, '合同总金额');
INSERT INTO `search_list_column` VALUES (21, 4, 1, 0, 5, '销售人员');
INSERT INTO `search_list_column` VALUES (22, 5, 82, 0, 1, '调整项目');
INSERT INTO `search_list_column` VALUES (23, 5, 84, 0, 2, '调整时间');
INSERT INTO `search_list_column` VALUES (24, 5, 85, 0, 3, '调整原因');
INSERT INTO `search_list_column` VALUES (25, 6, 89, 0, 1, '调整项目');
INSERT INTO `search_list_column` VALUES (26, 6, 91, 0, 2, '调整时间');
INSERT INTO `search_list_column` VALUES (27, 6, 92, 0, 3, '调整原因');
INSERT INTO `search_list_column` VALUES (28, 7, 8, 1, 1, '预算名称');
INSERT INTO `search_list_column` VALUES (29, 7, 9, 1, 2, '预算金额');
INSERT INTO `search_list_column` VALUES (30, 7, 10, 1, 3, '已使用预算');
INSERT INTO `search_list_column` VALUES (31, 7, 11, 1, 4, '相关项目');
INSERT INTO `search_list_column` VALUES (32, 7, 12, 1, 5, '项目经理');
INSERT INTO `search_list_column` VALUES (33, 7, 13, 1, 6, '相关合同');
INSERT INTO `search_list_column` VALUES (34, 7, 21, 1, 7, '是否超支');
INSERT INTO `search_list_column` VALUES (35, 8, 17, 1, 1, '预算名称');
INSERT INTO `search_list_column` VALUES (36, 8, 18, 1, 2, '使用金额');
INSERT INTO `search_list_column` VALUES (37, 8, 19, 1, 3, '支出类型');
INSERT INTO `search_list_column` VALUES (38, 8, 20, 1, 4, '使用时间');
INSERT INTO `search_list_column` VALUES (39, 8, 15, 1, 5, '相关项目');
INSERT INTO `search_list_column` VALUES (40, 8, 16, 1, 6, '项目经理');
INSERT INTO `search_list_column` VALUES (41, 8, 14, 1, 7, '相关合同');

-- ----------------------------
-- Table structure for section_resource
-- ----------------------------
DROP TABLE IF EXISTS `section_resource`;
CREATE TABLE `section_resource`  (
  `dataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '数据编号,分部唯一id',
  `sectionName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分部名称',
  `sectionCode` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分部编号',
  `fullName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分部全称',
  `belongSection` bigint(64) UNSIGNED NOT NULL COMMENT '上级分部',
  `sectionManager` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '分部负责人',
  `sectionIntroduction` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分部介绍',
  `createTime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `photo` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '照片文件编号',
  `isDeprecated` tinyint(4) NULL DEFAULT 0 COMMENT '0否1是废弃',
  PRIMARY KEY (`dataId`) USING BTREE,
  UNIQUE INDEX `sectionName_Unique`(`sectionName`) USING BTREE,
  UNIQUE INDEX `sectionCode_Unique`(`sectionCode`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '分部表单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of section_resource
-- ----------------------------
INSERT INTO `section_resource` VALUES (1, '支撑服务分部', 'SERVE', '维森化工石油公司支撑服务分部', 0, NULL, NULL, '2024-01-25 17:10:40', NULL, 0);
INSERT INTO `section_resource` VALUES (2, '能源制造公司', 'ENERGY', '维森化工石油-能源制造公司', 0, NULL, NULL, '2024-01-25 17:19:26', NULL, 0);
INSERT INTO `section_resource` VALUES (3, '设备制造分部', 'EQUIPMENT', '维森化工石油-设备制造分部', 0, NULL, NULL, '2024-01-25 17:26:51', NULL, 0);
INSERT INTO `section_resource` VALUES (4, '研发分部', 'EQUIPMENT/RESEARCH', '维森化工石油公司设备制造研发分部', 3, NULL, NULL, '2024-01-26 11:39:41', NULL, 0);
INSERT INTO `section_resource` VALUES (5, '商务分部', 'SERVER/FINICAL', '维森集团商务分部', 1, NULL, NULL, '2024-01-26 13:26:57', NULL, 0);
INSERT INTO `section_resource` VALUES (6, '外贸部门', 'OLD001', '石油外贸部门', 0, NULL, NULL, '2024-02-19 10:08:47', NULL, 1);

-- ----------------------------
-- Table structure for table_column_index
-- ----------------------------
DROP TABLE IF EXISTS `table_column_index`;
CREATE TABLE `table_column_index`  (
  `columnId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '字段id',
  `columnViewName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字段显示名称',
  `columnDataName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字段数据库存储名称',
  `columnType` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字段类型',
  `columnTypeDescription` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附带格式描述',
  `tableNo` bigint(64) UNSIGNED NOT NULL COMMENT '对应存储数据库表',
  `columnGroupNo` int(11) NULL DEFAULT -1 COMMENT '格式分组',
  `columnDetailNo` int(11) NULL DEFAULT -1 COMMENT '明细字段分组',
  `columnViewNo` int(11) NULL DEFAULT NULL COMMENT '顺序序号',
  `creator` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '创建人',
  `createTime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`columnId`) USING BTREE,
  INDEX `tableNo_FOREIGN`(`tableNo`) USING BTREE COMMENT '所属表单索引加速'
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字段数据索引表（table_column_index）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of table_column_index
-- ----------------------------
INSERT INTO `table_column_index` VALUES (1, '销售人员', 'xsry', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":1,\"columnId\":1}', 1, 1, -1, 1, 1, '2024-02-02 17:45:52');
INSERT INTO `table_column_index` VALUES (2, '销售时间', 'xssj', 'DATETIME', NULL, 1, 1, -1, 2, 1, '2024-02-02 17:46:53');
INSERT INTO `table_column_index` VALUES (3, '销售部门', 'xsbm', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":2,\"columnId\":4}', 1, 1, -1, 3, 1, '2024-02-02 17:51:33');
INSERT INTO `table_column_index` VALUES (4, '销售分部', 'xsfb', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":3,\"columnId\":5}', 1, 1, -1, 4, 1, '2024-02-02 18:02:04');
INSERT INTO `table_column_index` VALUES (5, '合同编号', 'htbh', 'SINGLE_TEXT', NULL, 1, 2, -1, 5, 1, '2024-02-04 11:21:30');
INSERT INTO `table_column_index` VALUES (6, '合同名称', 'htmc', 'SINGLE_TEXT', NULL, 1, 2, -1, 6, 1, '2024-02-04 11:21:53');
INSERT INTO `table_column_index` VALUES (7, '意向确定时间', 'yxqdsj', 'DATETIME', NULL, 1, 2, -1, 7, 1, '2024-02-04 11:22:33');
INSERT INTO `table_column_index` VALUES (8, '预计签署时间', 'yjqssj', 'DATETIME', NULL, 1, 2, -1, 8, 1, '2024-02-04 11:25:39');
INSERT INTO `table_column_index` VALUES (9, '相对方公司名称', 'xdfgsmc', 'SINGLE_TEXT', NULL, 1, 3, -1, 9, 1, '2024-02-04 11:30:01');
INSERT INTO `table_column_index` VALUES (10, '相对方公司银行账号', 'xdfgsyhzh', 'SINGLE_TEXT', NULL, 1, 3, -1, 10, 1, '2024-02-04 11:32:17');
INSERT INTO `table_column_index` VALUES (11, '相对方公司税号', 'xdfgssh', 'SINGLE_TEXT', NULL, 1, 3, -1, 11, 1, '2024-02-04 11:34:43');
INSERT INTO `table_column_index` VALUES (12, '相对方公司地址', 'xdfgsdz', 'SINGLE_TEXT', NULL, 1, 3, -1, 12, 1, '2024-02-04 11:37:33');
INSERT INTO `table_column_index` VALUES (13, '相对方公司联系人', 'xdfgslxr', 'SINGLE_TEXT', NULL, 1, 3, -1, 13, 1, '2024-02-04 11:38:06');
INSERT INTO `table_column_index` VALUES (14, '相对方公司联系方式', 'xdfgslxfs', 'SINGLE_TEXT', NULL, 1, 3, -1, 14, 1, '2024-02-04 11:38:47');
INSERT INTO `table_column_index` VALUES (15, '销售金额', 'xsje', 'NUMBER', NULL, 1, 4, -1, 15, 1, '2024-02-04 11:39:14');
INSERT INTO `table_column_index` VALUES (16, '销售币种', 'xsbz', 'SELECT_ITEM', '{\"items\":\"CNY,HKD,USD,EUR,JPY\"}', 1, 4, -1, 16, 1, '2024-02-04 11:40:13');
INSERT INTO `table_column_index` VALUES (17, '汇率', 'hl', 'NUMBER', NULL, 1, 4, -1, 17, 1, '2024-02-04 11:40:30');
INSERT INTO `table_column_index` VALUES (18, '税率', 'sl', 'NUMBER', NULL, 1, 4, -1, 18, 1, '2024-02-04 11:40:46');
INSERT INTO `table_column_index` VALUES (19, '合计人民币金额', 'hjrmbje', 'NUMBER', NULL, 1, 4, -1, 19, 1, '2024-02-04 11:41:20');
INSERT INTO `table_column_index` VALUES (20, '付款项名称', 'fkxmc', 'SINGLE_TEXT', NULL, 1, -1, 1, 20, 1, '2024-02-04 11:42:14');
INSERT INTO `table_column_index` VALUES (21, '付款条件', 'fktj', 'SINGLE_TEXT', NULL, 1, -1, 1, 21, 1, '2024-02-04 13:13:13');
INSERT INTO `table_column_index` VALUES (22, '预计时间', 'yjfksj', 'DATETIME', NULL, 1, -1, 1, 22, 1, '2024-02-04 13:15:14');
INSERT INTO `table_column_index` VALUES (23, '付款金额', 'fkje', 'NUMBER', NULL, 1, -1, 1, 23, 1, '2024-02-04 13:15:33');
INSERT INTO `table_column_index` VALUES (24, '合同附件', 'htfj', 'FILE', '{\"contentId\":7}', 1, 2, -1, 24, 1, '2024-02-04 13:23:14');
INSERT INTO `table_column_index` VALUES (25, '项目编号', 'xmbh', 'SINGLE_TEXT', NULL, 2, 1, -1, 1, 1, '2024-02-04 13:41:22');
INSERT INTO `table_column_index` VALUES (26, '项目名称', 'xmmc', 'SINGLE_TEXT', NULL, 2, 1, -1, 2, 1, '2024-02-04 13:42:05');
INSERT INTO `table_column_index` VALUES (27, '项目立项时间', 'xmlxsj', 'DATETIME', NULL, 2, 1, -1, 3, 1, '2024-02-04 13:49:09');
INSERT INTO `table_column_index` VALUES (28, '项目预计结束时间', 'xmyjjssj', 'DATETIME', NULL, 2, 1, -1, 4, 1, '2024-02-04 13:50:07');
INSERT INTO `table_column_index` VALUES (29, '项目实际结束时间', 'xmsjjssj', 'DATETIME', NULL, 2, 1, -1, 5, 1, '2024-02-04 13:50:37');
INSERT INTO `table_column_index` VALUES (30, '项目耗时(天)', 'xmhst', 'NUMBER', NULL, 2, 1, -1, 6, 1, '2024-02-04 13:51:05');
INSERT INTO `table_column_index` VALUES (31, '合同编号', 'htbh', 'BROWSER_BOX', '{\"isVirtual\":false,\"tableId\":1,\"columnId\":5}', 2, 2, -1, 7, 1, '2024-02-04 13:57:43');
INSERT INTO `table_column_index` VALUES (32, '项目经理', 'xmjl', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":1,\"columnId\":1}', 2, 3, -1, 8, 1, '2024-02-04 14:02:30');
INSERT INTO `table_column_index` VALUES (33, '执行分部', 'zxfb', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":3,\"columnId\":5}', 2, 3, -1, 9, 1, '2024-02-04 14:08:20');
INSERT INTO `table_column_index` VALUES (34, '销售金额', 'xsje', 'NUMBER', NULL, 2, 4, -1, 10, 1, '2024-02-04 14:09:44');
INSERT INTO `table_column_index` VALUES (35, '预算金额', 'ysje', 'NUMBER', NULL, 2, 4, -1, 11, 1, '2024-02-04 14:10:07');
INSERT INTO `table_column_index` VALUES (36, '实际支出', 'sjzc', 'NUMBER', NULL, 2, 4, -1, 12, 1, '2024-02-04 14:10:52');
INSERT INTO `table_column_index` VALUES (37, '备注', 'bz', 'TEXT', NULL, 2, 4, -1, 13, 1, '2024-02-04 14:11:35');
INSERT INTO `table_column_index` VALUES (38, '是否盈利', 'sfyl', 'SELECT_ITEM', '{\"items\":\"是,否\"}', 2, 5, -1, 14, 1, '2024-02-04 14:12:14');
INSERT INTO `table_column_index` VALUES (39, '是否超支', 'sfcz', 'SELECT_ITEM', '{\"items\":\"是,否\"}', 2, 5, -1, 15, 1, '2024-02-04 14:12:42');
INSERT INTO `table_column_index` VALUES (40, '预估利润', 'yglr', 'NUMBER', NULL, 2, 5, -1, 16, 1, '2024-02-04 14:14:29');
INSERT INTO `table_column_index` VALUES (41, '实际利润', 'sjlr', 'NUMBER', NULL, 2, 5, -1, 17, 1, '2024-02-04 14:14:44');
INSERT INTO `table_column_index` VALUES (42, '预算名称', 'ysmc', 'SINGLE_TEXT', NULL, 2, -1, 1, 18, 1, '2024-02-04 14:25:21');
INSERT INTO `table_column_index` VALUES (43, '既定金额', 'jdje', 'NUMBER', NULL, 2, -1, 1, 19, 1, '2024-02-04 14:25:49');
INSERT INTO `table_column_index` VALUES (44, '已用金额', 'yyje', 'NUMBER', NULL, 2, -1, 1, 20, 1, '2024-02-04 14:26:45');
INSERT INTO `table_column_index` VALUES (45, '是否超支', 'sfcz', 'SELECT_ITEM', '{\"items\":\"是,否\"}', 2, -1, 1, 21, 1, '2024-02-04 14:27:30');
INSERT INTO `table_column_index` VALUES (46, '组织名录附件', 'zzmlfj', 'FILE', '{\"contentId\":9}', 2, 3, -1, 22, 1, '2024-02-04 14:44:19');
INSERT INTO `table_column_index` VALUES (47, '采购人', 'cgr', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":1,\"columnId\":1}', 3, 1, -1, 1, 1, '2024-02-04 14:53:48');
INSERT INTO `table_column_index` VALUES (48, '采购时间', 'cgsj', 'DATETIME', NULL, 3, 1, -1, 2, 1, '2024-02-04 14:56:08');
INSERT INTO `table_column_index` VALUES (49, '采购编号', 'cgbh', 'SINGLE_TEXT', NULL, 3, 1, -1, 3, 1, '2024-02-04 14:56:23');
INSERT INTO `table_column_index` VALUES (50, '采购名称', 'cgmc', 'SINGLE_TEXT', NULL, 3, 1, -1, 4, 1, '2024-02-04 14:56:59');
INSERT INTO `table_column_index` VALUES (51, '合同附件', 'htfj', 'FILE', '{\"contentId\":8}', 3, 1, -1, 5, 1, '2024-02-04 14:57:48');
INSERT INTO `table_column_index` VALUES (52, '备注', 'bz', 'TEXT', NULL, 3, 1, -1, 6, 1, '2024-02-04 15:01:24');
INSERT INTO `table_column_index` VALUES (53, '项目名称', 'xmmc', 'BROWSER_BOX', '{\"isVirtual\":false,\"tableId\":2,\"columnId\":26}', 3, 2, -1, 7, 1, '2024-02-04 15:01:56');
INSERT INTO `table_column_index` VALUES (54, '项目经理', 'xmjl', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":1,\"columnId\":1}', 3, 2, -1, 8, 1, '2024-02-04 15:03:44');
INSERT INTO `table_column_index` VALUES (55, '采购金额', 'cgje', 'NUMBER', NULL, 3, 3, -1, 9, 1, '2024-02-04 15:04:58');
INSERT INTO `table_column_index` VALUES (56, '采购币种', 'cgbz', 'SELECT_ITEM', '{\"items\":\"CNY,HKD,USD,EUR,JPY\"}', 3, 3, -1, 10, 1, '2024-02-04 15:11:18');
INSERT INTO `table_column_index` VALUES (57, '税率', 'sl', 'NUMBER', NULL, 3, 3, -1, 11, 1, '2024-02-04 15:11:33');
INSERT INTO `table_column_index` VALUES (58, '汇率', 'hl', 'NUMBER', NULL, 3, 3, -1, 12, 1, '2024-02-04 15:12:00');
INSERT INTO `table_column_index` VALUES (59, '付款金额', 'fkje', 'NUMBER', NULL, 3, 3, -1, 13, 1, '2024-02-04 15:14:55');
INSERT INTO `table_column_index` VALUES (60, '交付项', 'jfx', 'SINGLE_TEXT', NULL, 3, -1, 1, 14, 1, '2024-02-04 15:15:36');
INSERT INTO `table_column_index` VALUES (61, '交付条件', 'jftj', 'SINGLE_TEXT', NULL, 3, -1, 1, 15, 1, '2024-02-04 15:15:54');
INSERT INTO `table_column_index` VALUES (62, '交付时间', 'jfsj', 'DATETIME', NULL, 3, -1, 1, 16, 1, '2024-02-04 15:17:03');
INSERT INTO `table_column_index` VALUES (63, '交付内容', 'jfnr', 'TEXT', NULL, 3, -1, 1, 17, 1, '2024-02-04 15:17:41');
INSERT INTO `table_column_index` VALUES (64, '对应付款', 'dyfk', 'NUMBER', NULL, 3, -1, 1, 18, 1, '2024-02-04 15:18:07');
INSERT INTO `table_column_index` VALUES (65, '预算项', 'ysx', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":6,\"columnId\":8}', 3, -1, 2, 19, 1, '2024-02-04 15:43:20');
INSERT INTO `table_column_index` VALUES (66, '既定金额', 'jdje', 'NUMBER', NULL, 3, -1, 2, 20, 1, '2024-02-04 15:44:09');
INSERT INTO `table_column_index` VALUES (67, '已用金额', 'yyje', 'NUMBER', NULL, 3, -1, 2, 21, 1, '2024-02-04 15:44:28');
INSERT INTO `table_column_index` VALUES (68, '本次金额', 'bcje', 'NUMBER', NULL, 3, -1, 2, 22, 1, '2024-02-04 15:44:53');
INSERT INTO `table_column_index` VALUES (69, '报销人', 'bxr', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":1,\"columnId\":1}', 4, 1, -1, 1, 1, '2024-02-04 14:53:48');
INSERT INTO `table_column_index` VALUES (70, '报销时间', 'bxsj', 'DATETIME', NULL, 4, 1, -1, 2, 1, '2024-02-04 14:56:08');
INSERT INTO `table_column_index` VALUES (71, '项目名称', 'xmmc', 'BROWSER_BOX', '{\"isVirtual\":false,\"tableId\":2,\"columnId\":26}', 4, 2, -1, 3, 1, '2024-02-04 15:01:56');
INSERT INTO `table_column_index` VALUES (72, '项目经理', 'xmjl', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":1,\"columnId\":1}', 4, 2, -1, 4, 1, '2024-02-04 15:03:44');
INSERT INTO `table_column_index` VALUES (73, '报销金额', 'bxje', 'NUMBER', NULL, 4, 3, -1, 5, 1, '2024-02-04 15:04:58');
INSERT INTO `table_column_index` VALUES (74, '报销事项', 'bxsx', 'TEXT', NULL, 4, 3, -1, 6, 1, '2024-02-04 15:11:18');
INSERT INTO `table_column_index` VALUES (75, '报销明细', 'bxnr', 'SINGLE_TEXT', NULL, 4, -1, 1, 7, 1, '2024-02-04 16:48:19');
INSERT INTO `table_column_index` VALUES (76, '明细金额', 'mxje', 'NUMBER', NULL, 4, -1, 1, 8, 1, '2024-02-04 16:48:52');
INSERT INTO `table_column_index` VALUES (77, '明细发票', 'mxfp', 'FILE', '{\"contentId\":6}', 4, -1, 1, 9, 1, '2024-02-04 16:49:21');
INSERT INTO `table_column_index` VALUES (78, '预算项', 'ysx', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":6,\"columnId\":8}', 4, -1, 2, 10, 1, '2024-02-04 15:43:20');
INSERT INTO `table_column_index` VALUES (79, '既定金额', 'jdje', 'NUMBER', NULL, 4, -1, 2, 10, 1, '2024-02-04 15:44:09');
INSERT INTO `table_column_index` VALUES (80, '已用金额', 'yyje', 'NUMBER', NULL, 4, -1, 2, 12, 1, '2024-02-04 15:44:28');
INSERT INTO `table_column_index` VALUES (81, '本次金额', 'bcje', 'NUMBER', NULL, 4, -1, 2, 13, 1, '2024-02-04 15:44:53');
INSERT INTO `table_column_index` VALUES (82, '项目名称', 'xmmc', 'BROWSER_BOX', '{\"isVirtual\":false,\"tableId\":2,\"columnId\":26}', 5, 1, -1, 1, 1, '2024-02-04 17:20:01');
INSERT INTO `table_column_index` VALUES (83, '项目经理', 'xmjl', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":1,\"columnId\":1}', 5, 1, -1, 2, 1, '2024-02-04 17:22:51');
INSERT INTO `table_column_index` VALUES (84, '调整时间', 'tzsj', 'DATETIME', NULL, 5, 2, -1, 3, 1, '2024-02-04 17:24:00');
INSERT INTO `table_column_index` VALUES (85, '调整原因', 'tzyy', 'TEXT', NULL, 5, 2, -1, 4, 1, '2024-02-04 17:24:18');
INSERT INTO `table_column_index` VALUES (86, '预算项', 'ysx', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":6,\"columnId\":8}', 5, -1, 1, 5, 1, '2024-02-04 17:24:54');
INSERT INTO `table_column_index` VALUES (87, '原既定金额', 'yjdje', 'NUMBER', NULL, 5, -1, 1, 6, 1, '2024-02-04 17:25:18');
INSERT INTO `table_column_index` VALUES (88, '新既定金额', 'xjdje', 'NUMBER', NULL, 5, -1, 1, 7, 1, '2024-02-04 17:25:39');
INSERT INTO `table_column_index` VALUES (89, '项目名称', 'xmmc', 'BROWSER_BOX', '{\"isVirtual\":false,\"tableId\":2,\"columnId\":26}', 6, 1, -1, 1, 1, '2024-02-04 17:20:01');
INSERT INTO `table_column_index` VALUES (90, '项目经理', 'xmjl', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":1,\"columnId\":1}', 6, 1, -1, 2, 1, '2024-02-04 17:22:51');
INSERT INTO `table_column_index` VALUES (91, '调整时间', 'tzsj', 'DATETIME', NULL, 6, 2, -1, 3, 1, '2024-02-04 17:24:00');
INSERT INTO `table_column_index` VALUES (92, '调整原因', 'tzyy', 'TEXT', NULL, 6, 2, -1, 4, 1, '2024-02-04 17:24:18');
INSERT INTO `table_column_index` VALUES (93, '预算项', 'ysx', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":6,\"columnId\":8}', 6, -1, 1, 5, 1, '2024-02-04 17:24:54');
INSERT INTO `table_column_index` VALUES (94, '既定金额', 'jdje', 'NUMBER', NULL, 6, -1, 1, 6, 1, '2024-02-04 17:25:18');
INSERT INTO `table_column_index` VALUES (95, '已用金额', 'yyje', 'NUMBER', NULL, 6, -1, 1, 6, 1, '2024-02-04 17:25:18');
INSERT INTO `table_column_index` VALUES (96, '调整金额', 'tzje', 'NUMBER', NULL, 6, -1, 1, 7, 1, '2024-02-04 17:25:39');
INSERT INTO `table_column_index` VALUES (97, '分配情况', 'fpqk', 'SELECT_ITEM', '{\"items\":\"待分配,已分配,已废弃\"}', 3, 3, -1, 23, 1, '2024-02-06 16:55:31');
INSERT INTO `table_column_index` VALUES (98, '分配情况', 'fpqk', 'SELECT_ITEM', '{\"items\":\"待分配,已分配,已废弃\"}', 4, 3, -1, 14, 1, '2024-02-06 16:57:45');
INSERT INTO `table_column_index` VALUES (99, '调整情况', 'tzqk', 'SELECT_ITEM', '{\"items\":\"待审批,已调整,已退回\"}', 6, 2, -1, 8, 1, '2024-02-07 16:34:22');
INSERT INTO `table_column_index` VALUES (100, '调整情况', 'tzqk', 'SELECT_ITEM', '{\"items\":\"待审批,已调整,已退回\"}', 5, 2, -1, 8, 1, '2024-02-19 13:50:22');

-- ----------------------------
-- Table structure for table_index
-- ----------------------------
DROP TABLE IF EXISTS `table_index`;
CREATE TABLE `table_index`  (
  `tableId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '表单编号',
  `tableViewName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '表单显示名称',
  `tableDataName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '表单存储名称',
  `moduleNo` bigint(100) UNSIGNED NULL DEFAULT NULL COMMENT '所属模块',
  `workFlowNo` bigint(100) UNSIGNED NULL DEFAULT NULL COMMENT '对应流程',
  `detailCount` int(11) NULL DEFAULT NULL COMMENT '明细表数量',
  `detailName` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '明细表名称',
  `groupCount` int(11) NULL DEFAULT NULL COMMENT '格式分组数量',
  `groupName` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '格式分组名称',
  `remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `defaultEdit` json NULL COMMENT '默认编辑权限',
  `defaultCreate` json NULL COMMENT '默认创建权限',
  `defaultDelete` json NULL COMMENT '默认删除权限',
  `defaultShare` json NULL COMMENT '默认共享权限',
  `creator` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '创建人',
  `createTime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`tableId`) USING BTREE,
  UNIQUE INDEX `tableViewName_UNIQUE`(`tableViewName`) USING BTREE,
  UNIQUE INDEX `tableDataName_UNIQUE`(`tableDataName`) USING BTREE,
  INDEX `workFlowId_Foreign`(`workFlowNo`) USING BTREE COMMENT '流程搜索加速索引',
  INDEX `ModuleNo_Foreign`(`moduleNo`) USING BTREE COMMENT '模块搜索加速索引'
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '表单数据索引表（table_index）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of table_index
-- ----------------------------
INSERT INTO `table_index` VALUES (1, '销售合同', 'form_table_1', 6, NULL, 1, '付款条件', 4, '申请信息,合同基础信息,相对方信息,金额信息', '项目预算表单', '{\"body\": {\"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":16,\\\"grade\\\":1}]}\"}, \"table\": {}, \"bodyType\": \"characterConstraint,\", \"tableType\": \"\"}', NULL, NULL, '{\"body\": {\"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":true,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\", \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":1,\\\"grade\\\":0},{\\\"characterId\\\":13,\\\"grade\\\":0},{\\\"characterId\\\":12,\\\"grade\\\":2},{\\\"characterId\\\":15,\\\"grade\\\":2}]}\"}, \"table\": {}, \"bodyType\": \"createConstraint,characterConstraint,\", \"tableType\": \"\"}', 1, '2024-02-02 16:03:51');
INSERT INTO `table_index` VALUES (2, '项目预算编制', 'form_table_2', 6, NULL, 1, '预算明细', 5, '项目信息,合同信息,组织信息,金额信息,利润信息', '项目预算表单', '{\"body\": {\"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":true,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\"}, \"table\": {}, \"bodyType\": \"createConstraint,\", \"tableType\": \"\"}', NULL, NULL, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\", \"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":false,\\\"section\\\":true,\\\"sectionRecursive\\\":false}\", \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":1,\\\"grade\\\":0}]}\"}, \"table\": {\"proposedConstraint\": \"{\\\"humans\\\":[],\\\"departs\\\":[33],\\\"sections\\\":[]}\"}, \"bodyType\": \"createConstraint,characterConstraint,allConstraint,\", \"tableType\": \"proposedConstraint,\"}', 1, '2024-02-02 16:18:13');
INSERT INTO `table_index` VALUES (3, '采购单', 'form_table_3', 6, NULL, 2, '物料交付明细,预算分配明细', 3, '采购信息,项目信息,金额信息', '项目预算表单', NULL, NULL, NULL, '{\"body\": {\"createConstraint\": \"{\\\"self\\\":false,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":false,\\\"section\\\":true,\\\"sectionRecursive\\\":false}\", \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":1,\\\"grade\\\":0},{\\\"characterId\\\":11,\\\"grade\\\":2}]}\"}, \"table\": {}, \"bodyType\": \"createConstraint,characterConstraint,\", \"tableType\": \"\"}', 1, '2024-02-02 16:19:35');
INSERT INTO `table_index` VALUES (4, '报销单', 'form_table_4', 6, NULL, 2, '报销金额明细,预算分配明细', 3, '报销信息,项目信息,金额信息', '项目预算表单', NULL, NULL, NULL, '{\"body\": {\"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":true,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\", \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":1,\\\"grade\\\":0}]}\"}, \"table\": {}, \"bodyType\": \"createConstraint,characterConstraint,\", \"tableType\": \"\"}', 1, '2024-02-02 16:19:40');
INSERT INTO `table_index` VALUES (5, '既定预算调整单', 'form_table_5', 6, NULL, 1, '调整明细', 2, '项目信息,调整信息', '项目预算表单', NULL, NULL, NULL, '{\"body\": {\"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":false,\\\"section\\\":true,\\\"sectionRecursive\\\":false}\", \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":1,\\\"grade\\\":0},{\\\"characterId\\\":15,\\\"grade\\\":2}]}\"}, \"table\": {}, \"bodyType\": \"createConstraint,characterConstraint,\", \"tableType\": \"\"}', 1, '2024-02-04 09:53:27');
INSERT INTO `table_index` VALUES (6, '使用预算调整单', 'form_table_6', 6, NULL, 1, '调整明细', 2, '项目信息,调整信息', '项目预算表单', NULL, NULL, NULL, '{\"body\": {\"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":false,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\", \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":1,\\\"grade\\\":0},{\\\"characterId\\\":15,\\\"grade\\\":2}]}\"}, \"table\": {}, \"bodyType\": \"createConstraint,characterConstraint,\", \"tableType\": \"\"}', 1, '2024-02-04 09:53:41');

-- ----------------------------
-- Table structure for table_view_column_index
-- ----------------------------
DROP TABLE IF EXISTS `table_view_column_index`;
CREATE TABLE `table_view_column_index`  (
  `columnId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '字段id',
  `columnViewName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字段显示名称',
  `columnDataName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字段数据库存储名称',
  `columnType` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字段类型',
  `columnTypeDescription` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附带格式描述',
  `tableNo` bigint(64) UNSIGNED NOT NULL COMMENT '对应存储数据库表',
  `columnGroupNo` int(11) NULL DEFAULT -1 COMMENT '格式分组',
  `columnViewNo` int(11) NULL DEFAULT NULL COMMENT '顺序序号',
  `creator` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '创建人',
  `createTime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `columnViewDisplay` tinyint(4) NULL DEFAULT 1 COMMENT '展示时是否显示(0否，1是)',
  PRIMARY KEY (`columnId`) USING BTREE,
  INDEX `tableNo_FOREIGN`(`tableNo`) USING BTREE COMMENT '所属表单索引加速'
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字段数据索引表（table_column_index）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of table_view_column_index
-- ----------------------------
INSERT INTO `table_view_column_index` VALUES (1, '姓名', 'name', 'SINGLE_TEXT', NULL, 1, 1, 1, 1, '2024-01-24 14:29:50', 1);
INSERT INTO `table_view_column_index` VALUES (2, '性别', 'sex', 'SELECT_ITEM', '{\"items\":\"男,女\"}', 1, 1, 2, 1, '2024-01-24 14:45:07', 1);
INSERT INTO `table_view_column_index` VALUES (3, '手机号', 'phone', 'SINGLE_TEXT', NULL, 1, 2, 3, 1, '2024-01-24 14:46:44', 1);
INSERT INTO `table_view_column_index` VALUES (4, '部门名称', 'departName', 'SINGLE_TEXT', NULL, 2, 1, 1, 1, '2024-01-24 17:22:31', 1);
INSERT INTO `table_view_column_index` VALUES (5, '分部名称', 'sectionName', 'SINGLE_TEXT', NULL, 3, 1, 1, 1, '2024-01-24 17:39:56', 1);
INSERT INTO `table_view_column_index` VALUES (6, '目录名称', 'contentName', 'SINGLE_TEXT', NULL, 4, 1, 1, 1, '2024-01-25 16:26:56', 1);
INSERT INTO `table_view_column_index` VALUES (7, '文件名称', 'fileName', 'SINGLE_TEXT', NULL, 5, 1, 1, 1, '2024-01-25 16:30:10', 1);
INSERT INTO `table_view_column_index` VALUES (8, '预算名称', 'ysmc', 'SINGLE_TEXT', NULL, 6, 1, 1, 1, '2024-02-04 15:33:18', 1);
INSERT INTO `table_view_column_index` VALUES (9, '预算金额', 'jdje', 'NUMBER', NULL, 6, 1, 2, 1, '2024-02-21 17:35:35', 1);
INSERT INTO `table_view_column_index` VALUES (10, '使用预算', 'ysyje', 'NUMBER', NULL, 6, 1, 3, 1, '2024-02-07 16:52:08', 1);
INSERT INTO `table_view_column_index` VALUES (11, '项目名称', 'xmmc', 'BROWSER_BOX', '{\"isVirtual\":false,\"tableId\":2,\"columnId\":26}', 6, 2, 4, 1, '2024-02-07 16:56:43', 1);
INSERT INTO `table_view_column_index` VALUES (12, '项目经理', 'xmjl', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":1,\"columnId\":1}', 6, 2, 5, 1, '2024-02-07 17:01:40', 1);
INSERT INTO `table_view_column_index` VALUES (13, '合同编号', 'htbh', 'BROWSER_BOX', '{\"isVirtual\":false,\"tableId\":1,\"columnId\":5}', 6, 3, 6, 1, '2024-02-07 17:02:05', 1);
INSERT INTO `table_view_column_index` VALUES (14, '合同编号', 'htbh', 'BROWSER_BOX', '{\"isVirtual\":false,\"tableId\":1,\"columnId\":5}', 7, 1, 1, 1, '2024-02-07 17:06:18', 1);
INSERT INTO `table_view_column_index` VALUES (15, '项目名称', 'xmmc', 'BROWSER_BOX', '{\"isVirtual\":false,\"tableId\":2,\"columnId\":26}', 7, 2, 2, 1, '2024-02-07 17:06:30', 1);
INSERT INTO `table_view_column_index` VALUES (16, '项目经理', 'xmjl', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":1,\"columnId\":1}', 7, 2, 3, 1, '2024-02-07 17:06:45', 1);
INSERT INTO `table_view_column_index` VALUES (17, '预算名称', 'ysmc', 'SINGLE_TEXT', NULL, 7, 3, 4, 1, '2024-02-07 17:07:04', 1);
INSERT INTO `table_view_column_index` VALUES (18, '使用金额', 'je', 'NUMBER', NULL, 7, 3, 5, 1, '2024-02-07 17:07:27', 1);
INSERT INTO `table_view_column_index` VALUES (19, '支出类型', 'type', 'SELECT_ITEM', '{\"items\":\"采购支出,报销支出,调整支出\"}', 7, 3, 6, 1, '2024-02-07 17:10:55', 1);
INSERT INTO `table_view_column_index` VALUES (20, '支出时间', 'createTime', 'DATETIME', NULL, 7, 3, 7, 1, '2024-02-07 17:11:11', 1);
INSERT INTO `table_view_column_index` VALUES (21, '是否超支', 'sfcz', 'SELECT_ITEM', '{\"items\":\"未超支,已超支\"}', 6, -1, 7, 1, '2024-02-07 17:21:44', 1);

-- ----------------------------
-- Table structure for table_view_index
-- ----------------------------
DROP TABLE IF EXISTS `table_view_index`;
CREATE TABLE `table_view_index`  (
  `tableId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '表单编号',
  `tableViewName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '表单显示名称',
  `tableDataName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '表单存储名称',
  `moduleNo` bigint(100) UNSIGNED NULL DEFAULT NULL COMMENT '所属模块',
  `groupCount` int(11) NULL DEFAULT NULL COMMENT '格式分组数量',
  `groupName` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '格式分组名称',
  `remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `creator` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '创建人',
  `createTime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`tableId`) USING BTREE,
  UNIQUE INDEX `tableViewName_UNIQUE`(`tableViewName`) USING BTREE,
  UNIQUE INDEX `tableDataName_UNIQUE`(`tableDataName`) USING BTREE,
  INDEX `ModuleNo_Foreign`(`moduleNo`) USING BTREE COMMENT '模块搜索加速索引'
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '表单数据索引表（table_index）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of table_view_index
-- ----------------------------
INSERT INTO `table_view_index` VALUES (1, '人力资源', 'human_resource', 1, 2, '人力信息,联系方式', '系统默认索引表单', 1, '2024-01-24 13:39:39');
INSERT INTO `table_view_index` VALUES (2, '职能部门', 'depart_resource', 1, 1, '组织信息', '系统默认索引表单', 1, '2024-01-24 17:10:35');
INSERT INTO `table_view_index` VALUES (3, '公司分部', 'section_resource', 1, 1, '分部信息', '系统默认索引表单', 1, '2024-01-24 17:26:21');
INSERT INTO `table_view_index` VALUES (4, '目录', 'content_list', 2, 1, '目录信息', '系统默认索引表单', 1, '2024-01-25 16:25:21');
INSERT INTO `table_view_index` VALUES (5, '文件', 'file_storage', 2, 1, '文件信息', '系统默认索引表单', 1, '2024-01-25 16:27:29');
INSERT INTO `table_view_index` VALUES (6, '预算使用统计', 'ysxx_use_statistics', 6, 3, '预算细项,项目信息,合同信息', '项目预算表单', 1, '2024-02-04 15:31:25');
INSERT INTO `table_view_index` VALUES (7, '预算使用明细', 'ysxx_use_record', 6, 3, '合同信息,项目信息,支出信息', '项目预算表单', 1, '2024-02-07 16:39:53');

-- ----------------------------
-- Table structure for workflow
-- ----------------------------
DROP TABLE IF EXISTS `workflow`;
CREATE TABLE `workflow`  (
  `dataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '数据编号,流程唯一id',
  `moduleTypeId` bigint(64) UNSIGNED NOT NULL COMMENT '所属模块id',
  `tableId` bigint(64) UNSIGNED NOT NULL COMMENT '对应表单id',
  `titleColumnId` bigint(64) UNSIGNED NULL DEFAULT NULL COMMENT '流程标题字段id',
  `workFlowName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '流程名称',
  `workflowDescription` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程描述',
  `workflowBaseTitle` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程默认标题',
  `creator` bigint(64) UNSIGNED NOT NULL COMMENT '创建人唯一id',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `isDeprecated` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否废弃',
  PRIMARY KEY (`dataId`) USING BTREE,
  UNIQUE INDEX `workFlowName_Unique`(`workFlowName`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流程表单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of workflow
-- ----------------------------
INSERT INTO `workflow` VALUES (1, 6, 1, 5, '销售合同审批流程', NULL, '销售合同审批-', 1, '2024-02-05 10:46:51', 0);
INSERT INTO `workflow` VALUES (2, 6, 2, 25, '项目预算编制流程', NULL, '项目预算编制审批-', 1, '2024-02-05 14:11:09', 0);
INSERT INTO `workflow` VALUES (3, 6, 3, 49, '采购审批流程', NULL, '采购审批', 1, '2024-02-05 14:16:15', 0);
INSERT INTO `workflow` VALUES (4, 6, 4, 74, '报销审批流程', NULL, '报销审批', 1, '2024-02-05 14:16:35', 0);
INSERT INTO `workflow` VALUES (5, 6, 5, 85, '既定预算调整审批流程', NULL, '既定预算调整申请', 1, '2024-02-05 14:17:12', 0);
INSERT INTO `workflow` VALUES (6, 6, 6, 92, '使用预算调整审批流程', '', '使用预算调整申请', 1, '2024-02-05 14:18:00', 0);

-- ----------------------------
-- Table structure for workflow_node
-- ----------------------------
DROP TABLE IF EXISTS `workflow_node`;
CREATE TABLE `workflow_node`  (
  `dataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '节点编号',
  `workflowNodeName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点名称',
  `userAuthorityLimit` json NULL COMMENT '操作对象权限组',
  `isCounterSign` int(11) NOT NULL DEFAULT 0 COMMENT '是否需要会签',
  `nodeType` int(11) NOT NULL COMMENT '节点类型 0、创建，1、提交，2、审批，3、抄送，4、归档',
  `tableModifyAuthority` json NULL COMMENT '当前节点允许修改的表单字段',
  `beforeAction` json NULL COMMENT '节点前操作',
  `checkAction` json NULL COMMENT '提交需要满足的条件',
  `afterAction` json NULL COMMENT '节点后操作',
  `workflowId` bigint(64) UNSIGNED NOT NULL COMMENT '所属流程编号',
  `viewNo` int(11) NOT NULL COMMENT '显示顺序',
  `creator` bigint(64) UNSIGNED NOT NULL COMMENT '创建人唯一id',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`dataId`) USING BTREE,
  INDEX `node_workflowId_index`(`workflowId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '节点数据' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of workflow_node
-- ----------------------------
INSERT INTO `workflow_node` VALUES (1, '创建', '{\"body\": {\"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":1,\\\"grade\\\":0},{\\\"characterId\\\":12,\\\"grade\\\":2}]}\"}, \"table\": {}, \"bodyType\": \"characterConstraint,\", \"tableType\": \"\"}', 0, 0, '{\"1\": true, \"2\": true, \"3\": true, \"4\": true, \"5\": true, \"6\": true, \"7\": true, \"8\": true, \"9\": true, \"10\": true, \"11\": true, \"12\": true, \"13\": true, \"14\": true, \"15\": true, \"16\": true, \"17\": true, \"18\": true, \"19\": true, \"20\": true, \"21\": true, \"22\": true, \"23\": true, \"24\": true}', NULL, NULL, NULL, 1, 1, 1, '2024-02-05 10:50:15');
INSERT INTO `workflow_node` VALUES (2, '上级审批', '{\"body\": {\"createConstraint\": \"{\\\"self\\\":false,\\\"leader\\\":true,\\\"leaderRecursion\\\":false,\\\"depart\\\":false,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\"}, \"table\": {}, \"bodyType\": \"createConstraint,\", \"tableType\": \"\"}', 0, 1, '{\"1\": false, \"2\": false, \"3\": false, \"4\": false, \"5\": false, \"6\": false, \"7\": false, \"8\": false, \"9\": false, \"10\": false, \"11\": false, \"12\": false, \"13\": false, \"14\": false, \"15\": false, \"16\": false, \"17\": false, \"18\": false, \"19\": false, \"20\": false, \"21\": false, \"22\": false, \"23\": false, \"24\": false}', NULL, NULL, NULL, 1, 2, 1, '2024-02-05 10:52:39');
INSERT INTO `workflow_node` VALUES (3, '分部领导审批', '{\"body\": {\"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":16,\\\"grade\\\":1}]}\"}, \"table\": {}, \"bodyType\": \"characterConstraint,\", \"tableType\": \"\"}', 0, 1, '{\"1\": false, \"2\": false, \"3\": false, \"4\": false, \"5\": false, \"6\": false, \"7\": false, \"8\": false, \"9\": false, \"10\": false, \"11\": false, \"12\": false, \"13\": false, \"14\": false, \"15\": false, \"16\": false, \"17\": false, \"18\": false, \"19\": false, \"20\": false, \"21\": false, \"22\": false, \"23\": false, \"24\": false}', NULL, NULL, NULL, 1, 3, 1, '2024-02-05 10:56:36');
INSERT INTO `workflow_node` VALUES (4, '公司领导审批', '{\"body\": {\"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":13,\\\"grade\\\":1}]}\"}, \"table\": {}, \"bodyType\": \"characterConstraint,\", \"tableType\": \"\"}', 0, 1, '{\"1\": false, \"2\": false, \"3\": false, \"4\": false, \"5\": false, \"6\": false, \"7\": false, \"8\": false, \"9\": false, \"10\": false, \"11\": false, \"12\": false, \"13\": false, \"14\": false, \"15\": false, \"16\": false, \"17\": false, \"18\": false, \"19\": false, \"20\": false, \"21\": false, \"22\": false, \"23\": false, \"24\": false}', NULL, NULL, NULL, 1, 4, 1, '2024-02-05 11:04:18');
INSERT INTO `workflow_node` VALUES (5, '通知预决算部', '{\"body\": {\"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":15,\\\"grade\\\":1}]}\"}, \"table\": {}, \"bodyType\": \"characterConstraint,\", \"tableType\": \"\"}', 0, 2, '{\"1\": false, \"2\": false, \"3\": false, \"4\": false, \"5\": false, \"6\": false, \"7\": false, \"8\": false, \"9\": false, \"10\": false, \"11\": false, \"12\": false, \"13\": false, \"14\": false, \"15\": false, \"16\": false, \"17\": false, \"18\": false, \"19\": false, \"20\": false, \"21\": false, \"22\": false, \"23\": false, \"24\": false}', NULL, NULL, NULL, 1, 5, 1, '2024-02-05 11:05:18');
INSERT INTO `workflow_node` VALUES (6, '创建人归档', '{\"body\": {\"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":false,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\"}, \"table\": {}, \"bodyType\": \"createConstraint,\", \"tableType\": \"\"}', 0, 3, '{\"1\": false, \"2\": false, \"3\": false, \"4\": false, \"5\": false, \"6\": false, \"7\": false, \"8\": false, \"9\": false, \"10\": false, \"11\": false, \"12\": false, \"13\": false, \"14\": false, \"15\": false, \"16\": false, \"17\": false, \"18\": false, \"19\": false, \"20\": false, \"21\": false, \"22\": false, \"23\": false, \"24\": false}', NULL, NULL, NULL, 1, 6, 1, '2024-02-05 11:05:33');
INSERT INTO `workflow_node` VALUES (7, '建立组织', '{\"body\": {\"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":1,\\\"grade\\\":0},{\\\"characterId\\\":15,\\\"grade\\\":2}]}\"}, \"table\": {}, \"bodyType\": \"characterConstraint,\", \"tableType\": \"\"}', 0, 0, '{\"25\": true, \"26\": true, \"27\": true, \"28\": true, \"29\": true, \"30\": true, \"31\": true, \"32\": true, \"33\": true, \"34\": false, \"35\": false, \"36\": false, \"37\": true, \"38\": false, \"39\": false, \"40\": false, \"41\": false, \"42\": true, \"43\": true, \"44\": true, \"45\": true, \"46\": true}', NULL, NULL, '{\"tasks\": [], \"classNames\": [\"org.eoa.projectbudget.extension.BudgetColumnLink\"]}', 2, 1, 1, '2024-02-05 14:27:02');
INSERT INTO `workflow_node` VALUES (8, '领导审批', '{\"body\": {\"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":16,\\\"grade\\\":1}]}\"}, \"table\": {}, \"bodyType\": \"characterConstraint,\", \"tableType\": \"\"}', 0, 1, '{\"25\": false, \"26\": false, \"27\": false, \"28\": false, \"29\": false, \"30\": false, \"31\": false, \"32\": false, \"33\": false, \"34\": false, \"35\": false, \"36\": false, \"37\": false, \"38\": false, \"39\": false, \"40\": false, \"41\": false, \"42\": false, \"43\": false, \"44\": false, \"45\": false, \"46\": false}', NULL, NULL, NULL, 2, 2, 1, '2024-02-05 14:27:12');
INSERT INTO `workflow_node` VALUES (9, '通知项目经理', '{\"body\": {\"characterConstraint\": \"{\\\"characters\\\":[]}\"}, \"table\": {\"proposedConstraint\": \"{\\\"humans\\\":[32],\\\"departs\\\":[],\\\"sections\\\":[]}\"}, \"bodyType\": \"characterConstraint,\", \"tableType\": \"proposedConstraint,\"}', 0, 2, '{\"25\": false, \"26\": false, \"27\": false, \"28\": false, \"29\": false, \"30\": false, \"31\": false, \"32\": false, \"33\": false, \"34\": false, \"35\": false, \"36\": false, \"37\": false, \"38\": false, \"39\": false, \"40\": false, \"41\": false, \"42\": false, \"43\": false, \"44\": false, \"45\": false, \"46\": false}', NULL, NULL, NULL, 2, 3, 1, '2024-02-05 14:27:24');
INSERT INTO `workflow_node` VALUES (10, '完成归档', '{\"body\": {\"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":false,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\"}, \"table\": {}, \"bodyType\": \"createConstraint,\", \"tableType\": \"\"}', 0, 3, '{\"25\": false, \"26\": false, \"27\": false, \"28\": false, \"29\": false, \"30\": false, \"31\": false, \"32\": false, \"33\": false, \"34\": false, \"35\": false, \"36\": false, \"37\": false, \"38\": false, \"39\": false, \"40\": false, \"41\": false, \"42\": false, \"43\": false, \"44\": false, \"45\": false, \"46\": false}', NULL, NULL, NULL, 2, 4, 1, '2024-02-05 14:27:39');
INSERT INTO `workflow_node` VALUES (11, '采购发起', '{\"body\": {\"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":1,\\\"grade\\\":0},{\\\"characterId\\\":11,\\\"grade\\\":2}]}\"}, \"table\": {}, \"bodyType\": \"characterConstraint,\", \"tableType\": \"\"}', 0, 0, '{\"47\": true, \"48\": true, \"49\": true, \"50\": true, \"51\": true, \"52\": true, \"53\": true, \"54\": false, \"55\": true, \"56\": true, \"57\": true, \"58\": true, \"59\": true, \"60\": true, \"61\": true, \"62\": true, \"63\": true, \"64\": true, \"65\": false, \"66\": false, \"67\": false, \"68\": false, \"97\": false}', '{\"tasks\": [{\"type\": 1, \"input\": \"2\", \"columnId\": 97}], \"classNames\": [\"org.eoa.projectbudget.extension.SyncUseBudget\"]}', NULL, '{\"tasks\": [{\"type\": 1, \"input\": \"0\", \"columnId\": 97}], \"classNames\": [\"org.eoa.projectbudget.extension.BudgetUseMainColumnLink\"]}', 3, 1, 1, '2024-02-05 16:17:19');
INSERT INTO `workflow_node` VALUES (12, '项目经理分配预算', '{\"body\": {}, \"table\": {\"proposedConstraint\": \"{\\\"humans\\\":[54],\\\"departs\\\":[],\\\"sections\\\":[]}\"}, \"bodyType\": \"\", \"tableType\": \"proposedConstraint,\"}', 0, 1, '{\"47\": false, \"48\": false, \"49\": false, \"50\": false, \"51\": false, \"52\": false, \"53\": false, \"54\": false, \"55\": false, \"56\": false, \"57\": false, \"58\": false, \"59\": false, \"60\": false, \"61\": false, \"62\": false, \"63\": false, \"64\": false, \"65\": true, \"66\": false, \"67\": false, \"68\": true, \"97\": false}', NULL, '{\"tasks\": [], \"classNames\": [\"org.eoa.projectbudget.extension.BudgetUseDetailColumnCheck\"]}', '{\"tasks\": [{\"type\": 1, \"input\": \"1\", \"columnId\": 97}], \"classNames\": [\"org.eoa.projectbudget.extension.SyncUseBudget\"]}', 3, 2, 1, '2024-02-05 16:17:41');
INSERT INTO `workflow_node` VALUES (13, '预决算部超支审批', '{\"body\": {\"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":15,\\\"grade\\\":1}]}\"}, \"table\": {}, \"bodyType\": \"characterConstraint,\", \"tableType\": \"\"}', 0, 1, '{\"47\": false, \"48\": false, \"49\": false, \"50\": false, \"51\": false, \"52\": false, \"53\": false, \"54\": false, \"55\": false, \"56\": false, \"57\": false, \"58\": false, \"59\": false, \"60\": false, \"61\": false, \"62\": false, \"63\": false, \"64\": false, \"65\": false, \"66\": false, \"67\": false, \"68\": false}', NULL, NULL, NULL, 3, 3, 1, '2024-02-05 16:18:26');
INSERT INTO `workflow_node` VALUES (14, '归档', '{\"body\": {\"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":false,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\"}, \"table\": {}, \"bodyType\": \"createConstraint,\", \"tableType\": \"\"}', 0, 3, '{\"47\": false, \"48\": false, \"49\": false, \"50\": false, \"51\": false, \"52\": false, \"53\": false, \"54\": false, \"55\": false, \"56\": false, \"57\": false, \"58\": false, \"59\": false, \"60\": false, \"61\": false, \"62\": false, \"63\": false, \"64\": false, \"65\": false, \"66\": false, \"67\": false, \"68\": false}', NULL, NULL, NULL, 3, 4, 1, '2024-02-05 16:18:32');
INSERT INTO `workflow_node` VALUES (15, '业务人员发起', '{\"body\": {\"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":false,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\"}, \"table\": {}, \"bodyType\": \"createConstraint,\", \"tableType\": \"\"}', 0, 0, '{\"69\": true, \"70\": true, \"71\": true, \"72\": true, \"73\": true, \"74\": true, \"75\": true, \"76\": true, \"77\": true, \"78\": false, \"79\": false, \"80\": false, \"81\": false, \"98\": false}', '{\"tasks\": [{\"type\": 1, \"input\": \"2\", \"columnId\": 98}], \"classNames\": [\"org.eoa.projectbudget.extension.SyncUseBudget\"]}', NULL, '{\"tasks\": [{\"type\": 1, \"input\": \"0\", \"columnId\": 98}], \"classNames\": [\"org.eoa.projectbudget.extension.BudgetUseMainColumnLink\"]}', 4, 1, 1, '2024-02-05 17:01:36');
INSERT INTO `workflow_node` VALUES (16, '项目经理分配预算', '{\"body\": {}, \"table\": {\"proposedConstraint\": \"{\\\"humans\\\":[72],\\\"departs\\\":[],\\\"sections\\\":[]}\"}, \"bodyType\": \"\", \"tableType\": \"proposedConstraint,\"}', 0, 1, '{\"69\": false, \"70\": false, \"71\": false, \"72\": false, \"73\": false, \"74\": false, \"75\": false, \"76\": false, \"77\": false, \"78\": true, \"79\": false, \"80\": false, \"81\": true, \"98\": false}', NULL, '{\"tasks\": [], \"classNames\": [\"org.eoa.projectbudget.extension.BudgetUseDetailColumnCheck\"]}', '{\"tasks\": [{\"type\": 1, \"input\": \"1\", \"columnId\": 98}], \"classNames\": [\"org.eoa.projectbudget.extension.SyncUseBudget\"]}', 4, 2, 1, '2024-02-05 17:01:56');
INSERT INTO `workflow_node` VALUES (17, '预算部超支审查', '{\"body\": {\"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":15,\\\"grade\\\":1}]}\"}, \"table\": {}, \"bodyType\": \"characterConstraint,\", \"tableType\": \"\"}', 0, 1, '{\"69\": false, \"70\": false, \"71\": false, \"72\": false, \"73\": false, \"74\": false, \"75\": false, \"76\": false, \"77\": false, \"78\": false, \"79\": false, \"80\": false, \"81\": false, \"98\": false}', NULL, NULL, NULL, 4, 3, 1, '2024-02-05 17:02:17');
INSERT INTO `workflow_node` VALUES (18, '归档', '{\"body\": {\"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":false,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\"}, \"table\": {}, \"bodyType\": \"createConstraint,\", \"tableType\": \"\"}', 0, 3, '{\"69\": false, \"70\": false, \"71\": false, \"72\": false, \"73\": false, \"74\": false, \"75\": false, \"76\": false, \"77\": false, \"78\": false, \"79\": false, \"80\": false, \"81\": false}', NULL, NULL, NULL, 4, 4, 1, '2024-02-05 17:05:35');
INSERT INTO `workflow_node` VALUES (19, '项目经理发起', '{\"body\": {\"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":1,\\\"grade\\\":0},{\\\"characterId\\\":14,\\\"grade\\\":2}]}\"}, \"table\": {}, \"bodyType\": \"characterConstraint,\", \"tableType\": \"\"}', 0, 0, '{\"82\": true, \"83\": true, \"84\": true, \"85\": true, \"86\": true, \"87\": false, \"88\": true, \"100\": false}', '{\"tasks\": [{\"type\": 1, \"input\": \"2\", \"columnId\": 100}], \"classNames\": []}', '{\"tasks\": [], \"classNames\": [\"org.eoa.projectbudget.extension.BudgetUseDetailColumnCheck\"]}', '{\"tasks\": [{\"type\": 1, \"input\": \"0\", \"columnId\": 100}], \"classNames\": [\"org.eoa.projectbudget.extension.BudgetUseMainColumnLink\"]}', 5, 1, 1, '2024-02-05 17:24:37');
INSERT INTO `workflow_node` VALUES (20, '预决算部审批', '{\"body\": {\"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":15,\\\"grade\\\":1}]}\"}, \"table\": {}, \"bodyType\": \"characterConstraint,\", \"tableType\": \"\"}', 0, 1, '{\"82\": false, \"83\": false, \"84\": false, \"85\": false, \"86\": false, \"87\": false, \"88\": false, \"100\": false}', NULL, NULL, '{\"tasks\": [{\"type\": 1, \"input\": \"1\", \"columnId\": 100}], \"classNames\": [\"org.eoa.projectbudget.extension.SyncProposedBudget\"]}', 5, 2, 1, '2024-02-05 17:24:47');
INSERT INTO `workflow_node` VALUES (21, '归档', '{\"body\": {\"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":false,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\"}, \"table\": {}, \"bodyType\": \"createConstraint,\", \"tableType\": \"\"}', 0, 3, '{\"82\": false, \"83\": false, \"84\": false, \"85\": false, \"86\": false, \"87\": false, \"88\": false}', NULL, NULL, NULL, 5, 3, 1, '2024-02-05 17:24:55');
INSERT INTO `workflow_node` VALUES (22, '项目经理发起', '{\"body\": {\"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":1,\\\"grade\\\":0},{\\\"characterId\\\":14,\\\"grade\\\":2}]}\"}, \"table\": {}, \"bodyType\": \"characterConstraint,\", \"tableType\": \"\"}', 0, 0, '{\"89\": true, \"90\": false, \"91\": true, \"92\": true, \"93\": true, \"94\": false, \"95\": false, \"96\": true, \"99\": false}', '{\"tasks\": [{\"type\": 1, \"input\": \"2\", \"columnId\": 99}], \"classNames\": [\"org.eoa.projectbudget.extension.SyncUseBudget\"]}', '{\"tasks\": [], \"classNames\": [\"org.eoa.projectbudget.extension.BudgetUseDetailColumnCheck\"]}', '{\"tasks\": [{\"type\": 1, \"input\": \"0\", \"columnId\": 99}], \"classNames\": [\"org.eoa.projectbudget.extension.BudgetUseMainColumnLink\"]}', 6, 1, 1, '2024-02-05 17:31:58');
INSERT INTO `workflow_node` VALUES (23, '预决算部审批', '{\"body\": {\"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":15,\\\"grade\\\":1}]}\"}, \"table\": {}, \"bodyType\": \"characterConstraint,\", \"tableType\": \"\"}', 0, 1, '{\"89\": false, \"90\": false, \"91\": false, \"92\": false, \"93\": false, \"94\": false, \"95\": false, \"96\": false, \"99\": false}', NULL, NULL, '{\"tasks\": [{\"type\": 1, \"input\": \"1\", \"columnId\": 99}], \"classNames\": [\"org.eoa.projectbudget.extension.SyncUseBudget\"]}', 6, 2, 1, '2024-02-05 17:32:04');
INSERT INTO `workflow_node` VALUES (24, '归档', '{\"body\": {\"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":false,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\"}, \"table\": {}, \"bodyType\": \"createConstraint,\", \"tableType\": \"\"}', 0, 3, '{\"89\": false, \"90\": false, \"91\": false, \"92\": false, \"93\": false, \"94\": false, \"95\": false, \"96\": false}', NULL, NULL, NULL, 6, 3, 1, '2024-02-05 17:32:09');

-- ----------------------------
-- Table structure for workflow_route
-- ----------------------------
DROP TABLE IF EXISTS `workflow_route`;
CREATE TABLE `workflow_route`  (
  `dataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '路径编号',
  `routeName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '路径名称',
  `workflowId` bigint(64) UNSIGNED NOT NULL COMMENT '所属流程编号',
  `startNodeId` bigint(64) UNSIGNED NOT NULL COMMENT '起点节点编号',
  `endNodeId` bigint(64) UNSIGNED NOT NULL COMMENT '终点节点编号',
  `viewNo` int(11) NOT NULL COMMENT '显示顺序',
  `enterCondition` json NULL COMMENT '进入条件',
  `routeAction` json NULL COMMENT '路径操作',
  `creator` bigint(64) UNSIGNED NOT NULL COMMENT '创建人唯一id',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`dataId`) USING BTREE,
  INDEX `route_node_start_index`(`startNodeId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '路径表单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of workflow_route
-- ----------------------------
INSERT INTO `workflow_route` VALUES (1, '创建-上级审批', 1, 1, 2, 1, NULL, NULL, 1, '2024-02-05 11:37:42');
INSERT INTO `workflow_route` VALUES (2, '上级审批-分部领导审批', 1, 2, 3, 2, NULL, NULL, 1, '2024-02-05 11:38:22');
INSERT INTO `workflow_route` VALUES (3, '分部领导-公司领导审批', 1, 3, 4, 3, '{\"tasks\": [], \"classNames\": [\"org.eoa.projectbudget.extension.SaleFlowCheckWeatherCompany\"]}', NULL, 1, '2024-02-05 11:38:55');
INSERT INTO `workflow_route` VALUES (4, '分部领导-预决算部', 1, 3, 5, 4, NULL, NULL, 1, '2024-02-05 11:40:52');
INSERT INTO `workflow_route` VALUES (5, '公司领导-预决算部', 1, 4, 5, 5, NULL, NULL, 1, '2024-02-05 11:41:09');
INSERT INTO `workflow_route` VALUES (6, '预决算部-归档', 1, 5, 6, 6, NULL, NULL, 1, '2024-02-05 11:41:28');
INSERT INTO `workflow_route` VALUES (7, '领导审批', 2, 7, 8, 1, NULL, NULL, 1, '2024-02-05 16:14:57');
INSERT INTO `workflow_route` VALUES (8, '通知项目经理', 2, 8, 9, 2, NULL, NULL, 1, '2024-02-05 16:15:11');
INSERT INTO `workflow_route` VALUES (9, '归档', 2, 9, 10, 3, NULL, NULL, 1, '2024-02-05 16:15:19');
INSERT INTO `workflow_route` VALUES (10, '项目经理审批', 4, 15, 16, 1, NULL, NULL, 1, '2024-02-05 17:23:05');
INSERT INTO `workflow_route` VALUES (11, '预决算部超支审批', 4, 16, 17, 2, '{\"tasks\": [], \"classNames\": [\"org.eoa.projectbudget.extension.CheckOverUsed\"]}', NULL, 1, '2024-02-05 17:23:32');
INSERT INTO `workflow_route` VALUES (12, '直接归档', 4, 16, 18, 3, NULL, NULL, 1, '2024-02-05 17:23:49');
INSERT INTO `workflow_route` VALUES (13, '审批后归档', 4, 17, 18, 4, NULL, NULL, 1, '2024-02-05 17:24:00');
INSERT INTO `workflow_route` VALUES (14, '预决算部审批', 5, 19, 20, 1, NULL, NULL, 1, '2024-02-05 17:28:26');
INSERT INTO `workflow_route` VALUES (15, '归档', 5, 20, 21, 2, NULL, NULL, 1, '2024-02-05 17:28:33');
INSERT INTO `workflow_route` VALUES (16, '预决算部审批', 6, 22, 23, 1, NULL, NULL, 1, '2024-02-05 17:32:28');
INSERT INTO `workflow_route` VALUES (17, '归档', 6, 23, 24, 2, NULL, NULL, 1, '2024-02-05 17:32:54');
INSERT INTO `workflow_route` VALUES (18, '项目经理分配预算', 3, 11, 12, 1, NULL, NULL, 1, '2024-02-06 17:10:31');
INSERT INTO `workflow_route` VALUES (19, '预决算部审批', 3, 12, 13, 2, '{\"tasks\": [], \"classNames\": [\"org.eoa.projectbudget.extension.CheckOverUsed\"]}', NULL, 1, '2024-02-06 17:10:44');
INSERT INTO `workflow_route` VALUES (20, '归档', 3, 13, 14, 3, NULL, NULL, 1, '2024-02-06 17:15:26');
INSERT INTO `workflow_route` VALUES (21, '未超支归档', 3, 12, 14, 4, NULL, NULL, 1, '2024-03-06 16:28:53');

-- ----------------------------
-- View structure for human_authority_list
-- ----------------------------
DROP VIEW IF EXISTS `human_authority_list`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `human_authority_list` AS (select distinct `t`.`humanId` AS `humanId`,`t`.`authorityId` AS `authorityId` from ((select `hc`.`humanId` AS `humanId`,`ac`.`authorityId` AS `authorityId` from (`eoa_build`.`character_human` `hc` left join `eoa_build`.`authority_character` `ac` on((`hc`.`characterId` = `ac`.`characterId`)))) union (select `eoa_build`.`authority_human`.`humanId` AS `humanId`,`eoa_build`.`authority_human`.`authorityId` AS `authorityId` from `eoa_build`.`authority_human`)) `t`);

-- ----------------------------
-- View structure for human_view
-- ----------------------------
DROP VIEW IF EXISTS `human_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `human_view` AS (select `human_resource`.`dataId` AS `dataId`,`human_resource`.`loginName` AS `loginName`,`human_resource`.`password` AS `password`,`human_resource`.`name` AS `name`,`human_resource`.`sex` AS `sex`,`human_resource`.`birth` AS `birth`,`human_resource`.`telephone` AS `telephone`,`human_resource`.`mail` AS `mail`,`human_resource`.`phone` AS `phone`,`human_resource`.`fax` AS `fax`,`human_resource`.`workCode` AS `workCode`,`human_resource`.`section` AS `section`,`human_resource`.`depart` AS `depart`,`human_resource`.`job` AS `job`,`human_resource`.`directorLeader` AS `directorLeader`,`human_resource`.`supporter` AS `supporter`,`human_resource`.`photo` AS `photo`,`human_resource`.`signature` AS `signature`,`human_resource`.`lastLogin` AS `lastLogin`,`human_resource`.`safety` AS `safety`,`human_resource`.`isDeprecated` AS `isDeprecated`,timestampdiff(YEAR,`human_resource`.`birth`,curdate()) AS `age` from `human_resource`);

-- ----------------------------
-- View structure for module_view
-- ----------------------------
DROP VIEW IF EXISTS `module_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `module_view` AS (select `module_type`.`moduleTypeId` AS `moduleTypeId`,`module_type`.`moduleTypeName` AS `moduleTypeName`,`module_type`.`workflowRemark` AS `workflowRemark`,`module_type`.`creator` AS `creator`,`module_type`.`createTime` AS `createTime`,1 AS `tableCounts`,1 AS `flowCounts`,1 AS `searchCounts`,1 AS `chartsCounts` from `module_type`);

-- ----------------------------
-- View structure for request_back_human
-- ----------------------------
DROP VIEW IF EXISTS `request_back_human`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `request_back_human` AS select `request_backlog`.`humanId` AS `humanId`,`request_backlog`.`requestId` AS `requestId`,`request_backlog`.`nodeId` AS `nodeId`,`request_backlog`.`arriveTime` AS `arriveTime`,`request_backlog`.`workflowId` AS `workflowId`,`human_resource`.`dataId` AS `dataId`,`human_resource`.`loginName` AS `loginName`,`human_resource`.`password` AS `password`,`human_resource`.`name` AS `name`,`human_resource`.`sex` AS `sex`,`human_resource`.`birth` AS `birth`,`human_resource`.`telephone` AS `telephone`,`human_resource`.`mail` AS `mail`,`human_resource`.`phone` AS `phone`,`human_resource`.`fax` AS `fax`,`human_resource`.`workCode` AS `workCode`,`human_resource`.`section` AS `section`,`human_resource`.`depart` AS `depart`,`human_resource`.`job` AS `job`,`human_resource`.`directorLeader` AS `directorLeader`,`human_resource`.`supporter` AS `supporter`,`human_resource`.`photo` AS `photo`,`human_resource`.`signature` AS `signature`,`human_resource`.`lastLogin` AS `lastLogin`,`human_resource`.`safety` AS `safety`,`human_resource`.`isDeprecated` AS `isDeprecated` from (`request_backlog` left join `human_resource` on((`request_backlog`.`humanId` = `human_resource`.`dataId`)));

-- ----------------------------
-- View structure for request_back_log_view
-- ----------------------------
DROP VIEW IF EXISTS `request_back_log_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `request_back_log_view` AS (select `r`.`requestId` AS `requestId`,`r`.`dataId` AS `dataId`,`r`.`workflowId` AS `workflowId`,`r`.`currentNode` AS `currentNode`,`r`.`doneHistory` AS `doneHistory`,`r`.`requestTitle` AS `requestTitle`,`r`.`requestStatus` AS `requestStatus`,`r`.`flowHistory` AS `flowHistory`,`r`.`submitTime` AS `submitTime`,`r`.`finishTime` AS `finishTime`,`r`.`creator` AS `creator`,`request_backlog`.`humanId` AS `humanId`,`request_backlog`.`arriveTime` AS `arriveTime`,`request_backlog`.`nodeId` AS `nodeId` from (`request_backlog` left join `request` `r` on((`request_backlog`.`requestId` = `r`.`requestId`))));

-- ----------------------------
-- View structure for request_done_view
-- ----------------------------
DROP VIEW IF EXISTS `request_done_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `request_done_view` AS (select `r`.`requestId` AS `requestId`,`r`.`dataId` AS `dataId`,`r`.`workflowId` AS `workflowId`,`r`.`currentNode` AS `currentNode`,`r`.`doneHistory` AS `doneHistory`,`r`.`requestTitle` AS `requestTitle`,`r`.`requestStatus` AS `requestStatus`,`r`.`flowHistory` AS `flowHistory`,`r`.`submitTime` AS `submitTime`,`r`.`finishTime` AS `finishTime`,`r`.`creator` AS `creator`,`request_done`.`humanId` AS `humanId`,`request_done`.`doneTime` AS `doneTime`,`request_done`.`nodeId` AS `nodeId` from (`request_done` left join `request` `r` on((`request_done`.`requestId` = `r`.`requestId`))));

-- ----------------------------
-- View structure for request_view_authority
-- ----------------------------
DROP VIEW IF EXISTS `request_view_authority`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `request_view_authority` AS (select `t`.`requestId` AS `requestId`,`t`.`humanId` AS `humanId`,`t`.`nodeId` AS `nodeId`,`t`.`time` AS `time` from ((select `request_done_view`.`requestId` AS `requestId`,`request_done_view`.`humanId` AS `humanId`,`request_done_view`.`nodeId` AS `nodeId`,`request_done_view`.`doneTime` AS `time` from `eoa_build`.`request_done_view`) union (select `request_back_log_view`.`requestId` AS `requestId`,`request_back_log_view`.`humanId` AS `humanId`,`request_back_log_view`.`nodeId` AS `nodeId`,(`request_back_log_view`.`arriveTime` + interval 1 second) AS `time` from `eoa_build`.`request_back_log_view`)) `t` order by `t`.`time` desc);

-- ----------------------------
-- View structure for ysxx_use_record
-- ----------------------------
DROP VIEW IF EXISTS `ysxx_use_record`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `ysxx_use_record` AS select (((unix_timestamp(`userecord`.`createTime`) * 30) + (`userecord`.`detailDataId` * 10)) + `userecord`.`type`) AS `dataId`,`ysxx`.`htbh` AS `htbh`,`ysxx`.`xmmc` AS `xmmc`,`ysxx`.`xmjl` AS `xmjl`,`ysxx`.`ysmc` AS `ysmc`,`userecord`.`je` AS `je`,`userecord`.`type` AS `type`,`userecord`.`createTime` AS `createTime` from (((select `dt`.`detailDataId` AS `detailDataId`,`dt`.`ysx` AS `ysx`,`dt`.`bcje` AS `je`,0 AS `type`,`main`.`createTime` AS `createTime` from (`eoa_build`.`form_table_3_dt_2` `dt` left join `eoa_build`.`form_table_3` `main` on((`dt`.`detailMainId` = `main`.`dataId`))) where (`main`.`fpqk` = 1)) union (select `dt`.`detailDataId` AS `detailDataId`,`dt`.`ysx` AS `ysx`,`dt`.`bcje` AS `je`,1 AS `type`,`main`.`createTime` AS `createTime` from (`eoa_build`.`form_table_4_dt_2` `dt` left join `eoa_build`.`form_table_4` `main` on((`dt`.`detailMainId` = `main`.`dataId`))) where (`main`.`fpqk` = 1)) union (select `dt`.`detailDataId` AS `detailDataId`,`dt`.`ysx` AS `ysx`,`dt`.`tzje` AS `je`,2 AS `type`,`main`.`createTime` AS `createTime` from (`eoa_build`.`form_table_6_dt_1` `dt` left join `eoa_build`.`form_table_6` `main` on((`dt`.`detailMainId` = `main`.`dataId`))) where (`main`.`tzqk` = 1))) `userecord` left join (select `dt`.`detailDataId` AS `detailDataId`,`dt`.`ysmc` AS `ysmc`,`main`.`dataId` AS `xmmc`,`main`.`xmjl` AS `xmjl`,`main`.`htbh` AS `htbh` from (`eoa_build`.`form_table_2_dt_1` `dt` left join `eoa_build`.`form_table_2` `main` on((`dt`.`detailMainId` = `main`.`dataId`)))) `ysxx` on((`userecord`.`ysx` = `ysxx`.`detailDataId`)));

-- ----------------------------
-- View structure for ysxx_use_statistics
-- ----------------------------
DROP VIEW IF EXISTS `ysxx_use_statistics`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `ysxx_use_statistics` AS select `ysxx`.`detailDataId` AS `dataId`,`ysxx`.`ysmc` AS `ysmc`,`ysxx`.`xmmc` AS `xmmc`,`ysxx`.`xmjl` AS `xmjl`,`ysxx`.`htbh` AS `htbh`,`ysxx`.`jdje` AS `jdje`,ifnull(`yssyqk`.`ysyje`,0) AS `ysyje`,if((`ysxx`.`jdje` >= ifnull(`yssyqk`.`ysyje`,0)),0,1) AS `sfcz` from (((select `dt`.`detailDataId` AS `detailDataId`,concat(`main`.`xmmc`,'-',`dt`.`ysmc`) AS `ysmc`,`main`.`dataId` AS `xmmc`,`dt`.`jdje` AS `jdje`,`main`.`xmjl` AS `xmjl`,`main`.`htbh` AS `htbh` from (`eoa_build`.`form_table_2_dt_1` `dt` left join `eoa_build`.`form_table_2` `main` on((`dt`.`detailMainId` = `main`.`dataId`))))) `ysxx` left join (select `userecord`.`ysx` AS `ysx`,sum(`userecord`.`je`) AS `ysyje` from ((select `dt`.`ysx` AS `ysx`,`dt`.`bcje` AS `je` from (`eoa_build`.`form_table_3_dt_2` `dt` left join `eoa_build`.`form_table_3` `main` on((`dt`.`detailMainId` = `main`.`dataId`))) where (`main`.`fpqk` = 1)) union (select `dt`.`ysx` AS `ysx`,`dt`.`bcje` AS `je` from (`eoa_build`.`form_table_4_dt_2` `dt` left join `eoa_build`.`form_table_4` `main` on((`dt`.`detailMainId` = `main`.`dataId`))) where (`main`.`fpqk` = 1)) union (select `dt`.`ysx` AS `ysx`,`dt`.`tzje` AS `je` from (`eoa_build`.`form_table_6_dt_1` `dt` left join `eoa_build`.`form_table_6` `main` on((`dt`.`detailMainId` = `main`.`dataId`))) where (`main`.`tzqk` = 1))) `userecord` group by `userecord`.`ysx`) `yssyqk` on((`ysxx`.`detailDataId` = `yssyqk`.`ysx`)));

-- ----------------------------
-- Triggers structure for table request
-- ----------------------------
DROP TRIGGER IF EXISTS `request_BEFORE_INSERT`;
delimiter ;;
CREATE TRIGGER `request_BEFORE_INSERT` BEFORE INSERT ON `request` FOR EACH ROW BEGIN
    set new.requestStatus = (select nodeType from workflow_node where workflow_node.dataId = new.currentNode);
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table request
-- ----------------------------
DROP TRIGGER IF EXISTS `request_BEFORE_UPDATE`;
delimiter ;;
CREATE TRIGGER `request_BEFORE_UPDATE` BEFORE UPDATE ON `request` FOR EACH ROW BEGIN
    if NEW.currentNode != OLD.currentNode then
        set new.requestStatus = (select nodeType from workflow_node where workflow_node.dataId = new.currentNode);
    end if;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
