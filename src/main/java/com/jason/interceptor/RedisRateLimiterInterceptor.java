package com.jason.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.util.Assert;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author Jason
 * @Date 2022/11/28
 */
@Slf4j
public class RedisRateLimiterInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisScript<List<Long>> redisScript;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getParameter("id");
        assertNonLimit(userId, "1", "10");
        return true;
    }

    public void assertNonLimit(String userId, String replenishRate, String burstCapacity) {
        List<String> keys = getKeys(userId);

        // 根据限流时间维度计算时间
        long timeLong = getCurrentTimeLong(TimeUnit.SECONDS);

        // The arguments to the LUA script. time() returns unixTime in seconds.
        List<String> scriptArgs = Arrays.asList(replenishRate,
                burstCapacity,
                (Instant.now().toEpochMilli() / timeLong) + "",
                "1",
                timeLong + "");

        // 第一个参数是是否被限流，第二个参数是剩余令牌数
        List<Long> rateLimitResponse = this.redisTemplate.execute(this.redisScript, keys, scriptArgs.toArray());
        Assert.notNull(rateLimitResponse, "redis execute redis lua limit failed.");
        Long isAllowed = rateLimitResponse.get(0);
        Long newTokens = rateLimitResponse.get(1);
        log.info("rate limit key [{}] result: isAllowed [{}] new tokens [{}].", userId, isAllowed, newTokens);
        if (isAllowed <= 0) {
            throw new RuntimeException("rate limit");
        }
    }

    private List<String> getKeys(String id) {
        // use `{}` around keys to use Redis Key hash tags
        // this allows for using redis cluster

        // Make a unique key per user.
        String prefix = "request_rate_limiter.{" + id;

        // You need two Redis keys for Token Bucket.
        String tokenKey = prefix + "}.tokens";
        String timestampKey = prefix + "}.timestamp";
        return Arrays.asList(tokenKey, timestampKey);
    }

    private long getCurrentTimeLong(TimeUnit timeUnit) {
        switch (timeUnit) {
            case SECONDS:
                return 1000;
            case MINUTES:
                return 1000 * 60;
            case HOURS:
                return 1000 * 60 * 60;
            case DAYS:
                return 1000 * 60 * 60 * 24;
            default:
                throw new IllegalArgumentException("timeUnit:" + timeUnit + " not support");
        }
    }
}
