package org.eoa.projectbudget.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author 张骏山
 * @Date 2023/10/9 14:55
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: TableJmsMapper
 * @Description: 直接运行建表,表单结构修改等操作
 * @Version 1.0
 */

@Component
public class TableJmsMapper {

    public final static int DETAIL = 0;
    public final static int MAIN = 1;
    public final String MAIN_TABLE_CREATE = """
            CREATE TABLE `%s` (
              `dataId` BIGINT(64) UNSIGNED NOT NULL,
              `requestId` BIGINT(64) NOT NULL,
              `requestStatus` INT NULL,
              `creator` BIGINT(64) NULL,
              `createTime` DATETIME NULL,
              `latestEditTime` DATETIME NULL,
              `editAuthority` JSON NULL,
              `viewAuthority` JSON NULL,
              `deleteAuthority` JSON NULL,
              PRIMARY KEY (`dataId`),
              UNIQUE INDEX `dataId_UNIQUE` (`dataId` ASC),
              UNIQUE INDEX `requestId_UNIQUE` (`requestId` ASC));
            """;

    public final String DETAIL_TABLE_CREATE = """
            CREATE TABLE `%s` (
              `detailDataId` BIGINT(64) UNSIGNED NOT NULL,
              `detailMainId` BIGINT(64) NOT NULL,
              PRIMARY KEY (`detailDataId`),
              UNIQUE INDEX `detailDataId_UNIQUE` (`detailDataId` ASC),
              UNIQUE INDEX `detailMainId_UNIQUE` (`detailMainId` ASC));
            """;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Integer createTable(String tableName,int type) {
        String createSql = String.format(type==MAIN?MAIN_TABLE_CREATE:DETAIL_TABLE_CREATE,tableName);
        jdbcTemplate.execute(createSql);
        return 0;
    }

    public Integer alterColumn() {
        return 0;
    }

}
