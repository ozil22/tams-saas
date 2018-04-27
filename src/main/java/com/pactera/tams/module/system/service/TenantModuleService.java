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
import com.pactera.tams.module.system.entity.OpeTenantModule;
import com.pactera.tams.module.system.mapper.OpeTenantModuleMapper;

import tk.mybatis.mapper.entity.Example;

/**
* 岗位Service
* @Author: mjh
* @Date: 2018-03-19 16:11:06
*/
@Service
public class TenantModuleService {
	
	private Logger logger = LoggerFactory.getLogger(TenantModuleService.class);
	
	
	@Autowired
	private OpeTenantModuleMapper objectMapper; 
	
	/**
	 * 新增
	 * @param bean
	 * @return
	 */
	public RestResult<OpeTenantModule> addTenantModule(OpeTenantModule bean) {
		
		OpeTenantModule paramBean = new OpeTenantModule();
		paramBean.setModuleId(bean.getModuleId());
		
		paramBean.setDeleteFlag(Global.NORMAL);
		
		List<OpeTenantModule> existList = this.getObjectByBean(paramBean);
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
			logger.debug("新增岗位 params={}", bean);
		}
		
		return ResultUtils.genSuccesResult(bean);
	}
	
	/**
	 * 更新
	 * @param bean
	 * @return
	 */
	public RestResult<OpeTenantModule> updateTenantModule(OpeTenantModule bean) {
		
		if(null==bean.getId()) {
			return ResultUtils.genErrorResult(ErrorInfo.MISSING_PARAM);
		}
		
		OpeTenantModule existBean = this.getObjectById(bean.getId());
		if(null==existBean) {
			return ResultUtils.genErrorResult(ErrorInfo.NOT_EXIST);
		}
		
        Example example = new Example(OpeTenantModule.class);
        Example.Criteria criteria = example.createCriteria();
        
        criteria.andEqualTo("moduleId", bean.getModuleId());
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
			logger.debug("更新岗位 params={}", bean);
		}
		
		return ResultUtils.genSuccesResult(bean);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public RestResult<OpeTenantModule> deleteTenantModule(String id) {
		
		OpeTenantModule existBean = this.getObjectById(id);
		if(null==existBean) {
			return ResultUtils.genErrorResult(ErrorInfo.NOT_EXIST);
		}
		
		OpeTenantModule deleteBean = new OpeTenantModule();
		deleteBean.setId(id);
		deleteBean.setDeleteFlag(BaseEntity.DELETE_FLAG_DELETED);
		deleteBean.setUpdateBy(UserUtils.getUserId());
		deleteBean.setUpdateDate(DateUtils.getNowDate());
		//逻辑删除
		objectMapper.updateByPrimaryKeySelective(deleteBean);
		
		if(logger.isDebugEnabled()) {
			logger.debug("删除岗位信息 id={} data={}", id, existBean);
		}
		
		return ResultUtils.genSuccesResult(existBean);
	}
	

	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public OpeTenantModule getObjectById(String id) {
		OpeTenantModule bean = new OpeTenantModule();
		bean.setId(id);
		bean.setDeleteFlag(Global.NORMAL);
		return objectMapper.selectOne(bean);
	}
	
	/**
	 * 根据实体查询
	 * @param bean
	 * @return
	 */
	public List<OpeTenantModule> getObjectByBean(OpeTenantModule bean) {
		bean.setDeleteFlag(Global.NORMAL);
		return objectMapper.select(bean);
	}
	
	/**
	 * 查询
	 * @param bean
	 * @return
	 */
	public List<OpeTenantModule> getList(OpeTenantModule bean) {
		
        Example example = new Example(OpeTenantModule.class);
        Example.Criteria criteria = example.createCriteria();
        //必带参数
        criteria.andEqualTo("deleteFlag", Global.NORMAL);
        criteria.andEqualTo("tenantId", UserUtils.getTenantId());
        
        if (StringUtils.isNotEmpty(bean.getModuleName())) {
            criteria.andLike("moduleName", "%" + bean.getModuleName() + "%");
        }
        
        example.setOrderByClause("sort asc ");
        return objectMapper.selectByExample(example);  		
	}
	
	
	/**
	 * 分页查询
	 * @param bean
	 * @return
	 */
	public List<OpeTenantModule> getPageList(OpeTenantModule bean) {
		if(bean.getPageNum() != null && bean.getPageSize() != null) {
			 PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
        }
        Example example = new Example(OpeTenantModule.class);
        Example.Criteria criteria = example.createCriteria();
        
        //必带参数
        criteria.andEqualTo("deleteFlag", Global.NORMAL);
        criteria.andEqualTo("tenantId", UserUtils.getTenantId());
        
        if (StringUtils.isNotEmpty(bean.getModuleName())) {
            criteria.andLike("moduleName", "%" + bean.getModuleName() + "%");
        }
       
        example.setOrderByClause("create_date desc ");
        
        return objectMapper.selectByExample(example);
	}

	

}
