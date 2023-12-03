CREATE TABLE `module_type` (
  `moduleTypeId` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '模块类型编号',
  `moduleTypeName` VARCHAR(100) NULL COMMENT '模块类型名称',
  `workflowRemark` VARCHAR(1000) NULL COMMENT '模块类型备注',
  `creator` BIGINT(64) UNSIGNED NULL COMMENT '创建人',
  `createTime` DATETIME NULL DEFAULT now() COMMENT '创建时间',
  PRIMARY KEY (`moduleTypeId`),
  UNIQUE INDEX `moduleTypeName_UNIQUE` (`moduleTypeName` ASC))
COMMENT = '模块表单 __module_type__';

-- 应用模块表单

CREATE TABLE `table_index` (
  `tableId` BIGINT(64) UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '表单编号',
  `tableViewName` VARCHAR(100) NOT NULL COMMENT '表单显示名称',
  `tableDataName` VARCHAR(100) NOT NULL COMMENT '表单存储名称',
  `moduleNo` BIGINT(100) UNSIGNED NULL COMMENT '所属模块',
  `workFlowNo` BIGINT(100) UNSIGNED NULL COMMENT '对应流程',
  `detailCount` INT NULL COMMENT '明细表数量',
  `detailName` VARCHAR(1000) NULL COMMENT '明细表名称',
  `groupCount` INT NULL COMMENT '格式分组数量',
  `groupName` VARCHAR(1000) NULL COMMENT '格式分组名称',
  `remark` VARCHAR(1000) NULL COMMENT '备注',
  `defaultEdit` JSON NULL COMMENT '默认编辑权限',
  `defaultCreate` JSON NULL COMMENT '默认创建权限',
  `defaultDelete` JSON NULL COMMENT '默认删除权限',
  `defaultShare` JSON NULL COMMENT '默认共享权限',
  `creator` BIGINT(64) UNSIGNED NULL COMMENT '创建人',
  `createTime` DATETIME NULL DEFAULT now() COMMENT '创建时间',
  PRIMARY KEY (`tableId`),
  UNIQUE INDEX `tableViewName_UNIQUE` (`tableViewName` ASC),
  UNIQUE INDEX `tableDataName_UNIQUE` (`tableDataName` ASC),
  INDEX `workFlowId_Foreign` (`workFlowNo` ASC) COMMENT '流程搜索加速索引',
  INDEX `ModuleNo_Foreign` (`moduleNo` ASC) COMMENT '模块搜索加速索引')
COMMENT = '表单数据索引表（table_index）';

CREATE TABLE `table_column_index` (
  `columnId` BIGINT(64) UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '字段id',
  `columnViewName` VARCHAR(100) NOT NULL COMMENT '字段显示名称',
  `columnDataName` VARCHAR(100) NOT NULL COMMENT '字段数据库存储名称',
  `columnType` VARCHAR(100) NOT NULL COMMENT '字段类型',
  `columnTypeDescription` VARCHAR(1000) NULL COMMENT '附带格式描述',
  `tableNo` BIGINT(64) UNSIGNED NOT NULL COMMENT '对应存储数据库表',
  `columnGroupNo` INT NULL DEFAULT -1 COMMENT '格式分组',
  `columnDetailNo` INT NULL DEFAULT -1 COMMENT '明细字段分组',
  `columnViewNo` INT NULL COMMENT '顺序序号',
  `creator` BIGINT(64) UNSIGNED NULL COMMENT '创建人',
  `createTime` DATETIME NULL DEFAULT now() COMMENT '创建时间',
  PRIMARY KEY (`columnId`),
  INDEX `tableNo_FOREIGN` (`tableNo` ASC) comment '所属表单索引加速'
) COMMENT = '字段数据索引表（table_column_index）';

CREATE TABLE `table_view_index` (
  `tableId` BIGINT(64) UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '表单编号',
  `tableViewName` VARCHAR(100) NOT NULL COMMENT '表单显示名称',
  `tableDataName` VARCHAR(100) NOT NULL COMMENT '表单存储名称',
  `moduleNo` BIGINT(100) UNSIGNED NULL COMMENT '所属模块',
  `groupCount` INT NULL COMMENT '格式分组数量',
  `groupName` VARCHAR(1000) NULL COMMENT '格式分组名称',
  `remark` VARCHAR(1000) NULL COMMENT '备注',
  `creator` BIGINT(64) UNSIGNED NULL COMMENT '创建人',
  `createTime` DATETIME NULL DEFAULT now() COMMENT '创建时间',
  PRIMARY KEY (`tableId`),
  UNIQUE INDEX `tableViewName_UNIQUE` (`tableViewName` ASC),
  UNIQUE INDEX `tableDataName_UNIQUE` (`tableDataName` ASC),
  INDEX `ModuleNo_Foreign` (`moduleNo` ASC) COMMENT '模块搜索加速索引')
