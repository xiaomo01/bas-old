-- ----------------------------
-- Table structure for system_admin
-- ----------------------------
DROP TABLE IF EXISTS `system_admin`;
CREATE TABLE `system_admin` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `admin_name` varchar(50) DEFAULT NULL COMMENT '管理员名称',
  `login_name` varchar(50) DEFAULT NULL COMMENT '登录名称',
  `login_pass` varchar(50) DEFAULT NULL COMMENT '登录密码',
  `admin_state` tinyint(1) DEFAULT NULL COMMENT '管理员状态',
  `sort_num` int(11) DEFAULT NULL COMMENT '排序号(升序)',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除状态',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新用户',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员信息表';

-- ----------------------------
-- Records of system_admin
-- ----------------------------
INSERT INTO `system_admin` VALUES ('1211949375159906306', '超级管理员', 'admin', 'd9bd544f731f68d67bd167761bcc291b', '1', '1', '0', null, null, null, '2019-12-31 17:57:03');
INSERT INTO `system_admin` VALUES ('1211950458989006850', '小莫', 'xiaomo', 'dfc7e187984567f5475c8091a666515e', '1', '2', '0', null, null, null, '2019-12-31 18:02:08');
INSERT INTO `system_admin` VALUES ('1212546655249895424', '测试1', 'ceshi', 'dfc7e187984567f5475c8091a666515e', '1', '3', '0', null, '2020-01-02 09:30:25', null, null);
INSERT INTO `system_admin` VALUES ('1212576408644227072', 'aaa', 'aaa1', '123456', '1', null, '0', null, '2020-01-02 11:28:39', null, null);

-- ----------------------------
-- Table structure for system_admin_info
-- ----------------------------
DROP TABLE IF EXISTS `system_admin_info`;
CREATE TABLE `system_admin_info` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `admin_id` bigint(20) DEFAULT NULL COMMENT '管理员ID',
  `head_image_path` varchar(50) DEFAULT NULL COMMENT '头像路径',
  `admin_phone` varchar(20) DEFAULT NULL COMMENT '联系方式',
  `admin_card_no` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `admin_sex` tinyint(1) DEFAULT NULL COMMENT '性别',
  `admin_age` tinyint(1) DEFAULT NULL COMMENT '年龄',
  `admin_address` varchar(100) DEFAULT NULL COMMENT '地址',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新用户',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员附属信息表';

-- ----------------------------
-- Records of system_admin_info
-- ----------------------------

