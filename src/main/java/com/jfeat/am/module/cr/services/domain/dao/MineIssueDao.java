package com.jfeat.am.module.cr.services.domain.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.cr.services.domain.model.IssueRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MineIssueDao {




    /*
     * 我创建的ISSUE
     * */
    List<IssueRecord> mineCreateIssue(Page<IssueRecord> page,
                                @Param("userId")Long userId,
                                @Param("record") IssueRecord record,
                                @Param("startTime") Date startTime,
                                @Param("endTime") Date endTime);

    /*
     * 我参与过的ISSUE
     * */
    List<IssueRecord> mineParticipateIssue(Page<IssueRecord> page,
                                      @Param("userId")Long userId,
                                      @Param("record") IssueRecord record,
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

}
