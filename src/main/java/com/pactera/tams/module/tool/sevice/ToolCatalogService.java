package com.pactera.tams.module.tool.sevice;

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
import com.pactera.tams.module.tool.mapper.ToolCatalogMapper;
import com.pactera.tams.module.tool.mapper.ToolMapper;
import com.pactera.tams.module.tool.model.Tool;
import com.pactera.tams.module.tool.model.ToolCatalog;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;


@Service
public class ToolCatalogService {
	
	@Autowired
	ToolCatalogMapper toolCatalogMapper;
	@Autowired
	ToolMapper toolMapper;
	/**
	 * 保存或新增
	 */
	public ModelMap saveOrUpdate(ToolCatalog bean) {
		ModelMap result = StringFormatUtils.getResultMessage();
		ToolCatalog catalog = new ToolCatalog();
		catalog.setCatalog_name(bean.getCatalog_name());
		catalog.setTenant_id(bean.getTenant_id());
		ToolCatalog isExistBean = getObjectByBean(catalog);
		if (StringUtils.isNotEmpty(bean.getId())) {
			//修改
			if (isExistBean!=null&&isExistBean.getId().equals(bean.getId())) {
				result.put("retCode", -2);
				result.put("msg", "产品目录名称已经存在");
				return result;	
			}
			bean.setUpdate_date(DateUtils.getCurrentTime());
			toolCatalogMapper.updateByPrimaryKeySelective(bean);
		}else {
			//新增
			if (isExistBean!=null) {
				result.put("retCode", -2);
				result.put("msg", "产品目录名称已经存在");
				return result;	
			}
			Tool tool = new Tool();
			tool.setCatalog_id(bean.getId());
			List list = toolMapper.getList(tool);
			if (list.size()>0) {
				result.put("retCode", -2);
				result.put("msg", "该产品目录下已存在产品");
				return result;
			}
			bean.setCreate_date(DateUtils.getCurrentTime());
			bean.setId(IdGenerator.uuid());
			toolCatalogMapper.insert(bean);
		}
		return result;
	}
	
	/**
	 * 删除 id
	 */
	public void delete(String id) {
		toolCatalogMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 判断目录是否存在子目录
	 */
	public boolean isExistSubNode(String id) {
		Example example = new Example(ToolCatalog.class);
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(id)) {
            criteria.andEqualTo("parent_id", id);
        }
		List<ToolCatalog> list = toolCatalogMapper.selectByExample(example);
		return list.size()>0 ? true:false;
	}
	/**
	 * 查询
	 */
	public List getList(ToolCatalog bean) {
		
		return toolCatalogMapper.getList(bean);
	}
	/**
	 * 查询目录下的子目录或子目录下的产品
	 */
	public List getToolOrCatalog(ToolCatalog bean) {
		Example example = new Example(ToolCatalog.class);
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(bean.getParent_id())) {
            criteria.andEqualTo("parent_id", bean.getParent_id());
        }
		return toolCatalogMapper.selectByExample(example);
	}
	/**
	 * 查询实体 
	 * id
	 */
	public ToolCatalog getObjectById(String id) {
		return toolCatalogMapper.selectByPrimaryKey(id);
	}
	/**
	 * 查询实体
	 * bean
	 */
	public ToolCatalog getObjectByBean(ToolCatalog bean) {
		return toolCatalogMapper.selectOne(bean);
	}
	/**
	 * 获得该节点目录下的所有子目录id
	 */
	public void findChildCatalogs(List<ToolCatalog> list,String id) {
		ToolCatalog toolCatalog = getObjectById(id);
		List<ToolCatalog> pcList=new ArrayList<ToolCatalog>();
		pcList.add(toolCatalog);
		// 加入当前目录 
		list.addAll(pcList.stream().collect(Collectors.toList()));  
		//获得子目录
		ToolCatalog tCatalog = new ToolCatalog();
		tCatalog.setParent_id(id);
		List<ToolCatalog> pc1List = getList(tCatalog);
		for (ToolCatalog p : pc1List) {
			//递归
			findChildCatalogs(list,p.getId());
		}
		
	}
	/**
	 *获得上级目录的所有id
	 */
	public String findChildCls(StringBuffer buffer,String id) {
		ToolCatalog toolCatalog = getObjectById(id);
		buffer.insert(0,","+toolCatalog.getId());
			//获得上级目录
			ToolCatalog tCatalog = new ToolCatalog();
			
			if (toolCatalog.getParent_id().equals("0")) {
				return buffer.toString();
			}else {
				tCatalog.setId(toolCatalog.getParent_id());
				ToolCatalog objectByBean = getObjectByBean(tCatalog);
				
				findChildCls(buffer,objectByBean.getId());
			}

		return buffer.toString();
	}
}
