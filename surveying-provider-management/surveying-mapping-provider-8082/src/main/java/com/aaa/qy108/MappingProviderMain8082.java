package com.aaa.qy108;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author guohang
 * @Description 测绘管理提供者
 * @Date 2020/5/22 18:20
 */
@SpringBootApplication(exclude = {RedisAutoConfiguration.class, RedisRepositoriesAutoConfiguration.class })
@MapperScan("com.aaa.qy108.mapper")
@EnableDiscoveryClient
@EnableCircuitBreaker
public class MappingProviderMain8082 {
    public static void main(String[] args) {
        SpringApplication.run(MappingProviderMain8082.class,args);
    }
}



