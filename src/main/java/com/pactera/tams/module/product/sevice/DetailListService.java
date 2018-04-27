package com.pactera.tams.module.product.sevice;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.pactera.tams.module.product.mapper.DetailListMapper;
import com.pactera.tams.module.product.model.DetailList;
import com.pactera.tams.module.tool.mapper.ToolComposeMapper;
import com.pactera.tams.module.tool.model.ToolCompose;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 道具清单
 * @author ljp
 *
 */
@Service
public class DetailListService {

	@Autowired
	DetailListMapper objectMapper;
	@Autowired
	ToolComposeMapper toolComposeMapper;
	/**
	 * save
	 */
	public int save(DetailList bean) {
		return objectMapper.insert(bean);
	}
	/**
	 * update
	 */
	public void update(DetailList bean) {
		Example example = new Example(DetailList.class);
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotEmpty(bean.getId())){
    		criteria.andEqualTo("id", bean.getId());
    	}
		 objectMapper.updateByExampleSelective(bean, example);
	}
	/**
	 * delete
	 */
	public void delete(DetailList bean) {
		objectMapper.delete(bean);
	}
	/**
	 * select
	 */
	public List getList(DetailList bean) {
		return objectMapper.select(bean);
	}
	/**
	 * select分页
	 */
	public List getPageList(DetailList bean) {
		if(bean.getPage() != null && bean.getRows() != null) {
			 PageHelper.startPage(bean.getPage(), bean.getRows());
     } 
		return objectMapper.select(bean);
	}
	/**
	 * 选择刀具带出
	 */
	public List getToolList(ToolCompose bean) {
		return toolComposeMapper.getToolList(bean);
	}
	public List getToolOne(ToolCompose bean) {
		return toolComposeMapper.getToolOne(bean);
	}
	/**
	 * total
	 */
	public int getTotal(DetailList bean) {
		return objectMapper.selectCount(bean);
	}
}
