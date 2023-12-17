package org.eoa.projectbudget.service.organization_module;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.entity.Authority;
import org.eoa.projectbudget.entity.Character;
import org.eoa.projectbudget.exception.LoginException;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.mapper.CharacterMapper;
import org.eoa.projectbudget.vo.Tokens;

import java.util.List;

/**
 * @Author 张骏山
 * @Date 2023/10/27 16:53
 * @PackageName: org.eoa.projectbudget.service
 * @ClassName: AuthorityService
 * @Description: 权限处理业务类
 * @Version 1.5
 */
public interface  AuthorityService {
    /**
     * 获取指定权限
     * @param authorityId 权限id
     * @param userId 操作人
     * @return 权限
     */
    Authority getAuthorityById(Long authorityId, Long userId);

    /**
     * 权限列表
     * @param wrapper 筛选器
     * @param userId 操作人
     * @return 权限
     */
    List<Authority> getAllAuthority(QueryWrapper<Authority> wrapper, Long userId);

    /**
     * 获取角色对象
     * @param characterId 角色编号
     * @param userId 用户编号
     * @return 角色
     */
    Character getCharacterById(Long characterId, Long userId);

    /**
     * 获取所有角色
     * @param userId 操作用户
     * @return 角色数组
     */
    List<Character> getAllCharacter(QueryWrapper<Character> wrapper, Long userId);

    /**
     * 新建角色
     * @param character 角色
     * @param userId 操作用户
     * @return 结果数字
     */
    Long newCharacter(Character character, Long userId);

    /**
     * 修改角色
     * @param character 角色
     * @param userId 操作用户
     * @return 结果数字
     */
    Integer updateCharacter(Character character, Long userId);

    /**
     * 删除角色
     * @param characterId 角色
     * @param userId 操作用户
     * @return 结果数字
     */
    Integer dropCharacter(Long characterId, Long userId);

    /**
     * 连接角色权限
     * @param characterId 角色
     * @param authorityId 权限
     * @param userId 操作人
     * @return 结果数字
     */
    Integer characterLinkAuthority(Long characterId, Long authorityId, Long userId);

    /**
     * 连接用户角色
     * @param characterId 角色
     * @param humanId 用户
     * @param userId 操作人
     * @return 结果数字
     */
    Integer characterLinkUser(Long characterId, Long humanId, Integer grade, Long userId);


    /**
     * 废弃角色权限
     * @param characterId 角色
     * @param authorityId 权限
     * @param userId 操作人
     * @return 结果数字
     */
    Integer characterDropAuthority(Long characterId, Long authorityId, Long userId);

    /**
     * 废弃用户角色
     * @param characterId 角色
     * @param humanId 用户
     * @param userId 操作人
     * @return 结果数字
     */
    Integer characterDropUser(Long characterId,Long humanId, Long userId);


    /**
     * 获取角色下的人员
     * @param characterId 角色编号
     * @param userId 操作人
     * @return 人员数组
     */
    List<CharacterMapper.Grade> getHumansOfCharacter(Long characterId, Long userId);


    /**
     * 获取角色下的权限
     * @param characterId 角色编号
     * @param userId 操作人
     * @return 权限数组
     */
    List<Authority> getAuthorityOfCharacter(Long characterId, Long userId);



    /**
     * 获取tokens
     * @param user 用户
     * @return 字符串数组
     */
    Tokens getTokens(Long user);

    /**
     * 检查token合法性并获取对象
     * @param tokens 用户授权码
     * @return UserWithToken
     */
    UserWithToken getUser(Tokens tokens) throws ParameterException, LoginException;

    class UserWithToken {
        protected boolean update;
        protected Long userId;

        public UserWithToken setUpdate(boolean update) {
            this.update = update;
            return this;
        }

        public UserWithToken setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public boolean isUpdate() {
            return update;
        }

        public Long getUserId() {
            return userId;
        }
    }
}

