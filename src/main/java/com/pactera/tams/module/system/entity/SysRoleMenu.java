package com.pactera.tams.module.system.entity;

import javax.persistence.Column;
import javax.persistence.Table;


/**
* 角色菜单关系实体
* @Author: mjh
* @Date: 2018-03-27 17:17:03
*/
@Table(name = "sys_role_menu")
public class SysRoleMenu {

    @Column(name = "role_id")
    private String roleId;


    @Column(name = "menu_id")
    private String menuId;

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

    /**
     * @return menu_id
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * @param menuId
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    
	public SysRoleMenu() {
	}

	public SysRoleMenu(String roleId, String menuId) {
		super();
		this.roleId = roleId;
		this.menuId = menuId;
	}


}