package com.pactera.tams.module.system.controller;

import java.util.List;

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
import com.pactera.tams.common.entity.RestResult;
import com.pactera.tams.common.entity.ResultUtils;
import com.pactera.tams.common.utils.StringUtils;
import com.pactera.tams.common.utils.UserUtils;
import com.pactera.tams.exception.ErrorInfo;
import com.pactera.tams.module.system.entity.OpeMenu;
import com.pactera.tams.module.system.entity.SysUser;
import com.pactera.tams.module.system.service.MenuService;
import com.pactera.tams.module.system.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
* 菜单controller
* @Author: mjh
* @Date: 2018-03-19 16:06:14
*/
@Api(value="菜单controller",tags={"菜单操作接口"})
@RestController
@RequestMapping("/menu")
public class MenuController {
	
	private Logger logger = LoggerFactory.getLogger(MenuController.class);
	
	@Autowired
    MenuService menuService;
	
	@Autowired
    UserService userService;
	
	
	@ApiOperation(value = "/get",notes="获取菜单")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@GetMapping("/get")
	public RestResult<OpeMenu> getMenu(
			@ApiParam(name="id",value="菜单id",required=true)
			@RequestParam(required=true) String id) {
		
		OpeMenu menu = menuService.getObjectById(id);
		if(null==menu) {
			return ResultUtils.genErrorResult(ErrorInfo.NOT_EXIST);
		}
		
		if(logger.isDebugEnabled()) {
			logger.debug("获取菜单信息 id={} data={}", id, menu);
		}
		
		return ResultUtils.genSuccesResult(menu);
	}
	
	@ApiOperation(value = "/getList",notes="获取所有菜单")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/getList")
	public RestResult<List<OpeMenu>> getAllMenu(
			@ApiParam(name="菜单对象",value="传入json格式",required=true)
			@RequestBody(required=true) OpeMenu menu) {
		
		List<OpeMenu> list = menuService.getList(menu);
		
		if(logger.isDebugEnabled()) {
			logger.debug("获取所有菜单信息 params={} data={}", menu, list);
		}
		
		return ResultUtils.genSuccesResult(list);
	}
	
	
	@ApiOperation(value = "/getPageList",notes="分页获取所有菜单")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/getPageList")
	public RestResult<PageInfo<OpeMenu>> getPageListMenu(
			@ApiParam(name="菜单对象",value="传入json格式",required=false)
			@RequestBody(required=false) OpeMenu menu) {
		
		List<OpeMenu> list = menuService.getPageList(menu);
		
		if(logger.isDebugEnabled()) {
			logger.debug("分页获取所有菜单信息 params={} data={}", menu, list);
		}
		
		return ResultUtils.genSuccesResult(ResultUtils.toPageInfo(list));
	}
	
	
	@ApiOperation(value = "/add",notes="新增菜单")
	@ApiResponses(value= {@ApiResponse(code=4101,message="数据已存在")})
	@PostMapping("/add")
	public RestResult<OpeMenu> addMenu(
			@ApiParam(name="菜单对象",value="传入json格式",required=true)
			@RequestBody(required=true) OpeMenu menu) {

		return menuService.addMenu(menu);
	}	
	
	@ApiOperation(value = "/update",notes="更新菜单")
	@ApiResponses(value= {
			@ApiResponse(code=4101,message="数据已存在"),
			@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/update")
	public RestResult<OpeMenu> updateMenu(
			@ApiParam(name="菜单对象",value="传入json格式",required=true)
			@RequestBody(required=true) OpeMenu menu) {
		
		return menuService.updateMenu(menu);
	}
	
	@ApiOperation(value = "/delete",notes="删除菜单")
	@ApiResponses(value= {
			@ApiResponse(code=4102,message="数据不存在"),
			@ApiResponse(code=4105,message="存在关联角色")})
	@GetMapping("/delete")
	public RestResult<OpeMenu> deleteMenu(
			@ApiParam(name="id",value="菜单id",required=true)
			@RequestParam(required=true) String id) {
		
		return menuService.deleteMenu(id);
	}
	
	@ApiOperation(value = "/getMenuTree",notes="获取所有菜单（树形）")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/getMenuTree")
	public RestResult<List<OpeMenu>> getMenuTree() {
		
		List<OpeMenu> list = menuService.getMenuTree();
		
		if(logger.isDebugEnabled()) {
			logger.debug("获取所有菜单（树形） data={}", list);
		}
		
		return ResultUtils.genSuccesResult(list);
	}
	
	@ApiOperation(value = "/getMenuTreeByUser",notes="获取用户拥有的菜单（树形结构）")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/getMenuTreeByUser")
	public RestResult<List<OpeMenu>> getMenuTreeByUser(
			@ApiParam(name="用户对象",value="传入json格式",required=false)
			@RequestBody(required=false) SysUser user) {
		
		if(null!=user && StringUtils.isNotBlank(user.getId())) {
			//传入用户
			user = userService.getObjectById(user.getId());
		}else {
			//当前用户
			user = UserUtils.getCurrentUser();
		}
		
		List<OpeMenu> list = menuService.getMenuTreeByUser(user);
		
		if(logger.isDebugEnabled()) {
			logger.debug("获取用户拥有的菜单（树形结构） params={} data={}", user, list);
		}
		
		return ResultUtils.genSuccesResult(list);
	}
	
	
	@ApiOperation(value = "/getButtonList",notes="获取用户某个菜单下拥有的按钮权限")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@GetMapping("/getButtonList")
	public RestResult<List<OpeMenu>> getButtonList(
			@ApiParam(name="id",value="菜单id",required=true)
			@RequestParam(required=true) String id) {

		List<OpeMenu> list = menuService.getButtonList(UserUtils.getUserId(), id);
		
		if(logger.isDebugEnabled()) {
			logger.debug("获取用户某个菜单下拥有的按钮权限 params={} data={}", id, list);
		}
		
		return ResultUtils.genSuccesResult(list);
	}
	

}
