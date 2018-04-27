package com.pactera.tams.module.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pactera.tams.module.system.mapper.DictionaryMapper;
import com.pactera.tams.module.system.entity.Dictionary;

import tk.mybatis.mapper.entity.Example;


/** * @author  zhuhao 
      @date ：2017年10月9日 下午3:47:56  
      @version 1.0     
**/

@Service
public class DictionaryService {
	 @Autowired
	 private DictionaryMapper dictionaryMapper; 
	 
	 /**
	  * 根据类型查询数据字典
	  * @param p_code
	  * @return
	  */
     public List getDictList(String p_code) {
         Example example = new Example(Dictionary.class);
       
         Example.Criteria criteria = example.createCriteria();
         if (p_code != null && p_code.length() > 0) {
             criteria.andEqualTo("p_code", p_code);
         }
         
         criteria.andEqualTo("is_show", 1);
         
         example.setOrderByClause("sort asc");
          
         return dictionaryMapper.selectByExample(example);    	 
     }
}
