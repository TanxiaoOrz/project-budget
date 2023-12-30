package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.eoa.projectbudget.entity.HumanResource;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/10/22 20:06
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: HumanMapper
 * @Description: 人力资源实体查询
 * @Version 1.1
 **/

@Mapper
public interface HumanMapper extends BaseMapper<HumanResource> {

    @Update("update human_resource set lastLogin = now() where dataId = #{dataId}")
    Integer freshLogin(@Param("dataId")Long dataId);

    @Select("select humanId from human_authority_list where authorityId = #{authorityId} or authorityId = 1")
    List<Long> getFromAuthorityIds(@Param("authorityId")Long authorityId);

    @Select("select authorityId from human_authority_list where humanId = #{humanId}")
    List<Integer> getHumansAuthority(@Param("humanId")Long authorityId);
}
