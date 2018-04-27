package com.pactera.tams.module.system.entity;

import javax.persistence.Column;
import javax.persistence.Table;

/**
* 用户角色实体
* @Author: mjh
* @Date: 2018-03-27 17:17:25
*/
@Table(name = "sys_user_role")
public class SysUserRole {

    @Column(name = "user_id")
    private String userId;


    @Column(name = "role_id")
    private String roleId;

    /**
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return role_id
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

	public SysUserRole() {
	}
    
	public SysUserRole(String userId, String roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}


}