package com.pactera.tams.module.system.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pactera.tams.common.utils.Collections3;
import com.pactera.tams.common.utils.StringUtils;
import com.pactera.tams.module.system.entity.SysRoleMenu;
import com.pactera.tams.module.system.mapper.SysRoleMenuMapper;


/**
* 角色菜单关系Service
* @Author: mjh
* @Date: 2018-03-27 17:35:48
*/
@Service
public class RoleMenuService {
	
	private Logger logger = LoggerFactory.getLogger(RoleMenuService.class);
	
	@Autowired
	private SysRoleMenuMapper roleMenuMapper; 
	
	
	/**
	 * 根据角色获取角色拥有的菜单id集合，逗号分隔
	 * @param role
	 * @return
	 */
	public String getMenuIdsByRole(String roleId){
		String menuIds = null;
		
		SysRoleMenu roleMenu = new SysRoleMenu();
		roleMenu.setRoleId(roleId);
		List<SysRoleMenu> relations = roleMenuMapper.select(roleMenu);
		
		if(CollectionUtils.isNotEmpty(relations)) {
			menuIds = Collections3.extractToString(relations, "menuId", ",");
		}
		return menuIds;
	}

	/**
	 * 保存角色拥有的菜单
	 * @param roleId
	 * @param menuIds
	 */
	public void saveMenuIdsByRole(String roleId, String menuIds) {
		//删除角色原来的菜单
		SysRoleMenu roleMenu = new SysRoleMenu();
		roleMenu.setRoleId(roleId);
		roleMenuMapper.delete(roleMenu);
		//新增角色拥有的菜单
		if(StringUtils.isNotEmpty(menuIds)) {
			String[] menuIdsArray = menuIds.split(",");
			for (String menuId : menuIdsArray) {
				roleMenuMapper.insert(new SysRoleMenu(roleId, menuId));
			}
		}
		
		if(logger.isDebugEnabled()) {
			logger.debug("保存角色拥有的菜单id roleId={} menuIds={}", roleId, menuIds);
		}
	}


}
