package com.pactera.tams.module.notice.mapper;

import java.util.List;

import com.pactera.tams.module.notice.model.Notice;

import tk.mybatis.mapper.common.Mapper;

/**
 * * @author ljp
 * 
 * @date ：2017.12.25 version 1.0
 **/
public interface NoticeMapper extends Mapper<Notice> {
	public List getNoticeList(Notice bean);
	public int getNoticeListCount(Notice bean);
}
