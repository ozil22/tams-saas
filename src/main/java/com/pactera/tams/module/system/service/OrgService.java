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
import com.pactera.tams.common.utils.TreeUtils;
import com.pactera.tams.common.utils.UserUtils;
import com.pactera.tams.exception.ErrorInfo;
import com.pactera.tams.module.system.entity.SysOrg;
import com.pactera.tams.module.system.entity.SysUser;
import com.pactera.tams.module.system.mapper.SysOrgMapper;

import tk.mybatis.mapper.entity.Example;

/**
* 组织机构Service
* @Author: mjh
* @Date: 2018-03-19 16:11:06
*/
@Service
public class OrgService {
	
	private Logger logger = LoggerFactory.getLogger(OrgService.class);
	
	
	@Autowired
	private SysOrgMapper objectMapper; 
	
	@Autowired
	private UserService userService; 
	
	
	/**
	 * 新增
	 * @param bean
	 * @return
	 */
	public RestResult<SysOrg> addOrg(SysOrg bean) {
		
		SysOrg paramBean = new SysOrg();
		paramBean.setName(bean.getName());
		paramBean.setDeleteFlag(Global.NORMAL);
		paramBean.setTenantId(UserUtils.getTenantId());
		
		List<SysOrg> existList = this.getObjectByBean(paramBean);
		if(CollectionUtils.isNotEmpty(existList)) {
			return ResultUtils.genErrorResult(
					StringFormatUtils.format("【{name}】数据已存在", bean), 
					ErrorInfo.EXIST.getCode());
		}
		
		String parentIds = "";
		SysOrg parentOrg = this.getObjectById(bean.getParentId());
		if(null!=parentOrg) {
			parentIds = parentOrg.getParentIds() + bean.getParentId() +",";
		}else {
			parentIds = "0,";
		}
		
		bean.setId(IdGenerator.uuid());
		bean.setParentIds(parentIds);
		bean.setCreateBy(UserUtils.getUserId());
		bean.setCreateDate(DateUtils.getNowDate());
		bean.setDeleteFlag(Global.NORMAL);
		bean.setTenantId(UserUtils.getTenantId());
		
		objectMapper.insertSelective(bean);
		
		if(logger.isDebugEnabled()) {
			logger.debug("新增组织机构 params={}", bean);
		}
		
		return ResultUtils.genSuccesResult(bean);
	}
	
	/**
	 * 更新
	 * @param bean
	 * @return
	 */
	public RestResult<SysOrg> updateOrg(SysOrg bean) {
		
		if(null==bean.getId()) {
			return ResultUtils.genErrorResult(ErrorInfo.MISSING_PARAM);
		}
		
		SysOrg existBean = this.getObjectById(bean.getId());
		if(null==existBean) {
			return ResultUtils.genErrorResult(ErrorInfo.NOT_EXIST);
		}
		
        Example example = new Example(SysOrg.class);
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
			logger.debug("更新组织机构 params={}", bean);
		}
		
		return ResultUtils.genSuccesResult(bean);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public RestResult<SysOrg> deleteOrg(String id) {
		
		SysOrg existBean = this.getObjectById(id);
		if(null==existBean) {
			return ResultUtils.genErrorResult(ErrorInfo.NOT_EXIST);
		}
		
		//查询当前组织下是否还有子组织
		SysOrg existChildren = new SysOrg();
		existChildren.setParentId(id);
		List<SysOrg> children = this.getList(existChildren);
		if(CollectionUtils.isNotEmpty(children)) {
			return ResultUtils.genErrorResult(ErrorInfo.EXIST_CHILDREN);
		}
		
		//查询当前组织及其自组织下是否还有人员
		SysUser user = new SysUser();
		user.setOrgId(id);
		List<SysUser> users = userService.getPageList(user);
		if(CollectionUtils.isNotEmpty(users)) {
			return ResultUtils.genErrorResult(ErrorInfo.EXIST_RELATED_USER);
		}
		
		SysOrg deleteBean = new SysOrg();
		deleteBean.setId(id);
		deleteBean.setDeleteFlag(BaseEntity.DELETE_FLAG_DELETED);
		deleteBean.setUpdateBy(UserUtils.getUserId());
		deleteBean.setUpdateDate(DateUtils.getNowDate());
		//逻辑删除
		objectMapper.updateByPrimaryKeySelective(deleteBean);
		
		if(logger.isDebugEnabled()) {
			logger.debug("删除组织机构信息 id={} data={}", id, existBean);
		}
		
		return ResultUtils.genSuccesResult(existBean);
	}
	

	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public SysOrg getObjectById(String id) {
		SysOrg bean = new SysOrg();
		bean.setId(id);
		bean.setDeleteFlag(Global.NORMAL);
		return objectMapper.selectOne(bean);
	}
	
	/**
	 * 根据实体查询
	 * @param bean
	 * @return
	 */
	public List<SysOrg> getObjectByBean(SysOrg bean) {
		bean.setDeleteFlag(Global.NORMAL);
		bean.setTenantId(UserUtils.getTenantId());
		return objectMapper.select(bean);
	}
	
	/**
	 * 查询
	 * @param bean
	 * @return
	 */
	public List<SysOrg> getList(SysOrg bean) {
		
        Example example = new Example(SysOrg.class);
        Example.Criteria criteria = example.createCriteria();
        //必带参数
        criteria.andEqualTo("deleteFlag", Global.NORMAL);
        criteria.andEqualTo("tenantId", UserUtils.getTenantId());
        
        if (StringUtils.isNotEmpty(bean.getName())) {
            criteria.andLike("name", "%" + bean.getName() + "%");
        }
        
        if (StringUtils.isNotEmpty(bean.getParentId())) {
            criteria.andEqualTo("parentId", bean.getParentId());
        }
        
        example.setOrderByClause("sort asc ");
        return objectMapper.selectByExample(example);  		
	}
	
	
	/**
	 * 分页查询
	 * @param bean
	 * @return
	 */
	public List<SysOrg> getPageList(SysOrg bean) {
		if(bean.getPageNum() != null && bean.getPageSize() != null) {
			 PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
        }
        Example example = new Example(SysOrg.class);
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

	/**
	 * 获取组织机构树
	 * @return
	 */
	public SysOrg getOrgTree() {
		//根节点
		SysOrg root = new SysOrg();
		root.setId("0");
		root.setParentId("-1");
		root.setName("组织机构");
		//所有组织机构
		List<SysOrg> orgs = this.getList(new SysOrg());
		if(CollectionUtils.isNotEmpty(orgs)) {
			//转换为tree结构
			List<SysOrg> children = TreeUtils.listToTree(orgs);
			//设置为子节点
			root.setChildren(children);
		}
		return root;
	}

	

}
