package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.eoa.projectbudget.entity.HumanResource;

/**
 * @Author: 张骏山
 * @Date: 2023/10/22 20:06
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: HumanMapper
 * @Description: 人力资源实体查询
 * @Version 1.0
 **/

@Mapper
public interface HumanMapper extends BaseMapper<HumanResource> {

    @Update("update human_resource set lastLogin = now() where dataId = #{dataId}")
    Integer freshLogin(@Param("dataId")Long dataId);
}
