package com.pactera.tams.module.machine.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.pactera.tams.common.utils.DateUtils;
import com.pactera.tams.module.machine.entity.MachineManagementEntity;
import com.pactera.tams.module.machine.mapper.MachineManagementMapper;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
@Service
public class MachineManagementService {
	
	@Autowired
	MachineManagementMapper  tamsMachineToolMapper;
	/**
	 * 查询
	 */
	public List getLists(MachineManagementEntity bean) {
		
		return tamsMachineToolMapper.getList(bean);
	}
	
	/**
	 * 查询
	 */
	public List getList(MachineManagementEntity bean) {
		if(bean.getPage() != null && bean.getRows() != null) {
			 PageHelper.startPage(bean.getPage(), bean.getRows());
      } 
		Example example = new Example(MachineManagementEntity.class);
		
		/*Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(bean.getCatalog_id())) {
			criteria.andEqualTo("catalog_id", bean.getCatalog_id());
		}*/
		return tamsMachineToolMapper.selectByExample(example);
	}
	
	/**
	 * 获得机床数量
	 */
	public int getTotal(MachineManagementEntity bean) {
		return tamsMachineToolMapper.selectCount(bean);
	}
	
	/**
	 * 分页查询
	 */
	public List getPageList(MachineManagementEntity bean) {
		if(bean.getPage() != null && bean.getRows() != null) {
			 PageHelper.startPage(bean.getPage(), bean.getRows());
       } 	
		return tamsMachineToolMapper.getList(bean);
	}
	
	/**
	 * 修改机床规格信息
	 */
	public void update(MachineManagementEntity bean) {
		Example example = new Example(MachineManagementEntity.class);
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotEmpty(bean.getId())){
    		criteria.andEqualTo("id", bean.getId());
    	}
		bean.setUpdateDate(DateUtils.getCurrentTime());
		tamsMachineToolMapper.updateByExampleSelective(bean, example);
	}
	
	/**
	 * 保存机床规格
	 */
	public int save(MachineManagementEntity bean) {
//		List<Map> combList = bean.getCombList_relation_table();
//		if(combList!=null&&combList.size() > 0) {
//			ToolCompose tc = new ToolCompose();
//			for(Map comb:combList) {
//				String smallId = (String) comb.get("id");
//				tc.setSmall_tool_id(smallId);
//				tc.setTool_id(bean.getId());
//				tc.setId(Until.getStringUUID());
//				toolComposeMapper.insert(tc);
//			}
//		}
		return tamsMachineToolMapper.insert(bean);
	}
	
	/**
	 * 查询实体
	 * id
	 */
	public MachineManagementEntity getObjectById(String id) {
		return tamsMachineToolMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 机床管理数据删除
	 */
	public void delete(String id) {
		tamsMachineToolMapper.deleteByPrimaryKey(id);
	}
}
