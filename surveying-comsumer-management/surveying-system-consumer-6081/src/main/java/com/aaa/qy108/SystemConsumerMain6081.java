package com.aaa.qy108;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author guohang
 * @Description 系统功能消费者主启动
 * @Date 2020/5/16 13:18
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SystemConsumerMain6081 {
    public static void main(String[] args) {
        SpringApplication.run(SystemConsumerMain6081.class,args);
    }
}



