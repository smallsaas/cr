package com.jfeat.am.module.test.services.domain.dao;

import com.jfeat.am.module.test.services.domain.model.TestSaasEntityRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.jfeat.am.module.test.services.gen.persistence.model.TestSaasEntity;
import java.util.List;

/**
 * Created by Code Generator on 2019-02-13
 */
public interface QueryTestSaasEntityDao extends BaseMapper<TestSaasEntity> {
    List<TestSaasEntityRecord> findTestSaasEntityPage(Page<TestSaasEntityRecord> page, @Param("record") TestSaasEntityRecord record, @Param("search") String search, @Param("orderBy") String orderBy);
}