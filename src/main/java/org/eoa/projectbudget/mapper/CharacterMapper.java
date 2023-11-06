package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.eoa.projectbudget.entity.Character;

import java.util.HashMap;
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
