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
    public static final int FORM = 10;
    public static final int LINK = 11;
    public static final int DEPART = 12;
    public static final int SECTION = 13;
    public static final int WORKFLOW = 14;
    public static final int WORKFLOW_NODE = 15;
    public static final int WORKFLOW_ROUTE = 16;
    public static final int REQUEST = 17;
    public static final int SEARCH = 18;
    public static final int SEARCH_COLUMN = 19;
    public static final int CHARTS = 20;
    public static final int SEARCH_DTO = 21;
    public static final int MENU = 22;
    public static final int LOGIN_CONFIG = 23;
    public static final int PAGE_CONFIG = 24;

    public static final String[] FLAGS = {
            "HUMAN", "MODUlE",
            "TABLE_ENTITY", "TABLE_VIEW",
            "COLUMN_ENTITY", "COLUMN_VIEW",
            "CHARACTER", "AUTHORITY",
            "CONTENT", "FILE",
            "Form", "Link",
            "DEPART", "SECTION",
            "WORKFLOW", "WORKFLOW_NODE", "WORKFLOW_ROUTE",
            "REQUEST",
            "SEARCH", "SEARCH_COLUMN", "CHARTS", "SEARCH_DTO",
            "MENU", "LOGIN_CONFIG", "PAGE_CONFIG"
    };

    //数组内子数组序号代表该序号缓存依赖于其位置序号缓存
    public static final int[][] DEPENDS_GROUP = {
            {},
            //HUMAN             0
            {},
            //MODULE            1
            {9},
            //TABLE_ENTITY      2
            {},
            //TABLE_VIEW        3
            {9, 21},
            //COLUMN_ENTITY     4
            {21,},
            //COLUMN_VIEW       5
            {11},
            //CHARACTER         6
            {11},
            //AUTHORITY         7
            {},
            //CONTENT           8
            {10},
            //FILE              9
            {17},
            //FORM              10
            {},
            //LINK              11
            {},
            //DEPART            12
            {},
            //SECTION           13
            {},
            //WORKFLOW          14
            {},
            //WORKFLOW_NODE     15
            {},
            //WORKFLOW_ROUTE    16
            {},
            //REQUEST           17
            {21},
            //SEARCH            18
            {21},
            //SEARCH_COLUMN     19
            {},
            //CHARTS            20
            {},
            //SEARCH_DTO        21
            {},
            //MENU              22
            {},
            //LOGIN_CONFIG      23
            {},
            //PAGE_CONFIG       24
    };
    private final HashMap<String, Date> flagMap = new HashMap<>();
    private final HashMap<Long, Date> userMap = new HashMap<>();
    /**
     * 初始化时间
     */
    private final Date initialTime = new Date();

    /**
     * 获取flag对应的更新时间
     *
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
     *
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
     *
     * @param flag 字符串位置常量
     * @return 更新时间
     */
    public Date getDate(int flag) {
        return getDate(FLAGS[flag]);
    }

    /**
     * 获取flag和userID的双重更新时间
     *
     * @param flag   字符串位置常量
     * @param userId 人员编号
     * @return 最新更新时间
     */
    public Date getDate(int flag, Long userId) {
        Date flagDate = getDate(flag);
        Date userDate = getDate(userId);
        return userDate.before(flagDate) ? flagDate : userDate;
    }

    /**
     * 获取flag和userID的双重更新时间
     *
     * @param flag   字符串位置常量
     * @param userId 人员编号
     * @return 最新更新时间
     */
    public Date getDate(String flag, Long userId) {
        Date flagDate = getDate(flag);
        Date userDate = getDate(userId);
        return userDate.before(flagDate) ? flagDate : userDate;
    }

    /**
     * 更新flag时间
     *
     * @param flag 字符串位置
     * @return this
     */
    public ChangeFlagUtils freshDate(int flag) {
        Date date = new Date();
        flagMap.put(FLAGS[flag], date);
        for (int belongs :
                DEPENDS_GROUP[flag]) {
            freshDate(belongs);
        }
        return this;
    }

    /**
     * 更新flag时间
     *
     * @param flag 字符串
     * @return this
     */
    public ChangeFlagUtils freshDate(String flag) {
        Date date = new Date();
        flagMap.put(flag, date);
        int i = searchPosition(flag);
        if (i != -1)
            for (int belongs :
                    DEPENDS_GROUP[i]) {
                freshDate(belongs);
            }
        return this;
    }

    /**
     * 更新user时间
     *
     * @param userId 字符串位置
     * @return this
     */
    public ChangeFlagUtils freshDate(Long userId) {
        Date date = new Date();
        userMap.put(userId, date);
        return this;
    }

    /**
     * 更新全部user时间,用于角色权限变化
     *
     * @return this
     */
    public ChangeFlagUtils freshDate() {
        Date date = new Date();
        userMap.keySet().forEach(userId -> userMap.put(userId, date));
        return this;
    }

    private int searchPosition(String flag) {
        if (flag.contains("-"))
            return searchPosition(flag.split("-")[0]);
        else
            for (int i = 0; i < FLAGS.length; i++) {
                if (flag.equals(FLAGS[i]))
                    return i;
            }
        return -1;
    }

}
