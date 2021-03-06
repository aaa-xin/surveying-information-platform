package com.aaa.qy108.controller;


import com.aaa.qy108.base.BaseController;
import com.aaa.qy108.base.BaseService;
import com.aaa.qy108.base.CommonController;
import com.aaa.qy108.base.ResultData;
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
import java.util.Map;

import static com.aaa.qy108.status.SelectStatus.SELECT_DATA_BY_ID_SUCCESS;
import static com.aaa.qy108.status.SelectStatus.SELECT_DATA_SUCCESS;

/**
 * @Author Qin
 * @Date 2020/5/22 22:46
 * @Description
 **/
@RestController
public class MappingUtilSearchController extends CommonController {

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
    public ResultData utilSelect(@RequestBody MappingUnit mappingUnit){
        System.out.println(mappingUnit);
        Map<String, Object> resultMap = mappingUtilSearchService.utilSelect(mappingUnit);
        if (SELECT_DATA_SUCCESS.getCode().equals(resultMap.get("code"))){
            return super.selectSuccess(resultMap.get("data"));
        }else{
            return super.selectFailed();
        }
    }
    /**
    * @Description: 通过字段查询所有的区域和资质，进行分组
    * @Param: [feild]
    * @return: java.util.List<java.util.HashMap>
    * @Author: Qin
    * @Date: 2020/5/23
    */
    @PostMapping("/selectGroupByFeild")
    public ResultData selectGroupByFeild(@RequestParam ("feild") String feild){
        System.out.println(feild);
        Map<String, Object> resultMap = mappingUtilSearchService.selectGroupByFeild(feild);
        if (SELECT_DATA_SUCCESS.getCode().equals(resultMap.get("code"))){
            return super.selectSuccess(resultMap.get("data"));
        }else{
            return super.selectFailed();
        }
    }
    
    
    /**
    * @Description: 通过id查询详细地单位信息
    * @Param: [id]
    * @return: com.aaa.qy108.base.ResultData
    * @Author: Qin
    * @Date: 2020/5/30
    */
    @PostMapping("/unitDetail")
    public ResultData unitDetail(@RequestParam("id") String id){
        Map<String, Object> resultMap = mappingUtilSearchService.unitDetail(id);
        if (SELECT_DATA_BY_ID_SUCCESS.getCode().equals(resultMap.get("code"))){
            return super.selectSuccess(resultMap.get("data"));
        }else{
            return super.selectFailed();
        }
    }





    @Override
    public BaseService getBaseService() {
        return null;
    }
}
