package com.aaa.qy108.controller;

import com.aaa.qy108.base.BaseService;
import com.aaa.qy108.base.CommonController;
import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.MappingUnit;
import com.aaa.qy108.service.MappingResultService;
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
 * @Date 2020/5/30 10:10
 * @Description
 **/
@RestController
public class MappingResultController extends CommonController {

    @Autowired
    private MappingResultService mappingResultService;
    
    /**
    * @Description: 根据传过来的条件查询所需要的 测绘 成果
    * @Param: [hashMap]
    * @return: com.aaa.qy108.base.ResultData
    * @Author: Qin
    * @Date: 2020/5/30
    */
    @PostMapping("/selcetAllResult")
    public ResultData selcetAllResult(@RequestBody HashMap hashMap){
        System.out.println(hashMap);
        Map<String, Object> resultMap = mappingResultService.selcetAllResult(hashMap);
        if (SELECT_DATA_SUCCESS.getCode().equals(resultMap.get("code"))){
            return super.selectSuccess(resultMap.get("data"));
        }else{
            return super.selectFailed();
        }
    }
    /**
    * @Description: 查询数据中所有的测绘类型，可以让前台进行选择查询
    * @Param: []
    * @return: com.aaa.qy108.base.ResultData
    * @Author: Qin
    * @Date: 2020/5/30
    */
    @PostMapping("/SelectProjectType")
    public ResultData SelectProjectType(){
        Map<String, Object> resultMap = mappingResultService.SelectProjectType();
        if (SELECT_DATA_SUCCESS.getCode().equals(resultMap.get("code"))){
            return super.selectSuccess(resultMap.get("data"));
        }else{
            return super.selectFailed();
        }
    }

    /**
    * @Description: 查询出成果的详细信息
    * @Param: [mappingUnit]
    * @return: com.aaa.qy108.base.ResultData
    * @Author: Qin
    * @Date: 2020/5/30
    */
    @PostMapping("/resultDetail")
    public ResultData resultDetail(@RequestParam("id") String id){
        Map<String, Object> resultMap = mappingResultService.resultDetail(id);
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
