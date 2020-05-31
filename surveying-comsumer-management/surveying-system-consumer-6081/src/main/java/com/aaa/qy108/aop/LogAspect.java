package com.aaa.qy108.aop;

import cn.hutool.core.date.DateUtil;
import com.aaa.qy108.dynamic.annotation.LogAnnotation;
import com.aaa.qy108.model.User;
import com.aaa.qy108.service.SystemApiService;
import com.aaa.qy108.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author guohang
 * @Description 日志切面
 * @Date 2020/5/27 20:33
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    private SystemApiService systemApiService;

    /**
    * @Description: 定义一个切面，检测到这个注解存在的时候，aop才会生效
    * @Author: guohang
    * @Date: 2020/5/27 20:34
    * @Param: []
    * @return: void
    */
    @Pointcut("@annotation(com.aaa.qy108.dynamic.annotation.LogAnnotation)")
    public void pointcut(){

    }

    /**
    * @Description: 定义一个环形切点
    *               proceedingJoinPoint里边封装了目标路径中的所有参数
    * @Author: guohang
    * @Date: 2020/5/27 20:38
    * @Param: [proceedingJoinPoint]
    * @return: java.lang.Object
    */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        //定义一个返回值
        Object result = null;

        try {
            result = proceedingJoinPoint.proceed();

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        //获取username信息，其实username的信息在方法的参数中，获取了目标方法的参数就可以获取username的值
        Object[] args = proceedingJoinPoint.getArgs();
        User user = new User();
        for (Object arg : args) {
            user = (User) arg;
        }

        //获取登录时间
        String dateTime = DateUtil.now();
        //获取ip地址
        HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ipAddr = IpUtils.getIpAddr(servletRequest);
        System.out.println(ipAddr);
        //获取operationType和operationName
        //获取目标方法所属类的名称，className获取全限定名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        //获取方法名（目标方法名）
        String targetMethodName = proceedingJoinPoint.getSignature().getName();
        String operationName = "";
        String operationType = "";

        try {
            //通过反射信息获取类对象
            Class targetClass = Class.forName(className);
            //获取类中的所有方法
            Method[] methods = targetClass.getMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                //判断两个methodname是不是相同
                if (methodName.equals(targetMethodName)){
                    //需要考虑方法重载，还需要对应方法中参数的个数
                    Class[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length == args.length){
                        //获取最终目标方法
                        operationName = method.getAnnotation(LogAnnotation.class).operationName();
                        operationType = method.getAnnotation(LogAnnotation.class).operationType();
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Map map = new HashMap<>();
        map.put("username",user.getUsername());
        map.put("operationTime",dateTime);
        map.put("ip",ipAddr);
        map.put("operationType",operationType);
        map.put("operationName",operationName);

        systemApiService.addLoginLog(map);

        //如果不return 永远不会跳转回目标controller
        return result;
    }






}



