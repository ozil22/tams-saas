package com.pactera.tams.module.product.sevice;

import com.github.pagehelper.PageHelper;
import com.pactera.tams.module.product.mapper.ProductTechnicsSchemeMapper;
import com.pactera.tams.module.product.model.ProductTechnicsScheme;
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
 * 产品工艺
 * @author ljp
 *2018.1.12
 */
@Service
public class ProductTechnicsSchemeService {
	
	@Autowired
	ProductTechnicsSchemeMapper objectMapper;
	@Autowired
	SolrModelService solrModelService;
	
	/**
	 * 产品工艺方案保存
	 */
	public int save(ProductTechnicsScheme bean) throws Exception {
		int count = objectMapper.insert(bean);
        SolrModel solrModel = objectMapper.getSolr(bean.getId());
        solrModelService.addBeanIndex(solrModel);
        return count;
	}
	
	/**
	 * 产品工艺方案修改
	 */
	public void update(ProductTechnicsScheme bean) {
		Example example = new Example(ProductTechnicsScheme.class);
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotEmpty(bean.getId())){
    		criteria.andEqualTo("id", bean.getId());
    	}
		objectMapper.updateByExampleSelective(bean, example);

		SolrModel solrModel = objectMapper.getSolr(bean.getId());
        StringBuilder solrql = new StringBuilder("index_name:technics AND ");
        solrql.append("technics_id").append(":").append(bean.getId());
        solrModelService.updateBeanByQuerySting(objectMapper, solrql.toString());
	}
	
	/**
	 * 产品工艺方案删除
	 * id
	 */
	public void delete(String id) {
		SolrModel solrModel = objectMapper.getSolr(id);
        StringBuilder solrql = new StringBuilder("index_name:technics AND ");
        solrql.append("technics_id").append(":").append(id);
        solrModelService.deleteByQuery(solrql.toString());
        objectMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 产品工艺方案查询
	 */
	public List getList(ProductTechnicsScheme bean) {
		
		return objectMapper.select(bean);
	}
	
	/**
	 * 产品工艺方案分页查询
	 */
	public List getPageList(ProductTechnicsScheme bean) {
		if(bean.getPage() != null && bean.getRows() != null) {
			 PageHelper.startPage(bean.getPage(), bean.getRows());
      } 
		return objectMapper.select(bean);
	}
	
	/**
	 * 产品工艺方案查询实体
	 * id
	 */
	public ProductTechnicsScheme getObjetById(String id) {
		return objectMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 产品工艺方案查询实体
	 * bean
	 */
	public ProductTechnicsScheme getObjectByBean(ProductTechnicsScheme bean) {
		return objectMapper.selectOne(bean);
	}
	
	/**
	 * 产品工艺方案数量
	 */
	public int getTotal(ProductTechnicsScheme bean) {
		return objectMapper.selectCount(bean);
	}
	/**
	 * 根据方案id查询工序
	 */
	public List getProcessList(ProductTechnicsSchemeProcess bean) {
		if(bean.getPage() != null && bean.getRows() != null) {
			 PageHelper.startPage(bean.getPage(), bean.getRows());
      } 
		return objectMapper.getProcessList(bean);
	}
	/**
	 * 根据产品id查询方案综合信息
	 */
	public List getSchemeMessage(ProductTechnicsScheme bean) {
		if(bean.getPage() != null && bean.getRows() != null) {
			 PageHelper.startPage(bean.getPage(), bean.getRows());
     } 
		return objectMapper.getSchemeMessage(bean);
	}
	/**
	 * 根据方案id查询刀具清单
	 */
	public List getSchemeToolList(ProductTechnicsSchemeProcess bean) {
		return objectMapper.getSchemeToolList(bean);
	}
	/**
	 * 根据方案id查询节拍汇总
	 */
	public List getRhythmList(ProductTechnicsSchemeProcess bean) {
		return objectMapper.getRhythmList(bean);
	}
	/**
	 * 查询所有工艺列表
	 */
	public List getAllList(ProductTechnicsScheme bean) {
		return objectMapper.getAllList(bean);
	}
	/**
	 * 查询所有工艺列表总数
	 */
	public int getAllListCount(ProductTechnicsScheme bean) {
		return objectMapper.getAllListCount(bean);
	}

    public List<ProductTechnicsScheme> getAll() {
		return objectMapper.getAll();
    }
}
