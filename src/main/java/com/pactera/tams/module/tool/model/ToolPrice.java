package com.pactera.tams.module.tool.model;

import javax.persistence.Table;

import com.pactera.tams.common.model.BaseEntity;

/**
 * 刀具价格
 * 
 * @author js 2018.2.8
 */
@Table(name = "tams_tool_price")
public class ToolPrice extends BaseEntity{
	private String tool_id = null;
	private String tool_price = null;
	private String price_date = null;
	private String sort = null;
	private String tenant_id = null;
	public String getTool_id() {
		return tool_id;
	}
	public void setTool_id(String tool_id) {
		this.tool_id = tool_id;
	}
	public String getTool_price() {
		return tool_price;
	}
	public void setTool_price(String tool_price) {
		this.tool_price = tool_price;
	}
	public String getPrice_date() {
		return price_date;
	}
	public void setPrice_date(String price_date) {
		this.price_date = price_date;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getTenant_id() {
		return tenant_id;
	}
	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}
	
	
}