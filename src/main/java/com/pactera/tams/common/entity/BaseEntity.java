package com.pactera.tams.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
* 基础实体类
* @Author: mjh
* @Date: 2018-03-19 16:04:18
*/
public class BaseEntity implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	/**
	 * 删除标记（0：正常；1：删除；）
	 */
	public static final Byte DELETE_FLAG_NORMAL = 0;
	public static final Byte DELETE_FLAG_DELETED = 1;
	

	@Id
    @Column(name = "id")
    private String id;
    
    /**
     * 创建日期
     */
    @Column(name = "create_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private String createBy;


    /**
     * 更新日期
     */
    @Column(name = "update_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

    /**
     * 更新人
     */
    @Column(name = "update_by")
    private String updateBy;
    
    /**
     * 删除标识，1删除，0未删除
     */
    @Column(name = "delete_flag")
    private Byte deleteFlag;
    
    /**
     * 排序
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;

    
    /**
     * 当前页码，默认第1页
     */
    @Transient
    private Integer pageNum = 1;

    /**
     * 每页的记录数,默认10条
     */
	@Transient
    private Integer pageSize = 10;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Byte getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Byte deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        BaseEntity that = (BaseEntity) obj;
        return null == this.getId() ? false : this.getId().equals(that.getId());
    }
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }


}
