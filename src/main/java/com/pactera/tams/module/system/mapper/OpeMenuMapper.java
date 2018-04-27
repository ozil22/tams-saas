package com.pactera.tams.module.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pactera.tams.common.mapper.MyMapper;
import com.pactera.tams.module.system.entity.OpeMenu;
import com.pactera.tams.module.system.entity.SysRole;


/**
* 菜单Mapper
* @Author: mjh
* @Date: 2018-03-19 16:09:45
*/
public interface OpeMenuMapper extends MyMapper<OpeMenu> {
	
	/**
	 * 获取角色拥有的菜单
	 * @param role
	 * @return
	 */
	List<OpeMenu> getListByRole(SysRole role);
	
	/**
	 * 获取用户拥有的菜单（已将多角色菜单去重）
	 * @param userId
	 * @param parentId
	 * @param type
	 * @return
	 */
	List<OpeMenu> getListByUser(@Param(value = "userId") String userId, @Param(value = "parentId") String parentId,
			@Param(value = "type") Byte type);

}