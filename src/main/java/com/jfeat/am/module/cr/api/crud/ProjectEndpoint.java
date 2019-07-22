package com.jfeat.am.module.cr.api.crud;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.cr.constant.CrPermission;
import com.jfeat.am.module.cr.services.domain.dao.QueryProjectDao;
import com.jfeat.am.module.cr.services.domain.model.ProjectModel;
import com.jfeat.am.module.cr.services.domain.model.ProjectRecord;
import com.jfeat.am.module.cr.services.domain.service.ProjectService;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.common.annotation.Permission;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.ApiOperation;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

@RequestMapping("/api/crud/projects")
public class ProjectEndpoint {


    @Resource
    ProjectService projectService;

    @Resource
    QueryProjectDao queryProjectDao;

    @BusinessLog(name = "Project", value = "create Project")
    @PostMapping
    @ApiOperation(value = "新建 Project", response = ProjectModel.class)
    @Permission(CrPermission.PROJECT_EDIT)
    public Tip createProject(@RequestBody ProjectModel entity) {

        Integer affected = 0;
        try {
            affected = projectService.createMaster(entity, null, null, null);
            ;

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @BusinessLog(name = "Project", value = "查看 Project")
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 Project", response = ProjectModel.class)
    @Permission(CrPermission.PROJECT_VIEW)
    public Tip getProject(@PathVariable Long id) {
        return SuccessTip.create(projectService.retrieveMaster(id, null, null, null).toJSONObject());
    }

    @BusinessLog(name = "Project", value = "update Project")
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 Project", response = ProjectModel.class)
    @Permission(CrPermission.PROJECT_EDIT)
    public Tip updateProject(@PathVariable Long id, @RequestBody ProjectModel entity) {
        entity.setId(id);
        return SuccessTip.create(projectService.updateMaster(entity, null, null, null));
    }

    @BusinessLog(name = "Project", value = "delete Project")
    @DeleteMapping("/{id}")
    @ApiOperation("删除 Project")
    @Permission(CrPermission.PROJECT_EDIT)
    public Tip deleteProject(@PathVariable Long id) {
        return SuccessTip.create(projectService.deleteMaster(id));
    }

    @BusinessLog(name = "Project", value = "delete Project")
    @ApiOperation(value = "Project 列表信息", response = ProjectRecord.class)
    @GetMapping
    @Permission(CrPermission.PROJECT_VIEW)
    public Tip queryProjects(Page<ProjectRecord> page,
                             @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                             @RequestParam(name = "path", required = false) String search,
                             @RequestParam(name = "id", required = false) Long id,
                             @RequestParam(name = "projectName", required = false) String projectName,
                             @RequestParam(name = "note", required = false) String note,
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

        ProjectRecord record = new ProjectRecord();
        record.setId(id);
        record.setOrgId(JWTKit.getOrgId());
        record.setProjectName(projectName);
        record.setNote(note);
        record.setCreateTime(createTime);

        page.setRecords(queryProjectDao.findProjectPage(page, record, search, orderBy));

        return SuccessTip.create(page);
    }


}
