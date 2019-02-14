package com.jfeat.am.module.test.services.gen.persistence.model;

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
 * @since 2019-02-13
 */
@TableName("t_test_saas_entity")
public class TestSaasEntity extends Model<TestSaasEntity> {

    @TableField(exist = false)
    private com.alibaba.fastjson.JSONObject extra;

    public com.alibaba.fastjson.JSONObject getExtra() {
        return extra;
    }
    public void setExtra(com.alibaba.fastjson.JSONObject extra) {
        this.extra = extra;
    }


    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 测试名称
     */
	private String name;
    /**
     * �?属组�?
     */
	@TableField("org_id")
	private Long orgId;
    /**
     * 状�??
     */
	private String status;
    /**
     * 创建时间
     */
	@TableField("created_time")
	private Date createdTime;


	public Long getId() {
		return id;
	}

	public TestSaasEntity setId(Long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public TestSaasEntity setName(String name) {
		this.name = name;
		return this;
	}

	public Long getOrgId() {
		return orgId;
	}

	public TestSaasEntity setOrgId(Long orgId) {
		this.orgId = orgId;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public TestSaasEntity setStatus(String status) {
		this.status = status;
		return this;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public TestSaasEntity setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
		return this;
	}

	public static final String ID = "id";

	public static final String NAME = "name";

	public static final String ORG_ID = "org_id";

	public static final String STATUS = "status";

	public static final String CREATED_TIME = "created_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TestSaasEntity{" +
			"id=" + id +
			", name=" + name +
			", orgId=" + orgId +
			", status=" + status +
			", createdTime=" + createdTime +
			"}";
	}
}
