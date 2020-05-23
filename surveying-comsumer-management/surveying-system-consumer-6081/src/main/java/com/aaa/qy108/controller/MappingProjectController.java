package com.aaa.qy108.controller;

import com.aaa.qy108.model.MappingProject;
import com.aaa.qy108.service.SystemApiService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
          * @Author Tzg
          * @Description //
          * @Date 17:23 2020/5/23
          * @Param  * @param null
          * @return
          **/
@RestController
public class MappingProjectController {
    @Autowired
    private SystemApiService systemApiService;
     /**
          * @Author Tzg
          * @Description //测绘项目管理，项目名称模糊查询，类型 ，日期精确查
          * @Date 17:24 2020/5/23
          * @Param  * @param null
          * @return
          **/
     @PostMapping("/projectSelect")
     @ApiOperation(value = "根据查询条件查询测绘项目",notes = "首页查询")
     public List<HashMap> projectSelect(@RequestBody MappingProject mappingProject,@RequestParam("tokenId") String tokenId){

         return systemApiService.projectSelect(mappingProject,tokenId);

     }
          /**
               * @Author Tzg
               * @Description //通过字段查询所有的类型和开工日期。分组
               * @Date 18:05 2020/5/23
               * @Param  * @param null
               * @return
               **/
    @PostMapping("/SelectGroupName")
    @ApiOperation(value = "通过字段查询所有的类型和开工日期",notes = "首页的查询")
    public List<HashMap>  SelectGroupName(@RequestParam("name") String name,@RequestParam("tokenId") String tokenId){
         return systemApiService.SelectGroupName(name,tokenId);
}

}
