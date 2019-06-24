package com.waykichain.chain.biz.domain;

import javax.persistence.Entity;
import javax.annotation.Generated;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.DynamicUpdate;
import com.querydsl.sql.Column;
import org.hibernate.annotations.DynamicInsert;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * SysChainMsgNotifySetting is a Querydsl bean type
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class SysChainMsgNotifySetting implements Serializable {

    @Column("created_at")
    private java.util.Date createdAt;

    @Column("creator_uid")
    private Long creatorUid;

    @Column("description")
    private String description;

    @Column("group_name")
    private String groupName;

    @Column("id")
    @Id
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column("module")
    private String module;

    @Column("msg_format")
    private String msgFormat;

    @Column("msg_id")
    private String msgId;

    @Column("msg_url")
    private String msgUrl;

    @Column("on_self")
    private Integer onSelf;

    @Column("op_uid")
    private Long opUid;

    @Column("robot_name")
    private String robotName;

    @Column("updated_at")
    private java.util.Date updatedAt;

    public java.util.Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCreatorUid() {
        return creatorUid;
    }

    public void setCreatorUid(Long creatorUid) {
        this.creatorUid = creatorUid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getMsgFormat() {
        return msgFormat;
    }

    public void setMsgFormat(String msgFormat) {
        this.msgFormat = msgFormat;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgUrl() {
        return msgUrl;
    }

    public void setMsgUrl(String msgUrl) {
        this.msgUrl = msgUrl;
    }

    public Integer getOnSelf() {
        return onSelf;
    }

    public void setOnSelf(Integer onSelf) {
        this.onSelf = onSelf;
    }

    public Long getOpUid() {
        return opUid;
    }

    public void setOpUid(Long opUid) {
        this.opUid = opUid;
    }

    public String getRobotName() {
        return robotName;
    }

    public void setRobotName(String robotName) {
        this.robotName = robotName;
    }

    public java.util.Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.util.Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}

