package com.aaa.qy108.config;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


/**
 * @Author guohang
 * @Description 这里不是让fein支持上传文件的配置，而是修改feign接收请求的请求头信息，使用Spring标准的encode，既可以接收文件又可以接收普通请求
 * @Date 2020/5/29 16:04
 */
@Configuration
public class FeignMultipartConfig {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;


    /** 
    * @Description: @ConditionalOnBean这个注解其实是spring公司提供所修改架构的注解,当某一个bean存在的时候，被这个注解所标识的bean就会被加载
    * @Author: guohang
    * @Date: 2020/5/29 16:21
    * @Param: [] 
    * @return: feign.codec.Encoder 
    */ 
    @Bean
    @ConditionalOnBean
    public Encoder SpringFormEncoder(){
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }




}



