package com.aaa.qy108.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description: 字典实体
 * @Author: mi
 * @Date: 2020/5/23 23:53
 **/
@Table(name = "t_dict")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Dict implements Serializable {
    /**
     * 字典ID
     */
    @Id
    @Column(name = "DICT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dictId;

    /**
     * 键
     */
    @Column(name = "KEYY")
    private Long keyy;

    /**
     * 值
     */
    @Column(name = "VALUEE")
    private String valuee;

    /**
     * 字段名称
     */
    @Column(name = "FIELD_NAME")
    private String fieldName;

    /**
     * 表名
     */
    @Column(name = "TABLE_NAME")
    private String tableName;

}