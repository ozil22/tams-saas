package com.pactera.tams.init;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.pactera.tams.Application;
import com.pactera.tams.common.entity.Global;
import com.pactera.tams.common.utils.DateUtils;
import com.pactera.tams.common.utils.IdGenerator;
import com.pactera.tams.common.utils.StringUtils;
import com.pactera.tams.module.system.entity.OpeTenant;
import com.pactera.tams.module.system.entity.SysUser;
import com.pactera.tams.module.system.mapper.OpeTenantMapper;
import com.pactera.tams.module.system.mapper.OpeMenuMapper;
import com.pactera.tams.module.system.mapper.SysOrgMapper;
import com.pactera.tams.module.system.mapper.SysPostMapper;
import com.pactera.tams.module.system.mapper.SysRoleMapper;
import com.pactera.tams.module.system.mapper.SysRoleMenuMapper;
import com.pactera.tams.module.system.mapper.SysUserMapper;
import com.pactera.tams.module.system.mapper.SysUserOrgMapper;
import com.pactera.tams.module.system.mapper.SysUserRoleMapper;

/**
 * 
 * @Author: mjh
 * @Date: 2018-03-31 10:51:49
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(Application.class)
public class InitDataTest {
	
    @Autowired
    private OpeTenantMapper tenantMapper;
    
    @Autowired
    private OpeMenuMapper menuMapper;
    
    @Autowired
    private SysOrgMapper orgMapper;
    
    @Autowired
    private SysPostMapper postMapper;

    @Autowired
    private SysUserMapper userMapper;
    
    @Autowired
    private SysRoleMapper roleMapper;
    
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    
    @Autowired
    private SysUserOrgMapper userOrgMapper;
    
    @Autowired
    private SysRoleMenuMapper roleMenuMapper;
    
    public static String tenantId_1;
    
    public static String tenantId_2;
    
    public static String tenantCreateBy;
    
    public static String adminPassword;
    
    @BeforeClass
    public static void intiParams() {
    	tenantId_1 = IdGenerator.uuid();
    	tenantId_2 = IdGenerator.uuid();
    	tenantCreateBy = "system";
    	adminPassword = "admin123456";
    }
    
	@Test
	public void test() {
		OpeTenant tenant = new OpeTenant();
		tenant.setId(tenantId_1);
		tenant.setCreateBy(tenantCreateBy);
		tenant.setCreateDate(DateUtils.getNowDate());
		tenant.setDeleteFlag(Global.NORMAL);
		tenant.setEnabled(Global.ENABLE);
		tenant.setName("文思海辉技术有限公司");
		tenant.setShortName("文思海辉");
		tenant.setEnname("pactera");
		tenantMapper.insert(tenant);
		
		OpeTenant tenant2 = new OpeTenant();
		tenant2.setId(tenantId_2);
		tenant2.setCreateBy(tenantCreateBy);
		tenant2.setCreateDate(DateUtils.getNowDate());
		tenant2.setDeleteFlag(Global.NORMAL);
		tenant2.setEnabled(Global.ENABLE);
		tenant2.setName("海航集团有限公司");
		tenant2.setShortName("海航集团");
		tenant2.setEnname("hnagroup");
		tenantMapper.insert(tenant2);
		
		SysUser admin = new SysUser();
		admin.setId(IdGenerator.uuid());
		admin.setCreateBy(tenant.getId());
		admin.setCreateDate(DateUtils.getNowDate());
		admin.setDeleteFlag(Global.NORMAL);
		admin.setEnabled(Global.ENABLE);
		admin.setTenantId(tenantId_1);
		admin.setLoginName("admin");
		admin.setName("文思海辉租户管理员");
		admin.setNo("p0000001");
		admin.setMobile("18688886666");
		admin.setSex((byte) 1);
		
		//保存盐和密码
		String slat = StringUtils.getRandomStr(10);
		//默认密码
		String md5Pwd = new Md5Hash(adminPassword, slat).toString();
		admin.setPassword(md5Pwd);
		admin.setSalt(slat);
		userMapper.insert(admin);
		
		
		SysUser admin2 = new SysUser();
		admin2.setId(IdGenerator.uuid());
		admin2.setCreateBy(tenant.getId());
		admin2.setCreateDate(DateUtils.getNowDate());
		admin2.setDeleteFlag(Global.NORMAL);
		admin2.setEnabled(Global.ENABLE);
		admin2.setTenantId(tenantId_2);
		admin2.setLoginName("admin");
		admin2.setName("海航集团租户管理员");
		admin2.setNo("p0000001");
		admin2.setMobile("18866668888");
		admin2.setSex((byte) 1);
		
		//保存盐和密码
		String slat2 = StringUtils.getRandomStr(10);
		//默认密码
		String md5Pwd2 = new Md5Hash(adminPassword, slat2).toString();
		admin2.setPassword(md5Pwd2);
		admin2.setSalt(slat2);
		userMapper.insert(admin2);
		
	}

}
