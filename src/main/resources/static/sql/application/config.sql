INSERT INTO `eoa_build`.`module_type` (`moduleTypeId`, `moduleTypeName`, `workflowRemark`, `creator`)
VALUES ('6', '项目预算', '毕设展示运行项目-化工生产建设项目预算管控应用', '1');
-- 应用添加

INSERT INTO `section_resource` (`dataId`, `sectionName`, `sectionCode`, `fullName`, `belongSection`, `sectionManager`,
                                `sectionIntroduction`, `createTime`, `photo`, `isDeprecated`)
VALUES (1, '支撑服务分部', 'SERVE', '维森化工石油公司支撑服务分部', 0, NULL, NULL, now(), NULL, 0),
       (2, '能源制造公司', 'ENERGY', '维森化工石油-能源制造公司', 0, NULL, NULL, now(), NULL, 0),
       (3, '设备制造分部', 'EQUIPMENT', '维森化工石油-设备制造分部', 0, NULL, NULL, now(), NULL, 0),
       (4, '研发分部', 'EQUIPMENT/RESEARCH', '维森化工石油公司设备制造研发分部', 3, NULL, NULL, now(), NULL, 0),
       (5, '商务分部', 'SERVER/FINICAL', '维森集团商务分部', 1, NULL, NULL, now(), NULL, 0);

INSERT INTO `depart_resource` (`dataId`, `departName`, `departCode`, `fullName`, `belongDepart`, `belongSection`,
                               `departManager`, `departIntroduction`, `createTime`, `photo`, `isDeprecated`)
VALUES (1, '总经理室', 'SERVE-MANAGER', '总经理室', NULL, 1, NULL, NULL, now(), NULL, 0)
     , (2, '法务部', 'SERVE-LAW', '法务部', NULL, 1, NULL, NULL, now(), NULL, 0)
     , (3, '预决算部', 'SERVE-BUDGET', '预决算部', NULL, 1, NULL, NULL, now(), NULL, 0)
     , (4, '项目部', 'ENERGY-PROJECT', '项目部', NULL, 2, NULL, NULL, now(), NULL, 0)
     , (5, '质量部', 'ENERGY-QUALITY', '质量部', NULL, 2, NULL, NULL, now(), NULL, 0)
     , (6, '项目部', 'EQUIPMENT-PROJECT', '项目部', NULL, 3, NULL, NULL, now(), NULL, 0)
     , (7, '质量部', 'EQUIPMENT-QUALITY', '质量部', NULL, 3, NULL, NULL, now(), NULL, 0)
     , (8, '研发部', 'EQUIPMENT/RESEARCH-DEVELOP', '研发部', NULL, 4, NULL, NULL, now(), NULL, 0)
     , (9, '销售部', 'FINICAL-SALE', '销售部', NULL, 5, NULL, NULL, now(), NULL, 0)
     , (10, '采购部', 'FINICAL-PURCHASE', '采购部', NULL, 5, NULL, NULL, now(), NULL, 0);

INSERT INTO `human_resource` (dataId, loginName, `password`, `name`, sex, birth, telephone, mail, phone, fax, workCode,
                              depart, section, job, directorLeader, supporter, photo, signature, lastLogin, safety)
VALUES (3, 'ywy', 'eoa', '杨文元', 0, null, null, null, null, null, 'W001', 1, 1, '总经理', 0, 0, 0, null, null, 0),
       (4, 'wzc', 'eoa', '王晨志', 0, null, null, null, null, null, 'W002', 1, 2, '法务', 3, 0, 0, null, null, 0),
       (5, 'ly', 'eoa', '李妍', 1, null, null, null, null, null, 'W003', 1, 3, '预决算工程师', 3, 0, 0, null, null, 0),
       (6, 'xp', 'eoa', '徐平', 0, null, null, null, null, null, 'W004', 4, 2, '项目人员', 3, 0, 0, null, null, 0),
       (7, 'xj', 'eoa', '赵静', 1, null, null, null, null, null, 'W005', 5, 2, '项目经理', 3, 0, 0, null, null, 0),
       (8, 'lcg', 'eoa', '刘长庚', 0, null, null, null, null, null, 'W006', 6, 3, '项目人员', 3, 0, 0, null, null, 0),
       (9, 'fl', 'eoa', '付蕾', 1, null, null, null, null, null, 'W007', 6, 3, '项目经理', 3, 0, 0, null, null, 0),
       (10, 'ml', 'eoa', '孟玲', 1, null, null, null, null, null, 'W008', 7, 4, '研发人员', 3, 0, 0, null, null, 0),
       (11, 'lch', 'eoa', '李长昊', 0, null, null, null, null, null, 'W009', 9, 5, '销售', 3, 0, 0, null, null, 0),
       (12, 'fzc', 'eoa', '范成志', 0, null, null, null, null, null, 'W010', 10, 5, '采购', 3, 0, 0, null, null, 0);

INSERT INTO `character` (`dataId`, `characterName`, `characterDescription`, `createTime`, `creator`)
VALUES (11, '采购', '项目预算管理角色', now(), 1)
     , (12, '销售', '项目预算管理角色', now(), 1)
     , (13, '总经理', '项目预算管理角色', now(), 1)
     , (14, '项目经理', '项目预算管理角色', now(), 1)
     , (15, '预决算工程师', '项目预算管理角色', now(), 1);

insert into `character_human` (`humanId`, `characterId`, `grade`)
values (11, 12, 0),
       (12, 11, 0),
       (3, 13, 0),
       (7, 14, 0),
       (9, 14, 0),
       (5, 15, 0);

INSERT INTO `module_type` (`moduleTypeId`, `moduleTypeName`, `workflowRemark`, `creator`)
VALUES ('6', '项目预算', '项目预算管理应用', '1');

Insert Into `content_list` (`dataId`, `isDeprecated`, `contentName`, `contentRemark`, `creator`, `createTime`,
                            `defaultEdit`, `defaultCreate`, `defaultDelete`, `defaultShare`, `leadContent`,
                            `defaultView`)
VALUES (5, 0, '合同管理', NULL, 1, '2024-01-26 16:11:23', NULL, '{
  \"body\": {
    \"allConstraint\": \"{\\\"start\\\":100,\\\"end\\\":0}\"
  },
  \"table\": {},
  \"bodyType\": \"allConstraint,\",
  \"tableType\": \"\"
}', NULL, '{
  \"body\": {
    \"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":true,\\\"leaderRecursion\\\":true,\\\"depart\\\":false,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\",
    \"characterConstraint\": \"[{\\\"characterId\\\":13,\\\"grade\\\":2}]\"
  },
  \"table\": {},
  \"bodyType\": \"createConstraint,characterConstraint,\",
  \"tableType\": \"\"
}', 0, '{
  \"body\": {
    \"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"
  },
  \"table\": {},
  \"bodyType\": \"allConstraint,\",
  \"tableType\": \"\"
}'),
       (6, 0, '发票管理', NULL, 1, '2024-01-26 16:11:37', NULL, NULL, NULL, '{
         \"body\": {
           \"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":true,\\\"leaderRecursion\\\":true,\\\"depart\\\":false,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\"
         },
         \"table\": {},
         \"bodyType\": \"createConstraint,\",
         \"tableType\": \"\"
       }', 0, '{
         \"body\": {
           \"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"
         },
         \"table\": {},
         \"bodyType\": \"allConstraint,\",
         \"tableType\": \"\"
       }'),
       (7, 0, '销售合同', NULL, 1, '2024-01-26 16:50:53', NULL, NULL, NULL, '{
         \"body\": {
           \"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":true,\\\"leaderRecursion\\\":true,\\\"depart\\\":false,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\",
           \"characterConstraint\": \"[{\\\"characterId\\\":13,\\\"grade\\\":0},{\\\"characterId\\\":12,\\\"grade\\\":0}]\"
         },
         \"table\": {},
         \"bodyType\": \"createConstraint,characterConstraint,\",
         \"tableType\": \"\"
       }', 5, '{
         \"body\": {
           \"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\",
           \"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":true,\\\"leaderRecursion\\\":true,\\\"depart\\\":false,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\",
           \"characterConstraint\": \"[]\"
         },
         \"table\": {},
         \"bodyType\": \"allConstraint,createConstraint,characterConstraint,\",
         \"tableType\": \"\"
       }'),
       (8, 0, '采购合同', NULL, 1, '2024-01-26 16:51:04', NULL, NULL, NULL, '{
         \"body\": {
           \"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":true,\\\"leaderRecursion\\\":true,\\\"depart\\\":false,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\",
           \"characterConstraint\": \"[{\\\"characterId\\\":11,\\\"grade\\\":0},{\\\"characterId\\\":13,\\\"grade\\\":0}]\"
         },
         \"table\": {},
         \"bodyType\": \"createConstraint,characterConstraint,\",
         \"tableType\": \"\"
       }', 5, '{
         \"body\": {
           \"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"
         },
         \"table\": {},
         \"bodyType\": \"allConstraint,\",
         \"tableType\": \"\"
       }');

