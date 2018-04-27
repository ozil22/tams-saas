package com.pactera.tams.module.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pactera.tams.common.mapper.MyMapper;
import com.pactera.tams.module.system.entity.SysUser;


/**
* 用户Mapper
* @Author: mjh
* @Date: 2018-03-19 16:10:49
*/
public interface SysUserMapper extends MyMapper<SysUser> {
	
	/**
	 * 获取用户列表
	 * 主要实现查询某个组织及其子级组织下的所有用户
	 * @param user
	 * @return
	 */
	List<SysUser> getList(SysUser user);

	/**
	 * 根据登录名（登录名、工号或者手机号）查询用户
	 * @param loginName
	 * @param tenantId 
	 * @return
	 */
	List<SysUser> getUserListByLoginInfo(@Param("loginName")String loginName, @Param("tenantId")String tenantId);
	
	
	
}