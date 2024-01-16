package org.eoa.projectbudget.service.menu;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.entity.LoginConfig;
import org.eoa.projectbudget.entity.PageConfig;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2024/1/16 22:37
 * @PackageName: org.eoa.projectbudget.service.menu
 * @ClassName: ConfigService
 * @Description: 设置业务类
 * @Version: 1.0
 */
public interface ConfigService {

    /**
     * 获取登录配置
     *
     * @param dataId 登录配置编号
     * @param userId  用户编号
     * @return 登录配置实体类
     */
    LoginConfig getLoginConfig(Long dataId, Long userId);

    /**
     * 获取登录配置数组
     *
     * @param wrapper 查询条件
     * @param userId  用户id
     * @return 登录配置实体类数组
     */
    List<LoginConfig> getLoginConfigList(QueryWrapper<LoginConfig> wrapper, Long userId);

    /**
     * 新建登录配置
     *
     * @param loginConfig 登录配置数据
     * @param userId 用户id
     * @return 图标编号
     */
    Long newLoginConfig(LoginConfig loginConfig, Long userId);

    /**
     * 更新登录配置
     *
     * @param loginConfig 登录配置数据
     * @param userId 用户编号
     * @return 更新数量
     */
    Integer updateLoginConfig(LoginConfig loginConfig, Long userId);

    /**
     * 删除登录配置
     *
     * @param dataId 登录配置编号
     * @param userId 用户编号
     * @return 更新数量
     */
    Integer deleteLoginConfig(Long dataId, Long userId);

    /**
     * 获取页面配置
     *
     * @param dataId 页面配置编号
     * @param userId  用户编号
     * @return 页面配置实体类
     */
    PageConfig getPageConfig(Long dataId, Long userId);

    /**
     * 获取页面配置数组
     *
     * @param wrapper 查询条件
     * @param userId  用户id
     * @return 页面配置实体类数组
     */
    List<PageConfig> getPageConfigList(QueryWrapper<PageConfig> wrapper, Long userId);

    /**
     * 新建页面配置
     *
     * @param pageConfig 页面配置数据
     * @param userId 用户id
     * @return 图标编号
     */
    Long newPageConfig(PageConfig pageConfig, Long userId);

    /**
     * 更新页面配置
     *
     * @param pageConfig 页面配置数据
     * @param userId 用户编号
     * @return 更新数量
     */
    Integer updatePageConfig(PageConfig pageConfig, Long userId);

    /**
     * 删除页面配置
     *
     * @param dataId 页面配置编号
     * @param userId 用户编号
     * @return 更新数量
     */
    Integer deletePageConfig(Long dataId, Long userId);
}
