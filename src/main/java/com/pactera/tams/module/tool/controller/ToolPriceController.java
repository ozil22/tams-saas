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

import com.pactera.tams.common.utils.DateUtils;
import com.pactera.tams.common.utils.IdGenerator;
import com.pactera.tams.common.utils.MapToObjectFactory;
import com.pactera.tams.common.utils.StringFormatUtils;
import com.pactera.tams.module.tool.model.Tool;
import com.pactera.tams.module.tool.model.ToolPrice;
import com.pactera.tams.module.tool.sevice.ToolPriceService;
import com.pactera.tams.module.tool.sevice.ToolService;

import io.swagger.annotations.ApiOperation;


/**
 * 刀具价格
 * @author js
 *2018.2.8
 */
@RestController
@RequestMapping("/toolPrice")
public class ToolPriceController {
	private Logger logger = LoggerFactory.getLogger(ToolPriceController.class);
	@Autowired
	ToolPriceService objectService;
	@Autowired
	ToolService toolService;
	/**
	 * 新增
	 */
	@ApiOperation(value = "/save",notes="新增刀具价格接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>保存成功 ,-2=>刀具价格已经存在")
	@RequestMapping("/save")
	public ModelMap saveToolPrice(@RequestBody Map map,HttpServletRequest request) {
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = new ModelMap();
		try {
			ToolPrice bean = new ToolPrice();
			String price = (String) map.get("tool_price");
			Float tool_price = Float.parseFloat(price);
			map.remove("tool_price");
			map.put("tool_price", price);
			bean = MapToObjectFactory.map2Java(bean, map);
			ToolPrice ToolPrice = objectService.getObjectByBean(bean);
			bean.setId(IdGenerator.uuid());
			bean.setCreate_by(current_user_id);
			bean.setCreate_date(DateUtils.getCurrentTime());
			objectService.save(bean);
			result.put("msg", "增加成功!");
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
	@ApiOperation(value = "/delete",notes="删除刀具价格目录接口,返回结果 retCode: 0=>系统异常，请联系管理员,1=>删除成功")
	@RequestMapping("/delete")
	public ModelMap deleteToolPrice(@RequestParam String id) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			objectService.delete(id);
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
	@ApiOperation(value = "/update",notes="修改刀具价格目录接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>修改成功")
	@RequestMapping("/update")
	public ModelMap updateToolPrice(@RequestBody Map map,HttpServletRequest request) {
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = new ModelMap();
		try {
			ToolPrice bean = new ToolPrice();
			String tool_price =  (String) map.get("tool_price");
			//Float price = (Float) map.get("tool_price");
			Float price = Float.parseFloat(tool_price);
			map.remove("tool_price");
			map.put("tool_price", price);
			bean = MapToObjectFactory.map2Java(bean, map);
//			bean.setTool_price(price);
			bean.setUpdate_by(current_user_id);
			objectService.update(bean);
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
	@ApiOperation(value = "/getList",notes="刀具价格查询接口")
	@RequestMapping("/getList")
	public ModelMap getList(@RequestBody Map map) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			ToolPrice bean = new ToolPrice();
			bean = MapToObjectFactory.map2Java(bean, map);
			List<Map<String, Object>> list = objectService.getList(bean);
			int total = objectService.getTotal(bean);
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
			ToolPrice bean = new ToolPrice();
			bean = MapToObjectFactory.map2Java(bean, map);
			List<Map<String, Object>> list = objectService.getPageList(bean);
			Tool tool = toolService.getObjectById(bean.getTool_id());
			int total = objectService.getTotal(bean);
			result.put("tool", tool);
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
