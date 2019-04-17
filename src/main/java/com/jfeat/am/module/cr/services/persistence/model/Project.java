package com.jfeat.am.module.cr.services.persistence.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Code Generator
 * @since 2019-04-08
 */
@TableName("cr_project")
public class Project extends Model<Project> {

    private static final long serialVersionUID = 1L;

    /**
     * primary key

     */
	private Long id;
    /**
     * 资源隔离使用字段
     */
	@TableField("org_id")
	private Long orgId;
    /**
     * 项目名称

     */
	@TableField("project_name")
	private String projectName;
    /**
     * 项目描述
     */
	private String note;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;


	public Long getId() {
		return id;
	}

	public Project setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getOrgId() {
		return orgId;
	}

	public Project setOrgId(Long orgId) {
		this.orgId = orgId;
		return this;
	}

	public String getProjectName() {
		return projectName;
	}

	public Project setProjectName(String projectName) {
		this.projectName = projectName;
		return this;
	}

	public String getNote() {
		return note;
	}

	public Project setNote(String note) {
		this.note = note;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Project setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public static final String ID = "id";

	public static final String ORG_ID = "org_id";

	public static final String PROJECT_NAME = "project_name";

	public static final String NOTE = "note";

	public static final String CREATE_TIME = "create_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Project{" +
			"id=" + id +
			", orgId=" + orgId +
			", projectName=" + projectName +
			", note=" + note +
			", createTime=" + createTime +
			"}";
	}
}
