package org.eoa.projectbudget.mapper;

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
    @Autowired
    FormDDLMapper formDDLMapper;

    @Test
    void mainTest() {
        formDDLMapper.createTable("main_table_1", FormDDLMapper.MAIN);
    }

    @Test
    void mainDetail() {
        formDDLMapper.createTable("main_table_1_dt_1", FormDDLMapper.DETAIL);
    }
}