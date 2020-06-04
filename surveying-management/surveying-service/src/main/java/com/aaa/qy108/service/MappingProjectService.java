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
 * @return
 **/
@Service
public class MappingProjectService extends BaseService<MappingProject> {

    @Autowired
    private MappingProjectMapper mappingProjectMapper;

    /**
    * @Description: 测绘项目管理，项目名称模糊查询，类型 ，日期精确查
    * @Author: guohang
    * @Date: 2020/6/3 22:52
    * @Param: [mappingProject]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    */
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
    * @Description: 通过字段查询所有的类型和开工日期。分组
    * @Author: guohang
    * @Date: 2020/6/3 22:52
    * @Param: [name]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    */
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
    * @Author: guohang
    * @Date: 2020/6/3 22:52
    * @Param: [id]
    * @return: java.util.HashMap<java.lang.String,java.lang.Object>
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
    

    /** 
    * @Description: 项目查询 
    * @Author: guohang
    * @Date: 2020/6/3 22:54
    * @Param: [userId]
    * @return: java.util.List<MappingProject> 
    */ 
    public List<MappingProject> selectAllPros(Long userId){
        try {
            //查询公司信息
            List<MappingProject> manProjects = mappingProjectMapper.selectAllPros(userId);
            //判断是否查询出值
            if (!"".equals(manProjects) && null != manProjects){
                return manProjects;
            }
            else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
    * @Description: 根据主键id进行查询
    * @Author: guohang
    * @Date: 2020/6/3 22:55
    * @Param: [id]
    * @return: com.aaa.qy108.model.MappingProject
    */
    public MappingProject selectById(Long id){
        try {
            if (!"".equals(id)){
                //根据id获取项目信息
                MappingProject manProject = mappingProjectMapper.selectByPrimaryKey(id);
                //判断是否存在该项目
                if (!"".equals(manProject) && null != manProject){
                    return manProject;
                }else {
                    return null;
                }
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
    * @Description: 通过id修改项目
    * @Author: guohang
    * @Date: 2020/6/3 22:55
    * @Param: [manProject]
    * @return: java.lang.Integer
    */
    public Integer updateById(MappingProject manProject){
        System.out.println(manProject);
        int i = 0;
        try {
            if (!"".equals(manProject)){
                //执行修改的方法 返回受影响的行数
                i = mappingProjectMapper.updateByPrimaryKeySelective(manProject);
                //判断受影响的行数
                if (i>0){
                    return i;
                }else {
                    //再次执行修改操作
                    int j = mappingProjectMapper.updateByPrimaryKeySelective(manProject);
                    if (j>0){
                        return j;
                    }
                }
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }













}
