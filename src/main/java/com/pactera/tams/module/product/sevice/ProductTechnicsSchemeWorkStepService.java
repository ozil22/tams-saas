package com.pactera.tams.module.product.sevice;

import com.github.pagehelper.PageHelper;
import com.pactera.tams.module.product.mapper.ProductTechnicsSchemeWorkStepMapper;
import com.pactera.tams.module.product.model.ProductTechnicsSchemeWorkStep;
import com.pactera.tams.module.solr.model.SolrModel;
import com.pactera.tams.module.solr.service.SolrModelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;

/**
 * 产品工艺方案工序工步
 * @author ljp
 *2018.1.15
 */
@Service
public class ProductTechnicsSchemeWorkStepService {
	
	@Autowired
	ProductTechnicsSchemeWorkStepMapper objectMapper;
	@Autowired
	SolrModelService solrModelService;
	
	/**
	 * 产品工艺方案工序工步保存
	 */
	public int save(ProductTechnicsSchemeWorkStep bean) throws Exception {
		int count = objectMapper.insert(bean);
        SolrModel solrModel = objectMapper.getSolr(bean.getId());
        solrModelService.addBeanIndex(solrModel);
        return count;
	}
	
	/**
	 * 产品工艺方案工序工步修改
	 */
	public void update(ProductTechnicsSchemeWorkStep bean) {
		Example example = new Example(ProductTechnicsSchemeWorkStep.class);
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotEmpty(bean.getId())){
    		criteria.andEqualTo("id", bean.getId());
    	}
		objectMapper.updateByExampleSelective(bean, example);
		
		SolrModel solrModel = objectMapper.getSolr(bean.getId());
        StringBuilder solrql = new StringBuilder("index_name:step AND ");
        solrql.append("step_id").append(":").append(bean.getId());
        solrModelService.updateBeanByQuerySting(solrModel, solrql.toString());
	}
	/**
	 * 产品工艺方案工序工步修改
	 */
	public void updateScheme(ProductTechnicsSchemeWorkStep bean) {
		Example example = new Example(ProductTechnicsSchemeWorkStep.class);
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotEmpty(bean.getScheme_id())){
    		criteria.andEqualTo("scheme_id", bean.getScheme_id());
    	}
		objectMapper.updateByExampleSelective(bean, example);
		
		SolrModel solrModel = objectMapper.getSolr(bean.getId());
        StringBuilder solrql = new StringBuilder("index_name:step AND ");
        solrql.append("step_id").append(":").append(bean.getId());
        solrModelService.updateBeanByQuerySting(solrModel, solrql.toString());
	}
	
	/**
	 *产品工艺方案工序工步删除
	 * 
	 */
	public void delete(ProductTechnicsSchemeWorkStep bean) {
		String id = bean.getId();
		SolrModel solrModel = objectMapper.getSolr(id);
        StringBuilder solrql = new StringBuilder("index_name:step AND ");
        solrql.append("step_id").append(":").append(id);
        solrModelService.deleteByQuery(solrql.toString());
        objectMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 产品工艺方案工序工步查询
	 */
	public List getList(ProductTechnicsSchemeWorkStep bean) {
		Example example = new Example(ProductTechnicsSchemeWorkStep.class);
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotEmpty(bean.getScheme_id())){
    		criteria.andEqualTo("scheme_id", bean.getScheme_id());
    	}
		example.setOrderByClause("type desc");
		return objectMapper.selectByExample(example);
	}
	
	/**
	 * 产品工艺方案工序工步分页查询
	 */
	public List getPageList(ProductTechnicsSchemeWorkStep bean) {
		if(bean.getPage() != null && bean.getRows() != null) {
			 PageHelper.startPage(bean.getPage(), bean.getRows());
      } 
		return objectMapper.select(bean);
	}
	
	/**
	 * 产品工艺方案工序工步查询实体
	 * id
	 */
	public ProductTechnicsSchemeWorkStep getObjetById(String id) {
		return objectMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 产品工艺方案工序工步查询实体
	 * bean
	 */
	public ProductTechnicsSchemeWorkStep getObjectByBean(ProductTechnicsSchemeWorkStep bean) {
		return objectMapper.selectOne(bean);
	}
	
	/**
	 * 产品工艺方案工序工步数量
	 */
	public int getTotal(ProductTechnicsSchemeWorkStep bean) {
		return objectMapper.selectCount(bean);
	}
}
