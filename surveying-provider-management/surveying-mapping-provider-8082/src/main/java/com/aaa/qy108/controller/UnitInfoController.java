package com.aaa.qy108.controller;

import cn.hutool.core.date.DateUtil;
import com.aaa.qy108.base.BaseService;
import com.aaa.qy108.base.CommonController;
import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.mapper.MappingUnitMapper;
import com.aaa.qy108.model.MappingUnit;
import com.aaa.qy108.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.aaa.qy108.status.SelectStatus.SELECT_DATA_FAILED;
import static com.aaa.qy108.status.SelectStatus.SELECT_DATA_SUCCESS;

/**
 * @Author guohang
 * @Description
 * @Date 2020/5/22 19:08
 */
@RestController
@RequestMapping("/unit")
public class UnitInfoController extends CommonController<MappingUnit> {


    @Autowired
    private MappingUnitMapper mappingUnitMapper;

    @Autowired
    private RedisService redisService;


    /**
    * @Description: 查询单位基本信息
    * @Author: guohang
    * @Date: 2020/5/22 19:09
    * @Param: []
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/selectUnitInfo")
    public ResultData selectUnitInfo(@RequestParam("tokenId") String tokenId){
        String token = null;
        try {
            token = redisService.get(tokenId);
            if (null != token){
                MappingUnit unit = new MappingUnit();
                unit.setUserId(Long.valueOf(tokenId));
                MappingUnit mappingUnit = mappingUnitMapper.selectOne(unit);
                if(null != mappingUnit && !"".equals(mappingUnit)){
                    return super.selectSuccess(mappingUnit);
                }else{
                    return super.selectFailed("没有查找到数据！");
                }
            }else{
                return super.loginTimeoutExit();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            return super.loginTimeoutExit();
        }
    }


    /**
    * @Description: 修改单位信息
    * @Author: guohang
    * @Date: 2020/5/22 20:29
    * @Param: [mappingUnit, tokenId]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/updateUnitInfo")
    ResultData updateUnitInfo(@RequestBody MappingUnit mappingUnit, @RequestParam("tokenId") String tokenId){
        String token = null;
        try {
            token = redisService.get(tokenId);
            if (null != token){
                mappingUnit.setModifyTime(DateUtil.now());
                System.out.println(mappingUnit);
                int i = mappingUnitMapper.updateByPrimaryKeySelective(mappingUnit);
                if(i > 0){
                    return super.updateSuccess();
                }else{
                    return super.updateFailed();
                }
            }else{
                return super.loginTimeoutExit();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            return super.loginTimeoutExit();
        }
    }



    @Override
    public BaseService<MappingUnit> getBaseService() {
        return null;
    }
}



