package com.jfeat.am.module.cr.services.domain.dao;

import com.jfeat.am.module.cr.services.domain.model.IssueModel;
import com.jfeat.am.module.cr.services.domain.model.IssueRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Code Generator on 2019-04-08
 */
public interface QueryIssueDao extends BaseMapper<IssueRecord> {
    List<IssueRecord> findIssuePage(Page<IssueRecord> page,
                                    @Param("record") IssueRecord record,
                                    @Param("startTime") Date startTime,
                                    @Param("endTime") Date endTime);

    /*
    *
    * 单个项目的 ISSUE列表  参数为 项目的ID
    * */
    List<IssueRecord> projectIssueDetails(Page<IssueRecord> page,
                                          @Param("record") IssueRecord record,
                                          @Param("projectId") Long projectId,
                                          @Param("startTime") Date startTime,
                                          @Param("endTime") Date endTime);
    /*
    * 指定模块 ISSUE列表 参数为 木块的ID
    * */
    List<IssueRecord> moduleIssueDetails(Page<IssueRecord> page,
                                          @Param("record") IssueRecord record,
                                          @Param("moduleId") Long moduleId,
                                          @Param("startTime") Date startTime,
                                          @Param("endTime") Date endTime);
    /*
    * 指定部门的 ISSUE列表
    * */
    List<IssueRecord> orgIssueDetails(Page<IssueRecord> page,
                                         @Param("record") IssueRecord record,
                                         @Param("orgId") Long orgId,
                                         @Param("startTime") Date startTime,
                                         @Param("endTime") Date endTime);

    /*
     * 指定部门的下属部门(包含本部门) ISSUE列表
     * */
    List<IssueRecord> orgAndKidsIssueDetails(Page<IssueRecord> page,
                                      @Param("record") IssueRecord record,
                                      @Param("orgId") Long orgId,
                                      @Param("startTime") Date startTime,
                                      @Param("endTime") Date endTime);

    /*
     * 指定部门的下属部门(不包含本部门) ISSUE列表
     * */
    List<IssueRecord> kidsIssueDetails(Page<IssueRecord> page,
                                             @Param("record") IssueRecord record,
                                             @Param("orgId") Long orgId,
                                             @Param("startTime") Date startTime,
                                             @Param("endTime") Date endTime);

    /*
     * 我处理的 ISSUE 列表
     * */
    List<IssueRecord> ownerIssueDetails(Page<IssueRecord> page,
                                       @Param("record") IssueRecord record,
                                       @Param("userId") Long userId,
                                       @Param("startTime") Date startTime,
                                       @Param("endTime") Date endTime);

    /*
     * OPEN ISSUE 列表
     * */
    List<IssueRecord> openIssue(Page<IssueRecord> page,
                                        @Param("record") IssueRecord record,
                                        @Param("startTime") Date startTime,
                                        @Param("endTime") Date endTime);

    /*
    * ISSUE 详情，包括 ISSUR 处理的  NOTE等所有的关于该 ISSUE 的所有的信息
    * */
    IssueModel issueDetails(@Param("issueId") Long issueId);
}