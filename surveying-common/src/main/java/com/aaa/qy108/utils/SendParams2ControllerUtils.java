package com.aaa.qy108.utils;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.ServletInputStreamWrapper;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * @Author guohang
 * @Description 从路由发送数据到目标controller，跳转到controller的工具类
 * @Date 2020/6/2 20:35
 */
public class SendParams2ControllerUtils {

    private SendParams2ControllerUtils(){

    }

    /**
    * @Description: 发送数据的方法
    * @Author: guohang
    * @Date: 2020/6/2 20:38
    * @Param: [bodyJson, rcx, request]
    * @return: void
    */
    public static void sendParams(JSONObject bodyJson, RequestContext rcx, HttpServletRequest request){
        //先将json转换成二进制流
        String body = bodyJson.toString();
        final byte[] bodyBytes = body.getBytes();
        //把request对象放进上下文对象中
        rcx.setRequest(new HttpServletRequestWrapper(request){
            @Override
            public ServletInputStream getInputStream() throws IOException {
                return new ServletInputStreamWrapper(bodyBytes);
            }

            @Override
            public int getContentLength() {
                return bodyBytes.length;
            }

            @Override
            public long getContentLengthLong() {
                return bodyBytes.length;
            }
        });



    }


}



