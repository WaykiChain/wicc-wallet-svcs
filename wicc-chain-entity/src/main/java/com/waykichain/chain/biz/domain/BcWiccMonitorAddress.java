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
 * BcWiccMonitorAddress is a Querydsl bean type
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class BcWiccMonitorAddress implements Serializable {

    @Column("active")
    private Integer active;

    @Column("address")
    private String address;

    @Column("created_at")
    private java.util.Date createdAt;

    @Column("current_balance")
    private Long currentBalance;

    @Column("heigh")
    private Integer heigh;

    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    @Id
    @Column("id")
    private Long id;

    @Column("memo")
    private String memo;

    @Column("monitor_amount")
    private Long monitorAmount;

    @Column("monitor_percent")
    private Integer monitorPercent;

    @Column("type")
    private Integer type;

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

    public java.util.Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Long currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Integer getHeigh() {
        return heigh;
    }

    public void setHeigh(Integer heigh) {
        this.heigh = heigh;
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

    public Long getMonitorAmount() {
        return monitorAmount;
    }

    public void setMonitorAmount(Long monitorAmount) {
        this.monitorAmount = monitorAmount;
    }

    public Integer getMonitorPercent() {
        return monitorPercent;
    }

    public void setMonitorPercent(Integer monitorPercent) {
        this.monitorPercent = monitorPercent;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public java.util.Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.util.Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}

