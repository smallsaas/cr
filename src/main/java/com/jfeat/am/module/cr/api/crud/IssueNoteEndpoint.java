package com.jfeat.am.module.cr.api.crud;

import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.cr.services.persistence.model.IssueNote;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
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
import com.jfeat.am.module.cr.services.domain.dao.QueryIssueNoteDao;
import com.jfeat.am.module.log.annotation.BusinessLog;


import com.jfeat.am.module.cr.services.domain.service.IssueNoteService;
import com.jfeat.am.module.cr.services.domain.model.IssueNoteRecord;

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

@RequestMapping("/api/crud/issue/notes")
public class IssueNoteEndpoint {


    @Resource
    IssueNoteService issueNoteService;

    @Resource
    QueryIssueNoteDao queryIssueNoteDao;

    @BusinessLog(name = "IssueNote", value = "create IssueNote")
    @PostMapping
    @ApiOperation(value = "新建 IssueNote", response = IssueNote.class)
    public Tip createIssueNote(@RequestBody IssueNote entity) {
        Integer affected = 0;
        try {
            entity.setHandlerId(JWTKit.getUserId());
            entity.setHandlerName(JWTKit.getAccount());
            affected = issueNoteService.createMaster(entity);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }
        return SuccessTip.create(affected);
    }

    @BusinessLog(name = "IssueNote", value = "查看 IssueNote")
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 IssueNote", response = IssueNote.class)
    public Tip getIssueNote(@PathVariable Long id) {
        return SuccessTip.create(issueNoteService.retrieveMaster(id));
    }

    @BusinessLog(name = "IssueNote", value = "update IssueNote,ISSUE 处理意见不提供修改功能，请前端不要使用修改接口")
    @PutMapping("/{id}")
    @Deprecated
    @ApiOperation(value = "修改 IssueNote", response = IssueNote.class)
    public Tip updateIssueNote(@PathVariable Long id, @RequestBody IssueNote entity) {
        entity.setId(id);
        return SuccessTip.create(issueNoteService.updateMaster(entity));
    }

    @BusinessLog(name = "IssueNote", value = "delete IssueNote")
    @DeleteMapping("/{id}")
    @ApiOperation("删除 IssueNote")
    public Tip deleteIssueNote(@PathVariable Long id) {
        return SuccessTip.create(issueNoteService.deleteMaster(id));
    }

    @BusinessLog(name = "IssueNote", value = "delete IssueNote")
    @ApiOperation(value = "IssueNote 列表信息", response = IssueNoteRecord.class)
    @GetMapping
    public Tip queryIssueNotes(Page<IssueNoteRecord> page,
                               @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                               @RequestParam(name = "path", required = false) String search,
                               @RequestParam(name = "id", required = false) Long id,
                               @RequestParam(name = "issueId", required = false) Long issueId,
                               @RequestParam(name = "handlerId", required = false) Long handlerId,
                               @RequestParam(name = "handlerName", required = false) String handlerName,
                               @RequestParam(name = "fromStatus", required = false) String fromStatus,
                               @RequestParam(name = "toStatus", required = false) String toStatus,
                               @RequestParam(name = "note", required = false) String note,
                               @RequestParam(name = "imageUrl", required = false) String imageUrl,
                               @RequestParam(name = "attachment", required = false) String attachment,
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

        IssueNoteRecord record = new IssueNoteRecord();
        record.setId(id);
        record.setIssueId(issueId);
        record.setHandlerId(handlerId);
        record.setHandlerName(handlerName);
        record.setFromStatus(fromStatus);
        record.setToStatus(toStatus);
        record.setNote(note);
        record.setImageUrl(imageUrl);
        record.setAttachment(attachment);
        record.setCreateTime(createTime);

        page.setRecords(queryIssueNoteDao.findIssueNotePage(page, record, search, orderBy));

        return SuccessTip.create(page);
    }


}
