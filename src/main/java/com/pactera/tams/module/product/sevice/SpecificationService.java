package com.pactera.tams.module.product.sevice;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.pactera.tams.common.utils.DateUtils;
import com.pactera.tams.module.product.mapper.SpecificationMapper;
import com.pactera.tams.module.product.model.Specification;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 规格
 * @author js 
 * 2018.2.6
 */
@Service
public class SpecificationService {
	@Autowired
	SpecificationMapper specificationMapper;
	
	/**
	 * 保存规格
	 */
	public int save(Specification bean) {
		return specificationMapper.insert(bean);
	}
	/**
	 * 修改规格信息
	 */
	public void update(Specification bean) {
		Example example = new Example(Specification.class);
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotEmpty(bean.getId())){
    		criteria.andEqualTo("id", bean.getId());
    	}
		bean.setUpdate_date(DateUtils.getCurrentTime());
		specificationMapper.updateByExampleSelective(bean, example);
	}
	/**
	 * 规格删除
	 */
	public void delete(String id) {
		specificationMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 查询
	 */
	public List getList(Specification bean) {
		Example example = new Example(Specification.class);
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(bean.getTenant_id())) {
			criteria.andEqualTo("tenant_id", bean.getTenant_id());
		}
		return specificationMapper.selectByExample(example);
	}
	/**
	 * 分页查询
	 */
	public List getPageList(Specification bean) {
		if(bean.getPage() != null && bean.getRows() != null) {
			 PageHelper.startPage(bean.getPage(), bean.getRows());
       } 	
		return specificationMapper.getList(bean);
	}
	/**
	 * 查询实体
	 * id
	 */
	public Specification getObjectById(String id) {
		return specificationMapper.selectByPrimaryKey(id);
	}
	/**
	 * 查询实体
	 * bean
	 */
	public Specification getObjectByBean(Specification bean) {
		return specificationMapper.selectOne(bean);
	}
	/**
	 * 获得规格数量
	 */
	public int getTotal(Specification bean) {
		return specificationMapper.getTotal(bean);
	}
	
}
