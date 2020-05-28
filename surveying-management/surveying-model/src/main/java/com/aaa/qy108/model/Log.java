package com.aaa.qy108.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "t_log")
public class Log implements Serializable {
    /**
     * id
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名
     */
    @Column(name = "USERNAME")
    private String username;

    /**
     * 时间
     */
    @Column(name = "OPERATION_TIME")
    private String operationTime;

    /**
     * 地点
     */
    @Column(name = "LOCATION")
    private String location;

    /**
     * IP地址
     */
    @Column(name = "IP")
    private String ip;

    /**
     * 操作类型
     */
    @Column(name = "OPERATION_TYPE")
    private String operationType;

    /**
     * 操作名称
     */
    @Column(name = "OPERATION_NAME")
    private String operationName;


}