package com.pactera.tams.module.notice.model;

import javax.persistence.Table;

import com.pactera.tams.common.model.BaseEntity;

/**
 * 通知
 * 
 * @author ljp
 * @date ：2017年12月25日
 * @version 1.0
 **/
@Table(name = "sys_notice")
public class Notice extends BaseEntity {
	private String title = null;
	
	private String content = null;
	
	private String 	status = null;
	
	private String user_id_str = null;
	
	private String tenant_id = null;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUser_id_str() {
		return user_id_str;
	}

	public void setUser_id_str(String user_id_str) {
		this.user_id_str = user_id_str;
	}

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}
	
}
