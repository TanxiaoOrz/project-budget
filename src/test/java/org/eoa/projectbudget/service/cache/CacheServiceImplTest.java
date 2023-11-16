package org.eoa.projectbudget.service.cache;

import org.eoa.projectbudget.exception.EoaException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;



/**
 * @Author: 张骏山
 * @Date: 2023/10/30 14:58
 * @PackageName: org.eoa.projectbudget.service.cache
 * @ClassName: CacheServiceImplTest
 * @Description: redis缓存测试类
 * @Version 1.0
 **/

@SpringBootTest
class CacheServiceImplTest {

    @Autowired
    CacheServiceImpl cacheService;

    @Test
    void all() throws EoaException, InterruptedException {
        cacheService.flashFlagCache("Test");
        Date date = new Date();
        Thread.sleep(1000);
        cacheService.setCache("Test","test1",1L,new Date());
        System.out.println(cacheService.getCache("Test","test1",1L,date,Date.class));
        Thread.sleep(100);
        cacheService.setCache("Test","test1",1L,new Date());
        System.out.println(cacheService.getCache("Test","test1",1L,date,Date.class));
        Thread.sleep(100);
        cacheService.setCache("Test","test1",1L,new Date());
        System.out.println(cacheService.getCache("Test","test1",1L,date,Date.class));
        Thread.sleep(100);
        cacheService.flashFlagCache("Test");
        System.out.println(cacheService.getCache("Test","test1",1L,date,Date.class));
        Thread.sleep(100);
    }


}