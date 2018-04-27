/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : tams_saas

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2018-04-22 17:22:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ope_menu
-- ----------------------------
DROP TABLE IF EXISTS `ope_menu`;
CREATE TABLE `ope_menu` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `parent_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '父级id',
  `parent_ids` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '所有父级id',
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型（1菜单，2页签，3按钮）',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `href` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '连接地址',
  `target` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '目标（预留）',
  `icon` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '图标样式',
  `is_show` tinyint(4) DEFAULT NULL COMMENT '是否显示',
  `code` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '编码（按钮级权限标识）',
  `permission` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '权限标识（shiro预留）',
  `create_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `delete_flag` tinyint(4) DEFAULT NULL COMMENT '删除标识（1是，0否）',
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='菜单表';

-- ----------------------------
-- Records of ope_menu
-- ----------------------------
INSERT INTO `ope_menu` VALUES ('1', '0', '0,', '系统管理', '1', '200', '/system', null, null, '1', null, null, null, '2018-03-25 18:26:18', null, '2018-03-25 18:26:21', '0', null);
INSERT INTO `ope_menu` VALUES ('11', '40', '0,40,', '机床管理', '1', '108', '/tams/machine', null, 'appstore', '1', null, null, null, '2018-03-25 18:26:18', null, '2018-03-25 18:26:21', '0', null);
INSERT INTO `ope_menu` VALUES ('12', '11', '0,40,11,', '机床管理', '1', '100', '/tams/machine/machine', null, null, '1', null, null, null, '2018-03-25 18:26:18', null, '2018-03-25 18:26:21', '0', null);
INSERT INTO `ope_menu` VALUES ('13', '11', '0,40,11,', '机床品牌管理', '1', '100', '/tams/machine/machine-brand', null, null, '1', null, null, null, '2018-03-25 18:26:18', null, '2018-03-25 18:26:21', '0', null);
INSERT INTO `ope_menu` VALUES ('14', '42', '0,40,42,', '材料管理', '1', '100', '/tams/material/material-list', null, null, '1', null, null, null, '2018-03-25 18:26:18', null, '2018-03-25 18:26:21', '0', null);
INSERT INTO `ope_menu` VALUES ('16', '40', '0,40,', '产品管理', '1', '104', '/tams/product', null, 'appstore', '1', null, null, null, '2018-03-25 18:26:18', null, '2018-03-25 18:26:21', '0', null);
INSERT INTO `ope_menu` VALUES ('17', '16', '0,40,16,', '产品列表', '1', '100', '/tams/product/product-list', null, null, '1', null, null, null, '2018-03-25 18:26:18', null, '2018-03-25 18:26:21', '0', null);
INSERT INTO `ope_menu` VALUES ('19', '40', '0,40,', '刀具管理', '1', '105', '/tams/tool', null, 'appstore', '1', null, null, null, '2018-03-25 18:26:18', null, '2018-03-25 18:26:21', '0', null);
INSERT INTO `ope_menu` VALUES ('2', '1', '0,1,', '基础管理', '1', '100', '/system', null, 'appstore', '1', null, null, null, '2018-03-25 18:27:24', null, '2018-03-25 18:27:27', '0', null);
INSERT INTO `ope_menu` VALUES ('20', '19', '0,40,19,', '刀具列表', '1', '100', '/tams/tool/tool-list', null, null, '1', null, null, null, '2018-03-25 18:26:18', null, '2018-03-25 18:26:21', '0', null);
INSERT INTO `ope_menu` VALUES ('21', '19', '0,40,19,', '规格列表', '1', '100', '/tams/tool/specification-list', null, null, '1', null, null, null, '2018-03-25 18:26:18', null, '2018-03-25 18:26:21', '0', null);
INSERT INTO `ope_menu` VALUES ('23', '40', '0,40,', '视频管理', '1', '106', '/tams/video', null, 'appstore', '1', null, null, null, '2018-03-25 18:26:18', null, '2018-03-25 18:26:21', '0', null);
INSERT INTO `ope_menu` VALUES ('24', '23', '0,40,23,', '视频列表', '1', '100', '/tams/video/video-list', null, null, '1', null, null, null, '2018-03-25 18:26:18', null, '2018-03-25 18:26:21', '0', null);
INSERT INTO `ope_menu` VALUES ('29', '40', '0,40,', '工艺列表管理', '1', '107', '/tams/technics', null, 'appstore', '1', null, null, null, '2018-03-25 18:26:18', null, '2018-03-25 18:26:21', '0', null);
INSERT INTO `ope_menu` VALUES ('3', '2', '0,1,2,', '企业信息', '1', '100', '/system', '', '', '1', null, '', '', '2018-03-25 18:27:24', '', '2018-03-25 18:27:27', '0', '');
INSERT INTO `ope_menu` VALUES ('30', '29', '0,40,29,', '工艺列表', '1', '100', '/tams/technics/technics-list', null, null, '1', null, null, null, '2018-03-25 18:26:18', null, '2018-03-25 18:26:21', '0', null);
INSERT INTO `ope_menu` VALUES ('32', '40', '0,40,', '用户反馈管理', '1', '103', '/tams/feedback', null, 'appstore', '1', null, null, null, '2018-03-25 18:26:18', null, '2018-03-25 18:26:21', '0', null);
INSERT INTO `ope_menu` VALUES ('33', '32', '0,40,32,', '我的加工反馈', '1', '100', '/tams/feedback/feedback-list', null, null, '1', null, null, null, '2018-03-25 18:26:18', null, '2018-03-25 18:26:21', '0', null);
INSERT INTO `ope_menu` VALUES ('35', '40', '0,40,', '主页', '1', '101', '/tams/home', null, 'appstore', '1', null, null, null, null, null, null, '0', null);
INSERT INTO `ope_menu` VALUES ('36', '35', '0,40,35,', '首页', '1', '400', '/tams/home/front', null, null, '1', null, null, null, null, null, null, '0', null);
INSERT INTO `ope_menu` VALUES ('37', '41', '0,40,41,', '通知公告', '1', '400', '/tams/notice/notice-list', null, null, '1', null, null, null, null, null, null, '0', null);
INSERT INTO `ope_menu` VALUES ('4', '2', '0,1,2,', '组织用户', '1', '200', '/system/org', '', '', '1', null, '', '', '2018-03-25 18:27:24', '', '2018-03-25 18:27:27', '0', '');
INSERT INTO `ope_menu` VALUES ('40', '0', '0,', 'TMAS', '1', '100', '/tams', null, null, '1', null, null, null, '2018-03-25 18:26:18', null, '2018-03-25 18:26:21', '0', null);
INSERT INTO `ope_menu` VALUES ('41', '40', '0,40,', '通知公告', '1', '102', '/tams/notice', null, 'appstore', '1', null, null, null, null, null, null, '0', null);
INSERT INTO `ope_menu` VALUES ('42', '40', '0,40,', '材质管理', '1', '109', '/tams/material', null, 'appstore', '1', null, null, null, null, null, null, '0', null);
INSERT INTO `ope_menu` VALUES ('43', '40', '0,40,', '报表管理', '1', '110', '/tams/report', null, null, '1', null, null, null, null, null, null, '0', null);
INSERT INTO `ope_menu` VALUES ('5', '2', '0,1,2,', '角色权限', '1', '300', '/system/role', null, null, '1', 'role', null, null, null, null, null, '0', null);
INSERT INTO `ope_menu` VALUES ('6', '2', '0,1,2,', '岗位', '1', '400', '/system/post', null, null, '1', null, null, null, null, null, null, '0', null);
INSERT INTO `ope_menu` VALUES ('8', '5', '0,1,2,5,', '新增角色', '3', '100', '/system/role/add', '', '', '1', 'btn-add', '', '975342095374041088', '2018-03-29 17:46:12', '', '2018-03-21 22:34:00', '0', '');
INSERT INTO `ope_menu` VALUES ('9', '5', '0,1,2,5,', '修改角色', '3', '200', '/system/role/edit', null, '', '1', 'btn-edit', null, '975342095374041088', '2018-03-29 17:46:12', null, null, '0', null);

-- ----------------------------
-- Table structure for ope_tenant
-- ----------------------------
DROP TABLE IF EXISTS `ope_tenant`;
CREATE TABLE `ope_tenant` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `short_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '简称',
  `enname` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '英文名称',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型（预留）',
  `province_code` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '所在省编码',
  `province_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '所在省名称',
  `city_code` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '所在市编码',
  `city_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '所在市名称',
  `district_code` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '所在县/区编码',
  `district_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '所在县/区名称',
  `address` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '详细地址',
  `telephone` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '公司电话',
  `fax` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '传真',
  `business_licence` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '营业执照路径',
  `contact` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '联系人',
  `contact_number` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '联系电话',
  `enabled` tinyint(4) DEFAULT NULL COMMENT '启用状态（1是，0否）',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `delete_flag` tinyint(4) DEFAULT NULL COMMENT '删除标识（1是，0否）',
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户';

-- ----------------------------
-- Records of ope_tenant
-- ----------------------------
INSERT INTO `ope_tenant` VALUES ('4fc7ce16d79b46f69697e2041cc35dc6', '文思海辉技术有限公司', '文思海辉', 'pactera', null, null, null, null, null, null, null, null, null, null, null, null, null, '1', null, 'system', '2018-03-31 11:54:42', null, null, '0', null);
INSERT INTO `ope_tenant` VALUES ('f4f5c7310c28425a8cddfb1540bf8ec5', '海航集团有限公司', '海航集团', 'hnagroup', null, null, null, null, null, null, null, null, null, null, null, null, null, '1', null, 'system', '2018-03-31 11:54:43', null, null, '0', null);

