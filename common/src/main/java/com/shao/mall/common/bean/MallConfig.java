package com.shao.mall.common.bean;

import com.shao.mall.common.code.IdWorker;
import com.shao.mall.common.code.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author newjiang
 * @date 2019/6/4
 * @description:
 */
@Configuration
public class MallConfig {

    @Value("${guid.workerId}")
    private int workerId;
    @Value("${guid.datacenterId}")
    private int datacenterId;

    @Bean
    public IdWorker getId() {
        return new IdWorker(new SnowflakeIdWorker(workerId, datacenterId));
    }

}
