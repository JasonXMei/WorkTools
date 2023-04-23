package com.jason.config;

import com.jason.interceptor.LoginInterceptor;
import com.jason.interceptor.RedisRateLimiterInterceptor;
import com.jason.interceptor.RedissionRateLimiterInterceptor;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author Jason
 * @Date 2022/06/21
 */
@Configuration
public class MvcInterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private RedissonClient redissonClient;

    @Bean
    public RedisRateLimiterInterceptor redisRateLimiterInterceptor() {
        return new RedisRateLimiterInterceptor();
    }

    @Bean
    public RedissionRateLimiterInterceptor redissionRateLimiterInter() {
        RRateLimiter rateLimiter = redissonClient.getRateLimiter("jason");
        rateLimiter.trySetRate(RateType.PER_CLIENT, 5, 10, RateIntervalUnit.MINUTES);
        return new RedissionRateLimiterInterceptor(rateLimiter);
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**", "/error", "/credential/**");

        /**
         * 1秒钟生成1个令牌，也就是1秒中允许一个人访问
         */
//        registry.addInterceptor(new GoogleRateLimiterInterceptor(RateLimiter.create(1, 1, TimeUnit.SECONDS)))
//                .addPathPatterns("/**");

//        registry.addInterceptor(redisRateLimiterInterceptor())
//                .addPathPatterns("/**");


        registry.addInterceptor(redissionRateLimiterInter())
                .addPathPatterns("/**");
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
