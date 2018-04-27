package com.pactera.tams.module.front.controller;

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

import com.pactera.tams.common.utils.MapToObjectFactory;
import com.pactera.tams.common.utils.StringFormatUtils;
import com.pactera.tams.module.product.model.Product;
import com.pactera.tams.module.product.model.ProductCatalog;
import com.pactera.tams.module.product.model.ProductTechnicsScheme;
import com.pactera.tams.module.product.model.ProductTechnicsSchemeProcess;
import com.pactera.tams.module.product.model.ProductTechnicsSchemeWorkStep;
import com.pactera.tams.module.product.sevice.ProductCatalogService;
import com.pactera.tams.module.product.sevice.ProductService;
import com.pactera.tams.module.product.sevice.ProductTechnicsSchemeProcessService;
import com.pactera.tams.module.product.sevice.ProductTechnicsSchemeService;
import com.pactera.tams.module.product.sevice.ProductTechnicsSchemeWorkStepService;

import io.swagger.annotations.ApiOperation;

/**
 * 前台产品
 * @author ljp
 *
 */
@RestController
@RequestMapping("/beforeProduct")
public class BeforeProductController {
	private Logger logger = LoggerFactory.getLogger(BeforeProductController.class);
	
	@Autowired
	ProductCatalogService productCatalogService;
	@Autowired
	ProductService productService;
	@Autowired
	ProductTechnicsSchemeService productTechnicsSchemeService;
	@Autowired
	ProductTechnicsSchemeProcessService processService;
	@Autowired
	ProductTechnicsSchemeWorkStepService workStepService;
	/**
	 * 产品目录查询
	 * @return
	 */	
	@ApiOperation(value = "/getProductCatalogList",notes="产品目录查询接口,返回结果 list产品目录列表 retCode: 0=>系统异常，请联系管理员,1=>查询成功 ")
	@RequestMapping("/getProductCatalogList")
	public ModelMap getProductCatalogList(@RequestBody Map map) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			ProductCatalog bean = new ProductCatalog();
			bean = MapToObjectFactory.map2Java(bean, map);
			List list = productCatalogService.getList(bean);
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
	 * 产品分页查询
	 */
	@ApiOperation(value = "/getProductPageList",notes="产品分页查询,返回结果 list,total产品数量,产品目录列表 retCode: 0=>系统异常，请联系管理员,1=>查询成功 ")
	@RequestMapping(value="/getProductPageList")
	public ModelMap getProductPageList(@RequestBody Map map) {	
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			StringBuffer sb = new StringBuffer();
			ProductCatalog catalog = new ProductCatalog();
			catalog=MapToObjectFactory.map2Java(catalog, map);
			if (null!=catalog.getId()&&""!=catalog.getId()) {
				List<ProductCatalog> pcatalogList=new ArrayList();
				productCatalogService.findChildCatalogs(pcatalogList,catalog.getId());
				for (int i = 0; i < pcatalogList.size(); i++) {
					ProductCatalog productCatalog = pcatalogList.get(i);
					sb.append("'").append(productCatalog.getId()).append("'");
					if(i+1 != pcatalogList.size()) {
						sb.append(",");
					}
				}
			}			
			Product bean = new Product();
			bean = MapToObjectFactory.map2Java(bean, map);
			bean.setCatalog_id(sb.toString());
			List<Map<String, Object>> list = productService.getPageList(bean);
			int total = productService.getTotal(bean);
			for (Map<String, Object> map2 : list) {
				StringBuffer buffer = new StringBuffer();
				String findChildCls = productCatalogService.findChildCls(buffer, map2.get("catalog_id").toString());
				String[] split = findChildCls.subSequence(1, findChildCls.length()).toString().split(",");
				map2.put("catalog_id", split);
			}

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
	 * 单个产品信息,该产品工艺方案查询
	 */
	@ApiOperation(value = "/getProductOne",notes="单个产品信息,该产品工艺方案查询,返回结果product产品信息 list方案列表,total方案数量,产品目录列表 retCode: 0=>系统异常，请联系管理员,1=>查询成功 ")
	@RequestMapping(value="/getProductOne")
	public ModelMap getProductOne(@RequestBody Map map) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			Product product = new Product();
			product = MapToObjectFactory.map2Java(product, map);
			Product productBean = productService.getObjectByBean(product);
			ProductTechnicsScheme scheme = new ProductTechnicsScheme();
			scheme.setProduct_id(productBean.getId());
			List list = productTechnicsSchemeService.getSchemeMessage(scheme);
			int total = productTechnicsSchemeService.getTotal(scheme);
			result.put("product", productBean);
			result.put("list", list);
			result.put("total", total);
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
	
	/**
	 * 根据方案id查询工序
	 */
	@ApiOperation(value = "/getProductProcess",notes="根据方案id查询工序查询,返回结果 retCode: 0=>系统异常，请联系管理员,1=>查询成功 ")
	@RequestMapping(value="/getProductProcess")
	public ModelMap getProductProcess(@RequestBody Map map) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			ProductTechnicsSchemeProcess process = new ProductTechnicsSchemeProcess();
			process=MapToObjectFactory.map2Java(process, map);
			List processList = productTechnicsSchemeService.getProcessList(process);
			int processtotal = processService.getTotal(process);
			result.put("processList", processList);
			result.put("processtotal", processtotal);
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
	
	/**
	 * 工序详情
	 */
	@ApiOperation(value = "/getProductWorkStep",notes="工序详情,返回结果 retCode: 0=>系统异常，请联系管理员,1=>查询成功 ")
	@RequestMapping(value="/getProductWorkStep")
	public ModelMap getProductWorkStep(@RequestBody Map map) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			ProductTechnicsSchemeProcess bean=new ProductTechnicsSchemeProcess();
			bean = MapToObjectFactory.map2Java(bean, map);
			ProductTechnicsSchemeProcess schemeProcess = processService.getObjectByBean(bean);
			ProductTechnicsSchemeWorkStep step = new ProductTechnicsSchemeWorkStep();
			step.setScheme_id(bean.getId());
			List list = workStepService.getList(step);
			result.put("list", list);
			result.put("schemeProcess", bean);
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
