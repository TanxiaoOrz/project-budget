package org.eoa.projectbudget.utils.factory;

import org.eoa.projectbudget.dto.MenuDto;
import org.eoa.projectbudget.entity.HumanResource;
import org.eoa.projectbudget.entity.Menu;
import org.eoa.projectbudget.mapper.HumanMapper;
import org.eoa.projectbudget.mapper.MenuMapper;
import org.eoa.projectbudget.vo.out.MenuDtoOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 张骏山
 * @Date: 2024/1/17 16:19
 * @PackageName: org.eoa.projectbudget.utils.factory
 * @ClassName: MenuDtoOutFactory
 * @Description: 菜单结构输出工厂
 * @Version: 1.0
 **/
@Component
public class MenuDtoOutFactory implements OutFactory<MenuDto, MenuDtoOut> {
    @Autowired
    HumanMapper humanMapper;
    @Autowired
    MenuMapper menuMapper;

    @Override
    public MenuDtoOut out(MenuDto menuDto) {
        if (menuDto == null) {
            return null;
        }
        MenuDtoOut menuDtoOut = new MenuDtoOut(menuDto);
        HumanResource humanResource = humanMapper.selectById(menuDto.getCreator());
        Menu belongs;
        if (menuDto.getBelongContent() != null)
            belongs = menuMapper.selectById(menuDto.getBelongContent());
        else
            belongs = null;
        return menuDtoOut.setCreatorName(humanResource==null?"":humanResource.getName())
                .setBelongContentName(belongs == null ? "" : belongs.getContentName())
                .setChildren(outs(menuDto.getChildren()));
    }

    @Override
    public List<MenuDtoOut> outs(List<? extends MenuDto> menuDtos) {
        if (menuDtos == null) {
            return null;
        }
        return menuDtos.stream().map(this::out).collect(Collectors.toList());
    }
}