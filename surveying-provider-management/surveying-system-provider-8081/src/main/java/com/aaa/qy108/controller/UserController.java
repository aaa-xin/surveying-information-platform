package com.aaa.qy108.controller;

import com.aaa.qy108.base.BaseService;
import com.aaa.qy108.base.CommonController;
import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.User;
import com.aaa.qy108.redis.RedisService;
import com.aaa.qy108.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.aaa.qy108.status.AddStatus.ADD_DATA_SUCCESS;
import static com.aaa.qy108.status.LoginStatus.LOGIN_TIMEOUT_EXIT;

/**
 * @Author guohang
 * @Description 用户管理的controller
 * @Date 2020/5/20 13:49
 */
@RestController
@RequestMapping("/user")
public class UserController extends CommonController<User> {


    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;


    /**
    * @Description: 用户管理中的新增用户
    * @Author: guohang
    * @Date: 2020/5/20 14:46
    * @Param: [user, token]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/addUser")
    ResultData addUser(@RequestBody User user, @RequestParam("tokenId") String tokenId){
        Map<String, Object> addResult = userService.addUser(user, redisService, tokenId);
        if (ADD_DATA_SUCCESS.getCode().equals(addResult.get("code"))){
            return super.addSuccess();
        }else if (LOGIN_TIMEOUT_EXIT.getCode().equals(addResult.get("code"))){
            return super.loginTimeoutExit();
        }else{
            return super.addFailed();
        }
    }


    @Override
    public BaseService<User> getBaseService() {
        return null;
    }
}



