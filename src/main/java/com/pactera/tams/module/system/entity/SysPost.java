package com.pactera.tams.module.system.entity;

import javax.persistence.Table;

import com.pactera.tams.common.entity.BaseTenantEntity;


/**
* 岗位实体
* @Author: mjh
* @Date: 2018-03-19 16:08:24
*/
@Table(name = "sys_post")
public class SysPost extends BaseTenantEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 岗位名称
     */
    private String name;

    /**
     * 岗位编码（预留）
     */
    private String code;

    /**
     * 岗位类型（预留）
     */
    private Integer type;



    /**
     * 获取岗位名称
     *
     * @return name - 岗位名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置岗位名称
     *
     * @param name 岗位名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取岗位编码（预留）
     *
     * @return code - 岗位编码（预留）
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置岗位编码（预留）
     *
     * @param code 岗位编码（预留）
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取岗位类型（预留）
     *
     * @return type - 岗位类型（预留）
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置岗位类型（预留）
     *
     * @param type 岗位类型（预留）
     */
    public void setType(Integer type) {
        this.type = type;
    }

    
}