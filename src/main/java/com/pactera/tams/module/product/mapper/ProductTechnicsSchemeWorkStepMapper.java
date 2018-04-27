package com.pactera.tams.module.product.mapper;

import com.pactera.tams.module.product.model.ProductTechnicsSchemeWorkStep;
import com.pactera.tams.module.solr.model.SolrModel;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 产品工艺方案工序工步
 * @author ljp
 *2018.1.15
 */
public interface ProductTechnicsSchemeWorkStepMapper extends Mapper<ProductTechnicsSchemeWorkStep>{
	List<SolrModel> findAllSolr();
	SolrModel getSolr(String id);
}
