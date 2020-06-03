package com.aaa.qy108.filter;

import com.aaa.qy108.utils.GetParamsUtils;
import com.aaa.qy108.utils.PostParamsUtils;
import com.aaa.qy108.utils.SendParams2ControllerUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author guohang
 * @Description 过滤登录时token中的参数
 * @Date 2020/6/2 18:39
 */
@Component
public class TokenFilter extends ZuulFilter {

    /**
    * @Description: 过滤器的类型，一共有四个类型。pre：到达路由之前执行；routing：到达路由的时候执行；post：到达路由之后执行；error：路由中抛出错误就会执行
    * @Author: guohang
    * @Date: 2020/6/2 18:40
    * @Param: []
    * @return: java.lang.String
    */
    @Override
    public String filterType() {
        return "pre";
    }
    
    /** 
    * @Description: filterType相同，filterOrder有作用，数字越小，越先执行。（负数也是这个规则，0和-1的话，-1先执行）
    * @Author: guohang
    * @Date: 2020/6/2 18:46
    * @Param: [] 
    * @return: int 
    */ 
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
    * @Description: 是否需要启用过滤器，true启用，false不启用。在这里判断请求头中是否带有token，如果没有则return false，如果有则true
    * @Author: guohang
    * @Date: 2020/6/2 19:02
    * @Param: []
    * @return: boolean
    */ 
    public boolean shouldFilter() {
        //zuul无论如何都不能获取到路由中所传递的参数，必须要从请求头里获取信息
        RequestContext rcx = RequestContext.getCurrentContext();
        HttpServletRequest request = rcx.getRequest();
        String params = GetParamsUtils.getParams(request);
        //判断里边是不是传了token
        if (params.contains("token") && (params.contains("http://") || params.contains("https://"))){
            //确定里边传了token
            return true;
        }
        //确定没有传token，则不需要验证，
        SendParams2ControllerUtils.sendParams(PostParamsUtils.postParams(rcx), rcx, request);
        return false;
    }
    
    /** 
    * @Description: 过滤的业务逻辑具体细节
    * @Author: guohang
    * @Date: 2020/6/2 19:03
    * @Param: [] 
    * @return: java.lang.Object 
    */ 
    public Object run() throws ZuulException {

        return null;
    }



}



