package org.eoa.projectbudget.utils;

import org.eoa.projectbudget.service.cache.CacheService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

/**
 * @Author: 张骏山
 * @Date: 2023/11/6 17:44
 * @PackageName: org.eoa.projectbudget.utils
 * @ClassName: ChangeFlagUtils
 * @Description: TODO
 * @Version: 1.0
 **/

@Component
public class ChangeFlagUtils implements InitializingBean {

    public static final int HUMAN = 0;
    public static final int MODULE = 1;
    public static final String[] Flags = {"HUMAN","MODUlE"};
    private final HashMap<String, Date> map = new HashMap<>();

    @Autowired
    CacheService cacheService;

    public Date getDate(String flag) {
        Date date = map.get(flag);
        if (date == null) {
            date =new Date(0);
        }
        return date;
    }

    public Date getDate(int flag) {
        return getDate(Flags[flag]);
    }

    public ChangeFlagUtils setDate(String flag, Date date) {
        map.put(flag, date);
        return this;
    }

    public ChangeFlagUtils freshDate(int flag) {
        return setDate(Flags[flag],new Date());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Date date = new Date();
        for (String flag:
             Flags) {
            map.put(flag,date);
        }
    }
}
