package com.pactera.tams.module.feedback.mapper;

import java.util.List;

import com.pactera.tams.module.feedback.model.Feedback;
import com.pactera.tams.common.mapper.MyMapper;


/**
* 
* @Author: mjh
* @Date: 2018-01-31 09:20:26
*/
public interface FeedbackMapper extends MyMapper<Feedback>{
	public List getPageList(Feedback bean);
	public Feedback getBean(Feedback bean);
}
