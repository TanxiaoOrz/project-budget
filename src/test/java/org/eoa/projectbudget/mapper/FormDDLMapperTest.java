package org.eoa.projectbudget.mapper;

import org.eoa.projectbudget.entity.ColumnEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author 张骏山
 * @Date 2023/10/9 15:34
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: FormDDLMapperTest
 * @Description: TODO
 * @Version 1.0
 */

@SpringBootTest
class FormDDLMapperTest {
    public static final String TEST_MAIN = "main_table_1";
    public static final String TEST_DETAIL = "main_table_1_dt_1";
    public static final String[] TEST_COLUMNS = {"column1","column2","column3"};
    @Autowired
    FormDDLMapper formDDLMapper;

    @Test
    void mainTest() {
        formDDLMapper.createTable(TEST_MAIN, FormDDLMapper.MAIN);
    }

    @Test
    void mainDetail() {
        formDDLMapper.createTable(TEST_DETAIL, FormDDLMapper.DETAIL);
    }

    @Test
    void createColumn() {
        for (String testColumn:
             TEST_COLUMNS) {
            formDDLMapper.createColumn(TEST_MAIN, testColumn, "varchar(100)");
            formDDLMapper.createColumn(TEST_DETAIL, testColumn, "varchar(100)");
        }
    }
}