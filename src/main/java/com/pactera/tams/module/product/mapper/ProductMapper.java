package com.pactera.tams.module.product.mapper;

import com.pactera.tams.module.product.model.Product;
import com.pactera.tams.module.solr.model.SolrModel;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
/**
 * 产品
 * @author ljp
 *2018.1.2
 */
public interface ProductMapper extends Mapper<Product>{
	List getList(Product bean);
	int getTotal(Product bean);
	List<SolrModel> findAllSolr();
	SolrModel getSolr(String id);
	List<Product> findHot();
	void addClick(String id);

    List<Product> findAll();
}
