
INSERT INTO `character` VALUES (11, '采购', '项目预算管理角色', '2024-01-26 13:39:36', 1);
INSERT INTO `character` VALUES (12, '销售', '项目预算管理角色', '2024-01-26 13:40:11', 1);
INSERT INTO `character` VALUES (13, '总经理', '项目预算管理角色', '2024-01-26 13:40:38', 1);
INSERT INTO `character` VALUES (14, '项目经理', '项目预算管理角色', '2024-01-26 13:40:49', 1);
INSERT INTO `character` VALUES (15, '预决算工程师', '项目预算管理角色', '2024-01-26 13:41:03', 1);
INSERT INTO `character` VALUES (16, '分部领导', '项目预算管理角色', '2024-02-05 11:24:45', 1);


INSERT INTO `character_human` VALUES (3, 13, 0);
INSERT INTO `character_human` VALUES (5, 15, 0);
INSERT INTO `character_human` VALUES (7, 14, 0);
INSERT INTO `character_human` VALUES (9, 14, 0);
INSERT INTO `character_human` VALUES (11, 12, 0);
INSERT INTO `character_human` VALUES (12, 11, 0);


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
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表单' ROW_FORMAT = Dynamic;

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


-- ----------------------------
-- Records of form_table_6_dt_1
-- ----------------------------

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
INSERT INTO `human_resource` VALUES (1, 'sysadmin', 'eoa', '系统管理员', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, '系统管理员', 0, 0, 0, NULL, '2024-02-07 17:36:51', 0, 0);
INSERT INTO `human_resource` VALUES (2, 'tourist', 'eoa', '游客用户', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, '游客', 1, 0, 0, NULL, NULL, 0, 0);
INSERT INTO `human_resource` VALUES (3, 'ywy', 'eoa', '杨文元', 0, NULL, NULL, NULL, NULL, NULL, 'W001', 1, 1, '总经理', 0, 0, 0, NULL, NULL, 0, 0);
INSERT INTO `human_resource` VALUES (4, 'wzc', 'eoa', '王晨志', 0, NULL, NULL, NULL, NULL, NULL, 'W002', 2, 1, '法务', 3, 0, 0, NULL, NULL, 0, 0);
INSERT INTO `human_resource` VALUES (5, 'ly', 'eoa', '李妍', 1, NULL, NULL, NULL, NULL, NULL, 'W003', 3, 1, '预决算工程师', 3, 0, 0, NULL, NULL, 0, 0);
INSERT INTO `human_resource` VALUES (6, 'xp', 'eoa', '徐平', 0, NULL, NULL, NULL, NULL, NULL, 'W004', 2, 4, '项目人员', 3, 0, 0, NULL, NULL, 0, 0);
INSERT INTO `human_resource` VALUES (7, 'xj', 'eoa', '赵静', 1, NULL, NULL, NULL, NULL, NULL, 'W005', 2, 5, '项目经理', 3, 0, 0, NULL, NULL, 0, 0);
INSERT INTO `human_resource` VALUES (8, 'lcg', 'eoa', '刘长庚', 0, NULL, NULL, NULL, NULL, NULL, 'W006', 3, 6, '项目人员', 3, 0, 0, NULL, NULL, 0, 0);
INSERT INTO `human_resource` VALUES (9, 'fl', 'eoa', '付蕾', 1, NULL, NULL, NULL, NULL, NULL, 'W007', 3, 6, '项目经理', 3, 0, 0, NULL, NULL, 0, 0);
INSERT INTO `human_resource` VALUES (10, 'ml', 'eoa', '孟玲', 1, NULL, NULL, NULL, NULL, NULL, 'W008', 4, 7, '研发人员', 3, 0, 0, NULL, NULL, 0, 0);
INSERT INTO `human_resource` VALUES (11, 'lch', 'eoa', '李长昊', 0, NULL, NULL, NULL, NULL, NULL, 'W009', 5, 9, '销售', 3, 0, 0, NULL, NULL, 0, 0);
INSERT INTO `human_resource` VALUES (12, 'fzc', 'eoa', '范成志', 0, NULL, NULL, NULL, NULL, NULL, 'W010', 5, 10, '采购', 3, 0, 0, NULL, NULL, 0, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '页面菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu_base
-- ----------------------------
INSERT INTO `menu_base` VALUES (1, '主页', NULL, NULL, 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-01-24 13:33:10');
INSERT INTO `menu_base` VALUES (2, '组织结构', NULL, NULL, 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-01-24 13:33:10');
INSERT INTO `menu_base` VALUES (3, '知识目录', NULL, NULL, 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-01-24 13:33:10');
INSERT INTO `menu_base` VALUES (4, '工作流程', NULL, NULL, 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-01-24 13:33:10');
INSERT INTO `menu_base` VALUES (5, '公司主页', 1, '/main', 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-01-24 13:33:10');
INSERT INTO `menu_base` VALUES (6, '组织结构树', 2, '/organization', 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-01-24 13:33:10');
INSERT INTO `menu_base` VALUES (7, '分部列表', 2, '/section', 2, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-01-24 13:33:10');
INSERT INTO `menu_base` VALUES (8, '部门列表', 2, '/depart', 3, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-01-24 13:33:10');
INSERT INTO `menu_base` VALUES (9, '人员列表', 2, '/human_resource', 4, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-01-24 13:33:10');
INSERT INTO `menu_base` VALUES (10, '知识地图', 3, '/content/0', 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-01-24 13:33:10');
INSERT INTO `menu_base` VALUES (11, '待办请求', 4, '/request/backlog', 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-01-24 13:33:10');
INSERT INTO `menu_base` VALUES (12, '已办请求', 4, '/request/done', 2, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-01-24 13:33:10');
INSERT INTO `menu_base` VALUES (13, '我的请求', 4, '/request/self', 3, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-01-24 13:33:10');
INSERT INTO `menu_base` VALUES (14, '发起请求', 4, '/workflow', 4, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, '2024-01-24 13:33:10');

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '审批数据' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of request
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '分部表单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of section_resource
-- ----------------------------
INSERT INTO `section_resource` VALUES (1, '支撑服务分部', 'SERVE', '维森化工石油公司支撑服务分部', 0, NULL, NULL, '2024-01-25 17:10:40', NULL, 0);
INSERT INTO `section_resource` VALUES (2, '能源制造公司', 'ENERGY', '维森化工石油-能源制造公司', 0, NULL, NULL, '2024-01-25 17:19:26', NULL, 0);
INSERT INTO `section_resource` VALUES (3, '设备制造分部', 'EQUIPMENT', '维森化工石油-设备制造分部', 0, NULL, NULL, '2024-01-25 17:26:51', NULL, 0);
INSERT INTO `section_resource` VALUES (4, '研发分部', 'EQUIPMENT/RESEARCH', '维森化工石油公司设备制造研发分部', 3, NULL, NULL, '2024-01-26 11:39:41', NULL, 0);
INSERT INTO `section_resource` VALUES (5, '商务分部', 'SERVER/FINICAL', '维森集团商务分部', 1, NULL, NULL, '2024-01-26 13:26:57', NULL, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字段数据索引表（table_column_index）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of table_column_index
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '路径表单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of workflow_route
-- ----------------------------


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
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `ysxx_use_record` AS select ((unix_timestamp(`userecord`.`createTime`) * 3) + `userecord`.`type`) AS `dataId`,`ysxx`.`htbh` AS `htbh`,`ysxx`.`xmmc` AS `xmmc`,`ysxx`.`xmjl` AS `xmjl`,`ysxx`.`ysmc` AS `ysmc`,`userecord`.`je` AS `je`,`userecord`.`type` AS `type`,`userecord`.`createTime` AS `createTime` from (((select `dt`.`detailDataId` AS `detailDataId`,`dt`.`ysx` AS `ysx`,`dt`.`bcje` AS `je`,0 AS `type`,`main`.`createTime` AS `createTime` from (`eoa_build`.`form_table_3_dt_2` `dt` left join `eoa_build`.`form_table_3` `main` on((`dt`.`detailMainId` = `main`.`dataId`))) where (`main`.`fpqk` = 1)) union (select `dt`.`detailDataId` AS `detailDataId`,`dt`.`ysx` AS `ysx`,`dt`.`bcje` AS `je`,1 AS `type`,`main`.`createTime` AS `createTime` from (`eoa_build`.`form_table_4_dt_2` `dt` left join `eoa_build`.`form_table_4` `main` on((`dt`.`detailMainId` = `main`.`dataId`))) where (`main`.`fpqk` = 1)) union (select `dt`.`detailDataId` AS `detailDataId`,`dt`.`ysx` AS `ysx`,`dt`.`tzje` AS `je`,2 AS `type`,`main`.`createTime` AS `createTime` from (`eoa_build`.`form_table_6_dt_1` `dt` left join `eoa_build`.`form_table_6` `main` on((`dt`.`detailMainId` = `main`.`dataId`))) where (`main`.`tzqk` = 1))) `userecord` left join (select `dt`.`detailDataId` AS `detailDataId`,`dt`.`ysmc` AS `ysmc`,`main`.`dataId` AS `xmmc`,`main`.`xmjl` AS `xmjl`,`main`.`htbh` AS `htbh` from (`eoa_build`.`form_table_2_dt_1` `dt` left join `eoa_build`.`form_table_2` `main` on((`dt`.`detailMainId` = `main`.`dataId`)))) `ysxx` on((`userecord`.`detailDataId` = `ysxx`.`detailDataId`)));

-- ----------------------------
-- View structure for ysxx_use_statistics
-- ----------------------------
DROP VIEW IF EXISTS `ysxx_use_statistics`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `ysxx_use_statistics` AS select `ysxx`.`detailDataId` AS `dataId`,`ysxx`.`ysmc` AS `ysmc`,`ysxx`.`xmmc` AS `xmmc`,`ysxx`.`xmjl` AS `xmjl`,`ysxx`.`htbh` AS `htbh`,`ysxx`.`jdje` AS `jdje`,ifnull(`yssyqk`.`ysyje`,0) AS `ysyje`,if((`ysxx`.`jdje` >= ifnull(`yssyqk`.`ysyje`,0)),0,1) AS `sfcz` from (((select `dt`.`detailDataId` AS `detailDataId`,`dt`.`ysmc` AS `ysmc`,`main`.`dataId` AS `xmmc`,`dt`.`jdje` AS `jdje`,`main`.`xmjl` AS `xmjl`,`main`.`htbh` AS `htbh` from (`eoa_build`.`form_table_2_dt_1` `dt` left join `eoa_build`.`form_table_2` `main` on((`dt`.`detailMainId` = `main`.`dataId`))))) `ysxx` left join (select `userecord`.`ysx` AS `ysx`,sum(`userecord`.`je`) AS `ysyje` from ((select `dt`.`ysx` AS `ysx`,`dt`.`bcje` AS `je` from (`eoa_build`.`form_table_3_dt_2` `dt` left join `eoa_build`.`form_table_3` `main` on((`dt`.`detailMainId` = `main`.`dataId`))) where (`main`.`fpqk` = 1)) union (select `dt`.`ysx` AS `ysx`,`dt`.`bcje` AS `je` from (`eoa_build`.`form_table_4_dt_2` `dt` left join `eoa_build`.`form_table_4` `main` on((`dt`.`detailMainId` = `main`.`dataId`))) where (`main`.`fpqk` = 1)) union (select `dt`.`ysx` AS `ysx`,`dt`.`tzje` AS `je` from (`eoa_build`.`form_table_6_dt_1` `dt` left join `eoa_build`.`form_table_6` `main` on((`dt`.`detailMainId` = `main`.`dataId`))) where (`main`.`tzqk` = 1))) `userecord` group by `userecord`.`ysx`) `yssyqk` on((`ysxx`.`detailDataId` = `yssyqk`.`ysx`)));

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
