package com.pactera.tams.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** * @author  zhuhao 
      @date ：2017年10月10日 上午10:58:31  
      @version 1.0     
**/
public class Constants {
	/****************************上传目录*****************************************/
	public static final String UPLOAD_PATH= "attachment";
 
	
	/****************************走刀方式****************************************/ 
	public static final String TOOL_WAY = "TOOL_WAY";   //走刀方式
	
	/****************************品牌****************************************/ 
	public static final String BRAND = "BRAND";   //走刀方式
	
	/****************************用户对应关系*************************************/
	public static final String USER_COMPANY_RELATION = "1";
	public static final String USER_ORG_RELATION = "2";
	public static final String USER_ROLE_RELATION = "3";
	public static final String USER_POST_RELATION = "4";
	
	/***************************用户状态****************************************/
	public static final String USER_NOACTIVE_STATUS = "0";
	public static final String USER_ACTIVE_STATUS = "1";
	public static final String USER_CANCEL_STATUS = "4";
	 	/***************************日志字典******************************************/
	public static final Map<String, String> logMap ;
	
	public static String ENGLISH_CHAR_ARRAY[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
			"S", "T", "U", "V", "W", "X", "Y", "Z" };
	/***************************文件类型*********************************************/
	public static final List<String> fileTypes = new ArrayList<String>();
	public static final List<String> fileVideoTypes = new ArrayList<String>();
	static  
    {  
		//日志模块
		logMap = new HashMap(); 
		logMap.put("USER", "用户信息");
		logMap.put("USERLOGOUT", "用户注销");
		logMap.put("FORGETPASSWORD", "用户修改密码信息");  
		logMap.put("USERLOGIN", "用户登录信息");  
		logMap.put("COMPAMYINFO", "企业信息");  
		logMap.put("AUTHCOMPAMYINFO", "企业认证信息");
		logMap.put("COMPAMYINFOLOGOUT", "企业信息注销");
		logMap.put("ROLE", "角色信息");
		logMap.put("ORG", "组织信息");
		logMap.put("BUTTON", "权限按钮信息");
		logMap.put("MENUINSTALL", "模块信息");
		logMap.put("POST", "岗位信息");
		logMap.put("PRIVILEGE", "权限信息");
		logMap.put("PRODUCT", "商品信息");
		logMap.put("CUSTOMER", "客户信息");
		logMap.put("MENU", "菜单信息");
		
		//文件类型
		fileTypes.add("jpg");
		fileTypes.add("jpeg");
		fileTypes.add("bmp");
		fileTypes.add("gif");
		fileTypes.add("png");
		
		//文件类型
		fileVideoTypes.add("mp4");
		fileVideoTypes.add("flv");
    }      
 
	

}
