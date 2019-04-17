package com.jfeat.am.module.cr.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
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
@TableName("cr_module")
public class Module extends Model<Module> {

    private static final long serialVersionUID = 1L;

    /**
     * primary key
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 项目名称
     */
	@TableField("project_id")
	private Long projectId;
    /**
     * 项目描述
     */
	private String note;
    /**
     * 模块名称
     */
	@TableField("module_name")
	private String moduleName;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;


	public Long getId() {
		return id;
	}

	public Module setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getProjectId() {
		return projectId;
	}

	public Module setProjectId(Long projectId) {
		this.projectId = projectId;
		return this;
	}

	public String getNote() {
		return note;
	}

	public Module setNote(String note) {
		this.note = note;
		return this;
	}

	public String getModuleName() {
		return moduleName;
	}

	public Module setModuleName(String moduleName) {
		this.moduleName = moduleName;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Module setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public static final String ID = "id";

	public static final String PROJECT_ID = "project_id";

	public static final String NOTE = "note";

	public static final String MODULE_NAME = "module_name";

	public static final String CREATE_TIME = "create_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Module{" +
			"id=" + id +
			", projectId=" + projectId +
			", note=" + note +
			", moduleName=" + moduleName +
			", createTime=" + createTime +
			"}";
	}
}
