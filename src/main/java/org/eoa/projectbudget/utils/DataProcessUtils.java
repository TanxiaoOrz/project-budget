package org.eoa.projectbudget.utils;

/**
 * @Author 张骏山
 * @Date 2023/10/7 16:02
 * @PackageName: org.eoa.projectbudget.utils
 * @ClassName: DataProcessUtil
 * @Description: 处理数据库中获取的数据
 * @Version 1.0
 */
public class DataProcessUtils {
    /**
     * int转换为boolean
     * @param x 传入的tinyInt
     * @return 转换后的boolean值
     */
    public static boolean translateIntegerToBoolean(int x) {
        return x!=0;
    }

    /**
     * 可能的null转换成空字符串
     * @param s 传入的判断字符串
     * @return 若为空则将其转换为""
     */
    public static String nullToString(String s) {
        return s==null?"":s;
    }

    /**
     * 检查字符穿是否为空
     * @param s 需要检查的字符串
     * @return 是否null或者空
     */
    public static boolean isEmpty(String s) {return s==null || s.equals("");}
}
