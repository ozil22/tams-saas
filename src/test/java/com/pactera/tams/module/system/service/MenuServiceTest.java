package com.pactera.tams.module.system.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.pactera.tams.Application;
import com.pactera.tams.common.utils.TreeUtils;
import com.pactera.tams.module.system.entity.OpeMenu;
import com.pactera.tams.module.system.entity.SysUser;
import com.pactera.tams.module.system.mapper.OpeMenuMapper;

import net.sf.json.JSONArray;

/**
* 菜单Service测试类
* @Author: mjh
* @Date: 2018-03-25 18:24:35
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@Import(Application.class)
public class MenuServiceTest {
	
	private Logger logger = LoggerFactory.getLogger(MenuServiceTest.class);
	
    @Autowired
    private MenuService menuService;
    
	@Autowired
	private OpeMenuMapper menuMapper;

    @Test
    public void test() {
    	SysUser user = new SysUser();
    	user.setId("975342095374041088");
    	List<OpeMenu> menus = null;
    	List<OpeMenu> menus2 = null;
		try {
			menus = menuService.getMenuTreeByUser(user);
			menus2 = menuMapper.getListByUser(user.getId(),null,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("menus：{}", menus);
    	logger.debug("menus2：{}", menus2);
    	
    	List<OpeMenu> tree = TreeUtils.listToTree(menus2);
    	
		//转为json格式        
		String json = JSONArray.fromObject(tree).toString();  
		logger.debug("json：{}", json);
    	
    }
    

    
    

}
