
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_project
-- ----------------------------
DROP TABLE IF EXISTS `cr_project`;
CREATE TABLE `cr_project` (
  `id` bigint(20) NOT NULL COMMENT 'primary key\n',
  `org_id` bigint(20) NOT NULL COMMENT '资源隔离使用字段',
  `project_name` varchar(26) NOT NULL COMMENT '项目名称\n',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '项目描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  UNIQUE KEY(`project_name`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_module
-- ----------------------------
DROP TABLE IF EXISTS `cr_module`;
CREATE TABLE `cr_module` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'primary key',
  `project_id` bigint(20) NOT NULL COMMENT '项目名称',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '项目描述',
  `module_name` varchar(26) NOT NULL COMMENT '模块名称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY(`project_id`,`module_name`),
  FOREIGN KEY (`project_id`) REFERENCES `cr_project` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_issue
-- ----------------------------
DROP TABLE IF EXISTS `cr_issue`;
CREATE TABLE `cr_issue` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'primary key\n',
  `module_id` bigint(20) default NULL COMMENT '模块ID',
  `org_id` bigint(20) default NULL COMMENT '资源隔离使用字段',
  `create_by` bigint(20) NOT NULL COMMENT '创建者ID',
  `create_by_name` varchar(26) NOT NULL COMMENT '创建者名称',
  `title` varchar(26) NOT NULL COMMENT '问题主要描述',
  `note` varchar(255) DEFAULT NULL COMMENT '问题描述',
  `priority` tinyint NOT NULL default 1 COMMENT '优先级,越大月严重',
  `image_url` varchar(255) DEFAULT NULL COMMENT '问题截图',
  `view_num` int DEFAULT 0 COMMENT '浏览数量',
  `attachment` varchar(255) DEFAULT NULL COMMENT '附件',
  `status` varchar(26) NOT NULL DEFAULT 'OPEN' COMMENT '状态',
  `owner_id` bigint(20) DEFAULT NULL COMMENT '指派处理人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  FOREIGN KEY (`module_id`) REFERENCES `cr_module` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_issue_note
-- ----------------------------
DROP TABLE IF EXISTS `cr_issue_note`;
CREATE TABLE `cr_issue_note` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'primary key\n',
  `issue_id` bigint(20) NOT NULL COMMENT 'issue ID',
  `handler_id` bigint(20) NOT NULL COMMENT '处理人 id',
  `handler_name` varchar(26) NOT NULL COMMENT '处理人名称',
  `from_status` varchar(26) NOT NULL COMMENT '处理前状态',
  `to_status` varchar(26) NOT NULL COMMENT '处理后状态',
  `note` varchar(255) NOT NULL COMMENT '处理意见',
  `image_url` varchar(255) DEFAULT NULL COMMENT '问题截图',
  `attachment` varchar(255) DEFAULT NULL COMMENT '附件',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY(`issue_id`),
  FOREIGN KEY (`issue_id`) REFERENCES `cr_issue` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



SET FOREIGN_KEY_CHECKS = 1;
