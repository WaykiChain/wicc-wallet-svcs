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
 * BcWiccWalletAddress is a Querydsl bean type
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class BcWiccWalletAddress implements Serializable {

    @Column("address")
    private String address;

    @Column("created_at")
    private java.util.Date createdAt;

    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    @Id
    @Column("id")
    private Long id;

    @Column("password")
    private String password;

    @Column("updated_at")
    private java.util.Date updatedAt;

    @Column("wallet_id")
    private Long walletId;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public java.util.Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.util.Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

}

