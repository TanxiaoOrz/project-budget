package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.eoa.projectbudget.entity.Depart;

/**
 * @Author: 张骏山
 * @Date: 2023/11/6 13:01
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: DepartMapper
 * @Description: 部门持久层
 * @Version: 1.0
 **/

@Mapper
public interface DepartMapper extends BaseMapper<Depart> {
    @Update("update depart_resource set isDeprecated = 1 where belongDepart = #{belongDepart}")
    Integer dropBelongDepart(@Param("belongDepart")Long belongDepart);

    @Update("update depart_resource set isDeprecated = 1 where belongSection = #{belongSection}")
    Integer dropBelongSection(@Param("belongSection")Long belongSection);
}
