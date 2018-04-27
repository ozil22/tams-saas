package com.pactera.tams.module.system.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.pactera.tams.common.entity.RestResult;
import com.pactera.tams.common.entity.ResultUtils;
import com.pactera.tams.common.utils.StringUtils;
import com.pactera.tams.common.utils.UserUtils;
import com.pactera.tams.exception.ErrorInfo;
import com.pactera.tams.module.system.entity.OpeMenu;
import com.pactera.tams.module.system.entity.SysRole;
import com.pactera.tams.module.system.entity.SysUser;
import com.pactera.tams.module.system.service.MenuService;
import com.pactera.tams.module.system.service.RoleMenuService;
import com.pactera.tams.module.system.service.RoleService;
import com.pactera.tams.module.system.service.UserRoleService;
import com.pactera.tams.module.system.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
* 角色controller
* @Author: mjh
* @Date: 2018-03-19 16:06:14
*/
@Api(value="角色controller",tags={"角色操作接口"})
@RestController
@RequestMapping("/role")
public class RoleController {
	
	private Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
    RoleService roleService;
	
	@Autowired
    UserService userService;
	
	@Autowired
    MenuService menuService;
	
	@Autowired
	RoleMenuService roleMenuService;
	
	@Autowired
	UserRoleService userRoleService;
	
	
	
	@ApiOperation(value = "/get",notes="获取角色")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@GetMapping("/get")
	public RestResult<SysRole> getRole(
			@ApiParam(name="id",value="角色id",required=true)
			@RequestParam(required=true) String id) {
		
		SysRole role = roleService.getObjectById(id);
		if(null==role) {
			return ResultUtils.genErrorResult(ErrorInfo.NOT_EXIST);
		}
		
		if(logger.isDebugEnabled()) {
			logger.debug("获取角色信息 id={} data={}", id, role);
		}
		
		return ResultUtils.genSuccesResult(role);
	}
	
	@ApiOperation(value = "/getList",notes="获取所有角色")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/getList")
	public RestResult<List<SysRole>> getAllRole(
			@ApiParam(name="角色对象",value="传入json格式",required=true)
			@RequestBody(required=true) SysRole role) {
		
		List<SysRole> list = roleService.getList(role);
		
		if(logger.isDebugEnabled()) {
			logger.debug("获取所有角色信息 params={} data={}", role, list);
		}
		
		return ResultUtils.genSuccesResult(list);
	}
	
	
	@ApiOperation(value = "/getPageList",notes="分页获取所有角色")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/getPageList")
	public RestResult<PageInfo<SysRole>> getPageListRole(
			@ApiParam(name="角色对象",value="传入json格式",required=false)
			@RequestBody(required=false) SysRole role) {
		
		List<SysRole> list = roleService.getPageList(role);
		
		if(logger.isDebugEnabled()) {
			logger.debug("分页获取所有角色信息 params={} data={}", role, list);
		}
		
		return ResultUtils.genSuccesResult(ResultUtils.toPageInfo(list));
	}
	
	
	@ApiOperation(value = "/add",notes="新增角色")
	@ApiResponses(value= {@ApiResponse(code=4101,message="数据已存在")})
	@PostMapping("/add")
	public RestResult<SysRole> addRole(
			@ApiParam(name="角色对象",value="传入json格式",required=true)
			@RequestBody(required=true) SysRole role) {

		return roleService.addRole(role);
	}	
	
