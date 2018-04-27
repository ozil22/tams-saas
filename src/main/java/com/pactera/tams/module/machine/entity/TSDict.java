package com.pactera.tams.module.machine.entity;

import javax.persistence.*;

@Table(name = "sys_dict")
public class TSDict {
    /**
     * 主键
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 父编码（类型）
     */
    @Column(name = "P_CODE")
    private String pCode;

    /**
     * 编码
     */
    @Column(name = "CODE")
    private String code;

    /**
     * 全称
     */
    @Column(name = "FULL_NAME")
    private String fullName;

    /**
     * 简写名称
     */
    @Column(name = "SHORT_NAME")
    private String shortName;

    /**
     * 英文名称
     */
    @Column(name = "ENGLISH_NAME")
    private String englishName;

    /**
     * 是否显示0:不显示 1:显示
     */
    @Column(name = "IS_SHOW")
    private Integer isShow;

    /**
     * 排序
     */
    @Column(name = "SORT")
    private Integer sort;

    /**
     * 获取主键
     *
     * @return ID - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取父编码（类型）
     *
     * @return P_CODE - 父编码（类型）
     */
    public String getpCode() {
        return pCode;
    }

    /**
     * 设置父编码（类型）
     *
     * @param pCode 父编码（类型）
     */
    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    /**
     * 获取编码
     *
     * @return CODE - 编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置编码
     *
     * @param code 编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取全称
     *
     * @return FULL_NAME - 全称
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置全称
     *
     * @param fullName 全称
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 获取简写名称
     *
     * @return SHORT_NAME - 简写名称
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 设置简写名称
     *
     * @param shortName 简写名称
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * 获取英文名称
     *
     * @return ENGLISH_NAME - 英文名称
     */
    public String getEnglishName() {
        return englishName;
    }

    /**
     * 设置英文名称
     *
     * @param englishName 英文名称
     */
    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    /**
     * 获取是否显示0:不显示 1:显示
     *
     * @return IS_SHOW - 是否显示0:不显示 1:显示
     */
    public Integer getIsShow() {
        return isShow;
    }

    /**
     * 设置是否显示0:不显示 1:显示
     *
     * @param isShow 是否显示0:不显示 1:显示
     */
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    /**
     * 获取排序
     *
     * @return SORT - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }
}