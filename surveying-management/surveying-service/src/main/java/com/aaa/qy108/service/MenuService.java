package com.aaa.qy108.service;

import cn.hutool.core.date.DateUtil;
import com.aaa.qy108.base.BaseService;
import com.aaa.qy108.mapper.MenuMapper;
import com.aaa.qy108.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author guohang
 * @Description
 * @Date 2020/6/3 18:36
 */
@Service
public class MenuService extends BaseService<Menu> {

    @Autowired
    private MenuMapper menuMapper;


    /**
    * @Description: 获取菜单信息
    * @Author: guohang
    * @Date: 2020/6/3 18:43
    * @Param: []
    * @return: java.util.List<com.aaa.qy108.model.Menu>
    */
    public List<Menu> selectAllMenus(){
        //菜单树
        List<Menu> menusList=new ArrayList<Menu>();
        //菜单的全部信息
        List<Menu> allMenusList= menuMapper.selectAll();
        if (null!=allMenusList&&allMenusList.size()>0) {
            //拿到一级菜单信息
            for (int i = 0; i <allMenusList.size() ; i++) {
                Menu menu = allMenusList.get(i);
                if (menu.getParentId()==0) {
                    //说明是一级菜单
                    menusList.add(menu);
                }
            }
            //为一级菜单设置子菜单
            for (Menu menu: menusList) {
                menu.setSubMenu(getSubMenu(menu.getMenuId(),allMenusList));
            }
        }
        return menusList;
    }

    /**
    * @Description:  获取上一级菜单的子菜单
    * @Author: guohang
    * @Date: 2020/6/3 18:43
    * @Param: [menuId, allMenus]
    * @return: java.util.List<com.aaa.qy108.model.Menu>
    */
    private List<Menu> getSubMenu(Long menuId,List<Menu> allMenus){
        //子菜单
        List<Menu> subMenus=new ArrayList<Menu>();
        for (Menu menu:
                allMenus) {
            if (menu.getParentId().equals(menuId)){
                subMenus.add(menu);
            }
        }
        //子菜单的下一级
        //疑问：当递归进入，查找子菜单的子菜单，那么子菜单的数据现在在哪，是在下面循环代码中的subMenus中
        for (Menu menu:
                subMenus) {
            menu.setSubMenu(getSubMenu(menu.getMenuId(),allMenus));
        }
        if (subMenus.size()==0){
            return null;
        }
        return subMenus;
    }

    /**
    * @Description: 新增菜单或者按钮
    * @Author: guohang
    * @Date: 2020/6/3 18:43
    * @Param: [menu]
    * @return: java.lang.Boolean
    */
    public Boolean insertMenuOrButton(Menu menu){
        String createTime = DateUtil.now();
        menu.setCreateTime(createTime);
        try{
            Integer add = super.add(menu);
            if (add > 0){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
    * @Description: 根据主键修改菜单或者按钮的信息
    * @Author: guohang
    * @Date: 2020/6/3 18:42
    * @Param: [menu]
    * @return: java.lang.Boolean
    */
    public Boolean updateMenuOrButton(Menu menu){
        String time = DateUtil.now();
        menu.setModifyTime(time);
        try {
            Integer update = super.update(menu);
            if (update>0){
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
    * @Description: 通过主键id删除菜单或者按钮
    * @Author: guohang
    * @Date: 2020/6/3 18:42
    * @Param: [menuId]
    * @return: java.lang.Boolean
    */
    public Boolean deleteMenuOrButton(Long menuId){
        int i = menuMapper.deleteByPrimaryKey(menuId);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }




}



