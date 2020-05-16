package com.aaa.qy108.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Author guohang
 * @Description 读取ftp配置文件的bean
 * @Date 2020/5/14 10:46
 */
@ConfigurationProperties(prefix = "spring.ftp")
@Component
@PropertySource("classpath:properties/ftp.properties")
@Data
public class FtpProperties implements Serializable {

    private String host;

    private Integer port;

    private String username;

    private String password;

    private String basePath;

    private String httpPath;

}



