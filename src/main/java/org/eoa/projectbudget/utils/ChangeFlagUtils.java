package org.eoa.projectbudget.utils;

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
public class ChangeFlagUtils {

    private HashMap<String, Date> map;

    public Date getDate(String flag) {
        Date date = map.get(flag);
        if (date == null) {
            date =new Date(0);
        }
        return date;
    }

    public ChangeFlagUtils setDate(String flag, Date date) {
        map.put(flag, date);
        return this;
    }
}
