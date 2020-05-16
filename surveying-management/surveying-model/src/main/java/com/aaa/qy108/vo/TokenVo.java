package com.aaa.qy108.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author guohang
 * @Description 封装一个tokenvo实体类
 * @Date 2020/5/15 23:04
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class TokenVo implements Serializable {

    /**
     * token值，也就是uuid
     */
    private String token;

    /**
     * 标识了方法是否执行成功
     */
    private Boolean ifSuccess;

    /**
     * redis的key值，也就是用户的id
     */
    private String redisKey;

}



