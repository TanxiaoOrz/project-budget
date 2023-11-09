package org.eoa.projectbudget.utils;

import org.springframework.beans.factory.InitializingBean;
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
    public static final String[] Flags = {"HUMAN"};
    private final HashMap<String, Date> map = new HashMap<>();

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

    public ChangeFlagUtils setDate(int flag, Date date) {
        return setDate(Flags[flag], date);
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
