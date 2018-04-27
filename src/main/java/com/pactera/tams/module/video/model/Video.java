package com.pactera.tams.module.video.model;

import javax.persistence.Table;

import com.pactera.tams.common.model.BaseEntity;

/**
 * 视频
 * 
 * @author ztx
 * @date ：2017年12月29日
 * @version 1.0
 **/
@Table(name = "tams_video")
public class Video extends BaseEntity{
	
	private String video_name = null; //视频名称
	
	private String upload_name = null;  //上传初始名称
	
	private String video_pic = null; //视频路径
	
	private String content = null; //内容
	
	private String catalog_id = null; //所属目录
	
	private String status = null; //状态(0：未审核，1：已审核，2：审核不通过)
	
	private Integer sort = null; //排序
	
	private String cover_pic = null; //封面图片
	
	
	public String getCover_pic() {
		return cover_pic;
	}

	public void setCover_pic(String cover_pic) {
		this.cover_pic = cover_pic;
	}

	public String getVideo_name() {
		return video_name;
	}

	public void setVideo_name(String video_name) {
		this.video_name = video_name;
	}

	public String getUpload_name() {
		return upload_name;
	}

	public void setUpload_name(String upload_name) {
		this.upload_name = upload_name;
	}

	public String getVideo_pic() {
		return video_pic;
	}

	public void setVideo_pic(String video_pic) {
		this.video_pic = video_pic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCatalog_id() {
		return catalog_id;
	}

	public void setCatalog_id(String catalog_id) {
		this.catalog_id = catalog_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}
