package com.pactera.tams.module.solr.util;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

/**
 *@ClassName:PushDataIntoSolrRequest.java
 *@ClassDescribe:solr存储请求类
 *@author:chenxi
 *@createDate:2017年9月29日 上午9:44:20
 *@version
 */
public class PushDataIntoSolrRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 核心名称
     */
    @NotNull
    private String coreName ;

    /**
     * 存储实体
     */
    @NotNull
    private Map<String, Object> input;

    public String getCoreName() {
        return coreName;
    }

    public void setCoreName(String coreName) {
        this.coreName = coreName;
    }

    public Map<String, Object> getInput() {
        return input;
    }

    public void setInput(Map<String, Object> input) {
        this.input = input;
    }
}
