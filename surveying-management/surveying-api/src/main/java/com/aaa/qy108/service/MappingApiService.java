package com.aaa.qy108.service;

import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.config.FeignMultipartConfig;
import com.aaa.qy108.model.MappingProject;
import com.aaa.qy108.model.MappingUnit;
import com.aaa.qy108.model.Technicist;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author guohang
 * @Description 测绘管理中的api接口
 * @Date 2020/5/22 18:26
 */
@FeignClient(value ="MAPPING-PROVIDER",configuration = FeignMultipartConfig.class)
public interface MappingApiService {

    /**
    * @Description: 查询单位基本信息
    * @Author: guohang
    * @Date: 2020/5/22 19:08
    * @Param: [userId]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/unit/selectUnitInfo")
    ResultData selectUnitInfo(@RequestParam("userId") String userId);

    /**
    * @Description: 修改单位信息
    * @Author: guohang
    * @Date: 2020/5/22 20:29
    * @Param: [mappingUnit]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/unit/updateUnitInfo")
    ResultData updateUnitInfo(@RequestBody MappingUnit mappingUnit);


    /**
    * @Description: 查询全部的单位负责人信息
    * @Author: guohang
    * @Date: 2020/5/28 15:50
    * @Param: [userId]
    * @return: com.aaa.qy108.base.ResultData
    */
    @GetMapping("/unit/selectAllPrincipal")
    ResultData selectAllPrincipal(@RequestParam("userId") String userId);


    /**
    * @Description: c查询全部技术员信息
    * @Author: guohang
    * @Date: 2020/5/28 17:22
    * @Param: [userId]
    * @return: com.aaa.qy108.base.ResultData
    */
    @GetMapping("/unit/selectAllTechnicist")
    ResultData selectAllTechnicist(@RequestParam("userId") String userId);


