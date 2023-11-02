package org.eoa.projectbudget.service.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eoa.projectbudget.exception.DataException;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.exception.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 张骏山
 * @Date: 2023/10/30 13:46
 * @PackageName: org.eoa.projectbudget.cache
 * @ClassName: BaseCache
 * @Description: redis缓存直接操作类
 * @Version 1.0
 **/

@Service
public class CacheServiceImpl implements CacheService{

    @Value("${eoa.cache-time}")
    Long cacheTime;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final Logger log = LoggerFactory.getLogger("CacheModule");

    public CacheServiceImpl flashFlagCache(String flag) {

        Boolean delete = redisTemplate.delete(flag);
        log.info("删除:Flag=>{}的所有缓存:success=>{}",flag,delete);
        return this;
    }

    public <T> T  getCache(String flag, String method,String userId, Date changeFlag,Class<T> clazz) throws EoaException {
        String key = String.format("%s-%s", userId, method);
        WithTime withTime = (WithTime) redisTemplate.boundHashOps(flag).get(key);
        if (withTime == null) {
            log.info("获取:Flag=>{}更新时间=>{}的缓存:=>{}success=>false",flag,changeFlag,key);
            return null;
        }
        log.info("获取:Flag=>{}更新时间=>{}的缓存:=>{}success=>true",flag,changeFlag,key);
        String s = (withTime).valueEffective(changeFlag);
        try {
            return new ObjectMapper().readValue(s,clazz);
        } catch (JsonProcessingException e) {
            throw new DataException("redis",flag,key,s,"json转换出错");
        }
    }

    public CacheServiceImpl setCache(String flag, String method, String userId, Object object) throws EoaException {
        String key = String.format("%s-%s", userId, method);
        String value;
        try {
            value = new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.info("存储:Flag=>{}的缓存:=>{}success=>false",flag,key);
            throw new ServerException("数据转换错误", e);
        }
        BoundHashOperations<String, Object, Object> hashFlag = redisTemplate.boundHashOps(flag);
        if (Boolean.TRUE.equals(hashFlag.hasKey(key)))
            hashFlag.delete(key);
        hashFlag.put(key, new WithTime(value));
        hashFlag.expire(cacheTime, TimeUnit.MINUTES);
        log.info("存储:Flag=>{}的缓存:=>{}success=>true",flag,key);
        return this;
    }

    private static class WithTime {
        String object;
        Date date;

        String valueEffective(Date changeFlag) {
            if (date.after(changeFlag))
                return object;
            else
                return null;
        }

        public WithTime() {
        }

        public WithTime(String object) {
            this.object = object;
            this.date = new Date();
        }

        public Object getObject() {
            return object;
        }

        public WithTime setObject(String object) {
            this.object = object;
            return this;
        }

        public Date getDate() {
            return date;
        }

        public WithTime setDate(Date date) {
            this.date = date;
            return this;
        }
    }



}
