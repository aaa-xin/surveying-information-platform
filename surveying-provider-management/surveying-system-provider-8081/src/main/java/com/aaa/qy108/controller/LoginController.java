package com.aaa.qy108.controller;

import com.aaa.qy108.model.User;
import com.aaa.qy108.redis.RedisService;
import com.aaa.qy108.service.LoginService;
import com.aaa.qy108.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author guohang
 * @Description 登录功能的消费者
 * @Date 2020/5/15 22:44
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisService redisService;

    /** 
    * @Description: 登录功能的provider
    * @Author: guohang
    * @Date: 2020/5/15 22:46
    * @Param: [user] 
    * @return: com.aaa.qy108.vo.TokenVo
    */
    @PostMapping("/doLogin")
    TokenVo doLogin(@RequestBody User user){
        //先判断登录信息是否为空，此处是登录功能，不需要判断token，因为token一定为空
        if (null != user){
            TokenVo tokenVo = loginService.doLogin(user,redisService);
            if (null != tokenVo && !"".equals(tokenVo)){
                return tokenVo;
            }
        }
        return null;
    }




}



