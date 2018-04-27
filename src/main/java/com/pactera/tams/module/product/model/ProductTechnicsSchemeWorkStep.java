package com.pactera.tams.module.product.model;

import com.pactera.tams.common.model.BaseEntity;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * 产品工艺方案工序工步
 * 
 * @author ljp 2018.1.12
 */
@Table(name="tams_product_technics_scheme_work_step")
public class ProductTechnicsSchemeWorkStep extends BaseEntity {

	private String type = null;
	
	private String step_no = null;
	
	private String content = null;
	
	private String step_require = null;
	
	private String tool_no = null;
	
	private String tool_name = null;
	
	private String tool_desc = null;
	
	private String tool_tooth = null;
	
	private String diameter = null;
	
	private String lenght = null;
	
	private String amount = null;
	
	private String piece_number = null;
	
	private String total = null;
	
	private String s = null;
	
	private String f = null;
	
	private String ap = null;
	
	private String ae = null;
	
	private String fn = null;
	
	private String vc = null;
	
	private String fz = null;
	
	private String cut_date = null;
	
	private String xyz_date = null;
	
	private String a_date = null;
	
	private String change_tool_date = null;
	
	private String other_date = null;
	
	private String workstep_date = null;
	
	private String scheme_id = null;
	
	private String tenant_id = null;
	
	private String video_id = null;
	
	@Transient
	private List tool_no_relation_table =null;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStep_no() {
		return step_no;
	}

	public void setStep_no(String step_no) {
		this.step_no = step_no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStep_require() {
		return step_require;
	}

	public void setStep_require(String step_require) {
		this.step_require = step_require;
	}

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

	public String getTool_desc() {
		return tool_desc;
	}

	public void setTool_desc(String tool_desc) {
		this.tool_desc = tool_desc;
	}

	public String getTool_tooth() {
		return tool_tooth;
	}

	public void setTool_tooth(String tool_tooth) {
		this.tool_tooth = tool_tooth;
	}

	public String getDiameter() {
		return diameter;
	}

	public void setDiameter(String diameter) {
		this.diameter = diameter;
	}

	public String getLenght() {
		return lenght;
	}

	public void setLenght(String lenght) {
		this.lenght = lenght;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPiece_number() {
		return piece_number;
	}

	public void setPiece_number(String piece_number) {
		this.piece_number = piece_number;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}

	public String getAp() {
		return ap;
	}

	public void setAp(String ap) {
		this.ap = ap;
	}

	public String getAe() {
		return ae;
	}

	public void setAe(String ae) {
		this.ae = ae;
	}

	public String getFn() {
		return fn;
	}

	public void setFn(String fn) {
		this.fn = fn;
	}

	public String getVc() {
		return vc;
	}

	public void setVc(String vc) {
		this.vc = vc;
	}

	public String getFz() {
		return fz;
	}

	public void setFz(String fz) {
		this.fz = fz;
	}

	public String getCut_date() {
		return cut_date;
	}

	public void setCut_date(String cut_date) {
		this.cut_date = cut_date;
	}

	public String getXyz_date() {
		return xyz_date;
	}

	public void setXyz_date(String xyz_date) {
		this.xyz_date = xyz_date;
	}

	public String getA_date() {
		return a_date;
	}

	public void setA_date(String a_date) {
		this.a_date = a_date;
	}

	public String getChange_tool_date() {
		return change_tool_date;
	}

	public void setChange_tool_date(String change_tool_date) {
		this.change_tool_date = change_tool_date;
	}

	public String getOther_date() {
		return other_date;
	}

	public void setOther_date(String other_date) {
		this.other_date = other_date;
	}

	public String getWorkstep_date() {
		return workstep_date;
	}

	public void setWorkstep_date(String workstep_date) {
		this.workstep_date = workstep_date;
	}

	public String getScheme_id() {
		return scheme_id;
	}

	public void setScheme_id(String scheme_id) {
		this.scheme_id = scheme_id;
	}

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

	public List getTool_no_relation_table() {
		return tool_no_relation_table;
	}

	public void setTool_no_relation_table(List tool_no_relation_table) {
		this.tool_no_relation_table = tool_no_relation_table;
	}

	public String getVideo_id() {
		return video_id;
	}

	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}
	
	

}
