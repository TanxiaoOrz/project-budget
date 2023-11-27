package org.eoa.projectbudget.utils.factory;

import org.eoa.projectbudget.entity.Character;
import org.eoa.projectbudget.mapper.HumanMapper;
import org.eoa.projectbudget.vo.out.CharacterOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 张骏山
 * @Date: 2023/11/27 17:07
 * @PackageName: org.eoa.projectbudget.utils.factory
 * @ClassName: CharacterOutFactory
 * @Description: 角色输出结构体构造工厂
 * @Version: 1.0
 **/

@Component
public class CharacterOutFactory implements OutFactory<Character, CharacterOut>{
    @Autowired
    HumanMapper humanMapper;

    @Override
    public CharacterOut out(Character character) {
        return new CharacterOut(character)
                .setCreateName(humanMapper.selectById(character.getCreator()).getName());
    }

    @Override
    public List<CharacterOut> outs(List<? extends Character> characters) {
        return characters.stream().map(this::out).collect(Collectors.toList());
    }
}
