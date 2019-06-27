package com.jfeat.am.module.cr.api.mine;


import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.cr.services.domain.dao.MineIssueDao;
import com.jfeat.am.module.cr.services.domain.model.IssueRecord;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@Api("MINE ISSUE")
@RequestMapping("/api/mine")
public class MineIssueEndpoint {



    @Resource
    MineIssueDao mineIssueDao;


    @ApiOperation(value = "我的 ISSUE列表", response = IssueRecord.class)
    @GetMapping("/issues")
    public Tip ownerIssueDetails(Page<IssueRecord> page,
                                 @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                 @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                 @RequestParam(name = "path", required = false) String search,
                                 @RequestParam(name = "id", required = false) Long id,
                                 @RequestParam(name = "createBy", required = false) Long createBy,
                                 @RequestParam(name = "createByName", required = false) String createByName,
                                 @RequestParam(name = "title", required = false) String title,
                                 @RequestParam(name = "note", required = false) String note,
                                 @RequestParam(name = "priority", required = false) Integer priority,
                                 @RequestParam(name = "imageUrl", required = false) String imageUrl,
                                 @RequestParam(name = "attachment", required = false) String attachment,
                                 @RequestParam(name = "status", required = false) String status,
                                 @RequestParam(name = "moduleName", required = false) String moduleName,
                                 @RequestParam(name = "orgName", required = false) String orgName,
                                 @RequestParam(name = "projectName", required = false) String projectName,
                                 @RequestParam(name = "orgName", required = false) String ownerName,
                                 @RequestParam(name = "createTime", required = false) Date[] createTime,
                                 @RequestParam(name = "orderBy", required = false) String orderBy,
                                 @RequestParam(name = "sort", required = false) String sort) {
        if (orderBy != null && orderBy.length() > 0) {
            if (sort != null && sort.length() > 0) {
                String pattern = "(ASC|DESC|asc|desc)";
                if (!sort.matches(pattern)) {
                    throw new BusinessException(BusinessCode.BadRequest.getCode(), "sort must be ASC or DESC");//此处异常类型根据实际情况而定
                }
            } else {
                sort = "ASC";
            }
            orderBy = "`" + orderBy + "`" + " " + sort;
        }
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        Date startTime = (createTime!=null && createTime.length == 2)? createTime [0] : null;
        Date endTime = (createTime!=null && createTime.length == 2)? createTime [1] : null;


        IssueRecord record = new IssueRecord();
        record.setModuleName(moduleName);
        record.setOrgName(orgName);
        record.setOwnerName(ownerName);
        record.setProjectName(projectName);
        record.setId(id);
        record.setCreateBy(createBy);
        record.setCreateByName(createByName);
        record.setTitle(title);
        record.setNote(note);
        record.setPriority(priority);
        record.setImageUrl(imageUrl);
        record.setAttachment(attachment);
        record.setStatus(status);
        page.setRecords(mineIssueDao.ownerIssueDetails(page, record,JWTKit.getUserId(), startTime,endTime));
        return SuccessTip.create(page);
    }

    @GetMapping("/issues/originator")
    public Tip originatorIssue(Page<IssueRecord> page,
                         @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                         @RequestParam(name = "path", required = false) String search,
                         @RequestParam(name = "id", required = false) Long id,
                         @RequestParam(name = "createBy", required = false) Long createBy,
                         @RequestParam(name = "createByName", required = false) String createByName,
                         @RequestParam(name = "title", required = false) String title,
                         @RequestParam(name = "note", required = false) String note,
                         @RequestParam(name = "priority", required = false) Integer priority,
                         @RequestParam(name = "imageUrl", required = false) String imageUrl,
                         @RequestParam(name = "attachment", required = false) String attachment,
                         @RequestParam(name = "status", required = false) String status,
                         @RequestParam(name = "moduleName", required = false) String moduleName,
                         @RequestParam(name = "orgName", required = false) String orgName,
                         @RequestParam(name = "projectName", required = false) String projectName,
                         @RequestParam(name = "orgName", required = false) String ownerName,
                         @RequestParam(name = "createTime", required = false) Date[] createTime,
                         @RequestParam(name = "orderBy", required = false) String orderBy,
                         @RequestParam(name = "sort", required = false) String sort) {
        if (orderBy != null && orderBy.length() > 0) {
            if (sort != null && sort.length() > 0) {
                String pattern = "(ASC|DESC|asc|desc)";
                if (!sort.matches(pattern)) {
                    throw new BusinessException(BusinessCode.BadRequest.getCode(), "sort must be ASC or DESC");//此处异常类型根据实际情况而定
                }
            } else {
                sort = "ASC";
            }
            orderBy = "`" + orderBy + "`" + " " + sort;
        }
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        Date startTime = (createTime!=null && createTime.length == 2)? createTime [0] : null;
        Date endTime = (createTime!=null && createTime.length == 2)? createTime [1] : null;

        IssueRecord record = new IssueRecord();
        record.setModuleName(moduleName);
        record.setOrgName(orgName);
        record.setOwnerName(ownerName);
        record.setProjectName(projectName);
        record.setId(id);
        record.setCreateBy(createBy);
        record.setCreateByName(createByName);
        record.setTitle(title);
        record.setNote(note);
        record.setPriority(priority);
        record.setImageUrl(imageUrl);
        record.setAttachment(attachment);
        record.setStatus(status);
        page.setRecords(mineIssueDao.mineCreateIssue(page, JWTKit.getUserId(),record, startTime,endTime));
        return SuccessTip.create(page);
    }

