package com.aaa.qy108.utils;

import java.util.Random;

/**
 * @Author guohang
 * @Description
 * @Date 2020/5/29 16:32
 */
public class FtpFileNameUtil {

    private FtpFileNameUtil(){}


    /**
    * @Description: 生成随机文件名的方法
    * @Author: guohang
    * @Date: 2020/5/29 16:32
    * @Param: []
    * @return: java.lang.String
    */
    public static String getFileName(){
        //获取当前的毫秒数
        long timeMillis = System.currentTimeMillis();
        //随机生成一个999以内的数字
        Random random = new Random();
        Integer randomNum = random.nextInt(999999);
        //拼接成一个新名称，%占位符，d代表数字，03代表3位数，如果不够三位则在前边补0
        String fileName = timeMillis + String.format("%06d", randomNum);
        return fileName;
    }



}



