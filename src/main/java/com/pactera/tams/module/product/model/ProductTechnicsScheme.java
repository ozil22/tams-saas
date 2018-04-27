package com.pactera.tams.module.product.model;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.pactera.tams.common.model.BaseEntity;

/**
 * 产品工艺方案
 * @author ljp
 * 2018.1.12
 *
 */

@Table(name="tams_product_technics_scheme")
public class ProductTechnicsScheme extends BaseEntity{

	private String technics_name=null;

	private String code=null;

	private String type=null;

	private String is_excellent=null;

	private String technics_pic=null;

	private String is_recommend=null;

	private String product_id=null;
	
	private String tenant_id = null;
	
	@Transient
	private String product_name=null;
	
	@Transient
	private String specification=null;

	public String getTechnics_name() {
		return technics_name;
	}

	public void setTechnics_name(String technics_name) {
		this.technics_name = technics_name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIs_excellent() {
		return is_excellent;
	}

	public void setIs_excellent(String is_excellent) {
		this.is_excellent = is_excellent;
	}

	public String getTechnics_pic() {
		return technics_pic;
	}

	public void setTechnics_pic(String technics_pic) {
		this.technics_pic = technics_pic;
	}

	public String getIs_recommend() {
		return is_recommend;
	}

	public void setIs_recommend(String is_recommend) {
		this.is_recommend = is_recommend;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

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
	
}
