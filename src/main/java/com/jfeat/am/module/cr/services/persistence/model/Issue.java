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
@TableName("cr_issue")
public class Issue extends Model<Issue> {

    private static final long serialVersionUID = 1L;

    /**
     * primary key

     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 模块ID
     */
	@TableField("module_id")
	private Long moduleId;
    /**
     * 资源隔离使用字段
     */
	@TableField("org_id")
	private Long orgId;
    /**
     * 创建者ID
     */
	@TableField("create_by")
	private Long createBy;
    /**
     * 创建者名称
     */
	@TableField("create_by_name")
	private String createByName;
    /**
     * 问题主要描述
     */
	private String title;
    /**
     * 问题描述
     */
	private String note;
	private Integer priority;
    /**
     * 问题截图
     */
	@TableField("image_url")
	private String imageUrl;
    /**
     * 附件
     */
	private String attachment;
    /**
     * 状态
     */
	private String status;
    /**
     * 指派处理人
     */
	@TableField("owner_id")
	private Long ownerId;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;

	/**
	 * 浏览数
	 */
	@TableField("view_num")
	private Integer viewNum;


	public Long getId() {
		return id;
	}

	public Issue setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public Issue setModuleId(Long moduleId) {
		this.moduleId = moduleId;
		return this;
	}

	public Long getOrgId() {
		return orgId;
	}

	public Issue setOrgId(Long orgId) {
		this.orgId = orgId;
		return this;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public Issue setCreateBy(Long createBy) {
		this.createBy = createBy;
		return this;
	}

	public String getCreateByName() {
		return createByName;
	}

	public Issue setCreateByName(String createByName) {
		this.createByName = createByName;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public Issue setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getNote() {
		return note;
	}

	public Issue setNote(String note) {
		this.note = note;
		return this;
	}

	public Integer getPriority() {
		return priority;
	}

	public Issue setPriority(Integer priority) {
		this.priority = priority;
		return this;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public Issue setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
		return this;
	}

	public String getAttachment() {
		return attachment;
	}

	public Issue setAttachment(String attachment) {
		this.attachment = attachment;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public Issue setStatus(String status) {
		this.status = status;
		return this;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public Issue setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Issue setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}


	public Integer getViewNum() {
		return viewNum;
	}

	public void setViewNum(Integer viewNum) {
		this.viewNum = viewNum;
	}

	public static final String ID = "id";

	public static final String MODULE_ID = "module_id";

	public static final String ORG_ID = "org_id";

	public static final String CREATE_BY = "create_by";

	public static final String CREATE_BY_NAME = "create_by_name";

	public static final String TITLE = "title";

	public static final String NOTE = "note";

	public static final String PRIORITY = "priority";

	public static final String IMAGE_URL = "image_url";

	public static final String ATTACHMENT = "attachment";

	public static final String STATUS = "status";

	public static final String OWNER_ID = "owner_id";

	public static final String CREATE_TIME = "create_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Issue{" +
			"id=" + id +
			", moduleId=" + moduleId +
			", orgId=" + orgId +
			", createBy=" + createBy +
			", createByName=" + createByName +
			", title=" + title +
			", note=" + note +
			", priority=" + priority +
			", imageUrl=" + imageUrl +
			", attachment=" + attachment +
			", status=" + status +
			", ownerId=" + ownerId +
			", createTime=" + createTime +
			"}";
	}
}
