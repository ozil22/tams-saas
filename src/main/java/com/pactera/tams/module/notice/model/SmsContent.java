package com.pactera.tams.module.notice.model;

import javax.persistence.Table;

import com.pactera.tams.common.model.BaseEntity;
/**
 * 公告消息实体类
 * @author liule
 * @date ： 2017年12月25日
 * @version 1.0
 */
@Table(name = "sys_sms_content")
public class SmsContent extends BaseEntity{
	private String title;//标题
	private Integer status;//状态（0：未读，1：已读）
	private String sort;//排序
	private String content;//内容
	private String user_id_str;//查看人
	
	public String getUser_id_str() {
		return user_id_str;
	}
	public void setUser_id_str(String user_id_str) {
		this.user_id_str = user_id_str;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
