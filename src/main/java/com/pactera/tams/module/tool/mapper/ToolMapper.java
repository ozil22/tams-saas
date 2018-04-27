package com.pactera.tams.module.tool.mapper;

import com.pactera.tams.module.solr.model.SolrModel;
import com.pactera.tams.module.tool.model.Tool;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
/**
 * 刀具
 * @author ztx
 *2018.1.12
 */
public interface ToolMapper extends Mapper<Tool>{
	List getList(Tool bean);
	List<SolrModel> findAllSolr();
	SolrModel getSolr(String id);
}
