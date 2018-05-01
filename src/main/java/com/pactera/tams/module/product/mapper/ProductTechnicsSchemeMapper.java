package com.pactera.tams.module.product.mapper;

import com.pactera.tams.module.product.model.ProductTechnicsScheme;
import com.pactera.tams.module.product.model.ProductTechnicsSchemeProcess;
import com.pactera.tams.module.solr.model.SolrModel;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
/**
 * 产品工艺方案
 * @author ljp
 * 2018.1.12
 *
 */
public interface ProductTechnicsSchemeMapper extends Mapper<ProductTechnicsScheme>{
	List getProcessList(ProductTechnicsSchemeProcess bean);
	List getSchemeMessage(ProductTechnicsScheme bean);
	List getSchemeToolList(ProductTechnicsSchemeProcess bean);
	List getRhythmList(ProductTechnicsSchemeProcess bean);
	List getAllList(ProductTechnicsScheme bean);
	int getAllListCount(ProductTechnicsScheme bean);
	List<SolrModel> findAllSolr();
	SolrModel getSolr(String id);

    List<ProductTechnicsScheme> getAll();
}
