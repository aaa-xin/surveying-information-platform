package com.aaa.qy108.utils;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author guohang
 * @Description 日期工具类
 * @Date 2020/5/27 21:34
 */
public class DateUtil {

    private DateUtil(){}

    /**
     * 日期格式
     */
    public static final String DATE_TYPE = "yyyy-MM-dd";

    /**
     * 时间格式
     */
    public static final String TIME_TYPE = "yyyy-MM-dd HH:mm:ss";


    /**
    * @Description: 按照DATE_TYPE格式进行 转换
    * @Author: guohang
    * @Date: 2020/5/27 21:37
    * @Param: [date]
    * @return: java.lang.String
    */
    public static String formatDate(Object date){
        if (null == date){
            return null;
        }else{
            return formatDate(date,DATE_TYPE);
        }
    }


    /**
    * @Description: 按照指定格式日期来进行转换
    * @Author: guohang
    * @Date: 2020/5/27 21:38
    * @Param: []
    * @return: java.lang.String
    */
    public static final String formatDate(Object date,String formatType){
        if (null == date){
            return null;
        }else{
            if (StringUtils.isNotEmpty(formatType)){
                //说明需要根据客户所定义的格式来转换
                SimpleDateFormat format = new SimpleDateFormat(formatType);
                return format.format(date);
            }else{
                //说明没有指定格式
                return formatDate(date);
            }
        }
    }


    /**
    * @Description: 将时间转换为字符串
    * @Author: guohang
    * @Date: 2020/5/27 21:51
    * @Param: [millisecond]
    * @return: java.lang.String
    */
    public static String formatDateAgo(long millisecond){
        StringBuilder stringBuilder = new StringBuilder();
        //判断传进来的时间大小
        if (1000 > millisecond){
            stringBuilder.append(millisecond).append("秒");
        }else{
            //说明传进来的秒数大于1000
            Integer ss = 1000;
            Integer mi = ss * 60;
            Integer hh = mi * 60;
            Integer dd = hh * 24;

            Long day = millisecond / dd;
            Long hour = (millisecond - day * dd) / hh;
            Long minute = (millisecond - day * dd - hour * hh) / mi;
            Long second = (millisecond - day * dd - hour * hh - minute * mi) / ss;

            if (day > 365){
                return formatDate(new Date(millisecond),"yyyy年MM月dd日 HH时mm分ss秒");
            }
            if (day > 0){
                stringBuilder.append(day).append("日");
            }
            if (hour > 0){
                stringBuilder.append(hour).append("时");
            }
            if (minute > 0){
                stringBuilder.append(minute).append("分");
            }
            if (second > 0){
                stringBuilder.append(second).append("秒");
            }
        }
        return stringBuilder.toString();
    }


    /**
    * @Description: 获取当前的日期
    * @Author: guohang
    * @Date: 2020/5/27 22:06
    * @Param: []
    * @return: java.lang.String
    */
    public static final String getCurrentDate(){
        return formatDate(new Date());
    }


    /**
    * @Description: 获取当前年份
    * @Author: guohang
    * @Date: 2020/5/27 22:08
    * @Param: []
    * @return: java.lang.Integer
    */
    public static Integer getCurrentYear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }


}



