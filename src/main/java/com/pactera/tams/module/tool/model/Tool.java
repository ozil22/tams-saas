package com.pactera.tams.module.tool.model;

import java.util.List;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.pactera.tams.common.model.BaseEntity;

/**
 * 刀具
 * 
 * @author ztx 2018.1.2
 */
@Table(name = "tams_tool")
public class Tool extends BaseEntity {
	
	private String tool_name = null;
	
	private String specification = null;
	
	private String product_pic = null;
	
	private String patrs_pic = null;
	
	private String video_id = null;
	
	private String three_dimensional = null;
	
	private String tool_label = null;
	
	private String content = null;
	
	private String catalog_id = null;
	
	private String product_id = null;

	private String brand_id = null;
	
	private String status = null;
	
	private String type = null;
	
	private String is_recommend = null;
	
	private Integer tool_way = null;
	
	private String tenant_id = null;
	@Transient
    private List combList_relation_table = null;
	
	private String trade_mark = null;

	private String spec_name = null;
	
	private String sample_pic = null;

	private String sort = null;

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

	public String getProduct_pic() {
		return product_pic;
	}

	public void setProduct_pic(String product_pic) {
		this.product_pic = product_pic;
	}

	public String getPatrs_pic() {
		return patrs_pic;
	}

	public void setPatrs_pic(String patrs_pic) {
		this.patrs_pic = patrs_pic;
	}

	public String getVideo_id() {
		return video_id;
	}

	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}

	public String getThree_dimensional() {
		return three_dimensional;
	}

	public void setThree_dimensional(String three_dimensional) {
		this.three_dimensional = three_dimensional;
	}

	public String getTool_label() {
		return tool_label;
	}

	public void setTool_label(String tool_label) {
		this.tool_label = tool_label;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCatalog_id() {
		return catalog_id;
	}

	public void setCatalog_id(String catalog_id) {
		this.catalog_id = catalog_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIs_recommend() {
		return is_recommend;
	}

	public void setIs_recommend(String is_recommend) {
		this.is_recommend = is_recommend;
	}

	public Integer getTool_way() {
		return tool_way;
	}

	public void setTool_way(Integer tool_way) {
		this.tool_way = tool_way;
	}

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

	public List getCombList_relation_table() {
		return combList_relation_table;
	}

	public void setCombList_relation_table(List combList_relation_table) {
		this.combList_relation_table = combList_relation_table;
	}

	public String getTrade_mark() {
		return trade_mark;
	}

	public void setTrade_mark(String trade_mark) {
		this.trade_mark = trade_mark;
	}

	public String getSpec_name() {
		return spec_name;
	}

	public void setSpec_name(String spec_name) {
		this.spec_name = spec_name;
	}

	public String getSample_pic() {
		return sample_pic;
	}

	public void setSample_pic(String sample_pic) {
		this.sample_pic = sample_pic;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	
	
	
}
