package org.eoa.projectbudget.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ColumnViewMapperTest {
    @Autowired
    ColumnViewMapper columnViewMapper;

    @Test
    void test() {
        System.out.println(columnViewMapper.selectList(null));
        System.out.println(columnViewMapper.getColumnViewNoNew(1L));
    }
}