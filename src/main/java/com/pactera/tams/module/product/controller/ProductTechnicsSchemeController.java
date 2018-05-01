package com.pactera.tams.module.product.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.pactera.tams.common.utils.*;
import com.pactera.tams.common.utils.excel.PruductTechnicsProcess;
import com.pactera.tams.module.product.model.ProductTechnicsScheme;
import com.pactera.tams.module.product.model.ProductTechnicsSchemeProcess;
import com.pactera.tams.module.product.model.ProductTechnicsSchemeWorkStep;
import com.pactera.tams.module.product.sevice.ProductTechnicsSchemeProcessService;
import com.pactera.tams.module.product.sevice.ProductTechnicsSchemeService;
import com.pactera.tams.module.product.sevice.ProductTechnicsSchemeWorkStepService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 产品工艺方案
 * @author ljp
 *2018.1.12
 */
@RestController
@RequestMapping("/productTechnicsScheme")
public class ProductTechnicsSchemeController {
	
	@Autowired
	ProductTechnicsSchemeService objectService;
	@Autowired
	ProductTechnicsSchemeProcessService processService;
	@Autowired
	ProductTechnicsSchemeWorkStepService workStepService;
	/**
	 * 转新增
	 */
	@ApiOperation(value = "/toSaveOrUpdate",notes="转新增产品工艺方案接口 ,返回结果 retCode: scheme:工艺方案  0=>系统异常，请联系管理员,1=>成功 ")
	@RequestMapping("/toSaveOrUpdate")
	public ModelMap toSaveOrUpdate(@RequestParam String id) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			ProductTechnicsScheme bean = objectService.getObjetById(id);
			result.put("msg", "查询成功");
			result.put("scheme", bean);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;
		}
		return result;
	}
	/**
	 * 产品工艺方案新增
	 */
	@ApiOperation(value = "/save",notes="新增产品工艺方案接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>保存成功 ,-2=>产品工艺方案已经存在")
	@RequestMapping("/save")
	public ModelMap saveProductTechnicsScheme(@RequestBody Map map,HttpServletRequest request) {
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = new ModelMap();
		try {
			ProductTechnicsScheme bean = new ProductTechnicsScheme();
			bean = MapToObjectFactory.map2Java(bean, map);
			ProductTechnicsScheme byBean = objectService.getObjectByBean(bean);
			if (null != byBean) {
				result.put("msg", "产品工艺方案已经存在");
				result.put("retCode", "-2");
				return result;
			}
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
	 * 产品工艺方案删除
	 */
	@ApiOperation(value = "/delete",notes="删除产品工艺方案接口,返回结果 retCode: 0=>系统异常，请联系管理员,1=>删除成功")
	@RequestMapping("/delete")
	public ModelMap deleteProductTechnicsScheme(@RequestParam String id) {
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
	 * 产品工艺方案修改
	 */
	@ApiOperation(value = "/update",notes="修改产品工艺方案接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>修改成功")
	@RequestMapping("/update")
	public ModelMap updateProduct(@RequestBody Map map,HttpServletRequest request) {
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = new ModelMap();
		try {
			ProductTechnicsScheme bean = new ProductTechnicsScheme();
			bean = MapToObjectFactory.map2Java(bean, map);
			if (map.containsKey("technics_pic")) {
				if (StringUtils.isEmpty(map.get("technics_pic").toString())) {
					bean.setTechnics_pic("");
				}
			}
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
	 * 产品工艺方案查询
	 */
	@ApiOperation(value = "/getList",notes="产品工艺方案查询接口,返回结果 list 方案列表 retCode: 0=>系统异常，请联系管理员,1=>查询成功")
	@RequestMapping("/getList")
	public ModelMap getList(@RequestBody Map map) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			ProductTechnicsScheme bean = new ProductTechnicsScheme();
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
	 * 产品工艺方案分页查询
	 */
	@ApiOperation(value = "/getPageList",notes="产品工艺方案查询接口,返回结果 list 方案列表 total 列表数 retCode: 0=>系统异常，请联系管理员,1=>查询成功")
	@RequestMapping("/getPageList")
	public ModelMap getPageList(@RequestBody Map map) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			ProductTechnicsScheme bean = new ProductTechnicsScheme();
			bean = MapToObjectFactory.map2Java(bean, map);
			List list = objectService.getPageList(bean);
			int total = objectService.getTotal(bean);
			result.put("msg", "查询成功");
			result.put("list", list);
			result.put("total", total);
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
	 * 产品工艺方案id查询工序,刀具清单
	 */
	@ApiOperation(value = "/getProcessList",notes="产品工艺方案工序查询接口，返回结果 list 方案列表 total 列表数 retCode: 0=>系统异常，请联系管理员,1=>查询成功")
	@RequestMapping("/getProcessList")
	public ModelMap getProcessList(@RequestParam String id) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			ProductTechnicsSchemeProcess bean = new ProductTechnicsSchemeProcess();
			bean.setTechnics_id(id);
			List processList = objectService.getProcessList(bean);
			int total = processService.getTotal(bean);
			List toolList = objectService.getSchemeToolList(bean);
			int size = processList.size();
			List rhythmList = objectService.getRhythmList(bean);
			result.put("rhythmList", rhythmList);
			result.put("toolList", toolList);
			result.put("size", size);
			result.put("msg", "查询成功");
			result.put("processList", processList);
			result.put("total", total);
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
	 * 下载
	 */
	@ApiOperation(value = "/downLoad",notes="下载模板")
	@RequestMapping("/downLoad")
	public ModelMap downLoad(HttpServletResponse response){
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			PruductTechnicsProcess process = new PruductTechnicsProcess();
			ArrayList<PruductTechnicsProcess> list = new ArrayList();
			list.add(process);
			Map mapMap = new HashMap<>() ; 
			ExportParams params1 = new ExportParams() ; 
			params1.setSheetName("sheet1"); 
	        Map dataMap1 = new HashMap<>();  
	        dataMap1.put("title",params1) ;  
	        dataMap1.put("entity",PruductTechnicsProcess.class);  
	        dataMap1.put("data",list) ;  
	        List sheetsList = new ArrayList<>() ;  
	        sheetsList.add(dataMap1);  
			ExportExcelUtils.exportExcel(sheetsList,response);
//			ProductTechnicsModel process = new ProductTechnicsModel();
//			ArrayList<ProductTechnicsModel> list = new ArrayList();
//			list.add(process);
//			Map mapMap = new HashMap<>() ; 
//			ExportParams params1 = new ExportParams() ; 
//			params1.setSheetName("sheet1"); 
//	        Map dataMap1 = new HashMap<>();  
//	        dataMap1.put("title",params1) ;  
//	        dataMap1.put("entity",ProductTechnicsModel.class);  
//	        dataMap1.put("data",list) ;  
//	        List sheetsList = new ArrayList<>() ;  
//	        sheetsList.add(dataMap1);  
//			ExportExcelUtils.exportExcel(sheetsList);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;
		}
		
		return result;
	}
	/**
	 * 导出
	 */
	@ApiOperation(value = "/excel",notes="工序工步导出")
	@RequestMapping("/excel")
	 public ModelMap excel(@RequestBody Map map,HttpServletResponse response){
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			ProductTechnicsSchemeProcess process = new ProductTechnicsSchemeProcess();
			process = MapToObjectFactory.map2Java(process, map);
			List<Map> list = objectService.getProcessList(process);
			List sheetsList = new ArrayList<>() ;  
			if (list.size()>0) {
				for (Map map1 : list) {
					ExportParams params = new ExportParams() ; 
					params.setSheetName(map1.get("step_no").toString());
					ProductTechnicsSchemeWorkStep step = new ProductTechnicsSchemeWorkStep();
					step.setScheme_id(map1.get("id").toString());
					List<ProductTechnicsSchemeWorkStep> list2 = workStepService.getList(step);
					List<PruductTechnicsProcess> list3 =new ArrayList<PruductTechnicsProcess>();
					for (ProductTechnicsSchemeWorkStep b : list2) {
						b.setType(b.getType().equals("0")? "工序":"工步");
						PruductTechnicsProcess technicsProcess = new PruductTechnicsProcess();
						technicsProcess=JavaBeanToJavaBean.convertBean(b, PruductTechnicsProcess.class);
						list3.add(technicsProcess);
					}
					Map dataMap1 = new HashMap<>();  
					dataMap1.put("title",params) ;  
					dataMap1.put("entity",PruductTechnicsProcess.class) ;  
					dataMap1.put("data",list3) ;  
					sheetsList.add(dataMap1);
				}
				ExportExcelUtils.exportExcel(sheetsList,response);
			}else {
				downLoad(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;
		}
		return result;
	}
	/**
	 * 工序工步导入
	 */
	@ApiOperation(value = "/importExcel",notes="工序工步导入")
	@RequestMapping("/importExcel")
	public ModelMap importExcel(@RequestBody Map map) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			String filePath = "C:/Users/Administrator/Desktop/test1.xlsx";
			List<List<PruductTechnicsProcess>> list = ExportExcelUtils.importExcel(filePath, 0, 1, PruductTechnicsProcess.class);
			for (List<PruductTechnicsProcess> list2 : list) {
				ProductTechnicsSchemeProcess schemeProcess = new ProductTechnicsSchemeProcess();
				schemeProcess=MapToObjectFactory.map2Java(schemeProcess, map);
				schemeProcess.setId(IdGenerator.uuid());
				processService.save(schemeProcess);
				for (PruductTechnicsProcess process : list2) {
					process.setType(process.getType().equals("工序")? "0":"1");
					ProductTechnicsSchemeWorkStep step = new ProductTechnicsSchemeWorkStep();
					step=JavaBeanToJavaBean.convertBean(process, ProductTechnicsSchemeWorkStep.class);
					step.setId(IdGenerator.uuid());
					step.setScheme_id(schemeProcess.getId());
					step.setTenant_id(schemeProcess.getTenant_id());
					workStepService.save(step);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;
		}
		return result;
	}
	/**
	 * 查询工艺列表
	 */
	@ApiOperation(value = "/getAllList",notes="工序工步导入")
	@RequestMapping("/getAllList")
	public ModelMap getAllList(@RequestBody Map map) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			ProductTechnicsScheme bean = new ProductTechnicsScheme();
			bean = MapToObjectFactory.map2Java(bean, map);
			List list = objectService.getAllList(bean);
			int total = objectService.getAllListCount(bean);
			result.put("list", list);
			result.put("total", total);
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
	 * 获取全部工艺
	 */
	@ApiOperation(value = "获取全部工艺",notes="获取全部工艺")
	@RequestMapping("/getAll")
	public List<ProductTechnicsScheme> getAll(@RequestBody Map map) {
		return objectService.getAll();
	}
}
