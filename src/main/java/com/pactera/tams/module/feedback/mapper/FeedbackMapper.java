package com.pactera.tams.module.feedback.mapper;

import com.alibaba.fastjson.JSON;
import com.pactera.tams.common.mapper.MyMapper;
import com.pactera.tams.module.feedback.model.Feedback;
import com.pactera.tams.module.report.vo.ReportQuery;

import java.util.List;


/**
* 
* @Author: mjh
* @Date: 2018-01-31 09:20:26
*/
public interface FeedbackMapper extends MyMapper<Feedback>{
	List getPageList(Feedback bean);
	Feedback getBean(Feedback bean);

    List<JSON> processRecord(ReportQuery q);

	List<JSON> processParamScope(ReportQuery q);

	List<JSON> processParamCompare(ReportQuery q);

	List<JSON> schemeRecommend(ReportQuery q);

    List<JSON> toolConsumptionCompare(ReportQuery q);

    List<JSON> toolConsumptionCompareGroupScheme(ReportQuery q);

    List<JSON> toolConsumption(ReportQuery q);
}
