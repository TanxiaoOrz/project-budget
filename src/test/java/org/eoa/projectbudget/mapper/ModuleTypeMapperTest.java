package org.eoa.projectbudget.mapper;

import org.eoa.projectbudget.entity.ModuleType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author 张骏山
 * @Date 2023/10/8 13:57
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: ModuleTypeMapperTest
 * @Description: mybatis-plus使用测试
 * @Version 1.0
 */

@SpringBootTest
class ModuleTypeMapperTest {
    @Autowired
    ModuleTypeMapper moduleTypeMapper;

    /**
     * @Description: 测试插入和读取的正常工作
     */
    @Test
    void insert() {
        ModuleType testEntity = new ModuleType(null, "预算管理", "石化项目预算编制分配管理应用", null, null);
        moduleTypeMapper.insert(testEntity);
        moduleTypeMapper.selectList(null).forEach(System.out::println);

    }
}