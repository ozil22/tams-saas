package com.pactera.tams.exception;

/**
* 错误信息枚举类
* @Author: mjh
* @Date: 2018-03-16 15:55:42
*/
public enum ErrorInfo {
	
	
	//1xx：用户登录注册相关信息
	
	/**
	 * 1001
	 * 用户不存在
	 */
	USER_NOT_EXIST("1001","用户不存在"),
	/**
	 * 1002
	 * 用户已存在
	 */
	USER_EXIST("1002","用户已存在"),
	/**
	 * 1003
	 * 账号不可用
	 */
	USER_NOT_AVAILABLE  ("1003","账号不可用"),
	/**
	 * 1004
	 * 用户或者密码错误
	 */
	LOGIN_INFO_ERROR("1004","用户或者密码错误"),
	/**
	 * 1005
	 * 获取用户角色菜单失败
	 */
	GET_USER_ROLE_MENU_ERROR("1005","获取用户角色菜单失败"),
	/**
	 * 1006
	 * 用户不明确
	 */
	USER_INDEFINITE("1006","用户不明确"),
	/**
	 * 1007
	 * 运营商服务不可用
	 */
	OPERATOR_SERVICE_NOT_AVAILABLE ("1007","运营商服务不可用"),
	/**
	 * 1008
	 * 原密码错误
	 */
	ORIGINAL_PASSWORD_ERROR ("1008","原密码错误"),
	
	
	//4xx：客户端错误，请求包含语法错误或者请求无法实现
	
	/**
	 * 4001
	 * 缺少必要参数
	 */
	MISSING_PARAM("4001","缺少必要参数"),
	/**
	 * 4002
	 * 非法参数
	 */
	ILLEGAL_PARAM("4002","非法参数"),
	/**
	 * 4003
	 * 类型不匹配
	 */
	TYPE_MISMATCH("4003","类型不匹配"),
	/**
	 * 4004
	 * HTTP请求方法不支持
	 */
	METHOD_NOT_SUPPORT("4004","HTTP请求方法不支持"),
	/**
	 * 4101
	 * 数据已存在
	 */
	EXIST("4101","数据已存在"),
	/**
	 * 4102
	 * 数据不存在
	 */
	NOT_EXIST("4102","数据不存在"),
	/**
	 * 4103
	 * 存在子数据
	 */
	EXIST_CHILDREN("4103","存在子数据"),
	/**
	 * 4104
	 * 存在关联用户
	 */
	EXIST_RELATED_USER("4104","存在关联用户"),
	/**
	 * 4105
	 * 存在关联角色
	 */
	EXIST_RELATED_ROLE("4104","存在关联角色"),
	
	
	//5xx：服务器错误，服务器不能实现一种明显无效的请求
	
	/**
	 * 5001
	 * 服务异常
	 */
	SERVICE_ERROR("5001","服务异常")
	
	;
	
	String code;
	
	String msg;
	
	ErrorInfo(String code, String msg){
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
}
