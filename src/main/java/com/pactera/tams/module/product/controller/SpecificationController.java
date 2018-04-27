package com.pactera.tams.module.product.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import com.pactera.tams.module.product.model.Specification;
import com.pactera.tams.module.product.sevice.SpecificationService;

import io.swagger.annotations.ApiOperation;

/**
 * 规格
 * @author js
 *2018.2.6
 */
@RestController
@RequestMapping("/specification") 
public class SpecificationController {
	private Logger logger = LoggerFactory.getLogger(SpecificationController.class);
	@Autowired
	SpecificationService objectService;
	
	/**
	 * 新增
	 */
	@ApiOperation(value = "/save",notes="新增规格接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>保存成功 ,-2=>规格已经存在")
	@RequestMapping("/save")
	public ModelMap saveSpecification(@RequestBody Map map,HttpServletRequest request) {
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = new ModelMap();
		try {
			Specification bean = new Specification();
			bean = MapToObjectFactory.map2Java(bean, map);
			Specification specification = objectService.getObjectByBean(bean);
			/*if (bean!=null) {
				result.put("msg", "规格已经存在");
				result.put("retCode", "-2");
				return result;
			}*/
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
	@ApiOperation(value = "/delete",notes="删除规格目录接口,返回结果 retCode: 0=>系统异常，请联系管理员,1=>删除成功")
	@RequestMapping("/delete")
	public ModelMap deleteSpecification(@RequestParam String id) {
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
	@ApiOperation(value = "/update",notes="修改规格目录接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>修改成功")
	@RequestMapping("/update")
	public ModelMap updateSpecification(@RequestBody Map map,HttpServletRequest request) {
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = new ModelMap();
		try {
			Specification bean = new Specification();
			bean = MapToObjectFactory.map2Java(bean, map);
			if (map.containsKey("pic_path")) {
				if (StringUtils.isEmpty(map.get("pic_path").toString())) {
					bean.setpic_path("");
				}
			}
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
	@ApiOperation(value = "/getList",notes="规格查询接口")
	@RequestMapping("/getList")
	public ModelMap getList(@RequestBody Map map) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			Specification bean = new Specification();
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
			Specification bean = new Specification();
			bean = MapToObjectFactory.map2Java(bean, map);
			List<Map<String, Object>> list = objectService.getPageList(bean);
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
	
}
