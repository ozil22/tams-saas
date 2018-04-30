package com.pactera.tams.module.product.controller;

import com.pactera.tams.common.utils.DateUtils;
import com.pactera.tams.common.utils.IdGenerator;
import com.pactera.tams.common.utils.MapToObjectFactory;
import com.pactera.tams.common.utils.StringFormatUtils;
import com.pactera.tams.module.product.model.Product;
import com.pactera.tams.module.product.model.ProductCatalog;
import com.pactera.tams.module.product.sevice.ProductCatalogService;
import com.pactera.tams.module.product.sevice.ProductService;
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
 * 产品
 * @author ljp
 *2018.1.2
 */
@RestController
@RequestMapping("/product") 
public class ProductController {
	private Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	ProductService objectService;
	@Autowired
	ProductCatalogService catalogService;
	/**
	 * 新增
	 */
	@ApiOperation(value = "/save",notes="新增产品接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>保存成功 ,-2=>产品已经存在")
	@RequestMapping("/save")
	public ModelMap saveProduct(@RequestBody Map map,HttpServletRequest request) {
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = new ModelMap();
		try {
			Product bean = new Product();
			bean = MapToObjectFactory.map2Java(bean, map);
			Product product = objectService.getObjectByBean(bean);
			if (product!=null) {
				result.put("msg", "产品已经存在");
				result.put("retCode", "-2");
				return result;
			}
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
	@ApiOperation(value = "/delete",notes="删除产品目录接口,返回结果 retCode: 0=>系统异常，请联系管理员,1=>删除成功")
	@RequestMapping("/delete")
	public ModelMap deleteProduct(@RequestParam String id) {
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
	@ApiOperation(value = "/update",notes="修改产品目录接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>修改成功")
	@RequestMapping("/update")
	public ModelMap updateProduct(@RequestBody Map map,HttpServletRequest request) {
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = new ModelMap();
		try {
			Product bean = new Product();
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
			bean.setUpdate_by(current_user_id);
			bean.setUpdate_date(DateUtils.getCurrentTime());
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
			Product bean = new Product();
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
			StringBuffer sb = new StringBuffer();
			ProductCatalog catalog = new ProductCatalog();
			catalog=MapToObjectFactory.map2Java(catalog, map);
			if (null!=catalog.getId()&&""!=catalog.getId()) {
				List<ProductCatalog> pcatalogList=new ArrayList();
				catalogService.findChildCatalogs(pcatalogList,catalog.getId());
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
	 * 分页查询
	 */
	@ApiOperation(value = "/getPageList",notes="分页查询")
	@RequestMapping(value="/getPageList")
	public ModelMap getPageList(@RequestBody Map map) {	
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			StringBuffer sb = new StringBuffer();
			ProductCatalog catalog = new ProductCatalog();
			catalog=MapToObjectFactory.map2Java(catalog, map);
			if (null!=catalog.getId()&&""!=catalog.getId()) {
				List<ProductCatalog> pcatalogList=new ArrayList();
				catalogService.findChildCatalogs(pcatalogList,catalog.getId());
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
			List<Map<String, Object>> list = objectService.getPageList(bean);
			int total = objectService.getTotal(bean);
			for (Map<String, Object> map2 : list) {
				StringBuffer buffer = new StringBuffer();
				String findChildCls = catalogService.findChildCls(buffer, map2.get("catalog_id").toString());
				String[] split = findChildCls.subSequence(1, findChildCls.length()).toString().split(",");
				map2.put("catalog_id", split);
			}

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
	@ApiOperation(value = "/getListOne",notes="产品单条查询接口")
	@RequestMapping("/getListOne")
	public ModelMap getListOne(@RequestParam String id) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			Product product = objectService.getObjectById(id);
			objectService.addClick(id);
			result.put("msg", "查询成功");
			result.put("product", product);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;
		}
		return result;
	}

	/**
	 * 获取全部产品
	 */
	@ApiOperation(value = "获取全部产品",notes="获取全部产品")
	@RequestMapping("/getAll")
	public List<Product> getAll(@RequestParam String id) {

		return objectService.getAll();
	}
}
