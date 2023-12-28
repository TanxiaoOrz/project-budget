package org.eoa.projectbudget.utils;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

/**
 * @Author: 张骏山
 * @Date: 2023/11/6 17:44
 * @PackageName: org.eoa.projectbudget.utils
 * @ClassName: ChangeFlagUtils
 * @Description: 缓存控制刷新工具
 * @Version: 1.5
 **/

@Component
public class ChangeFlagUtils {
    /**
     * Flag字符串数组位置常量
     */
    public static final int HUMAN = 0;
    public static final int MODULE = 1;
    public static final int TABLE_ENTITY = 2;
    public static final int TABLE_VIEW = 3;
    public static final int COLUMN_ENTITY = 4;
    public static final int COLUMN_VIEW = 5;
    public static final int CHARACTER = 6;
    public static final int AUTHORITY = 7;
    public static final int CONTENT = 8;
    public static final int FILE = 9;
    public static final int Form = 10;
    public static final int LINK = 11;
    public static final int DEPART = 12;
    public static final int SECTION = 13;
    public static final int WORKFLOW = 14;
    public static final int WORKFLOW_NODE = 15;
    public static final int WORKFLOW_ROUTE = 16;

    public static final String[] Flags = {
            "HUMAN","MODUlE",
            "TABLE_ENTITY","TABLE_VIEW",
            "COLUMN_ENTITY","COLUMN_VIEW",
            "CHARACTER","AUTHORITY",
            "CONTENT","FILE",
            "Form","Link",
            "DEPART","SECTION",
            "WORKFLOW","WORKFLOW_NODE","WORKFLOW_ROUTE"
    };
    private final HashMap<String, Date> flagMap = new HashMap<>();
    private final HashMap<Long, Date> userMap = new HashMap<>();
    /**
     * 初始化时间
     */
    private final Date initialTime = new Date();

    /**
     * 获取flag对应的更新时间
     * @param flag flag字符串
     * @return 更新时间
     */
    public Date getDate(String flag) {
        if (flag == null) {
            return initialTime;
        }
        Date date = flagMap.get(flag);
        if (date == null) {
            date = initialTime;
        }
        return date;
    }

    /**
     * 获取人员对应的更新时间
     * @param userId 人员编号
     * @return 更新时间
     */
    public Date getDate(Long userId) {
        if (userId == null) {
            return initialTime;
        }
        Date date = userMap.get(userId);
        if (date == null) {
            date = initialTime;
        }
        return date;
    }

    /**
     * 获取flag对应的更新时间
     * @param flag 字符串位置常量
     * @return 更新时间
     */
    public Date getDate(int flag) {
        return getDate(Flags[flag]);
    }

    /**
     * 获取flag和userID的双重更新时间
     * @param flag 字符串位置常量
     * @param userId 人员编号
     * @return 最新更新时间
     */
    public Date getDate(int flag,Long userId) {
        Date flagDate = getDate(flag);
        Date userDate = getDate(userId);
        return userDate.before(flagDate)?flagDate:userDate;
    }

    /**
     * 更新flag时间
     * @param flag 字符串位置
     * @return this
     */
    public ChangeFlagUtils freshDate(int flag) {
        Date date = new Date();
        flagMap.put(Flags[flag], date);
        return this;
    }

    /**
     * 更新user时间
     * @param userId 字符串位置
     * @return this
     */
    public ChangeFlagUtils freshDate(Long userId) {
        Date date = new Date();
        userMap.put(userId, date);
        return this;
    }

}
