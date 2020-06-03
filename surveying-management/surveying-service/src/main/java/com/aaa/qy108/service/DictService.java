package com.aaa.qy108.service;

import com.aaa.qy108.base.BaseService;
import com.aaa.qy108.mapper.DictMapper;
import com.aaa.qy108.model.Dict;
import com.aaa.qy108.redis.RedisService;
import com.aaa.qy108.utils.BaseUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.HashMap;
import java.util.List;

import static com.aaa.qy108.status.AddStatus.ADD_DATA_FAILED;
import static com.aaa.qy108.status.AddStatus.ADD_DATA_SUCCESS;
import static com.aaa.qy108.status.DeleteStatus.DELETE_DATA_FAILED;
import static com.aaa.qy108.status.DeleteStatus.DELETE_DATA_SUCCESS;
import static com.aaa.qy108.status.LoginStatus.LOGIN_TIMEOUT_EXIT;
import static com.aaa.qy108.status.SelectStatus.SELECT_DATA_FAILED;
import static com.aaa.qy108.status.SelectStatus.SELECT_DATA_SUCCESS;
import static com.aaa.qy108.status.UpdateStatus.*;
import static com.aaa.qy108.utils.BaseUtil.checkIsNotNull;

/**
 * @author mi
 * @Company miTeach.China.com
 * @date 2020/5/23 21:58
 * @Description
 *
 */
@Service
public class DictService extends BaseService<Dict> {

    @Autowired
    private DictMapper dictMapper;


    /**
     * @Description:
     *      分页查询字典信息
     * @Param: [hashMap]
     * @Author: mi
     * @Return: java.util.HashMap<java.lang.String,java.lang.Object>
     * @Date: 2020/5/24 13:35
     **/
    public HashMap<String,Object> selectAllDictByPage(HashMap hashMap) throws Exception {
        HashMap<String, Object> resultMap = new HashMap<>();
        Dict dict = new Dict();
        PageInfo<Dict> dictPageInfo = super.queryListByPage(dict, BaseUtil.transToInt(hashMap.get("pageNo")), BaseUtil.transToInt(hashMap.get("pageSize")));
        if (null != dictPageInfo && dictPageInfo.getSize() > 0) {
            resultMap.put("code", SELECT_DATA_SUCCESS.getCode());
            resultMap.put("msg", SELECT_DATA_SUCCESS.getMsg());
            resultMap.put("data", dictPageInfo);
        } else {
            resultMap.put("code", SELECT_DATA_FAILED.getCode());
            resultMap.put("msg", SELECT_DATA_FAILED.getMsg());
        }
        return resultMap;
    }



    /**
     * @Description:
     *         通过id批量删除字典
     * @Param: [ids]
     * @Author: mi
     * @Return: java.util.HashMap<java.lang.String,java.lang.Object>
     * @Date: 2020/5/24 12:37
     **/
    public HashMap<String,Object> delDictsById( List<Long> ids){
        HashMap<String, Object> resultMap = new HashMap<>();
        //获取参数类型，添加一个where条件
        Example example = Example.builder(Dict.class).where(Sqls.custom().andIn("id",ids)).build();
        int i = dictMapper.deleteByExample(example);
        if (i > 0){
            resultMap.put("code", DELETE_DATA_SUCCESS.getCode());
            resultMap.put("msg", DELETE_DATA_SUCCESS.getMsg());
        }
        else{
            resultMap.put("code", DELETE_DATA_FAILED.getCode());
            resultMap.put("msg", DELETE_DATA_FAILED.getMsg());
        }
        return resultMap;
    }

    /**
     * @Description:
     *      修改字典信息
     * @Param: [dict]
     * @Author: mi
     * @Return: java.util.HashMap<java.lang.String,java.lang.Object>
     * @Date: 2020/5/24 14:21
     **/
    public HashMap<String,Object> updateDict(Dict dict){
        HashMap<String, Object> resultMap = new HashMap<>();
        if (null != dict){
            int dictUpdateResult = dictMapper.updateByPrimaryKey(dict);
            if (dictUpdateResult > 0){
                resultMap.put("code", UPDATE_DATA_SUCCESS.getCode());
                resultMap.put("msg", UPDATE_DATA_SUCCESS.getMsg());
            }else{
                resultMap.put("code", UPDATE_DATA_FAILED.getCode());
                resultMap.put("msg", UPDATE_DATA_FAILED.getMsg());
            }
        }else {
            resultMap.put("code", UPDATE_DATA_NULL.getCode());
            resultMap.put("msg", UPDATE_DATA_NULL.getMsg());
        }
        return resultMap;
    }

    /**
     * @Description:
     *        新增字典信息
     * @Param: [dict]
     * @Author: mi
     * @Return: java.util.HashMap<java.lang.String,java.lang.Object>
     * @Date: 2020/5/24 14:31
     **/
    public HashMap<String,Object> addDict(Dict dict){
        HashMap<String, Object> resultMap = new HashMap<>();
        int addDictResult = dictMapper.insertSelective(dict);
        if (addDictResult > 0){
            resultMap.put("code", ADD_DATA_SUCCESS.getCode());
            resultMap.put("msg", ADD_DATA_SUCCESS.getMsg());
        }else{
            resultMap.put("code", ADD_DATA_FAILED.getCode());
            resultMap.put("msg", ADD_DATA_FAILED.getMsg());
        }
        return resultMap;
    }

}
