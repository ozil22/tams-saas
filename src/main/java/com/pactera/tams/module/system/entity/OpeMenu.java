package com.pactera.tams.module.system.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;

import com.pactera.tams.common.entity.BaseTreeEntity;


/**
* 菜单实体
* @Author: mjh
* @Date: 2018-03-19 16:07:41
*/
@Table(name = "ope_menu")
public class OpeMenu extends BaseTreeEntity<OpeMenu>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     * 类型（1菜单，2页签，3按钮）
     */
    private Byte type;

    /**
     * 连接地址
     */
    private String href;

    /**
     * 目标（预留）
     */
    private String target;

    /**
     * 图标样式
     */
    private String icon;

    /**
     * 是否显示
     */
    @Column(name = "is_show")
    private Byte isShow;
    
    /**
     * 编码（按钮级权限标识）
     */
    private String code;

    /**
     * 权限标识（shiro预留）
     */
    private String permission;

    /**
     * 获取类型（1菜单，2页签，3按钮）
     *
     * @return type - 类型（1菜单，2页签，3按钮）
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置类型（1菜单，2页签，3按钮）
     *
     * @param type 类型（1菜单，2页签，3按钮）
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 获取连接地址
     *
     * @return href - 连接地址
     */
    public String getHref() {
        return href;
    }

    /**
     * 设置连接地址
     *
     * @param href 连接地址
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * 获取目标（预留）
     *
     * @return target - 目标（预留）
     */
    public String getTarget() {
        return target;
    }

    /**
     * 设置目标（预留）
     *
     * @param target 目标（预留）
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * 获取图标样式
     *
     * @return icon - 图标样式
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标样式
     *
     * @param icon 图标样式
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取是否显示
     *
     * @return is_show - 是否显示
     */
    public Byte getIsShow() {
        return isShow;
    }

    /**
     * 设置是否显示
     *
     * @param isShow 是否显示
     */
    public void setIsShow(Byte isShow) {
        this.isShow = isShow;
    }

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
     * 获取权限标识（shiro预留）
     *
     * @return permission - 权限标识（shiro预留）
     */
    public String getPermission() {
        return permission;
    }

    /**
     * 设置权限标识（shiro预留）
     *
     * @param permission 权限标识（shiro预留）
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

	@Override
	public List<OpeMenu> getChildren() {
		return children;
	}

	@Override
	public void setChildren(List<OpeMenu> children) {
		this.children = children;
	}
    


    
}