package com.aaa.qy108.base;

import com.aaa.qy108.mapper.UserMapper;
import com.aaa.qy108.utils.BaseUtil;
import com.aaa.qy108.utils.Map2BeanUtils;
import com.aaa.qy108.utils.SpringContextUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author guohang
 * @Description 通用service，这个泛型T代表的是实体类，传递的是什么实体类进来，所注入的mapper就是什么类型
 * @Date 2020/5/11 20:30
 */
public abstract class BaseService<T> {

    /**
     * 本地缓存池
     */
    private Class<T> cache = null;

    @Autowired
    private Mapper<T> mapper;

    @Autowired
    private UserMapper userMapper;

    /** 
    * @Description: 注入进来的mapper为了保证安全性，必须是private的，那么子类也获取不到。如果子类要使用，需要使用提供的这个get方法
    *               这个get方法也不能是public和default的，必须是protected受保护的，只能子类调用。
    * @Author: guohang
    * @Date: 2020/5/11 20:41
    * @Param: [] 
    * @return: tk.mybatis.mapper.common.Mapper 
    */ 
    protected Mapper getMapper(){
        return mapper;
    }

    /** 
    * @Description: 新增的通用功能
    * @Author: guohang
    * @Date: 2020/5/11 20:49
    * @Param: [t] 
    * @return: java.lang.Integer 
    */ 
    public Integer add(T t) throws Exception{
        //insert是所有的字段都会添加一遍，即使没有值
        //insertSelective是选择性新增，只给有值的字段赋值，当你这个实体类中某个字段没有值的时候，就不会添加数据
        return mapper.insertSelective(t);
    }
    
    /**
    * @Description: 通过主键进行删除，这里要用T是因为不知道后期使用的时候主键是什么类型
    * @Author: guohang
    * @Date: 2020/5/11 20:53
    * @Param: [t] 
    * @return: java.lang.Integer 
    */ 
    public Integer delete(T t) throws Exception{
        return mapper.deleteByPrimaryKey(t);
    }

    /**
    * @Description: 通过主键进行批量删除，
    * @Author: guohang
    * @Date: 2020/5/11 20:56
    * @Param: [ids]
    * @return: java.lang.Integer
    */
    public Integer delete(List<Object> ids) throws Exception{
        //获取到参数类型，然后添加一个where条件，是in类型，id属于ids中的
        Example example = Example.builder(getTypeArguement()).where(Sqls.custom().andIn("id",ids)).build();
        return mapper.deleteByExample(example);
    }

