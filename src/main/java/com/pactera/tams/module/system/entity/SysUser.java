package com.pactera.tams.module.system.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.pactera.tams.common.entity.BaseTenantEntity;


/**
* 用户实体
* @Author: mjh
* @Date: 2018-03-19 16:08:46
*/
@Table(name = "sys_user")
public class SysUser extends BaseTenantEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 登录名
     */
    @Column(name = "login_name")
    private String loginName;

    @JsonIgnore
    private String password;

    /**
     * 加密用盐
     */
    @JsonIgnore
    private String salt;

    /**
     * 工号
     */
    private String no;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别（1男，0女）
     */
    private Byte sex;
    
    /**
     * 组织机构id
     */
    @Transient
    private String orgId;
    
    /**
     * 组织机构名称
     */
    @Transient
    private String orgName;
    
    /**
     * 组织机构id集合
     */
    @Column(name = "org_ids")
    private String orgIds;
    
    /**
     * 组织机构名称集合
     */
    @Transient
    private String orgNames;
    
    /**
     * 组织机构父级id集合
     */
    @Transient
    @JsonIgnore
    private String orgParentIds;

    /**
     * 公司id（预留）
     */
    @Column(name = "company_id")
    private String companyId;

    /**
     * 部门id（预留）
     */
    @Column(name = "dept_id")
    private String deptId;

    /**
     * 岗位id
     */
    @Column(name = "post_id")
    private String postId;
    
    /**
     * 岗位名称
     */
    @Transient
    private String postName;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 办公电话
     */
    private String telephone;

    /**
     * 移动电话
     */
    private String mobile;

    /**
     * 传真
     */
    private String fax;

    /**
     * 用户类型（预留）
     */
    @Column(name = "user_type")
    private Byte userType;

    /**
     * 头像路径
     */
    @Column(name = "avatar_path")
    private String avatarPath;

    /**
     * 登录IP
     */
    @Column(name = "login_ip")
    private String loginIp;

    /**
     * 最后登陆时间
     */
    @Column(name = "login_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date loginDate;

    /**
     * 登录次数
     */
    @Column(name = "login_times")
    private Integer loginTimes;

    /**
     * 登录标识（1是，0否）
     */
    @Column(name = "login_flag")
    private Byte loginFlag;

    /**
     * 启用状态（1是，0否）
     */
    private Byte enabled;
    
    /**
     * 用户拥有的角色列表
     */
    @Transient
    private List<SysRole> roles = Lists.newArrayList();
    
    /**
     * 用户拥有的菜单列表
     */
    @Transient
    private List<OpeMenu> menus = Lists.newArrayList();
    

    /**
     * 获取登录名
     *
     * @return login_name - 登录名
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登录名
     *
     * @param loginName 登录名
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取加密用盐
     *
     * @return salt - 加密用盐
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置加密用盐
     *
     * @param salt 加密用盐
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 获取工号
     *
     * @return no - 工号
     */
    public String getNo() {
        return no;
    }

    /**
     * 设置工号
     *
     * @param no 工号
     */
    public void setNo(String no) {
        this.no = no;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取性别（1男，0女）
     *
     * @return sex - 性别（1男，0女）
     */
    public Byte getSex() {
        return sex;
    }

    /**
     * 设置性别（1男，0女）
     *
     * @param sex 性别（1男，0女）
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     * 获取组织机构id
     *
     * @return org_id - 组织机构id
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 设置组织机构id
     *
     * @param orgId 组织机构id
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    
    public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
	
	public String getOrgNames() {
		return orgNames;
	}

	public void setOrgNames(String orgNames) {
		this.orgNames = orgNames;
	}

	public String getOrgParentIds() {
		return orgParentIds;
	}

	public void setOrgParentIds(String orgParentIds) {
		this.orgParentIds = orgParentIds;
	}

	/**
     * 获取公司id
     *
     * @return company_id - 公司id
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * 设置公司id
     *
     * @param companyId 公司id
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取部门id
     *
     * @return dept_id - 部门id
     */
    public String getDeptId() {
        return deptId;
    }

    /**
     * 设置部门id
     *
     * @param deptId 部门id
     */
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取岗位id
     *
     * @return post_id - 岗位id
     */
    public String getPostId() {
        return postId;
    }

    /**
     * 设置岗位id
     *
     * @param postId 岗位id
     */
    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	/**
     * 获取电子邮件
     *
     * @return email - 电子邮件
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置电子邮件
     *
     * @param email 电子邮件
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取办公电话
     *
     * @return telephone - 办公电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置办公电话
     *
     * @param telephone 办公电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取移动电话
     *
     * @return mobile - 移动电话
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置移动电话
     *
     * @param mobile 移动电话
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    /**
     * 获取用户类型（预留）
     *
     * @return user_type - 用户类型（预留）
     */
    public Byte getUserType() {
        return userType;
    }

    /**
     * 设置用户类型（预留）
     *
     * @param userType 用户类型（预留）
     */
    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    /**
     * 获取头像路径
     *
     * @return avatar_path - 头像路径
     */
    public String getAvatarPath() {
        return avatarPath;
    }

    /**
     * 设置头像路径
     *
     * @param avatarPath 头像路径
     */
    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    /**
     * 获取登录IP
     *
     * @return login_ip - 登录IP
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 设置登录IP
     *
     * @param loginIp 登录IP
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * 获取最后登陆时间
     *
     * @return login_date - 最后登陆时间
     */
    public Date getLoginDate() {
        return loginDate;
    }

    /**
     * 设置最后登陆时间
     *
     * @param loginDate 最后登陆时间
     */
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    /**
     * 获取登录次数
     *
     * @return login_times - 登录次数
     */
    public Integer getLoginTimes() {
        return loginTimes;
    }

    /**
     * 设置登录次数
     *
     * @param loginTimes 登录次数
     */
    public void setLoginTimes(Integer loginTimes) {
        this.loginTimes = loginTimes;
    }

    /**
     * 获取登录标识（1是，0否）
     *
     * @return login_flag - 登录标识（1是，0否）
     */
    public Byte getLoginFlag() {
        return loginFlag;
    }

    /**
     * 设置登录标识（1是，0否）
     *
     * @param loginFlag 登录标识（1是，0否）
     */
    public void setLoginFlag(Byte loginFlag) {
        this.loginFlag = loginFlag;
    }

    /**
     * 获取启用状态（1是，0否）
     *
     * @return enabled - 启用状态（1是，0否）
     */
    public Byte getEnabled() {
        return enabled;
    }

    /**
     * 设置启用状态（1是，0否）
     *
     * @param enabled 启用状态（1是，0否）
     */
    public void setEnabled(Byte enabled) {
        this.enabled = enabled;
    }

	public List<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}

	public List<OpeMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<OpeMenu> menus) {
		this.menus = menus;
	}
}