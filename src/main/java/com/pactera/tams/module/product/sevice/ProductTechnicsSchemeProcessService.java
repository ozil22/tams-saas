package com.pactera.tams.module.product.sevice;

import com.github.pagehelper.PageHelper;
import com.pactera.tams.module.product.mapper.ProductTechnicsSchemeProcessMapper;
import com.pactera.tams.module.product.model.ProductTechnicsSchemeProcess;
import com.pactera.tams.module.solr.model.SolrModel;
import com.pactera.tams.module.solr.service.SolrModelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;

/**
 * 产品工艺方案工序
 * @author ljp
 *2018.1.12
 */
@Service
public class ProductTechnicsSchemeProcessService {

	@Autowired
	ProductTechnicsSchemeProcessMapper productTechnicsSchemeProcessMapper;
	@Autowired
	SolrModelService solrModelService;
	
	/**
	 * 产品工艺方案工序保存
	 */
	public int save(ProductTechnicsSchemeProcess bean) throws Exception {
		int count = productTechnicsSchemeProcessMapper.insert(bean);
        SolrModel solrModel = productTechnicsSchemeProcessMapper.getSolr(bean.getId());
        solrModelService.addBeanIndex(solrModel);
        return count;
	}
	/**
	 * 产品工艺方案工序删除
	 */
	public void delete(String id) {
		SolrModel solrModel = productTechnicsSchemeProcessMapper.getSolr(id);
        StringBuilder solrql = new StringBuilder("index_name:scheme AND ");
        solrql.append("scheme_id").append(":").append(id);
        solrModelService.deleteByQuery(solrql.toString());
        productTechnicsSchemeProcessMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 产品工艺方案工序修改
	 */
	public void update(ProductTechnicsSchemeProcess bean) {
		Example example = new Example(ProductTechnicsSchemeProcess.class);
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotEmpty(bean.getId())){
    		criteria.andEqualTo("id", bean.getId());
    	}
		productTechnicsSchemeProcessMapper.updateByExampleSelective(bean, example);

		 SolrModel solrModel = productTechnicsSchemeProcessMapper.getSolr(bean.getId());
        StringBuilder solrql = new StringBuilder("index_name:scheme AND ");
        solrql.append("scheme_id").append(":").append(bean.getId());
        solrModelService.updateBeanByQuerySting(solrModel, solrql.toString());
	}
	/**
	 * 产品工艺方案工序查询
	 */
	public List getList(ProductTechnicsSchemeProcess bean) {
		
		return productTechnicsSchemeProcessMapper.select(bean);
	}
	/**
	 * 产品工艺方案工序分页查询
	 */
	public List getPageList(ProductTechnicsSchemeProcess bean) {
		if(bean.getPage() != null && bean.getRows() != null) {
			 PageHelper.startPage(bean.getPage(), bean.getRows());
      } 
		return productTechnicsSchemeProcessMapper.select(bean);
	}
	/**
	 * 产品工艺方案工序查询实体
	 * id
	 */
	public ProductTechnicsSchemeProcess getObjetById(String id) {
		return productTechnicsSchemeProcessMapper.selectByPrimaryKey(id);
	}
	/**
	 * 产品工艺方案工序查询实体
	 * bean
	 */
	public ProductTechnicsSchemeProcess getObjectByBean(ProductTechnicsSchemeProcess bean) {
		return productTechnicsSchemeProcessMapper.selectOne(bean);
	}
	/**
	 * 产品工艺方案工序数量
	 */
	public int getTotal(ProductTechnicsSchemeProcess bean) {
		return productTechnicsSchemeProcessMapper.selectCount(bean);
	}
}
