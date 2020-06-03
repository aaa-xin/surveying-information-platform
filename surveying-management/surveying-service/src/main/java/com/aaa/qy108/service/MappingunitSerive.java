package com.aaa.qy108.service;

import cn.hutool.core.date.DateUtil;
import com.aaa.qy108.base.BaseService;
import com.aaa.qy108.mapper.MappingUnitMapper;
import com.aaa.qy108.mapper.ResourceMapper;
import com.aaa.qy108.model.MappingUnit;
import com.aaa.qy108.model.Resource;
import com.aaa.qy108.properties.FtpProperties;
import com.aaa.qy108.utils.FtpFileNameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.aaa.qy108.status.AddStatus.ADD_DATA_FAILED;
import static com.aaa.qy108.status.AddStatus.ADD_DATA_SUCCESS;

/**
 * @Author guohang
 * @Description
 * @Date 2020/6/1 16:41
 */
@Service
public class MappingunitSerive extends BaseService<MappingUnit> {

    @Autowired
    private MappingUnitMapper mappingUnitMapper;

    @Autowired
    private FtpProperties ftpProperties;

    @Autowired
    private ResourceMapper resourceMapper;


    /**
    * @Description: 查询单位信息
    * @Author: guohang
    * @Date: 2020/6/1 16:57
    * @Param: [unit]
    * @return: com.aaa.qy108.model.MappingUnit
    */
    public MappingUnit selectUnitInfo(MappingUnit unit) {
        return mappingUnitMapper.selectOne(unit);
    }


    /**
    * @Description: 修改单位信息
    * @Author: guohang
    * @Date: 2020/6/1 17:03
    * @Param: [mappingUnit]
    * @return: int
    */
    public int updateUnitInfo(MappingUnit mappingUnit) {
        return mappingUnitMapper.updateByPrimaryKeySelective(mappingUnit);
    }


