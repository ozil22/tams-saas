package com.pactera.tams.module.system.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import com.pactera.tams.common.entity.BaseTenantEntity;


/**
* 角色实体
* @Author: mjh
* @Date: 2018-03-19 16:08:37
*/
@Table(name = "sys_role")
public class SysRole extends BaseTenantEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 部门id（部门角色预留）
     */
    @Column(name = "dept_id")
    private String deptId;

    /**
     * 名称
     */
    private String name;

    /**
     * 编码
     */
    private String code;

    /**
     * 角色类型（预留）
     */
    @Column(name = "role_type")
    private Byte roleType;

    /**
     * 数据范围（数据权限预留）
     */
    @Column(name = "data_scope")
    private Byte dataScope;

    /**
     * 系统内置角色（1是，0否）
     */
    @Column(name = "sys_role")
    private Byte sysRole;

    /**
     * 获取部门id（部门角色预留）
     *
     * @return dept_id - 部门id（部门角色预留）
     */
    public String getDeptId() {
        return deptId;
    }

    /**
     * 设置部门id（部门角色预留）
     *
     * @param deptId 部门id（部门角色预留）
     */
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取编码
     *
     * @return code - 编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置编码
     *
     * @param code 编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取角色类型（预留）
     *
     * @return role_type - 角色类型（预留）
     */
    public Byte getRoleType() {
        return roleType;
    }

    /**
     * 设置角色类型（预留）
     *
     * @param roleType 角色类型（预留）
     */
    public void setRoleType(Byte roleType) {
        this.roleType = roleType;
    }

    /**
     * 获取数据范围（数据权限预留）
     *
     * @return data_scope - 数据范围（数据权限预留）
     */
    public Byte getDataScope() {
        return dataScope;
    }

    /**
     * 设置数据范围（数据权限预留）
     *
     * @param dataScope 数据范围（数据权限预留）
     */
    public void setDataScope(Byte dataScope) {
        this.dataScope = dataScope;
    }

    /**
     * 获取系统内置角色（1是，0否）
     *
     * @return sys_role - 系统内置角色（1是，0否）
     */
    public Byte getSysRole() {
        return sysRole;
    }

    /**
     * 设置系统内置角色（1是，0否）
     *
     * @param sysRole 系统内置角色（1是，0否）
     */
    public void setSysRole(Byte sysRole) {
        this.sysRole = sysRole;
    }
}