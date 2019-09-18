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
 * BcWiccWalletAccount is a Querydsl bean type
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class BcWiccWalletAccount implements Serializable {

    @Column("address")
    private String address;

    @Column("assets_tokens")
    private String assetsTokens;

    @Column("balance")
    private Long balance;

    @Column("coin_symbol")
    private String coinSymbol;

    @Column("created_at")
    private java.util.Date createdAt;

    @Column("desc")
    private String desc;

    @Column("id")
    @Id
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
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

    public String getAssetsTokens() {
        return assetsTokens;
    }

    public void setAssetsTokens(String assetsTokens) {
        this.assetsTokens = assetsTokens;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

