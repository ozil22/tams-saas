package com.pactera.tams.module.notice.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pactera.tams.common.utils.DateUtils;
import com.pactera.tams.common.utils.IdGenerator;
import com.pactera.tams.common.utils.MapToObjectFactory;
import com.pactera.tams.common.utils.StringFormatUtils;
import com.pactera.tams.module.notice.model.Notice;
import com.pactera.tams.module.notice.service.NoticeService;
import com.pactera.tams.module.system.entity.SysUser;
import com.pactera.tams.module.system.service.UserService;

import io.swagger.annotations.ApiOperation;

/**  
 * @author ljp 
 * @version 创建时间：2017.12.25 
 * 类说明  
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {
	private Logger logger = LoggerFactory.getLogger(NoticeController.class);
	@Autowired
	NoticeService noticeService;
	@Autowired 
	UserService userService;
	/**
	 * 通知保存
	 */
    @ApiOperation(value = "/save",notes="通知公告新增接口，返回结果 retCode: 0=>系统异常，请联系管理员,1=>保存成功 ")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelMap toSave(@RequestBody Map map,HttpServletRequest request) {
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			SysUser user = userService.getObjectById(current_user_id);
			Notice bean = new Notice();
			bean = MapToObjectFactory.map2Java(bean, map);
			bean.setId(IdGenerator.uuid());
			bean.setCreate_by(current_user_id);
			bean.setCreate_date(DateUtils.getCurrentTime());
			bean.setTenant_id(user.getTenantId());
			bean.setStatus("0");
			noticeService.saveNotice(bean);
			result.put("noticeInfo", bean); 
	        result.put("msg", "通知公告添加成功");
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
     * 根据id删除
     * @param map
     * @return
     */
    @ApiOperation(value = "/delete",notes="根据通知id删除通知公告信息接口，返回结果 retCode: 0=>系统异常，请联系管理员,1=>删除成功")
    @RequestMapping(value = "/delete")
    public ModelMap deleteNotice(@RequestParam String id) {
    	ModelMap result = StringFormatUtils.getResultMessage();
    	try {
    		noticeService.delete(id);
        	result.put("msg", "删除成功!");
            result.put("retCode", 1);
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
     * @return
     */
    @ApiOperation(value = "/getList",notes="通知公告查询接口")
    @RequestMapping(value = "/getList")
    public ModelMap getList(@RequestBody Map map) {
    	ModelMap result = StringFormatUtils.getResultMessage();
    	try {
    		Notice bean = new Notice();
        	bean = MapToObjectFactory.map2Java(bean, map);
        	List<Notice> list = noticeService.getList(bean);
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
     * 分页查询
     * @return
     */
    @ApiOperation(value = "/getPageList",notes="分页查询")
	@RequestMapping(value="/getPageList")
	public ModelMap getPageList(@RequestBody Map map) {	
    	ModelMap result = StringFormatUtils.getResultMessage();
    	try {
    		Notice bean = new Notice();
        	bean = MapToObjectFactory.map2Java(bean, map);
        	List<Notice> list = noticeService.getpageList(bean);
        	int total = noticeService.getTotal(bean);
        	result.put("list", list);
        	result.put("total", total);
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
     * 根据id查询
     */
    @ApiOperation(value = "/updateNoticeStatus",notes="查看单条通知，更新查看状态")
	@RequestMapping("/updateNoticeStatus")
    public ModelMap updateNoticeStatus(@RequestParam String id,HttpServletRequest request) {
    	String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
    	ModelMap result = new ModelMap();
    	StringBuffer buffer = new StringBuffer();
    	Notice bean = new Notice();
    	Notice notice = noticeService.getObjectById(id);
    	if (StringUtils.isNotBlank(notice.getUser_id_str())) {
			buffer.append(bean.getUser_id_str()+",");
		}
    	if (!StringUtils.contains(bean.getUser_id_str(), current_user_id)) {
			buffer.append(current_user_id);
		}
    	bean.setId(id);
    	bean.setUser_id_str(buffer.toString());
    	bean.setStatus("1");
    	noticeService.updateNotice(bean);
    	result.put("noticeInfo", bean);
    	result.put("msg", "查询成功！");
    	return result;
    }
}
