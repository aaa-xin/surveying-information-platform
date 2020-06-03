package com.aaa.qy108;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author guohang
 * @Description
 * @Date 2020/6/2 16:51
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulMain4081 {
    public static void main(String[] args) {
        SpringApplication.run(ZuulMain4081.class,args);
    }
}



