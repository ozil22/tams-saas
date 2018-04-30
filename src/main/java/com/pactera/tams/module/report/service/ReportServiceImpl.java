package com.pactera.tams.module.report.service;

import com.alibaba.fastjson.JSONObject;
import com.pactera.tams.common.utils.StringUtils;
import com.pactera.tams.module.feedback.mapper.FeedbackMapper;
import com.pactera.tams.module.report.vo.ReportQuery;
import com.pactera.tams.module.tool.mapper.ToolPriceMapper;
import com.sun.corba.se.impl.orb.ParserTable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReportServiceImpl implements ReportService {
    protected Log log = LogFactory.getLog(getClass());
    @Autowired
    private ToolPriceMapper toolPriceMapper;
    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public List<JSONObject> toolPrice(String tool_id, String begin, String end) {
        ReportQuery q = new ReportQuery();
        q.setTool_id(tool_id);
        q.setBegin_date(begin);
        q.setEnd_date(end);
        return toolPriceMapper.findPriceHistory(q);
    }

    @Override
    public List<JSONObject> processRecord(String tool_id, String begin, String end, String material_names) {
        ReportQuery q = new ReportQuery();
        q.setTool_id(tool_id);
        q.setBegin_date(begin);
        q.setEnd_date(end);
        if (StringUtils.isNotBlank(material_names)) {
            String[] names = material_names.split(",");
            q.setMaterial_names(names);
        }
        return feedbackMapper.processRecord(q);
    }

    @Override
    public List<JSONObject> processParamScope(String tool_id, String begin, String end, String material_names) {
        ReportQuery q = new ReportQuery();
        q.setTool_id(tool_id);
        q.setBegin_date(begin);
        q.setEnd_date(end);
        if (StringUtils.isNotBlank(material_names)) {
            String[] names = material_names.split(",");
            q.setMaterial_names(names);
        }
        return feedbackMapper.processParamScope(q);
    }

    @Override
    public List<JSONObject> processParamCompare(String tool_id, String begin, String end, String material_names, String product_id, String scheme_id,String process_dates) {
        ReportQuery q = new ReportQuery();
        q.setTool_id(tool_id);
        q.setBegin_date(begin);
        q.setEnd_date(end);
        q.setMaterial_name(material_names);
        q.setProduct_id(product_id);
        q.setScheme_id(scheme_id);
        if (StringUtils.isNotBlank(process_dates)){
            String[] dates = process_dates.split(",");
            q.setProcess_dates(dates);
        }

        return feedbackMapper.processParamCompare(q);
    }

    @Override
    public List<JSONObject> schemeRecommend(String tool_id, String begin, String end, String material_names, String product_id, String process_dates) {
        ReportQuery q = new ReportQuery();
        q.setTool_id(tool_id);
        q.setBegin_date(begin);
        q.setEnd_date(end);
        q.setMaterial_name(material_names);
        q.setProduct_id(product_id);
        if (StringUtils.isNotBlank(process_dates)){
            String[] dates = process_dates.split(",");
            q.setProcess_dates(dates);
        }

        return feedbackMapper.schemeRecommend(q);
    }

    @Override
    public List<JSONObject> toolConsumption(String tool_label, String begin, String end, String product_id, String scheme_id,String group,String date) {
        ReportQuery q = new ReportQuery();
        q.setBegin_date(begin);
        q.setEnd_date(end);
        q.setTool_label(tool_label);
        q.setScheme_id(scheme_id);
        q.setProduct_id(product_id);
        q.setGroup(group);
        q.setDate(date);
        if (StringUtils.isNotBlank(begin) && StringUtils.isNotBlank(begin)){
            return feedbackMapper.toolConsumptionCompare(q);

        }else if (StringUtils.isNotBlank(group) && StringUtils.isNotBlank(date)){
            return feedbackMapper.toolConsumption(q);
        }
        return null;
    }

    @Override
    public List<JSONObject> toolConsumptionScheme(String begin, String end, String scheme_id, String group, String date) {
        ReportQuery q = new ReportQuery();
        q.setBegin_date(begin);
        q.setEnd_date(end);
        q.setScheme_id(scheme_id);
        q.setGroup(group);
        q.setDate(date);
        if (StringUtils.isNotBlank(begin) && StringUtils.isNotBlank(begin)){
            return feedbackMapper.toolConsumptionCompareGroupScheme(q);

        }else if (StringUtils.isNotBlank(group) && StringUtils.isNotBlank(date)){
            return feedbackMapper.toolConsumption(q);
        }
        return null;
    }

    @Override
    public Map<String,List<JSONObject>> makeAmount(String begin, String end, String group) {
        ReportQuery q = new ReportQuery();
        q.setBegin_date(begin);
        q.setEnd_date(end);
        q.setGroup(group);
        Map<String,List<JSONObject>> map = new HashMap<>();
        map.put("makeAmount",feedbackMapper.makeAmount(q));
        map.put("makeAmountTrend",feedbackMapper.makeAmountTrend(q));

        return map;
    }

    @Override
    public List<JSONObject> makeAmountTrendByProduct(String begin, String end, String group,String product_id) {
        ReportQuery q = new ReportQuery();
        q.setBegin_date(begin);
        q.setEnd_date(end);
        q.setProduct_id(product_id);
        q.setGroup(group);
        return feedbackMapper.makeAmountTrendByProduct(q);
    }

    @Override
    public Map<String,Object> toolConsumptionByProduct(String begin, String end, String group, String product_id) {
        ReportQuery q = new ReportQuery();
        q.setBegin_date(begin);
        q.setEnd_date(end);
        q.setProduct_id(product_id);
        q.setGroup(group);
        List<JSONObject> list = feedbackMapper.toolConsumptionByProduct(q);
        List<JSONObject> new_list = new ArrayList<>();
        Set<String> schemes = new HashSet<>();
        for (JSONObject json : list) {
            schemes.add(json.getString("technics_name"));
        }
        int a =1;
        for (int i = 0; i <list.size() ; i++) {
            if (i == 0 )
        }



        while (!list.isEmpty()){
            for (String scheme : schemes) {
                JSONObject o = new JSONObject();
                Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    JSONObject json = (JSONObject) iterator.next();
                    if (scheme.equals(json.getString("technics_name"))){
                        o.put("date",json.getString("date"));
                        o.put(scheme+"_amount",json.getString("amount"));
                        o.put(scheme+"_tool_cost",json.getString("tool_cost"));
                        o.put("technics_name",json.getString(scheme));
                        new_list.add(o)
                        iterator.remove();
                    }else{
                        break;
                    }
                }
            }
        }

        Map<String,Object> map = new HashMap<>();
        map.put("technics_list",schemes);
        map.put("compare_list",schemes);
        map.put("list",list);
        return map;
    }
}
