package com.aaa.qy108.controller;

import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.MappingUnit;
import com.aaa.qy108.service.MappingApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author guohang
 * @Description 单位基本信息下的全部模块
 * @Date 2020/5/22 19:02
 */
@RestController
@RequestMapping("/unit")
public class UnitInfoController {
    
    @Autowired
    private MappingApiService mappingApiService;
    
    
    /** 
    * @Description: 查询单位基本信息
    * @Author: guohang
    * @Date: 2020/5/22 19:07
    * @Param: [] 
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    @PostMapping("/selectUnitInfo")
    public ResultData selectUnitInfo(@RequestParam("userId") String userId){
        return mappingApiService.selectUnitInfo(userId);
    }

    /**
    * @Description: 修改单位信息
    * @Author: guohang
    * @Date: 2020/5/22 20:28
    * @Param: [mappingUnit]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/updateUnitInfo")
    public ResultData updateUnitInfo(@RequestBody MappingUnit mappingUnit){
        return mappingApiService.updateUnitInfo(mappingUnit);
    }

    
    /** 
    * @Description: 查询全部的单位负责人信息
    * @Author: guohang
    * @Date: 2020/5/28 15:49
    * @Param: [userId]
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    @GetMapping("/selectAllPrincipal")
    public ResultData selectAllPrincipal(@RequestParam("userId") String userId){
        return mappingApiService.selectAllPrincipal(userId);
    }


    /**
    * @Description: 查询全部的单位技术员信息
    * @Author: guohang
    * @Date: 2020/5/28 17:22
    * @Param: [userId]
    * @return: com.aaa.qy108.base.ResultData
    */
    @GetMapping("/selectAllTechnicist")
    public ResultData selectAllTechnicist(@RequestParam("userId") String userId){
        return mappingApiService.selectAllTechnicist(userId);
    }


}



