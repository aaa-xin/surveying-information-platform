package com.aaa.qy108.controller;

import com.aaa.qy108.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author guohang
 * @Description
 * @Date 2020/5/29 16:22
 */
@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;


    /**
    * @Description: ftp文件上传
    * @Author: guohang
    * @Date: 2020/5/29 16:23
    * @Param: [file]
    * @return: java.lang.Boolean
    */
    @PostMapping(value = "/uploadFile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean uploadFile(@RequestBody MultipartFile file){
        return uploadService.uploadFile(file);
    }







}



