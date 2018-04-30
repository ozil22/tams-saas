package com.pactera.tams.module.report.service;

import com.alibaba.fastjson.JSON;

import java.util.List;

public interface ReportService {
    List<JSON> toolPrice(String tool_id, String begin, String end);

    List<JSON> processRecord(String tool_id, String begin, String end, String material_names);

    List<JSON> processParamScope(String tool_id, String begin, String end, String material_names);

    List<JSON> processParamCompare(String tool_id, String begin, String end, String material_names, String product_id, String scheme_id,String process_dates);

    List<JSON> schemeRecommend(String tool_id, String begin, String end, String material_names, String product_id, String process_dates);

    List<JSON> toolConsumption(String tool_label, String begin, String end, String product_id, String scheme_id,String group,String date);

    List<JSON> toolConsumptionScheme(String begin, String end, String scheme_id, String group, String date);
}
