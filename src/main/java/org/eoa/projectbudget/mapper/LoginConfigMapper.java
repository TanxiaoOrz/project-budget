package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.eoa.projectbudget.entity.LoginConfig;

/**
 * @Author: 张骏山
 * @Date: 2024/1/16 22:44
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: LoginConfigMapper
 * @Description: 登录设置持久层
 * @Version: 1.0
 */
@Mapper
public interface LoginConfigMapper extends BaseMapper<LoginConfig> {
}
