package com.jfeat.am.module.cr.services.domain.dao;

import com.jfeat.am.module.cr.services.domain.model.IssueNoteRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2019-04-08
 */
public interface QueryIssueNoteDao extends BaseMapper<IssueNoteRecord> {
    List<IssueNoteRecord> findIssueNotePage(Page<IssueNoteRecord> page, @Param("record") IssueNoteRecord record, @Param("path") String search, @Param("orderBy") String orderBy);
}