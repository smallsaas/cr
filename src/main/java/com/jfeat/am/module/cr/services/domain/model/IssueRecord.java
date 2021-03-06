package com.jfeat.am.module.cr.services.domain.model;

import com.jfeat.am.module.cr.services.persistence.model.Issue;

/**
 * Created by Code Generator on 2019-04-08
 */
public class IssueRecord extends Issue{

    private String moduleName;
    private String orgName;
    private String ownerName;
    private String projectName;

    private Integer replyNum;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }
}
