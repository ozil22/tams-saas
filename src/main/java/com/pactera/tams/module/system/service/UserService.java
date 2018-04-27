package com.pactera.tams.module.system.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
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
import com.pactera.tams.module.system.entity.SysOrg;
import com.pactera.tams.module.system.entity.SysUser;
import com.pactera.tams.module.system.mapper.SysUserMapper;

/**
* 用户Service
* @Author: mjh
* @Date: 2018-03-19 16:11:21
*/
@Service
public class UserService {
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	
	@Autowired
	private SysUserMapper userMapper;
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private UserOrgService userOrgService;
	
	
	/**
	 * 新增
	 * @param bean
	 * @return
	 */
	public RestResult<SysUser> addUser(SysUser bean) {
		
		//重复校验
		RestResult<SysUser> validate = validateAddUser(bean);
		if(false==validate.isSuccess()) {
			return validate;
		}
		 
		bean.setId(IdGenerator.uuid());
		bean.setCreateBy(UserUtils.getUserId());
		bean.setCreateDate(DateUtils.getNowDate());
		bean.setEnabled(Global.ENABLE);
		bean.setDeleteFlag(Global.NORMAL);
		bean.setTenantId(UserUtils.getTenantId());
		
		//保存盐和密码
		String slat = StringUtils.getRandomStr(10);
		//默认密码
		String md5Pwd = new Md5Hash(Global.DEFAULT_PASSWORD, slat).toString();
		bean.setPassword(md5Pwd);
		bean.setSalt(slat);
		
		userMapper.insertSelective(bean);
		
		//保存用户的组织信息，注意是属性orgIds，不是orgId
		if(StringUtils.isNotBlank(bean.getOrgIds())) {
			userOrgService.saveOrgIdsByUser(bean.getId(), bean.getOrgIds());
		}
		
		if(logger.isDebugEnabled()) {
			logger.debug("新增用户 params={}", bean);
		}
		
		return ResultUtils.genSuccesResult(bean);
	}
	

	
	/**
	 * 更新
	 * @param bean
	 * @return
	 */
	public RestResult<SysUser> updateUser(SysUser bean) {
		
		if(null==bean.getId()) {
			return ResultUtils.genErrorResult(ErrorInfo.MISSING_PARAM);
		}
		
		SysUser existBean = this.getObjectById(bean.getId());
		if(null==existBean) {
			return ResultUtils.genErrorResult(ErrorInfo.NOT_EXIST);
		}
		//重复校验
		RestResult<SysUser> validate = validateUpdateUser(bean);
		if(false==validate.isSuccess()) {
			return validate;
		}
		
		bean.setUpdateBy(UserUtils.getUserId());
		bean.setUpdateDate(DateUtils.getNowDate());
		
		userMapper.updateByPrimaryKeySelective(bean);
		
		//保存用户的组织信息，注意是属性orgIds，不是orgId
		if(StringUtils.isNotBlank(bean.getOrgIds())) {
			userOrgService.saveOrgIdsByUser(bean.getId(), bean.getOrgIds());
		}
		
		bean = userMapper.selectByPrimaryKey(bean.getId());
		
		if(logger.isDebugEnabled()) {
			logger.debug("更新用户 params={}", bean);
		}
		
		return ResultUtils.genSuccesResult(bean);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public RestResult<SysUser> deleteUser(String id) {
		
		SysUser existBean = this.getObjectById(id);
		if(null==existBean) {
			return ResultUtils.genErrorResult(ErrorInfo.NOT_EXIST);
		}
		
		SysUser deleteBean = new SysUser();
		deleteBean.setId(id);
		deleteBean.setDeleteFlag(BaseEntity.DELETE_FLAG_DELETED);
		deleteBean.setUpdateBy(UserUtils.getUserId());
		deleteBean.setUpdateDate(DateUtils.getNowDate());
		//逻辑删除
		userMapper.updateByPrimaryKeySelective(deleteBean);
		
		//删除用户的组织信息
		userOrgService.saveOrgIdsByUser(id, null);
		
		if(logger.isDebugEnabled()) {
			logger.debug("删除用户信息 id={} data={}", id, existBean);
		}
		
		return ResultUtils.genSuccesResult(existBean);
	}
	

	
	/**
	 * 根据主键查询（必须有id属性）
	 * @param id
	 * @return
	 */
	public SysUser getObjectById(String id) {
		SysUser bean = new SysUser();
		bean.setId(id);
		bean.setDeleteFlag(Global.NORMAL);
		return userMapper.selectOne(bean);
	}
	
	/**
	 * 根据实体查询
	 * @param bean
	 * @return
	 */
	public List<SysUser> getListByBean(SysUser bean) {
		bean.setDeleteFlag(Global.NORMAL);
		bean.setTenantId(UserUtils.getTenantId());
		return userMapper.select(bean);
	}
	
	/**
	 * 获取用户列表 
	 * 主要实现查询某个组织及其子级组织下的所有用户
	 * @param bean
	 * @return
	 */
	public List<SysUser> getList(SysUser bean) {
		bean.setDeleteFlag(Global.NORMAL);
		bean.setTenantId(UserUtils.getTenantId());
		//设置用户所在组织父级id集合
		if(StringUtils.isNotEmpty(bean.getOrgId())) {
			SysOrg org = orgService.getObjectById(bean.getOrgId());
			if(null!=org && StringUtils.isNotEmpty(org.getParentIds())) {
				bean.setOrgParentIds(org.getParentIds());
			}
		}
        return userMapper.getList(bean);  		
	}
	
	/**
	 * 分页查询
	 * 已经实现查询某个组织及其子级组织下的所有用户
	 * @param bean
	 * @return
	 */
	public List<SysUser> getPageList(SysUser bean) {
		if(bean.getPageNum() != null && bean.getPageSize() != null) {
			 PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
        }
		bean.setDeleteFlag(Global.NORMAL);
		bean.setTenantId(UserUtils.getTenantId());
		//设置用户所在组织父级id集合
		if(StringUtils.isNotEmpty(bean.getOrgId())) {
			SysOrg org = orgService.getObjectById(bean.getOrgId());
			if(null!=org && StringUtils.isNotEmpty(org.getParentIds())) {
				bean.setOrgParentIds(org.getParentIds());
			}
		}
        return userMapper.getList(bean); 
	}

	
	/**
	 * 根据登录名（登录名、工号或者手机号）查询用户
	 * @param loginName
	 * @param tenantId 
	 * @return
	 */
	public List<SysUser> getUserListByLoginInfo(String loginName, String tenantId) {
        return userMapper.getUserListByLoginInfo(loginName, tenantId);	
	}
	
	/**
	 * 直接更新，不做重复校验
	 * @param bean
	 * @return
	 */
	public RestResult<SysUser> updateUserDirectly(SysUser bean) {
		
		if(null==bean.getId()) {
			return ResultUtils.genErrorResult(ErrorInfo.MISSING_PARAM);
		}
		
		SysUser existBean = this.getObjectById(bean.getId());
		if(null==existBean) {
			return ResultUtils.genErrorResult(ErrorInfo.NOT_EXIST);
		}
		
		bean.setUpdateBy(UserUtils.getUserId());
		bean.setUpdateDate(DateUtils.getNowDate());
		
		userMapper.updateByPrimaryKeySelective(bean);
		
		bean = userMapper.selectByPrimaryKey(bean.getId());
		
		if(logger.isDebugEnabled()) {
			logger.debug("更新用户 params={}", bean);
		}
		
		return ResultUtils.genSuccesResult(bean);
	}
	
	/**
	 * 用户重复校验（新增）
	 * @param bean
	 * @return
	 */
	public RestResult<SysUser> validateAddUser(SysUser bean){
		
		SysUser paramBean = new SysUser();
		paramBean.setDeleteFlag(Global.NORMAL);
		paramBean.setTenantId(UserUtils.getTenantId());
		
		//登录名校验
		paramBean.setLoginName(bean.getLoginName());
		List<SysUser> existList = this.getListByBean(paramBean);
		
		if(CollectionUtils.isNotEmpty(existList)) {
			return ResultUtils.genErrorResult(
					StringFormatUtils.format("【{loginName}】数据已存在", bean), 
					ErrorInfo.EXIST.getCode());
		}
		
		//工号校验
		paramBean.setLoginName(null);
		paramBean.setNo(bean.getNo());
		existList = this.getListByBean(paramBean);
		
		if(CollectionUtils.isNotEmpty(existList)) {
			return ResultUtils.genErrorResult(
					StringFormatUtils.format("【{no}】数据已存在", bean), 
					ErrorInfo.EXIST.getCode());
		}
		
		//手机号校验
		paramBean.setNo(null);
		paramBean.setMobile(bean.getMobile());
		existList = this.getListByBean(paramBean);
		
		if(CollectionUtils.isNotEmpty(existList)) {
			return ResultUtils.genErrorResult(
					StringFormatUtils.format("【{mobile}】数据已存在", bean), 
					ErrorInfo.EXIST.getCode());
		}
		
		return ResultUtils.genSuccesResult(bean);
	}
	
	/**
	 * 用户重复校验（修改）
	 * @param bean
	 * @return
	 */
	public RestResult<SysUser> validateUpdateUser(SysUser bean){
		
		SysUser paramBean = new SysUser();
		paramBean.setDeleteFlag(Global.NORMAL);
		paramBean.setTenantId(UserUtils.getTenantId());
		
		//登录名校验
		paramBean.setLoginName(bean.getLoginName());
		List<SysUser> existList = this.getListByBean(paramBean);
		
		if(CollectionUtils.isNotEmpty(existList)) {
			for (SysUser existBean : existList) {
				//排除自己
				if(!bean.getId().equals(existBean.getId())) {
					return ResultUtils.genErrorResult(
							StringFormatUtils.format("【{loginName}】数据已存在", bean), 
							ErrorInfo.EXIST.getCode());
				}
			}
		}
		
		//工号校验
		paramBean.setLoginName(null);
		paramBean.setNo(bean.getNo());
		existList = this.getListByBean(paramBean);
		
		if(CollectionUtils.isNotEmpty(existList)) {
			for (SysUser existBean : existList) {
				//排除自己
				if(!bean.getId().equals(existBean.getId())) {
					return ResultUtils.genErrorResult(
							StringFormatUtils.format("【{no}】数据已存在", bean), 
							ErrorInfo.EXIST.getCode());
				}
			}
		}
		
		//手机号校验
		paramBean.setNo(null);
		paramBean.setMobile(bean.getMobile());
		existList = this.getListByBean(paramBean);
		
		if(CollectionUtils.isNotEmpty(existList)) {
			for (SysUser existBean : existList) {
				//排除自己
				if(!bean.getId().equals(existBean.getId())) {
					return ResultUtils.genErrorResult(
							StringFormatUtils.format("【{mobile}】数据已存在", bean), 
							ErrorInfo.EXIST.getCode());
				}
			}
		}
		
		return ResultUtils.genSuccesResult(bean);
	}
	
	
	

}
