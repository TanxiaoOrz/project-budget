package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.eoa.projectbudget.entity.Character;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/11/6 13:00
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: CharacterMapper
 * @Description: 角色持久层
 * @Version: 1.0
 **/

@Mapper
public interface CharacterMapper extends BaseMapper<Character> {

    @Select("select characterId,grade from `character_human` where humanId = #{humanId}")
    List<Grade> getCharacterIdFromHuman(@Param("humanId")Long humanId);

    @Insert("insert into character_human (humanId, characterId, grade) VALUES (#{userId},#{characterId},#{grade})")
    Integer linkHuman(@Param("characterId")Long characterId, @Param("userId") Long userId, @Param("grade")Integer grade);

    @Delete("delete from character_human where characterId = #{characterId}")
    Integer dropHuman(@Param("characterId")Long characterId, @Param("userId") Long userId);

    @Delete("delete from character_human where humanId = #{userId} and characterId = #{characterId}")
    Integer dropHumanAll(@Param("characterId")Long characterId);

    @Insert("insert into authority_character (characterId, authorityId) VALUES (#{characterId}, #{authorityId})")
    Integer linkAuthority(@Param("characterId")Long characterId, @Param("authorityId")Long authorityId);

    @Delete("delete from authority_character where authorityId = #{authorityId} and characterId = #{characterId}")
    Integer dropAuthority(@Param("characterId")Long characterId, @Param("authorityId")Long authorityId);

    @Delete("delete from authority_character where authorityId = #{authorityId}")
    Integer dropAuthorityAll(@Param("characterId")Long characterId);

    public class Grade {
        Long characterId;
        Integer grade;

        public Long getCharacterId() {
            return characterId;
        }

        public Grade setCharacterId(Long characterId) {
            this.characterId = characterId;
            return this;
        }

        public Integer getGrade() {
            return grade;
        }

        public Grade setGrade(Integer grade) {
            this.grade = grade;
            return this;
        }
    }
}
