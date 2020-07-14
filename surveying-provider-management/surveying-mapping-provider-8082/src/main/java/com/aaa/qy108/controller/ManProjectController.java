package com.aaa.qy108.controller;

import com.aaa.qy108.model.MappingProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author guohang
 * @Description
 * @Date 2020/6/3 22:48
 */
@RequestMapping("/project")
@RestController
public class ManProjectController {

    @Autowired
    private MappingProjectService manProjectService;

    /**
    * @Description: 查询项目
    * @Author: guohang
    * @Date: 2020/6/3 22:49
    * @Param: [userId]
    * @return: List<ManProject>
    */
    @PostMapping("/allPro")
    public List<MappingProject> selectAllPros(@RequestParam("userId") Long userId){
        try {
            //PageInfo<ManProject> manProjectPageInfo = manProjectService.queryListByPage(manProject,5,2);
            List<MappingProject> manProjects = manProjectService.selectAllPros(userId);
            return manProjects;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
    * @Description: 通过id查询项目
    * @Author: guohang
    * @Date: 2020/6/3 22:51
    * @Param: [id]
    * @return: com.aaa.qy108.model.MappingProject
    */
    @GetMapping("selectById")
    public MappingProject selectById(@RequestParam("id") Long id){
        try {
            MappingProject manProject = manProjectService.selectById(id);
            if (!"".equals(manProject) && null != manProject){
                return manProject;
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
    * @Description: 通过id修改项目
    * @Author: guohang
    * @Date: 2020/6/3 22:51
    * @Param: [manProject]
    * @return: java.lang.Integer
    */
    @PostMapping("/updateById")
    public Integer updateById(@RequestBody MappingProject manProject){
        try {
            Integer integer = manProjectService.updateById(manProject);
            return integer;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
