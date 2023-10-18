package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.eoa.projectbudget.vo.ModuleOut;

@Mapper
public interface ModuleViewMapper extends BaseMapper<ModuleOut> {
}
