package com.pactera.tams.module.product.sevice;

import com.github.pagehelper.PageHelper;
import com.pactera.tams.common.utils.DateUtils;
import com.pactera.tams.module.product.mapper.ProductMapper;
import com.pactera.tams.module.product.model.Product;
import com.pactera.tams.module.solr.model.SolrModel;
import com.pactera.tams.module.solr.service.SolrModelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;

/**
 * 产品
 * @author ljp
 *2018.1.2
 */
@Service
public class ProductService {
	@Autowired
	ProductMapper productMapper;
	@Autowired
	SolrModelService solrModelService;
	
	/**
	 * 保存产品
	 */
	public int save(Product bean) throws Exception {
		int count = productMapper.insert(bean);;
        SolrModel solrModel = productMapper.getSolr(bean.getId());
        solrModelService.addBeanIndex(solrModel);
        return count;
	}
	/**
	 * 修改产品信息
	 */
	public void update(Product bean) {
		Example example = new Example(Product.class);
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotEmpty(bean.getId())){
    		criteria.andEqualTo("id", bean.getId());
    	}
		bean.setUpdate_date(DateUtils.getCurrentTime());
		productMapper.updateByExampleSelective(bean, example);

		int count = productMapper.insert(bean);
        SolrModel solrModel = productMapper.getSolr(bean.getId());
        StringBuilder solrql = new StringBuilder("index_name:product AND ");
        solrql.append("product_id").append(":").append(bean.getId());
        solrModelService.updateBeanByQuerySting(solrModel, solrql.toString());
	}
	/**
	 * 产品删除
	 */
	public void delete(String id) {
		SolrModel solrModel = productMapper.getSolr(id);
        StringBuilder solrql = new StringBuilder("index_name:product AND ");
        solrql.append("product_id").append(":").append(id);
        solrModelService.deleteByQuery(solrql.toString());
        productMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 查询
	 */
	public List getList(Product bean) {
		Example example = new Example(Product.class);
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(bean.getCatalog_id())) {
			criteria.andEqualTo("catalog_id", bean.getCatalog_id());
		}
		return productMapper.selectByExample(example);
	}
	/**
	 * 分页查询
	 */
	public List getPageList(Product bean) {
		if(bean.getPage() != null && bean.getRows() != null) {
			 PageHelper.startPage(bean.getPage(), bean.getRows());
       } 	
		return productMapper.getList(bean);
	}
	/**
	 * 查询实体
	 * id
	 */
	public Product getObjectById(String id) {
		return productMapper.selectByPrimaryKey(id);
	}
	/**
	 * 查询实体
	 * bean
	 */
	public Product getObjectByBean(Product bean) {
		return productMapper.selectOne(bean);
	}
	/**
	 * 获得产品数量
	 */
	public int getTotal(Product bean) {
		return productMapper.getTotal(bean);
	}
	/**
	 * 获取推荐产品五个
	 */
	public List getFiveList(Product bean) {
		PageHelper.startPage(bean.getPage(), bean.getRows());
		Example example = new Example(Product.class);
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(bean.getTenant_id())) {
			criteria.andEqualTo("tenant_id", bean.getTenant_id());
		}
		if (StringUtils.isNotEmpty(bean.getIs_recommend())) {
			criteria.andEqualTo("is_recommend", bean.getIs_recommend());
		}
		return productMapper.selectByExample(example);
	}

	public void addClick(String id) {
		productMapper.addClick(id);
	}
}
