package com.aaa.qy108.controller;

import com.aaa.qy108.model.MappingUnit;
import com.aaa.qy108.service.SurveingApiService;
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
    private SurveingApiService surveingApiService;
    /**
    * @Description: 测绘单位查询，单位名称模糊查询，单位地域和单位资质准确查询
    * @Param: [mappingUnit, tokenId]
    * @return: java.util.List<java.util.HashMap>
    * @Author: Qin
    * @Date: 2020/5/23
    */
    @ApiOperation(value = "根据条件查询测绘单位",notes = "首页的查询")
    @PostMapping("/utilSelect")
    public List<HashMap> utilSelect(@RequestBody MappingUnit mappingUnit, @RequestParam("tokenId") String tokenId){
        return surveingApiService.utilSelect(mappingUnit,tokenId);
    }

    /**
     * @Description: 通过字段查询所有的区域和资质，进行分组
     * @Param: [feild]
     * @return: java.util.List<java.util.HashMap>
     * @Author: Qin
     * @Date: 2020/5/23
     */
    @PostMapping("/selectGroupByFeild")
    @ApiOperation(value = "通过字段查询所有的区域和资质",notes = "首页的查询")
    public List<HashMap> selectGroupByFeild(@RequestParam ("feild") String feild,@RequestParam("tokenId") String tokenId){
        return surveingApiService.selectGroupByFeild(feild,tokenId);
    }
}
