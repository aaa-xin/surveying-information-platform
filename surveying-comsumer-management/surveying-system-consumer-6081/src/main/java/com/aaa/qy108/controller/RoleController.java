package com.aaa.qy108.controller;

import com.aaa.qy108.base.BaseController;
import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.Menu;
import com.aaa.qy108.service.SystemApiService;
import com.aaa.qy108.vo.RoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author guohang
 * @Description
 * @Date 2020/6/3 18:50
 */
@RestController
@RequestMapping("/role")
@Api(value = "角色管理", tags = "角色管理的接口")
public class RoleController extends BaseController {

    @Autowired
    SystemApiService SystemApiService;

    /**
    * @Description: 查询所有的角色
    * @Author: guohang
    * @Date: 2020/6/3 18:51
    * @Param: []
    * @return: com.aaa.qy108.base.ResultData<Menu>
    */
    @GetMapping("/allRoles")
    @ApiOperation(value = "角色信息", notes = "查询所有角色的功能")
    public ResultData<Menu> selectAllMenus(){
        return SystemApiService.selectAllRole();
    }

    /**
    * @Description: 简单的分页查询
    * @Author: guohang
    * @Date: 2020/6/3 18:51
    * @Param: [roleVo]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/pageRoles")
    @ApiOperation(value = "角色信息", notes = "查询所有角色带分页的功能")
    public ResultData selectAllRoleByPage(@RequestBody RoleVo roleVo){
        return SystemApiService.selectAllRoleByPage(roleVo);
    }

    /**
    * @Description: 删除角色
    * @Author: guohang
    * @Date: 2020/6/3 18:51
    * @Param: [roleId]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/deleteRole")
    @ApiOperation(value = "删除角色", notes = "删除角色的功能")
    public ResultData deleteRole(@RequestBody Long roleId){
        return SystemApiService.deleteRole(roleId);
    }

    /**
    * @Description: 新增角色以及批量新增权限
    * @Author: guohang
    * @Date: 2020/6/3 18:52
    * @Param: [roleVo]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/insertRole")
    @ApiOperation(value = "新增角色", notes = "新增角色的功能")
    public ResultData insertRole(@RequestBody RoleVo roleVo){
        return SystemApiService.insertRole(roleVo);
    }


    /**
    * @Description: 修改角色及其权限
    * @Author: guohang
    * @Date: 2020/6/3 18:52
    * @Param: [roleVo]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/updateRole")
    @ApiOperation(value = "修改角色", notes = "修改角色的功能")
    public ResultData updateRole(@RequestBody RoleVo roleVo){
        return SystemApiService.updateRole(roleVo);
    }

}