package org.eoa.projectbudget.utils.authority;

import org.eoa.projectbudget.dto.HumanDto;

/**
 * @Author: 张骏山
 * @Date: 2023/10/31 11:13
 * @PackageName: org.eoa.projectbudget.utils.authority
 * @ClassName: AuthoritySolve
 * @Description: 权限组要求解析接口
 * @Version 1.0
 **/
public interface AuthoritySolve {
    /**
     *
     * @param user 判断是否在权限组的角色
     * @param creator 权限保护对象的创建人
     * @return boolean 是否在权限内
     */
    boolean solve(HumanDto user, HumanDto creator);
}
