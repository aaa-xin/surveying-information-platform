package com.aaa.qy108.service;

import com.aaa.qy108.base.BaseService;
import com.aaa.qy108.mapper.MappingUnitMapper;
import com.aaa.qy108.model.MappingUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.qy108.status.SelectStatus.*;
import static com.aaa.qy108.status.SelectStatus.SELECT_DATA_BY_ID_FAILED;

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
    public Map<String,Object> utilSelect(MappingUnit mappingUnit){
        HashMap<String, Object> resultMap = new HashMap<>();
        List<HashMap> restdata = new ArrayList<>();
        if (null==mappingUnit){
            restdata= mappingUnitMapper.selectAllUnit();
        }else {
            restdata = mappingUnitMapper.selcetUnit(mappingUnit);
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
    * @Description: 通过字段查询所有的区域和资质，进行分组
    * @Param: [feild, redisService, tokenId]
    * @return: java.util.List<java.util.HashMap>
    * @Author: Qin
    * @Date: 2020/5/23
    */
    public Map<String,Object> selectGroupByFeild(String feild){
        HashMap<String, Object> resultMap = new HashMap<>();
        List<HashMap> restdata= mappingUnitMapper.selectGroupByFeild(feild);
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
    * @Description: 通过id查询详细地单位信息
    * @Param: [id]
    * @return: java.util.HashMap<java.lang.String,java.lang.Object>
    * @Author: Qin
    * @Date: 2020/5/30
    */
    public HashMap<String,Object> unitDetail(String id) {
        HashMap<String, Object> resultMap = new HashMap<>();
        if (null != id && !("").equals(id)) {
            List<HashMap> restdata = mappingUnitMapper.unitDetail(id);
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
