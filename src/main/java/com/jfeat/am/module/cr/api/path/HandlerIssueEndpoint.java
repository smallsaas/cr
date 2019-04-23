package com.jfeat.am.module.cr.api.path;

import com.jfeat.am.module.cr.services.bean.HandlerRequest;
import com.jfeat.am.module.cr.services.domain.service.IssueService;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api("handler issue")
@RequestMapping("/api/issue/issues")
public class HandlerIssueEndpoint {

    @Resource
    IssueService issueService;

    @ApiOperation("指派ISSUE给指定的开发人员")
    @PostMapping("/{id}/action/assign")
    public Tip assignIssue(@PathVariable Long issueId, @RequestBody HandlerRequest request) {
        return SuccessTip.create(issueService.assignIssue(issueId,request));
    }

    @ApiOperation("关闭ISSUE")
    @PostMapping("/{id}/action/closed")
    public Tip closedIssue(@PathVariable Long issueId, @RequestBody HandlerRequest request) {
        return SuccessTip.create(issueService.closedIssue(issueId,request));
    }

    @ApiOperation("未复现ISSUE")
    @PostMapping("/{id}/action/repetition")
    public Tip repetitionErrorIssue(@PathVariable Long issueId, @RequestBody HandlerRequest request) {
        return SuccessTip.create(issueService.repetitionErrorIssue(issueId,request));
    }

    @ApiOperation("修复ISSUE")
    @PostMapping("/{id}/action/fixed")
    public Tip fixedErrorIssue(@PathVariable Long issueId, @RequestBody HandlerRequest request) {
        return SuccessTip.create(issueService.fixedIssue(issueId,request));
    }

    @ApiOperation("完成ISSUE")
    @PostMapping("/{id}/action/done")
    public Tip doneIssue(@PathVariable Long issueId, @RequestBody HandlerRequest request) {
        return SuccessTip.create(issueService.doneIssue(issueId,request));
    }

    @ApiOperation("转移，基本业务为指派修复对象出错(如前端的问题，提交到了后端)")
    @PostMapping("/{id}/action/transfer")
    public Tip transferIssue(@PathVariable Long issueId, @RequestBody HandlerRequest request) {
        return SuccessTip.create(issueService.transferIssue(issueId,request));
    }


}
