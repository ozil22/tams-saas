package com.pactera.tams.common.entity;

import javax.persistence.Column;

/**
* 
* @Author: mjh
* @Date: 2018-03-26 18:07:23
*/
public class BaseTenantEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     * 租户id
     */
    @Column(name = "tenant_id")
    private String tenantId;

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
    
}
