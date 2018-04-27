package com.pactera.tams.module.machine.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pactera.tams.common.utils.StringFormatUtils;
import com.pactera.tams.module.machine.entity.TSDict;
import com.pactera.tams.module.machine.mapper.TSDictMapper;

import tk.mybatis.mapper.entity.Example;

@Service
public class TSDictService {
	@Autowired
	TSDictMapper  tSDictMapper;
	
	/**
	 * 查询
	 */
	public List<TSDict> getList(TSDict bean) {
		
		Example example = new Example(TSDict.class);
		
		
		return tSDictMapper.getDicList(bean);
	}
	/**
	 * 获取拼接的查询条件
	 * @param bean
	 * @return 
	 */
	public TSDict getTSDictCondition(TSDict bean) {
        //编码
        if(StringUtils.isNotEmpty(bean.getCode())) {
        	List list = StringFormatUtils.getListByStrFormat(bean.getCode(), ",");
        	bean.setCode(StringFormatUtils.getConditionStr(list,"s.code"));
        } 
         
        return bean;
	}
	
	 /**
	  * 根据类型查询数据字典
	  * @param p_code
	  * @return
	  */
    public List getDictList(String p_code) {
        Example example = new Example(TSDict.class);
      
        Example.Criteria criteria = example.createCriteria();
        if (p_code != null && p_code.length() > 0) {
            criteria.andEqualTo("pCode", p_code);
        }
        
        criteria.andEqualTo("isShow", 1);
        
        example.setOrderByClause("sort asc");
         
        return tSDictMapper.selectByExample(example);    	 
    }
}
