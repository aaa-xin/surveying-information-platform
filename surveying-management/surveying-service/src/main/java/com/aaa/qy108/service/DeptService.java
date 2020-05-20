package com.aaa.qy108.service;

import com.aaa.qy108.mapper.DeptMapper;
import com.aaa.qy108.model.Dept;
import com.aaa.qy108.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Liuyibo
 * @date 2020-05-20 17:16
 */
public class DeptService {
    @Autowired
    private DeptMapper deptMapper;

    public List<Dept> selectAllDept(RedisService redisService, String tokenId ){
            return null;
    }
}
