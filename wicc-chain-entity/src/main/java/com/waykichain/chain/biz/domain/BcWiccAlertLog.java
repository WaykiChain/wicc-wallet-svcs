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
 * BcWiccAlertLog is a Querydsl bean type
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class BcWiccAlertLog implements Serializable {

    @Column("active")
    private Integer active;

    @Column("address")
    private String address;

    @Column("alert_at")
    private Integer alertAt;

    @Column("alert_info")
    private String alertInfo;

    @Column("alert_param")
    private String alertParam;

    @Column("alert_type")
    private Integer alertType;

    @Column("created_at")
    private java.util.Date createdAt;

    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    @Id
    @Column("id")
    private Long id;

    @Column("memo")
    private String memo;

    @Column("notify_link_url")
    private String notifyLinkUrl;

    @Column("updated_at")
    private java.util.Date updatedAt;

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAlertAt() {
        return alertAt;
    }

    public void setAlertAt(Integer alertAt) {
        this.alertAt = alertAt;
    }

    public String getAlertInfo() {
        return alertInfo;
    }

    public void setAlertInfo(String alertInfo) {
        this.alertInfo = alertInfo;
    }

    public String getAlertParam() {
        return alertParam;
    }

    public void setAlertParam(String alertParam) {
        this.alertParam = alertParam;
    }

    public Integer getAlertType() {
        return alertType;
    }

    public void setAlertType(Integer alertType) {
        this.alertType = alertType;
    }

    public java.util.Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getNotifyLinkUrl() {
        return notifyLinkUrl;
    }

    public void setNotifyLinkUrl(String notifyLinkUrl) {
        this.notifyLinkUrl = notifyLinkUrl;
    }

    public java.util.Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.util.Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}

