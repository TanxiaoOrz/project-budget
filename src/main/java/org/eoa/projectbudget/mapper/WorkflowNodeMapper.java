package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.eoa.projectbudget.entity.WorkflowNode;

/**
 * @Author: 张骏山
 * @Date: 2023/12/28 10:37
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: WorkflowNodeMapper
 * @Description: 节点持久层接口
 * @Version: 1.0
 **/

@Mapper
public interface WorkflowNodeMapper extends BaseMapper<WorkflowNode> {
}
