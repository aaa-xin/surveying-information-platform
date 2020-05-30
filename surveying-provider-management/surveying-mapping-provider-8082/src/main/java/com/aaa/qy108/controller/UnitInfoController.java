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
import com.aaa.qy108.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

import static com.aaa.qy108.status.AddStatus.ADD_DATA_SUCCESS;


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
    private UploadService uploadService;


    /**
    * @Description: 查询单位基本信息
    * @Author: guohang
    * @Date: 2020/5/22 19:09
    * @Param: [userId]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/selectUnitInfo")
    public ResultData selectUnitInfo(@RequestParam("userId") String userId){
        MappingUnit unit = new MappingUnit();
        unit.setUserId(Long.valueOf(userId));
        MappingUnit mappingUnit = mappingUnitMapper.selectOne(unit);
        if(null != mappingUnit && !"".equals(mappingUnit)){
            return super.selectSuccess(mappingUnit);
        }else{
            return super.selectFailed("没有查找到数据！");
        }
    }


    /**
    * @Description: 修改单位信息
    * @Author: guohang
    * @Date: 2020/5/22 20:29
    * @Param: [mappingUnit]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/updateUnitInfo")
    ResultData updateUnitInfo(@RequestBody MappingUnit mappingUnit){
        mappingUnit.setModifyTime(DateUtil.now());
        int i = mappingUnitMapper.updateByPrimaryKeySelective(mappingUnit);
        if(i > 0){
            return super.updateSuccess();
        }else{
            return super.updateFailed();
        }
    }


    /** 
    * @Description: 查询单位中的全部负责人 
    * @Author: guohang
    * @Date: 2020/5/28 16:24
    * @Param: [userId]
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    @GetMapping("/selectAllPrincipal")
    ResultData selectAllPrincipal(@RequestParam("userId") String userId){
        Principal principal = new Principal().setUserId(Long.valueOf(userId));
        List<Principal> principals = null;
        try {
            principals = principalService.queryList(principal);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(null != principals && !principals.isEmpty()){
            return super.selectSuccess(principals);
        }else{
            return super.selectFailed("没有查找到数据！");
        }
    }


    /** 
    * @Description: 查询全部技术员信息
    * @Author: guohang
    * @Date: 2020/5/28 17:23
    * @Param: [userId]
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    @GetMapping("/selectAllTechnicist")
    ResultData selectAllTechnicist(@RequestParam("userId") String userId){
        Technicist technicist = new Technicist().setUserId(Long.valueOf(userId));
        List<Technicist> technicists = null;
        try {
            technicists = technicistService.queryList(technicist);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(null != technicists && !technicists.isEmpty()){
            return super.selectSuccess(technicists);
        }else{
            return super.selectFailed("没有查找到数据！");
        }

    }


    /** 
    * @Description: 添加单位负责人 
    * @Author: guohang
    * @Date: 2020/5/30 11:13
    * @Param: [file, type, name, idType, idNumber, age, sex, workYear, duty, title, major, mappingYear, userId] 
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    @PostMapping(value = "/addPrincipal",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData addPrincipal(@RequestBody MultipartFile[] files,@RequestParam("type") String type,@RequestParam("name") String name,@RequestParam("idType") String idType,
                            @RequestParam("idNumber") String idNumber,@RequestParam("age") Integer age,@RequestParam("sex") Integer sex,
                            @RequestParam("workYear") Integer workYear,@RequestParam("duty") String duty,@RequestParam("title") String title,
                            @RequestParam("major") String major,@RequestParam("mappingYear") Integer mappingYear,@RequestParam("userId") Long userId){
        Principal principal = new Principal();
        principal.setType(type).setName(name).setIdType(idType).setIdNumber(idNumber).setAge(age).
                setSex(sex).setWorkYear(workYear).setDuty(duty).setTitle(title).setMajor(major).setMappingYear(mappingYear).setUserId(userId);
        Map<String,Object> resultMap = principalService.addPrincipal(principal,files,uploadService);
        if (ADD_DATA_SUCCESS.getCode().equals(resultMap.get("code"))){
            return super.addSuccess();
        }else {
            return super.addFailed();
        }
    }
    
    
    
    
    
    


    @Override
    public BaseService<MappingUnit> getBaseService() {
        return null;
    }
}



