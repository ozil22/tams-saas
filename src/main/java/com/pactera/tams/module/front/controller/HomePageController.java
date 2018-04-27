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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pactera.tams.common.utils.StringFormatUtils;
import com.pactera.tams.module.notice.model.SmsContent;
import com.pactera.tams.module.notice.service.NoticeService;
import com.pactera.tams.module.notice.service.SmsContentService;
import com.pactera.tams.module.product.model.Product;
import com.pactera.tams.module.product.sevice.ProductService;
import com.pactera.tams.module.tool.model.Tool;
import com.pactera.tams.module.tool.sevice.ToolService;

import io.swagger.annotations.ApiOperation;


/**
 * 主页
 * @author ljp
 *
 */
@RestController
@RequestMapping("/homePage")
public class HomePageController {
	private Logger logger = LoggerFactory.getLogger(HomePageController.class);
	
	@Autowired
	NoticeService noticeService;
	@Autowired
	SmsContentService smsContentService;
	@Autowired
	ProductService productService;
	@Autowired
	ToolService toolService;
	
	/**
	 * 通知公告+系统消息
	 */
    @ApiOperation(value = "/getList",notes="通知公告+系统消息查询接口")
    @RequestMapping(value = "/getList")
	public ModelMap getList(@RequestBody Map map) {
    	ModelMap result = StringFormatUtils.getResultMessage();
    	try {
    		SmsContent smsContent = new SmsContent();
    		smsContent.setPage(1);
    		smsContent.setRows(1);
    		List smsList = smsContentService.getPageList(smsContent);
			List list = noticeService.getFiveNotice();
			result.put("smsList", smsList);
			result.put("list", list);
			result.put("msg", "查询成功！");
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
     * 推荐产品 推荐刀具 我的加工记录 运营统计
     */
    @ApiOperation(value = "/getRecommendList",notes="推荐产品1 推荐刀具2 我的加工记录3 运营统计4")
    @RequestMapping(value = "/getRecommendList")
    public ModelMap getRecommendList(@RequestParam String type,@RequestParam String tenantId) {
    	ModelMap result = StringFormatUtils.getResultMessage();
    	try {
    		List list=new ArrayList();
			if (type.equals("1")) {
				Product product = new Product();
				product.setPage(1);
				product.setRows(5);
				product.setIs_recommend("1");
				product.setTenant_id(tenantId);
				list = productService.getFiveList(product);
			}else if (type.equals("2")) {
				Tool tool = new Tool();
				tool.setPage(1);
				tool.setRows(5);
				tool.setIs_recommend("1");
				tool.setTenant_id(tenantId);
				list = toolService.getFiveList(tool);
			}
			result.put("list", list);
			result.put("msg", "查询成功！");
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