-- ----------------------------
-- Table structure for ope_tenant_module
-- ----------------------------
DROP TABLE IF EXISTS `ope_tenant_module`;
CREATE TABLE `ope_tenant_module` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `tenant_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '租户id',
  `module_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '模块id',
  `module_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '模块名称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `delete_flag` tinyint(4) DEFAULT NULL COMMENT '删除标识（1是，0否）',
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户模块';

-- ----------------------------
-- Records of ope_tenant_module
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `P_CODE` varchar(30) DEFAULT NULL COMMENT '父编码（类型）',
  `CODE` varchar(200) DEFAULT NULL COMMENT '编码',
  `FULL_NAME` varchar(100) DEFAULT NULL COMMENT '全称',
  `SHORT_NAME` varchar(20) DEFAULT NULL COMMENT '简写名称',
  `ENGLISH_NAME` varchar(100) DEFAULT NULL COMMENT '英文名称',
  `PIC_PATH` varchar(255) DEFAULT NULL COMMENT '图片路径',
  `IS_SHOW` int(11) DEFAULT NULL COMMENT '是否显示0:不显示 1:显示',
  `SORT` int(11) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '0', 'HENTENT_TYPE', '企业类型', '企业类型', '', null, '1', '0');
INSERT INTO `sys_dict` VALUES ('10', 'MACHINE_BRAND', 'MACHINE_BRAND_FE', 'FEMCO', '', '', null, '1', '9');
INSERT INTO `sys_dict` VALUES ('11', 'MACHINE_BRAND', 'MACHINE_BRAND_HA', 'HAAS', '', '', null, '1', '10');
INSERT INTO `sys_dict` VALUES ('12', 'MACHINE_BRAND', 'MACHINE_BRAND_SY', '沈阳机床厂', '', '', null, '1', '11');
INSERT INTO `sys_dict` VALUES ('13', 'MACHINE_BRAND', 'MACHINE_BRAND_JB', '日本机床', '', '', null, '1', '12');
INSERT INTO `sys_dict` VALUES ('14', 'MACHINE_BRAND_FE', '1', 'WVL-12', '', '', null, '1', '13');
INSERT INTO `sys_dict` VALUES ('15', 'MACHINE_BRAND_FE', '2', 'WVL-13', '', '', null, '1', '14');
INSERT INTO `sys_dict` VALUES ('16', 'MACHINE_BRAND_FE', '3', 'WVL-14', '', '', null, '1', '15');
INSERT INTO `sys_dict` VALUES ('17', 'MACHINE_BRAND_HA', '1', 'BMC-110R2', '', '', null, '1', '16');
INSERT INTO `sys_dict` VALUES ('18', 'MACHINE_BRAND_HA', '2', 'BMC-110', '', '', null, '1', '17');
INSERT INTO `sys_dict` VALUES ('19', 'MACHINE_BRAND_HA', '3', 'BMC-110R2', '', '', null, '1', '18');
INSERT INTO `sys_dict` VALUES ('2', 'HENTENT_TYPE', '1', '贸易', '贸易', '', null, '1', '1');
INSERT INTO `sys_dict` VALUES ('20', 'MACHINE_BRAND_SY', '1', 'Z3050X16/1', '', '', null, '1', '19');
INSERT INTO `sys_dict` VALUES ('21', 'MACHINE_BRAND_SY', '2', 'Z3050X1110', '', '', null, '1', '20');
INSERT INTO `sys_dict` VALUES ('22', 'MACHINE_BRAND_JB', '1', 'JBRS', '', '', null, '1', '21');
INSERT INTO `sys_dict` VALUES ('23', 'MACHINE_BRAND_JB', '2', 'JBRSAAA', '', '', null, '1', '22');
INSERT INTO `sys_dict` VALUES ('3', 'HENTENT_TYPE', '2', '生产', '生产', '', null, '1', '2');
INSERT INTO `sys_dict` VALUES ('4', 'HENTENT_TYPE', '3', '综合', '综合', '', null, '1', '3');
INSERT INTO `sys_dict` VALUES ('5', '0', 'TOOL_WAY', '走刀方式', '企业类型', '', null, '1', '4');
INSERT INTO `sys_dict` VALUES ('6', 'TOOL_WAY', '1', '插铣', '', '', null, '1', '5');
INSERT INTO `sys_dict` VALUES ('7', 'TOOL_WAY', '2', '斜坡铣', '', '', null, '1', '6');
INSERT INTO `sys_dict` VALUES ('8', 'TOOL_WAY', '3', '钻铣', '', '', null, '1', '7');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `TITLE` varchar(255) DEFAULT NULL COMMENT '标题',
  `CONTENT` text COMMENT '内容',
  `STATUS` tinyint(4) DEFAULT NULL COMMENT '状态（0：未读，1：已读）',
  `USER_ID_STR` text COMMENT '查看人id',
  `SORT` int(11) DEFAULT '1' COMMENT '排序',
  `TENANT_ID` varchar(32) DEFAULT NULL COMMENT '租户id',
  `UPDATE_DATE` varchar(20) DEFAULT NULL COMMENT '更新日期',
  `UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `CREATE_DATE` varchar(20) DEFAULT NULL COMMENT '创建日期',
  `CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业公告表';

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES ('07d897bf9df64ee9a4a549c741cf7a1e', '33', '<p>33333</p>', '0', null, '1', '4fc7ce16d79b46f69697e2041cc35dc6', null, null, '2018-04-20 16:28:27', '0f0ca899e7bd45e289452f0915fa6044');
INSERT INTO `sys_notice` VALUES ('7cbf160c60514e71a4816bbf67bbe0b7', '关于tmas的推广使用通知', '<p>各位公司同仁大家好！</p><p>西安万威刀具集团推出了机械行业最牛的产品工艺管理系统，秒杀一切。</p><p>从今天起大家可以开始试用。</p><p>及时反馈问题到杨妮处！</p>', '1', 'null,2b0a1d6269a2482f9cfc5933b0400fdc', '1', '706829e0701b44baa072ee41d9636f7a', null, null, '2018-01-25 10:08:59', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_notice` VALUES ('b15c1c4a0a8241db814ee99a073c90a5', '通知测试2', '<p><strong>各位公司同仁大家好！</strong></p><p><sup><em><u>西安万威刀具集团推出了机械行业最牛的产品工艺管理系统，秒杀一切。</u></em></sup></p><p>从今天起大家可以开始试用。</p><p>及时反馈问题到杨妮处！</p>', '1', 'null,2b0a1d6269a2482f9cfc5933b0400fdc', '1', '706829e0701b44baa072ee41d9636f7a', null, null, '2018-01-25 10:10:04', '2b0a1d6269a2482f9cfc5933b0400fdc');

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `parent_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '父级id',
  `parent_ids` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '所有父级id',
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `short_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '简称',
  `telephone` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '电话',
  `fax` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '传真',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `delete_flag` tinyint(4) DEFAULT NULL COMMENT '删除标识（1是，0否）',
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `tenant_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_index` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='组织机构';

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES ('1', '0', '0,', '文思海辉技术有限公司', '文思海辉', null, null, '100', null, null, null, null, '0', null, '4fc7ce16d79b46f69697e2041cc35dc6');
INSERT INTO `sys_org` VALUES ('2', '1', '0,1,', '文思海辉技术有限公司西安分公司', '文思海辉西安分公司', null, null, '100', null, null, null, null, '0', null, '4fc7ce16d79b46f69697e2041cc35dc6');
INSERT INTO `sys_org` VALUES ('3', '1', '0,1,', '文思海辉技术有限公司成都分公司', '文思海辉成都分公司', '', '', '200', '', '2018-03-26 12:25:54', '', '2018-03-28 12:26:01', '0', '', '4fc7ce16d79b46f69697e2041cc35dc6');
INSERT INTO `sys_org` VALUES ('4', '2', '0,1,2,', '文思海辉技术有限公司西安分公司研发部', '文思海辉西安研发部', null, null, '100', null, null, null, null, '0', null, '4fc7ce16d79b46f69697e2041cc35dc6');
INSERT INTO `sys_org` VALUES ('5', '2', '0,1,2,', '文思海辉技术有限公司西安分公司产品部', '文思海辉西安产品部', null, null, '200', null, null, null, null, '0', null, '4fc7ce16d79b46f69697e2041cc35dc6');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `name` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '岗位名称',
  `code` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '岗位编码（预留）',
  `type` int(11) DEFAULT NULL COMMENT '岗位类型（预留）',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `delete_flag` tinyint(4) DEFAULT NULL COMMENT '删除标识（1是，0否）',
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `tenant_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='岗位';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES ('77e96a6abd2e4782a94445171f24bf9e', 'java开发工程师', 'JAVA_DEV', null, null, '0f0ca899e7bd45e289452f0915fa6044', '2018-03-31 12:27:06', null, null, '0', '系统开发及设计', '4fc7ce16d79b46f69697e2041cc35dc6');
INSERT INTO `sys_post` VALUES ('b099e8de16cc419793fb7d9f03a8f3cf', '产品经理', 'PRO_MANAGER', null, null, '0f0ca899e7bd45e289452f0915fa6044', '2018-03-31 12:29:53', '0f0ca899e7bd45e289452f0915fa6044', '2018-03-31 12:33:38', '0', '需求调研及产品设计', '4fc7ce16d79b46f69697e2041cc35dc6');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `dept_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '部门id（部门角色预留）',
  `name` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `code` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '编码',
  `role_type` tinyint(4) DEFAULT NULL COMMENT '角色类型（预留）',
  `data_scope` tinyint(4) DEFAULT NULL COMMENT '数据范围（数据权限预留）',
  `sys_role` tinyint(4) DEFAULT NULL COMMENT '系统内置角色（1是，0否）',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `delete_flag` tinyint(4) DEFAULT NULL COMMENT '删除标识（1是，0否）',
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `tenant_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '', '管理员', 'admin', '1', '1', '1', '1', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-03-25 17:23:42', '0f0ca899e7bd45e289452f0915fa6044', '2018-03-31 12:19:06', '0', '租户管理员使用，用于管理租户下用户及系统信息', '4fc7ce16d79b46f69697e2041cc35dc6');
INSERT INTO `sys_role` VALUES ('2', '', '普通员工', 'user', '1', '3', '1', '2', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-03-25 17:23:42', '0f0ca899e7bd45e289452f0915fa6044', '2018-03-31 12:19:44', '0', '普通员工，只有基本的用户使用界面', '4fc7ce16d79b46f69697e2041cc35dc6');
INSERT INTO `sys_role` VALUES ('3', '', '管理员', 'admin', '1', '1', '1', '1', 'f4f5c7310c28425a8cddfb1540bf8ec5', '2018-03-25 17:23:42', 'f4f5c7310c28425a8cddfb1540bf8ec5', '2018-03-31 12:19:06', '0', '租户管理员使用，用于管理租户下用户及系统信息', 'f4f5c7310c28425a8cddfb1540bf8ec5');
INSERT INTO `sys_role` VALUES ('ed2900080a9d424588ff1daf9098c7b1', null, 'cc', null, null, null, null, null, '0f0ca899e7bd45e289452f0915fa6044', '2018-04-17 11:05:00', null, null, '0', 'cc', '4fc7ce16d79b46f69697e2041cc35dc6');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `menu_id` varchar(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色-菜单';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1');
INSERT INTO `sys_role_menu` VALUES ('1', '11');
INSERT INTO `sys_role_menu` VALUES ('1', '12');
INSERT INTO `sys_role_menu` VALUES ('1', '13');
INSERT INTO `sys_role_menu` VALUES ('1', '14');
INSERT INTO `sys_role_menu` VALUES ('1', '16');
INSERT INTO `sys_role_menu` VALUES ('1', '17');
INSERT INTO `sys_role_menu` VALUES ('1', '19');
INSERT INTO `sys_role_menu` VALUES ('1', '2');
INSERT INTO `sys_role_menu` VALUES ('1', '20');
INSERT INTO `sys_role_menu` VALUES ('1', '21');
INSERT INTO `sys_role_menu` VALUES ('1', '23');
INSERT INTO `sys_role_menu` VALUES ('1', '24');
INSERT INTO `sys_role_menu` VALUES ('1', '29');
INSERT INTO `sys_role_menu` VALUES ('1', '3');
INSERT INTO `sys_role_menu` VALUES ('1', '30');
INSERT INTO `sys_role_menu` VALUES ('1', '32');
INSERT INTO `sys_role_menu` VALUES ('1', '33');
INSERT INTO `sys_role_menu` VALUES ('1', '35');
INSERT INTO `sys_role_menu` VALUES ('1', '36');
INSERT INTO `sys_role_menu` VALUES ('1', '37');
INSERT INTO `sys_role_menu` VALUES ('1', '4');
INSERT INTO `sys_role_menu` VALUES ('1', '40');
INSERT INTO `sys_role_menu` VALUES ('1', '41');
INSERT INTO `sys_role_menu` VALUES ('1', '42');
INSERT INTO `sys_role_menu` VALUES ('1', '5');
INSERT INTO `sys_role_menu` VALUES ('1', '6');
INSERT INTO `sys_role_menu` VALUES ('1', '8');
INSERT INTO `sys_role_menu` VALUES ('1', '9');
INSERT INTO `sys_role_menu` VALUES ('2', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '2');
INSERT INTO `sys_role_menu` VALUES ('2', '3');
INSERT INTO `sys_role_menu` VALUES ('2', '6');
INSERT INTO `sys_role_menu` VALUES ('3', '1');
INSERT INTO `sys_role_menu` VALUES ('3', '2');
INSERT INTO `sys_role_menu` VALUES ('3', '3');
INSERT INTO `sys_role_menu` VALUES ('3', '4');
INSERT INTO `sys_role_menu` VALUES ('3', '5');
INSERT INTO `sys_role_menu` VALUES ('3', '6');
INSERT INTO `sys_role_menu` VALUES ('3', '8');
INSERT INTO `sys_role_menu` VALUES ('3', '9');

-- ----------------------------
-- Table structure for sys_sms_content
-- ----------------------------
DROP TABLE IF EXISTS `sys_sms_content`;
CREATE TABLE `sys_sms_content` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `TITLE` varchar(255) DEFAULT NULL COMMENT '标题',
  `CONTENT` text COMMENT '内容',
  `STATUS` tinyint(4) DEFAULT NULL COMMENT '状态（0：未读，1：已读）',
  `USER_ID_STR` text COMMENT '查看人id',
  `SORT` int(11) DEFAULT '1' COMMENT '排序',
  `UPDATE_DATE` varchar(20) DEFAULT NULL COMMENT '更新日期',
  `UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `CREATE_DATE` varchar(20) DEFAULT NULL COMMENT '创建日期',
  `CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统消息表';

-- ----------------------------
-- Records of sys_sms_content
-- ----------------------------
INSERT INTO `sys_sms_content` VALUES ('059830d3fa27413981f9be796e23bd59', 'erwer', '<p>werewr </p>', '0', '2b0a1d6269a2482f9cfc5933b0400fdc', null, null, null, '2018-01-26 17:43:44', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('09a6913781734d1abe9c17cf6cbab662', 'asd', '<p>asd</p>', '0', '2b0a1d6269a2482f9cfc5933b0400fdc', null, null, null, '2018-01-09 09:40:06', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('156ff7f6addd4cc098d82b5d48c83e25', '0.2版本', '<p>0.2版本</p>', '0', '', null, '', null, '2017-12-28 17:05:27', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('1673b455249b460c934baff36e6395e6', '阿斯达', '<p>阿斯达</p>', '0', '2b0a1d6269a2482f9cfc5933b0400fdc', null, null, null, '2018-01-02 15:52:25', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('285110358d2a4824a916eafa94731e0a', '阿斯达请问', '<p>ad</p>', '0', '2b0a1d6269a2482f9cfc5933b0400fdc', null, null, null, '2018-01-02 12:53:06', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('2c8c72b753b4474ea834ebe32167feb0', '收到', '<p>时代的</p>', '0', '2b0a1d6269a2482f9cfc5933b0400fdc', null, null, null, '2018-01-26 15:07:13', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('36e3f4ab3dc44c129c5ca2d78495cd3d', '收到', '<p>大多数</p>', '0', null, null, null, null, '2018-01-26 15:03:20', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('3fd36bbae9cd4dbe8e46d34dd2f5ab89', '7.0版本即将闪亮登场', '<h1 class=\"ql-align-center\">老铁们</h1><p><br></p><p>7.0版本即将闪亮登场</p>', '0', '2b0a1d6269a2482f9cfc5933b0400fdc', null, null, null, '2018-01-03 10:56:17', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('401ec160358f4fc0969b0dfa7ff6f66d', '爱上大', '<p>爱上大</p>', '0', '2b0a1d6269a2482f9cfc5933b0400fdc', null, null, null, '2017-12-29 18:48:16', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('42d3125bd3384e848660e8d0d36eb960', '阿德防守打法', '<p>是的范德萨范德萨的</p>', '0', '2b0a1d6269a2482f9cfc5933b0400fdc', null, null, null, '2018-01-02 12:53:26', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('4d9d555892194a4c98a675b06100cb92', '阿斯顿', '<p>是的冯绍峰</p>', '0', '2b0a1d6269a2482f9cfc5933b0400fdc', null, null, null, '2018-01-02 12:53:17', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('5a78bd13c8dc41dca1045d437e651e5c', '是多少', '<p>的说法都是</p>', '0', '2b0a1d6269a2482f9cfc5933b0400fdc', null, null, null, '2018-01-29 10:39:49', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('5d097195b7e041709d9c5fd523d2b0ab', '阿斯达', '<p><br></p><p><img src=\"data:image/png;base64,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\"></p>', '0', '', null, '', null, '2017-12-28 17:22:54', '');
INSERT INTO `sys_sms_content` VALUES ('5f08de8ba2124e6c8d05b6a7f9293f73', 'sdf', '<p>sdf</p>', '0', '2b0a1d6269a2482f9cfc5933b0400fdc,438b2323fb92414fa986f3150c1aae4f', null, null, null, '2018-01-24 11:00:18', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('691ad98bda354213aa69ec72f3129564', '版本将更新到5.0版本', '<p>	大家好。系统将于2018年1月2日晚12点进行版本更新，届时您将不能登录系统，请见谅</p>', '0', '2b0a1d6269a2482f9cfc5933b0400fdc', null, null, null, '2018-01-02 15:43:58', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('6fa2b831f2324e55ad2f9f3f02c484c6', 'asd', '<p>asd</p>', '0', '2b0a1d6269a2482f9cfc5933b0400fdc', null, null, null, '2018-01-05 23:07:41', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('755ec2a5c6be4224992447a377683c71', '阿斯达多', '<p>阿斯达</p><p><br></p><p><img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAaAAAAAdCAYAAADsFbhGAAAKU0lEQVR4Ae2dWajNXxTHN/eUqYhCpmTKPBQPpiSFF8qDDHlQHsgDRSg36SalEMWL4YUklAcSUZJICg9kiExFyBCZwtP999n/vse+P7/fme7v3HPuuWvVOec37L32Wt+19lp7r9/v0u779++NzsgQMAQMAUPAEGhhBNq38Hg2nCFgCBgChoAh4BGwBGSOYAgYAoaAIVARBCwBVQR2G9QQMAQMAUPAEpD5gCFgCBgChkBFELAEVBHYbVBDwBAwBAwBS0DmA4aAIWAIGAIVQcASUEVgt0ENAUPAEDAEypqAHj586FatWpX9HD9+vCTExWf//v3uz58/JfGotU5gsn79evf69et/VOPa1q1bY+/907iVXwAHdP327Vsqmly5csXxgfBXHTeHObLt3LmzquzBPGI+aX6moWc+jPBLcEjLVvnGy3U/Db8ph6/kkjnpnuKjbNma4mRdfX19Q5Jizb3+8eNH16FDB1dfX+/mz5/v7t+/7z59+uQGDRpUMGsmyvnz592KFSvc7NmzXSaTKbhvLTcE27dv37qOHTv+g+fly5fdjx8/3NixY123bt1qGQZ3/fp172O/fv36B4dSFH/58qXvho+OGzeuZJ4kr7q6OterVy8v37Rp06rKFqdOnXKDBw92a9eu9XPz3bt3XtZyzi98ERyICczrI0eOuJ49e1YElzT8JslXQtuX4oPF9onG2WfPnrkvX76U7Ltx45fLXmXdAUUVmTBhgsPRjdJBoHPnzo7VT7ii5Pj379+uf//+6QxSxVyk68yZM92LFy9sd1yErUjYw4YNy/aYOnWqTwzZCzV8UOt+05ribIZt8d69e928efPcrFmzWsztyKiHDh1yDx488GOyEhs9erQveZC9792754YOHeq+fv3q29y+fdstXrzYy8gK49q1a77fmDFj3MqVK/3kYUusvsOHD3czZsxwly5dcn379nVnz551tF2yZIkvPbx//97fX7ZsmQ9coSz04zrEWKyGL1686MI+3BN2P3/+dL1793YbNmxwXbt29X0kn2T2zFL+6tGjh+eIHGAH3blzx3Xv3t3vjjRcEtb0KwQf+IS6ci57MZmPHj3qMb5x44abPn26I7gJP/qdOHHCbdq0SeKk9gvvPn36uAEDBnieHz58yB5H7Rb1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\"></p>', '0', '2b0a1d6269a2482f9cfc5933b0400fdc', null, null, null, '2018-01-12 14:37:06', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('75881bb8da1c4bcbbe164b417b2551bc', '是多少', '<p>时代的</p>', '0', null, null, null, null, '2018-01-26 15:03:43', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('843727a6e9b64c5a8c70192a256a4cb9', '系统更新至0.1版本', '<p>系统更新至0.1版本</p>', '0', '', null, '', null, '2017-12-28 17:05:10', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('84b19a1dd1e84fa0a372b7591f95d048', '额外热无若', '<p>是否打算打算</p>', '0', '2b0a1d6269a2482f9cfc5933b0400fdc', null, null, null, '2018-01-02 12:53:36', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('869edc71e95042c58f1a250c5b8ebb0f', 'sad', '<p>zei</p>', '0', '2b0a1d6269a2482f9cfc5933b0400fdc', null, null, null, '2018-01-24 10:31:19', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('9ccb12d79d8747bd9b6bf4db5d06e2a8', 'fjhfhgf', '<p>tgjgjkh</p>', '0', '2b0a1d6269a2482f9cfc5933b0400fdc', null, null, null, '2018-01-26 18:07:03', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('9dc83d6c3522418e8e4dbe8eaeb02935', '是的冯绍峰', '<p>史蒂夫的说法是</p>', '0', '', null, null, null, '2018-01-02 12:53:45', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('ba0682f5f75c4021bac6fa191fe6a674', '西安万威机械制造股份有限公司', '<p><span style=\"color: rgb(102, 102, 102);\">       伴随着西安市“优化营商环境，助推新经济发展”的春风，2018年2月8日，正是农历腊月二十三中国传统文化的“小年”，西安市政府领导一行两人代表西安市委市政府到西安万威机械制造股份有限公司进行亲切慰问并送来新春贺信。</span></p>', '0', '2b0a1d6269a2482f9cfc5933b0400fdc', null, null, null, '2018-03-27 15:57:03', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('bb3cc20c0ff14255b4524d37f072525d', '士大夫但是', '<p>士大夫大师傅</p>', '0', '2b0a1d6269a2482f9cfc5933b0400fdc', null, null, null, '2018-01-26 14:56:06', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('c02255b0fef44cb0affa01ceebf777c5', '8.0版本即将更新', '<p><br></p><p><img src=\"data:image/png;base64,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\"></p>', '0', '2b0a1d6269a2482f9cfc5933b0400fdc', null, null, null, '2018-01-05 15:41:02', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('d5444e2ad35243d7bbff7fe03e78eb07', '哦哦哦', '<p>好啊好啊</p>', '0', '', null, null, null, '2017-12-29 17:54:22', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('dd54cbba70674b70a0b73f1fbb8c4bad', '撒大苏打', '<p>是多少</p>', '0', null, null, null, null, '2018-01-26 15:02:23', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('de2aa040973c49ca855c8a31a5eedecb', '6.0版本', '<p>怕吗，6.0版本了</p>', '0', '2b0a1d6269a2482f9cfc5933b0400fdc', null, null, null, '2018-01-03 10:22:46', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('e804ab8eec7f4dae80a897eb4c308cb9', '阿斯达奥多', '<h2 class=\"ql-align-center\"><u>asdasdadasdasda</u></h2><p><br></p><p>	阿达第三方朝鲜战争吃正餐阿斯达大所多</p>', '0', '2b0a1d6269a2482f9cfc5933b0400fdc', null, null, null, '2018-01-12 14:38:29', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('f4ee12ab967b47b2a05157426cef8aa8', '11111', '<p>2222</p>', '0', '2b0a1d6269a2482f9cfc5933b0400fdc', null, null, null, '2018-01-15 18:41:58', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `sys_sms_content` VALUES ('f5380beb8b2d49948f32ac7df83d4e87', '合伙人', '<p>中国合伙人</p>', '0', null, null, null, null, '2017-12-29 17:50:40', '2b0a1d6269a2482f9cfc5933b0400fdc');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `login_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '登录名',
  `password` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `salt` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '加密用盐',
  `no` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '工号',
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别（1男，0女）',
  `org_ids` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '组织机构id集合',
  `company_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '公司id（预留）',
  `dept_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '部门id（预留）',
  `post_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '岗位id',
  `email` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '电子邮件',
  `telephone` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '办公电话',
  `mobile` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '移动电话',
  `fax` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '传真',
  `user_type` tinyint(4) DEFAULT NULL COMMENT '用户类型（预留）',
  `avatar_path` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '头像路径',
  `login_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `login_times` int(11) DEFAULT NULL COMMENT '登录次数',
  `login_flag` tinyint(4) DEFAULT NULL COMMENT '登录标识（1是，0否）',
  `enabled` tinyint(4) DEFAULT NULL COMMENT '启用状态（1是，0否）',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `delete_flag` tinyint(4) DEFAULT NULL COMMENT '删除标识（1是，0否）',
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `tenant_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `in_index` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('0f0ca899e7bd45e289452f0915fa6044', 'admin', 'dcd63b609a2e038e2712cfc30c296fb1', 'CSSIHZAQBH', 'p0000001', '文思海辉租户管理员', '1', null, null, null, null, null, null, '18688886666', null, null, null, null, null, null, null, '1', null, '4fc7ce16d79b46f69697e2041cc35dc6', '2018-03-31 11:54:43', null, null, '0', '租户管理员，不要删除', '4fc7ce16d79b46f69697e2041cc35dc6');
INSERT INTO `sys_user` VALUES ('4683c516300e4d29aa7edd0350879aec', 'admin', '7c94266b7345694c1f0d0218b40eeba4', 'DC3ECADLTM', 'p0000001', '海航集团租户管理员', '1', null, null, null, null, null, null, '18866668888', null, null, null, null, null, null, null, '1', null, '4fc7ce16d79b46f69697e2041cc35dc6', '2018-03-31 11:54:43', null, null, '0', '租户管理员，不要删除', 'f4f5c7310c28425a8cddfb1540bf8ec5');
INSERT INTO `sys_user` VALUES ('a0c5fce52e3a480a8e95d6720b6b80b1', 'like', '2a1885f672828de42e0375bb31a83ef9', 'ENX1YGHOUC', 'p0000003', '李轲', '1', '5', null, null, 'b099e8de16cc419793fb7d9f03a8f3cf', null, null, '18699999999', null, null, null, null, null, null, null, '1', null, '0f0ca899e7bd45e289452f0915fa6044', '2018-03-31 12:52:20', null, null, '0', null, '4fc7ce16d79b46f69697e2041cc35dc6');
INSERT INTO `sys_user` VALUES ('aa718e095be84489bcf4922a1084958d', 'mark', 'fb51e0692a9dc67de26cb96d5d9032a9', 'SNENWP9F2Z', 'p0000002', '马小可', '1', '4,3', null, null, '77e96a6abd2e4782a94445171f24bf9e', null, null, '18629292929', null, null, null, null, null, null, null, '1', null, '0f0ca899e7bd45e289452f0915fa6044', '2018-03-31 12:36:00', '0f0ca899e7bd45e289452f0915fa6044', '2018-03-31 12:48:58', '0', null, '4fc7ce16d79b46f69697e2041cc35dc6');

-- ----------------------------
-- Table structure for sys_user_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_org`;
CREATE TABLE `sys_user_org` (
  `user_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `org_id` varchar(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`user_id`,`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户与组织机构关联表';

-- ----------------------------
-- Records of sys_user_org
-- ----------------------------
INSERT INTO `sys_user_org` VALUES ('18c8814514394000bfac546ce2140d32', '1');
INSERT INTO `sys_user_org` VALUES ('18c8814514394000bfac546ce2140d32', '2');
INSERT INTO `sys_user_org` VALUES ('18c8814514394000bfac546ce2140d32', '4');
INSERT INTO `sys_user_org` VALUES ('a0c5fce52e3a480a8e95d6720b6b80b1', '5');
INSERT INTO `sys_user_org` VALUES ('aa718e095be84489bcf4922a1084958d', '3');
INSERT INTO `sys_user_org` VALUES ('aa718e095be84489bcf4922a1084958d', '4');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `role_id` varchar(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户-角色';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('0f0ca899e7bd45e289452f0915fa6044', '1');
INSERT INTO `sys_user_role` VALUES ('4683c516300e4d29aa7edd0350879aec', '3');
INSERT INTO `sys_user_role` VALUES ('aa718e095be84489bcf4922a1084958d', '2');

-- ----------------------------
-- Table structure for tams_detail_list
-- ----------------------------
DROP TABLE IF EXISTS `tams_detail_list`;
CREATE TABLE `tams_detail_list` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `TOOL_NO` varchar(50) DEFAULT NULL COMMENT '刀号',
  `TOOL_NAME` varchar(50) DEFAULT NULL COMMENT '刀具名称',
  `SPECIFICATION` varchar(32) DEFAULT NULL COMMENT '刀具规格',
  `TOOL_DESC` varchar(255) DEFAULT NULL COMMENT '刀号描述',
  `TOOL_MODEL` varchar(50) DEFAULT NULL COMMENT '刀具型号',
  `AMOUNT` int(11) DEFAULT NULL COMMENT '数量',
  `TOOL_CODE` varchar(50) DEFAULT NULL COMMENT '刀具代号',
  `RECOMMEND_BRAND` varchar(50) DEFAULT NULL COMMENT '推荐品牌',
  `PRICE` float(9,2) DEFAULT NULL COMMENT '单价',
  `SORT` int(11) DEFAULT '0' COMMENT '排序',
  `SCHEME_NO` varchar(32) DEFAULT NULL COMMENT '工序号',
  `TENANT_ID` varchar(32) DEFAULT NULL COMMENT '租户id',
  `UPDATE_DATE` varchar(20) DEFAULT NULL COMMENT '更新日期',
  `UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `CREATE_DATE` varchar(20) DEFAULT NULL COMMENT '创建日期',
  `CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='刀具清单表';

-- ----------------------------
-- Records of tams_detail_list
-- ----------------------------

-- ----------------------------
-- Table structure for tams_hot_search
-- ----------------------------
DROP TABLE IF EXISTS `tams_hot_search`;
CREATE TABLE `tams_hot_search` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `NAME` varchar(255) DEFAULT NULL COMMENT '搜索词名称',
  `SEARCH_TIMES` int(11) DEFAULT NULL COMMENT '搜索次数',
  `TENANT_ID` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `UPDATE_DATE` varchar(20) DEFAULT NULL COMMENT '更新日期',
  `UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `CREATE_DATE` varchar(20) DEFAULT NULL COMMENT '创建日期',
  `CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='热搜表';

-- ----------------------------
-- Records of tams_hot_search
-- ----------------------------

-- ----------------------------
-- Table structure for tams_machine_tool
-- ----------------------------
DROP TABLE IF EXISTS `tams_machine_tool`;
CREATE TABLE `tams_machine_tool` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `DEVICE_CODE` varchar(50) DEFAULT NULL COMMENT '设备编号',
  `MACHINE_NAME` varchar(50) DEFAULT '0' COMMENT '机床名称',
  `PIC_PATH` varchar(255) DEFAULT NULL COMMENT '图片路径',
  `BRAND` varchar(50) DEFAULT NULL COMMENT '品牌',
  `MODE` varchar(50) DEFAULT NULL COMMENT '型号',
  `ORIGIN_AREA` varchar(255) DEFAULT NULL COMMENT '原产地',
  `SUPPLIER_NAME` varchar(100) DEFAULT '0' COMMENT '机供货单位名称',
  `MACHING_PRECISION` varchar(50) DEFAULT '0' COMMENT '加工精度',
  `MACHINE_LABLE` varchar(255) DEFAULT NULL COMMENT '标签',
  `MACHINE_TYPE` tinyint(4) DEFAULT NULL,
  `MACHING_RANGE` varchar(255) DEFAULT NULL COMMENT '加工范围',
  `SORT` int(11) DEFAULT '0' COMMENT '排序',
  `TENANT_ID` varchar(32) DEFAULT NULL COMMENT '租户id',
  `UPDATE_DATE` varchar(20) DEFAULT NULL COMMENT '更新日期',
  `UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `CREATE_DATE` varchar(20) DEFAULT NULL COMMENT '创建日期',
  `CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机床表';

-- ----------------------------
-- Records of tams_machine_tool
-- ----------------------------
INSERT INTO `tams_machine_tool` VALUES ('219d0c76834044e3a2c0b7d0c310d191', 'WINWAY-A-2006-09', '数控卧式镗铣加工中心  (32刀位)', 'attachment\\2018\\03\\71eed59487d641e292115817bf8de9d4.jpg,', 'FEMCO', 'WVL-12', '美国', '昂科机床（上海）有限公司', '0.005mm', null, null, '少时诵诗书', null, '706829e0701b44baa072ee41d9636f7a', null, null, '2018-03-01 11:03:04', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_machine_tool` VALUES ('ee28a4b9237c49be952d9a4fc5bc9a21', 'ccc', 'cc', null, null, null, 'cc', 'cc', 'cc', null, null, 'cc', null, null, null, null, '2018-04-17 14:01:02', '0f0ca899e7bd45e289452f0915fa6044');

-- ----------------------------
-- Table structure for tams_material
-- ----------------------------
DROP TABLE IF EXISTS `tams_material`;
CREATE TABLE `tams_material` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `MATERIAL_QUALITY_CODE` varchar(50) DEFAULT NULL COMMENT '材质编号',
  `MATERIAL_QUALITY_NAME` varchar(50) DEFAULT NULL COMMENT '材质名称',
  `MATERIAL_QUALITY_SPEC` varchar(50) DEFAULT NULL COMMENT '材质规则',
  `BRAND` varchar(50) DEFAULT NULL COMMENT '品牌',
  `SUPPLIER` varchar(255) DEFAULT NULL COMMENT '供货单位',
  `STATUS` tinyint(4) DEFAULT '0' COMMENT '状态(0：未认证，1：认证)',
  `SORT` int(11) DEFAULT '0' COMMENT '排序',
  `TENANT_ID` varchar(32) DEFAULT NULL COMMENT '租户id',
  `UPDATE_DATE` varchar(20) DEFAULT NULL COMMENT '更新日期',
  `UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `CREATE_DATE` varchar(20) DEFAULT NULL COMMENT '创建日期',
  `CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='材质表';

-- ----------------------------
-- Records of tams_material
-- ----------------------------
INSERT INTO `tams_material` VALUES ('efdba4a5d07a4ee5b3aa164928feaa10', 'ad1', 'asd', 'asd', 'asd', 'asd', '1', null, null, '2018-03-15 18:30:30', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-03-15 14:10:51', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_material` VALUES ('efdba4a5d07a4ee5b3aa164928feaa11', 'ad2', 'asd', 'asd', 'asd', 'asd', '1', null, null, '2018-03-15 17:47:38', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-03-15 14:10:51', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_material` VALUES ('efdba4a5d07a4ee5b3aa164928feaa12', 'ad3', 'asd', 'asd', 'asd', 'asd', '0', null, null, '2018-03-15 17:28:06', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-03-15 14:10:51', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_material` VALUES ('efdba4a5d07a4ee5b3aa164928feaa13', 'ad4', 'asd', 'asd', 'asd', 'asd', '1', null, null, '2018-03-15 18:19:52', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-03-15 14:10:51', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_material` VALUES ('efdba4a5d07a4ee5b3aa164928feaa14', 'ad5', 'asd', 'asd', 'asd', 'asd', '1', null, null, '2018-03-15 18:18:25', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-03-15 14:10:51', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_material` VALUES ('efdba4a5d07a4ee5b3aa164928feaa15', 'ad6', 'asd', 'asd', 'asd', 'asd', '1', null, null, '2018-03-15 17:28:06', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-03-15 14:10:51', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_material` VALUES ('efdba4a5d07a4ee5b3aa164928feaa16', 'ad7', 'asd', 'asd', 'asd', 'asd', '1', null, null, '2018-03-15 17:28:06', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-03-15 14:10:51', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_material` VALUES ('efdba4a5d07a4ee5b3aa164928feaa17', 'ad8', 'asd', 'asd', 'asd', 'asd', '1', null, null, '2018-03-15 17:28:06', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-03-15 14:10:51', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_material` VALUES ('efdba4a5d07a4ee5b3aa164928feaa18', 'ad9', 'asd', 'asd', 'asd', 'asd', '1', null, null, '2018-03-15 17:28:06', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-03-15 14:10:51', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_material` VALUES ('efdba4a5d07a4ee5b3aa164928feaa19', 'ad1234', 'asd', 'asd', 'asd', 'asd', '1', null, null, '2018-03-15 17:28:06', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-03-15 14:10:51', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_material` VALUES ('efdba4a5d07a4ee5b3aa164928feaa20', 'ad12313123', 'asd', 'asd', 'asd', 'asd', '1', null, null, '2018-03-15 17:28:06', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-03-15 14:10:51', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_material` VALUES ('efdba4a5d07a4ee5b3aa164928feaab1', 'ad12312313131', 'asd', 'asd', 'asd', 'asd', '0', null, null, '2018-03-15 17:28:06', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-03-15 14:10:51', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_material` VALUES ('efdba4a5d07a4ee5b3aa164928feaab2', 'ad13wrw', 'asd', 'asd', 'asd', 'asd', '0', null, null, '2018-03-15 17:28:06', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-03-15 14:10:51', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_material` VALUES ('efdba4a5d07a4ee5b3aa164928feaab3', 'adsfsf', 'asd', 'asd', 'asd', 'asd', '1', null, null, '2018-03-15 17:28:06', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-03-15 14:10:51', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_material` VALUES ('efdba4a5d07a4ee5b3aa164928feaab4', 'adwqeqej', 'asd', 'asd', 'asd', 'asd', '1', null, null, '2018-03-15 17:28:06', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-03-15 14:10:51', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_material` VALUES ('efdba4a5d07a4ee5b3aa164928feaab5', 'adqweqeh', 'asd', 'asd', 'asd', 'asd', '1', null, null, '2018-03-15 17:28:06', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-03-15 14:10:51', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_material` VALUES ('efdba4a5d07a4ee5b3aa164928feaab6', 'adqweqeqweq', 'asd', 'asd', 'asd', 'asd', '1', null, null, '2018-03-15 18:19:45', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-03-15 14:10:51', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_material` VALUES ('efdba4a5d07a4ee5b3aa164928feaab7', 'adqweqeqe', 'asd', 'asd', 'asd', 'asd', '1', null, null, '2018-03-15 17:28:06', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-03-15 14:10:51', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_material` VALUES ('efdba4a5d07a4ee5b3aa164928feaab8', 'adyu', 'asd', 'asd', 'asd', 'asd', '1', null, null, '2018-03-15 18:19:04', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-03-15 14:10:51', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_material` VALUES ('efdba4a5d07a4ee5b3aa164928feaab9', 'adli', 'asd', 'asd', 'asd', 'asd', '1', null, null, '2018-03-15 17:28:06', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-03-15 14:10:51', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_material` VALUES ('f87bb63805784a49aaba202ec933a5b8', '阿斯达', 'ada', 'ad', 'ad', 'ad', '1', null, null, '2018-03-15 17:28:06', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-03-15 15:26:27', '2b0a1d6269a2482f9cfc5933b0400fdc');

-- ----------------------------
-- Table structure for tams_product
-- ----------------------------
DROP TABLE IF EXISTS `tams_product`;
CREATE TABLE `tams_product` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `PRODUCT_NAME` varchar(50) DEFAULT NULL COMMENT '产品名称',
  `SPECIFICATION` varchar(50) DEFAULT NULL COMMENT '规格',
  `SPECIFICATION_NAME` varchar(100) DEFAULT NULL COMMENT '规格名称',
  `MATERIAL_QUALITY` varchar(50) DEFAULT NULL COMMENT '材质',
  `MATERIAL_QUALITY_SPEC` varchar(50) DEFAULT NULL COMMENT '材质规格',
  `TRADE_MARK` varchar(50) DEFAULT NULL COMMENT '牌号',
  `PRODUCT_PIC` varchar(255) DEFAULT NULL COMMENT '产品图片,多个图片以特殊分隔符分隔',
  `PATRS_PIC` varchar(255) DEFAULT NULL COMMENT '零件图片',
  `VIDEO_ID` varchar(255) DEFAULT NULL COMMENT '视频',
  `THREE_DIMENSIONAL` varchar(255) DEFAULT NULL COMMENT '三维图',
  `PRODUCT_LABEL` varchar(255) DEFAULT NULL COMMENT '标签',
  `CONTENT` varchar(255) DEFAULT NULL COMMENT '内容',
  `CATALOG_ID` varchar(32) DEFAULT NULL COMMENT '所属目录',
  `STATUS` tinyint(4) DEFAULT '0' COMMENT '状态(0：下架，1：上架)',
  `IS_RECOMMEND` tinyint(4) DEFAULT NULL COMMENT '是否推荐（0：未推荐，1：推荐）',
  `SORT` int(11) DEFAULT '0' COMMENT '排序',
  `TENANT_ID` varchar(32) DEFAULT '' COMMENT '租户id',
  `UPDATE_DATE` varchar(20) DEFAULT NULL COMMENT '更新日期',
  `UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `CREATE_DATE` varchar(20) DEFAULT NULL COMMENT '创建日期',
  `CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品表';

-- ----------------------------
-- Records of tams_product
-- ----------------------------
INSERT INTO `tams_product` VALUES ('00f05f925b8648af9b7111435ee99f54', '六六顺', '25*65*25', null, '铝合金', '30*65*65', 'NKHH-JJG2554*JVCFC*6500', null, null, null, null, '刀盘，六六顺', '2334552352', '3825d4c68698420481b86dc0b48586eb', '0', '0', '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-04-09 11:49:54', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-03-27 16:02:11', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product` VALUES ('078548f0a8a14871abf4271b79d00194', 'ee', 'ee', null, 'ee', 'ee', 'ee', null, null, null, null, 'ee', 'ee', 'ae13493bd8d04876a05e273f0e941829', '0', null, '0', null, null, null, '2018-04-20 18:09:21', '0f0ca899e7bd45e289452f0915fa6044');
INSERT INTO `tams_product` VALUES ('0a279689ce35481f8bbb7ffe71066642', '打发打发', '打发打发', null, null, null, null, '', null, null, null, '随风倒十分', '双方都是', '6fb1fbb99e4c475a832adf7272281a54', '0', null, '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-04-09 11:49:54', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-02-06 10:38:00', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product` VALUES ('2708cc4ef1b04c87911062683ca89306', 'aaaaa', 'aaa', null, null, null, null, 'attachment\\2018\\02\\53cab13d829c40748f1d5d5150221fd7.jpg,', null, null, null, 'aa', 'a', '7f28a68386af4219bc8916816002e4e0', '0', '1', '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-04-09 11:49:54', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-02-22 11:08:02', '56a733d88caa4ccd902ff054a08b8247');
INSERT INTO `tams_product` VALUES ('2adf0149c9f84008abefd3cc9947f94d', 'asd', 'asd', null, 'asd', 'asd', 'asd', null, null, null, null, 'sda', 'sad', '3825d4c68698420481b86dc0b48586eb', '0', null, '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-04-09 11:49:54', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-04-06 09:31:18', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product` VALUES ('31646449b21544a0a084b9569c1ed768', 'sad', 'asd', null, 'asd', 'ads', 'asd', null, null, null, null, '12', '21', 'ae13493bd8d04876a05e273f0e941829', '0', null, '0', '4fc7ce16d79b46f69697e2041cc35dc6', null, null, '2018-04-09 19:08:16', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product` VALUES ('42011a1bcd3f4ef289f8db0c5803a082', 'ww', 'ww', null, 'ww', 'ww', 'ww', null, null, null, null, '22', '22', 'ae13493bd8d04876a05e273f0e941829', '1', null, '0', null, '2018-04-20 14:44:17', '0f0ca899e7bd45e289452f0915fa6044', '2018-04-20 14:44:05', '0f0ca899e7bd45e289452f0915fa6044');
INSERT INTO `tams_product` VALUES ('44e5e92fe5a947cdba64ad01d2c0b4ee', '输入产品名称测试', '输入规格测试', null, '输入材料测试', '输入材质规格测试', 'WW00001', null, null, null, null, '测试组,产品测试标签', '产品简介输入测试', '47e5a2a2ca83414b9f1665e9b2b62fe9', '0', '1', '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-04-09 11:49:54', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-03-30 13:40:11', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product` VALUES ('4ad680895abe4dfc89011910da5be6ac', 'ssfsd', 'sdfsdfd', null, null, null, null, '', null, null, null, 'sdfsdf', 'dfdsf', '6fb1fbb99e4c475a832adf7272281a54', '0', null, '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-04-09 11:49:54', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-02-06 10:07:23', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product` VALUES ('541e83fbebfe45bf840c2c2929cfd519', '2222', '2222', null, null, null, null, 'attachment\\2018\\02\\a6086a1dc72b4b4dbc36e68bff5be335.jpg,', null, null, null, '2222', '2222', '3825d4c68698420481b86dc0b48586eb', '0', null, '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-04-09 11:49:54', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-02-07 11:23:41', '56a733d88caa4ccd902ff054a08b8247');
INSERT INTO `tams_product` VALUES ('59362dcc63a0476391f2bae75782351a', '333', '333', null, null, null, null, null, null, null, null, '33', '333', '3825d4c68698420481b86dc0b48586eb', '0', '1', '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-04-09 11:49:54', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-02-07 11:26:31', '56a733d88caa4ccd902ff054a08b8247');
INSERT INTO `tams_product` VALUES ('8face678ba21495e97ee2ead7636664a', 'dfgdfgdf', 'dfgdfgfd', null, null, null, null, null, null, null, null, 'dfgdfg', 'dfgdfgd', '6fb1fbb99e4c475a832adf7272281a54', '0', '0', '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-04-09 11:49:54', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-01-30 15:29:18', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product` VALUES ('8ff5b77b63474bcb96c166572b113a3b', 'dfdf', 'sdsdddsf', null, null, null, null, null, null, null, null, 'dsfdf', 'fdfdfdsf', '3825d4c68698420481b86dc0b48586eb', '0', null, '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-04-09 11:49:54', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-01-30 15:41:06', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product` VALUES ('a0b40affe7334262a8287c96e661c78d', '大齿轮', '111111', null, null, null, null, 'attachment\\2018\\01\\29875c28960f43139259b5d78f93e11e.jpg,', null, null, null, '123123', '123123123', '6fb1fbb99e4c475a832adf7272281a54', '0', '0', '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-04-09 11:49:54', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-01-11 14:51:00', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product` VALUES ('a0b40affe7334262a8287c96e662c78d', '5555552', '3333', null, null, null, null, 'attachment\\2018\\01\\c8f83e261f4548079f7f21faf1ed9081.jpg,', '', null, '', '123123', '123123123', '6fb1fbb99e4c475a832adf7272281a54', '0', '1', '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-04-09 11:49:54', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-01-12 11:24:58', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product` VALUES ('a0b40affe7334262a8287c96e663c78d', '5555553', '111111', null, null, null, null, 'attachment\\2018\\01\\14dbde14d38448c79e1ca88a4c2543cd.jpg,attachment\\2018\\01\\623cd4ff329b4261953fb357559fe51b.jpg,', null, null, null, '123123', '123123123', '6fb1fbb99e4c475a832adf7272281a54', '0', '1', '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-04-09 11:49:54', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-01-12 09:20:37', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product` VALUES ('a0b40affe7334262a8287c96e665c78d', '5555555', '111111', null, null, null, null, 'attachment\\2018\\01\\b1e36cade93e4252824355c91f641ebc.jpg', null, null, null, '123123', '123123123', '6fb1fbb99e4c475a832adf7272281a54', '0', '1', '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-04-09 11:49:54', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-01-10 13:55:40', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product` VALUES ('a0b40affe7334262a8287c96e666c78d', '5555556', '111111', null, null, null, null, 'attachment\\2018\\01\\b1e36cade93e4252824355c91f641ebc.jpg', null, null, null, '123123', '123123123', '6fb1fbb99e4c475a832adf7272281a54', '0', '1', '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-04-09 11:49:54', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-01-10 13:55:40', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product` VALUES ('a3297c57d70e4c6985e17403864448fb', '1111', '1111', null, null, null, null, 'attachment\\2018\\02\\f110ddb540b447d7b41b6cfc0c37d017.jpg,', null, null, null, '111', '111', '3825d4c68698420481b86dc0b48586eb', '0', null, '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-04-09 11:49:54', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-02-07 11:24:07', '56a733d88caa4ccd902ff054a08b8247');
INSERT INTO `tams_product` VALUES ('cea8f3f6666e4c41bd87c1971c85a3ab', '随风倒十分', '二二', null, null, null, null, null, null, null, null, '饿热望日 ', '好的齿轮', '6fb1fbb99e4c475a832adf7272281a54', '0', '0', '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-04-09 11:49:54', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-01-25 11:00:01', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product` VALUES ('cea8f3f6666e4c41bd87c1971c85a3ac', '测试测试', '散散', null, null, null, null, '', '', '', '', '饿热望日 ', '好的齿轮', '6fb1fbb99e4c475a832adf7272281a54', '0', '0', '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-04-09 11:49:54', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-01-25 11:00:01', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product` VALUES ('d54287d2d11847e8baa93de027ee0bcd', 'test产品2', '2*3*4', null, '铜合金', '1*3*4', 'WANWEI002', null, null, null, null, 'test', '测试新增产品2', '2da118ba5dd14185b132e9f5ecbf8411', '0', '0', '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-04-09 11:49:53', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-04-09 11:42:23', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product` VALUES ('fd695cb2e7674880beb777799a7d50d2', 'test商品1', '3*3*3', null, '锡', '3*1*1', 'Abcd1234测试', '', null, null, null, 'test', '测试新增商品1', 'ae13493bd8d04876a05e273f0e941829', '0', '0', '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-04-09 11:49:53', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-04-08 17:36:27', '2b0a1d6269a2482f9cfc5933b0400fdc');

-- ----------------------------
-- Table structure for tams_product_catalog
-- ----------------------------
DROP TABLE IF EXISTS `tams_product_catalog`;
CREATE TABLE `tams_product_catalog` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `PARENT_ID` varchar(32) DEFAULT NULL COMMENT '父节点 根节点为0',
  `CATALOG_NAME` varchar(50) DEFAULT NULL COMMENT '产品目录名称',
  `SORT` int(11) DEFAULT '0' COMMENT '排序',
  `TENANT_ID` varchar(32) DEFAULT NULL COMMENT '租户id',
  `UPDATE_DATE` varchar(20) DEFAULT NULL COMMENT '更新日期',
  `UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `CREATE_DATE` varchar(20) DEFAULT NULL COMMENT '创建日期',
  `CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品目录表';

-- ----------------------------
-- Records of tams_product_catalog
-- ----------------------------
INSERT INTO `tams_product_catalog` VALUES ('1ab58960bdcb4703ab0e23b94ca52473', '6fb1fbb99e4c475a832adf7272281a52', '起落架', '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-01-10 10:36:23', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-01-09 16:07:40', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_catalog` VALUES ('1e086b48f1184142acecf40acf67f660', '6fb1fbb99e4c475a832adf7272281a5a', 'test产品目录', '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-04-08 17:37:20', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-04-08 16:52:26', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_catalog` VALUES ('2da118ba5dd14185b132e9f5ecbf8411', '4a031a674d24459b8b054faa4529d72f', 'test子目录b1', '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-04-08 17:38:00', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-04-08 17:23:10', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_catalog` VALUES ('3825d4c68698420481b86dc0b48586eb', 'd04a3cb6370a4dd7a77d9b095b5aa319', 'aaaa', '0', '4fc7ce16d79b46f69697e2041cc35dc6', null, null, '2018-01-26 15:31:16', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_catalog` VALUES ('3f0e478dbe4a4d8fb13db5a485ca68cb', '1e086b48f1184142acecf40acf67f660', 'test子目录A', '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-04-08 17:37:30', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-04-08 17:35:37', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_catalog` VALUES ('47e5a2a2ca83414b9f1665e9b2b62fe9', '706c34a1354b4c96a2de06a305dbd801', '万威子目录 测试', '0', '4fc7ce16d79b46f69697e2041cc35dc6', null, null, '2018-03-30 13:37:16', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_catalog` VALUES ('4a031a674d24459b8b054faa4529d72f', '1e086b48f1184142acecf40acf67f660', 'test子目录B', '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-04-08 17:37:49', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-04-08 17:15:32', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_catalog` VALUES ('5ccadb321cd040098fa790d15d55c8c1', '6fb1fbb99e4c475a832adf7272281a5a', '汽车产品', '0', '4fc7ce16d79b46f69697e2041cc35dc6', null, null, '2018-01-09 20:10:55', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_catalog` VALUES ('6fb1fbb99e4c475a832adf7272281a52', '6fb1fbb99e4c475a832adf7272281a5a', '航空产品1', '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-01-09 14:51:04', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-01-02 17:13:45', '');
INSERT INTO `tams_product_catalog` VALUES ('6fb1fbb99e4c475a832adf7272281a54', '6fb1fbb99e4c475a832adf7272281a52', '齿轮', '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-01-02 17:19:11', '', '2018-01-02 17:13:45', '');
INSERT INTO `tams_product_catalog` VALUES ('6fb1fbb99e4c475a832adf7272281a5a', '0', '产品目录', '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-01-09 18:21:56', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-01-02 17:13:45', '');
INSERT INTO `tams_product_catalog` VALUES ('706c34a1354b4c96a2de06a305dbd801', '6fb1fbb99e4c475a832adf7272281a5a', '万威目录测试', '0', '4fc7ce16d79b46f69697e2041cc35dc6', null, null, '2018-03-30 13:37:04', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_catalog` VALUES ('77e8d91c790142f9aefed1cd186cff2d', '6fb1fbb99e4c475a832adf7272281a5a', '制造其他', '0', '4fc7ce16d79b46f69697e2041cc35dc6', null, null, '2018-03-28 09:34:06', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_catalog` VALUES ('7f28a68386af4219bc8916816002e4e0', '5ccadb321cd040098fa790d15d55c8c1', '法拉利', '0', '4fc7ce16d79b46f69697e2041cc35dc6', null, null, '2018-01-10 14:11:16', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_catalog` VALUES ('ae13493bd8d04876a05e273f0e941829', '3f0e478dbe4a4d8fb13db5a485ca68cb', 'test子目录a1', '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-04-08 17:37:41', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-04-08 17:36:05', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_catalog` VALUES ('d04a3cb6370a4dd7a77d9b095b5aa319', '6fb1fbb99e4c475a832adf7272281a5a', '清洁能源', '0', '4fc7ce16d79b46f69697e2041cc35dc6', null, null, '2018-01-10 14:10:56', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_catalog` VALUES ('eb3679e125fa4404bdf5b094f1c3b96f', '77e8d91c790142f9aefed1cd186cff2d', '万威', '0', '4fc7ce16d79b46f69697e2041cc35dc6', null, null, '2018-03-28 09:34:15', '2b0a1d6269a2482f9cfc5933b0400fdc');

-- ----------------------------
-- Table structure for tams_product_technics_scheme
-- ----------------------------
DROP TABLE IF EXISTS `tams_product_technics_scheme`;
CREATE TABLE `tams_product_technics_scheme` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `TECHNICS_NAME` varchar(50) DEFAULT NULL COMMENT '方案名称',
  `CODE` varchar(50) DEFAULT NULL COMMENT '工艺编号',
  `TYPE` tinyint(4) DEFAULT NULL COMMENT '类型（0：批量，1：离散）',
  `IS_EXCELLENT` tinyint(4) DEFAULT NULL COMMENT '是否最优（0：否，1：是）',
  `TECHNICS_PIC` varchar(255) DEFAULT NULL COMMENT '方案图片,多个图片以特殊分隔符分隔',
  `IS_RECOMMEND` tinyint(4) DEFAULT NULL COMMENT '是否推荐（0：未推荐，1：推荐）',
  `PRODUCT_ID` varchar(32) DEFAULT NULL COMMENT '产品ID',
  `SORT` int(11) DEFAULT '0' COMMENT '排序',
  `TENANT_ID` varchar(32) DEFAULT NULL COMMENT '租户id',
  `UPDATE_DATE` varchar(20) DEFAULT NULL COMMENT '更新日期',
  `UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `CREATE_DATE` varchar(20) DEFAULT NULL COMMENT '创建日期',
  `CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品工艺方案表';

-- ----------------------------
-- Records of tams_product_technics_scheme
-- ----------------------------
INSERT INTO `tams_product_technics_scheme` VALUES ('01a46df9eb654433a0f55f774623e613', '2', '2', '0', '1', 'attachment\\2018\\04\\b8a2231be53d47b2a491500c4cf1c819.jpg,attachment\\2018\\04\\dfe65b41c4004ffc89b50513c3e59698.jpg,', null, 'fd695cb2e7674880beb777799a7d50d2', '0', null, null, null, '2018-04-16 14:40:43', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_technics_scheme` VALUES ('4572c2738ce946dcbfc73829483aed88', '1', '1', '1', '0', 'attachment\\2018\\04\\79e7b96be7384fa5a5af62dca04a65b9.jpg,', null, 'fd695cb2e7674880beb777799a7d50d2', '0', null, null, null, '2018-04-16 14:40:26', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_technics_scheme` VALUES ('48d23426e724490e9d1dad0c60f92e34', '方案2测试', 'sad', '0', '0', 'attachment\\2018\\01\\87c9702f21f14df3a663055d4e2944d0.jpg,attachment\\2018\\02\\8446abacffe246d599ecadb23b509848.jpg,', null, 'a0b40affe7334262a8287c96e661c78d', '0', null, null, '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-03-30 14:00:27', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_technics_scheme` VALUES ('67154ed29ade4cb9a99818ba7f33be86', '工艺方案', 'asdads', '0', '1', 'attachment\\2018\\01\\49e150425efb470190bb0796b8e6ba88.jpg,', null, 'a0b40affe7334262a8287c96e661c78d', '0', null, null, '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-03-30 14:04:23', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_technics_scheme` VALUES ('6cebb0884e914151b5397cce1d8bd83a', 'asd', 'asd', '0', '1', 'attachment\\2018\\01\\21ebf19d02c64e8cac4bfa707591fc8f.jpg,attachment\\2018\\01\\c858ea10c09440b994903bf77e6aa36e.jpg,', null, 'a0b40affe7334262a8287c96e661c78d', '0', null, null, null, '2018-01-25 17:01:54', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_technics_scheme` VALUES ('aa821644414647cbb7868fe623663c25', '地方的规范规定', '的地方的', '0', '1', 'attachment\\2018\\01\\b9fc5c97f8df4c41b469070af146528e.jpg,', null, 'a0b40affe7334262a8287c96e661c78d', '0', null, null, '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-02-06 10:18:54', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_technics_scheme` VALUES ('b2674fb4f73047f7af96855cb03b4e99', '方案一', '123456', '0', '1', 'attachment\\2018\\01\\5ab99f38d91b4e7289a0fee3c73ad5c3.jpg,attachment\\2018\\01\\f0d989969a414916b5cb5b591de72648.jpg,', null, 'a0b40affe7334262a8287c96e661c78d', '0', null, null, '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-01-23 15:34:02', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_technics_scheme` VALUES ('de616fd5aead4dca90306d01bf74972f', '方案3', '12345', '1', '1', null, null, 'a0b40affe7334262a8287c96e661c78d', '0', null, null, null, '2018-01-24 15:45:38', '2b0a1d6269a2482f9cfc5933b0400fdc');

-- ----------------------------
-- Table structure for tams_product_technics_scheme_process
-- ----------------------------
DROP TABLE IF EXISTS `tams_product_technics_scheme_process`;
CREATE TABLE `tams_product_technics_scheme_process` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `SCHEME_PIC` varchar(255) DEFAULT NULL COMMENT '工序图片多个图片以特殊分隔符分隔',
  `MACHINE_SITUATION` varchar(50) DEFAULT NULL COMMENT '机床形式',
  `DEVICE_SPECS` varchar(50) DEFAULT NULL COMMENT '设备规格',
  `DEVICE_NAME` varchar(50) DEFAULT NULL COMMENT '设备名称',
  `PIC_NO` varchar(50) DEFAULT NULL COMMENT '图号',
  `MATERIAL_SPECS` varchar(50) DEFAULT NULL COMMENT '材质规格',
  `DAY_TIME` varchar(10) DEFAULT NULL COMMENT '每天工作时间',
  `MONTH_TIME` varchar(10) DEFAULT NULL COMMENT '月工作日',
  `YEAR_TIME` varchar(10) DEFAULT NULL COMMENT '年工作日',
  `EQUIPMENT_RATE` varchar(10) DEFAULT NULL COMMENT '设备开动率',
  `RHYTHM` varchar(10) DEFAULT NULL COMMENT '节拍',
  `OUTPUT` varchar(10) DEFAULT NULL COMMENT '生产纲领（年产量）',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '备注',
  `SORT` int(11) DEFAULT '0' COMMENT '排序',
  `TECHNICS_ID` varchar(32) DEFAULT NULL COMMENT '方案ID',
  `TENANT_ID` varchar(32) DEFAULT NULL COMMENT '租户id',
  `UPDATE_DATE` varchar(20) DEFAULT NULL COMMENT '更新日期',
  `UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `CREATE_DATE` varchar(20) DEFAULT NULL COMMENT '创建日期',
  `CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品方案工序表';

-- ----------------------------
-- Records of tams_product_technics_scheme_process
-- ----------------------------
INSERT INTO `tams_product_technics_scheme_process` VALUES ('1eee4e195cc84b0a889270ae9af44e07', 'attachment\\2018\\01\\fb196b2429524368b9d24240e5bd821c.jpg,', '11!!!1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '48d23426e724490e9d1dad0c60f92e34', null, null, '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-01-26 16:55:45', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_technics_scheme_process` VALUES ('2e2987e9cad2427d87edc0c5da2ce67e', null, null, '1', null, 'th001', '1', '1', '1', '1', '1', '1', '1', '1', '1', '48d23426e724490e9d1dad0c60f92e34', null, null, null, '2018-03-30 14:03:35', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_technics_scheme_process` VALUES ('44c1bffb15ba4928ae9065b612fa9640', 'attachment\\2018\\01\\210259a218bf44e9afefb5e5ac6dd540.jpg,', '3', null, null, '3', null, '3', '3', '3', null, '3', '3', '3', '0', 'f3b94e1168e144f59cc833b16cb64cec', null, null, null, '2018-01-23 11:56:54', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_technics_scheme_process` VALUES ('4d4973fb1a444473a16cd16673c9d29e', null, null, '1', null, '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', 'de616fd5aead4dca90306d01bf74972f', null, null, null, '2018-03-30 14:14:42', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_technics_scheme_process` VALUES ('6763efc6b1014e9da1fd523e33f70248', null, '33333', '41', '367676', '3', '35', '4', '4', '4', '4', '4', '4', '4', '0', '48d23426e724490e9d1dad0c60f92e34', null, null, '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-01-26 16:57:05', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_technics_scheme_process` VALUES ('836161476bd84aed82a16e2cf7701d08', null, null, '1', '1', '1', '1', null, null, null, null, null, null, '1', '1', '01a46df9eb654433a0f55f774623e613', '706829e0701b44baa072ee41d9636f7a', null, null, '2018-04-16 17:40:31', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_technics_scheme_process` VALUES ('c3a4735df50e40b39d0d815846806a13', 'attachment\\2018\\01\\1c7de6356715453592cacb72cfdb61c5.jpg,', '3', '1', '1', '3', '1', '3', '3', '3', '4', '3', '3', '3', '0', '48d23426e724490e9d1dad0c60f92e34', null, null, '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-01-26 16:59:32', '2b0a1d6269a2482f9cfc5933b0400fdc');

-- ----------------------------
-- Table structure for tams_product_technics_scheme_work_step
-- ----------------------------
DROP TABLE IF EXISTS `tams_product_technics_scheme_work_step`;
CREATE TABLE `tams_product_technics_scheme_work_step` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `TYPE` tinyint(4) DEFAULT NULL COMMENT '类型(0:工序，1:工步)',
  `STEP_NO` varchar(50) DEFAULT NULL COMMENT '工步号（0：工序号，1：工步号）',
  `TOOL_NAME` varchar(150) DEFAULT NULL COMMENT '名称',
  `CONTENT` varchar(150) DEFAULT NULL COMMENT '内容',
  `STEP_REQUIRE` varchar(150) DEFAULT NULL COMMENT '要求',
  `TOOL_NO` varchar(50) DEFAULT NULL COMMENT '刀号',
  `TOOL_DESC` varchar(150) DEFAULT NULL COMMENT '刀具描述',
  `TOOL_TOOTH` varchar(50) DEFAULT NULL COMMENT '刀齿',
  `DIAMETER` varchar(50) DEFAULT NULL COMMENT '直径',
  `LENGHT` varchar(50) DEFAULT NULL COMMENT '单长',
  `AMOUNT` int(11) DEFAULT NULL COMMENT '数量',
  `PIECE_NUMBER` int(11) DEFAULT NULL COMMENT '工件数',
  `TOTAL` int(11) DEFAULT NULL COMMENT '总长',
  `S` varchar(50) DEFAULT NULL COMMENT 'S',
  `F` varchar(50) DEFAULT NULL COMMENT 'F',
  `AP` varchar(50) DEFAULT NULL COMMENT 'AP',
  `AE` varchar(50) DEFAULT NULL COMMENT 'AE',
  `FN` varchar(50) DEFAULT NULL COMMENT 'FN',
  `VC` varchar(50) DEFAULT NULL COMMENT 'VC',
  `FZ` varchar(50) DEFAULT NULL COMMENT 'FZ',
  `CUT_DATE` varchar(20) DEFAULT NULL COMMENT '切削时间',
  `XYZ_DATE` varchar(20) DEFAULT NULL COMMENT 'XYZ时间',
  `A_DATE` varchar(20) DEFAULT NULL COMMENT 'A旋转时间',
  `CHANGE_TOOL_DATE` varchar(20) DEFAULT NULL COMMENT '换刀时间',
  `OTHER_DATE` varchar(20) DEFAULT NULL COMMENT '其他时间',
  `WORKSTEP_DATE` varchar(20) DEFAULT NULL COMMENT '工步时间',
  `VIDEO_ID` varchar(32) DEFAULT NULL COMMENT '视频',
  `SORT` int(11) DEFAULT '0' COMMENT '排序',
  `SCHEME_ID` varchar(32) DEFAULT NULL COMMENT '工序ID',
  `TENANT_ID` varchar(32) DEFAULT NULL COMMENT '租户id',
  `UPDATE_DATE` varchar(20) DEFAULT NULL COMMENT '更新日期',
  `UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `CREATE_DATE` varchar(20) DEFAULT NULL COMMENT '创建日期',
  `CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工序工步表';

-- ----------------------------
-- Records of tams_product_technics_scheme_work_step
-- ----------------------------
INSERT INTO `tams_product_technics_scheme_work_step` VALUES ('725f70017e844b4a8e705d60b1f44b71', '0', '3', null, '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', null, '0', '1eee4e195cc84b0a889270ae9af44e07', null, null, null, null, '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_technics_scheme_work_step` VALUES ('903db80f206d47e3a3facc89b66e2db3', '0', '3', '测试离散工艺工序', '3', '3', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '4d4973fb1a444473a16cd16673c9d29e', null, null, null, null, '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_technics_scheme_work_step` VALUES ('a1a67e1e17d843b5af8053739dbc5c99', '0', 'aAaAa', null, 'a', 'a', 'a', 'a', 'a', 'a', 'a', '11', '11', '111', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', null, '0', 'c3a4735df50e40b39d0d815846806a13', null, null, null, null, '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_technics_scheme_work_step` VALUES ('ca3d1009228a4646b59c9f894fa262df', '0', '55', null, '55', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', null, '0', '6763efc6b1014e9da1fd523e33f70248', null, null, null, null, '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_technics_scheme_work_step` VALUES ('e77d80532de8433281d68a4d9b69e90d', '0', '1', '1', '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '836161476bd84aed82a16e2cf7701d08', '706829e0701b44baa072ee41d9636f7a', null, null, null, '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_technics_scheme_work_step` VALUES ('efe5ebecba9e44b3a3542795f6c2e37c', '0', '3', '铣', '粗铣外表面', '粗铣四方', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '2e2987e9cad2427d87edc0c5da2ce67e', null, null, null, null, '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_product_technics_scheme_work_step` VALUES ('f798cc8fbef84f42acc0bc6cc7eda387', '1', '5', null, '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', null, '0', '1eee4e195cc84b0a889270ae9af44e07', null, null, null, null, '2b0a1d6269a2482f9cfc5933b0400fdc');

-- ----------------------------
-- Table structure for tams_rhythm_collect
-- ----------------------------
DROP TABLE IF EXISTS `tams_rhythm_collect`;
CREATE TABLE `tams_rhythm_collect` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `PARTS_NAME` varchar(50) DEFAULT NULL COMMENT '零件名称',
  `SCHEME_NO` varchar(32) DEFAULT NULL COMMENT '工序号',
  `DEVICE_DATE` varchar(20) DEFAULT NULL COMMENT '设备时间',
  `FEED_DATE` varchar(20) DEFAULT NULL COMMENT '上料下料时间',
  `RHYTHM` varchar(100) DEFAULT NULL COMMENT '节拍',
  `ANNUAL_OUTPUT` float(9,2) DEFAULT NULL COMMENT '年产量',
  `PRODUCTION_PROGRAM` varchar(100) DEFAULT NULL COMMENT '生产纲领',
  `MACHINE_AMOUNT` int(11) DEFAULT NULL COMMENT '机床数量',
  `DEVICE_MODE` varchar(50) DEFAULT NULL COMMENT '设备型号',
  `SORT` int(11) DEFAULT '0' COMMENT '排序',
  `TENANT_ID` varchar(32) DEFAULT NULL COMMENT '租户id',
  `UPDATE_DATE` varchar(20) DEFAULT NULL COMMENT '更新日期',
  `UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `CREATE_DATE` varchar(20) DEFAULT NULL COMMENT '创建日期',
  `CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='节拍汇总表';

-- ----------------------------
-- Records of tams_rhythm_collect
-- ----------------------------
INSERT INTO `tams_rhythm_collect` VALUES ('5037ede903db49458c230390b90f8e54', 'l零件名称', '123', '123', '123', '123', '123.00', '123', '123', '13', null, '706829e0701b44baa072ee41d9636f7a', null, null, '2018-03-14 13:58:59', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_rhythm_collect` VALUES ('5cde8f1f5bf7494397de85a51eaf36be', 'qwe', 'c3a4735df50e40b39d0d815846806a13', null, 'q', null, '12.00', '12', '12', null, null, '706829e0701b44baa072ee41d9636f7a', null, null, '2018-03-14 18:15:08', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_rhythm_collect` VALUES ('c87ea6c09f714b6f87a351f29e68e89d', 'sad', 'asd', null, 'asd', '1', '23.00', '11', '1', 'asdad', null, null, null, null, '2018-03-14 11:21:06', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_rhythm_collect` VALUES ('cd9e195601c042c78aeb280826cab74c', '360补休缸体', '6763efc6b1014e9da1fd523e33f70248', '12', '1', '34', '34.00', '45', '56', '34', null, '706829e0701b44baa072ee41d9636f7a', null, null, '2018-03-14 18:33:09', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_rhythm_collect` VALUES ('e931841b4987451587455567fc710b69', 'qw', 'wq', 'qw', '12', '12', '12.00', '12', '12', '12', null, null, null, null, '2018-03-14 13:55:35', '2b0a1d6269a2482f9cfc5933b0400fdc');

-- ----------------------------
-- Table structure for tams_specification
-- ----------------------------
DROP TABLE IF EXISTS `tams_specification`;
CREATE TABLE `tams_specification` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `SPECIFICATION` varchar(32) DEFAULT NULL COMMENT '规格',
  `SPEC_NAME` varchar(50) DEFAULT NULL COMMENT '名称',
  `PIC_PATH` varchar(255) DEFAULT NULL COMMENT '图片路径',
  `STATUS` int(11) DEFAULT NULL COMMENT '状态(0:未启用，1:启用)',
  `TYPE` int(11) DEFAULT NULL COMMENT '类型(1:产品，2：刀具)',
  `SORT` int(11) DEFAULT '0' COMMENT '排序',
  `TENANT_ID` varchar(32) DEFAULT NULL COMMENT '租户id',
  `UPDATE_DATE` varchar(20) DEFAULT NULL COMMENT '更新日期',
  `UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `CREATE_DATE` varchar(20) DEFAULT NULL COMMENT '创建日期',
  `CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规格表 ';

-- ----------------------------
-- Records of tams_specification
-- ----------------------------
INSERT INTO `tams_specification` VALUES ('01171b5b39ab400fa3a7e21cb5dbd782', '111', '222', null, null, null, null, '706829e0701b44baa072ee41d9636f7a', null, null, '2018-02-27 16:01:14', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_specification` VALUES ('07efccc9817944788878ac84c41968b4', '66', '66', null, null, null, null, '706829e0701b44baa072ee41d9636f7a', null, null, '2018-02-07 15:03:12', '56a733d88caa4ccd902ff054a08b8247');
INSERT INTO `tams_specification` VALUES ('3074ff8cb98d4eee88607ec850cfcd81', '77', '77', null, null, null, null, '706829e0701b44baa072ee41d9636f7a', null, null, '2018-02-07 15:03:16', '56a733d88caa4ccd902ff054a08b8247');
INSERT INTO `tams_specification` VALUES ('336a52266226441787539bfdfddcab4f', '007', '007', 'attachment\\2018\\02\\f89c2572c209439d813fdc7382cc8814.jpg,', null, null, null, '706829e0701b44baa072ee41d9636f7a', '2018-02-07 16:57:36', '56a733d88caa4ccd902ff054a08b8247', '2018-02-07 15:16:24', '56a733d88caa4ccd902ff054a08b8247');
INSERT INTO `tams_specification` VALUES ('48357d4df08a44e7a857bd4e864adeb3', '新增规格', '新增规格名称', '', null, null, null, '706829e0701b44baa072ee41d9636f7a', '2018-04-10 14:58:33', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-03-30 14:29:28', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_specification` VALUES ('69f160badb6b43da9ba0fbef5d002074', '111', '111', null, null, null, null, '706829e0701b44baa072ee41d9636f7a', null, null, '2018-02-07 15:02:49', '56a733d88caa4ccd902ff054a08b8247');
INSERT INTO `tams_specification` VALUES ('752b6531a68148148b902de38107e014', '10086', '10086', 'attachment\\2018\\02\\61da7e7331ba4bbd9aa281d95d33c8ec.jpg,', null, null, null, '706829e0701b44baa072ee41d9636f7a', null, null, '2018-02-07 16:58:00', '56a733d88caa4ccd902ff054a08b8247');
INSERT INTO `tams_specification` VALUES ('764cf41ec4aa4e2ebc9b86beb0042875', 'aaa', 'bbb', null, null, null, null, '706829e0701b44baa072ee41d9636f7a', null, null, '2018-02-27 16:01:08', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_specification` VALUES ('7ab7c754a7e94550bbb5890123299b5c', '88', '88', null, null, null, null, '706829e0701b44baa072ee41d9636f7a', null, null, '2018-02-07 15:03:21', '56a733d88caa4ccd902ff054a08b8247');
INSERT INTO `tams_specification` VALUES ('7f870f3412604e28bc2b8c551e7e5ec4', '44', '44', null, null, null, null, '706829e0701b44baa072ee41d9636f7a', null, null, '2018-02-07 15:03:01', '56a733d88caa4ccd902ff054a08b8247');
INSERT INTO `tams_specification` VALUES ('83d2ed455d9247869751a2563de4b810', '22', '22', null, null, null, null, '706829e0701b44baa072ee41d9636f7a', null, null, '2018-02-07 15:02:53', '56a733d88caa4ccd902ff054a08b8247');
INSERT INTO `tams_specification` VALUES ('90b1b92e23f048c08062c1923a9099a6', '33', '33', null, null, null, null, '706829e0701b44baa072ee41d9636f7a', null, null, '2018-02-07 15:02:57', '56a733d88caa4ccd902ff054a08b8247');
INSERT INTO `tams_specification` VALUES ('b5840e4faf694b3cae029e03c8abb61b', '99', '99', null, null, null, null, '706829e0701b44baa072ee41d9636f7a', null, null, '2018-02-07 15:03:30', '56a733d88caa4ccd902ff054a08b8247');
INSERT INTO `tams_specification` VALUES ('bb4a7140425e45fabadeaffc360a3719', 'dd', 'dd', null, null, null, null, null, null, null, '2018-04-22 10:32:19', '0f0ca899e7bd45e289452f0915fa6044');
INSERT INTO `tams_specification` VALUES ('e47080146fa94844867ab8bdb45a5b5a', '55', '55', null, null, null, null, '706829e0701b44baa072ee41d9636f7a', null, null, '2018-02-07 15:03:06', '56a733d88caa4ccd902ff054a08b8247');

-- ----------------------------
-- Table structure for tams_tool
-- ----------------------------
DROP TABLE IF EXISTS `tams_tool`;
CREATE TABLE `tams_tool` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `TOOL_NAME` varchar(50) DEFAULT NULL COMMENT '刀具名称',
  `TRADE_MARK` varchar(50) DEFAULT NULL COMMENT '牌号',
  `SPEC_NAME` varchar(50) DEFAULT NULL COMMENT '规格名称',
  `SPECIFICATION` varchar(50) DEFAULT NULL COMMENT '规格',
  `PRODUCT_PIC` varchar(255) DEFAULT NULL COMMENT '产品图片,多个图片以特殊分隔符分隔',
  `PATRS_PIC` varchar(255) DEFAULT NULL COMMENT '零件图片',
  `SAMPLE_PIC` varchar(255) DEFAULT NULL COMMENT '样本图片',
  `VIDEO_ID` varchar(255) DEFAULT NULL COMMENT '视频',
  `THREE_DIMENSIONAL` varchar(255) DEFAULT NULL COMMENT '三维图',
  `TOOL_LABEL` varchar(255) DEFAULT NULL COMMENT '标签',
  `CONTENT` varchar(255) DEFAULT NULL COMMENT '简介内容',
  `CATALOG_ID` varchar(32) DEFAULT NULL COMMENT '所属目录',
  `PRODUCT_ID` varchar(255) DEFAULT NULL COMMENT '产品关联id(产品组合以逗号分隔)',
  `BRAND_ID` varchar(255) DEFAULT NULL COMMENT '品牌ID（品牌id以逗号分隔）',
  `STATUS` tinyint(4) DEFAULT '0' COMMENT '状态(0：下架，1：上架)',
  `TOOL_WAY` tinyint(4) DEFAULT NULL COMMENT '走刀方式(数据字典，TOOL_WAY)',
  `TYPE` tinyint(4) DEFAULT NULL COMMENT '类型（0：组合，1：最小单位）',
  `IS_RECOMMEND` tinyint(4) DEFAULT NULL COMMENT '是否推荐（0：未推荐，1：推荐）',
  `SORT` int(11) DEFAULT '0' COMMENT '排序',
  `TENANT_ID` varchar(32) DEFAULT NULL COMMENT '租户id',
  `UPDATE_DATE` varchar(20) DEFAULT NULL COMMENT '更新日期',
  `UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `CREATE_DATE` varchar(20) DEFAULT NULL COMMENT '创建日期',
  `CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='刀具表';

-- ----------------------------
-- Records of tams_tool
-- ----------------------------
INSERT INTO `tams_tool` VALUES ('05ffd8b26dba47a2bd70f79f76d0fb0f', 'dgdfgdfg', null, null, 'xcvcxvxcv', null, null, null, null, null, null, 'xvxcvxcv', '4d4904bc3f6f4b729530ca80b8fc11f9', null, null, '0', null, '1', null, '0', null, null, null, '2018-01-29 15:22:59', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_tool` VALUES ('1c52afc0714a436ab2d5f5c2f6158b07', 'ddgdf', null, null, 'sdfdsf', null, null, null, null, null, null, 'dfdf', '4d4904bc3f6f4b729530ca80b8fc11f9', null, null, '0', null, '1', null, '0', null, null, null, '2018-01-29 11:26:44', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_tool` VALUES ('1c81bfd5a1d44c3c8e59606db4869eb1', '是多少q', null, null, '的事实a', null, null, null, null, null, null, '是多少', '4d4904bc3f6f4b729530ca80b8fc11f9', null, null, '1', null, '1', '0', '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-02-08 11:00:30', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-02-08 11:00:30', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_tool` VALUES ('1e0e1f2714f9434b8078b81fb19359d0', '说的都是', null, null, '撒大苏打 ', null, null, null, null, null, null, '啊实打实', '4d4904bc3f6f4b729530ca80b8fc11f9', null, null, '0', null, '1', null, '0', null, null, null, '2018-01-29 11:11:23', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_tool` VALUES ('2182d407822f412d902beceb290819af', '变脸王组合1', null, null, 'blw1111', 'attachment\\2018\\01\\f7f0f0b77da04306bd16a4a69ccc454f.jpg,', 'attachment\\2018\\01\\c732a8a190294489a8f49884c38d6805.jpg', null, null, null, null, '1111', 'f89a9044a5f34f178968d5a0de483e7d', null, null, '0', null, '1', null, '0', null, null, null, '2018-01-25 10:17:40', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_tool` VALUES ('2237f45308b84801a5153248684c56c6', 'fdfd', null, null, 'dfdf', null, null, null, null, null, null, 'dfdsf', '4d4904bc3f6f4b729530ca80b8fc11f9', null, null, '0', null, '1', null, '0', null, null, null, '2018-01-29 11:59:08', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_tool` VALUES ('2f8379c8854e4530bd1a202a4c5f6ac1', '11', null, null, '99', null, null, null, null, null, null, '11', 'f89a9044a5f34f178968d5a0de483e7d', null, null, '0', null, '1', null, '0', '4fc7ce16d79b46f69697e2041cc35dc6', null, null, '2018-02-09 14:37:00', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_tool` VALUES ('30cd8304acff4e2fa4309095486963e9', 'hsdjhdjghfdjslhgjdkf', null, null, 'dfgsagdgag', null, null, null, null, null, null, 'gasdg', '4d4904bc3f6f4b729530ca80b8fc11f9', null, null, '0', null, '1', null, '0', null, null, null, '2018-01-29 14:57:32', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_tool` VALUES ('39cad5e79ff8458284b713ed93a7c967', 'dfsdf', null, null, 'sdfdsfdsd', null, null, null, null, null, null, 'dsfdf', '4d4904bc3f6f4b729530ca80b8fc11f9', null, null, '0', null, '1', null, '0', null, null, null, '2018-01-29 15:30:20', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_tool` VALUES ('3a2ff6a394be43dbb5d58e9eeed82dc3', '士大夫大师傅的', null, null, '的说法都是', 'attachment\\2018\\01\\aefd74cc1fc645189a51785ccb218f4e.jpg,attachment\\2018\\01\\8e958cf4f3824193903ff367267bf359.jpg,', null, null, null, null, null, '大师傅士大夫', '4d4904bc3f6f4b729530ca80b8fc11f9', null, null, '0', null, '1', null, '0', null, null, null, '2018-01-29 11:23:24', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_tool` VALUES ('5ee75a4e9ec14779b733da7a452927bc', '变脸王2', null, null, 'rw123456', 'attachment\\2018\\01\\936051b15e1f490d975ff4419dcbea2a.jpg,', 'attachment\\2018\\01\\df6c5804cb4048abbfee0245789ba10a.jpg', null, null, 'attachment\\2018\\01\\35adb2a7d8d740c2a18138c3fced7563.jpg', null, '开发技术肯定J绝对空间发', '4d4904bc3f6f4b729530ca80b8fc11f9', null, null, '0', null, '1', null, '0', null, null, null, '2018-01-26 09:41:58', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_tool` VALUES ('6b7b018e1fe44a02b217957b7ae6bdbe', 'cvc', null, null, 'cvcvc', null, null, null, null, null, null, 'fdd', 'f89a9044a5f34f178968d5a0de483e7d', null, null, '0', null, '1', null, '0', null, null, null, '2018-01-29 11:54:06', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_tool` VALUES ('8647c8f0d3db4a68bcd51635b3f6c572', '士大夫', null, null, '所说的', null, null, null, null, null, null, '啊实打实的', '4d4904bc3f6f4b729530ca80b8fc11f9', null, null, '1', null, '1', null, '0', null, '2018-01-30 15:19:29', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-01-30 15:19:29', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_tool` VALUES ('88c7d5468f504608addd5544b529deed', '士大夫地方', null, null, '士大夫似的', 'attachment\\2018\\01\\f0b9be542e774b3fb9d2ba3e4040badc.jpg,attachment\\2018\\01\\4a21d853a0d6400e934cbd1585164f0f.jpg,', null, null, null, null, null, '但是肯定就很少看见', '4d4904bc3f6f4b729530ca80b8fc11f9', null, null, '0', null, '1', null, '0', null, null, null, '2018-01-29 11:22:31', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_tool` VALUES ('9c01915e52c642fe9cc562bd50a18811', 'sdfdsf', null, null, 'fsfsdf', null, null, null, null, null, null, 'sdfdfsdfsfdsddgsdg', '4d4904bc3f6f4b729530ca80b8fc11f9', null, null, '0', null, '1', null, '0', null, null, null, '2018-01-29 14:34:34', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_tool` VALUES ('a6d70cd6c51947e488b84659823ac681', 'dfdfdffd', null, null, 'dfdfdf', null, null, null, null, null, null, 'dfdfd', '4d4904bc3f6f4b729530ca80b8fc11f9', null, null, '0', null, '1', null, '0', null, null, null, '2018-01-29 11:27:03', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_tool` VALUES ('b904f9cb94c5404794157f4ee11335c2', 'dfdf', null, null, 'fdff', null, null, null, null, null, null, 'dssf', '4d4904bc3f6f4b729530ca80b8fc11f9', null, null, '0', null, '1', null, '0', null, null, null, '2018-01-29 11:53:08', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_tool` VALUES ('baa07e22fd4340f59ebced474ed6d140', '的速度', null, null, '是多少', 'attachment\\2018\\01\\cae6fabf58184c1da222980934cb16c3.jpg,attachment\\2018\\01\\84aff5c28a8d49b6aae527fdbb73dd12.jpg,', null, null, null, null, null, '啊实打实', '4d4904bc3f6f4b729530ca80b8fc11f9', null, null, '0', null, '1', null, '0', null, null, null, '2018-01-29 11:12:15', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_tool` VALUES ('bb9268e602f3442ba4cd76e23d3f7e6b', 'dgdfg', null, null, 'fdfdf', null, null, null, null, null, null, 'dfdf', '4d4904bc3f6f4b729530ca80b8fc11f9', null, null, '0', null, '1', null, '0', null, null, null, '2018-01-29 11:30:07', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_tool` VALUES ('be739095b68e4bad907fe2f3440fa910', 'xcdf', null, null, 'dfdf', null, null, null, null, null, null, 'dfdf', 'f89a9044a5f34f178968d5a0de483e7d', null, null, '0', null, '1', null, '0', null, null, null, '2018-01-29 11:57:14', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_tool` VALUES ('bfc2197ffcb1477f87217f7eb9ba19d9', '11', '11', null, '007', null, null, null, null, null, null, '111', 'f89a9044a5f34f178968d5a0de483e7d', null, null, '0', '1', '0', null, '0', '4fc7ce16d79b46f69697e2041cc35dc6', '2018-02-09 14:37:48', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-02-09 14:37:48', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_tool` VALUES ('c869cf16e609476eb94b727adee2bf67', 'fgfg', null, null, 'dfgdfg', null, null, null, null, null, null, 'dfdfg', '4d4904bc3f6f4b729530ca80b8fc11f9', null, null, '0', null, '1', '1', '0', '4fc7ce16d79b46f69697e2041cc35dc6', null, null, '2018-01-29 11:56:10', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_tool` VALUES ('ca6a37aee7c845ab815e1855fac0ce5f', '变脸王3', null, null, 'zx5678', 'attachment\\2018\\01\\25f9677c17a04494898970d62a9d8ad9.jpg,', 'attachment\\2018\\01\\7658dfa8c8bf4fb29ddb5aaa8380ee06.jpg', null, null, null, null, '大师傅士大夫但是', '4d4904bc3f6f4b729530ca80b8fc11f9', null, null, '0', null, '1', '1', '0', '4fc7ce16d79b46f69697e2041cc35dc6', null, null, '2018-01-26 09:51:56', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_tool` VALUES ('dc51b7d45c4c440a9ae8c02e94f10ed4', 'dfdf', null, null, 'sdfd', null, null, null, null, null, null, 'sdfdsf', '4d4904bc3f6f4b729530ca80b8fc11f9', null, null, '0', null, '1', '1', '0', '4fc7ce16d79b46f69697e2041cc35dc6', null, null, '2018-01-29 11:47:33', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_tool` VALUES ('e19cb9bf78ab4a05930c08522de440f2', '变脸王', null, null, 'ww11111', 'attachment\\2018\\01\\6d9a117a2423437dba7b4b0a8a586063.jpg,', 'attachment\\2018\\01\\af03e0131a62444b9f87b297d61fcaae.jpg', null, null, 'attachment\\2018\\01\\1cb44a0261e64ebe8510d59b9010080e.jpg', null, '我是变脸王', 'f89a9044a5f34f178968d5a0de483e7d', null, null, '0', null, '1', '1', '0', '4fc7ce16d79b46f69697e2041cc35dc6', null, null, '2018-01-25 10:16:37', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_tool` VALUES ('ed9b3488ede5453ca3d77b1769588872', 'ddgdg', null, null, 'dgdfgdf', null, null, null, null, null, null, 'dfgdfgd', '4d4904bc3f6f4b729530ca80b8fc11f9', null, null, '0', null, '1', null, '0', null, null, null, '2018-01-29 14:34:10', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_tool` VALUES ('f4570f617da84d02be0559e3c51cdb1d', 'gdf', null, null, 'dfgdfg', null, null, null, null, null, null, 'dgdfg', '4d4904bc3f6f4b729530ca80b8fc11f9', null, null, '0', null, '1', null, '0', null, null, null, '2018-01-29 11:56:26', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_tool` VALUES ('f50735578bf84623bbc989245746491d', 'sffsf', null, null, 'sdfsfsf', null, null, null, null, null, null, 'sdfsf', '4d4904bc3f6f4b729530ca80b8fc11f9', null, null, '0', null, '1', null, '0', null, null, null, '2018-01-29 14:24:53', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_tool` VALUES ('ff70500ca0e4470baf182ea25fdd0f6b', '士大夫但是', null, null, '士大夫但是', null, null, null, null, null, null, '士大夫但是', '4d4904bc3f6f4b729530ca80b8fc11f9', null, null, '0', null, '1', null, '0', null, null, null, '2018-01-29 11:22:54', '2b0a1d6269a2482f9cfc5933b0400fdc');

-- ----------------------------
-- Table structure for tams_tool_catalog
-- ----------------------------
DROP TABLE IF EXISTS `tams_tool_catalog`;
CREATE TABLE `tams_tool_catalog` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `PARENT_ID` varchar(32) DEFAULT NULL COMMENT '父节点 根节点为0',
  `CATALOG_NAME` varchar(50) DEFAULT NULL COMMENT '产品目录名称',
  `SORT` int(11) DEFAULT '0' COMMENT '排序',
  `UPDATE_DATE` varchar(20) DEFAULT NULL COMMENT '更新日期',
  `UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `CREATE_DATE` varchar(20) DEFAULT NULL COMMENT '创建日期',
  `CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `TENANT_ID` varchar(32) DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='刀具目录表';

-- ----------------------------
-- Records of tams_tool_catalog
-- ----------------------------
INSERT INTO `tams_tool_catalog` VALUES ('4d4904bc3f6f4b729530ca80b8fc11f9', '6fb1fbb99e4c475a832adf7272281a5a', '测试修改切削刀具', '0', '2018-03-30 13:47:53', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-01-25 10:15:18', '2b0a1d6269a2482f9cfc5933b0400fdc', '4fc7ce16d79b46f69697e2041cc35dc6');
INSERT INTO `tams_tool_catalog` VALUES ('6fb1fbb99e4c475a832adf7272281a5a', '0', '刀具目录', '0', '2018-01-09 18:21:56', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-01-02 17:13:45', '', '4fc7ce16d79b46f69697e2041cc35dc6');
INSERT INTO `tams_tool_catalog` VALUES ('f89a9044a5f34f178968d5a0de483e7d', '6fb1fbb99e4c475a832adf7272281a5a', '硬质刀具', '0', null, null, '2018-01-25 10:15:38', '2b0a1d6269a2482f9cfc5933b0400fdc', '4fc7ce16d79b46f69697e2041cc35dc6');

-- ----------------------------
-- Table structure for tams_tool_compose
-- ----------------------------
DROP TABLE IF EXISTS `tams_tool_compose`;
CREATE TABLE `tams_tool_compose` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `SMALL_TOOL_ID` varchar(32) DEFAULT NULL COMMENT '最小单位刀具ID',
  `TOOL_ID` varchar(32) DEFAULT NULL COMMENT '刀具ID',
  `SORT` int(11) DEFAULT '0' COMMENT '排序',
  `TENANT_ID` varchar(32) DEFAULT NULL COMMENT '租户id',
  `UPDATE_DATE` varchar(20) DEFAULT NULL COMMENT '更新日期',
  `UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `CREATE_DATE` varchar(20) DEFAULT NULL COMMENT '创建日期',
  `CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='刀具组合表';

-- ----------------------------
-- Records of tams_tool_compose
-- ----------------------------

-- ----------------------------
-- Table structure for tams_tool_price
-- ----------------------------
DROP TABLE IF EXISTS `tams_tool_price`;
CREATE TABLE `tams_tool_price` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `TOOL_ID` varchar(32) DEFAULT NULL COMMENT '刀具id',
  `TOOL_PRICE` float(9,2) DEFAULT NULL COMMENT '价格',
  `PRICE_DATE` varchar(20) DEFAULT NULL COMMENT '价格时间',
  `SORT` int(11) DEFAULT '0' COMMENT '排序',
  `TENANT_ID` varchar(32) DEFAULT NULL COMMENT '租户id',
  `UPDATE_DATE` varchar(20) DEFAULT NULL COMMENT '更新日期',
  `UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `CREATE_DATE` varchar(20) DEFAULT NULL COMMENT '创建日期',
  `CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='刀具价格表';

-- ----------------------------
-- Records of tams_tool_price
-- ----------------------------
INSERT INTO `tams_tool_price` VALUES ('e42d3f0cfffa4402b55700543284e163', 'bfc2197ffcb1477f87217f7eb9ba19d9', '121.00', '2018-04-18', null, '706829e0701b44baa072ee41d9636f7a', null, null, '2018-04-18 10:35:39', '2b0a1d6269a2482f9cfc5933b0400fdc');

-- ----------------------------
-- Table structure for tams_user_feed_back
-- ----------------------------
DROP TABLE IF EXISTS `tams_user_feed_back`;
CREATE TABLE `tams_user_feed_back` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `WORK_NO` varchar(32) DEFAULT NULL COMMENT '工号',
  `TOOL_NAME` varchar(100) DEFAULT NULL COMMENT '零件',
  `TECHNICS_CODE` varchar(50) DEFAULT NULL COMMENT '工艺编号',
  `SCHEME_NO` varchar(50) DEFAULT NULL COMMENT '工序号',
  `WORK_STEP` varchar(50) DEFAULT NULL COMMENT '工步',
  `START_TIME` varchar(20) DEFAULT NULL COMMENT '开始时间',
  `END_TIME` varchar(20) DEFAULT NULL COMMENT '结束时间',
  `S` varchar(50) DEFAULT NULL COMMENT 'S',
  `F` varchar(50) DEFAULT NULL COMMENT 'F',
  `AP` varchar(50) DEFAULT NULL COMMENT 'AP',
  `FN` varchar(50) DEFAULT NULL COMMENT 'FN',
  `VC` varchar(50) DEFAULT NULL COMMENT 'VC',
  `FZ` varchar(50) DEFAULT NULL COMMENT 'FZ',
  `AE` varchar(50) DEFAULT NULL COMMENT 'AE',
  `MAKE_AMOUNT` int(11) DEFAULT NULL COMMENT '加工数量',
  `WASTE_AMOUNT` int(11) DEFAULT NULL COMMENT '废品数量',
  `RECOVERY` varchar(50) DEFAULT NULL COMMENT '回用品',
  `QUALIFIED_AMOUNT` int(11) DEFAULT NULL COMMENT '合格数量',
  `INTERRUPT_REASON` varchar(255) DEFAULT NULL COMMENT '加工中断原因',
  `CONSUME` varchar(50) DEFAULT NULL COMMENT '消耗品',
  `CONSUME_AMOUNT` int(11) DEFAULT NULL COMMENT '消耗品数量',
  `DEVICE` varchar(50) DEFAULT NULL COMMENT '设备信息',
  `TOOL_COMPONENT` text COMMENT '组件串(字段以逗号分隔，记录以分号)',
  `SORT` int(11) DEFAULT '0' COMMENT '排序',
  `TENANT_ID` varchar(32) DEFAULT NULL,
  `UPDATE_DATE` varchar(20) DEFAULT NULL COMMENT '更新日期',
  `UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `CREATE_DATE` varchar(20) DEFAULT NULL COMMENT '创建日期',
  `CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户反馈表';

-- ----------------------------
-- Records of tams_user_feed_back
-- ----------------------------
INSERT INTO `tams_user_feed_back` VALUES ('390cb647a2104662a704dd81e692b260', '12', '0a279689ce35481f8bbb7ffe71066642', '48d23426e724490e9d1dad0c60f92e34', null, '2e2987e9cad2427d87edc0c5da2ce67e', '2018-04-10', '2018-04-10', '12', '12', '1', '1', '1', '1', null, '1', '1', '1', '1', '2', '1', null, null, '', null, null, null, null, '2018-04-09 23:37:46', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_user_feed_back` VALUES ('a4de67aba0c64e0cb7e07569d984bf41', '11', '00f05f925b8648af9b7111435ee99f54', '01a46df9eb654433a0f55f774623e613', null, '836161476bd84aed82a16e2cf7701d08', '2018-04-10', '2018-04-20', '1', '1', '1', '1', '1', '1', null, '1', '1', '1', '1', '0', null, '1', null, '', null, '4fc7ce16d79b46f69697e2041cc35dc6', null, null, '2018-04-20 18:27:00', '0f0ca899e7bd45e289452f0915fa6044');
INSERT INTO `tams_user_feed_back` VALUES ('cb065d0f1c6246729742344e76db9e4b', '12', '00f05f925b8648af9b7111435ee99f54', '48d23426e724490e9d1dad0c60f92e34', null, '6763efc6b1014e9da1fd523e33f70248', '2018-04-17', '2018-04-17', '12', '12', '11', '12', '12', '12', null, '12', '12', '12', '12', '1', '12', null, null, '', null, null, null, null, '2018-04-09 23:29:24', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_user_feed_back` VALUES ('d3c724268c7645b5a685d25d2ec00527', '12', '00f05f925b8648af9b7111435ee99f54', '48d23426e724490e9d1dad0c60f92e34', null, '6763efc6b1014e9da1fd523e33f70248', '2018-04-17', '2018-04-17', '12', '12', '11', '12', '12', '12', null, '12', '12', '12', '12', '1', '12', null, null, '', null, null, null, null, '2018-04-09 23:33:57', '2b0a1d6269a2482f9cfc5933b0400fdc');

-- ----------------------------
-- Table structure for tams_video
-- ----------------------------
DROP TABLE IF EXISTS `tams_video`;
CREATE TABLE `tams_video` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `VIDEO_NAME` varchar(50) DEFAULT NULL COMMENT '视频名称',
  `UPLOAD_NAME` varchar(255) DEFAULT NULL COMMENT '上传初始名称',
  `COVER_PIC` varchar(255) DEFAULT NULL COMMENT '封面图片',
  `VIDEO_PIC` varchar(255) DEFAULT NULL COMMENT '视频路径',
  `CONTENT` varchar(255) DEFAULT NULL COMMENT '内容',
  `CATALOG_ID` varchar(32) DEFAULT NULL COMMENT '所属目录',
  `STATUS` tinyint(4) DEFAULT '0' COMMENT '状态(0：未审核，1：已审核，2：审核不通过)',
  `SORT` int(11) DEFAULT '0' COMMENT '排序',
  `UPDATE_DATE` varchar(20) DEFAULT NULL COMMENT '更新日期',
  `UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `CREATE_DATE` varchar(20) DEFAULT NULL COMMENT '创建日期',
  `CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='视频表';

-- ----------------------------
-- Records of tams_video
-- ----------------------------
INSERT INTO `tams_video` VALUES ('08ee87338d2b4f09abf4edbd205bf3da', 'ff', null, null, null, 'ff', '336f4d56e4fc45e797b9393b27f63c7b', '0', null, null, null, '2018-04-20 17:44:31', '0f0ca899e7bd45e289452f0915fa6044');
INSERT INTO `tams_video` VALUES ('73d1c467eb8841a3b88230f4692a3d78', 'test上传视频', null, null, null, '测试上传产品视频', 'd782224b1682404983d5e6d510dc117a', '1', null, '2018-04-09 19:10:03', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-04-09 11:22:40', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_video` VALUES ('766ff18249d14ae0af8105126643c289', '航空', null, '', '/address', '航空', '336f4d56e4fc45e797b9393b27f63c7b', '1', null, '2018-01-25 10:06:25', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-01-25 10:04:19', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_video` VALUES ('87aac86e18ad46259c3904b0d2dcc2f8', '视频上传测试', null, null, null, '视频上传测试', '336f4d56e4fc45e797b9393b27f63c7b', '0', null, null, null, '2018-03-30 13:27:45', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_video` VALUES ('e09c6c16de9f4a55a8df925cf863a441', '士大夫似的', null, null, null, '现在v操作v出现', '336f4d56e4fc45e797b9393b27f63c7b', '2', null, '2018-03-30 12:01:11', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-01-25 11:16:02', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_video` VALUES ('f8cb7483cdbc460d902cabf6a27312c5', '33', null, null, null, '333', '336f4d56e4fc45e797b9393b27f63c7b', '0', null, null, null, '2018-04-20 17:44:05', '0f0ca899e7bd45e289452f0915fa6044');

-- ----------------------------
-- Table structure for tams_video_catalog
-- ----------------------------
DROP TABLE IF EXISTS `tams_video_catalog`;
CREATE TABLE `tams_video_catalog` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `PARENT_ID` varchar(32) DEFAULT NULL COMMENT '父节点 根节点为0',
  `CATALOG_NAME` varchar(50) DEFAULT NULL COMMENT '视频目录名称',
  `SORT` int(11) DEFAULT '0' COMMENT '排序',
  `TENANT_ID` varchar(32) DEFAULT NULL COMMENT '租户id',
  `UPDATE_DATE` varchar(20) DEFAULT NULL COMMENT '更新日期',
  `UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `CREATE_DATE` varchar(20) DEFAULT NULL COMMENT '创建日期',
  `CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='视频目录表';

-- ----------------------------
-- Records of tams_video_catalog
-- ----------------------------
INSERT INTO `tams_video_catalog` VALUES ('2fb86648f19048399345bfa2e20dab46', 'fc1c38562f59449eb0616afaf3620c2d', '汽车刀具', '0', '706829e0701b44baa072ee41d9636f7a', null, null, '2018-01-25 09:32:05', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_video_catalog` VALUES ('3191c994248e40a3b1692c93e06b006d', '6fb1fbb99e4c475a832adf7272281a5a', 'cc', '0', '4fc7ce16d79b46f69697e2041cc35dc6', null, null, '2018-04-20 19:36:34', '0f0ca899e7bd45e289452f0915fa6044');
INSERT INTO `tams_video_catalog` VALUES ('336f4d56e4fc45e797b9393b27f63c7b', '91d6a47283a44391bce283fa3384fabd', '加工视频', '0', '706829e0701b44baa072ee41d9636f7a', null, null, '2018-01-25 09:32:52', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_video_catalog` VALUES ('6fb1fbb99e4c475a832adf7272281a5a', '0', '视频目录', '0', '706829e0701b44baa072ee41d9636f7a', '2018-01-09 18:21:56', '2b0a1d6269a2482f9cfc5933b0400fdc', '2018-01-02 17:13:45', '');
INSERT INTO `tams_video_catalog` VALUES ('7e6bd3b5912a423fad128f064752b579', 'fc1c38562f59449eb0616afaf3620c2d', '航空刀具', '0', '706829e0701b44baa072ee41d9636f7a', null, null, '2018-01-25 09:31:40', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_video_catalog` VALUES ('91d6a47283a44391bce283fa3384fabd', '6fb1fbb99e4c475a832adf7272281a5a', '产品视频', '0', '706829e0701b44baa072ee41d9636f7a', null, null, '2018-01-25 09:32:19', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_video_catalog` VALUES ('d3452e73547c4589a02945ba088b53f4', '91d6a47283a44391bce283fa3384fabd', '简介视频', '0', '706829e0701b44baa072ee41d9636f7a', null, null, '2018-01-25 09:32:41', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_video_catalog` VALUES ('d782224b1682404983d5e6d510dc117a', '91d6a47283a44391bce283fa3384fabd', 'test视频目录', '0', '706829e0701b44baa072ee41d9636f7a', null, null, '2018-04-09 11:21:34', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_video_catalog` VALUES ('f7bdab22b1094caf8bcc22ef89ce57af', '91d6a47283a44391bce283fa3384fabd', '测试视频目录', '0', '706829e0701b44baa072ee41d9636f7a', null, null, '2018-03-30 14:18:50', '2b0a1d6269a2482f9cfc5933b0400fdc');
INSERT INTO `tams_video_catalog` VALUES ('fc1c38562f59449eb0616afaf3620c2d', '6fb1fbb99e4c475a832adf7272281a5a', '刀具视频 ', '0', '706829e0701b44baa072ee41d9636f7a', null, null, '2018-01-25 09:30:48', '2b0a1d6269a2482f9cfc5933b0400fdc');
