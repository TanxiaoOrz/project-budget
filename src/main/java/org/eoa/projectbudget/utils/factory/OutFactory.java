package org.eoa.projectbudget.utils.factory;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/11/15 21:37
 * @PackageName: IntelliJ IDEA
 * @ClassName: OutFactory
 * @Description: Vo输出类工厂构造接口
 * @Version: 1.0
 */
public interface OutFactory<E,O> {

    /**
     * 将实体转化成输出类
     * @param e 待转化的实体
     * @return 输出类
     */
    O out(E e);

    /**
     * 将实体数组转化成输出类数组
     * @param es 待转化的实体数组
     * @return 输出类数组
     */
    List<O> outs(List<E> es);
}
