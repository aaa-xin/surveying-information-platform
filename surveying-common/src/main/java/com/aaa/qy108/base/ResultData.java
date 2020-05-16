package com.aaa.qy108.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author guohang
 * @Description 统一返回值对象
 * @Date 2020/5/12 21:58
 */
@Data
public class ResultData<T> implements Serializable {

    private String code;
    private String msg;
    private String detail;
    private T data;


}



