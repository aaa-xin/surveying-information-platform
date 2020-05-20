package com.aaa.qy108.service;

import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.mapper.DeptMapper;
import com.aaa.qy108.model.Dept;
import com.aaa.qy108.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.qy108.status.LoginStatus.LOGIN_TIMEOUT_EXIT;
import static com.aaa.qy108.status.SelectStatus.*;

/**
 * @author Liuyibo
 * @date 2020-05-20 17:16
 */
public class DeptService {
    @Autowired
    private DeptMapper deptMapper;

    public Map<String,Object> selectAllDept(RedisService redisService, String tokenId ){
        String token= redisService.get(tokenId);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(null == token){
            //证明用户信息失效
            resultMap.put("code",LOGIN_TIMEOUT_EXIT.getCode());
            resultMap.put("msg",LOGIN_TIMEOUT_EXIT.getMsg());
        }else {
            //证明用户信息没有失效
            List<Dept> depts = deptMapper.selectAll();
            if(depts.size()>0 &&depts !=null){
                resultMap.put("code",SELECT_DATA_SUCCESS.getCode());
                resultMap.put("msg",SELECT_DATA_SUCCESS.getCode());
                resultMap.put("data",depts);
            }
        }
        return resultMap;
    }
}
