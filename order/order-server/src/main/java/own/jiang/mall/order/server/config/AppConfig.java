package own.jiang.mall.order.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import own.jiang.mall.common.bean.DateConverterConfig;
import own.jiang.mall.common.bean.GlobalExceptionHandler;
import own.jiang.mall.common.code.IdWorker;
import own.jiang.mall.common.code.SnowflakeIdWorker;

/**
 * @author newjiang
 * @date 2019/6/26
 * @description:
 */
@Configuration
public class AppConfig {

    @Value("${guid.workerId}")
    private int workerId;
    @Value("${guid.datacenterId}")
    private int datacenterId;

    @Bean
    public IdWorker getId() {
        return new IdWorker(new SnowflakeIdWorker(workerId, datacenterId));
    }

    @Bean
    public GlobalExceptionHandler globalExceptionHandler(){
        return new GlobalExceptionHandler();
    }

    @Bean
    public DateConverterConfig dateConverterConfig(){
        return new DateConverterConfig();
    }

}
