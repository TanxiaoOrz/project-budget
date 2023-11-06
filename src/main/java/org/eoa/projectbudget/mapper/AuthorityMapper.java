package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.eoa.projectbudget.entity.Authority;


/**
 * @Author: 张骏山
 * @Date: 2023/11/6 13:02
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: AuthorityMapper
 * @Description: 权限持久层
 * @Version: 1.0
 **/

@Mapper
public interface AuthorityMapper extends BaseMapper<Authority> {
}
