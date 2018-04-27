package com.pactera.tams.common.entity;

/**
* 全局配置类
* @Author: mjh
* @Date: 2018-03-27 21:25:49
*/
public class Global {
	
	/**
	 * 当前对象实例
	 */
	private static Global global = new Global();
	
	/**
	 * 是/否
	 */
	public static final boolean YES = true;
	public static final boolean NO = false;
	
	/**
	 * 删除标记（0：未删除；1：删除；）
	 */
	public static final byte NORMAL = 0;
	public static final byte DELETED = 1;
	
	/**
	 * 启用状态（0：未启用；1：启用；）
	 */
	public static final byte ENABLE = 1;
	public static final byte DISABLE = 0;
	
	/**
	 * 显示状态（0：隐藏；1：显示；）
	 */
	public static final byte SHOW = 1;
	public static final byte HIDE = 0;
	
	/**
	 * 默认密码
	 */
	public static final String DEFAULT_PASSWORD = "123456";
	
	/**
	 * 上传文件基础虚拟路径
	 */
	public static final String USERFILES_BASE_URL = "/userfiles/";
	
	/**
	 * 获取当前对象实例
	 */
	public static Global getInstance() {
		return global;
	}

	
}
