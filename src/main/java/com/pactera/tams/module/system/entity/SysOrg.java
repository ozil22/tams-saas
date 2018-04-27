package com.pactera.tams.module.system.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;

import com.pactera.tams.common.entity.BaseTreeTenantEntity;


/**
* 组织机构
* @Author: mjh
* @Date: 2018-03-19 16:08:11
*/
@Table(name = "sys_org")
public class SysOrg extends BaseTreeTenantEntity<SysOrg>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     * 简称
     */
    @Column(name = "short_name")
    private String shortName;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 传真
     */
    private String fax;

    /**
     * 获取简称
     *
     * @return short_name - 简称
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 设置简称
     *
     * @param shortName 简称
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * 获取电话
     *
     * @return telephone - 电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置电话
     *
     * @param telephone 电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取传真
     *
     * @return fax - 传真
     */
    public String getFax() {
        return fax;
    }

    /**
     * 设置传真
     *
     * @param fax 传真
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

	@Override
	public List<SysOrg> getChildren() {
		return children;
	}

	@Override
	public void setChildren(List<SysOrg> children) {
		this.children = children;
	}
}