package com.pactera.tams.common.utils.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * 导出工艺工序
 * @author ljp
 *
 */
public class PruductTechnicsProcess {
	
	@Excel(name="类型" , orderNum="0")
	private String type;
	@Excel(name="工序号" , orderNum="1")
	private String step_no;
	@Excel(name="内容" , orderNum="2")
	private String content;
	@Excel(name="要求" , orderNum="3")
	private String step_require;
	@Excel(name="刀号" , orderNum="4")
	private String tool_no;
	@Excel(name="刀具名称" , orderNum="5")
	private String tool_name;
	@Excel(name="刀片型号" , orderNum="6")
	private String tool_model;
	@Excel(name="刀齿" , orderNum="7")
	private String tool_tooth;
	@Excel(name="直径" , orderNum="8")
	private String diameter;
	@Excel(name="单长" , orderNum="9")
	private String lenght;
	@Excel(name="数量" , orderNum="10")
	private String amount;
	@Excel(name="工件数" , orderNum="11")
	private String piece_number;
	@Excel(name="总长" , orderNum="12")
	private String total;
	@Excel(name="S" , orderNum="13")
	private String s;
	@Excel(name="F" , orderNum="14")
	private String f;
	@Excel(name="Ap" , orderNum="15")
	private String ap;
	@Excel(name="Ae" , orderNum="16")
	private String ae;
	@Excel(name="Fn" , orderNum="17")
	private String fn;
	@Excel(name="Vc" , orderNum="18")
	private String vc;
	@Excel(name="Fz" , orderNum="19")
	private String fz;
	@Excel(name="切削时间" , orderNum="20")
	private String cut_date;
	@Excel(name="X,Y,Z轴时间" , orderNum="21")
	private String xyz_date;
	@Excel(name="A-轴旋转时间" , orderNum="22")
	private String a_date;
	@Excel(name="换刀时间" , orderNum="23")
	private String change_tool_date;
	@Excel(name="其他时间" , orderNum="24")
	private String other_date;
	@Excel(name="工步时" , orderNum="25")
	private String workstep_date;

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

	public String getTool_model() {
		return tool_model;
	}

	public void setTool_model(String tool_model) {
		this.tool_model = tool_model;
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

	@Override
	public String toString() {
		return "PruductTechnicsProcess [type=" + type + ", step_no=" + step_no + ", content=" + content
				+ ", step_require=" + step_require + ", tool_no=" + tool_no + ", tool_name=" + tool_name
				+ ", tool_model=" + tool_model + ", tool_tooth=" + tool_tooth + ", diameter=" + diameter + ", lenght="
				+ lenght + ", amount=" + amount + ", piece_number=" + piece_number + ", total=" + total + ", s=" + s
				+ ", f=" + f + ", ap=" + ap + ", ae=" + ae + ", fn=" + fn + ", vc=" + vc + ", fz=" + fz + ", cut_date="
				+ cut_date + ", xyz_date=" + xyz_date + ", a_date=" + a_date + ", change_tool_date=" + change_tool_date
				+ ", other_date=" + other_date + ", workstep_date=" + workstep_date + "]";
	}
	
}
