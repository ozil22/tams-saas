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
import com.pactera.tams.module.system.entity.OpeTenant;
import com.pactera.tams.module.system.service.TenantService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
* 租户controller
* @Author: mjh
* @Date: 2018-03-19 16:06:14
*/
@Api(value="租户controller",tags={"租户操作接口"})
@RestController
@RequestMapping("/tenant")
public class TenantController {
	
	private Logger logger = LoggerFactory.getLogger(TenantController.class);
	
	@Autowired
	TenantService objectService;
	
	
	@ApiOperation(value = "/get",notes="获取租户")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@GetMapping("/get")
	public RestResult<OpeTenant> getTenant(
			@ApiParam(name="id",value="租户id",required=true)
			@RequestParam(required=true) String id) {
		
		OpeTenant tenant = objectService.getObjectById(id);
		if(null==tenant) {
			return ResultUtils.genErrorResult(ErrorInfo.NOT_EXIST);
		}
		
		if(logger.isDebugEnabled()) {
			logger.debug("获取租户信息 id={} data={}", id, tenant);
		}
		
		return ResultUtils.genSuccesResult(tenant);
	}
	
	@ApiOperation(value = "/getList",notes="获取所有租户")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/getList")
	public RestResult<List<OpeTenant>> getAllTenant(
			@ApiParam(name="租户对象",value="传入json格式",required=true)
			@RequestBody(required=true) OpeTenant tenant) {
		
		List<OpeTenant> list = objectService.getList(tenant);
		
		if(logger.isDebugEnabled()) {
			logger.debug("获取所有租户信息 params={} data={}", tenant, list);
		}
		
		return ResultUtils.genSuccesResult(list);
	}
	
	
	@ApiOperation(value = "/getPageList",notes="分页获取所有租户")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/getPageList")
	public RestResult<PageInfo<OpeTenant>> getPageListTenant(
			@ApiParam(name="租户对象",value="传入json格式",required=false)
			@RequestBody(required=false) OpeTenant tenant) {
		
		List<OpeTenant> list = objectService.getPageList(tenant);
		
		if(logger.isDebugEnabled()) {
			logger.debug("分页获取所有租户信息 params={} data={}", tenant, list);
		}
		
		return ResultUtils.genSuccesResult(ResultUtils.toPageInfo(list));
	}
	
	
	@ApiOperation(value = "/add",notes="新增租户")
	@ApiResponses(value= {@ApiResponse(code=4101,message="数据已存在")})
	@PostMapping("/add")
	public RestResult<OpeTenant> addTenant(
			@ApiParam(name="租户对象",value="传入json格式",required=true)
			@RequestBody(required=true) OpeTenant tenant) {

		return objectService.addTenant(tenant);
	}	
	
	@ApiOperation(value = "/update",notes="更新租户")
	@ApiResponses(value= {
			@ApiResponse(code=4101,message="数据已存在"),
			@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/update")
	public RestResult<OpeTenant> updateTenant(
			@ApiParam(name="租户对象",value="传入json格式",required=true)
			@RequestBody(required=true) OpeTenant tenant) {
		
		return objectService.updateTenant(tenant);
	}
	
	@ApiOperation(value = "/delete",notes="删除租户")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@GetMapping("/delete")
	public RestResult<OpeTenant> deleteTenant(
			@ApiParam(name="id",value="租户id",required=true)
			@RequestParam(required=true) String id) {
		
		return objectService.deleteTenant(id);
	}
	

}
