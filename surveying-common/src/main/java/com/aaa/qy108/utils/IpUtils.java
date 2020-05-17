package com.aaa.qy108.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.lionsoul.ip2region.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author guohang
 * @Description 和ip地址有关的工具类
 * @Date 2020/5/16 10:46
 */
public class IpUtils {

    /**
     * 私有化构造方法
     */
    private IpUtils(){

    }


    /**
    * @Description: 获取客户端公网IP
    * @Author: guohang
    * @Date: 2020/5/16 11:05
    * @Param: []
    * @return: java.lang.String
    */
/*
    public static String getInternetIp(){
        try {
            // 打开连接
            Document doc = Jsoup.connect("http://chaipip.com/").get();
            Elements ipArea = doc.select("#ip");
            String ip = ipArea.attr("value");
            return ip;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
*/

    /** 
    * @Description: 获取客户端公网IP
    * @Author: guohang
    * @Date: 2020/5/17 19:49
    * @Param: [] 
    * @return: java.lang.String 
    */ 
    public static String getInternetIp() {
        String ip = "";
        String chinaz = "http://ip.chinaz.com/";
        StringBuilder inputLine = new StringBuilder();
        String read = "";
        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            url = new URL(chinaz);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            while ((read = in.readLine()) != null) {
                inputLine.append(read + "\r\n");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Pattern p = Pattern.compile("<dd class=\"fz24\">(.*?)</dd>");
        Matcher m = p.matcher(inputLine.toString());
        if (m.find()) {
            String ipstr = m.group(1);
            ip = ipstr;
        }
        return ip;
    }


    /** 
    * @Description: 使用ip2region工具，根据ip定位到具体的位置 
    * @Author: guohang
    * @Date: 2020/5/16 11:41
    * @Param: [IP] 
    * @return: java.lang.String 
    */ 
    public static String getAddressByIP(String IP) {
        //根据ip进行位置信息搜索
        DbConfig config = null;
        try {
            config = new DbConfig();
            //获取ip库的位置（放在src下）（直接通过测试类获取文件Ip2RegionTest为测试类）
            String dbfile = IpUtils.class.getResource("/ip2region.db").getPath();
            DbSearcher searcher = new DbSearcher(config, dbfile);
            //采用Btree搜索
            DataBlock block = searcher.btreeSearch(IP);
            String address = block.getRegion();
            //打印位置信息（格式：国家|大区|省份|城市|运营商）
            System.out.println(address);
            return address;
        } catch (DbMakerConfigException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "国家|大区|省份|城市|运营商";
    }

}



