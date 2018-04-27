package com.pactera.tams.module.feedback.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.pactera.tams.common.utils.StringFormatUtils;
import com.pactera.tams.module.feedback.model.Feedback;
import com.pactera.tams.module.feedback.service.FeedbackService;
import com.pactera.tams.module.product.model.Product;
import com.pactera.tams.module.product.model.ProductTechnicsScheme;
import com.pactera.tams.module.product.model.ProductTechnicsSchemeProcess;
import com.pactera.tams.module.product.model.ProductTechnicsSchemeWorkStep;
import com.pactera.tams.module.product.sevice.ProductService;
import com.pactera.tams.module.product.sevice.ProductTechnicsSchemeService;
import com.pactera.tams.module.product.sevice.ProductTechnicsSchemeWorkStepService;

import io.swagger.annotations.ApiOperation;


/**
* 用户反馈Controller
* @Author: mjh
* @Date: 2018-01-31 10:51:38
*/
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
	private Logger logger = LoggerFactory.getLogger(FeedbackController.class);
	@Autowired
	FeedbackService objectService; 
	@Autowired
	ProductService productService;
	@Autowired
	ProductTechnicsSchemeService productTechnicsSchemeService;
	@Autowired
	ProductTechnicsSchemeWorkStepService productTechnicsSchemeWorkStepService;
	
	/**
	 * 新增
	 * @param 
	 * @return
	 */
	@ApiOperation(value = "/save",notes="新增用户反馈接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>保存成功 ,-2=>用户反馈名称已经存在")
	@RequestMapping("/save")
	public ModelMap saveFeedback(@RequestBody Feedback bean,HttpServletRequest request) {
		String currentUserId = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = new ModelMap();
		try {
/*			Map map = new HashMap<>();
			Feedback bean = new Feedback();
			bean = MapToObjectFactory.map2Java(bean, map);	*/ 
			bean.setCreate_by(currentUserId);
			result =  objectService.saveOrUpdate(bean);
		}catch(Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;
		}
		return result;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "/delete",notes="删除用户反馈接口,返回结果 retCode: 0=>系统异常，请联系管理员,1=>删除成功")
	@RequestMapping("/delete")
	public ModelMap deleteFeedback(@RequestParam String id) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			objectService.delete(id);
			result.put("msg", "删除成功");
		}catch(Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;
		}
		return result;
	}
	
	/**
	 * 修改
	 * @param map
	 * @return
	 */
	@ApiOperation(value = "/update",notes="修改用户反馈接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>修改成功 ,-2=>用户反馈名称已经存在")
	@RequestMapping("/update")
	public ModelMap updateFeedback(@RequestBody Feedback bean,HttpServletRequest request) {
		String currentUserId = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = new ModelMap();
		try {
//			Feedback bean = new Feedback();
//			bean = MapToObjectFactory.map2Java(bean, map);	 
			bean.setUpdate_by(currentUserId);
			result =  objectService.saveOrUpdate(bean);
		}catch(Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;
		}
		return result;
	}
 
 
	/**
	 * 查询
	 * @return
	 */
	
	@ApiOperation(value = "/getList",notes="用户反馈查询接口")
	@RequestMapping("/getList")
	public ModelMap queryList(@RequestBody Feedback bean) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
//			Feedback bean = new Feedback();
//			bean = MapToObjectFactory.map2Java(bean, map);	
			List list = objectService.getList(bean);
			result.put("msg", "查詢成功");
			result.put("list", list);
		}catch(Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;
		}
		return result;
	}
	

	/**
	 * 分页查询
	 * @param bean
	 * @return
	 */
	@ApiOperation(value = "/getPageList",notes="分页查询")
	@RequestMapping("/getPageList")
	public ModelMap getPageList(@RequestBody Feedback bean) {
		ModelMap result = StringFormatUtils.getResultMessage();  
		try {
//			Feedback bean = new Feedback();		
//			bean = (Feedback)MapToObjectFactory.map2Java(bean, map);
			List list = objectService.getPageList(bean);
			result.put("list", new PageInfo<Feedback>(list));
			result.put("msg", "查询成功");
		}catch(Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;
		}
		return result;
	}
 
	/**
	 * 单个查询
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "/get",notes="获取用户反馈 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>成功 ")
	@RequestMapping(value ="/get", method = RequestMethod.GET)
	public ModelMap get(@RequestParam String id) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			Feedback bean = objectService.getObjectById(id);
			result.put("msg", "查询成功");
			result.put("resultdata", bean);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			logger.error("查询用户反馈失败：{}"+e.getMessage());
			return result;
		}
		return result;
	}
	
	/**
	 * 反馈界面产品
	 */
	@ApiOperation(value = "/getProductList",notes="产品查询")
	@RequestMapping("/getProductList")
	public ModelMap getProductList(@RequestBody Product bean) {
		ModelMap result = StringFormatUtils.getResultMessage();  
		try {
			List list = productService.getList(bean);
			int total = productService.getTotal(bean);
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
	 * 反馈界面产品方案
	 */
	@ApiOperation(value = "/getProductSchemeList",notes="产品方案查询")
	@RequestMapping("/getProductSchemeList")
	public ModelMap getProductSchemeList(@RequestBody ProductTechnicsScheme bean) {
		ModelMap result = StringFormatUtils.getResultMessage();  
		try {
			List list = productTechnicsSchemeService.getList(bean);
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
	 * 反馈界面产品方案工序
	 */
	@ApiOperation(value = "/getProductProcessList",notes="产品方案工序查询")
	@RequestMapping("/getProductProcessList")
	public ModelMap getProductProcessList(@RequestBody ProductTechnicsSchemeProcess bean) {
		ModelMap result = StringFormatUtils.getResultMessage();  
		try {
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
	/**
	 * 反馈界面产品方案工序工步
	 */
	@ApiOperation(value = "/getProductWorkStepList",notes="产品方案工序工步查询")
	@RequestMapping("/getProductWorkStepList")
	public ModelMap getProductWorkStepList(@RequestBody ProductTechnicsSchemeWorkStep bean) {
		ModelMap result = StringFormatUtils.getResultMessage();  
		try {
			List list = productTechnicsSchemeWorkStepService.getList(bean);
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
	 /** 分页查询2
	 * @param bean
	 * @return
	 */
	@ApiOperation(value = "/getFeedPageList",notes="分页查询")
	@RequestMapping("/getFeedPageList")
	public ModelMap getFeedPageList(@RequestBody Feedback bean) {
		ModelMap result = StringFormatUtils.getResultMessage();  
		try {
			List list = objectService.getFeedPageList(bean);
			result.put("list", new PageInfo<Feedback>(list));
			result.put("msg", "查询成功");
		}catch(Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;
		}
		return result;
	}
	/** 编辑
	 * @param bean
	 * @return
	 */
	@ApiOperation(value = "/getFeedOne",notes="编辑查询")
	@RequestMapping("/getFeedOne")
	public ModelMap getFeedOne(@RequestBody Feedback bean) {
		ModelMap result = StringFormatUtils.getResultMessage();  
		try {
			Feedback feedback = objectService.getObjectByBean(bean);
			result.put("resultdata", feedback);
			result.put("msg", "查询成功");
		}catch(Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;
		}
		return result;
	}
}
