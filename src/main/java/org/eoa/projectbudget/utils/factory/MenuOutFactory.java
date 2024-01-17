package org.eoa.projectbudget.utils.factory;

import org.eoa.projectbudget.entity.HumanResource;
import org.eoa.projectbudget.entity.Menu;
import org.eoa.projectbudget.mapper.HumanMapper;
import org.eoa.projectbudget.mapper.MenuMapper;
import org.eoa.projectbudget.vo.out.MenuOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 张骏山
 * @Date: 2024/1/17 16:12
 * @PackageName: org.eoa.projectbudget.utils.factory
 * @ClassName: MenuOutFactory
 * @Description: 页面菜单输出工厂
 * @Version: 1.0
 **/
@Component
public class MenuOutFactory implements OutFactory<Menu, MenuOut> {
    @Autowired
    HumanMapper humanMapper;
    @Autowired
    MenuMapper menuMapper;

    @Override
    public MenuOut out(Menu menu) {
        if (menu == null) {
            return null;
        }
        MenuOut menuOut = new MenuOut(menu);
        HumanResource humanResource = humanMapper.selectById(menu.getCreator());
        Menu belongs;
        if (menu.getBelongContent() != null)
            belongs = menuMapper.selectById(menu.getBelongContent());
        else
            belongs = null;
        return menuOut.setCreatorName(humanResource == null ? "" : humanResource.getName())
                .setBelongContentName(belongs == null ? "" : belongs.getContentName());
    }

    @Override
    public List<MenuOut> outs(List<? extends Menu> menus) {
        if (menus == null) {
            return null;
        }
        return menus.stream().map(this::out).collect(Collectors.toList());
    }
}
