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
import com.pactera.tams.module.system.entity.SysRole;
import com.pactera.tams.module.system.entity.SysUser;


/**
* 角色Service测试类
* @Author: mjh
* @Date: 2018-03-25 18:03:18
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@Import(Application.class)
public class RoleServiceTest {
	
	private Logger logger = LoggerFactory.getLogger(RoleServiceTest.class);
	
    @Autowired
    private RoleService roleService;

    @Test
    public void test() {
    	SysUser user = new SysUser();
    	user.setId("975342095374041088");
    	List<SysRole> roles = roleService.getListByUser(user);
    	logger.debug("roles：{}", roles);
    }
    

    
    

}
