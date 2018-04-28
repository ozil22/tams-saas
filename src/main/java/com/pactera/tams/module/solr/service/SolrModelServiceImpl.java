package com.pactera.tams.module.solr.service;

import com.pactera.tams.common.utils.IdGenerator;
import com.pactera.tams.common.utils.StringFormatUtils;
import com.pactera.tams.module.product.mapper.ProductMapper;
import com.pactera.tams.module.product.mapper.ProductTechnicsSchemeMapper;
import com.pactera.tams.module.product.mapper.ProductTechnicsSchemeProcessMapper;
import com.pactera.tams.module.product.mapper.ProductTechnicsSchemeWorkStepMapper;
import com.pactera.tams.module.product.model.Product;
import com.pactera.tams.module.solr.model.SolrModel;
import com.pactera.tams.module.tool.mapper.ToolMapper;
import com.pactera.tams.module.tool.model.Tool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@ConfigurationProperties(prefix = "solr")
public class SolrModelServiceImpl implements SolrModelService {

    protected Log log = LogFactory.getLog(getClass());

    @Autowired
    private ToolMapper toolMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductTechnicsSchemeMapper productTechnicsSchemeMapper;
    @Autowired
    private ProductTechnicsSchemeProcessMapper productTechnicsSchemeProcessMapper;
    @Autowired
    private ProductTechnicsSchemeWorkStepMapper productTechnicsSchemeWorkStepMapper;

    private String httpSolrClient;
    private String coreName;

    private static SolrClient solr;

    public String getHttpSolrClient() {
        return httpSolrClient;
    }

    public void setHttpSolrClient(String httpSolrClient) {
        this.httpSolrClient = httpSolrClient;
    }

    public String getCoreName() {
        return coreName;
    }

    public void setCoreName(String coreName) {
        this.coreName = coreName;
    }

    private HttpSolrClient connetHttpSolrClientServer() {
        HttpSolrClient solr = new HttpSolrClient(httpSolrClient + coreName);
        solr.setSoTimeout(5000);
        solr.setConnectionTimeout(1000);
        solr.setDefaultMaxConnectionsPerHost(1000);
        solr.setMaxTotalConnections(5000);
        return solr;
    }

    /**
     * 添加全量索引
     *
     * @param solr
     * @throws Exception
     */
    @Override
    public ModelMap addBeanAllIndex() throws Exception {
        ModelMap result = StringFormatUtils.getResultMessage();
        long start = System.currentTimeMillis();
        List<SolrModel> solrModels = toolMapper.findAllSolr();
        solrModels.addAll(productMapper.findAllSolr());
        solrModels.addAll(productTechnicsSchemeMapper.findAllSolr());
        solrModels.addAll(productTechnicsSchemeProcessMapper.findAllSolr());
        solrModels.addAll(productTechnicsSchemeWorkStepMapper.findAllSolr());
        log.info("【数据查询完毕，总共" + solrModels.size() + "条,花费时间：" + (System.currentTimeMillis() - start) / 1000 + "秒】。");
        start = System.currentTimeMillis();
        int count = 0;
        solr = connetHttpSolrClientServer();
        for (SolrModel solrModel : solrModels) {
            try {
                SolrInputDocument doc = handleEntity(solrModel);
                solr.add(doc);

                count++;

            } catch (Exception e) {
                log.info("建立索引出现异常。。。。。。");
                e.printStackTrace();
            }
        }

        solr.commit();
        log.info("【数据处理完毕，总共花费时间：" + (System.currentTimeMillis() - start) / 1000 + "秒】。" + count);
        return result;
    }

    /**
     * 添加索引
     * @param object
     * @throws Exception
     */
    public ModelMap addBeanIndex(SolrModel solrModel) throws Exception {
        ModelMap result = StringFormatUtils.getResultMessage();
        long start = System.currentTimeMillis();
        solr = connetHttpSolrClientServer();
            try {
                SolrInputDocument doc = handleEntity(solrModel);
                solr.add(doc);

            } catch (Exception e) {
                log.info("建立索引出现异常。。。。。。");
                e.printStackTrace();
            }

        solr.commit();
        log.info("【数据处理完毕，总共花费时间：" + (System.currentTimeMillis() - start) / 1000 + "秒】。");
        return result;
    }

