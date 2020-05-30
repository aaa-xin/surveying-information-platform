package com.aaa.qy108.service;

import cn.hutool.core.date.DateUtil;
import com.aaa.qy108.base.BaseService;
import com.aaa.qy108.mapper.PrincipalMapper;
import com.aaa.qy108.mapper.ResourceMapper;
import com.aaa.qy108.model.Principal;
import com.aaa.qy108.model.Resource;
import com.aaa.qy108.properties.FtpProperties;
import com.aaa.qy108.utils.FtpFileNameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.aaa.qy108.status.AddStatus.*;

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
            for (MultipartFile file : files) {
                //添加资源表
                Resource resource = new Resource();
                //设置资源ID
                String resourceId = FtpFileNameUtil.getFileName();
                //获取今天日期格式化后的数据，用来当做路径
                String filePath = com.aaa.qy108.utils.DateUtil.formatDate(new Date(), "yyyy/MM/dd");
                //获取原始文件的名称
                String oldFilename = file.getOriginalFilename();
                //截取文件后缀
                String extName = oldFilename.substring(oldFilename.lastIndexOf("."));
                //生成新的文件名称
                String newFileName = resourceId + "" + extName;
                //设置resource对象的值
                resource.setName(file.getOriginalFilename()).setSize(Long.valueOf(file.getSize())).setPath(ftpProperties.getBasePath()+""+filePath+""+newFileName)
                        .setType(file.getContentType()).setExtName(extName).setRefBizType("身份证").setRefBizId(Long.valueOf(id))
                        .setCreateTime(DateUtil.now()).setId(Long.valueOf(resourceId));
                //数据库添加resource的数据
                int r = resourceMapper.insert(resource);
                if (r > 0){
                    //添加成功后上传文件
                    Boolean result = uploadService.uploadFile(file, filePath, newFileName);
                    if (result){
                        resultMap.put("code",ADD_DATA_SUCCESS.getCode());
                        resultMap.put("msg",ADD_DATA_SUCCESS.getMsg());
                        return resultMap;
                    }
                }
            }
        }
        resultMap.put("code",ADD_DATA_FAILED.getCode());
        resultMap.put("msg",ADD_DATA_FAILED.getMsg());
        return resultMap;
    }




}



