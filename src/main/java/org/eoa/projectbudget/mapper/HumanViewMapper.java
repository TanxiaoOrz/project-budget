package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.eoa.projectbudget.entity.HumanResourceView;

/**
 * @Author: 张骏山
 * @Date: 2023/10/22 20:07
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: HumanViewMapper
 * @Description: 人力资源视图查询
 * @Version 1.0
 **/

@Mapper
public interface HumanViewMapper extends BaseMapper<HumanResourceView> {

    @Select("select directorLeader from human_resource where dataId = #{dataId}")
    Long getLeader(@Param("dataId")Long humanId);
}
