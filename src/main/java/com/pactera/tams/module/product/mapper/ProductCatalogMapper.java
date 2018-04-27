package com.pactera.tams.module.product.mapper;

import java.util.List;

import com.pactera.tams.module.product.model.ProductCatalog;

import tk.mybatis.mapper.common.Mapper;
/**
 * 产品目录
 * @author ljp
 *2018.1.2
 */
public interface ProductCatalogMapper extends Mapper<ProductCatalog>{
	public List getList(ProductCatalog bean);
}
