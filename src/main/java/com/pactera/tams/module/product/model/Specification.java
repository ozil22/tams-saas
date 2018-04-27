package com.pactera.tams.module.product.model;

import javax.persistence.Table;

import com.pactera.tams.common.model.BaseEntity;

/**
 * 规格
 * 
 * @author js 2018.2.6
 */
@Table(name = "tams_specification")
public class Specification extends BaseEntity {
   
	private String specification = null;
	
	private String spec_name = null;
	
	private String pic_path = null;
	
	private String sort = null;
	
	private String tenant_id = null;
	
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getSpec_name() {
		return spec_name;
	}
	public void setSpec_name(String spec_name) {
		this.spec_name = spec_name;
	}
	public String getpic_path() {
		return pic_path;
	}
	public void setpic_path(String pic_path) {
		this.pic_path = pic_path;
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
