package org.eoa.projectbudget.service.organization_module;

import jakarta.annotation.Nullable;
import org.eoa.projectbudget.dto.HumanDto;

import java.util.List;

/**
 * @Author 张骏山
 * @Date 2023/10/27 16:53
 * @PackageName: org.eoa.projectbudget.service
 * @ClassName: AuthorityService
 * @Description: 权限处理业务类
 * @Version 1.0
 */
public interface  AuthorityService {
    /**
     * 回记录权限解析的日志的同时调用
     * @param authority 权限描述字符串
     * @param userId 用户id
     * @return 该用户id是否符合字符串权限要求
     */
    boolean checkAuthority(String authority, Long userId);

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
    List<Character> getAllCharacter(Long userId);

    /**
     * 新建角色
     * @param character 角色
     * @param userId 操作用户
     * @return 结果数字
     */
    Integer newCharacter(Character character, Long userId);

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
     * @return 结果数字
     */
    Integer characterLinkAuthority(Long characterId, Long authorityId);

    /**
     * 连接用户角色
     * @param characterId 角色
     * @param userId 用户
     * @return 结果数字
     */
    Integer characterLinkUser(Long characterId, Long userId);

    /**
     * 连接用户权限
     * @param authorityId 全新啊
     * @param userId 用户
     * @return 结果数字
     */
    Integer userLinkAuthority(Long authorityId, Long userId);

    /**
     * 获取tokens
     * @param user 用户
     * @return 字符串数组
     */
    String[] getTokens(HumanDto user);

    /**
     * 检查token合法性并获取对象
     * @param tokens 用户授权码
     * @return UserWithToken
     */
    UserWithToken getUser(String[] tokens);

    class UserWithToken {
        String[] tokens;
        HumanDto user;

        public String[] getTokens() {
            return tokens;
        }

        public HumanDto getUser() {
            return user;
        }
    }



}
