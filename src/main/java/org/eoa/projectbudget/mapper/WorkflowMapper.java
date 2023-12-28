package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.eoa.projectbudget.entity.Workflow;

/**
 * @Author: 张骏山
 * @Date: 2023/12/28 9:28
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: WorkflowMapper
 * @Description: 工作流持久层接口
 * @Version: 1.0
 **/

@Mapper
public interface WorkflowMapper extends BaseMapper<Workflow> {
}
