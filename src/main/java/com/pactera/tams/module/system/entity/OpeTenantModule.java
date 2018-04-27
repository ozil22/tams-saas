package com.pactera.tams.module.system.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import com.pactera.tams.common.entity.BaseEntity;


/**
* 租户选用模块实体
* @Author: mjh
* @Date: 2018-03-19 16:07:04
*/
@Table(name = "ope_tenant_module")
public class OpeTenantModule extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 模块id
     */
    @Column(name = "module_id")
    private String moduleId;

    /**
     * 模块名称
     */
    @Column(name = "module_name")
    private String moduleName;

    /**
     * 获取模块id
     *
     * @return module_id - 模块id
     */
    public String getModuleId() {
        return moduleId;
    }

    /**
     * 设置模块id
     *
     * @param moduleId 模块id
     */
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    /**
     * 获取模块名称
     *
     * @return module_name - 模块名称
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * 设置模块名称
     *
     * @param moduleName 模块名称
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

}