    @GetMapping("/issues/participate")
    public Tip openIssue(Page<IssueRecord> page,
                         @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                         @RequestParam(name = "path", required = false) String search,
                         @RequestParam(name = "id", required = false) Long id,
                         @RequestParam(name = "createBy", required = false) Long createBy,
                         @RequestParam(name = "createByName", required = false) String createByName,
                         @RequestParam(name = "title", required = false) String title,
                         @RequestParam(name = "note", required = false) String note,
                         @RequestParam(name = "priority", required = false) Integer priority,
                         @RequestParam(name = "imageUrl", required = false) String imageUrl,
                         @RequestParam(name = "attachment", required = false) String attachment,
                         @RequestParam(name = "status", required = false) String status,
                         @RequestParam(name = "moduleName", required = false) String moduleName,
                         @RequestParam(name = "orgName", required = false) String orgName,
                         @RequestParam(name = "projectName", required = false) String projectName,
                         @RequestParam(name = "orgName", required = false) String ownerName,
                         @RequestParam(name = "createTime", required = false) Date[] createTime,
                         @RequestParam(name = "orderBy", required = false) String orderBy,
                         @RequestParam(name = "sort", required = false) String sort) {
        if (orderBy != null && orderBy.length() > 0) {
            if (sort != null && sort.length() > 0) {
                String pattern = "(ASC|DESC|asc|desc)";
                if (!sort.matches(pattern)) {
                    throw new BusinessException(BusinessCode.BadRequest.getCode(), "sort must be ASC or DESC");//此处异常类型根据实际情况而定
                }
            } else {
                sort = "ASC";
            }
            orderBy = "`" + orderBy + "`" + " " + sort;
        }
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        Date startTime = (createTime!=null && createTime.length == 2)? createTime [0] : null;
        Date endTime = (createTime!=null && createTime.length == 2)? createTime [1] : null;

        IssueRecord record = new IssueRecord();
        record.setModuleName(moduleName);
        record.setOrgName(orgName);
        record.setOwnerName(ownerName);
        record.setProjectName(projectName);
        record.setId(id);
        record.setCreateBy(createBy);
        record.setCreateByName(createByName);
        record.setTitle(title);
        record.setNote(note);
        record.setPriority(priority);
        record.setImageUrl(imageUrl);
        record.setAttachment(attachment);
        record.setStatus(status);
        page.setRecords(mineIssueDao.mineParticipateIssue(page, JWTKit.getUserId(),record, startTime,endTime));
        return SuccessTip.create(page);
    }


    // 待验收
    @GetMapping("/issues/verified")
    public Tip verifiedIssue(Page<IssueRecord> page,
                         @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                         @RequestParam(name = "path", required = false) String search,
                         @RequestParam(name = "id", required = false) Long id,
                         @RequestParam(name = "createBy", required = false) Long createBy,
                         @RequestParam(name = "createByName", required = false) String createByName,
                         @RequestParam(name = "title", required = false) String title,
                         @RequestParam(name = "note", required = false) String note,
                         @RequestParam(name = "priority", required = false) Integer priority,
                         @RequestParam(name = "imageUrl", required = false) String imageUrl,
                         @RequestParam(name = "attachment", required = false) String attachment,
                         @RequestParam(name = "status", required = false) String status,
                         @RequestParam(name = "moduleName", required = false) String moduleName,
                         @RequestParam(name = "orgName", required = false) String orgName,
                         @RequestParam(name = "projectName", required = false) String projectName,
                         @RequestParam(name = "orgName", required = false) String ownerName,
                         @RequestParam(name = "createTime", required = false) Date[] createTime,
                         @RequestParam(name = "orderBy", required = false) String orderBy,
                         @RequestParam(name = "sort", required = false) String sort) {
        if (orderBy != null && orderBy.length() > 0) {
            if (sort != null && sort.length() > 0) {
                String pattern = "(ASC|DESC|asc|desc)";
                if (!sort.matches(pattern)) {
                    throw new BusinessException(BusinessCode.BadRequest.getCode(), "sort must be ASC or DESC");//此处异常类型根据实际情况而定
                }
            } else {
                sort = "ASC";
            }
            orderBy = "`" + orderBy + "`" + " " + sort;
        }
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        Date startTime = (createTime!=null && createTime.length == 2)? createTime [0] : null;
        Date endTime = (createTime!=null && createTime.length == 2)? createTime [1] : null;

        IssueRecord record = new IssueRecord();
        record.setModuleName(moduleName);
        record.setOrgName(orgName);
        record.setOwnerName(ownerName);
        record.setProjectName(projectName);
        record.setId(id);
        record.setCreateBy(createBy);
        record.setCreateByName(createByName);
        record.setTitle(title);
        record.setNote(note);
        record.setPriority(priority);
        record.setImageUrl(imageUrl);
        record.setAttachment(attachment);
        record.setStatus(status);
        page.setRecords(mineIssueDao.mineParticipateIssue(page, JWTKit.getUserId(),record, startTime,endTime));
        return SuccessTip.create(page);
    }


}
