package com.pactera.tams.module.solr.model;

import com.pactera.tams.common.model.BaseEntity;


/**
 * solr索引
 *
 * @Author: mjh
 * @Date: 2018-01-30 18:41:48
 */
public class SolrModel extends BaseEntity {

    private String id;
    //索引名字
    private String index_name;
    //刀具id
    private String tool_id;
    //刀具名称
    private String tool_name;
    //刀具规格
    private String tool_specification;
    //刀具标签
    private String tool_label;
    //刀具简介
    private String tool_content;
    //产品id
    private String product_id;
    //产品名字
    private String product_name;
    //产品规格
    private String product_specification;
    //产品规格名称
    private String product_specification_name;
    //产品材质
    private String product_material_quality;
    //产品材质规格
    private String product_material_quality_spec;
    //产品牌号
    private String product_trade_mark;
    //产品标签
    private String product_label;
    //产品内容
    private String product_content;

    //工艺id
    private String technics_id;
    //工艺名称
    private String technics_name;
    //工艺编号
    private String technics_code;
    //工序id
    private String scheme_id;
    //工序设备规格
    private String scheme_device_specs;
    //工序设备名称
    private String scheme_device_name;
    //工序材质规格
    private String scheme_material_specs;
    //工序备注
    private String scheme_remark;
    //工步id
    private String step_id;
    //工步编号
    private String step_no;
    //工步内容
    private String step_content;
    //工步要求
    private String step_require;
    //工步刀号
    private String step_tool_no;
    //工步刀具描述
    private String step_tool_desc;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getIndex_name() {
        return index_name;
    }

    public void setIndex_name(String index_name) {
        this.index_name = index_name;
    }

    public String getTool_id() {
        return tool_id;
    }

    public void setTool_id(String tool_id) {
        this.tool_id = tool_id;
    }

    public String getTool_name() {
        return tool_name;
    }

    public void setTool_name(String tool_name) {
        this.tool_name = tool_name;
    }

    public String getTool_specification() {
        return tool_specification;
    }

    public void setTool_specification(String tool_specification) {
        this.tool_specification = tool_specification;
    }

    public String getTool_label() {
        return tool_label;
    }

    public void setTool_label(String tool_label) {
        this.tool_label = tool_label;
    }

    public String getTool_content() {
        return tool_content;
    }

    public void setTool_content(String tool_content) {
        this.tool_content = tool_content;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_specification() {
        return product_specification;
    }

    public void setProduct_specification(String product_specification) {
        this.product_specification = product_specification;
    }

    public String getProduct_specification_name() {
        return product_specification_name;
    }

    public void setProduct_specification_name(String product_specification_name) {
        this.product_specification_name = product_specification_name;
    }

    public String getProduct_material_quality() {
        return product_material_quality;
    }

    public void setProduct_material_quality(String product_material_quality) {
        this.product_material_quality = product_material_quality;
    }

    public String getProduct_material_quality_spec() {
        return product_material_quality_spec;
    }

    public void setProduct_material_quality_spec(String product_material_quality_spec) {
        this.product_material_quality_spec = product_material_quality_spec;
    }

    public String getProduct_trade_mark() {
        return product_trade_mark;
    }

    public void setProduct_trade_mark(String product_trade_mark) {
        this.product_trade_mark = product_trade_mark;
    }

    public String getProduct_label() {
        return product_label;
    }

    public void setProduct_label(String product_label) {
        this.product_label = product_label;
    }

    public String getProduct_content() {
        return product_content;
    }

    public void setProduct_content(String product_content) {
        this.product_content = product_content;
    }

    public String getTechnics_id() {
        return technics_id;
    }

    public void setTechnics_id(String technics_id) {
        this.technics_id = technics_id;
    }

    public String getTechnics_name() {
        return technics_name;
    }

    public void setTechnics_name(String technics_name) {
        this.technics_name = technics_name;
    }

    public String getTechnics_code() {
        return technics_code;
    }

    public void setTechnics_code(String technics_code) {
        this.technics_code = technics_code;
    }

    public String getScheme_id() {
        return scheme_id;
    }

    public void setScheme_id(String scheme_id) {
        this.scheme_id = scheme_id;
    }

    public String getScheme_device_specs() {
        return scheme_device_specs;
    }

    public void setScheme_device_specs(String scheme_device_specs) {
        this.scheme_device_specs = scheme_device_specs;
    }

    public String getScheme_device_name() {
        return scheme_device_name;
    }

    public void setScheme_device_name(String scheme_device_name) {
        this.scheme_device_name = scheme_device_name;
    }

    public String getScheme_material_specs() {
        return scheme_material_specs;
    }

    public void setScheme_material_specs(String scheme_material_specs) {
        this.scheme_material_specs = scheme_material_specs;
    }

    public String getScheme_remark() {
        return scheme_remark;
    }

    public void setScheme_remark(String scheme_remark) {
        this.scheme_remark = scheme_remark;
    }

    public String getStep_id() {
        return step_id;
    }

    public void setStep_id(String step_id) {
        this.step_id = step_id;
    }

    public String getStep_no() {
        return step_no;
    }

    public void setStep_no(String step_no) {
        this.step_no = step_no;
    }

    public String getStep_content() {
        return step_content;
    }

    public void setStep_content(String step_content) {
        this.step_content = step_content;
    }

    public String getStep_require() {
        return step_require;
    }

    public void setStep_require(String step_require) {
        this.step_require = step_require;
    }

    public String getStep_tool_no() {
        return step_tool_no;
    }

    public void setStep_tool_no(String step_tool_no) {
        this.step_tool_no = step_tool_no;
    }

    public String getStep_tool_desc() {
        return step_tool_desc;
    }

    public void setStep_tool_desc(String step_tool_desc) {
        this.step_tool_desc = step_tool_desc;
    }


}
