package com.pactera.tams.module.tool.sevice;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.pactera.tams.common.utils.DateUtils;
import com.pactera.tams.module.product.model.Specification;
import com.pactera.tams.module.tool.mapper.ToolPriceMapper;
import com.pactera.tams.module.tool.model.ToolPrice;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 刀具
 * @author js
 *2018.2.8
 */
@Service
public class ToolPriceService {
	@Autowired
	ToolPriceMapper toolPriceMapper;
	
	/**
	 * 保存刀具价格
	 */
	public int save(ToolPrice bean) {
		return toolPriceMapper.insert(bean);
	}
	/**
	 * 修改刀具价格信息
	 */
	public void update(ToolPrice bean) {
		Example example = new Example(Specification.class);
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotEmpty(bean.getId())){
    		criteria.andEqualTo("id", bean.getId());
    	}
		bean.setUpdate_date(DateUtils.getCurrentTime());
		toolPriceMapper.updateByExampleSelective(bean, example);
	}
	/**
	 * 刀具价格删除
	 */
	public void delete(String id) {
		toolPriceMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 查询
	 */
	public List getList(ToolPrice bean) {
		Example example = new Example(Specification.class);
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(bean.getTenant_id())) {
			criteria.andEqualTo("tenant_id", bean.getTenant_id());
		}
		return toolPriceMapper.selectByExample(example);
	}
	/**
	 * 分页查询
	 */
	public List getPageList(ToolPrice bean) {
		if(bean.getPage() != null && bean.getRows() != null) {
			 PageHelper.startPage(bean.getPage(), bean.getRows());
       } 	
		return toolPriceMapper.getList(bean);
	}
	/**
	 * 查询实体
	 * id
	 */
	public ToolPrice getObjectById(String id) {
		return toolPriceMapper.selectByPrimaryKey(id);
	}
	/**
	 * 查询实体
	 * bean
	 */
	public ToolPrice getObjectByBean(ToolPrice bean) {
		return toolPriceMapper.selectOne(bean);
	}
	/**
	 * 获得刀具价格数量
	 */
	public int getTotal(ToolPrice bean) {
		return toolPriceMapper.getTotal(bean);
	}
}
