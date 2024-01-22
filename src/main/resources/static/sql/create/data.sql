INSERT INTO `human_resource` (dataId, loginName, password, name, sex, birth, telephone, mail, phone, fax, workCode,
                              section, depart, job, directorLeader, supporter, photo, signature, lastLogin)
VALUES (1, 'sysadmin', 'eoa', '系统管理员', null, null, null, null, null, null, null, 0, 0, '系统管理员', 0, 0, 0, null,
        null),
       (2, 'tourist', 'eoa', '游客用户', null, null, null, null, null, null, null, 0, 0, '游客', 1, 0, 0, null, null);
-- 添加管理员


INSERT INTO `module_type` (`moduleTypeId`, `moduleTypeName`, `workflowRemark`, `creator`)
VALUES ('1', '组织架构', '系统默认应用-组织架构', '1'),
       ('2', '目录文档', '系统默认应用-目录文档', '1'),
       ('3', '页面菜单', '系统默认应用-页面菜单', '1'),
       ('4', '流程批阅', '系统默认应用-流程批阅', '1'),
       ('5', '汇总展示', '系统默认应用-汇总展示', '1');
-- 添加系统默认应用

INSERT INTO `authority` (`dataId`, `authorityName`, `authorityDescription`, `authorityRemark`)
values ('1', '全部权限', '全部后端权限拥有', '默认系统管理员享有该权限'),
       ('2', '表单权限', '表单列表的配置操作', '默认表单管理员享有该权限'),
       ('3', '组织权限', '组织人员的配置操作', '默认表单管理员享有该权限'),
       ('4', '目录权限', '目录文件的配置操作', '默认表单管理员享有该权限'),
       ('5', '角色权限', '角色人员的配置操作', '默认表单管理员享有该权限'),
       ('6', '授权权限', '权限授权的配置操作', '默认表单管理员享有该权限'),
       ('7', '流程权限', '流程审批的配置操作', '默认表单管理员享有该权限'),
       ('8', '监控权限', '流程数据的监控操作', '默认监控管理员享有该权限'),
       ('9', '图表权限', '图表展示的配置操作', '默认图表管理员享有该权限'),
       ('10', '页面权限', '页面菜单的配置操作', '默认页面管理员享有该权限');
-- 添加系统后端权限

INSERT INTO `character` (`dataId`, `characterName`, `characterDescription`, `createTime`, `creator`)
values ('1', '系统管理员', '拥有所有后端的操作权限', '2023-10-27 00:00:00', '1'),
       ('2', '表单管理员', '拥有表单字段的操作权限', '2023-10-27 00:00:00', '1'),
       ('3', '组织管理员', '拥有组织结构的操作权限', '2023-10-27 00:00:00', '1'),
       ('4', '目录管理员', '拥有目录配置的操作权限', '2023-10-27 00:00:00', '1'),
       ('5', '角色管理员', '拥有角色配置的操作权限', '2023-10-27 00:00:00', '1'),
       ('6', '授权管理员', '拥有权限授予的操作权限', '2023-10-27 00:00:00', '1'),
       ('7', '流程管理员', '拥有流程配置的操作权限', '2023-10-27 00:00:00', '1'),
       ('8', '监控管理员', '拥有流程监控的操作权限', '2023-10-27 00:00:00', '1'),
       ('9', '图表管理员', '拥有图表配置的操作权限', '2023-10-27 00:00:00', '1'),
       ('10', '页面管理员', '拥有页面配置的操作权限', '2023-10-27 00:00:00', '1');

-- 添加系统默认角色

INSERT INTO `authority_character` (`characterId`, `authorityId`)
values ('1', '1'),
       ('2', '2'),
       ('3', '3'),
       ('4', '4'),
       ('5', '5'),
       ('6', '6'),
       ('7', '7'),
       ('8', '8'),
       ('9', '9'),
       ('10', '10');
-- 链接角色与权限

INSERT INTO `character_human` (`characterId`, `humanId`)
values ('1', '1');
-- 链接系统管理员人员与角色

Insert Into `content_list` (dataId, contentName, contentRemark, creator, createTime, defaultEdit, defaultCreate,
                            defaultDelete, defaultShare, leadContent)
