package com.pactera.tams.module.product.mapper;

import java.util.List;

import com.pactera.tams.module.product.model.Specification;

import tk.mybatis.mapper.common.Mapper;
/**
 * 规格
 * @author js
 *2018.2.6
 */
public interface SpecificationMapper extends Mapper<Specification>{
	public List getList(Specification bean);
	public int getTotal(Specification bean);
}