-- ----------------------------
-- Table structure for system_admin_org_relation
-- ----------------------------
DROP TABLE IF EXISTS `system_admin_org_relation`;
CREATE TABLE `system_admin_org_relation` (
  `admin_id` bigint(20) DEFAULT NULL COMMENT '管理员ID',
  `org_id` bigint(20) DEFAULT NULL COMMENT '组织机构ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户组织关系表';

-- ----------------------------
-- Records of system_admin_org_relation
-- ----------------------------
INSERT INTO `system_admin_org_relation` VALUES ('1211949375159906306', '1212660853715898368');
INSERT INTO `system_admin_org_relation` VALUES ('1211950458989006850', '1212660853715898368');
INSERT INTO `system_admin_org_relation` VALUES ('1212546655249895424', '1212660853715898368');
INSERT INTO `system_admin_org_relation` VALUES ('1211949375159906306', '1212929581305171968');

-- ----------------------------
-- Table structure for system_config
-- ----------------------------
DROP TABLE IF EXISTS `system_config`;
CREATE TABLE `system_config` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `config_key` varchar(50) DEFAULT NULL COMMENT '配置属性编码',
  `config_content` varchar(100) DEFAULT NULL COMMENT '配置说明',
  `config_value` varchar(200) DEFAULT NULL COMMENT '配置数据',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `sort_num` int(11) DEFAULT NULL COMMENT '排序号(升序)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统配置表';

-- ----------------------------
-- Records of system_config
-- ----------------------------

-- ----------------------------
-- Table structure for system_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `system_dictionary`;
CREATE TABLE `system_dictionary` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `dict_code` varchar(50) DEFAULT NULL COMMENT '类型编码',
  `dict_name` varchar(100) DEFAULT NULL COMMENT '类型名称',
  `dict_label` varchar(50) DEFAULT NULL COMMENT '数据标签',
  `dict_value_code` varchar(50) DEFAULT NULL COMMENT '数据值编码',
  `dict_value` tinyint(2) DEFAULT NULL COMMENT '数据值',
  `sort_num` int(11) DEFAULT NULL COMMENT '排序号(升序)',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除状态',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新用户',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据字典信息表';

-- ----------------------------
-- Records of system_dictionary
-- ----------------------------

-- ----------------------------
-- Table structure for system_log
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `opt_user_id` bigint(20) DEFAULT NULL COMMENT '操作人ID',
  `permission_id` bigint(20) DEFAULT NULL COMMENT '功能ID',
  `opt_theme` varchar(100) DEFAULT NULL COMMENT '功能说明',
  `opt_content` varchar(200) DEFAULT NULL COMMENT '具体操作内容',
  `info_id` bigint(20) DEFAULT NULL COMMENT '操作相关信息ID',
  `opt_url` varchar(200) DEFAULT NULL COMMENT '操作接口地址',
  `opt_ip` varchar(50) DEFAULT NULL COMMENT '操作IP',
  `opt_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统日志信息表';

-- ----------------------------
-- Records of system_log
-- ----------------------------

-- ----------------------------
-- Table structure for system_org
-- ----------------------------
DROP TABLE IF EXISTS `system_org`;
CREATE TABLE `system_org` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `org_name` varchar(50) DEFAULT NULL COMMENT '机构名称',
  `org_grade` tinyint(2) DEFAULT NULL COMMENT '机构级别',
  `org_parent_id` bigint(20) DEFAULT NULL COMMENT '上级机构编码',
  `all_org_codes` varchar(200) DEFAULT NULL COMMENT '所有机构编码',
  `remark` varchar(200) DEFAULT NULL COMMENT '机构描述',
  `sort_num` int(11) DEFAULT NULL COMMENT '排序号(升序)',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除状态',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新用户',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组织机构信息表';

-- ----------------------------
-- Records of system_org
-- ----------------------------
INSERT INTO `system_org` VALUES ('1212660853715898368', '系统组织机构', '1', '0', ',0,1212660853715898368,', null, '1', '0', null, '2020-01-02 17:04:12', null, null);
INSERT INTO `system_org` VALUES ('1212929581305171968', '办公室1', '2', '1212660853715898368', ',0,1212660853715898368,1212929581305171968,', null, '1', '0', null, '2020-01-03 10:52:02', null, '2020-01-03 10:52:02');
INSERT INTO `system_org` VALUES ('1212931822476988416', '科室1', '3', '1212929581305171968', ',0,1212660853715898368,1212929581305171968,1212931822476988416,', null, '1', '0', null, '2020-01-03 11:00:56', null, '2020-01-03 11:00:56');

-- ----------------------------
-- Table structure for system_org_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `system_org_role_relation`;
CREATE TABLE `system_org_role_relation` (
  `org_id` bigint(20) DEFAULT NULL COMMENT '组织机构ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组织角色关系表';

-- ----------------------------
-- Records of system_org_role_relation
-- ----------------------------
INSERT INTO `system_org_role_relation` VALUES ('1212660853715898368', '1212638252062871552');
INSERT INTO `system_org_role_relation` VALUES ('1212929581305171968', '1212929419438592000');
INSERT INTO `system_org_role_relation` VALUES ('1212931822476988416', '1212929419438592000');

-- ----------------------------
-- Table structure for system_permission
-- ----------------------------
DROP TABLE IF EXISTS `system_permission`;
CREATE TABLE `system_permission` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `permission_name` varchar(50) DEFAULT NULL COMMENT '名称',
  `permission_parent_id` bigint(20) DEFAULT NULL COMMENT '上级编码',
  `permission_grade` tinyint(2) DEFAULT NULL COMMENT '级别编码',
  `permission_type` tinyint(1) DEFAULT NULL COMMENT '类型（菜单、功能）',
  `last_flag` tinyint(1) DEFAULT NULL COMMENT '是否底层菜单（用于判断是否可添加下级菜单）',
  `permission_url` varchar(200) DEFAULT NULL COMMENT '菜单链接',
  `all_permission_ids` varchar(200) DEFAULT NULL COMMENT '所有功能编码',
  `sort_num` int(11) DEFAULT NULL COMMENT '排序号(升序)',
  `remark` varchar(200) DEFAULT NULL COMMENT '描述',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新用户',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单功能信息表';

-- ----------------------------
-- Records of system_permission
-- ----------------------------
INSERT INTO `system_permission` VALUES ('1212629437191950336', '系统管理', '0', '1', '1', '0', '../', ',0,1212629437191950336,', '1', null, '1211949375159906306', '2020-01-02 15:30:37', null, null);
INSERT INTO `system_permission` VALUES ('1212636860334084096', '菜单管理', '1212629437191950336', '2', '1', '1', '../', ',0,1212629437191950336,1212636860334084096,', '1', null, '1211949375159906306', '2020-01-02 15:30:40', null, null);
INSERT INTO `system_permission` VALUES ('1212637146708578304', '新增', '1212636860334084096', '3', '2', '1', '../', ',0,1212629437191950336,1212636860334084096,1212637146708578304,', '1', null, '1211949375159906306', '2020-01-02 15:30:43', null, null);
INSERT INTO `system_permission` VALUES ('1212640466235953152', '编辑', '1212636860334084096', '3', '2', '1', '../', ',0,1212629437191950336,1212636860334084096,1212640466235953152,', '2', null, null, null, null, null);

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `sort_num` int(11) DEFAULT NULL COMMENT '排序号(升序)',
  `remark` varchar(200) DEFAULT NULL COMMENT '角色描述',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新用户',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员角色信息表';

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES ('1212638252062871552', '系统管理员', '1', null, null, '2020-01-02 15:34:54', null, null);
INSERT INTO `system_role` VALUES ('1212929419438592000', '平台管理员', '2', null, null, '2020-01-03 10:51:23', null, null);

-- ----------------------------
-- Table structure for system_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `system_role_permission_relation`;
CREATE TABLE `system_role_permission_relation` (
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `permission_id` bigint(20) DEFAULT NULL COMMENT '功能ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色功能关系表';

-- ----------------------------
-- Records of system_role_permission_relation
-- ----------------------------
INSERT INTO `system_role_permission_relation` VALUES ('1212638252062871552', '1212629437191950336');
INSERT INTO `system_role_permission_relation` VALUES ('1212638252062871552', '1212636860334084096');
INSERT INTO `system_role_permission_relation` VALUES ('1212638252062871552', '1212637146708578304');
INSERT INTO `system_role_permission_relation` VALUES ('1212929419438592000', '1212640466235953152');
INSERT INTO `system_role_permission_relation` VALUES ('1212929419438592000', '1212629437191950336');
INSERT INTO `system_role_permission_relation` VALUES ('1212929419438592000', '1212636860334084096');

-- ----------------------------
-- Table structure for system_sensitive
-- ----------------------------
DROP TABLE IF EXISTS `system_sensitive`;
CREATE TABLE `system_sensitive` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `sensitive_content` varchar(100) DEFAULT NULL COMMENT '敏感词内容',
  `relpace_content` varchar(100) DEFAULT NULL COMMENT '替换内容',
  `sort_num` int(11) DEFAULT NULL COMMENT '排序号(升序)',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除状态',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新用户',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='敏感词信息表';

-- ----------------------------
-- Records of system_sensitive
-- ----------------------------
