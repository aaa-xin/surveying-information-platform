package com.aaa.qy108;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author guohang
 * @Description
 * @Date 2020/5/11 19:12
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerMain7081 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerMain7081.class,args);
    }
}



