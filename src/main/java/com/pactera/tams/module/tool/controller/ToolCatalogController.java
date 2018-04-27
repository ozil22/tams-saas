package com.pactera.tams.module.tool.controller;
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
import com.pactera.tams.module.tool.model.Tool;
import com.pactera.tams.module.tool.model.ToolCatalog;
import com.pactera.tams.module.tool.sevice.ToolCatalogService;
import com.pactera.tams.module.tool.sevice.ToolService;

import io.swagger.annotations.ApiOperation;


/**
 * 刀具目录
 * @author ztx
 *2018.1.12
 */
@RestController
@RequestMapping("/toolCatalog")
public class ToolCatalogController {
	private Logger logger = LoggerFactory.getLogger(ToolCatalogController.class);
	
	@Autowired
	ToolCatalogService objectService;
	@Autowired
	UserService userService;
	@Autowired
	ToolService toolService;
	
	/**
	 * 新增
	 */
	@ApiOperation(value = "/save",notes="新增刀具目录接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>保存成功 ,-2=>刀具目录名称已经存在")
	@RequestMapping("/save")
	public ModelMap saveToolCatalog(@RequestBody Map map,HttpServletRequest request) {
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = new ModelMap();
		try {
			SysUser user = userService.getObjectById(current_user_id);
			ToolCatalog bean = new ToolCatalog();
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
	@ApiOperation(value = "/delete",notes="删除刀具目录接口,返回结果 retCode: 0=>系统异常，请联系管理员,1=>删除成功,-2=>当前刀具目录存在子目录，不能删除,-3=>当前刀具目录存在产品，不能删除")
	@RequestMapping("/delete")
	public ModelMap deleteToolCatalog(@RequestParam String id) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			//判断是否存在子目录
			boolean flag = objectService.isExistSubNode(id);
			if (flag) {
				result.put("msg", "当前刀具目录存在子目录，不能删除！");
				result.put("retCode", "-2");
				return result;
			}
			//判断是否存在刀具
			Tool tool = new Tool();
			tool.setCatalog_id(id);
			List list = toolService.getList(tool);
			if (list.size()>0) {
				result.put("msg", "当前刀具目录存在产品，不能删除");
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
	@ApiOperation(value = "/update",notes="修改刀具目录接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>修改成功 ,-2=>刀具目录名称已经存在")
	@RequestMapping("/update")
	public ModelMap updateToolCatalog(@RequestBody Map map,HttpServletRequest request) {
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = new ModelMap();
		try {
			ToolCatalog bean = new ToolCatalog();
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
	@ApiOperation(value = "/getListOne",notes="刀具目录单条查询接口")
	@RequestMapping("/getListOne")
	public ModelMap getListOne(@RequestParam String id) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			ToolCatalog catalog = objectService.getObjectById(id);
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
	@ApiOperation(value = "/getList",notes="刀具目录查询接口")
	@RequestMapping("/getList")
	public ModelMap getList(@RequestBody Map map) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			ToolCatalog bean = new ToolCatalog();
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
	 * 查询刀具目录或查询刀具
	 */
	@ApiOperation(value = "/getToolOrCatalog",notes="刀具目录下的刀具查询接口")
	@RequestMapping("/getToolOrCatalog")
	public ModelMap getToolOrCatalog(@RequestParam String id) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			List list=null;
			Tool tool = new Tool();
			tool.setCatalog_id(id);
			List toolList = toolService.getList(tool);
			ToolCatalog bean = new ToolCatalog();
			bean.setParent_id(id);
			List catalogList = objectService.getToolOrCatalog(bean);
			if (catalogList.size()>0) {
				list=catalogList;
				result.put("type", "0");
			}else if (toolList.size()>0) {
				list=toolList;
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
