package org.eoa.projectbudget;

/**
 * @Author: 张骏山
 * @Date: 2024/1/1 16:54
 * @PackageName: org.eoa.projectbudget
 * @ClassName: BaseTest
 * @Description: 无需Springboot的基本测试
 * @Version: 1.0
 */
public class BaseTest {

    static String test = "1";
    static Object testO = "0";

    static void test(String s) {
        System.out.println("s");
    }

    static void test(Object o) {
        System.out.println("o");
        System.out.println("o.getClass() = " + o.getClass());
    }

    public static void main(String[] args) {
        test(test);
        Object o = test;
        test(testO);
        test(new Object());
        test(o);
        System.out.println(Object.class.getSimpleName());
    }
}
