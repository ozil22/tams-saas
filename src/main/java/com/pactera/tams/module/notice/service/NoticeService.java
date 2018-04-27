package com.pactera.tams.module.notice.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.pactera.tams.module.notice.mapper.NoticeMapper;
import com.pactera.tams.module.notice.model.Notice;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;


/** * @author  ljp 
@date ：2017.12.25 
@version 1.0     
**/
@Service
public class NoticeService {
	@Autowired
	NoticeMapper noticeMapper;
	
	/**
	 * 保存
	 */
	public int saveNotice(Notice bean) {
		
		return noticeMapper.insert(bean);
	}
	/**
	 * 删除
	 */
	public void delete(String id) {
		noticeMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 分页查询
	 */
	public List getpageList(Notice bean) {
		if(bean.getPage() != null && bean.getRows() != null) {
			 PageHelper.startPage(bean.getPage(), bean.getRows());
		}
		Example example = new Example(Notice.class);
		example.setOrderByClause("create_date desc");
		return noticeMapper.getNoticeList(bean);
	}
	/**
	 * 列表数
	 */
	public int getTotal(Notice bean) {

		return noticeMapper.getNoticeListCount(bean);
	}
	/**
	 * 查询列表
	 */
	public List getList(Notice bean){
		
		return noticeMapper.getNoticeList(bean);
	}
	/**
	 * 查询实体
	 * @param id
	 */
	public Notice getObjectById(String id) {
		return noticeMapper.selectByPrimaryKey(id);
	}
	/**
	 * 查询实体
	 * @param bean
	 */
	public Notice getObjectByBean(Notice bean) {
		return noticeMapper.selectOne(bean);
	}
	/**
	 * 更新状态
	 */
	public void updateNotice(Notice bean) {
		noticeMapper.updateByPrimaryKeySelective(bean);
	}
	/**
	 * 查前五条通知公告
	 */
	public List getFiveNotice() {
		PageHelper.startPage(1, 5);
		Example example = new Example(Notice.class);
		example.setOrderByClause("create_date desc");
		return noticeMapper.selectByExample(example);
	}
}
