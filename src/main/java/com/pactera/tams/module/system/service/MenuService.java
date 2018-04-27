package com.pactera.tams.module.system.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
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
import com.pactera.tams.module.system.entity.OpeMenu;
import com.pactera.tams.module.system.entity.SysRoleMenu;
import com.pactera.tams.module.system.entity.SysUser;
import com.pactera.tams.module.system.mapper.OpeMenuMapper;
import com.pactera.tams.module.system.mapper.SysRoleMenuMapper;

import tk.mybatis.mapper.entity.Example;

/**
* 菜单Service
* @Author: mjh
* @Date: 2018-03-19 16:11:06
*/
@Service
public class MenuService {
	
	private Logger logger = LoggerFactory.getLogger(MenuService.class);
	
	@Autowired
	private OpeMenuMapper menuMapper;
	
	@Autowired
	private SysRoleMenuMapper roleMenuMapper; 
	
	/**
	 * 新增
	 * @param bean
	 * @return
	 */
	public RestResult<OpeMenu> addMenu(OpeMenu bean) {
		
		OpeMenu paramBean = new OpeMenu();
		paramBean.setName(bean.getName());
		paramBean.setDeleteFlag(Global.NORMAL);
		
		List<OpeMenu> existList = this.getObjectByBean(paramBean);
		if(CollectionUtils.isNotEmpty(existList)) {
			return ResultUtils.genErrorResult(
					StringFormatUtils.format("【{name}】数据已存在", bean), 
					ErrorInfo.EXIST.getCode());
		}
		
		bean.setId(IdGenerator.uuid());
		bean.setCreateBy(UserUtils.getUserId());
		bean.setCreateDate(DateUtils.getNowDate());
		bean.setDeleteFlag(Global.NORMAL);
//		bean.setTenantId(UserUtils.getTenantId());	
		
		menuMapper.insertSelective(bean);
		
		if(logger.isDebugEnabled()) {
			logger.debug("新增菜单 params={}", bean);
		}
		
		return ResultUtils.genSuccesResult(bean);
	}
	
	/**
	 * 更新
	 * @param bean
	 * @return
	 */
	public RestResult<OpeMenu> updateMenu(OpeMenu bean) {
		
		if(null==bean.getId()) {
			return ResultUtils.genErrorResult(ErrorInfo.MISSING_PARAM);
		}
		
		OpeMenu existBean = this.getObjectById(bean.getId());
		if(null==existBean) {
			return ResultUtils.genErrorResult(ErrorInfo.NOT_EXIST);
		}
		
        Example example = new Example(OpeMenu.class);
        Example.Criteria criteria = example.createCriteria();
        
        criteria.andEqualTo("name", bean.getName());
        criteria.andEqualTo("deleteFlag", Global.NORMAL);
        criteria.andEqualTo("tenantId", UserUtils.getTenantId());
        //排除本条记录
        criteria.andNotEqualTo("id", bean.getId());
        
        int count = menuMapper.selectCountByExample(example);
        
		if(count>0) {
			return ResultUtils.genErrorResult(
					StringFormatUtils.format("【{name}】数据已存在", bean), 
					ErrorInfo.EXIST.getCode());
		}
		
		bean.setUpdateBy(UserUtils.getUserId());
		bean.setUpdateDate(DateUtils.getNowDate());
		
		menuMapper.updateByPrimaryKeySelective(bean);
		
		bean = menuMapper.selectByPrimaryKey(bean.getId());
		
		if(logger.isDebugEnabled()) {
			logger.debug("更新菜单 params={}", bean);
		}
		
		return ResultUtils.genSuccesResult(bean);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public RestResult<OpeMenu> deleteMenu(String id) {
		
		OpeMenu existBean = this.getObjectById(id);
		if(null==existBean) {
			return ResultUtils.genErrorResult(ErrorInfo.NOT_EXIST);
		}
		
		SysRoleMenu roleMenu = new SysRoleMenu();
		roleMenu.setMenuId(id);
		List<SysRoleMenu> relations = roleMenuMapper.select(roleMenu);
		if(CollectionUtils.isNotEmpty(relations)) {
			return ResultUtils.genErrorResult(ErrorInfo.EXIST_RELATED_ROLE);
		}
		
		OpeMenu deleteBean = new OpeMenu();
		deleteBean.setId(id);
		deleteBean.setDeleteFlag(BaseEntity.DELETE_FLAG_DELETED);
		deleteBean.setUpdateBy(UserUtils.getUserId());
		deleteBean.setUpdateDate(DateUtils.getNowDate());
		//逻辑删除
		menuMapper.updateByPrimaryKeySelective(deleteBean);
		
		if(logger.isDebugEnabled()) {
			logger.debug("删除菜单信息 id={} data={}", id, existBean);
		}
		
		return ResultUtils.genSuccesResult(existBean);
	}
	

	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public OpeMenu getObjectById(String id) {
		OpeMenu bean = new OpeMenu();
		bean.setId(id);
		bean.setDeleteFlag(Global.NORMAL);
		return menuMapper.selectOne(bean);
	}
	
	/**
	 * 根据实体查询
	 * @param bean
	 * @return
	 */
	public List<OpeMenu> getObjectByBean(OpeMenu bean) {
		bean.setDeleteFlag(Global.NORMAL);
		return menuMapper.select(bean);
	}
	
	/**
	 * 查询
	 * @param bean
	 * @return
	 */
	public List<OpeMenu> getList(OpeMenu bean) {
		
        Example example = new Example(OpeMenu.class);
        Example.Criteria criteria = example.createCriteria();
        //必带参数
        criteria.andEqualTo("deleteFlag", Global.NORMAL);
        
        if (StringUtils.isNotEmpty(bean.getName())) {
            criteria.andLike("name", "%" + bean.getName() + "%");
        }
        
        example.setOrderByClause("sort asc ");
        return menuMapper.selectByExample(example);  		
	}
	
	
	/**
	 * 分页查询
	 * @param bean
	 * @return
	 */
	public List<OpeMenu> getPageList(OpeMenu bean) {
		if(bean.getPageNum() != null && bean.getPageSize() != null) {
			 PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
        }
        Example example = new Example(OpeMenu.class);
        Example.Criteria criteria = example.createCriteria();
        
        //必带参数
        criteria.andEqualTo("deleteFlag", Global.NORMAL);
        
        if (StringUtils.isNotEmpty(bean.getName())) {
            criteria.andLike("name", "%" + bean.getName() + "%");
        }
       
        example.setOrderByClause("create_date desc ");
        
        return menuMapper.selectByExample(example);
	}
	
	/**
	 * 获取所有菜单（树形）
	 * @return
	 */
	public List<OpeMenu> getMenuTree() {
		OpeMenu bean = new OpeMenu();
		List<OpeMenu> menus = this.getList(bean);
		return TreeUtils.listToTree(menus);
	}
	
	/**
	 * 根据用户获取树形结构的菜单
	 * @param user
	 * @return
	 */
	public List<OpeMenu> getMenuTreeByUser(SysUser user) {
		List<OpeMenu> list = menuMapper.getListByUser(user.getId(), null, null);
		List<OpeMenu> menus = Lists.newArrayList(Sets.newLinkedHashSet(list));
		return TreeUtils.listToTree(menus);
	}

	/**
	 * 获取用户某个菜单下拥有的按钮权限
	 * @param userId
	 * @param parentId
	 * @return
	 */
	public List<OpeMenu> getButtonList(String userId, String parentId){
		List<OpeMenu> buttons = menuMapper.getListByUser(userId, parentId, (byte) 3);
		return buttons;
	}


}
