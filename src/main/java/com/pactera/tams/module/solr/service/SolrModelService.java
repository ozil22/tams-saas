package com.pactera.tams.module.solr.service;

import com.pactera.tams.module.solr.model.SolrModel;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

public interface SolrModelService {
     ModelMap addBeanAllIndex() throws Exception;
     ModelMap addBeanIndex(SolrModel solrModel) throws Exception;
     ModelMap deleteAllIndex() throws Exception;
     Map<String, Object> search(String String, Integer pageNum, Integer pageSize);
     <T> void deleteByIds(String idName, List<T> ids);
     void deleteByQuery(String query);
     void deleteById(String idName, Object id);
     void deleteBean(SolrModel object, String idName);
     void updateBeanById(Object object, String idName);
     void updateBeanByQuerySting(Object object, String queryName);

    Map<String,Object> hotSearch();
}
