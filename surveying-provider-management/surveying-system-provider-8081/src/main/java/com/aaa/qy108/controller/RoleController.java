package com.aaa.qy108.controller;

import com.aaa.qy108.base.BaseController;
import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.service.RoleService;
import com.aaa.qy108.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author guohang
 * @Description
 * @Date 2020/6/3 18:55
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    /**
    * @Description: 查询所有的角色
    * @Author: guohang
    * @Date: 2020/6/3 19:01
    * @Param: []
    * @return: com.aaa.qy108.base.ResultData
    */
    @GetMapping("/allRoles")
    public ResultData selectAllRole(){
        ResultData resultData = roleService.selectAllRole();
        if ("20010" == resultData.getCode()){
            return selectSuccess(resultData.getData());
        }else {
            return selectFailed();
        }
    }

    /**
    * @Description: 简单的分页查询
    * @Author: guohang
    * @Date: 2020/6/3 19:01
    * @Param: [roleVo]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/pageRoles")
    public ResultData selectAllRoleByPage(@RequestBody RoleVo roleVo){
        ResultData resultData = roleService.selectAllRoleByPage(roleVo);
        if ("20010" == resultData.getCode()){
            return selectSuccess(resultData.getData());
        }else {
            return selectFailed();
        }
    }

    /**
    * @Description: 删除角色
    * @Author: guohang
    * @Date: 2020/6/3 19:01
    * @Param: [roleId]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/deleteRole")
    public ResultData deleteRole(@RequestParam("roleId") Long roleId){
        Boolean aBoolean = roleService.deleteRole(roleId);
        if (aBoolean == true){
            return deleteSuccess();
        }else {
            return deleteFailed();
        }
    }

    /**
    * @Description: 新增角色以及批量新增权限
    * @Author: guohang
    * @Date: 2020/6/3 19:01
    * @Param: [roleVo]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/insertRole")
    public ResultData insertRole(@RequestBody RoleVo roleVo){
        Boolean aBoolean = roleService.insertRole(roleVo);
        if (true == aBoolean){
            return super.addSuccess();
        }else {
            return super.addFailed();
        }
    }

    /**
    * @Description: 修改角色及其权限
    * @Author: guohang
    * @Date: 2020/6/3 19:01
    * @Param: [roleVo]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/updateRole")
    public ResultData updateRole(@RequestBody RoleVo roleVo){
        Boolean aBoolean = roleService.updateRole(roleVo);
        if (aBoolean==true){
            return updateSuccess();
        }else {
            return updateFailed();
        }
    }

}


