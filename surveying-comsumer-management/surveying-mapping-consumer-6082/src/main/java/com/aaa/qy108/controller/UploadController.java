package com.aaa.qy108.controller;

import com.aaa.qy108.base.BaseController;
import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.service.MappingApiService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author guohang
 * @Description
 * @Date 2020/5/29 15:50
 */
@RestController
@Api(value = "文件上传",tags = "文件上传的接口")
public class UploadController extends BaseController {

    @Autowired
    private MappingApiService mappingApiService;

    /**
    * @Description: 上传文件，服务器开启nginx代理，访问路径示例http://192.168.183.143/2020/05/29/1590743762979844.pdf
    * @Author: guohang
    * @Date: 2020/5/29 15:55
    * @Param: [file]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/uploadFile")
    public ResultData uploadFile(MultipartFile file){
        Boolean result = mappingApiService.uploadFile(file);
        if (result){
            return super.uploadSuccess();
        }
        return super.uploadFailed();
    }










}