    /** 
    * @Description: 【封装的工具方法】java通过反射获得泛型参数，获取子类泛型类型，在mapper操作中会经常需要先传一个参数类型
    * @Author: guohang
    * @Date: 2020/5/11 20:59
    * @Param: [] 
    * @return: java.lang.Class<T> 
    */     
    private Class<T> getTypeArguement(){
        //判断缓存池是否为空
        if (null == cache){
            //如果缓存池为空，说明里边没有数据，则需要往里边存入数据供别的方法使用
            //拿到本类，在拿本类的父类，然后获取父类的参数类型，然后取第一个数据
            //ParameterizedType参数化类型，即泛型。getActualTypeArguments获取参数化类型的数组，泛型可能有多个
            cache = (Class<T>) ((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0];
        }
        return cache;
    }

    /** 
    * @Description: 根据主键选择修改
    * @Author: guohang
    * @Date: 2020/5/11 21:28
    * @Param: [t] 
    * @return: java.lang.Integer 
    */ 
    public Integer update(T t) throws Exception{
        return mapper.updateByPrimaryKeySelective(t);
    }

    /** 
    * @Description: 批量更新
    *               t：所要更新的数据，代表实体类
     *              ids：通过主键来进行更新的主键
    * @Author: guohang
    * @Date: 2020/5/11 21:29
    * @Param: [t, ids, properties] 
    * @return: java.lang.Integer 
    */ 
    public Integer batchUpdate(T t,Object[] ids) throws Exception{
        Example example = Example.builder(getTypeArguement()).where(Sqls.custom().andIn("id", Arrays.asList(ids))).build();
        return mapper.updateByExample(t,example);
    }

    /**
    * @Description: 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
    * @Author: guohang
    * @Date: 2020/5/11 21:43
    * @Param: [t]
    * @return: T
    */
    public T queryOne(T t)throws Exception{
        return mapper.selectOne(t);
    }


    /** 
    * @Description: 【封装的工具方法】封装条件查询（多条件）、分页查询以及排序查询的通用方法
    * @Author: guohang
    * @Date: 2020/5/11 22:01
    * @Param: [pageNo, pageSize, Where, OrderByField, fields] 
    * @return: java.util.List<T> 
    */ 
    private List<T> queryByFieldsBase(Integer pageNo,Integer pageSize,Sqls where,String orderByField,String...fields){
        Example.Builder builder = null;
        //判断条件是否为空
        if (null == fields || fields.length == 0){
            //没有条件，查询所有数据
            builder = Example.builder(getTypeArguement());
        }else{
            //说明有条件，查询指定字段
            builder = Example.builder(getTypeArguement()).select(fields);
        }
        //拼接where查询
        if (null != where){
            builder = builder.where(where);
        }
        //排序，默认降序
        if (null != orderByField){
            builder = builder.orderByDesc(orderByField);
        }
        //pageHelper分页
        if (null != pageNo && null != pageSize){
            PageHelper.startPage(pageNo,pageSize);
        }
        List list = mapper.selectByExample(builder);
        return list;
    }


    /**
     * @Description: 通过指定字段查询一条数据
     * @Author: guohang
     * @Date: 2020/5/11 21:48
     * @Param: [where, orderByField, fields]
     * @return: T
     */
    public T queryByField(Sqls where,String orderByField,String... fields) throws Exception{
        return queryByFieldsBase(null,null,where,orderByField,fields).get(0);
    }


    /**
    * @Description: 根据条件查询集合
    * @Author: guohang
    * @Date: 2020/5/11 23:20
    * @Param: [where, orderByField, fields]
    * @return: java.util.List<T>
    */
    public List<T> queryListByFields(Sqls where,String orderByField,String... fields) throws Exception{
        return queryByFieldsBase(null,null,where,orderByField,fields);
    }

    /** 
    * @Description: 条件分页查询
    * @Author: guohang
    * @Date: 2020/5/11 23:22
    * @Param: [pageNo, pageSize, where, orderByFields, fields] 
    * @return: com.github.pagehelper.PageInfo<T> 
    */ 
    public PageInfo<T> queryListByPageAndFields(Integer pageNo,Integer pageSize,Sqls where,String orderByFields,String...fields) throws Exception{
        return new PageInfo<T>(queryByFieldsBase(pageNo,pageSize,where,orderByFields,fields));
    }

    /** 
    * @Description: 条件查询
    * @Author: guohang
    * @Date: 2020/5/11 23:25
    * @Param: [t] 
    * @return: java.util.List<T> 
    */ 
    public List<T> queryList(T t) throws Exception{
        return mapper.select(t);
    }

    /**
    * @Description: 分页查询
    * @Author: guohang
    * @Date: 2020/5/11 23:27
    * @Param: [t, pageNo, pageSize]
    * @return: com.github.pagehelper.PageInfo<T>
    */
    public PageInfo<T> queryListByPage(T t,Integer pageNo,Integer pageSize) throws Exception{
        PageHelper.startPage(pageNo,pageSize);
        List<T> select = mapper.select(t);
        PageInfo<T> pageInfo = new PageInfo<T>(select);
        return pageInfo;
    }

    /**
    * @Description: 根据反射获取实例对象
    * @Author: guohang
    * @Date: 2020/5/12 18:33
    * @Param: [map]
    * @return: T
    */
    public T newInstance(Map map){
        //调用map转对象的方法，往里边传入map和获取到的参数类型
        return (T) Map2BeanUtils.map2Bean(map,getTypeArguement());
    }


    /** 
    * @Description: 获取spring容器(此项目用不到)
    * @Author: guohang
    * @Date: 2020/5/12 20:26
    * @Param: [] 
    * @return: org.springframework.context.ApplicationContext 
    */ 
    public ApplicationContext getApplicationContext(){
        return SpringContextUtils.getApplicationContext();
    }

    /**
     * @Author Cy
     * @Description 多表分页查询所有用户
     * @Param [map]
     * @Data 2020/5/22
     * @return com.github.pagehelper.PageInfo<java.util.HashMap>
     * @throws
     */
    public PageInfo<HashMap> selectUserPageInfo(HashMap map){
        PageHelper.startPage(BaseUtil.transToInt(map.get("pageNo")),BaseUtil.transToInt(map.get("pageSize")));
        List<HashMap> list = userMapper.selectUserAll(map);
        PageInfo<HashMap> pageInfo = new PageInfo<HashMap>(list);
        if (null != pageInfo && !"".equals(pageInfo)){
            return pageInfo;
        }
        return null;
    }






}