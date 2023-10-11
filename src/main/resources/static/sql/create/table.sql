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
  `tableId` BIGINT(64) UNSIGNED NOT NULL COMMENT '表单编号',
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
  INDEX `workFlowId_Foreign_Index` (`workFlowNo` ASC) COMMENT '流程搜索加速索引',
  INDEX `ModuleNo_Foreign_Index` (`moduleNo` ASC) COMMENT '模块搜索加速索引')
COMMENT = '表单数据索引表（table_index）';

CREATE TABLE `table_column_index` (
  `columnId` BIGINT(64) UNSIGNED NOT NULL COMMENT '字段id',
  `columnViewName` VARCHAR(100) NOT NULL COMMENT '字段显示名称',
  `columnDataName` VARCHAR(100) NOT NULL COMMENT '字段数据库存储名称',
  `columnType` VARCHAR(100) NOT NULL COMMENT '字段类型',
  `columnTypeDescription` VARCHAR(1000) NULL COMMENT '附带格式描述',
  `tableNo` BIGINT(64) UNSIGNED NOT NULL COMMENT '对应存储数据库表',
  `columnGroupNo` INT NULL DEFAULT -1 COMMENT '格式分组',
  `columnDetailNo` INT NULL DEFAULT -1 COMMENT '明细字段分组',
  `columnViewNo` INT NULL AUTO_INCREMENT COMMENT '顺序序号',
  `creator` BIGINT(64) UNSIGNED NULL COMMENT '创建人',
  `createTime` DATETIME NULL DEFAULT now() COMMENT '创建时间',
  PRIMARY KEY (`columnId`),
  UNIQUE INDEX `columnViewNo_UNIQUE` (`columnViewNo` ASC))
COMMENT = '字段数据索引表（table_column_index）';

CREATE TABLE `table_view_index` (
  `tableId` BIGINT(64) UNSIGNED NOT NULL COMMENT '表单编号',
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
  INDEX `ModuleNo_Foreign_Index` (`moduleNo` ASC) COMMENT '模块搜索加速索引')
COMMENT = '表单数据索引表（table_index）';

CREATE TABLE `table_view_column_index` (
  `columnId` BIGINT(64) UNSIGNED NOT NULL COMMENT '字段id',
  `columnViewName` VARCHAR(100) NOT NULL COMMENT '字段显示名称',
  `columnDataName` VARCHAR(100) NOT NULL COMMENT '字段数据库存储名称',
  `columnType` VARCHAR(100) NOT NULL COMMENT '字段类型',
  `columnTypeDescription` VARCHAR(1000) NULL COMMENT '附带格式描述',
  `tableNo` BIGINT(64) UNSIGNED NOT NULL COMMENT '对应存储数据库表',
  `columnGroupNo` INT NULL DEFAULT -1 COMMENT '格式分组',
  `columnViewNo` INT NULL AUTO_INCREMENT COMMENT '顺序序号',
  `creator` BIGINT(64) UNSIGNED NULL COMMENT '创建人',
  `createTime` DATETIME NULL DEFAULT now() COMMENT '创建时间',
  `columnViewDisplay` TINYINT NULL DEFAULT 1 COMMENT '展示时是否显示(0否，1是)',
  PRIMARY KEY (`columnId`),
  UNIQUE INDEX `columnViewNo_UNIQUE` (`columnViewNo` ASC))
COMMENT = '字段数据索引表（table_column_index）';

--- 表单模块创建基础表单