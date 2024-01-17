package org.eoa.projectbudget.service.menu.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.dto.MenuDto;
import org.eoa.projectbudget.entity.Menu;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.mapper.MenuMapper;
import org.eoa.projectbudget.service.menu.MenuService;
import org.eoa.projectbudget.vo.out.MenuOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 张骏山
 * @Date: 2024/1/17 13:41
 * @PackageName: org.eoa.projectbudget.service.menu.impl
 * @ClassName: MenuServiceImpl
 * @Description: 菜单业务类实现
 * @Version: 1.0
 **/

@Service
public class MenuServiceImpl implements MenuService {

    private final Logger log = LoggerFactory.getLogger("MenuModule");
    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuList(QueryWrapper<Menu> wrapper, Long userId) {
        List<Menu> menu = menuMapper.selectList(wrapper);
        log.info("用户+>{}批量获取菜单数据=>{}", userId, menu.size());
        return menu;
    }

    @Override
    public Menu getMenu(Long dataId, Long userId) {
        Menu menu = menuMapper.selectById(dataId);
        if (menu == null) {
            log.warn("用户=>{}获取菜单=>{},success=>{}",userId,dataId, true);
            throw new ParameterException("dataId", dataId.toString(), "不存在该编号");
        }
        log.info("用户=>{}获取菜单=>{},success=>{}", userId, dataId, true);
        return menu;
    }

    @Override
    public Long newMenu(Menu menu, Long userId) {
        if (menu.getViewNo() == null)
            menu.setViewNo(menuMapper.getViewNoNew(menu.getBelongContent()));

        menu.setCreator(userId)
                .setCreateTime(new Date());

        menuMapper.insert(menu);
        log.info("用户=>{}创建菜单=>{}", userId, menu.getDataId());
        return menu.getDataId();
    }

    @Override
    public Integer updateMenu(Menu menu, Long userId) {
        Menu old = menuMapper.selectById(menu.getDataId());
        menu.setCreator(old.getCreator())
                .setCreateTime(old.getCreateTime());
        int i = menuMapper.updateById(menu);
        log.info("用户=>{}修改菜单=>{}", userId, menu.getDataId());
        return i;
    }

    @Override
    public Integer deleteMenu(Long dataId, Long userId) {
        Integer drop = menuMapper.deleteById(dataId);
        log.info("用户=>{}废弃菜单=>{}", userId, dataId);
        return drop;
    }

    @Override
    public MenuDto getMenuDto(Long dataId, Long userId) {
        Menu menu = menuMapper.selectById(dataId);
        if (menu == null) {
            throw new ParameterException("dataId", dataId.toString(), "该菜单编号不存在");
        }
        if (menu.getBelongContent() != null)
            throw new ParameterException("dataId", dataId.toString(), "该菜单不是顶级菜单");
        log.info("用户=>{}获取顶级菜单构造=>{}", userId, dataId);
        MenuDto menuDto = new MenuDto(menu);
        menuDto.setChildren(consistChildren(menu.getBelongContent()));
        return menuDto;
    }

    private List<MenuDto> consistChildren(Long belongId) {
        List<Menu> menus = menuMapper.selectList(new QueryWrapper<Menu>().eq("belongContent", belongId).orderByAsc("viewNo"));
        return menus.stream().map(menu -> new MenuDto(menu).setChildren(consistChildren(menu.getBelongContent()))).collect(Collectors.toList());
    }
}