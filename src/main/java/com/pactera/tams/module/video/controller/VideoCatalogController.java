package com.pactera.tams.module.video.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pactera.tams.common.utils.MapToObjectFactory;
import com.pactera.tams.common.utils.StringFormatUtils;
import com.pactera.tams.module.system.entity.SysUser;
import com.pactera.tams.module.system.service.UserService;
import com.pactera.tams.module.video.model.Video;
import com.pactera.tams.module.video.model.VideoCatalog;
import com.pactera.tams.module.video.service.VideoCatalogService;
import com.pactera.tams.module.video.service.VideoService;

import io.swagger.annotations.ApiOperation;


/**
 * 视频目录
 * @author ztx
 *2018.1.9
 */
@RestController
@RequestMapping("/videoCatalog")
public class VideoCatalogController {
	private Logger logger = LoggerFactory.getLogger(VideoCatalogController.class);
	
	@Autowired
	VideoCatalogService objectService;
	@Autowired
	UserService userService;
	@Autowired
	VideoService videoService;
	
	/**
	 * 新增
	 */
	@ApiOperation(value = "/save",notes="新增视频目录接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>保存成功 ,-2=>视频目录名称已经存在")
	@RequestMapping("/save")
	public ModelMap saveVideoCatalog(@RequestBody Map map,HttpServletRequest request) {
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = new ModelMap();
		try {
			SysUser user = userService.getObjectById(current_user_id);
			VideoCatalog bean = new VideoCatalog();
			bean = MapToObjectFactory.map2Java(bean, map);
			bean.setCreate_by(current_user_id);
			bean.setTenant_id(user.getTenantId());
			result=objectService.saveOrUpdate(bean);
			result.put("retCode", "1");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;
		}
		return result;
	}
	/**
	 * 删除
	 */
	@ApiOperation(value = "/delete",notes="删除视频目录接口,返回结果 retCode: 0=>系统异常，请联系管理员,1=>删除成功,-2=>当前视频目录存在子目录，不能删除,-3=>当前视频目录存在产品，不能删除")
	@RequestMapping("/delete")
	public ModelMap deleteVideoCatalog(@RequestParam String id) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			//判断是否存在子目录
			boolean flag = objectService.isExistSubNode(id);
			if (flag) {
				result.put("msg", "当前视频目录存在子目录，不能删除！");
				result.put("retCode", "-2");
				return result;
			}
			//判断是否存在产品
			Video video = new Video();
			video.setCatalog_id(id);
			List list = videoService.getList(video);
			if (list.size()>0) {
				result.put("msg", "当前视频目录存在产品，不能删除");
				result.put("retCode", "-3");
				return result;
			}
			objectService.delete(id);
			result.put("msg", "删除成功");
			result.put("retCode", "1");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;
		}
		return result;
	}
	/**
	 * 修改
	 * @param map
	 * @return
	 */
	@ApiOperation(value = "/update",notes="修改视频目录接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>修改成功 ,-2=>视频目录名称已经存在")
	@RequestMapping("/update")
	public ModelMap updateVideoCatalog(@RequestBody Map map,HttpServletRequest request) {
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = new ModelMap();
		try {
			VideoCatalog bean = new VideoCatalog();
			bean = MapToObjectFactory.map2Java(bean, map);
			bean.setUpdate_by(current_user_id);
			result=objectService.saveOrUpdate(bean);
			result.put("msg", "修改成功!");
			result.put("retCode", "1");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;
		}
		return result;
	}
	/**
	 * 查询单条目录
	 */
	@ApiOperation(value = "/getListOne",notes="视频目录单条查询接口")
	@RequestMapping("/getListOne")
	public ModelMap getListOne(@RequestParam String id) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			VideoCatalog catalog = objectService.getObjectById(id);
			result.put("msg", "查询成功");
			result.put("list", catalog);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;
		}
		return result;
	}
	/**
	 * 查询
	 * @return
	 */	
	@ApiOperation(value = "/getList",notes="视频目录查询接口")
	@RequestMapping("/getList")
	public ModelMap getList(@RequestBody Map map) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			VideoCatalog bean = new VideoCatalog();
			bean = MapToObjectFactory.map2Java(bean, map);
			List list = objectService.getList(bean);
			result.put("msg", "查询成功");
			result.put("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;
		}
		return result;
	}
	/**
	 * 查询视频目录或查询视频
	 */
	@ApiOperation(value = "/getVideoOrCatalog",notes="视频目录下的产品查询接口")
	@RequestMapping("/getVideoOrCatalog")
	public ModelMap getVideoOrCatalog(@RequestParam String id) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			List list=null;
			Video video = new Video();
			video.setCatalog_id(id);
			List videoList = videoService.getList(video);
			VideoCatalog bean = new VideoCatalog();
			bean.setParent_id(id);
			List catalogList = objectService.getVideoOrCatalog(bean);
			if (catalogList.size()>0) {
				list=catalogList;
				result.put("type", "0");
			}else if (videoList.size()>0) {
				list=videoList;
				result.put("type", "1");
			}
			result.put("msg", "查询成功");
			result.put("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;
		}
		return result;
	}
}
