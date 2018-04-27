package com.pactera.tams.common.utils;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;
import com.pactera.tams.common.entity.BaseTreeEntity;

/**
* 树工具类
* @Author: mjh
* @Date: 2018-03-25 21:04:35
*/
public class TreeUtils {
	
	/**
	 * list转换为tree结构
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> listToTree(List<T> list){
		List<T> tree = Lists.newArrayList();
		if(CollectionUtils.isNotEmpty(list)) {
			for (T node1 : list) {
				boolean flag = false;
				for (T node2 : list) {
					//判断node1的父级是否为node2
			        if(((BaseTreeEntity<T>) node1).getParentId()!=null 
			        		&& ((BaseTreeEntity<T>) node1).getParentId().equals(((BaseTreeEntity<T>) node2).getId())){  
			        	flag = true;  
						List<T> children = ((BaseTreeEntity<T>) node2).getChildren();
			            //将node1加入node2的子节点
			            children.add(node1);   
			            break;  
			        } 				
				}
			    if(!flag){
			    	tree.add(node1);   
			    } 
			}
		}
		return tree;
	}

}
