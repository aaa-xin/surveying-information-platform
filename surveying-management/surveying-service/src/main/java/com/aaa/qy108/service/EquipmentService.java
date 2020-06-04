package com.aaa.qy108.service;

import com.aaa.qy108.base.BaseService;
import com.aaa.qy108.mapper.EquipmentMapper;
import com.aaa.qy108.model.Equipment;
import com.aaa.qy108.utils.BaseUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

import static com.aaa.qy108.status.AddStatus.ADD_DATA_FAILED;
import static com.aaa.qy108.status.AddStatus.ADD_DATA_SUCCESS;
import static com.aaa.qy108.status.DeleteStatus.DELETE_DATA_FAILED;
import static com.aaa.qy108.status.DeleteStatus.DELETE_DATA_SUCCESS;
import static com.aaa.qy108.status.SelectStatus.*;
import static com.aaa.qy108.status.SelectStatus.SELECT_DATA_BY_ID_SUCCESS;
import static com.aaa.qy108.status.UpdateStatus.UPDATE_DATA_FAILED;
import static com.aaa.qy108.status.UpdateStatus.UPDATE_DATA_SUCCESS;

/**
 * @Author Tzg
 * @Date 2020/6/2 22:14
 * @Description
 **/
@Service
public class EquipmentService extends BaseService<Equipment> {

    @Autowired
    private EquipmentMapper equipmentMapper;

    /**
    * @Description: 查询设备的所有信息
    * @Param: [map]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: Tzg
    * @Date: 2020/6/4
    */
    public Map<String, Object> selectEquipment(HashMap map) throws Exception {
        HashMap<String, Object> resultMap = new HashMap<>();
        Equipment equipment = new Equipment();
        PageInfo<Equipment> equipmentPageInfo = super.queryListByPage(equipment, BaseUtil.transToInt(map.get("pageNo")), BaseUtil.transToInt(map.get("pageSize")));
        System.out.println(equipmentPageInfo.getSize());
        if (null != equipmentPageInfo && equipmentPageInfo.getSize()>0){
            resultMap.put("code",SELECT_DATA_SUCCESS.getCode());
            resultMap.put("msg",SELECT_DATA_SUCCESS.getMsg());
            resultMap.put("data",equipmentPageInfo);
            return resultMap;
        }
        resultMap.put("code",SELECT_DATA_FAILED.getCode());
        resultMap.put("msg",SELECT_DATA_FAILED.getMsg());
        resultMap.put("data",equipmentPageInfo);
        return resultMap;
    }

    /**
    * @Description: 根据id查询详细信息
    * @Param: [map]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: Tzg
    * @Date: 2020/6/4
    */
    public Map<String, Object> selectOne(HashMap map) throws Exception{
        Equipment equipment = equipmentMapper.selectByPrimaryKey(map.get("id"));
        HashMap<String, Object> resultMap = new HashMap<>();
        if (equipment!=null){
            resultMap.put("code", SELECT_DATA_BY_ID_SUCCESS.getCode());
            resultMap.put("msg", SELECT_DATA_BY_ID_SUCCESS.getMsg());
            resultMap.put("data", equipment);
            return resultMap;
        }
        resultMap.put("code", SELECT_DATA_BY_ID_FAILED.getCode());
        resultMap.put("msg", SELECT_DATA_BY_ID_FAILED.getMsg());
        return resultMap;
    }

    /**
    * @Description: 通过id删除设备信息
    * @Param: [id]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: Tzg
    * @Date: 2020/6/4
    */
    public Map<String, Object> deleteById(long id){
        int i = equipmentMapper.deleteByPrimaryKey(id);
        HashMap<String, Object> resultMap = new HashMap<>();
        if (i>0){
            resultMap.put("code", DELETE_DATA_SUCCESS.getCode());
            resultMap.put("msg", DELETE_DATA_SUCCESS.getMsg());
            resultMap.put("data", i);
            return resultMap;
        }
        resultMap.put("code", DELETE_DATA_FAILED.getCode());
        resultMap.put("msg", DELETE_DATA_FAILED.getMsg());
        return resultMap;
    }

    /**
    * @Description: 根据id删除更新设备信息
    * @Param: [equipment]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: Tzg
    * @Date: 2020/6/4
    */
    public Map<String, Object> updateById(@RequestBody Equipment equipment){
        int i = equipmentMapper.updateByPrimaryKey(equipment);
        HashMap<String, Object> resultMap = new HashMap<>();
        if (i>0){
            resultMap.put("code", UPDATE_DATA_SUCCESS.getCode());
            resultMap.put("msg", UPDATE_DATA_SUCCESS.getMsg());
            resultMap.put("data", i);
            return resultMap;
        }
        resultMap.put("code", UPDATE_DATA_FAILED.getCode());
        resultMap.put("msg", UPDATE_DATA_FAILED.getMsg());
        return resultMap;
    }


    /**
    * @Description: 新增设备信息
    * @Param: [equipment]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: Tzg
    * @Date: 2020/6/4
    */
    public Map<String, Object> addEquipmentInfo(@RequestBody Equipment equipment){
        int i = equipmentMapper.insert(equipment);
        HashMap<String, Object> resultMap = new HashMap<>();
        if (i>0){
            resultMap.put("code", ADD_DATA_SUCCESS.getCode());
            resultMap.put("msg", ADD_DATA_SUCCESS.getMsg());
            resultMap.put("data", i);
            return resultMap;
        }
        resultMap.put("code", ADD_DATA_FAILED.getCode());
        resultMap.put("msg", ADD_DATA_FAILED.getMsg());
        return resultMap;
    }
}
