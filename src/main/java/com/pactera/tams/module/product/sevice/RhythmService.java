package com.pactera.tams.module.product.sevice;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.pactera.tams.module.product.mapper.RhythmMapper;
import com.pactera.tams.module.product.model.Rhythm;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 节拍汇总
 * @author ljp
 *
 */
@Service
public class RhythmService {

	@Autowired
	RhythmMapper objectMapper;
	
	/**
	 * save
	 */
	public int save(Rhythm bean) {
		return objectMapper.insert(bean);
	}
	
	/**
	 * update
	 */
	public void update(Rhythm bean) {
		Example example = new Example(Rhythm.class);
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotEmpty(bean.getId())){
    		criteria.andEqualTo("id", bean.getId());
    	}
		 objectMapper.updateByExampleSelective(bean, example);
	}
	
	/**
	 * delete
	 */
	public void delete(String id) {
		objectMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * select
	 */
	public List getList(Rhythm bean) {
		return objectMapper.select(bean);
	}
	
	/**
	 * select分页
	 */
	public List getPageList(Rhythm bean) {
		if(bean.getPage() != null && bean.getRows() != null) {
			 PageHelper.startPage(bean.getPage(), bean.getRows());
     } 
		return objectMapper.select(bean);
	}
	
	/**
	 * total
	 */
	public int getTotal(Rhythm bean) {
		return objectMapper.selectCount(bean);
	}
	
	/**
	 * select one  id
	 */
	public Rhythm getById(String id) {
		return objectMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * select one bean
	 */
	public Rhythm getByBean(Rhythm bean) {
		return objectMapper.selectOne(bean);
	}
}
