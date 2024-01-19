package org.eoa.projectbudget.service.menu.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.entity.LoginConfig;
import org.eoa.projectbudget.entity.PageConfig;
import org.eoa.projectbudget.exception.DataException;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.mapper.LoginConfigMapper;
import org.eoa.projectbudget.mapper.PageConfigMapper;
import org.eoa.projectbudget.service.menu.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2024/1/17 13:39
 * @PackageName: org.eoa.projectbudget.service.menu.impl
 * @ClassName: ConfigServiceImpl
 * @Description: 配置业务类实现
 * @Version: 1.0
 **/

@Service
public class ConfigServiceImpl implements ConfigService {

    private final Logger log = LoggerFactory.getLogger("MenuModule");

    @Autowired
    LoginConfigMapper loginConfigMapper;
    @Autowired
    PageConfigMapper pageConfigMapper;

    @Override
    public List<LoginConfig> getLoginConfigList(QueryWrapper<LoginConfig> wrapper, Long userId) {
        List<LoginConfig> loginConfig = loginConfigMapper.selectList(wrapper);
        log.info("用户+>{}批量获取登录配置数据=>{}",userId,loginConfig.size());
        return loginConfig;
    }

    @Override
    public LoginConfig getLoginConfig(Long dataId, Long userId) {
        LoginConfig loginConfig = loginConfigMapper.selectById(dataId);
        if (loginConfig == null) {
            log.warn("用户=>{}获取登录配置=>{},success=>{}",userId,dataId, true);
            throw new ParameterException("dataId", dataId.toString(), "不存在该编号");
        }
        log.info("用户=>{}获取登录配置=>{},success=>{}",userId,dataId,true);
        return loginConfig;
    }

    @Override
    public LoginConfig getLoginConfigOnUsed() {
        LoginConfig onUse = loginConfigMapper.selectOne(new QueryWrapper<LoginConfig>().eq("OnUse", 1));
        if (onUse == null) {
            onUse = loginConfigMapper.selectById(1);
        }
        if (onUse == null) {
            log.warn("获取正在使用的登录配置,success=>失败,无符合条件的登录配置");
            throw new DataException("loginConfig", "1", "id / onUse", "1", "不存在该编号且无启用配置");
        }
        log.info("获取正在使用的登录配置=>{}",onUse);
        return onUse;
    }

    @Override
    public Long newLoginConfig(LoginConfig loginConfig, Long userId) {
        loginConfig.setCreator(userId).setCreateTime(new Date());

        loginConfigMapper.insert(loginConfig);
        log.info("用户=>{}创建登录配置=>{}",userId,loginConfig.getDataId());
        return loginConfig.getDataId();
    }

    @Override
    public Integer updateLoginConfig(LoginConfig loginConfig, Long userId) {
        LoginConfig old = loginConfigMapper.selectById(loginConfig.getDataId());

        if (old.getOnUse() == 0 && loginConfig.getOnUse() == 1)
            pageConfigMapper.updateOnUse();
        loginConfig.setCreator(old.getCreator())
                .setCreateTime(old.getCreateTime());
        int i = loginConfigMapper.updateById(loginConfig);
        log.info("用户=>{}修改登录配置=>{}",userId,loginConfig.getDataId());
        return i;
    }

    @Override
    public Integer deleteLoginConfig(Long dataId, Long userId) {
        Integer drop = loginConfigMapper.deleteById(dataId);
        log.info("用户=>{}废弃登录配置=>{}",userId,dataId);
        return drop;
    }


    @Override
    public List<PageConfig> getPageConfigList(QueryWrapper<PageConfig> wrapper, Long userId) {
        List<PageConfig> pageConfig = pageConfigMapper.selectList(wrapper);
        log.info("用户+>{}批量获取页面配置数据=>{}",userId,pageConfig.size());
        return pageConfig;
    }

    @Override
    public PageConfig getPageConfig(Long dataId, Long userId) {
        PageConfig pageConfig = pageConfigMapper.selectById(dataId);
        if (pageConfig == null) {
            log.warn("用户=>{}获取页面配置=>{},success=>{}",userId,dataId, true);
            throw new ParameterException("dataId", dataId.toString(), "不存在该编号");
        }
        log.info("用户=>{}获取页面配置=>{},success=>{}",userId,dataId,true);
        return pageConfig;
    }

    @Override
    public PageConfig getPageConfigOnUsed(Long userId) {
        PageConfig onUse = pageConfigMapper.selectOne(new QueryWrapper<PageConfig>().eq("OnUse", 1));
        if (onUse == null) {
            onUse = pageConfigMapper.selectById(1);
        }
        if (onUse == null) {
            log.warn("获取正在使用的登录配置,success=>失败,无符合条件的登录配置");
            throw new DataException("pageConfig", "1", "id / onUse", "1", "不存在该编号且无启用配置");
        }
        log.info("用户=>{}获取正在使用的页面配置=>{}",userId,onUse);
        return onUse;
    }

    @Override
    public Long newPageConfig(PageConfig pageConfig, Long userId) {
        pageConfig.setCreator(userId).setCreateTime(new Date());

        pageConfigMapper.insert(pageConfig);
        log.info("用户=>{}创建页面配置=>{}",userId,pageConfig.getDataId());
        return pageConfig.getDataId();
    }

    @Override
    public Integer updatePageConfig(PageConfig pageConfig, Long userId) {
        PageConfig old = pageConfigMapper.selectById(pageConfig.getDataId());
        if (old.getOnUse() == 0 && pageConfig.getOnUse() == 1)
            pageConfigMapper.updateOnUse();
        pageConfig.setCreator(old.getCreator())
                .setCreateTime(old.getCreateTime());
        int i = pageConfigMapper.updateById(pageConfig);
        log.info("用户=>{}修改页面配置=>{}",userId,pageConfig.getDataId());
        return i;
    }

    @Override
    public Integer deletePageConfig(Long dataId, Long userId) {
        Integer drop = pageConfigMapper.deleteById(dataId);
        log.info("用户=>{}废弃页面配置=>{}",userId,dataId);
        return drop;
    }


}