    /**
     * 删除全部索引
     *
     * @param solr
     * @throws Exception
     */
    public ModelMap deleteAllIndex() throws Exception {
        ModelMap result = StringFormatUtils.getResultMessage();
        solr = connetHttpSolrClientServer();
        try {
            solr.deleteByQuery("*:*");
            solr.commit(false, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 分页查询
     *
     * @param coreName
     * @param String
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Map<String, Object> search(String slorql, Integer pageNum, Integer pageSize) {
        log.info("=============slorql===========" + slorql);
        Map<String, Object> m = new HashMap<>();
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        SolrQuery query = new SolrQuery();
        query.setQuery(slorql);
//		query.set("fl", "price,goodsSeq,saleCount");//显示字段
        query.setStart(pageNum == null ? 0 : (pageNum - 1) * (pageSize == null ? 10 : pageSize));
        query.setRows(pageSize == null ? 10 : pageSize);
//		query.setSort("price", SolrQuery.ORDER.asc);
        solr = connetHttpSolrClientServer();
        try {
            QueryResponse response = solr.query(query);
            SolrDocumentList docs = response.getResults();
            long num =  docs.getNumFound();
            log.info("文档个数：" + docs.getNumFound());
            log.info("查询时间：" + response.getQTime());

            for (SolrDocument doc : docs) {
                Collection<String> fields = doc.getFieldNames();
                Map<String, String> map = new HashMap<String, String>();
                for (String fieldName : fields) {
                    String value = doc.getFieldValue(fieldName).toString();
                    if (!isEmpty(value)) {
                        map.put(fieldName, value);
                    }
                }
                result.add(map);
            }
            m.put("list",result);
            m.put("pageNum",pageNum);
            m.put("pageSize",pageSize);
            m.put("totalPage",(num  +  pageSize  - 1) / pageSize);
            return m;
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            log.info("Unknowned Exception!!!!");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据id集合从索引中删除记录
     *
     * @param solr
     * @param ids
     */
    public <T> void deleteByIds(String idName, List<T> ids) {
        solr = connetHttpSolrClientServer();
        try {
            if (ids.size() > 0) {
                StringBuffer query = new StringBuffer(idName + ":" + ids.get(0));
                for (int i = 1; i < ids.size(); i++) {
                    if (null != ids.get(i)) {
                        query.append(" OR " + idName + ":"
                                + ids.get(i).toString());
                    }
                }
                solr.deleteByQuery(query.toString());
                solr.commit(false, false);
                log.info("Delete from index by id list" + ids + " finished .");
            } else {
                log.info("Delete ids list is null.");
            }
        } catch (Exception e) {
            log.error("Delete from index by id list" + ids + " error, " + e.getMessage(), e);
            e.printStackTrace();
        }
    }

    /**
     * 根据查询从索引中删除
     *
     * @param solr
     * @param queryString
     */
    public void deleteByQuery(String query) {
        solr = connetHttpSolrClientServer();
        try {
            solr.deleteByQuery(query);
            //solr.commit(false, false);
            log.info("Delete from index by query string " + query
                    + " finished .");
        } catch (Exception e) {
            log.error("Delete from index by query Strng " + query + "error, "
                    + e.getMessage(), e);
            e.printStackTrace();
        }
    }


    /**
     * 根据id从索引中删除记录
     *
     * @param solr
     * @param idName 主键名
     * @param id     主键值
     */
    public void deleteById(String idName, Object id) {
        solr = connetHttpSolrClientServer();
        try {
            solr.deleteByQuery(idName + ":" + id.toString());
            solr.commit(false, false);
            log.info("Delete from index by id" + id
                    + " finished . operate param is：" + idName + ":"
                    + id.toString());
        } catch (Exception e) {
            log.error("Delete from index by id" + id + " error, "
                    + e.getMessage(), e);
        }
    }

    /**
     * 根据对象删除,实质是根据Id删除
     *
     * @param object 删除的对象
     * @param idName 对象的主键名
     */
    public void deleteBean(SolrModel object, String idName) {
        Class<?> cls = object.getClass();
        try {
            PropertyDescriptor pd = new PropertyDescriptor(idName, cls);
            Method method = pd.getReadMethod();
            Object o = method.invoke(object);
            if (o != null) {
                deleteById(idName, o);
            }
            log.info("Delete from index by object");
        } catch (Exception e) {
            log.error("Delete from index by object error, " + e.getMessage(), e);
            e.printStackTrace();
        }
    }

    /**
     * 更新单个记录
     *
     * @param object 要更新成的对象
     * @param idName 主键id名
     * @author pudongping
     */
    public void updateBeanById(Object object, String idName) {
        if (isEmpty(idName)) {
            solr = connetHttpSolrClientServer();
            Class<?> clzz = object.getClass();
            try {
                PropertyDescriptor pd = new PropertyDescriptor(idName, clzz);
                Method method = pd.getReadMethod();
                Object o = method.invoke(object);
                if (null != o) {
                    SolrQuery query = new SolrQuery();
                    query.setQuery(idName + ":" + o.toString());
                    query.setStart(0);
                    query.setRows(1);
                    QueryResponse response = solr.query(query);
                    SolrDocument document = response.getResults().get(0);
                    UpdateRequest updateRequest = new UpdateRequest();
                    updateRequest.setAction(AbstractUpdateRequest.ACTION.COMMIT, false, false);
                    updateRequest.add(solrDocument2SolrInputDocument(document, object, true));
                    updateRequest.process(solr);
                }
            } catch (IllegalAccessException e) {
                log.error("Update bean error" + object);
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                log.error("Update bean error" + object);
                e.printStackTrace();
            } catch (Exception e) {
                log.error("Update bean error" + object);
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过查询条件更新索引
     *
     * @param solr
     * @param object
     * @param queryName
     */
    public void updateBeanByQuerySting(Object object, String queryName) {
        if (isEmpty(queryName)) {
            try {
                SolrQuery query = new SolrQuery();
                query.setQuery(queryName);
                QueryResponse response = solr.query(query);
                SolrDocumentList docs = response.getResults();
                for (SolrDocument document : docs) {
                    UpdateRequest updateRequest = new UpdateRequest();
                    updateRequest.setAction(AbstractUpdateRequest.ACTION.COMMIT, false, false);
                    updateRequest.add(solrDocument2SolrInputDocument(document, object, true));
                    updateRequest.process(solr);
                }
            } catch (IllegalArgumentException e) {
                log.error("Update bean error" + object);
                e.printStackTrace();
            } catch (Exception e) {
                log.error("Update bean error" + object);
                e.printStackTrace();
            }
        }
    }

    @Override
    public Map<String, Object> hotSearch() {
       List<Tool> tools = toolMapper.findHot();
       List<Product> products = productMapper.findHot();
       Map<String, Object> hot = new HashMap<>();
       hot.put("tools",tools);
       hot.put("products",products);
       return hot;
    }

    public List<SolrModel> solrDocument2Entity(SolrDocumentList sds) {
        List<SolrModel> items = new ArrayList<SolrModel>();
        for (SolrDocument solrDocument : sds) {
            SolrModel solr = new SolrModel();
            Class<?> clazz = solr.getClass();
            Collection<String> fieldNameCollection = solrDocument.getFieldNames();
            Method method = null;
            for (String fieldName : fieldNameCollection) {
                Object value = solrDocument.getFieldValue(fieldName);
                try {
                    // 需要说明的是返回的结果集中的FieldNames()比类属性多
                    Field[] filedArrays = clazz.getDeclaredFields();// 获取类中所有属性
                    for (Field f : filedArrays) {
                        // 如果实体属性名和查询返回集中的字段名一致,填充对应的set方法
                        if (f.getName().equals(fieldName)) {
                            // 如果对应的属性的值为空或者为0，这不需要更新
                            PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);
                            method = pd.getWriteMethod();
                            if (f.getType().equals(List.class)) {
                                List fieldValue = List.class.cast(value);
                                if (fieldValue != null) {
                                    method.invoke(solr, fieldValue);
                                }
                            } else {
                                if (value.getClass().equals(ArrayList.class)) {
                                    method.invoke(solr, List.class.cast(value).get(0));
                                } else {
                                    method.invoke(solr, value);
                                }
                            }
                        }
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IntrospectionException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            items.add(solr);
        }
        return items;
    }


    public SolrInputDocument handleEntity(Object object) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        SolrInputDocument doc = new SolrInputDocument();
        Class<?> clazz = object.getClass();
        Field[] filedArrays = clazz.getDeclaredFields();// 获取类中所有属性
        for (Field field : filedArrays) {
            String modifier = Modifier.toString(field.getModifiers());//过滤不需要的字段
            if (modifier.contains("static final")) {
                continue;
            }

            String fieldName = field.getName();
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
            Method method = pd.getReadMethod();

            Object obj = method.invoke(object);
            Class<?> fieldType = field.getType();
            if ("id".equals(fieldName)) {
                if (obj == null) {
                    doc.addField("id", IdGenerator.nextId());
                } else {
                    doc.addField(fieldName, obj.toString());
                }
            } else if ("isDel".equals(fieldName)) {
                doc.addField("isDel", false);
            } else if ("opTime".equals(fieldName)) {
                doc.addField("opTime", new Date());
            } else if ("_version_".equals(fieldName)) {

            } else {
                if (fieldType.equals(Integer.class)) {
                    Integer fieldValue = Integer.class.cast(obj);
                    doc.addField(fieldName, fieldValue == null ? 0 : fieldValue);
                } else if (fieldType.equals(Float.class)) {
                    Float fieldValue = Float.class.cast(obj);
                    doc.addField(fieldName, fieldValue == null ? 0f : fieldValue);
                } else if (fieldType.equals(Double.class)) {
                    Double fieldValue = Double.class.cast(obj);
                    doc.addField(fieldName, fieldValue == null ? 0d : fieldValue);
                } else if (fieldType.equals(Short.class)) {
                    Short fieldValue = Short.class.cast(obj);
                    doc.addField(fieldName, fieldValue == null ? 0 : fieldValue);
                } else if (fieldType.equals(Long.class)) {
                    Long fieldValue = Long.class.cast(obj);
                    doc.addField(fieldName, fieldValue == null ? 0l : fieldValue);
                } else if (fieldType.equals(List.class)) {
                    List fieldValue = List.class.cast(obj);
                    doc.addField(fieldName, isEmpty(fieldValue) ? new ArrayList() : fieldValue);
                } else if (fieldType.equals(Boolean.class)) {
                    Boolean fieldValue = Boolean.class.cast(obj);
                    doc.addField(fieldName, isEmpty(fieldValue) ? false : fieldValue);
                } else if (fieldType.equals(Date.class)) {
                    Date fieldValue = Date.class.cast(obj);
                    doc.addField(fieldName, isEmpty(fieldValue) ? null : fieldValue);
                } else {
                    doc.addField(fieldName, isEmpty(obj) ? "" : obj.toString());
                }
            }
            if (!doc.containsKey("id")) {
                doc.addField("id", IdGenerator.nextId());
            }
        }
        log.info("处理数据中。。。");
        return doc;
    }

    public SolrInputDocument solrDocument2SolrInputDocument(
            SolrDocument sd, Object object, boolean isUpdate) throws IntrospectionException, ParseException {
        if (object != null && sd != null) {
            SolrInputDocument sid = new SolrInputDocument();
            Collection<String> fieldNameCollection = sd.getFieldNames(); // 得到所有的属性名
            Class<?> cls = object.getClass();
            List<String> allNames = new ArrayList<String>();
            for (String fieldName : fieldNameCollection) {
                try {
                    // 需要说明的是返回的结果集中的FieldNames()比类属性多
                    Field[] filedArrays = cls.getDeclaredFields(); // 获取类中所有属性
                    for (Field f : filedArrays) {
                        // 如果实体属性名和查询返回集中的字段名一致,填充对应的set方法
                        if (f.getName().equals(fieldName) && !fieldName.equals("_version_")) {
                            PropertyDescriptor pd = new PropertyDescriptor(fieldName, cls);
                            Method method = pd.getReadMethod();
                            // 如果对应的属性的值为空或者为0，这不需要更新
                            Object o = method.invoke(object);
                            Class<?> fieldType = cls.getDeclaredField(fieldName).getType();

                            if (fieldType.equals(Integer.class)) {
                                Integer fieldValue = Integer.class.cast(o);
                                if (isUpdate) {
                                    if (!isEmpty(fieldValue)) {
                                        sid.setField(fieldName, fieldValue);
                                    } else {
                                        sid.setField(fieldName, 0);
                                    }
                                } else {
                                    sid.addField(fieldName, fieldValue == null ? 0 : fieldValue);
                                }
                            } else if (fieldType.equals(Float.class)) {
                                Float fieldValue = Float.class.cast(o);
                                if (isUpdate) {
                                    if (!isEmpty(fieldValue)) {
                                        sid.setField(fieldName, fieldValue);
                                    } else {
                                        sid.setField(fieldName, 0f);
                                    }
                                } else {
                                    sid.addField(fieldName, fieldValue == null ? 0f : fieldValue);
                                }
                            } else if (fieldType.equals(Double.class)) {
                                Double fieldValue = Double.class.cast(o);
                                if (isUpdate) {
                                    if (!isEmpty(fieldValue)) {
                                        sid.setField(fieldName, fieldValue);
                                    } else {
                                        sid.setField(fieldName, 0.00);
                                    }
                                } else {
                                    sid.addField(fieldName, fieldValue == null ? 0d : fieldValue);
                                }
                            } else if (fieldType.equals(Short.class)) {
                                Short fieldValue = Short.class.cast(o);
                                if (isUpdate) {
                                    if (!isEmpty(fieldValue)) {
                                        sid.setField(fieldName, fieldValue);
                                    } else {
                                        sid.setField(fieldName, 0);
                                    }
                                } else {
                                    sid.addField(fieldName, fieldValue == null ? 0 : fieldValue);
                                }
                            } else if (fieldType.equals(Long.class)) {
                                Long fieldValue = Long.class.cast(o);
                                if (isUpdate) {
                                    if (!isEmpty(fieldValue)) {
                                        sid.setField(fieldName, fieldValue);
                                    } else {
                                        sid.setField(fieldName, 0l);
                                    }
                                } else {
                                    sid.addField(fieldName, fieldValue == null ? 0l : fieldValue);
                                }
                            } else if (fieldType.equals(List.class)) {
                                List fieldValue = List.class.cast(o);
                                if (isUpdate) {
                                    if (isEmpty(fieldValue)) {
                                        fieldValue = new ArrayList();
                                    }
                                    if ("goodsName".equals(fieldName) || "skuName".equals(fieldName)) {
                                        allNames.addAll(fieldValue);
                                        sid.setField(fieldName, fieldValue);
                                    }
                                } else {
                                    sid.addField(fieldName, fieldValue == null ? new ArrayList() : fieldValue);
                                }
                            } else if (fieldType.equals(Boolean.class)) {
                                Boolean fieldValue = Boolean.class.cast(o);
                                if (isUpdate) {
                                    if (!isEmpty(fieldValue)) {
                                        sid.setField(fieldName, fieldValue);
                                    } else {
                                        sid.setField(fieldName, false);
                                    }
                                } else {
                                    sid.addField(fieldName, fieldValue == null ? false : fieldValue);
                                }
                            } else if (fieldType.equals(Date.class)) {
                                Date fieldValue = Date.class.cast(o);//2016-09-01T04:51:12.708Z
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                                if (isUpdate) {
//									if(!"opTime".equals(fieldName)){
                                    if (!isEmpty(fieldValue)) {
                                        sid.setField(fieldName, sdf.parse(sdf.format(fieldValue)));
                                    } else {
                                        sid.setField(fieldName, null);
                                    }
//									}
                                } else {
                                    sid.addField(fieldName, sdf.parse(sdf.format(fieldValue)));
                                }
                            } else {
                                if (isUpdate) {
                                    if (isEmpty(o)) {
                                        sid.setField(fieldName, "");
                                    } else {
                                        sid.setField(fieldName, o.toString());
                                    }
                                } else {
                                    sid.addField(fieldName, isEmpty(o) ? "" : o.toString());
                                }
                            }
                        }
                    }

                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    log.error("请检查schema中的field是否不存在于PO类中！");
                    e.printStackTrace();
                }
            }
            if (isUpdate && !sid.containsKey("allName")) {
                sid.addField("allName", allNames);
            }
            return sid;
        }
        log.warn("即将要转换的SolrDocument或者要更新的Object为null");
        return null;
    }

    /**
     * 可以用于判断 Map,Collection,String,Array是否为空
     *
     * @param o
     * @return
     */
    @SuppressWarnings("all")
    public static boolean isEmpty(Object o) {
        if (o == null) return true;

        if (o instanceof String) {
            if (((String) o).length() == 0) {
                return true;
            }
        } else if (o instanceof Collection) {
            if (((Collection) o).isEmpty()) {
                return true;
            }
        } else if (o.getClass().isArray()) {
            if (Array.getLength(o) == 0) {
                return true;
            }
        } else if (o instanceof Map) {
            if (((Map) o).isEmpty()) {
                return true;
            }
        } else {
            return false;
        }

        return false;
    }
}
