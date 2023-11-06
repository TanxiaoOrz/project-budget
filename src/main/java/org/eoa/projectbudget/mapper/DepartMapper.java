package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
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
}
