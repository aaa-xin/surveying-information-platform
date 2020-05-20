package com.aaa.qy108.service;

import cn.hutool.core.date.DateUtil;
import com.aaa.qy108.base.BaseService;
import com.aaa.qy108.mapper.UserMapper;
import com.aaa.qy108.model.User;
import com.aaa.qy108.redis.RedisService;
import com.aaa.qy108.status.AddStatus;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.qy108.status.AddStatus.*;
import static com.aaa.qy108.status.DeleteStatus.*;
import static com.aaa.qy108.status.LoginStatus.*;

/**
 * @Author guohang
 * @Description
 * @Date 2020/5/20 13:53
 */
@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserMapper userMapper;


    /**
    * @Description: 新增用户
    * @Author: guohang
    * @Date: 2020/5/20 15:42
    * @Param: [user, redisService, token]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    */
    public Map<String,Object> addUser(User user, RedisService redisService, String tokenId){
        String tokenVal = redisService.get(tokenId);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (null == tokenVal){
            //在这里说明登录超时了
            resultMap.put("code",LOGIN_TIMEOUT_EXIT.getCode());
            resultMap.put("msg",LOGIN_TIMEOUT_EXIT.getMsg());
        }else{
            //可以查到redis里的token，然后可以进行新增用户
            user.setCreateTime(DateUtil.now());
            int addResult = userMapper.insert(user);
            if (addResult > 0){
                resultMap.put("code", ADD_DATA_SUCCESS.getCode());
                resultMap.put("msg", ADD_DATA_SUCCESS.getMsg());
            }else{
                resultMap.put("code", ADD_DATA_FAILED.getCode());
                resultMap.put("msg", ADD_DATA_FAILED.getMsg());
            }
        }
        return resultMap;
    }


    /**
    * @Description: 批量删除用户
    * @Author: guohang
    * @Date: 2020/5/20 20:50
    * @Param: [ids, redisService, tokenId]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    */
    public Map<String,Object> delUser(List<Long> ids,RedisService redisService,String tokenId) {
        String tokenVal = redisService.get(tokenId);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (null == tokenVal){
            //在这里说明登录超时了
            resultMap.put("code",LOGIN_TIMEOUT_EXIT.getCode());
            resultMap.put("msg",LOGIN_TIMEOUT_EXIT.getMsg());
        }else{
            //获取到参数类型，然后添加一个where条件，是in类型，id属于ids中的
            Example example = Example.builder(User.class).where(Sqls.custom().andIn("id",ids)).build();
            int i = userMapper.deleteByExample(example);
            if (i > 0){
                resultMap.put("code", DELETE_DATA_SUCCESS.getCode());
                resultMap.put("msg", DELETE_DATA_SUCCESS.getMsg());
            }else{
                resultMap.put("code", DELETE_DATA_FAILED.getCode());
                resultMap.put("msg", DELETE_DATA_FAILED.getMsg());
            }
        }
        return resultMap;
    }





}



