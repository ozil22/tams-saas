package com.pactera.tams.common.utils;   

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**  
 * @author zhuhao 
 * @version 创建时间：2017-9-22 上午9:41:47  
 * 类说明  
 */
public class MapToObjectFactory {
	 private static Logger logger = LoggerFactory.getLogger(MapToObjectFactory.class);
	/*
	 * map转换为实体
	 */
   public static <T> T map2Java(T javaBean, Map map) { 
       try {
           // 获取javaBean属性
           BeanInfo beanInfo = Introspector.getBeanInfo(javaBean.getClass());
           // 创建 JavaBean 对象
           Object obj = javaBean.getClass().newInstance();
           logger.debug("map==>"+map);
           PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
           if (propertyDescriptors != null && propertyDescriptors.length > 0) {
               String propertyName = null; // Bean属性名
               Object propertyValue = null; // Bean属性值
               for (PropertyDescriptor pd : propertyDescriptors) {
                   propertyName = pd.getName(); 
                   if (map.containsKey(propertyName)) {                	   
                       propertyValue = map.get(propertyName);    
                         if(propertyValue instanceof  List){
                        	if(!propertyName.contains("_relation_table")) {
	                       	   List<String> l = (List)propertyValue;
	                       	   String str = "";
	                       	   for(int i=0;i<l.size();i++) {
	                       		   if(i+1 == l.size()) {
	                       			   str += l.get(i);
	                       		   }else {
	                       			   str += l.get(i)+",";
	                       		   }
	                       	   }
	                       	   propertyValue = (Object)str;                      	  
                          }

                       }
                       if(propertyValue!=null&&!propertyValue.equals("")) {
                           pd.getWriteMethod().invoke(obj, new Object[] { propertyValue });
                       }  
                   }
               } 
               return (T) obj;
           }
       } catch (Exception e) {
    	   //logger.error("object===>"+map);
           e.printStackTrace();
       }

       return null;
   }
 
   public static void main(String[] args){
/*	   CompanyInfo c = new CompanyInfo();
	   map2Java(c,new HashMap());*/
   }

}
 