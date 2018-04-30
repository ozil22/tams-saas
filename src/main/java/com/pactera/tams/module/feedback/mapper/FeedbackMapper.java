package com.pactera.tams.module.feedback.mapper;

import com.alibaba.fastjson.JSONObject;
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

    List<JSONObject> processRecord(ReportQuery q);

	List<JSONObject> processParamScope(ReportQuery q);

	List<JSONObject> processParamCompare(ReportQuery q);

	List<JSONObject> schemeRecommend(ReportQuery q);

    List<JSONObject> toolConsumptionCompare(ReportQuery q);

    List<JSONObject> toolConsumptionCompareGroupScheme(ReportQuery q);

    List<JSONObject> toolConsumption(ReportQuery q);
    List<JSONObject> makeAmountTrend(ReportQuery q);
    List<JSONObject> makeAmount(ReportQuery q);

    List<JSONObject> makeAmountTrendByProduct(ReportQuery q);

    List<JSONObject> toolConsumptionByProduct(ReportQuery q);
    List<JSONObject> toolConsumptionTrend(ReportQuery q);
}
