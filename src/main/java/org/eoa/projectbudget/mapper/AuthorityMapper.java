package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.eoa.projectbudget.entity.Authority;

import java.util.List;


/**
 * @Author: 张骏山
 * @Date: 2023/11/6 13:02
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: AuthorityMapper
 * @Description: 权限持久层
 * @Version: 1.0
 **/

@Mapper
public interface AuthorityMapper extends BaseMapper<Authority> {

    @Select("select authorityId from authority_character where characterId = #{characterId}")
    List<Integer> getIdsFormCharacter(@Param("characterId")Long character);

    @Select("select authorityId from authority_human where humanId = #{humanId}")
    List<Integer> getIdsFormHuman(@Param("humanId")Long humanId);
}
