package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.eoa.projectbudget.entity.ModuleType;

/**
 * @Author 张骏山
 * @Date 2023/10/8 13:48
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: ModuleTypeMapper
 * @Description: 应用模块自动处理器
 * @Version 1.0
 */

@Mapper
public interface ModuleTypeMapper extends BaseMapper<ModuleType> {
}
