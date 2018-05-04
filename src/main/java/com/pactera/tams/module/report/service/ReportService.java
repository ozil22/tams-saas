package com.pactera.tams.module.report.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public interface ReportService {
    List<JSONObject> toolPrice(String tool_id, String begin, String end);

    List<JSONObject> processRecord(String tool_id, String begin, String end, String material_names);

    List<JSONObject> processParamScope(String tool_id, String begin, String end, String material_names);

    List<JSONObject> processParamCompare(String tool_id, String begin, String end, String material_names, String product_id, String scheme_id,String process_dates);

    List<JSONObject> schemeRecommend(String tool_id, String begin, String end, String material_names, String product_id, String process_dates);

    List<JSONObject> toolConsumption(String tool_label, String begin, String end, String product_id, String scheme_id,String group);

    List<JSONObject> toolConsumptionScheme(String begin, String end, String scheme_id, String group);

    Map<String,List<JSONObject>> makeAmount(String begin, String end, String group);

    List<JSONObject> makeAmountTrendByProduct(String begin, String end, String group,String product_id);

    Map<String,Object> toolConsumptionByProduct(String begin, String end, String group, String product_id);

    List<JSONObject> toolConsumptionTrend(String begin, String end, String group);

    List<JSONObject> productSchemeCompare(String begin, String end, String scheme_ids, String product_id);
}
