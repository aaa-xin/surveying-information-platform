package com.aaa.qy108.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.aaa.qy108.properties.RedisClusterPorperties;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author guohang
 * @Description redis的配置连接
 * @Date 2020/5/13 18:41
 */
@Configuration
public class RedisClusterConfig {

    @Autowired
    private RedisClusterPorperties redisClusterPorperties;

    /**
    * @Description: 使用jedis连接redis服务器，并注入到spring容器中
    * @Author: guohang
    * @Date: 2020/5/13 18:48
    * @Param: []
    * @return: redis.clients.jedis.JedisCluster
    */
    @Bean
    public JedisCluster getJedisCluster(){
        String nodes = redisClusterPorperties.getNodes();
        String[] nodeArr = nodes.split(",");
        Set<HostAndPort> hostAndPorts = new HashSet<HostAndPort>();
        for (String node : nodeArr) {
            String[] split = node.split(":");
            HostAndPort hostAndPort = new HostAndPort(split[0],Integer.parseInt(split[1]));
            hostAndPorts.add(hostAndPort);
        }
        return new JedisCluster(hostAndPorts,redisClusterPorperties.getCommandTimeout(),redisClusterPorperties.getMaxAttempts());
    }

}



