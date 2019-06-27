package com.jfeat.am.module.cr.services.domain.service;

import com.jfeat.am.module.cr.services.bean.HandlerRequest;
import com.jfeat.am.module.cr.services.crud.service.CRUDIssueService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by vincent on 2017/10/19.
 */
public interface IssueService extends CRUDIssueService{

    Integer assignIssue(Long issueId,HandlerRequest request);

    Integer closedIssue(Long issueId,HandlerRequest request);

    Integer repetitionErrorIssue(Long issueId,HandlerRequest request);

    Integer doneIssue(Long issueId,HandlerRequest request);

    Integer transferIssue(Long issueId,HandlerRequest request);

    Integer fixedIssue( Long issueId,HandlerRequest request);

}