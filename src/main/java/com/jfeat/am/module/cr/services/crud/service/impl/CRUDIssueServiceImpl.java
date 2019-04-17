package com.jfeat.am.module.cr.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.cr.services.persistence.model.Issue;
import com.jfeat.am.module.cr.services.persistence.dao.IssueMapper;
import com.jfeat.am.module.cr.services.persistence.dao.ModuleMapper;
import com.jfeat.am.module.cr.services.persistence.model.Module;


import com.jfeat.am.module.cr.services.crud.service.CRUDIssueService;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDIssueService
 * @author Code Generator
 * @since 2019-04-08
 */

@Service("CRUDIssueService")
public class CRUDIssueServiceImpl  extends CRUDServiceOnlyImpl<Issue> implements CRUDIssueService {
        @Resource
        protected IssueMapper issueMapper;

        @Override
        protected BaseMapper<Issue> getMasterMapper() {
                return issueMapper;
        }







}