COMMENT = '表单数据索引表（table_index）';

CREATE TABLE `table_view_column_index` (
  `columnId` BIGINT(64) UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '字段id',
  `columnViewName` VARCHAR(100) NOT NULL COMMENT '字段显示名称',
  `columnDataName` VARCHAR(100) NOT NULL COMMENT '字段数据库存储名称',
  `columnType` VARCHAR(100) NOT NULL COMMENT '字段类型',
  `columnTypeDescription` VARCHAR(1000) NULL COMMENT '附带格式描述',
  `tableNo` BIGINT(64) UNSIGNED NOT NULL COMMENT '对应存储数据库表',
  `columnGroupNo` INT NULL DEFAULT -1 COMMENT '格式分组',
  `columnViewNo` INT NULL COMMENT '顺序序号',
  `creator` BIGINT(64) UNSIGNED NULL COMMENT '创建人',
  `createTime` DATETIME NULL DEFAULT now() COMMENT '创建时间',
  `columnViewDisplay` TINYINT NULL DEFAULT 1 COMMENT '展示时是否显示(0否，1是)',
  PRIMARY KEY (`columnId`),
  INDEX `tableNo_FOREIGN` (`tableNo` ASC) comment '所属表单索引加速'
)COMMENT = '字段数据索引表（table_column_index）';

-- 表单模块创建基础表单

create view module_view as (select *, 1 as tableCounts, 1 as flowCounts, 1 as searchCounts, 1 as chartsCounts  from module_type);
-- 模块应用查看视图

create table `human_resource` (
    `dataId` BIGINT(64) UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '数据编号,人员唯一id',
    `loginName` varchar(100) NOT NULL COMMENT '登录名',
    `password` varchar(100) NOT NULL COMMENT '密码',
    `name` varchar(100) not null comment '姓名',
    `sex` int null comment '性别,0男1女',
    `birth` datetime null comment '出生年月',
    `telephone` varchar(100) null comment '手机号',
    `mail` varchar(100) null comment '邮箱',
    `phone` varchar(100) null comment '固话',
    `fax` varchar(100) null comment '传真',
    `workCode` varchar(100) null comment  '工号',
    `section` bigint(64) unsigned not null comment '分部编号',
    `depart` bigint(64) unsigned not null comment '部门编号',
    `job` varchar(100) null comment '岗位',
    `directorLeader` bigint(64) unsigned not null comment '直属领导',
    `supporter` bigint(64) unsigned not null comment '助理',
    `photo` bigint(64) unsigned not null comment '头像文件编号',
    `signature` varchar(100) null comment '个性签名',
    `lastLogin` datetime null comment '最后登录时间',
    `safety` INTEGER null default 0 comment '安全等级',
    PRIMARY KEY (`dataId`),
    UNIQUE INDEX `loginName_Unique` (`loginName` ASC),
    INDEX `section_FOREIGN` (`section` ASC),
    INDEX `depart_FOREIGN` (`depart` ASC),
    INDEX `directorLeader_FOREIGN` (`directorLeader` ASC)
) comment = '人员索引表,构建对应虚拟视图';
-- 人员表单

create view `human_view` as (
    select *,TIMESTAMPDIFF(YEAR, birth, CURDATE()) as age from human_resource
);
-- 人员添加年龄数据查看视图

-- 组织权限表单

create table `depart_resource` (
    `dataId` BIGINT(64) UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '数据编号,部门唯一id',
    `departName` varchar(100) null comment  '部门名称',
    `departCode` varchar(100) null comment  '部门编号',
    `fullName` varchar(100) null comment  '部门全称',
    `belongDepart` BIGINT(64) UNSIGNED NOT NULL comment  '上级部门',
    `belongSection` BIGINT(64) UNSIGNED NOT NULL comment  '上级分部',
    `departManager` BIGINT(64) UNSIGNED NOT NULL comment  '部门负责人',
    `departIntroduction` varchar(1000) NULL comment  '上级介绍',
    `createTime` datetime null default now() comment '创建时间',
    `photo` BIGINT(64) UNSIGNED NOT NULL comment  '照片文件编号',
    PRIMARY KEY (`dataId`),
    unique INDEX `departCode_Unique` (`departCode` ASC)
) comment = '部门表单';

