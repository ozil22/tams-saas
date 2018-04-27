package com.pactera.tams.module.feedback.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pactera.tams.common.model.BaseEntity;



/**
* 用户反馈实体
* @Author: mjh
* @Date: 2018-01-30 18:41:48
*/
@Table(name = "tams_user_feed_back")
public class Feedback extends BaseEntity{
	

    /**
     * 工号
     */
    @Column(name = "WORK_NO")
    private String workNo;

    /**
     * 零件
     */
    @Column(name = "TOOL_NAME")
    private String toolName;

    /**
     * 工艺编号
     */
    @Column(name = "TECHNICS_CODE")
    private String technicsCode;

    /**
     * 工序号
     */
    @Column(name = "SCHEME_NO")
    private String schemeNo;

    /**
     * 工步
     */
    @Column(name = "WORK_STEP")
    private String workStep;

    /**
     * 开始时间
     */
    @Column(name = "START_TIME")
    private String startTime;

    /**
     * 结束时间
     */
    @Column(name = "END_TIME")
    private String endTime;

    /**
     * S
     */
    @Column(name = "S")
    private BigDecimal s;

    /**
     * F
     */
    @Column(name = "F")
    private BigDecimal f;

    /**
     * AP
     */
    @Column(name = "AP")
    private BigDecimal ap;

    /**
     * FN
     */
    @Column(name = "FN")
    private BigDecimal fn;

    /**
     * VC
     */
    @Column(name = "VC")
    private BigDecimal vc;

    /**
     * FZ
     */
    @Column(name = "FZ")
    private BigDecimal fz;
    /**
     * AE
     */
    private BigDecimal ae;
    /**
     * 加工数量
     */
    @Column(name = "MAKE_AMOUNT")
    private Integer makeAmount;

    /**
     * 废品数量
     */
    @Column(name = "WASTE_AMOUNT")
    private Integer wasteAmount;

    /**
     * 回用品
     */
    @Column(name = "RECOVERY")
    private Integer recovery;

    /**
     * 合格数量
     */
    @Column(name = "QUALIFIED_AMOUNT")
    private Integer qualifiedAmount;

    /**
     * 加工中断原因
     */
    @Column(name = "INTERRUPT_REASON")
    private String interruptReason;

    /**
     * 消耗品
     */
    @Column(name = "CONSUME")
    private String consume;

    /**
     * 消耗品数量
     */
    @Column(name = "CONSUME_AMOUNT")
    private Integer consumeAmount;

    /**
     * 排序
     */
    @Column(name = "SORT")
    private Integer sort;

    /**
     * 租户id
     */
    @Column(name = "TENANT_ID")
    private String tenantId;
    
    /**
     * 设备信息
     */
    @Column(name = "DEVICE")
    private String device;
    
    /**
     * 组件串
     * @return
     */
    @Column(name = "TOOL_COMPONENT")
    private String toolComponent;
    
    @Transient
    private String product_name;
    @Transient
    private String product_specification;
    @Transient
    private String technics_name;
    @Transient
    private String code;
    @Transient
    private String process_name;
    @Transient
    private String workStep_name;
    
	public String getWorkNo() {
		return workNo;
	}

	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}

	public String getToolName() {
		return toolName;
	}

	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	public String getTechnicsCode() {
		return technicsCode;
	}

	public void setTechnicsCode(String technicsCode) {
		this.technicsCode = technicsCode;
	}

	public String getSchemeNo() {
		return schemeNo;
	}

	public void setSchemeNo(String schemeNo) {
		this.schemeNo = schemeNo;
	}

	public String getWorkStep() {
		return workStep;
	}

	public void setWorkStep(String workStep) {
		this.workStep = workStep;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getS() {
		return s;
	}

	public void setS(BigDecimal s) {
		this.s = s;
	}

	public BigDecimal getF() {
		return f;
	}

	public void setF(BigDecimal f) {
		this.f = f;
	}

	public BigDecimal getAp() {
		return ap;
	}

	public void setAp(BigDecimal ap) {
		this.ap = ap;
	}

	public BigDecimal getFn() {
		return fn;
	}

	public void setFn(BigDecimal fn) {
		this.fn = fn;
	}

	public BigDecimal getVc() {
		return vc;
	}

	public void setVc(BigDecimal vc) {
		this.vc = vc;
	}

	public BigDecimal getFz() {
		return fz;
	}

	public void setFz(BigDecimal fz) {
		this.fz = fz;
	}

	public Integer getMakeAmount() {
		return makeAmount;
	}

	public void setMakeAmount(Integer makeAmount) {
		this.makeAmount = makeAmount;
	}

	public Integer getWasteAmount() {
		return wasteAmount;
	}

	public void setWasteAmount(Integer wasteAmount) {
		this.wasteAmount = wasteAmount;
	}

	public Integer getRecovery() {
		return recovery;
	}

	public void setRecovery(Integer recovery) {
		this.recovery = recovery;
	}

	public Integer getQualifiedAmount() {
		return qualifiedAmount;
	}

	public void setQualifiedAmount(Integer qualifiedAmount) {
		this.qualifiedAmount = qualifiedAmount;
	}

	public String getInterruptReason() {
		return interruptReason;
	}

	public void setInterruptReason(String interruptReason) {
		this.interruptReason = interruptReason;
	}

	public String getConsume() {
		return consume;
	}

	public void setConsume(String consume) {
		this.consume = consume;
	}

	public Integer getConsumeAmount() {
		return consumeAmount;
	}

	public void setConsumeAmount(Integer consumeAmount) {
		this.consumeAmount = consumeAmount;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getToolComponent() {
		return toolComponent;
	}

	public void setToolComponent(String toolComponent) {
		this.toolComponent = toolComponent;
	}

	public BigDecimal getAe() {
		return ae;
	}

	public void setAe(BigDecimal ae) {
		this.ae = ae;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_specification() {
		return product_specification;
	}

	public void setProduct_specification(String product_specification) {
		this.product_specification = product_specification;
	}

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

	public String getProcess_name() {
		return process_name;
	}

	public void setProcess_name(String process_name) {
		this.process_name = process_name;
	}

	public String getWorkStep_name() {
		return workStep_name;
	}

	public void setWorkStep_name(String workStep_name) {
		this.workStep_name = workStep_name;
	}
    
    
}
