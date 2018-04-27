package com.pactera.tams.module.system.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pactera.tams.common.utils.Collections3;
import com.pactera.tams.common.utils.StringUtils;
import com.pactera.tams.module.system.entity.SysUserOrg;
import com.pactera.tams.module.system.mapper.SysUserOrgMapper;


/**
* 用户组织关系Service
* @Author: mjh
* @Date: 2018-03-29 15:30:38
*/
@Service
public class UserOrgService {
	
	private Logger logger = LoggerFactory.getLogger(UserOrgService.class);
	
	@Autowired
	private SysUserOrgMapper userOrgMapper; 
	
	
	/**
	 * 根据用户获取用户所属的组织id集合，逗号分隔
	 * @param role
	 * @return
	 */
	public String getOrgIdsByUser(String userId){
		String orgIds = null;
		
		SysUserOrg userOrg = new SysUserOrg();
		userOrg.setUserId(userId);
		List<SysUserOrg> relations = userOrgMapper.select(userOrg);
		
		if(CollectionUtils.isNotEmpty(relations)) {
			orgIds = Collections3.extractToString(relations, "orgId", ",");
		}
		return orgIds;
	}

	/**
	 * 保存用户所属的组织
	 * @param userId
	 * @param orgIds
	 */
	public void saveOrgIdsByUser(String userId, String orgIds) {
		//删除用户原来的组织
		SysUserOrg userOrg = new SysUserOrg();
		userOrg.setUserId(userId);
		userOrgMapper.delete(userOrg);
		//新增用户所属的组织
		if(StringUtils.isNotEmpty(orgIds)) {
			String[] orgIdsArray = orgIds.split(",");
			for (String orgId : orgIdsArray) {
				userOrgMapper.insert(new SysUserOrg(userId, orgId));
			}
		}
		
		if(logger.isDebugEnabled()) {
			logger.debug("保存用户所属的组织id userId={} orgIds={}", userId, orgIds);
		}
		
	}
	
	/**
	 * 保存所属该组织的用户
	 * @param orgId
	 * @param userIds
	 */
	public void saveUserIdsByRole(String orgId, String userIds) {
		//删除组织原来的用户
		SysUserOrg userOrg = new SysUserOrg();
		userOrg.setOrgId(orgId);
		userOrgMapper.delete(userOrg);
		//新增所属该组织的用户
		if(StringUtils.isNotEmpty(userIds)) {
			String[] userIdsArray = userIds.split(",");
			for (String userId : userIdsArray) {
				userOrgMapper.insert(new SysUserOrg(userId, orgId));
			}
		}
		
		if(logger.isDebugEnabled()) {
			logger.debug("保存所属该组织的用户id orgId={} userIds={}", orgId, userIds);
		}
		
	}


}
