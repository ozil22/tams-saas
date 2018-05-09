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
        List<JSONObject> list = toolPriceMapper.findPriceHistory(q);
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
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
        List<JSONObject> list = feedbackMapper.processRecord(q);
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
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
        List<JSONObject> list = feedbackMapper.processParamScope(q);
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<JSONObject> processParamCompare(String tool_id, String begin, String end, String material_names, String product_id, String scheme_id, String process_dates) {
        ReportQuery q = new ReportQuery();
        q.setTool_id(tool_id);
        q.setBegin_date(begin);
        q.setEnd_date(end);
        q.setMaterial_name(material_names);
        q.setProduct_id(product_id);
        q.setScheme_id(scheme_id);
        if (StringUtils.isNotBlank(process_dates)) {
            String[] dates = process_dates.split(",");
            q.setProcess_dates(dates);
        }

        List<JSONObject> list = feedbackMapper.processParamCompare(q);
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<JSONObject> schemeRecommend(String tool_id, String begin, String end, String material_names, String product_id, String process_dates) {
        ReportQuery q = new ReportQuery();
        q.setTool_id(tool_id);
        q.setBegin_date(begin);
        q.setEnd_date(end);
        q.setMaterial_name(material_names);
        q.setProduct_id(product_id);
        if (StringUtils.isNotBlank(process_dates)) {
            String[] dates = process_dates.split(",");
            q.setProcess_dates(dates);
        }

        List<JSONObject> list = feedbackMapper.schemeRecommend(q);
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<JSONObject> toolConsumption(String tool_label, String begin, String end, String product_id, String scheme_id, String group) {
        ReportQuery q = new ReportQuery();
        if (begin != null && begin.equals(end)) {
            q.setDate(begin);
        }
        q.setBegin_date(begin);
        q.setEnd_date(end);
        q.setTool_label(tool_label);
        q.setScheme_id(scheme_id);
        q.setProduct_id(product_id);
        q.setGroup(group);
        if (StringUtils.isNotBlank(begin) && StringUtils.isNotBlank(end)) {
            if (!begin.equals(end)) {
                List<JSONObject> list = feedbackMapper.toolConsumptionCompare(q);
                if (list == null) {
                    list = new ArrayList<>();
                }
                return list;

            } else {
                List<JSONObject> list = feedbackMapper.toolConsumption(q);
                if (list == null) {
                    list = new ArrayList<>();
                }
                return list;
            }
        }
        return new ArrayList<>();
    }

    @Override
    public List<JSONObject> toolConsumptionScheme(String begin, String end, String scheme_id, String group) {
        ReportQuery q = new ReportQuery();
        if (begin != null && begin.equals(end)) {
            q.setDate(begin);
        }
        q.setBegin_date(begin);
        q.setEnd_date(end);
        q.setScheme_id(scheme_id);
        q.setGroup(group);
        if (StringUtils.isNotBlank(begin) && StringUtils.isNotBlank(end)) {
            if (!begin.equals(end)) {
                List<JSONObject> list = feedbackMapper.toolConsumptionCompareGroupScheme(q);
                if (list == null) {
                    list = new ArrayList<>();
                }
                return list;
            } else {
                List<JSONObject> list = feedbackMapper.toolConsumption(q);
                if (list == null) {
                    list = new ArrayList<>();
                }
                return list;
            }
        }
        return new ArrayList<>();
    }

    @Override
    public Map<String, List<JSONObject>> makeAmount(String begin, String end, String group) {
        ReportQuery q = new ReportQuery();
        q.setBegin_date(begin);
        q.setEnd_date(end);
        q.setGroup(group);
        Map<String, List<JSONObject>> map = new HashMap<>();
        List<JSONObject> makeAmount = feedbackMapper.makeAmount(q);
        if (makeAmount == null) {
            makeAmount = new ArrayList<>();
        }
        List<JSONObject> makeAmountTrend = feedbackMapper.makeAmountTrend(q);
        if (makeAmountTrend == null) {
            makeAmountTrend = new ArrayList<>();
        }
        map.put("makeAmount", makeAmount);
        map.put("makeAmountTrend", makeAmountTrend);

        return map;
    }

    @Override
    public Map<String, Object> makeAmountTrendByProduct(String begin, String end, String group, String product_id) {
        Map<String, Object> map = new HashMap<>();

        ReportQuery q = new ReportQuery();
        q.setBegin_date(begin);
        q.setEnd_date(end);
        q.setProduct_id(product_id);
        q.setGroup(group);
        List<JSONObject> list = feedbackMapper.makeAmountTrendByProduct(q);

        if (list == null) {
            map.put("list", new ArrayList<>());
            map.put("compare_list", new ArrayList<>());
            map.put("technics_list", new ArrayList<>());
        } else {
            List<JSONObject> new_list = new ArrayList<>();
            List<String> technics_list = new ArrayList<String>();
            String d = null;
            JSONObject json = new JSONObject();
            int i = 1;
            for (JSONObject jsonObject : list) {
                String date = jsonObject.getString("date");
                String technics_code = jsonObject.getString("technics_code");
                String waste_amount = jsonObject.getString("waste_amount");
                String make_amount = jsonObject.getString("make_amount");
                String qualified_amount = jsonObject.getString("qualified_amount");
                if (!technics_list.contains(technics_code)) {
                    technics_list.add(technics_code);
                }

                if (d == null) {
                    json.put("date", jsonObject.get("date"));
                    json.put("technics_code_" + i, technics_code);
                    json.put("waste_amount_" + i, waste_amount);
                    json.put("make_amount_" + i, make_amount);
                    json.put("qualified_amount_" + i, qualified_amount);
                    i += 1;
                    d = date;
                } else if (d.equals(date)) {
                    json.put("date", jsonObject.get("date"));
                    json.put("technics_code_" + i, technics_code);
                    json.put("waste_amount_" + i, waste_amount);
                    json.put("make_amount_" + i, make_amount);
                    json.put("qualified_amount_" + i, qualified_amount);
                    i += 1;
                } else {
                    new_list.add(json);
                    i = 1;
                    json = new JSONObject();
                    json.put("date", jsonObject.get("date"));
                    json.put("technics_code_" + i, technics_code);
                    json.put("waste_amount_" + i, waste_amount);
                    json.put("make_amount_" + i, make_amount);
                    json.put("qualified_amount_" + i, qualified_amount);
                    d = date;
                    i += 1;
                }
            }
            new_list.add(json);
            map.put("list", list);
            map.put("compare_list", new_list);
            map.put("technics_list", technics_list);

        }
        return map;
    }

    @Override
    public Map<String, Object> toolConsumptionByProduct(String begin, String end, String group, String product_id) {
        ReportQuery q = new ReportQuery();
        q.setBegin_date(begin);
        q.setEnd_date(end);
        q.setProduct_id(product_id);
        q.setGroup(group);
        List<JSONObject> list = feedbackMapper.toolConsumptionByProduct(q);
//        List<JSONObject> old_list = new ArrayList<>();
//        old_list.addAll(list);
//        List<JSONObject> new_list = new ArrayList<>();
        Set<String> schemes = new HashSet<>();
        for (JSONObject json : list) {
            String scheme = json.getString("technics_name");
            if (StringUtils.isNotBlank(scheme)) {
                schemes.add(scheme);
            }
        }
//        while (!list.isEmpty()){
//            for (String scheme : schemes) {
//                JSONObject o = new JSONObject();
//                Iterator iterator = list.iterator();
//                while (iterator.hasNext()) {
//                    JSONObject json = (JSONObject) iterator.next();
//                    if (scheme.equals(json.getString("technics_name"))){
//                        o.put("date",json.getString("date"));
//                        o.put(scheme+"_amount",json.getString("amount"));
//                        o.put(scheme+"_tool_cost",json.getString("tool_cost"));
//                        o.put("technics_name",json.getString(scheme));
//                        new_list.add(o);
//                        iterator.remove();
//                    }else{
//                        break;
//                    }
//                }
//            }
//        }

        Map<String, Object> map = new HashMap<>();
        map.put("technics_list", schemes);
//        map.put("compare_list",new_list);
        map.put("list", list);
        return map;
    }

    @Override
    public List<JSONObject> toolConsumptionTrend(String begin, String end, String group) {
        ReportQuery q = new ReportQuery();
        q.setBegin_date(begin);
        q.setEnd_date(end);
        q.setGroup(group);
        List<JSONObject> list = feedbackMapper.toolConsumptionTrend(q);
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<JSONObject> productSchemeCompare(String begin, String end, String scheme_ids, String product_id) {
        ReportQuery q = new ReportQuery();
        q.setBegin_date(begin);
        q.setEnd_date(end);
        q.setProduct_id(product_id);
        if (StringUtils.isNotBlank(scheme_ids)) {
            String[] ids = scheme_ids.split(",");
            q.setScheme_ids(ids);
        }
        List<JSONObject> list = feedbackMapper.productSchemeCompare(q);
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }
}
