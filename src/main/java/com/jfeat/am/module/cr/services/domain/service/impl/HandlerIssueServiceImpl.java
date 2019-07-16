package com.jfeat.am.module.cr.services.domain.service.impl;


import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.cr.services.bean.HandlerRequest;
import com.jfeat.am.module.cr.services.definition.IssueStatus;
import com.jfeat.am.module.cr.services.domain.service.HandlerIssueService;
import com.jfeat.am.module.cr.services.persistence.dao.IssueMapper;
import com.jfeat.am.module.cr.services.persistence.dao.IssueNoteMapper;
import com.jfeat.am.module.cr.services.persistence.model.Issue;
import com.jfeat.am.module.cr.services.persistence.model.IssueNote;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service

public class HandlerIssueServiceImpl implements HandlerIssueService {


    @Resource
    IssueMapper issueMapper;
    @Resource
    IssueNoteMapper issueNoteMapper;



    /**
     * 指派某个 Issue
     * 1.自己指派给自己
     * 2.指派给其他同事
     * */
    @Transactional
    public Integer assign(HandlerRequest request){

        int affected = 0;
        Issue issue = issueMapper.selectById(request.getIssueId());
        if (issue==null){
            throw new BusinessException(BusinessCode.FileNotFound);
        }
        IssueNote note = new IssueNote();

        // 如果指派id为空，则默认指派对象为自己
        if (request.getOwnerId()==null||request.getOwnerId()<0){
            issue.setOwnerId(JWTKit.getUserId());
            if (request.getNote()==null||request.getNote().length()<=0){
                note.setNote("执行指派操作:"+JWTKit.getAccount()+" 指派给 "+" 自己");

            }
        }
        if (request.getOwnerId().equals(issue.getOwnerId())){
            throw new BusinessException(5120,"当前处理人已是\""+ request.getOwnerName()+"\",无须重复指派！");
        }

        note.setHandlerId(JWTKit.getUserId());
        note.setHandlerName(JWTKit.getAccount());
        note.setCreateTime(new Date());
        note.setFromStatus(issue.getStatus());
        note.setToStatus(issue.getStatus());
        note.setIssueId(issue.getId());
        note.setNote(request.getNote()+"\n"+"执行\"指派\"操作:"+JWTKit.getAccount()+" 指派给 "+ request.getOwnerName());
        affected += issueNoteMapper.insert(note);

        issue.setOwnerId(request.getOwnerId());
        issueMapper.updateById(issue);
        return affected;
    }

    /**
     * 关闭某个 Issue
     * 1.自己创建-自己关闭原则
     * */
    @Transactional
    public Integer closed(HandlerRequest request){

        int affected = 0;
        Issue issue = issueMapper.selectById(request.getIssueId());
        if (issue==null){
            throw new BusinessException(BusinessCode.FileNotFound);
        }

        if (!issue.getCreateBy().equals(JWTKit.getUserId())){
            throw new BusinessException(5101,"无法执行关闭操作，请联系创建人!");
        }



        IssueNote note = new IssueNote();
        note.setHandlerId(JWTKit.getUserId());
        note.setHandlerName(JWTKit.getAccount());
        note.setCreateTime(new Date());
        note.setFromStatus(issue.getStatus());
        note.setToStatus(IssueStatus.CLOSED.toString());
        note.setIssueId(issue.getId());
        note.setNote(request.getNote()+"\n"+"执行\"关闭\"操作:"+JWTKit.getAccount()+" 关闭该ISSUE。");
        affected += issueNoteMapper.insert(note);

        issue.setStatus(IssueStatus.CLOSED.toString());
        affected += issueMapper.updateById(issue);
        return affected;
    }

    /**
     * 完成某个 Issue
     * 1.自己创建-自己执行完成原则
     * */
    @Transactional
    public Integer done(HandlerRequest request){

        int affected = 0;
        Issue issue = issueMapper.selectById(request.getIssueId());
        if (issue==null){
            throw new BusinessException(BusinessCode.FileNotFound);
        }

        if (!issue.getCreateBy().equals(JWTKit.getUserId())){
            throw new BusinessException(5101,"无法执行\"完成\"操作，请联系创建人!");
        }


        IssueNote note = new IssueNote();
        note.setHandlerId(JWTKit.getUserId());
        note.setHandlerName(JWTKit.getAccount());
        note.setCreateTime(new Date());
        note.setFromStatus(issue.getStatus());
        note.setToStatus(IssueStatus.DONE.toString());
        note.setIssueId(issue.getId());
        note.setNote(request.getNote()+"\n"+"执行\"完成\"操作:"+JWTKit.getAccount()+" 对该ISSUE验收通过。");

        affected += issueNoteMapper.insert(note);

        issue.setStatus(IssueStatus.DONE.toString());
        affected += issueMapper.updateById(issue);

        return affected;
    }

