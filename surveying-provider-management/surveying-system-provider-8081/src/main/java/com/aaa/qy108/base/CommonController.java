package com.aaa.qy108.base;

import com.aaa.qy108.utils.BaseUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @Author guohang
 * @Description 通用controller，只供provider使用
 * @Date 2020/5/12 21:36
 */
public abstract class CommonController<T> extends BaseController {


    /** 
    * @Description: 在新增之前所执行的内容，可以用来日志处理
    * @Author: guohang
    * @Date: 2020/5/12 21:39
    * @Param: [map] 
    * @return: void 
    */ 
    protected void beforeAdd(Map map) {
        // 也就是说如果在插入之前你需要执行某些业务的时候，则需要编写内容
    }

    /**
    * @Description: 在新增之后所执行的内容
    * @Author: guohang
    * @Date: 2020/5/12 21:39
    * @Param: [map]
    * @return: void
    */
    protected void afterAdd(Map map) {
        // 也就是说如果在插入之后你需要执行某些业务的时候，则需要编写内容
    }

    /** 
    * @Description: 为了让子类继承的时候可以重写这个方法 
    * @Author: guohang
    * @Date: 2020/5/12 21:46
    * @Param: [] 
    * @return: com.aaa.qy108.base.BaseService<T> 
    */ 
    public abstract BaseService<T> getBaseService();


    /** 
    * @Description: 新增数据
    * @Author: guohang
    * @Date: 2020/5/12 22:00
    * @Param: [map] 
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    public ResultData add(@RequestBody Map map){
        //先把map转成对象
        T instance = getBaseService().newInstance(map);
        try {
            Integer insertResult = getBaseService().add(instance);
            if (insertResult > 0){
                return super.addSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.addSuccess();
    }

    /**
    * @Description: 查询List数据
    * @Author: guohang
    * @Date: 2020/5/13 10:30
    * @Param: [map]
    * @return: com.aaa.qy108.base.ResultData
    */
    public ResultData selectListData(@RequestBody Map map){
        // TODO: 2020/5/13 总感觉 queryListByFields中的fields字段有问题，没有查询条件
        T instance = getBaseService().newInstance(map);
        try {
            List<T> tList = getBaseService().queryList(instance);
            return super.selectSuccess(tList,"查询List数据成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.selectFailed("查询List数据失败");
    }

    /**
    * @Description: 不带条件的分页查询
    * @Author: guohang
    * @Date: 2020/5/13 1:17
    * @Param: [map]
    * @return: com.aaa.qy108.base.ResultData
    */
    public ResultData selectAllByPage(@RequestBody Map map){
        Integer pageNo = BaseUtil.transToInt(map.get("pageNo"));
        Integer pageSize = BaseUtil.transToInt(map.get("pageSize"));
        Object t = map.get("t");
        try {
            PageInfo<T> tPageInfo = getBaseService().queryListByPage((T) t, pageNo, pageSize);
            return super.selectSuccess(tPageInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.selectByIdFailed();
    }


    /**
     * @Description: 查询一条数据
     * @Author: guohang
     * @Date: 2020/5/13 0:48
     * @Param: [map]
     * @return: com.aaa.qy108.base.ResultData
     */
    public ResultData selectOne(@RequestBody Map map){
        T instance = getBaseService().newInstance(map);
        try {
            T t = getBaseService().queryOne(instance);
            if (null != t && !"".equals(t)){
                return super.selectSuccess(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.selectFailed();
    }

    /**
    * @Description: 删除数据
    * @Author: guohang
    * @Date: 2020/5/13 1:01
    * @Param: [map]
    * @return: com.aaa.qy108.base.ResultData
    */
    public ResultData delete(@RequestBody Map map){
        T instance = getBaseService().newInstance(map);
        try {
            Integer result = getBaseService().delete(instance);
            if (result > 0){
                return super.deleteSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.deleteFailed();
    }

    /**
    * @Description: 通过id批量删除数据
    * @Author: guohang
    * @Date: 2020/5/13 1:05
    * @Param: [map]
    * @return: com.aaa.qy108.base.ResultData
    */
    public ResultData batchDelete(@RequestBody Map map){
        List<Object> ids = (List<Object>) map.get("ids");
        try {
            Integer result = getBaseService().delete(ids);
            if (result > 0){
                return super.deleteSuccess("批量删除成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.deleteSuccess("批量删除失败！");
    }


    /**
    * @Description: 修改数据
    * @Author: guohang
    * @Date: 2020/5/13 0:37
    * @Param: [map]
    * @return: com.aaa.qy108.base.ResultData
    */
    public ResultData update(@RequestBody Map map){
        T instance = getBaseService().newInstance(map);
        try {
            Integer result = getBaseService().update(instance);
            if (result > 0){
                return super.updateSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.updateFailed();
    }


    /**
    * @Description: 防止数据不安全，所以不能直接在controller某个方法中直接接收HttpServletRequest对象,必须要从本地当前线程中获取对象
    * @Author: guohang
    * @Date: 2020/5/12 22:52
    * @Param: []
    * @return: javax.servlet.http.HttpServletRequest
    */
    public HttpServletRequest getServletRequest(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes;
        if (requestAttributes instanceof  ServletRequestAttributes){
            servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            return servletRequestAttributes.getRequest();
        }
        return null;
    }

    /** 
    * @Description: 使用本地线程中的HttpServletRequest对象获取客户端的session对象，如果不存在则重新创建一个
    * @Author: guohang
    * @Date: 2020/5/12 22:55
    * @Param: [] 
    * @return: javax.servlet.http.HttpSession 
    */ 
    public HttpSession getSession(){
        return getServletRequest().getSession();
    }

    
    /** 
    * @Description: 获取当前客户端的session对象，如果不存在，则直接返回为null
    * @Author: guohang
    * @Date: 2020/5/12 22:56
    * @Param: [] 
    * @return: javax.servlet.http.HttpSession 
    */ 
    public HttpSession getExistSession(){
        return getServletRequest().getSession(false);
    }


}



