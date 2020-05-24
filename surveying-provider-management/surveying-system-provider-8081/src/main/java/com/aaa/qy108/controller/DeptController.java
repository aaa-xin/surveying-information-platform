package com.aaa.qy108.controller;

import com.aaa.qy108.base.BaseService;
import com.aaa.qy108.base.CommonController;
import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.Dept;
import com.aaa.qy108.model.User;
import com.aaa.qy108.redis.RedisService;
import com.aaa.qy108.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.qy108.status.AddStatus.ADD_DATA_SUCCESS;
import static com.aaa.qy108.status.DeleteStatus.DELETE_DATA_SUCCESS;
import static com.aaa.qy108.status.LoginStatus.LOGIN_TIMEOUT_EXIT;
import static com.aaa.qy108.status.SelectStatus.SELECT_DATA_SUCCESS;
import static com.aaa.qy108.status.UpdateStatus.*;

/**
 * @author Liuyibo
 * 部门管理的生产者
 * @date 2020-05-20 17:14
 */
@RestController
@Slf4j
@RequestMapping("/dept")
public class DeptController extends CommonController<Dept> {
    @Autowired
    private DeptService deptService;
    @Autowired
    private RedisService redisService;
    /**
     * 通过条件查询部门信息
     * @Param: [tokenId]
     * @Return: com.aaa.qy108.base.ResultData
     * @Author: Liuyibo
     * @Date: 2020/5/20 20:33
     */
    @PostMapping("/selectAllDept")
    ResultData selectAllDept(@RequestBody HashMap map){
        System.out.println(map);
        Map<String,Object> resultMap = deptService.selectAllDept(redisService,map);
        System.out.println(resultMap);
        if(SELECT_DATA_SUCCESS.getCode().equals(resultMap.get("code"))){
            return super.selectSuccess(resultMap.get("data"));
        }else if (LOGIN_TIMEOUT_EXIT.getCode().equals(resultMap.get("code"))){
            return super.loginTimeoutExit();
        }else{
            return super.selectFailed();
        }
    }
    /**
     *
     * @Param: [dept, tokenId]
     * @Return: com.aaa.qy108.base.ResultData
     *      添加部门信息
     * @Author: Liuyibo
     * @Date: 2020/5/21 19:50
     */
    @PostMapping("/addDept")
    ResultData addDept(@RequestBody Dept dept, @RequestParam("tokenId") String tokenId){
        System.out.println(dept);
        System.out.println(tokenId+"token值");
        Map<String, Object> addResult = deptService.addDept(dept, redisService, tokenId);
        if (ADD_DATA_SUCCESS.getCode().equals(addResult.get("code"))){
            return super.addSuccess();
        }else if (LOGIN_TIMEOUT_EXIT.getCode().equals(addResult.get("code"))){
            return super.loginTimeoutExit();
        }else{
            return super.addFailed();
        }
    }
    /**
     *
     * @Param: [dept, tokenId]
     * @Return: com.aaa.qy108.base.ResultData
     * 修改部门信息
     * @Author: Liuyibo
     * @Date: 2020/5/21 20:11
     */
    @PostMapping("/updateDept")
    ResultData updateDept(@RequestBody Dept dept, @RequestParam("tokenId") String tokenId){
        System.out.println(dept);
        Map<String, Object> updateResult = deptService.updateDept(dept, redisService, tokenId);
        if (UPDATE_DATA_SUCCESS.getCode().equals(updateResult.get("code"))){
            return super.updateSuccess();
        }else if (LOGIN_TIMEOUT_EXIT.getCode().equals(updateResult.get("code"))){
            return super.loginTimeoutExit();
        }else{
            return super.updateFailed();
        }
    }
    /**
     *
     * @Param: [ids, tokenId]
     * @Return: com.aaa.qy108.base.ResultData
     * 批量删除部门信息
     * @Author: Liuyibo
     * @Date: 2020/5/22 15:09
     */
    @DeleteMapping("/delDept")
    ResultData delDept(@RequestBody List<Long> ids, @RequestParam("tokenId") String tokenId){
        System.out.println("我是批量删除");
        System.out.println(ids);
        Map<String, Object> resultMap = deptService.delDept(ids, redisService, tokenId);
        if (DELETE_DATA_SUCCESS.getCode().equals(resultMap.get("code"))){
            return super.deleteSuccess();
        }else if (LOGIN_TIMEOUT_EXIT.getCode().equals(resultMap.get("code"))){
            return super.loginTimeoutExit();
        }else{
            return super.deleteFailed();
        }
    }
    @Override
    public BaseService<Dept> getBaseService() {
        return null;
    }

}
