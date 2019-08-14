package com.jiang.mall.goods.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * @author: newjiang
 * @date: 2019/8/14 17:22
 * @description: todo
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCaching
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }
}
