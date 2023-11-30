package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @Author 张骏山
 * @Date 2023/10/27 17:20
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: Character
 * @Description: TODO
 * @Version 1.1
 */

@TableName("`character`")
public class Character {

    @TableId(type = IdType.AUTO)
    Long dataId;
    String characterName;
    String characterDescription;
    Date createTime;
    Long creator;

    public Character() {
    }

    public Character(Long dataId, String characterName, String characterDescription, Date createTime, Long creator) {
        this.dataId = dataId;
        this.characterName = characterName;
        this.characterDescription = characterDescription;
        this.createTime = createTime;
        this.creator = creator;
    }

    public Long getDataId() {
        return dataId;
    }

    public Character setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public String getCharacterName() {
        return characterName;
    }

    public Character setCharacterName(String characterName) {
        this.characterName = characterName;
        return this;
    }

    public String getCharacterDescription() {
        return characterDescription;
    }

    public Character setCharacterDescription(String characterDescription) {
        this.characterDescription = characterDescription;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Character setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getCreator() {
        return creator;
    }

    public Character setCreator(Long creator) {
        this.creator = creator;
        return this;
    }
}
