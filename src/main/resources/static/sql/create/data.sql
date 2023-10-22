insert into `human_resource` (dataId, loginName, password,name, sex, birth, telephone, mail, phone, fax, workCode, section, depart, job, directorLeader, supporter, photo, signature, lastLogin)
        VALUES (1,'sysadmin','eoa','系统管理员',null,null,null,null,null,null,null,0,0,'系统管理员',0,0,0,null,null);
-- 添加管理员


INSERT INTO `eoa_build`.`module_type` (`moduleTypeId`, `moduleTypeName`, `workflowRemark`, `creator`) VALUES ('1', '组织架构', '系统默认应用-组织架构', '1');
INSERT INTO `eoa_build`.`module_type` (`moduleTypeId`, `moduleTypeName`, `workflowRemark`, `creator`) VALUES ('2', '目录文档', '系统默认应用-目录文档', '1');
INSERT INTO `eoa_build`.`module_type` (`moduleTypeId`, `moduleTypeName`, `workflowRemark`, `creator`) VALUES ('3', '页面菜单', '系统默认应用-页面菜单', '1');
INSERT INTO `eoa_build`.`module_type` (`moduleTypeId`, `moduleTypeName`, `workflowRemark`, `creator`) VALUES ('4', '流程批阅', '系统默认应用-流程批阅', '1');
INSERT INTO `eoa_build`.`module_type` (`moduleTypeId`, `moduleTypeName`, `workflowRemark`, `creator`) VALUES ('5', '汇总展示', '系统默认应用-汇总展示', '1');
-- 添加系统默认应用