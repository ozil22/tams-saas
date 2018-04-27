package com.pactera.tams.module.machine.controller;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.github.pagehelper.PageInfo;
import com.pactera.tams.common.utils.DateUtils;
import com.pactera.tams.common.utils.IdGenerator;
import com.pactera.tams.common.utils.MapToObjectFactory;
import com.pactera.tams.common.utils.StringFormatUtils;
import com.pactera.tams.module.machine.entity.MachineManagementEntity;
import com.pactera.tams.module.machine.entity.TSDict;
import com.pactera.tams.module.machine.service.MachineManagementService;
import com.pactera.tams.module.machine.service.TSDictService;

import io.swagger.annotations.ApiOperation;

/**
 * 机床管理
 * @author wsp
 *2018.2.24
 */
@RestController
@RequestMapping("/machineManagement")
public class MachineManagementController {
private Logger  logger = LoggerFactory.getLogger(MachineManagementController.class);
	
	@Autowired
	TSDictService 	tSDictService;
	@Autowired
	MachineManagementService  machineService;
	
	/**
	 * 新增
	 */
	@ApiOperation(value = "/save",notes="新增机床信息接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>保存成功 ,-2=>刀具已经存在")
	@RequestMapping("/save")
	public ModelMap saveTool(@RequestBody Map map,HttpServletRequest request) {
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = new ModelMap();
		try {
				
			MachineManagementEntity bean = new MachineManagementEntity();
			bean = MapToObjectFactory.map2Java(bean, map);
			if(bean==null||StringUtils.isNotEmpty(bean.getId())) {
				result.put("retCode", "-2");
				return result;
			}
			bean.setId(IdGenerator.uuid());
			bean.setCreateBy(current_user_id);
			bean.setCreateDate(DateUtils.getCurrentTime());
			//bean.setStatus("0");
			machineService.save(bean);
			//objectService.save(bean);
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
	@ApiOperation(value = "/delete",notes="删除机床信息接口,返回结果 retCode: 0=>系统异常，请联系管理员,1=>删除成功")
	@RequestMapping("/delete")
	public ModelMap deleteTool(@RequestParam String id) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			//objectService.delete(id);
			machineService.delete(id);
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
	@ApiOperation(value = "/update",notes="修改机床信息接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>修改成功")
	@RequestMapping("/update")
	public ModelMap updateTool(@RequestBody Map map,HttpServletRequest request) {
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = new ModelMap();
		try {
			MachineManagementEntity bean = new MachineManagementEntity();
			bean = MapToObjectFactory.map2Java(bean, map);
			if (map.containsKey("pic_path")) {
				if (StringUtils.isEmpty(map.get("pic_path").toString())) {
					bean.setPicPath("");
				}
			}
			bean.setUpdateBy(current_user_id);
			bean.setCreateDate(DateUtils.getCurrentTime());
			machineService.update(bean);
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
	@ApiOperation(value = "/getList",notes="机床信息查询接口")
	@RequestMapping("/getList")
	public ModelMap getListss(@RequestBody Map map) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			StringBuffer sb = new StringBuffer();
			MachineManagementEntity bean = new MachineManagementEntity();
			bean = MapToObjectFactory.map2Java(bean, map);
			List<Map<String, Object>> list = machineService.getList(bean);
			int total = machineService.getTotal(bean);
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
	 * 查询单条数据
	 */
	@ApiOperation(value = "/getListOne",notes="机床信息单条查询接口")
	@RequestMapping("/getListOne")
	public ModelMap getListOne(@RequestParam String id) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			//Tool tool = objectService.getObjectById(id);
			MachineManagementEntity tool = machineService.getObjectById(id);
			result.put("msg", "查询成功");
			result.put("tool", tool);
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
			MachineManagementEntity bean = new MachineManagementEntity();
			bean = MapToObjectFactory.map2Java(bean, map);
			List<Map<String, Object>> list = machineService.getPageList(bean);
			result.put("msg", "查询成功");
			result.put("list", new PageInfo<Map<String, Object>>(list));
			//result.put("toolWayList", toolWayList);
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
	@ApiOperation(value = "/getLists",notes="查询接口")
	@RequestMapping("/getLists")
	public ModelMap getListsss() {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			StringBuffer sb = new StringBuffer();
			MachineManagementEntity bean = new MachineManagementEntity();
			//bean = MapToObjectFactory.map2Java(bean, map);
			List<Map<String, Object>> list = machineService.getList(bean);
			int total = machineService.getTotal(bean);
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
	 * 查询
	 */
	@ApiOperation(value = "/getbrandList",notes="品牌查询接口")
	@RequestMapping("/getbrandList")
	public ModelMap getbrandList(@RequestBody Map map) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			//StringBuffer sb = new StringBuffer();
			TSDict bean = new TSDict();
			bean = MapToObjectFactory.map2Java(bean, map);
			//bean.setpCode("MACHINE_BRAND");
			List list = tSDictService.getList(bean);
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
	 * 查询
	 */
	@ApiOperation(value = "/getModeList",notes="型号查询接口")
	@RequestMapping("/getModeList")
	public ModelMap getModeList(@RequestBody Map map) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			TSDict bean = new TSDict();
			bean = MapToObjectFactory.map2Java(bean, map);
			//bean.setpCode("MACHINE_BRAND");
			List<TSDict> list = tSDictService.getList(bean);
			
			Map<String, List<TSDict>> maps = new HashMap();
			List<TSDict> allList= new ArrayList();
			
			for (TSDict tSDict : list) {
				TSDict beans = new TSDict();
				beans.setpCode(tSDict.getCode());
				List<TSDict> lists = tSDictService.getList(beans);
				maps.put(tSDict.getCode(), lists);
				for (TSDict tDict : lists) {
					allList.add(tDict);
				}
			}
			maps.put("allList", allList);
			result.put("msg", "查询成功");
			result.put("list", maps);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;
		}
		return result;
	}
}