	@ApiOperation(value = "/update",notes="更新角色")
	@ApiResponses(value= {
			@ApiResponse(code=4101,message="数据已存在"),
			@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/update")
	public RestResult<SysRole> updateRole(
			@ApiParam(name="角色对象",value="传入json格式",required=true)
			@RequestBody(required=true) SysRole role) {
		
		return roleService.updateRole(role);
	}
	
	@ApiOperation(value = "/delete",notes="删除角色")
	@ApiResponses(value= {
			@ApiResponse(code=4102,message="数据不存在"),
			@ApiResponse(code=4104,message="存在关联用户")})
	@GetMapping("/delete")
	public RestResult<SysRole> deleteRole(
			@ApiParam(name="id",value="角色id",required=true)
			@RequestParam(required=true) String id) {
		
		return roleService.deleteRole(id);
	}
	
	@ApiOperation(value = "/getListByUser",notes="获取用户拥有的角色")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/getListByUser")
	public RestResult<List<SysRole>> getAllMenuByUser(
			@ApiParam(name="用户对象",value="传入json格式",required=false)
			@RequestBody(required=false) SysUser user) {
		
		if(null!=user && StringUtils.isNotBlank(user.getId())) {
			//传入用户
			user = userService.getObjectById(user.getId());
		}else {
			//当前用户
			user = UserUtils.getCurrentUser();
		}
		
		List<SysRole> list = roleService.getListByUser(user);
		
		if(logger.isDebugEnabled()) {
			logger.debug("获取所有菜单信息 params={} data={}", user, list);
		}
		
		return ResultUtils.genSuccesResult(list);
	}


	@ApiOperation(value = "/getMenuIdsByRole",notes="获取角色拥有的菜单")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@GetMapping("/getMenuIdsByRole")
	public RestResult<String> getMenuIdsByRole(
			@ApiParam(name="roleId",value="角色id",required=true)
			@RequestParam(required=true) String roleId) {
		
		String menuIds  = roleMenuService.getMenuIdsByRole(roleId);
		
		if(logger.isDebugEnabled()) {
			logger.debug("获取用户拥有的菜单（树形结构） params={} data={}", roleId, menuIds);
		}
		
		return ResultUtils.genSuccesResult(menuIds);
	}
	
	
	@ApiOperation(value = "/getMenuTreeSelectedByRole",notes="获取所有菜单（树形）以及角色拥有的菜单id")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@GetMapping("/getMenuTreeSelectedByRole")
	public RestResult<Map<String, Object>> getMenuTreeSelectedByRole(
			@ApiParam(name="roleId",value="角色id",required=true)
			@RequestParam(required=true) String roleId) {
		
		SysRole role = roleService.getObjectById(roleId);
		if(null==role) {
			return ResultUtils.genErrorResult(ErrorInfo.NOT_EXIST);
		}
		
		//菜单树
		List<OpeMenu> menuTree = menuService.getMenuTree();
		//角色拥有的菜单
		String menuIds  = roleMenuService.getMenuIdsByRole(roleId);
		
		if(logger.isDebugEnabled()) {
			logger.debug("获取所有菜单（树形）以及角色拥有的菜单id params={} data={}", roleId, menuIds);
		}
		
		//结果数据
		Map<String, Object> data = Maps.newHashMap();
		data.put("menuTree", menuTree);
		data.put("menuIds", menuIds);
		
		return ResultUtils.genSuccesResult(data);
	}
	
	
	@ApiOperation(value = "/saveMenuTreeSelectedByRole",notes="保存角色拥有的菜单")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/saveMenuTreeSelectedByRole")
	public RestResult<Map<String, Object>> saveMenuTreeSelectedByRole(
			@ApiParam(name="roleId",value="角色id",required=true)
			@RequestParam(required=true) String roleId,
			@ApiParam(name="menuIds",value="角色拥有的菜单id集合，逗号分隔",required=true)
			@RequestParam(required=true) String menuIds) {
		
		SysRole role = roleService.getObjectById(roleId);
		if(null==role) {
			return ResultUtils.genErrorResult(ErrorInfo.NOT_EXIST);
		}
		
		//角色拥有的菜单
		roleMenuService.saveMenuIdsByRole(roleId, menuIds);
		
		if(logger.isDebugEnabled()) {
			logger.debug("保存角色拥有的菜单 roleId={} menuIds={}", roleId, menuIds);
		}
		
		//结果数据
		Map<String, Object> data = Maps.newHashMap();
		data.put("roleId", roleId);
		data.put("menuIds", menuIds);
		
		return ResultUtils.genSuccesResult(data);
	}
	
	
	
	@ApiOperation(value = "/saveUserListSelectedByRole",notes="保存拥有该角色的用户")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/saveUserListSelectedByRole")
	public RestResult<Map<String, Object>> saveUserListSelectedByRole(
			@ApiParam(name="roleId",value="角色id",required=true)
			@RequestParam(required=true) String roleId,
			@ApiParam(name="userIds",value="拥有该角色的用户id集合，逗号分隔",required=true)
			@RequestParam(required=true) String userIds) {
		
		SysRole role = roleService.getObjectById(roleId);
		if(null==role) {
			return ResultUtils.genErrorResult(ErrorInfo.NOT_EXIST);
		}
		
		//拥有该角色的用户
		userRoleService.saveUserIdsByRole(roleId, userIds);
		
		if(logger.isDebugEnabled()) {
			logger.debug("保存角色拥有的菜单 roleId={} roleId={}", roleId, userIds);
		}
		
		//结果数据
		Map<String, Object> data = Maps.newHashMap();
		data.put("roleId", roleId);
		data.put("userIds", userIds);
		
		return ResultUtils.genSuccesResult(data);
	}	
	

}
