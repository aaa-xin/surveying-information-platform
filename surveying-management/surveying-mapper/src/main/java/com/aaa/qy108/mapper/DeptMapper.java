package com.aaa.qy108.mapper;

import com.aaa.qy108.model.Dept;
import tk.mybatis.mapper.common.Mapper;
import java.util.HashMap;
import java.util.List;

/**
 * @Author guohang
 * @Description 通用mapper
 * @Date 2020/5/20 21:36
 */
public interface DeptMapper extends Mapper<Dept> {
    /**
     *
     * @Param: [map]
     * @Return: java.util.List<com.aaa.qy108.model.Dept>
     *     通过条件查询部门信息
     * @Author: Liuyibo
     * @Date: 2020/5/20 21:48
     */
    List<Dept> selectDeptByNameOrTime(HashMap map);
}