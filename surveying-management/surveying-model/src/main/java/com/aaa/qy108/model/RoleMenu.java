package com.aaa.qy108.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: 郭航
 * @Date: 2020/05/22
 * @Time: 16:00
 * @Desc
 */
@Table(name = "t_role_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RoleMenu implements Serializable {
    @Column(name = "ROLE_ID")
    private Long roleId;

    @Column(name = "MENU_ID")
    private Long menuId;

}