package com.pactera.tams.module.system.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.pactera.tams.common.entity.BaseEntity;
import com.pactera.tams.common.entity.Global;
import com.pactera.tams.common.entity.RestResult;
import com.pactera.tams.common.entity.ResultUtils;
import com.pactera.tams.common.utils.DateUtils;
import com.pactera.tams.common.utils.IdGenerator;
import com.pactera.tams.common.utils.StringFormatUtils;
import com.pactera.tams.common.utils.StringUtils;
import com.pactera.tams.common.utils.UserUtils;
import com.pactera.tams.exception.ErrorInfo;
import com.pactera.tams.module.system.entity.OpeTenant;
import com.pactera.tams.module.system.mapper.OpeTenantMapper;

import tk.mybatis.mapper.entity.Example;

/**
* 租户Service
* @Author: mjh
* @Date: 2018-03-19 16:11:06
*/
@Service
public class TenantService {
	
	private Logger logger = LoggerFactory.getLogger(TenantService.class);
	
	
	@Autowired
	private OpeTenantMapper objectMapper; 
	
	/**
	 * 新增
	 * @param bean
	 * @return
	 */
	public RestResult<OpeTenant> addTenant(OpeTenant bean) {
		
		OpeTenant paramBean = new OpeTenant();
		paramBean.setName(bean.getName());
		paramBean.setDeleteFlag(Global.NORMAL);
		
		List<OpeTenant> existList = this.getObjectByBean(paramBean);
		if(CollectionUtils.isNotEmpty(existList)) {
			return ResultUtils.genErrorResult(
					StringFormatUtils.format("【{name}】数据已存在", bean), 
					ErrorInfo.EXIST.getCode());
		}
		
		bean.setId(IdGenerator.uuid());
		bean.setCreateBy(UserUtils.getUserId());
		bean.setCreateDate(DateUtils.getNowDate());
		bean.setDeleteFlag(Global.NORMAL);
		
		objectMapper.insertSelective(bean);
		
		if(logger.isDebugEnabled()) {
			logger.debug("新增租户 params={}", bean);
		}
		
		return ResultUtils.genSuccesResult(bean);
	}
	
	/**
	 * 更新
	 * @param bean
	 * @return
	 */
	public RestResult<OpeTenant> updateTenant(OpeTenant bean) {
		
		if(null==bean.getId()) {
			return ResultUtils.genErrorResult(ErrorInfo.MISSING_PARAM);
		}
		
		OpeTenant existBean = this.getObjectById(bean.getId());
		if(null==existBean) {
			return ResultUtils.genErrorResult(ErrorInfo.NOT_EXIST);
		}
		
        Example example = new Example(OpeTenant.class);
        Example.Criteria criteria = example.createCriteria();
        
        criteria.andEqualTo("name", bean.getName());
        criteria.andEqualTo("deleteFlag", Global.NORMAL);
        criteria.andEqualTo("tenantId", UserUtils.getTenantId());
        //排除本条记录
        criteria.andNotEqualTo("id", bean.getId());
        
        int count = objectMapper.selectCountByExample(example);
        
		if(count>0) {
			return ResultUtils.genErrorResult(
					StringFormatUtils.format("【{name}】数据已存在", bean), 
					ErrorInfo.EXIST.getCode());
		}
		
		bean.setUpdateBy(UserUtils.getUserId());
		bean.setUpdateDate(DateUtils.getNowDate());
		
		objectMapper.updateByPrimaryKeySelective(bean);
		
		bean = objectMapper.selectByPrimaryKey(bean.getId());
		
		if(logger.isDebugEnabled()) {
			logger.debug("更新租户 params={}", bean);
		}
		
		return ResultUtils.genSuccesResult(bean);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public RestResult<OpeTenant> deleteTenant(String id) {
		
		OpeTenant existBean = this.getObjectById(id);
		if(null==existBean) {
			return ResultUtils.genErrorResult(ErrorInfo.NOT_EXIST);
		}
		
		OpeTenant deleteBean = new OpeTenant();
		deleteBean.setId(id);
		deleteBean.setDeleteFlag(BaseEntity.DELETE_FLAG_DELETED);
		deleteBean.setUpdateBy(UserUtils.getUserId());
		deleteBean.setUpdateDate(DateUtils.getNowDate());
		//逻辑删除
		objectMapper.updateByPrimaryKeySelective(deleteBean);
		
		if(logger.isDebugEnabled()) {
			logger.debug("删除租户信息 id={} data={}", id, existBean);
		}
		
		return ResultUtils.genSuccesResult(existBean);
	}
	

	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public OpeTenant getObjectById(String id) {
		OpeTenant bean = new OpeTenant();
		bean.setId(id);
		bean.setDeleteFlag(Global.NORMAL);
		return objectMapper.selectOne(bean);
	}
	
	/**
	 * 根据实体查询
	 * @param bean
	 * @return
	 */
	public List<OpeTenant> getObjectByBean(OpeTenant bean) {
		bean.setDeleteFlag(Global.NORMAL);
		return objectMapper.select(bean);
	}
	
	/**
	 * 查询
	 * @param bean
	 * @return
	 */
	public List<OpeTenant> getList(OpeTenant bean) {
		
        Example example = new Example(OpeTenant.class);
        Example.Criteria criteria = example.createCriteria();
        //必带参数
        criteria.andEqualTo("deleteFlag", Global.NORMAL);
        criteria.andEqualTo("tenantId", UserUtils.getTenantId());
        
        if (StringUtils.isNotEmpty(bean.getName())) {
            criteria.andLike("name", "%" + bean.getName() + "%");
        }
        
        example.setOrderByClause("sort asc ");
        return objectMapper.selectByExample(example);  		
	}
	
	
	/**
	 * 分页查询
	 * @param bean
	 * @return
	 */
	public List<OpeTenant> getPageList(OpeTenant bean) {
		if(bean.getPageNum() != null && bean.getPageSize() != null) {
			 PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
        }
        Example example = new Example(OpeTenant.class);
        Example.Criteria criteria = example.createCriteria();
        
        //必带参数
        criteria.andEqualTo("deleteFlag", Global.NORMAL);
        criteria.andEqualTo("tenantId", UserUtils.getTenantId());
        
        if (StringUtils.isNotEmpty(bean.getName())) {
            criteria.andLike("name", "%" + bean.getName() + "%");
        }
       
        example.setOrderByClause("create_date desc ");
        
        return objectMapper.selectByExample(example);
	}

	

}
