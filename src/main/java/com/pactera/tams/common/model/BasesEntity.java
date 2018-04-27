package com.pactera.tams.common.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * 基础信息
 *
 * @author wushuping 
 * @since 2018-02-24 11:00
 */
public class BasesEntity {
	 @Id
	    @Column(name = "Id")
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private String id;

	    @Transient
	    private Integer page = 1;        // 页数

	    @Transient
	    private Integer rows = 10;       //每页的记录数
	    
		@Transient
	    private Integer pageSize = 0;
	    
		/**
	     * 更新人
	     */
	    @Column(name = "UPDATE_BY")
	    private String updateBy;
	    /**
	     * 更新日期 
	     */
	    @Column(name = "UPDATE_DATE")
	    private String updateDate;
	    /**
	     * 创建日期
	     */
	    @Column(name = "CREATE_DATE")
	    private String createDate;

	    /**
	     * 创建人
	     */
	    @Column(name = "CREATE_BY")
	    private String createBy;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Integer getPage() {
			return page;
		}

		public void setPage(Integer page) {
			this.page = page;
		}

		public Integer getRows() {
			return rows;
		}

		public void setRows(Integer rows) {
			this.rows = rows;
		}

		public Integer getPageSize() {
			return pageSize;
		}

		public void setPageSize(Integer pageSize) {
			this.pageSize = pageSize;
		}


		public String getUpdateBy() {
			return updateBy;
		}

		public void setUpdateBy(String updateBy) {
			this.updateBy = updateBy;
		}

		
		public String getUpdateDate() {
			return updateDate;
		}

		public void setUpdateDate(String updateDate) {
			this.updateDate = updateDate;
		}

		public String getCreateDate() {
			return createDate;
		}

		public void setCreateDate(String createDate) {
			this.createDate = createDate;
		}

		public String getCreateBy() {
			return createBy;
		}

		public void setCreateBy(String createBy) {
			this.createBy = createBy;
		}	
		
}
