package com.pactera.tams.module.tool.vo;

import com.pactera.tams.common.model.BaseEntity;

public class ToolPriceQuery extends BaseEntity{
    private String tool_id;
    private String bengin_date ;
    private String end_date;

    public String getTool_id() {
        return tool_id;
    }

    public void setTool_id(String tool_id) {
        this.tool_id = tool_id;
    }

    public String getBengin_date() {
        return bengin_date;
    }

    public void setBengin_date(String bengin_date) {
        this.bengin_date = bengin_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}
