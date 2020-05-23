package com.aaa.qy108.service;

import com.aaa.qy108.base.BaseService;
import com.aaa.qy108.mapper.MappingUnitMapper;
import com.aaa.qy108.model.MappingUnit;
import com.aaa.qy108.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.aaa.qy108.status.LoginStatus.LOGIN_TIMEOUT_EXIT;

/**
 * @Author Qin
 * @Date 2020/5/22 21:53
 * @Description
 **/
@Service
public class MappingUtilSearchService extends BaseService<MappingUnit> {
    @Autowired
    private MappingUnitMapper mappingUnitMapper;

    /**
    * @Description: 测绘单位查询，单位名称模糊查询，单位地域和单位资质准确查询
    * @Param: [hashMap]
    * @return: java.util.List<java.util.HashMap>
    * @Author: Qin
    * @Date: 2020/5/22
    */
    public List<HashMap> utilSelect(MappingUnit mappingUnit, String tokenId, RedisService redisService){

        List<HashMap> list = new ArrayList<>();
        HashMap<Object, Object> hm = new HashMap<>();
       //如果，tokenid为空，就说明是非法登录，直接返回
        if (!"".equals(tokenId) || null != tokenId){
            //判断redis中是否还存在这个token，如果不存在，就证明已经失效，需要让用户重新登录
            if (null != redisService.get(tokenId)){
                  //如果传过来的没有查询条件，则把所有的测绘单位都查询出来
                if (null==mappingUnit){
                    return  mappingUnitMapper.selectAllUnit();
                }
                return  mappingUnitMapper.selcetUnit(mappingUnit);
            }
        }
        hm.put("code",LOGIN_TIMEOUT_EXIT.getCode());
        hm.put("msg",LOGIN_TIMEOUT_EXIT.getMsg());
        list.add(hm);
        return list;
    }

    /**
    * @Description: 通过字段查询所有的区域和资质，进行分组
    * @Param: [feild, redisService, tokenId]
    * @return: java.util.List<java.util.HashMap>
    * @Author: Qin
    * @Date: 2020/5/23
    */
    public List<HashMap> selectGroupByFeild(String feild,RedisService redisService, String tokenId){

        List<HashMap> list = new ArrayList<>();
        HashMap<Object, Object> hm = new HashMap<>();
        //如果，tokenid为空，就说明是非法登录，直接返回
        if (!"".equals(tokenId) || null != tokenId){
            //判断redis中是否还存在这个token，如果不存在，就证明已经失效，需要让用户重新登录
            if (null != redisService.get(tokenId)){
                return   mappingUnitMapper.selectGroupByFeild(feild);
            }
        }
        hm.put("code",LOGIN_TIMEOUT_EXIT.getCode());
        hm.put("msg",LOGIN_TIMEOUT_EXIT.getMsg());
        list.add(hm);
        return list;
       
    }
}
