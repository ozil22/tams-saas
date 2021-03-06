package com.pactera.tams.module.video.model;

import javax.persistence.Table;

import com.pactera.tams.common.model.BaseEntity;

/**
 * 视频目录
 * 
 * @author ztx 2018.01.09
 */
@Table(name = "tams_video_catalog")
public class VideoCatalog extends BaseEntity {

	private String parent_id = null;

	private String catalog_name = null;

	private String tenant_id = null;

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getCatalog_name() {
		return catalog_name;
	}

	public void setCatalog_name(String catalog_name) {
		this.catalog_name = catalog_name;
	}

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

}