    /**
     * 重启某个 Issue
     * 1.自己创建-直接执行重启操作
     * 2.非自己创建，执行复制重新生成操作 此时该 issue 创建人变成了当前操作者
     * */
    @Transactional
    public Integer restart(HandlerRequest request){

        int affected = 0;
        Issue issue = issueMapper.selectById(request.getIssueId());
        if (issue==null){
            throw new BusinessException(BusinessCode.FileNotFound);
        }

        if (!issue.getCreateBy().equals(JWTKit.getUserId())){
            Issue i = new Issue();
            i.setCreateBy(JWTKit.getUserId());
            i.setCreateByName(JWTKit.getAccount());
            i.setCreateTime(new Date());
            i.setStatus(IssueStatus.OPEN.toString());
            i.setModuleId(issue.getModuleId());
            i.setNote(issue.getNote());
            i.setOrgId(issue.getOrgId());
            i.setPriority(issue.getPriority());
            i.setTitle(issue.getTitle());
            // 该 images 已经舍弃
            i.setImageUrl(issue.getImageUrl());

            affected += issueMapper.insert(i);

            IssueNote note = new IssueNote();
            note.setHandlerId(JWTKit.getUserId());
            note.setHandlerName(JWTKit.getAccount());
            note.setCreateTime(new Date());
            note.setFromStatus(issue.getStatus());
            note.setToStatus(IssueStatus.OPEN.toString());
            note.setIssueId(issue.getId());
            note.setNote(request.getNote()+"\n"+"执行\"重新打开\"操作:"+JWTKit.getAccount()+" 重新(复制)打开。");
            affected += issueNoteMapper.insert(note);

            return affected;
        }else {

            IssueNote note = new IssueNote();
            note.setHandlerId(JWTKit.getUserId());
            note.setHandlerName(JWTKit.getAccount());
            note.setCreateTime(new Date());
            note.setFromStatus(issue.getStatus());
            note.setToStatus(IssueStatus.OPEN.toString());
            note.setIssueId(issue.getId());
            note.setNote(request.getNote()+"\n"+"执行\"完成\"操作:"+JWTKit.getAccount()+" 对该ISSUE验收通过。");
            affected += issueNoteMapper.insert(note);

            issue.setStatus(IssueStatus.OPEN.toString());
            affected += issueMapper.updateById(issue);
            return affected;

        }

    }

    /**
     * 未复现
     * 1.一般来说，指的是开发人员 对ISSUE 执行该操作
     * */
    @Transactional
    public Integer nr(HandlerRequest request){

        int affected = 0;
        Issue issue = issueMapper.selectById(request.getIssueId());
        if (issue==null){
            throw new BusinessException(BusinessCode.FileNotFound);
        }

        IssueNote note = new IssueNote();

        note.setHandlerId(JWTKit.getUserId());
        note.setHandlerName(JWTKit.getAccount());
        note.setCreateTime(new Date());
        note.setFromStatus(issue.getStatus());
        note.setToStatus(IssueStatus.NR.toString());
        note.setIssueId(issue.getId());
        note.setNote(request.getNote()+"\n"+"执行\"无法复现\"操作:"+JWTKit.getAccount()+" 无法复现该ISSUE ");
        affected += issueNoteMapper.insert(note);

        issue.setStatus(IssueStatus.NR.toString());
        affected += issueMapper.updateById(issue);

        return affected;
    }

    /**
     * NFP 无修复计划
     * 1.一般来说，指的是开发人员 对ISSUE 执行该操作
     * */
    @Transactional
    public Integer nfp(HandlerRequest request){

        int affected = 0;
        Issue issue = issueMapper.selectById(request.getIssueId());
        if (issue==null){
            throw new BusinessException(BusinessCode.FileNotFound);
        }

        IssueNote note = new IssueNote();

        note.setHandlerId(JWTKit.getUserId());
        note.setHandlerName(JWTKit.getAccount());
        note.setCreateTime(new Date());
        note.setFromStatus(issue.getStatus());
        note.setToStatus(IssueStatus.NFP.toString());
        note.setIssueId(issue.getId());
        note.setNote(request.getNote()+"\n"+"执行\"NFP\"操作:"+JWTKit.getAccount()+" 无修复计划 ");
        affected += issueNoteMapper.insert(note);

        issue.setStatus(IssueStatus.NFP.toString());
        affected += issueMapper.updateById(issue);

        return affected;
    }

