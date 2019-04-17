package com.jfeat.am.module.cr.services.crud.filter;

import com.jfeat.am.module.cr.services.persistence.model.Module;
import com.jfeat.crud.plus.CRUDFilter;


/**
 * Created by Code Generator on 2019-04-08
 */
public class ModuleFilter implements CRUDFilter<Module> {

    private String[] ignoreFields = new String[]{};
    private String[] updateIgnoreFields = new String[]{};

    @Override
    public void filter(Module entity, boolean insertOrUpdate) {

        //if insertOrUpdate is true,means for insert, do this
        if (insertOrUpdate){

            //then insertOrUpdate is false,means for update,do this
        }else {

        }

    }

    @Override
    public String[] ignore(boolean retrieveOrUpdate) {
        //if retrieveOrUpdate is true,means for retrieve ,do this
        if (retrieveOrUpdate){
            return ignoreFields;
            //then retrieveOrUpdate  if false ,means for update,do this
        }else {
            return updateIgnoreFields;
        }
    }
}
