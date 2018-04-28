package com.pactera.tams.module.product.model;

import com.pactera.tams.common.model.BaseEntity;

import javax.persistence.Table;

/**
 * 产品
 * 
 * @author ljp 2018.1.2
 */
@Table(name = "tams_product")
public class Product extends BaseEntity {
	
	private String product_name = null;
	
	private String specification = null;
	
	private String specification_name = null;
	
	private String material_quality = null;
	
	private String material_quality_spec = null;
	
	private String trade_mark = null;
	
	private String product_pic = null;
	
	private String patrs_pic = null;
	
	private String video_id = null;
	
	private String three_dimensional = null;
	
	private String product_label = null;
	
	private String content = null;
	
	private String catalog_id = null;
	
	private String status = null;
	
	private String is_recommend = null;
	
	private String tenant_id = null;
	private Integer click_num = null;

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
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

	public String getProduct_label() {
		return product_label;
	}

	public void setProduct_label(String product_label) {
		this.product_label = product_label;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIs_recommend() {
		return is_recommend;
	}

	public void setIs_recommend(String is_recommend) {
		this.is_recommend = is_recommend;
	}

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

	public String getMaterial_quality() {
		return material_quality;
	}

	public void setMaterial_quality(String material_quality) {
		this.material_quality = material_quality;
	}

	public String getMaterial_quality_spec() {
		return material_quality_spec;
	}

	public void setMaterial_quality_spec(String material_quality_spec) {
		this.material_quality_spec = material_quality_spec;
	}

	public String getTrade_mark() {
		return trade_mark;
	}

	public void setTrade_mark(String trade_mark) {
		this.trade_mark = trade_mark;
	}

	public String getSpecification_name() {
		return specification_name;
	}

	public void setSpecification_name(String specification_name) {
		this.specification_name = specification_name;
	}

	public Integer getClick_num() {
		return click_num;
	}

	public void setClick_num(Integer click_num) {
		this.click_num = click_num;
	}
}
