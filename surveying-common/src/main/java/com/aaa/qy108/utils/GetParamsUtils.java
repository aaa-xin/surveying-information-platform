package com.aaa.qy108.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Author guohang
 * @Description
 * @Date 2020/6/2 20:23
 */
public class GetParamsUtils {

    private GetParamsUtils(){}

    /** 
    * @Description: 获取参数的方法
    * @Author: guohang
    * @Date: 2020/6/2 20:24
    * @Param: [request] 
    * @return: java.lang.String 
    */ 
    public static String getParams(HttpServletRequest request){
        StringBuilder params = new StringBuilder("?");
        //获取参数
        Enumeration<String> parameterNames = request.getParameterNames();
        //判断确定用户使用的就是get方式
        if (request.getMethod().toUpperCase().equals("GET")){
            //说明使用的get方式
            while (parameterNames.hasMoreElements()){
                //将参数封装进String中
                String name = parameterNames.nextElement();
                params.append(name);
                params.append("=");
                params.append(request.getParameter(name));
                params.append("&");
            }
        }
        if (params.length() > 1){
            params.delete(params.length()-1,params.length());
        }
        return params.toString();
    }




}



