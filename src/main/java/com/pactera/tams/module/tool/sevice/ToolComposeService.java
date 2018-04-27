package com.pactera.tams.module.tool.sevice;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.pactera.tams.common.utils.DateUtils;
import com.pactera.tams.module.tool.mapper.ToolComposeMapper;
import com.pactera.tams.module.tool.model.ToolCompose;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 刀具
 * @author ztx
 *2018.1.12
 */
@Service
public class ToolComposeService {
	@Autowired
	ToolComposeMapper toolComposeMapper;
	
	/**
	 * 保存刀具
	 */
	public int save(ToolCompose bean) {
		return toolComposeMapper.insert(bean);
	}
	
	
	/**
	 * 修改刀具信息
	 */
	public void update(ToolCompose bean) {
		Example example = new Example(ToolCompose.class);
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotEmpty(bean.getId())){
    		criteria.andEqualTo("id", bean.getId());
    	}
		bean.setUpdate_date(DateUtils.getCurrentTime());
		toolComposeMapper.updateByExampleSelective(bean, example);
	}
	/**
	 * 刀具删除
	 */
	public void delete(String id) {
		toolComposeMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 查询
	 */
	public List getList(ToolCompose bean) {
		Example example = new Example(ToolCompose.class);
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(bean.getTool_id())) {
			criteria.andEqualTo("tool_id", bean.getTool_id());
		}
		return toolComposeMapper.selectByExample(example);
	}
	public List getSmallToolId(ToolCompose bean) {
		return toolComposeMapper.getSmallToolId(bean);
	}
	/**
	 * 分页查询
	 */
	public ToolCompose getPageList(ToolCompose bean) {
		if(bean.getPage() != null && bean.getRows() != null) {
			 PageHelper.startPage(bean.getPage(), bean.getRows());
       } 	
		return (ToolCompose) toolComposeMapper.getList(bean);
	}
	/**
	 * 查询实体
	 * id
	 */
	public ToolCompose getObjectById(String id) {
		return toolComposeMapper.selectByPrimaryKey(id);
	}
	/**
	 * 查询实体
	 * bean
	 */
	public ToolCompose getObjectByBean(ToolCompose bean) {
		return toolComposeMapper.selectOne(bean);
	}
	/**
	 * 获得刀具数量
	 */
	public int getTotal(ToolCompose bean) {
		return toolComposeMapper.selectCount(bean);
	}
}
