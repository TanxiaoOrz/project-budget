package org.eoa.projectbudget.vo.out;

/**
 * @Author: 张骏山
 * @Date: 2023/12/18 15:42
 * @PackageName: org.eoa.projectbudget.vo.out
 * @ClassName: VoOut
 * @Description: 输出结构体
 * @Version: 1.1
 **/
public interface VoOut {
    /**
     * 浏览框转换,filter过滤不在限制分页,其它字段删除
     * @param browserId form使用或VoOut自定义对应字段
     */
    void toBrowser(Long browserId);

}
