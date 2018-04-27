package com.pactera.tams.module.video.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.util.StringUtil;
import com.pactera.tams.common.utils.DateUtils;
import com.pactera.tams.common.utils.IdGenerator;
import com.pactera.tams.common.utils.MapToObjectFactory;
import com.pactera.tams.common.utils.StringFormatUtils;
import com.pactera.tams.module.system.controller.OrgController;
import com.pactera.tams.module.system.service.UserService;
import com.pactera.tams.module.video.model.Video;
import com.pactera.tams.module.video.model.VideoCatalog;
import com.pactera.tams.module.video.service.VideoCatalogService;
import com.pactera.tams.module.video.service.VideoService;

import io.swagger.annotations.ApiOperation;

/**  
 * @author ztx
 * @version 创建时间：2017.12.29 
 * 类说明  
 */
@RestController
@RequestMapping("/video")
public class VideoController {
	private Logger logger = LoggerFactory.getLogger(OrgController.class);
	@Autowired
	VideoService videoService; 
	@Autowired
	UserService userService; 
	@Autowired
	VideoCatalogService catalogService;
	
	/**
	 * 新增
	 * @param 
	 * @return
	 */
	@ApiOperation(value = "/save",notes="新增视频接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>保存成功 ")
	@RequestMapping("/save")
	public ModelMap saveVideo(@RequestBody Map map,HttpServletRequest request) {
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = StringFormatUtils.getResultMessage();
		try {
			Video bean = new Video();
			bean = MapToObjectFactory.map2Java(bean, map);
			bean.setId(IdGenerator.uuid());
			bean.setCreate_by(current_user_id);
			bean.setCreate_date(DateUtils.getCurrentTime());
			bean.setStatus("0");
			videoService.saveVideo(bean);
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
    @ApiOperation(value = "/delete",notes="删除视频接口，返回结果 retCode: 0=>系统异常，请联系管理员,1=>删除成功")
    @RequestMapping(value = "/delete")
    public ModelMap deleteVideo(@RequestParam String id) {
    	ModelMap result = StringFormatUtils.getResultMessage();
    	try {
    		videoService.delete(id);
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
	 * 修改
	 * @param map
	 * @return
	 */
	@ApiOperation(value = "/update",notes="修改视频接口 ,返回结果 retCode: 0=>系统异常，请联系管理员,1=>修改成功 ")
	@RequestMapping("/update")
	public ModelMap updateVideo(@RequestBody Map map,HttpServletRequest request) {
		String current_user_id = request.getAttribute("user_id") == null?"":(String)request.getAttribute("user_id");
		ModelMap result = new ModelMap();
		try {
			Video bean = new Video();
			bean = MapToObjectFactory.map2Java(bean, map);	 
			bean.setUpdate_by(current_user_id);
			videoService.update(bean);
			result.put("msg", "修改成功");
		}catch(Exception e) {
			e.printStackTrace();
			result.put("msg", "系统异常，请联系管理员");
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
    		StringBuffer sb = new StringBuffer();
			VideoCatalog catalog = new VideoCatalog();
			catalog=MapToObjectFactory.map2Java(catalog, map);
			if (null!=catalog.getId()&&""!=catalog.getId()) {
				List<VideoCatalog> pcatalogList=new ArrayList();
				catalogService.findChildCatalogs(pcatalogList,catalog.getId());
				for (int i = 0; i < pcatalogList.size(); i++) {
					VideoCatalog videoCatalog = pcatalogList.get(i);
					sb.append("'").append(videoCatalog.getId()).append("'");
					if(i+1 != pcatalogList.size()) {
						sb.append(",");
					}
				}
			}
			Video bean = new Video();
			bean = MapToObjectFactory.map2Java(bean, map);
			bean.setCatalog_id(sb.toString());
			List list = videoService.getList(bean);
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
     * 分页查询
     * @return
     */
    @ApiOperation(value = "/getPageList",notes="分页查询")
	@RequestMapping(value="/getPageList")
	public ModelMap getPageList(@RequestBody Map map) {	
    	ModelMap result = StringFormatUtils.getResultMessage();
    	try {
    		StringBuffer sb = new StringBuffer();
			VideoCatalog catalog = new VideoCatalog();
			catalog=MapToObjectFactory.map2Java(catalog, map);
			if (null!=catalog.getId()&&""!=catalog.getId()) {
				List<VideoCatalog> pcatalogList=new ArrayList();
				catalogService.findChildCatalogs(pcatalogList,catalog.getId());
				for (int i = 0; i < pcatalogList.size(); i++) {
					VideoCatalog videoCatalog = pcatalogList.get(i);
					sb.append("'").append(videoCatalog.getId()).append("'");
					if(i+1 != pcatalogList.size()) {
						sb.append(",");
					}
				}
			}
    		Video bean = new Video();
        	bean = MapToObjectFactory.map2Java(bean, map);
        	int total = videoService.getTotal(bean);
        	bean.setCatalog_id(sb.toString());
        	List<Map<String, Object>> list = videoService.getpageList(bean);
        	for (Map<String, Object> map2 : list) {
				StringBuffer buffer = new StringBuffer();
				String findChildCls = catalogService.findChildCls(buffer, map2.get("catalog_id").toString());
				if(StringUtil.isNotEmpty(findChildCls)) {
					String[] split = findChildCls.subSequence(1, findChildCls.length()).toString().split(",");
					map2.put("catalog_id", split);
					System.out.println(findChildCls);
				}
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
}
