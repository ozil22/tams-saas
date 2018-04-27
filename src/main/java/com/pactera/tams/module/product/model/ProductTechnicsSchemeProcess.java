package com.pactera.tams.module.product.model;

import java.util.List;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.pactera.tams.common.model.BaseEntity;

/**
 * 产品工艺方案工序
 * @author ljp
 *2018.1.12
 */
@Table(name="tams_product_technics_scheme_process")
public class ProductTechnicsSchemeProcess extends BaseEntity{
	
	private String scheme_pic=null;
	
	private String device_specs=null;

	private String device_name=null;

	private String pic_no=null;

	private String material_specs=null;

	private String day_time=null;

	private String month_time=null;

	private String year_time=null;

	private String equipment_rate=null;

	private String rhythm=null;

	private String output=null;

	private String remark=null;

	private String technics_id=null;
	
	private String machine_situation=null;
	
	private String tenant_id = null;
	
	private String sort = null;
	
	@Transient
	private List process_relation_table = null;

	

	public String getScheme_pic() {
		return scheme_pic;
	}

	public void setScheme_pic(String scheme_pic) {
		this.scheme_pic = scheme_pic;
	}

	public String getDevice_specs() {
		return device_specs;
	}

	public void setDevice_specs(String device_specs) {
		this.device_specs = device_specs;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getPic_no() {
		return pic_no;
	}

	public void setPic_no(String pic_no) {
		this.pic_no = pic_no;
	}

	public String getMaterial_specs() {
		return material_specs;
	}

	public void setMaterial_specs(String material_specs) {
		this.material_specs = material_specs;
	}

	public String getDay_time() {
		return day_time;
	}

	public void setDay_time(String day_time) {
		this.day_time = day_time;
	}

	public String getMonth_time() {
		return month_time;
	}

	public void setMonth_time(String month_time) {
		this.month_time = month_time;
	}

	public String getYear_time() {
		return year_time;
	}

	public void setYear_time(String year_time) {
		this.year_time = year_time;
	}

	public String getEquipment_rate() {
		return equipment_rate;
	}

	public void setEquipment_rate(String equipment_rate) {
		this.equipment_rate = equipment_rate;
	}

	public String getRhythm() {
		return rhythm;
	}

	public void setRhythm(String rhythm) {
		this.rhythm = rhythm;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTechnics_id() {
		return technics_id;
	}

	public void setTechnics_id(String technics_id) {
		this.technics_id = technics_id;
	}

	public String getMachine_situation() {
		return machine_situation;
	}

	public void setMachine_situation(String machine_situation) {
		this.machine_situation = machine_situation;
	}

	public List getProcess_relation_table() {
		return process_relation_table;
	}

	public void setProcess_relation_table(List process_relation_table) {
		this.process_relation_table = process_relation_table;
	}

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	

}
