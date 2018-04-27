package com.pactera.tams.module.system.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.pactera.tams.common.entity.Global;
import com.pactera.tams.common.entity.RestResult;
import com.pactera.tams.common.entity.ResultUtils;
import com.pactera.tams.common.utils.UserUtils;
import com.pactera.tams.exception.ErrorInfo;
import com.pactera.tams.jwt.Jwt;
import com.pactera.tams.module.system.entity.OpeTenant;
import com.pactera.tams.module.system.entity.SysUser;
import com.pactera.tams.module.system.service.TenantService;
import com.pactera.tams.module.system.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
* 登录controller
* @Author: mjh
* @Date: 2018-03-19 16:06:26
*/
@Api(value="登录controller",tags={"登录操作接口"})
@RestController
public class LoginController {
	
	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
    UserService userService;
	
	@Autowired
	TenantService tenatService;

	
	@ApiOperation(value = "/login",notes="登录")
	@ApiResponses(value= {
			@ApiResponse(code=1001, message="用户不存在"),
			@ApiResponse(code=1004, message="用户或者密码错误"),
			@ApiResponse(code=1005, message="获取用户角色菜单失败"),
			@ApiResponse(code=1006, message="用户不明确"),
			@ApiResponse(code=1007, message="运营商服务不可用")})
	@PostMapping("/login")
	public RestResult<Map<String, Object>> login(
			@ApiParam(name="loginName",value="登录名/工号/电话",required=true)
			@RequestParam(required=true) String loginName, 
			@ApiParam(name="password",value="密码",required=true)
			@RequestParam(required=true) String password, 
			@ApiParam(name="tenantId",value="租户id",required=false)
			@RequestParam(required=false) String tenantId) {
		
		logger.debug("用户登录 loginName={}, tenantId={}", loginName, tenantId);
		
		SysUser user = null;
		
		//用户名、工号、电话都查一遍
		List<SysUser> users = userService.getUserListByLoginInfo(loginName, tenantId);
		if(CollectionUtils.isNotEmpty(users)) {
			if(1==users.size()) {
				//只有一个用户
				user = users.get(0);
			}else {
				//多个用户，查出对应多个租户，返回给前端，让前端选一个租户，再进行login
				List<OpeTenant> tenants = Lists.newArrayList();
				for (SysUser sysUser : users) {
					OpeTenant tenant = tenatService.getObjectById(sysUser.getTenantId());
					if(null!=tenant && Global.ENABLE==tenant.getEnabled()) {
						tenants.add(tenant);
					}
				}
				
				if(CollectionUtils.isNotEmpty(tenants)) {
					if(1==tenants.size()) {
						//一个租户，查出对应用户
						OpeTenant tenant = tenants.get(0);
						for (SysUser sysUser : users) {
							if(sysUser.getTenantId().equals(tenant.getId())) {
								user = sysUser;
								break;
							}
						}
					}else {
						//多个租户，返回租户列表
						Map<String, Object> data = Maps.newHashMap();
						data.put("tenants", tenants);
						return ResultUtils.genResult(false, ErrorInfo.USER_INDEFINITE.getMsg(),
								ErrorInfo.USER_INDEFINITE.getCode(), data);
					}
				}else {
					//查不到租户，租户已经停用服务
					return ResultUtils.genErrorResult(ErrorInfo.OPERATOR_SERVICE_NOT_AVAILABLE);
				}
			}
		}
		
		//用户是否存在判断
		if(null==user) {
			return ResultUtils.genErrorResult(ErrorInfo.USER_NOT_EXIST);
		}
		
		//密码校验
		String md5Pwd = new Md5Hash(password,user.getSalt()).toString();
		if(!md5Pwd.equals(user.getPassword())) {
			return ResultUtils.genErrorResult(ErrorInfo.LOGIN_INFO_ERROR);
		}
		
		//获取用户角色权限信息
		try {
			user = UserUtils.get(user.getId());
		} catch (Exception e) {
			logger.debug("用户登录，获取角色权限失败 loginName={}，msg={}", loginName, e);
			return ResultUtils.genErrorResult(ErrorInfo.GET_USER_ROLE_MENU_ERROR);
		}
		//租户（公司信息）
		OpeTenant tenant = tenatService.getObjectById(user.getTenantId());
		
		//创建token
		String token = Jwt.createToken(user.getId(), user.getTenantId());
		
		logger.debug("userId:{}, tenantId:{}", user.getId(), user.getTenantId());
		
		//结果数据
		Map<String, Object> data = Maps.newHashMap();
		data.put("token", token);
		data.put("user", user);
		data.put("tenant", tenant);
		
		
		return ResultUtils.genSuccesResult(data);
	}	
	
	
	@ApiOperation(value = "/logout",notes="登出")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@GetMapping("/logout")
	public RestResult<SysUser> logout() {
		
		return ResultUtils.genSuccesResult(null);
	}

	
	

}
