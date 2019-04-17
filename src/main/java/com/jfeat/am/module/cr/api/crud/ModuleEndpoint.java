package com.jfeat.am.module.cr.api.crud;

import com.jfeat.am.module.cr.services.persistence.model.Module;
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
import com.jfeat.am.module.cr.services.domain.dao.QueryModuleDao;
import com.jfeat.am.module.log.annotation.BusinessLog;

import com.jfeat.am.module.cr.services.domain.service.ModuleService;
import com.jfeat.am.module.cr.services.domain.model.ModuleRecord;

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

@RequestMapping("/api/cr/modules")
public class ModuleEndpoint {


    @Resource
    ModuleService moduleService;

    @Resource
    QueryModuleDao queryModuleDao;

    @BusinessLog(name = "Module", value = "create Module")
    @PostMapping
    @ApiOperation(value = "新建 Module", response = Module.class)
    public Tip createModule(@RequestBody Module entity) {

        Integer affected = 0;
        try {
            affected = moduleService.createMaster(entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @BusinessLog(name = "Module", value = "查看 Module")
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 Module", response = Module.class)
    public Tip getModule(@PathVariable Long id) {
        return SuccessTip.create(moduleService.retrieveMaster(id));
    }

    @BusinessLog(name = "Module", value = "update Module")
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 Module", response = Module.class)
    public Tip updateModule(@PathVariable Long id, @RequestBody Module entity) {
        entity.setId(id);
        return SuccessTip.create(moduleService.updateMaster(entity));
    }

    @BusinessLog(name = "Module", value = "delete Module")
    @DeleteMapping("/{id}")
    @ApiOperation("删除 Module")
    public Tip deleteModule(@PathVariable Long id) {
        return SuccessTip.create(moduleService.deleteMaster(id));
    }

    @BusinessLog(name = "Module", value = "delete Module")
    @ApiOperation(value = "Module 列表信息", response = ModuleRecord.class)
    @GetMapping
    public Tip queryModules(Page<ModuleRecord> page,
                            @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                            @RequestParam(name = "search", required = false) String search,
                            @RequestParam(name = "id", required = false) Long id,
                            @RequestParam(name = "projectId", required = false) Long projectId,
                            @RequestParam(name = "note", required = false) String note,
                            @RequestParam(name = "moduleName", required = false) String moduleName,
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

        ModuleRecord record = new ModuleRecord();
        record.setId(id);
        record.setProjectId(projectId);
        record.setNote(note);
        record.setModuleName(moduleName);
        record.setCreateTime(createTime);

        page.setRecords(queryModuleDao.findModulePage(page, record, search, orderBy));

        return SuccessTip.create(page);
    }


}
