package org.eoa.projectbudget.vo.out;

import io.swagger.v3.oas.annotations.media.Schema;
import org.eoa.projectbudget.entity.Character;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/11/27 16:55
 * @PackageName: org.eoa.projectbudget.vo.out
 * @ClassName: CharacterOut
 * @Description: 角色输出类结构体
 * @Version: 1.0
 **/

@Schema(name = "CharacterOut", description = "角色输出类结构体")
public class CharacterOut implements VoOut{
    @Schema(description = "唯一编号")
    Long dataId;
    @Schema(description = "角色名称,browserId=0")
    String characterName;
    @Schema(description = "角色描述,browserId=1")
    String characterDescription;
    @Schema(description = "创建时间")
    Date createTime;
    @Schema(description = "创建者")
    Long creator;
    @Schema(description = "创建者名称")
    String createName;

    public CharacterOut() {
    }

    public CharacterOut(Character character) {
        this.dataId = character.getDataId();
        this.characterName = character.getCharacterName();
        this.characterDescription = character.getCharacterDescription();
        this.createTime = character.getCreateTime();
        this.creator = character.getCreator();
    }

    public String getCharacterName() {
        return characterName;
    }

    public CharacterOut setCharacterName(String characterName) {
        this.characterName = characterName;
        return this;
    }

    public String getCharacterDescription() {
        return characterDescription;
    }

    public CharacterOut setCharacterDescription(String characterDescription) {
        this.characterDescription = characterDescription;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public CharacterOut setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getCreator() {
        return creator;
    }

    public CharacterOut setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    public String getCreateName() {
        return createName;
    }

    public CharacterOut setCreateName(String createName) {
        this.createName = createName;
        return this;
    }

    public Long getDataId() {
        return dataId;
    }

    public CharacterOut setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public CharacterOut(Long dataId, String characterName, String characterDescription, Date createTime, Long creator, String createName) {
        this.dataId = dataId;
        this.characterName = characterName;
        this.characterDescription = characterDescription;
        this.createTime = createTime;
        this.creator = creator;
        this.createName = createName;
    }

    @Override
    public void toBrowser(Long browserId) {
        VoOut.super.toBrowser(browserId);

        this.characterName = browserId!=0?null:characterName;
        this.characterDescription = browserId!=1?null:characterDescription;
        this.createTime = null;
        this.creator = null;
        this.createName = null;
    }
}
