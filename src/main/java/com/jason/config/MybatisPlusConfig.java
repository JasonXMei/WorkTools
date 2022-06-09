package com.jason.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.jason.handler.FillMetaObjectHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@MapperScan("com.jason.mapper")
public class MybatisPlusConfig {

	@Autowired
	private FillMetaObjectHandler fillMetaObjectHandler;

	/**
	 * 启用性能分析插件
	 */
    @Bean
    public PerformanceInterceptor performanceInterceptor(){
    	PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        //格式化sql语句
        Properties properties = new Properties();
        properties.setProperty("format", "true");
        performanceInterceptor.setProperties(properties);
        return performanceInterceptor;
    }
    
    @Bean
    public GlobalConfig globalConfig(){
    	GlobalConfig config = new GlobalConfig();
    	config.setMetaObjectHandler(fillMetaObjectHandler);
    	return config;
    }
    
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
