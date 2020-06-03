package com.aaa.qy108.service;

import cn.hutool.core.date.DateUtil;
import com.aaa.qy108.base.BaseService;
import com.aaa.qy108.mapper.PrincipalMapper;
import com.aaa.qy108.mapper.ResourceMapper;
import com.aaa.qy108.model.Principal;
import com.aaa.qy108.model.Resource;
import com.aaa.qy108.properties.FtpProperties;
import com.aaa.qy108.utils.FtpFileNameUtil;
import com.aaa.qy108.utils.FtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.qy108.status.AddStatus.*;
import static com.aaa.qy108.status.SelectStatus.*;

/**
 * @Author guohang
 * @Description Principal的实现类
 * @Date 2020/5/28 16:32
 */
@Service
public class PrincipalService extends BaseService<Principal> {

    @Autowired
    private PrincipalMapper principalMapper;

    @Autowired
    private FtpProperties ftpProperties;

    @Autowired
    private ResourceMapper resourceMapper;

    /**
    * @Description: 添加单位负责人
    * @Author: guohang
    * @Date: 2020/5/30 10:32
    * @Param: [principal, file, uploadService]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    */
    public Map<String, Object> addPrincipal(Principal principal, MultipartFile[] files, UploadService uploadService) {
        Map<String, Object> resultMap = new HashMap<>();
        //设置负责人创建时间
        principal.setCreateTime(DateUtil.now());
        //生成一个id，用于负责人id
        String id = FtpFileNameUtil.getFileName();
        principal.setId(Long.valueOf(id));
        //添加负责人
        int i = principalMapper.insertSelective(principal);
        if (i > 0){
            Boolean result = false;
            for (MultipartFile file : files) {
                //添加资源表
                Resource resource = new Resource();
                //设置资源ID
                String resourceId = FtpFileNameUtil.getFileName();
                //获取今天日期格式化后的数据，用来当做路径
                String filePath = com.aaa.qy108.utils.DateUtil.formatDate(new Date(), "yyyy/MM/dd");
                //获取原始文件的名称
                String oldFilename = file.getOriginalFilename();
                System.out.println(oldFilename);
                //截取文件后缀
                String extName = oldFilename.substring(oldFilename.lastIndexOf("."));
                //生成新的文件名称
                String newFileName = resourceId + "" + extName;
                //设置resource对象的值
                resource.setName(file.getOriginalFilename()).setSize(Long.valueOf(file.getSize())).setPath(ftpProperties.getHttpPath()+"/"+filePath+"/"+newFileName)
                        .setType(file.getContentType()).setExtName(extName).setRefBizType("身份证").setRefBizId(Long.valueOf(id))
                        .setCreateTime(DateUtil.now()).setId(Long.valueOf(resourceId));
                //数据库添加resource的数据
                int r = resourceMapper.insert(resource);
                if (r > 0){
                    //添加成功后上传文件
                    result = uploadService.uploadFile(file, filePath, newFileName);
                }
            }
            if (result){
                resultMap.put("code",ADD_DATA_SUCCESS.getCode());
                resultMap.put("msg",ADD_DATA_SUCCESS.getMsg());
                return resultMap;
            }
        }
        resultMap.put("code",ADD_DATA_FAILED.getCode());
        resultMap.put("msg",ADD_DATA_FAILED.getMsg());
        return resultMap;
    }


    /**
    * @Description: 查询负责人信息
    * @Author: guohang
    * @Date: 2020/6/1 15:42
    * @Param: [id]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    */
    public Map<String, Object> selectPrincipalById(String id) {
        Map<String,Object> resultMap = new HashMap<>();
        Principal principal = principalMapper.selectPrincipalById(id);
        if (principal != null && !"".equals(principal)){
            resultMap.put("code",SELECT_DATA_SUCCESS.getCode());
            resultMap.put("msg",SELECT_DATA_SUCCESS.getMsg());
            resultMap.put("data",principal);
        }else{
            resultMap.put("code",SELECT_DATA_FAILED.getCode());
            resultMap.put("msg",SELECT_DATA_FAILED.getMsg());
        }
        return resultMap;
    }


    /**
    * @Description: 删除某个单位负责人信息
    * @Author: guohang
    * @Date: 2020/6/1 16:42
    * @Param: [id]
    * @return: int
    */
    public int deletePrincipalById(String id) {
        //先删除负责人信息
        Principal principal = new Principal();
        principal.setId(Long.valueOf(id));
        int j = principalMapper.delete(principal);
        if (j > 0){
            //获取看资源表里有几个这个负责人的资源文件
            List<Resource> resources = resourceMapper.select(new Resource().setRefBizId(Long.valueOf(id)));
            //遍历获取每一个资源实体
            for (Resource resource : resources) {
                //删除这个实体和这个实体的ftp文件
                boolean b = FtpUtils.deleteFile(ftpProperties.getHost(), ftpProperties.getPort(), ftpProperties.getUsername(), ftpProperties.getPassword(), resource.getPath());
                int i = resourceMapper.delete(resource);
            }
            return 1;
        }
        return 0;
    }






}



