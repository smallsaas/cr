package com.jfeat.am.module.test.services.gen.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.am.module.test.services.gen.persistence.model.TestSaasEntity;
import com.jfeat.am.module.test.services.gen.persistence.dao.TestSaasEntityMapper;
import com.jfeat.am.module.test.services.gen.crud.service.CRUDTestSaasEntityService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDTestSaasEntityService
 * @author Code Generator
 * @since 2019-02-13
 */

@Service
public class CRUDTestSaasEntityServiceImpl  extends CRUDServiceOnlyImpl<TestSaasEntity> implements CRUDTestSaasEntityService {





        @Resource
        protected TestSaasEntityMapper testSaasEntityMapper;

        @Override
        protected BaseMapper<TestSaasEntity> getMasterMapper() {
                return testSaasEntityMapper;
        }







}


