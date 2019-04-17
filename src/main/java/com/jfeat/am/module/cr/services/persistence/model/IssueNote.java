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
@TableName("cr_issue_note")
public class IssueNote extends Model<IssueNote> {

    private static final long serialVersionUID = 1L;

    /**
     * primary key

     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * issue ID
     */
	@TableField("issue_id")
	private Long issueId;
    /**
     * 处理人 id
     */
	@TableField("handler_id")
	private Long handlerId;
    /**
     * 处理人名称
     */
	@TableField("handler_name")
	private String handlerName;
    /**
     * 处理前状态
     */
	@TableField("from_status")
	private String fromStatus;
    /**
     * 处理后状态
     */
	@TableField("to_status")
	private String toStatus;
    /**
     * 处理意见
     */
	private String note;
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
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;


	public Long getId() {
		return id;
	}

	public IssueNote setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getIssueId() {
		return issueId;
	}

	public IssueNote setIssueId(Long issueId) {
		this.issueId = issueId;
		return this;
	}

	public Long getHandlerId() {
		return handlerId;
	}

	public IssueNote setHandlerId(Long handlerId) {
		this.handlerId = handlerId;
		return this;
	}

	public String getHandlerName() {
		return handlerName;
	}

	public IssueNote setHandlerName(String handlerName) {
		this.handlerName = handlerName;
		return this;
	}

	public String getFromStatus() {
		return fromStatus;
	}

	public IssueNote setFromStatus(String fromStatus) {
		this.fromStatus = fromStatus;
		return this;
	}

	public String getToStatus() {
		return toStatus;
	}

	public IssueNote setToStatus(String toStatus) {
		this.toStatus = toStatus;
		return this;
	}

	public String getNote() {
		return note;
	}

	public IssueNote setNote(String note) {
		this.note = note;
		return this;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public IssueNote setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
		return this;
	}

	public String getAttachment() {
		return attachment;
	}

	public IssueNote setAttachment(String attachment) {
		this.attachment = attachment;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public IssueNote setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public static final String ID = "id";

	public static final String ISSUE_ID = "issue_id";

	public static final String HANDLER_ID = "handler_id";

	public static final String HANDLER_NAME = "handler_name";

	public static final String FROM_STATUS = "from_status";

	public static final String TO_STATUS = "to_status";

	public static final String NOTE = "note";

	public static final String IMAGE_URL = "image_url";

	public static final String ATTACHMENT = "attachment";

	public static final String CREATE_TIME = "create_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "IssueNote{" +
			"id=" + id +
			", issueId=" + issueId +
			", handlerId=" + handlerId +
			", handlerName=" + handlerName +
			", fromStatus=" + fromStatus +
			", toStatus=" + toStatus +
			", note=" + note +
			", imageUrl=" + imageUrl +
			", attachment=" + attachment +
			", createTime=" + createTime +
			"}";
	}
}
