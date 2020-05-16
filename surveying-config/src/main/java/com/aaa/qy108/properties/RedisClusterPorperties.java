package com.aaa.qy108.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author guohang
 * @Description 配置redis读取配置文件，PropertySource是因为配置文件不是application.properties，所以要指定一下
 * @Date 2020/5/13 18:37
 */
@ConfigurationProperties(prefix = "spring.redis")
@PropertySource("classpath:properties/redis_cluster.properties")
@Component
@Data
public class RedisClusterPorperties {

    private String nodes;
    private Integer maxAttempts;
    private Integer commandTimeout;
}



