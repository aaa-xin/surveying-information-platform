package com.aaa.qy108.controller;

import com.aaa.qy108.base.BaseController;
import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.Dept;
import com.aaa.qy108.model.User;
import com.aaa.qy108.service.SurveingApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author Liuyibo
 * 部门管理功能的消费者
 * @date 2020-05-20 16:38
 */
@RestController
public class DeptController extends BaseController {
    @Autowired
    private SurveingApiService surveingApiService;
    /**
     *
     * @Param: [map]
     * @Return: com.aaa.qy108.base.ResultData
     * 通过条件查询部门信息
     * @Author: Liuyibo
     * @Date: 2020/5/20 21:47
     */
    @PostMapping("selectAllDept")
    public ResultData selectAllDept(@RequestParam HashMap map){
        return surveingApiService.selectAllDept(map);
    }
}
