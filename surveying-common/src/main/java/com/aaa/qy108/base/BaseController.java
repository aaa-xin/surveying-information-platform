package com.aaa.qy108.base;

import static com.aaa.qy108.status.AddStatus.*;
import static com.aaa.qy108.status.DeleteStatus.*;
import static com.aaa.qy108.status.LoginStatus.*;
import static com.aaa.qy108.status.SelectStatus.*;
import static com.aaa.qy108.status.UpdateStatus.*;
import static com.aaa.qy108.status.FileStatus.*;

/**
 * @Author guohang
 * @Description 实现consumer和provider的controller统一返回值
 * @Date 2020/5/12 22:05
 */
public class BaseController {

    /**
    * @Description: 上传成功
    * @Author: guohang
    * @Date: 2020/5/29 16:45
    * @Param: []
    * @return: com.aaa.qy108.base.ResultData
    */
    protected ResultData uploadSuccess(){
        ResultData resultData = new ResultData();
        resultData.setCode(UPLOAD_SUCCESS.getCode());
        resultData.setMsg(UPLOAD_SUCCESS.getMsg());
        return resultData;
    }

    /**
    * @Description: 上传成功，自定义返回值
    * @Author: guohang
    * @Date: 2020/5/29 16:49
    * @Param: [msg]
    * @return: com.aaa.qy108.base.ResultData
    */
    protected ResultData uploadSuccess(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(UPLOAD_SUCCESS.getCode());
        resultData.setMsg(msg);
        return resultData;
    }


    /**
    * @Description: 上传失败
    * @Author: guohang
    * @Date: 2020/5/29 16:49
    * @Param: []
    * @return: com.aaa.qy108.base.ResultData
    */
    protected ResultData uploadFailed(){
        ResultData resultData = new ResultData();
        resultData.setCode(UPLOAD_FAILED.getCode());
        resultData.setMsg(UPLOAD_FAILED.getMsg());
        return resultData;
    }

