package com.aaa.qy108.aop;

import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.service.SystemApiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.aaa.qy108.status.LoginStatus.LOGIN_TIMEOUT_EXIT;
import static com.aaa.qy108.status.LoginStatus.NOT_LOGIN;

/**
 * @Author guohang
 * @Description 验证token的切面
 * @Date 2020/5/29 17:26
 */
@Slf4j
@Component
@Aspect
public class TokenAspect {

    @Autowired
    private SystemApiService SystemApiService;


    /**
    * @Description: 切点，定义横切的方法
    * @Author: guohang
    * @Date: 2020/5/29 17:30
    * @Param: []
    * @return: void
    */
    @Pointcut("execution(public * com.aaa.qy108.controller..*.*(..))")
    public void pointCut(){}


    @Before("pointCut()")
    public void before(JoinPoint joinPoint){
        //这个RequestContextHolder是Springmvc提供来获得请求的东西
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

    }

    /** 
    * @Description: 环绕切
    * @Author: guohang
    * @Date: 2020/5/29 17:30
    * @Param: [pjp] 
    * @return: java.lang.Object 
    */ 
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint){
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        ResultData resultData = new ResultData();
        String tokenId = request.getParameter("tokenId");
        //如果tokenid为空
        if (StringUtils.isEmpty(tokenId)){
            return resultData.setMsg(NOT_LOGIN.getMsg()).setCode(NOT_LOGIN.getCode());
        }else{
            String token = SystemApiService.getRedisToken(tokenId);
            if(null == token){
                //证明用户信息失效
                return resultData.setMsg(LOGIN_TIMEOUT_EXIT.getMsg()).setCode(LOGIN_TIMEOUT_EXIT.getCode());
            }
        }
        return result;
    }






}



