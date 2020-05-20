package com.aaa.qy108.controller;

import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.Dept;
import com.aaa.qy108.redis.RedisService;
import com.aaa.qy108.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Liuyibo
 * 部门管理的生产者
 * @date 2020-05-20 17:14
 */
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;
    @Autowired
    private RedisService redisService;

    @PostMapping("selectAllDept")
    public ResultData selectAllDept(@RequestParam("tokenId") String tokenId){
        ResultData resultData = deptService.selectAllDept(redisService, tokenId);
        if(resultData !=null){
            return resultData;
        }
        return null;
    }
}
