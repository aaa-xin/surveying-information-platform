package com.aaa.qy108.controller;

import com.aaa.qy108.base.BaseController;
import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.User;
import com.aaa.qy108.service.SurveingApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author guohang
 * @Description consumer的usercontroller
 * @Date 2020/5/20 14:22
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户管理",tags = "用户管理接口")
public class UserController extends BaseController {

    @Autowired
    private SurveingApiService surveingApiService;


    /**
    * @Description: 用户管理中新增用户
    * @Author: guohang
    * @Date: 2020/5/20 14:43
    * @Param: [user, token]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/addUser")
    @ApiOperation(value = "添加用户",notes = "用户管理的新增用户")
    public ResultData addUser(@RequestBody User user, @RequestParam("tokenId") String tokenId){
        return surveingApiService.addUser(user, tokenId);
    }


    @DeleteMapping("/delUser")
    @ApiOperation(value = "删除用户",notes = "用户管理的删除用户")
    public ResultData delUser(@RequestBody List<Long> ids, @RequestParam("tokenId") String tokenId){
        return surveingApiService.delUser(ids, tokenId);
    }



}



