package com.pactera.tams.module.notice.controller;

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

import com.github.pagehelper.PageInfo;
import com.pactera.tams.common.utils.MapToObjectFactory;
import com.pactera.tams.common.utils.StringFormatUtils;
import com.pactera.tams.module.notice.model.SmsContent;
import com.pactera.tams.module.notice.service.SmsContentService;
import com.pactera.tams.module.system.entity.SysUser;
import com.pactera.tams.module.system.service.UserService;

import io.swagger.annotations.ApiOperation;
/**
 * 系统消息表Controller
 * @author liule
 *
 */
@RestController
@RequestMapping("/smsContent") 
public class SmsContentController {
	
	@Autowired
	private SmsContentService objectService;
	
	@Autowired
	private UserService userService; 
	/**
	 * 更新已读状态标记
	 * @param map
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "/updateSmsContent",notes="更新查看状态")
	@RequestMapping("/updateSmsContent")
	public ModelMap updateSmsContent(@RequestBody Map map,HttpServletRequest request) {	
		ModelMap result = StringFormatUtils.getResultMessage();  
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		try {
			SmsContent bean = new SmsContent();		
			bean = (SmsContent)MapToObjectFactory.map2Java(bean, map);
			bean = objectService.getSmsContentById(bean.getId());
			StringBuffer user_id_str = new StringBuffer();
			if(StringUtils.isNotBlank(bean.getUser_id_str())) {
				if(!bean.getUser_id_str().contains(current_user_id)) {
					user_id_str.append(bean.getUser_id_str()).append(",");
				}
			}
			user_id_str.append(current_user_id);
			bean.setUser_id_str(user_id_str.toString());
			objectService.update(bean);
		}catch(Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;  
		}
		return result;
	}
	/**
	 * 新增和修改
	 * @param map
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "/saveOrUpdate",notes="新增和修改")
	@RequestMapping("/saveOrUpdate")
	public ModelMap saveOrUpdate(@RequestBody Map map,HttpServletRequest request) {	
		ModelMap result = StringFormatUtils.getResultMessage();  
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		try {
			SmsContent bean = new SmsContent();		
			bean = (SmsContent)MapToObjectFactory.map2Java(bean, map);
			objectService.saveOrUpdate(bean,current_user_id);
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
	 * @param map
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "/delete",notes="删除")
	@RequestMapping("/delete")
	public ModelMap delete(@RequestParam String id) {	
		ModelMap result = StringFormatUtils.getResultMessage();  
		try {
			objectService.delete(id); 
		}catch(Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;  
		}
		return result;
	}
	
	
	/**
	 * 分页列表查询
	 * @param map
	 * @return
	 */
	@ApiOperation(value = "/getPageList",notes="分页查询，smsContent：最新添加消息对象，total：消息总数量，list：分页数据列表")
	@RequestMapping("/getPageList")
	public ModelMap getPageList(@RequestBody Map map,HttpServletRequest request) {	
		ModelMap result = StringFormatUtils.getResultMessage();  
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		try {
			SmsContent bean = new SmsContent();		
			bean = (SmsContent)MapToObjectFactory.map2Java(bean, map);
			List<SmsContent> list = objectService.getPageList(bean);
			changeStatus(list,current_user_id);
			PageInfo pageInfo = new PageInfo<SmsContent>(list);
			List<SmsContent> list2 = objectService.getList();
			changeStatus(list2,current_user_id);
			result.put("smsContent", list2.get(0));
			result.put("total", pageInfo.getTotal());
			result.put("list", list);
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
	 * 列表查询
	 * @param map
	 * @return
	 */
	@ApiOperation(value = "/getList",notes="列表查询，list：数据列表")
	@RequestMapping("/getList")
	public ModelMap getList(@RequestBody Map map,HttpServletRequest request) {	
		ModelMap result = StringFormatUtils.getResultMessage();  
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		try {
			SmsContent bean = new SmsContent();		
			bean = (SmsContent)MapToObjectFactory.map2Java(bean, map);
			List<SmsContent> list = objectService.getList();
			changeStatus(list,current_user_id);
			result.put("list", list);
			result.put("msg", "查询成功");
		}catch(Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
			result.put("retCode", "0");
			return result;  
		}
		return result;
	}
	
	public void changeStatus(List<SmsContent> list,String current_user_id) {
		for (SmsContent smsContent : list) {
			if(StringUtils.isNotBlank(smsContent.getUser_id_str())) {
				if(smsContent.getUser_id_str().contains(current_user_id)) {
					//设置为已读
					smsContent.setStatus(1);
				}
			}
			//显示创建人姓名
			if(StringUtils.isNotBlank(smsContent.getCreate_by())) {
				SysUser user = userService.getObjectById(smsContent.getCreate_by());
				smsContent.setCreate_by(user.getName());
			}
		}
	}
}
