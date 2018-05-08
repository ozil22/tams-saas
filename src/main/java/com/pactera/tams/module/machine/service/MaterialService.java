package com.pactera.tams.module.machine.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.pactera.tams.module.machine.mapper.MaterialMapper;
import com.pactera.tams.module.machine.entity.Material;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
/**
 * 材质
 * @author ljp
 *
 */
@Service
public class MaterialService {
	
	@Autowired
	MaterialMapper objectMapper;
	
	/**
	 * save
	 */
	public int save(Material bean) {
		return objectMapper.insert(bean);
	}
	
	/**
	 * update
	 */
	public void update(Material bean) {
		Example example = new Example(Material.class);
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
	public List getList(Material bean) {
		Example example = new Example(Material.class);
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(bean.getMaterial_quality_code())) {
			criteria.andLike("material_quality_code", "%"+bean.getMaterial_quality_code()+"%");
		}
		if (StringUtils.isNotEmpty(bean.getMaterial_quality_name())) {
			criteria.andLike("material_quality_name", "%"+bean.getMaterial_quality_name()+"%");
		}
		if (StringUtils.isNotEmpty(bean.getMaterial_quality_spec())) {
			criteria.andLike("material_quality_spec", "%"+bean.getMaterial_quality_spec()+"%");
		}
		if (StringUtils.isNotEmpty(bean.getStatus())) {
			criteria.andEqualTo("status",bean.getStatus());
		}
		return objectMapper.selectByExample(example);
	}
	
	/**
	 * select分页
	 */
	public List getPageList(Material bean) {
		if(bean.getPage() != null && bean.getRows() != null) {
			 PageHelper.startPage(bean.getPage(), bean.getRows());
     } 
		Example example = new Example(Material.class);
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(bean.getMaterial_quality_code())) {
			criteria.andLike("material_quality_code", "%"+bean.getMaterial_quality_code()+"%");
		}
		if (StringUtils.isNotEmpty(bean.getMaterial_quality_name())) {
			criteria.andLike("material_quality_name", "%"+bean.getMaterial_quality_name()+"%");
		}
		if (StringUtils.isNotEmpty(bean.getMaterial_quality_spec())) {
			criteria.andLike("material_quality_spec", "%"+bean.getMaterial_quality_spec()+"%");
		}
		if (StringUtils.isNotEmpty(bean.getStatus())) {
			criteria.andEqualTo("status",bean.getStatus());
		}
		return objectMapper.selectByExample(example);
	}
	
	/**
	 * total
	 */
	public int getTotal(Material bean) {
		Example example = new Example(Material.class);
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(bean.getMaterial_quality_code())) {
			criteria.andLike("material_quality_code", "%"+bean.getMaterial_quality_code()+"%");
		}
		if (StringUtils.isNotEmpty(bean.getMaterial_quality_name())) {
			criteria.andLike("material_quality_name", "%"+bean.getMaterial_quality_name()+"%");
		}
		if (StringUtils.isNotEmpty(bean.getMaterial_quality_spec())) {
			criteria.andLike("material_quality_spec", "%"+bean.getMaterial_quality_spec()+"%");
		}
		if (StringUtils.isNotEmpty(bean.getStatus())) {
			criteria.andEqualTo("status",bean.getStatus());
		}
		return objectMapper.selectCountByExample(example);
	}
	
	/**
	 * select one  id
	 */
	public Material getById(String id) {
		return objectMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * select one bean
	 */
	public Material getByBean(Material bean) {
		return objectMapper.selectOne(bean);
	}

    public List<Material> getAll() {
		return objectMapper.selectAll();
    }
}
