package com.aaa.qy108.controller;

import com.aaa.qy108.base.BaseController;
import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.Dept;
import com.aaa.qy108.model.User;
import com.aaa.qy108.service.SurveingApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.qy108.status.AddStatus.ADD_DATA_SUCCESS;
import static com.aaa.qy108.status.LoginStatus.LOGIN_TIMEOUT_EXIT;
import static com.aaa.qy108.status.UpdateStatus.UPDATE_DATA_SUCCESS;

/**
 * @author Liuyibo
 * 部门管理功能的消费者
 * @date 2020-05-20 16:38
 */
@RestController
@RequestMapping("/dept")
@Api(value = "部门管理",tags = "部门管理接口")
public class DeptController extends BaseController {

    @Autowired
    private SurveingApiService surveingApiService;

    /**
     * @Param: [map]
     * @Return: com.aaa.qy108.base.ResultData
     * 通过条件查询部门信息
     * @Author: Liuyibo
     * @Date: 2020/5/20 21:47
     */
    @PostMapping("/selectAllDept")
    @ApiOperation(value = "根据条件查询部门",notes = "部门管理的查询")
    public ResultData selectAllDept(@RequestBody HashMap map){
        return surveingApiService.selectAllDept(map);
    }

    /**
     * @Param: [dept, tokenId]
     * @Return: com.aaa.qy108.base.ResultData
     * 添加部门
     * @Author: Liuyibo
     * @Date: 2020/5/21 21:56
     */
    @PostMapping("/addDept")
    @ApiOperation(value = "根据条件添加部门",notes = "部门管理的添加")
    public ResultData addDept(@RequestBody Dept dept, @RequestParam("tokenId") String tokenId){
        return surveingApiService.addDept(dept,tokenId);
    }

    /**
     * @Param: [dept, tokenId]
     * @Return: com.aaa.qy108.base.ResultData
     * 修改部门
     * @Author: Liuyibo
     * @Date: 2020/5/21 21:56
     */
    @PostMapping("/updateDept")
    @ApiOperation(value = "根据条件修改部门",notes = "部门管理的修改")
    public ResultData updateDept(@RequestBody Dept dept, @RequestParam("tokenId") String tokenId){
      return surveingApiService.updateDept(dept,tokenId);
    }







}
