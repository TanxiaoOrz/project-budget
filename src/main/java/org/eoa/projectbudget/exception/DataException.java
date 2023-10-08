package org.eoa.projectbudget.exception;

import org.eoa.projectbudget.vo.Vo;

/**
 * @Author 张骏山
 * @Date 2023/10/8 17:00
 * @PackageName: org.eoa.projectbudget.exception
 * @ClassName: DataException
 * @Description: TODO
 * @Version 1.0
 */
public class DataException extends EoaException{
    /**
     *
     * @param table 错误数据存储表单
     * @param id 错误数据id
     * @param attribute 错误数据所在属性
     * @param data 错误数据明细
     */
    public DataException(String table, String id, String attribute, String data) {
        code = Vo.STORE_DATA_ERROR;
        description = String.format("存储数据出错,请检查数据库\n\t表单:%s\t编号:%s\t属性:%s\t值:%s",table,id,attribute,data);
    }
}
