package com.aaa.qy108.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.SocketException;

/**
 * @Author guohang
 * @Description ftp工具类，负责上传与下载。
 *              这个工具类需要放入config中，因为如果放入common中可能会被consumer引入，但是consumer不需要这个依赖，所以有点多余。
 * @Date 2020/5/15 19:27
 */
@Slf4j
public class FtpUtils {

    /**
     * 私有化构造方法
     */
    private FtpUtils(){

    }

    /** 
    * @Description: 登录FTP服务器 
    * @Author: guohang
    * @Date: 2020/5/15 19:40
    * @Param: [host, port, username, password] 
    * @return: org.apache.commons.net.ftp.FTPClient 
    */ 
    public static FTPClient getFtpClient(String host, int port, String username, String password){
        //创建FTPClient对象,这是ftp给java提供的api
        FTPClient ftpClient = new FTPClient();
        try {
            // 连接FTP服务器，如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftpClient.connect(host,port);
            // 登录FTP服务器
            ftpClient.login(username,password);
            // 如果登录成功，返回码是230。如果失败，则返回530/503
            int replyCode = ftpClient.getReplyCode();
            // 判断返回码是否合法，如果不合法说明账号和密码错误
            if (!FTPReply.isPositiveCompletion(replyCode)){
                log.info("未连接到FTP，用户名或密码错误。");
                //如果连接失败，释放资源
                ftpClient.disconnect();
                return null;
            }else {
                log.info("FTP连接成功。");
                return ftpClient;
            }
        } catch (SocketException e) {
            e.printStackTrace();
            log.info("FTP的IP地址可能错误，请正确配置。");
        } catch (IOException e) {
            e.printStackTrace();
            log.info("FTP的端口错误,请正确配置。");
        }
        return ftpClient;
    }
    
    
    /** 
    * @Description: 上传文件，根据日期进行分文件夹上传
    * @Author: guohang
    * @Date: 2020/5/15 19:39
    * @Param: [host, port, username, password, filePath, basePath, fileName, input] 
    * @return: java.lang.Boolean 
    */ 
    public static Boolean uploadFile(String host, Integer port, String username, String password,
                                      String filePath, String basePath, String fileName, InputStream input){
        //方便拼接路径
        String tmpPath = "";
        FTPClient ftpClient = null;
        try {
            //获取ftp连接，然后判断是否为null，不为空则连接成功，为null则返回false
            ftpClient = getFtpClient(host, port, username, password);
            if (null == ftpClient){
                return false;
            }
            //走到这里说明连接成功，开始进行检测要上传的文件夹是否存在
            //basePath + filePath---> /home/ftp/2020/05/15
            //changeWorkingDirectory():判断路径是否存在，如果存在返回true，如果不存在则返回false
            if (!ftpClient.changeWorkingDirectory(basePath+filePath)){
                //说明路径不存在，需要进行创建文件夹，java中创建文件夹需要一层层创建，所以需要线分割当前日期
                String[] dirs = filePath.split("/");
                //把basePath赋值给tmpPath
                tmpPath = basePath;
                //循环要创建的文件夹数组
                for (String dir : dirs) {
                    //文件夹不能为null，判断是否为空
                    if (null == dir || "".equals(dir)){
                        //如果为空，跳出当前循环，进入下一次
                        continue;
                    }
                    //否则拼接路径，如/home/ftp/2020
                    tmpPath += "/" + dir;
                    //再次检测确保路径不存在
                    if (!ftpClient.changeWorkingDirectory(tmpPath)){
                        //创建文件夹，返回为bolean，然后直接判断
                        if (!ftpClient.makeDirectory(tmpPath)){
                            return false;
                        }else{
                            log.info("创建文件夹路径：" + ftpClient.changeWorkingDirectory(tmpPath));
                        }
                    }
                }
            }
            //如果没有if进入或者从if中出来，证明文件夹已经有了，可以进行直接上传
            //FTP支持IO操作，但是IO效率没有二进制字符流高，把文件转换为二进制字符流上传
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            //上传文件，storeFile():就是文件上传的方法，返回true/false
            if (!ftpClient.storeFile(fileName,input)){
                return false;
            }
            //关闭输入流
            input.close();
            //退出ftp
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()){
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }


    /** 
    * @Description: 下载文件
    * @Author: guohang
    * @Date: 2020/5/15 21:15
    * @Param: [host, port, username, password, ftpPath, localPath, fileName] 
    * @return: java.io.File 
    */ 
    public static File downloadFtpFile(String host, int port, String username, String password, String ftpPath,
                                       String localPath, String fileName){
        FTPClient ftpClient = null;
        try {
            //获取连接
            ftpClient = getFtpClient(host, port, username, password);
            //设置中文支持
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(ftpPath);
            //判断目录下是否存在文件，如果不存在则创建文件
            File localFile  = new File(localPath, fileName);
            if (!localFile.getParentFile().exists()){
                localFile.getParentFile().mkdirs();
            }
            //创建文件
            OutputStream outputStream = new FileOutputStream(localFile);
            //下载文件
            ftpClient.retrieveFile(fileName, outputStream);
            outputStream.close();
            ftpClient.logout();
            return localFile;
        } catch (FileNotFoundException e) {
            log.error("没有找到" + ftpPath + "文件，" + e);
        } catch (SocketException e) {
            log.error("连接FTP失败， " + e);
        } catch (IOException e) {
            log.error("文件读取错误。" + e);
        } finally {
            if (ftpClient != null && ftpClient.isConnected()){
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}



