package com.jfeat.am.module.cr.services.domain.dao;

import com.jfeat.am.module.cr.services.domain.model.ModuleRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2019-04-08
 */
public interface QueryModuleDao extends BaseMapper<ModuleRecord> {
    List<ModuleRecord> findModulePage(Page<ModuleRecord> page, @Param("record") ModuleRecord record, @Param("search") String search, @Param("orderBy") String orderBy);
}