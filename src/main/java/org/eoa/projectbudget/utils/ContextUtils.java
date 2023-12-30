package org.eoa.projectbudget.utils;

import org.eoa.projectbudget.mapper.CharacterMapper;
import org.eoa.projectbudget.mapper.HumanMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: 张骏山
 * @Date: 2023/12/30 22:53
 * @PackageName: org.eoa.projectbudget.utils
 * @ClassName: ContextUtils
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class ContextUtils implements InitializingBean {
    private static ContextUtils instance;
    public static ContextUtils getInstance() {
        return instance;
    }

    @Autowired
    HumanMapper humanMapper;
    @Autowired
    CharacterMapper characterMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        instance = this;
    }

    public HumanMapper getHumanMapper() {
        return humanMapper;
    }

    public CharacterMapper getCharacterMapper() {
        return characterMapper;
    }
}