    /** 
    * @Description: ftp文件上传，因为feign只能发送默认普通类型，不能发送特殊类型，所以需要转换
    *               让postmapping去接收MultipartFile类型
    * @Author: guohang
    * @Date: 2020/5/29 15:57
    * @Param: [file] 
    * @return: java.lang.Boolean 
    */
    @PostMapping(value = "/uploadFile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean uploadFile(MultipartFile file);

    /**
     * @Description: 添加单位负责人
     * @Author: guohang
     * @Date: 2020/5/30 11:02
     * @Param: [file, type, name, idType, idNumber, age, sex, workYear, duty, title, major, mappingYear, userId]
     * @return: com.aaa.qy108.base.ResultData
     */
    @PostMapping(value = "/unit/addPrincipal",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData addPrincipal(@RequestPart(value = "files") MultipartFile[] files,@RequestParam("type") String type,@RequestParam("name") String name,@RequestParam("idType") String idType,
                            @RequestParam("idNumber") String idNumber,@RequestParam("age") Integer age,@RequestParam("sex") Integer sex,
                            @RequestParam("workYear") Integer workYear,@RequestParam("duty") String duty,@RequestParam("title") String title,
                            @RequestParam("major") String major,@RequestParam("mappingYear") Integer mappingYear,@RequestParam("userId") Long userId);


    /**
    * @Description: 查询单个负责人的信息
    * @Author: guohang
    * @Date: 2020/6/1 15:19
    * @Param: [id]
    * @return: com.aaa.qy108.base.ResultData
    */
    @GetMapping("/unit/selectPrincipalById")
    ResultData selectPrincipalById(@RequestParam("id") String id);

    /**
    * @Description: 删除单个负责人的信息
    * @Author: guohang
    * @Date: 2020/6/1 16:05
    * @Param: [id]
    * @return: com.aaa.qy108.base.ResultData
    */
    @DeleteMapping("/unit/deletePrincipalById")
    ResultData deletePrincipalById(@RequestParam("id") String id);

    
    /** 
    * @Description: 添加测绘成果及档案管理 
    * @Author: guohang
    * @Date: 2020/6/3 18:39
    * @Param: [file1, file2, file3, file4, unitId] 
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    @PostMapping(value = "/unit/addRecord",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData addRecord(@RequestPart("file1") MultipartFile file1,@RequestPart("file2") MultipartFile file2
            ,@RequestPart("file3") MultipartFile file3,@RequestPart("file4") MultipartFile file4,@RequestParam("unitId") Long unitId);


    /**
    * @Description: 查询项目
    * @Author: guohang
    * @Date: 2020/6/3 22:47
    * @Param: [userId]
    * @return: java.util.List<com.aaa.qy108.model.MappingProject>
    */
    @PostMapping("/project/allPro")
    List<MappingProject> selectAllPros(@RequestParam("userId") Long userId);

    /**
    * @Description: 通过id查询项目
    * @Author: guohang
    * @Date: 2020/6/3 22:47
    * @Param: [id]
    * @return: com.aaa.qy108.model.MappingProject
    */
    @GetMapping("/project/selectById")
    MappingProject selectById(@RequestParam("id") Long id);
    /**
     *
     * @Param: [files, type, name, idType, idNumber, age, sex, workYear, duty, title, school, graduationDate, degree, degreeeducationBackground, major, titleMajor, startTime, titleTime, startContract, endContract, post, mappingYear, specialPost, affirm, user_id]
     * @Return: com.aaa.qy108.base.ResultData
     * 添加技术人员信息
     * @Author: Liuyibo
     * @Date: 2020/6/3 21:05
     */
    @PostMapping(value = "/unit/addTechnicist",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    ResultData addTechnicist(@RequestPart(value = "files") MultipartFile[] files,@RequestParam("majorType") String majorType,@RequestParam("name") String name,@RequestParam("idType") String idType,
                             @RequestParam("idNumber") String idNumber,@RequestParam("age") Integer age,@RequestParam("sex") Integer sex,
                             @RequestParam("workYear") Integer workYear,@RequestParam("duty") String duty,@RequestParam("title") String title,
                             @RequestParam("school") String school,@RequestParam("graduationDate") String graduationDate,@RequestParam("degree") String degree,
                             @RequestParam("educationBackground") String educationBackground,@RequestParam("major") String major,
                             @RequestParam("titleMajor") String titleMajor,@RequestParam("startTime") String startTime,@RequestParam("titleTime") String titleTime,
                             @RequestParam("startContract") String startContract,@RequestParam("endContract") String endContract,@RequestParam("post") String post,
                             @RequestParam("mappingYear") Integer mappingYear,@RequestParam("specialPost") String specialPost,@RequestParam("affirm") String affirm,
                             @RequestParam("userId") Long userId);
    /**
     *
     * @Param: [id]
     * @Return: com.aaa.qy108.base.ResultData
     * 查询单个技术人员信息
     * @Author: Liuyibo
     * @Date: 2020/6/3 21:34
     */
    @GetMapping("/unit/selectTechnicistById")
    ResultData selectTechnicistById(@RequestParam("id") String id);
    /**
     *
     * @Param: [id]
     * @Return: com.aaa.qy108.base.ResultData
     * 根据id删除技术人员信息
     * @Author: Liuyibo
     * @Date: 2020/6/3 21:45
     */
    @DeleteMapping("/unit/deleteTechnicistById")
    ResultData deleteTechnicistById(@RequestParam("id") String id);
    /**
     *
     * @Param: [technicist]
     * @Return: com.aaa.qy108.base.ResultData
     * 修改技术人员信息
     * @Author: Liuyibo
     * @Date: 2020/6/3 21:59
     */
    @PostMapping("/unit/updatedTechnicistById")
    ResultData updatedTechnicistById(@RequestBody Technicist technicist);

    /**
    * @Description: 通过id修改项目
    * @Author: guohang
    * @Date: 2020/6/3 22:47
    * @Param: [manProject]
    * @return: java.lang.Integer
    */
    @PostMapping("/project/updateById")
    Integer updateById(@RequestBody MappingProject manProject);




}
