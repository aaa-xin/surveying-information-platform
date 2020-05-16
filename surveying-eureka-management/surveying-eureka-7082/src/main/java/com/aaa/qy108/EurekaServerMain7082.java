package com.aaa.qy108;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author guohang
 * @Description
 * @Date 2020/5/11 19:13
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerMain7082 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerMain7082.class,args);
    }
}



