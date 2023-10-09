package org.eoa.projectbudget.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author 张骏山
 * @Date 2023/10/9 15:34
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: TableJmsMapperTest
 * @Description: TODO
 * @Version 1.0
 */

@SpringBootTest
class TableJmsMapperTest {
    @Autowired
    TableJmsMapper tableJmsMapper;

    @Test
    void mainTest() {
        tableJmsMapper.createTable("main_table_1",TableJmsMapper.MAIN);
    }

    @Test
    void mainDetail() {
        tableJmsMapper.createTable("main_table_1_dt_1",TableJmsMapper.DETAIL);
    }
}