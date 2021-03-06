package com.aaa.qy108.service;

import cn.hutool.core.date.DateUtil;
import com.aaa.qy108.utils.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.aaa.qy108.base.BaseService;
import com.aaa.qy108.mapper.UserMapper;
import com.aaa.qy108.model.User;
import com.aaa.qy108.redis.RedisService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.qy108.status.AddStatus.*;
import static com.aaa.qy108.status.DeleteStatus.*;
import static com.aaa.qy108.status.LoginStatus.*;
import static com.aaa.qy108.status.SelectStatus.SELECT_DATA_FAILED;
import static com.aaa.qy108.status.SelectStatus.SELECT_DATA_SUCCESS;
import static com.aaa.qy108.status.UpdateStatus.*;

/**
 * @Author guohang
 * @Description
 * @Date 2020/5/20 13:53
 */
@Service
@Slf4j
public class UserService extends BaseService<User> {

    @Autowired
    private UserMapper userMapper;


    /**
    * @Description: 新增用户
    * @Author: guohang
    * @Date: 2020/5/20 15:42
    * @Param: [user]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    */
    public Map<String,Object> addUser(User user){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //可以查到redis里的token，然后可以进行新增用户
        user.setCreateTime(DateUtil.now());
        int addResult = userMapper.insert(user);
        if (addResult > 0){
            resultMap.put("code", ADD_DATA_SUCCESS.getCode());
            resultMap.put("msg", ADD_DATA_SUCCESS.getMsg());
        }else{
            resultMap.put("code", ADD_DATA_FAILED.getCode());
            resultMap.put("msg", ADD_DATA_FAILED.getMsg());
        }
        return resultMap;
    }


    /**
    * @Description: 批量删除用户
    * @Author: guohang
    * @Date: 2020/5/20 20:50
    * @Param: [ids]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    */
    public Map<String,Object> delUser(List<Long> ids) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //获取到参数类型，然后添加一个where条件，是in类型，id属于ids中的
        Example example = Example.builder(User.class).where(Sqls.custom().andIn("id",ids)).build();
        int i = userMapper.deleteByExample(example);
        if (i > 0){
            resultMap.put("code", DELETE_DATA_SUCCESS.getCode());
            resultMap.put("msg", DELETE_DATA_SUCCESS.getMsg());
        }else{
            resultMap.put("code", DELETE_DATA_FAILED.getCode());
            resultMap.put("msg", DELETE_DATA_FAILED.getMsg());
        }
        return resultMap;
    }


    /**
    * @Description: 修改员工信息
    * @Author: guohang
    * @Date: 2020/5/21 16:00
    * @Param: [user]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    */
    public Map<String,Object> updateUser(User user){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        user.setModifyTime(DateUtil.now());
        int i = userMapper.updateByPrimaryKeySelective(user);
        if (i > 0){
            resultMap.put("code", UPDATE_DATA_SUCCESS.getCode());
            resultMap.put("msg", UPDATE_DATA_SUCCESS.getMsg());
        }else{
            resultMap.put("code", UPDATE_DATA_FAILED.getCode());
            resultMap.put("msg", UPDATE_DATA_FAILED.getMsg());
        }
        return resultMap;
    }


    /**
    * @Description: 查询全部用户信息
    * @Author: guohang
    * @Date: 2020/5/21 19:29
    * @Param: []
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    */
    public Map<String,Object> selectAll() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<User> users = userMapper.selectAll();
        if (null != users && !users.isEmpty()){
            resultMap.put("code",SELECT_DATA_SUCCESS.getCode());
            resultMap.put("msg",SELECT_DATA_SUCCESS.getMsg());
            resultMap.put("data",users);
            return resultMap;
        }else{
            resultMap.put("code",SELECT_DATA_FAILED.getCode());
            resultMap.put("msg",SELECT_DATA_FAILED.getMsg());
        }
        return resultMap;
    }

    /**
     * @Author Cy
     * @Description 分页查询全部用户
     * @Param [t, pageNo, pageSize]
     * @Data 2020/5/20
     * @return com.github.pagehelper.PageInfo<T>
     * @throws
     */
    public Map<String,Object> selectUserAll(HashMap map, RedisService redisService){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String tokenVal = redisService.get(map.get("tokenId").toString());
        //检测token
        if (null == tokenVal){
            resultMap.put("code",LOGIN_TIMEOUT_EXIT.getCode());
            resultMap.put("msg",LOGIN_TIMEOUT_EXIT.getMsg());
            return resultMap;
        }
        if(map.size()>0){
            //调用BaseService分页条件查询
            PageInfo<HashMap> pageInfo = super.selectUserPageInfo(map);
            if (null != pageInfo && pageInfo.getSize() > 0 ){
                resultMap.put("code", SELECT_DATA_SUCCESS.getCode());
                resultMap.put("msg", pageInfo);
                return resultMap;
            }else{
                resultMap.put("code", SELECT_DATA_FAILED.getCode());
                resultMap.put("msg", SELECT_DATA_FAILED.getMsg());
            }
        }
        return resultMap;
    }

}



