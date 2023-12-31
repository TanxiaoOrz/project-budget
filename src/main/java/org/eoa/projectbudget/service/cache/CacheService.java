package org.eoa.projectbudget.service.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.eoa.projectbudget.exception.EoaException;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/10/30 15:53
 * @PackageName: org.eoa.projectbudget.service.cache
 * @ClassName: CacheService
 * @Description: 缓存业务类声明接口
 * @Version 2.0
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
     * @param flag hash标志(缓存内容对象为单位)
     * @param method 方法
     * @param userId 用户编号,若是不会因为用户导致偏移的设置为空即可
     * @param changeFlag 结构体变化时间
     * @param clazz 数据类
     * @return 缓存的结构体
     * @param <T> 获取数据的结构体
     * @throws EoaException 存储数据一场
     */
    <T> T  getCache(String flag, String method, Long userId, Date changeFlag, Class<T> clazz) throws EoaException;

    /**
     *
     * @param flag hash标志的数组位置
     * @param method 方法
     * @param userId 用户
     * @param object 对象
     * @return 自身
     * @throws EoaException json转换错误
     */
    CacheService setCache(String flag, String method, Long userId, Object object) throws EoaException;

    <T> T  getCache(int flag, String method, Long userId, Class<T> clazz) throws EoaException;

    <T> T  getCache(String flag, String method, Long userId, Class<T> clazz) throws EoaException;

    /**
     *
     * @param flag hash标志的数组位置
     * @param method 方法
     * @param userId 用户,若是不会因为用户导致偏移的设置为空即可
     * @param object 对象
     * @return 自身
     * @throws EoaException json转换错误
     */
    CacheService setCache(int flag, String method, Long userId, Object object) throws EoaException;

    /**
     * 清楚field中的过期数据
     */
    void flashOutField() throws JsonProcessingException;
}
