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
 * BcWiccAccountAjustLog is a Querydsl bean type
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class BcWiccAccountAjustLog implements Serializable {

    @Column("address")
    private String address;

    @Column("after_balance")
    private Long afterBalance;

    @Column("ajust_at")
    private java.util.Date ajustAt;

    @Column("ajust_balance")
    private Long ajustBalance;

    @Column("before_balance")
    private Long beforeBalance;

    @Column("coin_symbol")
    private String coinSymbol;

    @Column("created_at")
    private java.util.Date createdAt;

    @Column("deal_balance")
    private Long dealBalance;

    @Column("deal_date")
    private Integer dealDate;

    @Column("deal_memo")
    private String dealMemo;

    @Column("diff_reason")
    private String diffReason;

    @Column("eod")
    private java.util.Date eod;

    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    @Id
    @Column("id")
    private Long id;

    @Column("memo")
    private String memo;

    @Column("type")
    private Integer type;

    @Column("updated_at")
    private java.util.Date updatedAt;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getAfterBalance() {
        return afterBalance;
    }

    public void setAfterBalance(Long afterBalance) {
        this.afterBalance = afterBalance;
    }

    public java.util.Date getAjustAt() {
        return ajustAt;
    }

    public void setAjustAt(java.util.Date ajustAt) {
        this.ajustAt = ajustAt;
    }

    public Long getAjustBalance() {
        return ajustBalance;
    }

    public void setAjustBalance(Long ajustBalance) {
        this.ajustBalance = ajustBalance;
    }

    public Long getBeforeBalance() {
        return beforeBalance;
    }

    public void setBeforeBalance(Long beforeBalance) {
        this.beforeBalance = beforeBalance;
    }

    public String getCoinSymbol() {
        return coinSymbol;
    }

    public void setCoinSymbol(String coinSymbol) {
        this.coinSymbol = coinSymbol;
    }

    public java.util.Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getDealBalance() {
        return dealBalance;
    }

    public void setDealBalance(Long dealBalance) {
        this.dealBalance = dealBalance;
    }

    public Integer getDealDate() {
        return dealDate;
    }

    public void setDealDate(Integer dealDate) {
        this.dealDate = dealDate;
    }

    public String getDealMemo() {
        return dealMemo;
    }

    public void setDealMemo(String dealMemo) {
        this.dealMemo = dealMemo;
    }

    public String getDiffReason() {
        return diffReason;
    }

    public void setDiffReason(String diffReason) {
        this.diffReason = diffReason;
    }

    public java.util.Date getEod() {
        return eod;
    }

    public void setEod(java.util.Date eod) {
        this.eod = eod;
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

