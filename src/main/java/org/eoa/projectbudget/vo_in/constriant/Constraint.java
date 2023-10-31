package org.eoa.projectbudget.vo_in.constriant;

import org.eoa.projectbudget.utils.AuthorityUtils;
import org.eoa.projectbudget.utils.DataProcessUtil;

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
    String type;    //解析类型选择
    Map<String, String> body;

    Map<String, String> table;

    /**
     * null代表错误,-1代表全部,0+代表正常序号
     * @return constraint的类型选择数字
     */
    public Integer index() {
        type = DataProcessUtil.nullToString(type);
        if (type.equals(""))
            return -1;
        for (int i = 0; i < AuthorityUtils.types.length; i++) {
            if (AuthorityUtils.types[i].equals(type))
                return i;
        }
        return null;
    }

    public String getType() {
        return type;
    }

    public Constraint setType(String type) {
        this.type = type;
        return this;
    }

    public Map<String, String> getBody() {
        return body;
    }

    public Constraint setBody(Map<String, String> body) {
        this.body = body;
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