    /**
    * @Description: 添加测绘成果及档案管理
    * @Author: guohang
    * @Date: 2020/6/3 13:02
    * @Param: [file1, file2, file3, file4, unitId, uploadService]
    * @return: Map<String,Object>
    */
    public Map<String,Object> addRecord(MultipartFile file1, MultipartFile file2, MultipartFile file3, MultipartFile file4, Long unitId, UploadService uploadService) {
        Map<String, Object> resultMap = new HashMap<>();
        Boolean result = false;
        if (null != file1){
            //添加资源表
            Resource resource = new Resource();
            //设置资源ID
            String resourceId = FtpFileNameUtil.getFileName();
            //获取今天日期格式化后的数据，用来当做路径
            String filePath = com.aaa.qy108.utils.DateUtil.formatDate(new Date(), "yyyy/MM/dd");
            //获取原始文件的名称
            String oldFilename = file1.getOriginalFilename();
            //截取文件后缀
            String extName = oldFilename.substring(oldFilename.lastIndexOf("."));
            //生成新的文件名称
            String newFileName = resourceId + "" + extName;
            //设置resource对象的值
            resource.setName(file1.getOriginalFilename()).setSize(Long.valueOf(file1.getSize())).setPath(ftpProperties.getHttpPath()+"/"+filePath+"/"+newFileName)
                    .setType(file1.getContentType()).setExtName(extName).setRefBizType("档案主管部门认证认可文件或取得相应考核的文件（概要）").setRefBizId(Long.valueOf(unitId))
                    .setCreateTime(DateUtil.now()).setId(Long.valueOf(resourceId));
            //数据库添加resource的数据
            int r = resourceMapper.insert(resource);
            if (r > 0){
                //添加成功后上传文件
                result = uploadService.uploadFile(file1, filePath, newFileName);
            }
        }
        if (null != file2){
            //添加资源表
            Resource resource = new Resource();
            //设置资源ID
            String resourceId = FtpFileNameUtil.getFileName();
            //获取今天日期格式化后的数据，用来当做路径
            String filePath = com.aaa.qy108.utils.DateUtil.formatDate(new Date(), "yyyy/MM/dd");
            //获取原始文件的名称
            String oldFilename = file2.getOriginalFilename();
            //截取文件后缀
            String extName = oldFilename.substring(oldFilename.lastIndexOf("."));
            //生成新的文件名称
            String newFileName = resourceId + "" + extName;
            //设置resource对象的值
            resource.setName(file2.getOriginalFilename()).setSize(Long.valueOf(file2.getSize())).setPath(ftpProperties.getHttpPath()+"/"+filePath+"/"+newFileName)
                    .setType(file2.getContentType()).setExtName(extName).setRefBizType("测绘成果及资料档案管理制度（概要）").setRefBizId(Long.valueOf(unitId))
                    .setCreateTime(DateUtil.now()).setId(Long.valueOf(resourceId));
            //数据库添加resource的数据
            int r = resourceMapper.insert(resource);
            if (r > 0){
                //添加成功后上传文件
                result = uploadService.uploadFile(file2, filePath, newFileName);
            }
        }
        if (null != file3){
            //添加资源表
            Resource resource = new Resource();
            //设置资源ID
            String resourceId = FtpFileNameUtil.getFileName();
            //获取今天日期格式化后的数据，用来当做路径
            String filePath = com.aaa.qy108.utils.DateUtil.formatDate(new Date(), "yyyy/MM/dd");
            //获取原始文件的名称
            String oldFilename = file3.getOriginalFilename();
            System.out.println(oldFilename);
            //截取文件后缀
            String extName = oldFilename.substring(oldFilename.lastIndexOf("."));
            //生成新的文件名称
            String newFileName = resourceId + "" + extName;
            //设置resource对象的值
            resource.setName(file3.getOriginalFilename()).setSize(Long.valueOf(file3.getSize())).setPath(ftpProperties.getHttpPath()+"/"+filePath+"/"+newFileName)
                    .setType(file3.getContentType()).setExtName(extName).setRefBizType("测绘成果及资料档案管理机构、人员情况（概要）").setRefBizId(Long.valueOf(unitId))
                    .setCreateTime(DateUtil.now()).setId(Long.valueOf(resourceId));
            //数据库添加resource的数据
            int r = resourceMapper.insert(resource);
            if (r > 0){
                //添加成功后上传文件
                result = uploadService.uploadFile(file3, filePath, newFileName);
            }
        }
        if (null != file4){
            //添加资源表
            Resource resource = new Resource();
            //设置资源ID
            String resourceId = FtpFileNameUtil.getFileName();
            //获取今天日期格式化后的数据，用来当做路径
            String filePath = com.aaa.qy108.utils.DateUtil.formatDate(new Date(), "yyyy/MM/dd");
            //获取原始文件的名称
            String oldFilename = file4.getOriginalFilename();
            //截取文件后缀
            String extName = oldFilename.substring(oldFilename.lastIndexOf("."));
            //生成新的文件名称
            String newFileName = resourceId + "" + extName;
            //设置resource对象的值
            resource.setName(file4.getOriginalFilename()).setSize(Long.valueOf(file4.getSize())).setPath(ftpProperties.getHttpPath()+"/"+filePath+"/"+newFileName)
                    .setType(file4.getContentType()).setExtName(extName).setRefBizType("测绘成果及资料档案管理设施说明（概要）").setRefBizId(Long.valueOf(unitId))
                    .setCreateTime(DateUtil.now()).setId(Long.valueOf(resourceId));
            //数据库添加resource的数据
            int r = resourceMapper.insert(resource);
            if (r > 0){
                //添加成功后上传文件
                result = uploadService.uploadFile(file4, filePath, newFileName);
            }
        }
        if (result){
            resultMap.put("code",ADD_DATA_SUCCESS.getCode());
            resultMap.put("msg",ADD_DATA_SUCCESS.getMsg());
            return resultMap;
        }
        resultMap.put("code",ADD_DATA_FAILED.getCode());
        resultMap.put("msg",ADD_DATA_FAILED.getMsg());
        return resultMap;
    }






}




