package com.jfeat.am.module.cr.services.domain.model;

import com.jfeat.am.module.cr.services.persistence.model.IssueNote;

import java.util.List;

public class IssueModel extends IssueRecord {
    List<IssueNote> issueNotes;


    public List<IssueNote> getIssueNotes() {
        return issueNotes;
    }

    public void setIssueNotes(List<IssueNote> issueNotes) {
        this.issueNotes = issueNotes;
    }
}
