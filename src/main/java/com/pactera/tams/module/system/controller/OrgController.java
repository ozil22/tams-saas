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
import com.pactera.tams.common.utils.UserUtils;
import com.pactera.tams.exception.ErrorInfo;
import com.pactera.tams.module.system.entity.SysOrg;
import com.pactera.tams.module.system.service.OrgService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
* 组织机构controller
* @Author: mjh
* @Date: 2018-03-19 16:06:14
*/
@Api(value="组织机构controller",tags={"组织机构操作接口"})
@RestController
@RequestMapping("/org")
public class OrgController {
	
	private Logger logger = LoggerFactory.getLogger(OrgController.class);
	
	@Autowired
    OrgService objectService;
	
	
	@ApiOperation(value = "/get",notes="获取组织机构")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@GetMapping("/get")
	public RestResult<SysOrg> getOrg(
			@ApiParam(name="id",value="组织机构id",required=true)
			@RequestParam(required=true) String id) {
		
		SysOrg org = objectService.getObjectById(id);
		if(null==org) {
			return ResultUtils.genErrorResult(ErrorInfo.NOT_EXIST);
		}
		
		if(logger.isDebugEnabled()) {
			logger.debug("获取组织机构信息 id={} data={}", id, org);
		}
		
		return ResultUtils.genSuccesResult(org);
	}
	
	@ApiOperation(value = "/getList",notes="获取所有组织机构")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/getList")
	public RestResult<List<SysOrg>> getAllOrg(
			@ApiParam(name="组织机构对象",value="传入json格式",required=true)
			@RequestBody(required=true) SysOrg org) {
		
		List<SysOrg> list = objectService.getList(org);
		
		if(logger.isDebugEnabled()) {
			logger.debug("获取所有组织机构信息 params={} data={}", org, list);
		}
		
		return ResultUtils.genSuccesResult(list);
	}
	
	
	@ApiOperation(value = "/getPageList",notes="分页获取所有组织机构")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/getPageList")
	public RestResult<PageInfo<SysOrg>> getPageListOrg(
			@ApiParam(name="组织机构对象",value="传入json格式",required=false)
			@RequestBody(required=false) SysOrg org) {
		
		List<SysOrg> list = objectService.getPageList(org);
		
		if(logger.isDebugEnabled()) {
			logger.debug("分页获取所有组织机构信息 params={} data={}", org, list);
		}
		
		return ResultUtils.genSuccesResult(ResultUtils.toPageInfo(list));
	}
	
	
	@ApiOperation(value = "/add",notes="新增组织机构")
	@ApiResponses(value= {@ApiResponse(code=4101,message="数据已存在")})
	@PostMapping("/add")
	public RestResult<SysOrg> addOrg(
			@ApiParam(name="组织机构对象",value="传入json格式",required=true)
			@RequestBody(required=true) SysOrg org) {

		return objectService.addOrg(org);
	}	
	
	@ApiOperation(value = "/update",notes="更新组织机构")
	@ApiResponses(value= {
			@ApiResponse(code=4101,message="数据已存在"),
			@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/update")
	public RestResult<SysOrg> updateOrg(
			@ApiParam(name="组织机构对象",value="传入json格式",required=true)
			@RequestBody(required=true) SysOrg org) {
		
		return objectService.updateOrg(org);
	}
	
	@ApiOperation(value = "/delete",notes="删除组织机构")
	@ApiResponses(value= {
			@ApiResponse(code=4102,message="数据不存在"),
			@ApiResponse(code=4103,message="存在子数据"),
			@ApiResponse(code=4104,message="存在关联用户")})
	@GetMapping("/delete")
	public RestResult<SysOrg> deleteOrg(
			@ApiParam(name="id",value="组织机构id",required=true)
			@RequestParam(required=true) String id) {
		
		return objectService.deleteOrg(id);
	}
	
	
	@ApiOperation(value = "/getOrgTree",notes="获取组织机构（树形结构）")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/getOrgTree")
	public RestResult<SysOrg> getOrgTree() {
		
		logger.debug("TenantId:{}", UserUtils.getTenantId());
		
		SysOrg tree = objectService.getOrgTree();
		if(logger.isDebugEnabled()) {
			logger.debug("获取组织机构（树形结构） tree={}", tree);
		}
		
		return ResultUtils.genSuccesResult(tree);
	}
	

}
