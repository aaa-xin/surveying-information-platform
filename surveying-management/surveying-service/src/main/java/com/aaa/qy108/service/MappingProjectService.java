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

import static com.aaa.qy108.status.LoginStatus.*;

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
    public List<HashMap> projectSelect(MappingProject mappingProject, String tokenId, RedisService redisService){

        List<HashMap> list =new ArrayList<>();
        HashMap<Object ,Object> hashMap=new HashMap<>();

       //判断token是否为空，判断是否登录
        if (!"".equals(tokenId)  || null != tokenId){
            //判断redsi中是否有token，如果没有证明登陆失效
            if (null!=redisService.get(tokenId)){
                //判断是否有查询条件，否则测绘项目的名称全部查询
                if (null == mappingProject){
                    return  mappingProjectMapper.SelectAllProject();
                }
                    return mappingProjectMapper.projectSelect(mappingProject);


            }
        }
        hashMap.put("code",LOGIN_TIMEOUT_EXIT.getCode());
        hashMap.put("msg",LOGIN_TIMEOUT_EXIT.getMsg());
        list.add(hashMap);
        return list;

    }
         /**
              * @Author Tzg
              * @Description //通过字段查询所有的类型和开工日期。分组
              * @Date 18:11 2020/5/23
              * @Param  * @param null
              * @return 
              **/
    public List<HashMap> SelectGroupName(String name,RedisService redisService, String tokenId){

        List<HashMap> list = new ArrayList<>();
        HashMap<Object, Object> hashMap = new HashMap<>();
        //如果，tokenid为空，就说明是非法登录，直接返回
        if (!"".equals(tokenId) || null != tokenId){
            //判断redis中是否还存在这个token，如果不存在，就证明已经失效，需要让用户重新登录
            if (null != redisService.get(tokenId)){
                return   mappingProjectMapper.SelectGroupName(name);
            }
        }
        hashMap.put("code",LOGIN_TIMEOUT_EXIT.getCode());
        hashMap.put("msg",LOGIN_TIMEOUT_EXIT.getMsg());
        list.add(hashMap);
        return list;

    }

}
