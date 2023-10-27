package org.eoa.projectbudget.service;

import java.util.List;

/**
 * @Author 张骏山
 * @Date 2023/10/27 16:53
 * @PackageName: org.eoa.projectbudget.service
 * @ClassName: AuthorityService
 * @Description: 权限处理业务类
 * @Version 1.0
 */
public interface AuthorityService {
    /**
     * 回记录权限解析的日志的同时调用
     * @param authority 权限描述字符串
     * @param userId 用户id
     * @return 该用户id是否符合字符串权限要求
     */
    boolean checkAuthority(String authority,Long userId);

    Character getCharacterById(Long characterId, Long userId);

    List<Character> getAllCharacter(Long userId);

    Integer newCharacter(Character character, Long userId);

    Integer updateCharacter(Character character, Long userId);

    Integer dropCharacter(Long characterId, Long userId);

    Integer characterLinkAuthority(Long characterId,Long authorityId);

    Integer characterLinkUser(Long characterId,Long userId);

    Integer userLinkAuthority(Long authorityId,Long userId);

}
