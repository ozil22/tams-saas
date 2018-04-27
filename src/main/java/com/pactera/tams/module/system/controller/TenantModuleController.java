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
import com.pactera.tams.exception.ErrorInfo;
import com.pactera.tams.module.system.entity.OpeTenantModule;
import com.pactera.tams.module.system.service.TenantModuleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
* 租户模块controller
* @Author: mjh
* @Date: 2018-03-19 16:06:14
*/
@Api(value="租户模块controller",tags={"租户模块操作接口"})
@RestController
@RequestMapping("/tenantModule")
public class TenantModuleController {
	
	private Logger logger = LoggerFactory.getLogger(TenantModuleController.class);
	
	@Autowired
	TenantModuleService objectService;
	
	
	@ApiOperation(value = "/get",notes="获取租户模块")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@GetMapping("/get")
	public RestResult<OpeTenantModule> getTenantModule(
			@ApiParam(name="id",value="租户模块id",required=true)
			@RequestParam(required=true) String id) {
		
		OpeTenantModule post = objectService.getObjectById(id);
		if(null==post) {
			return ResultUtils.genErrorResult(ErrorInfo.NOT_EXIST);
		}
		
		if(logger.isDebugEnabled()) {
			logger.debug("获取租户模块信息 id={} data={}", id, post);
		}
		
		return ResultUtils.genSuccesResult(post);
	}
	
	@ApiOperation(value = "/getList",notes="获取所有租户模块")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/getList")
	public RestResult<List<OpeTenantModule>> getAllTenantModule(
			@ApiParam(name="租户模块对象",value="传入json格式",required=true)
			@RequestBody(required=true) OpeTenantModule post) {
		
		List<OpeTenantModule> list = objectService.getList(post);
		
		if(logger.isDebugEnabled()) {
			logger.debug("获取所有租户模块信息 params={} data={}", post, list);
		}
		
		return ResultUtils.genSuccesResult(list);
	}
	
	
	@ApiOperation(value = "/getPageList",notes="分页获取所有租户模块")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/getPageList")
	public RestResult<PageInfo<OpeTenantModule>> getPageListTenantModule(
			@ApiParam(name="租户模块对象",value="传入json格式",required=false)
			@RequestBody(required=false) OpeTenantModule post) {
		
		List<OpeTenantModule> list = objectService.getPageList(post);
		
		if(logger.isDebugEnabled()) {
			logger.debug("分页获取所有租户模块信息 params={} data={}", post, list);
		}
		
		return ResultUtils.genSuccesResult(ResultUtils.toPageInfo(list));
	}
	
	
	@ApiOperation(value = "/add",notes="新增租户模块")
	@ApiResponses(value= {@ApiResponse(code=4101,message="数据已存在")})
	@PostMapping("/add")
	public RestResult<OpeTenantModule> addTenantModule(
			@ApiParam(name="租户模块对象",value="传入json格式",required=true)
			@RequestBody(required=true) OpeTenantModule post) {

		return objectService.addTenantModule(post);
	}	
	
	@ApiOperation(value = "/update",notes="更新租户模块")
	@ApiResponses(value= {
			@ApiResponse(code=4101,message="数据已存在"),
			@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/update")
	public RestResult<OpeTenantModule> updateTenantModule(
			@ApiParam(name="租户模块对象",value="传入json格式",required=true)
			@RequestBody(required=true) OpeTenantModule post) {
		
		return objectService.updateTenantModule(post);
	}
	
	@ApiOperation(value = "/delete",notes="删除租户模块")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@GetMapping("/delete")
	public RestResult<OpeTenantModule> deleteTenantModule(
			@ApiParam(name="id",value="租户模块id",required=true)
			@RequestParam(required=true) String id) {
		
		return objectService.deleteTenantModule(id);
	}
	

}
