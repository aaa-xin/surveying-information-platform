package com.aaa.qy108.controller;

import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.Equipment;
import com.aaa.qy108.service.SystemApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Author Tzg
 * @Date 2020/6/4 10:24
 * @Description
 **/
@RestController
public class equipmentController {

    @Autowired
    private SystemApiService systemApiService;

    /**
     * @Description: 直接使用通用controller查询所有的仪器设备信息
     * @Param: [map]
     * @return: com.aaa.qy108.base.ResultData
     * @Author: Qin
     * @Date: 2020/6/4
     */
    @PostMapping("/selectEquipment")
    public ResultData selectEquipment(@RequestBody HashMap map){
        return systemApiService.selectEquipment(map);
    }


    /**
     * @Description: 通过id查询详细信息
     * @Param: [map]
     * @return: com.aaa.qy108.base.ResultData
     * @Author: Tzg
     * @Date: 2020/6/4
     */
    @PostMapping("/selectOneById")
    public ResultData selectOne(@RequestBody HashMap map){
        return systemApiService.selectOne(map);
    }


    /**
     * @Description: 根据id删除设备信息
     * @Param: [id]
     * @return: com.aaa.qy108.base.ResultData
     * @Author: Tzg
     * @Date: 2020/6/4
     */
    @PostMapping("/deleteById")
    public ResultData deleteById(@RequestParam("id") long id){
        return systemApiService.deleteById(id);
    }


    /**
     * @Description: 根据id更新信息
     * @Param: [equipment]
     * @return: com.aaa.qy108.base.ResultData
     * @Author: Tzg
     * @Date: 2020/6/4
     */
    @PostMapping("/updateById")
    public ResultData updateById(@RequestBody Equipment equipment){
        return systemApiService.updateById(equipment);
    }


    /**
     * @Description: 根据id添加信息
     * @Param: [equipment]
     * @return: com.aaa.qy108.base.ResultData
     * @Author: Tzg
     * @Date: 2020/6/4
     */
    @PostMapping("/addEquipmentInfo")
    public ResultData addEquipmentInfo(@RequestBody Equipment equipment){
        return systemApiService.addEquipmentInfo(equipment);
    }
}
