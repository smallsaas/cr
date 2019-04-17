package com.jfeat.am.module.cr.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.cr.services.domain.model.ProjectModel;
import com.jfeat.am.module.cr.services.persistence.model.Project;
import com.jfeat.am.module.cr.services.persistence.dao.ProjectMapper;
import com.jfeat.am.module.cr.services.persistence.dao.ModuleMapper;
import com.jfeat.am.module.cr.services.persistence.model.Module;


import com.jfeat.am.module.cr.services.crud.service.CRUDProjectService;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.crud.plus.impl.CRUDServiceOverModelImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * implementation
 * </p>
 * CRUDProjectService
 *
 * @author Code Generator
 * @since 2019-04-08
 */

@Service
public class CRUDProjectServiceImpl extends CRUDServiceOverModelImpl<Project, ProjectModel> implements CRUDProjectService {


    @Resource
    protected ProjectMapper projectMapper;


    @Override
    protected BaseMapper<Project> getMasterMapper() {
        return projectMapper;
    }

    @Override
    protected Class<Project> masterClassName() {
        return Project.class;
    }

    @Override
    protected Class<ProjectModel> modelClassName() {
        return ProjectModel.class;
    }


    @Resource
    private ModuleMapper moduleMapper;

    @Deprecated
    private final static String moduleFieldName = "project_id";

    private final static String moduleKeyName = "modules";

    @Override
    protected String[] slaveFieldNames() {
        return new String[]{
                moduleKeyName

        };
    }

    @Override
    protected FIELD onSlaveFieldItem(String field) {
        if (field.compareTo(moduleKeyName) == 0) {
            FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(moduleFieldName);
            _field.setItemClassName(Module.class);
            _field.setItemMapper(moduleMapper);
            return _field;
        }

        throw new BusinessException(BusinessCode.BadRequest);
    }


    @Override
    protected String[] childFieldNames() {
        return new String[]{
        };
    }

    @Override
    protected FIELD onChildFieldItem(String field) {

        throw new BusinessException(BusinessCode.BadRequest);
    }


}


