package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.eoa.projectbudget.entity.RequestBacklogView;

/**
 * @Author: 张骏山
 * @Date: 2023/12/28 10:50
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: RequestBacklogMapper
 * @Description: 已办请求持久层接口
 * @Version: 1.0
 **/

@Mapper
public interface RequestBacklogMapper extends BaseMapper<RequestBacklogView> {
}
