package com.aaa.qy108.model;

import com.aaa.qy108.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
@Table(name = "t_user")
public class User extends BaseModel {
    /**
     * 用 户 名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 部门ID
     */
    @Column(name = "dept_id")
    private Long deptId;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 联系电话
     */
    private String mobile;

    /**
     * 状态 0锁定 1有效
     */
    private String status;

    /**
     * 最近访问时间
     */
    @Column(name = "last_login_time")
    private String lastLoginTime;

    /**
     * 性别 0男 1女 2保密
     */
    private String ssex;

    /**
     * 描述
     */
    private String description;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户类型 0:单位用户 1:审核用户 2:管理员
     */
    private String type;

    /**
     *  无状态token值
     */
    private String token;

}