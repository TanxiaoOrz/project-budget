package org.eoa.projectbudget.utils;

import org.eoa.projectbudget.entity.Column;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.mapper.FormDMLMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: 张骏山
 * @Date: 2023/12/8 14:54
 * @PackageName: org.eoa.projectbudget.utils
 * @ClassName: FilterFormUtils
 * @Description: 表单模块过滤器工具类
 * @Version: 1.0
 **/
public class FilterFormUtils {

    private final static String STRING = String.class.getTypeName();
    private final static String DATE = Date.class.getTypeName();

    private final static DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");

    Map<String,String[]> map;
    Page page;
    String description;

    Map<String,String[]> between;
    Map<String,String> like;
    Map<String,String> eq;

    public FilterFormUtils(Map<String, String[]> map) throws ParameterException {
        this.map = map;
        try {
            this.page = new Page(Integer.parseInt(map.get("current")[0]), Integer.parseInt(map.get("pageSize")[0]));
        }catch (NullPointerException e) {
            this.page = new Page(1,Integer.MAX_VALUE);
        }
        StringBuilder stringBuffer = new StringBuilder();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            String column = entry.getKey();
            String[] value = entry.getValue();
            if (column.equals("current")||column.equals("pageSize"))
                continue;
            stringBuffer.append(column).append(":").append(Arrays.toString(value));
        }
        description = stringBuffer.toString();
        between = new HashMap<>();
        like = new HashMap<>();
        eq = new HashMap<>();
    }

    public List<Long> getIds(FormDMLMapper dmlMapper, List<? extends Column> mains,String tableName) {
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            String column = entry.getKey();
            String[] value = entry.getValue();
            mains.stream().filter(c->c.getColumnDataName().equals(column)).findFirst().ifPresent(
                    (c)->{
                        switch (Column.DATATYPE_GROUP_VIEW.indexOf(c.getColumnType())){
                            case -1:
                                return;
                            case Column.BROWSER_BOX | Column.FILE | Column.NUMBER | Column.SELECT_ITEM:
                                eq.put(column,value[0]);
                                return;
                            case Column.DATETIME:
                                between.put(column,value);
                                return;
                            case Column.SINGLE_TEXT|Column.TEXT:
                                like.put(column,value[0]);
                                return;
                        }

                    }
            );
        }
        return dmlMapper.getIdsByMap(tableName,eq,like,between);
    }

    public Page getPage() {
        return page;
    }

    public String getDescription() {
        return description;
    }

    public <out> List<out> filt(List<out> entities) {
        return entities.subList(page.getStart(), page.getEnd(entities.size()));
    }
}
