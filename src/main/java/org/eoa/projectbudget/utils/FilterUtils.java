package org.eoa.projectbudget.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.dto.Page;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;

/**
 * @Author: 张骏山
 * @Date: 2023/11/16 21:16
 * @PackageName: IntelliJ IDEA
 * @ClassName: FilterUtils
 * @Description: TODO
 * @Version: 1.0
 */
public class FilterUtils<Entity> {

    private final static String STRING = String.class.getTypeName();


    Map<String,String> map;

    Page page;

    QueryWrapper<Entity> wrapper;

    public FilterUtils(Map<String, String> map,Class<Entity> clazz) {
        this.map = map;
        this.page = new Page(Integer.parseInt(map.get("currentPage")), Integer.parseInt(map.get("pageSize")));

        this.wrapper = new QueryWrapper<>();

        map.forEach((column, value) -> {
            Field declaredField;
            try {
                declaredField = clazz.getDeclaredField(column);
            } catch (NoSuchFieldException e) {
                return;
            }
            String typeName = declaredField.getType().getTypeName();
            if (typeName.equals(String.class.getTypeName()))
            }
        });
    }

    public Page getPage() {
        return page;
    }

    public QueryWrapper<Entity> getWrapper() {
        return wrapper;
    }
}
