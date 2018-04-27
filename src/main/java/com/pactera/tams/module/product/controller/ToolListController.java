package com.pactera.tams.module.product.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pactera.tams.common.utils.DateUtils;
import com.pactera.tams.common.utils.IdGenerator;
import com.pactera.tams.common.utils.MapToObjectFactory;
import com.pactera.tams.common.utils.StringFormatUtils;
import com.pactera.tams.module.product.model.DetailList;
import com.pactera.tams.module.product.sevice.DetailListService;
import com.pactera.tams.module.tool.model.ToolCompose;
import com.pactera.tams.module.tool.sevice.ToolService;
 

import io.swagger.annotations.ApiOperation;

/**
 * 刀具清单
 * @author ljp
 *
 */
@RestController
@RequestMapping("/toolLists")
public class ToolListController {
	
	@Autowired
	DetailListService detailListService;
	@Autowired
	ToolService toolService;
	/**
	 * 刀具带出查询
	 */
	@ApiOperation(value = "/getToolLists",notes="新增刀具清单接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>保存成功 ,-2=>产品已经存在")
	@RequestMapping("/getToolLists")
	public ModelMap getToolList(@RequestBody Map map) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			 ToolCompose bean = new ToolCompose();
			 bean= MapToObjectFactory.map2Java(bean, map);
			 List list = detailListService.getToolList(bean);
			 if (list.size() == 0) {
				 List toolOne = detailListService.getToolOne(bean);
				 list.add(toolOne.get(0));
				 
			}
			 result.put("list", list);
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
	 * 刀具清单保存
	 */
	@ApiOperation(value = "/save",notes="新增刀具清单接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>保存成功 ,-2=>产品已经存在")
	@RequestMapping("/save")
	public ModelMap saveToolList(@RequestBody Map map,HttpServletRequest request) {
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			DetailList bean = new DetailList();
			bean=MapToObjectFactory.map2Java(bean, map);
			bean.setCreate_by(current_user_id);
			bean.setId(IdGenerator.uuid());
			bean.setCreate_date(DateUtils.getCurrentTime());
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
	@ApiOperation(value = "/delete",notes="删除刀具清单接口,返回结果 retCode: 0=>系统异常，请联系管理员,1=>删除成功")
	@RequestMapping("/delete")
	public ModelMap deleteToolList(@RequestBody Map map) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			DetailList bean = new DetailList();
			bean=MapToObjectFactory.map2Java(bean, map);
			detailListService.delete(bean);
			result.put("msg", "删除成功!");
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
	 */
	@ApiOperation(value = "/update",notes="修改刀具清单接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>修改成功")
	@RequestMapping("/update")
	public ModelMap updateProduct(@RequestBody Map map,HttpServletRequest request) {
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			DetailList bean = new DetailList();
			bean=MapToObjectFactory.map2Java(bean, map);
			bean.setUpdate_by(current_user_id);
			bean.setUpdate_date(DateUtils.getCurrentTime());
			detailListService.update(bean);
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
	 * 查询
	 */
	@ApiOperation(value = "/getList",notes="产品查询接口")
	@RequestMapping("/getList")
	public ModelMap getList(@RequestBody Map map) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			DetailList bean = new DetailList();
			bean=MapToObjectFactory.map2Java(bean, map);
			List list = detailListService.getList(bean);
			int total = detailListService.getTotal(bean);
			result.put("msg", "查询成功");
			result.put("list", list);
			result.put("total", total);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;
		}
		return result;
	}
	/**
	 * 分页查询
	 */
	@ApiOperation(value = "/getPageList",notes="分页查询")
	@RequestMapping(value="/getPageList")
	public ModelMap getPageList(@RequestBody Map map) {	
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			DetailList bean = new DetailList();
			bean=MapToObjectFactory.map2Java(bean, map);
			List list = detailListService.getPageList(bean);
			int total = detailListService.getTotal(bean);
			result.put("msg", "查询成功");
			result.put("list", list);
			result.put("total", total);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;
		}
		return result;
	}
}
