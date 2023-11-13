package org.eoa.projectbudget.utils.factory;

import org.eoa.projectbudget.exception.ParameterException;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/11/13 16:54
 * @PackageName: org.eoa.projectbudget.utils.factory
 * @ClassName: VoFactory
 * @Description: 将Vo类转换成Entity
 * @Version: 1.0
 **/
public interface VoFactory<VoIn,Entity> {
    int NEW = 0;
    int UPDATE = 1;

    Entity translate(VoIn in, int type);

    List<Entity> translate(List<VoIn> ins, int type);
}
