package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

    /**
     * 获取搜索表单下新viewNo
     * @param belongContent 所属菜单编号
     * @return viewNo
     */
    @Select("select ifnull(max(viewNo),0)+1 from menu_base where belongContent = #{belongContent}")
    Integer getViewNoNew(@Param("belongContent")Long belongContent);

    /**
     * 获取搜索表单下新viewNo
     * @return viewNo
     */
    @Select("select ifnull(max(viewNo),0)+1 from menu_base where belongContent is null")
    Integer getViewNoNew();
}
