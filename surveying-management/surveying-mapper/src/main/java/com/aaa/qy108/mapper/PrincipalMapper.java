package com.aaa.qy108.mapper;

import com.aaa.qy108.model.Principal;
import tk.mybatis.mapper.common.Mapper;

/**
 * 测绘单位负责人的通用mapper
 */
public interface PrincipalMapper extends Mapper<Principal> {
    
    
    /** 
    * @Description: 查询单个负责人的信息
    * @Author: guohang
    * @Date: 2020/6/1 15:36
    * @Param: [id] 
    * @return: com.aaa.qy108.model.Principal 
    */ 
    Principal selectPrincipalById(String id);
    
    
    
}