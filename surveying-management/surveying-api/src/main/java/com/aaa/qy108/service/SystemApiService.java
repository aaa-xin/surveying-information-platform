package com.aaa.qy108.service;

import com.aaa.qy108.base.ResultData;
import com.aaa.qy108.model.*;
import com.aaa.qy108.vo.TokenVo;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author guohang
 * @Description feign的API接口。在开发阶段不能开启熔断，因为一旦开启熔断，异常会被捕捉，不容易发现问题
 *              api接口一定要和provider的controller一模一样
 * @Date 2020/5/15 22:15
 */
@FeignClient(value ="SYSTEM-PROVIDER")
public interface SystemApiService {

    /**
    * @Description: 登录接口
    * @Author: guohang
    * @Date: 2020/5/15 22:43
    * @Param: [user]
    * @return: com.aaa.qy108.model.TokenVo
    */
    @PostMapping("/doLogin")
    TokenVo doLogin(@RequestBody User user);

    /**
    * @Description: 新增登录日志
    * @Author: guohang
    * @Date: 2020/5/27 22:53
    * @Param: [map]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/log/addLoginLog")
    ResultData addLoginLog(@RequestBody Map map);


    /** 
    * @Description: 添加用户接口
    * @Author: guohang
    * @Date: 2020/5/20 14:42
    * @Param: [user, tokenId]
    * @return: com.aaa.qy108.base.ResultData 
    */ 
    @PostMapping("/user/addUser")
    ResultData addUser(@RequestBody User user, @RequestParam("tokenId") String tokenId);

    /**
     *
     * @Param: [dept, tokenId]
     * @Return: com.aaa.qy108.base.ResultData
     *      通过条件查询部门信息
     * @Author: Liuyibo
     * @Date: 2020/5/20 19:41
     */
    @PostMapping("/dept/selectAllDept")
    ResultData selectAllDept(@RequestBody HashMap map);

    /**
     *
     * @Param: [dept, tokenId]
     * @Return: com.aaa.qy108.base.ResultData
     * 添加部门信息
     * @Author: Liuyibo
     * @Date: 2020/5/21 19:50
     */
    @PostMapping("/dept/addDept")
    ResultData addDept(@RequestBody Dept dept, @RequestParam("tokenId") String tokenId);

    /**
     *
     * @Param: [dept, tokenId]
     * @Return: com.aaa.qy108.base.ResultData
     * 修改部门信息
     * @Author: Liuyibo
     * @Date: 2020/5/21 20:11
     */
    @PostMapping("/dept/updateDept")
    ResultData updateDept(@RequestBody Dept dept, @RequestParam("tokenId") String tokenId);
    /**
     *
     * @Param: [ids, tokenId]
     * @Return: com.aaa.qy108.base.ResultData
     * 批量删除部门信息
     * @Author: Liuyibo
     * @Date: 2020/5/22 15:12
     */
    @DeleteMapping("/dept/delDept")
    ResultData delDept(@RequestBody List<Long> ids, @RequestParam("tokenId") String tokenId);
    /** 
    * @Description: 批量删除用户
    * @Author: guohang
    * @Date: 2020/5/20 20:33
    * @Param: [ids, tokenId] 
    * @return: com.aaa.qy108.base.ResultData 
    */
    @DeleteMapping("/user/delUser")
    ResultData delUser(@RequestBody List<Long> ids, @RequestParam("tokenId") String tokenId);

    /**
    * @Description: 用户管理中修改用户信息
    * @Author: guohang
    * @Date: 2020/5/21 15:48
    * @Param: [user, tokenId]
    * @return: com.aaa.qy108.base.ResultData
    */
    @PostMapping("/user/updateUser")
    ResultData updateUser(@RequestBody User user, @RequestParam("tokenId") String tokenId);

    /**
    * @Description: 用户信息导出excle
    * @Author: guohang
    * @Date: 2020/5/21 16:25
    * @Param: [tokenId]
    */
    @GetMapping("/user/exportExcle")
    Response exportExcle(@RequestParam("tokenId") String tokenId);


    /**
     * @Author Cy
     * @Description 条件分页查询所有用户
     * @Param [map]
     * @Data 2020/5/21
     * @return com.aaa.qy108.base.ResultData
     * @throws
     */
    @PostMapping("/user/selectUser")
    ResultData selectUserAll(@RequestBody HashMap map);

    /**
    * @Description: 测绘单位查询，单位名称模糊查询，单位地域和单位资质准确查询
    * @Param: [mappingUnit, tokenId]
    * @return: java.util.List<java.util.HashMap>
    * @Author: Qin
    * @Date: 2020/5/23
    */
    @PostMapping("/utilSelect")
    List<HashMap>  utilSelect(@RequestBody MappingUnit mappingUnit, @RequestParam("tokenId") String tokenId);


    /**
    * @Description: 通过字段查询所有的单位区域和单位资质，进行分组
    * @Param: [feild, tokenId]
    * @return: java.util.List<java.util.HashMap>
    * @Author: Qin
    * @Date: 2020/5/23
    */
    @PostMapping("/selectGroupByFeild")
    List<HashMap> selectGroupByFeild(@RequestParam ("feild") String feild,@RequestParam("tokenId") String tokenId);



    /**
      * @Author Tzg
      * @Description //测绘项目管理，项目名称模糊查询，类型 ，日期精确查
      * @Date 15:58 2020/5/23
      * @Param  * @param null
      * @return
     **/
    @PostMapping("/projectSelect")
    List<HashMap>  projectSelect(@RequestBody MappingProject mappingProject, @RequestParam("tokenId") String tokenId);

     /**
      * @Author Tzg
      * @Description //通过字段查询类型，日期 ，进行分组
      * @Date 16:01 2020/5/23
      * @Param  * @param null
      * @return
    **/
    @PostMapping("/SelectGroupName")
    List<HashMap> SelectGroupName(@RequestParam ("name") String name,@RequestParam("tokenId") String tokenId);

    /**
     * @Description: 分页查询字典信息
     * @Param: [hashMap]
     * @Author: mi
     * @Return: com.aaa.qy108.base.ResultData
     * @Date: 2020/5/24 18:08
     **/
    @PostMapping("/dict/selectAllDictByPage")
    ResultData selectAllDictByPage(@RequestBody HashMap hashMap);

    /**
     * @Description: 新增字典信息
     * @Param: [dict, tokenId]
     * @Author: mi
     * @Return: com.aaa.qy108.base.ResultData
     * @Date: 2020/5/24 18:12
     **/
    @PostMapping("/dict/addDict")
    ResultData addDict(@RequestBody Dict dict, @RequestParam("tokenId") String tokenId);

    /**
     * @Description: 批量删除字典信息
     * @Param: [ids, tokenId]
     * @Author: mi
     * @Return: com.aaa.qy108.base.ResultData
     * @Date: 2020/5/24 18:12
     **/
    @DeleteMapping("/dict/delDictsById")
    ResultData delDictsById(@RequestBody List<Long> ids,@RequestParam("tokenId") String tokenId);

    /**
     * @Description: 修改字典信息
     * @Param: [dict, tokenId]
     * @Author: mi
     * @Return: com.aaa.qy108.base.ResultData
     * @Date: 2020/5/24 18:12
     **/
    @PostMapping("/dict/updateDict")
    ResultData updateDict(@RequestBody Dict dict,@RequestParam("tokenId") String tokenId);
}



