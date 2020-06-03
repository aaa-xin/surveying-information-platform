package com.aaa.qy108.controller;

import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.MappingUnit;
import com.aaa.qy108.model.Principal;
import com.aaa.qy108.service.MappingApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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


    /**
     * @Description: 添加单位负责人
     * @Author: guohang
     * @Date: 2020/5/30 11:02
     * @Param: [file, type, name, idType, idNumber, age, sex, workYear, duty, title, major, mappingYear, userId]
     * @return: com.aaa.qy108.base.ResultData
     */
    @PostMapping("/addPrincipal")
    public ResultData addPrincipal(@RequestParam("files") MultipartFile[] files,@RequestParam("type") String type,@RequestParam("name") String name,@RequestParam("idType") String idType,
                                   @RequestParam("idNumber") String idNumber,@RequestParam("age") Integer age,@RequestParam("sex") Integer sex,
                                   @RequestParam("workYear") Integer workYear,@RequestParam("duty") String duty,@RequestParam("title") String title,
                                   @RequestParam("major") String major,@RequestParam("mappingYear") Integer mappingYear,@RequestParam("userId") Long userId){
        return mappingApiService.addPrincipal(files,type,name,idType,idNumber,age,sex,workYear,duty,title,major,mappingYear,userId);
    }


    /**
    * @Description: 查询单个负责人的信息
    * @Author: guohang
    * @Date: 2020/6/1 15:19
    * @Param: [id]
    * @return: com.aaa.qy108.base.ResultData
    */
    @GetMapping("/selectPrincipalById")
    public ResultData selectPrincipalById(@RequestParam("id") String id){
        return mappingApiService.selectPrincipalById(id);
    }


    /**
    * @Description: 删除单个负责人的信息
    * @Author: guohang
    * @Date: 16:05
    * @Param: [id]
    * @return: com.aaa.qy108.base.ResultData
    */
    @DeleteMapping("/deletePrincipalById")
    public ResultData deletePrincipalById(@RequestParam("id") String id){
        return mappingApiService.deletePrincipalById(id);
    }



    /**
    * @Description: 添加测绘成果及档案管理
    * @Author: guohang
    * @Date: 2020/6/3 11:32
    * @Param: [file1, file2, file3, file4, userId]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/addRecord")
    public ResultData addRecord(@RequestParam("file1") MultipartFile file1,@RequestParam("file2") MultipartFile file2
            ,@RequestParam("file3") MultipartFile file3,@RequestParam("file4") MultipartFile file4,@RequestParam("unitId") Long unitId){
        return mappingApiService.addRecord(file1,file2,file3,file4,unitId);
    }















}



