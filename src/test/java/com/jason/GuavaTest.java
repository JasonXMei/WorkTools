package com.jason;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Author Jason
 * @Date 2022/07/19
 */
public class GuavaTest {

    Cache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(1, TimeUnit.SECONDS) // 根据写入时间过期
            .build();


    @Test
    public void test() throws InterruptedException {
        cache.put("test", "1111");
        System.out.println(cache.getIfPresent("test"));
        Thread.sleep(1000L);
        System.out.println(cache.getIfPresent("test"));
    }
}
