package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.entity.ColumnView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ColumnViewMapperTest {
    @Autowired
    ColumnViewMapper columnViewMapper;

    @Test
    void test() {
        System.out.println(columnViewMapper.selectList(null));
        System.out.println(columnViewMapper.getColumnViewNoNew(1L));
    }

    @Test
    void toLogString() {
        QueryWrapper<ColumnView> wrapper= new QueryWrapper<>();
        wrapper.eq("columnId",1).between("name",1,2);
        System.out.println("wrapper.getCustomSqlSegment() = " + wrapper.getCustomSqlSegment());
        System.out.println("wrapper.getTargetSql() = " + wrapper.getTargetSql());
        System.out.println("wrapper.getSqlSet() = " + wrapper.getSqlSet());
        System.out.println("wrapper.getSqlComment() = " + wrapper.getSqlComment());
        System.out.println("wrapper.getSqlSegment() = " + wrapper.getSqlSegment());
        System.out.println("wrapper.getSqlFirst() = " + wrapper.getSqlFirst());
        System.out.println("wrapper.getParamNameValuePairs().toString() = " + wrapper.getParamNameValuePairs().toString());
    }
}