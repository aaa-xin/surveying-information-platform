package com.aaa.qy108.controller;


import com.aaa.qy108.model.MappingUnit;
import com.aaa.qy108.redis.RedisService;
import com.aaa.qy108.service.MappingUtilSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @Author Qin
 * @Date 2020/5/22 22:46
 * @Description
 **/
@RestController
public class MappingUtilSearchController {

    @Autowired
    private MappingUtilSearchService mappingUtilSearchService;

    @Autowired
    private RedisService redisService;

    /**
    * @Description: 测绘单位查询，单位名称模糊查询，单位地域和单位资质准确查询
    * @Param: [hashMap]
    * @return: java.util.List<java.util.HashMap>
    * @Author: Qin
    * @Date: 2020/5/22
    */
    @PostMapping("/utilSelect")
    public List<HashMap>  utilSelect(@RequestBody MappingUnit mappingUnit,@RequestParam("tokenId") String tokenId){
        System.out.println(mappingUnit);
        System.out.println("token值"+tokenId);
        return  mappingUtilSearchService.utilSelect(mappingUnit,tokenId,redisService);
    }
    /**
    * @Description: 通过字段查询所有的区域和资质，进行分组
    * @Param: [feild]
    * @return: java.util.List<java.util.HashMap>
    * @Author: Qin
    * @Date: 2020/5/23
    */
    @PostMapping("/selectGroupByFeild")
    public List<HashMap> selectGroupByFeild(@RequestParam ("feild") String feild,@RequestParam("tokenId") String tokenId){
        return mappingUtilSearchService.selectGroupByFeild(feild,redisService,tokenId);
    }
}
