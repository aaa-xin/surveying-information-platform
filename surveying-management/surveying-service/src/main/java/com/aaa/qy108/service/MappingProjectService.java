package com.aaa.qy108.service;

import com.aaa.qy108.base.BaseService;
import com.aaa.qy108.mapper.MappingProjectMapper;
import com.aaa.qy108.model.MappingProject;
import com.aaa.qy108.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.qy108.status.LoginStatus.*;
import static com.aaa.qy108.status.SelectStatus.*;
import static com.aaa.qy108.status.SelectStatus.SELECT_DATA_BY_ID_FAILED;

/**
          * @Author Tzg
          * @Description //
          * @Date 16:40 2020/5/23
          * @Param  * @param null
          * @return
          **/
@Service
public class MappingProjectService extends BaseService<MappingProject> {
    @Autowired
         private MappingProjectMapper mappingProjectMapper;
         /**
              * @Author Tzg
              * @Description //测绘项目管理，项目名称模糊查询，类型 ，日期精确查
              * @Date 16:44 2020/5/23
              * @Param  * @param null
              * @return
              **/
    public Map<String,Object> projectSelect(MappingProject mappingProject){
        HashMap<String, Object> resultMap = new HashMap<>();
        List<HashMap> restdata = new ArrayList<>();
        if (null == mappingProject){
            restdata = mappingProjectMapper.SelectAllProject();
        }else {
            restdata = mappingProjectMapper.projectSelect(mappingProject);
        }
        if (restdata.size()>0){
            resultMap.put("code",SELECT_DATA_SUCCESS.getCode());
            resultMap.put("msg",SELECT_DATA_SUCCESS.getMsg());
            resultMap.put("data",restdata);
        }else {
            resultMap.put("code",SELECT_DATA_FAILED.getCode());
            resultMap.put("msg",SELECT_DATA_FAILED.getMsg());
        }
        return resultMap;
    }
         /**
              * @Author Tzg
              * @Description //通过字段查询所有的类型和开工日期。分组
              * @Date 18:11 2020/5/23
              * @Param  * @param null
              * @return 
              **/
    public Map<String,Object> SelectGroupName(String name){

        HashMap<String, Object> resultMap = new HashMap<>();
        List<HashMap> restdata = new ArrayList<>();

        restdata= mappingProjectMapper.SelectGroupName(name);
        if (restdata.size()>0){
            resultMap.put("code",SELECT_DATA_SUCCESS.getCode());
            resultMap.put("msg",SELECT_DATA_SUCCESS.getMsg());
            resultMap.put("data",restdata);
        }else {
            resultMap.put("code",SELECT_DATA_FAILED.getCode());
            resultMap.put("msg",SELECT_DATA_FAILED.getMsg());
        }
        return resultMap;
    }

    /**
     * @Description: 通过id查询测绘工程的详细信息
     * @Param: [id]
     * @return: java.util.HashMap<java.lang.String,java.lang.Object>
     * @Author: Qin
     * @Date: 2020/5/30
     */
    public HashMap<String,Object> projectDetail(String id) {
        HashMap<String, Object> resultMap = new HashMap<>();
        if (null != id && !("").equals(id)) {
            List<HashMap> restdata = mappingProjectMapper.projectDetail(id);
            if ( restdata.size() ==1) {
                resultMap.put("code", SELECT_DATA_BY_ID_SUCCESS.getCode());
                resultMap.put("msg", SELECT_DATA_BY_ID_SUCCESS.getMsg());
                resultMap.put("data", restdata);
                return resultMap;
            }
        }
        resultMap.put("code", SELECT_DATA_BY_ID_FAILED.getCode());
        resultMap.put("msg", SELECT_DATA_BY_ID_FAILED.getMsg());
        return resultMap;
    }

}