VALUES (1, '组织架构展示资料', '系统默认目录,勿动', 1, now(), null, '{
     "bodyType":"allConstraint",
     "body":{
       "allConstraint":"{\\"start\\":0,\\"end\\":100}"
     },
     "tableType":null,
     "table":null
   }', null, '{
     "bodyType":"allConstraint",
     "body":{
       "allConstraint":"{\\"start\\":0,\\"end\\":100}"
     },
     "tableType":null,
     "table":null
   }', 0),
       (2, '人员头像', '系统默认目录,勿动', 1, now(), null, '{
     "bodyType":"allConstraint",
     "body":{
       "allConstraint":"{\\"start\\":0,\\"end\\":100}"
     },
     "tableType":null,
     "table":null
   }', null, '{
     "bodyType":"allConstraint",
     "body":{
       "allConstraint":"{\\"start\\":0,\\"end\\":100}"
     },
     "tableType":null,
     "table":null
   }', 1),
       (3, '部门头像', '系统默认目录,勿动', 1, now(), null, '{
     "bodyType":"allConstraint",
     "body":{
       "allConstraint":"{\\"start\\":0,\\"end\\":100}"
     },
     "tableType":null,
     "table":null
   }', null, '{
     "bodyType":"allConstraint",
     "body":{
       "allConstraint":"{\\"start\\":0,\\"end\\":100}"
     },
     "tableType":null,
     "table":null
   }', 0),
       (4, '分部头像', '系统默认目录,勿动', 1, now(), null, '{
     "bodyType":"allConstraint",
     "body":{
       "allConstraint":"{\\"start\\":0,\\"end\\":100}"
     },
     "tableType":null,
     "table":null
   }', null, '{
     "bodyType":"allConstraint",
     "body":{
       "allConstraint":"{\\"start\\":0,\\"end\\":100}"
     },
     "tableType":null,
     "table":null
   }', 0)

-- 前端目录默认配置
    INSERT
INTO `menu_base` (`dataId`, `contentName`, `belongContent`, `contentUrl`, `viewNo`, `isDeprecated`, `shareAuthority`,
                  `creator`, `createTime`)
VALUES (1, '主页', NULL, NULL, 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, now())
        , (2, '组织结构', NULL, NULL, 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, now())
        , (3, '知识目录', NULL, NULL, 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, now())
        , (4, '工作流程', NULL, NULL, 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, now())
        , (5, '公司主页', 1, '/main', 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, now())
        , (6, '组织结构树', 2, '/organization', 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, now())
        , (7, '分部列表', 2, '/section', 2, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, now())
        , (8, '部门列表', 2, '/depart', 3, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, now())
        , (9, '人员列表', 2, '/human_resouce', 4, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, now())
        , (10, '知识地图', 3, '/content/0', 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, now())
        , (11, '待办请求', 4, '/request_backlog', 1, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, now())
        , (12, '已办请求', 4, '/request_done', 2, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, now())
        , (13, '我的请求', 4, '/request_self', 3, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, now())
        , (14, '发起请求', 4, '/workflow', 4, 0, '{\"body\": {\"allConstraint\": \"{\\\"start\\\":0,\\\"end\\\":100}\"}, \"table\": {}, \"bodyType\": \"allConstraint,\", \"tableType\": \"\"}', 1, now());

INSERT INTO `eoa_build`.`login_config` (`dataId`, `backgroundUrl`, `logoUrl`, `backgroundVideoUrl`, `loginTitle`, `loginSubTitle`, `activeMainTitle`, `activeIntroduction`, `contactManager`, `linkUrl`, `linkStr`, `creator`, `createTime`, `onUse`) VALUES ('1', 'https://mdn.alipayobjects.com/huamei_gcee1x/afts/img/A*y0ZTS6WLwvgAAAAAAAAAAAAADml6AQ/fmt.webp', 'https://github.githubassets.com/images/modules/logos_page/Octocat.png', 'https://gw.alipayobjects.com/v/huamei_gcee1x/afts/video/jXRBRK_VAwoAAAAAAAAAAAAAK4eUAQBr', 'EOA', '可配置的低代码办公平台', '项目预算特化开发版本', '作者相关', '张骏山:13671985248', 'http://127.0.0.1:8080/doc.html', '查看', '1', now(), '1');

INSERT INTO `eoa_build`.`page_config` (`dataId`, `companyName`, `headerColor`, `sideColor`, `onUse`, `creator`, `createTime`) VALUES ('1', 'EOA', '#140066', '#8A8DAD', '1', '1',NOW());



