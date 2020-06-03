package com.aaa.qy108.controller;

import com.aaa.qy108.base.BaseController;
import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.Menu;
import com.aaa.qy108.service.SystemApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author guohang
 * @Description 菜单的控制层
 * @Date 2020/6/3 18:27
 */
@RestController
@RequestMapping("/menu")
@Api(value = "菜单信息", tags = "菜单管理的接口")
public class MenuController extends BaseController {

    @Autowired
    private SystemApiService systemApiService;

    /**
    * @Description: 获取所有菜单的功能
    * @Author: guohang
    * @Date: 2020/6/3 18:30
    * @Param: []
    * @return: com.aaa.qy108.base.ResultData<com.aaa.qy108.model.Menu>
    */
    @GetMapping("/getMenus")
    @ApiOperation(value = "菜单管理", notes = "查询所有菜单的功能")
    public ResultData<Menu> selectAllMenus(){
        List<Menu> menus = systemApiService.selectAllMenus();
        if (null == menus || menus.size() < 0){
            return super.selectFailed();
        }
        return super.selectSuccess(menus);
    }

    /**
    * @Description: 添加菜单或者按钮
    * @Author: guohang
    * @Date: 2020/6/3 18:46
    * @Param: [menu]
    * @return: com.aaa.qy108.base.ResultData<com.aaa.qy108.model.Menu>
    */
    @PostMapping("/insertMenuOrButton")
    @ApiOperation(value = "新增菜单或按钮", notes = "新增菜单或按钮的功能")
    public ResultData<Menu> insertMenuOrButton(@RequestBody Menu menu){
        return systemApiService.insertMenuOrButton(menu);
    }


    /**
    * @Description: 修改菜单或按钮
    * @Author: guohang
    * @Date: 2020/6/3 18:46
    * @Param: [menu]
    * @return: com.aaa.qy108.base.ResultData<com.aaa.qy108.model.Menu>
    */
    @PostMapping("/updateMenuOrButton")
    @ApiOperation(value = "修改菜单或按钮", notes = "修改菜单或按钮信息的功能")
    public ResultData<Menu> updateMenuOrButton(@RequestBody Menu menu){
        return systemApiService.updateMenuOrButton(menu);
    }


    /**
    * @Description: 删除菜单或按钮
    * @Author: guohang
    * @Date: 2020/6/3 18:46
    * @Param: [menuId]
    * @return: com.aaa.qy108.base.ResultData<com.aaa.qy108.model.Menu>
    */
    @PostMapping("/deleteMenuOrButton")
    @ApiOperation(value = "删除菜单或按钮", notes = "删除菜单或按钮的功能")
    public ResultData<Menu> deleteMenuOrButton(@RequestBody Long menuId){
        return systemApiService.deleteMenuOrButton(menuId);
    }




}



