package com.aaa.qy108.controller;

import cn.hutool.core.date.DateUtil;
import com.aaa.qy108.base.BaseService;
import com.aaa.qy108.base.CommonController;
import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.mapper.MappingUnitMapper;
import com.aaa.qy108.model.MappingUnit;
import com.aaa.qy108.model.Principal;
import com.aaa.qy108.model.Technicist;
import com.aaa.qy108.service.MappingunitSerive;
import com.aaa.qy108.service.PrincipalService;
import com.aaa.qy108.service.TechnicistService;
import com.aaa.qy108.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import static com.aaa.qy108.status.SelectStatus.*;


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
    private MappingunitSerive mappingunitSerive;

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
        MappingUnit mappingUnit = mappingunitSerive.selectUnitInfo(unit);
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
        int i = mappingunitSerive.updateUnitInfo(mappingUnit);
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
     *
     * @Param: [files, type, name, idType, idNumber, age, sex, workYear, duty, title, school, graduationDate, degree, degreeeducationBackground, major, titleMajor, startTime, titleTime, startContract, endContract, post, mappingYear, specialPost, affirm, user_id]
     * @Return: com.aaa.qy108.base.ResultData
     * 添加技术人员信息
     * @Author: Liuyibo
     * @Date: 2020/6/3 21:05
     */
    @PostMapping(value = "addTechnicist",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    ResultData addTechnicist(@RequestParam("files") MultipartFile[] files,@RequestParam("majorType") String majorType,@RequestParam("name") String name,@RequestParam("idType") String idType,
                             @RequestParam("idNumber") String idNumber,@RequestParam("age") Integer age,@RequestParam("sex") Integer sex,
                             @RequestParam("workYear") Integer workYear,@RequestParam("duty") String duty,@RequestParam("title") String title,
                             @RequestParam("school") String school,@RequestParam("graduationDate") String graduationDate,@RequestParam("degree") String degree,
                             @RequestParam("educationBackground") String educationBackground,@RequestParam("major") String major,
                             @RequestParam("titleMajor") String titleMajor,@RequestParam("startTime") String startTime,@RequestParam("titleTime") String titleTime,
                             @RequestParam("startContract") String startContract,@RequestParam("endContract") String endContract,@RequestParam("post") String post,
                             @RequestParam("mappingYear") Integer mappingYear,@RequestParam("specialPost") String specialPost,@RequestParam("affirm") String affirm,
                             @RequestParam("userId") Long userId){
        Technicist technicist = new Technicist();
        technicist.setMajorType(majorType).setName(name).setIdType(idType).setIdNumber(idNumber).setAge(age).setSex(sex).setWorkYear(workYear).setDuty(duty).setTitle(title)
                  .setSchool(school).setGraduationDate(graduationDate).setDegree(degree).setEducationBackground(educationBackground).setMajor(major).setTitleMajor(titleMajor)
                  .setStartTime(startTime).setTitleTime(titleTime).setStartContract(startContract).setEndContract(endContract).setPost(post).setMappingYear(mappingYear)
                  .setSpecialPost(specialPost).setAffirm(affirm).setUserId(userId);
        Map<String, Object> addTechnicistMapper = technicistService.addTechnicist(technicist, files, uploadService);
        if (ADD_DATA_SUCCESS.getCode().equals(addTechnicistMapper.get("code"))){
            return super.addSuccess();
        }else {
            return super.addFailed();
        }
    }
    /**
     *
     * @Param: [id]
     * @Return: com.aaa.qy108.base.ResultData
     * 查询单个技术人员信息
     * @Author: Liuyibo
     * @Date: 2020/6/3 21:34
     */
    @GetMapping("/selectTechnicistById")
    ResultData selectTechnicistById(@RequestParam("id") String id){
        Map<String,Object> resultMap = technicistService.selectTechnicistById(id);
        if (SELECT_DATA_SUCCESS.getCode().equals(resultMap.get("code"))){
            return super.selectSuccess(resultMap.get("data"));
        }else{
            return super.selectFailed();
        }
    }
    /**
     *
     * @Param: [id]
     * @Return: com.aaa.qy108.base.ResultData
     * 根据id删除技术人员信息
     * @Author: Liuyibo
     * @Date: 2020/6/3 21:45
     */
    @DeleteMapping("/deleteTechnicistById")
    ResultData deleteTechnicistById(@RequestParam("id") String id){
        int i = technicistService.deleteTechnicistById(id);
        if (i > 0){
            return super.deleteSuccess();
        }
        return super.deleteFailed();
    }
    /**
     *
     * @Param: [technicist]
     * @Return: com.aaa.qy108.base.ResultData
     * 修改技术人员信息
     * @Author: Liuyibo
     * @Date: 2020/6/3 21:59
     */
    @PostMapping("/updatedTechnicistById")
    ResultData updatedTechnicistById(@RequestBody Technicist technicist){
        //获取修改时间，并且放入到对象中
        technicist.setModifyTime(DateUtil.now());
        //修改技术人员信息
        int res = technicistService.updateTechnicistById(technicist);
        if(res > 0){
            return super.updateSuccess();
        }else{
            return super.updateFailed();
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
    ResultData addPrincipal(@RequestParam("files") MultipartFile[] files,@RequestParam("type") String type,@RequestParam("name") String name,@RequestParam("idType") String idType,
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


    /**
    * @Description: 查询单个负责人的信息
    * @Author: guohang
    * @Date: 2020/6/1 15:20
    * @Param: [id]
    * @return: com.aaa.qy108.base.ResultData
    */
    @GetMapping("/selectPrincipalById")
    public ResultData selectPrincipalById(@RequestParam("id") String id){
        Map<String,Object> resultMap = principalService.selectPrincipalById(id);
        if (SELECT_DATA_SUCCESS.getCode().equals(resultMap.get("code"))){
            return super.selectSuccess(resultMap.get("data"));
        }else{
            return super.selectFailed();
        }
    }


    /**
    * @Description: 删除单个负责人的信息
    * @Author: guohang
    * @Date: 2020/6/1 16:07
    * @Param: [id]
    * @return: com.aaa.qy108.base.ResultData
    */
    @DeleteMapping("/deletePrincipalById")
    ResultData deletePrincipalById(@RequestParam("id") String id){
        int i = principalService.deletePrincipalById(id);
        if (i > 0){
            return super.deleteSuccess();
        }
        return super.deleteFailed();
    }


    /**
    * @Description: 添加测绘成果及档案管理
    * @Author: guohang
    * @Date: 2020/6/3 13:02
    * @Param: [file1, file2, file3, file4, unitId]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/addRecord")
    public ResultData addRecord(@RequestParam("file1") MultipartFile file1,@RequestParam("file2") MultipartFile file2
            ,@RequestParam("file3") MultipartFile file3,@RequestParam("file4") MultipartFile file4,@RequestParam("unitId") Long unitId){
        Map<String,Object> resultMap = mappingunitSerive.addRecord(file1,file2,file3,file4,unitId,uploadService);
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



