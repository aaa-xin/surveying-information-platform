package com.aaa.qy108.service;

import cn.hutool.core.date.DateUtil;
import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.mapper.DeptMapper;
import com.aaa.qy108.model.Dept;
import com.aaa.qy108.model.User;
import com.aaa.qy108.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.qy108.status.AddStatus.ADD_DATA_FAILED;
import static com.aaa.qy108.status.AddStatus.ADD_DATA_SUCCESS;
import static com.aaa.qy108.status.DeleteStatus.DELETE_DATA_FAILED;
import static com.aaa.qy108.status.DeleteStatus.DELETE_DATA_SUCCESS;
import static com.aaa.qy108.status.LoginStatus.LOGIN_TIMEOUT_EXIT;
import static com.aaa.qy108.status.SelectStatus.*;
import static com.aaa.qy108.status.UpdateStatus.UPDATE_DATA_FAILED;
import static com.aaa.qy108.status.UpdateStatus.UPDATE_DATA_SUCCESS;

/**
 * @author Liuyibo
 * @date 2020-05-20 17:16
 */
@Service
public class DeptService {
    @Autowired
    private DeptMapper deptMapper;
    /**
     *
     * @Param: [hashMap]
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     *     通过条件查询部门信息
     * @Author: Liuyibo
     * @Date: 2020/5/20 20:33
     */
    public Map<String,Object> selectAllDept(HashMap hashMap ){
        Map<String, Object> resultMap = new HashMap<String, Object>();
            //根据条件查询部门信息
            List<Dept> depts = deptMapper.selectDeptByNameOrTime(hashMap);
            //判断查询出来的信息是否为空
            if(depts.size()>0 && depts !=null){
                resultMap.put("code",SELECT_DATA_SUCCESS.getCode());
                resultMap.put("msg",SELECT_DATA_SUCCESS.getMsg());
                resultMap.put("data",depts);
            }else{
                resultMap.put("code", SELECT_DATA_FAILED.getCode());
                resultMap.put("msg", SELECT_DATA_FAILED.getMsg());
            }
        return resultMap;
    }


    /**
     *
     * @Param: [dept]
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     *     添加部门信息
     * @Author: Liuyibo
     * @Date: 2020/5/21 19:54
     */
    public Map<String,Object> addDept(Dept dept){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //设置创建时间
        dept.setCreateTime(DateUtil.now());
        int addResult = deptMapper.insert(dept);
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
     *
     * @Param: [dept]
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     *     修改部门信息
     * @Author: Liuyibo
     * @Date: 2020/5/21 20:12
     */
    public Map<String,Object> updateDept(Dept dept){
        Map<String, Object> resultMap = new HashMap<String, Object>();
            //设置修改时间
            dept.setModifyTime(DateUtil.now());
            //获取当前部门的信息
            Dept dept1 = deptMapper.selectByPrimaryKey(dept);
            if(dept1 != null){
                //判断是否为空 不为空的话 获取创建时间 并设置为参数
                String createTime = dept1.getCreateTime();
                dept.setCreateTime(createTime);
            }
            int updateResult = deptMapper.updateByPrimaryKey(dept);
            if (updateResult > 0){
                resultMap.put("code", UPDATE_DATA_SUCCESS.getCode());
                resultMap.put("msg", UPDATE_DATA_SUCCESS.getMsg());
            }else{
                resultMap.put("code", UPDATE_DATA_FAILED.getCode());
                resultMap.put("msg", UPDATE_DATA_FAILED.getMsg());
            }
        return resultMap;
    }
    /**
     *
     * @Param: [ids]
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     *     批量删除部门信息
     * @Author: Liuyibo
     * @Date: 2020/5/22 15:10
     */
    public Map<String,Object> delDept(List<Long> ids) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
            //获取到参数类型，然后添加一个where条件，是in类型，id属于ids中的
            Example example = Example.builder(Dept.class).where(Sqls.custom().andIn("deptId",ids)).build();
            int i = deptMapper.deleteByExample(example);
            if (i > 0){
                resultMap.put("code", DELETE_DATA_SUCCESS.getCode());
                resultMap.put("msg", DELETE_DATA_SUCCESS.getMsg());
            }else{
                resultMap.put("code", DELETE_DATA_FAILED.getCode());
                resultMap.put("msg", DELETE_DATA_FAILED.getMsg());
            }
        return resultMap;
    }





}
