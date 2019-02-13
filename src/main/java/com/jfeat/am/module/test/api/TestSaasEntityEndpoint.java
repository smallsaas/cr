package com.jfeat.am.module.test.api;


import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.module.test.services.domain.dao.QueryTestSaasEntityDao;
import com.jfeat.am.module.test.services.domain.model.TestSaasEntityRecord;
import com.jfeat.am.module.test.services.domain.service.TestSaasEntityService;
import com.jfeat.am.module.test.services.gen.persistence.model.TestSaasEntity;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;


/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2019-02-13
 */
@RestController

@Api("TestSaasEntity")
@RequestMapping("/api/test/entity")
public class TestSaasEntityEndpoint {

    @Resource
    TestSaasEntityService testSaasEntityService;

    @Resource
    QueryTestSaasEntityDao queryTestSaasEntityDao;

    @BusinessLog(name = "TestSaasEntity", value = "create TestSaasEntity")
    @PostMapping
    @ApiOperation(value = "新建 TestSaasEntity", response = TestSaasEntity.class)
    public Tip createTestSaasEntity(@RequestBody TestSaasEntity entity) {

        Integer affected = 0;
        try {
            affected = testSaasEntityService.createMaster(entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @BusinessLog(name = "TestSaasEntity", value = "查看 TestSaasEntity")
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 TestSaasEntity", response = TestSaasEntity.class)
    public Tip getTestSaasEntity(@PathVariable Long id) {
        return SuccessTip.create(testSaasEntityService.retrieveMaster(id));
    }

    @BusinessLog(name = "TestSaasEntity", value = "update TestSaasEntity")
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 TestSaasEntity", response = TestSaasEntity.class)
    public Tip updateTestSaasEntity(@PathVariable Long id, @RequestBody TestSaasEntity entity) {
        entity.setId(id);
        return SuccessTip.create(testSaasEntityService.updateMaster(entity));
    }

    @BusinessLog(name = "TestSaasEntity", value = "delete TestSaasEntity")
    @DeleteMapping("/{id}")
    @ApiOperation("删除 TestSaasEntity")
    public Tip deleteTestSaasEntity(@PathVariable Long id) {
        return SuccessTip.create(testSaasEntityService.deleteMaster(id));
    }

    @BusinessLog(name = "TestSaasEntity", value = "delete TestSaasEntity")
    @ApiOperation(value = "TestSaasEntity 列表信息", response = TestSaasEntityRecord.class)
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "search", dataType = "String"),
            @ApiImplicitParam(name = "id", dataType = "Long"),
            @ApiImplicitParam(name = "name", dataType = "String"),
            @ApiImplicitParam(name = "orgId", dataType = "Long"),
            @ApiImplicitParam(name = "status", dataType = "String"),
            @ApiImplicitParam(name = "createdTime", dataType = "Date"),
            @ApiImplicitParam(name = "orderBy", dataType = "String"),
            @ApiImplicitParam(name = "sort", dataType = "String")
    })
    public Tip queryTestSaasEntityies(Page<TestSaasEntityRecord> page,
                                      @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                      @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                      @RequestParam(name = "search", required = false) String search,
                                      @RequestParam(name = "id", required = false) Long id,
                                      @RequestParam(name = "name", required = false) String name,
                                      @RequestParam(name = "orgId", required = false) Long orgId,
                                      @RequestParam(name = "status", required = false) String status,
                                      @RequestParam(name = "createdTime", required = false) Date createdTime,
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

        TestSaasEntityRecord record = new TestSaasEntityRecord();
        record.setId(id);
        record.setName(name);
        record.setOrgId(orgId);
        record.setStatus(status);
        record.setCreatedTime(createdTime);
        page.setRecords(queryTestSaasEntityDao.findTestSaasEntityPage(page, record, search, orderBy));

        return SuccessTip.create(page);
    }
}
