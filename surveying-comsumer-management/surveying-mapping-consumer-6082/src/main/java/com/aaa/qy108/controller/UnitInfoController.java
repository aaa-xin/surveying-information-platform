package com.aaa.qy108.controller;

import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.MappingUnit;
import com.aaa.qy108.service.MappingApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author guohang
 * @Description
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
    public ResultData selectUnitInfo(@RequestParam("tokenId") String tokenId){
        return mappingApiService.selectUnitInfo(tokenId);
    }

    /**
    * @Description: 修改单位信息
    * @Author: guohang
    * @Date: 2020/5/22 20:28
    * @Param: [mappingUnit, tokenId]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/updateUnitInfo")
    public ResultData updateUnitInfo(@RequestBody MappingUnit mappingUnit, @RequestParam("tokenId") String tokenId){
        return mappingApiService.updateUnitInfo(mappingUnit,tokenId);
    }



}



