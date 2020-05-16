package com.aaa.qy108.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.aaa.qy108.constant.Swagger2.*;

/**
 * @Author guohang
 * @Description swagger2是Api接口文档生成工具
 * @Date 2020/5/15 22:16
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    
    /** 
    * @Description: swagger的构建器
    * @Author: guohang
    * @Date: 2020/5/15 22:27
    * @Param: [] 
    * @return: springfox.documentation.spring.web.plugins.Docket 
    */ 
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()//选择swagger生效的接口是什么
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE_PATH))
                .paths(PathSelectors.any())
                .build();
    }


    /**
    * @Description: 构建了项目的描述信息
    * @Author: guohang
    * @Date: 2020/5/15 22:24
    * @Param: []
    * @return: springfox.documentation.service.ApiInfo
    */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title(PROJECT_TITLE)
                .description(PROJECT_DESCRIPTION)
                .contact(new Contact(CONTACT_INFO_NAME,CONTACT_INFO_URL,CONTACT_INFO_EMAIL))
                .version(PROJECT_VERSION).build();
    }




}



