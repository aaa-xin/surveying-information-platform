package com.aaa.qy108.controller;

import com.aaa.qy108.base.BaseController;
import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.User;
import com.aaa.qy108.service.SurveingApiService;
import feign.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

/**
 * @Author guohang
 * @Description consumer的usercontroller
 * @Date 2020/5/20 14:22
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户管理",tags = "用户管理接口")
public class UserController extends BaseController {

    @Autowired
    private SurveingApiService surveingApiService;


    /**
    * @Description: 用户管理中新增用户
    * @Author: guohang
    * @Date: 2020/5/20 14:43
    * @Param: [user, token]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/addUser")
    @ApiOperation(value = "添加用户",notes = "用户管理的新增用户")
    public ResultData addUser(@RequestBody User user, @RequestParam("tokenId") String tokenId){
        return surveingApiService.addUser(user, tokenId);
    }


    /**
    * @Description: 用户管理中删除用户
    * @Author: guohang
    * @Date: 2020/5/21 15:44
    * @Param: [ids, tokenId]
    * @return: com.aaa.qy108.base.ResultData
    */
    @DeleteMapping("/delUser")
    @ApiOperation(value = "删除用户",notes = "用户管理的删除用户")
    public ResultData delUser(@RequestBody List<Long> ids, @RequestParam("tokenId") String tokenId){
        return surveingApiService.delUser(ids, tokenId);
    }


    /**
    * @Description: 用户管理中修改用户信息
    * @Author: guohang
    * @Date: 2020/5/21 15:47
    * @Param: [user, tokenId]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/updateUser")
    @ApiOperation(value = "修改用户信息",notes = "用户管理的修改用户信息")
    public ResultData updateUser(@RequestBody User user, @RequestParam("tokenId") String tokenId){
        return surveingApiService.updateUser(user, tokenId);
    }


    /**
    * @Description: 用户管理中的导出Excle
    * @Author: guohang
    * @Date: 2020/5/21 16:25
    * @Param: [tokenId]
    */
    @GetMapping("/exportExcle")
    @ApiOperation(value = "导出Excle",notes = "用户管理的导出用户信息")
    public ResponseEntity<byte[]> exportExcle(@RequestParam("tokenId") String tokenId){
        surveingApiService.exportExcle(tokenId);
        ResponseEntity<byte[]> result = null;
        Response response = surveingApiService.exportExcle(tokenId);
        Response.Body body = response.body();
        try (InputStream inputStream = body.asInputStream()) {
            // feign文件下载
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[10240];
            while (true) {
                int len = inputStream.read(buf);
                if (len < 0) {
                    break;
                }
                bos.write(buf, 0, len);
            }
            //网上也有这种将数据读入到byte[]里面的操作，但是会有问题，有可能一些数据没有读完整，导致最终下载的文件打不开，所以最好还是上面那种常规的读取操作
            //byte[] b = new byte[inputStream.available()];
            //inputStream.read(b);
            byte[] b = bos.toByteArray();
            HttpHeaders heads = new HttpHeaders();

            heads.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=user.xls");
            heads.add(HttpHeaders.CONTENT_TYPE, "application/vnd.ms-excel;charset=utf-8");
            heads.add(HttpHeaders.CONNECTION, "close");
            result = new ResponseEntity<>(b, heads, HttpStatus.OK);
        } catch (IOException e) {

        }
        return result;
    }


    /**
    * @Description: 查询用户，带条件
    * @Author: guohang
    * @Date: 2020/5/21 22:54
    * @Param: [map]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("selectUser")
    ResultData selectUserAll(@RequestBody HashMap map){
        return surveingApiService.selectUserAll(map);
    }




}



