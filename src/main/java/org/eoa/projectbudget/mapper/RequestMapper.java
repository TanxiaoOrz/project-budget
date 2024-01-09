package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.eoa.projectbudget.entity.Request;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/12/28 10:45
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: RequestMapper
 * @Description: 请求持久层
 * @Version: 1.1
 **/

@Mapper
public interface RequestMapper extends BaseMapper<Request> {

    @Select("select nodeId from request_view_authority where requestId = #{requestId} and humanId = #{humanId} limit 1")
    Long getViewNode(@Param("requestId")Long requestId, @Param("humanId")Long humanId);

    @Delete("delete from request_backlog where humanId = #{humanId} and requestId = #{requestId}")
    Integer doRequest(@Param("humanId")Long humanId, @Param("requestId")Long requestId);

    @Delete("delete from request_backlog where requestId = #{requestId}")
    Integer doRequestAll(@Param("requestId")Long requestId);

    @Insert("insert into request_done (humanId, requestId, nodeId, workflowId) VALUES (#{humanId}, #{requestId}, #{nodeId}, #{workflowId})")
    void addDone(@Param("humanId")Long humanId, @Param("requestId")Long requestId, @Param("nodeId")Long nodeId, @Param("workflowId")Long workflowId);

    @Update("update request_done set nodeId = #{nodeId} , doneTime = now() where requestId = #{requestId} and humanId = #{humanId}")
    void updateDone(@Param("humanId")Long humanId, @Param("requestId")Long requestId, @Param("nodeId")Long nodeId);

    void newBacklogs(@Param("humans")List<Long> humans, @Param("requestId")Long requestId, @Param("nodeId")Long nodeId, @Param("workflowId")Long workflowId);

    @Delete("delete from request_backlog where requestId = #{requestId}")
    Integer deleteDones(@Param("requestId")Long requestId);
}
