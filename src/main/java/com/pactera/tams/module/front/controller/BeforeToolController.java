package com.pactera.tams.module.front.controller;
/**
 * 前台页面刀具
 * @author ljp
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.pactera.tams.common.utils.MapToObjectFactory;
import com.pactera.tams.common.utils.StringFormatUtils;
import com.pactera.tams.module.tool.model.Tool;
import com.pactera.tams.module.tool.model.ToolCatalog;
import com.pactera.tams.module.tool.model.ToolCompose;
import com.pactera.tams.module.tool.sevice.ToolCatalogService;
import com.pactera.tams.module.tool.sevice.ToolComposeService;
import com.pactera.tams.module.tool.sevice.ToolService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/beforeTool")
public class BeforeToolController {
	private Logger logger = LoggerFactory.getLogger(BeforeToolController.class);
	
	@Autowired
	ToolCatalogService toolCatalogService;
	@Autowired
	ToolService toolService;
	@Autowired
	ToolComposeService toolComposeService;
	
	/**
	 * 刀具目录
	 */
	@ApiOperation(value = "/getToolCatalogList",notes="刀具目录查询接口")
	@RequestMapping("/getToolCatalogList")
	public ModelMap getToolCatalogList(@RequestBody Map map) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			ToolCatalog bean = new ToolCatalog();
			bean = MapToObjectFactory.map2Java(bean, map);
			List list = toolCatalogService.getList(bean);
			result.put("msg", "查询成功");
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
	 * 刀具分页查询
	 */
	@ApiOperation(value = "/getToolPageList",notes="分页查询")
	@RequestMapping(value="/getToolPageList")
	public ModelMap getToolPageList(@RequestBody Map map) {	
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			StringBuffer sb = new StringBuffer();
			ToolCatalog catalog = new ToolCatalog();
			catalog=MapToObjectFactory.map2Java(catalog, map);
			if (null!=catalog.getId()&&""!=catalog.getId()) {
				List<ToolCatalog> pcatalogList=new ArrayList();
				toolCatalogService.findChildCatalogs(pcatalogList,catalog.getId());
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
			List<Map<String, Object>> list = toolService.getPageList(bean);
			for (Map<String, Object> map2 : list) {
				StringBuffer buffer = new StringBuffer();
				String findChildCls = toolCatalogService.findChildCls(buffer, map2.get("catalog_id").toString());
				String[] split = findChildCls.subSequence(1, findChildCls.length()).toString().split(",");
				map2.put("catalog_id", split);
			}
			Tool bean1 = new Tool();
			result.put("msg", "查询成功");
			result.put("list", new PageInfo<Map<String, Object>>(list));
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;
		}
		return result;
	}
	
	/**
	 * 刀具主页详情
	 */
	@ApiOperation(value = "/getToolOne",notes="单个刀具信息,返回结果retCode: 0=>系统异常，请联系管理员,1=>查询成功 ")
	@RequestMapping(value="/getToolOne")
	public ModelMap getToolOne(@RequestBody Map map) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			Tool tool = new Tool();
			tool = MapToObjectFactory.map2Java(tool, map);
			Tool toolBean = toolService.getObjectByBean(tool);
			ToolCompose toolCompose = new ToolCompose();
			toolCompose.setTool_id(toolBean.getId());
			List smallIdList = new ArrayList();
			List<Map<String, String>> list = toolComposeService.getList(toolCompose);
			for (Map<String, String> map2 : list) {
				smallIdList.add(toolService.getObjectById(map2.get("small_tool_id")));
			}
			result.put("tool", toolBean);
			result.put("smallIdList", smallIdList);
			result.put("msg", "查询成功");
			result.put("retCode", "1");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;
		}
		return result;
	}
}
