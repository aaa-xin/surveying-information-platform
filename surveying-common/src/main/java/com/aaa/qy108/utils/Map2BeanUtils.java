package com.aaa.qy108.utils;

import com.esotericsoftware.reflectasm.MethodAccess;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author guohang
 * @Description map转实体类的工具
 * @Date 2020/5/12 18:34
 */
public class Map2BeanUtils {

    /**
     * 构造方法私有化，不让外界通过new对象调用
     */
    private Map2BeanUtils(){}

    /**
     * 使用一个高性能的java实例化工具来创建对象
     */
    private final static Objenesis OBJENESIS = new ObjenesisStd(true);

    /**
     * 需要一个线程安全的String字符串
     */
    private final static StringBuffer STRING_BUFFER = new StringBuffer();

    /**
     * 使用Map集合作为本地缓存池来使用（需要保证线程安全）
     */
    private final static ConcurrentHashMap<Class, MethodAccess> CONCURRENT_HASH_MAP = new ConcurrentHashMap<Class, MethodAccess>();

    /** 
    * @Description: 把map转换为对象 
    * @Author: guohang
    * @Date: 2020/5/12 19:01
    * @Param: [map, clazz] 
    * @return: T 
    */ 
    public static <T> T map2Bean(Map<String,Object> map, Class<T> clazz){
        //通过class类型获取泛型对象（获取所需要的对象，空对象）
        T instance = OBJENESIS.newInstance(clazz);
        //获取map中的对象，先从CONCURRENT_HASH_MAP中获取会比较快，因为这是一个本地缓存池
        MethodAccess methodAccess = CONCURRENT_HASH_MAP.get(clazz);
        //判断对象是否为空
        if (null == methodAccess){
            //如果本地缓存池中对象是空，直接从MethodAccess中获取对象
            methodAccess = MethodAccess.get(clazz);
            //然后将这个对象放入CONCURRENT_HASH_MAP中
            //如果不存在（新的entry），那么会向map中添加该键值对，并返回null。
            //如果已经存在，那么不会覆盖已有的值，直接返回已经存在的值。
            CONCURRENT_HASH_MAP.putIfAbsent(clazz,methodAccess);
            //循环map中的数据
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                //根据map中的属性名，调用setMethodName()可以获取方法名
                String methodName = setMethodName(entry.getKey());
                //拿到map中的各种数据，通过set方法给对象赋值
                //获取set方法的索引下标（方法名，可变参的参数类型）
                int index = methodAccess.getIndex(methodName, entry.getValue().getClass());
                //找到对象、方法，然后往里边赋值
                methodAccess.invoke(instance,index,entry.getValue());
            }
        }
        return instance;
    }

    /** 
    * @Description: 通过字段获取get/set的方法名
    * @Author: guohang
    * @Date: 2020/5/12 19:49
    * @Param: [fieldName] 
    * @return: java.lang.String 
    */ 
    private static String setMethodName(String fieldName){
        //先把字段的首字母变成大写，比如bookName的get方法是getBookName()
        String field = firstToUpperCase(fieldName);
        //把StringBuffer置空，确保了stringbuffer中没有数据
        STRING_BUFFER.setLength(0);
        //拼接set方法名，先是set，然后将方法名拼接上
        return STRING_BUFFER.append("set").append(field).toString();
    }

    /** 
    * @Description: 把String类型的字符串的首字母变成大写
    * @Author: guohang
    * @Date: 2020/5/12 19:51
    * @Param: [field] 
    * @return: java.lang.String 
    */ 
    private static String firstToUpperCase(String field){
        //截取第一个字母转换成大写，其余不变，拼接起来
        return field.substring(0,1).toUpperCase() + field.substring(1);
    }

}



