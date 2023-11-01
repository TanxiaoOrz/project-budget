package org.eoa.projectbudget.vo.constraint;

import java.util.Map;

/**
 * @Author: 张骏山
 * @Date: 2023/10/31 22:55
 * @PackageName: IntelliJ IDEA
 * @ClassName: Constraint
 * @Description: TODO
 * @Version: 1.0
 */
public class Constraint {
    String bodyType;    //默认解析类型选择
    Map<String, String> body;
    String tableType;   //表单解析类型选择
    Map<String, String> table;

    /**
     * null代表错误,-1代表全部,0+代表正常序号
     * @return constraint的类型选择数字
     */
//    public List<String> index(String s) {
//        s = DataProcessUtil.nullToString(s);
//        if (s.equals(""))
//            return null;
//        ArrayList<String> types = new ArrayList<>();
//        for (String type:
//             s.split(",")) {
//
//        }
//
//        return null;
//    }

    public String getBodyType() {
        return bodyType;
    }

    public Constraint setBodyType(String bodyType) {
        this.bodyType = bodyType;
        return this;
    }

    public Map<String, String> getBody() {
        return body;
    }

    public Constraint setBody(Map<String, String> body) {
        this.body = body;
        return this;
    }

    public String getTableType() {
        return tableType;
    }

    public Constraint setTableType(String tableType) {
        this.tableType = tableType;
        return this;
    }

    public Map<String, String> getTable() {
        return table;
    }

    public Constraint setTable(Map<String, String> table) {
        this.table = table;
        return this;
    }
}
