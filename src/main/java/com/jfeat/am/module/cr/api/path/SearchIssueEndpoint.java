package com.jfeat.am.module.cr.api.path;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.cr.services.domain.dao.QueryIssueDao;
import com.jfeat.am.module.cr.services.domain.model.IssueRecord;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
@Api("各种筛选API")
@RequestMapping("/api/issue/issues/path")
public class SearchIssueEndpoint {


    @Resource
    QueryIssueDao queryIssueDao;

    @ApiOperation(value = "单个项目的 ISSUE列表  参数为 项目的ID", response = IssueRecord.class)
    @GetMapping("/project/{projectId}")
    public Tip queryIssues(Page<IssueRecord> page,
                           @PathVariable Long projectId,
                           @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                           @RequestParam(name = "path", required = false) String search,
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
        page.setRecords(queryIssueDao.projectIssueDetails(page, record,projectId, startTime,endTime));
        return SuccessTip.create(page);
    }


    @ApiOperation(value = "指定模块 ISSUE列表 参数为 木块的ID", response = IssueRecord.class)
    @GetMapping("/module/{moduleId}")
    public Tip moduleIssueDetails(Page<IssueRecord> page,
                           @PathVariable Long moduleId,
                           @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                           @RequestParam(name = "path", required = false) String search,
                           @RequestParam(name = "id", required = false) Long id,
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
        page.setRecords(queryIssueDao.moduleIssueDetails(page, record,moduleId, startTime,endTime));
        return SuccessTip.create(page);
    }

    @ApiOperation(value = "指定部门的 ISSUE列表", response = IssueRecord.class)
    @GetMapping("/org/{orgId}")
    public Tip orgIssueDetails(Page<IssueRecord> page,
                                  @PathVariable Long orgId,
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
                                  @RequestParam(name = "ownerId", required = false) Long ownerId,
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
        record.setOwnerId(ownerId);
        page.setRecords(queryIssueDao.orgIssueDetails(page, record,orgId, startTime,endTime));
        return SuccessTip.create(page);
    }

    @ApiOperation(value = "指定部门的下属部门(包含本部门) ISSUE列表", response = IssueRecord.class)
    @GetMapping("/org/kids/{orgId}")
    public Tip orgAndKidsIssueDetails(Page<IssueRecord> page,
                               @PathVariable Long orgId,
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
                               @RequestParam(name = "ownerId", required = false) Long ownerId,
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
        record.setOwnerId(ownerId);
        page.setRecords(queryIssueDao.orgAndKidsIssueDetails(page, record,orgId, startTime,endTime));
        return SuccessTip.create(page);
    }

    @ApiOperation(value = "指定部门的下属部门(不包含本部门) ISSUE列表", response = IssueRecord.class)
    @GetMapping("/kids/{orgId}")
    public Tip kidsIssueDetails(Page<IssueRecord> page,
                                      @PathVariable Long orgId,
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
                                      @RequestParam(name = "ownerId", required = false) Long ownerId,
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
        record.setOwnerId(ownerId);
        page.setRecords(queryIssueDao.kidsIssueDetails(page, record,orgId, startTime,endTime));
        return SuccessTip.create(page);
    }

    @ApiOperation(value = "我的 ISSUE列表", response = IssueRecord.class)
    @GetMapping("/owner")
    public Tip ownerIssueDetails(Page<IssueRecord> page,
                                @PathVariable Long orgId,
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
        page.setRecords(queryIssueDao.ownerIssueDetails(page, record,JWTKit.getUserId(), startTime,endTime));
        return SuccessTip.create(page);
    }
}
