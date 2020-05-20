package com.aaa.qy108.controller;

import com.aaa.qy108.base.BaseService;
import com.aaa.qy108.base.CommonController;
import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.Dept;
import com.aaa.qy108.redis.RedisService;
import com.aaa.qy108.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.aaa.qy108.status.LoginStatus.LOGIN_TIMEOUT_EXIT;
import static com.aaa.qy108.status.SelectStatus.SELECT_DATA_SUCCESS;

/**
 * @author Liuyibo
 * 部门管理的生产者
 * @date 2020-05-20 17:14
 */
@RestController
public class DeptController extends CommonController {
    @Autowired
    private DeptService deptService;
    @Autowired
    private RedisService redisService;

    @PostMapping("selectAllDept")
    public ResultData selectAllDept(@RequestParam("tokenId") String tokenId){
        Map<String,Object> resultMap = deptService.selectAllDept(redisService, tokenId);
        if(SELECT_DATA_SUCCESS.getCode().equals(resultMap.get("code"))){
            return super.selectSuccess(resultMap.get("data"));
        }else if (LOGIN_TIMEOUT_EXIT.getCode().equals(resultMap.get("code"))){
            return super.loginTimeoutExit();
        }else{
            return super.selectFailed();
        }
    }

    @Override
    public BaseService getBaseService() {
        return null;
    }
}
