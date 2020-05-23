package com.aaa.qy108.service;

import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.MappingUnit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author guohang
 * @Description 测绘管理中的api接口
 * @Date 2020/5/22 18:26
 */
@FeignClient(value ="MAPPING-PROVIDER")
public interface MappingApiService {

    /**
    * @Description: 查询单位基本信息
    * @Author: guohang
    * @Date: 2020/5/22 19:08
    * @Param: []
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/unit/selectUnitInfo")
    ResultData selectUnitInfo(@RequestParam("tokenId") String tokenId);

    /**
    * @Description: 修改单位信息
    * @Author: guohang
    * @Date: 2020/5/22 20:29
    * @Param: [mappingUnit, tokenId]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/unit/updateUnitInfo")
    ResultData updateUnitInfo(@RequestBody MappingUnit mappingUnit, @RequestParam("tokenId") String tokenId);


}
