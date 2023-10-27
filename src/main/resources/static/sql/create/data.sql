insert into `human_resource` (dataId, loginName, password,name, sex, birth, telephone, mail, phone, fax, workCode, section, depart, job, directorLeader, supporter, photo, signature, lastLogin)
        VALUE (1,'sysadmin','eoa','系统管理员',null,null,null,null,null,null,null,0,0,'系统管理员',0,0,0,null,null);
-- 添加管理员


INSERT INTO `module_type` (`moduleTypeId`, `moduleTypeName`, `workflowRemark`, `creator`)
    VALUES ('1', '组织架构', '系统默认应用-组织架构', '1'),
        ('2', '目录文档', '系统默认应用-目录文档', '1'),
        ('3', '页面菜单', '系统默认应用-页面菜单', '1'),
        ('4', '流程批阅', '系统默认应用-流程批阅', '1'),
        ('5', '汇总展示', '系统默认应用-汇总展示', '1');
-- 添加系统默认应用

INSERT INTO `authority` (`dataId`,`authorityName`,`authorityDescription`,`authorityRemark`)
    values ('1','全部权限','全部后端权限拥有','默认系统管理员享有该权限'),
    ('2','表单权限','表单列表的配置操作','默认表单管理员享有该权限'),
    ('3','组织权限','组织人员的配置操作','默认表单管理员享有该权限'),
    ('4','目录权限','目录文件的配置操作','默认表单管理员享有该权限'),
    ('5','角色权限','角色人员的配置操作','默认表单管理员享有该权限'),
    ('6','授权权限','权限授权的配置操作','默认表单管理员享有该权限'),
    ('7','流程权限','流程审批的配置操作','默认表单管理员享有该权限'),
    ('8','监控权限','流程数据的监控操作','默认监控管理员享有该权限'),
    ('9','图表权限','图表展示的配置操作','默认图表管理员享有该权限'),
    ('10','页面权限','页面菜单的配置操作','默认页面管理员享有该权限');



