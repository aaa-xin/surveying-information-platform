package com.aaa.qy108.service;

import com.aaa.qy108.base.BaseService;
import com.aaa.qy108.mapper.MappingUnitMapper;
import com.aaa.qy108.model.MappingUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author guohang
 * @Description
 * @Date 2020/6/1 16:41
 */
@Service
public class MappingunitSerive extends BaseService<MappingUnit> {

    @Autowired
    private MappingUnitMapper mappingUnitMapper;

    /**
    * @Description: 查询单位信息
    * @Author: guohang
    * @Date: 2020/6/1 16:57
    * @Param: [unit]
    * @return: com.aaa.qy108.model.MappingUnit
    */
    public MappingUnit selectUnitInfo(MappingUnit unit) {
        return mappingUnitMapper.selectOne(unit);
    }


    /**
    * @Description: 修改单位信息
    * @Author: guohang
    * @Date: 2020/6/1 17:03
    * @Param: [mappingUnit]
    * @return: int
    */
    public int updateUnitInfo(MappingUnit mappingUnit) {
        return mappingUnitMapper.updateByPrimaryKeySelective(mappingUnit);
    }








}




