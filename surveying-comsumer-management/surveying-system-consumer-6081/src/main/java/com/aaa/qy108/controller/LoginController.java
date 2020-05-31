package com.aaa.qy108.controller;

import com.aaa.qy108.dynamic.annotation.LogAnnotation;
import com.aaa.qy108.base.BaseController;
import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.User;
import com.aaa.qy108.service.SystemApiService;
import com.aaa.qy108.vo.TokenVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author guohang
 * @Description 登录功能的消费者controller
 * @Date 2020/5/15 22:10
 */
@RestController
@Api(value = "登录信息",tags = "用户登录接口")
public class LoginController extends BaseController {

    @Autowired
    private SystemApiService systemApiService;


    /**
    * @Description: 执行登录操作
    * @Author: guohang
    * @Date: 2020/5/15 22:14
    * @Param: [user]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/doLogin")
    @ApiOperation(value = "登录功能",notes = "用户执行登录功能")
    @LogAnnotation(operationType = "登录操作", operationName = "用户执行登录操作")
    public ResultData doLogin(@RequestBody User user){
        TokenVo tokenVo = systemApiService.doLogin(user);
        if (tokenVo.getIfSuccess()){
            return super.loginSuccess("登录成功，token值为：" + tokenVo.getToken());
        }
        return super.loginFailed();
    }



}

