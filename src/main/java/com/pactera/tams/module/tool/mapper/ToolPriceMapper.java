package com.pactera.tams.module.tool.mapper;

import java.util.List;

import com.pactera.tams.module.tool.model.ToolPrice;

import tk.mybatis.mapper.common.Mapper;
/**
 * 刀具
 * @author js
 *2018.2.8
 */
public interface ToolPriceMapper extends Mapper<ToolPrice>{
	public List getList(ToolPrice bean);
	public int getTotal(ToolPrice bean);
}