create table `section_resource` (
    `dataId` BIGINT(64) UNSIGNED NOT NULL COMMENT '数据编号,部门唯一id',
    `sectionName` varchar(100) null comment  '部门名称',
    `sectionCode` varchar(100) null comment  '部门编号',
    `fullName` varchar(100) null comment  '部门全称',
    `belongSection` BIGINT(64) UNSIGNED NOT NULL comment  '上级分部',
    `sectionManager` BIGINT(64) UNSIGNED NOT NULL comment  '部门负责人',
    `sectionIntroduction` varchar(1000) NULL comment  '上级介绍',
    `createTime` datetime null default now() comment '创建时间',
    `photo` BIGINT(64) UNSIGNED NOT NULL comment  '照片文件编号',
    PRIMARY KEY (`dataId`),
    UNIQUE INDEX `sectionName_Unique` (`sectionName` ASC),
    UNIQUE INDEX `sectionCode_Unique` (`sectionCode` ASC)
) comment = '分部表单';

create table `character` (
    `dataId` BIGINT(64) UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '数据编号,角色唯一id',
    `characterName` varchar(100) null comment  '角色名称',
    `characterDescription` varchar(100) null comment  '角色描述',
    `createTime` datetime null default now() comment '创建时间',
    `creator` BIGINT(64) UNSIGNED NOT NULL COMMENT '创建者id',
    PRIMARY KEY (`dataId`),
    UNIQUE INDEX `characterName_Unique` (`characterName` ASC)
) comment = '角色表单';

create table `character_human` (
    `humanId` BIGINT(64) UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '数据编号,人员唯一id',
    `characterId` BIGINT(64) UNSIGNED NOT NULL COMMENT '数据编号,角色唯一id',
    `grade` int not null default 0 comment '角色作用级别',
    PRIMARY KEY (`humanId`,`characterId`)
) comment = '角色人员索引表';

create table `authority` (
    `dataId` BIGINT(64) UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '数据编号,权限唯一id',
    `authorityName` varchar(100) null comment  '权限名称',
    `authorityDescription` varchar(100) null comment  '权限描述',
    `authorityRemark` varchar(100) null comment  '权限备注,用来描述那些角色或人员拥有该权限',
    PRIMARY key (`dataId`)
) comment = '后端操作权限表';

create table `authority_character` (
    `characterId` BIGINT(64) UNSIGNED NOT NULL COMMENT '数据编号,角色唯一id',
    `authorityId` BIGINT(64) UNSIGNED NOT NULL COMMENT '数据编号,权限唯一id',
    PRIMARY KEY (`characterId`,`authorityId`)
) comment = '角色权限索引表';

create table `authority_human` (
    `humanId` BIGINT(64) UNSIGNED NOT NULL COMMENT '数据编号,人员唯一id',
    `authorityId` BIGINT(64) UNSIGNED NOT NULL COMMENT '数据编号,权限唯一id',
    PRIMARY KEY (`humanId`,`authorityId`)
) comment = '人员权限索引表';

-- 文件目录表单

create table `file_storage` (
    `dataId` BIGINT(64) UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '数据编号,文件唯一id',
    `isDeprecated` tinyInt null default 0 comment '0否1是废弃',
    `fileName` varchar(100) null comment  '文件名',
    `fileRoute` varchar(1000) not null comment  '文件路径',
    `creator`  BIGINT(64) UNSIGNED NOT NULL COMMENT '创建人唯一id',
    `createTime` datetime null comment  '创建时间',
    `editAuthority` json null comment  '编辑权限描述',
    `viewAuthority` json null comment  '查看权限描述',
    `deleteAuthority` json null comment  '删除权限描述',
    `leadContent` BIGINT(64) UNSIGNED NOT NULL COMMENT '数据编号,所在目录唯一id,null',
    primary key (`dataId`),
    INDEX `leadContent_FOREIGN` (`leadContent` ASC)
) COMMENT = '文件存储';

create table `content_list` (
    `dataId` BIGINT(64) UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '数据编号,目录唯一id',
    `isDeprecated` tinyInt null default 0 comment '0否1是废弃',
    `contentName` varchar(100) null comment  '目录名',
    `contentRemark` varchar(1000) not null comment  '目录描述',
    `creator`  BIGINT(64) UNSIGNED NOT NULL COMMENT '创建人唯一id',
    `createTime` datetime null comment  '创建时间',
    `defaultEdit` json null comment  '编辑权限描述',
    `defaultCreate` json null comment  '创建权限描述',
    `defaultDelete` json null comment  '删除权限描述',
    `defaultShare` json null comment  '共享权限描述',
    `leadContent` BIGINT(64) UNSIGNED NULL COMMENT '数据编号,所在目录唯一id',
    primary key (`dataId`),
    INDEX `leadContent_FOREIGN` (`leadContent` ASC)
) COMMENT = '目录';
