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
import com.pactera.tams.module.system.entity.SysPost;
import com.pactera.tams.module.system.service.PostService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
* 岗位controller
* @Author: mjh
* @Date: 2018-03-19 16:06:14
*/
@Api(value="岗位controller",tags={"岗位操作接口"})
@RestController
@RequestMapping("/post")
public class PostController {
	
	private Logger logger = LoggerFactory.getLogger(PostController.class);
	
	@Autowired
    PostService objectService;
	
	
	@ApiOperation(value = "/get",notes="获取岗位")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@GetMapping("/get")
	public RestResult<SysPost> getPost(
			@ApiParam(name="id",value="岗位id",required=true)
			@RequestParam(required=true) String id) {
		
		SysPost post = objectService.getObjectById(id);
		if(null==post) {
			return ResultUtils.genErrorResult(ErrorInfo.NOT_EXIST);
		}
		
		if(logger.isDebugEnabled()) {
			logger.debug("获取岗位信息 id={} data={}", id, post);
		}
		
		return ResultUtils.genSuccesResult(post);
	}
	
	@ApiOperation(value = "/getList",notes="获取所有岗位")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/getList")
	public RestResult<List<SysPost>> getAllPost(
			@ApiParam(name="岗位对象",value="传入json格式",required=true)
			@RequestBody(required=true) SysPost post) {
		
		List<SysPost> list = objectService.getList(post);
		
		if(logger.isDebugEnabled()) {
			logger.debug("获取所有岗位信息 params={} data={}", post, list);
		}
		
		return ResultUtils.genSuccesResult(list);
	}
	
	
	@ApiOperation(value = "/getPageList",notes="分页获取所有岗位")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/getPageList")
	public RestResult<PageInfo<SysPost>> getPageListPost(
			@ApiParam(name="岗位对象",value="传入json格式",required=false)
			@RequestBody(required=false) SysPost post) {
		
		List<SysPost> list = objectService.getPageList(post);
		
		if(logger.isDebugEnabled()) {
			logger.debug("分页获取所有岗位信息 params={} data={}", post, list);
		}
		
		return ResultUtils.genSuccesResult(ResultUtils.toPageInfo(list));
	}
	
	
	@ApiOperation(value = "/add",notes="新增岗位")
	@ApiResponses(value= {@ApiResponse(code=4101,message="数据已存在")})
	@PostMapping("/add")
	public RestResult<SysPost> addPost(
			@ApiParam(name="岗位对象",value="传入json格式",required=true)
			@RequestBody(required=true) SysPost post) {

		return objectService.addPost(post);
	}	
	
	@ApiOperation(value = "/update",notes="更新岗位")
	@ApiResponses(value= {
			@ApiResponse(code=4101,message="数据已存在"),
			@ApiResponse(code=4102,message="数据不存在")})
	@PostMapping("/update")
	public RestResult<SysPost> updatePost(
			@ApiParam(name="岗位对象",value="传入json格式",required=true)
			@RequestBody(required=true) SysPost post) {
		
		return objectService.updatePost(post);
	}
	
	@ApiOperation(value = "/delete",notes="删除岗位")
	@ApiResponses(value= {@ApiResponse(code=4102,message="数据不存在")})
	@GetMapping("/delete")
	public RestResult<SysPost> deletePost(
			@ApiParam(name="id",value="岗位id",required=true)
			@RequestParam(required=true) String id) {
		
		return objectService.deletePost(id);
	}
	

}
