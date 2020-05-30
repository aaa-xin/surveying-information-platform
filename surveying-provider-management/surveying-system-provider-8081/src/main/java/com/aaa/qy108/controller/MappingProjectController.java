package com.aaa.qy108.controller;

import com.aaa.qy108.base.BaseService;
import com.aaa.qy108.base.CommonController;
import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.MappingProject;
import com.aaa.qy108.model.MappingUnit;
import com.aaa.qy108.redis.RedisService;
import com.aaa.qy108.service.MappingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.qy108.status.LoginStatus.LOGIN_TIMEOUT_EXIT;
import static com.aaa.qy108.status.SelectStatus.SELECT_DATA_BY_ID_SUCCESS;
import static com.aaa.qy108.status.SelectStatus.SELECT_DATA_SUCCESS;

/**
          * @Author Tzg
          * @Description //
          * @Date 17:33 2020/5/23
          * @Param  * @param null
          * @return
          **/
@RestController
public class MappingProjectController extends CommonController<MappingUnit> {
    @Autowired
    private MappingProjectService mappingProjectService;
    @Autowired
    private RedisService redisService;


    /**
     * @Author Tzg
     * @Description //测绘项目管理，项目名称模糊查询，类型 ，日期精确查
     * @Date 17:37 2020/5/23
     * @Param  * @param null
     * @return
     **/
    @PostMapping("/projectSelect")
    public ResultData projectSelect(@RequestBody MappingProject mappingProject){
        Map<String, Object> resultMap = mappingProjectService.projectSelect(mappingProject);
        if (SELECT_DATA_SUCCESS.getCode().equals(resultMap.get("code"))){
            return super.selectSuccess(resultMap.get("data"));
        }else{
            return super.selectFailed();
        }
    }

 /**
  * @Author Tzg
  * @Description //通过字段查询所有的类型和开工日期。分组
  * @Date 18:10 2020/5/23
  * @Param  * @param null
  * @return
    **/
    @PostMapping("/SelectGroupName")
    public ResultData SelectGroupName(@RequestParam ("name") String name){
        System.out.println(name);
        Map<String, Object> resultMap = mappingProjectService.SelectGroupName(name);
        if (SELECT_DATA_SUCCESS.getCode().equals(resultMap.get("code"))){
            return super.selectSuccess(resultMap.get("data"));
        }else{
            return super.selectFailed();
        }
    }



    /**
    * @Description: 通过id查询测绘工程的详细信息
    * @Param: [id]
    * @return: com.aaa.qy108.base.ResultData
    * @Author: Qin
    * @Date: 2020/5/30
    */
    @PostMapping("/projectDetail")
    public ResultData projectDetail(@RequestParam("id") String id){
        Map<String, Object> resultMap = mappingProjectService.projectDetail(id);
        if (SELECT_DATA_BY_ID_SUCCESS.getCode().equals(resultMap.get("code"))){
            return super.selectSuccess(resultMap.get("data"));
        }else{
            return super.selectFailed();
        }
    }



    @Override
    public BaseService<MappingUnit> getBaseService() {
        return null;
    }
}
