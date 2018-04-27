package com.pactera.tams.common.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.pactera.tams.jwt.Jwt;
import com.pactera.tams.module.system.entity.OpeMenu;
import com.pactera.tams.module.system.entity.SysRole;
import com.pactera.tams.module.system.entity.SysUser;
import com.pactera.tams.module.system.service.MenuService;
import com.pactera.tams.module.system.service.RoleService;
import com.pactera.tams.module.system.service.UserService;

/**
* 获取用户相关信息工具
* @Author: mjh
* @Date: 2018-03-18 21:01:19
*/
public class UserUtils {
	
	private static UserService userService = SpringContextHolder.getBean(UserService.class);
	private static RoleService roleService = SpringContextHolder.getBean(RoleService.class);
	private static MenuService menuService = SpringContextHolder.getBean(MenuService.class);

	public static final String USER_CACHE = "userCache";
	public static final String USER_CACHE_ID_ = "id_";
	public static final String USER_CACHE_LOGIN_NAME_ = "ln";
	
	public static final String CACHE_ROLE_LIST = "roleList";
	public static final String CACHE_MENU_LIST = "menuList";

	
	
	/**
	 * 获取当前请求中的token
	 * @return
	 */
	public static String getToken() {
		String token = null;
		HttpServletRequest request = ServletUtils.getRequest();
		if(null!=request) {
			token = request.getHeader("token");
		}
		return token;
	}
	
	/**
	 * 获取用户id
	 * @return
	 */
	public static String getUserId() {
		return Jwt.getUserId(getToken());
	}
	
	/**
	 * 获取租户id
	 * @return
	 */
	public static String getTenantId() {
		return Jwt.getTenantId(getToken());
	}
	
	/**
	 * 根据ID获取用户
	 * @param id
	 * @return 取不到返回null
	 */
	public static SysUser get(String id){
		SysUser user = (SysUser)UserUtils.getCache(USER_CACHE_ID_ + id);
		if (user ==  null){
			user = userService.getObjectById(id);
			if (user == null){
				return null;
			}
			user.setRoles(roleService.getListByUser(user));
			user.setMenus(menuService.getMenuTreeByUser(user));
			UserUtils.putCache(USER_CACHE_ID_ + user.getId(), user);
			UserUtils.putCache(USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
		}
		return user;
	}
	
	/**
	 * 获取当前用户
	 * @return
	 */
	public static SysUser getCurrentUser(){
		String id = UserUtils.getUserId();
		SysUser user = (SysUser)UserUtils.getCache(USER_CACHE_ID_ + id);
		if (user ==  null){
			user = userService.getObjectById(id);
			if (user == null){
				return null;
			}
			user.setRoles(roleService.getListByUser(user));
			user.setMenus(menuService.getMenuTreeByUser(user));
			UserUtils.putCache(USER_CACHE_ID_ + user.getId(), user);
			UserUtils.putCache(USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
		}
		return user;
	}
	

	
	/**
	 * 清除当前用户缓存
	 */
	public static void clearCache(){
		UserUtils.removeCache(CACHE_ROLE_LIST);
		UserUtils.removeCache(CACHE_MENU_LIST);
		UserUtils.clearCache(getUser());
	}
	
	/**
	 * 清除指定用户缓存
	 * @param user
	 */
	public static void clearCache(SysUser user){
		UserUtils.removeCache(USER_CACHE_ID_ + user.getId());
		UserUtils.removeCache(USER_CACHE_LOGIN_NAME_ + user.getLoginName());
	}
	
	/**
	 * 获取当前用户
	 * @return 取不到返回 new User()
	 */
	public static SysUser getUser(){
		SysUser user = userService.getObjectById(UserUtils.getUserId());
		if (user != null){
			return user;
		}
		// 如果没有登录，则返回实例化空的User对象。
		return new SysUser();
	}

	/**
	 * 获取当前用户角色列表
	 * @return
	 */
	public static List<SysRole> getRoleList(){
		@SuppressWarnings("unchecked")
		List<SysRole> roleList = (List<SysRole>)getCache(CACHE_ROLE_LIST);
		if (roleList == null){
			SysUser user = getUser();
			roleList = roleService.getListByUser(user);
			UserUtils.putCache(CACHE_ROLE_LIST, roleList);
		}
		return roleList;
	}
	
	/**
	 * 获取当前用户授权菜单
	 * @return
	 */
	public static List<OpeMenu> getMenuList(){
		@SuppressWarnings("unchecked")
		List<OpeMenu> menuList = (List<OpeMenu>)getCache(CACHE_MENU_LIST);
		if (menuList == null){
			SysUser user = getUser();
			menuList = menuService.getMenuTreeByUser(user);
			UserUtils.putCache(CACHE_MENU_LIST, menuList);
		}
		return menuList;
	}
	
	
	
	/**
	 * 用户cache
	 */
	static Cache<String, Object> userCache = CacheBuilder.newBuilder()
			.maximumSize(300).expireAfterWrite(1, TimeUnit.MINUTES).build();
	
	public static Object getCache(String key) {
		return getCache(key, null);
	}
	
	public static Object getCache(String key, Object defaultValue) {
		Object obj = userCache.getIfPresent(key);
		return obj==null?defaultValue:obj;
	}

	public static void putCache(String key, Object value) {
		userCache.put(key, value);
	}

	public static void removeCache(String key) {
		userCache.invalidate(key);
	}

	
}
