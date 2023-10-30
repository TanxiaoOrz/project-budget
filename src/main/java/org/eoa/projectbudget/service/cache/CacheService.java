package org.eoa.projectbudget.service.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eoa.projectbudget.exception.DataException;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.exception.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/10/30 15:53
 * @PackageName: org.eoa.projectbudget.service.cache
 * @ClassName: CacheService
 * @Description: 缓存业务类声明接口
 * @Version 1.0
 **/

public interface CacheService {
    /**
     * 清楚hash标志的所有结构
     * @param flag hash标志
     * @return 自身
     */
    CacheService flashFlagCache(String flag);

    /**
     *
     * @param flag hash标志
     * @param method 方法
     * @param userId 用户编号
     * @param changeFlag 结构体变化时间
     * @param clazz 数据类
     * @return 缓存的结构体
     * @param <T> 获取数据的结构体
     * @throws EoaException 存储数据一场
     */
    <T> T  getCache(String flag, String method, String userId, Date changeFlag, Class<T> clazz) throws EoaException;

    /**
     *
     * @param flag hash标志
     * @param method 方法
     * @param userId 用户
     * @param object 对象
     * @return 自身
     * @throws EoaException json转换错误
     */
    CacheService setCache(String flag, String method, String userId, Object object) throws EoaException;
}
