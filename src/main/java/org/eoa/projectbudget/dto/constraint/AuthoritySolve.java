package org.eoa.projectbudget.dto.constraint;

import org.eoa.projectbudget.dto.HumanDto;

import java.util.List;

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
     * 判断是否在限制条件内
     * @param user 判断是否在权限组的角色
     * @param creator 权限保护对象的创建人
     * @return boolean 是否在权限内
     */
    boolean solve(HumanDto user, HumanDto creator);

    /**
     * 获取范围内对象
     * @param creator 创建人
     * @return 范围内人力资源数组
     */
    List<Long> get(HumanDto creator);
}
