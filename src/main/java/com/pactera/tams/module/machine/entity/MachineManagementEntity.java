package com.pactera.tams.module.machine.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import com.pactera.tams.common.model.BasesEntity;

@Table(name = "tams_machine_tool")
public class MachineManagementEntity extends BasesEntity{
   
	
	/**
     * 主键
     *//*
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;*/

    public MachineManagementEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
     * 设备编号
     */
    @Column(name = "DEVICE_CODE")
    private String deviceCode;

    /**
     * 机床名称
     */
    @Column(name = "MACHINE_NAME")
    private String machineName;

    /**
     * 机床类型
     */
    @Column(name = "MACHINE_TYPE")
    private Byte machineType;

    /**
     * 图片路径
     */
    @Column(name = "PIC_PATH")
    private String picPath;

    /**
     * 品牌
     */
    @Column(name = "BRAND")
    private String brand;

    /**
     * 型号
     */
    @Column(name = "MODE")
    private String mode;

    /**
     * 原产地
     */
    @Column(name = "ORIGIN_AREA")
    private String originArea;

    /**
     * 机供货单位名称
     */
    @Column(name = "SUPPLIER_NAME")
    private String supplierName;

    /**
     * 加工精度
     */
    @Column(name = "MACHING_PRECISION")
    private String machingPrecision;

    /**
     * 标签
     */
    @Column(name = "MACHINE_LABLE")
    private String machineLable;

    /**
     * 加工范围
     */
    @Column(name = "MACHING_RANGE")
    private String machingRange;

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

   /* *//**
     * 更新日期
     *//*
    @Column(name = "UPDATE_DATE")
    private String updateDate;

    *//**
     * 更新人
     *//*
    @Column(name = "UPDATE_BY")
    private String updateBy;

    *//**
     * 创建日期
     *//*
    @Column(name = "CREATE_DATE")
    private String createDate;

    *//**
     * 创建人
     *//*
    @Column(name = "CREATE_BY")
    private String createBy;
*/
    

    /**
     * 获取设备编号
     *
     * @return DEVICE_CODE - 设备编号
     */
    public String getDeviceCode() {
        return deviceCode;
    }

    /**
     * 设置设备编号
     *
     * @param deviceCode 设备编号
     */
    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

   

	/**
     * 获取机床名称
     *
     * @return MACHINE_NAME - 机床名称
     */
    public String getMachineName() {
        return machineName;
    }

    /**
     * 设置机床名称
     *
     * @param machineName 机床名称
     */
    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    /**
     * 获取图片路径
     *
     * @return PIC_PATH - 图片路径
     */
    public String getPicPath() {
        return picPath;
    }

    /**
     * 设置图片路径
     *
     * @param picPath 图片路径
     */
    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    /**
     * 获取品牌
     *
     * @return BRAND - 品牌
     */
    public String getBrand() {
        return brand;
    }

    /**
     * 设置品牌
     *
     * @param brand 品牌
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * 获取型号
     *
     * @return MODE - 型号
     */
    public String getMode() {
        return mode;
    }

    /**
     * 设置型号
     *
     * @param mode 型号
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * 获取原产地
     *
     * @return ORIGIN_AREA - 原产地
     */
    public String getOriginArea() {
        return originArea;
    }

    /**
     * 设置原产地
     *
     * @param originArea 原产地
     */
    public void setOriginArea(String originArea) {
        this.originArea = originArea;
    }

    /**
     * 获取机供货单位名称
     *
     * @return SUPPLIER_NAME - 机供货单位名称
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * 设置机供货单位名称
     *
     * @param supplierName 机供货单位名称
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * 获取加工精度
     *
     * @return MACHING_PRECISION - 加工精度
     */
    public String getMachingPrecision() {
        return machingPrecision;
    }

    /**
     * 设置加工精度
     *
     * @param machingPrecision 加工精度
     */
    public void setMachingPrecision(String machingPrecision) {
        this.machingPrecision = machingPrecision;
    }

    /**
     * 获取标签
     *
     * @return MACHINE_LABLE - 标签
     */
    public String getMachineLable() {
        return machineLable;
    }

    /**
     * 设置标签
     *
     * @param machineLable 标签
     */
    public void setMachineLable(String machineLable) {
        this.machineLable = machineLable;
    }

    /**
     * 获取加工范围
     *
     * @return MACHING_RANGE - 加工范围
     */
    public String getMachingRange() {
        return machingRange;
    }

    /**
     * 设置加工范围
     *
     * @param machingRange 加工范围
     */
    public void setMachingRange(String machingRange) {
        this.machingRange = machingRange;
    }

    /**
     * 获取租户id
     *
     * @return TENANT_ID - 租户id
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * 设置租户id
     *
     * @param tenantId 租户id
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

	public Byte getMachineType() {
		return machineType;
	}

	public void setMachineType(Byte machineType) {
		this.machineType = machineType;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

   /* *//**
     * 获取更新日期
     *
     * @return UPDATE_DATE - 更新日期
     *//*
    public String getUpdateDate() {
        return updateDate;
    }

    *//**
     * 设置更新日期
     *
     * @param updateDate 更新日期
     *//*
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    *//**
     * 获取更新人
     *
     * @return UPDATE_BY - 更新人
     *//*
    public String getUpdateBy() {
        return updateBy;
    }

    *//**
     * 设置更新人
     *
     * @param updateBy 更新人
     *//*
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    *//**
     * 获取创建日期
     *
     * @return CREATE_DATE - 创建日期
     *//*
    public String getCreateDate() {
        return createDate;
    }

    *//**
     * 设置创建日期
     *
     * @param createDate 创建日期
     *//*
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    *//**
     * 获取创建人
     *
     * @return CREATE_BY - 创建人
     *//*
    public String getCreateBy() {
        return createBy;
    }

    *//**
     * 设置创建人
     *
     * @param createBy 创建人
     *//*
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }*/
}