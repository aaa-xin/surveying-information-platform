package com.aaa.qy108.controller;

import com.aaa.qy108.base.BaseService;
import com.aaa.qy108.base.CommonController;
import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.Dict;
import com.aaa.qy108.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static com.aaa.qy108.status.AddStatus.ADD_DATA_SUCCESS;
import static com.aaa.qy108.status.DeleteStatus.DELETE_DATA_SUCCESS;
import static com.aaa.qy108.status.SelectStatus.SELECT_DATA_SUCCESS;
import static com.aaa.qy108.status.UpdateStatus.UPDATE_DATA_SUCCESS;

/**
 * @author mi
 * @Company miTeach.China.com
 * @date 2020/5/24 12:57
 * @Description
 *      字典管理的provider
 */
@RestController
@Slf4j
@RequestMapping("/dict")
public class DictController extends CommonController<Dict> {

    @Autowired
    private DictService dictService;

    /**
     * @Description:
     *      分页查询字典信息
     * @Param: [hashMap]
     * @Author: mi
     * @Return: com.aaa.qy108.base.ResultData
     * @Date: 2020/5/24 13:46
     **/
    @PostMapping("/selectAllDictByPage")
    public ResultData selectAllDictByPage(@RequestBody HashMap hashMap) throws Exception {
        System.out.println("-----------------这里是查询方法---------------");
        System.out.println(hashMap);
        HashMap<String,Object> AllDict = dictService.selectAllDictByPage(hashMap);
        if (SELECT_DATA_SUCCESS.getCode().equals(AllDict.get("code"))){
            return super.selectSuccess(AllDict.get("data"));
        }else{
            return super.selectFailed();
        }
    }

    /**
     * @Description:
     *      新增字典信息
     * @Param: [dict]
     * @Author: mi
     * @Return: com.aaa.qy108.base.ResultData
     * @Date: 2020/5/24 17:53
     **/
    @PostMapping("/addDict")
    public ResultData addDict(@RequestBody Dict dict){
        System.out.println("-----------------这里是新增方法---------------");
        HashMap<String, Object> addDictResult = dictService.addDict(dict);
        if (ADD_DATA_SUCCESS.getCode().equals(addDictResult.get("code"))){
            //新增成功
            return super.addSuccess();
        }else {
            return super.addFailed();
        }

    }

    /**
     * @Description:
     *      批量删除字典
     * @Param: [ids]
     * @Author: mi
     * @Return: com.aaa.qy108.base.ResultData
     * @Date: 2020/5/24 17:54
     **/
    @DeleteMapping("/delDictsById")
    public ResultData delDictsById(@RequestBody List<Long> ids)
    {
        System.out.println("-----------------这里是删除方法---------------");
        System.out.println("id"+ids);
        HashMap<String, Object> delDictResult = dictService.delDictsById(ids);
        Object code = delDictResult.get("code");
        if (DELETE_DATA_SUCCESS.getCode().equals(code))
        {
            //删除成功
            return super.deleteSuccess();
        }
        else
        {
            return super.deleteFailed();
        }

    }

    /**
     * @Description:
     *         修改字典信息
     * @Param: [dict]
     * @Author: mi
     * @Return: com.aaa.qy108.base.ResultData
     * @Date: 2020/5/24 17:56
     **/
    @PostMapping("/updateDict")
    public ResultData updateDict(@RequestBody Dict dict){
        System.out.println("-----------------这里是修改方法---------------");
        HashMap<String, Object> updateDictResult = dictService.updateDict(dict);
        Object code = updateDictResult.get("code");
        if (UPDATE_DATA_SUCCESS.getCode().equals(code)){
            //修改成功
            return super.updateSuccess();
        }else{
            return super.updateFailed();
        }
    }


    @Override
    public BaseService<Dict> getBaseService() {
        return null;
    }
}
