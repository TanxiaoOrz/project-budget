package org.eoa.projectbudget.utils;

import java.util.Map;

/**
 * @Author: 张骏山
 * @Date: 2023/11/16 21:16
 * @PackageName: IntelliJ IDEA
 * @ClassName: FilterUtils
 * @Description: TODO
 * @Version: 1.0
 */
public class FilterUtils {
    void removePage(Map<String,String> map) {
        map.remove("pageSize");
        map.remove("current");
    }
}
