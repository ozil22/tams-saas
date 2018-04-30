package com.pactera.tams.module.tool.sevice;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.pactera.tams.module.solr.model.SolrModel;
import com.pactera.tams.module.solr.service.SolrModelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.pactera.tams.common.utils.DateUtils;
import com.pactera.tams.common.utils.IdGenerator;
import com.pactera.tams.module.tool.mapper.ToolComposeMapper;
import com.pactera.tams.module.tool.mapper.ToolMapper;
import com.pactera.tams.module.tool.model.Tool;
import com.pactera.tams.module.tool.model.ToolCompose;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 刀具
 *
 * @author ztx
 * 2018.1.12
 */
@Service
public class ToolService {
    @Autowired
    ToolMapper toolMapper;
    @Autowired
    ToolComposeMapper toolComposeMapper;
    @Autowired
    SolrModelService solrModelService;

    /**
     * 保存刀具
     */
    public int save(Tool bean) throws Exception {
        List<Map> combList = bean.getCombList_relation_table();
        if (combList != null && combList.size() > 0) {
            ToolCompose tc = new ToolCompose();
            for (Map comb : combList) {
                String smallId = (String) comb.get("id");
                tc.setSmall_tool_id(smallId);
                tc.setTool_id(bean.getId());
                tc.setId(IdGenerator.uuid());
                toolComposeMapper.insert(tc);
            }
        }
        int count = toolMapper.insert(bean);
        SolrModel solrModel = toolMapper.getSolr(bean.getId());
        solrModelService.addBeanIndex(solrModel);
        return count;
    }

    /**
     * 修改刀具信息
     */
    public void update(Tool bean) throws IllegalAccessException {
        Example example = new Example(Tool.class);
        Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(bean.getId())) {
            criteria.andEqualTo("id", bean.getId());
        }
        bean.setUpdate_date(DateUtils.getCurrentTime());
        toolMapper.updateByExampleSelective(bean, example);

        SolrModel solrModel = toolMapper.getSolr(bean.getId());
        StringBuilder solrql = new StringBuilder("index_name:tool AND ");
        solrql.append("tool_id").append(":").append(bean.getId());
        solrModelService.updateBeanByQuerySting(solrModel, solrql.toString());
    }

    /**
     * 刀具删除
     */
    public void delete(String id) {
        SolrModel solrModel = toolMapper.getSolr(id);
        StringBuilder solrql = new StringBuilder("index_name:tool AND ");
        solrql.append("tool_id").append(":").append(id);
        solrModelService.deleteByQuery(solrql.toString());
        toolMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询
     */
    public List getList(Tool bean) {
        if (bean.getPage() != null && bean.getRows() != null) {
            PageHelper.startPage(bean.getPage(), bean.getRows());
        }
        Example example = new Example(Tool.class);
        Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(bean.getCatalog_id())) {
            criteria.andEqualTo("catalog_id", bean.getCatalog_id());
        }
        return toolMapper.selectByExample(example);
    }

    /**
     * 分页查询
     */
    public List getPageList(Tool bean) {
        if (bean.getPage() != null && bean.getRows() != null) {
            PageHelper.startPage(bean.getPage(), bean.getRows());
        }
        return toolMapper.getList(bean);
    }

    /**
     * 查询实体
     * id
     */
    public Tool getObjectById(String id) {
        return toolMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询实体
     * bean
     */
    public Tool getObjectByBean(Tool bean) {
        return toolMapper.selectOne(bean);
    }

    /**
     * 获得刀具数量
     */
    public int getTotal(Tool bean) {
        return toolMapper.selectCount(bean);
    }

    /**
     * 获取推荐刀具前五个
     */
    public List getFiveList(Tool bean) {
        if (bean.getPage() != null && bean.getRows() != null) {
            PageHelper.startPage(bean.getPage(), bean.getRows());
        }
        Example example = new Example(Tool.class);
        Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(bean.getTenant_id())) {
            criteria.andEqualTo("tenant_id", bean.getTenant_id());
        }
        if (StringUtils.isNotEmpty(bean.getIs_recommend())) {
            criteria.andEqualTo("is_recommend", bean.getIs_recommend());
        }
        return toolMapper.selectByExample(example);
    }

    public void addClick(String id) {
        toolMapper.addClick(id);
    }

    public List<Tool> getAll() {
        return toolMapper.selectAll();
    }
}
