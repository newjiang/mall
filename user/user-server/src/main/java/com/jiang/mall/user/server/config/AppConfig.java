package com.jiang.mall.user.server.config;

import com.jiang.mall.common.bean.GlobalExceptionHandler;
import com.jiang.mall.common.code.IdWorker;
import com.jiang.mall.common.code.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author newjiang
 * @date 2019/6/26
 * @description: 应用配置
 */
@Configuration
public class AppConfig {

    @Value("${guid.workerId}")
    private int workerId;
    @Value("${guid.datacenterId}")
    private int datacenterId;

    /**
     * ID生成工具Bean
     */
    @Bean
    public IdWorker getId() {
        return new IdWorker(new SnowflakeIdWorker(workerId, datacenterId));
    }

    /**
     * 全局异常处理Bean
     */
    @Bean
    public GlobalExceptionHandler globalExceptionHandler(){
        return new GlobalExceptionHandler();
    }

}
