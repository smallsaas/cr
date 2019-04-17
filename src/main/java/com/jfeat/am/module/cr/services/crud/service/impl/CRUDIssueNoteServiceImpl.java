package com.jfeat.am.module.cr.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.cr.services.persistence.model.IssueNote;
import com.jfeat.am.module.cr.services.persistence.dao.IssueNoteMapper;
import com.jfeat.am.module.cr.services.persistence.dao.ModuleMapper;
import com.jfeat.am.module.cr.services.persistence.model.Module;


import com.jfeat.am.module.cr.services.crud.service.CRUDIssueNoteService;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDIssueNoteService
 * @author Code Generator
 * @since 2019-04-08
 */

@Service
public class CRUDIssueNoteServiceImpl  extends CRUDServiceOnlyImpl<IssueNote> implements CRUDIssueNoteService {





        @Resource
        protected IssueNoteMapper issueNoteMapper;

        @Override
        protected BaseMapper<IssueNote> getMasterMapper() {
                return issueNoteMapper;
        }







}


