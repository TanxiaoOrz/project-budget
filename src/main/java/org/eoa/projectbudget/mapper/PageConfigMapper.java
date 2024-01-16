package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.eoa.projectbudget.entity.PageConfig;

/**
 * @Author: 张骏山
 * @Date: 2024/1/16 22:47
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: PageConfigMapper
 * @Description: 页面配置持久层
 * @Version: 1.0
 */
@Mapper
public interface PageConfigMapper extends BaseMapper<PageConfig> {
}
