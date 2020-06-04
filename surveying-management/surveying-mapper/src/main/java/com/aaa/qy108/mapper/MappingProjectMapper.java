package com.aaa.qy108.mapper;

import com.aaa.qy108.model.MappingProject;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @Author Tzg
 * @Description //测绘项目
 * @Date 16:25 2020/5/23
 * @Param  * @param null
 * @return
 **/
public interface MappingProjectMapper extends Mapper<MappingProject> {


     /**
      * @Author Tzg
      * @Description //测绘项目管理，项目名称模糊查询，类型 ，日期精确查
      * @Date 16:06 2020/5/23
      * @Param  * @param null
      * @return
      **/
    public List<HashMap> projectSelect(MappingProject mappingProject);

    /**
     * @Author Tzg
     * @Description //查询所有测绘项目
     * @Date 16:06 2020/5/23
     * @Param  * @param null
     * @return
     **/
    public List<HashMap> SelectAllProject();


    /**
     * @Author Tzg
     * @Description //查询测绘类型和开工时间，分组
     * @Date 16:09 2020/5/23
     * @Param  * @param null
     * @return
     **/
    List<HashMap> SelectGroupName(String name);
    
    /**
    * @Description: 通过id查询测绘工程的详细信息
    * @Param: [id]
    * @return: java.util.List<java.util.HashMap>
    * @Author: Qin
    * @Date: 2020/5/30
    */
    List<HashMap> projectDetail(String id);



    /**
    * @Description: 项目查询
    * @Author: guohang
    * @Date: 2020/6/3 22:58
    * @Param: [userId]
    * @return: java.util.List<com.aaa.qy108.model.MappingProject>
    */
    List<MappingProject> selectAllPros(Long userId);



}