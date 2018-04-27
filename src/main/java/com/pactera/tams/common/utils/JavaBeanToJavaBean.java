package com.pactera.tams.common.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author ljp
 *
 */
public class JavaBeanToJavaBean {

	/**
	 * 对象--转换--对象
	 * @param orimodel
	 * @param castClass
	 * @return
	 */
	public static  <T1,T2> T2 convertBean(T1 orimodel, Class<T2> castClass) {
        T2 returnModel = null;
        try {
            returnModel = castClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("创建"+castClass.getName()+"对象失败");
        } 
        List<Field> fieldlist = new ArrayList<Field>(); //要转换的字段集合
        while (castClass != null && //循环获取要转换的字段,包括父类的字段
                !castClass.getName().toLowerCase().equals("java.lang.object")) {
            fieldlist.addAll(Arrays.asList(castClass.getDeclaredFields()));
            castClass = (Class<T2>) castClass.getSuperclass(); //得到父类,然后赋给自己
        }
        for (Field field : fieldlist) {
            PropertyDescriptor getpd = null;
            PropertyDescriptor setpd = null;
            try {
                getpd= new PropertyDescriptor(field.getName(), orimodel.getClass());
                setpd=new PropertyDescriptor(field.getName(), returnModel.getClass());
            } catch (Exception e) {
                continue;
            }
            try {
                Method getMethod = getpd.getReadMethod();
                Object transValue = getMethod.invoke(orimodel);
                Method setMethod = setpd.getWriteMethod();
                setMethod.invoke(returnModel, transValue);
            } catch (Exception e) {
                throw  new RuntimeException("cast "+orimodel.getClass().getName()+"to "
                        +castClass.getName()+" failed");
            }
        }
        return returnModel;
    }
}
