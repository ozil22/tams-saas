package com.pactera.tams.module.product.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import com.pactera.tams.module.feedback.model.Feedback;
import com.pactera.tams.module.feedback.service.FeedbackService;
import com.pactera.tams.module.product.model.DetailList;
import com.pactera.tams.module.product.model.ProductTechnicsSchemeProcess;
import com.pactera.tams.module.product.model.ProductTechnicsSchemeWorkStep;
import com.pactera.tams.module.product.sevice.DetailListService;
import com.pactera.tams.module.product.sevice.ProductTechnicsSchemeProcessService;
import com.pactera.tams.module.product.sevice.ProductTechnicsSchemeWorkStepService;
import com.pactera.tams.module.video.model.Video;
import com.pactera.tams.module.video.service.VideoService;

import io.swagger.annotations.ApiOperation;

/**
 * 产品工艺方案工序
 * @author ljp
 *2018.1.12
 */
@RestController
@RequestMapping("/productTechnicsSchemeProcess")
public class ProductTechnicsSchemeProcessController {
	
	@Autowired
	ProductTechnicsSchemeProcessService objectService;
	@Autowired
	ProductTechnicsSchemeWorkStepService workStepService;
	@Autowired
	DetailListService detailListService;
	@Autowired
	FeedbackService feedbackService;
	@Autowired
	VideoService videoService;
	
