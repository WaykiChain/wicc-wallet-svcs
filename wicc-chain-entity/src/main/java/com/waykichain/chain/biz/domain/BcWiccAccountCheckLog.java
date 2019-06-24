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
 * BcWiccAccountCheckLog is a Querydsl bean type
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class BcWiccAccountCheckLog implements Serializable {

    @Column("address")
    private String address;

    @Column("balance")
    private Long balance;

    @Column("batch_id")
    private Long batchId;

    @Column("chain_balance")
    private Long chainBalance;

    @Column("coin_symbol")
    private String coinSymbol;

    @Column("created_at")
    private java.util.Date createdAt;

    @Column("deal_balance")
    private Long dealBalance;

    @Column("deal_chain_balance")
    private Long dealChainBalance;

    @Column("deal_date")
    private java.util.Date dealDate;

    @Column("deal_memo")
    private String dealMemo;

    @Column("diff_amount")
    private Long diffAmount;

    @Column("diff_reason")
    private String diffReason;

    @Column("eod")
    private java.util.Date eod;

    @Column("id")
    @Id
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column("memo")
    private String memo;

    @Column("status")
    private Integer status;

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

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getChainBalance() {
        return chainBalance;
    }

    public void setChainBalance(Long chainBalance) {
        this.chainBalance = chainBalance;
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

    public Long getDealChainBalance() {
        return dealChainBalance;
    }

    public void setDealChainBalance(Long dealChainBalance) {
        this.dealChainBalance = dealChainBalance;
    }

    public java.util.Date getDealDate() {
        return dealDate;
    }

    public void setDealDate(java.util.Date dealDate) {
        this.dealDate = dealDate;
    }

    public String getDealMemo() {
        return dealMemo;
    }

    public void setDealMemo(String dealMemo) {
        this.dealMemo = dealMemo;
    }

    public Long getDiffAmount() {
        return diffAmount;
    }

    public void setDiffAmount(Long diffAmount) {
        this.diffAmount = diffAmount;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

