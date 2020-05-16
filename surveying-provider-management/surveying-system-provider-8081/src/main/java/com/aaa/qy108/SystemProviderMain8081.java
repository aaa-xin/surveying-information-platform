package com.aaa.qy108;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author guohang
 * @Description
 * @Date 2020/5/12 20:41
 */
@SpringBootApplication(exclude = {RedisAutoConfiguration.class, RedisRepositoriesAutoConfiguration.class})
@MapperScan("com.aaa.qy108.mapper")
@EnableDiscoveryClient
@EnableCircuitBreaker
public class SystemProviderMain8081 {
    public static void main(String[] args) {
        SpringApplication.run(SystemProviderMain8081.class,args);
    }
}



