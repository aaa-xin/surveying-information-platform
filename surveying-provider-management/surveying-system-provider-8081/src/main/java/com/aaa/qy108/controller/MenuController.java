package com.aaa.qy108.controller;

import com.aaa.qy108.base.BaseController;
import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.Menu;
import com.aaa.qy108.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author guohang
 * @Description
 * @Date 2020/6/3 18:35
 */
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {


    @Autowired
    private MenuService menuService;

    /**
    * @Description: 查询所有菜单
    * @Author: guohang
    * @Date: 2020/6/3 18:36
    * @Param: []
    * @return: List<Menu>
    */
    @GetMapping("/getMenus")
    public List<Menu> selectAllMenus(){
        return menuService.selectAllMenus();
    }


    /** 
    * @Description: 在菜单管理中新增菜单或者是按钮 
    * @Author: guohang
    * @Date: 2020/6/3 19:02
    * @Param: [menu] 
    * @return: com.aaa.qy108.base.ResultData<com.aaa.qy108.model.Menu> 
    */ 
    @PostMapping("/insertMenuOrButton")
    public ResultData<Menu> insertMenuOrButton(@RequestBody Menu menu){
        Boolean aBoolean = menuService.insertMenuOrButton(menu);
        if (true == aBoolean){
            return super.addSuccess();
        }else {
            return super.addFailed();
        }
    }

    /** 
    * @Description: 在菜单管理中修改菜单或者按钮 
    * @Author: guohang
    * @Date: 2020/6/3 18:45
    * @Param: [menu] 
    * @return: com.aaa.qy108.base.ResultData<com.aaa.qy108.model.Menu> 
    */ 
    @PostMapping("/updateMenuOrButton")
    public ResultData<Menu> updateMenuOrButton(@RequestBody Menu menu){
        Boolean aBoolean = menuService.updateMenuOrButton(menu);
        if (true == aBoolean){
            return super.updateSuccess();
        }else {
            return super.updateFailed();
        }
    }
    
    /** 
    * @Description: 删除按钮或者菜单 
    * @Author: guohang
    * @Date: 2020/6/3 18:45
    * @Param: [menuId] 
    * @return: com.aaa.qy108.base.ResultData<com.aaa.qy108.model.Menu> 
    */ 
    @PostMapping("/deleteMenuOrButton")
    public ResultData<Menu> deleteMenuOrButton(@RequestBody Long menuId){
        Boolean aBoolean = menuService.deleteMenuOrButton(menuId);
        if (aBoolean == true){
            return super.deleteSuccess();
        }else {
            return super.deleteFailed();
        }
    }


}



