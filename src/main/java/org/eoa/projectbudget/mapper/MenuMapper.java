package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.eoa.projectbudget.entity.Menu;

/**
 * @Author: 张骏山
 * @Date: 2024/1/16 22:43
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: MenuMapper
 * @Description: 页面菜单持久层
 * @Version: 1.0
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
}
