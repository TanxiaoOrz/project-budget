package org.eoa.projectbudget.vo.in;

import org.eoa.projectbudget.exception.ParameterException;

/**
 * @Author: 张骏山
 * @Date: 2023/11/11 22:53
 * @PackageName: IntelliJ IDEA
 * @ClassName: CheckParameter
 * @Description: 传入结构体完成检查接口
 * @Version: 1.0
 */
public interface CheckParameter {

    int NEW = 0;
    int UPDATE = 1;

    void checkSelf(int type) throws ParameterException;

}
