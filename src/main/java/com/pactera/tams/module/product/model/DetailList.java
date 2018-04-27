package com.pactera.tams.module.product.model;

import javax.persistence.Table;

import com.pactera.tams.common.model.BaseEntity;

/**
 * 刀具清单
 * @author ljp
 *
 */
@Table(name = "tams_detail_list")
public class DetailList extends BaseEntity{

	private String tool_no = null;
	
	private String tool_name = null;
	
	private String specification = null;
	
	private String tool_desc = null;
	
	private String tool_model = null;
	
	private String amount = null;
	
	private String tool_code = null;
	
	private String recommend_brand = null;
	
	private String price = null;
	
	private String sort = null;
	
	private String scheme_no = null;
	
	private String tenant_id = null;

	public String getTool_no() {
		return tool_no;
	}

	public void setTool_no(String tool_no) {
		this.tool_no = tool_no;
	}

	public String getTool_name() {
		return tool_name;
	}

	public void setTool_name(String tool_name) {
		this.tool_name = tool_name;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getTool_desc() {
		return tool_desc;
	}

	public void setTool_desc(String tool_desc) {
		this.tool_desc = tool_desc;
	}

	public String getTool_model() {
		return tool_model;
	}

	public void setTool_model(String tool_model) {
		this.tool_model = tool_model;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTool_code() {
		return tool_code;
	}

	public void setTool_code(String tool_code) {
		this.tool_code = tool_code;
	}

	public String getRecommend_brand() {
		return recommend_brand;
	}

	public void setRecommend_brand(String recommend_brand) {
		this.recommend_brand = recommend_brand;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getScheme_no() {
		return scheme_no;
	}

	public void setScheme_no(String scheme_no) {
		this.scheme_no = scheme_no;
	}

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}
	
	
}
