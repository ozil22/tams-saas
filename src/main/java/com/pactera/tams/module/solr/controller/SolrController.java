package com.pactera.tams.module.solr.controller;

import com.pactera.tams.module.solr.model.SolrModel;
import com.pactera.tams.module.solr.service.SolrModelService;
import com.pactera.tams.module.solr.util.BaseResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

//@Api(value = "/solr", tags = {"搜索操作接口"})
@Controller
@RequestMapping(value = "/solr")
public class SolrController {

    @Resource
    private SolrModelService solrModelService;

    /**
     * 添加入solr索引
     */
    @ApiOperation(value = "添加全部索引", notes = "添加全部索引")
    @RequestMapping(value = "/pushDataIntoSolr", method = RequestMethod.GET)
    @ResponseBody
    public ModelMap pushDataIntoSolr() throws Exception {
        BaseResponse ress = new BaseResponse();
        ModelMap modelMap = solrModelService.addBeanAllIndex();
        return modelMap;
    }
    /**
     * 删除入solr索引
     */
    @ApiOperation(value = "删除全部索引", notes = "删除全部索引")
    @RequestMapping(value = "/deleteAllIndex", method = RequestMethod.GET)
    @ResponseBody
    public ModelMap deleteAllIndex() throws Exception {
        BaseResponse ress = new BaseResponse();
        ModelMap modelMap = solrModelService.deleteAllIndex();
        return modelMap;
    }

    /**
     * 快速查询
     */
    @ApiOperation(value = "/searchName", notes = "快速查询接口，用于搜索栏")
    @RequestMapping(value = "/searchName", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, String>> searchName(@RequestParam("queryString") String queryString, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        StringBuilder solrql = new StringBuilder("");
        solrql.append("tool_name:*").append(queryString).append("* OR ");
        solrql.append("product_name:*").append(queryString).append("*");
        return (List<Map<String, String>>) solrModelService.search(solrql.toString(), pageNum, pageSize).get("list");
    }

    /**
     * 按条件查询搜索引擎
     */
    @ApiOperation(value = "/search", notes = "搜索")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> search(@RequestParam("queryString") String queryString, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        StringBuilder solrql = new StringBuilder("");
        Class c = SolrModel.class;
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            solrql.append(field.getName()).append(":*").append(queryString).append("* OR ");
        }
        String q = solrql.substring(0, solrql.length() - 5);
        return solrModelService.search(q, pageNum, pageSize);
    }
    /**
     * 热门搜索
     */
    @ApiOperation(value = "/hotSearch", notes = "热门搜索")
    @RequestMapping(value = "/hotSearch", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> search() {
        return solrModelService.hotSearch();
    }
}
