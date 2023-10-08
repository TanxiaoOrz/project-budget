package org.eoa.projectbudget.utils;

/**
 * @Author 张骏山
 * @Date 2023/10/7 16:02
 * @PackageName: org.eoa.projectbudget.utils
 * @ClassName: TableProcessUtil
 * @Description: 处理数据库中获取的数据
 * @Version 1.0
 */
public class TableProcessUtil {
    /**
     *
     * @param x 传入的tinyInt
     * @return 转换后的boolean值
     */
    public static boolean translateIntegerToBoolean(int x) {
        return x!=0;
    }

    /**
     *
     * @param s 传入的判断字符串
     * @return 若为空则将其转换为""
     */
    public static String nullToString(String s) {
        return s==null?"":s;
    }
}
