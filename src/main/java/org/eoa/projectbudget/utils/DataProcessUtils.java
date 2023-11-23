package org.eoa.projectbudget.utils;

/**
 * @Author 张骏山
 * @Date 2023/10/7 16:02
 * @PackageName: org.eoa.projectbudget.utils
 * @ClassName: DataProcessUtil
 * @Description: 处理数据库中获取的数据
 * @Version 1.2
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
     * boolean转换成int
     * @param b 传入的boolean
     * @return 转换后的tinyInt值
     */
    public static int translateBooleanToInteger(boolean b) {
        return b?1:0;
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

    /**
     * 将数组字符串连接成数据库需要的但字符串形式
     * @param strings 字符串数组
     * @return ","分割的字符串
     */
    public static String contactStringArray(String[] strings) {
        if (strings == null || strings.length == 0) {
            return "";
        }
        if (strings.length>=2) {
            StringBuilder stringBuffer = new StringBuilder();
            stringBuffer.append(strings[0]);
            for (int i = 1; i < strings.length; i++) {
                stringBuffer.append(',').append(strings[i]);
            }
            return stringBuffer.toString();
        }else {
            return strings[0];
        }
    }

    /**
     * 将数据库字段数组拆分
     * @param s 字段数组字符串
     * @return 拆分后数组
     */
    public static String[] splitStringArray(String s) {
        return s.split(",");
    }
}
