package com.aaa.qy108.service;

import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.Dept;
import com.aaa.qy108.model.User;
import com.aaa.qy108.vo.TokenVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Author guohang
 * @Description feign的API接口。在开发阶段不能开启熔断，因为一旦开启熔断，异常会被捕捉，不容易发现问题
 *              api接口一定要和provider的controller一模一样
 * @Date 2020/5/15 22:15
 */
@FeignClient(value ="SYSTEM-PROVIDER")
public interface SurveingApiService {

    /**
    * @Description: 登录接口
    * @Author: guohang
    * @Date: 2020/5/15 22:43
    * @Param: [user]
    * @return: com.aaa.qy108.model.TokenVo
    */
    @PostMapping("/doLogin")
    TokenVo doLogin(@RequestBody User user);

    /** 
    * @Description: 添加订单接口
    * @Author: guohang
    * @Date: 2020/5/20 14:42
    * @Param: [user, tokenId]
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    @PostMapping("/user/addUser")
    ResultData addUser(@RequestBody User user, @RequestParam("tokenId") String tokenId);

    /**
     *
     * @Param: [dept, tokenId]
     * @Return: com.aaa.qy108.base.ResultData
     * 通过条件查询部门信息
     * @Author: Liuyibo
     * @Date: 2020/5/20 19:41
     */
    @PostMapping("selectAllDept")
    ResultData selectAllDept(@RequestBody HashMap map);

    /**
     *
     * @Param: [dept, tokenId]
     * @Return: com.aaa.qy108.base.ResultData
     * 添加部门信息
     * @Author: Liuyibo
     * @Date: 2020/5/21 19:50
     */
    @PostMapping("addDept")
    ResultData addDept(@RequestBody Dept dept, @RequestParam("tokenId") String tokenId);

    /**
     *
     * @Param: [dept, tokenId]
     * @Return: com.aaa.qy108.base.ResultData
     * 修改部门信息
     * @Author: Liuyibo
     * @Date: 2020/5/21 20:11
     */
    @PostMapping("updateDept")
    ResultData updateDept(@RequestBody Dept dept, @RequestParam("tokenId") String tokenId);

    /** 
    * @Description: 批量删除用户
    * @Author: guohang
    * @Date: 2020/5/20 20:33
    * @Param: [ids, tokenId] 
    * @return: com.aaa.qy108.base.ResultData 
    */
    @DeleteMapping("/user/delUser")
    ResultData delUser(@RequestBody List<Long> ids, @RequestParam("tokenId") String tokenId);
}



