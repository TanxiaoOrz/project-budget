package org.eoa.projectbudget.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import org.eoa.projectbudget.entity.Character;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.utils.DataProcessUtils;

/**
 * @Author: 张骏山
 * @Date: 2023/12/17 20:05
 * @PackageName: org.eoa.projectbudget.vo.in
 * @ClassName: CharacterIn
 * @Description: 角色传入结构体
 * @Version: 1.0
 */
@Schema(name = "CharacterIn", description = "角色传入结构体")
public class CharacterIn implements CheckParameter<Character>{

    String characterName;
    String characterDescription;


    @Override
    public void checkSelf() throws ParameterException {
        if (DataProcessUtils.isEmpty(characterName))
            throw new ParameterException("characterName","","缺少角色名");
    }

    @Override
    public Character toEntity(Long dataId) throws ParameterException {
        checkSelf();

        Character character = new Character();

        character.setDataId(dataId)
                .setCharacterName(characterName)
                .setCharacterDescription(characterDescription);

        return character;
    }

    public String getCharacterName() {
        return characterName;
    }

    public CharacterIn setCharacterName(String characterName) {
        this.characterName = characterName;
        return this;
    }

    public String getCharacterDescription() {
        return characterDescription;
    }

    public CharacterIn setCharacterDescription(String characterDescription) {
        this.characterDescription = characterDescription;
        return this;
    }
}
