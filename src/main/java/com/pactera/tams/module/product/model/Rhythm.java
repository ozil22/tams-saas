package com.pactera.tams.module.product.model;

import javax.persistence.Table;

import com.pactera.tams.common.model.BaseEntity;

/**
 * 节拍汇总
 * 
 * @author ljp
 *
 */
@Table(name="tams_rhythm_collect")
public class Rhythm extends BaseEntity {

	private String parts_name = null;
	
	private String scheme_no = null;
	
	private String device_date = null;
	
	private String feed_date = null;
	
	private String rhythm = null;
	
	private String annual_output = null;
	
	private String production_program = null;
	
	private String machine_amount = null;
	
	private String device_mode = null;
	
	private String sort = null;
	
	private String tenant_id = null;

	public String getParts_name() {
		return parts_name;
	}

	public void setParts_name(String parts_name) {
		this.parts_name = parts_name;
	}

	public String getScheme_no() {
		return scheme_no;
	}

	public void setScheme_no(String scheme_no) {
		this.scheme_no = scheme_no;
	}

	public String getDevice_date() {
		return device_date;
	}

	public void setDevice_date(String device_date) {
		this.device_date = device_date;
	}

	public String getFeed_date() {
		return feed_date;
	}

	public void setFeed_date(String feed_date) {
		this.feed_date = feed_date;
	}

	public String getRhythm() {
		return rhythm;
	}

	public void setRhythm(String rhythm) {
		this.rhythm = rhythm;
	}

	public String getAnnual_output() {
		return annual_output;
	}

	public void setAnnual_output(String annual_output) {
		this.annual_output = annual_output;
	}

	public String getProduction_program() {
		return production_program;
	}

	public void setProduction_program(String production_program) {
		this.production_program = production_program;
	}

	public String getMachine_amount() {
		return machine_amount;
	}

	public void setMachine_amount(String machine_amount) {
		this.machine_amount = machine_amount;
	}

	public String getDevice_mode() {
		return device_mode;
	}

	public void setDevice_mode(String device_mode) {
		this.device_mode = device_mode;
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
