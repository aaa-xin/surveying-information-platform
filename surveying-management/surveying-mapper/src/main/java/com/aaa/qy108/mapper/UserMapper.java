package com.aaa.qy108.mapper;

import com.aaa.qy108.model.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @Author guohang
 * @Description 通用usermapper
 * @Date 2020/5/12 21:36
 */
public interface UserMapper extends Mapper<User> {

    /**
    * @Description: 查询用户，带条件
    * @Author: guohang
    * @Date: 2020/5/21 22:29
    * @Param: [map]
    * @return: java.util.List<java.util.HashMap>
    */
    List<HashMap> selectUserAll (HashMap map);


}