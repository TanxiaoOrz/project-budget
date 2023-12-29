package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
    
    @Select("select max(viewNo) + 1 from workflow_node where workflowId = #{workflowId};")
    Integer getViewNo(@Param("workflowId")Long workflowId);

    @Select("select count(dataId) from workflow_node where dataId != #{dataId} and workflowId = #{workflowId} and nodeType = 0")
    Integer getCreateNodeElseCounts(@Param("workflowId")Long workflowId, @Param("dataId")Long dataId);

}
