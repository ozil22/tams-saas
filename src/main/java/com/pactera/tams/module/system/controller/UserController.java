package com.pactera.tams.module.system.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.crypto.hash.Md5Hash;
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
import com.pactera.tams.common.entity.Global;
import com.pactera.tams.common.entity.RestResult;
import com.pactera.tams.common.entity.ResultUtils;
import com.pactera.tams.exception.ErrorInfo;
import com.pactera.tams.module.system.entity.SysRole;
import com.pactera.tams.module.system.entity.SysUser;
import com.pactera.tams.module.system.service.RoleService;
import com.pactera.tams.module.system.service.UserRoleService;
import com.pactera.tams.module.system.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
* 用户controller
* @Author: mjh
* @Date: 2018-03-19 16:06:26
*/
@Api(value="用户controller",tags={"用户操作接口"})
@RestController
@RequestMapping("/user")
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
    UserService userService;
	
	@Autowired
    RoleService roleService;
	
	@Autowired
	UserRoleService userRoleService;
	
	
	@ApiOperation(value = "/get",notes="获取用户")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@GetMapping("/get")
	public RestResult<SysUser> getUser(
			@ApiParam(name="id",value="用户id",required=true)
			@RequestParam(required=true) String id) {
		
		SysUser user = userService.getObjectById(id);
		if(null==user) {
			return ResultUtils.genErrorResult(ErrorInfo.NOT_EXIST);
		}
		
		if(logger.isDebugEnabled()) {
			logger.debug("获取用户信息 id={} data={}", id, user);
		}
		
		return ResultUtils.genSuccesResult(user);
	}
	
	@ApiOperation(value = "/getList",notes="获取所有用户，可查询某个组织及其子级组织下的所有用户")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/getList")
	public RestResult<List<SysUser>> getAllUser(
			@ApiParam(name="用户对象",value="传入json格式",required=true)
			@RequestBody(required=true) SysUser user) {
		
		List<SysUser> list = userService.getList(user);
		
		if(logger.isDebugEnabled()) {
			logger.debug("获取所有用户信息 params={} data={}", user, list);
		}
		
		return ResultUtils.genSuccesResult(list);
	}
	
	
	@ApiOperation(value = "/getPageList",notes="分页获取所有用户，已经实现查询某个组织及其子级组织下的所有用户")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/getPageList")
	public RestResult<PageInfo<SysUser>> getPageListUser(
			@ApiParam(name="用户对象",value="传入json格式",required=false)
			@RequestBody(required=false) SysUser user) {
		
		List<SysUser> list = userService.getPageList(user);
		
		if(logger.isDebugEnabled()) {
			logger.debug("分页获取所有用户信息，已经实现查询某个组织及其子级组织下的所有用户  params={} data={}", user, list);
		}
		
		return ResultUtils.genSuccesResult(ResultUtils.toPageInfo(list));
	}
	
	
	@ApiOperation(value = "/add",notes="新增用户")
	@ApiResponses(value= {@ApiResponse(code=4101,message="数据已存在")})
	@PostMapping("/add")
	public RestResult<SysUser> addUser(
			@ApiParam(name="用户对象",value="传入json格式",required=true)
			@RequestBody(required=true) SysUser user) {

		return userService.addUser(user);
	}	
	
	@ApiOperation(value = "/update",notes="更新用户")
	@ApiResponses(value= {
			@ApiResponse(code=4101,message="数据已存在"),
			@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/update")
	public RestResult<SysUser> updateUser(
			@ApiParam(name="用户对象",value="传入json格式",required=true)
			@RequestBody(required=true) SysUser user) {
		
		//确保更新用户时，不会更新密码
		user.setPassword(null);
		
		return userService.updateUser(user);
	}
	
	@ApiOperation(value = "/delete",notes="删除用户")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@GetMapping("/delete")
	public RestResult<SysUser> deleteUser(
			@ApiParam(name="id",value="用户id",required=true)
			@RequestParam(required=true) String id) {
		
		return userService.deleteUser(id);
	}
	
	@ApiOperation(value = "/updateEnable",notes="更新用户启用状态")
	@ApiResponses(value= {
			@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/updateEnable")
	public RestResult<SysUser> updateEnable(
			@ApiParam(name="用户对象",value="传入json格式",required=true)
			@RequestBody(required=true) SysUser user) {
		
		//确保更新用户时，不会更新密码
		user.setPassword(null);
		
		return userService.updateUserDirectly(user);
	}
	
	
	@ApiOperation(value = "/setDefaultPassword",notes="设置用户默认密码")
	@ApiResponses(value= {
			@ApiResponse(code=4102,message="数据不存在")})
	@GetMapping("/setDefaultPassword")
	public RestResult<SysUser> setDefaultPassword(
			@ApiParam(name="id",value="用户id",required=true)
			@RequestParam(required=true) String id) {
		
		if(null==id) {
			return ResultUtils.genErrorResult(ErrorInfo.MISSING_PARAM);
		}
		
		SysUser existBean = userService.getObjectById(id);
		if(null==existBean) {
			return ResultUtils.genErrorResult(ErrorInfo.NOT_EXIST);
		}
		
		SysUser user = new SysUser();
		user.setId(id);
		//默认密码
		String md5Pwd = new Md5Hash(Global.DEFAULT_PASSWORD, existBean.getSalt()).toString();
		user.setPassword(md5Pwd);
		
		return userService.updateUserDirectly(user);
	}
	
	
	@ApiOperation(value = "/updatePassword",notes="用户修改密码")
	@ApiResponses(value= {
			@ApiResponse(code=1008,message="原密码错误"),
			@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/updatePassword")
	public RestResult<SysUser> updatePassword(
			@ApiParam(name="id",value="用户id",required=true)
			@RequestParam(required=true) String id,			
			@ApiParam(name="password",value="原密码",required=true)
			@RequestParam(required=true) String password,			
			@ApiParam(name="newPassword",value="新密码",required=true)
			@RequestParam(required=true) String newPassword) {
		
		SysUser user = userService.getObjectById(id);
		if(null==user) {
			return ResultUtils.genErrorResult(ErrorInfo.NOT_EXIST);
		}
		
		//原密码校验
		String md5Pwd = new Md5Hash(password, user.getSalt()).toString();
		if(!md5Pwd.equals(user.getPassword())) {
			return ResultUtils.genErrorResult(ErrorInfo.ORIGINAL_PASSWORD_ERROR);
		}
		
		//设置新密码
		String newMd5Pwd = new Md5Hash(newPassword, user.getSalt()).toString();
		user.setPassword(newMd5Pwd);
		
		return userService.updateUserDirectly(user);
	}
	
	
	@ApiOperation(value = "/getRoleListSelectedByUser",notes="获取所有角色（列表）以及用户拥有的角色id")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@GetMapping("/getRoleListSelectedByUser")
	public RestResult<Map<String, Object>> getRoleListSelectedByUser(
			@ApiParam(name="userId",value="用户id",required=true)
			@RequestParam(required=true) String userId) {
		
		SysUser user = userService.getObjectById(userId);
		if(null==user) {
			return ResultUtils.genErrorResult(ErrorInfo.NOT_EXIST);
		}
		
		//所有角色列表
		List<SysRole> roles = roleService.getList(new SysRole());
		//用户拥有的角色
		String roleIds  = userRoleService.getRoleIdsByUser(userId);
		
		if(logger.isDebugEnabled()) {
			logger.debug("获取所有角色（列表）以及用户拥有的角色id params={} data={}", userId, roleIds);
		}
		
		//结果数据
		Map<String, Object> data = Maps.newHashMap();
		data.put("roles", roles);
		data.put("roleIds", roleIds);
		
		return ResultUtils.genSuccesResult(data);
	}
	
	
	@ApiOperation(value = "/saveRoleListSelectedByUser",notes="保存用户拥有的角色")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/saveRoleListSelectedByUser")
	public RestResult<Map<String, Object>> saveRoleListSelectedByUser(
			@ApiParam(name="userId",value="用户id",required=true)
			@RequestParam(required=true) String userId,
			@ApiParam(name="roleIds",value="用户拥有的角色id集合，逗号分隔",required=true)
			@RequestParam(required=true) String roleIds) {
		
		SysUser user = userService.getObjectById(userId);
		if(null==user) {
			return ResultUtils.genErrorResult(ErrorInfo.NOT_EXIST);
		}
		
		//角色拥有的菜单
		userRoleService.saveRoleIdsByUser(userId, roleIds);
		
		if(logger.isDebugEnabled()) {
			logger.debug("保存用户拥有的角色 userId={} menuIds={}", userId, roleIds);
		}
		
		//结果数据
		Map<String, Object> data = Maps.newHashMap();
		data.put("userId", userId);
		data.put("roleIds", roleIds);
		
		return ResultUtils.genSuccesResult(data);
	}
		
	
	@ApiOperation(value = "/getUserListByRole",notes="获取拥有该角色的用户")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@GetMapping("/getUserListByRole")
	public RestResult<List<SysUser>> getUserListByRole(
			@ApiParam(name="roleId",value="角色id",required=true)
			@RequestParam(required=true) String roleId) {
		
		SysRole role = roleService.getObjectById(roleId);
		if(null==role) {
			return ResultUtils.genErrorResult(ErrorInfo.NOT_EXIST);
		}
		
		List<SysUser> list = userRoleService.getUserListByRole(roleId);
		
		if(logger.isDebugEnabled()) {
			logger.debug("获取拥有该角色的用户 params={} data={}", roleId, list);
		}
		
		return ResultUtils.genSuccesResult(list);
	}

}