CREATE TABLE `form_table_1`
(
    `dataId`          bigint(64) UNSIGNED                                       NOT NULL AUTO_INCREMENT,
    `requestId`       bigint(64)                                                NULL DEFAULT NULL,
    `requestStatus`   int(11)                                                   NULL DEFAULT NULL,
    `creator`         bigint(64)                                                NULL DEFAULT NULL,
    `createTime`      datetime                                                  NULL DEFAULT NULL,
    `lastEditTime`    datetime                                                  NULL DEFAULT NULL,
    `editAuthority`   json                                                      NULL,
    `viewAuthority`   json                                                      NULL,
    `deleteAuthority` json                                                      NULL,
    `xsry`            bigint(64) UNSIGNED                                       NULL DEFAULT NULL,
    `xssj`            datetime                                                  NULL DEFAULT NULL,
    `xsbm`            bigint(64) UNSIGNED                                       NULL DEFAULT NULL,
    `xsfb`            bigint(64) UNSIGNED                                       NULL DEFAULT NULL,
    `htbh`            varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL,
    `htmc`            varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL,
    `yxqdsj`          datetime                                                  NULL DEFAULT NULL,
    `yjqssj`          datetime                                                  NULL DEFAULT NULL,
    `xdfgsmc`         varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL,
    `xdfgsyhzh`       varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL,
    `xdfgssh`         varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL,
    `xdfgsdz`         varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL,
    `xdfgslxr`        varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL,
    `xdfgslxfs`       varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL,
    `xsje`            decimal(38, 2)                                            NULL DEFAULT NULL,
    `xsbz`            int(11)                                                   NULL DEFAULT NULL,
    `hl`              decimal(38, 2)                                            NULL DEFAULT NULL,
    `sl`              decimal(38, 2)                                            NULL DEFAULT NULL,
    `hjrmbje`         decimal(38, 2)                                            NULL DEFAULT NULL,
    `htfj`            varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`dataId`) USING BTREE,
    UNIQUE INDEX `dataId_UNIQUE` (`dataId`) USING BTREE,
    UNIQUE INDEX `requestId_UNIQUE` (`requestId`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

CREATE TABLE `form_table_1_dt_1`
(
    `detailDataId` bigint(64) UNSIGNED                                     NOT NULL AUTO_INCREMENT,
    `detailMainId` bigint(64)                                              NOT NULL,
    `fkxmc`        varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `fktj`         varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `yjfksj`       datetime                                                NULL DEFAULT NULL,
    `fkje`         decimal(38, 2)                                          NULL DEFAULT NULL,
    PRIMARY KEY (`detailDataId`) USING BTREE,
    UNIQUE INDEX `detailDataId_UNIQUE` (`detailDataId`) USING BTREE,
    INDEX `detailMainId_UNIQUE` (`detailMainId`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

CREATE TABLE `form_table_2`
(
    `dataId`          bigint(64) UNSIGNED                                       NOT NULL AUTO_INCREMENT,
    `requestId`       bigint(64)                                                NULL DEFAULT NULL,
    `requestStatus`   int(11)                                                   NULL DEFAULT NULL,
    `creator`         bigint(64)                                                NULL DEFAULT NULL,
    `createTime`      datetime                                                  NULL DEFAULT NULL,
    `lastEditTime`    datetime                                                  NULL DEFAULT NULL,
    `editAuthority`   json                                                      NULL,
    `viewAuthority`   json                                                      NULL,
    `deleteAuthority` json                                                      NULL,
    `xmbh`            varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL,
    `xmmc`            varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL,
    `xmlxsj`          datetime                                                  NULL DEFAULT NULL,
    `xmyjjssj`        datetime                                                  NULL DEFAULT NULL,
    `xmsjjssj`        datetime                                                  NULL DEFAULT NULL,
    `xmhst`           decimal(38, 2)                                            NULL DEFAULT NULL,
    `htbh`            bigint(64) UNSIGNED                                       NULL DEFAULT NULL,
    `xmjl`            bigint(64) UNSIGNED                                       NULL DEFAULT NULL,
    `zxfb`            bigint(64) UNSIGNED                                       NULL DEFAULT NULL,
    `xsje`            decimal(38, 2)                                            NULL DEFAULT NULL,
    `ysje`            decimal(38, 2)                                            NULL DEFAULT NULL,
    `sjzc`            decimal(38, 2)                                            NULL DEFAULT NULL,
    `bz`              varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `sfyl`            int(11)                                                   NULL DEFAULT NULL,
    `sfcz`            int(11)                                                   NULL DEFAULT NULL,
    `yglr`            decimal(38, 2)                                            NULL DEFAULT NULL,
    `sjlr`            decimal(38, 2)                                            NULL DEFAULT NULL,
    `zzmlfj`          varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`dataId`) USING BTREE,
    UNIQUE INDEX `dataId_UNIQUE` (`dataId`) USING BTREE,
    UNIQUE INDEX `requestId_UNIQUE` (`requestId`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

CREATE TABLE `form_table_2_dt_1`
(
    `detailDataId` bigint(64) UNSIGNED                                     NOT NULL AUTO_INCREMENT,
    `detailMainId` bigint(64)                                              NOT NULL,
    `ysmc`         varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `jdje`         decimal(38, 2)                                          NULL DEFAULT NULL,
    `yyje`         decimal(38, 2)                                          NULL DEFAULT NULL,
    `sfcz`         int(11)                                                 NULL DEFAULT NULL,
    PRIMARY KEY (`detailDataId`) USING BTREE,
    UNIQUE INDEX `detailDataId_UNIQUE` (`detailDataId`) USING BTREE,
    INDEX `detailMainId_UNIQUE` (`detailMainId`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

CREATE TABLE `form_table_3`
(
    `dataId`          bigint(64) UNSIGNED                                       NOT NULL AUTO_INCREMENT,
    `requestId`       bigint(64)                                                NULL DEFAULT NULL,
    `requestStatus`   int(11)                                                   NULL DEFAULT NULL,
    `creator`         bigint(64)                                                NULL DEFAULT NULL,
    `createTime`      datetime                                                  NULL DEFAULT NULL,
    `lastEditTime`    datetime                                                  NULL DEFAULT NULL,
    `editAuthority`   json                                                      NULL,
    `viewAuthority`   json                                                      NULL,
    `deleteAuthority` json                                                      NULL,
    `cgr`             bigint(64) UNSIGNED                                       NULL DEFAULT NULL,
    `cgsj`            datetime                                                  NULL DEFAULT NULL,
    `cgbh`            varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL,
    `cgmc`            varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL,
    `htfj`            varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `bz`              varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `xmmc`            bigint(64) UNSIGNED                                       NULL DEFAULT NULL,
    `xmjl`            bigint(64) UNSIGNED                                       NULL DEFAULT NULL,
    `cgje`            decimal(38, 2)                                            NULL DEFAULT NULL,
    `cgbz`            int(11)                                                   NULL DEFAULT NULL,
    `sl`              decimal(38, 2)                                            NULL DEFAULT NULL,
    `hl`              decimal(38, 2)                                            NULL DEFAULT NULL,
    `fkje`            decimal(38, 2)                                            NULL DEFAULT NULL,
    `fpqk`            int(11)                                                   NULL DEFAULT NULL,
    PRIMARY KEY (`dataId`) USING BTREE,
    UNIQUE INDEX `dataId_UNIQUE` (`dataId`) USING BTREE,
    UNIQUE INDEX `requestId_UNIQUE` (`requestId`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

CREATE TABLE `form_table_3_dt_1`
(
    `detailDataId` bigint(64) UNSIGNED                                      NOT NULL AUTO_INCREMENT,
    `detailMainId` bigint(64)                                               NOT NULL,
    `jfx`          varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `jftj`         varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `jfsj`         datetime                                                 NULL DEFAULT NULL,
    `jfnr`         varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `dyfk`         decimal(38, 2)                                           NULL DEFAULT NULL,
    PRIMARY KEY (`detailDataId`) USING BTREE,
    UNIQUE INDEX `detailDataId_UNIQUE` (`detailDataId`) USING BTREE,
    INDEX `detailMainId_UNIQUE` (`detailMainId`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

CREATE TABLE `form_table_3_dt_2`
(
    `detailDataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT,
    `detailMainId` bigint(64)          NOT NULL,
    `ysx`          bigint(64) UNSIGNED NULL DEFAULT NULL,
    `jdje`         decimal(38, 2)      NULL DEFAULT NULL,
    `yyje`         decimal(38, 2)      NULL DEFAULT NULL,
    `bcje`         decimal(38, 2)      NULL DEFAULT NULL,
    PRIMARY KEY (`detailDataId`) USING BTREE,
    UNIQUE INDEX `detailDataId_UNIQUE` (`detailDataId`) USING BTREE,
    INDEX `detailMainId_UNIQUE` (`detailMainId`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;


CREATE TABLE `form_table_4`
(
    `dataId`          bigint(64) UNSIGNED                                       NOT NULL AUTO_INCREMENT,
    `requestId`       bigint(64)                                                NULL DEFAULT NULL,
    `requestStatus`   int(11)                                                   NULL DEFAULT NULL,
    `creator`         bigint(64)                                                NULL DEFAULT NULL,
    `createTime`      datetime                                                  NULL DEFAULT NULL,
    `lastEditTime`    datetime                                                  NULL DEFAULT NULL,
    `editAuthority`   json                                                      NULL,
    `viewAuthority`   json                                                      NULL,
    `deleteAuthority` json                                                      NULL,
    `bxr`             bigint(64) UNSIGNED                                       NULL DEFAULT NULL,
    `bxsj`            datetime                                                  NULL DEFAULT NULL,
    `xmmc`            bigint(64) UNSIGNED                                       NULL DEFAULT NULL,
    `xmjl`            bigint(64) UNSIGNED                                       NULL DEFAULT NULL,
    `bxje`            decimal(38, 2)                                            NULL DEFAULT NULL,
    `bxsx`            varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `fpqk`            int(11)                                                   NULL DEFAULT NULL,
    PRIMARY KEY (`dataId`) USING BTREE,
    UNIQUE INDEX `dataId_UNIQUE` (`dataId`) USING BTREE,
    UNIQUE INDEX `requestId_UNIQUE` (`requestId`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

CREATE TABLE `form_table_4_dt_1`
(
    `detailDataId` bigint(64) UNSIGNED                                       NOT NULL AUTO_INCREMENT,
    `detailMainId` bigint(64)                                                NOT NULL,
    `bxnr`         varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL,
    `mxje`         decimal(38, 2)                                            NULL DEFAULT NULL,
    `mxfp`         varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`detailDataId`) USING BTREE,
    UNIQUE INDEX `detailDataId_UNIQUE` (`detailDataId`) USING BTREE,
    INDEX `detailMainId_UNIQUE` (`detailMainId`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

CREATE TABLE `form_table_4_dt_2`
(
    `detailDataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT,
    `detailMainId` bigint(64)          NOT NULL,
    `ysx`          bigint(64) UNSIGNED NULL DEFAULT NULL,
    `jdje`         decimal(38, 2)      NULL DEFAULT NULL,
    `yyje`         decimal(38, 2)      NULL DEFAULT NULL,
    `bcje`         decimal(38, 2)      NULL DEFAULT NULL,
    PRIMARY KEY (`detailDataId`) USING BTREE,
    UNIQUE INDEX `detailDataId_UNIQUE` (`detailDataId`) USING BTREE,
    INDEX `detailMainId_UNIQUE` (`detailMainId`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

CREATE TABLE `form_table_5`
(
    `dataId`          bigint(64) UNSIGNED                                      NOT NULL AUTO_INCREMENT,
    `requestId`       bigint(64)                                               NULL DEFAULT NULL,
    `requestStatus`   int(11)                                                  NULL DEFAULT NULL,
    `creator`         bigint(64)                                               NULL DEFAULT NULL,
    `createTime`      datetime                                                 NULL DEFAULT NULL,
    `lastEditTime`    datetime                                                 NULL DEFAULT NULL,
    `editAuthority`   json                                                     NULL,
    `viewAuthority`   json                                                     NULL,
    `deleteAuthority` json                                                     NULL,
    `xmmc`            bigint(64) UNSIGNED                                      NULL DEFAULT NULL,
    `xmjl`            bigint(64) UNSIGNED                                      NULL DEFAULT NULL,
    `tzsj`            datetime                                                 NULL DEFAULT NULL,
    `tzyy`            varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`dataId`) USING BTREE,
    UNIQUE INDEX `dataId_UNIQUE` (`dataId`) USING BTREE,
    UNIQUE INDEX `requestId_UNIQUE` (`requestId`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

CREATE TABLE `form_table_5_dt_1`
(
    `detailDataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT,
    `detailMainId` bigint(64)          NOT NULL,
    `ysx`          bigint(64) UNSIGNED NULL DEFAULT NULL,
    `yjdje`        decimal(38, 2)      NULL DEFAULT NULL,
    `xjdje`        decimal(38, 2)      NULL DEFAULT NULL,
    PRIMARY KEY (`detailDataId`) USING BTREE,
    UNIQUE INDEX `detailDataId_UNIQUE` (`detailDataId`) USING BTREE,
    INDEX `detailMainId_UNIQUE` (`detailMainId`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

CREATE TABLE `form_table_6`
(
    `dataId`          bigint(64) UNSIGNED                                      NOT NULL AUTO_INCREMENT,
    `requestId`       bigint(64)                                               NULL DEFAULT NULL,
    `requestStatus`   int(11)                                                  NULL DEFAULT NULL,
    `creator`         bigint(64)                                               NULL DEFAULT NULL,
    `createTime`      datetime                                                 NULL DEFAULT NULL,
    `lastEditTime`    datetime                                                 NULL DEFAULT NULL,
    `editAuthority`   json                                                     NULL,
    `viewAuthority`   json                                                     NULL,
    `deleteAuthority` json                                                     NULL,
    `xmmc`            bigint(64) UNSIGNED                                      NULL DEFAULT NULL,
    `xmjl`            bigint(64) UNSIGNED                                      NULL DEFAULT NULL,
    `tzsj`            datetime                                                 NULL DEFAULT NULL,
    `tzyy`            varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `tzqk`            int(11)                                                  NULL DEFAULT NULL,
    PRIMARY KEY (`dataId`) USING BTREE,
    UNIQUE INDEX `dataId_UNIQUE` (`dataId`) USING BTREE,
    UNIQUE INDEX `requestId_UNIQUE` (`requestId`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

CREATE TABLE `form_table_6_dt_1`
(
    `detailDataId` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT,
    `detailMainId` bigint(64)          NOT NULL,
    `ysx`          bigint(64) UNSIGNED NULL DEFAULT NULL,
    `jdje`         decimal(38, 2)      NULL DEFAULT NULL,
    `ysyje`        decimal(38, 2)      NULL DEFAULT NULL,
    `tzje`         decimal(38, 2)      NULL DEFAULT NULL,
    PRIMARY KEY (`detailDataId`) USING BTREE,
    UNIQUE INDEX `detailDataId_UNIQUE` (`detailDataId`) USING BTREE,
    INDEX `detailMainId_UNIQUE` (`detailMainId`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

INSERT INTO `menu_base`
VALUES (15, '项目预算', NULL, NULL, 2, 0, '{
  \"body\": {
    \"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"
  },
  \"table\": {},
  \"bodyType\": \"allConstraint,\",
  \"tableType\": \"\"
}', 1, '2024-02-06 14:21:25');
INSERT INTO `menu_base`
VALUES (16, '项目信息', 15, '', 1, 0, '{
  \"body\": {
    \"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"
  },
  \"table\": {},
  \"bodyType\": \"allConstraint,\",
  \"tableType\": \"\"
}', 1, '2024-02-06 14:30:44');
INSERT INTO `menu_base`
VALUES (17, '分配信息', 15, NULL, 3, 0, '{
  \"body\": {
    \"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"
  },
  \"table\": {},
  \"bodyType\": \"allConstraint,\",
  \"tableType\": \"\"
}', 1, '2024-02-06 14:37:40');
INSERT INTO `menu_base`
VALUES (18, '调整信息', 15, NULL, 4, 0, '{
  \"body\": {
    \"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"
  },
  \"table\": {},
  \"bodyType\": \"allConstraint,\",
  \"tableType\": \"\"
}', 1, '2024-02-06 14:55:08');
INSERT INTO `menu_base`
VALUES (19, '采购金额分配记录', 17, '/search/2', 1, 0, '{
  \"body\": {
    \"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"
  },
  \"table\": {},
  \"bodyType\": \"allConstraint,\",
  \"tableType\": \"\"
}', 1, '2024-02-06 15:00:43');
INSERT INTO `menu_base`
VALUES (20, '报销金额分配记录', 17, '/search/3', 2, 0, '{
  \"body\": {
    \"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"
  },
  \"table\": {},
  \"bodyType\": \"allConstraint,\",
  \"tableType\": \"\"
}', 1, '2024-02-06 15:01:38');
INSERT INTO `menu_base`
VALUES (21, '既定预算调整记录', 18, '/search/5', 1, 0, '{
  \"body\": {
    \"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"
  },
  \"table\": {},
  \"bodyType\": \"allConstraint,\",
  \"tableType\": \"\"
}', 1, '2024-02-06 15:31:09');
INSERT INTO `menu_base`
VALUES (22, '预算使用统计', 28, '/search/7', 1, 0, '{
  \"body\": {
    \"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"
  },
  \"table\": {},
  \"bodyType\": \"allConstraint,\",
  \"tableType\": \"\"
}', 1, '2024-02-06 15:51:07');
INSERT INTO `menu_base`
VALUES (23, '销售合同台账', 16, '/search/4', 1, 0, '{
  \"body\": {
    \"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"
  },
  \"table\": {},
  \"bodyType\": \"allConstraint,\",
  \"tableType\": \"\"
}', 1, '2024-02-06 15:54:08');
INSERT INTO `menu_base`
VALUES (24, '使用预算调整记录', 18, '/search/6', 2, 0, '{
  \"body\": {
    \"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"
  },
  \"table\": {},
  \"bodyType\": \"allConstraint,\",
  \"tableType\": \"\"
}', 1, '2024-02-06 15:56:09');
INSERT INTO `menu_base`
VALUES (25, '项目预算业务流程', 1, '/main', 2, 0, '{
  \"body\": {
    \"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"
  },
  \"table\": {},
  \"bodyType\": \"allConstraint,\",
  \"tableType\": \"\"
}', 1, '2024-02-06 15:57:02');
INSERT INTO `menu_base`
VALUES (26, '组织预算', 16, '/search/1', 2, 0, '{
  \"body\": {
    \"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"
  },
  \"table\": {},
  \"bodyType\": \"allConstraint,\",
  \"tableType\": \"\"
}', 1, '2024-02-06 16:35:02');
INSERT INTO `menu_base`
VALUES (27, '预算使用明细', 28, '/search/8', 2, 0, '{
  \"body\": {
    \"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"
  },
  \"table\": {},
  \"bodyType\": \"allConstraint,\",
  \"tableType\": \"\"
}', 1, '2024-02-07 17:37:29');
INSERT INTO `menu_base`
VALUES (28, '预算信息', 15, '', 2, 0, '{
  \"body\": {
    \"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"
  },
  \"table\": {},
  \"bodyType\": \"allConstraint,\",
  \"tableType\": \"\"
}', 1, '2024-02-07 17:39:46');

INSERT INTO `search_list_base`
VALUES (1, 6, '项目组织台账', NULL, 2, '{
  \"body\": {
    \"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"
  },
  \"table\": {},
  \"bodyType\": \"allConstraint,\",
  \"tableType\": \"\"
}', NULL, 0, 1, '2024-02-06 09:17:57');
INSERT INTO `search_list_base`
VALUES (2, 6, '采购合同分配台账', '{
  "fpqk": "1"
}', 3, '{
  \"body\": {
    \"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"
  },
  \"table\": {},
  \"bodyType\": \"allConstraint,\",
  \"tableType\": \"\"
}', '{
  "type": "desc",
  "column": "dataId"
}', 0, 1, '2024-02-06 09:18:40');
INSERT INTO `search_list_base`
VALUES (3, 6, '报销账单分配台账', '{
  "fpqk": "1"
}', 4, '{
  \"body\": {
    \"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"
  },
  \"table\": {},
  \"bodyType\": \"allConstraint,\",
  \"tableType\": \"\"
}', '{
  "type": "desc",
  "column": "dataId"
}', 0, 1, '2024-02-06 09:19:01');
INSERT INTO `search_list_base`
VALUES (4, 6, '销售合同台账', '{}', 1, '{
  \"body\": {
    \"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"
  },
  \"table\": {},
  \"bodyType\": \"allConstraint,\",
  \"tableType\": \"\"
}', NULL, 0, 1, '2024-02-06 09:19:31');
INSERT INTO `search_list_base`
VALUES (5, 6, '既定预算调整台账', NULL, 5, '{
  \"body\": {
    \"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"
  },
  \"table\": {},
  \"bodyType\": \"allConstraint,\",
  \"tableType\": \"\"
}', NULL, 0, 1, '2024-02-06 09:22:20');
INSERT INTO `search_list_base`
VALUES (6, 6, '使用预算调账台账', '{
  "tzqk": "1"
}', 6, '{
  \"body\": {
    \"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"
  },
  \"table\": {},
  \"bodyType\": \"allConstraint,\",
  \"tableType\": \"\"
}', '{
  "type": "desc",
  "column": "dataId"
}', 0, 1, '2024-02-06 09:22:33');
INSERT INTO `search_list_base`
VALUES (7, 6, '预算统计台账', '{}', 6, '{
  \"body\": {
    \"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"
  },
  \"table\": {},
  \"bodyType\": \"allConstraint,\",
  \"tableType\": \"\"
}', NULL, 1, 1, '2024-02-06 10:01:34');
INSERT INTO `search_list_base`
VALUES (8, 6, '预算使用记录台账', '{}', 7, '{
  \"body\": {
    \"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"
  },
  \"table\": {},
  \"bodyType\": \"allConstraint,\",
  \"tableType\": \"\"
}', NULL, 1, 1, '2024-02-07 17:22:59');

INSERT INTO `search_list_column`
VALUES (1, 1, 25, 0, 1, '项目编号');
INSERT INTO `search_list_column`
VALUES (2, 1, 26, 0, 2, '项目名称');
INSERT INTO `search_list_column`
VALUES (3, 1, 31, 0, 3, '关联合同');
INSERT INTO `search_list_column`
VALUES (4, 1, 32, 0, 4, '项目经理');
INSERT INTO `search_list_column`
VALUES (5, 1, 33, 0, 5, '所属分部');
INSERT INTO `search_list_column`
VALUES (6, 2, 49, 0, 1, '采购编号');
INSERT INTO `search_list_column`
VALUES (7, 2, 50, 0, 2, '采购名称');
INSERT INTO `search_list_column`
VALUES (8, 2, 59, 0, 3, '总金额');
INSERT INTO `search_list_column`
VALUES (9, 2, 53, 0, 4, '相关项目');
INSERT INTO `search_list_column`
VALUES (10, 2, 54, 0, 5, '项目经理');
INSERT INTO `search_list_column`
VALUES (11, 2, 52, 0, 6, '备注');
INSERT INTO `search_list_column`
VALUES (12, 3, 69, 0, 1, '报销人');
INSERT INTO `search_list_column`
VALUES (13, 3, 73, 0, 2, '报销金额');
INSERT INTO `search_list_column`
VALUES (14, 3, 71, 0, 3, '相关项目');
INSERT INTO `search_list_column`
VALUES (15, 3, 72, 0, 4, '项目经理');
INSERT INTO `search_list_column`
VALUES (16, 3, 74, 0, 5, '备注');
INSERT INTO `search_list_column`
VALUES (17, 4, 5, 0, 1, '合同编号');
INSERT INTO `search_list_column`
VALUES (18, 4, 6, 0, 2, '合同名称');
INSERT INTO `search_list_column`
VALUES (19, 4, 9, 0, 3, '相对方公司');
INSERT INTO `search_list_column`
VALUES (20, 4, 19, 0, 4, '合同总金额');
INSERT INTO `search_list_column`
VALUES (21, 4, 1, 0, 5, '销售人员');
INSERT INTO `search_list_column`
VALUES (22, 5, 82, 0, 1, '调整项目');
INSERT INTO `search_list_column`
VALUES (23, 5, 84, 0, 2, '调整时间');
INSERT INTO `search_list_column`
VALUES (24, 5, 85, 0, 3, '调整原因');
INSERT INTO `search_list_column`
VALUES (25, 6, 89, 0, 1, '调整项目');
INSERT INTO `search_list_column`
VALUES (26, 6, 91, 0, 2, '调整时间');
INSERT INTO `search_list_column`
VALUES (27, 6, 92, 0, 3, '调整原因');
INSERT INTO `search_list_column`
VALUES (28, 7, 8, 1, 1, '预算名称');
INSERT INTO `search_list_column`
VALUES (29, 7, 9, 1, 2, '预算金额');
INSERT INTO `search_list_column`
VALUES (30, 7, 10, 1, 3, '已使用预算');
INSERT INTO `search_list_column`
VALUES (31, 7, 11, 1, 4, '相关项目');
INSERT INTO `search_list_column`
VALUES (32, 7, 12, 1, 5, '项目经理');
INSERT INTO `search_list_column`
VALUES (33, 7, 13, 1, 6, '相关合同');
INSERT INTO `search_list_column`
VALUES (34, 7, 21, 1, 7, '是否超支');
INSERT INTO `search_list_column`
VALUES (35, 8, 17, 1, 1, '预算名称');
INSERT INTO `search_list_column`
VALUES (36, 8, 18, 1, 2, '使用金额');
INSERT INTO `search_list_column`
VALUES (37, 8, 19, 1, 3, '支出类型');
INSERT INTO `search_list_column`
VALUES (38, 8, 20, 1, 4, '使用时间');
INSERT INTO `search_list_column`
VALUES (39, 8, 15, 1, 5, '相关项目');
INSERT INTO `search_list_column`
VALUES (40, 8, 16, 1, 6, '项目经理');
INSERT INTO `search_list_column`
VALUES (41, 8, 14, 1, 7, '相关合同');

INSERT INTO `table_column_index`
VALUES (1, '销售人员', 'xsry', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":1,\"columnId\":1}', 1, 1, -1, 1, 1,
        '2024-02-02 17:45:52');
INSERT INTO `table_column_index`
VALUES (2, '销售时间', 'xssj', 'DATETIME', NULL, 1, 1, -1, 2, 1, '2024-02-02 17:46:53');
INSERT INTO `table_column_index`
VALUES (3, '销售部门', 'xsbm', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":2,\"columnId\":4}', 1, 1, -1, 3, 1,
        '2024-02-02 17:51:33');
INSERT INTO `table_column_index`
VALUES (4, '销售分部', 'xsfb', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":3,\"columnId\":5}', 1, 1, -1, 4, 1,
        '2024-02-02 18:02:04');
INSERT INTO `table_column_index`
VALUES (5, '合同编号', 'htbh', 'SINGLE_TEXT', NULL, 1, 2, -1, 5, 1, '2024-02-04 11:21:30');
INSERT INTO `table_column_index`
VALUES (6, '合同名称', 'htmc', 'SINGLE_TEXT', NULL, 1, 2, -1, 6, 1, '2024-02-04 11:21:53');
INSERT INTO `table_column_index`
VALUES (7, '意向确定时间', 'yxqdsj', 'DATETIME', NULL, 1, 2, -1, 7, 1, '2024-02-04 11:22:33');
INSERT INTO `table_column_index`
VALUES (8, '预计签署时间', 'yjqssj', 'DATETIME', NULL, 1, 2, -1, 8, 1, '2024-02-04 11:25:39');
INSERT INTO `table_column_index`
VALUES (9, '相对方公司名称', 'xdfgsmc', 'SINGLE_TEXT', NULL, 1, 3, -1, 9, 1, '2024-02-04 11:30:01');
INSERT INTO `table_column_index`
VALUES (10, '相对方公司银行账号', 'xdfgsyhzh', 'SINGLE_TEXT', NULL, 1, 3, -1, 10, 1, '2024-02-04 11:32:17');
INSERT INTO `table_column_index`
VALUES (11, '相对方公司税号', 'xdfgssh', 'SINGLE_TEXT', NULL, 1, 3, -1, 11, 1, '2024-02-04 11:34:43');
INSERT INTO `table_column_index`
VALUES (12, '相对方公司地址', 'xdfgsdz', 'SINGLE_TEXT', NULL, 1, 3, -1, 12, 1, '2024-02-04 11:37:33');
INSERT INTO `table_column_index`
VALUES (13, '相对方公司联系人', 'xdfgslxr', 'SINGLE_TEXT', NULL, 1, 3, -1, 13, 1, '2024-02-04 11:38:06');
INSERT INTO `table_column_index`
VALUES (14, '相对方公司联系方式', 'xdfgslxfs', 'SINGLE_TEXT', NULL, 1, 3, -1, 14, 1, '2024-02-04 11:38:47');
INSERT INTO `table_column_index`
VALUES (15, '销售金额', 'xsje', 'NUMBER', NULL, 1, 4, -1, 15, 1, '2024-02-04 11:39:14');
INSERT INTO `table_column_index`
VALUES (16, '销售币种', 'xsbz', 'SELECT_ITEM', '{\"items\":\"CNY,HKD,USD,EUR,JPY\"}', 1, 4, -1, 16, 1,
        '2024-02-04 11:40:13');
INSERT INTO `table_column_index`
VALUES (17, '汇率', 'hl', 'NUMBER', NULL, 1, 4, -1, 17, 1, '2024-02-04 11:40:30');
INSERT INTO `table_column_index`
VALUES (18, '税率', 'sl', 'NUMBER', NULL, 1, 4, -1, 18, 1, '2024-02-04 11:40:46');
INSERT INTO `table_column_index`
VALUES (19, '合计人民币金额', 'hjrmbje', 'NUMBER', NULL, 1, 4, -1, 19, 1, '2024-02-04 11:41:20');
INSERT INTO `table_column_index`
VALUES (20, '付款项名称', 'fkxmc', 'SINGLE_TEXT', NULL, 1, -1, 1, 20, 1, '2024-02-04 11:42:14');
INSERT INTO `table_column_index`
VALUES (21, '付款条件', 'fktj', 'SINGLE_TEXT', NULL, 1, -1, 1, 21, 1, '2024-02-04 13:13:13');
INSERT INTO `table_column_index`
VALUES (22, '预计时间', 'yjfksj', 'DATETIME', NULL, 1, -1, 1, 22, 1, '2024-02-04 13:15:14');
INSERT INTO `table_column_index`
VALUES (23, '付款金额', 'fkje', 'NUMBER', NULL, 1, -1, 1, 23, 1, '2024-02-04 13:15:33');
INSERT INTO `table_column_index`
VALUES (24, '合同附件', 'htfj', 'FILE', '{\"contentId\":7}', 1, 2, -1, 24, 1, '2024-02-04 13:23:14');
INSERT INTO `table_column_index`
VALUES (25, '项目编号', 'xmbh', 'SINGLE_TEXT', NULL, 2, 1, -1, 1, 1, '2024-02-04 13:41:22');
INSERT INTO `table_column_index`
VALUES (26, '项目名称', 'xmmc', 'SINGLE_TEXT', NULL, 2, 1, -1, 2, 1, '2024-02-04 13:42:05');
INSERT INTO `table_column_index`
VALUES (27, '项目立项时间', 'xmlxsj', 'DATETIME', NULL, 2, 1, -1, 3, 1, '2024-02-04 13:49:09');
INSERT INTO `table_column_index`
VALUES (28, '项目预计结束时间', 'xmyjjssj', 'DATETIME', NULL, 2, 1, -1, 4, 1, '2024-02-04 13:50:07');
INSERT INTO `table_column_index`
VALUES (29, '项目实际结束时间', 'xmsjjssj', 'DATETIME', NULL, 2, 1, -1, 5, 1, '2024-02-04 13:50:37');
INSERT INTO `table_column_index`
VALUES (30, '项目耗时(天)', 'xmhst', 'NUMBER', NULL, 2, 1, -1, 6, 1, '2024-02-04 13:51:05');
INSERT INTO `table_column_index`
VALUES (31, '合同编号', 'htbh', 'BROWSER_BOX', '{\"isVirtual\":false,\"tableId\":1,\"columnId\":5}', 2, 2, -1, 7, 1,
        '2024-02-04 13:57:43');
INSERT INTO `table_column_index`
VALUES (32, '项目经理', 'xmjl', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":1,\"columnId\":1}', 2, 3, -1, 8, 1,
        '2024-02-04 14:02:30');
INSERT INTO `table_column_index`
VALUES (33, '执行分部', 'zxfb', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":3,\"columnId\":5}', 2, 3, -1, 9, 1,
        '2024-02-04 14:08:20');
INSERT INTO `table_column_index`
VALUES (34, '销售金额', 'xsje', 'NUMBER', NULL, 2, 4, -1, 10, 1, '2024-02-04 14:09:44');
INSERT INTO `table_column_index`
VALUES (35, '预算金额', 'ysje', 'NUMBER', NULL, 2, 4, -1, 11, 1, '2024-02-04 14:10:07');
INSERT INTO `table_column_index`
VALUES (36, '实际支出', 'sjzc', 'NUMBER', NULL, 2, 4, -1, 12, 1, '2024-02-04 14:10:52');
INSERT INTO `table_column_index`
VALUES (37, '备注', 'bz', 'TEXT', NULL, 2, 4, -1, 13, 1, '2024-02-04 14:11:35');
INSERT INTO `table_column_index`
VALUES (38, '是否盈利', 'sfyl', 'SELECT_ITEM', '{\"items\":\"是,否\"}', 2, 5, -1, 14, 1, '2024-02-04 14:12:14');
INSERT INTO `table_column_index`
VALUES (39, '是否超支', 'sfcz', 'SELECT_ITEM', '{\"items\":\"是,否\"}', 2, 5, -1, 15, 1, '2024-02-04 14:12:42');
INSERT INTO `table_column_index`
VALUES (40, '预估利润', 'yglr', 'NUMBER', NULL, 2, 5, -1, 16, 1, '2024-02-04 14:14:29');
INSERT INTO `table_column_index`
VALUES (41, '实际利润', 'sjlr', 'NUMBER', NULL, 2, 5, -1, 17, 1, '2024-02-04 14:14:44');
INSERT INTO `table_column_index`
VALUES (42, '预算名称', 'ysmc', 'SINGLE_TEXT', NULL, 2, -1, 1, 18, 1, '2024-02-04 14:25:21');
INSERT INTO `table_column_index`
VALUES (43, '既定金额', 'jdje', 'NUMBER', NULL, 2, -1, 1, 19, 1, '2024-02-04 14:25:49');
INSERT INTO `table_column_index`
VALUES (44, '已用金额', 'yyje', 'NUMBER', NULL, 2, -1, 1, 20, 1, '2024-02-04 14:26:45');
INSERT INTO `table_column_index`
VALUES (45, '是否超支', 'sfcz', 'SELECT_ITEM', '{\"items\":\"是,否\"}', 2, -1, 1, 21, 1, '2024-02-04 14:27:30');
INSERT INTO `table_column_index`
VALUES (46, '组织名录附件', 'zzmlfj', 'FILE', '{\"contentId\":9}', 2, 3, -1, 22, 1, '2024-02-04 14:44:19');
INSERT INTO `table_column_index`
VALUES (47, '采购人', 'cgr', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":1,\"columnId\":1}', 3, 1, -1, 1, 1,
        '2024-02-04 14:53:48');
INSERT INTO `table_column_index`
VALUES (48, '采购时间', 'cgsj', 'DATETIME', NULL, 3, 1, -1, 2, 1, '2024-02-04 14:56:08');
INSERT INTO `table_column_index`
VALUES (49, '采购编号', 'cgbh', 'SINGLE_TEXT', NULL, 3, 1, -1, 3, 1, '2024-02-04 14:56:23');
INSERT INTO `table_column_index`
VALUES (50, '采购名称', 'cgmc', 'SINGLE_TEXT', NULL, 3, 1, -1, 4, 1, '2024-02-04 14:56:59');
INSERT INTO `table_column_index`
VALUES (51, '合同附件', 'htfj', 'FILE', '{\"contentId\":8}', 3, 1, -1, 5, 1, '2024-02-04 14:57:48');
INSERT INTO `table_column_index`
VALUES (52, '备注', 'bz', 'TEXT', NULL, 3, 1, -1, 6, 1, '2024-02-04 15:01:24');
INSERT INTO `table_column_index`
VALUES (53, '项目名称', 'xmmc', 'BROWSER_BOX', '{\"isVirtual\":false,\"tableId\":2,\"columnId\":26}', 3, 2, -1, 7, 1,
        '2024-02-04 15:01:56');
INSERT INTO `table_column_index`
VALUES (54, '项目经理', 'xmjl', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":1,\"columnId\":1}', 3, 2, -1, 8, 1,
        '2024-02-04 15:03:44');
INSERT INTO `table_column_index`
VALUES (55, '采购金额', 'cgje', 'NUMBER', NULL, 3, 3, -1, 9, 1, '2024-02-04 15:04:58');
INSERT INTO `table_column_index`
VALUES (56, '采购币种', 'cgbz', 'SELECT_ITEM', '{\"items\":\"CNY,HKD,USD,EUR,JPY\"}', 3, 3, -1, 10, 1,
        '2024-02-04 15:11:18');
INSERT INTO `table_column_index`
VALUES (57, '税率', 'sl', 'NUMBER', NULL, 3, 3, -1, 11, 1, '2024-02-04 15:11:33');
INSERT INTO `table_column_index`
VALUES (58, '汇率', 'hl', 'NUMBER', NULL, 3, 3, -1, 12, 1, '2024-02-04 15:12:00');
INSERT INTO `table_column_index`
VALUES (59, '付款金额', 'fkje', 'NUMBER', NULL, 3, 3, -1, 13, 1, '2024-02-04 15:14:55');
INSERT INTO `table_column_index`
VALUES (60, '交付项', 'jfx', 'SINGLE_TEXT', NULL, 3, -1, 1, 14, 1, '2024-02-04 15:15:36');
INSERT INTO `table_column_index`
VALUES (61, '交付条件', 'jftj', 'SINGLE_TEXT', NULL, 3, -1, 1, 15, 1, '2024-02-04 15:15:54');
INSERT INTO `table_column_index`
VALUES (62, '交付时间', 'jfsj', 'DATETIME', NULL, 3, -1, 1, 16, 1, '2024-02-04 15:17:03');
INSERT INTO `table_column_index`
VALUES (63, '交付内容', 'jfnr', 'TEXT', NULL, 3, -1, 1, 17, 1, '2024-02-04 15:17:41');
INSERT INTO `table_column_index`
VALUES (64, '对应付款', 'dyfk', 'NUMBER', NULL, 3, -1, 1, 18, 1, '2024-02-04 15:18:07');
INSERT INTO `table_column_index`
VALUES (65, '预算项', 'ysx', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":6,\"columnId\":8}', 3, -1, 2, 19, 1,
        '2024-02-04 15:43:20');
INSERT INTO `table_column_index`
VALUES (66, '既定金额', 'jdje', 'NUMBER', NULL, 3, -1, 2, 20, 1, '2024-02-04 15:44:09');
INSERT INTO `table_column_index`
VALUES (67, '已用金额', 'yyje', 'NUMBER', NULL, 3, -1, 2, 21, 1, '2024-02-04 15:44:28');
INSERT INTO `table_column_index`
VALUES (68, '本次金额', 'bcje', 'NUMBER', NULL, 3, -1, 2, 22, 1, '2024-02-04 15:44:53');
INSERT INTO `table_column_index`
VALUES (69, '报销人', 'bxr', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":1,\"columnId\":1}', 4, 1, -1, 1, 1,
        '2024-02-04 14:53:48');
INSERT INTO `table_column_index`
VALUES (70, '报销时间', 'bxsj', 'DATETIME', NULL, 4, 1, -1, 2, 1, '2024-02-04 14:56:08');
INSERT INTO `table_column_index`
VALUES (71, '项目名称', 'xmmc', 'BROWSER_BOX', '{\"isVirtual\":false,\"tableId\":2,\"columnId\":26}', 4, 2, -1, 3, 1,
        '2024-02-04 15:01:56');
INSERT INTO `table_column_index`
VALUES (72, '项目经理', 'xmjl', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":1,\"columnId\":1}', 4, 2, -1, 4, 1,
        '2024-02-04 15:03:44');
INSERT INTO `table_column_index`
VALUES (73, '报销金额', 'bxje', 'NUMBER', NULL, 4, 3, -1, 5, 1, '2024-02-04 15:04:58');
INSERT INTO `table_column_index`
VALUES (74, '报销事项', 'bxsx', 'TEXT', NULL, 4, 3, -1, 6, 1, '2024-02-04 15:11:18');
INSERT INTO `table_column_index`
VALUES (75, '报销明细', 'bxnr', 'SINGLE_TEXT', NULL, 4, -1, 1, 7, 1, '2024-02-04 16:48:19');
INSERT INTO `table_column_index`
VALUES (76, '明细金额', 'mxje', 'NUMBER', NULL, 4, -1, 1, 8, 1, '2024-02-04 16:48:52');
INSERT INTO `table_column_index`
VALUES (77, '明细发票', 'mxfp', 'FILE', '{\"contentId\":6}', 4, -1, 1, 9, 1, '2024-02-04 16:49:21');
INSERT INTO `table_column_index`
VALUES (78, '预算项', 'ysx', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":6,\"columnId\":8}', 4, -1, 2, 10, 1,
        '2024-02-04 15:43:20');
INSERT INTO `table_column_index`
VALUES (79, '既定金额', 'jdje', 'NUMBER', NULL, 4, -1, 2, 10, 1, '2024-02-04 15:44:09');
INSERT INTO `table_column_index`
VALUES (80, '已用金额', 'yyje', 'NUMBER', NULL, 4, -1, 2, 12, 1, '2024-02-04 15:44:28');
INSERT INTO `table_column_index`
VALUES (81, '本次金额', 'bcje', 'NUMBER', NULL, 4, -1, 2, 13, 1, '2024-02-04 15:44:53');
INSERT INTO `table_column_index`
VALUES (82, '项目名称', 'xmmc', 'BROWSER_BOX', '{\"isVirtual\":false,\"tableId\":2,\"columnId\":26}', 5, 1, -1, 1, 1,
        '2024-02-04 17:20:01');
INSERT INTO `table_column_index`
VALUES (83, '项目经理', 'xmjl', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":1,\"columnId\":1}', 5, 1, -1, 2, 1,
        '2024-02-04 17:22:51');
INSERT INTO `table_column_index`
VALUES (84, '调整时间', 'tzsj', 'DATETIME', NULL, 5, 2, -1, 3, 1, '2024-02-04 17:24:00');
INSERT INTO `table_column_index`
VALUES (85, '调整原因', 'tzyy', 'TEXT', NULL, 5, 2, -1, 4, 1, '2024-02-04 17:24:18');
INSERT INTO `table_column_index`
VALUES (86, '预算项', 'ysx', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":6,\"columnId\":8}', 5, -1, 1, 5, 1,
        '2024-02-04 17:24:54');
INSERT INTO `table_column_index`
VALUES (87, '原既定金额', 'yjdje', 'NUMBER', NULL, 5, -1, 1, 6, 1, '2024-02-04 17:25:18');
INSERT INTO `table_column_index`
VALUES (88, '新既定金额', 'xjdje', 'NUMBER', NULL, 5, -1, 1, 7, 1, '2024-02-04 17:25:39');
INSERT INTO `table_column_index`
VALUES (89, '项目名称', 'xmmc', 'BROWSER_BOX', '{\"isVirtual\":false,\"tableId\":2,\"columnId\":26}', 6, 1, -1, 1, 1,
        '2024-02-04 17:20:01');
INSERT INTO `table_column_index`
VALUES (90, '项目经理', 'xmjl', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":1,\"columnId\":1}', 6, 1, -1, 2, 1,
        '2024-02-04 17:22:51');
INSERT INTO `table_column_index`
VALUES (91, '调整时间', 'tzsj', 'DATETIME', NULL, 6, 2, -1, 3, 1, '2024-02-04 17:24:00');
INSERT INTO `table_column_index`
VALUES (92, '调整原因', 'tzyy', 'TEXT', NULL, 6, 2, -1, 4, 1, '2024-02-04 17:24:18');
INSERT INTO `table_column_index`
VALUES (93, '预算项', 'ysx', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":6,\"columnId\":8}', 6, -1, 1, 5, 1,
        '2024-02-04 17:24:54');
INSERT INTO `table_column_index`
VALUES (94, '既定金额', 'jdje', 'NUMBER', NULL, 6, -1, 1, 6, 1, '2024-02-04 17:25:18');
INSERT INTO `table_column_index`
VALUES (95, '已使用金额', 'ysyje', 'NUMBER', NULL, 6, -1, 1, 6, 1, '2024-02-04 17:25:18');
INSERT INTO `table_column_index`
VALUES (96, '调整金额', 'dzje', 'NUMBER', NULL, 6, -1, 1, 7, 1, '2024-02-04 17:25:39');
INSERT INTO `table_column_index`
VALUES (97, '分配情况', 'fpqk', 'SELECT_ITEM', '{\"items\":\"待分配,已分配,已废弃\"}', 3, 3, -1, 23, 1,
        '2024-02-06 16:55:31');
INSERT INTO `table_column_index`
VALUES (98, '分配情况', 'fpqk', 'SELECT_ITEM', '{\"items\":\"待分配,已分配,已废弃\"}', 4, 3, -1, 14, 1,
        '2024-02-06 16:57:45');
INSERT INTO `table_column_index`
VALUES (99, '调整情况', 'tzqk', 'SELECT_ITEM', '{\"items\":\"待审批,已调整,已退回\"}', 6, 2, -1, 8, 1,
        '2024-02-07 16:34:22');

INSERT INTO `table_index`
VALUES (1, '销售合同', 'form_table_1', 6, NULL, 1, '付款条件', 4, '申请信息,合同基础信息,相对方信息,金额信息',
        '项目预算表单', NULL, NULL, NULL, NULL, 1, '2024-02-02 16:03:51');
INSERT INTO `table_index`
VALUES (2, '项目预算编制', 'form_table_2', 6, NULL, 1, '预算明细', 5, '项目信息,合同信息,组织信息,金额信息,利润信息',
        '项目预算表单', NULL, NULL, NULL, NULL, 1, '2024-02-02 16:18:13');
INSERT INTO `table_index`
VALUES (3, '采购单', 'form_table_3', 6, NULL, 2, '物料交付明细,预算分配明细', 3, '采购信息,项目信息,金额信息',
        '项目预算表单', NULL, NULL, NULL, NULL, 1, '2024-02-02 16:19:35');
INSERT INTO `table_index`
VALUES (4, '报销单', 'form_table_4', 6, NULL, 2, '报销金额明细,预算分配明细', 3, '报销信息,项目信息,金额信息',
        '项目预算表单', NULL, NULL, NULL, NULL, 1, '2024-02-02 16:19:40');
INSERT INTO `table_index`
VALUES (5, '既定预算调整单', 'form_table_5', 6, NULL, 1, '调整明细', 2, '项目信息,调整信息', '项目预算表单', NULL, NULL,
        NULL, NULL, 1, '2024-02-04 09:53:27');
INSERT INTO `table_index`
VALUES (6, '使用预算调整单', 'form_table_6', 6, NULL, 1, '调整明细', 2, '项目信息,调整信息', '项目预算表单', NULL, NULL,
        NULL, NULL, 1, '2024-02-04 09:53:41');

INSERT INTO `table_view_column_index`
VALUES (8, '预算名称', 'ysmc', 'SINGLE_TEXT', NULL, 6, 1, 1, 1, '2024-02-04 15:33:18', 1);
INSERT INTO `table_view_column_index`
VALUES (9, '预算金额', 'jdys', 'NUMBER', NULL, 6, 1, 2, 1, '2024-02-04 15:35:51', 1);
INSERT INTO `table_view_column_index`
VALUES (10, '使用预算', 'ysyje', 'NUMBER', NULL, 6, 1, 3, 1, '2024-02-07 16:52:08', 1);
INSERT INTO `table_view_column_index`
VALUES (11, '项目名称', 'xmmc', 'BROWSER_BOX', '{\"isVirtual\":false,\"tableId\":2,\"columnId\":26}', 6, 2, 4, 1,
        '2024-02-07 16:56:43', 1);
INSERT INTO `table_view_column_index`
VALUES (12, '项目经理', 'xmjl', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":1,\"columnId\":1}', 6, 2, 5, 1,
        '2024-02-07 17:01:40', 1);
INSERT INTO `table_view_column_index`
VALUES (13, '合同编号', 'htbh', 'BROWSER_BOX', '{\"isVirtual\":false,\"tableId\":1,\"columnId\":5}', 6, 3, 6, 1,
        '2024-02-07 17:02:05', 1);
INSERT INTO `table_view_column_index`
VALUES (14, '合同编号', 'htbh', 'BROWSER_BOX', NULL, 7, 1, 1, 1, '2024-02-07 17:06:18', 1);
INSERT INTO `table_view_column_index`
VALUES (15, '项目名称', 'xmmc', 'BROWSER_BOX', '{\"isVirtual\":false,\"tableId\":2,\"columnId\":26}', 7, 2, 2, 1,
        '2024-02-07 17:06:30', 1);
INSERT INTO `table_view_column_index`
VALUES (16, '项目经理', 'xmjl', 'BROWSER_BOX', '{\"isVirtual\":true,\"tableId\":1,\"columnId\":1}', 7, 2, 3, 1,
        '2024-02-07 17:06:45', 1);
INSERT INTO `table_view_column_index`
VALUES (17, '预算名称', 'ysmc', 'SINGLE_TEXT', NULL, 7, 3, 4, 1, '2024-02-07 17:07:04', 1);
INSERT INTO `table_view_column_index`
VALUES (18, '使用金额', 'je', 'NUMBER', NULL, 7, 3, 5, 1, '2024-02-07 17:07:27', 1);
INSERT INTO `table_view_column_index`
VALUES (19, '支出类型', 'type', 'SELECT_ITEM', '{\"items\":\"采购支出,报销支出,调整支出\"}', 7, 3, 6, 1,
        '2024-02-07 17:10:55', 1);
INSERT INTO `table_view_column_index`
VALUES (20, '支出时间', 'createTime', 'DATETIME', NULL, 7, 3, 7, 1, '2024-02-07 17:11:11', 1);
INSERT INTO `table_view_column_index`
VALUES (21, '是否超支', 'sfcz', 'SELECT_ITEM', '{\"items\":\"未超支,已超支\"}', 6, -1, 7, 1, '2024-02-07 17:21:44', 1);

INSERT INTO `table_view_index`
VALUES (1, '人力资源', 'human_resource', 1, 2, '人力信息,联系方式', '系统默认索引表单', 1, '2024-01-24 13:39:39');
INSERT INTO `table_view_index`
VALUES (2, '职能部门', 'depart_resource', 1, 1, '组织信息', '系统默认索引表单', 1, '2024-01-24 17:10:35');
INSERT INTO `table_view_index`
VALUES (3, '公司分部', 'section_resource', 1, 1, '分部信息', '系统默认索引表单', 1, '2024-01-24 17:26:21');
INSERT INTO `table_view_index`
VALUES (4, '目录', 'content_list', 2, 1, '目录信息', '系统默认索引表单', 1, '2024-01-25 16:25:21');
INSERT INTO `table_view_index`
VALUES (5, '文件', 'file_storage', 2, 1, '文件信息', '系统默认索引表单', 1, '2024-01-25 16:27:29');
INSERT INTO `table_view_index`
VALUES (6, '预算使用统计', 'ysxx_use_statistics', 6, 3, '预算细项,项目信息,合同信息', '项目预算表单', 1,
        '2024-02-04 15:31:25');
INSERT INTO `table_view_index`
VALUES (7, '预算使用明细', 'ysxx_use_record', 6, 3, '合同信息,项目信息,支出信息', '项目预算表单', 1,
        '2024-02-07 16:39:53');

INSERT INTO `workflow`
VALUES (1, 6, 1, 5, '销售合同审批流程', NULL, '销售合同审批-', 1, '2024-02-05 10:46:51', 0);
INSERT INTO `workflow`
VALUES (2, 6, 2, NULL, '项目预算编制流程', NULL, '项目预算编制审批-', 1, '2024-02-05 14:11:09', 0);
INSERT INTO `workflow`
VALUES (3, 6, 3, NULL, '采购审批流程', NULL, '采购审批', 1, '2024-02-05 14:16:15', 0);
INSERT INTO `workflow`
VALUES (4, 6, 4, NULL, '报销审批流程', NULL, '报销审批', 1, '2024-02-05 14:16:35', 0);
INSERT INTO `workflow`
VALUES (5, 6, 5, NULL, '既定预算调整审批流程', NULL, '既定预算调整申请', 1, '2024-02-05 14:17:12', 0);
INSERT INTO `workflow`
VALUES (6, 6, 6, NULL, '使用预算调整审批流程', '', '使用预算调整申请', 1, '2024-02-05 14:18:00', 0);

INSERT INTO `workflow_node`
VALUES (1, '创建', '{
  \"body\": {
    \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":1,\\\"grade\\\":0},{\\\"characterId\\\":12,\\\"grade\\\":2}]}\"
  },
  \"table\": {},
  \"bodyType\": \"characterConstraint,\",
  \"tableType\": \"\"
}', 0, 0, '{
  \"1\": true,
  \"2\": true,
  \"3\": true,
  \"4\": true,
  \"5\": true,
  \"6\": true,
  \"7\": true,
  \"8\": true,
  \"9\": true,
  \"10\": true,
  \"11\": true,
  \"12\": true,
  \"13\": true,
  \"14\": true,
  \"15\": true,
  \"16\": true,
  \"17\": true,
  \"18\": true,
  \"19\": true,
  \"20\": true,
  \"21\": true,
  \"22\": true,
  \"23\": true,
  \"24\": true
}', NULL, NULL, NULL, 1, 1, 1, '2024-02-05 10:50:15');
INSERT INTO `workflow_node`
VALUES (2, '上级审批', '{
  \"body\": {
    \"createConstraint\": \"{\\\"self\\\":false,\\\"leader\\\":true,\\\"leaderRecursion\\\":false,\\\"depart\\\":false,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\"
  },
  \"table\": {},
  \"bodyType\": \"createConstraint,\",
  \"tableType\": \"\"
}', 0, 1, '{
  \"1\": false,
  \"2\": false,
  \"3\": false,
  \"4\": false,
  \"5\": false,
  \"6\": false,
  \"7\": false,
  \"8\": false,
  \"9\": false,
  \"10\": false,
  \"11\": false,
  \"12\": false,
  \"13\": false,
  \"14\": false,
  \"15\": false,
  \"16\": false,
  \"17\": false,
  \"18\": false,
  \"19\": false,
  \"20\": false,
  \"21\": false,
  \"22\": false,
  \"23\": false,
  \"24\": false
}', NULL, NULL, NULL, 1, 2, 1, '2024-02-05 10:52:39');
INSERT INTO `workflow_node`
VALUES (3, '分部领导审批', '{
  \"body\": {
    \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":16,\\\"grade\\\":1}]}\"
  },
  \"table\": {},
  \"bodyType\": \"characterConstraint,\",
  \"tableType\": \"\"
}', 0, 1, '{
  \"1\": false,
  \"2\": false,
  \"3\": false,
  \"4\": false,
  \"5\": false,
  \"6\": false,
  \"7\": false,
  \"8\": false,
  \"9\": false,
  \"10\": false,
  \"11\": false,
  \"12\": false,
  \"13\": false,
  \"14\": false,
  \"15\": false,
  \"16\": false,
  \"17\": false,
  \"18\": false,
  \"19\": false,
  \"20\": false,
  \"21\": false,
  \"22\": false,
  \"23\": false,
  \"24\": false
}', NULL, NULL, NULL, 1, 3, 1, '2024-02-05 10:56:36');
INSERT INTO `workflow_node`
VALUES (4, '公司领导审批', '{
  \"body\": {
    \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":13,\\\"grade\\\":1}]}\"
  },
  \"table\": {},
  \"bodyType\": \"characterConstraint,\",
  \"tableType\": \"\"
}', 0, 1, '{
  \"1\": false,
  \"2\": false,
  \"3\": false,
  \"4\": false,
  \"5\": false,
  \"6\": false,
  \"7\": false,
  \"8\": false,
  \"9\": false,
  \"10\": false,
  \"11\": false,
  \"12\": false,
  \"13\": false,
  \"14\": false,
  \"15\": false,
  \"16\": false,
  \"17\": false,
  \"18\": false,
  \"19\": false,
  \"20\": false,
  \"21\": false,
  \"22\": false,
  \"23\": false,
  \"24\": false
}', NULL, NULL, NULL, 1, 4, 1, '2024-02-05 11:04:18');
INSERT INTO `workflow_node`
VALUES (5, '通知预决算部', '{
  \"body\": {
    \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":15,\\\"grade\\\":1}]}\"
  },
  \"table\": {},
  \"bodyType\": \"characterConstraint,\",
  \"tableType\": \"\"
}', 0, 2, '{
  \"1\": false,
  \"2\": false,
  \"3\": false,
  \"4\": false,
  \"5\": false,
  \"6\": false,
  \"7\": false,
  \"8\": false,
  \"9\": false,
  \"10\": false,
  \"11\": false,
  \"12\": false,
  \"13\": false,
  \"14\": false,
  \"15\": false,
  \"16\": false,
  \"17\": false,
  \"18\": false,
  \"19\": false,
  \"20\": false,
  \"21\": false,
  \"22\": false,
  \"23\": false,
  \"24\": false
}', NULL, NULL, NULL, 1, 5, 1, '2024-02-05 11:05:18');
INSERT INTO `workflow_node`
VALUES (6, '创建人归档', '{
  \"body\": {
    \"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":false,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\"
  },
  \"table\": {},
  \"bodyType\": \"createConstraint,\",
  \"tableType\": \"\"
}', 0, 3, '{
  \"1\": false,
  \"2\": false,
  \"3\": false,
  \"4\": false,
  \"5\": false,
  \"6\": false,
  \"7\": false,
  \"8\": false,
  \"9\": false,
  \"10\": false,
  \"11\": false,
  \"12\": false,
  \"13\": false,
  \"14\": false,
  \"15\": false,
  \"16\": false,
  \"17\": false,
  \"18\": false,
  \"19\": false,
  \"20\": false,
  \"21\": false,
  \"22\": false,
  \"23\": false,
  \"24\": false
}', NULL, NULL, NULL, 1, 6, 1, '2024-02-05 11:05:33');
INSERT INTO `workflow_node`
VALUES (7, '建立组织', '{
  \"body\": {
    \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":1,\\\"grade\\\":0},{\\\"characterId\\\":15,\\\"grade\\\":2}]}\"
  },
  \"table\": {},
  \"bodyType\": \"characterConstraint,\",
  \"tableType\": \"\"
}', 0, 0, '{
  \"25\": true,
  \"26\": true,
  \"27\": true,
  \"28\": true,
  \"29\": true,
  \"30\": true,
  \"31\": true,
  \"32\": true,
  \"33\": true,
  \"34\": true,
  \"35\": true,
  \"36\": true,
  \"37\": true,
  \"38\": true,
  \"39\": true,
  \"40\": true,
  \"41\": true,
  \"42\": true,
  \"43\": true,
  \"44\": true,
  \"45\": true,
  \"46\": true
}', NULL, NULL, NULL, 2, 1, 1, '2024-02-05 14:27:02');
INSERT INTO `workflow_node`
VALUES (8, '领导审批', '{
  \"body\": {
    \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":16,\\\"grade\\\":1}]}\"
  },
  \"table\": {},
  \"bodyType\": \"characterConstraint,\",
  \"tableType\": \"\"
}', 0, 1, '{
  \"25\": false,
  \"26\": false,
  \"27\": false,
  \"28\": false,
  \"29\": false,
  \"30\": false,
  \"31\": false,
  \"32\": false,
  \"33\": false,
  \"34\": false,
  \"35\": false,
  \"36\": false,
  \"37\": false,
  \"38\": false,
  \"39\": false,
  \"40\": false,
  \"41\": false,
  \"42\": false,
  \"43\": false,
  \"44\": false,
  \"45\": false,
  \"46\": false
}', NULL, NULL, NULL, 2, 2, 1, '2024-02-05 14:27:12');
INSERT INTO `workflow_node`
VALUES (9, '通知项目经理', '{
  \"body\": {
    \"characterConstraint\": \"[]\"
  },
  \"table\": {
    \"proposedConstraint\": \"{\\\"humans\\\":[32],\\\"departs\\\":[],\\\"section\\\":[]}\"
  },
  \"bodyType\": \"characterConstraint,\",
  \"tableType\": \"proposedConstraint,\"
}', 0, 2, '{
  \"25\": false,
  \"26\": false,
  \"27\": false,
  \"28\": false,
  \"29\": false,
  \"30\": false,
  \"31\": false,
  \"32\": false,
  \"33\": false,
  \"34\": false,
  \"35\": false,
  \"36\": false,
  \"37\": false,
  \"38\": false,
  \"39\": false,
  \"40\": false,
  \"41\": false,
  \"42\": false,
  \"43\": false,
  \"44\": false,
  \"45\": false,
  \"46\": false
}', NULL, NULL, NULL, 2, 3, 1, '2024-02-05 14:27:24');
INSERT INTO `workflow_node`
VALUES (10, '完成归档', '{
  \"body\": {
    \"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":false,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\"
  },
  \"table\": {},
  \"bodyType\": \"createConstraint,\",
  \"tableType\": \"\"
}', 0, 3, '{
  \"25\": false,
  \"26\": false,
  \"27\": false,
  \"28\": false,
  \"29\": false,
  \"30\": false,
  \"31\": false,
  \"32\": false,
  \"33\": false,
  \"34\": false,
  \"35\": false,
  \"36\": false,
  \"37\": false,
  \"38\": false,
  \"39\": false,
  \"40\": false,
  \"41\": false,
  \"42\": false,
  \"43\": false,
  \"44\": false,
  \"45\": false,
  \"46\": false
}', NULL, NULL, NULL, 2, 4, 1, '2024-02-05 14:27:39');
INSERT INTO `workflow_node`
VALUES (11, '采购发起', '{
  \"body\": {
    \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":1,\\\"grade\\\":0},{\\\"characterId\\\":11,\\\"grade\\\":2}]}\"
  },
  \"table\": {},
  \"bodyType\": \"characterConstraint,\",
  \"tableType\": \"\"
}', 0, 0, '{
  \"47\": true,
  \"48\": true,
  \"49\": true,
  \"50\": true,
  \"51\": true,
  \"52\": true,
  \"53\": true,
  \"54\": true,
  \"55\": true,
  \"56\": true,
  \"57\": true,
  \"58\": true,
  \"59\": true,
  \"60\": true,
  \"61\": true,
  \"62\": true,
  \"63\": true,
  \"64\": true,
  \"65\": true,
  \"66\": true,
  \"67\": true,
  \"68\": true
}', '{
  \"tasks\": [
    {
      \"type\": 1,
      \"input\": \"2\",
      \"columnId\": 97
    }
  ],
  \"classNames\": []
}', NULL, '{
  \"tasks\": [
    {
      \"type\": 1,
      \"input\": \"0\",
      \"columnId\": 97
    }
  ],
  \"classNames\": []
}', 3, 1, 1, '2024-02-05 16:17:19');
INSERT INTO `workflow_node`
VALUES (12, '项目经理分配预算', '{
  \"body\": {},
  \"table\": {
    \"proposedConstraint\": \"{\\\"humans\\\":[54],\\\"departs\\\":[],\\\"section\\\":[]}\"
  },
  \"bodyType\": \"\",
  \"tableType\": \"proposedConstraint,\"
}', 0, 1, '{
  \"47\": false,
  \"48\": false,
  \"49\": false,
  \"50\": false,
  \"51\": false,
  \"52\": false,
  \"53\": false,
  \"54\": false,
  \"55\": false,
  \"56\": false,
  \"57\": false,
  \"58\": false,
  \"59\": false,
  \"60\": false,
  \"61\": false,
  \"62\": false,
  \"63\": false,
  \"64\": false,
  \"65\": true,
  \"66\": true,
  \"67\": true,
  \"68\": true
}', NULL, NULL, '{
  \"tasks\": [
    {
      \"type\": 1,
      \"input\": \"1\",
      \"columnId\": 97
    }
  ],
  \"classNames\": []
}', 3, 2, 1, '2024-02-05 16:17:41');
INSERT INTO `workflow_node`
VALUES (13, '预决算部超支审批', '{
  \"body\": {
    \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":15,\\\"grade\\\":1}]}\"
  },
  \"table\": {},
  \"bodyType\": \"characterConstraint,\",
  \"tableType\": \"\"
}', 0, 1, '{
  \"47\": false,
  \"48\": false,
  \"49\": false,
  \"50\": false,
  \"51\": false,
  \"52\": false,
  \"53\": false,
  \"54\": false,
  \"55\": false,
  \"56\": false,
  \"57\": false,
  \"58\": false,
  \"59\": false,
  \"60\": false,
  \"61\": false,
  \"62\": false,
  \"63\": false,
  \"64\": false,
  \"65\": false,
  \"66\": false,
  \"67\": false,
  \"68\": false
}', NULL, NULL, NULL, 3, 3, 1, '2024-02-05 16:18:26');
INSERT INTO `workflow_node`
VALUES (14, '归档', '{
  \"body\": {
    \"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":false,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\"
  },
  \"table\": {},
  \"bodyType\": \"createConstraint,\",
  \"tableType\": \"\"
}', 0, 3, '{
  \"47\": false,
  \"48\": false,
  \"49\": false,
  \"50\": false,
  \"51\": false,
  \"52\": false,
  \"53\": false,
  \"54\": false,
  \"55\": false,
  \"56\": false,
  \"57\": false,
  \"58\": false,
  \"59\": false,
  \"60\": false,
  \"61\": false,
  \"62\": false,
  \"63\": false,
  \"64\": false,
  \"65\": false,
  \"66\": false,
  \"67\": false,
  \"68\": false
}', NULL, NULL, NULL, 3, 4, 1, '2024-02-05 16:18:32');
INSERT INTO `workflow_node`
VALUES (15, '业务人员发起', '{
  \"body\": {
    \"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":false,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\"
  },
  \"table\": {},
  \"bodyType\": \"createConstraint,\",
  \"tableType\": \"\"
}', 0, 0, '{
  \"69\": true,
  \"70\": true,
  \"71\": true,
  \"72\": true,
  \"73\": true,
  \"74\": true,
  \"75\": true,
  \"76\": true,
  \"77\": true,
  \"78\": true,
  \"79\": true,
  \"80\": true,
  \"81\": true
}', '{
  \"tasks\": [
    {
      \"type\": 1,
      \"input\": \"2\",
      \"columnId\": 98
    }
  ],
  \"classNames\": []
}', NULL, '{
  \"tasks\": [
    {
      \"type\": 1,
      \"input\": \"0\",
      \"columnId\": 98
    }
  ],
  \"classNames\": []
}', 4, 1, 1, '2024-02-05 17:01:36');
INSERT INTO `workflow_node`
VALUES (16, '项目经理分配预算', '{
  \"body\": {},
  \"table\": {
    \"proposedConstraint\": \"{\\\"humans\\\":[72],\\\"departs\\\":[],\\\"section\\\":[]}\"
  },
  \"bodyType\": \"\",
  \"tableType\": \"proposedConstraint,\"
}', 0, 1, '{
  \"69\": false,
  \"70\": false,
  \"71\": false,
  \"72\": false,
  \"73\": false,
  \"74\": false,
  \"75\": false,
  \"76\": false,
  \"77\": false,
  \"78\": true,
  \"79\": true,
  \"80\": true,
  \"81\": true
}', NULL, NULL, '{
  \"tasks\": [
    {
      \"type\": 1,
      \"input\": \"1\",
      \"columnId\": 98
    }
  ],
  \"classNames\": []
}', 4, 2, 1, '2024-02-05 17:01:56');
INSERT INTO `workflow_node`
VALUES (17, '预算部超支审查', '{
  \"body\": {
    \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":15,\\\"grade\\\":1}]}\"
  },
  \"table\": {},
  \"bodyType\": \"characterConstraint,\",
  \"tableType\": \"\"
}', 0, 1, '{
  \"69\": false,
  \"70\": false,
  \"71\": false,
  \"72\": false,
  \"73\": false,
  \"74\": false,
  \"75\": false,
  \"76\": false,
  \"77\": false,
  \"78\": false,
  \"79\": false,
  \"80\": false,
  \"81\": false
}', NULL, NULL, NULL, 4, 3, 1, '2024-02-05 17:02:17');
INSERT INTO `workflow_node`
VALUES (18, '归档', '{
  \"body\": {
    \"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":false,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\"
  },
  \"table\": {},
  \"bodyType\": \"createConstraint,\",
  \"tableType\": \"\"
}', 0, 3, '{
  \"69\": false,
  \"70\": false,
  \"71\": false,
  \"72\": false,
  \"73\": false,
  \"74\": false,
  \"75\": false,
  \"76\": false,
  \"77\": false,
  \"78\": false,
  \"79\": false,
  \"80\": false,
  \"81\": false
}', NULL, NULL, NULL, 4, 4, 1, '2024-02-05 17:05:35');
INSERT INTO `workflow_node`
VALUES (19, '项目经理发起', '{
  \"body\": {
    \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":1,\\\"grade\\\":0},{\\\"characterId\\\":14,\\\"grade\\\":2}]}\"
  },
  \"table\": {},
  \"bodyType\": \"characterConstraint,\",
  \"tableType\": \"\"
}', 0, 0, '{
  \"82\": true,
  \"83\": true,
  \"84\": true,
  \"85\": true,
  \"86\": true,
  \"87\": true,
  \"88\": true
}', NULL, NULL, NULL, 5, 1, 1, '2024-02-05 17:24:37');
INSERT INTO `workflow_node`
VALUES (20, '预决算部审批', '{
  \"body\": {
    \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":15,\\\"grade\\\":1}]}\"
  },
  \"table\": {},
  \"bodyType\": \"characterConstraint,\",
  \"tableType\": \"\"
}', 0, 1, '{
  \"82\": false,
  \"83\": false,
  \"84\": false,
  \"85\": false,
  \"86\": false,
  \"87\": false,
  \"88\": false
}', NULL, NULL, NULL, 5, 2, 1, '2024-02-05 17:24:47');
INSERT INTO `workflow_node`
VALUES (21, '归档', '{
  \"body\": {
    \"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":false,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\"
  },
  \"table\": {},
  \"bodyType\": \"createConstraint,\",
  \"tableType\": \"\"
}', 0, 3, '{
  \"82\": false,
  \"83\": false,
  \"84\": false,
  \"85\": false,
  \"86\": false,
  \"87\": false,
  \"88\": false
}', NULL, NULL, NULL, 5, 3, 1, '2024-02-05 17:24:55');
INSERT INTO `workflow_node`
VALUES (22, '项目经理发起', '{
  \"body\": {
    \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":1,\\\"grade\\\":0},{\\\"characterId\\\":14,\\\"grade\\\":2}]}\"
  },
  \"table\": {},
  \"bodyType\": \"characterConstraint,\",
  \"tableType\": \"\"
}', 0, 0, '{
  \"89\": true,
  \"90\": true,
  \"91\": true,
  \"92\": true,
  \"93\": true,
  \"94\": true,
  \"95\": true,
  \"96\": true
}', NULL, NULL, NULL, 6, 1, 1, '2024-02-05 17:31:58');
INSERT INTO `workflow_node`
VALUES (23, '预决算部审批', '{
  \"body\": {
    \"characterConstraint\": \"{\\\"characters\\\":[{\\\"characterId\\\":15,\\\"grade\\\":1}]}\"
  },
  \"table\": {},
  \"bodyType\": \"characterConstraint,\",
  \"tableType\": \"\"
}', 0, 1, '{
  \"89\": false,
  \"90\": false,
  \"91\": false,
  \"92\": false,
  \"93\": false,
  \"94\": false,
  \"95\": false,
  \"96\": false
}', NULL, NULL, NULL, 6, 2, 1, '2024-02-05 17:32:04');
INSERT INTO `workflow_node`
VALUES (24, '归档', '{
  \"body\": {
    \"createConstraint\": \"{\\\"self\\\":true,\\\"leader\\\":false,\\\"leaderRecursion\\\":false,\\\"depart\\\":false,\\\"section\\\":false,\\\"sectionRecursive\\\":false}\"
  },
  \"table\": {},
  \"bodyType\": \"createConstraint,\",
  \"tableType\": \"\"
}', 0, 3, '{
  \"89\": false,
  \"90\": false,
  \"91\": false,
  \"92\": false,
  \"93\": false,
  \"94\": false,
  \"95\": false,
  \"96\": false
}', NULL, NULL, NULL, 6, 3, 1, '2024-02-05 17:32:09');

INSERT INTO `workflow_route`
VALUES (1, '创建-上级审批', 1, 1, 2, 1, NULL, NULL, 1, '2024-02-05 11:37:42');
INSERT INTO `workflow_route`
VALUES (2, '上级审批-分部领导审批', 1, 2, 3, 2, NULL, NULL, 1, '2024-02-05 11:38:22');
INSERT INTO `workflow_route`
VALUES (3, '分部领导-公司领导审批', 1, 3, 4, 3, NULL, NULL, 1, '2024-02-05 11:38:55');
INSERT INTO `workflow_route`
VALUES (4, '分部领导-预决算部', 1, 3, 5, 4, NULL, NULL, 1, '2024-02-05 11:40:52');
INSERT INTO `workflow_route`
VALUES (5, '公司领导-预决算部', 1, 4, 5, 5, NULL, NULL, 1, '2024-02-05 11:41:09');
INSERT INTO `workflow_route`
VALUES (6, '预决算部-归档', 1, 5, 6, 6, NULL, NULL, 1, '2024-02-05 11:41:28');
INSERT INTO `workflow_route`
VALUES (7, '领导审批', 2, 7, 8, 1, NULL, NULL, 1, '2024-02-05 16:14:57');
INSERT INTO `workflow_route`
VALUES (8, '通知项目经理', 2, 8, 9, 2, NULL, NULL, 1, '2024-02-05 16:15:11');
INSERT INTO `workflow_route`
VALUES (9, '归档', 2, 9, 10, 3, NULL, NULL, 1, '2024-02-05 16:15:19');
INSERT INTO `workflow_route`
VALUES (10, '项目经理审批', 4, 15, 16, 1, NULL, NULL, 1, '2024-02-05 17:23:05');
INSERT INTO `workflow_route`
VALUES (11, '预决算部超支审批', 4, 16, 17, 2, NULL, NULL, 1, '2024-02-05 17:23:32');
INSERT INTO `workflow_route`
VALUES (12, '直接归档', 4, 16, 18, 3, NULL, NULL, 1, '2024-02-05 17:23:49');
INSERT INTO `workflow_route`
VALUES (13, '审批后归档', 4, 17, 18, 4, NULL, NULL, 1, '2024-02-05 17:24:00');
INSERT INTO `workflow_route`
VALUES (14, '预决算部审批', 5, 19, 20, 1, NULL, NULL, 1, '2024-02-05 17:28:26');
INSERT INTO `workflow_route`
VALUES (15, '归档', 5, 20, 21, 2, NULL, NULL, 1, '2024-02-05 17:28:33');
INSERT INTO `workflow_route`
VALUES (16, '预决算部审批', 6, 22, 23, 1, NULL, NULL, 1, '2024-02-05 17:32:28');
INSERT INTO `workflow_route`
VALUES (17, '归档', 6, 23, 24, 2, NULL, NULL, 1, '2024-02-05 17:32:54');
INSERT INTO `workflow_route`
VALUES (18, '项目经理分配预算', 3, 11, 12, 1, NULL, NULL, 1, '2024-02-06 17:10:31');
INSERT INTO `workflow_route`
VALUES (19, '预决算部审批', 3, 12, 13, 2, NULL, NULL, 1, '2024-02-06 17:10:44');
INSERT INTO `workflow_route`
VALUES (20, '归档', 3, 13, 14, 3, NULL, NULL, 1, '2024-02-06 17:15:26');

CREATE VIEW `ysxx_use_record` AS
SELECT UNIX_TIMESTAMP(createTime) * 3 + type AS dataId,
       ysxx.htbh,
       ysxx.xmmc,
       ysxx.xmjl,
       ysxx.ysmc,
       useRecord.je,
       useRecord.type,
       useRecord.createTime
FROM ((SELECT detailDataId,
              ysx,
              bcje AS je,
              0    AS type,
              main.createTime
       FROM form_table_3_dt_2 AS dt
                LEFT JOIN form_table_3 AS main ON dt.detailMainId = main.dataId
       where main.fpqk = 1)
      -- 采购分配细项
      UNION
      (SELECT detailDataId,
              ysx,
              bcje AS je,
              1    AS type,
              main.createTime
       FROM form_table_4_dt_2 AS dt
                LEFT JOIN form_table_4 AS main ON dt.detailMainId = main.dataId
       where main.fpqk = 1)
      -- 报销分配细项
      UNION
      (SELECT detailDataId,
              ysx,
              tzje AS je,
              2    AS type,
              main.createTime
       FROM form_table_6_dt_1 AS dt
                LEFT JOIN form_table_6 AS main ON dt.detailMainId = main.dataId
       where main.tzqk = 1) -- 预算调整细项

     ) AS useRecord
         LEFT JOIN (SELECT detailDataId,
                           ysmc,
                           dataId as xmmc,
                           xmjl,
                           htbh
                    FROM form_table_2_dt_1 AS dt
                             LEFT JOIN form_table_2 AS main ON dt.detailMainId = main.dataId) AS ysxx
                   ON useRecord.detailDataId = ysxx.detailDataId;

CREATE VIEW `ysxx_use_statistics` AS
SELECT ysxx.detailDataId                             AS dataId,
       ysmc,
       xmmc,
       xmjl,
       htbh,
       jdje,
       ifnull(`yssyqk`.`ysyje`, 0)                   as ysyje,
       if(jdje >= ifnull(`yssyqk`.`ysyje`, 0), 0, 1) as sfcz
FROM (SELECT detailDataId,
             ysmc,
             dataId as xmmc,
             jdje,
             xmjl,
             htbh
      FROM form_table_2_dt_1 AS dt
               LEFT JOIN form_table_2 AS main ON dt.detailMainId = main.dataId) AS ysxx
         LEFT JOIN (SELECT ysx,
                           sum(je) AS ysyje
                    FROM ((SELECT ysx, bcje AS je
                           FROM form_table_3_dt_2 AS dt
                                    LEFT JOIN form_table_3 AS main ON dt.detailMainId = main.dataId
                           where main.fpqk = 1)
                          -- 采购分配细项
                          UNION
                          (SELECT ysx, bcje AS je
                           FROM form_table_4_dt_2 AS dt
                                    LEFT JOIN form_table_4 AS main ON dt.detailMainId = main.dataId
                           where main.fpqk = 1)
                          -- 报销分配细项
                          UNION
                          (SELECT ysx, tzje AS je
                           FROM form_table_6_dt_1 AS dt
                                    LEFT JOIN form_table_6 AS main ON dt.detailMainId = main.dataId
                           where main.tzqk = 1) -- 预算调整细项

                         ) AS useRecord
                    GROUP BY (ysx)) AS yssyqk ON ysxx.detailDataId = yssyqk.ysx;