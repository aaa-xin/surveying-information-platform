package com.aaa.qy108.service;

import com.aaa.qy108.properties.FtpProperties;
import com.aaa.qy108.utils.DateUtil;
import com.aaa.qy108.utils.FtpFileNameUtil;
import com.aaa.qy108.utils.FtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * @Author guohang
 * @Description
 * @Date 2020/5/29 16:24
 */
@Service
public class UploadService {

    @Autowired
    private FtpProperties ftpProperties;

    /** 
    * @Description: ftp文件上传
    * @Author: guohang
    * @Date: 2020/5/29 16:30
    * @Param: [file] 
    * @return: java.lang.Boolean 
    */ 
    public Boolean uploadFile(MultipartFile file){
        //获取原始文件的名称
        String oldFilename = file.getOriginalFilename();
        //随机生成一个名称
        String newFileName = FtpFileNameUtil.getFileName();
        //生成新的文件名称
        newFileName = newFileName + oldFilename.substring(oldFilename.lastIndexOf("."));
        try {
            //获取今天日期格式化后的数据，用来当做路径
            String filePath = DateUtil.formatDate(new Date(), "yyyy/MM/dd");
            System.out.println("filePath-----"+filePath);
            System.out.println("newFileName-----"+newFileName);
            return FtpUtils.uploadFile(ftpProperties.getHost(), ftpProperties.getPort(), ftpProperties.getUsername(), ftpProperties.getPassword(), filePath, ftpProperties.getBasePath(), newFileName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
    * @Description: 上传文件，两个参数，需要传入一个文件，再传入一个文件名
    * @Author: guohang
    * @Date: 2020/5/30 10:41
    * @Param: [file, newFileName]
    * @return: java.lang.Boolean
    */
    public Boolean uploadFile(MultipartFile file,String newFileName){
        //获取原始文件的名称
        String oldFilename = file.getOriginalFilename();
        //生成新的文件名称
        newFileName = newFileName + oldFilename.substring(oldFilename.lastIndexOf("."));
        try {
            //获取今天日期格式化后的数据，用来当做路径
            String filePath = DateUtil.formatDate(new Date(), "yyyy/MM/dd");
            System.out.println("filePath-----"+filePath);
            System.out.println("newFileName-----"+newFileName);
            return FtpUtils.uploadFile(ftpProperties.getHost(), ftpProperties.getPort(), ftpProperties.getUsername(), ftpProperties.getPassword(), filePath, ftpProperties.getBasePath(), newFileName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
    * @Description: 上传文件，传入三个参数，分别是文件、文件路径、新的文件名称
    * @Author: guohang
    * @Date: 2020/5/30 11:40
    * @Param: [file, filePath, newFileName]
    * @return: java.lang.Boolean
    */
    public Boolean uploadFile(MultipartFile file,String filePath,String newFileName){
        try {
            return FtpUtils.uploadFile(ftpProperties.getHost(), ftpProperties.getPort(), ftpProperties.getUsername(), ftpProperties.getPassword(), filePath, ftpProperties.getBasePath(), newFileName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }



}



