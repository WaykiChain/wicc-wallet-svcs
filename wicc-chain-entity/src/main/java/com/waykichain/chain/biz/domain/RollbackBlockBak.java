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
 * RollbackBlockBak is a Querydsl bean type
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class RollbackBlockBak implements Serializable {

    @Column("chainwork")
    private String chainwork;

    @Column("confirmations")
    private Integer confirmations;

    @Column("created_at")
    private java.util.Date createdAt;

    @Column("fuel")
    private Integer fuel;

    @Column("fuel_rate")
    private Integer fuelRate;

    @Column("hash")
    private String hash;

    @Column("hash_on_chain")
    private String hashOnChain;

    @Column("height")
    private Integer height;

    @Column("id")
    @Id
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column("merkle_root")
    private String merkleRoot;

    @Column("next_block_hash")
    private String nextBlockHash;

    @Column("nonce")
    private Long nonce;

    @Column("number")
    private Integer number;

    @Column("previous_block_hash")
    private String previousBlockHash;

    @Column("size")
    private Integer size;

    @Column("status")
    private Integer status;

    @Column("time")
    private java.util.Date time;

    @Column("updated_at")
    private java.util.Date updatedAt;

    @Column("version")
    private Integer version;

    public String getChainwork() {
        return chainwork;
    }

    public void setChainwork(String chainwork) {
        this.chainwork = chainwork;
    }

    public Integer getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(Integer confirmations) {
        this.confirmations = confirmations;
    }

    public java.util.Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getFuel() {
        return fuel;
    }

    public void setFuel(Integer fuel) {
        this.fuel = fuel;
    }

    public Integer getFuelRate() {
        return fuelRate;
    }

    public void setFuelRate(Integer fuelRate) {
        this.fuelRate = fuelRate;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getHashOnChain() {
        return hashOnChain;
    }

    public void setHashOnChain(String hashOnChain) {
        this.hashOnChain = hashOnChain;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public void setMerkleRoot(String merkleRoot) {
        this.merkleRoot = merkleRoot;
    }

    public String getNextBlockHash() {
        return nextBlockHash;
    }

    public void setNextBlockHash(String nextBlockHash) {
        this.nextBlockHash = nextBlockHash;
    }

    public Long getNonce() {
        return nonce;
    }

    public void setNonce(Long nonce) {
        this.nonce = nonce;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getPreviousBlockHash() {
        return previousBlockHash;
    }

    public void setPreviousBlockHash(String previousBlockHash) {
        this.previousBlockHash = previousBlockHash;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public java.util.Date getTime() {
        return time;
    }

    public void setTime(java.util.Date time) {
        this.time = time;
    }

    public java.util.Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.util.Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}

