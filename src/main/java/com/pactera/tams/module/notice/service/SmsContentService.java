package com.pactera.tams.module.notice.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.pactera.tams.common.utils.DateUtils;
import com.pactera.tams.common.utils.IdGenerator;
import com.pactera.tams.module.notice.mapper.SmsContentMapper;
import com.pactera.tams.module.notice.model.SmsContent;

import tk.mybatis.mapper.entity.Example;
/**
 * 公告消息service
 * @author liule
 * @date ： 2017年12月25日
 * @version 1.0
 */
@Service
public class SmsContentService {
	
	@Autowired
	private SmsContentMapper objectMapper; 
	/**
	 * 根据id查找对象实体
	 * @param id
	 * @return
	 */
	public SmsContent getSmsContentById(String id) {
		return (SmsContent)objectMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 更新修改字段
	 * @param bean
	 */
	public void update(SmsContent bean) {
		objectMapper.updateByPrimaryKeySelective(bean);
	}
	/**
	 * 保存或修改
	 * @param bean
	 */
	public void saveOrUpdate(SmsContent bean,String current_user_id) {
		if(StringUtils.isNotBlank(bean.getId())) {
			//修改
			bean.setUpdate_by(current_user_id);
			bean.setUpdate_date(DateUtils.getCurrentTime());
			objectMapper.updateByPrimaryKeySelective(bean);
		}else {
			//新增
			bean.setCreate_by(current_user_id);
			bean.setStatus(0);//默认状态 0：(未读)
			bean.setCreate_date(DateUtils.getCurrentTime());
			bean.setId(IdGenerator.uuid());
			objectMapper.insert(bean);
		}
	}
	
	/**
	 * 根据id删除对象
	 * @param id
	 */
	public void delete(String id) {
		objectMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 分页查询
	 * @param bean
	 * @return
	 */
	public List getPageList(SmsContent bean) {
		if(bean.getPage() != null && bean.getRows() != null) {
			 PageHelper.startPage(bean.getPage(), bean.getRows());
		}		
        Example example = new Example(SmsContent.class);
        Example.Criteria criteria = example.createCriteria();
        example.setOrderByClause("create_date  desc ");
        return objectMapper.selectByExample(example);
	}
	
	/**
	 * 列表查询
	 * @param bean
	 * @return
	 */
	public List getList() {
        Example example = new Example(SmsContent.class);
        example.setOrderByClause("create_date  desc ");
        return objectMapper.selectByExample(example);
	}
}
