package com.shao.mall.order;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author newjiang
 * @date 2019/5/13 23:10
 * @description: 订单模块的启动类
 */
@SpringBootApplication //(scanBasePackages = "com.shao.mall")
@EnableEurekaClient
@EnableCaching
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
