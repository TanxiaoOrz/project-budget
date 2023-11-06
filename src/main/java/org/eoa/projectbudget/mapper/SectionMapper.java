package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.eoa.projectbudget.entity.Section;

/**
 * @Author: 张骏山
 * @Date: 2023/11/6 13:02
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: SectionMapper
 * @Description: 分部持久层
 * @Version: 1.0
 **/

@Mapper
public interface SectionMapper extends BaseMapper<Section> {

    @Select("select belongSection from  section_resource where dataId = #{dataId}")
    Long getBelong(@Param("dataId")Long dataId);

}
