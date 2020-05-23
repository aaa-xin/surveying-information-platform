package com.aaa.qy108;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author guohang
 * @Description 测绘管理消费者
 * @Date 2020/5/22 18:24
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MappingConsumerMain6082 {
    public static void main(String[] args) {
        SpringApplication.run(MappingConsumerMain6082.class,args);
    }
}



