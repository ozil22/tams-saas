package com.pactera.tams.module.product.sevice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.pactera.tams.common.utils.DateUtils;
import com.pactera.tams.common.utils.IdGenerator;
import com.pactera.tams.common.utils.StringFormatUtils;
import com.pactera.tams.module.product.mapper.ProductCatalogMapper;
import com.pactera.tams.module.product.model.Product;
import com.pactera.tams.module.product.model.ProductCatalog;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;


@Service
public class ProductCatalogService {
	
	@Autowired
	ProductCatalogMapper productCatalogMapper;
	@Autowired
	ProductService productService;
	/**
	 * 保存或新增
	 */
	public ModelMap saveOrUpdate(ProductCatalog bean) {
		ModelMap result = StringFormatUtils.getResultMessage();
		ProductCatalog catalog = new ProductCatalog();
		catalog.setCatalog_name(bean.getCatalog_name());
		catalog.setTenant_id(bean.getTenant_id());
		ProductCatalog isExistBean = getObjectByBean(catalog);
		if (StringUtils.isNotEmpty(bean.getId())) {
			//修改
			if (isExistBean!=null&&isExistBean.getId().equals(bean.getId())) {
				result.put("retCode", -2);
				result.put("msg", "产品目录名称已经存在");
				return result;	
			}
			bean.setUpdate_date(DateUtils.getCurrentTime());
			productCatalogMapper.updateByPrimaryKeySelective(bean);
		}else {
			//新增
			if (isExistBean!=null) {
				result.put("retCode", -2);
				result.put("msg", "产品目录名称已经存在");
				return result;	
			}
			Product product = new Product();		
			product.setCatalog_id(bean.getParent_id());
			List list = productService.getList(product);
			if (list.size()>0) {
				result.put("retCode", -2);
				result.put("msg", "该产品目录下已存在产品");
				return result;
			}
			bean.setCreate_date(DateUtils.getCurrentTime());
			bean.setId(IdGenerator.uuid());
			productCatalogMapper.insert(bean);
		}
		return result;
	}
	
	/**
	 * 删除 id
	 */
	public void delete(String id) {
		productCatalogMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 判断目录是否存在子目录
	 */
	public boolean isExistSubNode(String id) {
		Example example = new Example(ProductCatalog.class);
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(id)) {
            criteria.andEqualTo("parent_id", id);
        }
		List<ProductCatalog> list = productCatalogMapper.selectByExample(example);
		return list.size()>0 ? true:false;
	}
	/**
	 * 查询
	 */
	public List getList(ProductCatalog bean) {
		
		return productCatalogMapper.getList(bean);
	}
	/**
	 * 查询目录下的子目录或子目录下的产品
	 */
	public List getProductOrCatalog(ProductCatalog bean) {
		Example example = new Example(ProductCatalog.class);
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(bean.getParent_id())) {
            criteria.andEqualTo("parent_id", bean.getParent_id());
        }
		return productCatalogMapper.selectByExample(example);
	}
	/**
	 * 查询实体 
	 * id
	 */
	public ProductCatalog getObjectById(String id) {
		return productCatalogMapper.selectByPrimaryKey(id);
	}
	/**
	 * 查询实体
	 * bean
	 */
	public ProductCatalog getObjectByBean(ProductCatalog bean) {
		return productCatalogMapper.selectOne(bean);
	}
	/**
	 * 获得该节点目录下的所有子目录id
	 */
	public void findChildCatalogs(List<ProductCatalog> list,String id) {
		ProductCatalog productCatalog = getObjectById(id);
		List<ProductCatalog> pcList=new ArrayList<ProductCatalog>();
		pcList.add(productCatalog);
		// 加入当前目录 
		list.addAll(pcList.stream().collect(Collectors.toList()));  
		//获得子目录
		ProductCatalog pCatalog = new ProductCatalog();
		pCatalog.setParent_id(id);
		List<ProductCatalog> pc1List = getList(pCatalog);
		for (ProductCatalog p : pc1List) {
			//递归
			findChildCatalogs(list,p.getId());
		}
		
	}
	/**
	 *获得上级目录的所有id
	 */
	public String findChildCls(StringBuffer buffer,String id) {
		ProductCatalog productCatalog = getObjectById(id);
		buffer.insert(0,","+productCatalog.getId());
			//获得上级目录
			ProductCatalog pCatalog = new ProductCatalog();
			
			if (productCatalog.getParent_id().equals("0")) {
				return buffer.toString();
			}else {
				pCatalog.setId(productCatalog.getParent_id());
				ProductCatalog objectByBean = getObjectByBean(pCatalog);
				
				findChildCls(buffer,objectByBean.getId());
			}

		return buffer.toString();
	}
}
