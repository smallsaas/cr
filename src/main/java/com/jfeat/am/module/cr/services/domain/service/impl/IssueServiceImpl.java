package com.jfeat.am.module.cr.services.domain.service.impl;

import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.cr.services.bean.HandlerRequest;
import com.jfeat.am.module.cr.services.definition.IssueStatus;
import com.jfeat.am.module.cr.services.domain.service.IssueService;

import com.jfeat.am.module.cr.services.crud.service.impl.CRUDIssueServiceImpl;
import com.jfeat.am.module.cr.services.persistence.dao.IssueNoteMapper;
import com.jfeat.am.module.cr.services.persistence.model.Issue;
import com.jfeat.am.module.cr.services.persistence.model.IssueNote;
import com.jfeat.crud.base.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service("IssueService")
public class IssueServiceImpl extends CRUDIssueServiceImpl implements IssueService {

    @Resource
    IssueNoteMapper issueNoteMapper;

    @Transactional
    private Integer handler(Integer handler, Long issueId,HandlerRequest request) {
        int affected = 0;
        // handler 1:指派 2:作废 3:未复现 4:完成 5:转移 6: FIXED
        Issue issue = issueMapper.selectById(issueId);
        String fromStatus = issue.getStatus();
        IssueNote note = new IssueNote();
        note.setHandlerName(JWTKit.getAccount());
        note.setHandlerId(JWTKit.getUserId());
        switch (handler) {
            // 指派 ISSUE
            case 1:
                issue.setStatus(IssueStatus.WIP.toString());
                issue.setOwnerId(request.getOwnerId());
                note.setNote("执行\"指派\"操作:\n" +
                        "指派人:\n" + note.getHandlerName() +
                        "被指派人:\n" + request.getOwnerName() +
                        "。\n"
                        + request.getNote());
                note.setFromStatus(fromStatus);
                note.setToStatus(issue.getStatus());
                break;
            // 作废 ISSUE
            case 2:
                issue.setStatus(IssueStatus.CLOSED.toString());
                note.setNote("执行\"作废\"操作:\n" +
                        "操作人:\n" + note.getHandlerName() +
                        "。\n"
                        + request.getNote());
                note.setFromStatus(fromStatus);
                note.setToStatus(issue.getStatus());
                break;
            // 未复现
            case 3:
                issue.setStatus(IssueStatus.REPETITIONERROR.toString());
                note.setNote("执行\"未复现\"操作:\n" +
                        "操作人:\n" + note.getHandlerName() +
                        "。\n"
                        + request.getNote());
                note.setFromStatus(fromStatus);
                note.setToStatus(issue.getStatus());
                break;
            // 4:完成
            case 4:
                issue.setStatus(IssueStatus.DONE.toString());
                note.setNote("执行\"完成\"操作:\n" +
                        "操作人:\n" + note.getHandlerName() +
                        "。\n"
                        + request.getNote());
                note.setFromStatus(fromStatus);
                note.setToStatus(issue.getStatus());
                break;
            // 4:转移
            case 5:
                issue.setStatus(IssueStatus.TRANSFER.toString());
                note.setNote("执行\"转移\"操作:\n" +
                        "操作人:\n" + note.getHandlerName() +
                        "。\n"
                        + request.getNote());
                note.setFromStatus(fromStatus);
                note.setToStatus(issue.getStatus());
                break;
            // 6:FIXED
            case 6:
                issue.setStatus(IssueStatus.FIXED.toString());
                note.setNote("执行\"修复\"操作:\n" +
                        "操作人:\n" + note.getHandlerName() +
                        "。\n"
                        + request.getNote());
                note.setFromStatus(fromStatus);
                note.setToStatus(issue.getStatus());
                break;
            default:
                throw new BusinessException(5000, "错误的状态处理码!");
        }
        affected += issueNoteMapper.insert(note);
        affected += issueMapper.updateById(issue);
        return affected;
    }

    public Integer assignIssue( Long issueId,HandlerRequest request) {
        // handler 1:指派 2:作废 3:未复现 4:完成 5:转移
        return handler(1,issueId,request);
    }

    public Integer closedIssue( Long issueId,HandlerRequest request) {
        // handler 1:指派 2:作废 3:未复现 4:完成 5:转移
        return handler(2,issueId,request);
    }

    public Integer repetitionErrorIssue( Long issueId,HandlerRequest request) {
        // handler 1:指派 2:作废 3:未复现 4:完成 5:转移
        return handler(3,issueId,request);
    }

    public Integer doneIssue( Long issueId,HandlerRequest request) {
        // handler 1:指派 2:作废 3:未复现 4:完成 5:转移
        return handler(4,issueId,request);
    }

    public Integer transferIssue( Long issueId,HandlerRequest request) {
        // handler 1:指派 2:作废 3:未复现 4:完成 5:转移
        return handler(5,issueId,request);
    }

    public Integer fixedIssue( Long issueId,HandlerRequest request) {
        // handler 1:指派 2:作废 3:未复现 4:完成 5:转移
        return handler(6,issueId,request);
    }

}
