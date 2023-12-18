package org.eoa.projectbudget.vo.out;

/**
 * @Author: 张骏山
 * @Date: 2023/12/18 15:42
 * @PackageName: org.eoa.projectbudget.vo.out
 * @ClassName: VoOut
 * @Description: 输出结构体
 * @Version: 1.0
 **/
public interface VoOut {
    /**
     * 浏览框转换,filter过滤不在限制分页,其它字段删除
     * @param columnId form使用或type总定义
     */
    void toBrowser(Long columnId);

    /**
     * 浏览框转换,filter过滤不在限制分页,其它字段删除
     * @param columnName 特殊要求使用
     */
    void toBrowser(String columnName);

    /**
     * 浏览框转换,filter过滤不在限制分页,其它字段删除
     * @param columnId form使用
     */
    void toBrowser();
}
