package com.aaa.qy108.service;

import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.MappingUnit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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


    /**
    * @Description: 查询全部的单位负责人信息
    * @Author: guohang
    * @Date: 2020/5/28 15:50
    * @Param: []
    * @return: com.aaa.qy108.base.ResultData
    */
    @GetMapping("/unit/selectAllPrincipal")
    ResultData selectAllPrincipal(@RequestParam("tokenId") String tokenId);


    /**
    * @Description: c查询全部技术员信息
    * @Author: guohang
    * @Date: 2020/5/28 17:22
    * @Param: [tokenId]
    * @return: com.aaa.qy108.base.ResultData
    */
    @GetMapping("/unit/selectAllTechnicist")
    ResultData selectAllTechnicist(@RequestParam("tokenId") String tokenId);


    /** 
    * @Description: ftp文件上传，因为feign只能发送默认普通类型，不能发送特殊类型，所以需要转换
    *               让postmapping去接收MultipartFile类型
    * @Author: guohang
    * @Date: 2020/5/29 15:57
    * @Param: [file] 
    * @return: java.lang.Boolean 
    */
    @PostMapping(value = "/uploadFile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean uploadFile(@RequestBody MultipartFile file);





















}
