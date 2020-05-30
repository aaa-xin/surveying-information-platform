package com.aaa.qy108.mapper;

import com.aaa.qy108.model.MappingUnit;
import com.aaa.qy108.model.ResultCommit;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @Author guohang
 * @Description 通用mapper
 * @Date 2020-5-22 16:49:57
 */
public interface ResultCommitMapper extends Mapper<ResultCommit> {

    /**
    * @Description: 根据传过来的条件查询测绘成果
    * @Param: [hashMap]
    * @return: java.util.List<java.util.HashMap>
    * @Author: Qin
    * @Date: 2020/5/30
    */
    public List<HashMap> selcetResultByFeild(HashMap hashMap);
    
    /**
    * @Description: 如果没有传过来查询条件，证明是查询所有的测绘成果
    * @Param: []
    * @return: java.util.List<java.util.HashMap>
    * @Author: Qin
    * @Date: 2020/5/30
    */
    public List<HashMap> selectAllResult();

    /**
    * @Description: 查询数据中所有的测绘类型，可以让前台进行选择查询
    * @Param: []
    * @return: java.util.List<java.util.HashMap>
    * @Author: Qin
    * @Date: 2020/5/30
    */
    public List<HashMap> SelectProjectType();
    
    /**
    * @Description: 查询出成果的详细信息
    * @Param: []
    * @return: java.util.HashMap<java.lang.String,java.lang.Object>
    * @Author: Qin
    * @Date: 2020/5/30
    */
    public List<HashMap> resultDetail(String id);
}