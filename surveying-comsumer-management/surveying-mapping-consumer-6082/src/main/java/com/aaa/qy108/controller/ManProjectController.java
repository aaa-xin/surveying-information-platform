package com.aaa.qy108.controller;

import com.aaa.qy108.base.BaseController;
import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.MappingProject;
import com.aaa.qy108.service.MappingApiService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author guohang
 * @Description
 * @Date 2020/6/3 22:39
 */
@RestController
@RequestMapping("/project")
@Api(value = "项目管理", tags = "项目管理接口")
public class ManProjectController extends BaseController {

    @Autowired
    private MappingApiService mappingApiService;

    /**
    * @Description: 查询项目信息
    * @Author: guohang
    * @Date: 2020/6/3 22:42
    * @Param: [userId]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/allPro")
    public ResultData selectAllPro(@RequestParam("userId") Long userId) {
        List<MappingProject> manProjects = mappingApiService.selectAllPros(userId);
        if (manProjects.size()>0){
            return super.selectSuccess(manProjects);
        }
        return super.selectFailed();
    }

    /**
    * @Description: 根据主键查询项目信息
    * @Author: guohang
    * @Date: 2020/6/3 22:42
    * @Param: [id]
    * @return: com.aaa.qy108.base.ResultData
    */
    @GetMapping("/selectById")
    public ResultData selectProById(Long id){
        MappingProject manProject = mappingApiService.selectById(id);
        if (!"".equals(manProject) && null != manProject){
            return super.selectSuccess(manProject);
        }
        return super.selectFailed();
    }


    /**
    * @Description: 根据id修改项目信息
    * @Author: guohang
    * @Date: 2020/6/3 22:42
    * @Param: [manProject]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/updateById")
    public ResultData updateById(@RequestBody MappingProject manProject){
        Integer i = mappingApiService.updateById(manProject);
        if (null != i && i > 0){
            return super.updateSuccess();
        }
        return super.updateFailed();
    }

//    /**
//     * @Summary:
//     * @Author:  xj
//     * @description
//     *      根据项目类型查询
//     * @Data: 2020/5/22 10:48
//     * @param type
//     * @Return:com.aaa.xj.base.ResultData
//     */
//    @PostMapping("/selectByType")
//    public ResultData selectByType(String projectType,User user){
//        ResultData resultData = iqyService.selectByType(projectType, user);
//        return resultData;
//    }


}
