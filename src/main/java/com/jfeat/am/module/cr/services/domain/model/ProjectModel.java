package com.jfeat.am.module.cr.services.domain.model;

import com.jfeat.am.module.cr.services.persistence.model.Module;
import com.jfeat.am.module.cr.services.persistence.model.Project;

import java.util.List;

public class ProjectModel extends Project {

    List<Module> modules;


    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}
