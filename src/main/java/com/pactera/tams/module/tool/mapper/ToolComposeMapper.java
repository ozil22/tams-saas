package com.pactera.tams.module.tool.mapper;

import java.util.List;

import com.pactera.tams.module.tool.model.Tool;
import com.pactera.tams.module.tool.model.ToolCompose;

import tk.mybatis.mapper.common.Mapper;
/**
 * 刀具
 * @author ztx
 *2018.1.12
 */
public interface ToolComposeMapper extends Mapper<ToolCompose>{
	public List getList(ToolCompose bean);
	public List getSmallToolId(ToolCompose bean);
	public List getToolList(ToolCompose bean);
	public List getToolOne(ToolCompose bean);
}
