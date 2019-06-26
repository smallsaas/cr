package com.jfeat.am.module.cr.api.path;

import com.jfeat.am.module.cr.services.bean.HandlerRequest;
import com.jfeat.am.module.cr.services.domain.service.HandlerIssueService;
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
    @Resource
    HandlerIssueService handlerIssueService;

    @ApiOperation("指派ISSUE给指定的开发人员")
    @PostMapping("/{id}/action/assign")
    public Tip assignIssue(@PathVariable Long issueId, @RequestBody HandlerRequest request) {
        request.setIssueId(issueId);
        return SuccessTip.create(handlerIssueService.assign(request));
    }

    @ApiOperation("关闭ISSUE")
    @PostMapping("/{id}/action/closed")
    public Tip closedIssue(@PathVariable Long issueId, @RequestBody HandlerRequest request) {
        request.setIssueId(issueId);

        return SuccessTip.create(handlerIssueService.closed(request));
    }

    @ApiOperation("完成ISSUE")
    @PostMapping("/{id}/action/done")
    public Tip doneIssue(@PathVariable Long issueId, @RequestBody HandlerRequest request) {
        request.setIssueId(issueId);

        return SuccessTip.create(handlerIssueService.done(request));
    }

    @ApiOperation("重启ISSUE")
    @PostMapping("/{id}/action/restart")
    public Tip restartIssue(@PathVariable Long issueId, @RequestBody HandlerRequest request) {
        request.setIssueId(issueId);

        return SuccessTip.create(handlerIssueService.restart(request));
    }

    @ApiOperation("未复现")
    @PostMapping("/{id}/action/nr")
    public Tip nrIssue(@PathVariable Long issueId, @RequestBody HandlerRequest request) {
        request.setIssueId(issueId);

        return SuccessTip.create(handlerIssueService.nr(request));
    }

    @ApiOperation("无修复计划")
    @PostMapping("/{id}/action/nfp")
    public Tip nfpIssue(@PathVariable Long issueId, @RequestBody HandlerRequest request) {
        request.setIssueId(issueId);

        return SuccessTip.create(handlerIssueService.nfp(request));
    }

    @ApiOperation("重复的ISSUE")
    @PostMapping("/{id}/action/deplicate")
    public Tip deplicateIssue(@PathVariable Long issueId, @RequestBody HandlerRequest request) {
        request.setIssueId(issueId);

        return SuccessTip.create(handlerIssueService.deplicate(request));
    }

    @ApiOperation("未发现该issue的问题，一般指两边业务理解的不同导致")
    @PostMapping("/{id}/action/ntf")
    public Tip ntfIssue(@PathVariable Long issueId, @RequestBody HandlerRequest request) {
        request.setIssueId(issueId);

        return SuccessTip.create(handlerIssueService.ntf(request));
    }


    @ApiOperation("修复ISSUE")
    @PostMapping("/{id}/action/fixed")
    public Tip fixedErrorIssue(@PathVariable Long issueId, @RequestBody HandlerRequest request) {
        request.setIssueId(issueId);

        return SuccessTip.create(handlerIssueService.fixed(request));
    }


    @ApiOperation("不是自己处理的范畴 同时不知道谁处理，直接 route out")
    @PostMapping("/{id}/action/route")
    public Tip routeErrorIssue(@PathVariable Long issueId, @RequestBody HandlerRequest request) {
        request.setIssueId(issueId);

        return SuccessTip.create(handlerIssueService.route(request));
    }




    @Deprecated
    @ApiOperation("未复现ISSUE")
    @PostMapping("/{id}/action/repetition")
    public Tip repetitionErrorIssue(@PathVariable Long issueId, @RequestBody HandlerRequest request) {
        return SuccessTip.create(issueService.repetitionErrorIssue(issueId,request));
    }


    @Deprecated
    @ApiOperation("转移，基本业务为指派修复对象出错(如前端的问题，提交到了后端)")
    @PostMapping("/{id}/action/transfer")
    public Tip transferIssue(@PathVariable Long issueId, @RequestBody HandlerRequest request) {
        return SuccessTip.create(issueService.transferIssue(issueId,request));
    }


}
