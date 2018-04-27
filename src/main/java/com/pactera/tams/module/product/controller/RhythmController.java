package com.pactera.tams.module.product.controller;

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
import com.pactera.tams.module.product.model.ProductTechnicsSchemeProcess;
import com.pactera.tams.module.product.model.Rhythm;
import com.pactera.tams.module.product.sevice.ProductTechnicsSchemeService;
import com.pactera.tams.module.product.sevice.RhythmService;

import io.swagger.annotations.ApiOperation;

/**
 * 节拍汇总
 * @author ljp
 *
 */
@RestController
@RequestMapping("/rhythm")
public class RhythmController {

	private Logger logger = LoggerFactory.getLogger(RhythmController.class);
	@Autowired
	RhythmService rhythmService;
	@Autowired
	ProductTechnicsSchemeService productTechnicsSchemeService;
	
	/**
	 * 新增
	 */
	@ApiOperation(value = "/save",notes="新增接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>保存成功 ,-2=>产品已经存在")
	@RequestMapping("/save")
	public ModelMap saveProduct(@RequestBody Map map,HttpServletRequest request) {
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = new ModelMap();
		try {
			Rhythm bean=new Rhythm();
			bean = MapToObjectFactory.map2Java(bean, map);
			bean.setId(IdGenerator.uuid());
			bean.setCreate_by(current_user_id);
			bean.setCreate_date(DateUtils.getCurrentTime());
			rhythmService.save(bean);
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
	@ApiOperation(value = "/delete",notes="删除接口,返回结果 retCode: 0=>系统异常，请联系管理员,1=>删除成功")
	@RequestMapping("/delete")
	public ModelMap deleteProduct(@RequestParam String id) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			rhythmService.delete(id);
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
	@ApiOperation(value = "/update",notes="修改接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>修改成功")
	@RequestMapping("/update")
	public ModelMap updateProduct(@RequestBody Map map,HttpServletRequest request) {
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = new ModelMap();
		try {
			Rhythm bean=new Rhythm();
			bean = MapToObjectFactory.map2Java(bean, map);
			bean.setUpdate_by(current_user_id);
			bean.setUpdate_date(DateUtils.getCurrentTime());
			rhythmService.update(bean);
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
			Rhythm bean=new Rhythm();
			bean = MapToObjectFactory.map2Java(bean, map);
			List list = rhythmService.getList(bean);
			int total = rhythmService.getTotal(bean);
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
			Rhythm bean=new Rhythm();
			bean = MapToObjectFactory.map2Java(bean, map);
			List list = rhythmService.getPageList(bean);
			int total = rhythmService.getTotal(bean);
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
	 * saveorupdate
	 */
	@ApiOperation(value = "/toSaveOrUpdate",notes="转新增")
	@RequestMapping(value="/toSaveOrUpdate")
	public ModelMap toSaveOrUpdate(@RequestBody Map map) {	
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			Rhythm bean=new Rhythm();
			bean = MapToObjectFactory.map2Java(bean, map);
			Rhythm byBean = rhythmService.getByBean(bean);
			result.put("msg", "查询成功");
			result.put("rhythm", byBean);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;
		}
		return result;
	}
	/**
	 * 方案ID-工序
	 */
	@ApiOperation(value = "/processList",notes="工序列表")
	@RequestMapping(value="/processList")
	public ModelMap processList(@RequestBody Map map) {	
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			ProductTechnicsSchemeProcess bean=new ProductTechnicsSchemeProcess();
			bean=MapToObjectFactory.map2Java(bean, map);
			List list = productTechnicsSchemeService.getProcessList(bean);
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
