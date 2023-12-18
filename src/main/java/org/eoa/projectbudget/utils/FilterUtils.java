package org.eoa.projectbudget.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.exception.ParameterException;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: 张骏山
 * @Date: 2023/11/16 21:16
 * @PackageName: IntelliJ IDEA
 * @ClassName: FilterUtils
 * @Description: 过滤器工具
 * @Version: 1.0
 */
public class FilterUtils<Entity> {

    private final static String STRING = String.class.getTypeName();
    private final static String DATE = Date.class.getTypeName();

    private final static DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");

    Boolean browser = false;

    Map<String,String[]> map;

    Page page;

    QueryWrapper<Entity> wrapper;

    String description;

    public FilterUtils(Map<String, String[]> map,Class<Entity> clazz) throws ParameterException {
        this.map = map;
        try {
            this.page = new Page(Integer.parseInt(map.get("current")[0]), Integer.parseInt(map.get("pageSize")[0]));
        }catch (NullPointerException e) {
            this.page = new Page(1,Integer.MAX_VALUE);
        }
        try {
            this.browser = Boolean.getBoolean(map.get("browser")[0]);
        }catch (NullPointerException e) {
            this.browser = false;
        }

        this.wrapper = new QueryWrapper<>();
        StringBuilder stringBuffer = new StringBuilder();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            String column = entry.getKey();
            String[] value = entry.getValue();
            Field declaredField = getField(clazz,column);
            if (declaredField == null) {
                continue;
            }

            if (value[0].equals("null")) {
                wrapper.isNull(column);
            } else {
                String typeName = declaredField.getType().getTypeName();
                if (typeName.equals(STRING))
                    wrapper.like(column, value[0]);
                else if (typeName.equals(DATE)) {
                    String[] split = value[0].split(",");
                    try {
                        wrapper.between(column, FORMAT.parse(split[0]), FORMAT.parse(split[1]));
                    } catch (ParseException e) {
                        throw new ParameterException(column, value[0], "日期格式错误");
                    }
                } else
                    wrapper.eq(column, value[0]);
            }
            stringBuffer.append(column).append(":").append(Arrays.toString(value));
        }
        description = stringBuffer.toString();
    }

    private Field getField(Class<?> clazz, String name) {
        if (clazz == null) {
            return null;
        }
        try {
            return clazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            return getField(clazz.getSuperclass(),name);
        }
    }

    public Page getPage() {
        return page;
    }

    public QueryWrapper<Entity> getWrapper() {
        return wrapper;
    }

    public String getDescription() {
        return description;
    }

    public <out> List<out> filt(List<out> entities) {
        return entities.subList(page.getStart(), page.getEnd(entities.size()));
    }
}
