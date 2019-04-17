package com.jfeat.am.module.cr.api.crud;

import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.cr.services.crud.filter.IssueFilter;
import com.jfeat.am.module.cr.services.persistence.model.Issue;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.crud.plus.CRUDFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.dao.DuplicateKeyException;
import com.jfeat.am.module.cr.services.domain.dao.QueryIssueDao;


import com.jfeat.am.module.cr.services.domain.service.IssueService;
import com.jfeat.am.module.cr.services.domain.model.IssueRecord;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;


/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2019-04-08
 */
@RestController

@RequestMapping("/api/cr/issues")
public class IssueEndpoint{


    @Resource
    IssueService issueService;

    @Resource
    QueryIssueDao queryIssueDao;

    @BusinessLog(name = "Issue", value = "create Issue")
    @PostMapping
    @ApiOperation(value = "新建 Issue", response = Issue.class)
    public Tip createIssue(@RequestBody Issue entity) {

        Integer affected = 0;
        try {
            entity.setCreateBy(JWTKit.getUserId());
            entity.setCreateByName(JWTKit.getAccount());
            affected += issueService.createMaster(entity);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }
        return SuccessTip.create(affected);
    }

    @BusinessLog(name = "Issue", value = "查看 Issue")
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 Issue", response = Issue.class)
    public Tip getIssue(@PathVariable Long id) {
        return SuccessTip.create(issueService.retrieveMaster(id));
    }

    @BusinessLog(name = "Issue", value = "update Issue")
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 Issue", response = Issue.class)
    public Tip updateIssue(@PathVariable Long id, @RequestBody Issue entity) {
        entity.setId(id);
        IssueFilter filter = new IssueFilter();
        filter.ignore(false);
        return SuccessTip.create(issueService.updateMaster(entity,filter));
    }

    @BusinessLog(name = "Issue", value = "delete Issue")
    @DeleteMapping("/{id}")
    @ApiOperation("删除 Issue")
    public Tip deleteIssue(@PathVariable Long id) {
        return SuccessTip.create(issueService.deleteMaster(id));
    }

    @BusinessLog(name = "Issue", value = "delete Issue")
    @ApiOperation(value = "Issue 列表信息", response = IssueRecord.class)
    @GetMapping
    public Tip queryIssues(Page<IssueRecord> page,
                           @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                           @RequestParam(name = "search", required = false) String search,
                           @RequestParam(name = "id", required = false) Long id,
                           @RequestParam(name = "moduleId", required = false) Long moduleId,
                           @RequestParam(name = "orgId", required = false) Long orgId,
                           @RequestParam(name = "createBy", required = false) Long createBy,
                           @RequestParam(name = "createByName", required = false) String createByName,
                           @RequestParam(name = "title", required = false) String title,
                           @RequestParam(name = "note", required = false) String note,
                           @RequestParam(name = "priority", required = false) Integer priority,
                           @RequestParam(name = "imageUrl", required = false) String imageUrl,
                           @RequestParam(name = "attachment", required = false) String attachment,
                           @RequestParam(name = "status", required = false) String status,
                           @RequestParam(name = "ownerId", required = false) Long ownerId,
                           @RequestParam(name = "createTime", required = false) Date createTime,
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

        IssueRecord record = new IssueRecord();
        record.setId(id);
        record.setModuleId(moduleId);
        record.setOrgId(orgId);
        record.setCreateBy(createBy);
        record.setCreateByName(createByName);
        record.setTitle(title);
        record.setNote(note);
        record.setPriority(priority);
        record.setImageUrl(imageUrl);
        record.setAttachment(attachment);
        record.setStatus(status);
        record.setOwnerId(ownerId);
        record.setCreateTime(createTime);
        page.setRecords(queryIssueDao.findIssuePage(page, record, search, orderBy));
        return SuccessTip.create(page);
    }


}
