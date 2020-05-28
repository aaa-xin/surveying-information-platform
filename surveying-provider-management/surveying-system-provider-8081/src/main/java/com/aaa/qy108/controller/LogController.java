package com.aaa.qy108.controller;

import com.aaa.qy108.base.BaseService;
import com.aaa.qy108.base.CommonController;
import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.Log;
import com.aaa.qy108.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author guohang
 * @Description 日志controller
 * @Date 2020/5/27 22:54
 */
@RestController
@RequestMapping("/log")
public class LogController extends CommonController<Log> {


    @Autowired
    private LogService logService;

    /**
    * @Description: 登录记录日志
    * @Author: guohang
    * @Date: 2020/5/27 23:03
    * @Param: [map]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/addLoginLog")
    ResultData addLoginLog(@RequestBody Map map){
        return super.add(map);
    }



    @Override
    public BaseService<Log> getBaseService() {
        return logService;
    }
}



