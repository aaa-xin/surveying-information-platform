package com.aaa.qy108.redis;

import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.List;

import static com.aaa.qy108.constant.Redis.*;

/**
 * @Author guohang
 * @Description redis的业务类，存放了redis的增删改查操作
 * @Date 2020/5/13 18:51
 */
@Service
public class RedisService<T> {


    @Autowired
    private JedisCluster jedisCluster;


    
    /** 
    * @Description: 向redis中存储数据，并设置失效时间
    *               key：redis的key
    *               value：redis的value
    *               nxxx：nx--key不存在可以存数据，xx--key存在可以才可以存储数据
    *               expx：时间单位，wx--秒，px--毫秒
    *               seconds：失效时间，如果没写则默认不需要设置
    * @Author: guohang
    * @Date: 2020/5/16 9:34
    * @Param: [key, value, nxxx, expx, seconds] 
    * @return: java.lang.String 
    */ 
    public String set(String key,T value,String nxxx,String expx,Integer seconds){
        String set =null;
        if (null != seconds && seconds > 0 && (NX.equals(nxxx)||XX.equals(nxxx)) && (EX.equals(expx) || PX.equals(expx))){
            //说明需要设置失效时间，使用糊涂工具包的JSONObject对象转换String
            set = jedisCluster.set(key, JSONUtil.toJsonStr(value), nxxx, expx, seconds);
            //返回一个set结果，如果当第一次储存一个key值的时候，会返回null，可以进行判断，如果为null，在通过nx存储
            if (null==set){
                set = jedisCluster.set(key, JSONUtil.toJsonStr(value), NX, expx, seconds);
            }
            return set;
        }else{
            //不需要设置失效时间
            if (NX.equals(nxxx)){
                //long类型，需要转换为String类型传出去
                return String.valueOf(jedisCluster.setnx(key,JSONUtil.toJsonStr(value)));
            }else if (XX.equals(nxxx)){
                return String.valueOf(jedisCluster.set(key,JSONUtil.toJsonStr(value)));
            }
        }
        return NO;
    }


    /**
     * 获取指定key的值,如果key不存在返回null，如果该Key存储的不是字符串，会抛出一个错误
     *
     * @param key
     * @return
     */
    public String get(String key) {
        String value = null;
        value = jedisCluster.get(key);
        return value;
    }

    /**
     * 设置key的值为value
     *
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) {
        return jedisCluster.set(key, value);
    }

    /**
     * 删除指定的key,也可以传入一个包含key的数组
     *
     * @param keys
     * @return
     */
    public Long del(String... keys) {
        return jedisCluster.del(keys);
    }

    /**
     * 通过key向指定的value值追加值
     *
     * @param key
     * @param str
     * @return
     */
    public Long append(String key, String str) {
        return jedisCluster.append(key, str);
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public Boolean exists(String key) {
        return jedisCluster.exists(key);
    }

    /**
     * 设置key value,如果key已经存在则返回0
     *
     * @param key
     * @param value
     * @return
     */
    public Long setnx(String key, String value) {
        return jedisCluster.setnx(key, value);
    }

    /**
     * 设置key value并指定这个键值的有效期
     *
     * @param key
     * @param seconds
     * @param value
     * @return
     */
    public String setex(String key, int seconds, String value) {
        return jedisCluster.setex(key, seconds, value);
    }

    /**
     * 通过key 和offset 从指定的位置开始将原先value替换
     *
     * @param key
     * @param offset
     * @param str
     * @return
     */
    public Long setrange(String key, int offset, String str) {
        return jedisCluster.setrange(key, offset, str);
    }

    /**
     * 通过批量的key获取批量的value
     *
     * @param keys
     * @return
     */
    public List<String> mget(String... keys) {
        return jedisCluster.mget(keys);
    }

    /**
     * 批量的设置key:value,也可以一个
     *
     * @param keysValues
     * @return
     */
    public String mset(String... keysValues) {
        return jedisCluster.mset(keysValues);
    }

    /**
     * 批量的设置key:value,可以一个,如果key已经存在则会失败,操作会回滚
     *
     * @param keysValues
     * @return
     */
    public Long msetnx(String... keysValues) {
        return jedisCluster.msetnx(keysValues);
    }

    /**
     * 设置key的值,并返回一个旧值
     *
     * @param key
     * @param value
     * @return
     */
    public String getSet(String key, String value) {
        return jedisCluster.getSet(key, value);
    }




}