    /**
     * 重复的ISSUE
     * 1.一般来说，指的是开发人员 对ISSUE 执行该操作
     * */
    @Transactional
    public Integer deplicate(HandlerRequest request){

        int affected = 0;
        Issue issue = issueMapper.selectById(request.getIssueId());
        if (issue==null){
            throw new BusinessException(BusinessCode.FileNotFound);
        }

        IssueNote note = new IssueNote();

        note.setHandlerId(JWTKit.getUserId());
        note.setHandlerName(JWTKit.getAccount());
        note.setCreateTime(new Date());
        note.setFromStatus(issue.getStatus());
        note.setToStatus(IssueStatus.DEPLICATE.toString());
        note.setIssueId(issue.getId());
        note.setNote(request.getNote()+"\n"+"执行\"DEPLICATE\"操作:"+JWTKit.getAccount()+" 重复的ISSUE ");
        affected += issueNoteMapper.insert(note);

        issue.setStatus(IssueStatus.DEPLICATE.toString());
        affected += issueMapper.updateById(issue);

        return affected;
    }

    /**
     * 未发现该issue的问题，一般指两边业务理解的不同导致
     * 1.一般来说，指的是开发人员 对ISSUE 执行该操作
     * */
    @Transactional
    public Integer ntf(HandlerRequest request){

        int affected = 0;
        Issue issue = issueMapper.selectById(request.getIssueId());
        if (issue==null){
            throw new BusinessException(BusinessCode.FileNotFound);
        }

        IssueNote note = new IssueNote();

        note.setHandlerId(JWTKit.getUserId());
        note.setHandlerName(JWTKit.getAccount());
        note.setCreateTime(new Date());
        note.setFromStatus(issue.getStatus());
        note.setToStatus(IssueStatus.NTF.toString());
        note.setIssueId(issue.getId());
        note.setNote(request.getNote()+"\n"+"执行\"NTF\"操作:"+JWTKit.getAccount()+" 非程序ISSUE ");
        affected += issueNoteMapper.insert(note);

        issue.setStatus(IssueStatus.NTF.toString());
        affected += issueMapper.updateById(issue);

        return affected;
    }


    /**
     * 修复 ISSUE
     * */
    @Transactional
    public Integer fixed(HandlerRequest request){

        int affected = 0;
        Issue issue = issueMapper.selectById(request.getIssueId());
        if (issue==null){
            throw new BusinessException(BusinessCode.FileNotFound);
        }

        IssueNote note = new IssueNote();

        note.setHandlerId(JWTKit.getUserId());
        note.setHandlerName(JWTKit.getAccount());
        note.setCreateTime(new Date());
        note.setFromStatus(issue.getStatus());
        note.setToStatus(IssueStatus.FIXED.toString());
        note.setIssueId(issue.getId());
        note.setNote(request.getNote()+"\n"+"执行\"FIXED\"操作:"+JWTKit.getAccount()+" ISSUE已修复 ");
        affected += issueNoteMapper.insert(note);

        issue.setStatus(IssueStatus.FIXED.toString());
        affected += issueMapper.updateById(issue);

        return affected;
    }


    /**
     * 不是自己处理的范畴 同时不知道谁处理，直接 route out
     * */
    public Integer route(HandlerRequest request){

        int affected = 0;
        Issue issue = issueMapper.selectById(request.getIssueId());
        if (issue==null){
            throw new BusinessException(BusinessCode.FileNotFound);
        }
        if (!issue.getOwnerId().equals(JWTKit.getUserId())){
            throw new BusinessException(5110,"无法对他人的ISSUE进行ROUTE-OUT操作");
        }

        IssueNote note = new IssueNote();

        note.setHandlerId(JWTKit.getUserId());
        note.setHandlerName(JWTKit.getAccount());
        note.setCreateTime(new Date());
        note.setFromStatus(issue.getStatus());
        note.setToStatus(IssueStatus.OPEN.toString());
        note.setIssueId(issue.getId());
        note.setNote(request.getNote()+"\n"+"执行\"ROUTE_OUT\"操作:"+JWTKit.getAccount()+"  ");
        affected += issueNoteMapper.insert(note);

        issue.setOwnerId(null);
        affected += issueMapper.updateById(issue);

        return affected;
    }




}
