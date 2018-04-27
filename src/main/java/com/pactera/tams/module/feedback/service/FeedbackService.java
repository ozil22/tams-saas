package com.pactera.tams.module.feedback.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.github.pagehelper.PageHelper;
import com.pactera.tams.common.utils.DateUtils;
import com.pactera.tams.common.utils.IdGenerator;
import com.pactera.tams.common.utils.StringFormatUtils;
import com.pactera.tams.module.feedback.mapper.FeedbackMapper;
import com.pactera.tams.module.feedback.model.Feedback;

import tk.mybatis.mapper.entity.Example;


/**
* 
* @Author: mjh
* @Date: 2018-01-31 09:19:42
*/
@Service
public class FeedbackService {
	
	@Autowired
	private FeedbackMapper objectMapper; 
	
	
	/**
	 * 保存
	 * @param bean
	 */
	public ModelMap saveOrUpdate(Feedback bean) {
		ModelMap result = StringFormatUtils.getResultMessage();
		if(StringUtils.isNotEmpty(bean.getId())) {
			//更新
			bean.setUpdate_date(DateUtils.getCurrentTime());
			objectMapper.updateByPrimaryKeySelective(bean);
		}else {
			//新增
			bean.setCreate_date(DateUtils.getCurrentTime());
			bean.setId(IdGenerator.uuid());
			objectMapper.insert(bean);
		}
		return result;
	}
	
	/**
	 *刪除
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
	public List getPageList(Feedback bean) {
		if(bean.getPage() != null && bean.getRows() != null) {
			 PageHelper.startPage(bean.getPage(), bean.getRows());
        }
        Example example = new Example(Feedback.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(bean.getWorkNo())) {
            criteria.andLike("post_name", "%" + bean.getWorkNo() + "%");
        }
        if (StringUtils.isNotEmpty(bean.getSchemeNo())) {
            criteria.andLike("scheme_no", bean.getSchemeNo());
        }
        if (StringUtils.isNotEmpty(bean.getWorkStep())) {
            criteria.andLike("work_step", bean.getWorkStep());
        }
        example.setOrderByClause("create_date desc ");
        
        return objectMapper.selectByExample(example);
	}
	/**
	 * 查询列表
	 * @param p_code
	 * @return
	 */
	public List getList(Feedback bean) {
		if(bean.getPage() != null && bean.getRows() != null) {
			 PageHelper.startPage(bean.getPage(), bean.getRows());
       }		
        Example example = new Example(Feedback.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(bean.getWorkNo())) {
            criteria.andLike("post_name", "%" + bean.getWorkNo() + "%");
        }
        example.setOrderByClause("create_date desc ");
        return objectMapper.selectByExample(example);  		
	}
	/**
	 * 查询实体
	 * @param id
	 * @return
	 */
	public Feedback getObjectById(String id) {
		return objectMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 查询实体
	 * @param bean
	 * @return
	 */
	public Feedback getObjectByBean(Feedback bean) {
		return objectMapper.getBean(bean);
	}
/**
 * 分页查询
 */
	public List getFeedPageList(Feedback bean) {
		if(bean.getPage() != null && bean.getRows() != null) {
			 PageHelper.startPage(bean.getPage(), bean.getRows());
      }	
		return objectMapper.getPageList(bean);
	}
	/**
	 * 查询实体
	 */
}
