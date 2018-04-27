package com.pactera.tams.module.tool.mapper;

import java.util.List;

import com.pactera.tams.module.tool.model.ToolCatalog;

import tk.mybatis.mapper.common.Mapper;
/**
 * 刀具目录
 * @author ztx
 *2018.1.12
 */
public interface ToolCatalogMapper extends Mapper<ToolCatalog>{
	public List getList(ToolCatalog bean);
}
