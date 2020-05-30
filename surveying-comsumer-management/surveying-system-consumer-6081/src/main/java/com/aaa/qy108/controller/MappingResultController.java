package com.aaa.qy108.controller;

import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.service.SystemApiService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Author Qin
 * @Date 2020/5/30 17:59
 * @Description
 **/
@RestController
public class MappingResultController {

    @Autowired
    private SystemApiService systemApiService;
    
    /**
    * @Description: 根据传过 来的条件去查询需要测绘成果
    * @Param: [hashMap]
    * @return: com.aaa.qy108.base.ResultData
    * @Author: Qin
    * @Date: 2020/5/30
    */
    @PostMapping("/selcetAllResult")
    @ApiOperation(value = "根据传过 来的条件去查询需要测绘成果",notes = "测绘成果的查询")
    public  ResultData selcetAllResult(@RequestBody HashMap hashMap){
        return systemApiService.selcetAllResult(hashMap);
    }

    /**
    * @Description: 查询数据中所有的测绘类型，可以让前台进行选择查询
    * @Param: []
    * @return: com.aaa.qy108.base.ResultData
    * @Author: Qin
    * @Date: 2020/5/30
    */
    @PostMapping("/SelectProjectType")
    @ApiOperation(value = "查询测绘类型，让前台选择",notes = "测绘成果类型的查询")
    public ResultData SelectProjectType(){
        return systemApiService.SelectProjectType();
    }


    /**
     * @Description: 查询出成果的详细信息
     * @Param: [mappingUnit]
     * @return: com.aaa.qy108.base.ResultData
     * @Author: Qin
     * @Date: 2020/5/30
     */
    @PostMapping("/resultDetail")
    @ApiOperation(value = "查询测绘成果的所有信息",notes = "通过id查询信息")
    public ResultData resultDetail(@RequestParam("id") String id){
        System.out.println(id);
        return systemApiService.resultDetail(id);
    }
}
