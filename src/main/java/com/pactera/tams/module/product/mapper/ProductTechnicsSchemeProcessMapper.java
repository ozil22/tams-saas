package com.pactera.tams.module.product.mapper;

import com.pactera.tams.module.product.model.ProductTechnicsSchemeProcess;
import com.pactera.tams.module.solr.model.SolrModel;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 产品工艺方案工序
 * @author ljp
 *2018.1.12
 */
public interface ProductTechnicsSchemeProcessMapper extends Mapper<ProductTechnicsSchemeProcess>{
    List<SolrModel> findAllSolr();
    SolrModel getSolr(String id);
}
