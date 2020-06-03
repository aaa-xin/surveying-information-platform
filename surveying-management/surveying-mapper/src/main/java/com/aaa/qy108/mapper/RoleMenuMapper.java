package com.aaa.qy108.mapper;

import com.aaa.qy108.model.RoleMenu;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
 * @author: 郭航
 * @Date: 2020/05/22
 * @Time: 16:00
 * @Desc  角色菜单表
 */
public interface RoleMenuMapper extends Mapper<RoleMenu> {

    /**
    * @Description: 在RoleMenu表中根据roleId删除
    * @Author: guohang
    * @Date: 2020/6/3 19:03
    * @Param: [roleId]
    * @return: int
    */
    int deleteRoleMenu(Long roleId);

    /**
    * @Description: 批量新增
    * @Author: guohang
    * @Date: 2020/6/3 19:03
    * @Param: [roleMenus]
    * @return: int
    */
    int batchInsertRoleMenu(List<RoleMenu> roleMenus);

    /**
    * @Description: 查询表里面有没有roleId
    * @Author: guohang
    * @Date: 2020/6/3 19:03
    * @Param: [roleId]
    * @return: java.util.List<com.aaa.qy108.model.RoleMenu>
    */
    List<RoleMenu> selectByRoleId(Long roleId);

}