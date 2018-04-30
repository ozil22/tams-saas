package com.pactera.tams.module.report.vo;

import com.pactera.tams.common.model.BaseEntity;

public class ReportQuery extends BaseEntity{
    private String tool_id;
    private String begin_date;
    private String end_date;
    private String[] material_names;
    private String[] process_dates;
    private String material_name;
    private String product_id;
    private String scheme_id;
    private String tool_label;
    private String group;
    private String date;

    public String getTool_id() {
        return tool_id;
    }

    public void setTool_id(String tool_id) {
        this.tool_id = tool_id;
    }

    public String getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(String begin_date) {
        this.begin_date = begin_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }


    public String[] getMaterial_names() {
        return material_names;
    }

    public void setMaterial_names(String[] material_names) {
        this.material_names = material_names;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getScheme_id() {
        return scheme_id;
    }

    public void setScheme_id(String scheme_id) {
        this.scheme_id = scheme_id;
    }

    public String[] getProcess_dates() {
        return process_dates;
    }

    public void setProcess_dates(String[] process_dates) {
        this.process_dates = process_dates;
    }

    public String getTool_label() {
        return tool_label;
    }

    public void setTool_label(String tool_label) {
        this.tool_label = tool_label;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
