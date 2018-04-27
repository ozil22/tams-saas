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
import com.pactera.tams.module.system.entity.SysRole;
import com.pactera.tams.module.system.entity.SysUser;
import com.pactera.tams.module.system.entity.SysUserRole;
import com.pactera.tams.module.system.mapper.SysRoleMapper;
import com.pactera.tams.module.system.mapper.SysUserRoleMapper;

import tk.mybatis.mapper.entity.Example;

/**
* 角色Service
* @Author: mjh
* @Date: 2018-03-19 16:11:06
*/
@Service
public class RoleService {
	
	private Logger logger = LoggerFactory.getLogger(RoleService.class);
	
	
	@Autowired
	private SysRoleMapper roleMapper; 
	
	@Autowired
	private SysUserRoleMapper userRoleMapper; 
	
	
	/**
	 * 新增
	 * @param bean
	 * @return
	 */
	public RestResult<SysRole> addRole(SysRole bean) {
		
		SysRole paramBean = new SysRole();
		paramBean.setName(bean.getName());
		paramBean.setDeleteFlag(Global.NORMAL);
		paramBean.setTenantId(UserUtils.getTenantId());
		
		List<SysRole> existList = this.getObjectByBean(paramBean);
		if(CollectionUtils.isNotEmpty(existList)) {
			return ResultUtils.genErrorResult(
					StringFormatUtils.format("【{name}】数据已存在", bean), 
					ErrorInfo.EXIST.getCode());
		}
		
		bean.setId(IdGenerator.uuid());
		bean.setCreateBy(UserUtils.getUserId());
		bean.setCreateDate(DateUtils.getNowDate());
		bean.setDeleteFlag(Global.NORMAL);
		bean.setTenantId(UserUtils.getTenantId());
		
		roleMapper.insertSelective(bean);
		
		if(logger.isDebugEnabled()) {
			logger.debug("新增角色 params={}", bean);
		}
		
		return ResultUtils.genSuccesResult(bean);
	}
	
	/**
	 * 更新
	 * @param bean
	 * @return
	 */
	public RestResult<SysRole> updateRole(SysRole bean) {
		
		if(null==bean.getId()) {
			return ResultUtils.genErrorResult(ErrorInfo.MISSING_PARAM);
		}
		
		SysRole existBean = this.getObjectById(bean.getId());
		if(null==existBean) {
			return ResultUtils.genErrorResult(ErrorInfo.NOT_EXIST);
		}
		
        Example example = new Example(SysRole.class);
        Example.Criteria criteria = example.createCriteria();
        
        criteria.andEqualTo("name", bean.getName());
        criteria.andEqualTo("deleteFlag", Global.NORMAL);
        criteria.andEqualTo("tenantId", UserUtils.getTenantId());
        //排除本条记录
        criteria.andNotEqualTo("id", bean.getId());
        
        int count = roleMapper.selectCountByExample(example);
        
		if(count>0) {
			return ResultUtils.genErrorResult(
					StringFormatUtils.format("【{name}】数据已存在", bean), 
					ErrorInfo.EXIST.getCode());
		}
		
		bean.setUpdateBy(UserUtils.getUserId());
		bean.setUpdateDate(DateUtils.getNowDate());
		
		roleMapper.updateByPrimaryKeySelective(bean);
		
		bean = roleMapper.selectByPrimaryKey(bean.getId());
		
		if(logger.isDebugEnabled()) {
			logger.debug("更新角色 params={}", bean);
		}
		
		return ResultUtils.genSuccesResult(bean);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public RestResult<SysRole> deleteRole(String id) {
		
		SysRole existBean = this.getObjectById(id);
		if(null==existBean) {
			return ResultUtils.genErrorResult(ErrorInfo.NOT_EXIST);
		}
		
		//根据角色查询角色关联的用户id
		SysUserRole userRole = new SysUserRole();
		userRole.setRoleId(id);
		List<SysUserRole> relations = userRoleMapper.select(userRole);
		if(CollectionUtils.isNotEmpty(relations)) {
			return ResultUtils.genErrorResult(ErrorInfo.EXIST_RELATED_USER);
		}
		
		SysRole deleteBean = new SysRole();
		deleteBean.setId(id);
		deleteBean.setDeleteFlag(BaseEntity.DELETE_FLAG_DELETED);
		deleteBean.setUpdateBy(UserUtils.getUserId());
		deleteBean.setUpdateDate(DateUtils.getNowDate());
		//逻辑删除
		roleMapper.updateByPrimaryKeySelective(deleteBean);
		
		if(logger.isDebugEnabled()) {
			logger.debug("删除角色信息 id={} data={}", id, existBean);
		}
		
		return ResultUtils.genSuccesResult(existBean);
	}
	

	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public SysRole getObjectById(String id) {
		SysRole bean = new SysRole();
		bean.setId(id);
		bean.setDeleteFlag(Global.NORMAL);
		return roleMapper.selectOne(bean);
	}
	
	/**
	 * 根据实体查询
	 * @param bean
	 * @return
	 */
	public List<SysRole> getObjectByBean(SysRole bean) {
		bean.setDeleteFlag(Global.NORMAL);
		bean.setTenantId(UserUtils.getTenantId());
		return roleMapper.select(bean);
	}
	
	/**
	 * 查询
	 * @param bean
	 * @return
	 */
	public List<SysRole> getList(SysRole bean) {
		
        Example example = new Example(SysRole.class);
        Example.Criteria criteria = example.createCriteria();
        //必带参数
        criteria.andEqualTo("deleteFlag", Global.NORMAL);
        criteria.andEqualTo("tenantId", UserUtils.getTenantId());
        
        if (StringUtils.isNotEmpty(bean.getName())) {
            criteria.andLike("name", "%" + bean.getName() + "%");
        }
        
        example.setOrderByClause("sort asc ");
        return roleMapper.selectByExample(example);  		
	}
	
	
	/**
	 * 分页查询
	 * @param bean
	 * @return
	 */
	public List<SysRole> getPageList(SysRole bean) {
		if(bean.getPageNum() != null && bean.getPageSize() != null) {
			 PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
        }
        Example example = new Example(SysRole.class);
        Example.Criteria criteria = example.createCriteria();
        
        //必带参数
        criteria.andEqualTo("deleteFlag", Global.NORMAL);
        criteria.andEqualTo("tenantId", UserUtils.getTenantId());
        
        if (StringUtils.isNotEmpty(bean.getName())) {
            criteria.andLike("name", "%" + bean.getName() + "%");
        }
       
        example.setOrderByClause("create_date desc ");
        
        return roleMapper.selectByExample(example);
	}

	/**
	 * 获取用户拥有的角色
	 * @param user
	 * @return
	 */
	public List<SysRole> getListByUser(SysUser user){
		return roleMapper.getListByUser(user);
	}

}