	/**
	 * 产品工艺方案工序新增
	 */
	@ApiOperation(value = "/save",notes="新增产品工艺方案工序接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>保存成功 ,-2=>产品工艺方案工序已经存在")
	@RequestMapping("/save")
	public ModelMap saveProductTechnicsProcessScheme(@RequestBody Map map,HttpServletRequest request) {
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = new ModelMap();
		try {
			ProductTechnicsSchemeProcess bean = new ProductTechnicsSchemeProcess();
			bean = MapToObjectFactory.map2Java(bean, map);
			ProductTechnicsSchemeProcess byBean = objectService.getObjectByBean(bean);
			if (null != byBean) {
				result.put("msg", "产品工艺方案工序已经存在");
				result.put("retCode", "-2");
				return result;
			}
			bean.setId(IdGenerator.uuid());
			bean.setCreate_by(current_user_id);
			bean.setCreate_date(DateUtils.getCurrentTime());
			objectService.save(bean);
			ProductTechnicsSchemeWorkStep workStep = new ProductTechnicsSchemeWorkStep();
			workStep=MapToObjectFactory.map2Java(workStep, map);
			workStep.setType("0");
			workStep.setTenant_id(bean.getTenant_id());
			workStep.setId(IdGenerator.uuid());
			workStep.setCreate_by(current_user_id);
			workStep.setScheme_id(bean.getId());
			workStepService.save(workStep);
//			List<Map> list = bean.getProcess_relation_table();
//			for (Map map2 : list) {
//				ProductTechnicsSchemeWorkStep workStep = new ProductTechnicsSchemeWorkStep();
//				workStep=MapToObjectFactory.map2Java(workStep, map2);
//				workStep.setTenant_id(bean.getTenant_id());
//				workStep.setId(Until.getStringUUID());
//				workStep.setCreate_by(current_user_id);
//				workStep.setScheme_id(bean.getId());
//				workStepService.save(workStep);
//			}
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
	 * 产品工艺方案工序删除
	 */
	@ApiOperation(value = "/delete",notes="删除产品工艺方案工序接口,返回结果 retCode: 0=>系统异常，请联系管理员,1=>删除成功")
	@RequestMapping("/delete")
	public ModelMap deleteProductTechnicsSchemeProcess(@RequestParam String id) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			objectService.delete(id);
			ProductTechnicsSchemeWorkStep bean = new ProductTechnicsSchemeWorkStep();
			bean.setScheme_id(id);
			workStepService.delete(bean);
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
	 * 产品工艺方案工序修改
	 */
	@ApiOperation(value = "/update",notes="修改产品工艺方案工序接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>修改成功")
	@RequestMapping("/update")
	public ModelMap updateProductTechnicsSchemeProcess(@RequestBody Map map,HttpServletRequest request) {
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = new ModelMap();
		try {
			ProductTechnicsSchemeProcess bean = new ProductTechnicsSchemeProcess();
			bean = MapToObjectFactory.map2Java(bean, map);
			if (map.containsKey("scheme_pic")) {
				if (StringUtils.isEmpty(map.get("scheme_pic").toString())) {
					bean.setScheme_pic("");
				}
			}
			bean.setUpdate_by(current_user_id);
			bean.setCreate_date(DateUtils.getCurrentTime());
			objectService.update(bean);
			ProductTechnicsSchemeWorkStep workStep = new ProductTechnicsSchemeWorkStep();
			workStep=MapToObjectFactory.map2Java(workStep, map);
			workStep.setScheme_id(bean.getId());
			workStep.setType("0");
			workStep.setUpdate_by(current_user_id);
			workStep.setUpdate_date(DateUtils.getCurrentTime());
			workStepService.updateScheme(workStep);
//			if (null != bean.getProcess_relation_table()) {
//				ProductTechnicsSchemeWorkStep step = new ProductTechnicsSchemeWorkStep();
//				step.setScheme_id(bean.getId());
//				workStepService.delete(step);
//				List<Map> list = bean.getProcess_relation_table();
//				for (Map map2 : list) {
//					ProductTechnicsSchemeWorkStep workStep = new ProductTechnicsSchemeWorkStep();
//					workStep=MapToObjectFactory.map2Java(workStep, map2);
//					workStep.setId(Until.getStringUUID());
//					workStep.setCreate_by(current_user_id);
//					workStep.setScheme_id(bean.getId());
//					workStepService.save(workStep);
//				}
//			}
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
	 * 产品工艺方案工序查询
	 */
	@ApiOperation(value = "/getList",notes="产品工艺方案工序查询接口")
	@RequestMapping("/getList")
	public ModelMap getList(@RequestBody Map map) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			ProductTechnicsSchemeProcess bean = new ProductTechnicsSchemeProcess();
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
	 * 产品工艺方案工序分页查询
	 */
	@ApiOperation(value = "/getPageList",notes="产品工艺方案工序查询接口")
	@RequestMapping("/getPageList")
	public ModelMap getPageList(@RequestBody Map map) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			ProductTechnicsSchemeProcess bean = new ProductTechnicsSchemeProcess();
			bean = MapToObjectFactory.map2Java(bean, map);
			List list = objectService.getPageList(bean);
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
	 * 产品工艺方案工序单条查询
	 */
	@ApiOperation(value = "/getProcessOne",notes="产品查询接口")
	@RequestMapping("/getProcessOne")
	public ModelMap getProcessOne(@RequestParam String id) {
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			ProductTechnicsSchemeProcess bean = objectService.getObjetById(id);
			ProductTechnicsSchemeWorkStep step = new ProductTechnicsSchemeWorkStep();
			step.setScheme_id(id);
			step.setType("0");
			ProductTechnicsSchemeWorkStep workStep = workStepService.getObjectByBean(step);
			
			List<ProductTechnicsSchemeWorkStep> list=null;
			if(StringUtils.isNotEmpty(id)) {
				ProductTechnicsSchemeWorkStep step1 = new ProductTechnicsSchemeWorkStep();
				step1.setScheme_id(id);
				 list = workStepService.getList(step1);
				 for (ProductTechnicsSchemeWorkStep productTechnicsSchemeWorkStep : list) {
					 DetailList detail = new DetailList();
					 detail.setScheme_no(productTechnicsSchemeWorkStep.getId());
					 List list2 = detailListService.getList(detail);
					 productTechnicsSchemeWorkStep.setTool_no_relation_table(list2);
				}
			}
			result.put("schemeProcess", bean);
			result.put("workStep", workStep);
			result.put("list", list);
			result.put("msg", "查询成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;
		}
		return result;
	}
	/**
	 * 编辑工序（工步）详情
	 */
	@ApiOperation(value = "/updateProcessDetail",notes="编辑工序（工步）详情接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>修改成功")
	@RequestMapping("/updateProcessDetail")
	public ModelMap updateProcessDetail(@RequestBody Map map,HttpServletRequest request) {
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = new ModelMap();
		try {
			ProductTechnicsSchemeProcess bean = new ProductTechnicsSchemeProcess();
			bean = MapToObjectFactory.map2Java(bean, map);
			List<Map> list = bean.getProcess_relation_table();
			for (Map map2 : list) {
				ProductTechnicsSchemeWorkStep workStep = new ProductTechnicsSchemeWorkStep();
				workStep=MapToObjectFactory.map2Java(workStep, map2);
				if (StringUtils.isNotEmpty(workStep.getId())) {
					workStepService.update(workStep);
				}else {
					workStep.setId(IdGenerator.uuid());
					workStep.setCreate_by(current_user_id);
					workStepService.save(workStep);
				}
				if (workStep.getTool_no_relation_table()!=null) {
					List<Map> table = workStep.getTool_no_relation_table();
					DetailList detail = new DetailList();
					detail.setScheme_no(workStep.getId());
					detailListService.delete(detail);
					for (Map map3 : table) {
						DetailList detailList = new DetailList();
						detailList=MapToObjectFactory.map2Java(detailList, map3);
						detailList.setScheme_no(workStep.getId());
						detailList.setTool_no(workStep.getTool_no());
						detailList.setTenant_id(workStep.getTenant_id());
						detailList.setId(IdGenerator.uuid());
						detailList.setCreate_by(current_user_id);
						detailList.setCreate_date(DateUtils.getCurrentTime());
						detailListService.save(detailList);
					}
				}
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
	 * 工序工步详情（单个）
	 */
	@ApiOperation(value = "/getWorkStepDetail",notes="工序工步详情（单个）详情接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>查询成功")
	@RequestMapping("/getWorkStepDetail")
	public ModelMap getWorkStepDetail(@RequestBody Map map) {
		ModelMap result = new ModelMap();
		try {
			ProductTechnicsSchemeWorkStep bean = new ProductTechnicsSchemeWorkStep();
			bean=MapToObjectFactory.map2Java(bean, map);
			ProductTechnicsSchemeWorkStep workStep = workStepService.getObjectByBean(bean);
			DetailList detail = new DetailList();
			detail.setScheme_no(bean.getId());
			List list = detailListService.getList(detail);
			Feedback feedback = new Feedback();
			if (map.get("type").toString().equals("0")) {
				feedback.setSchemeNo(bean.getId());
			}else if (map.get("type").toString().equals("1")) {
				feedback.setWorkStep(bean.getId());
			}
			List feedList = feedbackService.getList(feedback);
			result.put("msg", "查询成功");
			result.put("workStep", workStep);
			result.put("feedList", feedList);
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
	 * 查看工步视频
	 */
	@ApiOperation(value = "/getWorkStepVideo",notes="查看工步视频接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>查询成功")
	@RequestMapping("/getWorkStepVideo")
	public ModelMap getWorkStepVideo(@RequestBody Map map) {
		ModelMap result = new ModelMap();
		try {
			Video video = videoService.getObjectById(map.get("id").toString());
			result.put("msg", "查询成功");
			result.put("video", video);
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
