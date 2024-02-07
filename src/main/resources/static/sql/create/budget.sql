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
SELECT ysxx.detailDataId AS dataId,
       ysmc,
       xmmc,
       xmjl,
       htbh,
       jdje,
       ifnull(`yssyqk`.`ysyje`, 0) as ysyje,
       if(jdje>=ifnull(`yssyqk`.`ysyje`, 0),0,1) as sfcz
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