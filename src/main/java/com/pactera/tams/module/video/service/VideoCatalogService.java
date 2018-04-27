package com.pactera.tams.module.video.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.pactera.tams.common.utils.DateUtils;
import com.pactera.tams.common.utils.IdGenerator;
import com.pactera.tams.common.utils.StringFormatUtils;
import com.pactera.tams.module.video.mapper.VideoCatalogMapper;
import com.pactera.tams.module.video.model.VideoCatalog;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;


@Service
public class VideoCatalogService {
	
	@Autowired
	VideoCatalogMapper videoCatalogMapper;
	
	/**
	 * 保存或新增
	 */
	public ModelMap saveOrUpdate(VideoCatalog bean) {
		ModelMap result = StringFormatUtils.getResultMessage();
		VideoCatalog catalog = new VideoCatalog();
		catalog.setCatalog_name(bean.getCatalog_name());
		catalog.setTenant_id(bean.getTenant_id());
		VideoCatalog isExistBean = getObjectByBean(catalog);
		if (StringUtils.isNotEmpty(bean.getId())) {
			//修改
			if (isExistBean!=null&&isExistBean.getId().equals(bean.getId())) {
				result.put("retCode", -2);
				result.put("msg", "产品目录名称已经存在");
				return result;	
			}
			bean.setUpdate_date(DateUtils.getCurrentTime());
			videoCatalogMapper.updateByPrimaryKeySelective(bean);
		}else {
			//新增
			if (isExistBean!=null) {
				result.put("retCode", -2);
				result.put("msg", "产品目录名称已经存在");
				return result;	
			}
			bean.setCreate_date(DateUtils.getCurrentTime());
			bean.setId(IdGenerator.uuid());
			videoCatalogMapper.insert(bean);
		}
		return result;
	}
	
	/**
	 * 删除 id
	 */
	public void delete(String id) {
		videoCatalogMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 判断目录是否存在子目录
	 */
	public boolean isExistSubNode(String id) {
		Example example = new Example(VideoCatalog.class);
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(id)) {
            criteria.andEqualTo("parent_id", id);
        }
		List<VideoCatalog> list = videoCatalogMapper.selectByExample(example);
		return list.size()>0 ? true:false;
	}
	/**
	 * 查询
	 */
	public List getList(VideoCatalog bean) {
		
		return videoCatalogMapper.getList(bean);
	}
	/**
	 * 查询目录下的子目录或子目录下的产品
	 */
	public List getVideoOrCatalog(VideoCatalog bean) {
		Example example = new Example(VideoCatalog.class);
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(bean.getParent_id())) {
            criteria.andEqualTo("parent_id", bean.getParent_id());
        }
		return videoCatalogMapper.selectByExample(example);
	}
	/**
	 * 查询实体 
	 * id
	 */
	public VideoCatalog getObjectById(String id) {
		return videoCatalogMapper.selectByPrimaryKey(id);
	}
	/**
	 * 查询实体
	 * bean
	 */
	public VideoCatalog getObjectByBean(VideoCatalog bean) {
		return videoCatalogMapper.selectOne(bean);
	}
	/**
	 * 获得该节点目录下的所有子目录id
	 */
	public void findChildCatalogs(List<VideoCatalog> list,String id) {
		VideoCatalog videoCatalog = getObjectById(id);
		List<VideoCatalog> pcList=new ArrayList<VideoCatalog>();
		pcList.add(videoCatalog);
		// 加入当前目录 
		list.addAll(pcList.stream().collect(Collectors.toList()));  
		//获得子目录
		VideoCatalog vCatalog = new VideoCatalog();
		vCatalog.setParent_id(id);
		List<VideoCatalog> pc1List = getList(vCatalog);
		for (VideoCatalog p : pc1List) {
			//递归
			findChildCatalogs(list,p.getId());
		}
		
	}
	/**
	 * 
	 */
	public String findChildCls(StringBuffer buffer,String id) {
		VideoCatalog videoCatalog = getObjectById(id);
		buffer.insert(0,","+videoCatalog.getId());
			//获得上级目录
			VideoCatalog vCatalog = new VideoCatalog();
			
			if (videoCatalog.getParent_id().equals("0")) {
				return buffer.toString();
			}else {
				vCatalog.setId(videoCatalog.getParent_id());
				VideoCatalog objectByBean = getObjectByBean(vCatalog);
				
				findChildCls(buffer,objectByBean.getId());
			}

		return buffer.toString();
	}
}
