package com.aaa.qy108.service;

import com.aaa.qy108.mapper.ResultCommitMapper;
import com.aaa.qy108.model.MappingUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.qy108.status.SelectStatus.*;

/**
 * @Author Qin
 * @Date 2020/5/30 10:10
 * @Description
 **/
@Service
public class MappingResultService {

     @Autowired
    private ResultCommitMapper resultCommitMapper;
    
     /**
     * @Description: 根据传过 来的条件去查询需要测绘成果
     * @Param: [hashMap]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: Qin
     * @Date: 2020/5/30
     */
     public Map<String, Object> selcetAllResult(HashMap hashMap){

         HashMap<String, Object> resultMap = new HashMap<>();
         List<HashMap> restdata = new ArrayList<>();
         System.out.println(hashMap.size());
         if (hashMap.size()>0){
             restdata= resultCommitMapper.selcetResultByFeild(hashMap);
         }else {
             restdata = resultCommitMapper.selectAllResult();
         }
         if (restdata.size()>0){
             resultMap.put("code",SELECT_DATA_SUCCESS.getCode());
             resultMap.put("msg",SELECT_DATA_SUCCESS.getMsg());
             resultMap.put("data",restdata);
         }else {
             resultMap.put("code",SELECT_DATA_FAILED.getCode());
             resultMap.put("msg",SELECT_DATA_FAILED.getMsg());
         }
         return resultMap;
     }

    /**
    * @Description: 查询数据中所有的测绘类型，可以让前台进行选择查询
    * @Param: []
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: Qin
    * @Date: 2020/5/30
    */
    public Map<String, Object> SelectProjectType(){
        HashMap<String, Object> resultMap = new HashMap<>();
        List<HashMap> restdata= resultCommitMapper.SelectProjectType();
        if (restdata.size()>0){
            resultMap.put("code",SELECT_DATA_SUCCESS.getCode());
            resultMap.put("msg",SELECT_DATA_SUCCESS.getMsg());
            resultMap.put("data",restdata);
        }else {
            resultMap.put("code",SELECT_DATA_FAILED.getCode());
            resultMap.put("msg",SELECT_DATA_FAILED.getMsg());
        }
        return resultMap;
    }



    /**
    * @Description: 查询出成果的详细信息
    * @Param: [mappingUnit]
    * @return: java.util.HashMap<java.lang.String,java.lang.Object>
    * @Author: Qin
    * @Date: 2020/5/30
    */
    public HashMap<String,Object> resultDetail(String id) {
        HashMap<String, Object> resultMap = new HashMap<>();
        if (null != id && !("").equals(id)) {
            List<HashMap> restdata = resultCommitMapper.resultDetail(id);
            if ( restdata.size() ==1) {
                resultMap.put("code", SELECT_DATA_BY_ID_SUCCESS.getCode());
                resultMap.put("msg", SELECT_DATA_BY_ID_SUCCESS.getMsg());
                resultMap.put("data", restdata);
                return resultMap;
            }
        }
        resultMap.put("code", SELECT_DATA_BY_ID_FAILED.getCode());
        resultMap.put("msg", SELECT_DATA_BY_ID_FAILED.getMsg());
        return resultMap;
    }
}


