package com.pactera.tams.module.system.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


/**
* 用户组织关系实体
* @Author: mjh
* @Date: 2018-03-29 15:28:52
*/
@Table(name = "sys_user_org")
public class SysUserOrg {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Id
    @Column(name = "org_id")
    private String orgId;

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
     * @return org_id
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * @param orgId
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

	public SysUserOrg() {
	}
    
    
	public SysUserOrg(String userId, String orgId) {
		super();
		this.userId = userId;
		this.orgId = orgId;
	}

}