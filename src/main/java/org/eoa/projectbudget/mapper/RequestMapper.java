package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.eoa.projectbudget.entity.Request;

/**
 * @Author: 张骏山
 * @Date: 2023/12/28 10:45
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: RequestMapper
 * @Description: 请求持久层
 * @Version: 1.0
 **/

@Mapper
public interface RequestMapper extends BaseMapper<Request> {
}
