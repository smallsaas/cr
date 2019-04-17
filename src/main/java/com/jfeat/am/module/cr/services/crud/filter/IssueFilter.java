package com.jfeat.am.module.cr.services.crud.filter;

import com.jfeat.am.module.cr.services.persistence.model.Issue;
import com.jfeat.crud.plus.CRUDFilter;


/**
 * Created by Code Generator on 2019-04-08
 */
public class IssueFilter implements CRUDFilter<Issue> {

    private String[] ignoreFields = new String[]{};
    //任何时候，更新 ISSUE都需要过滤掉 创建者 名称，创建者 ID ，以及  ISSUE  的创建时间
    private String[] updateIgnoreFields = new String[]{"create_by_name","create_by","create_time"};

    @Override
    public void filter(Issue entity, boolean insertOrUpdate) {

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
