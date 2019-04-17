package com.jfeat.am.module.cr.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.cr.services.persistence.model.Module;
import com.jfeat.am.module.cr.services.persistence.dao.ModuleMapper;


import com.jfeat.am.module.cr.services.crud.service.CRUDModuleService;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDModuleService
 * @author Code Generator
 * @since 2019-04-08
 */

@Service
public class CRUDModuleServiceImpl  extends CRUDServiceOnlyImpl<Module> implements CRUDModuleService {

    @Resource
    protected ModuleMapper moduleMapper;

    @Override
    protected BaseMapper<Module> getMasterMapper() {
        return moduleMapper;
    }






}


