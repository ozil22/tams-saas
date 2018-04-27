package com.pactera.tams.common.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Transient;

import com.google.common.collect.Lists;

/**
* 树实体抽象类
* @Author: mjh
* @Date: 2018-03-25 20:22:05
*/
public abstract class BaseTreeEntity<T> extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 父级id
     */
    @Column(name = "parent_id")
    protected String parentId;

    /**
     * 所有父级id
     */
    @Column(name = "parent_ids")
    protected String parentIds;
    
    /**
     * 名称
     */
    protected String name;
    
    /**
     * 子节点
     */
    @Transient
	protected List<T> children = Lists.newArrayList();

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取子列表
	 * @return
	 */
	public abstract List<T> getChildren();
	/**
	 * 设置子列表
	 * @param children
	 */
	public abstract void setChildren(List<T> children);
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
    
    @Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		@SuppressWarnings("unchecked")
		BaseTreeEntity<T> other = (BaseTreeEntity<T>) obj;	
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
}
