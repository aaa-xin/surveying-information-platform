package com.aaa.qy108.controller;

import cn.hutool.core.date.DateUtil;
import com.aaa.qy108.base.BaseService;
import com.aaa.qy108.base.CommonController;
import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.mapper.MappingUnitMapper;
import com.aaa.qy108.model.MappingUnit;
import com.aaa.qy108.model.Principal;
import com.aaa.qy108.model.Technicist;
import com.aaa.qy108.redis.RedisService;
import com.aaa.qy108.service.PrincipalService;
import com.aaa.qy108.service.TechnicistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Author guohang
 * @Description
 * @Date 2020/5/22 19:08
 */
@RestController
@RequestMapping("/unit")
public class UnitInfoController extends CommonController<MappingUnit> {


    @Autowired
    private PrincipalService principalService;

    @Autowired
    private TechnicistService technicistService;

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


    /** 
    * @Description: 查询单位中的全部负责人 
    * @Author: guohang
    * @Date: 2020/5/28 16:24
    * @Param: [] 
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    @GetMapping("/selectAllPrincipal")
    ResultData selectAllPrincipal(@RequestParam("tokenId") String tokenId){
        String token = null;
        try {
            token = redisService.get(tokenId);
            if (null != token){
                Principal principal = new Principal().setUserId(Long.valueOf(tokenId));
                List<Principal> principals = principalService.queryList(principal);

                if(null != principals && !principals.isEmpty()){
                    return super.selectSuccess(principals);
                }else{
                    return super.selectFailed("没有查找到数据！");
                }
            }else{
                return super.loginTimeoutExit();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            return super.loginTimeoutExit();
        } catch (Exception e) {
            e.printStackTrace();
            return super.loginTimeoutExit();
        }
    }


    /** 
    * @Description: 查询全部技术员信息
    * @Author: guohang
    * @Date: 2020/5/28 17:23
    * @Param: [tokenId] 
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    @GetMapping("/selectAllTechnicist")
    ResultData selectAllTechnicist(@RequestParam("tokenId") String tokenId){
        String token = null;
        try {
            token = redisService.get(tokenId);
            if (null != token){
                Technicist technicist = new Technicist().setUserId(Long.valueOf(tokenId));
                List<Technicist> technicists = technicistService.queryList(technicist);

                if(null != technicists && !technicists.isEmpty()){
                    return super.selectSuccess(technicists);
                }else{
                    return super.selectFailed("没有查找到数据！");
                }
            }else{
                return super.loginTimeoutExit();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            return super.loginTimeoutExit();
        } catch (Exception e) {
            e.printStackTrace();
            return super.loginTimeoutExit();
        }
    }
    
    
    
    
    
    
    
    
    
    


    @Override
    public BaseService<MappingUnit> getBaseService() {
        return null;
    }
}



