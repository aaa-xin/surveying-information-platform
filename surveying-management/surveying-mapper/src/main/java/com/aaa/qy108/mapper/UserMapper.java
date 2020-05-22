package com.aaa.qy108.mapper;

import com.aaa.qy108.model.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author guohang
 * @Description 通用usermapper
 * @Date 2020/5/12 21:36
 */
public interface UserMapper extends Mapper<User> {
    /**
     * @Author Cy
     * @Description 条件分页查询所有用户
     * @Param [map]
     * @Data 2020/5/21
     * @return java.util.List<java.util.HashMap>
     * @throws
     */
    List<HashMap> selectUserAll(HashMap map);
}