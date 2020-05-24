package com.aaa.qy108.controller;

import com.aaa.qy108.base.BaseController;
import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.Dict;
import com.aaa.qy108.service.SystemApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static com.aaa.qy108.status.AddStatus.ADD_DATA_SUCCESS;
import static com.aaa.qy108.status.DeleteStatus.DELETE_DATA_SUCCESS;
import static com.aaa.qy108.status.LoginStatus.LOGIN_TIMEOUT_EXIT;
import static com.aaa.qy108.status.SelectStatus.SELECT_DATA_SUCCESS;
import static com.aaa.qy108.status.UpdateStatus.UPDATE_DATA_SUCCESS;

/**
 * @author mi
 * @Company miTeach.China.com
 * @date 2020/5/24 18:15
 * @Description 字典管理的consumer
 */
@RestController
@RequestMapping("dict")
@Api(value = "字典管理",tags = "字典管理接口")
public class DictController extends BaseController {
    @Autowired
    private SystemApiService systemApiService;


    /**
     * @Description: 分页查询字典信息
     * @Param: [hashMap]
     * @Author: mi
     * @Return: com.aaa.qy108.base.ResultData
     * @Date: 2020/5/24 18:25
     **/
    @PostMapping("/selectAllDictByPage")
    @ApiOperation(value = "分页查询字典信息",notes = "字典管理的查询")
    public ResultData selectAllDictByPage(@RequestBody HashMap hashMap) throws Exception {
      return systemApiService.selectAllDictByPage(hashMap);
    }

    /**
     * @Description:
     *      新增字典信息
     * @Param: [dict, tokenId]
     * @Author: mi
     * @Return: com.aaa.qy108.base.ResultData
     * @Date: 2020/5/24 17:53
     **/
    @PostMapping("/addDict")
    @ApiOperation(value = "新增字典信息",notes = "字典管理的新增")
    public ResultData addDict(@RequestBody Dict dict, @RequestParam("tokenId") String tokenId){
        return systemApiService.addDict(dict,tokenId);
    }

    /**
     * @Description:
     *      批量删除字典
     * @Param: [ids, tokenId]
     * @Author: mi
     * @Return: com.aaa.qy108.base.ResultData
     * @Date: 2020/5/24 17:54
     **/
    @DeleteMapping("/delDictsById")
    @ApiOperation(value = "批量删除字典信息",notes = "字典管理的删除")
    public ResultData delDictsById(@RequestBody List<Long> ids, @RequestParam("tokenId") String tokenId){
        return systemApiService.delDictsById(ids,tokenId);
    }

    /**
     * @Description: 修改字典信息
     * @Param: [dict, tokenId]
     * @Author: mi
     * @Return: com.aaa.qy108.base.ResultData
     * @Date: 2020/5/24 18:26
     **/
    @PostMapping("/updateDict")
    @ApiOperation(value = "修改字典信息",notes = "字典管理的修改")
    public ResultData updateDict(@RequestBody Dict dict,@RequestParam("tokenId") String tokenId){
       return systemApiService.updateDict(dict,tokenId);
    }
}
