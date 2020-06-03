package com.aaa.qy108.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.context.RequestContext;
import io.micrometer.core.instrument.util.IOUtils;

import javax.servlet.ServletInputStream;
import java.io.IOException;

/**
 * @Author guohang
 * @Description post参数工具类
 * @Date 2020/6/2 20:52
 */
public class PostParamsUtils {


    private PostParamsUtils(){}


    /**
    * @Description: 获取参数的方法
    * @Author: guohang
    * @Date: 2020/6/2 20:57
    * @Param: [rcx]
    * @return: com.alibaba.fastjson.JSONObject
    */
    public static JSONObject postParams(RequestContext rcx){
        String body = null;
        if (!rcx.isChunkedRequestBody()){
            ServletInputStream inp;
            try {
                inp = rcx.getRequest().getInputStream();
                if (null != inp){
                    body = IOUtils.toString(inp);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return JSON.parseObject(body);
    }



}



