package com.pactera.tams.module.report.service;

import com.alibaba.fastjson.JSON;
import com.pactera.tams.common.utils.StringUtils;
import com.pactera.tams.module.feedback.mapper.FeedbackMapper;
import com.pactera.tams.module.report.vo.ReportQuery;
import com.pactera.tams.module.tool.mapper.ToolPriceMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    protected Log log = LogFactory.getLog(getClass());
    @Autowired
    private ToolPriceMapper toolPriceMapper;
    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public List<JSON> toolPrice(String tool_id, String begin, String end) {
        ReportQuery q = new ReportQuery();
        q.setTool_id(tool_id);
        q.setBegin_date(begin);
        q.setEnd_date(end);
        return toolPriceMapper.findPriceHistory(q);
    }

    @Override
    public List<JSON> processRecord(String tool_id, String begin, String end, String material_names) {
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
    public List<JSON> processParamScope(String tool_id, String begin, String end, String material_names) {
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
    public List<JSON> processParamCompare(String tool_id, String begin, String end, String material_names, String product_id, String scheme_id,String process_dates) {
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
    public List<JSON> schemeRecommend(String tool_id, String begin, String end, String material_names, String product_id, String process_dates) {
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
    public List<JSON> toolConsumption(String tool_label, String begin, String end, String product_id, String scheme_id,String group,String date) {
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
    public List<JSON> toolConsumptionScheme(String begin, String end, String scheme_id, String group, String date) {
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
}
