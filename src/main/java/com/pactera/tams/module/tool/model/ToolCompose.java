package com.pactera.tams.module.tool.model;

import javax.persistence.Table;

import com.pactera.tams.common.model.BaseEntity;

/**
 * 刀具
 * 
 * @author ztx 2018.1.2
 */
@Table(name = "tams_tool_compose")
public class ToolCompose extends BaseEntity {
	
	private String small_tool_id = null;
	
	private String tool_id = null;

	public String getSmall_tool_id() {
		return small_tool_id;
	}

	public void setSmall_tool_id(String small_tool_id) {
		this.small_tool_id = small_tool_id;
	}

	public String getTool_id() {
		return tool_id;
	}

	public void setTool_id(String tool_id) {
		this.tool_id = tool_id;
	}
	
	
	
}
