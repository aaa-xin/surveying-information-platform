package com.aaa.qy108.controller;

import com.aaa.qy108.base.BaseService;
import com.aaa.qy108.base.CommonController;
import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.Equipment;
import com.aaa.qy108.service.EquipmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.aaa.qy108.status.AddStatus.ADD_DATA_SUCCESS;
import static com.aaa.qy108.status.DeleteStatus.DELETE_DATA_SUCCESS;
import static com.aaa.qy108.status.LoginStatus.LOGIN_TIMEOUT_EXIT;
import static com.aaa.qy108.status.SelectStatus.SELECT_DATA_BY_ID_SUCCESS;
import static com.aaa.qy108.status.SelectStatus.SELECT_DATA_SUCCESS;
import static com.aaa.qy108.status.UpdateStatus.UPDATE_DATA_SUCCESS;

/**
 * @Author Tzg
 * @Date 2020/6/3 21:52
 * @Description
 **/

@RestController
public class equipmentController extends CommonController<Equipment> {

@Autowired
private EquipmentService equipmentService;

/**
* @Description: 直接使用通用controller查询所有的仪器设备信息
* @Param: [map]
* @return: com.aaa.qy108.base.ResultData
* @Author: Tzg
* @Date: 2020/6/4
*/
@PostMapping("/selectEquipment")
public ResultData selectEquipment(@RequestBody HashMap map) throws Exception {
    Map<String, Object> resultMap = equipmentService.selectEquipment(map);
    if(SELECT_DATA_SUCCESS.getCode().equals(resultMap.get("code"))){
            return super.selectSuccess(resultMap.get("data"));
        }else{
            return super.selectFailed();
        }
  // return super.selectAllByPage(map);

}
    /**
    * @Description: 通过id查询详细信息
    * @Param: [map]
    * @return: com.aaa.qy108.base.ResultData
    * @Author: Tzg
    * @Date: 2020/6/4
    */
    @PostMapping("/selectOneById")
    public ResultData selectOne(@RequestBody HashMap map) throws Exception {
        Map<String, Object> resultMap = equipmentService.selectOne(map);
        if (SELECT_DATA_BY_ID_SUCCESS.getCode().equals(resultMap.get("code"))){
            return super.selectSuccess(resultMap.get("data"));
        }else{
            return super.selectFailed();
        }
    }

    /**
    * @Description: 根据id删除设备信息
    * @Param: [id]
    * @return: com.aaa.qy108.base.ResultData
    * @Author: Tzg
    * @Date: 2020/6/4
    */
    @PostMapping("/deleteById")
    public ResultData deleteById(@RequestParam("id") long id){
        Map<String, Object> resultMap = equipmentService.deleteById(id);
        if (DELETE_DATA_SUCCESS.getCode().equals(resultMap.get("code"))){
            return super.deleteSuccess();
        }else{
            return super.deleteFailed();
        }
    }


    /**
    * @Description: 根据id更新信息
    * @Param: [equipment]
    * @return: com.aaa.qy108.base.ResultData
    * @Author: Tzg
    * @Date: 2020/6/4
    */
    @PostMapping("/updateById")
    public ResultData updateById(@RequestBody Equipment equipment) {
        Map<String, Object> resultMap = equipmentService.updateById(equipment);
        if (UPDATE_DATA_SUCCESS.getCode().equals(resultMap.get("code"))) {
            return super.updateSuccess();
        } else {
            return super.updateFailed();
        }

    }



    /**
    * @Description: 根据id添加信息
    * @Param: [equipment]
    * @return: com.aaa.qy108.base.ResultData
    * @Author: Tzg
    * @Date: 2020/6/4
    */
    @PostMapping("/addEquipmentInfo")
    public ResultData addEquipmentInfo(@RequestBody Equipment equipment){
        Map<String, Object> resultMap = equipmentService.addEquipmentInfo(equipment);
        if (ADD_DATA_SUCCESS.getCode().equals(resultMap.get("code"))) {
            return super.addSuccess();
        } else {
            return super.addFailed();
        }
    }




        @Override
    public BaseService<Equipment> getBaseService() {
        return equipmentService;
    }
}
