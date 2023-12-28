package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.eoa.projectbudget.entity.WorkflowRoute;

/**
 * @Author: 张骏山
 * @Date: 2023/12/28 10:43
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: WorkflowRouteMapper
 * @Description: 路径持久层接口
 * @Version: 1.0
 **/

@Mapper
public interface WorkflowRouteMapper extends BaseMapper<WorkflowRoute> {
}
