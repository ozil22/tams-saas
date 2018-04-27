package com.pactera.tams.module.system.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pactera.tams.common.utils.Collections3;
import com.pactera.tams.common.utils.StringUtils;
import com.pactera.tams.module.system.entity.SysUser;
import com.pactera.tams.module.system.entity.SysUserRole;
import com.pactera.tams.module.system.mapper.SysUserRoleMapper;

/**
* 用户角色关系Service
* @Author: mjh
* @Date: 2018-03-28 15:53:06
*/
@Service
public class UserRoleService {
	
	private Logger logger = LoggerFactory.getLogger(UserRoleService.class);
	
	@Autowired
	private SysUserRoleMapper userRoleMapper; 
	
	@Autowired
	private UserService userService; 
	
	
	/**
	 * 根据用户获取用户拥有的角色id集合，逗号分隔
	 * @param role
	 * @return
	 */
	public String getRoleIdsByUser(String userId){
		String roleIds = null;
		
		SysUserRole userRole = new SysUserRole();
		userRole.setUserId(userId);
		List<SysUserRole> relations = userRoleMapper.select(userRole);
		
		if(CollectionUtils.isNotEmpty(relations)) {
			roleIds = Collections3.extractToString(relations, "roleId", ",");
		}
		return roleIds;
	}

	/**
	 * 保存用户拥有的角色
	 * @param userId
	 * @param roleIds
	 */
	public void saveRoleIdsByUser(String userId, String roleIds) {
		//删除用户原来的角色
		SysUserRole userRole = new SysUserRole();
		userRole.setUserId(userId);
		userRoleMapper.delete(userRole);
		//新增用户拥有的角色
		if(StringUtils.isNotEmpty(roleIds)) {
			String[] roleIdsArray = roleIds.split(",");
			for (String roleId : roleIdsArray) {
				userRoleMapper.insert(new SysUserRole(userId, roleId));
			}
		}
		
		if(logger.isDebugEnabled()) {
			logger.debug("保存用户拥有的角色id userId={} roleIds={}", userId, roleIds);
		}
		
	}
	
	/**
	 * 获取拥有该角色的用户
	 * @param roleId
	 * @return
	 */
	public List<SysUser> getUserListByRole(String roleId){
		
		List<SysUser> users = Lists.newArrayList();
		//根据角色查询角色关联的用户id
		SysUserRole userRole = new SysUserRole();
		userRole.setRoleId(roleId);
		List<SysUserRole> relations = userRoleMapper.select(userRole);
		//查询用户
		if(CollectionUtils.isNotEmpty(relations)) {
			for (SysUserRole sysUserRole : relations) {
				SysUser user = userService.getObjectById(sysUserRole.getUserId());
				if(null!=user) {
					users.add(user);
				}
			}
		}
		
		return users;
	}
	
	
	/**
	 * 保存拥有该角色的用户
	 * @param roleId
	 * @param userIds
	 */
	public void saveUserIdsByRole(String roleId, String userIds) {
		//删除角色原来的用户
		SysUserRole userRole = new SysUserRole();
		userRole.setRoleId(roleId);
		userRoleMapper.delete(userRole);
		//新增拥有该角色的用户
		if(StringUtils.isNotEmpty(userIds)) {
			String[] userIdsArray = userIds.split(",");
			for (String userId : userIdsArray) {
				userRoleMapper.insert(new SysUserRole(userId, roleId));
			}
		}
		
		if(logger.isDebugEnabled()) {
			logger.debug("保存用户拥有的角色id roleId={} userIds={}", roleId, userIds);
		}
		
	}


}
