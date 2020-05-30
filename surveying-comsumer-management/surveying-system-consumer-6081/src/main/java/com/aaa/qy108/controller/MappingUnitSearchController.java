package com.aaa.qy108.controller;

import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.MappingUnit;

import com.aaa.qy108.service.SystemApiService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Author Qin
 * @Date 2020/5/23 13:22
 * @Description
 **/
@RestController
public class MappingUnitSearchController {

    @Autowired
    private SystemApiService systemApiService;
    /**
    * @Description: 测绘单位查询，单位名称模糊查询，单位地域和单位资质准确查询
    * @Param: [mappingUnit, tokenId]
    * @return: java.util.List<java.util.HashMap>
    * @Author: Qin
    * @Date: 2020/5/23
    */
    @ApiOperation(value = "根据条件查询测绘单位",notes = "首页的查询")
    @PostMapping("/utilSelect")
    public ResultData utilSelect(@RequestBody MappingUnit mappingUnit/*, @RequestParam("tokenId") String tokenId*/){
        return systemApiService.utilSelect(mappingUnit);

    }

    /**
     * @Description: 通过字段查询所有的区域和资质，进行分组,当点击单位地域和单位资质的时候，从后台获取条件
     * @Param: [feild]
     * @return: java.util.List<java.util.HashMap>
     * @Author: Qin
     * @Date: 2020/5/23
     */
    @PostMapping("/selectGroupByFeild")
    @ApiOperation(value = "通过字段查询所有的区域和资质",notes = "首页的查询")
    public ResultData selectGroupByFeild(@RequestParam ("feild") String feild){
        return systemApiService.selectGroupByFeild(feild);
    }
    /**
     * @Description: 通过id查询详细地单位信息
     * @Param: [id]
     * @return: com.aaa.qy108.base.ResultData
     * @Author: Qin
     * @Date: 2020/5/30
     */
    @PostMapping("/unitDetail")
    @ApiOperation(value = "通过id查询详细地单位信息",notes = "首页的详情信息查询")
    public ResultData unitDetail(@RequestParam("id") String id){
        return systemApiService.unitDetail(id);
    }
}
