package com.pactera.tams.module.system.mapper;

import java.util.List;

import com.pactera.tams.common.mapper.MyMapper;
import com.pactera.tams.module.system.entity.SysRole;
import com.pactera.tams.module.system.entity.SysUser;


/**
* 角色Mapper
* @Author: mjh
* @Date: 2018-03-19 16:10:40
*/
public interface SysRoleMapper extends MyMapper<SysRole> {
	
	/**
	 * 获取用户拥有的角色
	 * @param user
	 * @return
	 */
	List<SysRole> getListByUser(SysUser user);
}