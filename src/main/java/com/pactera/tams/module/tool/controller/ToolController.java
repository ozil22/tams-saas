package com.pactera.tams.module.tool.controller;

import com.github.pagehelper.PageInfo;
import com.pactera.tams.common.utils.*;
import com.pactera.tams.module.machine.service.TSDictService;
import com.pactera.tams.module.tool.model.Tool;
import com.pactera.tams.module.tool.model.ToolCatalog;
import com.pactera.tams.module.tool.model.ToolCompose;
import com.pactera.tams.module.tool.sevice.ToolCatalogService;
import com.pactera.tams.module.tool.sevice.ToolComposeService;
import com.pactera.tams.module.tool.sevice.ToolService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 刀具
 * @author ztx
 *2018.1.12
 */
@RestController
@RequestMapping("/tool") 
public class ToolController {
	private Logger logger = LoggerFactory.getLogger(ToolController.class);
	@Autowired
	ToolService objectService;
	@Autowired
	ToolCatalogService catalogService;
	@Autowired
	ToolComposeService toolComposeService;
	@Autowired
	TSDictService dictionaryService; 
	/**
	 * 新增
	 */
	@ApiOperation(value = "/save",notes="新增刀具接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>保存成功 ,-2=>刀具已经存在")
	@RequestMapping("/save")
	public ModelMap saveTool(@RequestBody Map map,HttpServletRequest request) {
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = new ModelMap();
		try {
			Tool bean = new Tool();
			bean = MapToObjectFactory.map2Java(bean, map);
			//Tool tool = objectService.getObjectByBean(bean);
//			if (tool!=null) {
//				result.put("msg", "刀具已经存在");
//				result.put("retCode", "-2");
//				return result;
//			}
			if(bean==null||StringUtils.isNotEmpty(bean.getId())) {
				result.put("retCode", "-2");
				return result;
			}
			String specifications = bean.getSpecification();
			String specification = specifications.substring(0,specifications.indexOf(","));
			String spec_name = specifications.substring(specifications.indexOf(",") + 1);
			bean.setSpec_name(spec_name);
			bean.setSpecification(specification);
			bean.setId(IdGenerator.uuid());
			bean.setCreate_by(current_user_id);
			bean.setCreate_date(DateUtils.getCurrentTime());
			bean.setStatus("0");
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
	@ApiOperation(value = "/delete",notes="删除刀具目录接口,返回结果 retCode: 0=>系统异常，请联系管理员,1=>删除成功")
	@RequestMapping("/delete")
	public ModelMap deleteTool(@RequestParam String id) {
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
	@ApiOperation(value = "/update",notes="修改刀具目录接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>修改成功")
	@RequestMapping("/update")
	public ModelMap updateTool(@RequestBody Map map,HttpServletRequest request) {
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = new ModelMap();
		try {
			Tool bean = new Tool();
			bean = MapToObjectFactory.map2Java(bean, map);
			if (map.containsKey("product_pic")) {
				if (StringUtils.isEmpty(map.get("product_pic").toString())) {
					bean.setProduct_pic("");
				}
			}
			if (map.containsKey("patrs_pic")) {
				if (StringUtils.isEmpty(map.get("patrs_pic").toString())) {
					bean.setPatrs_pic("");
				}
			}
			if (map.containsKey("three_dimensional")) {
				if (StringUtils.isEmpty(map.get("three_dimensional").toString())) {
					bean.setThree_dimensional("");
				}
			}
			String specifications = bean.getSpecification();
			String specification = specifications.substring(0,specifications.indexOf(","));
			String spec_name = specifications.substring(specifications.indexOf(",") + 1);
			bean.setSpec_name(spec_name);
			bean.setSpecification(specification);
			bean.setUpdate_by(current_user_id);
			bean.setCreate_date(DateUtils.getCurrentTime());
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
	 * 批量修改
	 */
	@ApiOperation(value = "/updateMore",notes="批量修改接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>修改成功")
	@RequestMapping("/updateMore")
	public ModelMap updateMore(@RequestBody Map map,HttpServletRequest request) {
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = new ModelMap();
		try {
			Tool bean = new Tool();
			bean = MapToObjectFactory.map2Java(bean, map);
			String[] split = bean.getId().split(",");
			for (String string : split) {
				bean.setId(string);
				bean.setUpdate_by(current_user_id);
				bean.setUpdate_date(DateUtils.getCurrentTime());
				objectService.update(bean);
			}
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
	@ApiOperation(value = "/getList",notes="刀具查询接口")
	@RequestMapping("/getList")
	public ModelMap getList(@RequestBody Map map) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			StringBuffer sb = new StringBuffer();
			ToolCatalog catalog = new ToolCatalog();
			catalog=MapToObjectFactory.map2Java(catalog, map);
			if (null!=catalog.getId()&&""!=catalog.getId()) {
				List<ToolCatalog> pcatalogList=new ArrayList();
				catalogService.findChildCatalogs(pcatalogList,catalog.getId());
				for (int i = 0; i < pcatalogList.size(); i++) {
					ToolCatalog toolCatalog = pcatalogList.get(i);
					sb.append("'").append(toolCatalog.getId()).append("'");
					if(i+1 != pcatalogList.size()) {
						sb.append(",");
					}
				}
			}
			Tool bean = new Tool();
			bean = MapToObjectFactory.map2Java(bean, map);
			bean.setCatalog_id(sb.toString());
			List<Map<String, Object>> list = objectService.getList(bean);
			for (Map<String, Object> map2 : list) {
				StringBuffer buffer = new StringBuffer();
				String findChildCls = catalogService.findChildCls(buffer, map2.get("catalog_id").toString());
				String[] split = findChildCls.subSequence(1, findChildCls.length()).toString().split(",");
				map2.put("catalog_id", split);
			}
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
	 * 查询单条数据
	 */
	@ApiOperation(value = "/getListOne",notes="刀具单条查询接口")
	@RequestMapping("/getListOne")
	public ModelMap getListOne(@RequestParam String id) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			Tool tool = objectService.getObjectById(id);
			objectService.addClick(id);
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
	 * 
	 */
	@ApiOperation(value = "/getSmallToolId",notes="刀具单条查询接口")
	@RequestMapping("/getSmallToolId")
	public ModelMap getSmallToolId(@RequestParam String id) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			ToolCompose toolCompose = new ToolCompose();
			toolCompose.setTool_id(id);
			List<ToolCompose> smallList = toolComposeService.getSmallToolId(toolCompose);
			List smallIdList = new ArrayList();
			for(ToolCompose map:smallList){
				smallIdList.add(objectService.getObjectById(map.getSmall_tool_id()));
			}
			System.out.println(smallIdList);
			result.put("msg", "查询成功");
			result.put("smallIdList", smallIdList);

			objectService.addClick(id);
			//System.out.println(smallIdList);
			result.put("code", "1");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("code", "0");
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
			StringBuffer sb = new StringBuffer();
			ToolCatalog catalog = new ToolCatalog();
			catalog=MapToObjectFactory.map2Java(catalog, map);
			if (null!=catalog.getId()&&""!=catalog.getId()) {
				List<ToolCatalog> pcatalogList=new ArrayList();
				catalogService.findChildCatalogs(pcatalogList,catalog.getId());
				for (int i = 0; i < pcatalogList.size(); i++) {
					ToolCatalog toolCatalog = pcatalogList.get(i);
					sb.append("'").append(toolCatalog.getId()).append("'");
					if(i+1 != pcatalogList.size()) {
						sb.append(",");
					}
				}
			}			
			Tool bean = new Tool();
			bean = MapToObjectFactory.map2Java(bean, map);
			bean.setCatalog_id(sb.toString());
			List<Map<String, Object>> list = objectService.getPageList(bean);
			for (Map<String, Object> map2 : list) {
				StringBuffer buffer = new StringBuffer();
				String findChildCls = catalogService.findChildCls(buffer, map2.get("catalog_id").toString());
				String[] split = findChildCls.subSequence(1, findChildCls.length()).toString().split(",");
				map2.put("catalog_id", split);
			}
			List toolWayList = dictionaryService.getDictList(Constants.TOOL_WAY);
			List brandList = dictionaryService.getDictList(Constants.BRAND);
			Tool bean1 = new Tool();
			result.put("msg", "查询成功");
			result.put("list", new PageInfo<Map<String, Object>>(list));
			result.put("toolWayList", toolWayList);
			result.put("brandList", brandList);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;
		}
		return result;
	}
}
