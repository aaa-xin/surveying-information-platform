package com.aaa.qy108.service;

import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.User;
import com.aaa.qy108.vo.TokenVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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





}



