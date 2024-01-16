package org.eoa.projectbudget.service.menu;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.dto.MenuDto;
import org.eoa.projectbudget.entity.Menu;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2024/1/16 22:37
 * @PackageName: org.eoa.projectbudget.service.menu
 * @ClassName: MenuService
 * @Description: 页面菜单持久层
 * @Version: 1.0
 */
public interface MenuService {

    /**
     * 获取菜单
     *
     * @param dataId 菜单编号
     * @param userId  用户编号
     * @return 菜单实体类
     */
    Menu getMenu(Long dataId, Long userId);

    /**
     * 获取菜单数组
     *
     * @param wrapper 查询条件
     * @param userId  用户id
     * @return 菜单实体类数组
     */
    List<Menu> getMenuList(QueryWrapper<Menu> wrapper, Long userId);

    /**
     * 新建菜单
     *
     * @param menu 菜单数据
     * @param userId 用户id
     * @return 图标编号
     */
    Long newMenu(Menu menu, Long userId);

    /**
     * 更新菜单
     *
     * @param menu 菜单数据
     * @param userId 用户编号
     * @return 更新数量
     */
    Integer updateMenu(Menu menu, Long userId);

    /**
     * 删除菜单
     *
     * @param dataId 菜单编号
     * @param userId 用户编号
     * @return 更新数量
     */
    Integer deleteMenu(Long dataId, Long userId);

    /**
     * 获得顶级菜单完整树信息
     * @param dateId 根菜单编号
     * @param userId 用户编号
     * @return menuDto
     */
    MenuDto getMenuDto(Long dateId, Long userId);
}
