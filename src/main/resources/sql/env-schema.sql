DROP TABLE IF EXISTS `t_test_saas_entity`;
CREATE TABLE `t_test_saas_entity`(
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL COMMENT '测试名称',
  `org_id` bigint(20) DEFAULT 0 COMMENT '所属组织',
  `status` varchar(20) NOT NULL COMMENT '状态',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