    /**
    * @Description: 上传失败，自定义返回值
    * @Author: guohang
    * @Date: 2020/5/29 16:49
    * @Param: [msg]
    * @return: com.aaa.qy108.base.ResultData
    */
    protected ResultData uploadFailed(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(UPLOAD_FAILED.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

    /** 
    * @Description: 登录成功，使用系统消息
    * @Author: guohang
    * @Date: 2020/5/12 22:28
    * @Param: [] 
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    protected ResultData loginSuccess(){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(LOGIN_SUCCESS.getMsg());
        return resultData;
    }

    /**
    * @Description: 登录成功，使用自定义消息
    * @Author: guohang
    * @Date: 2020/5/12 22:28
    * @Param: [msg]
    * @return: com.aaa.qy108.base.ResultData
    */
    protected ResultData loginSuccess(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

    /** 
    * @Description: 登录成功，系统消息，使用自定义返回值
    * @Author: guohang
    * @Date: 2020/5/12 22:30
    * @Param: [data] 
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    protected ResultData loginSuccess(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(LOGIN_SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }

    /**
    * @Description: 登录成功，自定义消息，自定义返回值
    * @Author: guohang
    * @Date: 2020/5/12 22:31
    * @Param: [msg, data]
    * @return: com.aaa.qy108.base.ResultData
    */
    protected ResultData loginSuccess(String msg, Object data) {
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }


    /**
    * @Description: 超时退出
    * @Author: guohang
    * @Date: 2020/5/20 16:00
    * @Param: []
    * @return: com.aaa.qy108.base.ResultData
    */
    protected ResultData loginTimeoutExit() {
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_TIMEOUT_EXIT.getCode());
        resultData.setMsg(LOGIN_TIMEOUT_EXIT.getMsg());
        return resultData;
    }


    /**
    * @Description: 超时退出，自定义消息
    * @Author: guohang
    * @Date: 2020/5/20 15:59
    * @Param: [msg]
    * @return: com.aaa.qy108.base.ResultData
    */
    protected ResultData loginTimeoutExit(String msg) {
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_TIMEOUT_EXIT.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

    /** 
    * @Description: 登录失败，使用系统消息
    * @Author: guohang
    * @Date: 2020/5/12 22:32
    * @Param: [] 
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    protected ResultData loginFailed() {
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMsg(LOGIN_FAILED.getMsg());
        return resultData;
    }

    /**
    * @Description: 添加数据成功，返回系统消息
    * @Author: guohang
    * @Date: 2020/5/12 23:18
    * @Param: []
    * @return: com.aaa.qy108.base.ResultData
    */
    protected ResultData addSuccess(){
        ResultData resultData = new ResultData();
        resultData.setCode(ADD_DATA_SUCCESS.getCode());
        resultData.setMsg(ADD_DATA_SUCCESS.getMsg());
        return resultData;
    }

    /**
    * @Description: 添加数据成功，返回自定义消息
    * @Author: guohang
    * @Date: 2020/5/12 23:19
    * @Param: [msg]
    * @return: com.aaa.qy108.base.ResultData
    */
    protected ResultData addSuccess(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(ADD_DATA_SUCCESS.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

    /**
    * @Description: 添加数据失败，返回系统消息
    * @Author: guohang
    * @Date: 2020/5/12 23:21
    * @Param: []
    * @return: com.aaa.qy108.base.ResultData
    */
    protected ResultData addFailed(){
        ResultData resultData = new ResultData();
        resultData.setCode(ADD_DATA_FAILED.getCode());
        resultData.setMsg(ADD_DATA_FAILED.getMsg());
        return resultData;
    }

    /**
    * @Description: 添加数据失败，返回自定义消息
    * @Author: guohang
    * @Date: 2020/5/12 23:20
    * @Param: [msg]
    * @return: com.aaa.qy108.base.ResultData
    */
    protected ResultData addFailed(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(ADD_DATA_FAILED.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

    /**
    * @Description: 添加的数据已经存在，添加失败，返回系统消息
    * @Author: guohang
    * @Date: 2020/5/12 23:21
    * @Param: []
    * @return: com.aaa.qy108.base.ResultData
    */
    protected ResultData addDataEexist(){
        ResultData resultData = new ResultData();
        resultData.setCode(ADD_DATA_EXIST.getCode());
        resultData.setMsg(ADD_DATA_EXIST.getMsg());
        return resultData;
    }

    /**
    * @Description: 添加的数据已经存在，添加失败，返回自定义消息
    * @Author: guohang
    * @Date: 2020/5/12 23:22
    * @Param: [msg]
    * @return: com.aaa.qy108.base.ResultData
    */
    protected ResultData addDataEexist(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(ADD_DATA_EXIST.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

    /**
    * @Description: 删除数据成功，返回系统消息
    * @Author: guohang
    * @Date: 2020/5/12 23:23
    * @Param: []
    * @return: com.aaa.qy108.base.ResultData
    */
    protected ResultData deleteSuccess(){
        ResultData resultData = new ResultData();
        resultData.setCode(DELETE_DATA_SUCCESS.getCode());
        resultData.setMsg(DELETE_DATA_SUCCESS.getMsg());
        return resultData;
    }


    /**
    * @Description: 删除数据成功，返回自定义消息
    * @Author: guohang
    * @Date: 2020/5/12 23:24
    * @Param: [msg]
    * @return: com.aaa.qy108.base.ResultData
    */
    protected ResultData deleteSuccess(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(DELETE_DATA_SUCCESS.getCode());
        resultData.setMsg(msg);
        return resultData;
    }
    
    /** 
    * @Description: 删除数据失败 
    * @Author: guohang
    * @Date: 2020/5/12 23:25
    * @Param: [] 
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    protected ResultData deleteFailed(){
        ResultData resultData = new ResultData();
        resultData.setCode(DELETE_DATA_FAILED.getCode());
        resultData.setMsg(DELETE_DATA_FAILED.getMsg());
        return resultData;
    }
    
    /** 
    * @Description: 删除数据失败，返回自定义数据 
    * @Author: guohang
    * @Date: 2020/5/12 23:25
    * @Param: [msg] 
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    protected ResultData deleteFailed(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(DELETE_DATA_FAILED.getCode());
        resultData.setMsg(msg);
        return resultData;
    }


    /** 
    * @Description: 删除的数据不存在，删除失败 
    * @Author: guohang
    * @Date: 2020/5/12 23:26
    * @Param: []
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    protected ResultData deleteDataNotExist(){
        ResultData resultData = new ResultData();
        resultData.setCode(DELETE_DATA_NOT_EXIST.getCode());
        resultData.setMsg(DELETE_DATA_NOT_EXIST.getMsg());
        return resultData;
    }

    /** 
    * @Description: 出现未知错误，请稍后再试！ 
    * @Author: guohang
    * @Date: 2020/5/12 23:27
    * @Param: [] 
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    protected ResultData deleteDataError(){
        ResultData resultData = new ResultData();
        resultData.setCode(DELETE_DATA_ERROR.getCode());
        resultData.setMsg(DELETE_DATA_ERROR.getMsg());
        return resultData;
    }

    /** 
    * @Description: 修改数据成功 
    * @Author: guohang
    * @Date: 2020/5/12 23:27
    * @Param: [] 
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    protected ResultData updateSuccess(){
        ResultData resultData = new ResultData();
        resultData.setCode(UPDATE_DATA_SUCCESS.getCode());
        resultData.setMsg(UPDATE_DATA_SUCCESS.getMsg());
        return resultData;
    }


    /** 
    * @Description: 修改数据成功，返回自定义消息 
    * @Author: guohang
    * @Date: 2020/5/12 23:28
    * @Param: [msg] 
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    protected ResultData updateSuccess(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(UPDATE_DATA_SUCCESS.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

    /** 
    * @Description: 修改数据失败 
    * @Author: guohang
    * @Date: 2020/5/12 23:28
    * @Param: [] 
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    protected ResultData updateFailed(){
        ResultData resultData = new ResultData();
        resultData.setCode(UPDATE_DATA_FAILED.getCode());
        resultData.setMsg(UPDATE_DATA_FAILED.getMsg());
        return resultData;
    }


    /** 
    * @Description: 修改数据失败，返回自定义消息 
    * @Author: guohang
    * @Date: 2020/5/12 23:29
    * @Param: [msg] 
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    protected ResultData updateFailed(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(UPDATE_DATA_FAILED.getCode());
        resultData.setMsg(msg);
        return resultData;
    }


    /** 
    * @Description: 要修改的数据已存在 
    * @Author: guohang
    * @Date: 2020/5/12 23:30
    * @Param: [] 
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    protected ResultData updateDataExist(){
        ResultData resultData = new ResultData();
        resultData.setCode(UPDATE_DATA_EXIST.getCode());
        resultData.setMsg(UPDATE_DATA_EXIST.getMsg());
        return resultData;
    }

    /** 
    * @Description: 查询数据成功
    * @Author: guohang
    * @Date: 2020/5/12 23:31
    * @Param: [obj] 
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    protected ResultData selectSuccess(Object obj){
        ResultData resultData = new ResultData();
        resultData.setCode(SELECT_DATA_SUCCESS.getCode());
        resultData.setMsg(SELECT_DATA_SUCCESS.getMsg());
        resultData.setData(obj);
        return resultData;
    }

    
    /** 
    * @Description: 查询数据成功，返回自定义消息 
    * @Author: guohang
    * @Date: 2020/5/12 23:32
    * @Param: [obj, msg] 
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    protected ResultData selectSuccess(Object obj,String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(SELECT_DATA_SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setData(obj);
        return resultData;
    }

    /** 
    * @Description: 查询数据失败 
    * @Author: guohang
    * @Date: 2020/5/12 23:32
    * @Param: [] 
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    protected ResultData selectFailed(){
        ResultData resultData = new ResultData();
        resultData.setCode(SELECT_DATA_FAILED.getCode());
        resultData.setMsg(SELECT_DATA_FAILED.getMsg());
        return resultData;
    }


    /** 
    * @Description: 查询数据失败，返回自定义消息 
    * @Author: guohang
    * @Date: 2020/5/12 23:33
    * @Param: [msg] 
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    protected ResultData selectFailed(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(SELECT_DATA_FAILED.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

    /** 
    * @Description: 查询的数据不存在 
    * @Author: guohang
    * @Date: 2020/5/12 23:34
    * @Param: [] 
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    protected ResultData selectDataNotExist(){
        ResultData resultData = new ResultData();
        resultData.setCode(SELECT_DATA_NOT_EXIST.getCode());
        resultData.setMsg(SELECT_DATA_NOT_EXIST.getMsg());
        return resultData;
    }
    
    /** 
    * @Description: 查询的数据不存在，返回自定义消息 
    * @Author: guohang
    * @Date: 2020/5/12 23:34
    * @Param: [msg] 
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    protected ResultData selectDataNotExist(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(SELECT_DATA_NOT_EXIST.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

    /** 
    * @Description: 根据ID查询数据成功 
    * @Author: guohang
    * @Date: 2020/5/12 23:35
    * @Param: [obj] 
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    protected ResultData selectByIdSuccess(Object obj){
        ResultData resultData = new ResultData();
        resultData.setCode(SELECT_DATA_BY_ID_SUCCESS.getCode());
        resultData.setMsg(SELECT_DATA_BY_ID_SUCCESS.getMsg());
        resultData.setData(obj);
        return resultData;
    }
    
    /** 
    * @Description: 根据ID查询数据成功，返回自定义消息 
    * @Author: guohang
    * @Date: 2020/5/12 23:36
    * @Param: [obj,msg] 
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    protected ResultData selectByIdSuccess(String msg,Object obj){
        ResultData resultData = new ResultData();
        resultData.setCode(SELECT_DATA_BY_ID_SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setData(obj);
        return resultData;
    }


    /** 
    * @Description: 根据ID查询数据失败
    * @Author: guohang
    * @Date: 2020/5/12 23:37
    * @Param: [] 
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    protected ResultData selectByIdFailed(){
        ResultData resultData = new ResultData();
        resultData.setCode(SELECT_DATA_BY_ID_FAILED.getCode());
        resultData.setMsg(SELECT_DATA_BY_ID_FAILED.getMsg());
        return resultData;
    }

    /** 
    * @Description: 根据ID查询数据失败，返回自定义消息
    * @Author: guohang
    * @Date: 2020/5/12 23:38
    * @Param: [msg] 
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    protected ResultData selectByIdFailed(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(SELECT_DATA_BY_ID_FAILED.getCode());
        resultData.setMsg(msg);
        return resultData;
    }


}



