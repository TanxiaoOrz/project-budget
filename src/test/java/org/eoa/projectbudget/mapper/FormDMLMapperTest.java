package org.eoa.projectbudget.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: 张骏山
 * @Date: 2023/11/2 17:09
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: FormDMLMapperTest
 * @Description: FormDMLMapper测试工具
 * @Version: 1.0
 **/
@SpringBootTest
class FormDMLMapperTest {

    @Autowired
    FormDMLMapper formDMLMapper;

    @Test
    void test1() {
        formDMLMapper.test();
    }
}